package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;
import application.controleur.inventaire.ObservateurObjet;
import application.controleur.inventaire.ObservateurResources;
import application.controleur.map.ObservateurMap;
import application.modele.*;
import application.modele.objet.armes.Epee;
import application.modele.objet.armes.Pistolet;
import application.vue.*;
import application.vue.inventaire.InventaireVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import static application.Parameters.*;
public class Controleur implements Initializable {

	private Environnement env;
	private Timeline gameLoop;
	private int temps;
	@FXML
	private BorderPane root;
	@FXML
	private TilePane panneauJeu;
	@FXML
	private Pane paneCentral;

	@FXML
	private ImageView quitterJeuButton;

	@FXML
	private ImageView recommencerButton;

	@FXML
	private ImageView optionsJeuButton;

	@FXML
	private Pane pauseMenu;
	@FXML
	private Pane gameOverPane;
	private ObservableVie obsVie;
	private JoueurVue joueurVue;
	private VieVue vieVue;
	private CarteVue carteVue;
	private RobotFantassin robotFantassin;
	private RobotFantassinVue robotFantassinVue;
	private DroneSentinelle droneSentinelle;
	private DroneSentinelleVue droneSentinelleVue;
	private Joueur joueur;
	private RobotGeneral robotGeneral;
	private RobotGeneralVue robotGeneralVue;
	private BooleanProperty mine;
	private int tempInit;
	private InventaireVue inventaireVue;
	boolean isGameOverAdded = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Image fond = new Image("application/ressources/map/bgorange.png");
		BackgroundImage imageFond = new BackgroundImage(fond, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(imageFond);

		bgSound.playSound();
		root.setBackground(background);

		initialiserVariables();
		faireBindEtListener();
		joueur.ajouterRessourceInitial();
		insererImagesPaneCentral();
		initGameLoop();
		getInventaireVue().affichageInventaire();

		gameLoop.play();
		root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(joueur, joueurVue, this, pauseMenu, gameLoop, robotFantassinVue, droneSentinelleVue,  robotGeneralVue, vieVue));
		root.addEventHandler(KeyEvent.KEY_RELEASED, new KeyReleased(joueur, joueurVue));

		droneSentinelleVue.setOnMouseClicked(event -> {
			if (joueur.getEnMain() instanceof Pistolet) {
				droneSentinelle.perdreVie(3);
				Transition tir = new Transition() {

					{
						setCycleDuration(Duration.millis(200));

					}

					@Override
					protected void interpolate(double v) {
						joueurVue.setImage(11);
						gun.playSoundMenu();
					}
				};
				tir.play();
			}
		});

		robotFantassinVue.setOnMouseClicked(event -> {
			if (Math.abs(joueur.getY() - robotFantassin.getY()) < 50 && joueur.getEnMain() instanceof Epee){
				robotFantassin.perdreVie(4);
			} else if (joueur.getEnMain() instanceof Pistolet ) {
				robotFantassin.perdreVie(3);
				Transition tir = new Transition() {

					{
						setCycleDuration(Duration.millis(200));

					}

					@Override
					protected void interpolate(double v) {

						joueurVue.setImage(11);
						gun.playSoundMenu();
					}
				};
				tir.play();
			}

		});


