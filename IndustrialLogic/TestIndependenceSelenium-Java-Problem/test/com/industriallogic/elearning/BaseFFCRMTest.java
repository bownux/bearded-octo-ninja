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
	protected static final String PASSWORD = "authentication_password";
	protected static final String USERNAME = "authentication_username";
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
		loginPage.findElement(By.id(USERNAME)).sendKeys("admin");
		loginPage.findElement(By.id(PASSWORD)).sendKeys("admin");
		loginPage.submit();
	}

	@After
	public void closeSelenium() {
		driver.close();
		driver.quit();
	}

}
