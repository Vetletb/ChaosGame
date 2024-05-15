package edu.ntnu.idatt2003.view.components;

import javafx.stage.Window;

/**
 * A popup that displays a success message.
 */
public class SuccessPopup extends MessagePopup {
  public SuccessPopup(String message, Window ownerWindow) {
    super(message, ownerWindow);
    setWrapperStyle();
  }

  /**
   * Sets the style of the wrapper.
   */
  @Override
  void setWrapperStyle() {
    labelWrapper.setStyle("-fx-background-color: #98ff98;"
              + "-fx-background-radius: 10px;");
  }
}
