package com.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {

	public static String getValueByKey(String responseString, String findByKey) {
		
		Object obj = new JSONObject(responseString);
		
		//Object obj = responseJson;
		
		for (String s : findByKey.split("/")) {
			if (!s.isEmpty())
				if (!(s.contains("[") || s.contains("]"))) {
					obj = ((JSONObject) obj).get(s);
				} else if (s.contains("[") || s.contains("]")) {
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
				}
		}
		return obj.toString();

	}
	
	public static void convertToHashMap() {
		
		
	}
}