package edu.ntnu.idatt2003.view.components;

public class SecondaryButton extends PrimaryButton {
  public SecondaryButton(String text) {
    super(text);
    this.getStyleClass().add("secondary-button");
  }
}
