package edu.ntnu.idatt2003;

import java.util.List;
import java.util.Random;

public class ChaosGame {
  private final ChaosCanvas canvas;
  private final ChaosGameDescription description;
  private Vector2D currentPoint;
  private final Random random;

  public ChaosGame(ChaosGameDescription description, int width, int height) {
    this.description = description;
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
    this.currentPoint = new Vector2D(0, 0);
    this.random = new Random();
  }

  public ChaosCanvas getCanvas() {
    return canvas;
  }

  public void runSteps(int steps) {
    List<Transform2D> transforms = description.getTransforms();
    for (int i = 0; i < steps; i++) {
      Transform2D currentTransformation = transforms.get(random.nextInt(transforms.size()));
      currentPoint = currentTransformation.transform(currentPoint);
      canvas.putPixel(currentPoint);
    }
  }
}
