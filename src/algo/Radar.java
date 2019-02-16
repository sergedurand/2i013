package algo;

import circuit.Circuit;
import voiture.Voiture;

public interface Radar {
	public double[] scores(double epsilon); // score de chaque branche
    public int getBestIndex(); // meilleur indice
    public double[] getAngles(); // angles de chaque faisceau
    public Voiture getVoiture();
    public void traceRadar();
	double[] getDistPix();
	public void init(Voiture v,Circuit c);
}
