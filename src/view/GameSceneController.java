package view;

import engine.BadParamsException;
import engine.Game;
import engine.Player;
import engine.Table;
import engine.XMLGame;
import engine.bets.Bet;
import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.RotateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.bind.JAXBException;

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

    private Game game;
    private Player currentPlayer;
    private String filePath;
    private ArrayList<Integer> numbers;
    private ArrayList<Bet> currentBets;
    private Bet.BetType betType;
    private IntegerProperty amount;
    private final HashMap<Integer, Integer> rowsReverse = new HashMap();
    private final int NOM_OF_ACTUAL_ROWS = 3;
    private final int COLS_TO_FIRST_NUMBER = 3;
//    private static final Bet.BetType COMP_BET_TYPE = Bet.BetType.NOIR;
//    private static final int COMP_BET_MONEY = 1;//        TODO delete
//    private static final int[] COMP_BET_NUMBERS = null;
    private final String AMERICAN_TABLE_IMAGE_PATH = "/resources/table00.png";
    private final String AMERICAN_WHEEL_IMAGE_PATH = "/resources/roulette00_300.png";

    private boolean isErrorMessageShown;
    private SimpleBooleanProperty newGame;
    private SimpleBooleanProperty exitGame;

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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numbers = new ArrayList<>();
        currentBets = new ArrayList<>();
        isErrorMessageShown = false;
        amount = new SimpleIntegerProperty();
        amount.set(0);
        AmountLabel.textProperty().bind(
                Bindings.concat(amount, "$"));
        setRowsHashMap(rowsReverse);
        newGame = new SimpleBooleanProperty(false);
        exitGame = new SimpleBooleanProperty(false);
    }

    public void init() {
        if (game.getGameDetails().getTableType() == Table.TableType.AMERICAN) {
            setTableToAmerican();
        }
        buildPlayersPane();
        moveToNextHumanPlayer();
        ballPossitionLabel.textProperty().set("");
        messageLabel.textProperty().set("Choose amount, and click the table to place a bet");
    }

    public Boolean updateCurrentPlayerReturnIfLast() {
        Boolean isLastPlayer = false;
        if (currentPlayer != null && getCurrentPlayerView() != null) {
            getCurrentPlayerView().setIsBold(false);
            isLastPlayer = currentPlayer.equals(game.getGameDetails().getPlayers().get(game.getGameDetails().getPlayers().size() - 1));
        }
        currentPlayer = currentPlayer == null ? game.getGameDetails().getPlayers().get(0) : game.getGameDetails().getPlayers().get((game.getGameDetails().getPlayers().indexOf(currentPlayer) + 1) % game.getGameDetails().getPlayers().size());
        if (getCurrentPlayerView() != null) {
            getCurrentPlayerView().setIsBold(true);
        }
        return isLastPlayer;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @FXML
    private void saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML", Arrays.asList("xml")));
        fileChooser.setTitle("Save Roulette Game");
        if (filePath != null && !filePath.trim().isEmpty()) {
            fileChooser.setInitialDirectory(new File(filePath));
        }
        File XMLFile = fileChooser.showSaveDialog(primaryStage);
        if (XMLFile != null) {
            new Thread(() -> {
                try {
                    filePath = XMLFile.getPath();
                    XMLGame.setXMLGame(filePath, game);
                } catch (JAXBException ex) {
                    showError("cannot save XML, try again");
                }
            }).start();
        }
    }

    private void endRound() {
        spinRoulette();
        game.getGameDetails().getPlayers().stream().map((player) -> {
            if (player.getPlayerDetails().getBets() != null) {
                player.getPlayerDetails().getBets().stream().forEach((bet) -> {
                    player.getPlayerDetails().setMoney(player.getPlayerDetails().getMoney().add(bet.winningSum(game.getTable().getCurrentBallPosition(), game.getTable().getCells().length)));
                });
            }
            return player;
        }).forEach((player) -> {
            player.getPlayerDetails().setBets(null);
        });
        if (!isAnybodyLeft()) {
            popupGoodbyeDialog("Game Over", "No active human players, goodbye!");
        }
    }

    private void retirePlayer(Player.PlayerDetails player) {
        player.setIsActive(Boolean.FALSE);
    }

    private void computerPlay() {
//        try {
//        TODO delete
//            currentBets.add(Bet.makeBet(COMP_BET_TYPE, BigInteger.valueOf(COMP_BET_MONEY), COMP_BET_NUMBERS, game.getTable().getTableType()));
//        } catch (BadParamsException ex) {
//        }
//        currentPlayer.getPlayerDetails().setMoney(currentPlayer.getPlayerDetails().getMoney().add(BigInteger.valueOf((int) amount.getValue() * -1)));
        plus1Button.fire();
        button_black.fire();
        FinishBettingButton.fire();
    }

    private void showError(String message) {
        if (!isErrorMessageShown) {
            isErrorMessageShown = true;
            messageLabel.setText(message);
            FadeTransition animation = new FadeTransition();
            animation.setNode(messageLabel);
            animation.setDuration(Duration.seconds(3));
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
                betType = Bet.BetType.SPLIT;
            } else {
                //single number
                numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
                betType = Bet.BetType.STRAIGHT;
            }
        } else {
            if (row % 2 == 0) {
                //corner
                numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
                numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow));
                numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow - 1));
                numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow - 1));
                betType = Bet.BetType.CORNER;
            } else {
                //horizontal split
                numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
                numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow));
                betType = Bet.BetType.SPLIT;
            }
        }
        addBetOnTable(event);
    }

    @FXML
    private void AddOneClicked(ActionEvent event) {
        amount.set(amount.get() + 1);
    }

    @FXML
    private void AddTwoClicked(ActionEvent event) {
        amount.set(amount.get() + 2);
    }

    @FXML
    private void AddFiveClicked(ActionEvent event) {
        amount.set(amount.get() + 5);
    }

    @FXML
    private void AddTenClicked(ActionEvent event) {
        amount.set(amount.get() + 10);
    }

    @FXML
    private void finishedBettingClicked(ActionEvent event) {
        FinishBettingButton.setDisable(true);
        currentPlayer.getPlayerDetails().setBets(currentBets);
        moveToNextHumanPlayer();
    }

    private void moveToNextHumanPlayer() {
        do {
            clearChips();
            if (updateCurrentPlayerReturnIfLast()) {
                endRound();
            }
            currentBets = new ArrayList<>();
            if (!currentPlayer.getPlayerDetails().getIsHuman()) {
                computerPlay();
            }
            if (currentPlayer.getPlayerDetails().getIsActive() && currentPlayer.getPlayerDetails().getMoney().compareTo(BigInteger.ZERO) <= 0) {
                popupGoodbyeDialog("Retire", currentPlayer.getPlayerDetails().getName() + " you are broke, Goodbye!");
                retireButton.fire();
            }
        } while (!currentPlayer.getPlayerDetails().getIsHuman() || !currentPlayer.getPlayerDetails().getIsActive());
        FinishBettingButton.setDisable(game.getGameDetails().getMinWages() == 1);
    }

    private void clearAmountClicked(ActionEvent event) {
        amount.set(0);
    }

    @FXML
    private void thirdRowClicked(ActionEvent event) {
        betType = Bet.BetType.COLUMN_3;
        addBetOnTable(event);
    }

    @FXML
    private void secondRowClicked(ActionEvent event) {
        betType = Bet.BetType.COLUMN_2;
        addBetOnTable(event);
    }

    @FXML
    private void firstRowClicked(ActionEvent event) {
        betType = Bet.BetType.COLUMN_1;
        addBetOnTable(event);
    }

    @FXML
    private void first12Clicked(ActionEvent event) {
        betType = Bet.BetType.PREMIERE_DOUZAINE;
        addBetOnTable(event);
    }

    @FXML
    private void second12Clicked(ActionEvent event) {
        betType = Bet.BetType.MOYENNE_DOUZAINE;
        addBetOnTable(event);
    }

    @FXML
    private void third12Clicked(ActionEvent event) {
        betType = Bet.BetType.DERNIERE_DOUZAINE;
        addBetOnTable(event);
    }

    @FXML
    private void firstHalfClicked(ActionEvent event) {
        betType = Bet.BetType.MANQUE;
        addBetOnTable(event);
    }

    @FXML
    private void evenClicked(ActionEvent event) {
        betType = Bet.BetType.PAIR;
        addBetOnTable(event);
    }

    @FXML
    private void redClicked(ActionEvent event) {
        betType = Bet.BetType.ROUGE;
        addBetOnTable(event);
    }

    @FXML
    private void blackClicked(ActionEvent event) {
        betType = Bet.BetType.NOIR;
        addBetOnTable(event);
    }

    @FXML
    private void oddClicked(ActionEvent event) {
        betType = Bet.BetType.IMPAIR;
        addBetOnTable(event);
    }

    @FXML
    private void secondHalfClicked(ActionEvent event) {
        betType = Bet.BetType.PASSE;
        addBetOnTable(event);
    }

    private void setRowsHashMap(HashMap<Integer, Integer> rows) {
        rows.put(1, 5);
        rows.put(2, 4);
        rows.put(3, 3);
        rows.put(4, 2);
        rows.put(5, 1);
    }

    public void buildPlayersPane() {
        playersPane.getChildren().remove(0, playersPane.getChildren().size());
        game.getGameDetails().getPlayers().stream().forEach((player) -> {
            if (player.getPlayerDetails().getIsActive()) {
                PlayerViewWithAmount playerView = new PlayerViewWithAmount(player);
                playersPane.getChildren().add(playerView);
                playerView.getPlayerAmountLabel().textProperty().bind(
                        Bindings.concat(player.getPlayerDetails().getAmount(), "$"));
            }
        });
    }

    private PlayerViewWithAmount getCurrentPlayerView() {
        for (Node playerView : playersPane.getChildren()) {
            PlayerViewWithAmount view = (PlayerViewWithAmount) playerView;
            if (view.getPlayer() == currentPlayer) {
                return view;
            }
        }
        return null;
    }

    private void clearChips() {
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
    }

    private void addBetOnTable(ActionEvent event) {
        ChipForTable newChip = new ChipForTable(amount.getValue().toString() + '$');
        Button initiatingButton = (Button) event.getSource();
        AnchorPane parent = (AnchorPane) initiatingButton.getParent();
        parent.getChildren().add(newChip);
        placeBet();
    }

    private void placeBet() {
        if (!playerHasMoneyForBet()) {
            showError("You cannot place money you do not have");
            amount.set(0);
            numbers.clear();
            clearChips();
        } else {
            int[] nums = null;
            if (!numbers.isEmpty()) {
                nums = new int[numbers.size()];
                int count = 0;
                for (Integer n : numbers) {
                    nums[count++] = n;
                }
            }
            try {
                currentBets.add(Bet.makeBet(betType, BigInteger.valueOf((amount.getValue())), nums, game.getTable().getTableType()));
            } catch (BadParamsException ex) {
                showError(ex.getMessage() + ", try again");
            }
            currentPlayer.getPlayerDetails().setMoney(currentPlayer.getPlayerDetails().getMoney().add(BigInteger.valueOf((int) amount.getValue() * -1)));
            amount.set(0);
            numbers.clear();
            FinishBettingButton.setDisable(false);
        }
    }

    @FXML
    private void snakeClicked(ActionEvent event) {
        betType = Bet.BetType.SNAKE;
        addBetOnTable(event);
    }

    void setTableToAmerican() {
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
        betType = Bet.BetType.STRAIGHT;
        numbers.add(0);
        addBetOnTable(event);
    }

    private void doubleZeroClicked(ActionEvent event) {
        betType = Bet.BetType.STRAIGHT;
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
        betType = Bet.BetType.BASKET;
        for (int num : Game.ConstValuesForBets.BASKET_AM_V1) {
            numbers.add(num);
        }
        addBetOnTable(event);
    }

    private void basketAM_V2Clicked(ActionEvent event) {
        betType = Bet.BetType.BASKET;
        for (int num : Game.ConstValuesForBets.BASKET_AM_V2) {
            numbers.add(num);
        }
        addBetOnTable(event);
    }

    private void basketAM_V3Clicked(ActionEvent event) {
        betType = Bet.BetType.BASKET;
        for (int num : Game.ConstValuesForBets.BASKET_AM_V3) {
            numbers.add(num);
        }
        addBetOnTable(event);
    }

    private void basketFRClicked(ActionEvent event) {
        betType = Bet.BetType.BASKET;
        for (int num : Game.ConstValuesForBets.BASKET_FR) {
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

    @FXML
    private void onNewGame(ActionEvent event) {
        saveGame();
        newGame.set(popupDialog("New Game", "Are you sure you want to start a new game?"));
    }

    @FXML
    private void onExitGame(ActionEvent event) {
        exitGame.set(popupDialog("Exit Game", "Are you sure you want to leave?"));
    }

    private Boolean popupDialog(String title, String content) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle(title);
//        alert.setContentText(content);
//        Optional<ButtonType> result = alert.showAndWait();
//        return result.get() == ButtonType.OK;
        //TODO return notes
        return false;
    }

    private void popupGoodbyeDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait(); //        TODO return notes
    }

    private boolean playerHasMoneyForBet() {
        return currentPlayer.getPlayerDetails().getMoney().intValue() >= amount.intValue();
    }

    private void spinRoulette() {
        RotateTransition rt = new RotateTransition(Duration.millis(3000), rouletteImageView);
        game.getTable().spinRoulette();
        rt.setByAngle(360);
        rt.setCycleCount(1);
        rt.setDuration(Duration.seconds(3));
        rt.setAutoReverse(false);

        rt.play();
        setBallPosLabel();
    }

    private void setBallPosLabel() {
        ballPossitionLabel.textProperty().set("Ball on: " + game.getTable().getCurrentBallPosition().getValue());
    }

    @FXML
    private void onRetire(ActionEvent event) {
        retirePlayer(currentPlayer.getPlayerDetails());
        buildPlayersPane();
        moveToNextHumanPlayer();
    }

    private boolean isAnybodyLeft() {
        return game.getGameDetails().getPlayers().stream().anyMatch((player) -> (player.getPlayerDetails().getIsActive() && player.getPlayerDetails().getIsHuman()));
    }
}
