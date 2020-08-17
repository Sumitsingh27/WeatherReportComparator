package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Properties;

import bdd.APIFramework.utils.WebDataConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.ApiResources;
import resources.Utils;

public class StepDefination extends Utils {

	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;

	
	@Given("Using Weather Api to get {string} City Name")
	public void cityWeather(String cityName) throws IOException{
		res = given().spec(requestSpecification()).pathParam("cityName", cityName);
	}
	
	@Given("Using Weather Api to get {string} City Name and {string} City Code")
	public void cityWeatherWithcityNameAndCode(String cityName, String cityCode) throws IOException{
		res = given().spec(requestSpecification()).pathParam("cityName", cityName).pathParam("state_code", cityCode);
	}
	

	@When("User calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String resource, String methods) {
		
		ApiResources getApiResource = ApiResources.valueOf(resource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(methods.equalsIgnoreCase("POST"))
		response = res.when().post(getApiResource.getResource());
		else if(methods.equalsIgnoreCase("GET")){
		
			response = res.when().get(getApiResource.getResource());
		}
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String ExpectedValue) {
		
		assertEquals(getJsonPath(response, key), ExpectedValue);
	}
	
	
	@Then("Get Temperature data from response")
	public void verify_placeID_created_maps_to_using() throws IOException {
		
		String key = getJsonPath(response, "main.temp");
		WebDataConstants.TEMP_FROM_API = key;
	}
	
	@Then("Compare the Temperature")
	public void CompareWeatherDetails() {
		
		Properties prop = new Properties();
		FileInputStream fis = null;
		File file;
		double difference;
		float range = 0;
		
		double tempFromApi= (Double.parseDouble(WebDataConstants.TEMP_FROM_API) - 273.15);
		
		DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.CEILING);
        
        if(Float.parseFloat(df.format(tempFromApi)) > Float.parseFloat(WebDataConstants.TEMP_DERGREE) )
        	difference  = Float.parseFloat(df.format(tempFromApi)) - Float.parseFloat(WebDataConstants.TEMP_DERGREE);
         else 
            difference = Float.parseFloat(WebDataConstants.TEMP_DERGREE) - Float.parseFloat(df.format(tempFromApi));
        
        try {
			file = new File("Properties/TemperatureCompare.properties");
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			 range = Float.parseFloat(prop.getProperty("RangeisBetween"));
		} 
		catch ( Exception e) {
			e.printStackTrace();
		}
		

		if(difference <= range){
			System.out.println(prop.getProperty("Success"));
		}		
		else {
			System.out.println(prop.getProperty("MatcherException"));
		}
		
		assertTrue("Difference is Greater then Expected", difference <= range);
		

	}

	private void assertTrue(String string, boolean b) {
		// TODO Auto-generated method stub
		
	}

}
