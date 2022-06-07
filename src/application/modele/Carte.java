package application.modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Carte {

	final static String CheminMap = "src/application/ressources/map/Map.txt";
	private ArrayList<Integer> map;
	private int largeur;
	private int hauteur; 
	
	public Carte(){
		initialiserMap();
	}
	
	public void initialiserMap() {
		this.map = new ArrayList<>();
		int Y;
		String ligne;
		
		try {
			FileReader fichier = new FileReader(CheminMap);
			BufferedReader pointeur = new BufferedReader(fichier);
			
			ligne = pointeur.readLine();
			this.largeur = ligne.length();
			
			Y=0;
			while(ligne != null) {
				for(int i=0; i<ligne.length(); i++) 
					map.add(Character.getNumericValue(ligne.charAt(i)));
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


	public int getHauteur() {
		return largeur;
	}


	public int getLargeur() {
		return hauteur;
	}

	public ArrayList<Integer> getMap() {
		return map;
	}
	
}
