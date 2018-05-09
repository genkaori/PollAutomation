package com.poll.framgia.automation.dataprovider;


import org.testng.annotations.DataProvider;

import com.poll.framgia.automation.bean.PollBean;
import com.poll.framgia.automation.bean.UserBean;
import com.poll.framgia.automation.controller.CreatePollTestCase;
import com.poll.framgia.automation.controller.LoginTestCase;

public class DataProviderTest {

	@DataProvider(name = "dataUserValid")
	public Object[][] dataValidLogin() {
		UserBean x = (UserBean) LoginTestCase.listUserValid.get(0);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "loginEmpty")
	public Object[][] dataLoginEmpty() {
		UserBean x = (UserBean) LoginTestCase.listUserInvalid.get(0);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}

	@DataProvider(name = "loginEmailValidPassNull")
	public Object[][] dataEmailValidPassNull() {
		UserBean x = (UserBean) LoginTestCase.listUserInvalid.get(1);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "loginEmailValidPassEmpty")
	public Object[][] dataEmailValidPassEmpty() {
		UserBean x = (UserBean) LoginTestCase.listUserInvalid.get(2);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "loginEmailValidPassIncorrect")
	public Object[][] dataEmailValidPassIncorrect() {
		UserBean x = (UserBean) LoginTestCase.listUserInvalid.get(3);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "loginEmailNullPassCorrect")
	public Object[][] dataloginEmailNullPassCorrect() {
		UserBean x = (UserBean) LoginTestCase.listUserInvalid.get(4);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "loginEmailEmptyPassCorrect")
	public Object[][] dataEmailEmptyPassCorrect() {
		UserBean x = (UserBean) LoginTestCase.listUserInvalid.get(5);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "loginEmailInvalidPassCorrect")
	public Object[][] dataloginEmailInvalidPassCorrect() {
		UserBean x = (UserBean) LoginTestCase.listUserInvalid.get(6);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "loginEmailInvalidPassIncorrect")
	public Object[][] dataEmailInvalidPassIncorrect() {
		UserBean x = (UserBean) LoginTestCase.listUserInvalid.get(7);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "dataLoginGoogle")
	public Object[][] dataLoginGoogle() {
		UserBean x = (UserBean) LoginTestCase.listUserValid.get(1);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "dataLoginTwitter")
	public Object[][] dataLoginTwitter() {
		UserBean x = (UserBean) LoginTestCase.listUserValid.get(2);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider(name = "dataLoginWsm")
	public Object[][] dataLoginWsm() {
		UserBean x = (UserBean) LoginTestCase.listUserValid.get(3);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider (name ="dataLoginCreatePoll")
	public Object[][] dataLoginCreatePoll() {
		UserBean x = (UserBean) CreatePollTestCase.listUserValid.get(0);
		Object [][]Cred ={{x.getEmail(), x.getPassword()}};
		return Cred;
	}
	
	@DataProvider (name ="dataCreatePollTitleEmpty")
	public Object[][] dataCreatePollTitleEmpty() {
		PollBean x = (PollBean) CreatePollTestCase.listDataPoll.get(0);
		Object [][]Cred ={{x.getTitle(), x.getOption(), x.getDescription(), x.getLocation()}};
		return Cred;
	}
}
