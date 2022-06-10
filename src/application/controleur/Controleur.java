package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.controleur.inventaire.ObservateurObjet;
import application.controleur.inventaire.ObservateurResources;
import application.modele.Environnement;
import application.modele.Joueur;
import application.modele.Personnage;
import application.modele.RobotFantassin;
import application.vue.CarteVue;
import application.vue.JoueurVue;
import application.vue.RobotFantassinVue;
import application.vue.VieVue;
import application.vue.inventaire.InventaireVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controleur implements Initializable {

	private int temps;
	
	private Environnement env;
	private Joueur joueur;
	private Personnage robotFantassin;
	private Timeline gameLoop;
	private ObservableVie obsVie;

	private BooleanProperty mine;

	private int tempInit;
	private JoueurVue joueurVue;
	private VieVue vieVue;
	private CarteVue carteVue;
	private RobotFantassinVue robotFantassinVue;
	private InventaireVue inventaireVue;
	
	@FXML
	private BorderPane root;
	@FXML
	private TilePane panneauJeu;
	@FXML
	private Pane paneCentral;



	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.mine = new SimpleBooleanProperty(false);
		initialiserVariables();
		faireBindEtListener();
		insererImagesPaneCentral();
		initGameLoop();

		
		
		gameLoop.play();
		root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(joueur, joueurVue, this));
		root.addEventHandler(KeyEvent.KEY_RELEASED, new KeyReleased(this));
		joueur.getInventaire().ajouterObjet(0, 6);
		joueur.getInventaire().ajouterObjet(2, 93);
		joueur.getInventaire().ajouterObjet(1, 3);
		joueur.getInventaire().ajouterObjet(3, 7);
		//joueur.getInventaire().supprimerObjet(2, 5);
		joueur.getInventaire().supprimerObjet(0, 3);
		joueur.getInventaire().ajouterObjet(2, 93);
	}

	private void initGameLoop() {
		gameLoop = new Timeline();
		temps = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.002), // 1 frame = 0.002 seconde
				(ev -> {

					if (temps % 5 == 0) {
						faireTour();
						//afficherInfosEnConsole();
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
		
		//System.out.println(env.getMap().get(env.getPosition( joueur.getX(), joueur.getY()) ));
		

		
		joueurVue.setScaleX(joueur.getDirection());
		robotFantassinVue.setScaleX(robotFantassin.getDirection());

		casserBlokMinage();

	}

	private void casserBlokMinage(){
		if(mine.getValue()){
			if(tempInit == 0){
				tempInit = temps;
			}else {
				if(temps - tempInit > 500){
					System.out.println("j'ai fini'");
					tempInit = 0;
					mine.setValue(false);
				}else{
					System.out.println("je mine encore");
				}
			}
		}else{
			tempInit = 0;
		}
	}

	private void initialiserVariables() {
		// création de l'environnement
		this.env = new Environnement();

		// création de la vue de la map
		this.carteVue = new CarteVue(this, env, panneauJeu, 56, 31);

		// Création du joueur et de la vue du joueur
		this.joueur = env.getJoueur();
		this.joueurVue = new JoueurVue();

		// Création de la vue de la vie avec l'observable
		this.vieVue = new VieVue(carteVue);
		this.obsVie = new ObservableVie(vieVue);
		this.joueur.vieProperty().addListener(obsVie);

		//Création de l'inventaire
		this.inventaireVue = new InventaireVue();
		
		// Création d'un fantassin et de sa vue
		this.robotFantassin = new RobotFantassin();
		this.robotFantassinVue = new RobotFantassinVue();
	}

	private void faireBindEtListener() {
		// Bind Joueur
		this.joueurVue.translateXProperty().bindBidirectional(this.joueur.xProperty());
		this.joueurVue.translateYProperty().bindBidirectional(this.joueur.yProperty());

		// Bind RobotFantassin
		this.robotFantassinVue.translateXProperty().bindBidirectional(this.robotFantassin.xProperty());
		this.robotFantassinVue.translateYProperty().bindBidirectional(this.robotFantassin.yProperty());
		
		//Creation des Listeners pour InventaireVue
		joueur.getInventaire().getObjet(0).objetProperty().addListener(new ObservateurObjet(joueur.getInventaire().getObjet(0), inventaireVue, env.getJoueur()));
		joueur.getInventaire().getObjet(1).objetProperty().addListener(new ObservateurObjet(joueur.getInventaire().getObjet(1), inventaireVue, env.getJoueur()));
		joueur.getInventaire().getObjet(2).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(2), inventaireVue, env.getJoueur()));
		joueur.getInventaire().getObjet(3).objetProperty().addListener(new ObservateurResources(joueur.getInventaire().getObjet(3), inventaireVue, env.getJoueur()));

		//Bin CarteVue
		carteVue.mineProperty().bindBidirectional(mine);
	}

	private void insererImagesPaneCentral() {
		// Ajout des vue au panneau central
		this.paneCentral.getChildren().add(joueurVue);
		this.paneCentral.getChildren().add(vieVue);
		this.paneCentral.getChildren().add(robotFantassinVue);
		this.paneCentral.getChildren().add(inventaireVue);
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
	
	public InventaireVue getInventaireVue() {
		return inventaireVue;
	}

	public Environnement getEnv() {
		return env;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public JoueurVue getJoueurVue() {
		return joueurVue;
	}

	public CarteVue getCarteVue() {
		return carteVue;
	}

	public int getTemps(){
		return temps;
	}
}
