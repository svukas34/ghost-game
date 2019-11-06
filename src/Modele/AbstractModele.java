package Modele;

import java.util.ArrayList;

import Controleur.Coordonnees2D;
import OutilsObservation.Observable;
import OutilsObservation.Observateur;

//cette classe correspond en quelques sortes à l'interface Regle Du Jeu prévu à la base
public abstract class AbstractModele implements Observable {

	protected Case[][] plateau;
	protected Player joueur1;
	protected Player joueur2;
	
	//Va peut etre servir pour savoir quel joueur est en train de jouer:
	protected Player joueurActuel;
	
	protected ArrayList<Observateur> listeObservateurs = new ArrayList<Observateur>();
	
	public int getSizeX(){return 0;}
	public int getSizeY(){return 0;}
	
	// pour l'instant protected mais peut etre public ?
	// il faudra peut etre mettre des argumet a la fonction ?
	protected abstract boolean aGagne();
	
	public abstract void mouvement(Coordonnees2D pos1, Coordonnees2D pos2);
	
	public Case[][] getPlateau() {
		return plateau;
	}
	
	public Case getCaseDuPlateau(Coordonnees2D pos){
		return this.plateau[pos.getX()][pos.getY()];
	}
	
	public Case getCaseDuPlateau(int x, int y){
		return this.plateau[x][y];
	}
	
	
	public void ajouterObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		listeObservateurs.add(obs);

	}

	public void supprimerObservateurs() {
		// TODO Auto-generated method stub
		listeObservateurs = new ArrayList<Observateur>();
	}

	public void prevenirObservateur() {
		// TODO Auto-generated method stub
		for(Observateur obs : listeObservateurs){
			obs.miseAJour();
		}
	}
	public Player getJoueurActuel() {
		return joueurActuel;
	}

}
