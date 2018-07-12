package com.framgia.automation.funjapan.script.research.AnhLTN_Research;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.framgia.automation.funjapan.script.CommonTestCase;
import com.framgia.automation.funjapan.util.Setting;
import com.framgia.automation.funjapan.util.XLSHelper;

public class ResearchArticleEdit extends CommonTestCase {
	@Test(priority = 0, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}

	// TC ID: 218 Research Article edit => Change Author
	@DataProvider
	public Object[][] GetDataEditArticleID() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 2, 3, 3, 1);
		return data;
	}

	@Test(priority = 1, dataProvider = "GetDataEditArticleID")
	public void testChangeAuthor(String articleID) {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/edit/" + articleID);
		// get info before you change author
		HashMap<String, String> before = getDataReseachArticleEditPage();
		// choose another author
		Select listbox = new Select(driver.findElement(By.id("author_id")));
		List<WebElement> options = listbox.getOptions();
		for (WebElement webElement : options) {
			if (!(before.get("author").equals(webElement.getAttribute("value")))
					&& !(webElement.getAttribute("value").isEmpty())) {
				listbox.selectByValue(webElement.getAttribute("value"));
				break;
			}
		}
		// Click on "Draft" button
		WebElement btnDraft = driver.findElement(By.xpath("//button[contains(@class,'save-draft')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.xpath("//a[contains(@class,'btn-modify')]")).click();
		driver.findElement(By.cssSelector("a[href*='admin/researchArticles/edit/" + articleID + "']")).click();
		// get info after you changed author
		HashMap<String, String> after = getDataReseachArticleEditPage();
		Assert.assertNotEquals(after.get("author"), before.get("author"), "Please choose another author");
		Set<String> keys = after.keySet();
		keys.remove("author");
		for (String key : keys) {
			Assert.assertEquals(after.get(key), before.get(key));
		}
	}

	// TC ID: 220 Research Article edit => Change Client ID
	@Test(priority = 2, dataProvider = "GetDataEditArticleID")
	public void testChangeClientId(String articleID) {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/edit/" + articleID);
		// get info before you change author
		HashMap<String, String> before = getDataReseachArticleEditPage();
		// choose another client id
		Select listbox = new Select(driver.findElement(By.id("client_id")));
		List<WebElement> options = listbox.getOptions();
		for (WebElement webElement : options) {
			if (!(before.get("client_id").equals(webElement.getAttribute("value")))) {
				listbox.selectByValue(webElement.getAttribute("value"));
				break;
			}
		}
		// Click on "Draft" button
		WebElement btnDraft = driver.findElement(By.xpath("//button[contains(@class,'save-draft')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.xpath("//a[contains(@class,'btn-modify')]")).click();
		driver.findElement(By.cssSelector("a[href*='admin/researchArticles/edit/" + articleID + "']")).click();
		// get info after you changed author
		HashMap<String, String> after = getDataReseachArticleEditPage();
		Assert.assertNotEquals(after.get("client_id"), before.get("client_id"), "Please choose another client id");
		Set<String> keys = after.keySet();
		keys.remove("client_id");
		for (String key : keys) {
			Assert.assertEquals(after.get(key), before.get(key));
		}
	}

	// TC ID: 222 Research Article edit => Change Change Category
	@Test(priority = 3, dataProvider = "GetDataEditArticleID")
	public void testChangeCategory(String articleID) {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/edit/" + articleID);
		// get info before you change author
		HashMap<String, String> before = getDataReseachArticleEditPage();

		// choose another client id
		Select listbox = new Select(driver.findElement(By.id("category_id")));
		List<WebElement> options = listbox.getOptions();
		for (WebElement webElement : options) {
			if (!(before.get("category").equals(webElement.getAttribute("value")))
					&& !(webElement.getAttribute("value").isEmpty())) {
				listbox.selectByValue(webElement.getAttribute("value"));
				break;
			}
		}

		// Click on "Draft" button
		WebElement btnDraft = driver.findElement(By.xpath("//button[contains(@class,'save-draft')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.xpath("//a[contains(@class,'btn-modify')]")).click();
		driver.findElement(By.cssSelector("a[href*='admin/researchArticles/edit/" + articleID + "']")).click();

		// get info after you changed author
		HashMap<String, String> after = getDataReseachArticleEditPage();
		Assert.assertNotEquals(after.get("category"), before.get("category"), "Please choose another category");
		Set<String> keys = after.keySet();
		keys.remove("category");
		for (String key : keys) {
			Assert.assertEquals(after.get(key), before.get(key));
		}
	}

	// TC ID 226: Research Article edit > Check can't enter Published time > End
	// published time
	@DataProvider
	public Object[][] GetDataChangePublished() {
		Object[][] data = XLSHelper.getValueRow1ToRow2AndCol(Setting.getSetting(Setting.DATA_FILE), 2, 6, 6, 4);
		return data;
	}

	@Test(priority = 4, dataProvider = "GetDataChangePublished")
	public void testChangePublished(String articleID, String publishedTime, String endPublishedTime, String error) {
		driver.get("http://fun-auto-test.framgia.vn/admin/researchArticles/edit/" + articleID);
		driver.findElement(By.name("publish_date")).clear();
		driver.findElement(By.name("publish_date")).sendKeys(publishedTime);
		driver.findElement(By.name("end_publish_date")).clear();
		driver.findElement(By.name("end_publish_date")).sendKeys(endPublishedTime);
		// Click on "Draft" button
		WebElement btnDraft = driver.findElement(By.xpath("//button[contains(@class,'save-draft')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnDraft);
		String errorMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]")).getText();
		Assert.assertTrue(errorMessage.contains(error));
	}

	public HashMap<String, String> getDataReseachArticleEditPage() {
		HashMap<String, String> map = new HashMap<String, String>();
		// author
		Select listbox = new Select(driver.findElement(By.id("author_id")));
		map.put("author", listbox.getFirstSelectedOption().getAttribute("value"));
		// country
		map.put("country",
				new Select(driver.findElement(By.id("locale_id"))).getFirstSelectedOption().getAttribute("value"));
		// client id
		map.put("client_id",
				new Select(driver.findElement(By.id("client_id"))).getFirstSelectedOption().getAttribute("value"));
		// category
		map.put("category",
				new Select(driver.findElement(By.id("category_id"))).getFirstSelectedOption().getAttribute("value"));
		// published date
		map.put("published_date", driver.findElement(By.id("publish_date")).getAttribute("value"));
		// published time
		map.put("published_time", driver.findElement(By.name("publish_time")).getAttribute("value"));
		// end published date
		map.put("end_published_date", driver.findElement(By.id("end_publish_date")).getAttribute("value"));
		// end published time
		map.put("end_published_time", driver.findElement(By.id("end_publish_time")).getAttribute("value"));
		// Member Only
		List<WebElement> radioMemberButtons = driver.findElements(By.name("is_member_only"));
		for (WebElement webElement : radioMemberButtons) {
			if (webElement.isSelected()) {
				map.put("member", webElement.getAttribute("value"));
			}
		}
		// Hide
		List<WebElement> radioHideButtons = driver.findElements(By.name("hide"));
		for (WebElement webElement : radioHideButtons) {
			if (webElement.isSelected()) {
				map.put("hide", webElement.getAttribute("value"));
			}
		}
		// Social Flag
		List<WebElement> radioSocialButtons = driver.findElements(By.name("social_flag"));
		for (WebElement webElement : radioSocialButtons) {
			if (webElement.isSelected()) {
				map.put("social_flag", webElement.getAttribute("value"));
			}
		}
		// Article Title
		map.put("article_title", driver.findElement(By.id("title")).getAttribute("value"));
		// Article Content
		map.put("article_content", driver.findElement(By.name("contentMedium")).getAttribute("value"));
		// Tags
		List<WebElement> tagList = driver.findElements(By.xpath("//div[@class='bootstrap-tagsinput']/span"));
		for (WebElement webElement : tagList) {
			map.put("tags", webElement.getText());
		}
		// Meta Description
		map.put("meta_description", driver.findElement(By.name("description")).getText());
		return map;
	}
}
