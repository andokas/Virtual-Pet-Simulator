package expansionmodelo;

import java.util.ArrayList;

public class ApuestaMitad implements Apuesta{
	private ArrayList<String> apuestas = new ArrayList<String>();
	private int res=0;
	private int cantidad = 0;
	private boolean comprobado = false;
	private ArrayList<String> apuestasComprobadas = new ArrayList<>();
	
	@Override
	public int apostar() {
		// TODO Auto-generated method stub
		Expansion exp = Expansion.getExpansion();
		apuestas = exp.getApuestas();
		res = exp.getResultado();
		cantidad = exp.getCantidad();
		apuestasComprobadas = exp.getApuestasComprobadas();
		comprobado = apuestasComprobadas.contains("1 - 18") || apuestasComprobadas.contains("19 - 36");
		if (apuestas.contains("1 - 18") && 1<= res && res<=18 &&!comprobado) {
			cantidad = cantidad*2;
			exp.añadirApuestaComprobada("1 - 18");
		}
		else if (apuestas.contains("19 - 36") && 19 <= res && res <= 36 &&!comprobado) {
			cantidad = cantidad*2;
			exp.añadirApuestaComprobada("19 - 36");
		}
		else {
			cantidad = 0;
		}
		System.out.println("mitad");
		return cantidad;
	}

}
