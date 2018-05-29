package rest.webapi.laptopbag.auth;
import static io.restassured.RestAssured.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class AccessTokenGet {
@Test
public void getDirectionsMapsAPI() throws URISyntaxException {
	final String AUTH_TOKEN="AIzaSyCHdXPFoC9z-ANkZtAePwEt9VlBDtPSZV4";
	URI uri=new URI("https://maps.googleapis.com/maps/api/directions/json");


	String directions=given().params("origin", "15205", "destination", "20006")
	.auth().oauth2(AUTH_TOKEN).accept(ContentType.JSON)
	.when().get(uri).thenReturn()
	.body().asString();
	Response response =when().get(uri);
	response.getStatusCode();
	
	
	
	JsonPath json=new JsonPath(directions);
	String distance=json.getString("routes.legs.distance.text");
	String duration=json.getString("routes.legs.duration.text");	
	System.out.println("distance : "+distance);
	System.out.println("duration : "+duration);
}








}










