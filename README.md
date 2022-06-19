
# Stampede


![Logo](https://raw.githubusercontent.com/DUT-Info-Montreuil/Stampede/dev/src/application/ressources/menu/logoStampede.png)
## Membres du projet

- [Steven CHING](https://www.github.com/stvenchg)
- [Yanis HAMANI](https://www.github.com/YanisTTC)
- [Philippe SANTOS](https://github.com/Philippe20033)


## Badges

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?style=for-the-badge)]()

[![Made with Java](https://img.shields.io/badge/MADE%20WITH-JAVA-red?style=for-the-badge)]()

## Vidéo de démonstration

[![IMAGE ALT TEXT HERE](https://i.imgur.com/673DOJX.jpeg)](https://www.youtube.com/watch?v=qJ1NbUfGgnw)
## Important : En cas d'erreur

Aller dans la classe CarteVue située dans le package Vue et modifier la ligne 110.

Si vous être sur une version de Java > 1.8 alors cette ligne doit être :

    String url = tile.getImage().getUrl();
 
Sinon elle doit être :

    String url = tile.getImage().impl_getUrl();
## Univers du jeu : Planète désertée

Le jeu se déroule dans un monde post-apocalyptique après une guerre opposant 2 puissances de l’espace, sur une planète dont le seul paysage est un désert de débris métalliques, le dernier être vivant : c’est vous. Votre objectif est de quitter la planète en construisant un vaisseau spatial. Pour ce faire, vous devez réunir assez de métal et surtout une source d’énergie pour faire fonctionner votre vaisseau.
Vous n’êtes cependant pas en sécurité. Des robots militaires encore actifs et programmés pour tirer sur tout ce qu’ils voient vous mettront des bâtons dans les roues.
## Le héros du jeu

On le surnomme "Vash the Stampede".
Il possède une barre de vie limitée à 6 cœurs maximum, il récupère un demi-cœur toutes les trente secondes. Si le personnage n’a plus de cœur, il redémarre de zéro.
## Les différents ennemis

**Robot fantassin** : Cet ennemi pourra se déplacer latéralement au sol à la même vitesse que le joueur, il ne peut attaquer le joueur que par combat rapproché (Il est armé d’une épée).
(Drop: 1 engrenage et 1 ressort)
(Vie: 5 coeurs)
(Dégâts: 2 coeurs)

**Drone sentinelle** : Cet ennemi pourra se déplacer latéralement dans les airs légèrement plus vite que le joueur, il ne peut attaquer le joueur qu’à distance en tirant des missiles. Uniquement le flingue peut venir à bout de cet ennemi.
Cet ennemi n'apparaît pas tant que le joueur n’a pas le flingue.
(Drop: 1 ressorts et 2 engrenages)
(Vie: 3 coeurs)
(Dégâts: 1.5 coeurs)

**Robot général** : Cet ennemi est l’ennemi final, il apparaît une fois que tous les matériaux pour le vaisseau sont réunis (hormis le noyau d’énergie). Il pourra se déplacer latéralement au sol et en sautant (comme le joueur) plus vite que le joueur. Il peut attaquer le joueur au corps-à-corps avec une épée et à distance avec un flingue.
(Drop: Noyau d’énergie)
(Vie: 10 coeurs)
(Dégâts flingue: 1.5 coeurs)	
(Dégâts épée: 2 coeurs)
## Les armes et les matériaux

**Armes** :
	
    Epée (Utilisable dès le départ; Usage infini; Courte portée)
		Dégâts : 2 coeurs 
	Flingue  (Craft; Usage limité au nombre de munitions; Longue portée)
		Dégâts : 1.5 coeurs

**Outils** :
	
    Pioche (Utilisable dès le départ; Usage infini; Courte portée)
	
**Matériaux** :

    Plaque de métal (Obtenable en cassant des débris)
		Objet de craft
	Engrenage (Obtenable en détruisant des robots)
		Objet de craft
	Ressort (Obtenable en détruisant des robots)
		Objet de craft
	Bloc de métal (Craft) 
		Bloc placable
	Noyau d’énergie (En battant le robot général)
		Objet de craft
## Contrôles

ESPACE -> Sauter

Q -> Reculer

D -> Avancer

E -> Ouvrir ou fermer l'inventaire

ECHAP -> Mettre le jeu en pause

F1 -> Retirer de la vie au joueur

F2 -> Ajouter de la vie au joueur

F4 -> Mode invincible

Clique droit -> Poser des blocs

Clique gauche -> Attaquer / Tirer / Casser des blocs / Craft

**Disclaimer : À condition que l'objet permettant d'effectuer l'action soit équipé.**
## Recettes de craft


	Bloc de métal : 5 plaques de métal
	Flingue : 3 plaque de métal + 5 ressorts + 6 engrenages
	Balles de flingue (6) : 1 plaque de métal
	Vaisseau : 20 plaques de métal + 20 engrenages + 10 ressorts + 1 noyau d’énergie
