package application.modele;

import javafx.beans.property.SimpleIntegerProperty;

public class RobotFantassin extends Personnage{

	private SimpleIntegerProperty X;
	private SimpleIntegerProperty Y;
	private int vitesseD;
	private int vie;
	private static Environnement env;
	
	public RobotFantassin() {
		super(env);
		this.X = new SimpleIntegerProperty();
		this.Y = new SimpleIntegerProperty();
		this.vie = 10;
		this.vitesseD = 6;
	}
	
	public int getVie() {
		return this.vie;
	}

	public int getVitesseD() {
		return this.vitesseD;
	}
	

}
