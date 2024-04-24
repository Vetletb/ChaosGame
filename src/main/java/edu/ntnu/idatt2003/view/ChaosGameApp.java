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
import javafx.scene.layout.HBox;
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
  Service<Void> service;

  @Override
  public void start(Stage stage) throws Exception {

    service = new Service<Void>() {
      @Override
      protected Task<Void> createTask() {
        return new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            for (int i = 0; i < 100000; i++) {
              Platform.runLater(() -> controller.runSteps(1));
              Thread.sleep(0,1);
            }
            return null;
          }
        };
      }
    };

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    VBox root = new VBox();
    viewCanvas = new ViewCanvas(800, 800);
    controller = new ChaosGameController(viewCanvas, 800, 800);
    HBox topBar = new TopBar(controller, service);
    root.getChildren().addAll(
        topBar,
        viewCanvas.getCanvas());


//    Button button = new Button("Run 100000 steps");
//    button.setOnAction(e -> {
//      if (service.getState() == Worker.State.SUCCEEDED) {
//        service.restart();
//      } else {
//        service.start();
//      }
//    });
//    root.getChildren().add(button);

//    Button readFileButton = new Button("Read file");
//    readFileButton.setOnAction(e -> {
//      FileChooser fileChooser = new FileChooser();
//      fileChooser.setTitle("Open File");
//      File file = fileChooser.showOpenDialog(new Stage());
//    });

    Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.show();
  }
}