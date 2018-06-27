package testing_framework_demo;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Jaspal
 *
 */
public class TestNgDemo {
	
	@Test
	public void test1() {
		int value1 = 10;
		int value2 = 10;
		Assert.assertEquals(value1, value2);
	}
	
	@Test
	public void test2() {
		int value1 = 15;
		int value2 = 10;
		Assert.assertEquals(value1, value2);
	}
	
	@Test
	public void test3() {
		int value1 = 5;
		int value2 = 5;
		Assert.assertEquals(value1, value2);
	}

}
