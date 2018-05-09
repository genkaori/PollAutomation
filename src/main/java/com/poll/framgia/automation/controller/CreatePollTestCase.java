package com.poll.framgia.automation.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.poll.framgia.automation.bean.PollBean;
import com.poll.framgia.automation.bean.UserBean;
import com.poll.framgia.automation.util.ReadWriteFileExcel;

import javafx.event.ActionEvent;
import junit.framework.Assert;

public class CreatePollTestCase {
	public static final String PATH = "C:\\Users\\Huong\\Downloads\\chromedriver.exe";
	public static final String DRIVER = "webdriver.chrome.driver";
	public static final String URL = "http://poll.framgia.vn/login";
	public static List<UserBean> listUserValid;
	public static List<PollBean> listDataPoll;
	
	final String MESSAGE_ERROR = "Vui lòng nhập vào ô này";
	String fileDataLoginValidPath = "data/user_input_valid.xlsx";
	String fileDataPollPath = "data/poll_data_input.xlsx";
	private static WebDriver driver = null;
	LoginTestCase login = new LoginTestCase();
	ReadWriteFileExcel readWirteExcel = new ReadWriteFileExcel();
	WebElement passForm, emailForm;
	
	@Test (priority = 0, dataProvider = "dataLoginCreatePoll", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginDataValid(String email, String password) throws Exception {
		System.out.println("----test start------");
		login(email, password);
		
	}
	/*
	 * case 1: check infor name, email
	 */
//	@Test(priority = 1)
	public void checkInfor(){
		String nameInfor = driver.findElement(By.cssSelector("input[id='name']")).getAttribute("value");
		String emailInfor = driver.findElement(By.cssSelector("input[id='email']")).getAttribute("value");
		driver.findElement(By.cssSelector("a[href = 'http://poll.framgia.vn/user/profile']")).click();;
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String nameProfile = driver.findElement(By.cssSelector("input[id='name']")).getAttribute("value");
		String emailProfile = driver.findElement(By.cssSelector("input[id='email']")).getAttribute("value");
		
		System.out.println("nameinfo "+nameInfor.toString()+"emailInfor "+emailInfor.toString()+" namepro ");
		Assert.assertEquals(true, (nameInfor.equals(nameProfile)) && (emailInfor.equals(emailProfile)));
	}
	
	/*
	 * case 2: title null
	 */
	//@Test(priority = 1)
	public void createPollwithNullTitle(){
		driver.findElement(By.xpath("//div//ul//li[@class='next']//a[@class='btn-change-step btn btn-darkcyan']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String message = driver.findElement(By.xpath("//div//span[@id='title-error']")).getText();
		Assert.assertEquals(true, MESSAGE_ERROR.equals(message));
	}
	
	/*
	 * case 3: title empty
	 */
	@Test (priority = 1, dataProvider ="dataCreatePollTitleEmpty", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class )
	public void createPollTitleEmpty(String title, String option, String description, String location){
		driver.findElement(By.id("title")).sendKeys(title);
		//WebElement optionAnswer = driver.findElement(By.xpath("//div[@class='col-lg-6 col-md-6 col-sm-6 col-xs-6 col-xs-info']//div[@class='form-group']"));
		Select select = new Select(driver.findElement(By.xpath("//select")));
		WebElement optionAnswer = select.getFirstSelectedOption();
		String defaultItem = optionAnswer.getText();
		System.out.println(defaultItem );
		
		driver.findElement(By.xpath("//textarea[@id='description']")).sendKeys(description);
		driver.findElement(By.xpath("//input[@id='location']")).sendKeys(location);
		
	}
	
	
	public void login(String email, String password) {
			emailForm = driver.findElement(By.xpath("//input[@placeholder='Nhập địa chỉ emai...']"));
			emailForm.sendKeys(email);
			passForm = driver.findElement(By.name("password"));
			passForm.sendKeys(password);
		driver.findElement(By.xpath("//button[@class='btn btn-success btn-block btn-login btn-darkcyan']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@BeforeTest()
	public void openBrowser() throws IOException {
		listUserValid = readWirteExcel.readDataExcel(fileDataLoginValidPath);
		listDataPoll = readWirteExcel.readDataPollExcel(fileDataPollPath);
		System.setProperty(DRIVER, PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closebrowser() {
		System.out.print("\nBrowser close");
	}
	
}
