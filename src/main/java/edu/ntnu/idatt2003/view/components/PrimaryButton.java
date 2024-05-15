package edu.ntnu.idatt2003.view.components;

import javafx.scene.control.Button;

/**
 * A button with a primary style.
 */
public class PrimaryButton extends Button {
  /**
   * Constructor for the PrimaryButton class.
   *
   * @param text the text of the button.
   */
  public PrimaryButton(String text) {
    super(text);
    this.getStyleClass().add("primary-button");
  }
}
