package stepDefinations;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import bdd.APIFramework.browser.BrowserSelection;
import bdd.APIFramework.utils.Actions;
import bdd.APIFramework.utils.WebDataConstants;
import bdd.APIFramework.utils.WebElementsCollector;
import groovyjarjarantlr4.v4.codegen.model.Action;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class WebStepDefination extends BrowserSelection {

	public static final String file = "Properties/WebStepDefination.properties";
	static Logger log = Logger.getLogger(WebStepDefination.class.getName());

	
	
	@Given("Go to the BaseUrl Page")
	public void go_to_the_BaseUrl_Page() throws InterruptedException {
		BaseTestClass.testStartUp();
	   log.info("Web Browser Launched");
	}
	
	@Then("I close The Browser")
	public void browserClose() throws InterruptedException {
		BaseTestClass.testCleanUp();
	   log.info("Web Browser Closed");
	}
	
	@When("I select {string} Option from the TaskBar")
	public void i_select_Option_from_the_TaskBar(String option) {
		WebElement extendTaskBar = WebElementsCollector.getWebElement(driver, file, "extendTaskBar");
		WebElement clickOnOptions = WebElementsCollector.getWebElement(driver, "xpath$//a[text()='"+option+"']");
		Actions.waitForElementToBeVisible(extendTaskBar);
		Actions.clickOnElement(extendTaskBar);
		log.debug("Click extendTaskBar Button Successful");
		Actions.waitForElementToBeClickable(clickOnOptions);
		Actions.clickOnElement(clickOnOptions);
		log.debug("Click "+option+" Button Successful");
	}
	
	@Then("I verify NDTV Weather is opened")
	public void i_verify_NDTV_Weather() {
		WebElement ndtvWeatherIcon = WebElementsCollector.getWebElement(driver, file, "ndtvWeatherIcon");
		Actions.waitForElementToBeVisible(ndtvWeatherIcon);
		Actions.isElementDisplayed(ndtvWeatherIcon);
		log.debug("NDTV Weather Icon is displayed");
	
	}
	
	@When("I entered city name as {string} in the Pin your City Field")
	public void enterinfCityName(String cityName) {
		WebElement pinYourCityField = WebElementsCollector.getWebElement(driver, file, "pinYourCityField");
		WebElement cityNamePresent = WebElementsCollector.getWebElement(driver, "xpath$//div[@class='cityText' and text()='"+cityName+"']");
		WebElement selectFromTheDropDown = WebElementsCollector.getWebElement(driver, "xpath$//*[@id='messages']/div/label[@for='"+cityName+"']");
		Actions.waitForElementToBeVisible(pinYourCityField);
		Actions.sendKeysToElement(pinYourCityField, "");
		Actions.sendKeysToElement(pinYourCityField, cityName);
		//Actions.sendKeysToElement(pinYourCityField, Keys.TAB);
		Actions.waitForElementToBeVisible(selectFromTheDropDown);
		if(!Actions.isElementDisplayed(cityNamePresent))
		Actions.clickOnElement(selectFromTheDropDown);		
		log.debug("Enter CityName as "+cityName+" in the pin your city field");
	
	}
	
	@Then("I verify city name {string} is present in the map with temperature info")
	public void verifyCityNameWithTempInfo(String cityName) {
		WebElement cityNamePresent = WebElementsCollector.getWebElement(driver, "xpath$//div[@class='cityText' and text()='"+cityName+"']");
		WebElement cityNameTemperature = WebElementsCollector.getWebElement(driver, "xpath$//div[@class='cityText' and text()='"+cityName+"']/../*[2]/*");
		
		Assert.assertTrue(Actions.isElementDisplayed(cityNamePresent));
		Assert.assertTrue(Actions.isElementDisplayed(cityNameTemperature));		 
	}
	
	@Then("I verify on clicking city name {string} reveals weather details")
	public void verifyOnClickWeatherDetails(String cityName) {
		WebElement cityNamePresent = WebElementsCollector.getWebElement(driver, "xpath$//div[@class='cityText' and text()='"+cityName+"']/..");
		
		Actions.waitForElementToBeVisible(cityNamePresent);
		Actions.clickOnElement(cityNamePresent);
		WebElement weatherDetailsPopUp = WebElementsCollector.getWebElement(driver, file, "weatherDetailsPopUp");
		Actions.waitForElementToBeClickable(weatherDetailsPopUp);
		Actions.isElementDisplayed(weatherDetailsPopUp);
		
		
	}
	
	@Then("I store Weather details")
	public void storeWeatherDetails() {
		WebElement weatherDetailsCondition = WebElementsCollector.getWebElement(driver, file, "weatherDetailsCondition");
		WebElement weatherDetailsWind = WebElementsCollector.getWebElement(driver, file, "weatherDetailsWind");
		WebElement weatherDetailsHumidity = WebElementsCollector.getWebElement(driver, file, "weatherDetailsHumidity");
		WebElement weatherDetailsTempDegree = WebElementsCollector.getWebElement(driver, file, "weatherDetailsTempDegree");
		WebElement weatherDetailsTempFahrenheit = WebElementsCollector.getWebElement(driver, file, "weatherDetailsTempFahrenheit");
		
		String condition[] = Actions.getElementText(weatherDetailsCondition).split(":");
		WebDataConstants.CONDITON = condition[1].trim() ;
		System.out.println(WebDataConstants.CONDITON);
		
		String wind[] = Actions.getElementText(weatherDetailsWind).split(":");
		WebDataConstants.WIND = wind[1].trim() ;
		System.out.println(WebDataConstants.WIND);

		String humidity[] = Actions.getElementText(weatherDetailsHumidity).split(":");
		WebDataConstants.HUMIDITY = humidity[1].trim() ;
		System.out.println(WebDataConstants.HUMIDITY);
		
		String tempDegree[] = Actions.getElementText(weatherDetailsTempDegree).split(":");
		WebDataConstants.TEMP_DERGREE = tempDegree[1].trim() ;
		System.out.println(WebDataConstants.TEMP_DERGREE);
		
		String tempFahre[] = Actions.getElementText(weatherDetailsTempFahrenheit).split(":");
		WebDataConstants.TEMP_FAHRENHEIT = tempFahre[1].trim() ;
		System.out.println(WebDataConstants.TEMP_FAHRENHEIT);
		
	}
	
}
