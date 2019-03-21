package voiture;

import geometrie.Vecteur;

public class VoitureImpl implements Voiture {
	// état à l'instant t
	private double vitesse = 0;
	private Vecteur position;
	private Vecteur direction;
	
	
	//Constantes définies dans la factory
	private double[] tabVitesse;
	private double[] tabTurn;
	private double vmax;
	private double alpha_c;
	private double braquage;
	private double alpha_f;
	private double beta_f;
  
	public VoitureImpl(double vitesse, Vecteur position, Vecteur direction, double[] tabVitesse, double[] tabTurn,
			double vmax, double alpha_c, double braquage, double alpha_f, double beta_f) {
		super();
		this.vitesse = vitesse;
		this.position = position;
		this.direction = direction;
		this.tabVitesse = tabVitesse;
		this.tabTurn = tabTurn;
		this.vmax = vmax;
		this.alpha_c = alpha_c;
		this.braquage = braquage;
		this.alpha_f = alpha_f;
		this.beta_f = beta_f;
	}

	@Override
	public void drive(Commande c) throws VoitureException{
		if(c==null) {
			System.out.println("Commande vide");
			return;
		}
		// VERIFICATIONS !!!
        // 1) Est ce que la rotation et l'accélération sont entre -1 et 1, sinon, throw new VoitureException
        if ((c.getTurn()>1)||(c.getTurn()<-1)) {
        	throw new VoitureException("Erreur dans la rotation");
        }
        if ((c.getAcc()>1)||(c.getAcc()<-1)) {
        	throw new VoitureException("Erreur dans l'accélération");
        }
        // 2) Est ce que la rotation demandée est compatible avec la vitesse actuelle, sinon, throw new VoitureException
        if (c.getTurn()>getMaxTurn()) {
        	drive(new Commande(-1*(c.getAcc()),c.getTurn()*(1/2)));
        }
        
        // approche normale
        // 1.1) gestion du volant
        direction = direction.rotation(c.getTurn() * braquage); // modif de direction
        // Note: on remarque bien que l'on tourne d'un pourcentage des capacités de la voiture

        // 1.2) garanties, bornes...
        direction = direction.normalisation(); // renormalisation pour éviter les approximations

        // 2.1) gestion des frottements

        vitesse -= alpha_f;
        vitesse -= beta_f*vitesse;

        // 2.2) gestion de l'acceleration/freinage

        vitesse += c.getAcc() * alpha_c;

        vitesse = Math.max(0., vitesse); // pas de vitesse négative (= pas de marche arriere)
        vitesse = Math.min(vmax, vitesse); // pas de dépassement des capacités

        // 3) mise à jour de la position

        position = position.addition(direction.multiplication(vitesse));
	}

	@Override
	public double getMaxTurn() {
		// TODO Auto-generated method stub
		for (int i=0;i<tabVitesse.length;i++) {
			if(vitesse<tabVitesse[i]) {
				return tabTurn[i];
			}
		}
		return 0.;
	}

	@Override
	public double getVitesse() {
		// TODO Auto-generated method stub
		return this.vitesse;
	}

	@Override
	public Vecteur getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Vecteur getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	public void setDirection (Vecteur v) {
		this.direction=v;
	}
	
	public void setPosition (Vecteur v) {
		this.position=v;
	}
	
	@Override
	public double getBraquage() {
		// TODO Auto-generated method stub
		return braquage;
	}

	@Override
	public boolean getDerapage() {
		// TODO Auto-generated method stub
		return false;
	}

}
