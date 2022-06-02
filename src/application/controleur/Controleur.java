package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;
import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.Personnage;
import application.modele.RobotFantassin;
import application.vue.CarteVue;
import application.vue.JoueurVue;
import application.vue.RobotFantassinVue;
import application.vue.VieVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controleur implements Initializable {

	private Environnement env;
	private Personnage joueur;
	private Personnage robotFantassin;

	private Timeline gameLoop;
	private int temps;
	@FXML
	private BorderPane root;
	@FXML
	private TilePane panneauJeu;
	@FXML
	private Pane paneCentral;
	private ObservableVie obsVie;
	private JoueurVue joueurVue;
	private VieVue vieVue;
	private CarteVue carteVue;
	private RobotFantassinVue robotFantassinVue;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initialiserVariables();
		faireBind();
		insererImagesPaneCentral();
		initGameLoop();

		gameLoop.play();
		root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(joueur, joueurVue));
	}

	private void initGameLoop() {
		gameLoop = new Timeline();
		temps = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.002), // 1 frame = 0.002 seconde
				(ev -> {

					if (temps % 5 == 0) {
						faireTour();
						afficherInfosEnConsole();
					}
					temps++;
				}));

		gameLoop.getKeyFrames().add(kf);
	}

	private void faireTour() {
		joueur.setY(joueur.getY() + 5);
		robotFantassin.setY(robotFantassin.getY() + 5);

		if (joueur.getX() > robotFantassin.getX() && (joueur.getX() - robotFantassin.getX()) > 50) {
			robotFantassin.setX(robotFantassin.getX() + 1);
			robotFantassin.setDirection(1);
		} else if (joueur.getX() < robotFantassin.getX() && (robotFantassin.getX() - joueur.getX()) > 50) {
			robotFantassin.setX(robotFantassin.getX() - 1);
			robotFantassin.setDirection(-1);
		} else {
			if (Math.abs(joueur.getY() - robotFantassin.getY()) < 50 && temps % 500 == 0)
				joueur.perdreVie(1);
		}

		if (joueur.getY() > 320) {
			joueur.setY(joueur.getY() - 5);
		}

		if (robotFantassin.getY() > 320) {
			robotFantassin.setY(robotFantassin.getY() - 5);
		}

		joueurVue.setScaleX(joueur.getDirection());
		robotFantassinVue.setScaleX(robotFantassin.getDirection());
	}

	private void initialiserVariables() {
		// création de l'environnement
		this.env = new Environnement();

		// création de la vue de la map
		this.carteVue = new CarteVue(env, panneauJeu, 56, 31);

		// Création du joueur et de la vue du joueur
		this.joueur = new Joueur();
		this.joueurVue = new JoueurVue();

		// Création de la vue de la vie avec l'observable
		this.vieVue = new VieVue(carteVue);
		this.obsVie = new ObservableVie(vieVue);
		this.joueur.vieProperty().addListener(obsVie);

		// Création d'un fantassin et de sa vue
		this.robotFantassin = new RobotFantassin();
		this.robotFantassinVue = new RobotFantassinVue();
	}

	private void faireBind() {
		// Bind Joueur
		this.joueurVue.translateXProperty().bindBidirectional(this.joueur.xProperty());
		this.joueurVue.translateYProperty().bindBidirectional(this.joueur.yProperty());

		// Bind RobotFantassin
		this.robotFantassinVue.translateXProperty().bindBidirectional(this.robotFantassin.xProperty());
		this.robotFantassinVue.translateYProperty().bindBidirectional(this.robotFantassin.yProperty());
	}

	private void insererImagesPaneCentral() {
		// Ajout des vue au panneau central
		this.paneCentral.getChildren().add(joueurVue);
		this.paneCentral.getChildren().add(vieVue);
		this.paneCentral.getChildren().add(robotFantassinVue);
	}

	private void afficherInfosEnConsole() {
		System.out.println("Joueur x:" + joueur.getX());
		System.out.println("Joueur y:" + joueur.getY());
		System.out.println("Direction joueur:" + joueur.getDirection());
		System.out.println("Robot x:" + robotFantassin.getX());
		System.out.println("Robot y:" + robotFantassin.getY());
		System.out.println("Direction robot:" + robotFantassin.getDirection());
		System.out.println("Vie du joueur: " + joueur.vieProperty().getValue());
	}
}
