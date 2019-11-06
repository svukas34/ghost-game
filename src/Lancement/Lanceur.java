package Lancement;

import Controleur.AbstractControleur;
import Modele.AbstractModele;
import OutilsObservation.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Lanceur extends JFrame implements Observable{
	
	public Lanceur(){
		System.out.println("ok 2");
		this.setTitle("Projet");
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	protected AbstractModele modele;
	protected AbstractControleur controleur;
	protected JPanel pan;
	
	public abstract void lancer();
}
