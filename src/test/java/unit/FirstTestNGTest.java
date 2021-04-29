package unit;

import app.DuplicateUserException;
import app.UserManager;
import baseclasses.UnitTestBaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static app.TestPriority.HIGH;

//@Test(dependsOnGroups = "sanity") Group dependency at class level or at method level
public class FirstTestNGTest extends UnitTestBaseClass {

	UserManager um;

	@Test(groups = "sanity")
	public void sanityStorageGetsCreatedAndisEmpty(){
			UserManager um = new UserManager();
			Assert.assertTrue(um.getAllUsers().isEmpty());
		}

	@BeforeMethod
	public void customLocalSetupMethod(Method testMethod){
		String desc = testMethod.getAnnotation(Test.class).description();

		System.out.println("Starting test: " +testMethod.getName() +
			" with description: " + desc);
		// Arrange
		um = new UserManager();
	}

	@Test(dependsOnGroups = "sanity", description = "Verify that addUser method returns true when successful")
	public void successfulAddUserReturnsTrue() throws DuplicateUserException {
		// Act
		boolean result = um.addUser("john@email.com");

		// Assert
		Assert.assertTrue(result);
	}

	@Test(priority = HIGH, dependsOnGroups = "sanity", description = "Verify that getUser method retrieves the correct existing user")
	public void getExistingUserReturnsExistingSavedUser() throws DuplicateUserException {
		// Arrange
		um.addUser("john@email.com");

		// Act
		String user = um.getUser("john@email.com");

		// Assert
		Assert.assertEquals(user, "john@email.com");
	}

	@Test(dependsOnGroups = "sanity", description = "Verify that getUser method returns null if the user does not exist")
	public void getNonExistingUserReturnsNull(){
		// Act
		String user = um.getUser("john@email.com");

		// Assert
		Assert.assertNull(user,"The method should return null if it doesn't find a user");
	}


	@Test(dependsOnGroups = "sanity", expectedExceptions = DuplicateUserException.class,
		expectedExceptionsMessageRegExp = ".*already exists")
	public void addDuplicateThrowsException() throws DuplicateUserException {
		// Act
		um.addUser("same@email.com");
		um.addUser("same@email.com");
	}

}
