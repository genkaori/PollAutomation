package com.framgia.automation.funjapan.script.research.newresearch_ThuyLT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.XLSHelper;

public class CheckQASettingButton extends CommonTestCase {
	@Test(priority=1, dataProvider="SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}
	
	//Case blank
	@Test(priority=2)
	public void checkBtnQASetting() {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/create");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement btnQA = driver.findElement(By.className("check-preview"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnQA);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		List<WebElement> listResult = driver.findElements(By.cssSelector(
				"div[class='wrapper wrapper-content alert-error-section'] div[class='alert alert-danger'] p"));
		List<String> error_mess = new ArrayList<String>();

		for (WebElement e : listResult) {
			error_mess.add(e.getText());
		}

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElements(listResult));

		Assert.assertTrue(error_mess.contains("The Author field is required."));
		Assert.assertTrue(error_mess.contains("The Country field is required"));
		Assert.assertTrue(error_mess.contains("The Category field is required."));
		Assert.assertTrue(error_mess.contains("The Published Time field is required."));
		Assert.assertTrue(error_mess.contains("The End Published Time must be a date after Published Date."));
		Assert.assertTrue(error_mess.contains("The Article Title field is required."));
		Assert.assertTrue(error_mess.contains("The Article Content field is required."));
		Assert.assertTrue(error_mess.contains("The Country field is required"));
		Assert.assertTrue(error_mess.contains("The Meta Description field is required."));
	}
	
	
	//Case 2: Case valid
	@DataProvider
	public Object[][] SetDataCreate() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_ThuyLT.xls",2, 2);
		return data;
	}
	
	@Test(priority=3, dataProvider="SetDataCreate")
	public void checkBtnDraftValid(String author_id, String country, String client_id, String category, String publish_date, String publish_time, String article_title, String article_content, String tags, String meta_description) {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/create");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Select select_author = new Select(driver.findElement(By.cssSelector("#author_id")));
		select_author.selectByValue(author_id);
		
		Select select_country = new Select(driver.findElement(By.cssSelector("#locale_id")));
		select_country.selectByValue(country);
		
		Select select_client_id = new Select(driver.findElement(By.cssSelector("#client_id")));
		select_client_id.selectByValue(client_id);
		
		Select select_category = new Select(driver.findElement(By.cssSelector("#category_id")));
		select_category.selectByValue(category);
		
		WebElement txtPublishDate = driver.findElement(By.name("publish_date"));
		txtPublishDate.sendKeys(publish_date);
		
		WebElement txtPublishTime = driver.findElement(By.name("publish_time"));
		txtPublishTime.sendKeys(publish_time);
		
		WebElement txtArticleTitle = driver.findElement(By.cssSelector("#title"));
		txtArticleTitle.sendKeys(article_title);
		
		WebElement txtArticleContent = driver.findElement
				(By.xpath("//*[@id='article-setting']//form/div[5]/div[1]/div[3]/div"));
		
		Actions action = new Actions(driver);
		action.moveToElement(txtArticleContent).click().build().perform();
		
		WebElement pactiveElement = driver.findElement
				(By.xpath("//p[@class='medium-insert-active']"));
		
		WebElement txtArticleHidend = driver.findElement
				(By.name("contentMedium"));
		
		((JavascriptExecutor) driver)
		.executeScript("var ele=arguments[0]; ele.innerHTML = '"+article_content+"';", pactiveElement);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].value='"+article_content+"';", txtArticleHidend);
		
		WebElement txtTags = driver.findElement
				(By.xpath("//*[@id='article-setting']/div/div/div/div/div/form/div[5]/div[1]/div[4]/div/input"));
		txtTags.sendKeys(tags);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement clickSearch = driver
				.findElement(By.xpath("//ul[contains(@class,'typeahead dropdown-menu')]//li[3]"));
		System.out.println(clickSearch.getText());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickSearch.click();
		
		WebElement txtDescription = driver.findElement(By.cssSelector("#description"));
		txtDescription.sendKeys(meta_description);
		
		WebElement btnQA = driver.findElement(By.className("check-preview"));		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnQA);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl()
				.equals("http://fun-auto-test.framgia.vn/admin/researchArticles/preview"));
		
	}
	
}
