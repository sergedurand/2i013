package voiture;
import circuit.Circuit;
import geometrie.*;


public class VoitureFactory {
	  private final static double[] tabVitesse     = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.};
	  private final static double[] tabTurn        = {1.,  0.9, 0.75, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05};

	  private final static double vmax = 0.9;
	  private final static double alpha_c = 0.02;
	  private final static double braquage = 0.2;
	  private final static double alpha_f = 0.001;
	  private final static double beta_f = 0.002;
	
	public static Voiture build(Circuit c) {
		Vecteur position = c.getPointDepart();
		Vecteur direction = c.getDirectionDepart();
		Voiture v = new VoitureImpl(0.,position,direction,tabVitesse,tabTurn,
				vmax,alpha_c,braquage,alpha_f,beta_f);
		return v;
		
	}
}
