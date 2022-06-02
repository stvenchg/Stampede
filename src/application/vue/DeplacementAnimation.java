package application.vue;

import java.util.ArrayList;
import java.util.List;

import application.modele.Joueur;
import application.modele.Personnage;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class DeplacementAnimation {
	
	public SimpleIntegerProperty direction;

	public static void sauter(Personnage joueur, JoueurVue joueurVue) {

		TranslateTransition saut = new TranslateTransition(Duration.millis(250), joueurVue);

		
		joueurVue.setImage(1);
		saut.setByY(-100);
		saut.setAutoReverse(true);
		saut.setCycleCount(2);
		saut.play();

		saut.setOnFinished(e -> {
			List<Image> images = new ArrayList<>();
			Image spriteJoueurRetombeSaut = joueurVue.getImages().getImage(2);
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
	
	public static void allerDroite(Personnage joueur, JoueurVue joueurVue) {

		joueurVue.setImage(joueurVue.getImages().getImage(3));
		joueur.setDirection(1);
		TranslateTransition droite = new TranslateTransition(Duration.millis(80), joueurVue);
        droite.setByX(+30);
        droite.play();
        
		droite.setOnFinished(e -> {
			List<Image> images = new ArrayList<>();
			images.add(joueurVue.getImages().getImage(4));

			Transition animation = new Transition() {
				{
					setCycleDuration(Duration.millis(80)); // total time for animation
				}

				@Override
				protected void interpolate(double fraction) {
					int index = (int) (fraction * (images.size() - 1));
					joueurVue.setImage(images.get(index));
				}
			};
			animation.play();
			
			animation.setOnFinished(ev -> {
				joueurVue.setImage(joueurVue.getImages().getImage(0));
				
			});
		});
		
		
	}
	
	public static void allerGauche(Personnage joueur, JoueurVue joueurVue) {

		joueurVue.setImage(joueurVue.getImages().getImage(3));
		joueur.setDirection(-1);
		TranslateTransition gauche = new TranslateTransition(Duration.millis(80), joueurVue);
		gauche.setByX(-30);
		gauche.play();
        
		gauche.setOnFinished(e -> {
			List<Image> images = new ArrayList<>();
			images.add(joueurVue.getImages().getImage(4));

			Transition animation = new Transition() {
				{
					setCycleDuration(Duration.millis(80)); // total time for animation
				}

				@Override
				protected void interpolate(double fraction) {
					int index = (int) (fraction * (images.size() - 1));
					joueurVue.setImage(images.get(index));
				}
			};
			animation.play();
			
			animation.setOnFinished(ev -> {
				joueurVue.setImage(joueurVue.getImages().getImage(0));
				
			});
		});
		
		
	}
}
