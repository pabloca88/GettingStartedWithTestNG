package api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

public class WebServiceTestofAssert {
	@Test
		public void hardAssertStopInmediately() throws IOException {
		//Arrange
		CloseableHttpClient client = HttpClientBuilder.create().build();

		//Act : execute an http get to the base endpoint of github API, and save the response that contains all the headers
		CloseableHttpResponse response = client.execute(new HttpGet("https://api.github.com"));

		//Assert
		System.out.println("First assert");
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404); //this test will fail becasue we will get 200 OK

		System.out.println("Second assert");
		Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/xml");

		System.out.println("Third assert");
		Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");

		client.close();
		response.close();
	}

	@Test
	//dont use this most of the time? Why? Proper good test should fail for ONE reason only (Single responsability principle applied to test)
	public void softAssertContinuesToTheEnd() throws IOException {
		//Arrange
		CloseableHttpClient client = HttpClientBuilder.create().build();
		SoftAssert sa = new SoftAssert();

		//Act
		CloseableHttpResponse response = client.execute(new HttpGet("https://api.github.com"));

		//Assert
		System.out.println("First assert");
		sa.assertEquals(response.getStatusLine().getStatusCode(), 404);

		System.out.println("Second assert");
		sa.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/xml");

		System.out.println("Third assert");
		sa.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");

		client.close();
		response.close();

		sa.assertAll();


	}
}
