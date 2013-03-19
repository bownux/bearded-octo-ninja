package com.industriallogic.elearning.ffcrm.dashboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WhenAUserSearchesForAnAccount {
	private static final String HOME_PAGE_URL = "http://demo.fatfreecrm.com/";
	private final int TIMEOUT_IN_SECONDS = 15;
	protected static final String PASSWORD = "password";
	protected static final String USERNAME = "seleniumpatterns";
	public WebDriver driver;
	private WebElement hiddenSearchPanel;

	@Before
	public void openFirefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		driver.navigate().to(HOME_PAGE_URL);
	}

	@Test
	public void theyCanFindTheOneTheySearchedFor() throws Exception {
		login(USERNAME, PASSWORD);
		openTheHiddenQuickFindPanel();
		clickOnTheAccountsSearchLink();
		
		String searchTerm = "Acme 123";
		enterSearchText(searchTerm); 
		assertThatResultingPanelContains(searchTerm);
	}
	
	private void login(String userName, String password) {
		WebElement loginForm = driver.findElement(By.id("new_authentication"));
		loginForm.findElement(By.id("authentication_username")).sendKeys(userName);
		loginForm.findElement(By.id("authentication_password")).sendKeys(password);
		loginForm.submit();
	}

	private void openTheHiddenQuickFindPanel() {
		hiddenSearchPanel = driver.findElement(By.id("jumpbox"));
		assertFalse(hiddenSearchPanel.isDisplayed());

		WebElement quickFindMenuLink = driver.findElement(By.id("jumper"));
		quickFindMenuLink.click();
		
		assertTrue(hiddenSearchPanel.isDisplayed());		
	}
	
	private void clickOnTheAccountsSearchLink() {
		WebElement accountLink = driver.findElement(By.linkText("Accounts"));
		accountLink.click();
	}

	private void enterSearchText(String searchTerm) throws Exception {
		WebElement searchBox = driver.findElement(By.id("auto_complete_query"));
		searchBox.sendKeys(searchTerm);
		
		WebElement autoCompleteHighlight = driver.findElement(
				By.cssSelector("div[id=auto_complete_dropdown] li[class=selected] strong[class=highlight]"));
		autoCompleteHighlight.click();
	}
	
	private void assertThatResultingPanelContains(String searchTerm) {
		WebElement acmeAccountPanelTitle = driver.findElement(By.id("edit_account_title"));
		assertEquals(searchTerm, acmeAccountPanelTitle.getText());
	}

	@After
	public void stopEverything() {
		driver.quit();
	}
}
