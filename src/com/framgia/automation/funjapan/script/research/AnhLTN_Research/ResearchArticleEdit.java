package com.framgia.automation.funjapan.script.research.AnhLTN_Research;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framgia.automation.funjapan.script.CommonTestCase;

public class ResearchArticleEdit extends CommonTestCase {
	private String EDIT_ARTICLES_ID = "1320";

	@Test(priority = 0, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}

	// TC ID: 218 Research Article edit => Change Author
	@Test(priority = 1)
	public void testChangeAuthor() {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/edit/" + EDIT_ARTICLES_ID);
		// get info before you change author
		ArrayList<String> before = new ArrayList<String>();
		getDataReseachArticleEditPage(before);
		// choose another author
		Select listbox = new Select(driver.findElement(By.id("author_id")));
		listbox.selectByValue("6");
		// Click on "Draft" button
		WebElement btnDraft = driver.findElement(By.xpath("//button[contains(@class,'save-draft')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//a[contains(@class,'btn-modify')]")).click();
		driver.findElement(By.cssSelector("a[href*='admin/researchArticles/edit/"+ EDIT_ARTICLES_ID + "']")).click();

		// get info after you changed author
		ArrayList<String> after = new ArrayList<String>();
		getDataReseachArticleEditPage(after);
		Assert.assertNotEquals(after.get(0), before.get(0), "Please choose another author");
		for (int i = 1; i < after.size(); i++) {
			Assert.assertEquals(after.get(i), before.get(i));
		}
	}

	public ArrayList<String> getDataReseachArticleEditPage(ArrayList<String> array) {
		// author
		Select listbox = new Select(driver.findElement(By.id("author_id")));
		String selectedValue = listbox.getFirstSelectedOption().getAttribute("value");
		array.add(selectedValue);
		// country
		array.add(new Select(driver.findElement(By.id("locale_id"))).getFirstSelectedOption().getText());
		// client id
		array.add(new Select(driver.findElement(By.id("client_id"))).getFirstSelectedOption().getText());
		// category
		array.add(new Select(driver.findElement(By.id("category_id"))).getFirstSelectedOption().getText());
		// published date
		array.add(driver.findElement(By.id("publish_date")).getAttribute("value"));
		// published time
		array.add(driver.findElement(By.name("publish_time")).getAttribute("value"));
		// end published date
		array.add(driver.findElement(By.id("end_publish_date")).getAttribute("value"));
		// end published time
		array.add(driver.findElement(By.id("end_publish_time")).getAttribute("value"));
		// Member Only
		List<WebElement> radioMemberButtons = driver.findElements(By.name("is_member_only"));
		for (WebElement webElement : radioMemberButtons) {
			if (webElement.isSelected()) {
				array.add(webElement.getAttribute("value"));
			}
		}
		// Hide
		List<WebElement> radioHideButtons = driver.findElements(By.name("hide"));
		for (WebElement webElement : radioHideButtons) {
			if (webElement.isSelected()) {
				array.add(webElement.getAttribute("value"));
			}
		}
		// Social Flag
		List<WebElement> radioSocialButtons = driver.findElements(By.name("social_flag"));
		for (WebElement webElement : radioSocialButtons) {
			if (webElement.isSelected()) {
				array.add(webElement.getAttribute("value"));
			}
		}
		// Article Title
		array.add(driver.findElement(By.id("title")).getAttribute("value"));
		// Article Content
		array.add(driver.findElement(By.name("contentMedium")).getAttribute("value"));
		// Tags
		List<WebElement> tagList = driver.findElements(By.xpath("//div[@class='bootstrap-tagsinput']/span"));
		for (WebElement webElement : tagList) {
			array.add(webElement.getText());
		}
		// Meta Description
		array.add(driver.findElement(By.name("description")).getText());
		return array;
	}

}
