package com.industriallogic.elearning;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WhenAUserTriesToCreateAnAccount {
	private static final int TIMEOUT = 25;
	private static final String FAT_FREE_CRM_URL = "http://il-ffcrm.herokuapp.com/";
	private static final String NEW_ACCOUNT_NAME = "Bob's Bearings and BassOmatics";
	private static final String PARTIAL_ACCOUNT_NAME = "BassOmatics";
	private static final String PASSWORD_FIELD_ID = "authentication_password";
	private static final String USERNAME_FIELD_ID = "authentication_username";
	private static final String PASSWORD = "admin";
	private static final String USERNAME = "admin";

	private WebElement createAccountPane;
	private WebElement createAccountLink;
	protected WebElement panel;
	public WebDriver driver;
	public DriverExtensions xdriver;

	@Before
	public void openSelenium() {
		configureDriver();
		driveToFatFreeCrmLoginPage();
		xdriver = new DriverExtensions(driver, TIMEOUT);
	}

	@Test
	public void theyCanCreateOne() throws Exception {
		login();
		createNewAccount();
		confirmAccountCreated();
		deleteAccount();
	}

	@After
	public void closeSelenium() {
		driver.close();
		driver.quit();
	}

	private void configureDriver() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
	}

	private void driveToFatFreeCrmLoginPage() {
		driver.get(FAT_FREE_CRM_URL);
	}

	private void login() {
		WebElement loginPage = driver.findElement(By.id("new_authentication"));
		loginPage.findElement(By.id(USERNAME_FIELD_ID)).sendKeys(USERNAME);
		loginPage.findElement(By.id(PASSWORD_FIELD_ID)).sendKeys(PASSWORD);
		loginPage.submit();
	}

	private void createNewAccount() {
		goToAccountsTab();
		getAccountTitle();
		openCreateAccountPane();
		completeCreateAccountForm();
	}

	private void goToAccountsTab() {
		WebElement accountsTab = driver.findElement(By.linkText("Accounts"));
		accountsTab.click();
	}

	private void getAccountTitle() {
		WebElement createAccountTitle = driver.findElement(By
				.cssSelector("span[id=create_account_title]"));
		assertNotNull(createAccountTitle);
		assertTrue(createAccountTitle.isDisplayed());
	}

	private void openCreateAccountPane() {
		clickCreateAccountLink();
		assertCreateAccountPaneIsOpen();
	}

	private void clickCreateAccountLink() {
		createAccountPane = driver.findElement(By.id("main"));
		WebElement createAccountArrowLink = createAccountPane.findElement(By
				.id("create_account_arrow"));
		createAccountArrowLink.click();
	}

	private void assertCreateAccountPaneIsOpen() {
		WebElement createAccountPaneTitle = driver.findElement(By
				.id("create_account_title"));
		assertNotNull(createAccountPaneTitle);
		assertTrue(createAccountPaneTitle.isDisplayed());
	}

	private void completeCreateAccountForm() {
		enterAccountName();
		submitCreateAccountForm();
	}

	private void enterAccountName() {
		createAccountLink = createAccountPane.findElement(By
				.id("create_account"));
		WebElement accountNameInputField = createAccountLink.findElement(By
				.id("account_name"));
		accountNameInputField.sendKeys(NEW_ACCOUNT_NAME);
	}

	private void submitCreateAccountForm() {
		WebElement button = createAccountLink.findElement(By
				.className("buttonbar"));
		button.submit();
	}

	private void confirmAccountCreated() {
		searchForAccount();
		WebElement link2 = xdriver.getElementOnceNotStale(By
				.partialLinkText(PARTIAL_ACCOUNT_NAME));
		link2.getTagName();
		link2.click();
	}

	private void deleteAccount() {
		clickDeleteLink();
		confirmDeletion();
		searchForAccount();
		confirmDeletedAccountNoLongerExists();
	}

	private void clickDeleteLink() {
		driver.findElement(By.id("edit_account_title"));
		WebElement delete = driver.findElement(By.linkText("Delete?"));
		delete.click();
	}

	private void confirmDeletion() {
		WebElement confirm = driver.findElement(By.linkText("Yes"));
		confirm.click();
	}

	private void searchForAccount() {
		WebElement search = driver.findElement(By.id("query"));
		search.sendKeys(NEW_ACCOUNT_NAME);
	}

	private void confirmDeletedAccountNoLongerExists() {
		WebElement empty = driver.findElement(By.id("empty"));
		xdriver.assertElementVisible(empty);
		assertEquals("Couldn't find any accounts matching " + NEW_ACCOUNT_NAME
				+ "; please try another query.", empty.getText());
	}
}
