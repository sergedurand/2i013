package genetique;

import java.util.Comparator;

public class ComparatorGenome implements Comparator<Genome> {

	@Override
	public int compare(Genome arg0, Genome arg1) {
		if(arg0.getScore()<arg1.getScore()) {
			return -10;
		}
		if(arg0.getScore()>arg1.getScore()) {
			return 10;
		}
		return 0;
	}

}
