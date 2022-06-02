package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {

	private SimpleIntegerProperty X;
	private SimpleIntegerProperty Y;
	public SimpleIntegerProperty direction;
	private Environnement env;
	private SimpleIntegerProperty vieProperty;

	public Personnage(Environnement env) {
		this.env = env;
		this.X = new SimpleIntegerProperty();
		this.Y = new SimpleIntegerProperty();
		this.direction = new SimpleIntegerProperty(1);
		this.vieProperty = new SimpleIntegerProperty(12);
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
		return this.vieProperty;
	}
	
	public void perdreVie(int nombre) {
		if(vieProperty.getValue() - nombre > 0)
			vieProperty.setValue(vieProperty.getValue() - nombre);
	}
	
	public void ajouterVie(int nombre) {
		if(vieProperty.getValue() + nombre <= 12)
			vieProperty.setValue(vieProperty.getValue() + nombre);
	}
	
	
}
