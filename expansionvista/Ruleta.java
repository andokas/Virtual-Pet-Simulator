package expansionvista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import expansionmodelo.Expansion;
import modelo.Tamagotchi;
import vista.Juego;

import java.awt.Color;

@SuppressWarnings("deprecation")
public class Ruleta extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel apuestas;
	private JPanel cantidadapostar;
	private JPanel numeros;
	private JPanel docenas;
	private JPanel colorparmitad;
	private JButton docena1;
	private JButton docena2;
	private JButton docena3;
	private JButton mitad1;
	private JButton par;
	private JButton rojo;
	private JButton negro;
	private JButton impar;
	private JButton mitad2;
	private JLabel lblCantidadAApostar;
	private JLabel score;
	private JTextField apuesta;
	private JButton exit;
	private JButton apostar;
	private int puntuacion;
	private Border border = BorderFactory.createLineBorder(Color.WHITE);
	private Controler controler = null;
	private HashMap<String, JButton> mapaBotones = new HashMap<String, JButton>();
	private Resultado resultado = Resultado.getResultado();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ruleta frame = new Ruleta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ruleta() {
		getContentPane().setBackground(new Color(38, 162, 105));
		setBackground(new Color(38, 162, 105));
		setBounds(100, 100, 594, 406);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getApuestas(), BorderLayout.SOUTH);
		getContentPane().add(getCantidadapostar(), BorderLayout.NORTH);
		getContentPane().add(getNumeros(), BorderLayout.CENTER);
		Expansion expansion = Expansion.getExpansion();
		expansion.addObserver(this);
		expansion.crearRuleta(3, 12);
		Tamagotchi.getTamagotchi().addObserver(this);
	}
	private JPanel getApuestas() {
		if (apuestas == null) {
			apuestas = new JPanel();
			apuestas.setBackground(new Color(38, 162, 105));
			apuestas.setLayout(new BorderLayout(0, 0));
			apuestas.add(getDocenas(), BorderLayout.NORTH);
			apuestas.add(getColorparmitad(), BorderLayout.SOUTH);
		}
		return apuestas;
	}
	private JPanel getCantidadapostar() {
		if (cantidadapostar == null) {
			cantidadapostar = new JPanel();
			cantidadapostar.setBackground(new Color(38, 162, 105));
			cantidadapostar.setLayout(new GridLayout(1, 1, 0, 0));
			cantidadapostar.add(getScore());
			cantidadapostar.add(getLblCantidadAApostar());
			cantidadapostar.add(getApuesta());
			cantidadapostar.add(getApostar());
			cantidadapostar.add(getExit());
		}
		return cantidadapostar;
	}
	private JPanel getNumeros() {
		if (numeros == null) {
			numeros = new JPanel();
			numeros.setBackground(Color.LIGHT_GRAY);
			numeros.setLayout(new GridLayout(3, 13, 0, 0));
		}
		return numeros;
	}
	private JPanel getDocenas() {
		if (docenas == null) {
			docenas = new JPanel();
			docenas.setBackground(new Color(38, 162, 105));
			docenas.setLayout(new GridLayout(1, 0, 0, 0));
			docenas.add(getDocena1());
			docenas.add(getDocena2());
			docenas.add(getDocena3());
		}
		return docenas;
	}
	private JPanel getColorparmitad() {
		if (colorparmitad == null) {
			colorparmitad = new JPanel();
			colorparmitad.setBackground(new Color(38, 162, 105));
			colorparmitad.setLayout(new GridLayout(1, 0, 0, 0));
			colorparmitad.add(getMitad1());
			colorparmitad.add(getPar());
			colorparmitad.add(getRojo());
			colorparmitad.add(getNegro());
			colorparmitad.add(getImpar());
			colorparmitad.add(getMitad2());
		}
		return colorparmitad;
	}
	private JButton getDocena1() {
		if (docena1 == null) {
			docena1 = new JButton("1 - 12");
			docena1.setForeground(new Color(255, 255, 255));
			docena1.setBackground(new Color(38, 162, 105));
			docena1.setBorder(border);
			mapaBotones.put(docena1.getActionCommand(), docena1);
			docena1.addActionListener(getControler());
		}
		return docena1;
	}
	private JButton getDocena2() {
		if (docena2 == null) {
			docena2 = new JButton("13 - 24");
			docena2.setForeground(new Color(255, 255, 255));
			docena2.setBackground(new Color(38, 162, 105));
			docena2.setBorder(border);
			mapaBotones.put(docena2.getActionCommand(), docena2);
			docena2.addActionListener(getControler());
		}
		return docena2;
	}
	private JButton getDocena3() {
		if (docena3 == null) {
			docena3 = new JButton("25 - 36");
			docena3.setForeground(new Color(255, 255, 255));
			docena3.setBackground(new Color(38, 162, 105));
			docena3.setBorder(border);
			mapaBotones.put(docena3.getActionCommand(), docena3);
			docena3.addActionListener(getControler());
		}
		return docena3;
	}
	private JButton getMitad1() {
		if (mitad1 == null) {
			mitad1 = new JButton("1 - 18");
			mitad1.setForeground(new Color(255, 255, 255));
			mitad1.setBackground(new Color(38, 162, 105));
			mitad1.setBorder(border);
			mapaBotones.put(mitad1.getActionCommand(), mitad1);
			mitad1.addActionListener(getControler());
		}
		return mitad1;
	}
	private JButton getPar() {
		if (par == null) {
			par = new JButton("PAR");
			par.setForeground(new Color(255, 255, 255));
			par.setBackground(new Color(38, 162, 105));
			par.setBorder(border);
			mapaBotones.put(par.getActionCommand(), par);
			par.addActionListener(getControler());
		}
		return par;
	}
	private JButton getRojo() {
		if (rojo == null) {
			rojo = new JButton("rojo");
			rojo.setBackground(new Color(224, 27, 36));
			rojo.setForeground(new Color(0, 0, 0, 0));
			rojo.setBorder(border);
			mapaBotones.put(rojo.getActionCommand(), rojo);
			rojo.addActionListener(getControler());
		}
		return rojo;
	}
	private JButton getNegro() {
		if (negro == null) {
			negro = new JButton("negro");
			negro.setForeground(new Color(0, 0, 0, 0));
			negro.setBackground(new Color(0, 0, 0));
			negro.setBorder(border);
			mapaBotones.put(negro.getActionCommand(), negro);
			negro.addActionListener(getControler());
		}
		return negro;
	}
	private JButton getImpar() {
		if (impar == null) {
			impar = new JButton("IMPAR");
			impar.setForeground(new Color(255, 255, 255));
			impar.setBackground(new Color(38, 162, 105));
			impar.setBorder(border);
			mapaBotones.put(impar.getActionCommand(), impar);
			impar.addActionListener(getControler());
		}
		return impar;
	}
	private JButton getMitad2() {
		if (mitad2 == null) {
			mitad2 = new JButton("19 - 36");
			mitad2.setForeground(new Color(255, 255, 255));
			mitad2.setBackground(new Color(38, 162, 105));
			mitad2.setBorder(border);
			mapaBotones.put(mitad2.getActionCommand(), mitad2);
			mitad2.addActionListener(getControler());
		}
		return mitad2;
	}
	private JLabel getLblCantidadAApostar() {
		if (lblCantidadAApostar == null) {
			lblCantidadAApostar = new JLabel("Cantidad a apostar:");
			lblCantidadAApostar.setForeground(new Color(255, 255, 255));
			lblCantidadAApostar.setBackground(new Color(38, 162, 105));
			lblCantidadAApostar.setBorder(border);
		}
		return lblCantidadAApostar;
	}
	private JLabel getScore() {
		if (score == null) {
			score = new JLabel("Score:");
			score.setForeground(new Color(255, 255, 255));
			score.setBackground(new Color(38, 162, 105));
			score.setHorizontalAlignment(SwingConstants.CENTER);
			score.setBorder(border);
		}
		return score;
	}
	private JTextField getApuesta() {
		if (apuesta == null) {
			apuesta = new JTextField();
			apuesta.setBackground(new Color(38, 162, 105));
			apuesta.setColumns(10);
		}
		return apuesta;
	}
	private JButton getExit() {
		if (exit == null) {
			exit = new JButton("Exit");
			exit.setForeground(new Color(255, 255, 255));
			exit.setBackground(new Color(38, 162, 105));
			exit.setBorder(border);
			exit.addActionListener(getControler());
		}
		return exit;
	}
	private JButton getApostar() {
		if (apostar == null) {
			apostar = new JButton("Apostar");
			apostar.setForeground(new Color(255, 255, 255));
			apostar.setBackground(new Color(38, 162, 105));
			apostar.setBorder(border);
			apostar.addActionListener(getControler());
		}
		return apostar;
	}
	//--------------------------------------------Controler----------------------------------------------------------------------	
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	private class Controler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Expansion exp = Expansion.getExpansion();
			if(e.getSource().equals(getExit())){
				exp.salirSinApostar();
			}
			else if(e.getSource().equals(getApostar())) {
				if (apuesta.getText().equals(""))
				{
					System.out.println("no puedes apostar 0, perro");
				}
				else
				{
					 try 
					 {
						 int valorApostado = Integer.parseInt(apuesta.getText());
				         exp.apostar(valorApostado);
				     } 
					 catch (NumberFormatException ex) 
					 {
				         System.out.println("No es un número válido");
				     }
				}			
			}
			else {
				exp.gestionarApuesta(e.getActionCommand());
			}
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Object[]) 
		{
			Object[] objectArray = (Object[]) arg;
			if (objectArray[0].equals("Aï¿½adir numero")) {
				int numero = (int) objectArray[1];
				Numero miNumero = new Numero(numero);
				miNumero.setText(Integer.toString(numero));
				miNumero.setForeground(Color.WHITE);
				miNumero.setOpaque(true);
				miNumero.setHorizontalAlignment(SwingConstants.CENTER);
				miNumero.addActionListener(getControler());
				mapaBotones.put(miNumero.getActionCommand(), miNumero);
				miNumero.setBorder(border);
				String color = (String) objectArray[2];
				if (color.equals("rojo")) {
					miNumero.setBackground(Color.RED);
				}
				else if (color.equals("negro")) {
					miNumero.setBackground(Color.BLACK);
				}
				else {
					miNumero.setBackground(Color.GREEN);
				}
				numeros.add(miNumero,BorderLayout.CENTER,-1);
			}
			else if(objectArray[0].equals("Pintar")) {
				String casilla = (String) objectArray[1];
				JButton boton = mapaBotones.get(casilla);
				boton.setForeground(new Color(239, 184, 16));
			}
			else if(objectArray[0].equals("Quitar apuesta")) {
				String casilla = (String) objectArray[1];
				JButton boton = mapaBotones.get(casilla);
				if(casilla.equals("rojo")||casilla.equals("negro")) {
					boton.setForeground(new Color(0, 0, 0, 0));
				}
				else {
					boton.setForeground(new Color(255, 255, 255));
				}
			}
			else if(objectArray[0].equals("Mostrar resultado")) {
				setVisible(false);
				int retorno = (int) objectArray[1];
				score.setText(Integer.toString(retorno));
				resultado.setVisible(true);
			}
			else if (objectArray[0].equals("Actualizar puntuacion")) {
				puntuacion = (int) objectArray[1];
				getScore().setText("Score: "+ puntuacion);
			}
		}
		else if(arg instanceof String) {
			String mensaje = (String) arg;
			if(mensaje.equals("CerrarSinApuesta")) {
				setVisible(false);
				Juego.getJuego().setVisible(true);
			}
		}
	}
	
}
