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
		deleteCampaign();
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

	private void confirmCampaignCreated() throws Exception {
		searchForNewCampaign();
		WebElement campaign = xdriver.getElementOnceNotStale(By
				.partialLinkText("Twenty Million"));
		campaign.getTagName();
		campaign.click();
	}
	
	private void deleteCampaign() {
		clickDeleteLink();
		confirmDeletion();
		searchForNewCampaign();
		confirmDeletedCampaignNoLongerExists();
	}

	private void clickDeleteLink() {
		driver.findElement(By.id("edit_campaign_title"));
		WebElement delete = driver.findElement(By.linkText("Delete?"));
		delete.click();
	}

	private void confirmDeletion() {
		WebElement confirm = driver.findElement(By.linkText("Yes"));
		confirm.click();
	}

	private void confirmDeletedCampaignNoLongerExists() {
		WebElement empty = driver.findElement(By.id("empty"));
		xdriver.assertElementVisible(empty);
		assertEquals("Couldn't find any campaigns matching " + NEW_CAMPAIGN_NAME
				+ "; please try another query.", empty.getText());
	}
}
