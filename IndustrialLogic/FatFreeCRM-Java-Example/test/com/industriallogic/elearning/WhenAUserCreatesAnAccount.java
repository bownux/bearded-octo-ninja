package com.industriallogic.elearning;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WhenAUserCreatesAnAccount  extends BaseFFCRMTest {
	private static final String ACCOUNT_NAME = "Bob's Bearings and BassOmatics";
	private WebElement accountsTab;
	private WebElement createAccountPanel;

	@Test
	public void theyCanVerifyThatTheyCreatedIt() throws Exception {
		accountsTab = goToAccountsTab();
		
		createAnAccount(ACCOUNT_NAME);
		verifyAccountCreated(ACCOUNT_NAME);
		
		deleteAccount(ACCOUNT_NAME);
		verifyAccountDeleted(ACCOUNT_NAME);
	}

	private void createAnAccount(String accountName) throws Exception {
		createAccountPanel = openAccountCreationPane();
		fillOutCreateAccountPanel(accountName);
	}

	private WebElement openAccountCreationPane() {
		WebElement createAccountPaneOpeningToggle = accountsTab.findElement(By.id("create_account_arrow"));
		createAccountPaneOpeningToggle.click();
		assertElementPresent(By.id("create_account_title"));
		return accountsTab.findElement(By.id("create_account"));
	}
	
	private void fillOutCreateAccountPanel(String accountName) throws Exception {
		enterNewAccountName(accountName);
		submitForm();
	}
	
	private void enterNewAccountName(String accountName) {
		WebElement accountNameInputField = createAccountPanel.findElement(By.id("account_name"));
		accountNameInputField.sendKeys(accountName);
	}

	private void submitForm() throws Exception {
		WebElement submit = createAccountPanel.findElement(By.className("buttonbar"));
		submit.submit();
	}
	
	private void verifyAccountCreated(String accountName) {
		searchForAccount();
		WebElement accountLink = driver.findElement(By.partialLinkText("BassOmatics"));
		accountLink.click();
		driver.findElement(By.id("edit_account_title"));
	}

	private void searchForAccount() {
		WebElement searchBox = driver.findElement(By.id("query"));
		searchBox.sendKeys(ACCOUNT_NAME);
	}
	
	private void deleteAccount(String accountName) {
		WebElement deleteAccountLink = driver.findElement(By.linkText("Delete?"));
		deleteAccountLink.click();
		WebElement confirmation = driver.findElement(By.linkText("Yes"));
		confirmation.click();
	}

	private void verifyAccountDeleted(String accountName) {
		searchForAccount();
		WebElement noResultsFound = driver.findElement(By.id("empty"));
		assertElementVisible(noResultsFound);
		assertEquals("Couldn't find any accounts matching " + ACCOUNT_NAME + "; please try another query.",noResultsFound.getText());
	}
}
