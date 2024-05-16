package edu.ntnu.idatt2003.model.io;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.exceptions.CouldNotWriteException;
import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.exceptions.WrongFileFormatException;
import edu.ntnu.idatt2003.model.game.ChaosGameDescription;
import edu.ntnu.idatt2003.model.math.mathModel.Complex;
import edu.ntnu.idatt2003.model.math.mathModel.Matrix2x2;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.AffineTransform2D;
import edu.ntnu.idatt2003.model.math.transformation.JuliaTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test for ChaosGameFileHandler. Has both positive and negative tests.
 */
class ChaosGameFileHandlerTest {

  ChaosGameFileHandler chaosGameFileHandler;

  @BeforeEach
    void setUp() {
      chaosGameFileHandler = new ChaosGameFileHandler();
    }
  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    ChaosGameDescription juliaPositive;
    ChaosGameDescription affine2DPositive;

    @BeforeEach
    void setUp() throws IsNullException, EmptyListException,
        InvalidVectorRangeException, InvalidSignException {
      Vector2D vector1 = new Vector2D(0, 0);
      Vector2D vector2 = new Vector2D(0, 1.6);
      Vector2D vector3 = new Vector2D(0, 1.6);
      Vector2D vector4 = new Vector2D(0, .44);
      Matrix2x2 matrix1 = new Matrix2x2(0, 0, 0, .16);
      Matrix2x2 matrix2 = new Matrix2x2(.85, .04, -.04, .85);
      Matrix2x2 matrix3 = new Matrix2x2(.2, -.26, .23, .22);
      Matrix2x2 matrix4 = new Matrix2x2(-.15, .28, .26, .24);
      AffineTransform2D affine1 = new AffineTransform2D(matrix1, vector1);
      AffineTransform2D affine2 = new AffineTransform2D(matrix2, vector2);
      AffineTransform2D affine3 = new AffineTransform2D(matrix3, vector3);
      AffineTransform2D affine4 = new AffineTransform2D(matrix4, vector4);
      affine2DPositive = new ChaosGameDescription(
          List.of(affine1, affine2, affine3, affine4), new Vector2D(-2.65, 0),
          new Vector2D(2.65, 10));

      JuliaTransform julia1 = new JuliaTransform(new Complex(-.74543, .11301), -1);
      JuliaTransform julia2 = new JuliaTransform(new Complex(-.74543, .11301), 1);
      juliaPositive = new ChaosGameDescription(
          List.of(julia1, julia2), new Vector2D(-1.6, -1), new Vector2D(1.6, 1));
    }

    @Test
    @DisplayName("Test if readFromFile reads affine transformations correctly")
    void testIfReadChaosGameFileReadsAffineTransformationsCorrectly()
        throws InvalidSignException,
        WrongFileFormatException, IsNullException, FileNotFoundException, EmptyListException,
        InvalidVectorRangeException {
      File file = new File("src/test/resources/Affine2DReadPositive.txt");
      ChaosGameDescription chaosGameDescription = chaosGameFileHandler.readFromFile(file);

      assertEquals(chaosGameDescription, affine2DPositive);
    }

    @Test
    @DisplayName("Test if readFromFile reads julia transformations correctly")
    void testIfReadChaosGameFileReadsJuliaTransformationsCorrectly()
        throws InvalidSignException, WrongFileFormatException, IsNullException,
        FileNotFoundException, EmptyListException, InvalidVectorRangeException {
      File file = new File("src/test/resources/JuliaReadPositive.txt");
      ChaosGameDescription chaosGameDescription = chaosGameFileHandler.readFromFile(file);

      assertEquals(chaosGameDescription, juliaPositive);
    }

    @Test
    @DisplayName("Test if writeToFile writes affine transformations correctly")
    void testIfWriteChaosGameFileWritesAffineTransformationsCorrectly()
        throws CouldNotWriteException, InvalidSignException, WrongFileFormatException,
        IsNullException, FileNotFoundException, EmptyListException, InvalidVectorRangeException {
      File file = new File("src/test/resources/Affine2DWritePositive.txt");
      chaosGameFileHandler.writeToFile(affine2DPositive, file);
      ChaosGameDescription chaosGameDescription = chaosGameFileHandler.readFromFile(file);
      file.delete();

      assertEquals(chaosGameDescription, affine2DPositive);
    }

    @Test
    @DisplayName("Test if writeToFile writes julia transformations correctly")
    void testIfWriteChaosGameFileWritesJuliaTransformationsCorrectly()
        throws CouldNotWriteException, InvalidSignException, WrongFileFormatException, IsNullException,
        FileNotFoundException, EmptyListException, InvalidVectorRangeException {
      File file = new File("src/test/resources/JuliaWritePositive.txt");
      chaosGameFileHandler.writeToFile(juliaPositive, file);
      ChaosGameDescription chaosGameDescription = chaosGameFileHandler.readFromFile(file);
      file.delete();

      assertEquals(chaosGameDescription, juliaPositive);
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

    @Test
    @DisplayName("Test if readFromFile throws exception with extra comma")
    void testIfReadFromFileThrowsExceptionWithExtraComma() {
      File file = new File("src/test/resources/Affine2DReadNegative1.txt");
        assertThrows(WrongFileFormatException.class, () -> chaosGameFileHandler.readFromFile(file));
    }

    @Test
    @DisplayName("Test if readFromFile throws exception with letter mixed in")
    void testIfReadFromFileThrowsExceptionWithLetterMixedIn() {
      File file = new File("src/test/resources/Affine2DReadNegative2.txt");
        assertThrows(WrongFileFormatException.class, () -> chaosGameFileHandler.readFromFile(file));
    }

    @Test
    @DisplayName("Test if readFromFile throws exception on too few transformation values")
    void testIfReadFromFileThrowsExceptionOnTooFewValues() {
      File file = new File("src/test/resources/Affine2DReadNegative3.txt");
        assertThrows(WrongFileFormatException.class, () -> chaosGameFileHandler.readFromFile(file));
    }

    @Test
    @DisplayName("Test if readFromFile throws exception on hashtag before values")
    void testIfReadFromFileThrowsExceptionOnHashtagBeforeValues() {
      File file = new File("src/test/resources/JuliaReadNegative1.txt");
        assertThrows(WrongFileFormatException.class, () -> chaosGameFileHandler.readFromFile(file));
    }

    @Test
    @DisplayName("Test if readFromFile throws exception on wrong transformation type")
    void testIfReadFromFileThrowsExceptionOnWrongTransformationType() {
      File file = new File("src/test/resources/JuliaReadNegative2.txt");
        assertThrows(WrongFileFormatException.class, () -> chaosGameFileHandler.readFromFile(file));
    }

    @Test
    @DisplayName("Test if readFromFile throws exception on two transformations on one line")
    void testIfReadFromFileThrowsExceptionOnTwoTransformationsOnOneLine() {
      File file = new File("src/test/resources/JuliaReadNegative3.txt");
        assertThrows(WrongFileFormatException.class, () -> chaosGameFileHandler.readFromFile(file));
    }

  }
}