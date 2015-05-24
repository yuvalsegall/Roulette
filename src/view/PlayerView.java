package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerView extends VBox {
    private Label name;
    
    public PlayerView(String title, boolean isHuman) {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        name = createLabel(title);
        getChildren().addAll(name);
    }

    private Label createLabel(String title) {
        return new Label(title);
    }

    public Label getName() {
        return name;
    }
}
