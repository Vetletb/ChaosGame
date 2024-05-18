package edu.ntnu.idatt2003.view.components.input;

import javafx.scene.control.TextField;

/**
 * A text field for input.
 */
public class PopupInputBar extends TextField {
  /**
     * Constructor for the PopupInputBar class.
     */
  public PopupInputBar() {
    super();
    setMaxSize(100, 18);
    this.getStyleClass().add("popup-input-bar");
  }
}
