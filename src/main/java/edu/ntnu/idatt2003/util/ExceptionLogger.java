package edu.ntnu.idatt2003.util;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A class for logging exceptions.
 */
public class ExceptionLogger {
  private final Logger logger;

  /**
   * Constructor for the ExceptionLogger class.
   *
   * @param loggerName the name of the logger.
   */
  public ExceptionLogger(String loggerName) {
    logger = Logger.getLogger(loggerName);
    try {
      FileHandler fileHandler = new FileHandler("src/main/resources/logs/LogFile.log", false);
      SimpleFormatter formatter = new SimpleFormatter();
      fileHandler.setFormatter(formatter);
      logger.addHandler(fileHandler);
    } catch (SecurityException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Logs a warning, used for user input errors.
   *
   * @param e the exception to log.
   */
  public void logWarning(Exception e) {
    logger.log(Level.WARNING, e.getMessage(), e);
  }

  /**
   * Logs a severe exception, used for unexpected errors.
   *
   * @param e the exception to log.
   */
  public void logSevere(Exception e) {
    logger.log(Level.SEVERE, e.getMessage(), e);
  }
}
