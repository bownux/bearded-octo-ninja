package com.industriallogic.elearning;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WhenCustomerSearchesForAPicture {
	private static final String SEARCH_TERM = "Stepping stones near Ogmore Castle";
	public WebDriver driver;
	private final int TIMEOUT_IN_SECONDS = 15;
	
	@Before
	public void openFirefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
	}

	@Test
	public void theyCanFindThePicture() {
		goToWikimediaCommonsPage();
		searchForSteppingStonesPicture();
		assertThatWeFoundTheRightPicture();
	}
	
	private void goToWikimediaCommonsPage() {
		driver.navigate().to("http://commons.wikimedia.org/wiki/Main_Page");
	}

	private void searchForSteppingStonesPicture() {
		WebElement searchBox = driver.findElement(By.id("searchInput"));
		searchBox.sendKeys(SEARCH_TERM);
		searchBox.submit();
	}
	
	private void assertThatWeFoundTheRightPicture() {
		assertNotNull(driver.findElement(By.partialLinkText(SEARCH_TERM)));
	}

	@After
	public void closeFirefox() {
		driver.quit();
	}
}
