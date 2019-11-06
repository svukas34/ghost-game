package Vue;

import Controleur.Coordonnees2D;

public class Converter {

	private int vueSizeX;
	private int vueSizeY;
	private int modeleSizeX;
	private int modeleSizeY;
	
	public Converter(int vueSizeX, int vueSizeY, int modeleSizeX, int modeleSizeY) {
		super();
		this.vueSizeX = vueSizeX;
		this.vueSizeY = vueSizeY;
		this.modeleSizeX = modeleSizeX;
		this.modeleSizeY = modeleSizeY;
	}
	
	public int xTabToPixel(int x){
		return (x*vueSizeX/modeleSizeX);
	}
	
	public int yTabToPixel(int y){
		return (vueSizeY - y*vueSizeY/modeleSizeY - vueSizeY/modeleSizeY);
	}
	
	public Coordonnees2D tabToPixel(int x, int y){
		return new Coordonnees2D(xTabToPixel(x),yTabToPixel(y)); 
	}
	
	public Coordonnees2D tabToPixel(Coordonnees2D tabCoord){
		return tabToPixel(tabCoord.getX(), tabCoord.getY());
	}
	
	public int xPixelToTab(int xPix){
		return (xPix * modeleSizeX/vueSizeX);
	}
	
	public int yPixelToTab(int yPix){
		return ((modeleSizeY-1) - yPix * modeleSizeY/vueSizeY);
	}
	
	public Coordonnees2D pixelToTab(int x, int y){
		return new Coordonnees2D(xPixelToTab(x),yPixelToTab(y));
	}
	
	public Coordonnees2D pixelToTab(Coordonnees2D pixCoord){
		return pixelToTab(pixCoord.getX(), pixCoord.getY());
	}
	
}
