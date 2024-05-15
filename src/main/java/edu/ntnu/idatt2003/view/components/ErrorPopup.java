package edu.ntnu.idatt2003.view.components;

import javafx.stage.Window;

/**
 * A popup that displays an error message.
 */
public class ErrorPopup extends MessagePopup {
  public ErrorPopup(String message, Window ownerWindow) {
    super(message, ownerWindow);
    setWrapperStyle();
  }

  @Override
  void setWrapperStyle() {
    labelWrapper.setStyle("-fx-background-color: #ff9898;"
        + "-fx-background-radius: 10px;");
  }
}

