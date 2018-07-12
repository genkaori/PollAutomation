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

public class EditCoupon2 extends CommonTestCase {
	@Test(priority = 1, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}


	// TC check button MobilePreview

	@DataProvider
	public Object[][] SetDataEdit_TC170() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 8, 8);
		return data;
	}

	@Test(priority = 11, dataProvider = "SetDataEdit_TC170")

	public void editCoupon_TC170(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement editpublish_date = driver.findElement(By.cssSelector("#publish_date"));
		editpublish_date.clear();
		editpublish_date.sendKeys(publish_date);

		WebElement editpublish_time = driver.findElement(
				By.cssSelector("form#form-coupon > div:nth-of-type(2) > div > div > div:nth-of-type(2) > div > input"));
		editpublish_time.clear();
		editpublish_time.sendKeys(publish_time);

		WebElement endpublish_date = driver.findElement(By.cssSelector("#end_publish_date"));
		endpublish_date.clear();
		endpublish_date.sendKeys(end_publish_date);

		WebElement editend_publish_time = driver.findElement(By.cssSelector("#end_publish_time"));
		editend_publish_time.clear();
		editend_publish_time.sendKeys(end_publish_time);

		WebElement radioMemberOnly = driver.findElement(By.cssSelector("form input[name='is_member_only'][value='1']"));
		radioMemberOnly.click();

		WebElement radioHide = driver.findElement(By.cssSelector("form input[name='hide'][value='0']"));
		radioHide.click();

		WebElement radioFlag = driver.findElement(By.cssSelector("form input[name='social_flag'][value='0']"));
		radioFlag.click();

		WebElement edittitle = driver.findElement(By.cssSelector("#title"));
		edittitle.clear();
		edittitle.sendKeys(title);

		WebElement editcontent = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div > div:nth-of-type(3) > div"));
		editcontent.clear();
		editcontent.sendKeys(content);

		WebElement tag = driver.findElement(By.cssSelector("div.bootstrap-tagsinput > input[type=\"text\"]"));
		tag.sendKeys(Keys.BACK_SPACE);

		tag.sendKeys(tags);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement dropdown = driver.findElement(By.cssSelector("a.dropdown-item"));
		dropdown.click();

		WebElement metades = driver.findElement(By.cssSelector("textarea#description"));
		metades.clear();

		metades.sendKeys(meta_description);

		WebElement edit_start_coupon_period = driver.findElement(By.cssSelector("#start_coupon_period"));
		edit_start_coupon_period.clear();
		edit_start_coupon_period.sendKeys(start_coupon_period);

		WebElement edit_start_time = driver.findElement(By.cssSelector("#start_time"));
		edit_start_time.clear();
		edit_start_time.sendKeys(start_time);

		WebElement edit_end_coupon_period = driver.findElement(By.cssSelector("#end_coupon_period"));
		edit_end_coupon_period.clear();
		edit_end_coupon_period.sendKeys(end_coupon_period);

		WebElement edit_end_time = driver.findElement(By.cssSelector("#end_time"));
		edit_end_time.clear();
		edit_end_time.sendKeys(end_time);

		WebElement couponDes = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(9) > div > div"));
		couponDes.clear();
		couponDes.sendKeys(coupon_description);

		WebElement editabout = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(10) > div > div"));
		editabout.clear();
		editabout.sendKeys(about);

		WebElement btnCheckPreview = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(5) > span > button"));

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click();", btnCheckPreview);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		WebElement btnMobilePreview = driver
				.findElement(By.cssSelector(".btn-preview .preview-mode-btn:nth-of-type(1)"));

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("arguments[0].click();", btnMobilePreview);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Verify
		WebElement tag1 = driver.findElement(By.cssSelector(
				".mobile-preview .label-warning"));

		Assert.assertEquals(tag1.getText(), tags);

		WebElement title1 = driver.findElement(
				By.cssSelector(".mobile-preview h2"));
		Assert.assertEquals(title1.getText(), title);

		WebElement content1 = driver.findElement(By.cssSelector(
				".mobile-preview p"));
		Assert.assertEquals(content1.getText(), content);

	}

	// TC check button PCPreview

	@DataProvider
	public Object[][] SetDataEdit_TC169() {
		Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 8, 8);
		return data;
	}

	@Test(priority = 12, dataProvider = "SetDataEdit_TC169")

	public void editCoupon_TC169(String publish_date, String publish_time, String end_publish_date,
			String end_publish_time, String title, String content, String tags, String meta_description,
			String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
			String coupon_description, String about, String url, String id_client) {

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement editpublish_date = driver.findElement(By.cssSelector("#publish_date"));
		editpublish_date.clear();
		editpublish_date.sendKeys(publish_date);

		WebElement editpublish_time = driver.findElement(
				By.cssSelector("form#form-coupon > div:nth-of-type(2) > div > div > div:nth-of-type(2) > div > input"));
		editpublish_time.clear();
		editpublish_time.sendKeys(publish_time);

		WebElement endpublish_date = driver.findElement(By.cssSelector("#end_publish_date"));
		endpublish_date.clear();
		endpublish_date.sendKeys(end_publish_date);

		WebElement editend_publish_time = driver.findElement(By.cssSelector("#end_publish_time"));
		editend_publish_time.clear();
		editend_publish_time.sendKeys(end_publish_time);

		WebElement radioMemberOnly = driver.findElement(By.cssSelector("form input[name='is_member_only'][value='1']"));
		radioMemberOnly.click();

		WebElement radioHide = driver.findElement(By.cssSelector("form input[name='hide'][value='0']"));
		radioHide.click();

		WebElement radioFlag = driver.findElement(By.cssSelector("form input[name='social_flag'][value='0']"));
		radioFlag.click();

		WebElement edittitle = driver.findElement(By.cssSelector("#title"));
		edittitle.clear();
		edittitle.sendKeys(title);

		WebElement editcontent = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div > div:nth-of-type(3) > div"));
		editcontent.clear();
		editcontent.sendKeys(content);

		WebElement tag = driver.findElement(By.cssSelector("div.bootstrap-tagsinput > input[type=\"text\"]"));
		tag.sendKeys(Keys.BACK_SPACE);

		tag.sendKeys(tags);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement dropdown = driver.findElement(By.cssSelector("a.dropdown-item"));
		dropdown.click();

		WebElement metades = driver.findElement(By.cssSelector("textarea#description"));
		metades.clear();

		metades.sendKeys(meta_description);

		WebElement edit_start_coupon_period = driver.findElement(By.cssSelector("#start_coupon_period"));
		edit_start_coupon_period.clear();
		edit_start_coupon_period.sendKeys(start_coupon_period);

		WebElement edit_start_time = driver.findElement(By.cssSelector("#start_time"));
		edit_start_time.clear();
		edit_start_time.sendKeys(start_time);

		WebElement edit_end_coupon_period = driver.findElement(By.cssSelector("#end_coupon_period"));
		edit_end_coupon_period.clear();
		edit_end_coupon_period.sendKeys(end_coupon_period);

		WebElement edit_end_time = driver.findElement(By.cssSelector("#end_time"));
		edit_end_time.clear();
		edit_end_time.sendKeys(end_time);

		WebElement couponDes = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(9) > div > div"));
		couponDes.clear();
		couponDes.sendKeys(coupon_description);

		WebElement editabout = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(10) > div > div"));
		editabout.clear();
		editabout.sendKeys(about);

		WebElement btnCheckPreview = driver
				.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(5) > span > button"));

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click();", btnCheckPreview);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		WebElement btnPCPreview = driver.findElement(By.cssSelector(".btn-preview .preview-mode-btn:nth-of-type(2)"));

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("arguments[0].click();", btnPCPreview);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Verify
		WebElement tag1 = driver.findElement(By.cssSelector(
				"#pc-preview .label-warning"));

		Assert.assertEquals(tag1.getText(), tags);

		WebElement title1 = driver.findElement(By.cssSelector(
				"#pc-preview h2"));
		Assert.assertEquals(title1.getText(), title);

		WebElement content1 = driver.findElement(By.cssSelector(
				"#pc-preview p"));
		Assert.assertEquals(content1.getText(), content);

	}
	
	

	// TC check button Next

		@DataProvider
		public Object[][] SetDataEdit_TC182() {
			Object[][] data = XLSHelper.retrieveCellsMulti("data/data_thuyvtp.xls", 8, 8);
			return data;
		}

		@Test(priority = 13, dataProvider = "SetDataEdit_TC182")

		public void editCoupon_TC182(String publish_date, String publish_time, String end_publish_date,
				String end_publish_time, String title, String content, String tags, String meta_description,
				String MaxCoupon, String start_coupon_period, String start_time, String end_coupon_period, String end_time,
				String coupon_description, String about, String url, String id_client) {

			driver.get(url);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			
			
			Select select = (Select)driver.findElement(By.cssSelector("#client_id"));
			select.selectByValue("2");
			
			WebElement editpublish_date = driver.findElement(By.cssSelector("#publish_date"));
			editpublish_date.clear();
			editpublish_date.sendKeys(publish_date);

			WebElement editpublish_time = driver.findElement(
					By.cssSelector("form#form-coupon > div:nth-of-type(2) > div > div > div:nth-of-type(2) > div > input"));
			editpublish_time.clear();
			editpublish_time.sendKeys(publish_time);

			WebElement endpublish_date = driver.findElement(By.cssSelector("#end_publish_date"));
			endpublish_date.clear();
			endpublish_date.sendKeys(end_publish_date);

			WebElement editend_publish_time = driver.findElement(By.cssSelector("#end_publish_time"));
			editend_publish_time.clear();
			editend_publish_time.sendKeys(end_publish_time);

			WebElement radioMemberOnly = driver.findElement(By.cssSelector("form input[name='is_member_only'][value='1']"));
			radioMemberOnly.click();

			WebElement radioHide = driver.findElement(By.cssSelector("form input[name='hide'][value='0']"));
			radioHide.click();

			WebElement radioFlag = driver.findElement(By.cssSelector("form input[name='social_flag'][value='0']"));
			radioFlag.click();

			WebElement edittitle = driver.findElement(By.cssSelector("#title"));
			edittitle.clear();
			edittitle.sendKeys(title);

			WebElement editcontent = driver
					.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div > div:nth-of-type(3) > div"));
			editcontent.clear();
			editcontent.sendKeys(content);

			WebElement tag = driver.findElement(By.cssSelector("div.bootstrap-tagsinput > input[type=\"text\"]"));
			tag.sendKeys(Keys.BACK_SPACE);

			tag.sendKeys(tags);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			WebElement dropdown = driver.findElement(By.cssSelector("a.dropdown-item"));
			dropdown.click();

			WebElement metades = driver.findElement(By.cssSelector("textarea#description"));
			metades.clear();

			metades.sendKeys(meta_description);

			WebElement edit_start_coupon_period = driver.findElement(By.cssSelector("#start_coupon_period"));
			edit_start_coupon_period.clear();
			edit_start_coupon_period.sendKeys(start_coupon_period);

			WebElement edit_start_time = driver.findElement(By.cssSelector("#start_time"));
			edit_start_time.clear();
			edit_start_time.sendKeys(start_time);

			WebElement edit_end_coupon_period = driver.findElement(By.cssSelector("#end_coupon_period"));
			edit_end_coupon_period.clear();
			edit_end_coupon_period.sendKeys(end_coupon_period);

			WebElement edit_end_time = driver.findElement(By.cssSelector("#end_time"));
			edit_end_time.clear();
			edit_end_time.sendKeys(end_time);
			
			WebElement maxcoupon = driver.findElement(By.cssSelector("#max_coupon"));
			maxcoupon.clear();
			maxcoupon.sendKeys(MaxCoupon);
			
			WebElement trackingMethod = driver.findElement(By.cssSelector("#tracking_method > option[value=\"1\"]"));
			trackingMethod.click();

			WebElement couponDes = driver
					.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(9) > div > div"));
			couponDes.clear();
			couponDes.sendKeys(coupon_description);

			WebElement editabout = driver
					.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(4) > div:nth-of-type(10) > div > div"));
			editabout.clear();
			editabout.sendKeys(about);

			WebElement btnCheckPreview = driver
					.findElement(By.cssSelector("form#form-coupon > div:nth-of-type(5) > span > button"));

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", btnCheckPreview);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			WebElement btnPCPreview = driver.findElement(By.cssSelector(".btn-preview .preview-mode-btn:nth-of-type(2)"));
			
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", btnPCPreview);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			WebElement btnNext = driver.findElement(By.cssSelector("#pc-preview .next-to-confirm"));
		
			btnNext.submit();
			// Verify
			

			WebElement select1 = driver.findElement(By.cssSelector("#client_id"));

			Assert.assertEquals("2", select1.getAttribute("value"));
			
			WebElement trackingMethod1 = driver.findElement(By.cssSelector("#tracking_method > option[value=\"1\"]"));
			Assert.assertEquals("1", trackingMethod1.getAttribute("value"));
			
			
			WebElement pincode = driver.findElement(By.cssSelector("form input[name='pin_code']"));
			Assert.assertEquals("3145", pincode.getAttribute("value"));
			
			WebElement  editpublish_date1 = driver.findElement(By.cssSelector("form input[name='publish_date']"));
			Assert.assertEquals(publish_date, editpublish_date1.getAttribute("value"));
			WebElement editpublish_time1 = driver.findElement(
					By.cssSelector("form input[name='publish_time']"));
			
			Assert.assertEquals(publish_time, editpublish_time1.getAttribute("value"));
			
			WebElement endpublish_date1 = driver.findElement(By.cssSelector("#end_publish_date"));
			Assert.assertEquals(end_publish_date, endpublish_date1.getAttribute("value"));
			
			

			/*WebElement radioHide1 = driver.findElement(By.cssSelector("form input[name='hide'][value='0']"));
			
			Assert.assertTrue(  radioHide1.isSelected());*/

		}

}




