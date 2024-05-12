package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.model.game.ChaosGame;
import edu.ntnu.idatt2003.model.game.ChaosGameDescription;
import edu.ntnu.idatt2003.model.game.ChaosGameDescriptionFactory;
import edu.ntnu.idatt2003.model.game.Observer;
import edu.ntnu.idatt2003.view.components.ViewCanvas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * The controller class for the chaos game.
 */
public class ChaosGameController implements Observer {
  ChaosGame chaosGame;
  ViewCanvas viewCanvas;
  Timeline timeline;

  /**
   * Constructor for the ChaosGameController class.
   *
   * @param viewCanvas the view canvas to draw on.
   * @param width the width of the canvas.
   * @param height the height of the canvas.
   */
  public ChaosGameController(ViewCanvas viewCanvas, int width, int height) {
    this.viewCanvas = viewCanvas;
    chaosGame = new ChaosGame(ChaosGameDescriptionFactory.get("Julia Set"), width, height);
    chaosGame.attach(this);
  }

  public void resetChaosGameWithDescription(String description) {
    ChaosGameDescription newDescription = ChaosGameDescriptionFactory.get(description);
    chaosGame.resetGameWithDescription(newDescription);
  }

  public void resetChaosGame() {
    chaosGame.resetGame();
  }

  public void resetViewCanvas() {
    viewCanvas.reset();
  }

  /**
   * Runs a given number of steps in the chaos game.
   */
  public void runSteps(int steps) {
    chaosGame.runSteps(steps);
  }

  /**
   * Draws a pixel on the viewCanvas. The color of the pixel is determined by how many times the
   * pixel has been visited.
   */
  private void drawPixel() {
    int[] newPixel = chaosGame.getCanvas().getNewPixel();
    int x = newPixel[1];
    int y = newPixel[0];
    int value = newPixel[2];
    int blue = 100;
    int green = 0;
    int red = 0;
    for (int i = 0; (i < value && i < 10) ; i++) {
      blue += 15;
      green += 15;
      red += 3;
    }
    int[] rgbColor = {red, green, blue};
    viewCanvas.drawPoint(x, y, rgbColor);
  }

  public void animateIterations(int iterations) {
    int runSeconds = 1;
    int fps = 60;
    timeline = new Timeline();
    KeyFrame keyFrame = new KeyFrame(Duration.millis(1000.0 / fps), e -> {
      chaosGame.runSteps(iterations / (fps * runSeconds));
    });
    timeline.getKeyFrames().add(keyFrame);
    timeline.setCycleCount(fps * runSeconds);
    timeline.play();
  }

  /**
   * When the chaosGame runs a step, this method is called and
   * a pixel is drawn on the viewCanvas.
   */
  @Override
  public void update() {
    drawPixel();
  }
}
