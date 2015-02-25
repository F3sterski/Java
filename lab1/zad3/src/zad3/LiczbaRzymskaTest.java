package zad3;

import org.junit.*;

public class LiczbaRzymskaTest {
	
	LiczbaRzymska l = new LiczbaRzymska(3);
	
	@Test
    public void testSum() {	
    	
      Assert.assertEquals("III", l.toString());
    }
    
}