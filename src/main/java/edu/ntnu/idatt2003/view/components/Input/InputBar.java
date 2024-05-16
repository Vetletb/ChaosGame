package edu.ntnu.idatt2003.view.components.Input;

import javafx.scene.control.TextField;

/**
 * A text field for input.
 */
public class InputBar extends TextField {
  private static final int MAX_CHARS = 10;

  /**
   * Constructor for the InputBar class.
   */
  public InputBar() {
    super();
    setMaxSize(150, 18);
    this.getStyleClass().add("input-bar");

    this.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.length() > MAX_CHARS) {
        this.setText(oldValue);
      }
    });
  }
}
