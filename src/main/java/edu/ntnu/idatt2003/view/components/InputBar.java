package edu.ntnu.idatt2003.view.components;

import javafx.scene.control.TextField;

public class InputBar extends TextField {
    public InputBar() {
        super();
        this.setPrefWidth(150);
        this.setMaxHeight(20);
        this.setStyle("-fx-font-size: 18px; -fx-background-color: #ffffff; -fx-text-fill: #000000;-fx-background-radius: 10px");
    }
}
