package view;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ws.roulette.RouletteWebService;
import ws.roulette.RouletteWebServiceService;

/**
 *
 * @author yuvalsegall
 */
public class Program extends Application {

    private static final String PROPERTIES_SCENE_FXML_PATH = "PropertiesScene.fxml";
    private static final String GAME_SCENE_FXML_PATH = "GameScene.fxml";
    private RouletteWebServiceService service = null;
    private RouletteWebService gameWebService = null;
    private Stage primaryStage;
    private String gameName;
    private Integer playerId;
    private static String[] args;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.service = new RouletteWebServiceService();
        this.gameWebService = service.getRouletteWebServicePort();

        FXMLLoader gameFxmlLoader = getFXMLLoader(GAME_SCENE_FXML_PATH);
        Parent gameRoot = getRoot(gameFxmlLoader);
        GameSceneController gameController = getGameController(gameFxmlLoader, primaryStage);
        gameController.setGameName(gameName);
        gameController.setService(gameWebService);
        gameController.setPlayerId(playerId);
        Scene gameScene = new Scene(gameRoot);

        FXMLLoader propertiesFxmlLoader = getFXMLLoader(PROPERTIES_SCENE_FXML_PATH);
        Parent propertiesRoot = getRoot(propertiesFxmlLoader);
        PropertiesSceneController propertiesController = getPropertiesController(propertiesFxmlLoader, primaryStage, gameScene, gameController);
        propertiesController.setGameName(gameName);
        propertiesController.setService(gameWebService);
        propertiesController.setPlayerId(playerId);
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
        propertiesSceneController.setService(gameWebService);
        propertiesSceneController.setPrimaryStage(primaryStage);
        propertiesSceneController.getFinishedInit().addListener((ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                try {
                    gameController.popupWaittingDialog();
                    primaryStage.setScene(nextScene);
                } catch (Exception ex) {
                    popupErrorDialog();
                }
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
        gameSceneController.getOnException().addListener((source, oldValue, newValue) -> {
            if (newValue) {
                popupErrorDialog();
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
            popupErrorDialog();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Program.args = args;
            launch(args);
        } catch (Exception ex) {
            popupErrorDialog();
        }
    }

    private static void popupErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Something went wrong... lets start over...");
        alert.showAndWait();
        if (args != null) {
            launch(args);
        }
    }
}
