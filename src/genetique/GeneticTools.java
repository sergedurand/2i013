package genetique;

import java.util.ArrayList;
import java.util.List;

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
}
