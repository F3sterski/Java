import java.util.Arrays;
import java.util.Collection;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;


public class zabawaTest {

	zabawa z = new zabawa();

	
	@Test
	public void CyfrokradReturnNotSameNumber() {
		int x = 123;
		Assert.assertThat(x, is(not(z.cyfrokrad(123))));
	}

	@Test
	public void HultajchochlaReturnNotSameNumber() throws NieudanyPsikusException {
		int k = 123456;
		Assert.assertThat(k, is(not(z.hultajchochla(123456))));
	}
	
    @Test(expected= NieudanyPsikusException.class) 
    public void HultajchochlaReturnException() throws NieudanyPsikusException { 
    	z.hultajchochla(10);
    }

	@Test
	public void NieksztaltekReturnSameNumber() {
		Integer k = 1122;
		Assert.assertEquals(k, z.nieksztaltek(1122));
	}
	
	@Test
	public void NieksztaltekOneFromOthers() {
		Assert.assertThat(z.nieksztaltek(1136), either(is(1186)).or(is(1139)));

	}

}
