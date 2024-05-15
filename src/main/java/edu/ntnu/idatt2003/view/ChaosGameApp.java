package edu.ntnu.idatt2003.view;

import edu.ntnu.idatt2003.controller.ChaosGameController;
import edu.ntnu.idatt2003.view.components.TopBar;
import edu.ntnu.idatt2003.view.components.ViewCanvas;
import java.util.Objects;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main application class for the chaos game.
 */
public class ChaosGameApp extends Application {
  ChaosGameController controller;
  ViewCanvas viewCanvas;
  Scene scene;
  StackPane canvasContainer;
  TopBar topBar;

  @Override
  public void start(Stage stage) throws Exception {
    viewCanvas = new ViewCanvas();
    controller = new ChaosGameController(viewCanvas, 680, 680);

    canvasContainer = new StackPane();
    canvasContainer.getChildren().add(viewCanvas.getCanvas());

    topBar = new TopBar(controller);

    VBox root = new VBox();
    root.getChildren().addAll(
        topBar,
        canvasContainer);
    root.getStylesheets().add(
        Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

    scene = new Scene(root, 900, 760);

    scene.heightProperty().addListener((observable, oldValue, newValue) -> {
      updateCanvasSize();
    });
    scene.widthProperty().addListener((observable, oldValue, newValue) -> {
      updateCanvasSize();
    });

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.setMinWidth(900);
    stage.setMinHeight(800);
    stage.show();
  }

  private void updateCanvasSize() {
    int minSize = (scene.getWidth() <= scene.getHeight() - topBar.getHeight())
        ? (int) scene.getWidth() : (int) scene.getHeight() - (int) topBar.getHeight();
    canvasContainer.setMinSize(minSize, minSize);
    viewCanvas.getCanvas().setWidth(minSize - 20);
    viewCanvas.getCanvas().setHeight(minSize - 20);
    viewCanvas.reset();
    controller.rescaleCanvas();
  }
}