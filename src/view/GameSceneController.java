/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
<<<<<<< HEAD
=======
 asdcascascsa
>>>>>>> origin/master
 */
package view;

import engine.BadParamsException;
import engine.Game;
import engine.Player;
import engine.XMLGame;
import engine.bets.Bet;
import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    private Button button0_2_3;
    @FXML
    private Button button0_1_2;
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
    private Label betNameLabel;
    @FXML
    private Label AmmountLabel;
    @FXML
    private Button FinishBettingButton;
    @FXML
    private Button clearAmmountButton;
    @FXML
    private Button placeBetButton;
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

    private Game game;
    private String filePath;
    private ArrayList<Integer> numbers;
    private Bet bet;
<<<<<<< HEAD
    private final int NOM_OF_ACTUAL_ROWS = 3;
    private final int COLS_TO_FIRST_NUMBER = 3;
    
            
=======
    private final int NOM_OF_ROWS = 3;
    private final int FIRST_NUMBER_COLS = 3;

    private static final Bet.BetType COMP_BET_TYPE = Bet.BetType.NOIR;
    private static final int COMP_BET_MONEY = 1;
    private static final int[] COMP_BET_NUMBERS = null;

    private boolean isErrorMessageShown;

>>>>>>> origin/master
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numbers = new ArrayList<>();
        isErrorMessageShown = false;
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

    private void playRound() {
        for (Player.PlayerDetails player : game.getGameDetails().getPlayersDetails()) {
            if (player.getIsActive()) {
                if (player.getIsHuman()) {
//                    ConsoleUI.printToUser(player.getName() + "'s  turn!");
//                    if (ConsoleUI.getIsSaveGameFromUser()) {
//                        saveGame();
//                    }
//                    if (ConsoleUI.getYesNoAnswer("Do you want quit from the game?")) {
//                        quitPlayer(player);
//                        continue;
//                    }
                }
                if (player.getMoney().compareTo(BigInteger.ZERO) <= 0) {
//                    ConsoleUI.PrintUserIsBrokeGoingToNextPlayerToUser(player.getName());
                } else {
                    placeBets(player);
                }
            }
        }
//        ConsoleUI.printUserSpinRoulleteResult(game.getTable().spinRoulette());

        endRound();
    }

    private void saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML", Arrays.asList("xml")));
        fileChooser.setTitle("Save Roulette Game");
        if (filePath == null || filePath.trim().isEmpty()) {
            fileChooser.setInitialDirectory(new File(filePath));
        }
        File XMLFile = fileChooser.showSaveDialog(primaryStage);
        new Thread(() -> {
            try {
                XMLGame.setXMLGame(filePath, game);
            } catch (JAXBException ex) {
                showError("cannot save XML, try again");
            }
        }).start();
    }

    private void endRound() {
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
    }

    private void placeBets(Player.PlayerDetails player) {
        Bet.BetType betType;
        BigInteger betMoney;
        int[] numbers = null;
        int i;
        List<Bet> bets;

        if (player.getBets() == null || player.getBets().isEmpty()) {
            bets = new ArrayList<>();

            if (game.getGameDetails().getMinWages() == 0 && player.getIsHuman()) {
                if (!ConsoleUI.getYesNoAnswer("Do you want to place a bet?")) {
                    return;
                }
            }
            i = 0;
        } else {
            bets = player.getBets();
            i = bets.size();
        }
        while (i++ <= game.getGameDetails().getMaxWages()) {
            if (player.getIsHuman()) {
                betType = ConsoleUI.getBetTypeFromUser();
                betMoney = ConsoleUI.getBetMoneyFromUser();
                while (betMoney.compareTo(player.getMoney()) > 0 || betMoney.compareTo(BigInteger.ZERO) == 0) {
                    if (betMoney.compareTo(BigInteger.ZERO) == 0) {
                        ConsoleUI.printToUser("Cannot bet 0 money");
                    } else {
                        ConsoleUI.printToUser("You can't bet on more money that you owned, try again");
                    }
                    betMoney = ConsoleUI.getBetMoneyFromUser();
                }
                numbers = betType.isAskUserForNumbers() ? ConsoleUI.getBetNumbersFromUser(betType, game.getTable().getTableType()) : null;

            } else {
                betType = COMP_BET_TYPE;
                betMoney = BigInteger.valueOf(COMP_BET_MONEY);
                numbers = COMP_BET_NUMBERS;
                if (numbers == null || numbers.length == 0) {
                    ConsoleUI.printToUser(player.getName() + " bet " + betMoney + "$ on: " + betType);
                } else {
                    ConsoleUI.printToUser(player.getName() + " bet " + betMoney + "$ on: " + betType + " with the numbers:" + Arrays.toString(numbers));
                }
            }
            try {
                bets.add(Bet.makeBet(betType, betMoney, numbers, game.getTable().getTableType()));
            } catch (BadParamsException ex) {
                ConsoleUI.printToUser(ex.getMessage() + ", try again");
                i--;
                continue;
            }
            player.setMoney(player.getMoney().add(betMoney.negate()));
            if (player.getIsHuman() && game.getGameDetails().getMaxWages() != i && player.getMoney().compareTo(BigInteger.ZERO) > 0) {
                if (ConsoleUI.getIsFinishedBettingFromUser()) {
                    break;
                }
            } else {
                break;
            }
        }
        player.setBets(bets);
    }

    private void quitPlayer(Player.PlayerDetails player) {
        player.setIsActive(Boolean.FALSE);
    }

    private void showError(String message) {
        if (!isErrorMessageShown) {
            isErrorMessageShown = true;
            messageLabel.setText(message);
            FadeTransition animation = new FadeTransition();
            animation.setNode(messageLabel);
            animation.setDuration(Duration.seconds(0.3));
            animation.setFromValue(0.0);
            animation.setToValue(1.0);
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
<<<<<<< HEAD
       HashMap<Integer,Integer> rows = new HashMap();
       Node node = ((Button)event.getSource()).getParent();
       setRowsHashMap(rows);
       int row = GridPane.getRowIndex(node);     
       int col = GridPane.getColumnIndex(node);
       int myCol = col - COLS_TO_FIRST_NUMBER;
       int myRow = rows.get(row);
       myCol -= myCol/2;
       myRow -= myRow/2;
       
       if(col % 2 == 0){
           if (row % 2 == 0){
               //vertical split
               numbers.add(myCol * NOM_OF_ACTUAL_ROWS - 1);
               if (row < NOM_OF_ACTUAL_ROWS)
                   numbers.add(myCol * NOM_OF_ACTUAL_ROWS);
               else
                   numbers.add(myCol * NOM_OF_ACTUAL_ROWS - 2);
           }
           else{
               //single number
               numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
           }
       }
       else{
           if (row % 2 == 0){
               //corner
               numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
               numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow));
               numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow - 1));
               numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow - 1));
           }
           else{
               //horizontal split
               numbers.add(NOM_OF_ACTUAL_ROWS * myCol - (NOM_OF_ACTUAL_ROWS - myRow));
               numbers.add(NOM_OF_ACTUAL_ROWS * (myCol + 1) - (NOM_OF_ACTUAL_ROWS - myRow));
           }
       }
       messageLabel.setText(numbers.toString());
