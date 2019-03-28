package voiture;

public class Commande {
	private double acc;
	private double turn;
	
	public Commande(double acc, double turn) {
		super();
		this.acc = acc;
		this.turn = turn;
	}

	public double getAcc() {
		return acc;
	}

	public double getTurn() {
		return turn;
	}
	
	public void setTurn(double turn) {
		this.turn=turn;
	}
	
	public void setAcc(double acc) {
		this.acc=acc;
	}
	

	@Override
	public String toString() {
		return "Commande [acc=" + acc + ", turn=" + turn + "]";
	}

}
