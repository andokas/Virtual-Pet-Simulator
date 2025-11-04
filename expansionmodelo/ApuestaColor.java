package expansionmodelo;

import java.util.ArrayList;
import java.util.Arrays;

public class ApuestaColor implements Apuesta{
	private ArrayList<String> apuestas = new ArrayList<String>();
	private int res=0;
	private int cantidad = 0;
	private boolean rojo = false;
	private boolean negro = false;
	private ArrayList<Integer> numRojos = new ArrayList<>(Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36)) ;
	private ArrayList<Integer> numNegros = new ArrayList<>(Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35));
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
		comprobado = apuestasComprobadas.contains("rojo") || apuestasComprobadas.contains("negro");
		if (apuestas.contains("rojo")) {
			rojo = true;
		}
		else if (apuestas.contains("negro")) {
			negro = true;
		}
		
		if (rojo && numRojos.contains(res)&&!comprobado) {
			cantidad = cantidad*2;
			exp.añadirApuestaComprobada("rojo");
		}
		else if (negro && numNegros.contains(res)&&!comprobado) {
			cantidad = cantidad*2;
			exp.añadirApuestaComprobada("negro");
		}
		else {
			cantidad = 0;
		}
		System.out.println("color");
		return cantidad;
	}

}
