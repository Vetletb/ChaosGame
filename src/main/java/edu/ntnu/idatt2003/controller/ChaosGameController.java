package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.exceptions.CouldNotWriteException;
import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidTypeException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.exceptions.ObservingException;
import edu.ntnu.idatt2003.exceptions.WrongFileFormatException;
import edu.ntnu.idatt2003.model.game.ChaosGame;
import edu.ntnu.idatt2003.model.game.ChaosGameDescription;
import edu.ntnu.idatt2003.model.game.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.model.game.Observer;
import edu.ntnu.idatt2003.model.io.ChaosGameFileHandler;
import edu.ntnu.idatt2003.util.ExceptionLogger;
import edu.ntnu.idatt2003.view.PopupHandler;
import edu.ntnu.idatt2003.view.components.ViewCanvas;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * The controller class for the chaos game.
 */
public class ChaosGameController implements Observer {
  //Constants for the chaos game.
  public static final int CHAOS_GAME_WIDTH = 680;
  public static final int CHAOS_GAME_HEIGHT = 680;
  public static final String START_DESCRIPTION = "Julia Set";

  //Constants for the animation
  public static int RUN_SECONDS = 3;
  public static int FPS = 60;
  public static double K = 0.05;

  //Constant for unexpected exceptions not caused by the user
  public static final String UNEXPECTED_EXCEPTION =
      "Something went wrong. Please try again or restart.";

  private ChaosGame chaosGame = null;
  private Timeline timeline;
  private final ViewCanvas viewCanvas;
  private final PopupHandler popupHandler;
  private final ExceptionLogger exceptionLogger;

