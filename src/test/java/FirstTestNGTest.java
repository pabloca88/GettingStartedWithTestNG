import app.UserManager;
import baseclasses.UnitTestBaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class FirstTestNGTest extends UnitTestBaseClass {

	UserManager um;

	@BeforeMethod
	public void customLocalSetupMethod(Method testMethod){
		String desc = testMethod.getAnnotation(Test.class).description();

		System.out.println("Starting test: "+testMethod.getName() + " with description: " + desc );

		um = new UserManager();
	}

	@Test(description = "Verify that addUser method returns true when successful")
	public void successfulAddUserReturnsTrue() {
		//Arrange -> Set things up / fetch resources / initialize necessary objects

		//Act -> Execute the methods we want to test
		boolean result = um.addUser("pablo@email.com");

		//Assert -> verify stuff
		Assert.assertTrue(result);
	}

	@Test(description = "Verify that getUser method retrieves the correct existing user")
	public void getUserReturnsExistingSavedUser(){
		//Arrange
		um.addUser("pablo@email.com");

		//Act
		String user = um.getUser("pablo@email.com");

		//Assert
		Assert.assertEquals(user,"pablo@email.com");
	}

	@Test(description = "Verify that getUser method returns null if the user does not exist")
	public void getNonExistingUserReturnsNull() {
		//Arrange

		//Act
		String user = um.getUser("pablo@email.com");

		//Assert
		Assert.assertNull(user,"The method should return null if it doesn't find a user");

	}

}
