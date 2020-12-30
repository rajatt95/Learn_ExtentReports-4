package testcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCase2 {

	@Test
	public void doUserReg() {
		Assert.fail("User Reg test failed");
	}

	@Test
	public void isSkip() {
		throw new SkipException("Skipping the test case");
	}

	@Test
	public void doLogin() {
		System.out.println("Executing Login test");
	}

}
