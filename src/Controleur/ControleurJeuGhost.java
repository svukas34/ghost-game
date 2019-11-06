package Controleur;

import java.util.ArrayList;

import Modele.AbstractModele;
import Modele.Case;

public class ControleurJeuGhost extends AbstractControleur{

	
	public ControleurJeuGhost(AbstractModele modele) {
		super(modele);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void control(Coordonnees2D pos1, Coordonnees2D pos2) {
		// TODO Auto-generated method stub
		
		//si le mouvement est possible on effectue le mouvement dans le modele
	
		if (mouvementPossible(pos1,pos2)){
			System.out.println("mvmt possible");
			modele.mouvement(pos1,pos2);
		}
		//si il est pas possible, on remet le pion à sa case d'origine
		else{
			modele.mouvement(pos1,pos1);
		}
	}
	
	/*
	 * si la piece à la case pos1 permet le mouvement
	 * et si il n'y a rien sur la case pos2
	 * et verifier que pos2 est bien sur le plateau (>0 et <6)
	 */
	public boolean mouvementPossible(Coordonnees2D posTab1, Coordonnees2D posTab2){
		//si il y a bien une piece sur la case posTab1
		if(this.modele.getCaseDuPlateau(posTab1).caseOccupee()){
			//si posTab2 est bien a l'interieur du plateau
			if(posTab2.getX()>=0 && posTab2.getX()<this.modele.getSizeX()){
				if(posTab2.getY()>=0 && posTab2.getY()<this.modele.getSizeY()){
					ArrayList<Coordonnees2D> mvmt = this.modele.getCaseDuPlateau(posTab1).getPiece().listeMvmtPossibles(posTab1);
					// si la case de coordonnées posTab2 est bien atteignable par la piece issu de la case posTab1
					for(Coordonnees2D iterator : mvmt){
						if( posTab2.equals(iterator)){
							if (modele.getCaseDuPlateau(posTab2).caseOccupee()){
								if(modele.getCaseDuPlateau(posTab1).getPiece().getProprietaire().equals(modele.getCaseDuPlateau(posTab2).getPiece().getProprietaire())) {
									return false;
								}
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
}
