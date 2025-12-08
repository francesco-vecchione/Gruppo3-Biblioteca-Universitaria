package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;

/**
 * @author gruppo 3
 */
public class TabArchivioLibri extends TabArchivio<Libro>{
    private Button bottoneCancella;
    
    public TabArchivioLibri(FilteredList<Libro> listaOsservabileLibri) {
        super(listaOsservabileLibri);
    }
    
    public Button getBottoneCancella() {
        return null;
    }
}
