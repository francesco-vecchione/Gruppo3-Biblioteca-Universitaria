package it.unisa.diem.gruppo3.biblioteca.universitaria.main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX AppBibliotecaUniversitaria
 */
public class AppBibliotecaUniversitaria extends Application {

    @Override
    public void start(Stage stage) {
        Label lbl1 = new Label("Prova");
        HBox box = new HBox(lbl1);
        box.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(box);
        
        
        stage.setWidth(300);
        stage.setHeight(300);
        
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}