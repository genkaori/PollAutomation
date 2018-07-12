package com.framgia.automation.funjapan.script.research.newresearch_ThuyLT;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.XLSHelper;

public class CheckAuthorField extends CommonTestCase {
	@Test(priority=1, dataProvider="SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}
	
	//Case no select author
	@DataProvider
	public Object[][] SetAuthorBlank() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_ThuyLT.xls",3, 3);
		return data;
	}
	
	@Test(priority=2, dataProvider="SetAuthorBlank")
	public void checkNotSelectAuthorField(String author_id, String country, String client_id, String category, String publish_date,
			String publish_time, String article_title, String article_content, String tags, String meta_description) {
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
		
		WebElement btnDraft = driver.findElement(By.xpath("//form/div[5]/div[2]/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement textCheck = driver.findElement(By.cssSelector("div[class='alert alert-danger'] p"));
		Assert.assertTrue(textCheck.getText().equals("The Author field is required."));
	}
	
	//Case Change Author
	@DataProvider
	public Object[][] ChangeAuthor() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_ThuyLT_2.xls",2, 2);
		return data;
	}
	
	@Test(priority=3, dataProvider="ChangeAuthor")
	public void checkNotSelectAuthorField(String author_id_1, String author_id_2, String country, String client_id, String category, String publish_date,
			String publish_time, String article_title, String article_content, String tags, String meta_description) {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/create");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Select select_author_1 = new Select(driver.findElement(By.cssSelector("#author_id")));
		select_author_1.selectByValue(author_id_1);
	
		Select select_author_2 = new Select(driver.findElement(By.cssSelector("#author_id")));
		select_author_2.selectByValue(author_id_2);
		
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
		
		WebElement pactiveElement = driver.findElement(By.xpath("//p[@class='medium-insert-active']"));
		
		WebElement txtArticleHidend = driver.findElement(By.name("contentMedium"));
		
		((JavascriptExecutor) driver).executeScript("var ele=arguments[0]; ele.innerHTML = '"
					+ article_content+"';", pactiveElement);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].value='"+ article_content+"';", txtArticleHidend);
		
		WebElement txtTags = driver.findElement
				(By.xpath("//*[@id='article-setting']/div/div/div/div/div/form/div[5]/div[1]/div[4]/div/input"));
		txtTags.sendKeys(tags);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement clickSearch = driver
				.findElement(By.xpath("//ul[contains(@class,'typeahead dropdown-menu')]//li[3]"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickSearch.click();
		
		WebElement txtDescription = driver.findElement(By.cssSelector("#description"));
		txtDescription.sendKeys(meta_description);
		
		WebElement btnDraft = driver.findElement(By.xpath("//form/div[5]/div[2]/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement textCheck = driver.findElement(By.cssSelector("#alert-message p"));
		Assert.assertTrue(textCheck.getText().equals("Created Successfully"));
	}
}
