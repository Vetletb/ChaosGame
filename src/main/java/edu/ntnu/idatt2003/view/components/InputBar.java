package edu.ntnu.idatt2003.view.components;

import javafx.scene.control.TextField;

public class InputBar extends TextField {
  private static final int MAX_CHARS = 10;

  public InputBar() {
    super();
    this.setPrefWidth(150);
    this.setMaxHeight(18);
    this.getStyleClass().add("input-bar");

    this.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.length() > MAX_CHARS) {
        this.setText(oldValue);
      }
    });
    }
}
