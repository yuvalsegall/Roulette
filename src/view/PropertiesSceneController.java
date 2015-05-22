/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.exceptions.DuplicateNameException;
import controller.exceptions.EmptyNameException;
import controller.exceptions.NumOfHumanPlayersException;
import controller.exceptions.NumOfPlayersException;
import controller.exceptions.OutOfRangeException;
import controller.exceptions.XmlException;
import engine.BadParamsException;
import engine.Game;
import engine.Player;
import engine.Table;
import engine.XMLGame;
import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.bind.JAXBException;

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

    private Game game;
    private boolean isErrorMessageShown;
    private SimpleBooleanProperty isPlayersCountCheckBad;
    private SimpleBooleanProperty finishedInit;
    private String filePath;

    private Stage primaryStage;
    @FXML
    private MenuItem loadXMLMenuItem;
    @FXML
    private TextField gameNameTextField;
    @FXML
    private Slider initialSumOfMoneySlider;
    @FXML
    private Slider maxWagesSlider;
    @FXML
    private Slider minWagesSlider;
    @FXML
    private ComboBox tableTypeComboBox;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private CheckBox isHumanCheckBox;
    @FXML
    private Button addPlayerButton;
    @FXML
    private VBox playersVBox;
    @FXML
    private Button startGameButton;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private FlowPane playersPane;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game();
        isErrorMessageShown = false;
        isPlayersCountCheckBad = new SimpleBooleanProperty(false);
        finishedInit = new SimpleBooleanProperty(false);
        tableTypeComboBox.getItems().addAll(Arrays.asList(Table.TableType.AMERICAN.name(), Table.TableType.FRENCH.name()));
        onPlayerNameChange();
        onGameNameOrTableTypeOrPlayersChange();

        playerNameTextField.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            onPlayerNameChange();
        });
        gameNameTextField.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            onGameNameOrTableTypeOrPlayersChange();
        });
        tableTypeComboBox.selectionModelProperty().addListener(new ChangeListener<ComboBox>() {
            @Override
            public void changed(ObservableValue<? extends ComboBox> observable, ComboBox oldValue, ComboBox newValue) {
                onGameNameOrTableTypeOrPlayersChange();
            }
        });
        isPlayersCountCheckBad.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            onGameNameOrTableTypeOrPlayersChange();
        });
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

//    private void createGame(String gameName, int minWages, int maxWages, Table.TableType tableType, int initalSumOfMoney) {
//        game = new Game();
//        Game.GameDetails details = new Game.GameDetails(gameName, minWages, maxWages, tableType, initalSumOfMoney);
//    }
    private void paramsCheck(int computerPlayers, int humanPlayers, int minWages, int maxWages, int initalSumOfMoney) throws NumOfPlayersException, OutOfRangeException, NumOfHumanPlayersException {
        playersCountCheck(computerPlayers, humanPlayers);
        propertiesCheck(minWages, maxWages, initalSumOfMoney);
    }

    private void propertiesCheck(int minWages, int maxWages, int initalSumOfMoney) throws OutOfRangeException {
        if (minWages < FROM_MIN_WAGES || minWages > TO_MIN_WAGES || maxWages < FROM_MAX_WAGES || maxWages > TO_MAX_WAGES || initalSumOfMoney < MIN_INITIAL_SUM_OF_MONEY || initalSumOfMoney > MAX_INITIAL_SUM_OF_MONEY) {
            throw new OutOfRangeException();
        }
    }

    private void playersCountCheck(int computerPlayers, int humanPlayers) throws NumOfHumanPlayersException, NumOfPlayersException {
        if (computerPlayers < MIN_NUM || computerPlayers > MAX_COMP_PLAYERS || humanPlayers < MIN_NUM || humanPlayers > MAX_HUMAN_PLAYERS || computerPlayers + humanPlayers > MAX_PLAYERS || computerPlayers + humanPlayers < MIN_NUM + 1) {
            throw new NumOfPlayersException(MIN_NUM + 1, MAX_PLAYERS);
        }
        if (humanPlayers == MIN_NUM) {
            throw new NumOfHumanPlayersException(MIN_NUM + 1, MAX_PLAYERS);
        }
    }

    private Player joinGame(String playerName, Boolean isHuman) throws DuplicateNameException, EmptyNameException, NumOfPlayersException, NumOfHumanPlayersException {
//        int computerPlayers = 0, humanPlayers = 0;
//        if (playerName.trim().isEmpty()) {
//            throw new EmptyNameException();
//        }
//        for (Player player : game.getGameDetails().getPlayers()) {
//            if (player.getPlayerDetails().getName().equals(playerName)) {
//                throw new DuplicateNameException();
//            }
//            if (player.getPlayerDetails().getIsHuman()) {
//                humanPlayers++;
//            } else {
//                computerPlayers++;
//            }
//        }
//        if (isHuman) {
//            humanPlayers++;
//        } else {
//            computerPlayers++;
//        }
//        playersCountCheck(computerPlayers, humanPlayers);
        Player player = new Player(new Player.PlayerDetails(playerName, isHuman, BigInteger.valueOf(game.getGameDetails().getInitialSumOfMoney())));
        game.getGameDetails().addPlayer(player);
        return player;
    }

