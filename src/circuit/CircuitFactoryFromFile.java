package circuit;
import circuit.CircuitImpl;
import geometrie.Vecteur;
import terrain.*;

public class CircuitFactoryFromFile {
	public final static Vecteur dirDepart = new Vecteur(0,1);
	public final static Vecteur dirArrivee = new Vecteur(0,1);

	public static Circuit build(String filename) {
		Terrain[][] t=TerrainTools.lectureFichier(filename);
		return new CircuitImpl(t,dirDepart,dirArrivee);
		
	}
}
