package Controleur;

import Modele.AbstractModele;
import Modele.Case;

public abstract class AbstractControleur {
	Coordonnees2D positionInitial;
	Coordonnees2D positonFinal;

	protected AbstractModele modele;
	
	public AbstractControleur(AbstractModele modele){
		this.modele = modele;
	}
	
	public abstract void control(Coordonnees2D pos1, Coordonnees2D pos2);

	public AbstractModele getModele() {
		return modele;
	}
	//verifie si il ya un pion sur la case, puis si elle a un proprietaire et enfin si le proprietaire est le joueur qui a la main
	public boolean proprietairePieceEstJoueurActuel(Case casePlateau){
		if (casePlateau.caseOccupee()) {
			if (casePlateau.getPiece().getProprietaire() != null){
				if (casePlateau.getPiece().getProprietaire().equals(this.modele.getJoueurActuel())){
					return true;
				}
			} else{		System.out.println("pas de proprietaire"); }
		} else {	System.out.println("pas de piece (case vide)"); }
		return false;
	}
	
	public boolean proprietairePieceEstJoueurActuel(int xTab, int yTab){
		return(proprietairePieceEstJoueurActuel(this.modele.getCaseDuPlateau(xTab,yTab)));
	}
	
}
