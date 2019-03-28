package vue;

import javax.swing.*;

public class Fenetre extends JFrame{	
	public Fenetre(JPanel ihm, String s) {
		super(s);
		this.getContentPane().add(ihm);
		this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
}
