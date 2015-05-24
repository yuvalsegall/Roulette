package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import engine.Game;
import engine.Table;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author yuvalsegall
 */
public class Program extends Application {

    private static final String PROPERTIES_SCENE_FXML_PATH = "PropertiesScene.fxml";
    private static final String GAME_SCENE_FXML_PATH = "GameScene.fxml";
    private Game game;
    private String filePath;

    @Override
    public void start(Stage primaryStage) throws IOException {
        game = new Game();

        FXMLLoader gameFxmlLoader = getFXMLLoader(GAME_SCENE_FXML_PATH);
        Parent gameRoot = getRoot(gameFxmlLoader);
        GameSceneController gameController = getGameController(gameFxmlLoader, primaryStage);
        Scene gameScene = new Scene(gameRoot);

        FXMLLoader propertiesFxmlLoader = getFXMLLoader(PROPERTIES_SCENE_FXML_PATH);
        Parent propertiesRoot = getRoot(propertiesFxmlLoader);
        PropertiesSceneController propertiesController = getPropertiesController(propertiesFxmlLoader, primaryStage, gameScene, gameController);
        Scene propertiesScene = new Scene(propertiesRoot);

        primaryStage.setTitle("Roulette!");
        primaryStage.setScene(propertiesScene);
        primaryStage.show();
    }

    private FXMLLoader getFXMLLoader(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource(fxmlPath);
        fxmlLoader.setLocation(url);
        return fxmlLoader;
    }

    private PropertiesSceneController getPropertiesController(FXMLLoader fxmlLoader, final Stage primaryStage, Scene nextScene, GameSceneController gameController) {
        PropertiesSceneController propertiesSceneController = (PropertiesSceneController) fxmlLoader.getController();
        propertiesSceneController.setGame(game);
        filePath = propertiesSceneController.getFilePath();
        propertiesSceneController.setPrimaryStage(primaryStage);
        propertiesSceneController.getFinishedInit().addListener((source, oldValue, newValue) -> {
            if (newValue) {
                gameController.init();
                primaryStage.setScene(nextScene);
            }
        });
        return propertiesSceneController;
    }

    private GameSceneController getGameController(FXMLLoader fxmlLoader, final Stage primaryStage) {
        //TODO: set table type
        GameSceneController gameSceneController = (GameSceneController) fxmlLoader.getController();
        gameSceneController.setGame(game);
        gameSceneController.setFilePath(filePath);
        gameSceneController.setPrimaryStage(primaryStage);
        
//        gameSceneController.getFinishedInit().addListener((source, oldValue, newValue) -> {
//            if (newValue) {
//                primaryStage.setScene(gameScene);
//            }
//        });
        return gameSceneController;
    }

    private Parent getRoot(FXMLLoader fxmlLoader) throws IOException {
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
