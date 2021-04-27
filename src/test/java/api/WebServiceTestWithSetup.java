package api;

import baseclasses.WebServiceBaseClass;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

public class WebServiceTestWithSetup extends WebServiceBaseClass {


	@Test
	public void statusIs200() throws IOException {
		Assert.assertEquals(
			response.getStatusLine().getStatusCode(),
			200); //this test will fail because we will get 200 OK
	}

	@Test
	public void typeIsJson() throws IOException {
		Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/json");
	}

	@Test
	public void charSetIsUtf8() throws IOException {
		Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");
	}

} // Is an alternative for Soft Asserts, here we apply TestNG setup on multiple smaller and focused Test

