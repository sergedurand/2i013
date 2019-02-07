package voiture;

import geometrie.Vecteur;

public interface Voiture {
	// pour le pilotage
    public void drive(Commande c) throws VoitureException;
    public double getMaxTurn(); // cf juste après
    // pour l'observation
    public double getVitesse();
    public Vecteur getPosition();
    public Vecteur getDirection();
    public double getBraquage();
}
