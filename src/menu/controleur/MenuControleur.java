package menu.controleur;

import application.modele.SoundEffect;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuControleur implements Initializable {

    @FXML private ImageView jouerButton;
    @FXML private ImageView quitterButton;
    @FXML
    private ImageView howToButton;

    @FXML
    private ImageView optionsButton;

    @FXML
    private ImageView logo;

    @FXML
    private BorderPane menuPane;

    @FXML
    private Pane optionsPane;

    @FXML
    private Pane instructionsPane;

    @FXML
    private ImageView goBackButton;

    @FXML
    private ImageView goBackOptionsButton;

    @FXML
    private ImageView sonToggleButton;


    public static SoundEffect button_hover = new SoundEffect("application/ressources/sounds/button_hover.wav");
    public static SoundEffect button_clicked = new SoundEffect("application/ressources/sounds/button_clicked.wav");

    public static SoundEffect menuSound = new SoundEffect("application/ressources/sounds/menuThemeSound.wav");

    public boolean sonActive = true;
    Image ActiveButtonImage = new Image("application/ressources/menu/activeButton.png");
    Image DesactiveButtonImage = new Image("application/ressources/menu/desactiveButton.png");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        jouerButton.requestFocus();

        menuSound.playSound();

        instructionsPane.setVisible(false);
        optionsPane.setVisible(false);

    }

    @FXML
    private void logoMouseEntered(MouseEvent event) {
        logo.setOpacity(0.8);
    }
    @FXML
    private void logoMouseExited(MouseEvent event) {logo.setOpacity(1); }


    // Bouton Jouer ////////////////////////////////////////////////////
    @FXML
    private void jouerButtonPressed (MouseEvent mouseEvent) {

        System.out.println("Lancement du jeu.");

        Stage stage = new Stage();
        lancerJeu(stage);

        button_clicked.playSoundMenu();
        menuSound.stop();
    }

    public void jouerButtonEntered(MouseEvent mouseEvent) {
        jouerButton.setOpacity(0.8);
        jouerButton.setScaleX(jouerButton.getScaleX()+0.1);
        jouerButton.setScaleY(jouerButton.getScaleY()+0.1);
        button_hover.playSoundMenu();
    }

    public void jouerButtonExited(MouseEvent mouseEvent) {
        jouerButton.setOpacity(1);
        jouerButton.setScaleX(jouerButton.getScaleX()-0.1);
        jouerButton.setScaleY(jouerButton.getScaleY()-0.1);
    }

    /////////////////////////////////////////////////////////////////////

    // Bouton Quitter ///////////////////////////////////////////////////
    @FXML
    private void quitterButtonPressed(MouseEvent mouseEvent) {
        Stage stage = (Stage) quitterButton.getScene().getWindow();
        stage.close();

        button_clicked.playSoundMenu();
    }

    @FXML
    public void quitterButtonEntered(MouseEvent event) {
        quitterButton.setOpacity(0.8);
        quitterButton.setScaleX(quitterButton.getScaleX()+0.1);
        quitterButton.setScaleY(quitterButton.getScaleY()+0.1);
        button_hover.playSoundMenu();
    }

    @FXML
    public void quitterButtonExited(MouseEvent event) {
        quitterButton.setOpacity(1);
        quitterButton.setScaleX(quitterButton.getScaleX()-0.1);
        quitterButton.setScaleY(quitterButton.getScaleY()-0.1);
    }

    /////////////////////////////////////////////////////////////////////

    // Bouton Instructions /////////////////////////////////////////////

    @FXML
    private void howToButtonPressed(MouseEvent event) {

        menuPane.setVisible(false);
        instructionsPane.setVisible(true);
        button_clicked.playSoundMenu();
    }
    @FXML
    public void howToButtonEntered(MouseEvent event) {
        howToButton.setOpacity(0.8);
        howToButton.setScaleX(howToButton.getScaleX()+0.1);
        howToButton.setScaleY(howToButton.getScaleY()+0.1);
        button_hover.playSoundMenu();
    }

    @FXML
    public  void howToButtonExited(MouseEvent event) {
        howToButton.setOpacity(1);
        howToButton.setScaleX(howToButton.getScaleX()-0.1);
        howToButton.setScaleY(howToButton.getScaleY()-0.1);
    }

    /////////////////////////////////////////////////////////////////////

    // Bouton Options ///////////////////////////////////////////////////

    @FXML
    private void optionsButtonPressed(MouseEvent event) {

        menuPane.setVisible(false);
        optionsPane.setVisible(true);

        button_clicked.playSoundMenu();
    }
    @FXML
    public void optionsButtonEntered(MouseEvent event) {
        optionsButton.setOpacity(0.8);
        optionsButton.setScaleX(optionsButton.getScaleX()+0.1);
        optionsButton.setScaleY(optionsButton.getScaleY()+0.1);
        button_hover.playSoundMenu();
    }

    @FXML
    public  void optionsButtonExited(MouseEvent event) {
        optionsButton.setOpacity(1);
        optionsButton.setScaleX(optionsButton.getScaleX()-0.1);
        optionsButton.setScaleY(optionsButton.getScaleY()-0.1);
    }

    /////////////////////////////////////////////////////////////////////

    @FXML
    void goBackButtonEntered(MouseEvent event) {
        goBackButton.setOpacity(0.8);
        goBackButton.setScaleX(goBackButton.getScaleX()+0.1);
        goBackButton.setScaleY(goBackButton.getScaleY()+0.1);

        button_hover.playSoundMenu();
    }

    @FXML
    void goBackButtonExited(MouseEvent event) {
        goBackButton.setOpacity(1);
        goBackButton.setScaleX(goBackButton.getScaleX()-0.1);
        goBackButton.setScaleY(goBackButton.getScaleY()-0.1);
    }

    @FXML
    void goBackButtonPressed(MouseEvent event) {
        instructionsPane.setVisible(false);
        menuPane.setVisible(true);

        button_clicked.playSoundMenu();
    }


    @FXML
    void goBackOptionsButtonEntered(MouseEvent event) {
        goBackOptionsButton.setOpacity(0.8);
        goBackOptionsButton.setScaleX(goBackOptionsButton.getScaleX()+0.1);
        goBackOptionsButton.setScaleY(goBackOptionsButton.getScaleY()+0.1);

        button_hover.playSoundMenu();
    }

    @FXML
    void goBackOptionsButtonExited(MouseEvent event) {
        goBackOptionsButton.setOpacity(1);
        goBackOptionsButton.setScaleX(goBackOptionsButton.getScaleX()-0.1);
        goBackOptionsButton.setScaleY(goBackOptionsButton.getScaleY()-0.1);
    }

    @FXML
    void goBackOptionsButtonPressed(MouseEvent event) {
        optionsPane.setVisible(false);
        menuPane.setVisible(true);

        button_clicked.playSoundMenu();
    }

    @FXML
    void sonToggleButtonPressed(MouseEvent event) {

        if (sonActive) {
            sonToggleButton.setImage(DesactiveButtonImage);
            sonActive = false;

            menuSound.stop();
        }
        else {
            sonToggleButton.setImage(ActiveButtonImage);
            sonActive = true;

            menuSound.playSound();
        }

        button_clicked.playSoundMenu();
    }

    @FXML
    void sonToggleButtonEntered(MouseEvent event) {
        sonToggleButton.setOpacity(0.8);
        sonToggleButton.setScaleX(sonToggleButton.getScaleX()+0.1);
        sonToggleButton.setScaleY(sonToggleButton.getScaleY()+0.1);

        button_hover.playSoundMenu();
    }

    @FXML
    void sonToggleButtonExited(MouseEvent event) {
        sonToggleButton.setOpacity(1);
        sonToggleButton.setScaleX(sonToggleButton.getScaleX()-0.1);
        sonToggleButton.setScaleY(sonToggleButton.getScaleY()-0.1);

        button_hover.playSoundMenu();
    }



    private void lancerJeu(Stage stage) {
        try {
            BorderPane root1 = (BorderPane) FXMLLoader.load(getClass().getResource("/application/vue/vue.fxml"));
            Scene scene = new Scene(root1, 1520, 896);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            root1.requestFocus();
            stage.setScene(scene);
            stage.setTitle("Stampede - Jeu");
            stage.setResizable(false);
            stage.show();
            stage.getIcons().add(new Image("/application/ressources/menu/iconeVash.png"));

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    Platform.exit();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}