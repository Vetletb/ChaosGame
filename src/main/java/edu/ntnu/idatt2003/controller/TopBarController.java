package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.exceptions.CouldNotWriteException;
import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidTypeException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.exceptions.WrongFileFormatException;
import edu.ntnu.idatt2003.model.game.ChaosGame;
import edu.ntnu.idatt2003.model.game.ChaosGameDescription;
import edu.ntnu.idatt2003.model.game.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.model.io.ChaosGameFileHandler;
import edu.ntnu.idatt2003.util.ExceptionLogger;
import edu.ntnu.idatt2003.view.components.TopBar;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The controller class for the top bar.
 */
public class TopBarController {

  private ChaosGame chaosGame;
  private final TopBar topBar;
  private  ExceptionLogger exceptionLogger;
  private MessageController messageController;
  private EditController editController;
  private CanvasController canvasController;

  /**
   * Constructor for the TopBarController class.
   */
  public TopBarController(TopBar topBar) {
    this.topBar = topBar;
  }

  /**
   * Sets the chaos game to a new chaos game.
   *
   * @param chaosGame the chaos game to be set
   */
  public void setChaosGame(ChaosGame chaosGame) {
    this.chaosGame = chaosGame;
  }

  /**
   * Sets the exception logger to a new exception logger.
   *
   * @param exceptionLogger the exception logger to be set
   */
  public void setLogger(ExceptionLogger exceptionLogger) {
    this.exceptionLogger = exceptionLogger;
  }

  /**
   * Sets the message controller to a new message controller.
   *
   * @param messageController the message controller to be set
   */
  public void setMessageController(MessageController messageController) {
    this.messageController = messageController;
  }

  /**
   * Sets the edit controller to a new edit controller.
   *
   * @param editController the edit controller to be set
   */
  public void setEditController(EditController editController) {
    this.editController = editController;
  }

  /**
   * Sets the canvas controller to a new canvas controller.
   *
   * @param canvasController the canvas controller to be set
   */
  public void setCanvasController(CanvasController canvasController) {
    this.canvasController = canvasController;
  }

  /**
   * Gets the edit controller.
   *
   * @return the edit controller
   */
  public EditController getEditController() {
    return editController;
  }

  /**
   * Resets the chaos game with a new description.
   *
   * @param description the description to be set
   */
  public void resetChaosGameWithDescription(String description) {
    try {
      ChaosGameDescription newDescription = ChaosGameDescriptionFactory.get(description);
      chaosGame.resetGameWithDescription(newDescription);
      messageController.showSuccessPopup(description + " loaded successfully");
    } catch (IsNullException | InvalidVectorRangeException | EmptyListException
             | InvalidSignException | InvalidTypeException e) {
      exceptionLogger.logSevere(e);
      messageController.showErrorPopup(MainController.UNEXPECTED_EXCEPTION);
    }
  }

  /**
   * Resets the chaos game.
   */
  public void resetChaosGame() {
    chaosGame.resetGame();
  }

  /**
   * Reads a description from a file and resets the chaos game with the new description.
   *
   * @param file the file to read the description from
   */
  public void resetChaosGameWithFile(File file) {
    try {
      ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
      ChaosGameDescription newDescription = fileHandler.readFromFile(file);
      chaosGame.resetGameWithDescription(newDescription);
      messageController.showSuccessPopup("File loaded successfully");
    } catch (IsNullException | InvalidVectorRangeException
             | EmptyListException | InvalidSignException e) {
      exceptionLogger.logSevere(e);
      messageController.showErrorPopup(e.getMessage());
    } catch (FileNotFoundException e) {
      exceptionLogger.logWarning(e);
      messageController.showErrorPopup("File not found");
    } catch (WrongFileFormatException e) {
      exceptionLogger.logWarning(e);
      messageController.showErrorPopup(e.getMessage());
    }
  }

  /**
   * Writes the set description of the chaos game to a file.
   *
   * @param file the file to write to
   */
  public void writeChaosGameToFile(File file)  {
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
    try {
      fileHandler.writeToFile(chaosGame.getDescriptions(), file);
      messageController.showSuccessPopup("File written successfully");
    } catch (CouldNotWriteException e) {
      exceptionLogger.logSevere(e);
      messageController.showErrorPopup(MainController.UNEXPECTED_EXCEPTION);
    }
  }

  /**
   * Method for running the animation with a given number of iterations
   * from the top bar.
   */
  public void runAnimation() {
    try {
      int iterations = Integer.parseInt(topBar.getIterations());
      canvasController.animateIterations(iterations);
    } catch (NumberFormatException e) {
      exceptionLogger.logWarning(e);
      messageController.showErrorPopup("Input must be a number");
    }
  }
}

