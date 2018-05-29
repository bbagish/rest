package rest.webapi.laptopbag.helpers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

public class RestHelper {
	public static String buildURI(Methods method) throws URISyntaxException {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/laptop-bag/webapi/api";

		String apiMethod = null;

		switch (method) {
		case PING:
			apiMethod = "/ping/World";
			break;
		case ALL:
			apiMethod = "/all";
			break;
		case FIND:
			apiMethod = "/find/";
			break;
		case ADD:
			apiMethod = "/add";
			break;
		default:
			throw new RuntimeException("Invalid API Method");
		}
		return apiMethod;
	}

	public enum Methods {
		PING, ALL, FIND, ADD
	}

	public static Map<String, String> getHeaders(HeaderType type) {
		Map<String, String> headers = new HashMap<>();
		switch (type) {
		case JSON:
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			break;
		case XML:
			headers.put("Content-Type", "application/xml");
			headers.put("Accept", "application/xml");
			break;
		default:
			throw new RuntimeException("Invalid Header Type");
		}
		return headers;
	}

	public enum HeaderType {
		JSON, XML
	}

}
