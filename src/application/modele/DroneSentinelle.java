package application.modele;

public class DroneSentinelle extends Personnage {

	public DroneSentinelle() {
		super(6);
		this.setX(200);
		this.setY(60);
	}

	@Override
	public void attaque(Personnage perso) {
		perso.perdreVie(3);
	}

	@Override
	public void respawn() {
		super.vie.setValue(6);
		this.setX(1400);
		this.setY(60);
	}

}
