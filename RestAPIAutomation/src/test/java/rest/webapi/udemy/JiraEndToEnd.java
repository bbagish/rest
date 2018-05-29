package rest.webapi.udemy;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import rest.webapi.udemy.files.Resources;

public class JiraEndToEnd {

	@Test
	public void test() throws IOException {
		baseURI ="http://localhost:9090";
		
		 //Creating bug
		String bugId=ReusableMethods.createBug("\"Kahren this is a demo\"", baseURI);

}
		}
