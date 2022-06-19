package application.modele;

import application.vue.JoueurVue;
import application.vue.RobotFantassinVue;
import application.vue.RobotGeneralVue;

public class Collisions {
    private Environnement env;

    private Joueur joueur;
    private JoueurVue joueurVue;

    private RobotFantassin robotFantassin;
    private RobotFantassinVue robotFantassinVue;


    private RobotGeneral robotGeneral;

    private RobotGeneralVue robotGeneralVue;

    public Collisions(Environnement env, Joueur joueur, JoueurVue  joueurVue)  {
        this.env = env;
        this.joueur = joueur;
        this.joueurVue = joueurVue;
    }

    public Collisions(Environnement env, RobotFantassin robotFantassin, RobotFantassinVue robotFantassinVue)  {
        this.env = env;
        this.robotFantassin = robotFantassin;
        this.robotFantassinVue = robotFantassinVue;
    }

    public Collisions(Environnement env, RobotGeneral robotGeneral, RobotGeneralVue robotGeneralVue)  {
        this.env = env;
        this.robotGeneral = robotGeneral;
        this.robotGeneralVue = robotGeneralVue;
    }

    public void collisionJoueur() {
        if (env.mapProperty().get(env.getTileBas(joueur.getX(), joueur.getY())) == 1 ||
                env.mapProperty().get(env.getTileBas(joueur.getX(), joueur.getY())) == 2 ||
                env.mapProperty().get(env.getTileBas(joueur.getX(), joueur.getY())) == 3 ||
                env.mapProperty().get(env.getTileBas(joueur.getX(), joueur.getY())) == 4 ||
                env.mapProperty().get(env.getTileBas(joueur.getX(), joueur.getY())) == 5 ||
                env.mapProperty().get(env.getTileBas(joueur.getX(), joueur.getY())) == 6) {


            joueur.setY(joueur.getY() - 5);
            joueur.setSaute(false);
        }

        if (env.mapProperty().get(env.getTileBas(joueur.getX(), joueur.getY())) == 7) {
            this.joueur.meurt();
            joueurVue.setVisible(false);
            joueur.setY(joueur.getY() - 5);
        }

        if (env.mapProperty().get(env.getTileBasDroite(joueur.getX(), joueur.getY())) == 1 ||
                env.mapProperty().get(env.getTileBasDroite(joueur.getX(), joueur.getY())) == 3) {
            System.out.println("TOUCHE BAS DROITE");
            joueur.setY(joueur.getY() - 5);
        }

        if (env.mapProperty().get(env.getTileBasGauche(joueur.getX(), joueur.getY())) == 1 ||
                env.mapProperty().get(env.getTileBasDroite(joueur.getX(), joueur.getY())) == 3) {
            System.out.println("TOUCHE BAS GAUCHE");
            joueur.setY(joueur.getY() - 5);
        }

        if (env.mapProperty().get(env.getTileHautGauche(joueur.getX(), joueur.getY())) == 2) {
            System.out.println("TOUCHE HAUT GAUCHE");
            joueur.setX(joueur.getX() + 5);
        }

        if (env.mapProperty().get(env.getTileHautDroite(joueur.getX(), joueur.getY())) == 2) {
            System.out.println("TOUCHE HAUT DROITE");
            joueur.setX(joueur.getX() - 5);
        }
    }

