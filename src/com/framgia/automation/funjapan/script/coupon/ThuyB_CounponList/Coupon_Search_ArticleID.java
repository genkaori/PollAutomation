package com.framgia.automation.funjapan.script.coupon.ThuyB_CounponList;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.XLSHelper;




public class Coupon_Search_ArticleID extends CommonTestCase {
	@Test(priority=0, dataProvider="SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
		
	}
	
	@DataProvider
	public static Object[][] testSearch_ClientID() {	
		Object[][] data = XLSHelper.retrieveCellsMulti("/home/le.thi.thuyb/Documents/FunJapan_Automation/data/user.xls", 3, 3);
		return data;
	}
	
	//@Test(priority=1, dataProvider="testSearch_ClientID")
	public void Search_ArticleID (String ExpSearch, String test) {
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
		
		WebElement txtInput = driver.findElement(By.cssSelector("div[class='input-group col-md-10 '] input[type='text']"));
		txtInput.sendKeys(ExpSearch);
		
		WebElement btnSearch = driver.findElement(By.cssSelector("button[class='btn btn-primary bg-custom']"));
		btnSearch.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> lists = driver.findElements(By.cssSelector(".table.table-bordered.article-table tbody tr"));
		for (WebElement el : lists) {	
			String str = el.findElement(By.cssSelector("td.text-center")).getText();
				Assert.assertTrue(str.contains(ExpSearch));
		}	
		Assert.assertEquals(txtInput.getAttribute("value"), ExpSearch);
	}
	
	@DataProvider
	public static Object[][] testSearch_ClientID_Not_Existed() {	
		Object[][] data = XLSHelper.retrieveCellsMulti("/home/le.thi.thuyb/Documents/FunJapan_Automation/data/user.xls", 4, 4);
		return data;
	}
	
	@Test(priority=2, dataProvider="testSearch_ClientID_Not_Existed")
	public void Search_ClientID (String ExpSearch, String test) {
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
		
		WebElement txtInput = driver.findElement(By.cssSelector("div[class='input-group col-md-10 '] input[type='text']"));
		txtInput.sendKeys(ExpSearch);
		
		WebElement btnSearch = driver.findElement(By.cssSelector("button[class='btn btn-primary bg-custom']"));
		btnSearch.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement result = driver.findElement(By.cssSelector(".table.table-bordered.article-table tbody tr td span"));
				Assert.assertEquals(result.getText(),"No Articles");
	}
}
