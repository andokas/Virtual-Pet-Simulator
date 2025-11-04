package expansionmodelo;

import java.util.ArrayList;

public class ApuestaCompuesta implements Apuesta{

	private ArrayList <Apuesta> apu = new ArrayList<Apuesta>();
	private int punt = 0;
	
	public void addApuesta(Apuesta pA) {
		apu.add(pA);
	}
	
	public void deleteApuesta(Apuesta pA) {
		apu.remove(pA);
	}
	
	public int apostar() {
		punt = apu.stream().mapToInt(p->p.apostar()).sum();
		apu = new ArrayList<Apuesta>();
		System.out.println("---------------------------------------------");
		return punt;
	}
}
