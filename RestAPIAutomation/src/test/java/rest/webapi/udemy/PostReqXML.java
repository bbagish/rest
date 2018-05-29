package rest.webapi.udemy;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class PostReqXML {
	@Test
	public void test() throws IOException {
		baseURI = "https://maps.googleapis.com/";
		String XMLbody = GenerateStringFromResource("src/test/java/rest/webapi/udemy/body.xml");

		Response responseRaw=given().queryParam("key", "AIzaSyBEcPtgPUKObLbUaiWTm4zFuJfaIUGBp10").body(XMLbody).when()
				.post("/maps/api/place/add/xml").then().assertThat().statusCode(200).and().contentType(ContentType.XML)
				.and().extract().response();
		XmlPath xml= ReusableMethods.rawDataToXML(responseRaw);
		
		
		
	}

	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
