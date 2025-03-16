package root;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AimsRoot extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
        	Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenuScreen.fxml"));
        	primaryStage.setTitle("Home Page");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
