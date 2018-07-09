package com.framgia.automation.funjapan.script.research.add;

import java.awt.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;

public class Add_QA extends CommonTestCase {

	@Test(priority = 1, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
		
	}

	// TC#369: Check data in combo Question Type
	 @Test	(priority = 2)
	public void CheckDataInComboQuestion() {		
		 driver.get("http://fun-auto-test.framgia.vn/admin/qa/create");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Select cbQuestionType = new Select(driver.findElement(By.cssSelector("#question-type")));
			
			// Verify Question type has four options
			 Assert.assertEquals(4, cbQuestionType.getOptions().size());
			//Verify if default value in combobox is Normal 
			Assert.assertEquals("Normal", cbQuestionType.getFirstSelectedOption().getText());
			cbQuestionType.selectByVisibleText("Personality Test");
			Assert.assertEquals("Personality Test", cbQuestionType.getFirstSelectedOption().getText());
			cbQuestionType.selectByVisibleText("Quiz");
			Assert.assertEquals("Quiz", cbQuestionType.getFirstSelectedOption().getText());
			cbQuestionType.selectByVisibleText("User answer");
			Assert.assertEquals("User answer", cbQuestionType.getFirstSelectedOption().getText());
			
			
			
		
	
			
			
}
		}
