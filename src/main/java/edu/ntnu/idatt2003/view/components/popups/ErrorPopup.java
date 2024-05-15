package edu.ntnu.idatt2003.view.components.popups;

/**
 * A popup that displays an error message.
 */
public class ErrorPopup extends MessagePopup {
  /**
   * Constructor for the ErrorPopup class.
   *
   * @param message     the message to display.
   * @param ownerWindow the window that owns the popup.
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
    messageWrapper.getStyleClass().add("error-popup");
  }
}

