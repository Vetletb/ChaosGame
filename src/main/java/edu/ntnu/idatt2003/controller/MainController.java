package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidTypeException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.exceptions.ObservingException;
import edu.ntnu.idatt2003.model.game.ChaosGame;
import edu.ntnu.idatt2003.model.game.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.model.game.Observer;
import edu.ntnu.idatt2003.util.ExceptionLogger;

/**
 * The main controller for the application. Responsible for initializing the chaos game and
 * connecting the other controllers.
 */
public class MainController implements Observer {
  //Constants for the chaos game.
  public static final int CHAOS_GAME_WIDTH = 1000;
  public static final int CHAOS_GAME_HEIGHT = 1000;
  public static final String START_DESCRIPTION = "Barnsley";

  //Constant for unexpected exceptions not caused by the user
  public static final String UNEXPECTED_EXCEPTION =
      "Something went wrong. Please try again or restart.";

  private final TopBarController topBarController;
  private final EditController editController;
  private final CanvasController canvasController;
  private final MessageController messageController;
  private final ExceptionLogger exceptionLogger;

  /**
   * Constructor for the MainController class.
   *
   * @param topBarController the chaos game controller
   * @param editController the edit controller
   * @param messageController the message controller
   */
  public MainController(TopBarController topBarController, EditController editController,
                        MessageController messageController, CanvasController canvasController) {
    this.topBarController = topBarController;
    this.editController = editController;
    this.canvasController = canvasController;
    this.messageController = messageController;
    exceptionLogger = new ExceptionLogger("ChaosGameController");
    try {
      ChaosGame chaosGame = new ChaosGame(ChaosGameDescriptionFactory.get(
          START_DESCRIPTION), CHAOS_GAME_WIDTH, CHAOS_GAME_HEIGHT);
      chaosGame.attach(this);
      this.topBarController.setChaosGame(chaosGame);
      this.editController.setChaosGame(chaosGame);
      this.canvasController.setChaosGame(chaosGame);
      this.editController.updateEditPopup();
    } catch (EmptyListException | InvalidVectorRangeException | InvalidSignException
             | IsNullException | InvalidTypeException | InvalidPositiveIntException e) {
      exceptionLogger.logSevere(e);
      messageController.showErrorPopup(UNEXPECTED_EXCEPTION);
    }
    this.topBarController.setLogger(exceptionLogger);
    this.topBarController.setMessageController(messageController);
    this.topBarController.setEditController(editController);
    this.topBarController.setCanvasController(canvasController);
    this.editController.setLogger(exceptionLogger);
    this.editController.setMessageController(messageController);
    this.canvasController.setLogger(exceptionLogger);
    this.canvasController.setMessageController(messageController);
  }

  /**
   * Update method from the observer interface. Updates different parts of the application based on
   * what is being observed int the model.
   *
   * @param updated string that tells what is being updated.
   */
  @Override
  public void update(String updated) {
    switch (updated) {
      case "clearGame" -> canvasController.resetViewCanvas();
      case "putPixel" -> canvasController.drawCurrentPixel();
      case "setDescription" -> editController.updateEditPopup();
      default -> {
        try {
          throw new ObservingException(updated + " is not being observed.");
        } catch (ObservingException e) {
          exceptionLogger.logSevere(e);
          messageController.showErrorPopup(UNEXPECTED_EXCEPTION);
        }
      }
    }
  }
}
