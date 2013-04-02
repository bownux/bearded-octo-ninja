package com.industriallogic.elearning;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
		accountsTab = goToAccountsTab();
		Thread.sleep(15000);
	}
	
	private void verifyAccountCreated(String accountName) {
		// TODO Auto-generated method stub
		
	}
	
	private void deleteAccount(String accountName) {
		// TODO Auto-generated method stub
		
	}

	private void verifyAccountDeleted(String accountName) {
		// TODO Auto-generated method stub
		
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
