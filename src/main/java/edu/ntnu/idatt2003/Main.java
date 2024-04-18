package edu.ntnu.idatt2003;

import edu.ntnu.idatt2003.view.ChaosGameApp;
import edu.ntnu.idatt2003.view.CommandLineInterface;
import javafx.application.Application;

/**
 * The main class of the program.
 */
public class Main {

  /**
   * The main method of the program.
   */
  public static void main(String[] args) {
    Application.launch(ChaosGameApp.class, args);
    //new CommandLineInterface().start();
  }
}