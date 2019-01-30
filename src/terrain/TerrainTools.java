package terrain;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TerrainTools {

	public static Terrain terrainFromChar(char c) throws TerrainException{
		Terrain[] values= Terrain.values();
		for(int i=0;i<values.length;i++) {
			if(c==Terrain.conversion[i]) {
				return values[i];
			}
		}
		throw new TerrainException("Terrain incconu : "+c);
	}
	
	public static char charFromTerrain(Terrain c) {
		return Terrain.conversion[c.ordinal()];
	}
	
    public static Color terrainToRGB(Terrain c) {
    	return Terrain.convColor[c.ordinal()];
    }
    
   public static boolean isRunnable(Terrain t) {
	   char t2 = charFromTerrain(t);
	   if((t2=='g') || (t2=='b') || (t2=='o')) {
		   return false;
	   }
	   return true;
   }
   
   public static Terrain[][] lectureFichier(String fichier){
	   FileInputStream file=null;
	   int longueur,hauteur;
	   try {
		 file = new FileInputStream(fichier);
	     InputStreamReader fr = new InputStreamReader(file);
	     BufferedReader in = new BufferedReader(fr);
	     String buf = in.readLine();
	     longueur=Integer.parseInt(buf);
	     buf = in.readLine();
	     hauteur=Integer.parseInt(buf);
	     Terrain[][] matrice=new Terrain[longueur][hauteur];
	     for (int j=0;j<hauteur;j++) {
	    	 buf = in.readLine();
	    	 for (int k=0;k<longueur;k++) {
	    		 matrice[k][j]=terrainFromChar(buf.charAt(k));
	    	 } 	 
	     }
	     
	     in.close();
	     return matrice;
	   // dans l'idéal, on sépare la gestion des exceptions
	   } catch (Exception e) {
	     e.printStackTrace();
	     System.err.println("Invalid Format : " + file
	              + "... Loading aborted");
	     return null;
	   }
   }

    
    
}
