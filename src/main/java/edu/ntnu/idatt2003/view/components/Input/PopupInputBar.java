package edu.ntnu.idatt2003.view.components.Input;

import javafx.scene.control.TextField;

/**
 * A text field for input.
 */
public class PopupInputBar extends TextField {
  private static final int MAX_CHARS = 4;

  /**
     * Constructor for the PopupInputBar class.
     */
  public PopupInputBar() {
    super();
    setMaxSize(100, 18);
    this.getStyleClass().add("popup-input-bar");
    this.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.length() > MAX_CHARS) {
        this.setText(oldValue);
      }
    });
    }
}
