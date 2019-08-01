package com.avinc.ReadingConsoleLog;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReadingBrowserConsoleLog {
	
	public WebDriver driver;
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void setUpDriver() {
		new DesiredCapabilities();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		LoggingPreferences logPreference = new LoggingPreferences();
		logPreference.enable(LogType.BROWSER, Level.ALL);
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPreference);
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resource\\driver\\chromedriver.exe");

		driver = new ChromeDriver(capabilities);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void getTheLog() {
		driver.get("http://www.newtours.demoaut.com/");  //supply your website link
		
		LogEntries entries = driver.manage().logs().get(LogType.BROWSER);
		for(LogEntry entry : entries) {
			System.out.println(entry.getTimestamp()+ " : " + entry.getLevel()+" : "+ entry.getMessage());
		}
	}

}
