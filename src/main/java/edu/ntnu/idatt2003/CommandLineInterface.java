package edu.ntnu.idatt2003;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class representing the command line interface of the program.
 */
public class CommandLineInterface {
  private ChaosGameDescription description;
  private final ChaosGameFileHandler chaosGameFileHandler;
  private final Scanner scanner;
  private final int width = 80;
  private final int height = 80;

  /**
   * Constructor for the CommandLineInterface class.
   */
  public CommandLineInterface() {
    chaosGameFileHandler = new ChaosGameFileHandler();
    scanner = new Scanner(System.in);
  }

  /**
   * Starts the command line interface.
   */
  public void start() {
    boolean exit = false;
    while (!exit) {
      System.out.println("Welcome to the Chaos Game!");
      System.out.println("1. Choose file");
      System.out.println("2. Write to file");
      System.out.println("3. Run chaos game");
      System.out.println("4. Exit");
      int choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1 -> chooseFile();
        case 2 -> writeToFile();
        case 3 -> runChaosGame();
        case 4 -> exit = true;
        default -> System.out.println("Invalid choice. Please try again.");
      }
      System.out.println(" ");
    }
    scanner.close();
  }

  /**
   * Chooses a file to construct a ChaosGameDescription from.
   */
  private void chooseFile() {
    System.out.println("Choose a file:");
    int i = 1;
    for (String path : chaosGameFileHandler.listFiles()) {
      String name = path.replace("src/main/resources/", "").replace(".txt", "");
      System.out.println(i + ". " + name);
      i++;
    }
    int choice = scanner.nextInt();
    scanner.nextLine();
    boolean found = false;
    for (String path : chaosGameFileHandler.listFiles()) {
      choice--;
      if (choice == 0) {
        found = true;
        try {
          description = chaosGameFileHandler.readFromFile(path);
          System.out.println("File read successfully!");
        } catch (FileNotFoundException e) {
          System.out.println("File not found. Please try again.");
        }
        break;
      }
    }
    if (!found) {
      System.out.println("Invalid choice. Please try again.");
    }
  }

  /**
   * Writes the ChaosGameDescription to a file.
   */
  private void writeToFile() {
    try {
      if (description == null) {
        System.out.println("Please choose a file first.");
        return;
      }
      System.out.println("Enter the new name of the file:");
      String path = "src/main/resources/" + scanner.nextLine() + ".txt";
      chaosGameFileHandler.writeToFile(description, path);
      System.out.println("File written successfully!");
    } catch (IOException e) {
      System.out.println("An error occurred. Please try again.");
    }
  }

  /**
   * Runs the chaos game and prints the result.
   */
  private void runChaosGame() {
    ChaosGame chaosGame = new ChaosGame(description, width, height);
    System.out.println("Choose number of steps:");
    int steps = scanner.nextInt();
    chaosGame.runSteps(steps);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (chaosGame.getCanvas().getCanvasArray()[i][j] == 1) {
          sb.append("xx");
        } else {
          sb.append("  ");
        }
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }
}
