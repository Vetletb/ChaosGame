package edu.ntnu.idatt2003.view.components;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * A popup that displays a message to the user.
 */
public abstract class MessagePopup extends Popup {
  StackPane labelWrapper;

  /**
   * Creates a new message popup with the given message and owner window.
   *
   * @param message     the message to display in the popup
   * @param ownerWindow the window that owns the popup
   */
  public MessagePopup(String message, Window ownerWindow) {
    super();
    Label popupLabel = new Label(message);
    popupLabel.setStyle("-fx-text-fill: #000000;"
          + "-fx-font-size: 18px;");

    labelWrapper = new StackPane(popupLabel);
    labelWrapper.setMaxHeight(50);
    if (popupLabel.getText() == null) {
      labelWrapper.setMaxWidth(200);
    } else {
      labelWrapper.setMaxWidth(popupLabel.getText().length() * 8);
    }

    StackPane popupWrapper = new StackPane();
    popupWrapper.getChildren().add(labelWrapper);

    this.getContent().add(popupWrapper);
    this.show(ownerWindow);
    popupWrapper.setMinSize(ownerWindow.getWidth(), ownerWindow.getHeight());
    this.hide();

    this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.hide());
    this.show(ownerWindow);

    PauseTransition pause = new PauseTransition(Duration.seconds(3));
    pause.setOnFinished(e -> this.hide());
    pause.play();
  }

  abstract void setWrapperStyle();
}
