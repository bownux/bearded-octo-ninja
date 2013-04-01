package com.industriallogic.elearning;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseFFCRMTest {

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

	protected void login(String userName, String password) {
		WebElement loginForm = driver.findElement(By.id("new_authentication"));
		loginForm.findElement(By.id("authentication_username")).sendKeys(userName);
		loginForm.findElement(By.id("authentication_password")).sendKeys(password);
		loginForm.submit();
	}

	@After
	public void stopEverything() {
		driver.quit();
	}

}
