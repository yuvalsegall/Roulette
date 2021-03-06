package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import ws.roulette.DuplicateGameName_Exception;
import ws.roulette.GameDoesNotExists_Exception;
import ws.roulette.InvalidParameters_Exception;
import ws.roulette.InvalidXML_Exception;
import ws.roulette.RouletteType;
import ws.roulette.RouletteWebService;

/**
 * FXML Controller class
 *
 * @author yuvalsegall
 */
public class PropertiesSceneController implements Initializable {

    private RouletteWebService service;
    private StringBuilder gameName;
    private StringBuilder playerName;
    private AtomicInteger playerId;
    private boolean isErrorMessageShown;
    private SimpleBooleanProperty finishedInit;
    private SimpleBooleanProperty newGame;
    private SimpleBooleanProperty exitGame;
    private SimpleBooleanProperty onException;
    private String targetGame;
    private List<String> games;

    private Stage primaryStage;
    @FXML
    private TextField gameNameTextField;
    @FXML
    private ComboBox tableTypeComboBox;
    @FXML
    private Slider minWagesSlider;
    @FXML
    private Slider maxWagesSlider;
    @FXML
    private Slider initialSumOfMoneySlider;
    @FXML
    private VBox playersVBox;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private FlowPane gamesPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button createGameButton;
    @FXML
    private Slider numOfComputerPlayersSlider;
    @FXML
    private Button joinGameButton;
    @FXML
    private Slider numOfHumanPlayersSlider;
    @FXML
    private TextField playerNameTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isErrorMessageShown = false;
        finishedInit = new SimpleBooleanProperty(false);
        newGame = new SimpleBooleanProperty(false);
        exitGame = new SimpleBooleanProperty(false);
        onException = new SimpleBooleanProperty(false);
        tableTypeComboBox.getItems().addAll(Arrays.asList(RouletteType.AMERICAN.name(), RouletteType.FRENCH.name()));
        gameNameTextField.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            onGameNameOrTableTypeOrPlayersChange();
        });
        tableTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            onGameNameOrTableTypeOrPlayersChange();
        });
        playerNameTextField.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            onPlayerNameChange();
        });
        onGameNameOrTableTypeOrPlayersChange();
        onPlayerNameChange();
    }

    public void init() {
        updateServerGamesView();
    }

    public void setService(RouletteWebService service) {
        this.service = service;
    }

    public void setGameName(String gameName) {
        this.gameName.append(gameName);
    }

    public void setGameName(StringBuilder gameName) {
        this.gameName = gameName;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setPlayerId(AtomicInteger playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return this.playerId.intValue();
    }

    public void setPlayerName(String playerName) {
        this.playerName.append(playerName);
    }

    public void setPlayerName(StringBuilder playerName) {
        this.playerName = playerName;
    }

    @FXML
    private void joinGame() {
        new Thread(() -> {
            try {
                playerId.set(service.joinGame(targetGame, playerNameTextField.getText()));
                setGameName(targetGame);
                setPlayerName(playerNameTextField.getText());
                finishedInit.set(Boolean.TRUE);
            } catch (GameDoesNotExists_Exception | InvalidParameters_Exception ex) {
                showError(ex.getMessage());
            }
        }).start();
    }

    private void initiateXMLGame(File XMLFile) throws DuplicateGameName_Exception, InvalidParameters_Exception, InvalidXML_Exception, FileNotFoundException {
        if (XMLFile == null) {
            throw new InvalidXML_Exception(null, null);
        }
        new Thread(() -> {
            try {
                setGameName(service.createGameFromXML(new Scanner(XMLFile).useDelimiter("\\Z").next()));
                updateServerGamesView();
            } catch (InvalidParameters_Exception | FileNotFoundException | DuplicateGameName_Exception | InvalidXML_Exception ex) {
                System.out.println(ex.getMessage());
                showError(ex.getMessage());
            }
        }).start();
    }

    @FXML
    private void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML", Arrays.asList("xml")));
        fileChooser.setTitle("Load Roulette Game");
        File XMLFile = fileChooser.showOpenDialog(primaryStage);
        new Thread(() -> {
            try {
                initiateXMLGame(XMLFile);
            } catch (DuplicateGameName_Exception | InvalidParameters_Exception | InvalidXML_Exception | FileNotFoundException ex) {
                showError(ex.getMessage());
            }
        }).start();
    }

    private void onGameNameOrTableTypeOrPlayersChange() {
        hideError();
        updateCreateGameButtonState();
    }

    private void onPlayerNameChange() {
        hideError();
        updateJoinButtonState();
    }

    @FXML
    private void createGame(ActionEvent event) {
        new Thread(() -> {
            try {
                service.createGame((int) numOfComputerPlayersSlider.getValue(), (int) numOfHumanPlayersSlider.getValue(), (int) initialSumOfMoneySlider.getValue(), (int) maxWagesSlider.getValue(), (int) minWagesSlider.getValue(), gameNameTextField.getText(), RouletteType.valueOf(tableTypeComboBox.getValue().toString()));
                updateServerGamesView();
            } catch (InvalidParameters_Exception | DuplicateGameName_Exception ex) {
                showError(ex.getMessage());
            }
        }).start();
    }

    public SimpleBooleanProperty getFinishedInit() {
        return finishedInit;
    }

    public SimpleBooleanProperty getNewGame() {
        return newGame;
    }

    public SimpleBooleanProperty getExitGame() {
        return exitGame;
    }

    public SimpleBooleanProperty getOnException() {
        return onException;
    }

    private void updateCreateGameButtonState() {
        boolean isEmptyFields = getGameName().trim().isEmpty() || getTableType() == null || getTableType().toString().trim().isEmpty();
        boolean disable = isEmptyFields || isErrorMessageShown;
        createGameButton.setDisable(disable);
    }

    private void updateJoinButtonState() {
        boolean isEmptyFields = getPlayerNameTextField().trim().isEmpty() || targetGame == null;
        boolean disable = isEmptyFields || isErrorMessageShown;
        joinGameButton.setDisable(disable);
    }

    public String getGameName() {
        return gameNameTextField.getText();
    }

    public Object getTableType() {
        return tableTypeComboBox.getValue();
    }

    public String getPlayerNameTextField() {
        return playerNameTextField.getText();
    }

    private void showError(String message) {
        Platform.runLater(() -> {
            if (!isErrorMessageShown) {
                isErrorMessageShown = true;
                errorMessageLabel.setText(message);
                FadeTransition animation = new FadeTransition();
                animation.setNode(errorMessageLabel);
                animation.setDuration(Duration.seconds(0.3));
                animation.setFromValue(0.0);
                animation.setToValue(1.0);
                animation.play();
            }
            updateCreateGameButtonState();
            updateJoinButtonState();
        });
    }

    private void hideError() {
        Platform.runLater(() -> {
            if (isErrorMessageShown) {
                FadeTransition animation = FadeTransitionBuilder.create()
                        .node(errorMessageLabel)
                        .duration(Duration.seconds(0.3))
                        .fromValue(1.0)
                        .toValue(0.0)
                        .build();
                animation.play();
                isErrorMessageShown = false;
                errorMessageLabel.textProperty().setValue("");
                updateCreateGameButtonState();
                updateJoinButtonState();
            }
        });
    }

    @FXML
    private void onNewGame(ActionEvent event) {
        newGame.set(popupDialog("New Game", "Are you sure you want to start a new game?"));
    }

    @FXML
    private void onExitGame(ActionEvent event) {
        exitGame.set(popupDialog("Exit Game", "Are you sure you want to leave?"));
    }

    private Boolean popupDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    private void updateServerGamesView() {
        new Thread(() -> {
            games = service.getWaitingGames();
            Platform.runLater(() -> {
                gamesPane.getChildren().clear();
                games.stream().forEach((name) -> {
                    Button newButton = new Button(name);
                    newButton.getStyleClass().add("gameButton");
                    newButton.setOnAction((e) -> gameButtonClicked(e));
                    gamesPane.getChildren().add(newButton);
                });
            });
        }).start();
    }

    private void updateButtons() {
        gamesPane.getChildren().clear();
        games.stream().forEach((name) -> {
            Button newButton = new Button(name);
            newButton.getStyleClass().add("gameButton");
            newButton.setOnAction((e) -> gameButtonClicked(e));
            gamesPane.getChildren().add(newButton);
        });
    }

    private void gameButtonClicked(ActionEvent event) {
        for (Object object : gamesPane.getChildren()) {
            Button button = (Button) object;
            button.getStyleClass().remove("clickedGameButton");
        }
        Button selected = (Button) event.getSource();
        selected.getStyleClass().remove("gameButton");
        selected.getStyleClass().add("clickedGameButton");
        targetGame = selected.getText();
        updateJoinButtonState();
    }

    @FXML
    private void refreshTablesClicked(ActionEvent event) {
        targetGame = null;
        updateServerGamesView();
    }
}
