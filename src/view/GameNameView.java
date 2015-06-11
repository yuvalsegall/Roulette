package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameNameView extends VBox {

    private Button name;

    public GameNameView(String title) {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        name = createButton(title);
        getChildren().addAll(name);
        this.getStyleClass().add("paddedBox");
    }

    private Button createButton(String title) {
        return new Button(title);
    }

    public Button getName() {
        return name;
    }
}
