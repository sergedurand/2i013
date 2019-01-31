package terrain.test;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import terrain.Terrain;
import terrain.TerrainException;
import terrain.TerrainTools;
public class TestTerrain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Terrain[][] circuit1 = null;
		try {
			Terrain t1 = TerrainTools.terrainFromChar('.');
			System.out.println(t1.toString());
			System.out.println(TerrainTools.isRunnable(t1));
			Terrain t2 = TerrainTools.terrainFromChar('g');
			System.out.println(TerrainTools.isRunnable(t2));
			circuit1 = TerrainTools.lectureFichier("1_safe.trk");
		} catch (TerrainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedImage im = TerrainTools.imageFromCircuit(circuit1);
            File outputfile = new File("saved.png");
            ImageIO.write(im, "png", outputfile);
         } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde");
         }
		System.out.println("pour tester github");

		

	}

}
