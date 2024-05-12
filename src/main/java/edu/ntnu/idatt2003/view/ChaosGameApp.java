package edu.ntnu.idatt2003.view;

import edu.ntnu.idatt2003.controller.ChaosGameController;
import edu.ntnu.idatt2003.view.components.TopBar;
import edu.ntnu.idatt2003.view.components.ViewCanvas;
import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The main application class for the chaos game.
 */
public class ChaosGameApp extends Application {
  ChaosGameController controller;
  ViewCanvas viewCanvas;

  @Override
  public void start(Stage stage) throws Exception {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    VBox root = new VBox();
    viewCanvas = new ViewCanvas(800, 800);
    controller = new ChaosGameController(viewCanvas, 800, 800);
    StackPane topBar = new TopBar(controller);
    root.getChildren().addAll(
        topBar,
        viewCanvas.getCanvas());

    Scene scene = new Scene(root, 1000, 860);

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();
  }
}