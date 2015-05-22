package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import engine.Game;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author yuvalsegall
 */
public class Temp extends Application {

    private static final String PROPERTIES_SCENE_FXML_PATH = "GameScene.fxml";
    private Game game;

    @Override
    public void start(Stage primaryStage) throws IOException {
        game = new Game();

        FXMLLoader fxmlLoader = getPropertiesFXMLLoader();
        Parent propertiesRoot = getPropertiesRoot(fxmlLoader);
        GameSceneController gameSceneController = getPropertiesController(fxmlLoader, primaryStage);

        Scene scene = new Scene(propertiesRoot, 950, 650);

        primaryStage.setTitle("Roulette!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private FXMLLoader getPropertiesFXMLLoader() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource(PROPERTIES_SCENE_FXML_PATH);
        fxmlLoader.setLocation(url);
        return fxmlLoader;
    }

    private GameSceneController getPropertiesController(FXMLLoader fxmlLoader, final Stage primaryStage) {
        GameSceneController propertiesSceneController = (GameSceneController) fxmlLoader.getController();
//        propertiesSceneController.setGame(game);
        propertiesSceneController.setPrimaryStage(primaryStage);
//        propertiesSceneController.getFinishedInit().addListener((source, oldValue, newValue) -> {
//            if (newValue) {
//                game=game;
////                primaryStage.setScene(new GameScene(game));
//            }
//        });
        return propertiesSceneController;
    }

    private Parent getPropertiesRoot(FXMLLoader fxmlLoader) throws IOException {
        InputStream inputStream = fxmlLoader.getLocation().openStream();
        return (Parent) fxmlLoader.load(inputStream);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
