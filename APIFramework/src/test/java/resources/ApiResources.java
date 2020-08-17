package resources;

public enum ApiResources {
	ApiUsingCityCode("/weather?q={cityName}&appid={app_id}"),
	ApiUsingCityAndStateCode("weather?q={cityName},{state_code}");
	private String resource;
	
	 ApiResources(String resource) {
		 this.resource = resource;
		
	}
	 
	 public String getResource()
	 {
		 return resource;
	 }
	
}
