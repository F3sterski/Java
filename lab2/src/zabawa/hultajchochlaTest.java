package zabawa;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Assert;
import org.junit.Test;


public class hultajchochlaTest {

	zabawa z = new zabawa();
	
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
	public void HultajchochlaReturnCorrectNumber() throws NieudanyPsikusException {
		for(int i=0;i<100;i++){
			Assert.assertThat(z.hultajchochla(123), either(is(132)).or(is(321)).or(is(213)));
			Assert.assertThat(z.hultajchochla(4567),either(is(5467)).or(is(4576)).or(is(4657)).or(is(6547)).or(is(7564)).or(is(4765)));
		}
	}    
    
}