=======
        HashMap<Integer, Integer> rows = new HashMap();
        setRowsHashMap(rows);
        Node node = ((Button) event.getSource()).getParent();
        int row = GridPane.getRowIndex(node);
        int col = GridPane.getColumnIndex(node);
        if (col % 2 == 0) {
            if (row % 2 == 0) {
                //vertical split
                int myCol = col - FIRST_NUMBER_COLS;
                myCol = myCol - myCol / 2;
                numbers.add(myCol * NOM_OF_ROWS - 1);
                if (row > NOM_OF_ROWS) {
                    numbers.add(myCol * NOM_OF_ROWS);
                } else {
                    numbers.add(myCol * NOM_OF_ROWS - 2);
                }
            } else {
                //single number
                int myRow = rows.get(row);
                myRow -= myRow / 2;
                int myCol = col - FIRST_NUMBER_COLS;
                myCol -= myCol / 2;
                numbers.add(NOM_OF_ROWS * myCol - (NOM_OF_ROWS - myRow));
            }
        } else {
            if (row % 2 == 0) {
                //corner
            } else {
                //horizontal split
            }
        }
        messageLabel.setText(numbers.toString());
>>>>>>> origin/master
    }

    @FXML
    private void AddOneClicked(ActionEvent event) {
    }

    @FXML
    private void AddTwoClicked(ActionEvent event) {
    }

    @FXML
    private void AddFiveClicked(ActionEvent event) {
    }

    @FXML
    private void AddTenClicked(ActionEvent event) {
    }

    @FXML
    private void finishedBettingClicked(ActionEvent event) {
    }

    @FXML
    private void clearAmountClicked(ActionEvent event) {
    }

    @FXML
    private void placeBetClicked(ActionEvent event) {
    }

    @FXML
    private void thirdRowClicked(ActionEvent event) {
    }

    @FXML
    private void secondRowClicked(ActionEvent event) {
    }

    @FXML
    private void firstRowClicked(ActionEvent event) {
    }

    @FXML
    private void first12Clicked(ActionEvent event) {
    }

    @FXML
    private void second12Clicked(ActionEvent event) {
    }

    @FXML
    private void third12Clicked(ActionEvent event) {
    }

    @FXML
    private void firstHalfClicked(ActionEvent event) {
    }

    @FXML
    private void evenClicked(ActionEvent event) {
    }

    @FXML
    private void redClicked(ActionEvent event) {
    }

    @FXML
    private void blackClicked(ActionEvent event) {
    }

    @FXML
    private void oddClicked(ActionEvent event) {
    }

    @FXML
    private void secondHalfClicked(ActionEvent event) {
    }

    private void setRowsHashMap(HashMap<Integer, Integer> rows) {
        rows.put(1, 5);
        rows.put(2, 4);
        rows.put(3, 3);
        rows.put(4, 2);
        rows.put(5, 1);
    }
}
