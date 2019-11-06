package Modele;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Controleur.Coordonnees2D;

//Cette classe correspond à la classe RegleDuJeuBasique prévu initialement
public class ModeleJeuGhost extends AbstractModele {
	
	protected final int sizeX = 6;
	protected final int sizeY = 6;

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	/*
	 * Consructeur du modele de jeu ghost
	 * il ne prend pas d'argument, on construit donc le jeu le plus "basique"
	 * on met les mauvais fantomes sur les premieres et derneires lignes
	 * (on a pas le choix de placer les mauvais fantomes ici enfaite)
	 */
	public ModeleJeuGhost(){
		
		// on initilaise les 2 joueurs :
		this.joueur1 = new Joueur();
		this.joueur2 = new Joueur();
		// on fait jouer le joueur1
		this.joueurActuel = this.joueur1;
				
		plateau = new Case[sizeX][sizeY];
		//on initialise les cases;
		initTableau();
		// on place les cases de sortie aux extrémités du plateau
		initSorties();
		
	}
	
	// on crer les Fantomes	
	public void setMauvaisClasique(){
		for(int i=1; i<5; i++){
			plateau[i][0].setPiece(new Fantome(joueur1,new ImageIcon("Pion1.png"),Caracteristique.Mauvais));
			plateau[i][1].setPiece(new Fantome(joueur1,new ImageIcon("Pion1.png"),Caracteristique.Bon));
			plateau[i][4].setPiece(new Fantome(joueur2,new ImageIcon("Pion2.png"),Caracteristique.Bon));
			plateau[i][5].setPiece(new Fantome(joueur2,new ImageIcon("Pion2.png"),Caracteristique.Mauvais));
		}	
	}
	
	public void setMauvais(ArrayList<Coordonnees2D> coordMauvais){
		for(Coordonnees2D pos : coordMauvais){
			//si "pos" fait partit de la premeire moitier du tableau de position, ce sont les mauvais appartenant au Joueur1, sinon ce sont les mauvais du joueur 2
			if (coordMauvais.indexOf(pos) < (coordMauvais.size() / 2)) {
				getCaseDuPlateau(pos).setPiece(new Fantome(joueur1,new ImageIcon("Pion1.png"),Caracteristique.Mauvais));
			} else {
				getCaseDuPlateau(pos).setPiece(new Fantome(joueur2,new ImageIcon("Pion2.png"),Caracteristique.Mauvais));
			}
		}
		setBon();
	}
	
	public void setBon(){
		for(int i=1; i<5; i++){
			//si la case n'est pas occupé, on la remplie
			if(plateau[i][0].caseOccupee() == false)
				plateau[i][0].setPiece(new Fantome(joueur1,new ImageIcon("Pion1.png"),Caracteristique.Bon));
			if(plateau[i][1].caseOccupee() == false)
				plateau[i][1].setPiece(new Fantome(joueur1,new ImageIcon("Pion1.png"),Caracteristique.Bon));
			if(plateau[i][4].caseOccupee() == false)
				plateau[i][4].setPiece(new Fantome(joueur2,new ImageIcon("Pion2.png"),Caracteristique.Bon));
			if(plateau[i][5].caseOccupee() == false)
				plateau[i][5].setPiece(new Fantome(joueur2,new ImageIcon("Pion2.png"),Caracteristique.Bon));
		}
	}
	
	public void initTableau(){
		for(int i=0; i<sizeX; i++){
			for(int j=0; j<sizeY; j++){
				plateau[i][j]= new Case();
			}
		}
	}
	
	public void initSorties(){
		plateau[0][0]=new CaseSortie(joueur2);
		plateau[5][0]=new CaseSortie(joueur2);
		plateau[0][5]=new CaseSortie(joueur1);
		plateau[5][5]=new CaseSortie(joueur1);
	}
	
	@Override
	protected boolean aGagne() {
		if(aGagne1() || aGagne2() || aGagne3() )
			return true;
		return false;
	}
	
	// manger tout les bon fantomes
	private boolean aGagne1(){
		int nbBonFantomeAdverse = 0;
		for(int i=0; i<sizeX; i++){
			for(int j=0; j<sizeY; j++){
				if (plateau[i][j].caseOccupee()){
					if ( plateau[i][j].piece.proprietaire != joueurActuel ){
						if (plateau[i][j].piece instanceof Fantome){
							if( ((Fantome)plateau[i][j].piece).caracteristique == Caracteristique.Bon){
								nbBonFantomeAdverse++;
							}
						}
					}
				}
			}
		}
		return (nbBonFantomeAdverse == 0);
	}
	
	//s'est fait manger les mauvais fantomes
	private boolean aGagne2(){
		int nbMauvaisFantomeJoueurActuel = 0;
		for(int i=0; i<sizeX; i++){
			for(int j=0; j<sizeY; j++){
				if (plateau[i][j].caseOccupee()){
					if ( plateau[i][j].piece.proprietaire == joueurActuel ){
						if (plateau[i][j].piece instanceof Fantome){
							if( ((Fantome)plateau[i][j].piece).caracteristique
									== Caracteristique.Mauvais){
								nbMauvaisFantomeJoueurActuel++;
							}
						}
					}
				}
			}
		}
		return (nbMauvaisFantomeJoueurActuel== 0);
	}
	
	//bon fantome a atteint sortie adverse
	private boolean aGagne3(){
		return(testAGagne3SurUneCase(0,0) || testAGagne3SurUneCase(5,0) 
				|| testAGagne3SurUneCase(0,5) || testAGagne3SurUneCase(5,5));	
	}
	
	private boolean testAGagne3SurUneCase(int x, int y){
		if (plateau[x][y].caseOccupee()){
			if( ((Fantome)plateau[x][y].piece).caracteristique == Caracteristique.Bon){
				if (((CaseSortie)plateau[x][y]).proprietaire != joueurActuel){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void mouvement(Coordonnees2D pos1, Coordonnees2D pos2) {
	
		if(pos1.equals(pos2)){
			prevenirObservateur();
		}
		else {
		
						
			Piece p = plateau[pos1.getX()][pos1.getY()].getPiece();		
			plateau[pos1.getX()][pos1.getY()].setPiece(null);
			plateau[pos2.getX()][pos2.getY()].setPiece(p);
			
			if(joueurActuel.equals(joueur1)){
				joueurActuel = joueur2;
			}else{
				joueurActuel = joueur1;
			}
			/**
			 * Emma: NE REAGIT PAS COMME VOULU - QUI EST JOUEUR 1 ET QUI EST JOUEUR 2?
			 * 
			// Disparition d'un bon ou mauvais fantome
			if(((Fantome)getCaseDuPlateau(pos2).piece).caracteristique ==
					Caracteristique.Bon ){
				if(getCaseDuPlateau(pos2).piece.proprietaire == joueur1){
						System.out.println("Joueur 1 a "+this.nbFantB1+"bon fantome");
						this.nbFantB1 --;	
						System.out.println("Joueur 1 a "+this.nbFantB1+"bon fantome");
				}else
						this.nbFantB2 --;
			}else if(((Fantome)getCaseDuPlateau(pos2).piece).caracteristique ==
					Caracteristique.Mauvais )
				if(getCaseDuPlateau(pos2).piece.proprietaire == joueur1)
						this.nbFantM1 --;	
				else
						this.nbFantM2 --;
			*/
			if(aGagne()){
				System.out.println("Vous avez gagné !! ");
				System.exit(1);
			}
			prevenirObservateur();
		}
		
	}

}
