package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ws.roulette.BetType;
import ws.roulette.Event;
import static ws.roulette.EventType.GAME_OVER;
import static ws.roulette.EventType.GAME_START;
import static ws.roulette.EventType.PLAYER_BET;
import static ws.roulette.EventType.PLAYER_FINISHED_BETTING;
import static ws.roulette.EventType.PLAYER_RESIGNED;
import static ws.roulette.EventType.RESULTS_SCORES;
import static ws.roulette.EventType.WINNING_NUMBER;
import ws.roulette.GameDetails;
import ws.roulette.GameDoesNotExists_Exception;
import ws.roulette.InvalidParameters_Exception;
import ws.roulette.PlayerDetails;
import ws.roulette.PlayerStatus;
import ws.roulette.PlayerType;
import ws.roulette.RouletteType;
import ws.roulette.RouletteWebService;

/**
 * FXML Controller class
 *
 * @author yuvalsegall
 */
public class GameSceneController implements Initializable {

    private Stage primaryStage;
    @FXML
    private Button button0;
    @FXML
    private Button button3;
    @FXML
    private Button button2;
    @FXML
    private Button button1;
    @FXML
    private Button button1_2;
    @FXML
    private Button button2_3;
    @FXML
    private Button button2_3_5_6;
    @FXML
    private Button button5_6;
    @FXML
    private Button button5_6_8_9;
    @FXML
    private Button button8_9;
    @FXML
    private Button button8_9_11_12;
    @FXML
    private Button button11_12;
    @FXML
    private Button button11_12_14_15;
    @FXML
    private Button button14_15;
    @FXML
    private Button button14_15_17_18;
    @FXML
    private Button button17_18;
    @FXML
    private Button button17_18_20_21;
    @FXML
    private Button button20_21;
    @FXML
    private Button button20_21_23_24;
    @FXML
    private Button button23_24;
    @FXML
    private Button button23_24_26_27;
    @FXML
    private Button button26_27;
    @FXML
    private Button button26_27_29_30;
    @FXML
    private Button button29_30;
    @FXML
    private Button button29_30_32_33;
    @FXML
    private Button button32_33;
    @FXML
    private Button button32_33_35_36;
    @FXML
    private Button button35_36;
    @FXML
    private Button button3_6;
    @FXML
    private Button button6;
    @FXML
    private Button button6_9;
    @FXML
    private Button button9;
    @FXML
    private Button button9_12;
    @FXML
    private Button button12;
    @FXML
    private Button button12_15;
    @FXML
    private Button button15;
    @FXML
    private Button button15_18;
    @FXML
    private Button button18;
    @FXML
    private Button button18_21;
    @FXML
    private Button button21;
    @FXML
    private Button button21_24;
    @FXML
    private Button button24;
    @FXML
    private Button button24_27;
    @FXML
    private Button button27;
    @FXML
    private Button button27_30;
    @FXML
    private Button button30;
    @FXML
    private Button button30_33;
    @FXML
    private Button button33;
    @FXML
    private Button button33_36;
    @FXML
    private Button button36;
    @FXML
    private Button button2_5;
    @FXML
    private Button button5;
    @FXML
    private Button button5_8;
    @FXML
    private Button button8;
    @FXML
    private Button button8_11;
    @FXML
    private Button button11;
    @FXML
    private Button button11_14;
    @FXML
    private Button button14;
    @FXML
    private Button button14_17;
    @FXML
    private Button button17;
    @FXML
    private Button button17_20;
    @FXML
    private Button button20;
    @FXML
    private Button button20_23;
    @FXML
    private Button button23;
    @FXML
    private Button button23_26;
    @FXML
    private Button button26;
    @FXML
    private Button button26_29;
    @FXML
    private Button button29;
    @FXML
    private Button button29_32;
    @FXML
    private Button button32;
    @FXML
    private Button button32_35;
    @FXML
    private Button button35;
    @FXML
    private Button button1_2_4_5;
    @FXML
    private Button button4_5;
    @FXML
    private Button button4_5_7_8;
    @FXML
    private Button button7_8;
    @FXML
    private Button button7_8_10_11;
    @FXML
    private Button button10_11;
    @FXML
    private Button button10_11_13_14;
    @FXML
    private Button button13_14;
    @FXML
    private Button button13_14_16_17;
    @FXML
    private Button button16_17;
    @FXML
    private Button button16_17_19_20;
    @FXML
    private Button button19_20;
    @FXML
    private Button button19_20_22_23;
    @FXML
    private Button button22_23;
    @FXML
    private Button button22_23_25_26;
    @FXML
    private Button button25_26;
    @FXML
    private Button button25_26_28_29;
    @FXML
    private Button button28_29;
    @FXML
    private Button button28_29_31_32;
    @FXML
    private Button button31_32;
    @FXML
    private Button button31_32_34_35;
    @FXML
    private Button button34_35;
    @FXML
    private Button button1_4;
    @FXML
    private Button button4;
    @FXML
    private Button button4_7;
    @FXML
    private Button button7;
    @FXML
    private Button button7_10;
    @FXML
    private Button button10;
    @FXML
    private Button button10_13;
    @FXML
    private Button button13;
    @FXML
    private Button button13_16;
    @FXML
    private Button button16;
    @FXML
    private Button button16_19;
    @FXML
    private Button button19;
    @FXML
    private Button button19_22;
    @FXML
    private Button button22;
    @FXML
    private Button button22_25;
    @FXML
    private Button button25;
    @FXML
    private Button button25_28;
    @FXML
    private Button button28;
    @FXML
    private Button button28_31;
    @FXML
    private Button button31;
    @FXML
    private Button button31_34;
    @FXML
    private Button button34;
    @FXML
    private Button button_col_3;
    @FXML
    private Button button_col_2;
    @FXML
    private Button button_col_1;
    @FXML
    private Button button_1st_12;
    @FXML
    private Button button_2nd_12;
    @FXML
    private Button button_3rd_12;
    @FXML
    private Button button_1_18;
    @FXML
    private Button button_even;
    @FXML
    private Button button_red;
    @FXML
    private Button button_black;
    @FXML
    private Button button_odd;
    @FXML
    private Button button_19_36;
    @FXML
    private Button plus1Button;
    @FXML
    private Button plus2Button;
    @FXML
    private Button plus5Button;
    @FXML
    private Button plus10Button;
    @FXML
    private Button FinishBettingButton;
    @FXML
    private Button buttonSnake;
    @FXML
    private Label messageLabel;
    @FXML
    private Label ballPossitionLabel;
    @FXML
    private GridPane tableGridPane;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label AmountLabel;
    @FXML
    private FlowPane playersPane;
    private StringBuilder gameName;
    private StringBuilder playerName;
    private Number playerId;
    private RouletteWebService service;
    private ArrayList<Integer> numbers;
    private BetType betType;
    private IntegerProperty amount;
    private int lastEventId;
    private final HashMap<Integer, Integer> rowsReverse = new HashMap();
    private final int NOM_OF_ACTUAL_ROWS = 3;
    private final int COLS_TO_FIRST_NUMBER = 3;
    private final String AMERICAN_TABLE_IMAGE_PATH = "/resources/table00.png";
    private final String AMERICAN_WHEEL_IMAGE_PATH = "/resources/roulette00_300.png";
    public static final int[] BASKET_FR = {0, 1, 2, 3};
    public static final int[] BASKET_AM_V1 = {0, 1, 2};
    public static final int[] BASKET_AM_V2 = {0, 2, 37};
    public static final int[] BASKET_AM_V3 = {2, 3, 37};
    private boolean isErrorMessageShown;
    private SimpleBooleanProperty newGame;
    private SimpleBooleanProperty exitGame;
    private SimpleBooleanProperty onException;
    private SimpleBooleanProperty isGameActive;
    private static final int SEC_BETWEEN_SERVER_CALLS = 1;
    ObservableList<String> eventsForView;