  /**
   * Constructor for the ChaosGameController class.
   *
   * @param viewCanvas the view canvas to draw on.
   */
  public ChaosGameController(ViewCanvas viewCanvas, PopupHandler popupHandler) {
    exceptionLogger = new ExceptionLogger("ChaosGameController");
    this.viewCanvas = viewCanvas;
    this.popupHandler = popupHandler;
    try {
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.get(
          START_DESCRIPTION), CHAOS_GAME_WIDTH, CHAOS_GAME_HEIGHT);
      chaosGame.attach(this);
    } catch (EmptyListException | InvalidVectorRangeException | InvalidSignException
             | IsNullException | InvalidTypeException | InvalidPositiveIntException e) {
      exceptionLogger.logSevere(e);
      popupHandler.showErrorPopup(UNEXPECTED_EXCEPTION);
    }
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
      stopTimeline();
      popupHandler.showSuccessPopup(description + " loaded successfully");
    } catch (IsNullException | InvalidVectorRangeException | EmptyListException
             | InvalidSignException | InvalidTypeException e) {
      exceptionLogger.logSevere(e);
      popupHandler.showErrorPopup(UNEXPECTED_EXCEPTION);
    }
  }

  /**
   * Resets the chaos game.
   */
  public void resetChaosGame() {
    stopTimeline();
    chaosGame.resetGame();
  }

  /**
   * Resets the gui canvas.
   */
  public void resetViewCanvas() {
    viewCanvas.reset();
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
      popupHandler.showSuccessPopup("File loaded successfully");
    } catch (IsNullException | InvalidVectorRangeException
             | EmptyListException | InvalidSignException e) {
      exceptionLogger.logSevere(e);
      popupHandler.showErrorPopup(e.getMessage());
    } catch (FileNotFoundException e) {
      exceptionLogger.logWarning(e);
      popupHandler.showErrorPopup("File not found");
    } catch (WrongFileFormatException e) {
      exceptionLogger.logWarning(e);
      popupHandler.showErrorPopup(e.getMessage());
    }
  }

  /**
   * Runs a given number of steps in the chaos game.
   */
  public void runSteps(int steps) {
    try {
      chaosGame.runSteps(steps);
    } catch (InvalidPositiveIntException e) {
      exceptionLogger.logWarning(e);
      popupHandler.showErrorPopup(e.getMessage());
    }
  }

  /**
   * Draws a pixel on the viewCanvas. The color of the pixel is determined by how many times the
   * pixel has been visited.
   */
  private void drawPixel(int[] pixel) {
    int x = pixel[1];
    int y = pixel[0];
    int value = pixel[2];
    int blue = 100;
    int green = 0;
    int red = 0;
    for (int i = 0; (i < value && i < 10); i++) {
      blue += 15;
      green += 15;
      red += 3;
    }
    int[] rgbColor = {red, green, blue};
    int[] scaledCoordinates = scaleCoordinates(x, y);
    viewCanvas.drawPoint(scaledCoordinates[0], scaledCoordinates[1], rgbColor);
  }

  /**
   * Draws the current pixel on the viewCanvas.
   */
  private void drawCurrentPixel() {
    drawPixel(chaosGame.getCanvas().getNewPixel());
  }

  /**
   * Scales the coordinates of the chaos game to the viewCanvas.
   *
   * @param x the x-coordinate to scale.
   * @param y the y-coordinate to scale.
   * @return the scaled coordinates.
   */
  private int[] scaleCoordinates(int x, int y) {
    int scaledX = (int) (
        x * viewCanvas.getCanvas().getWidth() / chaosGame.getCanvas().getWidth());
    int scaledY = (int) (
        y * viewCanvas.getCanvas().getHeight() / chaosGame.getCanvas().getHeight());
    return new int[]{scaledX, scaledY};
  }

  /**
   * Animates the chaos game by running a given number of iterations.
   *
   * @param iterations the number of iterations to animate
   */
  public void animateIterations(int iterations)  {
    if (iterations <= 0) {
      try {
        throw new InvalidPositiveIntException("Iterations must be a positive number");
      } catch (InvalidPositiveIntException e) {
        exceptionLogger.logWarning(e);
        popupHandler.showErrorPopup(e.getMessage());
      }
      return;
    }

    final int [] x = {0};

    final int [] totalSteps = {0};
    timeline = new Timeline();
    KeyFrame keyFrame = new KeyFrame(Duration.millis(1000.0 / FPS), e -> {
      int steps = (int) (iterations * K / Math.exp(FPS * RUN_SECONDS * K) * Math.exp(K * x[0]));
      try {
        if (steps != 0) {
          chaosGame.runSteps(steps);
        }
      } catch (InvalidPositiveIntException ex) {
        exceptionLogger.logSevere(ex);
        popupHandler.showErrorPopup(UNEXPECTED_EXCEPTION);
        timeline.stop();
      }
      x[0]++;
      totalSteps[0] += steps;
    });
    timeline.getKeyFrames().add(keyFrame);
    timeline.setCycleCount(FPS * RUN_SECONDS);
    timeline.setOnFinished(e -> {
      try {
        if (iterations - totalSteps[0] != 0) {
          chaosGame.runSteps(iterations - totalSteps[0]);
        }
      } catch (InvalidPositiveIntException ex) {
        exceptionLogger.logSevere(ex);
        popupHandler.showErrorPopup(UNEXPECTED_EXCEPTION);
      }
    });
    timeline.play();
  }

  /**
   * Stops the animation of the chaos game if it's currently running.
   */
  private void stopTimeline() {
    if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
      timeline.stop();
    }
  }

  /**
   * Rescales the canvas.
   */
  public void rescaleCanvas() {
    int[][] array = chaosGame.getCanvas().getCanvasArray();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        int[] pixel = {i, j, array[i][j]};
        if (pixel[2] != 0) {
          drawPixel(pixel);
        }
      }
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
      popupHandler.showSuccessPopup("File written successfully");
    } catch (CouldNotWriteException e) {
      exceptionLogger.logSevere(e);
      popupHandler.showErrorPopup(UNEXPECTED_EXCEPTION);
    }
  }

  public PopupHandler getPopupHandler() {
    return popupHandler;
  }

  /**
   * logs a warning exception.
   */
  public void logWarning(Exception e) {
    exceptionLogger.logWarning(e);
  }

  /**
   * When the chaosGame is updated, this method is called.
  */
  @Override
  public void update(String updated) {
    switch (updated) {
      case "clearGame" -> viewCanvas.reset();
      case "putPixel" -> drawCurrentPixel();
      case "setDescription" -> System.out.println("description");
      default -> {
        try {
          throw new ObservingException(updated + " is not being observed.");
        } catch (ObservingException e) {
          exceptionLogger.logSevere(e);
          popupHandler.showErrorPopup(UNEXPECTED_EXCEPTION);
        }
      }
    }
  }
}

