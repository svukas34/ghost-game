package Controleur;

public class Coordonnees2D {

	protected int x;
	protected int y;
	
	public boolean equals(Coordonnees2D c2d){
		return((c2d.getX() == this.x) && (c2d.getY() == this.y));
	}
	
	public Coordonnees2D(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
