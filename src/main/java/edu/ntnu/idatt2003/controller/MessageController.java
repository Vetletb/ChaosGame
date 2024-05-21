package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.view.components.popups.MessagePopup;

/**
 * A controller for displaying messages to the user. Shows error and success popups.
 */
public class MessageController {
  private final MessagePopup successPopup;
  private final MessagePopup errorPopup;

  /**
   * Constructor for the MessageController class.
   *
   * @param successPopup the success popup to be shown
   * @param errorPopup the error popup to be shown
   */
  public MessageController(MessagePopup successPopup, MessagePopup errorPopup) {
    this.successPopup = successPopup;
    this.errorPopup = errorPopup;
  }

  /**
   * Shows an error popup with the given message.
   *
   * @param message the message to be shown
   */
  public void showErrorPopup(String message) {
    errorPopup.setText(message);
    errorPopup.show();
  }

  /**
   * Shows a success popup with the given message.
   *
   * @param message the message to be shown
   */
  public void showSuccessPopup(String message) {
    successPopup.setText(message);
    successPopup.show();
  }
}
