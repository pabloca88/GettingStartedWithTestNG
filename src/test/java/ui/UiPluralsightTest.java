package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UiPluralsightTest {
	ChromeDriver driver;

	@BeforeMethod
	public void startUpBrowser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\pablo.calvano\\Documents\\Automation\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://pluralsight.com");

		doBasicCheck();
	}



	@Test(description = "No exception thrown by findElement considered a sucessful test")
	public void checkLoginButtonIsPresent(){
		driver.findElement(By.xpath(".//a[@data-aa-title='Sign-in']"));
	}

	@AfterMethod(alwaysRun = true)//force cleanup
	public void closeBrowser(){
		System.out.println("Closing down the browser");
		driver.close();
	}

	private void doBasicCheck() {
		driver.findElement(By.className("ps-nav--logo"));
			//driver.findElement(By.className("ps-nav-logo"));
	}


}
