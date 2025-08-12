package api.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.UserPayload;
import api.utilities.DataProvider2;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	private static final UserPayload String = null;

	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProvider2.class)
	public void testcretaeUser(String id,String name,String location,String number,List<String> course) throws InterruptedException {
	UserPayload payload=new UserPayload();
	payload.setId(id);
	payload.setName(name);
	payload.setLocation(location);
	payload.setPhone(number);
	payload.setCourse(course);
	Response res=UserEndpoints.create_user(payload);
	res.then().log().all();
	Assert.assertEquals(res.getStatusCode(), 201);
	Thread.sleep(3000);
	}
	
	@Test(priority=2,dataProvider="Id",dataProviderClass=DataProvider2.class)
	public void testGetUser(String id) throws InterruptedException{
		
		Response res=UserEndpoints.read_user(id);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		Thread.sleep(3000);
	}
	
//	@Test(priority=3,dataProvider="Id",dataProviderClass=DataProvider2.class)
//	public void testupdateUser(String id) {
//		Response res=UserEndpoints.update_user(String id,String name,String location,String number,List<String> course, String id){
//			
//		}
//	}
	
	@Test(priority=2,dataProvider="Id",dataProviderClass=DataProvider2.class)
	public void testdeleteUser(String id) throws InterruptedException{
		Response res=UserEndpoints.delete_user(id);
		Assert.assertEquals(res.getStatusCode(), 200);
		Thread.sleep(3000);
	}

	
}
