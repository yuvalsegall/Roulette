package view;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import ws.roulette.RouletteWebService;
import ws.roulette.RouletteWebServiceService;

/**
 *
 * @author yuvalsegall
 */
public class Program extends Application {

    private static final String PROPERTIES_SCENE_FXML_PATH = "PropertiesScene.fxml";
    private static final String GAME_SCENE_FXML_PATH = "GameScene.fxml";
    private static RouletteWebServiceService service = null;
    private static RouletteWebService gameWebService = null;
    private static Stage thisPrimaryStage = null;
    private StringBuilder gameName = null;
    private StringBuilder playerName = null;
    private AtomicInteger playerId = null;
    private static String[] args = null;

    @Override
    public void start(Stage primaryStage) throws IOException {
        thisPrimaryStage = primaryStage;
        while (service == null) {
            if (args.length != 2 || args[0].trim().isEmpty() || args[1].trim().isEmpty()) {
                popupStartupDialog();
            }
            URL url = new URL("http://" + args[0].trim() + ":" + args[1].trim() + "/RouletteServer/RouletteWebServiceService");
            playerId = new AtomicInteger();
            playerName = new StringBuilder();
            gameName = new StringBuilder();
            try {
                service = new RouletteWebServiceService(url);
            } catch (Exception ex) {
                args[0] = new String();
            }
        }
        gameWebService = service.getRouletteWebServicePort();
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
        propertiesSceneController.setService(gameWebService);
        propertiesSceneController.setPrimaryStage(primaryStage);
        propertiesSceneController.setGameName(gameName);
        propertiesSceneController.setPlayerName(playerName);
        propertiesSceneController.setPlayerId(playerId);
        propertiesSceneController.init();
        propertiesSceneController.getFinishedInit().addListener((ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                Platform.runLater(() -> {
                    try {
                        gameController.popupWaittingDialog();
                        primaryStage.setScene(nextScene);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        popupErrorDialog();
                    }
                });
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
        propertiesSceneController.getOnException().addListener((source, oldValue, newValue) -> {
            if (newValue) {
                popupErrorDialog();
            }
        });
        return propertiesSceneController;
    }

    private GameSceneController getGameController(FXMLLoader fxmlLoader, final Stage primaryStage) {
        GameSceneController gameSceneController = (GameSceneController) fxmlLoader.getController();
        gameSceneController.setPrimaryStage(primaryStage);
        gameSceneController.setGameName(gameName);
        gameSceneController.setPlayerName(playerName);
        gameSceneController.setService(gameWebService);
        gameSceneController.setPlayerId(playerId);
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
            start(thisPrimaryStage);
        } catch (IOException ex) {
            popupErrorDialog();
        }
    }

    private void popupStartupDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Connect to server");
        dialog.setHeaderText("Let's connect to the Roulette Server!");

        ButtonType connectButtonType = new ButtonType("Connect", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(connectButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField ip = new TextField();
        ip.setText("localhost");
        TextField port = new TextField();
        port.setText("8080");

        grid.add(new Label("IP:"), 0, 0);
        grid.add(ip, 1, 0);
        grid.add(new Label("Port:"), 0, 1);
        grid.add(port, 1, 1);

        Node connectButton = dialog.getDialogPane().lookupButton(connectButtonType);
        connectButton.setDisable(false);

        ip.textProperty().addListener((observable, oldValue, newValue) -> {
            connectButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> ip.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == connectButtonType) {
                args = new String[]{ip.getText(), port.getText()};
                return new Pair<>(ip.getText(), port.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Program.args = args;
            launch(args);
        } catch (Exception ex) {
            launch(args);
        }
    }
//TODO deal onEXception
    private void popupErrorDialog() {
        try {
            start(thisPrimaryStage);
        } catch (IOException ex) {
            popupErrorDialog();
        }
//        new Thread(() -> {
//            Platform.runLater(() -> {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setContentText("Something went wrong... lets start over...");
//                alert.showAndWait();
//                if (args != null) {
//                    exit(1);
//                }
//            });
//        }).start();
//TODO exception doesnt work
    }
}
