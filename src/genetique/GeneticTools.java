package genetique;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import voiture.Commande;

public class GeneticTools {
	
	public static ArrayList<Genome> listToArrayList(List<Genome> list){
		ArrayList<Genome> res = new ArrayList<Genome>();
		for(Genome g : list) {
			res.add(g);
		}
		return res;
	}
	
	public static List<Genome> arrayListtoList(ArrayList<Genome> list){
		List<Genome> res = new ArrayList<Genome>();
		for(Genome g : list) {
			res.add(g);
		}
		return res;
	}
	
	public static void saveGenome(Genome g, String filename){
        try {
                DataOutputStream os = new DataOutputStream(new FileOutputStream(filename));
                for(int i : g.getStructure()){
                        os.writeInt(i);
                }
                for(int i = 0;i<g.getListe_poids().size();i++) {
                	for(int j = 0; j<g.getListe_poids().get(i).size();j++) {
                		for(int k = 0;k<g.getListe_poids().get(i).get(j).size();k++) {
                			os.writeDouble(g.getListe_poids().get(i).get(j).get(k));
                		}
                	}
                }
                
                for(int i = 0;i<g.getListe_biais().size();i++) {
                	for(int j= 0;j<g.getListe_biais().get(i).size();j++) {
                		os.writeDouble(g.getListe_biais().get(i).get(j));
                	}
                }
                os.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
	}
	
//	public static Genome loadGenome(String filename) {
//		Genome g = null;
//
//        try {
//                DataInputStream os = new DataInputStream(new FileInputStream(filename));
//                ArrayList<Integer> struct = new ArrayList<Integer>();
//                int i;
//                while(){ // on attend la fin de fichier
//                	os.readi
//                        a = os.readDouble();
//                        t = os.readDouble();
//                        liste.add(new Commande(a,t));
//                }
//                
//
//        } catch (EOFException e){
//                return liste;
//        }
//	}
}
