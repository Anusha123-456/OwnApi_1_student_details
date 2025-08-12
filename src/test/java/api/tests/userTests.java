package api.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.UserPayload;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class userTests {
	
	Faker faker;
	UserPayload payload;
	
	@BeforeClass()
	void generate_value() {
		faker=new Faker();
		payload=new UserPayload();
		String id=UUID.randomUUID().toString().substring(0,4);
		payload.setId(id);
		payload.setName(faker.name().name());
		payload.setLocation(faker.address().city());
		payload.setPhone(faker.phoneNumber().phoneNumber());
		List<String> courses = Arrays.asList("java", "selenium", "python", "rest-assured", "cypress");
		Collections.shuffle(courses);

		payload.setCourse(courses.subList(0, 2));		
	}
	
	@Test(priority=1)
	void crete_user() {
		Response res=UserEndpoints.create_user(payload);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 201);
	}
	
	@Test(priority=2)
	void read_user() {
		Response res=UserEndpoints.read_user(payload.getId());
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/json");
		Headers data=res.getHeaders();
		for(Header key:data) {
			System.out.println(key.getName()+"  :  "+key.getValue());
		}
	}
	
	@Test(priority=3)
	void update_user() {
		payload.setName(faker.name().name());
		payload.setLocation(faker.address().city());
		payload.setPhone(faker.phoneNumber().phoneNumber());
		
		Response res=UserEndpoints.update_user(payload,payload.getId());
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	void delete_user() {
		Response res=UserEndpoints.delete_user(payload.getId());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
	}
}
