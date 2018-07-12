package com.framgia.automation.funjapan.script.article.list;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;

public class ArticleListTestCase extends CommonTestCase {
	final static String ARTICLE_ID = "Article ID";
	final static String CLIENT_ID = "Client ID";
	final static String ARTICLE_TITLE = "Article Title";
	final static String NO_ARTICLE = "No Articles";

	@Test(priority = 0, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}

	// case 1: Check sort default
	@Test(priority = 1)
	public void testSortDefault() {
		driver.get("http://fun-auto-test.framgia.vn/admin/articles");
		WebElement acticle_default = driver.findElement(By.xpath("//b[@class='selected-search-by']"));
		Assert.assertEquals(true, ARTICLE_ID.contains(acticle_default.getText()));
	}

	// case 2: Check sort by column
	@Test(priority = 2)
	public void testSortByColumn() {
		Assert.assertEquals(checkSortByColumn(), true);
	}

	// case 3: Search Client ID, data valid

	@Test(priority = 3, dataProvider = "dataSearchClientID", dataProviderClass = com.framgia.automation.funjapan.script.article.list.DataProviderTest.class)
	public void testSearchClientID(String articleSearch, String clientID) {
		driver.get("http://fun-auto-test.framgia.vn/admin/articles");
		selectSearchMethod(articleSearch, clientID);
		Assert.assertEquals((checkKeepIdSearchOnTextBox(clientID) && searchClientId(clientID)), true);
	}

	// case 4: Search client ID, No articles result.
	@Test(priority = 4, dataProvider = "dataSearchClientIdNoResult", dataProviderClass = com.framgia.automation.funjapan.script.article.list.DataProviderTest.class)
	public void testSearchClientIdNoResult(String articleSearch, String clientID) {
		driver.get("http://fun-auto-test.framgia.vn/admin/articles");
		selectSearchMethod(articleSearch, clientID);
		Assert.assertEquals(checkClientIdNoResult(clientID), true);
	}

	// case 5: Search client ID, max length data input
	@Test(priority = 5, dataProvider = "dataSearchClientIdMaxLength", dataProviderClass = com.framgia.automation.funjapan.script.article.list.DataProviderTest.class)
	public void testSearchClientIdMaxLength(String articleSearch, String clientID) {
		driver.get("http://fun-auto-test.framgia.vn/admin/articles");
		selectSearchMethod(articleSearch, clientID);
		Assert.assertEquals(checkClientIdNoResult(clientID), true);
	}

	// case 6: Search Article ID, data valid
	@Test(priority = 6, dataProvider = "dataSearchArticleID", dataProviderClass = com.framgia.automation.funjapan.script.article.list.DataProviderTest.class)
	public void testSearchArticleID(String articleSearch, String articleID) {
		driver.get("http://fun-auto-test.framgia.vn/admin/articles");
		selectSearchMethod(articleSearch, articleID);
		Assert.assertEquals((checkKeepIdSearchOnTextBox(articleID) && searchArticleId(articleID)), true);
	}

	public boolean checkClientIdNoResult(String id) {
		WebElement element = driver.findElement(By.xpath("//span[@class='label label-warning']"));
		return NO_ARTICLE.equals(element.getText()) ? true : false;
	}

	public boolean searchArticleId(String id) {
		WebElement element = driver.findElement(By.xpath("//table//tbody//tr"));
		List<WebElement> colmns = element.findElements(By.tagName("td"));
		String str = colmns.get(1).getText();
		return id.equals(str.replace("#", "").trim())? true : false;
	}

	public boolean searchClientId(String id) {
		boolean isID = false;
		WebElement element = driver.findElement(By.xpath("//table//tbody"));
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			String str = row.findElements(By.xpath("//td")).get(0).getText();
			if (str.contains(id))
				isID = true;
		}

		return isID;
	}

	public boolean checkKeepIdSearchOnTextBox(String id) {
		WebElement keySearch = driver.findElement(By.xpath("//input[@placeholder = 'Search']"));
		String txtKey = keySearch.getAttribute("value");
		return id.equals(txtKey) ? true : false;
	}

	public void selectSearchMethod(String searchMethod, String idSearch) {
		driver.findElement(By.xpath("//div[@class='input-group-btn']")).click();
		WebElement listArticleIDSearch = driver.findElement(By.xpath("//ul[@class='dropdown-menu search-by']"));
		List<WebElement> options = listArticleIDSearch.findElements(By.tagName("li"));
		options.get(Integer.parseInt(searchMethod)).click();
		WebElement keySearch = driver.findElement(By.xpath("//input[@placeholder = 'Search']"));
		keySearch.sendKeys(idSearch);
		driver.findElement(By.xpath("//button[@class='btn btn-primary bg-custom']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public boolean checkSortByColumn() {
		driver.findElement(By.xpath("//div[@class='input-group-btn']")).click();
		WebElement listArticleIDSearch = driver.findElement(By.xpath("//ul[@class='dropdown-menu search-by']"));
		List<WebElement> options = listArticleIDSearch.findElements(By.tagName("li"));
		int size = options.size();
		String clientID = options.get(0).getText();
		String articleID = options.get(1).getText();
		String articleTitle = options.get(2).getText();

		return (size == 3 && ARTICLE_ID.equals(articleID) && CLIENT_ID.equals(clientID)
				&& ARTICLE_TITLE.equals(articleTitle)) ? true : false;
	}

}
