package algo;

import circuit.Circuit;
import voiture.Voiture;

public interface Radar {
	public void scores(double epsilon); // score de chaque branche
    public int getBestIndex(); // meilleur indice
    public double[] getAngles(); // angles de chaque faisceau
    public Voiture getVoiture();
    public void traceRadar();
	double[] getDistPix();
	public void init(Voiture v,Circuit c);
	//public Radar(int n); //constructeur avec le nombre d'angles en paramètres
	double getDistMin();
}