    public void collisionRobotFantassin() {
        if (env.mapProperty().get(env.getTileBas(robotFantassin.getX(), robotFantassin.getY())) == 1 ||
                env.mapProperty().get(env.getTileBas(robotFantassin.getX(), robotFantassin.getY())) == 2 ||
                env.mapProperty().get(env.getTileBas(robotFantassin.getX(), robotFantassin.getY())) == 3 ||
                env.mapProperty().get(env.getTileBas(robotFantassin.getX(), robotFantassin.getY())) == 4 ||
                env.mapProperty().get(env.getTileBas(robotFantassin.getX(), robotFantassin.getY())) == 5 ||
                env.mapProperty().get(env.getTileBas(robotFantassin.getX(), robotFantassin.getY())) == 6) {

            robotFantassin.setY(robotFantassin.getY() - 5);

        }
        if (env.mapProperty().get(env.getTileBas(robotFantassin.getX(), robotFantassin.getY())) == 7) {
            this.robotFantassin.meurt();
            robotFantassin.setY(robotFantassin.getY() - 5);
        }


        if (env.mapProperty().get(env.getTileBasDroite(robotFantassin.getX(), robotFantassin.getY())) == 1 ||
                env.mapProperty().get(env.getTileBasDroite(robotFantassin.getX(), robotFantassin.getY())) == 3) {
            robotFantassin.setY(robotFantassin.getY() - 5);
        }

        if (env.mapProperty().get(env.getTileBasGauche(robotFantassin.getX(), robotFantassin.getY())) == 1 ||
                env.mapProperty().get(env.getTileBasDroite(robotFantassin.getX(), robotFantassin.getY())) == 3) {
            robotFantassin.setY(robotFantassin.getY() - 5);
        }

        if (env.mapProperty().get(env.getTileHautGauche(robotFantassin.getX(), robotFantassin.getY())) == 2) {
            robotFantassin.setX(robotFantassin.getX() + 5);
        }

        if (env.mapProperty().get(env.getTileHautDroite(robotFantassin.getX(), robotFantassin.getY())) == 2) {
            robotFantassin.setX(robotFantassin.getX() - 5);
        }
    }

    public void collisionRobotGeneral() {
        if (env.mapProperty().get(env.getTileBasGeneral(robotGeneral.getX(), robotGeneral.getY())) == 1 ||
                env.mapProperty().get(env.getTileBasGeneral(robotGeneral.getX(), robotGeneral.getY())) == 2 ||
                env.mapProperty().get(env.getTileBasGeneral(robotGeneral.getX(), robotGeneral.getY())) == 3 ||
                env.mapProperty().get(env.getTileBasGeneral(robotGeneral.getX(), robotGeneral.getY())) == 4 ||
                env.mapProperty().get(env.getTileBasGeneral(robotGeneral.getX(), robotGeneral.getY())) == 5 ||
                env.mapProperty().get(env.getTileBasGeneral(robotGeneral.getX(), robotGeneral.getY())) == 6) {

            robotGeneral.setY(robotGeneral.getY() - 5);

        }
        if (env.mapProperty().get(env.getTileBasGeneral(robotGeneral.getX(), robotGeneral.getY())) == 7) {
            this.robotGeneral.meurt();
            robotGeneral.setY(robotGeneral.getY() - 5);
        }


        if (env.mapProperty().get(env.getTileBasDroiteGeneral(robotGeneral.getX(), robotGeneral.getY())) == 1 ||
                env.mapProperty().get(env.getTileBasDroiteGeneral(robotGeneral.getX(), robotGeneral.getY())) == 3) {
            robotGeneral.setY(robotGeneral.getY() - 5);
        }

        if (env.mapProperty().get(env.getTileBasGaucheGeneral(robotGeneral.getX(), robotGeneral.getY())) == 1 ||
                env.mapProperty().get(env.getTileBasDroiteGeneral(robotGeneral.getX(), robotGeneral.getY())) == 3) {
            robotGeneral.setY(robotGeneral.getY() - 5);
        }

        if (env.mapProperty().get(env.getTileHautGauche(robotGeneral.getX(), robotGeneral.getY())) == 2) {
            robotGeneral.setX(robotGeneral.getX() + 5);
        }

        if (env.mapProperty().get(env.getTileHautDroiteGeneral(robotGeneral.getX(), robotGeneral.getY())) == 2) {
            robotGeneral.setX(robotGeneral.getX() - 5);
        }
    }

}
