Feature: Validating Weather Details by Compasring both in Web and through API

Scenario: Getting Weather Details from The NDTV WebSite

Given Go to the BaseUrl Page
When I select "WEATHER" Option from the TaskBar
Then I verify NDTV Weather is opened
When I entered city name as "Bengaluru" in the Pin your City Field
Then I verify city name "Bengaluru" is present in the map with temperature info
And I verify on clicking city name "Bengaluru" reveals weather details
And I store Weather details
And I close The Browser


@RunAPIToGetData
Scenario: Get Temperature Details Using City Name From API

Given Using Weather Api to get "Bengaluru" City Name
When User calls "ApiUsingCityCode" with "GET" http request
Then the API call got success with status code 200
And Get Temperature data from response

Scenario: Compare the Web Temperature and Api Temperature

Then Compare the Temperature
 