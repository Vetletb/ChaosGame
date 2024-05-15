package edu.ntnu.idatt2003.view.components;

import javafx.stage.Window;

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
  public ErrorPopup(String message, Window ownerWindow) {
    super(message, ownerWindow);
    setWrapperStyle();
  }

  /**
   * Sets the style of the wrapper.
   */
  @Override
  void setWrapperStyle() {
    labelWrapper.setStyle("-fx-background-color: #ff9898;"
        + "-fx-background-radius: 10px;");
  }
}

