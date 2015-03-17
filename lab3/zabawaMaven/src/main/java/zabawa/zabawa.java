package zabawa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class zabawa implements psikus {

	public Integer cyfrokrad(Integer liczba) {
		if(liczba<10) return null;
		Random generator = new Random();
		String s = liczba.toString();
		StringBuilder sb = new StringBuilder(s);
		sb.deleteCharAt(generator.nextInt(s.length()));
		return Integer.parseInt(sb.toString());
	}

	public Integer hultajchochla(Integer liczba) throws NieudanyPsikusException {
		if(liczba<100) throw new NieudanyPsikusException();
		Random generator = new Random();
		String s = liczba.toString();
		StringBuilder sb = new StringBuilder(s);
		int a = generator.nextInt(sb.length());
		int b=a;
		while(b==a){
			b=generator.nextInt(sb.length());
		}
		char temp = sb.charAt(a);
		sb.setCharAt(a, sb.charAt(b));
		sb.setCharAt(b, temp);
		return Integer.parseInt(sb.toString());		
	}

	public Integer nieksztaltek(Integer liczba) {
		Random generator = new Random();
		String s = liczba.toString();
		StringBuilder sb = new StringBuilder(s);
		List<Integer> listInt = new ArrayList<Integer>();
		for(int i=0;i<sb.length();i++){
			switch (sb.charAt(i)){
			case '3':
				listInt.add(i);
				break;
			case '7':
				listInt.add(i);
				break;			
			case '6':
				listInt.add(i);
				break;
			}
		}
		if(listInt.isEmpty()){
			return liczba;
		}
		else{
			Integer PozycjaLiczbyWLiscie = generator.nextInt(listInt.size());			
			char Liczba = sb.charAt(listInt.get(PozycjaLiczbyWLiscie));
			
			switch (Liczba){
			case '3':
				Liczba='8';
				break;
			case '7':
				Liczba='1';
				break;			
			case '6':
				Liczba='9';
				break;
			}
			sb.setCharAt(listInt.get(PozycjaLiczbyWLiscie), Liczba);
		}
		return Integer.parseInt(sb.toString());		
	}

}
