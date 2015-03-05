package zad3;

import org.junit.*;

import java.util.*;


public class LiczbaRzymskaTest {
		
	@Test
    public void testKnownValues() {	
		Hashtable<Integer,String> numbers
	     = new Hashtable<Integer, String>();
		numbers.put(1, "I");
		numbers.put(2, "II");
		numbers.put(3, "III");
		numbers.put(4, "IV");
		numbers.put(5, "V");
		numbers.put(6, "VI");
		numbers.put(7, "VII");
		numbers.put(8, "VIII");
		numbers.put(9, "IX");
		numbers.put(10, "X");
		numbers.put(50, "L");
		numbers.put(100, "C");
		numbers.put(500, "D");
		numbers.put(1000, "M");
		numbers.put(31, "XXXI");
		numbers.put(148, "CXLVIII");
		numbers.put(294, "CCXCIV");
		numbers.put(312, "CCCXII");
		numbers.put(421, "CDXXI");
		numbers.put(528, "DXXVIII");
		numbers.put(621, "DCXXI");
		numbers.put(782, "DCCLXXXII");
		numbers.put(870, "DCCCLXX");
		numbers.put(941, "CMXLI");
		numbers.put(1043, "MXLIII");
		numbers.put(1110, "MCX");
		numbers.put(1226, "MCCXXVI");
		numbers.put(1301, "MCCCI");
		numbers.put(1485, "MCDLXXXV");
		numbers.put(1509, "MDIX");
		numbers.put(1607, "MDCVII");
		numbers.put(1754, "MDCCLIV");
		numbers.put(1832, "MDCCCXXXII");
		numbers.put(1993, "MCMXCIII");
		numbers.put(2074, "MMLXXIV");
		numbers.put(2152, "MMCLII");
		numbers.put(2212, "MMCCXII");
		numbers.put(2343, "MMCCCXLIII");
		numbers.put(2499, "MMCDXCIX");
		numbers.put(2574, "MMDLXXIV");
		numbers.put(2646, "MMDCXLVI");
		numbers.put(2723, "MMDCCXXIII");
		numbers.put(2892, "MMDCCCXCII");
		numbers.put(2975, "MMCMLXXV");
		numbers.put(3051, "MMMLI");
		numbers.put(3185, "MMMCLXXXV");
		numbers.put(3250, "MMMCCL");
		numbers.put(3313, "MMMCCCXIII");
		numbers.put(3408, "MMMCDVIII");
		numbers.put(3501, "MMMDI");
		numbers.put(3610, "MMMDCX");
		numbers.put(3743, "MMMDCCXLIII");
		numbers.put(3844, "MMMDCCCXLIV");
		numbers.put(3888, "MMMDCCCLXXXVIII");
		numbers.put(3940, "MMMCMXL");
		numbers.put(3999, "MMMCMXCIX");

		Enumeration<Integer> enumKey = numbers.keys();
		
		//From num to roman
		while(enumKey.hasMoreElements()){
			Integer key = enumKey.nextElement();
		    String val = numbers.get(key);
		    LiczbaRzymska roman = new LiczbaRzymska(key);
		    Assert.assertEquals(val,roman.toString());
		}
	}
		
	@Test(expected=IllegalArgumentException.class)
	public void ToRomanBadInputMinus(){
		LiczbaRzymska roman = new LiczbaRzymska(-2);
		roman.toString();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void ToRomanBadInputPlus(){
		LiczbaRzymska roman = new LiczbaRzymska(5000);
		roman.toString();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void ToRomanBadInputZero(){
		LiczbaRzymska roman = new LiczbaRzymska(0);
		roman.toString();
	}
}