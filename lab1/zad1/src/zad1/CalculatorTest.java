package zad1;
import org.junit.*;

public class CalculatorTest {
	
	Calc c = new Calc();
    @Test
    public void testSum() {	
      Assert.assertEquals(3, c.add(1, 2));
    }
    @Test
    public void testSub() {	
        Assert.assertNotEquals(0, c.sub(1, 2));
    }
    @Test
    public void testMult() {	
        Assert.assertEquals(4, c.multi(2, 2));
    }
    @Test
    public void testDiv() {	
        Assert.assertEquals(2, c.div(4, 2));
    }
    
    @Test(expected=ArithmeticException.class)
    public void divideByZero() {
          c.div(3,0);
    }
    @Test
    public void testGreater() {	
        Assert.assertTrue(c.greater(3, 2));
    }
}
