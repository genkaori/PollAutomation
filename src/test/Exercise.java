package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Exercise {

	private static WebDriver driver = null;

	public static void main(String[] args) {

	}

	@Test(dataProvider = "signupData")
	public void testSignup(String name, String email, String pass) {
		WebElement signupLink = driver.findElement(By.linkText("Sign up now!"));
		signupLink.click();

		new WebDriverWait(driver, 50000).until(ExpectedConditions.visibilityOfElementLocated(By.id("new_user")));

		WebElement form = driver.findElement(By.id("new_user"));
		WebElement txtName = form.findElement(By.cssSelector("input[name='user[name]']"));
		txtName.clear();
		txtName.sendKeys(name);
		WebElement txtEmail = form.findElement(By.name("user[email]"));
		txtEmail.clear();
		txtEmail.sendKeys(email);
		WebElement txtPassword = form.findElement(By.name("user[password]"));
		txtPassword.clear();
		txtPassword.sendKeys(pass);
		WebElement txtPasswordConfirmation = form.findElement(By.name("user[password_confirmation]"));
		txtPasswordConfirmation.clear();
		txtPasswordConfirmation.sendKeys(pass);
		WebElement btnLogin = form.findElement(By.name("commit"));
		btnLogin.submit();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

		List<WebElement> imgs = driver.findElements(By.tagName("img"));
		for (WebElement img : imgs) {
			String alt = img.getAttribute("alt") + "123";
			if (alt != null) {
				Assert.assertEquals(alt, name);
				break;
			}
		}
	}

	@DataProvider
	public Object[][] signupData() {
		Object[][] data = new Object[2][3];

		data[0][0] = "Chu Anh Tuan 05";
		data[0][1] = "chu.anh.tuan.vn05@gmail.com";
		data[0][2] = "123456";

		data[1][0] = "Chu Anh Tuan 06";
		data[1][1] = "chu.anh.tuan.vn06@gmail.com";
		data[1][2] = "123456";

		return data;
	}

	@BeforeMethod
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/chu.anh.tuan/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://selenium-training.herokuapp.com/");
	}

	@AfterMethod
	public void closebrowser() {
		System.out.print("\nBrowser close");
		driver.quit();
	}

}
