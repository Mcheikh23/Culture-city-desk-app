package com.mycompany.projetman;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Entity.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class MainApp extends Application {
 public static User user = new User(3, "Hachem", "123456789");
    @Override
    public void start(Stage stage) throws Exception {
      //  Parent root = FXMLLoader.load(getClass().getResource("/fxml/AccueilClub.fxml"));
      //  Parent root = FXMLLoader.load(getClass().getResource("/fxml/AcceuilForum.fxml"));
       //  Parent root = FXMLLoader.load(getClass().getResource("/fxml/AcceuilCC.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
