package rest.webapi.laptopbag.post;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.http.ContentType;
import rest.webapi.laptopbag.helpers.POJOHelper;
import rest.webapi.laptopbag.helpers.RestHelper;
import rest.webapi.laptopbag.helpers.RestHelper.HeaderType;
import rest.webapi.laptopbag.helpers.RestHelper.Methods;
import rest.webapi.laptopbag.pojos.Features;
import rest.webapi.laptopbag.pojos.Laptop;

public class PostWithPojos {
	@Test // cerialization
	public void PostActionUsingPOJO() throws URISyntaxException {
		URI postURI = new URI(RestHelper.buildURI(Methods.ADD));

		Laptop laptop = new Laptop();
		laptop.setBrandName("ASUS");
		laptop.setId(333);
		laptop.setLaptopName("ZenBook");

		List<String> laptopFeatures = Arrays.asList("32GB RAM", "2GB VRAM", "255GB SSD");
		Features features = new Features();
		features.setFeature(laptopFeatures);
		laptop.setFeatures(features);
		given().headers(RestHelper.getHeaders(HeaderType.JSON)).and().body(laptop).when().post(postURI).then()
				.assertThat().statusCode(200);

	}

	@Test // deserilazation
	public void PostActionUsingPOJO1() throws URISyntaxException {
		URI postURI = new URI(RestHelper.buildURI(Methods.ADD));

		Laptop laptop = new Laptop();
		laptop.setBrandName("HP");
		laptop.setId(444);
		laptop.setLaptopName("Envy");

		List<String> laptopFeatures = Arrays.asList("32GB RAM", "2GB VRAM", "255GB SSD");
		Features features = new Features();
		features.setFeature(laptopFeatures);

		laptop.setFeatures(features);

		Laptop returnPOJO = given().headers(RestHelper.getHeaders(HeaderType.JSON)).and().body(laptop).log().all()
				.when().post(postURI).thenReturn().body().as(Laptop.class);
		Gson gson = new Gson();
		String str = gson.toJson(returnPOJO);
		System.out.println(str);

		// System.out.println(returnPOJO.getBrandName());
		//
		// assertTrue(returnPOJO.getId()==444);
		// assertEquals("Envy", returnPOJO.getLaptopName());
		//
		// List<String> featuress=returnPOJO.getFeatures().getFeature();
		// System.out.println(featuress);
	}

	@Test
	public void postUsingPojoHelper() throws URISyntaxException {
		URI postURI = new URI(RestHelper.buildURI(Methods.ADD));
		Laptop laptopPost = POJOHelper.buildNewLaptop("Toshiba", "Monster", "16GB Ram, OnSale, 500GB SSD");
		given().headers(RestHelper.getHeaders(HeaderType.JSON)).and().body(laptopPost).when().post(postURI).then()
				.assertThat().statusCode(200);
		int id = laptopPost.getId();
		URI findURI = new URI(RestHelper.buildURI(Methods.FIND) + id);
		// with().accept(ContentType.JSON).when().get(findURI).thenReturn().body().prettyPrint();
		Laptop retLaptop = with().accept(ContentType.JSON).when().get(findURI).thenReturn().body().as(Laptop.class);
		assertEquals(laptopPost.getBrandName(), retLaptop.getBrandName());
		assertEquals(laptopPost.getLaptopName(), retLaptop.getLaptopName());
		assertEquals(laptopPost.getId(), retLaptop.getId());

		assertEquals(laptopPost.getFeatures().getFeature(), retLaptop.getFeatures().getFeature());
	}
}
