package edu.ntnu.idatt2003.view.components;

/**
 * A button with a secondary style.
 */
public class SecondaryButton extends PrimaryButton {
  /**
   * Constructor for the SecondaryButton class.
   *
   * @param text the text of the button.
   */
  public SecondaryButton(String text) {
    super(text);
    this.getStyleClass().add("secondary-button");
  }
}
