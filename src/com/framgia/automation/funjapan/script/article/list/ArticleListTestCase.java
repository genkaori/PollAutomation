package com.framgia.automation.funjapan.script.article.list;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.framgia.automation.funjapan.script.CommonTestCase;

public class ArticleListTestCase extends CommonTestCase {
	final static String ARTICLE_ID = "Article ID";
	final static String CLIENT_ID = "Client ID";
	final static String ARTICLE_TITLE = "Article Title";

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
