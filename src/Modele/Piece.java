package Modele;

import java.awt.Image;
import java.util.ArrayList;

import Controleur.Coordonnees2D;

public abstract class Piece {

	Image image;

	protected Player proprietaire;

	// Cette méthode renvoi une Liste de coordonnees que peut atteindre une
	// piece
	public abstract ArrayList<Coordonnees2D> listeMvmtPossibles(Coordonnees2D pos);

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Player getProprietaire() {
		return proprietaire;
	}

	
}
