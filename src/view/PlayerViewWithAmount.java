/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import engine.Player;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

/**
 *
 * @author yuvalsegall
 */
public class PlayerViewWithAmount extends PlayerView{
    private Label playerAmountLabel;
    private Player player;
    
    public PlayerViewWithAmount(Player player) {
        super(player.getPlayerDetails().getName(), player.getPlayerDetails().getIsHuman());
        //TODO:fix design. looks like shit
        this.player = player;
        playerAmountLabel = new Label();
        getChildren().addAll(playerAmountLabel);
    }

    public Label getPlayerAmountLabel() {
        return playerAmountLabel;
    }
    
    public void setIsBold(Boolean isBold){
        if(isBold)
            playerAmountLabel.getStyleClass().add("boldLabel");
        else
            playerAmountLabel.getStyleClass().remove("boldLabel");
    }

    public Player getPlayer() {
        return player;
    }
}
