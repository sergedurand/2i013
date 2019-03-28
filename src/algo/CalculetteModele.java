package algo;

public class CalculetteModele {
	private int total;
	private static final int ADD=1;
	private static final int SUB=2;
	private static final int MULT=3;
	private static final int DIV=4;
	private static final int RIEN=0;
	private static final int DEBUT=10;
	
	
	private int operation=DEBUT;
	
	public CalculetteModele()
    {
        
    }
	
	public void setOperation(int o,int e) {
		if (o==DEBUT) {
			total=e;
		}
		else {
			if (operation!=(RIEN)) {
				operation=o;
				doOperation(e);
			}
		}
		
	}
	
	public void doOperation(int e) {
		switch(operation) {
		case ADD:
			add(e);
			break;
		case SUB:
			sub(e);
			break;
		case MULT:
			mult(e);
			break;
		case DIV:
			div(e);
			break;
		case DEBUT:
			total=e;
			break;
		}
		operation=RIEN;
	}
	
	
	private void div(int e) {
		if (e==0) return;
		total/=e;
	}

	private void mult(int e) {
		total*=e;
	}

	private void add(int e) {
		total+=e;
	}
	
	private void sub(int e) {
		total-=e;
	}
	
	public void resetTotal() {
		total=0;
		operation=DEBUT;
	}
	
	public int getTotal() {
		return total;
	}
	
	
	
	
	
}
