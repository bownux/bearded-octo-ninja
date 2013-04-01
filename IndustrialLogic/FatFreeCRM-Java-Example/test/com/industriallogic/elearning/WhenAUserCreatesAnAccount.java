package com.industriallogic.elearning;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
		fillOutCreateAccountPanel(accountName);
		
	}

	private void fillOutCreateAccountPanel(String accountName) {
		enterNewAccountName(accountName);
		selectBergbauBranchOption();
		submitForm();
		
	}

	private void submitForm() {
		// TODO Auto-generated method stub
		
	}

	private void enterNewAccountName(String accountName) {
		WebElement accountNameInputField = createAccountPanel.findElement(By.id("account_name"));
		accountNameInputField.sendKeys(accountName);
	}

	private void selectBergbauBranchOption() {
		WebElement branchToggle = createAccountPanel.findElement(By.partialLinkText(" Branche"));
		assertElementVisible(branchToggle);
		branchToggle.click();
		WebElement branchSelect = createAccountPanel.findElement(By.id("account_cf_branche"));
		assertElementVisible(branchSelect);
		branchSelect.click();
		
		WebElement bergBauOption = branchSelect.findElement(By.cssSelector("option[value=Bergbau]"));
		bergBauOption.click();
	}

	private WebElement openAccountCreationPane() {
		WebElement createAccountPaneOpeningToggle = accountsTab.findElement(By.id("create_account_arrow"));
		createAccountPaneOpeningToggle.click();
		assertElementPresent(By.id("create_account_title"));
		return accountsTab.findElement(By.id("create_account"));
	}
	
	
}
