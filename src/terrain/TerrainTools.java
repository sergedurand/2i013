package terrain;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Color;

/** Cette classe contient les outils pour la gestion des terrains
 * 
 * @author Serge et Kevin
 *
 */

public class TerrainTools {

	/**
	 * Permet de créer un terrain selon un caractère : 
	 * un "g" en paramètre renvoie un terrain de type Herbe (cf la classe Terrain)
	 * Elle récupère une exception si le caractère n'est pas valide : il doit être 
	 * dans la liste : '.', 'g', 'b', 'o', 'r', 'w', '*', '!', 'm'.
	 * @param c
	 * @return
	 * @throws TerrainException
	 */
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
	   // dans l'ideal, on separe la gestion des exceptions
	   } catch (Exception e) {
	     e.printStackTrace();
	     System.err.println("Invalid Format : " + file
	              + "... Loading aborted");
	     return null;
	   }
   }
   
   public static BufferedImage imageFromCircuit(Terrain[][] track) {
	   //récupération de la taille et création d'une image de taille correspondante
	   int nColonne = track.length;
	   if(nColonne==0) {
		   System.out.println("terrain vide");
		   return null;
	   }
	   int nLigne = track[1].length;
	   
	   BufferedImage im = new BufferedImage(nColonne,nLigne,BufferedImage.TYPE_INT_ARGB);
	   //parcourt du circuit et dessin pixel par pixel en fonction du terrain
	   Graphics g = im.getGraphics();
	   for(int i = 0; i<nColonne;i++) {
		   for(int j = 0;j<nLigne;j++) {
			   //on récupère la couleur du pixel courant
			   Color cour = terrainToRGB(track[i][j]);
			   g.setColor(cour);
			   g.drawLine(i,j,i,j); //dessine une ligne de longueur 1 pixel...
		   }
	   }
	   return im;
	   
	  
	   
	   
   }

    
    
}
