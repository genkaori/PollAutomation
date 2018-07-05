package com.framgia.automation.funjapan.script;

import static com.framgia.automation.funjapan.util.Setting.URL_ADMIN;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.framgia.automation.funjapan.util.Setting;
import com.framgia.automation.funjapan.util.XLSHelper_1;

public abstract class CommonTestCase {
	public static WebDriver driver = null;

	@BeforeTest
	public static void beforeTest() {
		System.setProperty(Setting.getSetting(Setting.WEBDRIVER), Setting.getSetting(Setting.WEBDRIVER_PATH));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Setting.getSetting(URL_ADMIN));
		XLSHelper_1.readXLS();
	}

	@AfterTest
	public static void end() {
		// driver.quit();
	}

	@DataProvider
	public Object[][] SetLogin() {
		Object[][] data = XLSHelper_1.retrieveCellsMulti(0, 2, 2);
		return data;
	}


	public void testLogin(String email, String pass) {
		WebElement btnLogin = driver
				.findElement(By.cssSelector("a[href='http://fun-auto-test.framgia.vn/admin/account/facebook']"));
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

	}
}
