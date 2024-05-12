package edu.ntnu.idatt2003.view.components;

import javafx.scene.control.Button;

public class PrimaryButton extends Button {
    public PrimaryButton(String text) {
        super(text);
        this.setPrefHeight(20);
        this.setStyle("-fx-font-size: 18px; -fx-background-color: #7fd1ff; -fx-text-fill: #000000;-fx-background-radius: 10px");
    }
}
