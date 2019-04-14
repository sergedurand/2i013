package observeurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

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
		try {
		// TODO Auto-generated method stub
		g.setColor(coul);
		if (voit!=null) {
			trajectoire.add(voit.getPosition());
		}
		
		
		
		for(Vecteur v : trajectoire) {
			if (v!=null) {
				g.fillRect((int)v.getX(),(int) v.getY(), 2, 2);
			}
		}
		}catch(ConcurrentModificationException e1) {
			for(Vecteur v : trajectoire) {
				if (v!=null) {
					g.fillRect((int)v.getX(),(int) v.getY(), 2, 2);	
				}
			//System.out.println("modification concurrente trajectoire");
				
			}
		}catch (NullPointerException e2) {
			for(Vecteur v : trajectoire) {
				if (v!=null) {
					g.fillRect((int)v.getX(),(int) v.getY(), 2, 2);
				}
				
			}
		}
		
		

	}
	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		coul = c ;
		
	}
	

}
