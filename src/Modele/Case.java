package Modele;

public class Case {
	
	protected Piece piece;
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Case(){}

	public Case(Piece piece) {
		this.piece = piece;
	}
	
	public boolean caseOccupee (){
		if(this.piece == null) /* Pas de pion sur la case */
			return false;
		else
			return true;
	}
	
		
	
}
