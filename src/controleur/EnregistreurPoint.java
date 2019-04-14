package controleur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import circuit.Circuit;
import geometrie.*;
import vue.VuePoint;

public class EnregistreurPoint implements MouseListener {
	
	private ArrayList<Vecteur> positions;
	private UpdateEventListener ihm;
	private VuePoint vuep;
	private Circuit c;

	
	public EnregistreurPoint(ArrayList<Vecteur> positions, UpdateEventListener ihm, VuePoint vuep) {
		super();
		this.positions = positions;
		this.ihm = ihm;
		this.vuep = vuep;
	}
	
	

	public ArrayList<Vecteur> getPositions() {
		return positions;
	}



	public void setPositions(ArrayList<Vecteur> positions) {
		this.positions = positions;
	}



	public UpdateEventListener getIhm() {
		return ihm;
	}



	public void setIhm(UpdateEventListener ihm) {
		this.ihm = ihm;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		positions.add(new Vecteur(e.getY(),e.getX()));
		Graphics g = vuep.getGraphics();
		g.setColor(Color.YELLOW);
		g.drawRect(e.getX(), e.getY(), 2, 2);
		ihm.manageUpdate(0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
