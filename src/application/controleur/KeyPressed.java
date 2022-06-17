package application.controleur;

import application.modele.Personnage;
import application.vue.JoueurVue;
import application.vue.RobotFantassinVue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressed implements EventHandler<KeyEvent> {

	private Personnage joueur;
	private Personnage robotFantassin;
	private JoueurVue joueurVue;
	private RobotFantassinVue robotFantassinVue;

	private Controleur controleur;

	public KeyPressed(Personnage joueur2, JoueurVue joueurVue, Controleur controleur) {
		this.joueur = joueur2;
		this.joueurVue = joueurVue;
		this.controleur = controleur;
	}

	public void handle(KeyEvent event) {

		if (joueur.estVivant())

			switch(event.getCode()) {

				case Q:
					DeplacementAnimation.allerGauche(joueur, joueurVue);
					break;
				case LEFT:
					DeplacementAnimation.allerGauche(joueur, joueurVue);
					break;
				case D:
					DeplacementAnimation.allerDroite(joueur, joueurVue);
					break;
				case RIGHT:
					DeplacementAnimation.allerDroite(joueur, joueurVue);
					break;
				case Z:
					DeplacementAnimation.sauter(joueur, joueurVue);
					break;
				case UP:
					DeplacementAnimation.sauter(joueur, joueurVue);
					break;
				case SPACE:
					DeplacementAnimation.sauter(joueur, joueurVue);
					break;
				case F1:
					joueur.perdreVie(1);
					System.out.println("Le joueur perd un demi coeur.");
					break;
				case F2:
					joueur.ajouterVie(1);
					System.out.println("Le joueur gagne un demi coeur.");
					break;
				case F4:
					joueur.setVie(9999);
					System.out.println("Mode invincible active.");
					break;
				case TAB:
					controleur.getInventaireVue().affichageInventaire();
					break;
				case G:
					joueurVue.setImage(joueurVue.getImages().getImage(10));
				default:
					break;

			}

	}

}