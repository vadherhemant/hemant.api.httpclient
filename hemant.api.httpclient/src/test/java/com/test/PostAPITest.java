package com.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.client.RestClient;
import com.data.Users;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostAPITest extends TestBase{
	
	String appUri;
	
	@BeforeMethod
	public void setup() {
		
		String baseURL = prop.getProperty("BaseURL");
		String serviceURL = prop.getProperty("ServiceURL");
		appUri = baseURL + serviceURL;
		
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		
		RestClient restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Content-Type", "application/json");
		
		//jackson API
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("hcv", "Sr Mgr");

		//object to json file
		mapper.writeValue(new File(System.getProperty("user.dir") + "/src/main/java/com/data/users.json" ), users);

		//object to json in string
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		
		CloseableHttpResponse closeableHttpResponse = restClient.post(appUri, userJsonString, headerMap);
		System.out.println("closeableHttpResponse --> " + closeableHttpResponse);
		
		//1 status code:
		int gotStatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(RESPONSE_STATUS_CODE_201, gotStatusCode);
		
		//2 json string
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		System.out.println("responseString --> " + responseString);
		
		//note: the output of responseString and jsonObject is the same
		JSONObject jsonObject = new JSONObject(responseString);
		System.out.println("jsonObject --> " + jsonObject);
		
		//json to java object
		Users userResponseObject = mapper.readValue(responseString, Users.class);
		System.out.println("userResponseObject --> " + userResponseObject);
		
		//assert
		Assert.assertTrue(users.getName().equals(userResponseObject.getName()));
		Assert.assertTrue(users.getJob().equals(userResponseObject.getJob()));
		
	}

}
