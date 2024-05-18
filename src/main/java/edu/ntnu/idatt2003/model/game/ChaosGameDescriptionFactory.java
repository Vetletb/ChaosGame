package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidTypeException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
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
   * @throws InvalidTypeException if the transformation type is invalid.
   * @throws EmptyListException if the list of transforms is empty.
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *     maximum coordinates.
   * @throws IsNullException if any of the parameters are null.
   * @throws InvalidSignException if the sign of the vector is invalid.
   */
  public static ChaosGameDescription get(String type)
      throws EmptyListException, InvalidVectorRangeException,
      InvalidSignException, IsNullException, InvalidTypeException {
    return switch (type) {
      case "Sierpinski" -> createSierpinskiTriangle();
      case "Barnsley" -> createBarnsleyFern();
      case "Julia Set" -> createJuliaSet();
      default -> throw new InvalidTypeException("Invalid description type");
    };
  }

  /**
   * Helper method for creating a chaos game description of the Julia set.
   *
   * @return a list of the available chaos game types.
   * @throws EmptyListException if the list of transforms is empty.
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *      maximum coordinates.
   * @throws IsNullException if any of the parameters are null.
   * @throws InvalidSignException if the sign of the vector is invalid.
   */
  private static ChaosGameDescription createJuliaSet()
      throws EmptyListException, InvalidVectorRangeException, InvalidSignException,
      IsNullException {
    List<Transform2D> transforms = List.of(
        new JuliaTransform(new Complex(-0.74543, 0.11301), 1),
        new JuliaTransform(new Complex(-0.74543, 0.11301), -1)
    );
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);

    return new ChaosGameDescription(transforms, minCoords, maxCoords);
  }

  /**
   * Helper method for creating a chaos game description of the Barnsley fern.
   *
   * @return a list of the available chaos game types.
   * @throws IsNullException if any of the parameters are null.
   * @throws EmptyListException if the list of transforms is empty.
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *      maximum coordinates.
   */
  private static ChaosGameDescription createBarnsleyFern()
      throws IsNullException, EmptyListException,
      InvalidVectorRangeException {
    List<Transform2D> transforms = List.of(
        new AffineTransform2D(new Matrix2x2(0, 0, 0, 0.16), new Vector2D(0, 0)),
        new AffineTransform2D(new Matrix2x2(0.85, 0.04, -0.04, 0.85), new Vector2D(0, 1.6)),
        new AffineTransform2D(new Matrix2x2(0.2, -0.26, 0.23, 0.22), new Vector2D(0, 1.6)),
        new AffineTransform2D(new Matrix2x2(-0.15, 0.28, 0.26, 0.24), new Vector2D(0, 0.44))
    );
    Vector2D minCoords = new Vector2D(-2.65, 0);
    Vector2D maxCoords = new Vector2D(2.65, 10);

    return new ChaosGameDescription(transforms, minCoords, maxCoords);
  }

  /**
   * Helper method for creating a chaos game description of the Sierpinski triangle.
   *
   * @return a list of the available chaos game types.
   * @throws IsNullException if any of the parameters are null.
   * @throws EmptyListException if the list of transforms is empty.
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *     maximum coordinates.
   */
  private static ChaosGameDescription createSierpinskiTriangle()
      throws IsNullException, EmptyListException,
      InvalidVectorRangeException {
    List<Transform2D> transforms = List.of(
          new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0)),
          new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0)),
          new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5))
    );
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);

    return new ChaosGameDescription(transforms, minCoords, maxCoords);

  }
}
