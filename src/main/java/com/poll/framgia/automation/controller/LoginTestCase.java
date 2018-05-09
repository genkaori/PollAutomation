package com.poll.framgia.automation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.poll.framgia.automation.bean.UserBean;
import com.poll.framgia.automation.util.ReadWriteFileExcel;


public class LoginTestCase {
	public static final String PATH = "C:\\Users\\Huong\\Downloads\\chromedriver.exe";
	public static final String DRIVER = "webdriver.chrome.driver";
	public static final String URL = "http://poll.framgia.vn/login";
	final String MESSAGE_ERROR = "Email không hợp lệ Hãy nhập mật khẩu của bạn Hãy nhập email của bạn";

	public static List<UserBean> listUserValid, listUserInvalid;
	private static WebDriver driver = null;

	String fileDataLoginValidPath = "data/user_input_valid.xlsx";
	String fileDataLoginInvalidPath = "data/user_input_invalid.xlsx";
	String fileUserLoginTemplatePath = "data/user_template.xlsx";
	String fileUserLoginOutputPath = "data/user_ouput.xlsx";
	String fileDataInvalidOutputPath = "data/user_data_invalid_output.xlsx";

	List testResultExport = new ArrayList();
	WebElement emailForm, passForm;
	ReadWriteFileExcel readWirteExcel = new ReadWriteFileExcel();

