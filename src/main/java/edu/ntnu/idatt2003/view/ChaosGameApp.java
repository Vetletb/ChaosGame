package edu.ntnu.idatt2003.view;

import edu.ntnu.idatt2003.controller.ChaosGameController;
import edu.ntnu.idatt2003.controller.EditController;
import edu.ntnu.idatt2003.controller.MainController;
import edu.ntnu.idatt2003.controller.MessageController;
import edu.ntnu.idatt2003.view.components.TopBar;
import edu.ntnu.idatt2003.view.components.ViewCanvas;
import edu.ntnu.idatt2003.view.components.popups.EditPopup;
import edu.ntnu.idatt2003.view.components.popups.ErrorPopup;
import edu.ntnu.idatt2003.view.components.popups.SuccessPopup;
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
  ViewCanvas viewCanvas;
  Scene scene;
  StackPane canvasWrapper;
  TopBar topBar;
  ErrorPopup errorPopup;
  SuccessPopup successPopup;
  EditPopup editPopup;
  ChaosGameController chaosGameController;
  EditController editController;
  MessageController messageController;

  /**
   * Starts the JavaFX application.
   *
   * @param stage the stage of the application.
   */
  @Override
  public void start(Stage stage) {
    viewCanvas = new ViewCanvas();
    errorPopup = new ErrorPopup();
    successPopup = new SuccessPopup();
    editPopup = new EditPopup();
    topBar = new TopBar();

    chaosGameController = new ChaosGameController(viewCanvas, topBar);
    editController = new EditController(editPopup);
    messageController = new MessageController(successPopup, errorPopup);
    new MainController(chaosGameController, editController, messageController);

    editPopup.setPopupController(editController);
    topBar.setChaosGameController(chaosGameController);

    canvasWrapper = new StackPane();
    canvasWrapper.getChildren().add(viewCanvas.getCanvas());

    VBox contentWrapper = new VBox();
    contentWrapper.getChildren().addAll(
        topBar,
        canvasWrapper);

    StackPane root = new StackPane();
    root.getChildren().addAll(
        contentWrapper,
        errorPopup,
        successPopup,
        editPopup
    );
    root.getStylesheets().add(
        Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

    scene = new Scene(root, 900, 760);

    scene.heightProperty().addListener((observable, oldValue, newValue) -> updateCanvasSize());
    scene.widthProperty().addListener((observable, oldValue, newValue) -> updateCanvasSize());

    stage.setTitle("Chaos Game");
    stage.setScene(scene);
    stage.setMinWidth(900);
    stage.setMinHeight(800);
    stage.show();
  }

  /**
   * Updates the size of the canvas to the window.
   */
  private void updateCanvasSize() {
    int minSize = (scene.getWidth() <= scene.getHeight() - topBar.getHeight())
        ? (int) scene.getWidth() : (int) scene.getHeight() - (int) topBar.getHeight();
    canvasWrapper.setMinSize(minSize, minSize);
    viewCanvas.getCanvas().setWidth(minSize - 20);
    viewCanvas.getCanvas().setHeight(minSize - 20);
    viewCanvas.reset();
    chaosGameController.rescaleCanvas();
  }
}