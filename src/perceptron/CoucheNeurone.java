package perceptron;

import java.util.ArrayList;

public class CoucheNeurone {
	private ArrayList<Neurone> neurones = new ArrayList<Neurone>();
	private int taille;
	
	public CoucheNeurone(int taille) {
		this.taille = taille;
		
	}

	public ArrayList<Neurone> getNeurones() {
		return neurones;
	}
	public void setNeurones(ArrayList<Neurone> neurones) {
		this.neurones = neurones;
	}
	public int getTaille() {
		return taille;
	}
	public void initAleatoire(int input, int output, double min,  double max) {
		int i;
		ArrayList<Neurone> neurones = new ArrayList<Neurone>();
		for(i=0;i<this.getTaille();i++) {
			Neurone temp = new Neurone(0,output,min,max);
			neurones.add(temp);
		}
		this.setNeurones(neurones);
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public void afficher() {
		
	}


}
