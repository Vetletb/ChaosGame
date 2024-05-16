package edu.ntnu.idatt2003.view;

import edu.ntnu.idatt2003.exceptions.CouldNotWriteException;
import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.exceptions.WrongFileFormatException;
import edu.ntnu.idatt2003.model.game.ChaosGame;
import edu.ntnu.idatt2003.model.game.ChaosGameDescription;
import edu.ntnu.idatt2003.model.io.ChaosGameFileHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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
    List<String> files;
    try {
      files = chaosGameFileHandler.listFiles();
    } catch (IOException e) {
      System.out.println("An error occurred. Please try again.");
      return;
    }
    for (String path : files) {
      String name = path.replace("src/main/resources/", "").replace(".txt", "");
      System.out.println(i + ". " + name);
      i++;
    }
    int choice = scanner.nextInt();
    scanner.nextLine();
    boolean found = false;
    for (String path : files) {
      choice--;
      if (choice == 0) {
        found = true;
        try {
          File file = new File(path);
          description = chaosGameFileHandler.readFromFile(file);
          System.out.println("File read successfully!");
        } catch (WrongFileFormatException e) {
          System.out.println("Wrong file format. Please try again.");
        } catch (FileNotFoundException e) {
          System.out.println("File not found. Please try again.");
        } catch (IsNullException | EmptyListException
                 | InvalidVectorRangeException | InvalidSignException e) {
          System.out.println("An error occurred. Please try again.");
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
      File file = new File(path);
      chaosGameFileHandler.writeToFile(description, file);
      System.out.println("File written successfully!");
    } catch (CouldNotWriteException e) {
      System.out.println("An error occurred. Please try again.");
    }
  }

  /**
   * Runs the chaos game and prints the result.
   */
  private void runChaosGame() {
    ChaosGame chaosGame;
    try {
      chaosGame = new ChaosGame(description, width, height);
    } catch (IsNullException | InvalidPositiveIntException | InvalidVectorRangeException e) {
      System.out.println("An error occurred. Please try again.");
      return;
    }
    System.out.println("Choose number of steps:");
    int steps = scanner.nextInt();
    try {
      chaosGame.runSteps(steps);
    } catch (InvalidPositiveIntException e) {
      System.out.println("Steps must be a positive number.");
      return;
    }

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
