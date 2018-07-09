package com.framgia.automation.funjapan.script.coupon.add;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.XLSHelper;

public class CreateCoupon extends CommonTestCase {
	
	@Test(priority=1, dataProvider="SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
		driver.get("http://fun-auto-test.framgia.vn/admin/coupons/create");
	}
	
	//TC#42: No input click Draft
	//@Test(priority=2)
	public void NoInputClickDraft () {		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		WebElement error1 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(1)"));
		assertEquals(error1.getText(),"The Country field is required");
		
		WebElement error2 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(2)"));
		assertEquals(error2.getText(),"The Published Time field is required.");
		
		WebElement error3 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(3)"));
		assertEquals(error3.getText(),"The End Published Time must be a date after Published Date.");
		
		WebElement error31 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(4)"));
		assertEquals(error31.getText(),"The Article Title field is required.");
		
		WebElement error4 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(5)"));
		assertEquals(error4.getText(),"The Article Content field is required.");
		
		WebElement error5 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(6)"));
		assertEquals(error5.getText(),"The Tags field is required.");
		
		WebElement error6 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(7)"));
		assertEquals(error6.getText(),"The Meta Description field is required.");
		
		WebElement error7 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(8)"));
		assertEquals(error7.getText(),"The Max Coupon field is required.");
		
		WebElement error8 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(9)"));
		assertEquals(error8.getText(),"The Tracking Method field is required.");
		
		WebElement error9 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(10)"));
		assertEquals(error9.getText(),"The Start Date Of Coupon field is required.");
		
		WebElement error10 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(11)"));
		assertEquals(error10.getText(),"The End Date Of Coupon field is required.");
		
		WebElement error11 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(12)"));
		assertEquals(error11.getText(),"The Coupon Image field is required.");
		
		WebElement error12 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(13)"));
		assertEquals(error12.getText(),"The Coupon Description field is required.");
		
		WebElement error13 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(14)"));
		assertEquals(error13.getText(),"The About Coupon (How to use) field is required.");		
		
	}
	//TC#43: No input click Preview
	@Test(priority=3)
	public void NoInputClickPreview () {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement btnPreview = driver.findElement(By.xpath("//form/div[5]/span/button"));		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnPreview);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		WebElement error1 = driver.findElement(By.cssSelector("div.wrapper div.alert-danger p:nth-child(1)"));
		assertEquals(error1.getText(),"The Country field is required");
	}

}