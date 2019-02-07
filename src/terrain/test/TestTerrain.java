package terrain.test;
import terrain.Terrain;
import terrain.TerrainException;
import terrain.TerrainTools;
public class TestTerrain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Terrain[][] t = null;
		try {
			Terrain t1 = TerrainTools.terrainFromChar('.');
			System.out.println(t1.toString());
			System.out.println(TerrainTools.isRunnable(t1));
			Terrain t2 = TerrainTools.terrainFromChar('g');
			System.out.println(TerrainTools.isRunnable(t2));
			t = TerrainTools.lectureFichier("1_safe.trk");
		} catch (TerrainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TerrainTools.sauvegardeCircuit(t);

	}

}
