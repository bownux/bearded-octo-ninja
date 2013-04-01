package com.industriallogic.elearning;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WhenAUserCreatesAnAccount  extends BaseFFCRMTest {
	private WebElement accountsTab;
	private WebElement createAccountPanel;

	@Test
	public void theyCanVerifyThatTheyCreatedIt() throws Exception {
		accountsTab = goToAccountsTab();
		addAnAccount("Bob's Bearings and BassOmatics");
	}

	private void addAnAccount(String accountName) {
		createAccountPanel = openAccountCreationPane();
		fillOutCreateAccountPanel();
		
	}

	private void fillOutCreateAccountPanel() {
		// TODO Auto-generated method stub
		
	}

	private WebElement openAccountCreationPane() {
		WebElement createAccountPaneOpeningToggle = accountsTab.findElement(By.id("create_account_arrow"));
		createAccountPaneOpeningToggle.click();
		assertElementPresent(By.id("create_account_title"));
		return accountsTab.findElement(By.id("create_account"));
	}
	
	
}
