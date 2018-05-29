package rest.webapi.laptopbag.gets;

import static io.restassured.RestAssured.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import groovy.util.logging.Log;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicGets {
	static String pingURL = "http://localhost:8080/laptop-bag/webapi/api/ping/Belek";

	@Test
	public void GetWeatherDetails() {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method
		// URL.
		// This will return the Response from the server. Store the response in a
		// variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		JsonPath jsonPathEvaluator = response.jsonPath();
		String city = jsonPathEvaluator.get("WindSpeed");
		 
		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + city);

	}
}
