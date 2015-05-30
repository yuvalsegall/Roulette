package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.CheckBox;
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
import web.client.DuplicateGameName_Exception;
import web.client.GameDoesNotExists_Exception;
import web.client.InvalidParameters_Exception;
import web.client.InvalidXML_Exception;
import web.client.RouletteType;
import web.client.RouletteWebService;

/**
 * FXML Controller class
 *
 * @author yuvalsegall
 */
public class PropertiesSceneController implements Initializable {

    private static final int MIN_NUM = 0;
    private static final int MAX_COMP_PLAYERS = 6;
    private static final int MAX_HUMAN_PLAYERS = 6;
    private static final int MAX_PLAYERS = 6;
    private static final int FROM_MIN_WAGES = 0;
    private static final int TO_MIN_WAGES = 1;
    private static final int FROM_MAX_WAGES = 1;
    private static final int TO_MAX_WAGES = 10;
    private static final int MIN_INITIAL_SUM_OF_MONEY = 10;
    private static final int MAX_INITIAL_SUM_OF_MONEY = 100;

    private RouletteWebService service;
    private String gameName;
    private Integer playerId;
    private boolean isErrorMessageShown;
    private SimpleBooleanProperty isPlayersCountCheckBad;
    private SimpleBooleanProperty finishedInit;
    private SimpleBooleanProperty newGame;
    private SimpleBooleanProperty exitGame;

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
    private TextField playerNameTextField;
    @FXML
    private CheckBox isHumanCheckBox;
    @FXML
    private Button addPlayerButton;
    @FXML
    private Button startGameButton;
    @FXML
    private VBox playersVBox;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private FlowPane playersPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Slider numOfComputerPlayers;
    @FXML
    private Slider numOfHumanPlayers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isErrorMessageShown = false;
        isPlayersCountCheckBad = new SimpleBooleanProperty(false);
        finishedInit = new SimpleBooleanProperty(false);
        newGame = new SimpleBooleanProperty(false);
        exitGame = new SimpleBooleanProperty(false);
        tableTypeComboBox.getItems().addAll(Arrays.asList(RouletteType.AMERICAN.name(), RouletteType.FRENCH.name()));
        gameNameTextField.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            onGameNameOrTableTypeOrPlayersChange();
        });
        tableTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            onGameNameOrTableTypeOrPlayersChange();
        });
        isPlayersCountCheckBad.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            onGameNameOrTableTypeOrPlayersChange();
        });
        onGameNameOrTableTypeOrPlayersChange();
    }

    public void setService(RouletteWebService service) {
        this.service = service;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    private void paramsCheck(int computerPlayers, int humanPlayers, int minWages, int maxWages, int initalSumOfMoney) throws Exception {
        playersCountCheck(computerPlayers, humanPlayers);
        propertiesCheck(minWages, maxWages, initalSumOfMoney);
    }

    private void propertiesCheck(int minWages, int maxWages, int initalSumOfMoney) throws Exception {
        if (minWages < FROM_MIN_WAGES || minWages > TO_MIN_WAGES || maxWages < FROM_MAX_WAGES || maxWages > TO_MAX_WAGES || initalSumOfMoney < MIN_INITIAL_SUM_OF_MONEY || initalSumOfMoney > MAX_INITIAL_SUM_OF_MONEY) {
            throw new Exception();
        }
    }

    private void playersCountCheck(int computerPlayers, int humanPlayers) throws Exception {
        if (computerPlayers < MIN_NUM || computerPlayers > MAX_COMP_PLAYERS || humanPlayers < MIN_NUM || humanPlayers > MAX_HUMAN_PLAYERS || computerPlayers + humanPlayers > MAX_PLAYERS || computerPlayers + humanPlayers < MIN_NUM + 1) {
            throw new Exception();
        }
        if (humanPlayers == MIN_NUM) {
            throw new Exception();
        }
    }

    private void joinGame(String playerName) throws GameDoesNotExists_Exception, InvalidParameters_Exception {
        playerId = service.joinGame(gameName, playerName);
    }

    private void initiateXMLGame(File XMLFile) throws DuplicateGameName_Exception, InvalidParameters_Exception, InvalidXML_Exception, FileNotFoundException {
        if (XMLFile == null) {
            throw new InvalidXML_Exception(null, null);
        }
        setGameName(service.createGameFromXML(new Scanner(XMLFile).useDelimiter("\\Z").next()));
    }

    @FXML
    private void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML", Arrays.asList("xml")));
        fileChooser.setTitle("Load Roulette Game");
        File XMLFile = fileChooser.showOpenDialog(primaryStage);
        new Thread(() -> {
            Platform.runLater(() -> {
                try {
                    initiateXMLGame(XMLFile);
                    finishedInit.set(true);
                } catch (DuplicateGameName_Exception | InvalidParameters_Exception | InvalidXML_Exception | FileNotFoundException ex) {
                    showError(ex.getMessage());
                }
            });
        }).start();
    }

    private void onGameNameOrTableTypeOrPlayersChange() {
        updateStartGameButtonState();
        hideError();
    }

    @FXML
    private void onStartGame(ActionEvent event) throws InvalidParameters_Exception, DuplicateGameName_Exception, Exception {
        paramsCheck((int) numOfComputerPlayers.getValue(), (int) numOfHumanPlayers.getValue(), (int) maxWagesSlider.getValue(), (int) minWagesSlider.getValue(), (int) initialSumOfMoneySlider.getValue());
        service.createGame((int) numOfComputerPlayers.getValue(), (int) numOfHumanPlayers.getValue(), (int) initialSumOfMoneySlider.getValue(), (int) maxWagesSlider.getValue(), (int) minWagesSlider.getValue(), gameNameTextField.getText(), RouletteType.valueOf(tableTypeComboBox.getValue().toString()));
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

    private void updateStartGameButtonState() {
        boolean isEmptyFields = getGameName().trim().isEmpty() || getTableType() == null || getTableType().toString().trim().isEmpty();
        boolean disable = isEmptyFields || isPlayersCountCheckBad.getValue() || isErrorMessageShown;
        startGameButton.setDisable(disable);
    }

    private String getPlayerName() {
        return playerNameTextField.getText();
    }

    private boolean isPlayerHuman() {
        return isHumanCheckBox.isSelected();
    }

    public String getGameName() {
        return gameNameTextField.getText();
    }

    public Object getTableType() {
        return tableTypeComboBox.getValue();
    }

    private void clearPlayerNameField() {
        playerNameTextField.clear();
        isHumanCheckBox.setSelected(false);
        playerNameTextField.requestFocus();
    }

    private void showError(String message) {
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
        updateStartGameButtonState();
    }

    private void hideError() {
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
            updateStartGameButtonState();
        }
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
}
