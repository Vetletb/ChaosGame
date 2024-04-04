package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.model.game.ChaosGame;

/**
 * A class that provides updates from the chaos game when change is observed.
 */
public class ChaosGameObserver implements Observer {
  ChaosGame chaosGame;

  /**
   * Constructor for the ChaosGameObserver class.
   *
   * @param chaosGame the chaos game to observe.
   */
  public ChaosGameObserver(ChaosGame chaosGame) {
    this.chaosGame = chaosGame;
  }

  /**
   * provides an update from the chaos game.
   */
  @Override
  public void update() {
    chaosGame.getCanvas().getCanvasArray();
  }
}
