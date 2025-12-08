package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.scene.control.TextField;

/**
 * @author gruppo 3
 */
public class UtilityPopUpUtenti extends UtilityPopUp<Utente>{
    private TextField fieldNome;
    private TextField fieldCognome;
    private TextField fieldMatricola;
    private TextField fieldEmail;
    
    public UtilityPopUpUtenti() {
        
    }
    
    public UtilityPopUpUtenti(Utente placeHolder) {
        
    }
    
    @Override
    public Utente getDatiInseriti() {
        return null;
    }
}
