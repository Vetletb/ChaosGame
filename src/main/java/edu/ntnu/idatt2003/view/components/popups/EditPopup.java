package edu.ntnu.idatt2003.view.components.popups;

import edu.ntnu.idatt2003.controller.ChaosGameController;
import edu.ntnu.idatt2003.view.components.buttons.CloseButton;
import javafx.scene.layout.StackPane;

public class EditPopup extends StackPane {
  public EditPopup(ChaosGameController controller) {
    super();
    CloseButton closeButton = new CloseButton();
    closeButton.setOnAction(e -> this.setVisible(false));

    StackPane editWrapper = new StackPane();
    editWrapper.getStyleClass().add("edit-wrapper");
    editWrapper.setMaxSize(400, 500);

    editWrapper.getChildren().add(closeButton);

    this.getStyleClass().add("edit-popup");
    this.getChildren().add(editWrapper);
    this.setVisible(false);
  }

  public void show() {
    this.toFront();
    this.setVisible(true);
  }
}
