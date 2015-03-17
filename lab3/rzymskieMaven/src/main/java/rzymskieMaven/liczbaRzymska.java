package rzymskieMaven;

import java.lang.reflect.Array;

public class liczbaRzymska {

	
	public liczbaRzymska(int liczba){
		this.liczba=liczba;
	}
	
	public int getLiczba() {
		return liczba;
	}
	public void setLiczba(int liczba) {
		this.liczba = liczba;
	}
	private int liczba;
	
	public String toString(){
		int pom,i,poz=0,miejsce=0;
		int n = this.liczba;
		char[] jeden={'I','X','C','M'};
		char[] dwa={'V','L','D'};
		char[] trzy={'X','C','M'};

		char[] tab= new char[100];
		if (n<1 || n>3999){
			throw new IllegalArgumentException("Podana liczba jest niepoprawna");
		}
		do{
			pom=n%10;      
			n=n/10;
			if(pom>=1 && pom<=3){
				for(i=0;i<pom; i++){
					tab[poz++]=jeden[miejsce];
				}
			}
			if(pom==4){
			        tab[poz++]=dwa[miejsce];
				tab[poz++]=jeden[miejsce];
			}
			if(pom==5){
				tab[poz++]=dwa[miejsce];
			}
			if(pom>=6 && pom<=8){
				for(i=0;i<pom-5; i++){
					tab[poz++]=jeden[miejsce];
				}
		        tab[poz++]=dwa[miejsce];
			}
			if(pom==9){
		                tab[poz++]=trzy[miejsce];
				tab[poz++]=jeden[miejsce];
			}
			miejsce++;              
		}while(n!=0);
		String rom="";
		for(poz--;poz>=0;poz--) {
			rom = rom + "" + tab[poz];
		}
		return rom;
	}
	
}
