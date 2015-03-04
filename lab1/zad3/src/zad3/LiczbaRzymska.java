package zad3;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class LiczbaRzymska {

	public LiczbaRzymska(){
	}
	
	public String toString(int Int){
		if(Int>3999 || Int<1) throw new IllegalArgumentException("Bad integer"); 
		LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
	    roman_numerals.put("M", 1000);
	    roman_numerals.put("CM", 900);
	    roman_numerals.put("D", 500);
	    roman_numerals.put("CD", 400);
	    roman_numerals.put("C", 100);
	    roman_numerals.put("XC", 90);
	    roman_numerals.put("L", 50);
	    roman_numerals.put("XL", 40);
	    roman_numerals.put("X", 10);
	    roman_numerals.put("IX", 9);
	    roman_numerals.put("V", 5);
	    roman_numerals.put("IV", 4);
	    roman_numerals.put("I", 1);
	    String res = "";
	    for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
	      int matches = Int/entry.getValue();
	      res += repeat(entry.getKey(), matches);
	      Int = Int % entry.getValue();
	    }
	    return res;
	  }
	  public static String repeat(String s, int n) {
	    if(s == null) {
	        return null;
	    }
	    final StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < n; i++) {
	        sb.append(s);
	    }
	    return sb.toString();
	}
	  private static final int[] VALUES = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
	  private static final String[] DIGRAMS = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};    

	  public int toInteger(String s) {
	      if (s.isEmpty())
	          throw new IllegalArgumentException("empty string");
	      if (!s.matches("^(C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"))
	          throw new IllegalArgumentException("unexpected roman numerals");

	      //creates a regex with each roman numeral pattern -> M|CM|D|......
	      //You'll need it to check if you have several occurrences of a pattern
	      //-> CC or III...
	      StringBuilder builder = new StringBuilder();
	      for (int i = 0; i < DIGRAMS.length; i++) {
	          builder.append(DIGRAMS[i]);

	          if (i < DIGRAMS.length - 1)
	              builder.append("|");
	      }     

	      Matcher matcher = Pattern.compile(builder.toString()).matcher(s);    
	      int result = 0;

	      while (matcher.find()) {
	          for (int j = 0; j < DIGRAMS.length; j++) {
	              if (DIGRAMS[j].equals(matcher.group()))
	                  result += VALUES[j];
	          }
	      }
	      return result;
	  }	  
}
