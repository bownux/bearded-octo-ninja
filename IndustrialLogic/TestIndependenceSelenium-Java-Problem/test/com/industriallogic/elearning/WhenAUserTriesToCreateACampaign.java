package com.industriallogic.elearning;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WhenAUserTriesToCreateACampaign extends BaseCampaignTest {
	
	@Test
	public void theyCanCreateOne() throws Exception {
		login();
		createNewCampaign();
		confirmCampaignCreated();
	}

	private void createNewCampaign() {
		goToCampaignsTab();
		getCampaignTitle();
		openCreateCampaignPane();
		completeCreateCampaignForm();
	}

	private void openCreateCampaignPane() {
		clickCreateCampaignLink();
		assertCreateCampaignPaneIsOpen();
	}

	private void clickCreateCampaignLink() {
		createCampaignPane = driver.findElement(By.id("main"));
		WebElement createCampaignArrowLink = createCampaignPane.findElement(By
				.id("create_campaign_arrow"));
		createCampaignArrowLink.click();
	}

	private void assertCreateCampaignPaneIsOpen() {
		WebElement createCampaignPaneTitle = driver.findElement(By
				.id("create_campaign_title"));
		assertNotNull(createCampaignPaneTitle);
		assertTrue(createCampaignPaneTitle.isDisplayed());
	}

	private void completeCreateCampaignForm() {
		enterCampaignName();
		submitCreateCampaignForm();
	}

	private void enterCampaignName() {
		createCampaignLink = createCampaignPane.findElement(By
				.id("create_campaign"));
		WebElement campaignNameInputField = createCampaignLink.findElement(By
				.id("campaign_name"));
		campaignNameInputField.sendKeys(NEW_CAMPAIGN_NAME);
	}

	private void submitCreateCampaignForm() {
		WebElement button = createCampaignLink.findElement(By
				.className("buttonbar"));
		button.submit();
	}

	private void confirmCampaignCreated() {
		searchForNewCampaign();
		WebElement campaign = xdriver.getElementOnceNotStale(By
				.partialLinkText("Twenty Million"));
		campaign.getTagName();
		campaign.click();
	}
}
