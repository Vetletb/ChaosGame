package edu.ntnu.idatt2003.view.components.popups;

/**
 * A popup that displays a success message.
 */
public class SuccessPopup extends MessagePopup {
  public SuccessPopup() {
    super();
    setWrapperStyle();
  }

  /**
   * Sets the style of the wrapper.
   */
  @Override
  void setWrapperStyle() {
    getMessageWrapper().getStyleClass().add("success-popup");
  }
}
