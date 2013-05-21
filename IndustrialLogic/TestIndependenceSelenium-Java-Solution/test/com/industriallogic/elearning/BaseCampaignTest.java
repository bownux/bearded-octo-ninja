package com.industriallogic.elearning;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaseCampaignTest extends BaseFFCRMTest {
	protected static final String NEW_CAMPAIGN_NAME = "Twenty Million Dollar Club: We Can Do This, People";
	protected WebElement createCampaignPane;
	protected WebElement createCampaignLink;
	protected WebElement panel;

	protected void goToCampaignsTab() {
		WebElement CampaignsTab = driver.findElement(By.linkText("Campaigns"));
		CampaignsTab.click();
	}

	protected void getCampaignTitle() {
		WebElement createCampaignTitle = driver.findElement(By
				.cssSelector("span[id=create_campaign_title]"));
		assertNotNull(createCampaignTitle);
		assertTrue(createCampaignTitle.isDisplayed());
	}

	protected void searchForNewCampaign() {
		WebElement search = driver.findElement(By.id("query"));
		search.sendKeys(NEW_CAMPAIGN_NAME);
	}
}
