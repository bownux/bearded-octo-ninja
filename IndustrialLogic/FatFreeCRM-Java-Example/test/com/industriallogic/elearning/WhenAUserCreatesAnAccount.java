package com.industriallogic.elearning;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

	private void fillOutCreateAccountPanel(String accountName) throws Exception {
		enterNewAccountName(accountName);
		selectBergbauBranchOption();
		submitForm();
	}

	private void submitForm() throws Exception {
		WebElement submit = createAccountPanel.findElement(By.className("buttonbar"));
		submit.submit();
	}
	
	private void verifyAccountCreated(String accountName) {
		searchForAccount();
		WebElement accountLink = driver.findElement(By.linkText(ACCOUNT_NAME));
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
	

	private void enterNewAccountName(String accountName) {
		WebElement accountNameInputField = createAccountPanel.findElement(By.id("account_name"));
		accountNameInputField.sendKeys(accountName);
	}

	private void selectBergbauBranchOption() throws Exception {
		WebElement branchToggle = createAccountPanel.findElement(By.partialLinkText(" Branche"));
		assertElementVisible(branchToggle);
		branchToggle.click();
		
		WebElement branchSelect = createAccountPanel.findElement(By.id("account_cf_branche"));
		assertElementVisible(branchSelect);
		branchSelect.click();
		
		WebElement bergBauOption = branchSelect.findElement(By.cssSelector("option[value=Bergbau]"));
		assertElementVisible(bergBauOption);
		branchSelect.click();
		branchSelect.sendKeys("b");
		branchSelect.sendKeys(Keys.ENTER);
	}

	private WebElement openAccountCreationPane() {
		WebElement createAccountPaneOpeningToggle = accountsTab.findElement(By.id("create_account_arrow"));
		createAccountPaneOpeningToggle.click();
		assertElementPresent(By.id("create_account_title"));
		return accountsTab.findElement(By.id("create_account"));
	}
	
	
}
