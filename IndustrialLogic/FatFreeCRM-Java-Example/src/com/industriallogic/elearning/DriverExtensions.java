package com.industriallogic.elearning;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverExtensions {
	private WebDriver driver;
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

	public WebElement getElementOnceNotStale(By location) {
		WebElement element = null;
		long maxTime = timeoutInSeconds/1000; // timeout in milliseconds
		long waitTime = 500; // time to wait per loop
		long i = 0;
		do {
			try {
				element = driver.findElement(location);
				element.getTagName();
				
				break; //Success: element is no longer stale
			} catch (StaleElementReferenceException sere) {
				// Element is stale; need to wait briefly and retry findElement()
			}

			try {
				driver.wait(waitTime);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		} while (weHaventTimedOut(maxTime, waitTime, i));

		return element;
	}

	private boolean weHaventTimedOut(long maxTime, long waitTime, long i) {
		return (i += waitTime) < maxTime;
	}
}
