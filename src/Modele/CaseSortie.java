package Modele;

public class CaseSortie extends Case {

	protected Player proprietaire;
	
	public CaseSortie(Player prop){
		proprietaire = prop;
		this.piece = null;
	}

}
