package edu.ntnu.idatt2003.view.components;

import javafx.scene.control.Button;

public class PrimaryButton extends Button {
    public PrimaryButton(String text) {
        super(text);
        this.setPrefHeight(18);
        this.getStyleClass().add("primary-button");

    }
}
