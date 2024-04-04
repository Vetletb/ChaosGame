package edu.ntnu.idatt2003.model.io;

import edu.ntnu.idatt2003.model.game.ChaosGameDescription;
import edu.ntnu.idatt2003.model.math.mathModel.Complex;
import edu.ntnu.idatt2003.model.math.mathModel.Matrix2x2;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.AffineTransform2D;
import edu.ntnu.idatt2003.model.math.transformation.JuliaTransform;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * This class handles reading and writing of files for the ChaosGameDescription class.
 */
public class ChaosGameFileHandler {

  /**
   * Reads a ChaosGameDescription from a file.
   *
   * @param path the path to the file.
   * @return the ChaosGameDescription read from the file.
   */
  public ChaosGameDescription readFromFile(String path) throws FileNotFoundException {
    File file = new File(path);
    try (Scanner scanner = new Scanner(file)) {
      scanner.useDelimiter("#.*|\n");
      ArrayList<String> units = new ArrayList<>();
      while (scanner.hasNext()) {
        String line = scanner.next().trim();

        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {
          lineScanner.useDelimiter(", ");
          units.add(lineScanner.next());
        }
      }
      if (units.get(0).equals("Affine2D")) {
        ArrayList<Transform2D> transforms = new ArrayList<>();
        for (int i = 5; i < units.size(); i += 6) {
          double a = Double.parseDouble(units.get(i));
          double b = Double.parseDouble(units.get(i + 1));
          double c = Double.parseDouble(units.get(i + 2));
          double d = Double.parseDouble(units.get(i + 3));
          double e = Double.parseDouble(units.get(i + 4));
          double f = Double.parseDouble(units.get(i + 5));
          transforms.add(new AffineTransform2D(new Matrix2x2(a, b, c, d), new Vector2D(e, f)));
        }
        double minX = Double.parseDouble(units.get(1));
        double minY = Double.parseDouble(units.get(2));
        double maxX = Double.parseDouble(units.get(3));
        double maxY = Double.parseDouble(units.get(4));
        return new ChaosGameDescription(transforms, new Vector2D(minX, minY), new Vector2D(maxX, maxY));
      } else if (units.get(0).equals("Julia")) {
        double minX = Double.parseDouble(units.get(1));
        double minY = Double.parseDouble(units.get(2));
        double maxX = Double.parseDouble(units.get(3));
        double maxY = Double.parseDouble(units.get(4));
        double real = Double.parseDouble(units.get(5));
        double imaginary = Double.parseDouble(units.get(6));
        JuliaTransform juliaTransformPositive = new JuliaTransform(new Complex(real, imaginary), 1);
        JuliaTransform juliaTransformNegative = new JuliaTransform(new Complex(real, imaginary), -1);
        return new ChaosGameDescription(List.of(juliaTransformNegative, juliaTransformPositive), new Vector2D(minX, minY), new Vector2D(maxX, maxY));
      }
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found");
    }
    return null;
  }


  /**
   * Writes a ChaosGameDescription to a file.
   *
   * @param chaosGameDescription the ChaosGameDescription to write.
   * @param path the path to the file.
   */
  public void writeToFile(ChaosGameDescription chaosGameDescription, String path) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      String type = chaosGameDescription.getTransforms().get(0) instanceof AffineTransform2D ? "Affine2D" : "Julia";
      String minCoords = chaosGameDescription.getMinCoords().getX0() + ", " + chaosGameDescription.getMinCoords().getX1();
      String maxCoords = chaosGameDescription.getMaxCoords().getX0() + ", " + chaosGameDescription.getMaxCoords().getX1();
      writer.write(type + "\n");
      writer.write(minCoords + "\n");
      writer.write(maxCoords + "\n");
      if (type.equals("Affine2D")) {
        for (Transform2D transform : chaosGameDescription.getTransforms()) {
          AffineTransform2D affineTransform = (AffineTransform2D) transform;

          Matrix2x2 matrix = affineTransform.getMatrix();
          double a00 = matrix.getA00();
          double a01 = matrix.getA01();
          double a10 = matrix.getA10();
          double a11 = matrix.getA11();

          Vector2D vector = affineTransform.getVector();
          double x0 = vector.getX0();
          double x1 = vector.getX1();

          writer.write(a00 + ", " + a01 + ", " + a10 + ", " + a11 + ", " + x0 + ", " + x1 + "\n");
        }
      } else if (type.equals("Julia")) {
        JuliaTransform juliaTransform = (JuliaTransform) chaosGameDescription.getTransforms().get(0);

        Complex complex = juliaTransform.getPoint();
        double real = complex.getX0();
        double imaginary = complex.getX1();

        writer.write(real + ", " + imaginary + "\n");
      }

    } catch (IOException e) {
      throw new IOException("Could not write to file");
    }
  }

  /**
   * Lists all files in the resources directory.
   *
   * @return a list of all files in the resources directory.
   */
  public List<String> listFiles() {
    List<String> fileList = new ArrayList<>();
    try {
      Path resourceDirectory = Paths.get("src/main/resources");
      try (Stream<Path> paths = Files.walk(resourceDirectory)) {
        paths
            .filter(Files::isRegularFile)
            .forEach(path -> fileList.add(path.toString()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileList;
  }
}
