package edu.ntnu.idatt2003.controller;

import static edu.ntnu.idatt2003.controller.MainController.UNEXPECTED_EXCEPTION;

import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.model.game.ChaosGame;
import edu.ntnu.idatt2003.model.game.ChaosGameDescription;
import edu.ntnu.idatt2003.model.math.mathModel.Complex;
import edu.ntnu.idatt2003.model.math.mathModel.Matrix2x2;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.AffineTransform2D;
import edu.ntnu.idatt2003.model.math.transformation.JuliaTransform;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import edu.ntnu.idatt2003.util.ExceptionLogger;
import edu.ntnu.idatt2003.view.components.popups.EditPopup;
import java.util.ArrayList;
import java.util.List;


/**
 * The controller class for the edit popup.
 */
public class EditController {
  private ChaosGame chaosGame;
  private final EditPopup editPopup;
  private ExceptionLogger exceptionLogger;
  private MessageController messageController;
  private String chosenDescription;
  private final List<List<String>> affineTransforms;
  private int currentTransformIndex = 0;

  /**
   * Constructor for the EditController class.
   *
   * @param editPopup the edit popup to be controlled.
   */
  public EditController(EditPopup editPopup) {
    this.editPopup = editPopup;
    this.affineTransforms = new ArrayList<>();
  }

  /**
   * Sets the chaos game.
   *
   * @param chaosGame the chaos game to be set
   */
  public void setChaosGame(ChaosGame chaosGame) {
    this.chaosGame = chaosGame;
  }

  /**
   * Sets the exception logger.
   *
   * @param exceptionLogger the exception logger to be set
   */
  public void setLogger(ExceptionLogger exceptionLogger) {
    this.exceptionLogger = exceptionLogger;
  }

  /**
   * Sets the message controller.
   *
   * @param messageController the message controller to be set
   */
  public void setMessageController(MessageController messageController) {
    this.messageController = messageController;
  }

  /**
   * Shows the edit popup.
   */
  public void showEditPopup() {
    editPopup.show();
  }

  /**
   * Updates the edit popup.
   */
  public void updateEditPopup() {
    chosenDescription =
        chaosGame.getDescriptions().getTransforms().getFirst().getClass().getSimpleName();
    editPopup.setDescription(chosenDescription);
    editPopup.setCoordinateInput(
        String.valueOf(chaosGame.getDescriptions().getMinCoords().getX0()),
        String.valueOf(chaosGame.getDescriptions().getMinCoords().getX1()),
        String.valueOf(chaosGame.getDescriptions().getMaxCoords().getX0()),
        String.valueOf(chaosGame.getDescriptions().getMaxCoords().getX1()));
    if (chosenDescription.equals("JuliaTransform")) {
      updateJuliaTransforms();
      resetAffineTransform();
    } else {
      updateAffineTransformsFromDescription();
      resetJuliaTransform();
    }
  }

  /**
   * Helper method to update the julia transforms.
   */
  private void updateJuliaTransforms() {
    JuliaTransform juliaTransform =
        (JuliaTransform) chaosGame.getDescriptions().getTransforms().getFirst();
    editPopup.setJuliaInput(
        String.valueOf(juliaTransform.getPoint().getX0()),
        String.valueOf(juliaTransform.getPoint().getX1()));
  }

  /**
   * Helper method to update the affine transforms.
   */
  private void updateAffineTransformsFromDescription() {
    affineTransforms.clear();
    for (int i = 0; i < chaosGame.getDescriptions().getTransforms().size(); i++) {
      AffineTransform2D affineTransform2D =
          (AffineTransform2D) chaosGame.getDescriptions().getTransforms().get(i);
      List<String> affineTransform = List.of(
          String.valueOf(affineTransform2D.getMatrix().getA00()),
          String.valueOf(affineTransform2D.getMatrix().getA01()),
          String.valueOf(affineTransform2D.getMatrix().getA10()),
          String.valueOf(affineTransform2D.getMatrix().getA11()),
          String.valueOf(affineTransform2D.getVector().getX0()),
          String.valueOf(affineTransform2D.getVector().getX1()));
      affineTransforms.add(affineTransform);
      currentTransformIndex = 0;
      updateTransformInputText();
    }
  }

  /**
   * Updates the affine transforms from the input.
   */
  private void updateAffineTransformsFromInput() {
    try {
      affineTransforms.set(currentTransformIndex, List.of(
          editPopup.getAffineInput()[0],
          editPopup.getAffineInput()[1],
          editPopup.getAffineInput()[2],
          editPopup.getAffineInput()[3],
          editPopup.getAffineInput()[4],
          editPopup.getAffineInput()[5]));
    } catch (NullPointerException e) {
      exceptionLogger.logWarning(e);
      messageController.showErrorPopup("Transforms cannot be empty");
    }

  }

  /**
   * Helper method to update the affine transforms input text to the current transform.
   */
  private void updateTransformInputText() {
    editPopup.setAffineInput(affineTransforms.get(currentTransformIndex), currentTransformIndex);
  }

