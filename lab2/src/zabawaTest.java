import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;


public class zabawaTest {

	zabawa z = new zabawa();
	
	@Test
	public void testCyfrokrad() {
		
		Integer k = 23;
		Assert.assertEquals(k, z.cyfrokrad(123));
		
	}

	@Test
	public void testHultajchochla() throws NieudanyPsikusException {
		Integer k = 2321;
		Assert.assertEquals(k, z.hultajchochla(1223));
	}

	//@Test
	public void testNieksztaltek() {
		Assert.fail("Not yet implemented");
	}

}
