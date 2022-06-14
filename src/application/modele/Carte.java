package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;

public class Carte {

	final static String CheminMap = "src/application/ressources/map/Map.txt";
	private ObservableList<Integer> map;
	private int largeur;
	private int hauteur;
	private final static Integer[] tilesTerre = {1, 2, 4, 5, 6};
	
	public Carte(){
		map = FXCollections.observableArrayList();
		initialiserMap();
	}
	
	public void initialiserMap() {
		int Y;
		String ligne;
		
		try {
			FileReader fichier = new FileReader(CheminMap);
			BufferedReader pointeur = new BufferedReader(fichier);

			ligne = pointeur.readLine();
			this.largeur = ligne.length();

			Y = 0;
			int numero = 0;
			while (ligne != null) {
				for (int i = 0; i < ligne.length(); i++) {
					numero = Character.getNumericValue(ligne.charAt(i));
					map.add(numero);
				}
				ligne = pointeur.readLine();
				Y++;
			}
			this.hauteur = Y;
			pointeur.close();
		} catch (Exception e) {
			System.out.println("Erreur lecture de la Map : \"" + CheminMap + "\"");
			System.out.println(e);
		}
	}

	public String toString() {
		String map;
		
		map = "Largeur = " + largeur + "\nHauteur = " + hauteur + "\n\nMap :\n";
		for(int i=0; i<this.map.size(); i++) {
			if(i !=0 && i%largeur == 0)
				map += ("\n");
			map += (String.valueOf(this.map.get(i)));
		}
		return map;
	}

	private boolean contains(Integer[] tab, Integer r) {
		for(Integer i : tab){
			if(i == r){
				return true;
			}
		}
		return false;
	}

	public int getHauteur() {
		return largeur;
	}


	public int getLargeur() {
		return hauteur;
	}

	public ObservableList<Integer> mapProperty() {
		return map;
	}
	
}
