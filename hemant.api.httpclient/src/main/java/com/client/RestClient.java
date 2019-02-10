package com.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	// GET method with only url
	public CloseableHttpResponse get(String uri) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(uri);

		CloseableHttpResponse closeableHttpResponse;

		closeableHttpResponse = httpClient.execute(httpget);

		return closeableHttpResponse;
	}

	// GET method with url + header
	public CloseableHttpResponse get(String uri, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(uri);

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);

		return closeableHttpResponse;
	}

	// POST method
	public CloseableHttpResponse post(String uri, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(uri); // http post request

		httppost.setEntity(new StringEntity(entityString)); // for payload

		// for header
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}

		// execute post method
		CloseableHttpResponse response = httpClient.execute(httppost);

		return response;

	}
}
