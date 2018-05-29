package rest.webapi.udemy;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.http.ContentType;

public class basics {
	@Test
	public void test1() {
		baseURI = "https://maps.googleapis.com/";
		//equalTo only applies to body
		given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", "AIzaSyBEcPtgPUKObLbUaiWTm4zFuJfaIUGBp10").when().get("maps/api/place/nearbysearch/json")
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and()
				.body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")) 
				.and().header("Server", "scaffolding on HTTPServer2");
		
		
		
		
		
		
		
		
	}
}



