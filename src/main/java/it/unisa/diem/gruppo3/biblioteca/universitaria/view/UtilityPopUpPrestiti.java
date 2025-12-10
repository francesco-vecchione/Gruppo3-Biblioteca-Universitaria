package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * @author gruppo 3
 */
public class UtilityPopUpPrestiti extends UtilityPopUp {
    private TextField txfMatricola;
    private TextField txfIsbn;
    private DatePicker dpkDataPrestito;
    private DatePicker dpkDataRestituzione;
    
    public UtilityPopUpPrestiti() {
        
    }

    public TextField getTxfMatricola() {
        return txfMatricola;
    }

    public TextField getTxfIsbn() {
        return txfIsbn;
    }

    public DatePicker getDpkDataPrestito() {
        return dpkDataPrestito;
    }

    public DatePicker getDpkDataRestituzione() {
        return dpkDataRestituzione;
    }
}
