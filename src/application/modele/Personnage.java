package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {

	private SimpleIntegerProperty X;
	private SimpleIntegerProperty Y;
	public SimpleIntegerProperty direction;
	private Environnement env;
	private SimpleIntegerProperty vie;
	private boolean saute;
	private int trajectoire;

	public Personnage(Environnement env, int vie) {
		this.env = env;
		this.X = new SimpleIntegerProperty();
		this.Y = new SimpleIntegerProperty();
		this.direction = new SimpleIntegerProperty(1);
		this.vie = new SimpleIntegerProperty(vie);
		this.saute = false;
		this.trajectoire = 0;
	}
	
//	public void allerAGauche() {
//		this.setX(getX()-1);
//	}
//	
//	public void allerADroite() {
//		this.setX(getX()+1);
//	}

	public void gravite() {
		this.setY(this.getY()+5);
	}

	public int getX() {
		return this.X.getValue();
	}

	public int getY() {
		return this.Y.getValue();
	}

	public int getDirection() {
		return this.direction.getValue();
	}

	public SimpleIntegerProperty directionProperty() {
		return this.direction;
	}

	public SimpleIntegerProperty xProperty() {
		return this.X;
	}

	public SimpleIntegerProperty yProperty() {
		return this.Y;
	}
	
	public int getVie() {
		return this.vie.getValue();
	}

	public void setX(int valeur) {
		this.X.setValue(valeur);
	}

	public void setY(int valeur) {
		this.Y.setValue(valeur);
	}

	public void setDirection(int valeur) {
		this.direction.setValue(valeur);
	}
	
	public final IntegerProperty vieProperty() {
		return this.vie;
	}
	
	public void perdreVie(int nombre) {
		if(vie.getValue() - nombre >= -4)
			vie.setValue(vie.getValue() - nombre);
	}
	
	public void ajouterVie(int nombre) {
		if(vie.getValue() + nombre <= 12)
			vie.setValue(vie.getValue() + nombre);
	}
	
	public void setVie(int nombre) {
		vie.setValue(nombre);
	}
	
	public abstract void attaque(Personnage perso);
	
	public boolean estVivant() {
		return this.getVie()>0;
	}
	
	public void meurt() {
		this.vie.setValue(0);
	}
	
	public boolean getSaute() {
		return this.saute;
	}
	
	public void setSaute(boolean booleen) {
		this.saute = booleen;
	}
	
	public int getTrajectoire() {
		return this.trajectoire;
	}
	
	public void setTrajectoire(int traj) {
		this.trajectoire = traj;
	}
	
}
