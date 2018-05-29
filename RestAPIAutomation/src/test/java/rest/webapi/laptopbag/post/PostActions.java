package rest.webapi.laptopbag.post;

import static io.restassured.RestAssured.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import rest.webapi.laptopbag.helpers.RestHelper;
import rest.webapi.laptopbag.helpers.RestHelper.HeaderType;
import rest.webapi.laptopbag.helpers.RestHelper.Methods;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class PostActions {
	String id = (int) (1000 * (Math.random())) + "";
	String jsonBody = "{" + "\"BrandName\": \"Apple\"," + "\"Features\": {" + "\"Feature\": [\"8GB RAM\","
			+ "\"1TB Hard Drive\"]" + "}," + "\"Id\": " + id + "," + "\"LaptopName\": \"MacBook Pro\"" + "}";

	@Test
	public void addLaptop() throws URISyntaxException {
		URI uri = new URI("http://localhost:8080/laptop-bag/webapi/api/add");
		System.out.println(id);
		given().accept(ContentType.JSON).contentType(ContentType.JSON).and().body(jsonBody).log().all().when().post(uri).then()
				.assertThat().statusCode(200).and().assertThat().body("Id", equalTo(Integer.parseInt(id)));

	}

	//@Test
	public void postWithHeaders() throws URISyntaxException {
		URI uri = new URI("http://localhost:8080/laptop-bag/webapi/api/add");
		// Map<String, String> headers=new HashMap<>();
		// headers.put("Content-Type", "application/json");
		// headers.put("Accept", "application/json");
		with().headers(RestHelper.getHeaders(HeaderType.JSON)).body(jsonBody).when().post(uri).then().assertThat()
				.body("Features.Feature", hasItems("8GB RAM", "1TB Hard Drive"));
		JsonPath jsonPath = new JsonPath(
				with().headers(RestHelper.getHeaders(HeaderType.JSON)).body(jsonBody).when().post(uri).asString());
		List<String> expFeatures = Arrays.asList("8GB RAM", "1TB Hard Drive");
		assertTrue(jsonPath.getList("Features.Feature").containsAll(expFeatures));
	}

	
}
