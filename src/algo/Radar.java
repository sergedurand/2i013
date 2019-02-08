package algo;

public interface Radar {
	public int[] scores(double epsilon); // score de chaque branche
    public double[] distancesInPixels(); // pour l'observer
    public int getBestIndex(); // meilleur indice
    public double[] thetas(); // angles de chaque faisceau
}

