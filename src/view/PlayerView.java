package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author iblecher
 */
public class PlayerView extends VBox {

    public PlayerView(String title, boolean isHuman) {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        getChildren().addAll(createLabel(title));
    }

    private Label createLabel(String title) {
        return new Label(title);
    }
}
