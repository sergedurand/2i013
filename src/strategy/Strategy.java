package strategy;
import java.io.Serializable;

import circuit.Circuit;
import exceptions.NeuroneException;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureException;


public interface Strategy extends Serializable {
	public Commande getCommande() throws VoitureException, NeuroneException;
	public void init(Voiture v, Circuit c); 
}
