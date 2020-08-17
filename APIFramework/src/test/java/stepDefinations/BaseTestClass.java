package stepDefinations;

import org.openqa.selenium.WebDriver;

import bdd.APIFramework.browser.BrowserSelection;
import bdd.APIFramework.utils.WebDataConstants;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseTestClass {
	

	 static BrowserSelection bs ;
	 protected static WebDriver driver;
	 
	 public static WebDataConstants constants;

	//@Before()
	public static void testStartUp() throws InterruptedException {

		bs = new BrowserSelection();
		driver = bs.openBrowser();
		constants = new WebDataConstants();
		
	}
	
	//@After
	public static void testCleanUp() throws InterruptedException {
		Thread.sleep(2000);
		bs.closeBrowser(driver);
		bs.quitBrowser(driver);
	
	}

}