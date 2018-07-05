package com.framgia.automation.funjapan.util;

import java.util.Properties;

public class Setting {
	private static Properties setting = FileUtil.loadProperties("data/setting.properties");

	public static final String WEBDRIVER = "webdriver";
	public static final String WEBDRIVER_PATH = "webdriver-path";
	public static final String URL_ADMIN = "url-admin";
	public static final String DATA_FILE = "data-file";
	
	public static String getSetting(String key) {
		return setting.getProperty(key);
	}
}
