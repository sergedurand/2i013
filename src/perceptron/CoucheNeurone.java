package perceptron;

import java.util.ArrayList;

public class CoucheNeurone {
	private ArrayList<Neurone> neurones = new ArrayList<Neurone>();
	private int taille;
	
	
	
	/**
	 * straightforward constructor
	 */
	public CoucheNeurone(int taille, ArrayList<Neurone> neurones) {
		this.taille = taille;
		this.neurones = neurones;
	}

	/**
	 * Initialize a empty layer with a given size.
	 * @param taille
	 */
	public CoucheNeurone(int taille) {
		this.taille = taille;
		ArrayList<Neurone> l_neurones = new ArrayList<Neurone>();
		for(int i = 0;i<taille;i++) {
			l_neurones.add(new Neurone(0));
		}
		this.neurones = l_neurones;
		
	}
	
	/**
	 * Create a layer from list of list of weights and list of bias
	 * @param listePoidsNeurones
	 * @param biais
	 */
	public CoucheNeurone(ArrayList<ArrayList<ArrayList<Double>>> listePoidsNeurones, ArrayList<Double> biais) {
		this(listePoidsNeurones.size());
		if(listePoidsNeurones.get(0).size() != 2) {
			System.out.println("probleme nombre de liste de poids : il en faut 2");
			return;
		}
		
		int i = 0;
		for(ArrayList<ArrayList<Double>> listePoids : listePoidsNeurones) {
			double biais_courant = biais.get(i);
			ArrayList<Double> poidsEntrants = listePoids.get(0);
			ArrayList<Double> poidsSortants = listePoids.get(1);
			this.getNeurones().set(i,new Neurone(poidsEntrants,poidsSortants,poidsEntrants.size(),poidsSortants.size(),biais_courant));
			i++;
		}
	}
	
	public CoucheNeurone(CoucheNeurone couche1, ArrayList<Double> biais) {
		this(couche1.getNeurones().get(0).getNb_output());
		int j = 0;
		for(Neurone n : this.getNeurones()) {
			ArrayList<Double> l_poids_courant = new ArrayList<Double>();
			for(int i = 0; i < couche1.getTaille();i++) {
				l_poids_courant.add(couche1.getNeurones().get(i).getListePoidsSortants().get(j));
			}
			n.setListePoidsEntrants(l_poids_courant);
			n.setListePoidsSortants(new ArrayList<Double>());
			j++;
		}
		
		this.setBiais(biais);
	}
	
	public ArrayList<Double> getBiais(){
		ArrayList<Double> res = new ArrayList<Double>();
		for(Neurone n : this.getNeurones()) {
			res.add(n.getBiais());
		}
		return res;
	}
	
	public void setRandomBiais(double min, double max) {
		
	}
	public void setPoidsSortants(ArrayList<ArrayList<Double>> listePoids) {
		int i = 0;
		for(ArrayList<Double> l : listePoids) {
			this.getNeurones().get(i).setListePoidsSortants(l);
			i++;
		}
	}
	public void setValues(ArrayList<Double> values) {
		if(values.size()!=this.getTaille()) {
			System.out.println("erreur nombre de valeur différent du nombre de neurone");
			return;
		}
		for(int i = 0;i<values.size();i++) {
			this.getNeurones().get(i).setValeur(values.get(i));
		}
	}
	
	public void setBiais(ArrayList<Double> biais) {
		for(int i = 0;i<this.getTaille();i++) {
			this.getNeurones().get(i).setBiais(biais.get(i));
		}
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
	
	/**
	 * initialize all input weights randomly in the given bracket.
	 * layer must have been instantiated first with the given size
	 * 
	 * @param input
	 * @param output
	 * @param min
	 * @param max
	 */
	public void setRandomWeightsBias(int output, double min,  double max) {
		for(int i = 0;i<this.getTaille();i++) {
			this.getNeurones().get(i).setRandomPoidsSortant(output, min, max);
		}
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	/**
	 * sets all the neurons input weights. layer elements must have been instantiated before. To be use to change weights 
	 * @param listepoids
	 */
	public void setInputWeights(ArrayList<ArrayList<Double>> listepoids) {
		if(listepoids.size() != this.getTaille()) {
			System.out.println("le nombre de liste ne correspond pas au nombre de neurone");
			return;
		}
		int i = 0;
		for(ArrayList<Double> l : listepoids) {
			Neurone neurone_courant = this.getNeurones().get(i);
			if(l.size()!=neurone_courant.getNb_input()) {
				System.out.println("nombre de poids entrant ne correspond pas au neurone");
				return;
			}
			neurone_courant.setListePoidsEntrants(l);
			i++;
		}
		
	}
	/**
	 * sets all the neurons input weights. layer elements must have been instantiated before. To be use to change weights 
	 * @param listepoids
	 */
	public void setOutputWeights(ArrayList<ArrayList<Double>> listepoids) {
		if(listepoids.size() != this.getTaille()) {
			System.out.println("le nombre de liste ne correspond pas au nombre de neurone");
			return;
		}
		int i = 0;
		for(ArrayList<Double> l : listepoids) {
			Neurone neurone_courant = this.getNeurones().get(i);
			if(l.size()!=neurone_courant.getNb_output()) {
				System.out.println("nombre de poids entrant ne correspond pas au neurone");
				return;
			}
			neurone_courant.setListePoidsSortants(l);
			i++;
		}
		
	}
	
	public void setBias(ArrayList<Double> lbiais) {
		if(lbiais.size()!=this.getTaille()) {
			System.out.println("nombre de biais différent du nombre de neurone");
			return;
		}
		int i = 0;
		for(double biais : lbiais) {
			this.getNeurones().get(i).setBiais(biais);
			i++;
		}
		
	}
	
	public void setRandomBias(double min, double max) {
		for(Neurone n : this.getNeurones()) {
			n.setRandomBiais(min, max);
		}
	}
	
	public void setRandomPoidsSortant(double min, double max) {
		for(Neurone n : this.getNeurones()) {
			n.setRandomPoidsSortant(n.getNb_output(), min, max);
		}
	}
	
	@Override
	public String toString() {
		String res = "taille couche : " + this.getTaille() + "\n";
		int i = 0;
		for(Neurone n : this.getNeurones()) {
			res += "Neurone : " + i + " : \n";
			res += n.toString();
		}
		return res;
		
	}


}
