package expansionvista;

import javax.swing.JButton;

public class Numero extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int num;
	
	public Numero(int pNum) {
		num = pNum;
	}
	
	public int getNum() {
		return num;
	}
}
