package application.modele;

public class RobotFantassin extends Personnage{

	private static Environnement env;
	
	public RobotFantassin() {
		super(env, 10);
		this.setX(300);
		this.setY(320);
	}

	@Override
	public void attaque(Personnage perso) {
		perso.perdreVie(4);
	}

}
