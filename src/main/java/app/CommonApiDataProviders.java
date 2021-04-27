package app;

import org.testng.annotations.DataProvider;

public class CommonApiDataProviders {
	@DataProvider
	public static Object[][] endpointRequiringAuthentication(){
		return new Object[][] {
			{"user"},
			{"user/followers"},
			{"notifications"}
		};
	}
}
