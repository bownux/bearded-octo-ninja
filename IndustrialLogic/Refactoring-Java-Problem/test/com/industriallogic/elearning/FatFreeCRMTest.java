package com.industriallogic.elearning;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FatFreeCRMTest  {
	private WebElement tab;
	private WebElement create;
	protected WebElement panel;
	public WebDriver driver;
	public DriverExtensions xdriver;
	
	@Test
	public void accounts() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.get("http://il-ffcrm.herokuapp.com/");
		xdriver = new DriverExtensions(driver, 25);
		WebElement login = driver.findElement(By.id("new_authentication"));
		login.findElement(By.id("authentication_username")).sendKeys("admin");
		login.findElement(By.id("authentication_password")).sendKeys("admin");
		login.submit();
		WebElement link = driver.findElement(By.linkText("Accounts"));
		link.click();
		WebElement element = driver.findElement(By.cssSelector("span[id=create_account_title]"));
		assertNotNull(element);
		assertTrue(element.isDisplayed());
		tab = driver.findElement(By.id("main"));
		WebElement arrow = tab.findElement(By.id("create_account_arrow"));
		arrow.click();
		WebElement title = driver.findElement(By.id("create_account_title"));
		assertNotNull(title);
		assertTrue(title.isDisplayed());
		create = tab.findElement(By.id("create_account"));
		WebElement accountNameInputField = create.findElement(By
				.id("account_name"));
		accountNameInputField.sendKeys("Bob's Bearings and BassOmatics");
		WebElement button = create.findElement(By.className("buttonbar"));
		button.submit();
		WebElement searchBox = driver.findElement(By.id("query"));
		searchBox.sendKeys("Bob's Bearings and BassOmatics");
		WebElement link2 = xdriver.getElementOnceNotStale(By.partialLinkText("BassOmatics"));
		link2.getTagName();
		link2.click();
		driver.findElement(By.id("edit_account_title"));
		WebElement delete = driver.findElement(By.linkText("Delete?"));
		delete.click();
		WebElement confirm = driver.findElement(By.linkText("Yes"));
		confirm.click();
		WebElement search = driver.findElement(By.id("query"));
		search.sendKeys("Bob's Bearings and BassOmatics");
		WebElement empty = driver.findElement(By.id("empty"));
		xdriver.assertElementVisible(empty);
		assertEquals("Couldn't find any accounts matching " + "Bob's Bearings and BassOmatics"
				+ "; please try another query.", empty.getText());
		driver.close();
		driver.quit();
	}
}
