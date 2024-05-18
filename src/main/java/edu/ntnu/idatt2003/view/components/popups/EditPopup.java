package edu.ntnu.idatt2003.view.components.popups;

import edu.ntnu.idatt2003.controller.EditController;
import edu.ntnu.idatt2003.view.components.buttons.CloseButton;
import edu.ntnu.idatt2003.view.components.buttons.NextButton;
import edu.ntnu.idatt2003.view.components.buttons.PrevButton;
import edu.ntnu.idatt2003.view.components.buttons.PrimaryButton;
import edu.ntnu.idatt2003.view.components.buttons.SecondaryButton;
import edu.ntnu.idatt2003.view.components.input.PopupInputBar;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


/**
 * Popup for editing the description of a chaos game.
 */
public class EditPopup extends StackPane  {
  private EditController editController;
  private final VBox juliaWrapper;
  private final VBox affineWrapper;
  private final Button juliaButton;
  private final Button affineButton;
  private final PopupInputBar minXinput;
  private final PopupInputBar minYinput;
  private final PopupInputBar maxXinput;
  private final PopupInputBar maxYinput;
  private final PopupInputBar juliaRealInput;
  private final PopupInputBar juliaImaginaryInput;
  private final PopupInputBar[] affineInputBars;
  private final Label transformInputText;

  /**
   * Constructor for the EditPopup class.
   */
  public EditPopup() {
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
    juliaButton.setOnAction(e -> chooseJulia());
    affineButton = new Button("Affine");
    affineButton.getStyleClass().add("secondary-button");
    affineButton.setOnAction(e -> chooseAffine());
    HBox transformButtonsWrapper = new HBox();
    transformButtonsWrapper.getChildren().addAll(juliaButton, affineButton);
    transformButtonsWrapper.setAlignment(javafx.geometry.Pos.CENTER);
    transformButtonsWrapper.setSpacing(10);

    Label coordinateText = new Label("Coordinates");
    coordinateText.getStyleClass().add("popup-text");
    HBox coordinateTextWrapper = new HBox();
    coordinateTextWrapper.getChildren().add(coordinateText);
    coordinateTextWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    minXinput = new PopupInputBar();
    minXinput.getStyleClass().add("input-bar");
    minXinput.setPromptText("Min x");
    minYinput = new PopupInputBar();
    minYinput.getStyleClass().add("input-bar");
    minYinput.setPromptText("Min y");
    maxXinput = new PopupInputBar();
    maxXinput.getStyleClass().add("input-bar");
    maxXinput.setPromptText("Max x");
    maxYinput = new PopupInputBar();
    maxYinput.getStyleClass().add("input-bar");
    maxYinput.setPromptText("Max y");
    HBox coordinateInputWrapper = new HBox();
    coordinateInputWrapper.getChildren().addAll(minXinput, minYinput, maxXinput, maxYinput);
    coordinateInputWrapper.setAlignment(javafx.geometry.Pos.CENTER);
    coordinateInputWrapper.setSpacing(10);
    coordinateInputWrapper.setMaxWidth(380);
    StackPane coordinateWrapper = new StackPane();
    coordinateWrapper.getChildren().add(coordinateInputWrapper);

    Label juliaInputText = new Label("Constant c");
    juliaInputText.getStyleClass().add("popup-text");
    HBox juliaInputTextWrapper = new HBox();
    juliaInputTextWrapper.getChildren().add(juliaInputText);
    juliaInputTextWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    juliaRealInput = new PopupInputBar();
    juliaRealInput.getStyleClass().add("input-bar");
    juliaRealInput.setPromptText("Real");
    juliaImaginaryInput = new PopupInputBar();
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
    prevButton.setOnAction(e -> editController.previousAffineTransform());
    HBox prevButtonWrapper = new HBox();
    prevButtonWrapper.getChildren().add(prevButton);
    prevButtonWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    NextButton nextButton = new NextButton();
    nextButton.setOnAction(e -> editController.nextAffineTransform());
    HBox nextButtonWrapper = new HBox();
    nextButtonWrapper.getChildren().add(nextButton);
    nextButtonWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    transformInputText = new Label("Transformation 1");
    transformInputText.getStyleClass().add("popup-text");
    HBox transformInputTextWrapper = new HBox();
    transformInputTextWrapper.getChildren().add(transformInputText);
    transformInputTextWrapper.setAlignment(javafx.geometry.Pos.CENTER);

    affineInputBars = new PopupInputBar[6];
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

    SecondaryButton addTransformButton = new SecondaryButton("Add Transform");
    addTransformButton.setOnAction(e -> editController.addAffineTransform());

    SecondaryButton removeTransformButton = new SecondaryButton("Remove Transform");
    removeTransformButton.setOnAction(e -> editController.removeAffineTransform());

    HBox transformAmountButtonsWrapper = new HBox();
    transformAmountButtonsWrapper.getChildren().addAll(addTransformButton, removeTransformButton);
    transformAmountButtonsWrapper.setAlignment(javafx.geometry.Pos.CENTER);
    transformAmountButtonsWrapper.setSpacing(10);

    affineWrapper = new VBox();
    affineWrapper.getChildren().addAll(
        affineInputTextWrapper,
        prevButtonWrapper,
        transformInputTextWrapper,
        affineInputWrapper,
        nextButtonWrapper,
        transformAmountButtonsWrapper);
    affineWrapper.setSpacing(5);
    affineWrapper.setMaxWidth(380);
    affineWrapper.setVisible(false);

    StackPane transformWrapper = new StackPane();
    transformWrapper.getChildren().addAll(juliaWrapper, affineWrapper);

    PrimaryButton saveButton = new PrimaryButton("Save");
    saveButton.setOnAction(e -> editController.saveEdit());
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
        coordinateTextWrapper,
        coordinateWrapper,
        transformButtonsWrapper,
        transformWrapper,
        saveButtonWrapper);
    editWrapper.setSpacing(10);

