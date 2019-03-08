package strategy;
import java.io.Serializable;

import circuit.Circuit;
import voiture.Commande;
import voiture.Voiture;


public interface Strategy extends Serializable {
	public Commande getCommande();
	public void init(Voiture v, Circuit c); 
}
