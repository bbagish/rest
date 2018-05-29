package rest.webapi.udemy;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.http.ContentType;

public class PostReq {
	@Test
	public void test() {
		baseURI = "https://maps.googleapis.com/";
		given().
		queryParam("key", "AIzaSyBEcPtgPUKObLbUaiWTm4zFuJfaIUGBp10")
		.body("{"+ "\"location\": {"
				+ 
				"\"lat\": -33.8669710,"+
				"\"lng\": 151.1958750"+
				" },"+
				"\"accuracy\": 50," + 
				"\"name\": \"Google Shoes!\","+
			 " \"phone_number\": \"(02) 9374 4000\","+
			  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
			"\"types\": [\"shoe_store\"],"+
				"\"website\": \"http://www.google.com.au/\","+
				"\"language\": \"en-AU\""
				+"}").when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		and().body("status", equalTo("OK"));
	}
	@Test
	public void test1() {
		
	}
}
