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

}