//    private void initiateNewGame() {
//        String gameName = gameNameTextField.getText();
//        Table.TableType tableType = (Table.TableType) tableTypeComboBox.getValue();
//        int minWages = (int) minWagesSlider.getValue();
//        int maxWages = (int) maxWagesSlider.getValue();
//        int initalSumOfMoney = (int) initialSumOfMoneySlider.getValue();
//        createGame(gameName, minWages, maxWages, tableType, initalSumOfMoney);
//    }
    private void initiateXMLGame(File XMLFile) throws XmlException, NumOfPlayersException, OutOfRangeException, BadParamsException, JAXBException, NumOfHumanPlayersException {
        if (XMLFile == null) {
            throw new XmlException();
        }
        game = XMLGame.getXMLGame(XMLFile);
        paramsCheck(game.getGameDetails().getComputerPlayers(), game.getGameDetails().getHumanPlayers(), game.getGameDetails().getMinWages(), game.getGameDetails().getMaxWages(), game.getGameDetails().getInitialSumOfMoney());
    }

    public void playGame() {
        while (true) {
//            if (ConsoleUI.getIsLoadFromXMLFromUser()) {
//                loadGame();
//            } else {
//                initiateNewGame();
//            }
//            do {
//                playRound();
//                ConsoleUI.printPlayersListSortByMoney(game.getGameDetails().getPlayers());
//            } while (ConsoleUI.getIsAnotherRoundFromUser());
//            ConsoleUI.printPlayersListSortByMoney(game.getGameDetails().getPlayers());
//            if (!ConsoleUI.getIsNewGameFromUser()) {
//                break;
//            }
        }
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
                    filePath = XMLFile.getPath();
                    finishedInit.set(true);
                } catch (XmlException | NumOfPlayersException | OutOfRangeException | BadParamsException | JAXBException | NumOfHumanPlayersException ex) {
                    showError(ex.getMessage());
                }
            });
        }).start();
    }

    private void saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML", Arrays.asList("xml")));
        fileChooser.setTitle("Save Roulette Game");
        File XMLFile = fileChooser.showSaveDialog(primaryStage);
//            if (filePath == null || !ConsoleUI.getYesNoAnswer("Save to: " + filePath + "?")) {
//                filePath = ConsoleUI.getXmlFileNameFromUser();
//            }
        new Thread(() -> {
            try {
                XMLGame.setXMLGame(filePath, game);
            } catch (JAXBException ex) {
                showError("cannot save XML, try again");
            }
        }).start();
    }

    @FXML
    protected void addPlayer(ActionEvent event) {
        String name = getPlayerName();
        boolean isHuman = isPlayerHuman();
        try {
            Player player = joinGame(name, isHuman);
            addPlayerToList(player);
            if (isHuman) {
                game.getGameDetails().setHumanPlayers(game.getGameDetails().getHumanPlayers() + 1);
            } else {
                game.getGameDetails().setComputerPlayers(game.getGameDetails().getComputerPlayers() + 1);
            }
            clearPlayerNameField();
            hideError();
        } catch (DuplicateNameException | EmptyNameException | NumOfPlayersException | NumOfHumanPlayersException ex) {
            showError(ex.getMessage());
        }
    }

    protected void onPlayerNameChange() {
        updateAddPlayerButtonState();
        hideError();
    }

    protected void onGameNameOrTableTypeOrPlayersChange() {
        updateStartGameButtonState();
        hideError();
    }

    @FXML
    protected void onStartGame(ActionEvent event) {
        Game.GameDetails details = game.getGameDetails();
        details.setMinWages((int) minWagesSlider.getValue());
        details.setMaxWages((int) maxWagesSlider.getValue());
        details.setInitialSumOfMoney((int) initialSumOfMoneySlider.getValue());
        details.setGameName(gameNameTextField.getText());
        details.setTableType(Table.TableType.valueOf(tableTypeComboBox.getValue().toString()));
        finishedInit.set(true);
    }

    public SimpleBooleanProperty getFinishedInit() {
        return finishedInit;
    }

    private void updateAddPlayerButtonState() {
        int computerPlayers = game.getGameDetails().getComputerPlayers();
        int humanPlayers = game.getGameDetails().getHumanPlayers();
        boolean isEmptyName = getPlayerName().trim().isEmpty();
        boolean isNameExist = false;
        for (Player player : game.getGameDetails().getPlayers()) {
            if (player.getPlayerDetails().getName().equals(getPlayerName().trim())) {
                isNameExist = true;
                break;
            }
        }
        if (isPlayerHuman()) {
            humanPlayers++;
        } else {
            computerPlayers++;
        }
        try {
            playersCountCheck(computerPlayers, humanPlayers);
            isPlayersCountCheckBad = new SimpleBooleanProperty(false);
        } catch (NumOfHumanPlayersException | NumOfPlayersException ex) {
            isPlayersCountCheckBad = new SimpleBooleanProperty(true);
        }
        boolean disable = isEmptyName || isNameExist || (isPlayersCountCheckBad.getValue() && humanPlayers + computerPlayers == 0) || isErrorMessageShown;
        addPlayerButton.setDisable(disable);
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

    private void addPlayerToList(Player player) {
        PlayerView playerView = new PlayerView(player.getPlayerDetails().getName(), player.getPlayerDetails().getIsHuman()
        );
        playersPane.getChildren().add(playerView);
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
        updateAddPlayerButtonState();
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
            updateAddPlayerButtonState();
            updateStartGameButtonState();
        }
    }

}
