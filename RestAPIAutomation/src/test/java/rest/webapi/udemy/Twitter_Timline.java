package rest.webapi.udemy;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Twitter_Timline {
	String consumerKey="";
	String consumerSecret="";
	String accessToken="-";
	String TokenSecret="";
	
	@Test
	public void getLatestTwitt() {
		//getting access to Twitter
		baseURI = "https://api.twitter.com/1.1/statuses";
		Response response=given().auth().oauth(consumerKey, consumerSecret, accessToken, TokenSecret).queryParam("count", "1")
		.when().get("home_timeline.json").then().extract().response();
		String response2=response.asString();
		System.out.println(response2);
		JsonPath json=new JsonPath(response2);
		System.out.println(json.get("text") + " twitt id"+ json.get("id"));
		
	}
}