    @FXML
    private AnchorPane frenchZeroAnchor;
    @FXML
    private ImageView tableImageView;
    @FXML
    private ImageView rouletteImageView;
    @FXML
    private AnchorPane basketAnchor;
    @FXML
    private Button button0_2_3;
    @FXML
    private Button retireButton;
    @FXML
    private AnchorPane snakeAnchor;
    @FXML
    private ListView eventsListView;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numbers = new ArrayList<>();
        isErrorMessageShown = false;
        amount = new SimpleIntegerProperty();
        setAmount(0);
        AmountLabel.textProperty().bind(
                Bindings.concat(amount, "$"));
        setRowsHashMap(rowsReverse);
        newGame = new SimpleBooleanProperty(false);
        exitGame = new SimpleBooleanProperty(false);
        onException = new SimpleBooleanProperty(false);
        isGameActive = new SimpleBooleanProperty(false);
        lastEventId = 0;
//        eventsListView = new ListView<String>();
        
        eventsForView = FXCollections.observableArrayList ();
        eventsListView.setItems(eventsForView);
        eventsListView.setDisable(true);
    }

    public void init() {
        new Thread(() -> {
            try {
                GameDetails details = service.getGameDetails(getGameName());
                if (details.getRouletteType().equals(RouletteType.AMERICAN)) {
                    Platform.runLater(() -> {
                        setTableToAmerican();
                    });
                }
                buildPlayersMap();
                ballPossitionLabel.textProperty().set("");
                messageLabel.textProperty().set("Choose amount, and click the table to place a bet");
            } catch (GameDoesNotExists_Exception ex) {
                showError(ex.getMessage());
            }
        }).start();
    }

    public void setGameName(StringBuilder gameName) {
        this.gameName = gameName;
    }

    public void setGameName(String gameName) {
        this.gameName.append(gameName);
    }

    public void setService(RouletteWebService service) {
        this.service = service;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setPlayerId(Number playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return this.playerId.intValue();
    }

    public String getGameName() {
        return gameName.toString();
    }

    public void setPlayerName(StringBuilder playerName) {
        this.playerName = playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName.append(playerName);
    }

    public String getPlayerName() {
        return this.playerName.toString();
    }

    private void showError(String message) {
        if (!isErrorMessageShown) {
            isErrorMessageShown = true;
            messageLabel.setText(message);
            FadeTransition animation = new FadeTransition();
            animation.setNode(messageLabel);
            animation.setDuration(Duration.seconds(5));
            animation.setFromValue(1.0);
            animation.setToValue(0.0);
            animation.play();
        }
    }

    private void hideError() {
        if (isErrorMessageShown) {
            FadeTransition animation = FadeTransitionBuilder.create()
                    .node(messageLabel)
                    .duration(Duration.seconds(0.3))
                    .fromValue(1.0)
                    .toValue(0.0)
                    .build();
            animation.play();

            isErrorMessageShown = false;
            messageLabel.textProperty().setValue("");
        }
    }

    @FXML
    private void numberButtonClicked(ActionEvent event) {
        Node node = ((Button) event.getSource()).getParent();
        int row = GridPane.getRowIndex(node);
        int col = GridPane.getColumnIndex(node);
        int myCol = col - COLS_TO_FIRST_NUMBER;
        int myRow = rowsReverse.get(row);
        myCol -= myCol / 2;
        myRow -= myRow / 2;

        if (col % 2 == 0) {
            if (row % 2 == 0) {
                //vertical split
                numbers.add(myCol * NOM_OF_ACTUAL_ROWS - 1);
                if (row < NOM_OF_ACTUAL_ROWS) {
                    numbers.add(myCol * NOM_OF_ACTUAL_ROWS);
                } else {
                    numbers.add(myCol * NOM_OF_ACTUAL_ROWS - 2);
                }
                betType = BetType.SPLIT;
            } else {
                //single number
                numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
                betType = BetType.STRAIGHT;
            }
        } else {
            if (row % 2 == 0) {
                //corner
                numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
                numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow));
                numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow - 1));
                numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow - 1));
                betType = BetType.CORNER;
            } else {
                //horizontal split
                numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
                numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow));
                betType = BetType.SPLIT;
            }
        }
        addBetOnTable(event);
    }

    @FXML
    private void AddOneClicked(ActionEvent event) {
        setAmount(amount.get() + 1);
    }

    @FXML
    private void AddTwoClicked(ActionEvent event) {
        setAmount(amount.get() + 2);
    }

    @FXML
    private void AddFiveClicked(ActionEvent event) {
        setAmount(amount.get() + 5);
    }

    @FXML
    private void AddTenClicked(ActionEvent event) {
        setAmount(amount.get() + 10);
    }

    @FXML
    private void finishedBettingClicked(ActionEvent event) {
        new Thread(() -> {
            try {
                FinishBettingButton.setDisable(true);
                clearChips();
                service.finishBetting(getPlayerId());
            } catch (InvalidParameters_Exception ex) {
                onException.set(true);
            }
        }).start();
    }

    private void clearAmountClicked(ActionEvent event) {
        setAmount(0);
    }

    @FXML
    private void thirdRowClicked(ActionEvent event) {
        betType = BetType.COLUMN_3;
        addBetOnTable(event);
    }

    @FXML
    private void secondRowClicked(ActionEvent event) {
        betType = BetType.COLUMN_2;
        addBetOnTable(event);
    }

    @FXML
    private void firstRowClicked(ActionEvent event) {
        betType = BetType.COLUMN_1;
        addBetOnTable(event);
    }

    @FXML
    private void first12Clicked(ActionEvent event) {
        betType = BetType.PREMIERE_DOUZAINE;
        addBetOnTable(event);
    }

    @FXML
    private void second12Clicked(ActionEvent event) {
        betType = BetType.MOYENNE_DOUZAINE;
        addBetOnTable(event);
    }

    @FXML
    private void third12Clicked(ActionEvent event) {
        betType = BetType.DERNIERE_DOUZAINE;
        addBetOnTable(event);
    }

    @FXML
    private void firstHalfClicked(ActionEvent event) {
        betType = BetType.MANQUE;
        addBetOnTable(event);
    }

    @FXML
    private void evenClicked(ActionEvent event) {
        betType = BetType.PAIR;
        addBetOnTable(event);
    }

    @FXML
    private void redClicked(ActionEvent event) {
        betType = BetType.ROUGE;
        addBetOnTable(event);
    }

    @FXML
    private void blackClicked(ActionEvent event) {
        betType = BetType.NOIR;
        addBetOnTable(event);
    }

    @FXML
    private void oddClicked(ActionEvent event) {
        betType = BetType.IMPAIR;
        addBetOnTable(event);
    }

    @FXML
    private void secondHalfClicked(ActionEvent event) {
        betType = BetType.PASSE;
        addBetOnTable(event);
    }

    private void setRowsHashMap(HashMap<Integer, Integer> rows) {
        rows.put(1, 5);
        rows.put(2, 4);
        rows.put(3, 3);
        rows.put(4, 2);
        rows.put(5, 1);
    }

    private PlayerViewWithAmount findPlayerInPane(String name) {
        for (Node player : playersPane.getChildren()) {
            if (((PlayerViewWithAmount) player).getName().getText().equals(name)) {
                return (PlayerViewWithAmount) player;
            }
        }
        return null;
    }

    private void setPlayerMoney(String name, int amount) {
        PlayerViewWithAmount player = findPlayerInPane(name);
        player.setAmount(amount);
    }

    private int getPlayerMoney(String name) {
        PlayerViewWithAmount player = findPlayerInPane(name);
        return player.getAmount();
    }

    private void setPlayerResigned(String name) {
        PlayerViewWithAmount player = findPlayerInPane(name);
        player.getName().getStyleClass().remove("themeLabel");
        player.getName().getStyleClass().add("redThemeLabel");
    }

    private void clearChips() {
        Platform.runLater(() -> {
            tableGridPane.getChildren().stream().filter((node) -> (node instanceof AnchorPane)).map((node) -> (AnchorPane) node).forEach((parent) -> {
                for (int i = 0; i < parent.getChildren().size(); i++) {
                    if (parent.getChildren().get(i) instanceof ChipForTable) {
                        parent.getChildren().remove(i);
                    }
                }
            });
            for (Node node : snakeAnchor.getChildren()) {
                if (node instanceof ChipForTable) {
                    snakeAnchor.getChildren().remove(node);
                    break;
                }
            }
        });
    }

    private void addBetOnTable(ActionEvent event) {
        ChipForTable newChip = new ChipForTable(amount.getValue().toString() + '$');
        Button initiatingButton = (Button) event.getSource();
        AnchorPane parent = (AnchorPane) initiatingButton.getParent();
        parent.getChildren().add(newChip);
        placeBet(parent);
    }

    private void placeBet(AnchorPane parent) {
        if (!isPlayerHasMoneyForBet()) {
            showError("You cannot place money you do not have");
            parent.getChildren().remove(parent.getChildren().size() - 1);
            clearBet();
        } else {
            new Thread(() -> {
                try {
                    service.makeBet(amount.getValue(), betType, numbers, getPlayerId());
                    clearBet();
                } catch (InvalidParameters_Exception ex) {
                    onException.set(true);
                }
            }).start();
        }
    }

    private void clearBet() {
        FinishBettingButton.setDisable(false);
        setAmount(0);
        numbers.clear();
    }

    @FXML
    private void snakeClicked(ActionEvent event) {
        betType = BetType.SNAKE;
        addBetOnTable(event);
    }

    private void setTableToAmerican() {
        tableImageView.setImage(new Image(AMERICAN_TABLE_IMAGE_PATH));
        rouletteImageView.setImage(new Image(AMERICAN_WHEEL_IMAGE_PATH));
        removeFrenchZero();
        removeFrenchBaskets();
        addAmericanZeros();
        addAmericanBaskets();
    }

    private void removeFrenchBaskets() {
        tableGridPane.getChildren().remove(basketAnchor);
    }

    private void removeFrenchZero() {
        tableGridPane.getChildren().remove(frenchZeroAnchor);
    }

    private void addAmericanZeros() {
        AnchorPane anchor0 = new AnchorPane();
        AnchorPane anchor00 = new AnchorPane();
        Button newbutton0 = new Button();
        Button newbutton00 = new Button();

        newbutton0.setOnAction((e) -> zeroClicked(e));
        newbutton00.setOnAction((e) -> doubleZeroClicked(e));
        setButtonProperties(newbutton0);
        setButtonProperties(newbutton00);
        setButtonToAnchor(newbutton0);
        setButtonToAnchor(newbutton00);
        anchor0.getChildren().add(newbutton0);
        anchor00.getChildren().add(newbutton00);
        setAnchorProperties(anchor0);
        setAnchorProperties(anchor00);
        tableGridPane.add(anchor0, 1, 4, 1, 2);
        tableGridPane.add(anchor00, 1, 1, 1, 2);
    }

    private void setButtonProperties(Button button) {
        button.setOpacity(0);
    }

    private void setAnchorProperties(AnchorPane anchor) {
        anchor.prefWidthProperty().set(46.0);
    }

    private void setButtonToAnchor(Button button) {
        AnchorPane.setTopAnchor(button, 0.0);
        AnchorPane.setLeftAnchor(button, 0.0);
        AnchorPane.setRightAnchor(button, 0.0);
        AnchorPane.setBottomAnchor(button, 0.0);
    }

    @FXML
    private void zeroClicked(ActionEvent event) {
        betType = BetType.STRAIGHT;
        numbers.add(0);
        addBetOnTable(event);
    }

    private void doubleZeroClicked(ActionEvent event) {
        betType = BetType.STRAIGHT;
        numbers.add(37);
        addBetOnTable(event);
    }

    private void addAmericanBaskets() {
        AnchorPane basket1 = createNewBasketAnchor("V1");
        AnchorPane basket2 = createNewBasketAnchor("V2");
        AnchorPane basket3 = createNewBasketAnchor("V3");
        tableGridPane.add(basket1, 2, 4, 2, 1);
        tableGridPane.add(basket2, 2, 3, 2, 1);
        tableGridPane.add(basket3, 2, 2, 2, 1);
    }

    private AnchorPane createNewBasketAnchor(String version) {
        AnchorPane anchorBasket = new AnchorPane();
        Button newbutton = new Button();
        setButtonProperties(newbutton);
        setButtonToAnchor(newbutton);
        anchorBasket.getChildren().add(newbutton);
        anchorBasket.prefWidthProperty().set(35.0);

        switch (version) {
            case "V1":
                newbutton.setOnAction((e) -> basketAM_V1Clicked(e));
                break;
            case "V2":
                newbutton.setOnAction((e) -> basketAM_V2Clicked(e));
                break;
            default:
                newbutton.setOnAction((e) -> basketAM_V3Clicked(e));
                break;
        }
        return anchorBasket;
    }

    private void basketAM_V1Clicked(ActionEvent event) {
        betType = BetType.BASKET;
        for (int num : BASKET_AM_V1) {
            numbers.add(num);
        }
        addBetOnTable(event);
    }

    private void basketAM_V2Clicked(ActionEvent event) {
        betType = BetType.BASKET;
        for (int num : BASKET_AM_V2) {
            numbers.add(num);
        }
        addBetOnTable(event);
    }

    private void basketAM_V3Clicked(ActionEvent event) {
        betType = BetType.BASKET;
        for (int num : BASKET_AM_V3) {
            numbers.add(num);
        }
        addBetOnTable(event);
    }

    private void basketFRClicked(ActionEvent event) {
        betType = BetType.BASKET;
        for (int num : BASKET_FR) {
            numbers.add(num);
        }
        addBetOnTable(event);
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

    private void popupGoodbyeDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
        if (popupDialog("New Game", "Do you want to start a new game?")) {
            newGame.set(true);
        } else {
            exitGame.set(true);
        }
    }

    public void popupWaittingDialog() throws InterruptedException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("One moment and the game begin!");
        alert.setContentText("Waitting for the game to start...");
        alert.getButtonTypes().clear();
