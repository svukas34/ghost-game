package Lancement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import OutilsObservation.Observateur;

public class FenetreChoix extends JFrame implements Observateur{

	private Lanceur lanceur;
	private JPanel panneauChoix;
	
	enum ListeJeux{ ghost; }
	private ListeJeux jeu;
	
	public FenetreChoix(){
		
		panneauChoix = new JPanel();
		panneauChoix.setBackground(Color.BLUE);
		this.setTitle("Projet");
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(panneauChoix);
		System.out.println("ici");
		choixJeu();
		System.out.println("ici2");
		this.setVisible(true);
		
		
	}
	
	public void choixJeu(){
		JPanel p = new JPanel();
		//p.setSize(600,600);
		p.setBackground(Color.ORANGE);
		System.out.println("ici3");
		JButton jeuBouton = new JButton("jeu GHOST");
		jeuBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				jeu = ListeJeux.ghost;
				choixMenu();
				System.out.println("ici");
			}
		});
		p.add(jeuBouton);
		p.setVisible(true);
		panneauChoix.add(p,BorderLayout.CENTER);
		panneauChoix.repaint();
		
	}
	
	public void choixMenu(){
		if (jeu == ListeJeux.ghost)
			this.setTitle("jeu Ghost");
		JPanel p = new JPanel();
		JButton menuBouton = new JButton("nouvelle Partie");
		menuBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				menuModeJeu();
			}
		});
		p.add(menuBouton);
		p.setVisible(true);
		panneauChoix.removeAll();
		panneauChoix.add(p,BorderLayout.CENTER);
		System.out.println("ici4");
		panneauChoix.repaint();
		this.setContentPane(panneauChoix);
	}
	
	public void menuModeJeu(){
		JPanel p = new JPanel();
		JButton modeJeuBouton = new JButton("2 joueurs humains");
		modeJeuBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (jeu == ListeJeux.ghost){
					lanceur=new LancementGhost();
				}
				menuPlacementMauvais();
			}
		});
		p.add(modeJeuBouton);
		p.setVisible(true);
		panneauChoix.removeAll();
		panneauChoix.add(p,BorderLayout.CENTER);
		System.out.println("ici5");
		panneauChoix.repaint();
		this.setContentPane(panneauChoix);
	}
	
	
	
	public void menuPlacementMauvais(){
		if(lanceur != null){
			lanceur.ajouterObservateur(this);
			System.out.println("ntm");
		}
		JPanel p = new JPanel();
		JButton Bouton1 = new JButton("placement classique");
		JButton Bouton2 = new JButton("placement aléatoire");
		JButton Bouton3 = new JButton("placement à la main");
		Bouton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				lanceur.lancer();
			}
		});
		Bouton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((LancementGhost)lanceur).lancerAlea();
			}
		});
		Bouton3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				((LancementGhost)lanceur).lancerChoixMauvais();
			}
		});
		
		p.add(Bouton1,BorderLayout.NORTH);
		p.add(Bouton2,BorderLayout.CENTER);
		p.add(Bouton3,BorderLayout.SOUTH);
		panneauChoix.removeAll();
		panneauChoix.add(p,BorderLayout.CENTER);
		System.out.println("ici5");
		this.setContentPane(panneauChoix);
	}
	

	public void miseAJour() {
		panneauChoix = new JPanel();
		this.setContentPane(lanceur.pan);
	}
	

}
