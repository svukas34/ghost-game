package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Controleur.AbstractControleur;
import Controleur.Coordonnees2D;
import Modele.Caracteristique;
import Modele.Case;
import OutilsObservation.Observateur;

public class GhostPan extends JPanel implements Observateur, MouseListener,
		MouseMotionListener, KeyListener {

	protected AbstractControleur controleur;
	Image fond;
	public final int sizeX = 600;
	public final int sizeY = 600;

	public final int tabSizeX;
	public final int tabSizeY;

	Converter converter;

	Coordonnees2D posTabPieceMouv;
	Coordonnees2D posPixPieceMouv;
	boolean pieceEnMouvement;
	int deltaX;
	int deltaY;

	// le mode triche permet d'afficher les mauvais fantomes
	boolean modeTriche;

	public GhostPan(AbstractControleur controleur) {
		// ces 2 méthodes servent à faire en sorte que notre GhostPan objet
		// puisse écouter les touches du clavier
		this.setFocusable(true);
		this.requestFocusInWindow();

		// s'écoute pour les action claviers/souris
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);

		this.controleur = controleur;
		modeTriche = false;
		posTabPieceMouv = new Coordonnees2D(0, 0);
		posPixPieceMouv = new Coordonnees2D(0, 0);
		pieceEnMouvement = false;

		// converter = new
		// Converter(sizeX,sizeY,controleur.getModele().getSizeX(),controleur.getModele().getSizeY());

		if (controleur.getModele() instanceof Modele.ModeleJeuGhost) {
			tabSizeX = ((Modele.ModeleJeuGhost) controleur.getModele())
					.getSizeX();
			tabSizeY = ((Modele.ModeleJeuGhost) controleur.getModele())
					.getSizeY();
		} else {
			System.out.println("pas modele ghost");
			tabSizeX = 6;
			tabSizeY = 6;
		}
		converter = new Converter(sizeX, sizeY, tabSizeX, tabSizeY);

		this.setSize(sizeX, sizeY);
		try {
			fond = new ImageIcon("Grille.jpg").getImage();
		} catch (Exception e) {
			System.out.println("erreur");
		}
		repaint();

		/*
		 * Coordonnees2D p1 = new Coordonnees2D(1,1); Coordonnees2D p2 = new
		 * Coordonnees2D(1,2); controleur.control(p1,p2);
		 */
	}

	public void miseAJour() {
		System.out.println("observateur prevenu");
		repaint();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(fond, 0, 0, this);
		// g.drawImage(this.controleur.getModele().getCaseDuPlateau(new
		// CoordonneesTableau(1,1)).getPiece().getImage(),0,0,this);

		int xPix;
		int yPix;
		Image img;
		for (int i = 0; i < this.controleur.getModele().getSizeX(); i++) {
			for (int j = 0; j < this.controleur.getModele().getSizeY(); j++) {
				if ((pieceEnMouvement) && (posTabPieceMouv.getX() == i)
						&& (posTabPieceMouv.getY() == j)) {
					if(j < this.controleur.getModele().getSizeY()-1)
						j++;
					else
						if(i < this.controleur.getModele().getSizeX()-1){
							j=0;
							i++;
						}else
							break;
				}
				if (this.controleur.getModele()
						.getCaseDuPlateau(new Coordonnees2D(i, j)).getPiece() != null) {
					xPix = converter.xTabToPixel(i);
					yPix = converter.yTabToPixel(j);
					// System.out.println(xPix + " " + yPix );
					try {
						img = this.controleur.getModele()
								.getCaseDuPlateau(new Coordonnees2D(i, j))
								.getPiece().getImage();
						g.drawImage(img, xPix, yPix, this);
					} catch (Exception e) {
						System.out.println("image vide");
					}
				}
			}
		}
		if (pieceEnMouvement) {
			if (this.controleur.getModele().getCaseDuPlateau(posTabPieceMouv)
					.getPiece() != null) {
				try {
					img = this.controleur.getModele()
							.getCaseDuPlateau(posTabPieceMouv).getPiece()
							.getImage();
					g.drawImage(img, posPixPieceMouv.getX(),
							posPixPieceMouv.getY(), this);
				} catch (Exception e) {
					System.out.println("image vide");
				}
			}
		}

	}

	public void paintMauvais(Graphics g) {
		int xPix;
		int yPix;
		for (int i = 0; i < this.controleur.getModele().getSizeX(); i++) {
			for (int j = 0; j < this.controleur.getModele().getSizeY(); j++) {
				Case caseIterator = this.controleur.getModele().getCaseDuPlateau(new Coordonnees2D(i, j));
				if (this.controleur.proprietairePieceEstJoueurActuel(caseIterator)) {
					if (caseIterator.getPiece() instanceof Modele.Fantome) {
						if (((Modele.Fantome) caseIterator.getPiece()).getCaracteristique() == Caracteristique.Mauvais) {

							xPix = converter.xTabToPixel(i)
									+ ((sizeX / tabSizeX) / 2) - 5;
							yPix = converter.yTabToPixel(j)
									+ (3 * (sizeY / tabSizeY) / 4) - 5;
							g.setColor(Color.RED);
							g.fillOval(xPix, yPix, 10, 10);

						}
					}
				}
			}
		}
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {

		// les coordonnées sur le plateau, de la case ou on a cliqué
		int xTab = converter.xPixelToTab(arg0.getX());
		int yTab = converter.yPixelToTab(arg0.getY());

		// si on clique sur une piece (et non pas sur une case vide) && si son
		// proprietaire est le joueur qui joue ce tour ci
		if (this.controleur.proprietairePieceEstJoueurActuel(xTab, yTab)) {
			// on met la piece en mouvement
			pieceEnMouvement = true;

			// on met à jour les coordonnes sur le plateau de la piece en
			// mouvement
			posTabPieceMouv.setXY(xTab, yTab);

			// l'ecart entre l'endroit ou on clique et "l'origine" de la piece
			deltaX = arg0.getX() - converter.xTabToPixel(xTab);
			deltaY = arg0.getY() - converter.yTabToPixel(yTab);

			// l'endroit ou la piece doit etre painte
			posPixPieceMouv.setXY(arg0.getX() - deltaX, arg0.getY() - deltaY);

			/*
			 * System.out.println(posPixPieceMouv.getX());
			 * System.out.println(posPixPieceMouv.getY());
			 * System.out.println(posTabPieceMouv.getX());
			 * System.out.println(posTabPieceMouv.getY());
			 */
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		// l'endroit ou la piece doit etre painte est mis à jour
		posPixPieceMouv.setXY(arg0.getX() - deltaX, arg0.getY() - deltaY);

		// on repaint le plateau
		repaint();
	}

	public void mouseReleased(MouseEvent arg0) {
		// la piece n'est plus en mouvement
		pieceEnMouvement = false;

		// les coordonnées sur le plateau, de la case ou on a laché la souris
		int xTab = converter.xPixelToTab(arg0.getX());
		int yTab = converter.yPixelToTab(arg0.getY());
		Coordonnees2D posTabLastClick = new Coordonnees2D(xTab, yTab);

		// apelle au controleur avec les coordonnees de la case ou était le
		// pion et les coordonnees de la case ou on a laché le pions
		this.controleur.control(posTabPieceMouv, posTabLastClick);

		System.out.println(posTabPieceMouv.getX());
		System.out.println(posTabPieceMouv.getY());
		System.out.println(posTabLastClick.getX());
		System.out.println(posTabLastClick.getY());

	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyChar() == 'f') {
			modeTriche = true;
			System.out.println("mode triche");
			paintMauvais(this.getGraphics());
		}

	}

	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyChar() == 'f') {
			modeTriche = false;
			System.out.println("mode desactiver");
		}
		repaint();
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
