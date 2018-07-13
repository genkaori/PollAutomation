package com.framgia.automation.funjapan.script.article.list;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;

public class ArticleListTestCase extends CommonTestCase {

	@Test(priority = 0, dataProvider = "SetLogin")
	public void testLogin(String email, String pass) {
		super.testLogin(email, pass);
	}

	// case 1: Check sort default
	@Test(priority = 1)
	public void testSortDefault() {
		driver.get("http://fun-auto-test.framgia.vn/admin/articles");
		WebElement acticle_default = driver.findElement(By.xpath("//b[@class='selected-search-by']"));
		Assert.assertEquals(true, Constants.ARTICLE_ID.contains(acticle_default.getText()));
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

	// case 7: Search Article ID, No articles result
		@Test(priority = 7, dataProvider = "dataSearchArticleIdNoResult", dataProviderClass = com.framgia.automation.funjapan.script.article.list.DataProviderTest.class)
		public void testSearchArticleIdNoResult(String articleSearch, String articleID) {
			driver.get("http://fun-auto-test.framgia.vn/admin/articles");
			selectSearchMethod(articleSearch, articleID);
			Assert.assertEquals(checkIdNoResult(), true);
		}

		// case 8:  Search Article ID, max length data input
		@Test(priority = 8, dataProvider = "dataSearchArticleIdNoResult", dataProviderClass = com.framgia.automation.funjapan.script.article.list.DataProviderTest.class)
		public void testSearchArticleIdMaxLength(String articleSearch, String articleID) {
			driver.get("http://fun-auto-test.framgia.vn/admin/articles");
			selectSearchMethod(articleSearch, articleID);
			Assert.assertEquals(checkIdNoResult(), true);
		}
		
		// case 9: Search Article Title, data valid
		@Test(priority = 9, dataProvider = "dataSearchArticleTitle", dataProviderClass = com.framgia.automation.funjapan.script.article.list.DataProviderTest.class)
		public void testSearchArticleTitle(String articleSearch, String titleID) {
			driver.get("http://fun-auto-test.framgia.vn/admin/articles");
			selectSearchMethod(articleSearch, titleID);
			Assert.assertEquals((checkKeepIdSearchOnTextBox(titleID) && searchArticleTitle(titleID)), true);
		}

		// case 10: Search Title ID, No articles result
		@Test(priority = 10, dataProvider = "dataSearchArticleTitleNoResult", dataProviderClass = com.framgia.automation.funjapan.script.article.list.DataProviderTest.class)
		public void testSearchArticleTitleNoResult(String articleSearch, String titleID) {
			driver.get("http://fun-auto-test.framgia.vn/admin/articles");
			selectSearchMethod(articleSearch, titleID);
			Assert.assertEquals(checkIdNoResult(), true);
		}
		
		
		public boolean checkIdNoResult() {
			WebElement element = driver.findElement(By.xpath("//span[@class='label label-warning']"));
			return Constants.NO_ARTICLE.equals(element.getText()) ? true : false;
		}

		public boolean searchArticleTitle(String id){
			boolean isID = false;
			WebElement element = driver.findElement(By.xpath("//table//tbody"));
			List<WebElement> rows = element.findElements(By.tagName("tr"));
			System.out.println("size "+rows.size());
			for (WebElement row : rows) {
				WebElement td = row.findElements(By.tagName("td")).get(3);
				WebElement a = null;
				try {
					a = td.findElement(By.cssSelector("div > a"));
				} catch (Exception e) {
					
				}
				String str = "";
				if(a == null) {
					str = td.getText();
				} else {
					str = a.getText();
				}
				System.out.println("content ==="+str);
				if (str.toLowerCase().contains(id.toLowerCase()))
					isID = true;
			}
			return isID;
		}
		
	public boolean checkClientIdNoResult(String id) {
		WebElement element = driver.findElement(By.xpath("//span[@class='label label-warning']"));
		return Constants.NO_ARTICLE.equals(element.getText()) ? true : false;
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

		return (size == 3 && Constants.ARTICLE_ID.equals(articleID) && Constants.CLIENT_ID.equals(clientID)
				&& Constants.ARTICLE_TITLE.equals(articleTitle)) ? true : false;
	}

}
