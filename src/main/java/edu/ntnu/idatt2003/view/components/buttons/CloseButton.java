package edu.ntnu.idatt2003.view.components.buttons;

import javafx.scene.control.Button;

/**
 * A button for closing a popup.
 */
public class CloseButton extends Button {

  /**
   * Constructor for the CloseButton class.
   */
  public CloseButton() {
    super("X");
    this.getStyleClass().add("close-button");
    this.setMinSize(30, 30);
    this.setMaxSize(30, 30);
  }
}
