package terrain.test;
import terrain.Terrain;
import terrain.TerrainException;
import terrain.TerrainTools;
public class TestTerrain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Terrain t1 = TerrainTools.terrainFromChar('.');
			System.out.println(t1.toString());
			System.out.println(TerrainTools.isRunnable(t1));
			Terrain t2 = TerrainTools.terrainFromChar('g');
			System.out.println(TerrainTools.isRunnable(t2));
			Terrain[][] circuit1 = TerrainTools.lectureFichier("1_safe.trk");
			for(int i=0;i<1024;i++) {
				for(int j=0;j<768;j++) {
					System.out.print(TerrainTools.charFromTerrain(circuit1[i][j]));
				}
				System.out.print('\n');
			}
		} catch (TerrainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
