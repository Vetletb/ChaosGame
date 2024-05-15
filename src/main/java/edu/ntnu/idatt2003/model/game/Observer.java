package edu.ntnu.idatt2003.model.game;

/**
 * An interface for the observer pattern.
 */
public interface Observer {
  /**
   * Gets called on when the subject has been updated.
   *
   * @param updated what has been updated.
   */
  void update(String updated);
}
