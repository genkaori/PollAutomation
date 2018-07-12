package com.framgia.automation.funjapan.script.article.list;

import org.testng.annotations.DataProvider;

import com.framgia.automation.funjapan.util.Setting;
import com.framgia.automation.funjapan.util.XLSHelper;

public class DataProviderTest {

	@DataProvider
	public Object[][] dataSearchClientID() {
		Object[][] data = XLSHelper.retrieveCellsMulti(Setting.getSetting(Setting.DATA_ARTICLE_LIST), 2, 2);
		return data;
	}

	@DataProvider
	public Object[][] dataSearchClientIdNoResult() {
		Object[][] data = XLSHelper.retrieveCellsMulti(Setting.getSetting(Setting.DATA_ARTICLE_LIST), 3, 3);
		return data;
	}
	
	@DataProvider
	public Object[][] dataSearchClientIdMaxLength() {
		Object[][] data = XLSHelper.retrieveCellsMulti(Setting.getSetting(Setting.DATA_ARTICLE_LIST), 4, 4);
		return data;
	}

	@DataProvider
	public Object[][] dataSearchArticleID() {
		Object[][] data = XLSHelper.retrieveCellsMulti(Setting.getSetting(Setting.DATA_ARTICLE_LIST), 5, 5);
		return data;
	}
	
}
