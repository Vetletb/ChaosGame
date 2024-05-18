package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.view.components.popups.ErrorPopup;
import edu.ntnu.idatt2003.view.components.popups.SuccessPopup;

/**
 * A controller for displaying messages to the user. Shows error and success popups.
 */
public class MessageController {
  private final SuccessPopup successPopup;
  private final ErrorPopup errorPopup;

  /**
   * Constructor for the MessageController class.
   *
   * @param successPopup the success popup to be shown
   * @param errorPopup the error popup to be shown
   */
  public MessageController(SuccessPopup successPopup, ErrorPopup errorPopup) {
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
