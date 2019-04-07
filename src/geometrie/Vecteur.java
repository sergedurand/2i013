package geometrie;
import java.lang.Math;
public class Vecteur {
	
	public final double x,y;

	public Vecteur(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Vecteur(Vecteur a, Vecteur b) {
		super();
		this.x = b.x-a.x;
		this.y = b.y - a.y;
	}

	public double getX() {
		return x;
	}
	
	

	/*public void setX(double x) {
		this.x = x;
	}*/

	public double getY() {
		return y;
	}
	/*
	public void setY(double y) {
		this.y = y;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecteur other = (Vecteur) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vecteur [x=" + x + ", y=" + y+ "]"; 
	}
	
	public Vecteur addition(Vecteur v1) {
		return new Vecteur(this.x+v1.x,this.y + v1.y);
	}
/*public void additionVoid(Vecteur v1) {
		this.x += v1.x;
		this.y += v1.y;
	}*/
	
	public Vecteur soustraction(Vecteur v1) {
		return new Vecteur(this.x-v1.x,this.y - v1.y);
	}
	/*
	public void soustractionVoid(Vecteur v1) {
		this.x -= v1.x;
		this.y -= v1.y;
	}*/

	public double prodScal(Vecteur v1) {
		return (this.x*v1.x + this.y*v1.y);
	}
	
	public double prodVect(Vecteur v2) {
		return this.x*v2.y-this.y*v2.x;
	}
	
	@Override
	public Vecteur clone() {
		return new Vecteur(this.x,this.y);
	}

	public double norme() {
		return Math.sqrt((this.prodScal(this)));
	}
	
	public double angle(Vecteur v2) {
		double norme1 = this.norme();
		double norme2 = v2.norme();
		if((norme1==0.0) || (norme2==0)) {
			System.out.println("Un des vecteurs est nul ");
			return 0.0;
		}
		double cos = (this.prodScal(v2))/(this.norme()*v2.norme());
		if(cos > 1) {cos = 1.0;}
		if(cos<-1) {cos = -1.0;}
		double acos = Math.acos(cos);

		double prodvect = this.prodVect(v2);
		if(prodvect<0) {
			return acos*(-1);
		}
		return acos;
	}
	
	public Vecteur multiplication(double k) {
		return new Vecteur(this.x*k,this.y*k);
	}
	
	/*
	public void multiplicationVoid(double k) {
		this.x*=k;
		this.y*=k;
	}*/
	
	public Vecteur rotation(double theta) {
		return new Vecteur(this.x*Math.cos(theta)-this.y*Math.sin(theta),this.x*Math.sin(theta)+this.y*Math.cos(theta));
	}
	
	/*
	public void rotationVoid(double theta) {
		double tempx = this.x;
		tempx = this.x*Math.cos(theta)-this.y*Math.sin(theta);
		this.y = this.x*Math.sin(theta)+this.y*Math.cos(theta);
		this.x = tempx;
	}*/
	
	public Vecteur normalisation() {
		if(this.norme() == 0) {
			System.out.println("Vecteur nul");
			return this;
		}
		return this.multiplication(1/this.norme());
	}
	
	public double getDistance(Vecteur v) {
		return Math.sqrt(Math.pow(x-v.getX(),2)+Math.pow(y-v.getY(),2));
	}
	
}
