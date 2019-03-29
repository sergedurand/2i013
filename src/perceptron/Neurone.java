package perceptron;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class Neurone {
	private ArrayList<Double> listePoidsEntrants = new ArrayList<Double>();
	private ArrayList<Double> listePoidsSortants = new ArrayList<Double>();
	private double valeur;
	int nb_input;
	int nb_output;
	private double biais;	

	public Neurone(ArrayList<Double> listePoidsEntrants, ArrayList<Double> listePoidsSortants, int nb_input,
			int nb_output) {
		super();
		this.listePoidsEntrants = listePoidsEntrants;
		this.listePoidsSortants = listePoidsSortants;
		this.nb_input = nb_input;
		this.nb_output = nb_output;
	}


	
	
	
	public Neurone(int input,int output,double min, double max) {
		Random r = new Random();
		int i, j;
		for(i = 0;i<input;i++) {
			listePoidsEntrants.add(min + (max - min) * r.nextDouble());
		}
		for(j = 0;j<output;j++) {
			listePoidsSortants.add((min + (max - min)*r.nextDouble()));
		}
		
		this.biais = (double)(min + (max - min)*r.nextDouble());
		
	}

	public ArrayList<Double> getListePoidsEntrants() {
		return listePoidsEntrants;
	}

	public void setListePoidsEntrants(ArrayList<Double> listePoidsEntrants) {
		this.listePoidsEntrants = listePoidsEntrants;
	}

	public ArrayList<Double> getListePoidsSortants() {
		return listePoidsSortants;
	}

	public void setListePoidsSortants(ArrayList<Double> listePoidsSortants) {
		this.listePoidsSortants = listePoidsSortants;
	}

	public int getNb_input() {
		return nb_input;
	}

	public void setNb_input(int nb_input) {
		this.nb_input = nb_input;
	}

	public int getNb_output() {
		return nb_output;
	}

	public void setNb_output(int nb_output) {
		this.nb_output = nb_output;
	}
	
	@Override
	public String toString() {
		String poidsEntrant;
		String poidsSortant;
		return null;
	}

	public double getBiais() {
		return biais;
	}

	public void setBiais(double biais) {
		this.biais = biais;
	}



	public double getValeur() {
		return valeur;
	}





	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	
}
