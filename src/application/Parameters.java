package application;

import application.modele.SoundEffect;

public class Parameters {

    // ------------ Map ------------ //
    public static int tailleTile = 16;
    public static int hauteurMap = 68;
    public static int largeurMap = 95;


    // ------------ Son ------------ //
    public static SoundEffect gun = new SoundEffect("application/ressources/sounds/gun.wav");
    public static SoundEffect bgSound = new SoundEffect("application/ressources/sounds/bgSound.wav");
    public static SoundEffect die = new SoundEffect("application/ressources/sounds/boss_die.wav");
    public static SoundEffect button_hover = new SoundEffect("application/ressources/sounds/button_hover.wav");
    public static SoundEffect button_clicked = new SoundEffect("application/ressources/sounds/button_clicked.wav");
    public static SoundEffect menuSound = new SoundEffect("application/ressources/sounds/menuThemeSound.wav");
    public static SoundEffect walk = new SoundEffect("application/ressources/sounds/walk.wav");
    public static SoundEffect jump = new SoundEffect("application/ressources/sounds/jump.wav");
    public static SoundEffect gameOver = new SoundEffect("application/ressources/sounds/gameOverSound.wav");

    public static SoundEffect gameCompleted = new SoundEffect("application/ressources/sounds/gameCompleted.wav");
}
