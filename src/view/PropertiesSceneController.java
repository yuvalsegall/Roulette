package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.event.EventType;
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
    private StringBuilder gameName;
    private StringBuilder playerName;
    private AtomicInteger playerId;
    private boolean isErrorMessageShown;
    private SimpleBooleanProperty finishedInit;
    private SimpleBooleanProperty newGame;
    private SimpleBooleanProperty exitGame;
    private SimpleBooleanProperty onException;
    private String targetGame;
    List<String> games;

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
            updateJoinButtonState();//TODO delete
        });
        tableTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            onGameNameOrTableTypeOrPlayersChange();
        });
        playerNameTextField.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            updateJoinButtonState();
        });
        onGameNameOrTableTypeOrPlayersChange();
        updateJoinButtonState();
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

    @FXML
    private void joinGame() {
        new Thread(() -> {
            try {
                playerId.set(service.joinGame(gameNameTextField.getText(), playerNameTextField.getText()));
                setGameName(targetGame);
                setPlayerName(playerNameTextField.getText());
                finishedInit.set(Boolean.TRUE);
            } catch (GameDoesNotExists_Exception | InvalidParameters_Exception ex) {
                onException.set(true);
            }
        }).start();
        // TODO game name
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
                onException.set(true);
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
                finishedInit.set(true);
            } catch (DuplicateGameName_Exception | InvalidParameters_Exception | InvalidXML_Exception | FileNotFoundException ex) {
                showError(ex.getMessage());
            }
        }).start();
    }

    private void onGameNameOrTableTypeOrPlayersChange() {
        updateCreateGameButtonState();
        updateJoinButtonState();//TODO delete
        hideError();
    }

    private void onPlayerNameChange() {
        updateJoinButtonState();
        hideError();
    }

    @FXML
    private void createGame(ActionEvent event) throws Exception {
        paramsCheck((int) numOfComputerPlayersSlider.getValue(), (int) numOfHumanPlayersSlider.getValue(), (int) minWagesSlider.getValue(), (int) maxWagesSlider.getValue(), (int) initialSumOfMoneySlider.getValue());
        new Thread(() -> {
            try {
                service.createGame((int) numOfComputerPlayersSlider.getValue(), (int) numOfHumanPlayersSlider.getValue(), (int) initialSumOfMoneySlider.getValue(), (int) maxWagesSlider.getValue(), (int) minWagesSlider.getValue(), gameNameTextField.getText(), RouletteType.valueOf(tableTypeComboBox.getValue().toString()));
                updateServerGamesView();
            } catch (InvalidParameters_Exception | DuplicateGameName_Exception ex) {
                onException.set(true);
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
            updateCreateGameButtonState();
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
    
    private void updateButtons (){
        gamesPane.getChildren().clear();
        games.stream().forEach((name) -> {
            Button newButton = new Button(name);
            newButton.getStyleClass().add("gameButton");
            newButton.setOnAction((e) -> gameButtonClicked(e));
            gamesPane.getChildren().add(newButton);
        });        
    }
    
    private void gameButtonClicked(ActionEvent event){
        for (Object object : gamesPane.getChildren()){
            Button button = (Button)object;
            button.getStyleClass().remove("clickedGameButton");
        }
        Button selected = (Button)event.getSource();
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