	/*
	 * case 1: email and password valid
	 */
	 @Test (dataProvider = "dataUserValid", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginDataValid(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, isElementPresent());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileUserLoginOutputPath, testResultExport);
	}

	/*
	 * case 2: email null, pass null
	 */
	 @Test
	public void loginNullValue() {
		driver.findElement(By.xpath("//button[@class='btn btn-success btn-block btn-login btn-darkcyan']")).click();
		checkLogin(null, null, getMessageError());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}

	/*
	 * case 3: email empty, pass empty
	 */

	 @Test(dataProvider = "loginEmpty", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginEmpty(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, getMessageError());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}

	/*
	 * case 4: email valid, password null
	 */

	@Test(dataProvider = "loginEmailValidPassNull", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginEmailValidPassNull(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, getMessageError());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}

	/*
	 * case 5: email valid, password empty
	 */
	@Test(dataProvider = "loginEmailValidPassEmpty", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginEmailValidPassEmpty(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, getMessageError());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}
	
	
	/*
	 * case 6: email valid, password incorrect
	 */
	@Test(dataProvider = "loginEmailValidPassIncorrect", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginEmailValidPassIncorrect(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, logiFail());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}
	
	/*
	 * case 7: email null, password correct
	 */
	@Test(dataProvider = "loginEmailNullPassCorrect", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginEmailNullPassCorrect(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, getMessageError());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}
	
	
	/*
	 * case 8: email empty, password correct
	 */
	
	@Test(dataProvider = "loginEmailEmptyPassCorrect", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginEmailEmptyPassCorrect(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, getMessageError());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}
	
	/*
	 * case 9: email invalid, password correct
	 */
	@Test(dataProvider = "loginEmailInvalidPassCorrect", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginEmailInvalidPassCorrect(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, getMessageError());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}
	
	
	/*
	 * case 10: email invalid, password incorrect
	 */
	@Test(dataProvider = "loginEmailInvalidPassIncorrect", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginEmailInvalidPassIncorrect(String email, String password) throws Exception {
		login(email, password);
		checkLogin(email, password, getMessageError());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
	}
	
	/*
	 * case 11: Login via facebook (system error)
	 */
	
	@Test(dataProvider = "dataFacebook")
	public void loginViaFacebook(String email, String password){
		loginOther("a[href='http://poll.framgia.vn/redirect/facebook'");
	}
	
	/*
	 * case 12: Login via Google
	 */
	@Test(dataProvider = "dataLoginGoogle", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginViaGoogle(String email, String password){
		loginOther("a[href='http://poll.framgia.vn/redirect/google']");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
		driver.findElement(By.cssSelector("input[id='identifierId'")).sendKeys(email);
		driver.findElement(By.id("identifierNext")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.findElement(By.xpath("//div//input[@aria-label='Enter your password']")).sendKeys(password);;
		driver.findElement(By.xpath("//div[@id='passwordNext']//content")).click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
		checkLogin(email, password, isElementPresent());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileUserLoginOutputPath, testResultExport);
		
	}
	
	/*
	 * case 13: Login via Twitter
	 */
	@Test(dataProvider = "dataLoginTwitter", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginViaTwitter(String email, String password){
		loginOther("a[href='http://poll.framgia.vn/redirect/twitter']");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
		driver.findElement(By.cssSelector("input[id='username_or_email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);;
		driver.findElement(By.cssSelector("input[id='allow']")).click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
		checkLogin(email, password, isElementPresent());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileUserLoginOutputPath, testResultExport);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
		checkLogin(email, password, isElementPresent());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileUserLoginOutputPath, testResultExport);
	}
	
	/*
	 * case 14: Login via Wsm
	 */
	@Test(dataProvider = "dataLoginWsm", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void loginViaWsm(String email, String password){
		loginOther("a[href='http://poll.framgia.vn/redirect/framgia']");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
		driver.findElement(By.xpath("//a[@class='wsm-btn btn-login']")).click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
		driver.findElement(By.cssSelector("input[id='user_email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[id='user_password']")).sendKeys(password);;
		driver.findElement(By.cssSelector("button[class='wsm-btn login-success']")).click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
		checkLogin(email, password, isElementPresent());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileUserLoginOutputPath, testResultExport);
		
	}
	
	/*
	 * case 15: Remember (system error)
	 */
	
	@Test (dataProvider = "dataUserValid", dataProviderClass = com.poll.framgia.automation.dataprovider.DataProviderTest.class)
	public void testRemeberMe(String email, String password) throws IOException {
	
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("user-data-dir=/data/temp/");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		WebElement checkBox = driver.findElement(By.cssSelector("input[name='remember']"));
		if(!checkBox.isSelected())
		{
			checkBox.click();
			login(email, password);
		}
		driver.close();
		driver = new ChromeDriver(capabilities);
		driver.get(URL);
		
	}
	
	
	public void loginOther(String path){
		driver.findElement(By.cssSelector(path)).click();
	}
	public void login(String email, String password) {
		if (email == null) {
			passForm = driver.findElement(By.name("password"));
			passForm.sendKeys(password);

		} else if (password == null) {
			emailForm = driver.findElement(By.xpath("//input[@placeholder='Nhập địa chỉ emai...']"));
			emailForm.sendKeys(email);
		} else {
			emailForm = driver.findElement(By.xpath("//input[@placeholder='Nhập địa chỉ emai...']"));
			emailForm.sendKeys(email);
			passForm = driver.findElement(By.name("password"));
			passForm.sendKeys(password);
		}
		driver.findElement(By.xpath("//button[@class='btn btn-success btn-block btn-login btn-darkcyan']")).click();

	}

	public boolean logiFail(){
		return driver.findElement(By.xpath("//div[@class = 'alert alert-success']")).isDisplayed() ? true:false;
	}
	public boolean isElementPresent() {
		return driver.findElements(By.cssSelector("a[href='http://poll.framgia.vn/user/profile']")).isEmpty() ? false: true;
	}

	public boolean getMessageError() {
		WebElement errorMessageForm = driver.findElement(By.xpath("//div[@class='col-lg-12']"));
		List<WebElement> errorMessageList = (List<WebElement>) errorMessageForm
				.findElements(By.xpath("//ul[@class='alert alert-danger alert-dismissable']//li"));
		String messages = "";
		int count = 0;
		for (int i = 0; i < errorMessageList.size(); i++) {
			messages = messages + errorMessageList.get(i).getText();
			if (MESSAGE_ERROR.contains(messages))
				count += 1;
		}
		System.out.println("messsage" + messages);
		
		return count >= 1 ? true: false;
	}

	public void checkLogin(String email, String password, Boolean result) {
		Map testResult = new HashMap();
		testResult.put("testcase", "login");
		testResult.put("email", email);
		testResult.put("password", password);
		testResult.put("testresult", result);
		testResultExport.add(testResult);
	}

	public void waitForElement(int seconds, String waitConditionLocator) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(waitConditionLocator)));
	}

	@BeforeTest()
	public void openBrowser() throws IOException {
		listUserValid = readWirteExcel.readDataExcel(fileDataLoginValidPath);
		listUserInvalid = readWirteExcel.readDataExcel(fileDataLoginInvalidPath);
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