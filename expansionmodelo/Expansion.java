package expansionmodelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

import modelo.Tamagotchi;

@SuppressWarnings("deprecation")
public class Expansion extends Observable{
	private static Expansion miExpansion = new Expansion();
	private Color[][] matrizColores = null;
	private String[][] matrizNumeros = new String[3][13]; 
	private ArrayList<Integer> numRojos = new ArrayList<>(Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36)) ;
	private ArrayList<Integer> numNegros = new ArrayList<>(Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35));
	private ArrayList<String> apuestas = new ArrayList<>();
	private ArrayList<String> apuestasComprobadas = new ArrayList<>();
	private Random random = new Random();
	private ApuestaCompuesta apucomp;
	private int resultado;
	private int cantidad;
	
	private Expansion() {
		apucomp = new ApuestaCompuesta();
	}
	
	public static Expansion getExpansion() {
		return miExpansion;
	}
	
	public void crearRuleta(int fila, int col) {
		int[][] matriz = new int[3][13];
		int numero = 0;
		matrizColores = new Color[fila][col];
		Color color = null;
		for (int j = 0; j < col; j++) {
            for (int i = fila - 1; i >= 0; i--) {
                numero = (numero + 1) % 37;
                matriz[i][j] = numero;
            }
        }
		for (int i = 0; i < fila; i++) {
            for (int j = 0; j < col; j++) {
            	numero = matriz[i][j];
            	if (numRojos.contains(numero)) {
        			color = ColorFactory.getColorFactory().crearColor("rojo");
        		}
        		else if (numNegros.contains(numero)) {
        			color = ColorFactory.getColorFactory().crearColor("negro");
        		}
        		matrizColores[i][j] = color;
        		matrizNumeros[i][j] = color.getColor();
        		Object[] objectArray = new Object[] {"A�adir numero", numero, color.getColor()};
        		setChanged();
        		notifyObservers(objectArray);
            }
        }
	}
	
	public void salirSinApostar() {
		String mensaje;
		mensaje = "CerrarSinApuesta";
		setChanged();
		notifyObservers(mensaje);
		Tamagotchi.getTamagotchi().iniciarCont();
	}
	
	public void salirApostando() {
		String mensaje;
		mensaje = "CerrarConApuesta";
		setChanged();
		notifyObservers(mensaje);
		Tamagotchi.getTamagotchi().iniciarCont();
	}
	
	public void gestionarApuesta(String casilla) {
		String mensaje;
		if(apuestas.contains(casilla)) {
			mensaje = "Quitar apuesta";
			eliminarApuesta(casilla);
		}
		else {
			mensaje = "Pintar";
			anadirApuesta(casilla);
		}
		Object[] objectArray = new Object[] {mensaje, casilla};
		setChanged();
		notifyObservers(objectArray);
	}
	
	private void anadirApuesta(String casilla) {
		apuestas.add(casilla);
		if(casilla.equals("rojo")||casilla.equals("negro")) {
			ApuestaColor color = new ApuestaColor();
			apucomp.addApuesta(color);
		}
		else if(casilla.equals("1 - 12")||casilla.equals("13 - 24")||casilla.equals("25 - 36")) {
			ApuestaDocena docena = new ApuestaDocena();
			apucomp.addApuesta(docena);
		}
		else if(casilla.equals("1 - 18")||casilla.equals("19 - 36")) {
			ApuestaMitad mitad = new ApuestaMitad();
			apucomp.addApuesta(mitad);
		}
		else if(casilla.equals("PAR")||casilla.equals("IMPAR")) {
			ApuestaPar par = new ApuestaPar();
			apucomp.addApuesta(par);
		}
		else {
			ApuestaNumero numero = new ApuestaNumero();
			apucomp.addApuesta(numero);
		}
	}
	
	private void eliminarApuesta(String casilla) {
		apuestas.remove(casilla);
		if(casilla.equals("rojo")||casilla.equals("negro")) {
			ApuestaColor color = new ApuestaColor();
			apucomp.deleteApuesta(color);
		}
		else if(casilla.equals("1 - 12")||casilla.equals("13 - 24")||casilla.equals("25 - 36")) {
			ApuestaDocena docena = new ApuestaDocena();
			apucomp.deleteApuesta(docena);
		}
		else if(casilla.equals("1 - 18")||casilla.equals("19 - 36")) {
			ApuestaMitad mitad = new ApuestaMitad();
			apucomp.deleteApuesta(mitad);
		}
		else if(casilla.equals("PAR")||casilla.equals("IMPAR")) {
			ApuestaPar par = new ApuestaPar();
			apucomp.deleteApuesta(par);
		}
		else {
			ApuestaNumero numero = new ApuestaNumero();
			apucomp.deleteApuesta(numero);
		}
	}
	
	public void apostar(int pCantidad) {
		Tamagotchi tama = Tamagotchi.getTamagotchi();
		if (pCantidad > 0 && pCantidad*apuestas.size() <= tama.getScore() && !apuestas.isEmpty()) {
			tama.restarPuntuacion(pCantidad*apuestas.size());
			
			cantidad = pCantidad;
			String mensaje = "Mostrar resultado";
			resultado = random.nextInt(36) + 1;
			System.out.println(resultado);
			int retorno = apucomp.apostar();
			System.out.println("el retorno esss:   "+retorno);
			tama.sumarPuntuacion(retorno);
			
			reiniciar();
			Object[] objectArray = new Object[] {mensaje, retorno, resultado};
			setChanged();
			notifyObservers(objectArray);
		}
	}
	
	private void reiniciar() {
		String mensaje = "Quitar apuesta";
		for(int i=0;i<apuestas.size();i++) {
			String casilla = apuestas.get(i);
			Object[] objectArray = new Object[] {mensaje, casilla};
			setChanged();
			notifyObservers(objectArray);
		}
		apuestas = new ArrayList<>();
		apuestasComprobadas = new ArrayList<>();
	}
	
	public ArrayList<String> getApuestas() {
		return apuestas;
	}
	
	public int getResultado() {
		return resultado;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void añadirApuestaComprobada(String pApuesta) {
		apuestasComprobadas.add(pApuesta);
	}
	
	public ArrayList<String> getApuestasComprobadas() {
		return apuestasComprobadas;
	}

}
