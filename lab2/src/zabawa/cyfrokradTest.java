package zabawa;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;




@RunWith(Parameterized.class)
public class cyfrokradTest {
	
	zabawa z = new zabawa();
	
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { null, 0 }, { null, 1 }, { null, 2 }, { null, 3 }, { null, 4 }, { null, 5 },{ null, 6 }, { null, 7 },{ null, 8 }, { null, 9 }  
           });
    }

    @Parameter
    public String Cinput;

    @Parameter(value = 1)
    public int CExpected;
    
	@Test 
	public void  CyfrokradReturnNull(){
		Assert.assertEquals(Cinput, z.cyfrokrad(CExpected));
	}
	
	@Test
	public void CyfrokradReturnNotSameNumber() {
		int x = 123;
		Assert.assertThat(x, is(not(z.cyfrokrad(123))));
	}
	
	@Test
	public void CyfrokradReturnCorrectNumber() {
		Assert.assertThat(z.cyfrokrad(123), either(is(12)).or(is(13)).or(is(23)));
		Assert.assertThat(z.cyfrokrad(4567),either(is(456)).or(is(457)).or(is(567)).or(is(467)));

	}	
	
}