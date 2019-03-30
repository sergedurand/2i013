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

	/**
	 * straightforward constructor. 
	 * @param listePoidsEntrants
	 * @param listePoidsSortants
	 * @param nb_input
	 * @param nb_output
	 * @param valeur
	 * @param biais
	 */
	public Neurone(ArrayList<Double> listePoidsEntrants, ArrayList<Double> listePoidsSortants, int nb_input,
			int nb_output, double valeur, double biais) {
		super();
		this.listePoidsEntrants = listePoidsEntrants;
		this.listePoidsSortants = listePoidsSortants;
		this.nb_input = nb_input;
		this.nb_output = nb_output;
		this.valeur = valeur;
		this.biais = biais;
	}
	
	/**
	 * constructor that leaves the value at 0.
	 * @param listePoidsEntrants
	 * @param listePoidsSortants
	 * @param nb_input
	 * @param nb_output
	 * @param biais
	 */
	public Neurone(ArrayList<Double> listePoidsEntrants, ArrayList<Double> listePoidsSortants, int nb_input,
			int nb_output, double biais) {
		this(listePoidsEntrants,listePoidsSortants,nb_input,nb_output,0,biais);

	}
	
	/**
	 * Instantiate a neuron with the value only, sets emtpy lists for weights, bias at 0
	 * @param valeur
	 */
	public Neurone(double valeur) {
		this(new ArrayList<Double>(),new ArrayList<Double>(),0,0,valeur,0);
	}


	
	
	/**
	 * Instantiate a neuron with given size of inputs and outputs with random weights and bias in the given range
	 * @param input
	 * @param output
	 * @param min
	 * @param max
	 */
	public void setRandomPoidsSortant(int output,double min, double max) {
		Random r = new Random();
		this.nb_output=output;

		for(int j = 0;j<output;j++) {
			listePoidsSortants.add((min + (max - min)*r.nextDouble()));
		}
		
	}
	
	
	
	public void setRandomBiais(double min,double max) {
		Random r = new Random();
		this.setBiais((min + (max - min)*r.nextDouble()));
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
		String res = "Valeur neurone : "+this.getValeur() +" \n";
		res += "biais = " + this.getBiais() +"\n";
		
		res += "liste des poids entrants : ";
		String poidsEntrants ="{";
		int i = 0;
		for(double poids : this.getListePoidsEntrants()) {
			i++;
			poidsEntrants += poids;
			if(i!=this.getNb_input()) {
				poidsEntrants +=" ;";
			}
		}
		poidsEntrants += "}";
		res+= poidsEntrants;
		res+= "\n";
		res+= "liste des poids sortants : ";
		String poidsSortants ="{";
		i = 0;
		for(double poids : this.getListePoidsSortants()) {
			i++;
			poidsSortants += poids;
			if(i!=this.getNb_output()) {
				poidsSortants +=" ;";
			}
		}
		poidsSortants += "}";
		res+= poidsSortants;
		res+= "\n";
		return res;
		
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
