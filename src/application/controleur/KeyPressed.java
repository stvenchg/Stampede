package application.controleur;

import application.modele.Personnage;
import application.vue.*;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class KeyPressed implements EventHandler<KeyEvent> {

	private Personnage joueur;
	private Personnage robotFantassin;
	private JoueurVue joueurVue;
	private RobotFantassinVue robotFantassinVue;

	private DroneSentinelleVue droneSentinelleVue;

	private RobotGeneralVue robotGeneralVue;

	private VieVue vieVue;

	private Controleur controleur;

	@FXML
	private Pane pauseMenu;

	private Timeline Gameloop;

	public KeyPressed(Personnage joueur2, JoueurVue joueurVue, Controleur controleur, Pane pauseMenu, Timeline Gameloop, RobotFantassinVue robotFantassinVue, DroneSentinelleVue droneSentinelleVue, RobotGeneralVue robotGeneralVue, VieVue vieVue) {
		this.joueur = joueur2;
		this.joueurVue = joueurVue;
		this.controleur = controleur;
		this.pauseMenu = pauseMenu;
		this.Gameloop = Gameloop;
		this.robotGeneralVue = robotGeneralVue;
		this.droneSentinelleVue = droneSentinelleVue;
		this.robotFantassinVue = robotFantassinVue;
		this.vieVue = vieVue;
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
				case E:
					controleur.getInventaireVue().affichageInventaire();
					break;
				case G:
					joueurVue.setImage(joueurVue.getImages().getImage(10));
				case ESCAPE:
					if (pauseMenu.isVisible()) {
						pauseMenu.setVisible(false);
						Gameloop.play();
						joueurVue.setVisible(true);
						robotFantassinVue.setVisible(true);
						vieVue.setVisible(true);
					} else {
						pauseMenu.setVisible(true);
						Gameloop.pause();
						joueurVue.setVisible(false);
						robotFantassinVue.setVisible(false);
						vieVue.setVisible(false);
					}
					break;
				default:
					break;

			}

	}

}