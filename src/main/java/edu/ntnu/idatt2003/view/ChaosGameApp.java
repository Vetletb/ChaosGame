package edu.ntnu.idatt2003.view;

import edu.ntnu.idatt2003.controller.ChaosGameController;
import edu.ntnu.idatt2003.view.components.ViewCanvas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The main application class for the chaos game.
 */
public class ChaosGameApp extends Application {
  ChaosGameController controller;
  ViewCanvas viewCanvas;
  Service<Void> service;

  @Override
  public void start(Stage stage) throws Exception {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    HBox root = new HBox();
    viewCanvas = new ViewCanvas(800, 800);
    controller = new ChaosGameController(viewCanvas, 800, 800);
    root.getChildren().add(viewCanvas.getCanvas());

    service = new Service<Void>() {
      @Override
      protected Task<Void> createTask() {
        return new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            for (int i = 0; i < 100000; i++) {
              Platform.runLater(() -> controller.runSteps(1));
              Thread.sleep(0, 1);
            }
            return null;
          }
        };
      }
    };

    Button button = new Button("Run 100000 steps");
    button.setOnAction(e -> {
      if (service.getState() == Worker.State.SUCCEEDED) {
        service.restart();
      } else {
        service.start();
      }
    });
    root.getChildren().add(button);

    Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();
  }
}