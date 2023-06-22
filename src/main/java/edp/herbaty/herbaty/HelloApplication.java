package edp.herbaty.herbaty;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        DBControl baza = new DBControl();
        try {
            baza.createTable();
        } catch (Exception e) {
        }
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Herbaciarnia");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}