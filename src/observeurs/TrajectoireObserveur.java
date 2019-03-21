package observeurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import geometrie.Vecteur;
import voiture.Voiture;
import voiture.VoitureImpl;

public class TrajectoireObserveur implements ObserveurSwing {
	private Voiture voit;
	private ArrayList<Vecteur> trajectoire;
	
	public TrajectoireObserveur(Voiture v) {
		voit = v;
		trajectoire = new ArrayList<Vecteur>();
	}
	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
		trajectoire.add(voit.getPosition());
		for(Vecteur v : trajectoire) {
			g.fillRect((int)v.getX(),(int) v.getY(), 5, 5);
		}
		
		

	}

}
