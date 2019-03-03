package strategy;
import java.io.Serializable;

import algo.Radar;
import circuit.*;
import voiture.*;


public interface Strategy extends Serializable {
	public Commande getCommande();
	public void init(Voiture v, Circuit c); 
}
