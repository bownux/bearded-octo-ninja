package com.industriallogic.elearning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WhenAUserSearchesForAnAccount extends BaseFFCRMTest {
	private WebElement hiddenSearchPanel;

	@Test
	public void theyCanFindTheOneTheySearchedFor() throws Exception {
		openTheHiddenQuickFindPanel();
		clickOnTheAccountsSearchLink();
		
		String searchTerm = "Acme 123";
		enterSearchText(searchTerm); 
		assertThatResultingPanelContains(searchTerm);
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
}
