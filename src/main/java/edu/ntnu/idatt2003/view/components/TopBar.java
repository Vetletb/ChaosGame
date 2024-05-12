package edu.ntnu.idatt2003.view.components;

import edu.ntnu.idatt2003.controller.ChaosGameController;
import javafx.concurrent.Service;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TopBar extends HBox {
  private ChaosGameController controller;

  public TopBar(ChaosGameController controller) {
    super();
    this.controller = controller;

    Button juliaButton = new Button("Julia Set");
    juliaButton.setOnAction(e -> {
      this.controller.resetChaosGameWithDescription("Julia Set");
    });

    Button sierpinskiButton = new Button("Sierpinski");
    sierpinskiButton.setOnAction(e -> {
      this.controller.resetChaosGameWithDescription("Sierpinski");
    });

    Button barnsleyButton = new Button("Barnsley");
    barnsleyButton.setOnAction(e -> {
      this.controller.resetChaosGameWithDescription("Barnsley");
    });

    Button readFileButton = new Button("Read File");
    readFileButton.setOnAction(e -> {
      System.out.println("Read File");
    });

    Button writeFileButton = new Button("Write File");
    writeFileButton.setOnAction(e -> {
      System.out.println("Write File");
    });

    Button editButton = new Button("Edit");
    editButton.setOnAction(e -> {
      System.out.println("Edit");
    });

    TextField iterationsField = new TextField();
    iterationsField.setPromptText("Iterations");

    Button runButton = new Button("Run");
    runButton.setOnAction(e -> {
      this.controller.resetViewCanvas();
      this.controller.resetChaosGame();
      int iterations = Integer.parseInt(iterationsField.getText());
      this.controller.animateIterations(iterations);
    });

    this.setSpacing(10);

    this.getChildren().addAll(
        juliaButton,
        sierpinskiButton,
        barnsleyButton,
        readFileButton,
        writeFileButton,
        editButton,
        iterationsField,
        runButton);
  }
}
