package zabawa;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;


public class nieksztaltekTest {

	zabawa z = new zabawa();
	

	@Test
	public void NieksztaltekReturnSameNumber() {
		Integer k = 1122;
		Assert.assertEquals(k, z.nieksztaltek(1122));
	}
	
	@Test 
	public void NieksztaltekTestOneNumber(){
		Assert.assertEquals(new Integer(8),z.nieksztaltek(3));
		Assert.assertEquals(new Integer(1),z.nieksztaltek(7));
		Assert.assertEquals(new Integer(9),z.nieksztaltek(6));
	}
	
	@Test
	public void NieksztaltekOneFromOthers() {
		for(int i=0;i<100;i++){
			Assert.assertThat(z.nieksztaltek(1136), either(is(1186)).or(is(1139)));
			Assert.assertThat(z.nieksztaltek(7226), either(is(1226)).or(is(7229)));
		}
	}
}
