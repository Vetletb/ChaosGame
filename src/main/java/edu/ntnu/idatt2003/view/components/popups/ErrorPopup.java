package edu.ntnu.idatt2003.view.components.popups;

/**
 * A popup that displays an error message.
 */
public class ErrorPopup extends MessagePopup {
  /**
   * Constructor for the ErrorPopup class.
   */
  public ErrorPopup() {
    super();
    setWrapperStyle();
  }

  /**
   * Sets the style of the wrapper.
   */
  @Override
  void setWrapperStyle() {
    getMessageWrapper().getStyleClass().add("error-popup");
  }
}

