package com.industriallogic.elearning;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseFFCRMTest {

	private static final String HOME_PAGE_URL = "http://demo.fatfreecrm.com/";
	private final int TIMEOUT_IN_SECONDS = 15;
	protected static final String PASSWORD = "password";
	protected static final String USERNAME = "seleniumpatterns";
	static final String ACCOUNTS = "Accounts";
	static final String ACCOUNTS_TAB_TITLE_CSS = "span[id=create_account_title]";
	
	
	public WebDriver driver;

	@Before
	public void openFirefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		driver.navigate().to(HOME_PAGE_URL);
		login(USERNAME, PASSWORD);
	}

	protected void login(String userName, String password) {
		WebElement loginForm = driver.findElement(By.id("new_authentication"));
		loginForm.findElement(By.id("authentication_username")).sendKeys(userName);
		loginForm.findElement(By.id("authentication_password")).sendKeys(password);
		loginForm.submit();
	}

	protected WebElement goToAccountsTab() {
		clickTab(ACCOUNTS);
		return verifyWeAreOnAccountsTab();
	}

	protected WebElement verifyWeAreOnAccountsTab() {
		assertElementPresent(By.cssSelector(ACCOUNTS_TAB_TITLE_CSS));
		return mainTabPanel();
	}

	private WebElement mainTabPanel() {
		return driver.findElement(By.id("main"));
	}

	protected WebElement assertElementPresent(By selector) {
		WebElement element = driver.findElement(selector);
		assertNotNull(element);
		assertTrue(element.isDisplayed());
		return element;
	}
	
	protected void assertElementVisible(WebElement element) {
		assertTrue(element.isDisplayed());
	}

	protected void clickTab(String tabName) {
		WebElement tab = driver.findElement(By.linkText(tabName));
		tab.click();
	}

	@After
	public void stopEverything() {
		driver.quit();
	}
}

