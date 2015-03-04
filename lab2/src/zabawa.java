import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class zabawa implements psikus {

	@Override
	public Integer cyfrokrad(Integer liczba) {
		if(liczba<10) return null;
		Random generator = new Random();
		String s = liczba.toString();
		StringBuilder sb = new StringBuilder(s);
		sb.deleteCharAt(generator.nextInt(s.length()));
		return Integer.parseInt(sb.toString());
	}

	@Override
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

	@Override
	public Integer nieksztaltek(Integer liczba) {
		Random generator = new Random();
		String s = liczba.toString();
		StringBuilder sb = new StringBuilder(s);
		boolean hoho = true;
		int trzy=0;
		int siedem=0;
		int dziewiec=0;
		
		for(int i =0;i<sb.length();i++){
			switch(sb.charAt(i)){
			case '3':
				trzy++;
				break;
			case '7':
				siedem++;
				break;
			case '9':
				dziewiec++;
				break;
			}			
		}
		for(int i =0 ; i<sb.length();i++){
			if(generator.nextBoolean() && hoho){
				hoho = false;
				switch(sb.charAt(i)){
				case '3':
					sb.setCharAt(sb.charAt(i), '8');
					break;
				case '7':
					sb.setCharAt(sb.charAt(i), '1');
					break;
				case '9':
					sb.setCharAt(sb.charAt(i), '9');
					break;
				}
			}
		}
		return Integer.parseInt(sb.toString());		
	}

}
