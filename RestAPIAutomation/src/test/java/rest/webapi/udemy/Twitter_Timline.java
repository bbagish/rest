package rest.webapi.udemy;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Twitter_Timline {
	String consumerKey="Wzf4iffJ4TORnrzlcKgZlscmp";
	String consumerSecret="SJiW3JOTiiV0MOJPfU5H6mJzkBuogA92Fe54JzCrZawZVVGPjq";
	String accessToken="954863864843206656-rUsj00N682J9kiWRljv5M1u02c02frL";
	String TokenSecret="ajP42ngHeMNkQmPH1kh0VTeyn6ots0NJV3YyWzel8zAlA";
	
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
