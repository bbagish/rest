package rest.webapi.udemy;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics5 {
	@Test
	public void test1() {
		baseURI = "https://maps.googleapis.com/";
		// equalTo only applies to body
		Response response = given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", "token").log().all().when().get("maps/api/place/nearbysearch/json")
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and()
				.body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
				.header("Server", "scaffolding on HTTPServer2").log().body().extract().response();
		JsonPath json=ReusableMethods.rawDataToJSON(response);
		int count=json.get("results.size()");
		for(int i=0; i<count; i++) {
			System.out.println(json.get("results["+i+"].name"));
		}
		System.out.println(count);
		
	}
}
