package com.framgia.automation.funjapan;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.framgia.automation.funjapan.XLSHelper;

public class Login {
	private WebDriver driver = null;
	
	@BeforeMethod
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "/home/pham.thi.thu.hang/Documents/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://fun-auto-test.framgia.vn/admin/login");
	}
	@AfterMethod
	public void end() {
		driver.quit();
	}
	
	@DataProvider
	public Object[][] SetLogin() {

		Object[][] data = XLSHelper.retrieveCellsMulti(2, 2);

		return data;
	}

	@org.testng.annotations.Test(priority=1, dataProvider="SetLogin")
	public void testLogin(String email, String pass) {

		WebElement btnLogin = driver.findElement(By.cssSelector("a[href='http://fun-auto-test.framgia.vn/admin/account/facebook']"));
		btnLogin.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement txtEmail = driver.findElement(By.cssSelector("input[name='email']"));
		txtEmail.sendKeys(email);
		
		WebElement txtPass = driver.findElement(By.cssSelector("input[name='pass']"));
		txtPass.sendKeys(pass);
		
		WebElement buttonLogin = driver.findElement(By.cssSelector("button[name='login']"));
		buttonLogin.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		String pageTitle = (String) javascript.executeScript("return document.title");
		Assert.assertEquals(pageTitle, "Fun! Japan Renewal Admin");
	}

}
