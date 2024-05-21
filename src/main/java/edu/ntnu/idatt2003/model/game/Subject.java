package edu.ntnu.idatt2003.model.game;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for handling observers.
 */
public abstract class Subject {

  private final List<Observer> observers;

  /**
   * Constructor for the Subject class.
   */
  protected Subject() {
    observers = new ArrayList<>();
  }

  /**
   * Attaches an observer to the subject.
   *
   * @param observer the observer to attach.
   */
  public void attach(Observer observer) {
    observers.add(observer);
  }

  /**
   * Detaches an observer from the subject.
   *
   * @param observer the observer to detach.
   */
  public void detach(Observer observer) {
    observers.remove(observer);
  }

  /**
   * Notifies all observers.
   *
   * @param updated what has been updated.
   */
  public void notifyObservers(String updated) {
    observers.forEach(observer -> observer.update(updated));
  }
}
