/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Alert;

/**
 *
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
