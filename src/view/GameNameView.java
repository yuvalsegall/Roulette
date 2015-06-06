package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameNameView extends VBox {

    private Label name;

    public GameNameView(String title) {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        name = createLabel(title);
        getChildren().addAll(name);
        this.getStyleClass().add("paddedBox");
    }

    private Label createLabel(String title) {
        return new Label(title);
    }

    public Label getName() {
        return name;
    }
}
