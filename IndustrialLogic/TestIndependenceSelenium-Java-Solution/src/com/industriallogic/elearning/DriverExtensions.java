package com.industriallogic.elearning;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverExtensions {
	WebDriver driver;
	private int timeoutInSeconds;

	public DriverExtensions(WebDriver driver, int timeoutInSeconds) {
		this.driver = driver;
		this.timeoutInSeconds = timeoutInSeconds;
	}
	
	public WebElement assertElementPresent(By selector) {
		WebElement element = driver.findElement(selector);
		assertNotNull(element);
		assertTrue(element.isDisplayed());
		return element;
	}
	
	public void assertElementVisible(WebElement element) {
		assertTrue(element.isDisplayed());
	}

	public WebElement getElementOnceNotStale(By locator) throws Exception {
		WebElement element = null;
		long maxTimeInMillis = timeoutInSeconds/1000; 
		long loopWaitTimeInMillis = 500; 
		long elapsedTimeInMillis = 0;
		
		do {
			try {
				element = driver.findElement(locator);
				flowContinuesIfNotStale(element);
				
				break; 
			} catch (StaleElementReferenceException sere) {
				// Element is stale; need to wait briefly and retry findElement()
			}

			Thread.sleep(loopWaitTimeInMillis);

		} while (weHaventTimedOut(maxTimeInMillis, loopWaitTimeInMillis, elapsedTimeInMillis));

		return element;
	}

	private void flowContinuesIfNotStale(WebElement element) {
		element.getTagName();
	}

	private boolean weHaventTimedOut(long maxTime, long waitTime, long elapsedTimeInMillis) {
		return (elapsedTimeInMillis += waitTime) < maxTime;
	}
}
