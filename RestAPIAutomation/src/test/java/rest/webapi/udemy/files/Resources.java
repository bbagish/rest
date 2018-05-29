package rest.webapi.udemy.files;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import rest.webapi.udemy.ReusableMethods;

public class Resources {
public static String addPlace() {
	String resource="/maps/api/place/add/json";
	return resource;
}
public static String deletePlace() {
	String resource="/maps/api/place/delete/json";
	return resource;
}
public static String jiraAuth() {
	String resource="/rest/auth/1/session";
	return resource;
}

}
