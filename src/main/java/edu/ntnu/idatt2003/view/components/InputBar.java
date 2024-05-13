package edu.ntnu.idatt2003.view.components;

import javafx.scene.control.TextField;

public class InputBar extends TextField {
    public InputBar() {
        super();
        this.setPrefWidth(150);
        this.setMaxHeight(18);
        this.getStyleClass().add("input-bar");
    }
}
