package com.framgia.automation.funjapan.script.coupon.ThuyB_CounponList;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;

import testng.XLSHelper;


public class Coupon_Search_ArticleID extends CommonTestCase {
	@Test(priority=0, dataProvider="SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
		
	}
	
	@DataProvider
	public static Object[][] testNameBlank() {	
		Object[][] data = XLSHelper.retrieveCellsMulti(2, 2);
		return data;
	}
	
	@Test(priority=1)
	public void Search_ArticleID () {
		driver.get("http://fun-auto-test.framgia.vn/admin/coupons");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.findElement(By.xpath("//div[@class='input-group-btn']")).click();
		WebElement listArticleIDSearch = driver.findElement(By.xpath("//ul[@class='dropdown-menu search-by']"));
		List<WebElement> options = listArticleIDSearch.findElements(By.tagName("li"));
		options.get(0).click();
		
		WebElement txtInput = driver.findElement(By.cssSelector("input[class='form-control']"));
		txtInput.sendKeys("1");
		
		WebElement btnSearch = driver.findElement(By.cssSelector("button[class='btn btn-primary bg-custom']"));
		btnSearch.click();
	}

}
