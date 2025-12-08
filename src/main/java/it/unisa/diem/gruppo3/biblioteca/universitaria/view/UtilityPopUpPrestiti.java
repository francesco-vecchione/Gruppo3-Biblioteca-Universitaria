package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import javafx.scene.control.TextField;

/**
 * @author gruppo 3
 */
public class UtilityPopUpPrestiti extends UtilityPopUp<Prestito>{
    private TextField fieldMatricolaUtente;
    private TextField fieldIsbnPrestito;
    private TextField fieldDataPrestito;
    private TextField fieldDataRestituzione;
    
    public UtilityPopUpPrestiti() {
        
    }
    
    public UtilityPopUpPrestiti(Prestito placeHolder) {
        
    }
    
    @Override
    public Prestito getDatiInseriti() {
        return null;
    } 
}
