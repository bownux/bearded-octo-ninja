package com.industriallogic.elearning;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseFFCRMTest {

	protected final int TIMEOUT_IN_SECONDS = 15;
	private static final String HOME_PAGE_URL = "http://il-ffcrm.herokuapp.com/";
	protected static final String PASSWORD = "admin";
	protected static final String USERNAME = "admin";
	
	static final String ACCOUNTS = "Accounts";
	static final String ACCOUNTS_TAB_TITLE_CSS = "span[id=create_account_title]";
	
	protected WebElement hiddenSearchPanel;
	public WebDriver driver;

	public void openFirefoxOnSauce() throws Exception {
        DesiredCapabilities capabillities = DesiredCapabilities.firefox();
        capabillities.setCapability("version", "12.0");
        capabillities.setCapability("platform", Platform.MAC);
        this.driver = new RemoteWebDriver(
					  new URL("http://patrickwilsonwelsh:dec2c72a-6dc5-4f38-a5ee-56750db1c22c@ondemand.saucelabs.com:80/wd/hub"),
					  capabillities);
		driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		driver.get(HOME_PAGE_URL);
		login(USERNAME, PASSWORD);
	}

	@Before
	public void openFireFox() throws Exception {
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		driver.get(HOME_PAGE_URL);
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
		driver.close();
		driver.quit();
	}

	protected void openTheHiddenQuickFindPanel() {
		hiddenSearchPanel = driver.findElement(By.id("jumpbox"));
		assertFalse(hiddenSearchPanel.isDisplayed());
	
		WebElement quickFindMenuLink = driver.findElement(By.id("jumper"));
		quickFindMenuLink.click();
		
		assertTrue(hiddenSearchPanel.isDisplayed());		
	}

	protected void assertThatResultingPanelContains(String searchTerm) {
		WebElement panelTitle = driver.findElement(By.id("edit_account_title"));
		assertEquals(searchTerm, panelTitle.getText());
	}
	
	protected WebElement getElementOnceNotStale(By location) {
		WebElement element = null;
		long maxTime = TIMEOUT_IN_SECONDS/60; // time out in milliseconds
		long waitTime = 500; // time to wait each loop
		long i = 0;
		do {
			try {
				element = driver.findElement(location);
				element.getTagName();
				
				break; //Success: element is no longer stale
			} catch (StaleElementReferenceException sere) {
				// Element is stale; need to wait briefly and retry findElement()
			}

			try {
				driver.wait(waitTime);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		} while (weHaventTimedOut(maxTime, waitTime, i));

		return element;
	}

	private boolean weHaventTimedOut(long maxTime, long waitTime, long i) {
		return (i += waitTime) < maxTime;
	}

}

