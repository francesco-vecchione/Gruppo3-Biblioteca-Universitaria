package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * @file ConfermaAlert.java
 * @author gruppo 3
 * 
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
