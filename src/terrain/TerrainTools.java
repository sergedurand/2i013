package terrain;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/** Cette classe contient les outils pour la gestion des terrains
 * 
 * @author Serge et Kevin
 *
 */

public class TerrainTools {

	/**
	 * Permet de creer un terrain selon un caractere : 
	 * un "g" en parametre renvoie un terrain de type Herbe (cf la classe Terrain)
	 * dans la liste : '.', 'g', 'b', 'o', 'r', 'w', '*', '!', 'm'.
	 * @param c le caractere doit etre parmi '.', 'g', 'b', 'o', 'r', 'w', '*', '!', 'm'.
	 * @return
	 * 	retourne un terrain (= un pixel d'un circuit)
	 * @throws TerrainException
	 * jette une exception si le caractere n'est pas dans la liste des caracteres possibles
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
	   if(t == Terrain.Herbe || t == Terrain.Obstacle|| t == Terrain.Eau) {
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
	     hauteur=Integer.parseInt(buf);
	     buf = in.readLine();
	     longueur=Integer.parseInt(buf);
	     Terrain[][] matrice=new Terrain[longueur][hauteur];
	     for (int j=0;j<longueur;j++) {
	    	 buf = in.readLine();
	    	 for (int k=0;k<hauteur;k++) {
	    		 matrice[j][k]=terrainFromChar(buf.charAt(k));
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
   
   public static BufferedImage imageFromTerrain(Terrain[][] track) {
	   //r�cup�ration de la taille et cr�ation d'une image de taille correspondante
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
			   //on r�cup�re la couleur du pixel courant
			   Color cour = terrainToRGB(track[i][j]);
			   g.setColor(cour);
			   g.drawLine(i,j,i,j); //dessine une ligne de longueur 1 pixel...
		   }
	   }
	   return im;	       
   }
   
   //peut être inutile
   public static void sauvegardeCircuit(Terrain[][] t,String s) {
		try {
			BufferedImage im = TerrainTools.imageFromTerrain(t);
           File outputfile = new File(s);
           ImageIO.write(im, "png", outputfile);
        } catch (IOException e) {
           System.out.println("Erreur lors de la sauvegarde");
        }
 }

    
    
}
