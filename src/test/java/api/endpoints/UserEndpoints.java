package api.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.UserPayload;


public class UserEndpoints {
	
	public static Response create_user(UserPayload payload) {
		
		Response res=given()
				.body(payload)
			.when()
				.post(routes.post_url);
		return res;
	}
	
	public static Response read_user(String id) {
		Response res=given()
				.pathParam("id", id)
		.when()
			.get(routes.get_url);
		return res;
	}
	
	public static Response update_user(UserPayload payload,String id) {
		Response res=given()
				.pathParam("id", id)
				.body(payload)
			.when()
				.put(routes.put_url);
		return res;
	}
	
	public static Response delete_user(String id) {
		Response res=given()
				.pathParam("id", id)
			.when()
				.delete(routes.del_url);
		return res;
	}
}

