package edu.ntnu.idatt2003.view;

import edu.ntnu.idatt2003.view.components.popups.EditPopup;
import edu.ntnu.idatt2003.view.components.popups.ErrorPopup;
import edu.ntnu.idatt2003.view.components.popups.SuccessPopup;

public class PopupHandler {
  private final ErrorPopup errorPopup;
  private final SuccessPopup successPopup;
  private final EditPopup editPopup;

  public PopupHandler(ErrorPopup errorPopup, SuccessPopup successPopup, EditPopup editPopup) {
    this.errorPopup = errorPopup;
    this.successPopup = successPopup;
    this.editPopup = editPopup;
  }

  public void showErrorPopup(String message) {
    errorPopup.setText(message);
    errorPopup.show();
  }

  public void showSuccessPopup(String message) {
    successPopup.setText(message);
    successPopup.show();
  }

  public void showEditPopup() {
    editPopup.show();
  }
}
