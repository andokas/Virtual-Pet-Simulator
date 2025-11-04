package expansionmodelo;

import java.util.ArrayList;

public class ApuestaDocena implements Apuesta{
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
		comprobado = apuestasComprobadas.contains("1 - 12") || apuestasComprobadas.contains("13 - 24") || apuestasComprobadas.contains("25 - 36");
		if (apuestas.contains("1 - 12") && 1<= res && res<=12 &&!comprobado) {
			cantidad = cantidad*3;
			exp.añadirApuestaComprobada("1 - 12");
		}
		else if (apuestas.contains("13 - 24") && 13 <= res && res <= 24 &&!comprobado) {
			cantidad = cantidad*3;
			exp.añadirApuestaComprobada("13 - 24");
		}
		else if (apuestas.contains("25 - 36") && 25 <= res && res <= 36 &&!comprobado) {
			cantidad = cantidad*3;
			exp.añadirApuestaComprobada("25 - 36");
		}
		else {
			cantidad = 0;
		}
		System.out.println("docena");
		return cantidad;
	}

}
