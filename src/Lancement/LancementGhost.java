package Lancement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleur.ControleurJeuGhost;
import Controleur.Coordonnees2D;
import Modele.Caracteristique;
import Modele.Fantome;
import Modele.ModeleJeuGhost;
import OutilsObservation.Observable;
import OutilsObservation.Observateur;
import Vue.Converter;
import Vue.GhostPan;

public class LancementGhost extends Lanceur implements Observateur {
	
	private ArrayList<Observateur> listeObs;
	
	private ArrayList<Coordonnees2D> coordMauvais;
	
	public LancementGhost() {
		super();
		System.out.println("ok 1");
		listeObs = new ArrayList<Observateur>();
		this.lancerChoixMauvais();
		//this.lancer();
		this.setContentPane(pan);
	}
	
	@Override
	public void lancer() {
		// TODO Auto-generated method stub
		//JFrame fen = new JFrame();
		
		modele = new ModeleJeuGhost();
		((ModeleJeuGhost)modele).setMauvaisClasique();
		controleur = new ControleurJeuGhost(modele);
		GhostPan damierGhost = new GhostPan(controleur);
		modele.ajouterObservateur(damierGhost);
		pan = damierGhost;
				
		//fen.setContentPane(damierGhost);
		
		
		/*JPanel p = new JPanel();
		p.setBackground(Color.ORANGE);
		fen.setContentPane(p);*/

	}
	
	public void lancerChoixMauvais(){
		coordMauvais = new ArrayList<Coordonnees2D>();
		modele = new ModeleJeuGhost();
		//lance un JPanel pour choisir
		PanelChoix p = new PanelChoix();
		pan = p;
		pan.setBackground(Color.black);
		prevenirObservateur();
		p.ajouterObservateur(this);

	}
	
	class PanelChoix extends JPanel implements MouseListener, Observable{

		private Image fond;
		
		private ArrayList<Observateur> listeObsPan;
		
		Converter converter;
		
		PanelChoix(){
			this.setSize(600,600);
			System.out.println(this.getHeight());
			fond = new ImageIcon("Grille.jpg").getImage();
			listeObsPan = new ArrayList<Observateur>();
			converter = new Converter(this.getHeight(), this.getWidth(),modele.getSizeX(),modele.getSizeY());
			this.addMouseListener(this);
		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(fond, 0, 0, this);
		}
		
		public void paintPion(Image img, Graphics g, int tabX, int tabY){
			g.drawImage(img, converter.xTabToPixel(tabX), converter.yTabToPixel(tabY), this);
		}
		
		public void paintPion(String str, Graphics g, int tabX, int tabY){
			g.drawImage(new ImageIcon(str).getImage(), converter.xTabToPixel(tabX), converter.yTabToPixel(tabY), this);
		}
		
		public void paintAllPionsJ1(Graphics g){
			for(int i=1; i<5; i++){
				paintPion(new ImageIcon("Pion1.png").getImage(),g,i,0);
				paintPion(new ImageIcon("Pion1.png").getImage(),g,i,1);	
			}	
		}
		
		public void paintAllPionsJ2(Graphics g){
			for(int i=1; i<5; i++){
				paintPion(new ImageIcon("Pion2.png").getImage(),g,i,4);
				paintPion(new ImageIcon("Pion2.png").getImage(),g,i,5);
			}
		}
		
		public void mouseClicked(MouseEvent e) {
			int tabX = converter.xPixelToTab(e.getX());
			if(tabX > 0 && tabX < 5){

				//si ce sont les pions du joueur 1 (les 4 premeirs sont ceux du joueur 1)
				if(coordMauvais.size() < 4){
					int tabY = converter.yPixelToTab(e.getY());
					if(tabY < 2){
						Coordonnees2D pos = new Coordonnees2D(tabX, tabY);
						if (coordMauvais.contains(pos)) {
							System.out.println(" deja positionner ici ! ");
						} else {
							System.out.println(" Pion1 bien mis ");
							coordMauvais.add(pos);
							System.out.println(tabX +" : "+ tabY);

							paintPion("Pion1.png", this.getGraphics(), tabX,
									tabY);
						}
					} else {
						System.out.println(" le joueur 1 ne peut pas placer ici !! ");
					}
					//sinon, si ce sont les pions du joueur 2
				} else {
					int tabY = converter.yPixelToTab(e.getY());
					if(tabY > 3){
						Coordonnees2D pos = new Coordonnees2D(tabX, tabY);
						if (coordMauvais.contains(pos)) {
							System.out.println(" deja positionner ici ! ");
						} else {
							System.out.println(" Pion2 bien mis ");
							coordMauvais.add(pos);
							paintPion("Pion2.png", this.getGraphics(), tabX,
									tabY);
						}
					} else {
						System.out.println(" le joueur 2 ne peut pas placer ici !! ");
					}
				}
			} else {
				System.out.println(" mauvaise position !! ");
			}
			
			
			if (coordMauvais.size() == 4) {
				paintAllPionsJ1(this.getGraphics());
			}
			if (coordMauvais.size() == 8) {
				paintAllPionsJ2(this.getGraphics());
				prevenirObservateur();
			}
		}

		public void mouseEntered(MouseEvent arg0) {}

		public void mouseExited(MouseEvent arg0) {}

		public void mousePressed(MouseEvent arg0) {}

		public void mouseReleased(MouseEvent arg0) {}

		public void ajouterObservateur(Observateur obs) {
			listeObsPan.add(obs);
		}

		public void supprimerObservateurs() {
			listeObsPan = new ArrayList<Observateur>();
			
		}

		public void prevenirObservateur() {
			for(Observateur obs : this.listeObsPan){
				obs.miseAJour();
			}
		}
		
	}
	
	public void lancerAlea() {

	}

	public void miseAJour() {
		((ModeleJeuGhost)modele).setMauvais(coordMauvais);
		controleur = new ControleurJeuGhost(modele);
		GhostPan damierGhost = new GhostPan(controleur);
		modele.ajouterObservateur(damierGhost);
		// a voir avec le ajouterObservateur si sa marche !!!!!!!!!
		pan = damierGhost;
		//System.out.println("ici");
		super.setContentPane(damierGhost);
		prevenirObservateur();
	}

	public void ajouterObservateur(Observateur obs) {
		listeObs.add(obs);
	}

	public void supprimerObservateurs() {
		listeObs = new ArrayList<Observateur>();
		
	}

	public void prevenirObservateur() {
		for(Observateur obs : this.listeObs){
			obs.miseAJour();
		}
	}


}
