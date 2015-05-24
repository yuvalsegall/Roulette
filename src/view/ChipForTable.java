/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author yuvalsegall
 */
public class ChipForTable extends StackPane{
    private Label value;
    private ImageView imageView;
    private static final String imageUrl = "/resources/blueChip.png";
    
    public ChipForTable(String value){
        this.value = new Label();
        this.imageView = new ImageView(imageUrl);
        this.value.textProperty().set(value);
        this.getChildren().addAll(this.imageView, this.value);
    }
}
