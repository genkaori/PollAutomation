package com.framgia.automation.funjapan.script.research.ThaoDTP_QnA;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.XLSHelper_1;

public class search extends CommonTestCase {
	@Test(priority = 1, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}

	@DataProvider
	public Object[][] PartOfQuestion() {
		Object[][] data = XLSHelper_1.retrieveCellsMulti(1, 2, 2);
		return data;
	}

	@Test(priority = 2, dataProvider = "PartOfQuestion")
	public void testSearchPartOfQuestionInJapanese(String question) {

		driver.get("http://fun-auto-test.framgia.vn/admin/qa");

		WebElement divContent = driver.findElement(By.cssSelector(
				"#page-wrapper > div.wrapper.wrapper-content > div.wrapper.wrapper-content.animated.fadeInRight > div > div > div > div.ibox-content > div > div"));
		
		WebElement txtKeyword = divContent.findElement(By.cssSelector("form > div > input"));
		txtKeyword.sendKeys(question);

		WebElement btnSearch = divContent.findElement(By.cssSelector("form > div > span > button"));
		btnSearch.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> questionList = driver.findElements(By.cssSelector(
				"#page-wrapper > div.wrapper.wrapper-content > div.wrapper.wrapper-content.animated.fadeInRight > div > div > div > div.ibox-content > div > div > table > tbody > tr"));
		
		for (WebElement e : questionList) {
			String str = e.findElement(By.cssSelector("td.break-word")).getText();
			Assert.assertTrue(str.contains(question));
		}

	}

	@DataProvider
	public Object[][] FullQuestion() {
		Object[][] data = XLSHelper_1.retrieveCellsMulti(1, 3, 3);
		return data;
	}

	@Test(priority = 3, dataProvider = "FullQuestion")
	public void testSearchFullQuestionInJapanese(String question) {

		driver.get("http://fun-auto-test.framgia.vn/admin/qa");

		WebElement divContent = driver.findElement(By.cssSelector(
				"#page-wrapper > div.wrapper.wrapper-content > div.wrapper.wrapper-content.animated.fadeInRight > div > div > div > div.ibox-content > div > div"));
		
		WebElement txtKeyword = divContent.findElement(By.cssSelector("form > div > input"));
		txtKeyword.sendKeys(question);

		WebElement btnSearch = divContent.findElement(By.cssSelector("form > div > span > button"));
		btnSearch.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<WebElement> questionList = driver.findElements(By.cssSelector(
				"#page-wrapper > div.wrapper.wrapper-content > div.wrapper.wrapper-content.animated.fadeInRight > div > div > div > div.ibox-content > div > div > table > tbody > tr"));
		
		for (WebElement e : questionList) {
			String str = e.findElement(By.cssSelector("td.break-word")).getText();
			System.out.println(str);
			Assert.assertEquals(str, question);;
		}

	}
}