  /**
   * Updates the affine transform input text to the previous transform.
   */
  public void previousAffineTransform() {
    updateAffineTransformsFromInput();
    currentTransformIndex =
        (currentTransformIndex - 1 + affineTransforms.size()) % affineTransforms.size();
    updateTransformInputText();
  }

  /**
   * Updates the affine transform input text to the next transform.
   */
  public void nextAffineTransform() {
    updateAffineTransformsFromInput();
    currentTransformIndex = (currentTransformIndex + 1) % affineTransforms.size();
    updateTransformInputText();
  }

  /**
   * Saves the edited description and resets the chaos game with the new description.
   */
  public void saveEdit() {
    try {
      Vector2D minCoords = new Vector2D(
          Double.parseDouble(editPopup.getMinX()), Double.parseDouble(editPopup.getMinY()));
      Vector2D maxCoords = new Vector2D(
          Double.parseDouble(editPopup.getMaxX()), Double.parseDouble(editPopup.getMaxY()));
      List<Transform2D> transforms;
      if (chosenDescription.equals("JuliaTransform")) {
        transforms = parseJuliaInput();
      } else {
        updateAffineTransformsFromInput();
        transforms = parseAffineInput();
      }
      ChaosGameDescription chaosGameDescription =
          new ChaosGameDescription(transforms, minCoords, maxCoords);
      chaosGame.resetGameWithDescription(chaosGameDescription);
      editPopup.hide();
      messageController.showSuccessPopup("Chaos game updated");
    } catch (IsNullException | EmptyListException e) {
      exceptionLogger.logWarning(e);
      messageController.showErrorPopup("Transforms cannot be empty");
    } catch (InvalidVectorRangeException e) {
      exceptionLogger.logWarning(e);
      messageController.showErrorPopup("Invalid vector range");
    } catch (NumberFormatException  e) {
      exceptionLogger.logWarning(e);
      messageController.showErrorPopup("Input must be a number");
    } catch (InvalidSignException e) {
      exceptionLogger.logSevere(e);
      messageController.showErrorPopup(UNEXPECTED_EXCEPTION);
    }
  }

  /**
   * Helper method to parse the julia input to a list of transforms
   * from the input of the edit popup.
   *
   * @return the list of transforms
   * @throws InvalidSignException if the input is invalid
   */
  private List<Transform2D> parseJuliaInput() throws InvalidSignException {
    List<Transform2D> transforms;
    transforms = List.of(
          new JuliaTransform(new Complex(
              Double.parseDouble(editPopup.getJuliaReal()),
              Double.parseDouble(editPopup.getJuliaImaginary())), 1),
          new JuliaTransform(new Complex(
              Double.parseDouble(editPopup.getJuliaReal()),
              Double.parseDouble(editPopup.getJuliaImaginary())), -1)
      );
    return transforms;
  }

  /**
   * Helper method to parse the affine input to a list of transforms
   * from the input of the edit popup.
   *
   * @return the list of transforms
   */
  private List<Transform2D> parseAffineInput() {
    List<Transform2D> transforms = new ArrayList<>();
    for (List<String> affineTransform : affineTransforms) {
      AffineTransform2D affineTransform2D = new AffineTransform2D(
          new Matrix2x2(
              Double.parseDouble(affineTransform.get(0)),
              Double.parseDouble(affineTransform.get(1)),
              Double.parseDouble(affineTransform.get(2)),
              Double.parseDouble(affineTransform.get(3))),
          new Vector2D(
              Double.parseDouble(affineTransform.get(4)),
              Double.parseDouble(affineTransform.get(5))));
      transforms.add(affineTransform2D);
    }
    return transforms;
  }

  /**
   * Sets the chosen description.
   *
   * @param description the description to be set
   */
  public void setChosenDescription(String description) {
    this.chosenDescription = description;
  }

  /**
   * Adds a new affine transform to the list of affine transforms stored in the controller.
   */
  public void addAffineTransform() {
    affineTransforms.add(List.of("0", "0", "0", "0", "0", "0"));
    currentTransformIndex = (currentTransformIndex + 1) % affineTransforms.size();
    updateTransformInputText();
  }

  /**
   * Removes the current affine transform from the list of affine transforms
   * stored in the controller.
   */
  public void removeAffineTransform() {
    if (affineTransforms.size() > 1) {
      affineTransforms.remove(currentTransformIndex);
      currentTransformIndex =
          (currentTransformIndex - 1 + affineTransforms.size()) % affineTransforms.size();
      updateTransformInputText();
    }
  }

  /**
   * Resets the julia transform input to the default values.
   */
  public void resetJuliaTransform() {
    editPopup.setJuliaInput("0", "0");
  }

  /**
   * Resets the affine transform input to the default values.
   */
  public void resetAffineTransform() {
    affineTransforms.clear();
    affineTransforms.add(List.of("0", "0", "0", "0", "0", "0"));
    currentTransformIndex = 0;
    updateTransformInputText();
  }
}

