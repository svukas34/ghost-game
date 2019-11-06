package Lancement;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import OutilsObservation.Observateur;
import Vue.GhostPan;

public class Main {

	
	public static void main(String[] args){
		//JFrame fenetre = new FenetreChoix();
		
			
		
		Lanceur lanceur = new LancementGhost();
		//((LancementGhost)lanceur).lancerChoixMauvais();
		//fenetre.setContentPane(lanceur.pan);
		//fenetre.setVisible(true);
	}

	/*public static void main(String[] args){		
			
		
		Lanceur lanceur = new LancementGhost();
		//((LancementGhost)lanceur).lancerChoixMauvais();
		lanceur.setContentPane(lanceur.pan);
		lanceur.setVisible(true);
	}*/

	
	
	/*public static void main(String[] args){
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//lancer l'interface ici
				new FenetreChoix();
			}
		});
	}*/
	
	

}
