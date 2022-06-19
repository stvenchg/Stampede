package application.controleur;

import java.util.ArrayList;
import java.util.List;
import application.modele.Personnage;
import application.vue.JoueurVue;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.util.Duration;
import static application.Parameters.jump;
import static application.Parameters.walk;

public class DeplacementAnimation {

	public SimpleIntegerProperty direction;
	public static void sauter(Personnage joueur, JoueurVue joueurVue) {

		if ((joueur.getY()-80) < 0) {
			System.out.println("Limite hauteur de la carte atteinte.");
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
				joueurVue.setImage(count);
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
		}

	}
}
