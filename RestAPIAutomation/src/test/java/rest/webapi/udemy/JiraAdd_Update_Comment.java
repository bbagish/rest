package rest.webapi.udemy;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraAdd_Update_Comment {
	static String extractingBugId=null;
	static String commentID="";
	@Test
	public void test() throws IOException {
		baseURI ="http://localhost:9090";
		
		// Creating bug
		extractingBugId=ReusableMethods.createBug("\"creating another bug in Cybertek\"", baseURI);
		System.out.println(extractingBugId);
		Response response=given().log().all().header("Content-Type", "application/json")
		.header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKeyForJira())
		.body("{" + 
				"    \"body\": \"Kahren this is a demo\"," + 
				"    \"visibility\": {" + 
				"      \"type\": \"role\"," + 
				"      \"value\": \"Administrators\"" + 
				"    }" + 
				"  }").when().post("/rest/api/2/issue/"+extractingBugId+"/comment").then().statusCode(201).extract().response();
		JsonPath js=ReusableMethods.rawDataToJSON(response);
		commentID=js.get("id");
		System.out.println(commentID);
	//updating comments
		Response response2=given().header("Content-Type", "application/json")
				.header("Cookie", "JSESSIONID="+ReusableMethods.getSessionKeyForJira())
				.body("{" + 
						"    \"body\": \"Don't need to update\"," + 
						"    \"visibility\": {" + 
						"      \"type\": \"role\"," + 
						"      \"value\": \"Administrators\"" + 
						"    }" + 
						"  }").when().put("/rest/api/2/issue/"+extractingBugId+"/comment/"+commentID).then().statusCode(200).extract().response();
			
		
	}
}	
	