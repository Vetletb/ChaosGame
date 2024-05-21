package edu.ntnu.idatt2003.view.components.popups;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * A popup that displays a message to the user.
 */
public abstract class MessagePopup extends StackPane {
  private final StackPane messageWrapper;
  private final Label message;
  private PauseTransition pause;

  /**
   * Creates a new message popup with the given message and owner window.
   */
  protected MessagePopup() {
    super();
    message = new Label();
    message.getStyleClass().add("popup-message");

    messageWrapper = new StackPane(message);
    messageWrapper.getStyleClass().add("popup");

    this.getChildren().add(messageWrapper);
    this.setMaxHeight(50);

    this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.setVisible(false));
    this.setVisible(false);
  }

  public StackPane getMessageWrapper() {
    return messageWrapper;
  }

  /**
   * Sets the text of the popup.
   *
   * @param message the message to be shown
   */
  public void setText(String message) {
    this.message.setText(message);
    messageWrapper.setMaxSize(message.length() * 9.0, 50);
  }

  /**
   * Shows the popup for 3 seconds.
   */
  public void show() {
    this.toFront();
    this.setVisible(true);
    pause = new PauseTransition(Duration.seconds(3));
    pause.setOnFinished(e -> this.setVisible(false));
    pause.play();
  }

  /**
   * Sets the style of the wrapper.
   */
  abstract void setWrapperStyle();
}
