package com.framgia.automation.funjapan.script.article;

import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;

public class UserTestCase extends CommonTestCase {
	@Test(priority=1, dataProvider="SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}

}
