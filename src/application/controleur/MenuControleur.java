package application.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuControleur implements Initializable {

    @FXML private Button jouerButton;
    @FXML private Button quitterButton;

    @FXML
    private void jouerButtonPressed (ActionEvent event) {

        System.out.println("Lancement du jeu.");

        try {
            BorderPane root1 = (BorderPane) FXMLLoader.load(getClass().getResource("/application/vue/vue.fxml"));
            Scene scene = new Scene(root1, 1520, 896);
            scene.getStylesheets().add(getClass().getResource("/application/vue/application.css").toExternalForm());
            root1.requestFocus();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Stampede");
            stage.setResizable(false);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    Platform.exit();
                    System.exit(0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void quitterButtonPressed(ActionEvent event) {
        Stage stage = (Stage) quitterButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jouerButton.requestFocus();
    }
}