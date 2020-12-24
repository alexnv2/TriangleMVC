package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("newForms.fxml"));
        primaryStage.setTitle("Треугольник");
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        int vSize = sSize.height;//разрешение монитора
        int hSize  = sSize.width;
        //System.out.println(vSize+"   " +hSize);
        primaryStage.setScene(new Scene(root, hSize-200, vSize-100));

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
