package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;

/**
 * @author gruppo 3
 */
public class TabArchivioUtenti extends TabArchivio<Utente>{
    private Button btnCancella;
    
    public TabArchivioUtenti(FilteredList<Utente> listaOsservabileUtenti) {
        super(listaOsservabileUtenti);
    }
    
    public Button getBtnCancella() {
        return btnCancella;
    }
}
