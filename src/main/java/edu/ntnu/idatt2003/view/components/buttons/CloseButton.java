package edu.ntnu.idatt2003.view.components.buttons;

import javafx.scene.control.Button;

public class CloseButton extends Button {
    public CloseButton() {
        super("Close");
        this.getStyleClass().add("close-button");
        this.setPrefSize(50, 20);
    }
}
