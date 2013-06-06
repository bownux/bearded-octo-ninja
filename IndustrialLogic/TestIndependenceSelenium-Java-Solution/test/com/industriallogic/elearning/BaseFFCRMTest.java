package com.industriallogic.elearning;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseFFCRMTest {
	protected static final int TIMEOUT = 25;
	protected static final String FAT_FREE_CRM_URL = "http://il-ffcrm.herokuapp.com/";
	private static final String PASSWORD_FIELD_ID = "authentication_password";
	private static final String USERNAME_FIELD_ID = "authentication_username";
	private static final String PASSWORD = "admin";
	private static final String USERNAME = "admin";
	public WebDriver driver;
	public DriverExtensions xdriver;

	@Before
	public void openSelenium() {
		configureDriver();
		driveToFatFreeCrmLoginPage();
		xdriver = new DriverExtensions(driver, TIMEOUT);
	}

	void configureDriver() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
	}

	void driveToFatFreeCrmLoginPage() {
		driver.get(FAT_FREE_CRM_URL);
	}

	protected void login() {
		WebElement loginPage = driver.findElement(By.id("new_authentication"));
		loginPage.findElement(By.id(USERNAME_FIELD_ID)).sendKeys(USERNAME);
		loginPage.findElement(By.id(PASSWORD_FIELD_ID)).sendKeys(PASSWORD);
		loginPage.submit();
	}

	@After
	public void closeSelenium() {
		driver.close();
		driver.quit();
	}
}
