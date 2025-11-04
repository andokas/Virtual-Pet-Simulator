package expansionmodelo;

import java.util.ArrayList;

public class ApuestaPar implements Apuesta{
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
		comprobado = apuestasComprobadas.contains("PAR") || apuestasComprobadas.contains("IMPAR");
		if (apuestas.contains("PAR") && (res % 2 == 0) && !comprobado) {
			cantidad = cantidad*2;
			exp.añadirApuestaComprobada("PAR");
		}
		else if (apuestas.contains("IMPAR") && (res % 2 != 0) && !comprobado) {
			cantidad = cantidad*2;
			exp.añadirApuestaComprobada("IMPAR");
		}
		else {
			cantidad = 0;
		}
		System.out.println("par/impar");
		return cantidad;
	}

}
