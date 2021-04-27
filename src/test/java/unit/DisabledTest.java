package unit;

import baseclasses.UnitTestBaseClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
//@Test (enabled = false) // this will disable all tests and save you some typing instead of putting it on each test
//with enabled = false -> nothing runs but using the @Ignore the parent class will be executed
public class DisabledTest extends UnitTestBaseClass {

	@Test
	public void unstableTest1(){
		System.out.println("Test 1");
	}

	@Test
	public void unstableTest2(){
		System.out.println("Test 2");
	}
}
