package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.exceptions.*;
import edu.ntnu.idatt2003.model.game.ChaosGame;
import edu.ntnu.idatt2003.model.game.ChaosGameDescription;
import edu.ntnu.idatt2003.model.game.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.model.game.Observer;
import edu.ntnu.idatt2003.model.io.ChaosGameFileHandler;
import edu.ntnu.idatt2003.view.components.ViewCanvas;
import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * The controller class for the chaos game.
 */
public class ChaosGameController implements Observer {
  private final ChaosGame chaosGame;
  private final ViewCanvas viewCanvas;
  private Exception exceptionInTimeline;


  /**
   * Constructor for the ChaosGameController class.
   *
   * @param viewCanvas the view canvas to draw on.
   * @param width      the width of the canvas.
   * @param height     the height of the canvas.
   */
  public ChaosGameController(ViewCanvas viewCanvas, int width, int height)
      throws ChaosGameDescriptionFactoryException, ChaosGameException, ChaosCanvasException {
    this.viewCanvas = viewCanvas;
    chaosGame = new ChaosGame(ChaosGameDescriptionFactory.get("Julia Set"), width, height);
    chaosGame.attach(this);
  }

  public void resetChaosGameWithDescription(String description)
      throws ChaosGameDescriptionFactoryException, ChaosGameException {
    ChaosGameDescription newDescription = ChaosGameDescriptionFactory.get(description);
    chaosGame.resetGameWithDescription(newDescription);
  }

  public void resetChaosGame() {
    chaosGame.resetGame();
  }

  public void resetViewCanvas() {
    viewCanvas.reset();
  }

  public void resetChaosGameWithFile(File file) throws ChaosGameFileHandlerException, ChaosGameDescriptionException, ChaosGameException {
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
    ChaosGameDescription newDescription = fileHandler.readFromFile(file);
    chaosGame.resetGameWithDescription(newDescription);
  }

  /**
   * Runs a given number of steps in the chaos game.
   */
  public void runSteps(int steps) throws ChaosGameException {
    chaosGame.runSteps(steps);
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
    int scaledX = (int) (x * viewCanvas.getCanvas().getWidth() / chaosGame.getCanvas().getWidth());
    int scaledY = (int) (y * viewCanvas.getCanvas().getHeight() / chaosGame.getCanvas().getHeight());
    return new int[]{scaledX, scaledY};
  }

  /**
   * Animates the chaos game by running a given number of iterations.
   */
  public void animateIterations(int iterations) throws ChaosGameException {
    if (iterations <= 0) {
      throw new ChaosGameException("Iterations must be a positive number");
    }
    int runSeconds = 3;
    int fps = 60;

    final int [] x = {0};
    double k = 0.05;

    final int [] totalSteps = {0};
    Timeline timeline = new Timeline();
    KeyFrame keyFrame = new KeyFrame(Duration.millis(1000.0 / fps), e -> {
      int steps = (int) (iterations * k / Math.exp(fps * runSeconds * k) * Math.exp(k * x[0]));
      try {
        if (steps != 0) {
          chaosGame.runSteps(steps);
        }
      } catch (ChaosGameException ex) {
        exceptionInTimeline = ex;
        timeline.stop();
      }
      x[0]++;
      totalSteps[0] += steps;
    });
    timeline.getKeyFrames().add(keyFrame);
    timeline.setCycleCount(fps * runSeconds);
    timeline.setOnFinished(e -> {
      try {
        if (iterations - totalSteps[0] != 0) {
          chaosGame.runSteps(iterations - totalSteps[0]);
        }
      } catch (ChaosGameException ex) {
        exceptionInTimeline = ex;
      }
    });
    timeline.play();

    if (exceptionInTimeline != null) {
      throw new ChaosGameException(exceptionInTimeline.getMessage(), exceptionInTimeline);
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
        drawPixel(pixel);
      }
    }
  }

  public void writeChaosGameToFile(File file) throws ChaosGameFileHandlerException {
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
    fileHandler.writeToFile(chaosGame.getDescriptions(), file);
  }

  /**
   * When the chaosGame runs a step, this method is called and
   * a pixel is drawn on the viewCanvas.
  */
  @Override
  public void update () {
    drawCurrentPixel();
  }
}

