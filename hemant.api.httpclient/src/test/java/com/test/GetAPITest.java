package com.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.client.RestClient;
import com.util.TestUtil;

public class GetAPITest extends TestBase{
	TestBase testBase;
	String url;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setup() {
		
		String baseURL = prop.getProperty("BaseURL");
		
		String serviceURL = prop.getProperty("ServiceURL");
	
		url = baseURL + serviceURL;
		
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Content-Type", "application/json");
		
		headerMap.put("password","1112233");
		
		
		closeableHttpResponse = restClient.get(url, headerMap);
		
		// get status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

		System.out.println("Status Code is --> " + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Expected: "+ RESPONSE_STATUS_CODE_200 + " Got: " + statusCode);
		
		// json string
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		String per_page = TestUtil.getValueByKey(responseString, "/per_page");
		System.out.println(per_page);
	
		String total = TestUtil.getValueByKey(responseString, "/total");
		System.out.println(total);
		Assert.assertEquals(Integer.parseInt(total), 12);

		String lastname = TestUtil.getValueByKey(responseString, "/data[0]/last_name");
		System.out.println(lastname);
		Assert.assertEquals(lastname, "Bluth");
		
		String avatar = TestUtil.getValueByKey(responseString, "/data[0]/avatar");
		System.out.println(avatar);
		

		
		// all header in hash map
		Header[] allHeaderArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> headerHashMap = new HashMap<String, String>();

		for (Header header : allHeaderArray) {
			headerHashMap.put(header.getName(), header.getValue());
		}

		System.out.println("This is Header HashMap --> " + headerHashMap);

		
	}



	
}
