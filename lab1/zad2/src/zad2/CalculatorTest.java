package zad2;

import org.junit.*;

public class CalculatorTest {
	
	Calculator c = new Calculator();
    @Test
    public void testSum() {	
    	
      Assert.assertEquals(3, c.add(1, 2),0.01);
    }
    @Test
    public void testSub() {	
        Assert.assertNotEquals(0, c.sub(1, 2),0.01);
    }
    @Test
    public void testMult() {	
        Assert.assertEquals(4, c.multi(2, 2),0.01);
    }
    @Test
    public void testDiv() {	
        Assert.assertEquals(2, c.div(4, 2),0.01);
    }
    
	@Test(expected=AssertionError.class)
    public void divideByZero() {
    	Assert.assertEquals(0,c.div(3.0,0.0),0.1);
    }
    
    @Test
    public void testGreater() {	
        Assert.assertTrue(c.greater(3, 2));
    }
}
