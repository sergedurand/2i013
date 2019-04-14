package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VuePoint extends JFrame {
	
	public VuePoint(JPanel ihm, String s) {
		super(s);
		ihm.setLayout(new BorderLayout());
		this.getContentPane().add(ihm);
		this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}
