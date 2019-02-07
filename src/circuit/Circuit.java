package circuit;
import geometrie.Vecteur;
import terrain.Terrain;
import java.util.ArrayList;

public interface Circuit {
	public Terrain getTerrain(int i, int j);
    public Terrain getTerrain(Vecteur v);
    public Vecteur getPointDepart();
    public Vecteur getDirectionDepart();
    public Vecteur getDirectionArrivee();
    public int getWidth();
    public int getHeight();
    public ArrayList<Vecteur> getArrivees();
    public Terrain[][] getTerrain();

    public double getDist(int i, int j); // dijkstra

    // SALE et discutable, désactivé pour le moment
    // public Terrain[][] getMatrix();   

}
