package com.industriallogic.elearning.ffcrm.dashboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.industriallogic.elearning.BaseFFCRMTestCase;

public class WhenWeAreOnDashboardTab extends BaseFFCRMTestCase {
	
	@Test
	public void weCanSeeOurCurrentTasks() {
		login(USERNAME, PASSWORD);
		assertWeAreOnTab("Dashboard");
		
		String[] taskNames = new String[] {
				"Email Filter and Bearing", 
				"Contact re Potential Premium Vendor"};
		
		verifyWeCanSeeOurCurrentTasks(taskNames);
		assertThatQuickLinksBoxIsInvisible();
	}
	
	
	

	private void verifyWeCanSeeOurCurrentTasks(String[] tasks) {
		WebElement tasksList =driver.findElement(By.cssSelector("ul[class=list][id=tasks]"));
		List<WebElement> taskElements = tasksList.findElements(By.cssSelector("li.highlight.task"));
		
		assertWeCanSeeTheExpectedNumberOfTasks(tasks, taskElements);
		assertThatAllELementsCanBeFoundByPartialTextMatch(tasks);

	}

	private void assertThatQuickLinksBoxIsInvisible() {
		WebElement jumpbox = driver.findElement(By.id("jumpbox"));
		assertFalse(jumpbox.isDisplayed());
	}




	private void assertThatAllELementsCanBeFoundByPartialTextMatch(String[] tasks) {
		for (String taskName : tasks) {
			assertNotNull(driver.findElement(By.partialLinkText(taskName)));
		}
	}

	private void assertWeCanSeeTheExpectedNumberOfTasks(String[] tasks,
			List<WebElement> taskElements) {
		assertEquals(tasks.length, taskElements.size());
	}





}
