package application.modele;

public class RobotFantassin extends Personnage{

	private static Environnement env;

	public RobotFantassin() {
		super(10);
		this.setX(950);
		this.setY(620);
	}

	@Override
	public void attaque(Personnage perso) {
		perso.perdreVie(4);
	}

	@Override
	public void respawn() {
		super.vie.setValue(10);
		this.setX(50);
		this.setY(600);
	}

}
