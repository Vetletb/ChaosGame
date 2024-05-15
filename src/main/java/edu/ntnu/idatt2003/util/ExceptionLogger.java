package edu.ntnu.idatt2003.util;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ExceptionLogger {
  private final Logger logger;

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

  public void logWarning(Exception e) {
    logger.log(Level.WARNING, e.getMessage(), e);
  }

  public void logSevere(Exception e) {
    logger.log(Level.SEVERE, e.getMessage(), e);
  }
}
