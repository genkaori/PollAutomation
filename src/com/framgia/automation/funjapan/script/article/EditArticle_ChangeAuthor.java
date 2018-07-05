package com.framgia.automation.funjapan.script.article;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.xpath.functions.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.XLSHelper;

public class EditArticle_ChangeAuthor extends CommonTestCase {

	@Test(priority=1, dataProvider="SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}
	
	@DataProvider
	public Object[][] SetAuthor() {
		Object[][] data = XLSHelper_Author.retrieveCellsMulti(2, 2);
		return data;
	}
	
	@Test(priority=2, dataProvider="SetAuthor")
	public void ChangeAuthor(String author) {

		driver.get("http://fun-auto-test.framgia.vn/admin/articles/813");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement link_Article_Modify = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[3]/div/div/div/div/table/tbody/tr[1]/td[4]/a"));
		link_Article_Modify.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Select select = new Select(driver.findElement(By.name("author_id")));
		select.selectByVisibleText(author);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement button_Article_Draft=driver.findElement(By.xpath("//button[@class='btn btn-primary btn-w-m create-action save-draft text-uppercase'"));
		 		JavascriptExecutor js = (JavascriptExecutor) driver;
		 		js.executeScript("arguments[0].click();", button_Article_Draft);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		WebElement Check_Article_Modify = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[3]/div/div/div/div/table/tbody/tr[1]/td[4]/a")));
		Check_Article_Modify.click();
		
		
		WebElement check_Author = driver.findElement(By.name("author_id"));
		Assert.assertEquals(check_Author.getText(), author);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

}
