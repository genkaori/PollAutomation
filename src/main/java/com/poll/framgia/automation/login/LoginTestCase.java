package com.poll.framgia.automation.login;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.poll.framgia.automation.bean.UserBean;
import com.poll.framgia.automation.util.EmailValidator;
import com.poll.framgia.automation.util.EncodeUTF8;
import com.poll.framgia.automation.util.ReadWriteFileExcel;


public class LoginTestCase {
	public static final String PATH = "C:\\Users\\nguyen.thi.thu.huong\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public static final String DRIVER = "webdriver.chrome.driver";
	public static final String URL = "http://poll.framgia.vn/login";
	String fileDataLoginValidPath = "data/user_input_valid.xlsx";
	String fileDataLoginInvalidPath = "data/user_input_invalid.xlsx";
	
	String fileUserLoginTemplatePath = "data/user_template.xlsx";
	String fileUserLoginOutputPath = "data/user_ouput.xlsx";
	String fileDataInvalidOutputPath = "data/user_data_invalid_output.xlsx";
	
	final String MESSAGE_ERROR = "Email không hợp lệ Hãy nhập mật khẩu của bạn Hãy nhập email của bạn";
	List testResultExport = new ArrayList();
	EncodeUTF8 encodeUtf8;
	EmailValidator emailValidator = new EmailValidator();
	WebElement emailForm, passForm;
	private static WebDriver driver = null;
	ReadWriteFileExcel readWirteExcel = new ReadWriteFileExcel();
	List<UserBean> listUserValid, listUserInvalid;
	
	
	
	@DataProvider(name = "dataUserValid")
	public Object[][] dataValidLogin() {
		int numberCases = listUserValid.size();
		Object[][] Cred = new Object[numberCases][2];
		for (int i = 0; i < numberCases; i++) {
			UserBean x = (UserBean) listUserValid.get(i);
			Cred[i][0] = x.getEmail();
			Cred[i][1] = x.getPassword();

		}
		return Cred;
	}
	
	@DataProvider(name = "dataUserInValid")
	public Object[][] dataInvalidLogin() {
		int numberCases = listUserInvalid.size();
		Object[][] Cred = new Object[numberCases][2];
		for (int i = 0; i < numberCases; i++) {
			UserBean x = (UserBean) listUserInvalid.get(i);
			Cred[i][0] = x.getEmail();
			Cred[i][1] = x.getPassword();

		}
		return Cred;
	}
	
	
	public void login(String email, String password){
		emailForm = driver.findElement(By.xpath("//input[@placeholder='Nhập địa chỉ emai...']"));
		emailForm.clear();
		
		emailForm.sendKeys(email);
		passForm = driver.findElement(By.name("password"));
		passForm.clear();
		passForm.sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.findElement(By.xpath("//button[@class='btn btn-success btn-block btn-login btn-darkcyan']")).click();
		
	}
	/*
	 *  email and password valid
	 */
	//@Test (dataProvider = "dataUserValid")
	public void loginDataValid(String email, String password) throws Exception{
		if(emailValidator.validate(email))
		login(email, password);
		checkLogin(email, password, isElementPresent());
		readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileUserLoginOutputPath, testResultExport);
	}
	
	   public boolean isElementPresent() {
	        boolean isPresent = true;
	        if (driver.findElements(By.cssSelector("a[href='http://poll.framgia.vn/user/profile']")).isEmpty()) {
	            isPresent = false;
	        }
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        return isPresent;
	    }

	   /*
	    *  email invalid
	    */
	 @Test (dataProvider = "dataUserInValid")
		public void loginDataInvalid(String email, String password) throws Exception{
		 if(!emailValidator.validate(email))
		 	login(email, password);			
		 	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			checkLogin(email, password, getMessageError());
			//driver.get(URL);
			readWirteExcel.writeExcell(fileUserLoginTemplatePath, fileDataInvalidOutputPath, testResultExport);
		}
	   
	 /*
	  * email valid, password invalid
	  */
	   
	 
	 /*
	  * email valid, passwor empty
	  */
	 
	 
	 
	 public boolean getMessageError(){
		 WebElement errorMessageForm = driver.findElement(By.xpath("//div[@class='col-lg-12']"));
		 List<WebElement> errorMessageList = (List<WebElement>) errorMessageForm.findElements(By.xpath("//ul[@class='alert alert-danger alert-dismissable']//li"));
			String messages = "";
			int count = 0;
			for (int i = 0; i < errorMessageList.size(); i++) {
				messages = messages + errorMessageList.get(i).getText();
				if (MESSAGE_ERROR.contains(messages)) count +=1;
			}
			System.out.println("messsage"+messages);
			if (count>=1) return true;
			return false;
	 }
	 
	public void checkLogin(String email, String password, Boolean result){
		Map testResult = new HashMap();
		testResult.put("testcase", "login");
		testResult.put("email", email);
		testResult.put("password", password);
		testResult.put("testresult", result);
		testResultExport.add(testResult);
	}

	
	
	
	
	
	
	
	
	@BeforeTest()
	public void openBrowser() throws IOException {
		listUserValid = readWirteExcel.readDataExcel(fileDataLoginValidPath);
		listUserInvalid = readWirteExcel.readDataExcel(fileDataLoginInvalidPath);
		System.setProperty(DRIVER,PATH );
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	}

	@AfterTest
	public void closebrowser() {
		System.out.print("\nBrowser close");
	}
	

}
