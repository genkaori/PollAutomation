package com.framgia.automation.funjapan.script.research.AnhLTN_Research;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.Setting;
import com.framgia.automation.funjapan.util.XLSHelper;

public class ResearchList extends CommonTestCase {
	private String url = "http://fun-auto-test.framgia.vn/admin/researchArticles";

	@Test(priority = 0, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}

	// TC 80: Research list > Sort > Sort default > "Published Date" field
	// @Test(priority = 1)
	public void testSortDefault() throws ParseException {
		driver.get(url);
		List<Date> publishedArray = new ArrayList<Date>();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		while (wait.until(
				ExpectedConditions.attributeContains(By.xpath("//ul/li[contains(@class,'next')]"), "class", "next"))) {
			List<WebElement> articleTable = driver.findElements(By.xpath("//table/tbody/*/td[5]"));
			System.out.println(articleTable.size());
			for (WebElement td : articleTable) {
				String txtPublished = td.getText();
				publishedArray.add(convertStringToDate("yyyy/MM/dd hh:mm a", txtPublished));
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[contains(@class,'next')]")))
					.getAttribute("class").contains("disabled")) {
				break;
			}
			WebElement linkNext = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[contains(@class,'next')]/a")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", linkNext);
		}
		Assert.assertTrue(isSorted(publishedArray));
	}

	// TC 82: Research list > Search > Check UI of search field
	@DataProvider
	public Object[][] GetDataSearchCheckUI() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 1, 6, 6, 4);
		return data;
	}

	@Test(priority = 2, dataProvider = "GetDataSearchCheckUI")
	public void testSearchCheckUI(String client_id, String article_id, String article_title, String palceholder) {
		driver.get(url);
		// Default criterial: Article ID
		String defaultCriterial = driver.findElement(By.className("selected-search-by")).getText();
		Assert.assertEquals(defaultCriterial, article_id);
		// Show popup
		driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
		// criterias: Client ID, Article ID, Article Title
		String txtClient = driver
				.findElement(By.xpath("//ul[contains(@class,'search-by')]/li/a[@data-column = 'client_id']")).getText();
		Assert.assertEquals(txtClient, client_id);
		String txtArticle = driver
				.findElement(By.xpath("//ul[contains(@class,'search-by')]/li/a[@data-column = 'article_id']"))
				.getText();
		Assert.assertEquals(txtArticle, article_id);
		String txtArticleTitle = driver
				.findElement(By.xpath("//ul[contains(@class,'search-by')]/li/a[@data-column = 'title']")).getText();
		Assert.assertEquals(txtArticleTitle, article_title);
		// 2. Textbox - place holder: Search
		Assert.assertEquals(driver.findElement(By.name("keyword")).getAttribute("placeholder"), palceholder);
	}

	// TC 84: Research list > Search > Search article ID: not existed article ID
	@DataProvider
	public Object[][] GetDataNotExistedArticleID() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 1, 9, 9, 2);
		return data;
	}

	@Test(priority = 3, dataProvider = "GetDataNotExistedArticleID")
	public void testNotExistedArticleID(String article_id, String error_message) {
		driver.get(url);
		checkErrorMessageNoRecord(article_id, error_message);
	}

	// TC 88: Research list > Search > Search article ID: maxlength
	@DataProvider
	public Object[][] GetDataMaxlengthArticleID() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 1, 12, 12, 2);
		return data;
	}

	@Test(priority = 4, dataProvider = "GetDataMaxlengthArticleID")
	public void testMaxlengthArticleID(String article_id, String error_message) {
		driver.get(url);
		checkErrorMessageNoRecord(article_id, error_message);
	}

	// TC 90: Research list > Search > SearchClient ID: not existed article ID
	@DataProvider
	public Object[][] GetDataNotExistedClientID() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 1, 15, 15, 2);
		return data;
	}

	@Test(priority = 5, dataProvider = "GetDataNotExistedClientID")
	public void testNotExistedClientID(String client_id, String error_message) {
		driver.get(url);
		driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
		driver.findElement(By.xpath("//ul[contains(@class,'search-by')]/li/a[@data-column = 'client_id']")).click();
		checkErrorMessageNoRecord(client_id, error_message);
	}

	// TC 94: Research list > Search > Search Client ID: maxlength
	@DataProvider
	public Object[][] GetDataMaxlengthClientID() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 1, 18, 18, 2);
		return data;
	}

	@Test(priority = 6, dataProvider = "GetDataMaxlengthClientID")
	public void testMaxlengthClientID(String client_id, String error_message) {
		driver.get(url);
		driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
		driver.findElement(By.xpath("//ul[contains(@class,'search-by')]/li/a[@data-column = 'client_id']")).click();
		checkErrorMessageNoRecord(client_id, error_message);
	}

	// TC 96: Research list > Search > Search Title: not existed article title
	@DataProvider
	public Object[][] GetDataNotExistedArticleTitle() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 1, 21, 21, 2);
		return data;
	}

	@Test(priority = 7, dataProvider = "GetDataNotExistedArticleTitle")
	public void testNotExistedArticleTitle(String article_title, String error_message) {
		driver.get(url);
		driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
		driver.findElement(By.xpath("//ul[contains(@class,'search-by')]/li/a[@data-column = 'title']")).click();
		checkErrorMessageNoRecord(article_title, error_message);
	}

	// TC 100: Research list > Search > Search Title: Lower letters, upper letters
	@DataProvider
	public Object[][] GetDataSearchByArticleTitle() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 1, 24, 25, 1);
		return data;
	}

	@Test(priority = 8, dataProvider = "GetDataSearchByArticleTitle")
	public void testSearchByArticleTitle(String article_title) {
		driver.get(url);
		driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
		driver.findElement(By.xpath("//ul[contains(@class,'search-by')]/li/a[@data-column = 'title']")).click();
		driver.findElement(By.xpath("//form//input[@name='keyword']")).sendKeys(article_title);
		driver.findElement(By.xpath("//form//span/button[@type='submit']")).submit();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		Assert.assertEquals(driver.findElement(By.xpath("//form//input[@name='keyword']")).getAttribute("value"),
				article_title);
		List<WebElement> table = driver
				.findElements(By.xpath("//table[contains(@class, 'article-table')]/tbody/tr/td[4]"));
		for (WebElement element : table) {
			Assert.assertTrue(lowercaseText(element.getText()).contains(lowercaseText(article_title)));
		}
	}

	// Convert String to dd/mm/yyyy HH:MM am/pm format
	public Date convertStringToDate(String dateFormat, String txt) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm a", Locale.US);
		Date date = format.parse(txt);
		return date;
	}

	public void checkErrorMessageNoRecord(String txt, String error) {
		driver.findElement(By.xpath("//form//input[@name='keyword']")).sendKeys(txt);
		driver.findElement(By.xpath("//form//span/button[@type='submit']")).submit();
		Assert.assertEquals(
				driver.findElement(By.xpath("//table[contains(@class,'article-table')]//td/span")).getText(), error);
	}

	// An array is sorted by DESC
	public boolean isSorted(List<Date> date) {
		if (date.size() == 1) {
			return true;
		}
		for (int i = 0; i < date.size() - 1; i++) {
			System.out.println(date.get(i));
			if (date.get(i).before(date.get(i + 1))) {
				return false;
			}
		}
		return true;
	}

	// lower text
	public static String lowercaseText(String str) {
		if (str == null)
			return "";
		else
			return str.toLowerCase();
	}
}
