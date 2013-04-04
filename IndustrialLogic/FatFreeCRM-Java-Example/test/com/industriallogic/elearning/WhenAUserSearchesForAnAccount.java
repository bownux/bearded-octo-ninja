package com.industriallogic.elearning;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WhenAUserSearchesForAnAccount extends BaseFFCRMTest {


	@Test
	public void theyCanFindTheOneTheySearchedFor() throws Exception {
		openTheHiddenQuickFindPanel();
		clickOnTheAccountsSearchLink();
		
		String searchTerm = "Acme 123";
		enterSearchText(searchTerm); 
		assertThatResultingPanelContains(searchTerm);
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
}
