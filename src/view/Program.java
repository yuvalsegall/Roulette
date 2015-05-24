package view;

import engine.Game;
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
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.game = new Game();
        this.primaryStage = primaryStage;
        this.filePath = new String();

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
        propertiesSceneController.setFilePath(filePath);
        propertiesSceneController.setPrimaryStage(primaryStage);
        propertiesSceneController.getFinishedInit().addListener((source, oldValue, newValue) -> {
            if (newValue) {
                gameController.init();
                primaryStage.setScene(nextScene);
            }
        });
        propertiesSceneController.getNewGame().addListener((source, oldValue, newValue) -> {
            if (newValue) {
                onNew();
            }
        });
        propertiesSceneController.getExitGame().addListener((source, oldValue, newValue) -> {
            if (newValue) {
                onExit();
            }
        });
        return propertiesSceneController;
    }

    private GameSceneController getGameController(FXMLLoader fxmlLoader, final Stage primaryStage) {
        GameSceneController gameSceneController = (GameSceneController) fxmlLoader.getController();
        gameSceneController.setGame(game);
        gameSceneController.setFilePath(filePath);
        gameSceneController.setPrimaryStage(primaryStage);
        gameSceneController.getNewGame().addListener((source, oldValue, newValue) -> {
            if (newValue) {
                onNew();
            }
        });
        gameSceneController.getExitGame().addListener((source, oldValue, newValue) -> {
            if (newValue) {
                onExit();
            }
        });
        return gameSceneController;
    }

    private Parent getRoot(FXMLLoader fxmlLoader) throws IOException {
        InputStream inputStream = fxmlLoader.getLocation().openStream();
        return (Parent) fxmlLoader.load(inputStream);
    }

    private void onExit() {
        System.exit(1);
    }

    private void onNew() {
        try {
            start(primaryStage);
        } catch (IOException ex) {
            popupErrorDialog(null);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception ex) {
            popupErrorDialog(args);
        }
    }

    private static void popupErrorDialog(String[] args) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setContentText("Something went wrong... lets start over...");
//        alert.showAndWait();
        //TODO return alert
        if (args != null) {
            launch(args);
        }
    }
}
