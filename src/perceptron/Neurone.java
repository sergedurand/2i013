package perceptron;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class Neurone {

	private ArrayList<Double> listePoidsEntrants = new ArrayList<Double>();
	private ArrayList<Double> listePoidsSortants = new ArrayList<Double>();
	private double valeur;
	private int nb_input;
	private int nb_output;
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
	private Neurone(ArrayList<Double> listePoidsEntrants, ArrayList<Double> listePoidsSortants, int nb_input,
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
	 * Instantiate a neuron with given size of outputs with random weights in the given range
	 * @param output
	 * @param min
	 * @param max
	 */
	public void setRandomPoidsSortant(int output,double min, double max) {
		Random r = new Random();
		this.nb_output=output;
		ArrayList<Double> l_poids_sortants = new ArrayList<Double>();
		for(int j = 0;j<output;j++) {
			l_poids_sortants.add((min + (max - min)*r.nextDouble()));
		}
		this.setListePoidsSortants(l_poids_sortants);
		
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
		this.setNb_input(listePoidsEntrants.size());
	}

	public ArrayList<Double> getListePoidsSortants() {
		return listePoidsSortants;
	}

	public void setListePoidsSortants(ArrayList<Double> listePoidsSortants) {
		this.listePoidsSortants = listePoidsSortants;
		this.setNb_output(listePoidsSortants.size());
	}

	public int getNb_input() {
		return nb_input;
	}

	private void setNb_input(int nb_input) {
		this.nb_input = nb_input;
	}

	public int getNb_output() {
		return nb_output;
	}

	private void setNb_output(int nb_output) {
		this.nb_output = nb_output;
	}
	
	@Override
	public String toString() {
		String res = "Valeur neurone : "+this.getValeur() +" \n";
		res += "biais = " + this.getBiais() +"\n";	
		res += "liste des poids entrants : ";
		res += this.getListePoidsEntrants().toString();
		res+= "\n";
		res+= "liste des poids sortants : ";
		res += this.getListePoidsSortants().toString();
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
