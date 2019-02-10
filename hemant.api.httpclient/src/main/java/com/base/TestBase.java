package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public Properties prop;
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_500 = 500;

	public TestBase() {

		try {

			prop = new Properties();

			FileInputStream fs;
			
			fs = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/config/config.properties");
			
			prop.load(fs);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}


}
