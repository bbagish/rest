package rest.webapi.udemy;
import rest.webapi.udemy.files.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostReq2 {
	Properties properties = new Properties();

	@Before
	public void setUp() throws IOException {
		FileInputStream file = new FileInputStream("src/test/java/rest/webapi/udemy/files/evn.properties");
		properties.load(file);
		baseURI = properties.getProperty("HOST");
	}

	@Test
	public void AddandDeletePlace() {
		
		Response response = given().queryParam("key", properties.getProperty("KEY")).body(PayLoad.getPostBody()).when()
				.post(Resources.addPlace()).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK")).extract().response();

		// response comes in raw format
		// in this step we convert it to String

		String responseString = response.asString();
		// System.out.println(responseString);

		// converting String to Json Format
		JsonPath jsonPath = new JsonPath(responseString);

		String place = jsonPath.get("place_id");
		System.out.println(place);

		// deleting

		given().queryParam("key", properties.getProperty("KEY"))
				.body("{" + "\"place_id\": \"" + place + "\"" + "}").when().post(Resources.deletePlace()).then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK"));

	}
}