//        alert.show();
        //TODO show and hide
        while (!isGameActive.getValue()) {
            checkForServerEvents();
            Thread.sleep(TimeUnit.SECONDS.toMillis(SEC_BETWEEN_SERVER_CALLS));
        }
        Platform.runLater(() -> {
            alert.close();
        });
    }

    private boolean isPlayerHasMoneyForBet() {
        return findPlayerInPane(getPlayerName()).getAmount() >= amount.getValue();
    }

    private void spinRoulette(int position) {
        Platform.runLater(() -> {
            RotateTransition rt = new RotateTransition(Duration.millis(3000), rouletteImageView);
            rt.setByAngle(360);
            rt.setCycleCount(1);
            rt.setDuration(Duration.seconds(3));
            rt.setAutoReverse(false);
            rt.play();
            String rouletteResultString = "Ball on: ";
            rouletteResultString += position == 37 ? "00" : position;
            ballPossitionLabel.textProperty().set(rouletteResultString);
        });
    }

    @FXML
    private void onRetire(ActionEvent event) {
        new Thread(() -> {
            try {
                service.resign(getPlayerId());
            } catch (InvalidParameters_Exception ex) {
                onException.set(true);
            }
        }).start();
    }
//TODO: check XML
    private void checkForServerEvents() {
        new Thread(() -> {
            try {
                List<Event> events = service.getEvents(lastEventId, getPlayerId());
                eventsForView.clear();
                for(Event event : events){
                    eventsForView.add(eventToString(event));
                    eventsListView.scrollTo(eventsForView.size()-1);
                }
                lastEventId = events.isEmpty() ? lastEventId : events.get(events.size() - 1).getId();
                events.stream().forEach((Event event) -> {
                    switch (event.getType()) {
                        case GAME_OVER:
                            isGameActive.set(false);
                            popupGoodbyeDialog("Game Over", "The game has ended.");
                            break;
                        case GAME_START:
                            init();
                            eventsListView.getItems().add("The Game has Started");
                            isGameActive.set(true);
                            new Thread(() -> {
                                try {
                                    startCheckingForServerEvents();

                                } catch (InterruptedException ex) {
                                    onException.set(true);
                                }
                            }).start();
                            break;
                        case WINNING_NUMBER:
                            spinRoulette(event.getWinningNumber());
                            break;
                        case RESULTS_SCORES:
                            setPlayerMoney(event.getPlayerName(), getPlayerMoney(event.getPlayerName()) + event.getAmount());
                            break;
                        case PLAYER_RESIGNED:
                            setPlayerResigned(event.getPlayerName());
                            //TODO if server retierd me??
                            break;
                        case PLAYER_BET:
                            setPlayerMoney(event.getPlayerName(), getPlayerMoney(event.getPlayerName()) - event.getAmount());
//                            });
                            eventsListView.getItems().add(event.getPlayerName() + " bet " + event.getAmount() + "$ on " + event.getBetType());
                            //TODO print in the feed
                            break;
                        case PLAYER_FINISHED_BETTING:
                            //TODO print in the feed
                            break;
                        default:
                            onException.set(true);
                    }
                });
            } catch (InvalidParameters_Exception ex) {
                onException.set(true);
            }
        }).start();
    }

    private void buildPlayersMap() {
        new Thread(() -> {
            try {
                List<PlayerDetails> playersList = service.getPlayersDetails(getGameName());
                Platform.runLater(() -> {
                    if (!playersPane.getChildren().isEmpty()) {
                        playersPane.getChildren().remove(0, playersPane.getChildren().size());
                    }
                    playersList.stream().forEach((PlayerDetails player) -> {
                        if (player.getStatus().equals(PlayerStatus.ACTIVE)) {
                            PlayerViewWithAmount playerView = new PlayerViewWithAmount(player.getName(), player.getType().equals(PlayerType.HUMAN), player.getMoney());
                            playersPane.getChildren().add(playerView);
                        }

                    }
                    );
                });
            } catch (GameDoesNotExists_Exception ex) {
                onException.set(true);
            }
        }).start();
    }

    private void startCheckingForServerEvents() throws InterruptedException {
        while (isGameActive.getValue()) {
            checkForServerEvents();
            Thread.sleep(TimeUnit.SECONDS.toMillis(SEC_BETWEEN_SERVER_CALLS));
        }
    }

    public void setAmount(int amount) {
        Platform.runLater(() -> {
            this.amount.set(amount);
        });
    }
    
    private String eventToString (Event event){
        StringBuilder result = new StringBuilder();
        
//        if(!event.getPlayerName().isEmpty()){
//            result.append(event.getPlayerName());
//            result.append(" ");
//        }
        result.append(event.getType());
//        
//        if(event.getBetType() != null){
//            result.append(" ");
//            result.append(event.getBetType());
//            result.append(" ");
//            result.append(event.getAmount());
//        }
//        
        return result.toString();
    }
}