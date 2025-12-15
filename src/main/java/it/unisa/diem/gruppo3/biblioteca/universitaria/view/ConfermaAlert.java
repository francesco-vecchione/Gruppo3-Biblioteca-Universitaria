/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author giosc
 */
public class ConfermaAlert {
    
    private boolean esito = false;

    public ConfermaAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText("Conferma");
        alert.setContentText(text);
        alert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) esito = true;
        });
    }
    
    public boolean getEsito() {
        return esito;
    }
    
}
