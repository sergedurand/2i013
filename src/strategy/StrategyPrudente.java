package strategy;

import circuit.Circuit;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;

import java.util.ArrayList;

import algo.*;

public class StrategyPrudente implements Strategy{
	/**
	 * 
	 */
	public int i=0;
	private static final long serialVersionUID = 1L;
	private Voiture v;
	private Radar radar;
	public ArrayList<Commande> commandes;
	public StrategyPrudente(Radar radar,Voiture v) {
		super();
		this.radar = radar;
		commandes= new ArrayList<Commande>();
		//this.v=v;
	}
	
	public Radar getRadar() {
		return radar;
	}
	
	public void setVoiture(Voiture v) {
		this.v=v;
	}
	public boolean vc=false;
	@Override
	public Commande getCommande() throws VoitureException {
		i++;
		boolean bool=true;
		boolean bool2=false;
		radar.scores(0.1);
		double turn = radar.getAngles()[radar.getBestIndex()]*2/Math.PI;
		//System.out.println(radar.getDistMin());
		//if (radar.getDistPix()[radar.getBestIndex()]<=200 && i>150) {	
		//if (radar.getDistPix()[radar.getBestIndex()]<=200 ) {	
		if (radar.getDistMin()<200 && i>20) {
			Commande com=new Commande(1,turn);
			if (v.getVitesse()<0.5) {
				com =v.setVitesseConstante(vc,com);
				vc=!vc;
				if (((com.getTurn())>v.getMaxTurn()&&turn>=0)||((com.getTurn())<v.getMaxTurn()*-1&&turn<0)) {
					while(turn>=0&&(turn>=v.getMaxTurn())) {
						if (com.getAcc()>0) {
			        		turn/=2;
			        		bool=false;
			        	}
			        	else {
			        		turn*=0.50;
			        		bool=false;
			        	}
					}
					if (bool==false) {
						if (com.getAcc()>0) {
							commandes.add(new Commande(-com.getAcc(),turn));
							com=(new Commande(-com.getAcc(),turn));
							return new Commande(-com.getAcc(),turn);
							
							//v.drive(com);
						}
						else {
							commandes.add(new Commande(com.getAcc(),turn));
							com=(new Commande(com.getAcc(),turn));
							return new Commande(com.getAcc(),turn);
							//v.drive(com);
						}
					}
					
					while ((turn<0)&&(turn<=v.getMaxTurn()*-1)) {
						//System.out.println("turn = "+turn+"   getMaxTurn= "+v.getMaxTurn());
						if (com.getAcc()>0) {
			        		turn*=0.50;
			        		//System.out.println("2eme version de turn:"+ turn);
			        		bool2=false;
			        		bool=false;
			        	}
			        	else {
			        		turn*=0.50;
			        		bool2=false;
			        		bool=false;
			        	}
					}
					if (bool2==false) {
						if (com.getAcc()>0) {
							commandes.add(new Commande(-com.getAcc(),turn));
							com=(new Commande(-com.getAcc(),turn));
							//v.drive(com);
							return com;
						}
						else {
							commandes.add(new Commande(com.getAcc(),turn));
							com=(new Commande(com.getAcc(),turn));
							//v.drive(com);
							return com;
						}
					}
					
			
		        }
				
				
				
				if (bool==true) {
					commandes.add(com);
					return(com);
				}

			}

			
			
		}else {
			//return new Commande(1,turn);
		}
		//throw new RuntimeException("wtf");
		return new Commande(1,turn);
		
	}

	@Override
	public void init(Voiture v, Circuit c) {
		radar.init(v,c);
	}
	

}