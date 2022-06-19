package application.modele;

public class RobotGeneral extends Personnage {

    public RobotGeneral() {
        super(20);
        this.setX(800);
        this.setY(320);
    }

    @Override
    public void attaque(Personnage perso) {
        perso.perdreVie(4);
    }

    @Override
    public void respawn() {
        super.vie.setValue(20);
    }

    public void attaqueDistance(Personnage perso) {
        perso.perdreVie(3);
    }
}
