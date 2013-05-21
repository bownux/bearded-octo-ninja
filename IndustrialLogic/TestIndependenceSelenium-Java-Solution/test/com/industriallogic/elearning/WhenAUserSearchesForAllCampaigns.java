package com.industriallogic.elearning;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WhenAUserSearchesForAllCampaigns extends BaseCampaignTest {
	
	@Test
	public void theyRetrieveTheExpectedCampaigns() throws Exception {
		login();
		goToCampaignsTab();
		verifyExpectedNumberOfCampaignsExists();
	}

	private void verifyExpectedNumberOfCampaignsExists() {
		List<WebElement> campaignListElements = driver.findElements(By.cssSelector("li[id*=campaign_]"));
		int actualNumberOfCampaigns = campaignListElements.size();
		
		assertEquals(1, actualNumberOfCampaigns);
	}
}
