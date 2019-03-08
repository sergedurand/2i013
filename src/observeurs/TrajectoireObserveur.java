package observeurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import geometrie.Vecteur;
import voiture.VoitureImpl;

public class TrajectoireObserveur implements ObserveurSwing {
	private VoitureImpl voit;
	private ArrayList<Vecteur> trajectoire;
	
	public TrajectoireObserveur(VoitureImpl v) {
		voit = v;
	}
	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		trajectoire.add(voit.getPosition());
		
		

	}

}