		robotGeneralVue.setOnMouseClicked(event -> {
			if (joueur.getEnMain() instanceof Pistolet ) {
				robotGeneral.perdreVie(3);
				Transition tir = new Transition() {

					{
						setCycleDuration(Duration.millis(200));

					}

					@Override
					protected void interpolate(double v) {

						joueurVue.setImage(11);
						gun.playSoundMenu();

					}
				};
				tir.play();
			}
			else if (Math.abs(joueur.getY() - robotGeneral.getY()) < 50 && joueur.getEnMain() instanceof Epee) {
				robotGeneral.perdreVie(4);
			}
		});
	}

	private void initGameLoop() {
		gameLoop = new Timeline();
		temps = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.002),
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
		joueur.gravite();
		robotFantassin.gravite();
		robotGeneral.gravite();

		// regenaration de la vie du perso
		if (joueur.estVivant() && joueur.getVie() < 12 && temps % 15000 == 0)
			joueur.ajouterVie(1);


		//action du robot fantassin
		if (robotFantassin.estVivant() && joueur.estVivant() && robotFantassinVue.isVisible()) {
			if (joueur.getX() > robotFantassin.getX() && (joueur.getX() - robotFantassin.getX()) > 50) {
				robotFantassin.setX(robotFantassin.getX() + 1);
				robotFantassin.setDirection(1);

			} else if (joueur.getX() < robotFantassin.getX() && (robotFantassin.getX() - joueur.getX()) > 50) {
				robotFantassin.setX(robotFantassin.getX() - 1);
				robotFantassin.setDirection(-1);

			} else {
				if (Math.abs(joueur.getY() - robotFantassin.getY()) < 50 && temps % 700 == 0) {
					robotFantassin.attaque(joueur);
				}

			}
		}

		if (!robotFantassin.estVivant() && temps % 5000 == 0) {
			robotFantassin.respawn();
			robotFantassinVue.setVisible(true);
		}


		//action du drone sentinelle
		if (droneSentinelle.estVivant() && joueur.estVivant() && droneSentinelleVue.isVisible()) {
			if (joueur.getX() > droneSentinelle.getX() && (joueur.getX() - droneSentinelle.getX()) > 50) {
				droneSentinelle.setX(droneSentinelle.getX() + 2);
				droneSentinelle.setDirection(1);

			} else if (joueur.getX() < droneSentinelle.getX() && (droneSentinelle.getX() - joueur.getX()) > 50) {
				droneSentinelle.setX(droneSentinelle.getX() - 2);
				droneSentinelle.setDirection(-1);

			} else {
				if (temps % 2000 == 0) {
					droneSentinelle.attaque(joueur);
				}
			}
		}

		if (!droneSentinelleVue.isVisible() && temps % 8000 == 0 && joueur.getEnMain() instanceof Pistolet) {
			droneSentinelle.respawn();
			droneSentinelleVue.setVisible(true);
		}

		// action du robot general
		if (robotGeneral.estVivant() && joueur.estVivant() && robotGeneralVue.isVisible()) {
			if (joueur.getX() > robotGeneral.getX() && (joueur.getX() - robotGeneral.getX()) > 50) {
				robotGeneral.setX(robotGeneral.getX() + 1);
				robotGeneral.setDirection(1);

			} else if (joueur.getX() < robotGeneral.getX() && (robotGeneral.getX() - joueur.getX()) > 50) {
				robotGeneral.setX(robotGeneral.getX() - 1);
				robotGeneral.setDirection(-1);

			} else {
				if (Math.abs(joueur.getY() - robotGeneral.getY()) < 50 && temps % 700 == 0) {
					robotGeneral.attaque(joueur);
				} else if (temps % 2000 == 0){
					robotGeneral.attaqueDistance(joueur);
				}

			}
		}

		// Collision joueur
		Collisions Joueur = new Collisions(env, joueur, joueurVue);
		Joueur.collisionJoueur();


		// Collision robot
		Collisions RobotFantassin = new Collisions(env, robotFantassin, robotFantassinVue);
		RobotFantassin.collisionRobotFantassin();

		// Collisions Robot General
		Collisions RobotGeneral = new Collisions(env, robotGeneral, robotGeneralVue);
		RobotGeneral.collisionRobotGeneral();

		// Gestion des morts

		if (!joueur.estVivant()) {
			joueurVue.setImage(9);
			joueurVue.setLayoutY(joueurVue.getY() + 20);

			gameLoop.stop();
			gameOverPane.setVisible(true);
			bgSound.stop();
			die.playSound();
			joueurVue.setVisible(false);
			robotFantassinVue.setVisible(false);
			robotGeneralVue.setVisible(false);
			droneSentinelleVue.setVisible(false);
		}

		if (!robotFantassin.estVivant()) {
			robotFantassinVue.setVisible(false);
		}

		if (!droneSentinelle.estVivant()) {
			droneSentinelleVue.setVisible(false);
		}

		if (!robotGeneral.estVivant()) {
			paneCentral.getChildren().remove(robotGeneralVue);
		}

		if (joueur.getEnMain() instanceof Pistolet && joueur.getTrajectoire() == 0 && joueur.estVivant())
			joueurVue.setImage(10);

		joueurVue.setScaleX(joueur.getDirection());
		robotFantassinVue.setScaleX(robotFantassin.getDirection());
		droneSentinelleVue.setScaleX(droneSentinelle.getDirection());
		robotGeneralVue.setScaleX(robotGeneral.getDirection());
	}

	private void initialiserVariables() {
		// création de l'environnement
		this.env = new Environnement();

		// création de la vue de la map
		this.carteVue = new CarteVue(env, panneauJeu, largeurMap, hauteurMap);

		// Création du joueur et de la vue du joueur
		this.joueur = this.env.getJoueur();
		this.joueurVue = new JoueurVue();

		// Création de la vue de la vie avec l'observable
		this.vieVue = new VieVue(carteVue);
		this.obsVie = new ObservableVie(vieVue);
		this.joueur.vieProperty().addListener(obsVie);

		//Création de l'inventaire
		this.inventaireVue = new InventaireVue(joueur);

		// Création d'un fantassin et de sa vue
		this.robotFantassin = this.env.getRobotFantassin();
		this.robotFantassinVue = new RobotFantassinVue();

		// Création d'une sentinelle et de sa vue
		this.droneSentinelle = this.env.getDroneSentinelle();
		this.droneSentinelleVue = new DroneSentinelleVue();


		// Création d'un général et de sa vue
		this.robotGeneral = this.env.getRobotGeneral();
		this.robotGeneralVue = new RobotGeneralVue();

		pauseMenu.setVisible(false);
		gameOverPane.setVisible(false);
	}

	private void faireBindEtListener() {
		// Bind Joueur
		this.joueurVue.translateXProperty().bindBidirectional(this.joueur.xProperty());
		this.joueurVue.translateYProperty().bindBidirectional(this.joueur.yProperty());

		// Bind RobotFantassin
		this.robotFantassinVue.translateXProperty().bindBidirectional(this.robotFantassin.xProperty());
		this.robotFantassinVue.translateYProperty().bindBidirectional(this.robotFantassin.yProperty());

		// Bind DroneSentinelle
		this.droneSentinelleVue.translateXProperty().bindBidirectional(this.droneSentinelle.xProperty());
		this.droneSentinelleVue.translateYProperty().bindBidirectional(this.droneSentinelle.yProperty());

		// Bind RobotGeneral
		this.robotGeneralVue.translateXProperty().bindBidirectional(this.robotGeneral.xProperty());
		this.robotGeneralVue.translateYProperty().bindBidirectional(this.robotGeneral.yProperty());

		//Creation des Listeners pour InventaireVue
		joueur.getInventaire().getObjet(0).objetProperty().addListener(new ObservateurObjet(joueur.getInventaire().getObjet(0), inventaireVue, joueur));
		joueur.getInventaire().getObjet(1).objetProperty().addListener(new ObservateurObjet(joueur.getInventaire().getObjet(1), inventaireVue, joueur));
		joueur.getInventaire().getObjet(2).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(2), inventaireVue, joueur));
		joueur.getInventaire().getObjet(3).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(3), inventaireVue, joueur));
		joueur.getInventaire().getObjet(4).objetProperty().addListener(new ObservateurObjet(joueur.getInventaire().getObjet(4), inventaireVue, joueur));
		joueur.getInventaire().getObjet(5).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(5), inventaireVue, joueur));
		joueur.getInventaire().getObjet(6).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(6), inventaireVue, joueur));
		joueur.getInventaire().getObjet(7).objetProperty().addListener(new ObservateurObjet(joueur.getInventaire().getObjet(7), inventaireVue, joueur));
		joueur.getInventaire().getObjet(8).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(8), inventaireVue, joueur));
		joueur.getInventaire().getObjet(9).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(9), inventaireVue, joueur));
		joueur.getInventaire().getObjet(10).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(10), inventaireVue, joueur));

		//Creation Observateur Map
		env.mapProperty().addListener(new ObservateurMap(carteVue, env.mapProperty()));
	}

	private void insererImagesPaneCentral() {
		// Ajout import javafx.beans.property.SimpleIntegerProperty;des vue au panneau central
		this.paneCentral.getChildren().add(joueurVue);
		this.paneCentral.getChildren().add(vieVue);
		this.paneCentral.getChildren().add(robotFantassinVue);
		this.paneCentral.getChildren().add(droneSentinelleVue);
		droneSentinelleVue.setVisible(false);
		this.paneCentral.getChildren().add(robotGeneralVue);
		robotGeneralVue.setVisible(false);
		this.paneCentral.getChildren().add(inventaireVue);
	}

	private void afficherInfosEnConsole() {
//		System.out.println("Direction joueur:" + joueur.getDirection());
		System.out.println("Joueur x:" + joueur.getX());
		System.out.println("Joueur y:" + joueur.getY());
		System.out.println("Robot x:" + robotFantassin.getX());
		System.out.println("Robot y:" + robotFantassin.getY());
//		System.out.println("Direction robot:" + robotFantassin.getDirection());
		System.out.println("Vie du joueur: " + joueur.vieProperty().getValue());
		System.out.println(env.mapProperty().get(env.getTileBas(joueur.getX(), joueur.getY())));
		System.out.println("Vie du robot fantassin: " + robotFantassin.vieProperty().getValue());
		System.out.println("Vie du dronde sentinelle: " + droneSentinelle.vieProperty().getValue());
		System.out.println(this.env.getListePersonnages());
		System.out.println(robotFantassin.estVivant());
	}

	public InventaireVue getInventaireVue() {
		return inventaireVue;
	}

	@FXML
	void optionsJeuButtonEntered(MouseEvent event) {
		optionsJeuButton.setOpacity(0.8);
		optionsJeuButton.setScaleX(optionsJeuButton.getScaleX()+0.1);
		optionsJeuButton.setScaleY(optionsJeuButton.getScaleY()+0.1);

		button_hover.playSoundMenu();
	}

	@FXML
	void optionsJeuButtonExited(MouseEvent event) {
		optionsJeuButton.setOpacity(1);
		optionsJeuButton.setScaleX(optionsJeuButton.getScaleX()-0.1);
		optionsJeuButton.setScaleY(optionsJeuButton.getScaleY()-0.1);
	}

	@FXML
	void optionsJeuButtonPressed(MouseEvent event) {
		button_clicked.playSoundMenu();
	}

	@FXML
	void quitterJeuButtonEntered(MouseEvent event) {
		quitterJeuButton.setOpacity(0.8);
		quitterJeuButton.setScaleX(quitterJeuButton.getScaleX()+0.1);
		quitterJeuButton.setScaleY(quitterJeuButton.getScaleY()+0.1);

		button_hover.playSoundMenu();
	}

	@FXML
	void quitterJeuButtonExited(MouseEvent event) {
		quitterJeuButton.setOpacity(1);
		quitterJeuButton.setScaleX(quitterJeuButton.getScaleX()-0.1);
		quitterJeuButton.setScaleY(quitterJeuButton.getScaleY()-0.1);
	}

	@FXML
	void quitterJeuButtonPressed(MouseEvent event) {
		Platform.exit();

		button_clicked.playSoundMenu();
	}

	@FXML
	void recommencerButtonEntered(MouseEvent event) {
		recommencerButton.setOpacity(0.8);
		recommencerButton.setScaleX(recommencerButton.getScaleX()+0.1);
		recommencerButton.setScaleY(recommencerButton.getScaleY()+0.1);

		button_hover.playSoundMenu();
	}

	@FXML
	void recommencerButtonExited(MouseEvent event) {
		recommencerButton.setOpacity(1);
		recommencerButton.setScaleX(recommencerButton.getScaleX()-0.1);
		recommencerButton.setScaleY(recommencerButton.getScaleY()-0.1);
	}

	@FXML
	void recommencerButtonPressed(MouseEvent event) {
		pauseMenu.setVisible(false);
		gameOverPane.setVisible(false);
		joueur.setVie(12);
		robotFantassin.setVie(10);
		robotGeneral.setVie(20);
		droneSentinelle.setVie(6);

		joueurVue.setVisible(true);
		robotFantassinVue.setVisible(true);
		vieVue.setVisible(true);

		joueur.setX(100);
		joueur.setY(310);

		robotFantassin.setX(300);
		robotFantassin.setY(320);

		bgSound.stop();
		bgSound.playSound();

		gameLoop.stop();
		gameLoop.play();

		button_clicked.playSoundMenu();
	}
}