package rest.webapi.udemy;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import rest.webapi.udemy.files.Resources;

public class ReusableMethods {
public static XmlPath rawDataToXML(Response response) {
	String responseRawToString=response.asString();
	XmlPath x= new XmlPath(responseRawToString);
	return x;
}
public static JsonPath rawDataToJSON(Response response) {
	String responseRawToString=response.asString();
	JsonPath x= new JsonPath(responseRawToString);
	return x;
}
public static String getSessionKeyForJira() throws IOException {
	baseURI ="http://localhost:9090";
	Response response=given().header("Content-Type", "application/json").body("{ \"username\": \"bagishman.pro\", \"password\": \"cadilacescalade1994\"}")
	.when().post(Resources.jiraAuth()).then().statusCode(200).extract().response();
	JsonPath json=ReusableMethods.rawDataToJSON(response);
	String sessionID=json.get("session.value");
	
	return sessionID;
}
public static String createBug(String description, String baseURIuser) throws IOException {
	baseURI =baseURIuser;
	Response response=given().log().all().header("Content-Type", "application/json")
	.header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKeyForJira())
	.body("{"+
		    "\"fields\": {"+
	        "\"project\": {"+
	            "\"key\": \"PNCVIR\""+
	       "},"+
	        "\"summary\": \"Bug with comment\","+
	       "\"description\": "+description+","+
	       " \"issuetype\": {"+
	            "\"name\": \"Bug\""+
	        "}"+ 
	      "}}").when().post("/rest/api/2/issue").then().statusCode(201).extract().response();
	JsonPath json=ReusableMethods.rawDataToJSON(response);
	String id=json.get("id");

	return 	id;
}
}
