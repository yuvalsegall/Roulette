/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author yuvalsegall
 */
public class PlayerViewWithImage extends PlayerView {

    public PlayerViewWithImage(String title, boolean isHuman) {
        super(title, isHuman);
        getChildren().addAll(createImage(isHuman));
    }
    
    private ImageView createImage(boolean isHuman) {
        return new ImageView(getImage(isHuman));
    }

    private Image getImage(boolean isHuman) {
        String filename = isHuman ? "human" : "computer";
        return ImageUtils.getImage(filename);
    }    
}
