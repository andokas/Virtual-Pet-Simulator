package expansionvista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import expansionmodelo.Expansion;

import vista.Juego;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;

@SuppressWarnings("deprecation")
public class Resultado extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelmensaje;
	private JPanel panelnumero;
	private JLabel mensaje;
	private JLabel numero;
	private JButton exit;
	private Controler controler = null;
	private static Resultado mResultado=new Resultado();
	
	

	/**
	 * Create the frame.
	 */
	private Resultado() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanelmensaje(), BorderLayout.NORTH);
		getContentPane().add(getPanelnumero(), BorderLayout.CENTER);
		Expansion expansion = Expansion.getExpansion();
		expansion.addObserver(this);
	}
	
	public static Resultado getResultado() {
		return mResultado;
	}

	private JPanel getPanelmensaje() {
		if (panelmensaje == null) {
			panelmensaje = new JPanel();
			panelmensaje.add(getMensaje());
			panelmensaje.add(getExit());
		}
		return panelmensaje;
	}
	private JPanel getPanelnumero() {
		if (panelnumero == null) {
			panelnumero = new JPanel();
			panelnumero.setLayout(new GridLayout(0, 1, 0, 0));
			panelnumero.add(getNumero());
		}
		return panelnumero;
	}
	private JLabel getMensaje() {
		if (mensaje == null) {
			mensaje = new JLabel("");
		}
		return mensaje;
	}
	private JLabel getNumero() {
		if (numero == null) {
			numero = new JLabel();
			numero.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return numero;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String) {
			String mensaje = (String) arg;
			if (mensaje.equals("CerrarConApuesta")) {
				setVisible(false);
				Juego.getJuego().setVisible(true);
			}
		}
		else if (arg instanceof Object[]) {
			Object[] objectArray = (Object[]) arg;
			if(objectArray[0].equals("Mostrar resultado")) {
				getNumero().setText("En la ruleta ha salido el " + objectArray[2].toString() + " y el retorno es " + objectArray[1].toString());
			}
		}
	}
	private JButton getExit() {
		if (exit == null) {
			exit = new JButton("Exit");
			exit.addActionListener(getControler());
		}
		return exit;
	}
	
	//--------------------------------------------Controler--------------------------------------------------------------

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
				exp.salirApostando();
			}
		}
		
	}
}
