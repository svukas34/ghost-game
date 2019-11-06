package Modele;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Fantome extends Pion{
	
	protected Caracteristique caracteristique;
	
	public Fantome(Player p, Caracteristique c){
		super(p);
		caracteristique = c;
	}
	
	public Fantome(Player p, Image img, Caracteristique c){
		super(p,img);
		caracteristique = c;
	}
	
	public Fantome(Player p, ImageIcon imgIc, Caracteristique c){
		super(p,imgIc.getImage());
		caracteristique = c;
	}
	
	public Caracteristique getCaracteristique(){
		return this.caracteristique;
	}
}
