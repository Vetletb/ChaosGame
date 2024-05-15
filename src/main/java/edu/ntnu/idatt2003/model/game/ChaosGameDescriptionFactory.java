package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.exceptions.ChaosGameDescriptionException;
import edu.ntnu.idatt2003.exceptions.ChaosGameDescriptionFactoryException;
import edu.ntnu.idatt2003.model.math.mathModel.Complex;
import edu.ntnu.idatt2003.model.math.mathModel.Matrix2x2;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.AffineTransform2D;
import edu.ntnu.idatt2003.model.math.transformation.JuliaTransform;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import java.util.List;

/**
 * A factory class for creating ChaosGameDescription objects. This class can be used to create
 * a set of predefined ChaosGameDescription objects, for the user to choose in a menu.
 */
public class ChaosGameDescriptionFactory {

  /**
   * Returns a ChaosGameDescription object based on the given type.
   *
   * @param type the type of chaos game to create.
   * @return a ChaosGameDescription object.
   * @throws ChaosGameDescriptionFactoryException if the type is invalid.
   */
  public static ChaosGameDescription get(String type) throws ChaosGameDescriptionFactoryException {
    return switch (type) {
      case "Sierpinski" -> createSierpinskiTriangle();
      case "Barnsley" -> createBarnsleyFern();
      case "Julia Set" -> createJuliaSet();
      default -> throw new ChaosGameDescriptionFactoryException("Invalid chaos game type");
    };
  }

  /**
   * Helper method for creating a chaos game description of the Julia set.
   *
   * @return a list of the available chaos game types.
   * @throws ChaosGameDescriptionFactoryException if an error occurs while creating the chaos game
   */
  private static ChaosGameDescription createJuliaSet() throws ChaosGameDescriptionFactoryException {
    List<Transform2D> transforms = List.of(
        new JuliaTransform(new Complex(0, 0.8), 1),
        new JuliaTransform(new Complex(0, 0.8), -1)
    );
    Vector2D minCoords = new Vector2D(-1.4, -1.4);
    Vector2D maxCoords = new Vector2D(1.4, 1.4);
    try {
      return new ChaosGameDescription(transforms, minCoords, maxCoords);
    } catch (ChaosGameDescriptionException e) {
      throw new ChaosGameDescriptionFactoryException(
          "An error occurred while creating a ChaosGameDescription object in factory.", e);
    }
  }

  /**
   * Helper method for creating a chaos game description of the Barnsley fern.
   *
   * @return a list of the available chaos game types.
   * @throws ChaosGameDescriptionFactoryException if an error occurs while creating the chaos game
   */
  private static ChaosGameDescription createBarnsleyFern()
      throws ChaosGameDescriptionFactoryException {
    List<Transform2D> transforms = List.of(
        new AffineTransform2D(new Matrix2x2(0, 0, 0, 0.16), new Vector2D(0, 0)),
        new AffineTransform2D(new Matrix2x2(0.85, 0.04, -0.04, 0.85), new Vector2D(0, 1.6)),
        new AffineTransform2D(new Matrix2x2(0.2, -0.26, 0.23, 0.22), new Vector2D(0, 1.6)),
        new AffineTransform2D(new Matrix2x2(-0.15, 0.28, 0.26, 0.24), new Vector2D(0, 0.44))
    );
    Vector2D minCoords = new Vector2D(-2.65, 0);
    Vector2D maxCoords = new Vector2D(2.65, 10);
    try {
      return new ChaosGameDescription(transforms, minCoords, maxCoords);
    } catch (ChaosGameDescriptionException e) {
      throw new ChaosGameDescriptionFactoryException(
          "An error occurred while creating a ChaosGameDescription object in factory.", e);
    }
  }

  /**
   * Helper method for creating a chaos game description of the Sierpinski triangle.
   *
   * @return a list of the available chaos game types.
   * @throws ChaosGameDescriptionFactoryException if an error occurs while creating the chaos game
   */
  private static ChaosGameDescription createSierpinskiTriangle()
      throws ChaosGameDescriptionFactoryException {
    List<Transform2D> transforms = List.of(
          new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0)),
          new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0)),
          new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5))
    );
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    try {
      return new ChaosGameDescription(transforms, minCoords, maxCoords);
    } catch (ChaosGameDescriptionException e) {
      throw new ChaosGameDescriptionFactoryException(
          "An error occurred while creating a ChaosGameDescription object in factory.", e);
    }
  }
}
