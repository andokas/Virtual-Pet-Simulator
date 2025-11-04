package expansionmodelo;

import java.util.ArrayList;

public class ApuestaNumero implements Apuesta{
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
		comprobado = apuestasComprobadas.contains("numero");
		if (apuestas.contains(Integer.toString(res)) &&!comprobado) {
			cantidad = cantidad*35;
			exp.añadirApuestaComprobada("numero");
		}
		else {
			cantidad = 0;
		}
		System.out.println("numero");
		return cantidad;
	}

}
