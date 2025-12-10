package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.scene.control.TextField;

/**
 * @author gruppo 3
 */
public class UtilityPopUpUtenti extends UtilityPopUp {
    private TextField txfNome;
    private TextField txfCognome;
    private TextField txfMatricola;
    private TextField txfEmail;
    
    public UtilityPopUpUtenti() {
        
    }

    public TextField getTxfNome() {
        return txfNome;
    }

    public TextField getTxfCognome() {
        return txfCognome;
    }

    public TextField getTxfMatricola() {
        return txfMatricola;
    }

    public TextField getTxfEmail() {
        return txfEmail;
    }
}
