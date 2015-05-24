/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.Label;

/**
 *
 * @author yuvalsegall
 */
public class PlayerViewWithAmount extends PlayerView{
    private Label playerAmountLabel;
    
    public PlayerViewWithAmount(String name, boolean isHuman) {
        super(name, isHuman);
        playerAmountLabel = new Label();
    }

    public Label getPlayerAmountLabel() {
        return playerAmountLabel;
    }
}
