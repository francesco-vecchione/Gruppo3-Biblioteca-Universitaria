package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Alert;

/**
 * @file ErroreAlert.java
 * @author giosc
 */
public class ErroreAlert {
    public ErroreAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Errore!");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
