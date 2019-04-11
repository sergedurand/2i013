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
	private Color coul;
	
	public TrajectoireObserveur(Voiture v) {
		voit = v;
		trajectoire = new ArrayList<Vecteur>();
		this.coul = Color.YELLOW;
	}
	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(coul);
		trajectoire.add(voit.getPosition());
		
		
		for(Vecteur v : trajectoire) {
			g.fillRect((int)v.getX(),(int) v.getY(), 2, 2);
		}
		
		

	}
	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		coul = c ;
		
	}
	

}
