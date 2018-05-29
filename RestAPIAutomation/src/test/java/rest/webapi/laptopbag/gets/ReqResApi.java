package rest.webapi.laptopbag.gets;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import rest.webapi.laptopbag.helpers.RestHelper;
import rest.webapi.laptopbag.helpers.RestHelper.Methods;

public class ReqResApi {
	// @Test
	public void GetValuesFromResource() throws URISyntaxException {
		URI uri = new URI("https://reqres.in/api/users/2");
		String body = given().accept(ContentType.JSON).when().get(uri).thenReturn().body().asString();

		System.out.println(body);

	}

	@Test
	public void JsonPathPractice() throws URISyntaxException {
		String ping = RestHelper.buildURI(Methods.PING);
		System.out.println(when().get(ping).asString());
		URI uri = new URI(ping);
		System.out.println(uri);
		String json = given().accept(ContentType.JSON).when().get(RestHelper.buildURI(Methods.ALL)).andReturn().body()
				.asString();
		JsonPath jsonBody = new JsonPath(json);
		List<Integer> ids = jsonBody.getList("Id");
		System.out.println(ids);
		List<String> brands = jsonBody.getList("BrandName");
		System.out.println(brands);
		List<String> features = jsonBody.getList("Features.Feature");
		System.out.println(features);
		List<String> laptopNames = jsonBody.getList("findAll{it.Id<150}.LaptopName");
		System.out.println(laptopNames);
		
		List<Integer> id=jsonBody.getList("findAll{it.BrandName=='Apple'}.Id");
		System.out.println(id);
		

	}
	@Test
	public void XmlPathSamples() throws URISyntaxException {
		URI uri=new URI("http://localhost:8080/laptop-bag/webapi/api/all");
		Response response=given().accept(ContentType.XML)
		.when().get(uri);
		assertEquals(200, response.statusCode());
		XmlPath xml=new XmlPath(response.body().asString());
		List<String> brands=xml.getList("laptopDetailss.Laptop.BrandName");
		System.out.println(brands);
	}
}
