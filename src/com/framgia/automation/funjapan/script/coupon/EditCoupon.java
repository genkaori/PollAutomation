package com.framgia.automation.funjapan.script.coupon;

import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.Setting;
import com.framgia.automation.funjapan.util.XLSHelper;

public class EditCoupon extends CommonTestCase {
	@Test(priority = 1, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}


	
	// Test case change clientID

	
	@DataProvider
	public Object[][] SetDataEdit_TC145() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 9, 9);
		return data;
	}

	@Test(priority = 2, dataProvider = "SetDataEdit_TC145")

	public void editCoupon_TC145(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {
	


		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Select select = (Select)driver.findElement(By.cssSelector("#client_id"));
		select.selectByValue("2");

		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Verify
		WebElement message_success = driver.findElement(By.cssSelector("p"));

		Assert.assertEquals(message_success.getText(), "Updated Successfully");

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement select1 = driver.findElement(By.cssSelector("#client_id"));

		Assert.assertEquals(id_client, select1.getAttribute("value"));
	}
	
	// TC check radioMemberOnly radioHide radioFlag
	@DataProvider
	public Object[][] SetDataEdit_TC151_TC152_TC154() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 10, 10);
		return data;
	}

	@Test(priority = 3, dataProvider = "SetDataEdit_TC151_TC152_TC154")

	public void editCoupon_TC151_TC152_TC154(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {
	


		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement radioMemberOnly = driver.findElement(By.cssSelector("form input[name='is_member_only'][value='1']"));
		radioMemberOnly.click();

		WebElement radioHide = driver.findElement(By.cssSelector("form input[name='hide'][value='0']"));
		radioHide.click();

		WebElement radioFlag = driver.findElement(By.cssSelector("form input[name='social_flag'][value='0']"));
		radioFlag.click();
		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

		// Verify
		WebElement message_success = driver.findElement(By.cssSelector("p"));

		Assert.assertEquals(message_success.getText(), "Updated Successfully");

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement radioMemberOnly1 = driver
				.findElement(By.cssSelector("form input[name='is_member_only'][value='1']"));
Assert.assertTrue( radioMemberOnly1.isSelected());
		

		WebElement radioHide1 = driver.findElement(By.cssSelector("form input[name='hide'][value='0']"));
		
		Assert.assertTrue(  radioHide1.isSelected());
		WebElement radioFlag1 = driver.findElement(By.cssSelector("form input[name='social_flag'][value='0']"));
		
		Assert.assertTrue( radioFlag1.isSelected());
	}



	// TC input valid data into Title field
	@DataProvider
	public Object[][] SetDataEdit_TC155() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 2, 2);
		return data;
	}

	@Test(priority = 4, dataProvider = "SetDataEdit_TC155")

	public void editCoupon_TC155(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement edit_title = driver.findElement(By.cssSelector("#title"));
		edit_title.clear();
		edit_title.sendKeys(title);

		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// verify
		WebElement message_success = driver.findElement(By.cssSelector("p"));

		Assert.assertEquals(message_success.getText(), "Updated Successfully");

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement verify_title = driver.findElement(By.cssSelector("#title"));
		verify_title.getAttribute("value");
		Assert.assertEquals(title, verify_title.getAttribute("value"));
	}

	// TC input valid data into content field
	@DataProvider
	public Object[][] SetDataEdit_TC156() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 3, 3);
		return data;
	}

	@Test(priority = 5, dataProvider = "SetDataEdit_TC156")

	public void editCoupon_TC156(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement Articlecontent = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div > div:nth-of-type(3) > div"));

		Articlecontent.clear();

		Articlecontent.sendKeys(content);

		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// verify
		WebElement message_success = driver.findElement(By.cssSelector("p"));

		Assert.assertEquals(message_success.getText(), "Updated Successfully");

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement Articlecontent1 = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div > div:nth-of-type(3) > div"));

		Assert.assertEquals(content, Articlecontent1.getText());

	}

	// TC input valid data into tag field
	@DataProvider
	public Object[][] SetDataEdit_TC157() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 4, 4);
		return data;
	}

	@Test(priority = 6, dataProvider = "SetDataEdit_TC157")

	public void editCoupon_TC157(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement tag = driver.findElement(By.cssSelector("div.bootstrap-tagsinput > input[type=\"text\"]"));

		tag.sendKeys(Keys.BACK_SPACE);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		tag.sendKeys(tags);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement dropdown = driver.findElement(By.cssSelector("a.dropdown-item"));
		dropdown.click();

		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// verify
		WebElement message_success = driver.findElement(By.cssSelector("p"));

		Assert.assertEquals(message_success.getText(), "Updated Successfully");

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		WebElement tag1 = driver.findElement(
				By.cssSelector("form#form-coupon > div:nth-of-type(4) > div > div:nth-of-type(4) > div > span"));

		Assert.assertEquals(tag1.getText(), tags);

	}

	// TC input valid data into meta_description field
	@DataProvider
	public Object[][] SetDataEdit_TC159() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 5, 5);
		return data;
	}

	@Test(priority = 7, dataProvider = "SetDataEdit_TC159")

	public void editCoupon_TC159(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement metades = driver.findElement(By.cssSelector("textarea#description"));
		metades.clear();
		metades.sendKeys(meta_description);

		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// verify
		WebElement message_success = driver.findElement(By.cssSelector("p"));

		Assert.assertEquals(message_success.getText(), "Updated Successfully");

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		WebElement metades1 = driver.findElement(By.cssSelector("textarea#description"));

		Assert.assertEquals(metades1.getText(), meta_description);

	}

	// TC input valid data into Coupon Description field
	@DataProvider
	public Object[][] SetDataEdit_TC165() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 6, 6);
		return data;
	}

	@Test(priority = 8, dataProvider = "SetDataEdit_TC165")

	public void editCoupon_TC165(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement couponDes = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(9) > div > div"));
		couponDes.clear();
		couponDes.sendKeys(coupon_description);

		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// verify
		WebElement message_success = driver.findElement(By.cssSelector("p"));

		Assert.assertEquals(message_success.getText(), "Updated Successfully");

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		WebElement couponDes1 = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(9) > div > div"));

		Assert.assertEquals(couponDes1.getText(), coupon_description);

	}

	// TC input valid data into about Coupon field
	@DataProvider
	public Object[][] SetDataEdit_TC166() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 7, 7);
		return data;
	}

	@Test(priority = 9, dataProvider = "SetDataEdit_TC166")

	public void editCoupon_TC166(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement edit_about = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(10) > div > div"));
		edit_about.clear();
		edit_about.sendKeys(about);

		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// verify
		WebElement message_success = driver.findElement(By.cssSelector("p"));

		Assert.assertEquals(message_success.getText(), "Updated Successfully");

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		WebElement edit_about1 = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(10) > div > div"));

		Assert.assertEquals(edit_about1.getText(), about);

	}

	// Test case check field that is required
	@Test(priority = 10)

	public void editCoupon_TC167() {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement editpublish_date = driver.findElement(By.cssSelector("#publish_date"));
		editpublish_date.clear();

		WebElement edit_publish_time = driver.findElement(
				By.cssSelector("form input[name='publish_time']"));
		edit_publish_time.clear();

		WebElement endpublish_date = driver.findElement(By.cssSelector("input#end_publish_date"));
		endpublish_date.clear();

		WebElement edit_end_publish_time = driver.findElement(By.cssSelector("input#end_publish_time"));
		edit_end_publish_time.clear();

		WebElement Articletitle = driver.findElement(By.cssSelector("input#title"));
		Articletitle.clear();
		WebElement Articlecontent = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div > div:nth-of-type(3) > div"));

		Actions ref = new Actions(driver);
		Actions navigator = new Actions(driver);
		navigator.click(Articlecontent).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT)
				.sendKeys(Keys.BACK_SPACE).perform();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement metades = driver.findElement(By.cssSelector("textarea#description"));
		metades.clear();
		metades.click();

		WebElement edit_start_coupon_period = driver.findElement(By.cssSelector("input#start_coupon_period"));
		edit_start_coupon_period.clear();

		WebElement edit_start_time = driver.findElement(By.cssSelector("input#start_time"));
		edit_start_time.clear();

		WebElement edit_end_coupon_period = driver.findElement(By.cssSelector("input#end_coupon_period"));
		edit_end_coupon_period.clear();

		WebElement edit_end_time = driver.findElement(By.cssSelector("input#end_time"));
		edit_end_time.clear();

		WebElement maxcoupon = driver.findElement(By.cssSelector("#max_coupon"));
		maxcoupon.clear();

		WebElement trackingMethod = driver.findElement(By.cssSelector("#tracking_method > option[value=\"\"]"));
		trackingMethod.click();

		WebElement couponDes = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(9) > div > div"));
		Actions ref1 = new Actions(driver);
		Actions navigator1 = new Actions(driver);
		navigator.click(couponDes).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT)
				.sendKeys(Keys.BACK_SPACE).perform();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement edit_about = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(10) > div > div"));
		Actions ref2 = new Actions(driver);
		Actions navigator2 = new Actions(driver);
		navigator.click(edit_about).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT)
				.sendKeys(Keys.BACK_SPACE).perform();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// remove tag

		WebElement tag = driver.findElement(By.cssSelector("div.bootstrap-tagsinput > input[type=\"text\"]"));

		tag.sendKeys(Keys.BACK_SPACE);

		WebElement btnDraft = driver.findElement(By.xpath("//form/div[4]/div[11]/div/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Verify

		WebElement message_error9 = driver
				.findElement(By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p"));

		Assert.assertEquals(message_error9.getText(), "The Published Time field is required.");

		WebElement message_error1 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(2)"));

		Assert.assertEquals(message_error1.getText(), "The End Published Time field is required.");

		WebElement message_error2 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(3)"));

		Assert.assertEquals(message_error2.getText(), "The Article Title field is required.");

		WebElement message_error3 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(4)"));

		Assert.assertEquals(message_error3.getText(), "The Article Content field is required.");

		WebElement message_error4 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(5)"));

		Assert.assertEquals(message_error4.getText(), "The Tags field is required.");

		WebElement message_error5 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(6)"));

		Assert.assertEquals(message_error5.getText(), "The Meta Description field is required.");

		WebElement message_error10 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(7)"));

		Assert.assertEquals(message_error10.getText(), "The Max Coupon field is required.");

		WebElement message_error12 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(8)"));

		Assert.assertEquals(message_error12.getText(), "The Tracking Method field is required.");

		WebElement message_error11 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(9)"));

		Assert.assertEquals(message_error11.getText(), "The Start Date Of Coupon field is required.");

		WebElement message_error6 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(10)"));

		Assert.assertEquals(message_error6.getText(), "The End Date Of Coupon field is required.");
		WebElement message_error7 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(11)"));

		Assert.assertEquals(message_error7.getText(), "The Coupon Description field is required.");

		WebElement message_error8 = driver.findElement(
				By.cssSelector("div#page-wrapper > div:nth-of-type(3) > div:nth-of-type(2) > div > p:nth-of-type(12)"));

		Assert.assertEquals(message_error8.getText(), "The About Coupon (How to use) field is required.");

	}

	
	
}




