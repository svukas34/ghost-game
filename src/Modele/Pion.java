package Modele;

import java.awt.Image;
import java.util.ArrayList;

import Controleur.Coordonnees2D;

public class Pion extends Piece {

	public Pion(Player p) {
		proprietaire = p;
	}

	public Pion(Player p, Image img) {
		this.image = img;
		proprietaire = p;
	}

	// cette méthode renvoie les coordonnées que peut atteindre un pions, c'est
	// à dire : la case en dessous de pos, la case au dessus de pos, la case à
	// droite de pos et la case à gauche de pos.
	//
	// ATTENTION : cette méthode ne vérifie pas si les case atteignable sont
	// bien dans le plateau, c'est le controleur qui devras s'en charger
	@Override
	public ArrayList<Coordonnees2D> listeMvmtPossibles(Coordonnees2D pos) {
		ArrayList<Coordonnees2D> lMvmt = new ArrayList<Coordonnees2D>();
		lMvmt.add(new Coordonnees2D(pos.getX() + 1, pos.getY()));
		lMvmt.add(new Coordonnees2D(pos.getX() - 1, pos.getY()));
		lMvmt.add(new Coordonnees2D(pos.getX(), pos.getY() + 1));
		lMvmt.add(new Coordonnees2D(pos.getX(), pos.getY() - 1));
		return lMvmt;
	}

}
