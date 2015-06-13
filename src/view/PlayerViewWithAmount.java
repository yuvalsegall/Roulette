/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author yuvalsegall
 */
public class PlayerViewWithAmount extends PlayerView {

    private final Label playerAmountLabel;
    private int amount;

    public PlayerViewWithAmount(String name, boolean isHuman, int amount) {
        super(name, isHuman);
        playerAmountLabel = new Label();
        getChildren().addAll(playerAmountLabel);
        getName().getStyleClass().add("themeLabel");
        playerAmountLabel.getStyleClass().add("themeLabel");
        playerAmountLabel.textProperty().set(amount + "$");
        this.amount = amount;
    }

    public Label getPlayerAmountLabel() {
        return playerAmountLabel;
    }

    public void setIsBold(Boolean isBold) {
        if (isBold) {
            playerAmountLabel.getStyleClass().add("boldLabel");
            getName().getStyleClass().add("boldLabel");
        } else {
            playerAmountLabel.getStyleClass().remove("boldLabel");
            getName().getStyleClass().remove("boldLabel");
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        Platform.runLater(() -> {
            this.playerAmountLabel.setText(amount + "$");
        });
    }
}
