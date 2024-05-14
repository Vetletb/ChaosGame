package edu.ntnu.idatt2003.view.components;

import edu.ntnu.idatt2003.controller.ChaosGameController;
import edu.ntnu.idatt2003.exceptions.ChaosGameDescriptionFactoryException;
import edu.ntnu.idatt2003.exceptions.ChaosGameException;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

/**
 * The top bar that contains buttons for choosing chaos game types, editing and running the chaos game.
 */
public class TopBar extends StackPane {
  private final ChaosGameController controller;
  private int iterations;

  /**
   * Constructor for the TopBar class.
   *
   * @param controller the controller for the chaos game.
   */
  public TopBar(ChaosGameController controller) {
    super();
    this.controller = controller;

    Button juliaButton = new SecondaryButton("Julia Set");
    juliaButton.setOnAction(e -> {
      this.controller.resetViewCanvas();
      try {
        this.controller.resetChaosGameWithDescription("Julia Set");
      } catch (ChaosGameDescriptionFactoryException | ChaosGameException ex) {
        new ErrorPopup(ex.getMessage(), this.getScene().getWindow());
      }
    });

    Button sierpinskiButton = new SecondaryButton("Sierpinski");
    sierpinskiButton.setOnAction(e -> {
      this.controller.resetViewCanvas();
      try {
        this.controller.resetChaosGameWithDescription("Sierpinski");
      } catch (ChaosGameDescriptionFactoryException | ChaosGameException ex) {
        new ErrorPopup(ex.getMessage(), this.getScene().getWindow());
      }
    });

    Button barnsleyButton = new SecondaryButton("Barnsley");
    barnsleyButton.setOnAction(e -> {
      this.controller.resetViewCanvas();
      try {
        this.controller.resetChaosGameWithDescription("Barnsley");
      } catch (ChaosGameDescriptionFactoryException | ChaosGameException ex) {
        new ErrorPopup(ex.getMessage(), this.getScene().getWindow());
      }
    });

    Button readFileButton = new SecondaryButton("Read File");
    readFileButton.setOnAction(e -> {
      this.controller.resetViewCanvas();
      FileChooser fileChooser = new FileChooser();
      File file = fileChooser.showOpenDialog(this.getScene().getWindow());
      try {
        controller.resetChaosGameWithFile(file);
        new SuccessPopup("File read successfully", this.getScene().getWindow());
      } catch (Exception ex) {
        if (ex.getMessage() != null) {
          new ErrorPopup(ex.getMessage(), this.getScene().getWindow());
        } else {
          new ErrorPopup("No file selected", this.getScene().getWindow());
        }
      }
    });

    Button writeFileButton = new PrimaryButton("Write File");
    writeFileButton.setOnAction(e -> {
      System.out.println("Write File");
    });

    Button editButton = new PrimaryButton("Edit");
    editButton.setOnAction(e -> {
      System.out.println("Edit");
    });

    TextField iterationsField = new InputBar();
    iterationsField.setPromptText("Iterations");
    iterationsField.setText("100000");

    Button runButton = new PrimaryButton("Run");
    runButton.setOnAction(e -> {
      this.controller.resetViewCanvas();
      this.controller.resetChaosGame();
      try {
        iterations = Integer.parseInt(iterationsField.getText());
      } catch (NumberFormatException ex) {
        new ErrorPopup("Iterations must be an integer", this.getScene().getWindow());
        return;
      }
      try {
        this.controller.animateIterations(iterations);
      } catch (ChaosGameException ex) {
        new ErrorPopup(ex.getMessage(), this.getScene().getWindow());
      }

    });

    HBox leftButtonBox = new HBox();
    HBox middleButtonBox = new HBox();
    HBox rightButtonBox = new HBox();

    leftButtonBox.getChildren().addAll(writeFileButton, editButton);
    middleButtonBox.getChildren().addAll(juliaButton, sierpinskiButton, barnsleyButton, readFileButton);
    rightButtonBox.getChildren().addAll(iterationsField, runButton);

    leftButtonBox.setSpacing(8);
    middleButtonBox.setSpacing(8);
    rightButtonBox.setSpacing(8);

    HBox buttonBox = new HBox();
    buttonBox.setSpacing(50);
    buttonBox.setMaxHeight(25);
    buttonBox.setMaxWidth(860);
    buttonBox.setAlignment(javafx.geometry.Pos.CENTER);

    buttonBox.getChildren().addAll(
        leftButtonBox,
        middleButtonBox,
        rightButtonBox
    );

    this.setMinHeight(60);
    this.setMinWidth(900);
    this.setAlignment(javafx.geometry.Pos.CENTER);
    this.getChildren().add(buttonBox);
  }
}
