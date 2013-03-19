package com.industriallogic.elearning;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseFFCRMTestCase {
	private static final String HOME_PAGE_URL = "http://demo.fatfreecrm.com/";
	private final int TIMEOUT_IN_SECONDS = 15;
	protected static final String PASSWORD = "password";
	protected static final String USERNAME = "seleniumpatterns";
	public WebDriver driver;

	@Before
	public void openFirefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		driver.navigate().to(HOME_PAGE_URL);
	}

	@After
	public void stopEverything() {
		driver.quit();
	}

	protected void login(String userName, String password) {
		WebElement loginForm = driver.findElement(By.id("new_authentication"));
		loginForm.findElement(By.id("authentication_username")).sendKeys(userName);
		loginForm.findElement(By.id("authentication_password")).sendKeys(password);
		loginForm.submit();
	}

	protected WebElement assertWeAreOnTab(String tabName) {
		WebElement tabElement = driver.findElement(By.cssSelector("a[class=active]"));
		assertEquals(tabName, tabElement.getText());
		return tabElement;
	}

}
