module edu.ntnu.idatt2003.chaosgame {
  requires javafx.controls;
  requires javafx.fxml;


  opens edu.ntnu.idatt2003.chaosgame to javafx.fxml;
  exports edu.ntnu.idatt2003.chaosgame;
}