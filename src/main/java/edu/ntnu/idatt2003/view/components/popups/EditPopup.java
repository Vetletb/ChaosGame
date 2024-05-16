package edu.ntnu.idatt2003.view.components.popups;

import edu.ntnu.idatt2003.controller.ChaosGameController;
import edu.ntnu.idatt2003.view.components.Input.PopupInputBar;
import edu.ntnu.idatt2003.view.components.buttons.CloseButton;
import edu.ntnu.idatt2003.view.components.buttons.NextButton;
import edu.ntnu.idatt2003.view.components.buttons.PrevButton;
import edu.ntnu.idatt2003.view.components.buttons.PrimaryButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Popup for editing the description of a chaos game.

 */
public class EditPopup extends StackPane {
  VBox juliaWrapper;
  VBox affineWrapper;
  Button juliaButton;
  Button affineButton;
  StackPane transformWrapper;

  /**
   * Constructor for the EditPopup class.
   *
   * @param controller the controller for the chaos game.
   */
  public EditPopup(ChaosGameController controller) {
    super();
    CloseButton closeButton = new CloseButton();
    closeButton.setOnAction(e -> this.setVisible(false));
    AnchorPane closeButtonWrapper = new AnchorPane();
    closeButtonWrapper.getChildren().addAll(closeButton);
    AnchorPane.setRightAnchor(closeButton, 10.0);
    AnchorPane.setTopAnchor(closeButton, 10.0);

    Label transformText = new Label("Edit Description");
    transformText.getStyleClass().add("popup-title");
    HBox transformTitleWrapper = new HBox();
    transformTitleWrapper.getChildren().add(transformText);
    transformTitleWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    juliaButton = new Button("Julia");
    juliaButton.getStyleClass().add("secondary-button");
    juliaButton.setOnAction(e -> {
      affineButton.getStyleClass().remove("active-secondary-button");
      affineWrapper.setVisible(false);
      affineButton.setDisable(false);
      juliaButton.getStyleClass().add("active-secondary-button");
      juliaWrapper.setVisible(true);
      juliaButton.setDisable(true);
    });
    affineButton = new Button("Affine");
    affineButton.getStyleClass().add("secondary-button");
    affineButton.setOnAction(e -> {
      juliaButton.getStyleClass().remove("active-secondary-button");
      juliaWrapper.setVisible(false);
      juliaButton.setDisable(false);
      affineButton.getStyleClass().add("active-secondary-button");
      affineWrapper.setVisible(true);
      affineButton.setDisable(true);
    });
    HBox transformButtonsWrapper = new HBox();
    transformButtonsWrapper.getChildren().addAll(juliaButton, affineButton);
    transformButtonsWrapper.setAlignment(javafx.geometry.Pos.CENTER);
    transformButtonsWrapper.setSpacing(10);

    Label coordinateText = new Label("Coordinates");
    coordinateText.getStyleClass().add("popup-text");
    HBox coordinateTextWrapper = new HBox();
    coordinateTextWrapper.getChildren().add(coordinateText);
    coordinateTextWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    PopupInputBar minInput = new PopupInputBar();
    minInput.getStyleClass().add("input-bar");
    minInput.setPromptText("Min");
    minInput.setMaxSize(100, 18);
    PopupInputBar maxInput = new PopupInputBar();
    maxInput.getStyleClass().add("input-bar");
    maxInput.setPromptText("Max");
    maxInput.setMaxSize(100, 18);
    HBox coordinateInputWrapper = new HBox();
    coordinateInputWrapper.getChildren().addAll(minInput, maxInput);
    coordinateInputWrapper.setAlignment(javafx.geometry.Pos.CENTER);
    coordinateInputWrapper.setSpacing(10);

    Label juliaInputText = new Label("Constant c");
    juliaInputText.getStyleClass().add("popup-text");
    HBox juliaInputTextWrapper = new HBox();
    juliaInputTextWrapper.getChildren().add(juliaInputText);
    juliaInputTextWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    PopupInputBar juliaRealInput = new PopupInputBar();
    juliaRealInput.getStyleClass().add("input-bar");
    juliaRealInput.setPromptText("Real");
    PopupInputBar juliaImaginaryInput = new PopupInputBar();
    juliaImaginaryInput.getStyleClass().add("input-bar");
    juliaImaginaryInput.setPromptText("Imaginary");
    HBox juliaInputWrapper = new HBox();
    juliaInputWrapper.getChildren().addAll(juliaRealInput, juliaImaginaryInput);
    juliaInputWrapper.setAlignment(javafx.geometry.Pos.CENTER);
    juliaInputWrapper.setSpacing(10);

    juliaWrapper = new VBox();
    juliaWrapper.getChildren().addAll(juliaInputTextWrapper, juliaInputWrapper);
    juliaWrapper.setSpacing(5);
    juliaWrapper.setVisible(false);


    Label affineInputText = new Label("Transformations");
    affineInputText.getStyleClass().add("popup-text");
    HBox affineInputTextWrapper = new HBox();
    affineInputTextWrapper.getChildren().add(affineInputText);
    affineInputTextWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    PrevButton prevButton = new PrevButton();
    prevButton.setOnAction(e -> System.out.println("prev"));
    HBox prevButtonWrapper = new HBox();
    prevButtonWrapper.getChildren().add(prevButton);
    prevButtonWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    NextButton nextButton = new NextButton();
    nextButton.setOnAction(e -> System.out.println("next"));
    HBox nextButtonWrapper = new HBox();
    nextButtonWrapper.getChildren().add(nextButton);
    nextButtonWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    Label transformInputText = new Label("Transformation 1");
    transformInputText.getStyleClass().add("popup-text");
    HBox transformInputTextWrapper = new HBox();
    transformInputTextWrapper.getChildren().add(transformInputText);
    transformInputTextWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    PopupInputBar[] affineInputBars = new PopupInputBar[6];
    for (int i = 0; i < 6; i++) {
      affineInputBars[i] = new PopupInputBar();
      affineInputBars[i].getStyleClass().add("input-bar");
      if (i < 4) {
        affineInputBars[i].setPromptText("mat" + (i + 1));
      } else {
        affineInputBars[i].setPromptText("vec" + (i - 3));
      }
    }
    HBox affineInputWrapper = new HBox();
    affineInputWrapper.getChildren().addAll(affineInputBars);
    affineInputWrapper.setAlignment(javafx.geometry.Pos.CENTER);
    affineInputWrapper.setSpacing(10);

    affineWrapper = new VBox();
    affineWrapper.getChildren().addAll(
        affineInputTextWrapper,
        prevButtonWrapper,
        transformInputTextWrapper,
        affineInputWrapper,
        nextButtonWrapper);
    affineWrapper.setSpacing(5);
    affineWrapper.setMaxWidth(380);
    affineWrapper.setVisible(false);

    transformWrapper = new StackPane();
    transformWrapper.getChildren().addAll(juliaWrapper, affineWrapper);

    PrimaryButton saveButton = new PrimaryButton("Save");
    saveButton.setOnAction(e -> {
      this.setVisible(false);
      System.out.println("save");
    });
    AnchorPane saveButtonWrapper = new AnchorPane();
    saveButtonWrapper.getChildren().add(saveButton);
    AnchorPane.setBottomAnchor(saveButton, 10.0);
    AnchorPane.setRightAnchor(saveButton, 10.0);

    VBox editWrapper = new VBox();
    editWrapper.getStyleClass().add("edit-wrapper");
    editWrapper.setMaxSize(400, 400);
    editWrapper.getChildren().addAll(
        closeButtonWrapper,
        transformTitleWrapper,
        transformButtonsWrapper,
        coordinateTextWrapper,
        coordinateInputWrapper,
        transformWrapper,
        saveButtonWrapper);
    editWrapper.setSpacing(10);

    this.getStyleClass().add("edit-popup");
    this.getChildren().add(editWrapper);
    this.setVisible(false);
    juliaButton.fire();
  }

  public void show() {
    this.toFront();
    this.setVisible(true);
  }
}
