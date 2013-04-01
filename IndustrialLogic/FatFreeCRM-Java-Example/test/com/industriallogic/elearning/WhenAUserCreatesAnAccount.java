package com.industriallogic.elearning;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WhenAUserCreatesAnAccount  extends BaseFFCRMTest {

	
	@Test
	public void theyCanFindTheOneTheySearchedFor() throws Exception {
		login(USERNAME, PASSWORD);
		clickTab("Accounts");
	}

	private void clickTab(String tabName) {
		WebElement tab = driver.findElement(By.linkText(tabName));
		tab.click();
		
	}
	
	
}
