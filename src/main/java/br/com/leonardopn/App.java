package br.com.leonardopn;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage stageMain;

    @Override
    public void start(Stage stage) throws IOException {
    	stageMain = stage;
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}