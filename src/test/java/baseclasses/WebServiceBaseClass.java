package baseclasses;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class WebServiceBaseClass {

	protected CloseableHttpClient client;
	protected CloseableHttpResponse response;

	@BeforeClass
	// instead of BeforeMetho to just make 1 call and improve test time doing request calls (1 call vs 3 calls)
	public void setUp() throws IOException {
		client = HttpClientBuilder.create().build();
		response = client.execute(new HttpGet("https://api.github.com/"));

		int actualStatusCode =  response.getStatusLine().getStatusCode();
		if (actualStatusCode != 200){
			throw new SkipException("Basic criteria failed,"+"was expecting code 200, but got: "+ actualStatusCode);
		}

	}

	@BeforeMethod // to show the call made after each method (3) vs 1 with the BeforeClass->  improves test time
	public void setupMethod() {
		System.out.println("Runs before each @Test");
	}

	@AfterClass
	public void cleanUp() throws IOException {
		client.close();
		response.close();
	}
}
