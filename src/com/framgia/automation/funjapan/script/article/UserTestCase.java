package com.framgia.automation.funjapan.script.article;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;

public class UserTestCase extends CommonTestCase {
	@Test(priority = 1, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}

	@Test(priority = 2)
	public void test() {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/create");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement btnDraft = driver.findElement(By.cssSelector(
				"form[action='http://fun-auto-test.framgia.vn/admin/researchArticles/preview'] button[data-url='http://fun-auto-test.framgia.vn/admin/researchArticles/validate']"));
		WebDriverWait wait1 = new WebDriverWait(driver, 100);
		WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(btnDraft));
		element.submit();
	}

}
