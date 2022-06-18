package application;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		Image fond = new Image("application/ressources/menu/blurMain.jpg");

		BackgroundImage imageFond = new BackgroundImage(fond, BackgroundRepeat.SPACE,
				BackgroundRepeat.SPACE,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);

		Background background = new Background(imageFond);

		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/menu/vue/menu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/menu/vue/menu.css").toExternalForm());
			root.requestFocus();
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Stampede - Menu principal");
			root.setBackground(background);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("/application/ressources/menu/iconeVash.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}