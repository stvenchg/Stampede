package application.vue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import application.controleur.Controleur;
import application.modele.Joueur;
import application.modele.Personnage;
import application.modele.SoundEffect;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class DeplacementAnimation {

	public SimpleIntegerProperty direction;

	private static SoundEffect walk = new SoundEffect("application/ressources/sounds/walk.wav");
	private static SoundEffect jump = new SoundEffect("application/ressources/sounds/jump.wav");

	public static void sauter(Personnage joueur, JoueurVue joueurVue) {

		if ((joueur.getY()-80) < 0) {
			System.out.println("Limite hauteur de la carte atteinte");
		}
		else if (joueur.estVivant() && !joueur.getSaute()){
			
			joueur.setSaute(true);
			
			TranslateTransition sauter = new TranslateTransition(Duration.millis(250), joueurVue);

			joueurVue.setImage(1);
			sauter.setByY(-100);
			if (joueur.getTrajectoire() == 1) {
				sauter.setByX(+80);
			} else if (joueur.getTrajectoire() == 2){
				sauter.setByX(-80);
			}
			jump.playSound();
			sauter.play();


			sauter.setOnFinished(e -> {
				List<Image> images = new ArrayList<>();
				Image spriteJoueurRetombeSaut = joueurVue.getImages().getImage(1);
				images.add(spriteJoueurRetombeSaut);
				images.add(joueurVue.getImages().getImage(0));
//					walk.playSound();

				Transition animation = new Transition() {
					{
						setCycleDuration(Duration.millis(250));
						
					}

					@Override
					protected void interpolate(double fraction) {
						int index = (int) (fraction * (images.size() - 1));
						joueurVue.setImage(images.get(index));
					}
					
				};
				animation.play();
				
			});
		}
	}
	

	static int count = 3;
	public static void allerDroite(Personnage joueur, JoueurVue joueurVue) {

		if (joueur.estVivant()) {

			joueur.setTrajectoire(1);
			
			if (count == 3) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 4) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 5) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 6) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 7) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 8) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count = 3;
			}

			joueur.setDirection(1);
			TranslateTransition droite = new TranslateTransition(Duration.millis(10), joueurVue);
			droite.setByX(+10);
			walk.playSound();
			droite.play();
			
			

//			droite.setOnFinished(e -> {
//				joueurVue.setImage(joueurVue.getImages().getImage(0));
//			});
		}

	}

	public static void allerGauche(Personnage joueur, JoueurVue joueurVue) {

		if (joueur.estVivant()) {
			
			joueur.setTrajectoire(2);

			joueurVue.setImage(joueurVue.getImages().getImage(0));

			if (count == 3) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 4) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 5) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 6) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 7) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count++;
			}
			else if (count == 8) {
				joueurVue.setImage(joueurVue.getImages().getImage(count));
				count = 3;
			}

			joueur.setDirection(-1);
			TranslateTransition gauche = new TranslateTransition(Duration.millis(10), joueurVue);
			gauche.setByX(-10);
			walk.playSound();
			gauche.play();
			

//			gauche.setOnFinished(e -> {
//			joueurVue.setImage(joueurVue.getImages().getImage(0));
//		});

		}

	}
}