    this.getStyleClass().add("edit-popup");
    this.getChildren().add(editWrapper);
    this.setVisible(false);
  }

  /**
   * Helper method for choosing the affine description.
   */
  private void chooseAffine() {
    juliaButton.getStyleClass().remove("active-secondary-button");
    juliaWrapper.setVisible(false);
    juliaButton.setDisable(false);
    affineButton.getStyleClass().add("active-secondary-button");
    affineWrapper.setVisible(true);
    affineButton.setDisable(true);
    if (editController != null) {
      editController.setChosenDescription("Affine");
    }

  }

  /**
   * Helper method for choosing the Julia description.
   */
  private void chooseJulia() {
    affineButton.getStyleClass().remove("active-secondary-button");
    affineWrapper.setVisible(false);
    affineButton.setDisable(false);
    juliaButton.getStyleClass().add("active-secondary-button");
    juliaWrapper.setVisible(true);
    juliaButton.setDisable(true);
    if (editController != null) {
      editController.setChosenDescription("JuliaTransform");
    }
  }

  /**
   * Shows this edit popup.
   */
  public void show() {
    this.toFront();
    this.setVisible(true);
  }

  /**
   * Hides this edit popup.
   */
  public void hide() {
    this.setVisible(false);
  }

  /**
   * Sets the description in the popup by pressing the corresponding button,
   * by getting the description from the controller.
   *
   * @param description the description to be set
   */
  public void setDescription(String description) {
    if (description.equals("JuliaTransform")) {
      juliaButton.fire();
    } else {
      affineButton.fire();
    }
  }

  /**
   * Sets the coordinates in the input fields from the controller.
   *
   * @param minX the minimum x value
   * @param minY the minimum y value
   * @param maxX the maximum x value
   * @param maxY the maximum y value
   */
  public void setCoordinateInput(String minX, String minY, String maxX, String maxY) {
    minXinput.setText(minX);
    minYinput.setText(minY);
    maxXinput.setText(maxX);
    maxYinput.setText(maxY);
  }

  /**
   * Sets the constant c in the input fields from the controller.
   *
   * @param real the real part of the constant c
   * @param imaginary the imaginary part of the constant c
   */
  public void setJuliaInput(String real, String imaginary) {
    juliaRealInput.setText(real);
    juliaImaginaryInput.setText(imaginary);
  }

  /**
   * Sets the affine input fields from the controller. Only one affine transform is shown at a time.
   * The currentTransformIndex is used to show which affine transform is being shown.
   *
   * @param affineTransform the affine transform
   * @param currentTransformIndex the index of the current affine transform
   */
  public void setAffineInput(List<String> affineTransform, int currentTransformIndex) {
    for (int i = 0; i < 6; i++) {
      affineInputBars[i].setText(affineTransform.get(i));
      transformInputText.setText("Transformation " + (currentTransformIndex + 1));
    }
  }

  /**
   * Sets the controller for this popup.
   *
   * @param editController the controller to be set
   */
  public void setPopupController(EditController editController) {
    this.editController = editController;
  }

  /**
   * Gets the minimum x value from the input field.
   *
   * @return the minimum x value
   */
  public String getMinX() {
    return minXinput.getText();
  }

  /**
   * Gets the minimum y value from the input field.
   *
   * @return the minimum y value
   */
  public String getMinY() {
    return minYinput.getText();
  }

  /**
   * Gets the maximum x value from the input field.
   *
   * @return the maximum x value
   */
  public String getMaxX() {
    return maxXinput.getText();
  }

  /**
   * Gets the maximum y value from the input field.
   *
   * @return the maximum y value
   */
  public String getMaxY() {
    return maxYinput.getText();
  }

  /**
   * Gets the real part of the constant c from the input field.
   *
   * @return the real part of the constant c
   */
  public String getJuliaReal() {
    return juliaRealInput.getText();
  }

  /**
   * Gets the imaginary part of the constant c from the input field.
   *
   * @return the imaginary part of the constant c
   */
  public String getJuliaImaginary() {
    return juliaImaginaryInput.getText();
  }

  /**
   * Gets the affine input from the input fields.
   *
   * @return the affine input
   */
  public String[] getAffineInput() {
    String[] affineInput = new String[6];
    for (int i = 0; i < 6; i++) {
      affineInput[i] = affineInputBars[i].getText();
    }
    return affineInput;
  }
}
