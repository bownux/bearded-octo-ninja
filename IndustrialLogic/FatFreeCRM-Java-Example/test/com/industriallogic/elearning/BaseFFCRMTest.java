package com.industriallogic.elearning;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseFFCRMTest {
	protected final int TIMEOUT_IN_SECONDS = 25;
	protected static final String PASSWORD = "admin";
	protected static final String USERNAME = "admin";
	
	static final String ACCOUNTS = "Accounts";
	static final String ACCOUNTS_TAB_TITLE_CSS = "span[id=create_account_title]";
	
	private static final String HOME_PAGE_URL = "http://il-ffcrm.herokuapp.com/";
	private static final String SAUCE_URL_WITH_CREDENTIALS = "http://industriallogic:f6300c89-a572-4bf0-80e4-8ff5db698f9a@ondemand.saucelabs.com:80/wd/hub";
	
	protected WebElement hiddenSearchPanel;
	public WebDriver driver;
	public DriverExtensions xdriver;

	@Before
	public void openBrowserOnSauce() throws Exception {
		this.driver = new RemoteWebDriver(sauceLabsURL(),configureSauceCababilities());
		configureDriver();
	}

	private void configureDriver() {
		driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		driver.get(HOME_PAGE_URL);
		xdriver = new DriverExtensions(driver, TIMEOUT_IN_SECONDS);
		login(USERNAME, PASSWORD);
	}
	

	private URL sauceLabsURL() throws Exception {
		return new URL(SAUCE_URL_WITH_CREDENTIALS);
	}

	private DesiredCapabilities configureSauceCababilities() {
		DesiredCapabilities capabillities = DesiredCapabilities.firefox();
        capabillities.setCapability("version", "12.0");
        capabillities.setCapability("platform", Platform.MAC);
		return capabillities;
	}

	public void openBrowser() throws Exception {
		this.driver = new FirefoxDriver();
		configureDriver();
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
		xdriver.assertElementPresent(By.cssSelector(ACCOUNTS_TAB_TITLE_CSS));
		return mainTabPanel();
	}

	private WebElement mainTabPanel() {
		return driver.findElement(By.id("main"));
	}
	
	protected void clickTab(String tabName) {
		WebElement tab = driver.findElement(By.linkText(tabName));
		tab.click();
	}
	
	protected void openTheHiddenQuickFindPanel() {
		hiddenSearchPanel = driver.findElement(By.id("jumpbox"));
		assertFalse(hiddenSearchPanel.isDisplayed());
		
		WebElement quickFindMenuLink = driver.findElement(By.id("jumper"));
		quickFindMenuLink.click();
		
		assertTrue(hiddenSearchPanel.isDisplayed());		
	}
	
	public void assertThatResultingPanelContains(String searchTerm) {
		WebElement panelTitle = driver.findElement(By.id("edit_account_title"));
		assertEquals(searchTerm, panelTitle.getText());
	}
	
	@After
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
}

