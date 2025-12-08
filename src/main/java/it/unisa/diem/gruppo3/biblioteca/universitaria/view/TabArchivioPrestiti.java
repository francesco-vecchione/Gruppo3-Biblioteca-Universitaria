package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import javafx.collections.transformation.FilteredList;

/**
 * @author gruppo 3
 */
public class TabArchivioPrestiti extends TabArchivio<Prestito> { 
    
    public TabArchivioPrestiti(FilteredList<Prestito> listaOsservabilePrestiti) {
        super(listaOsservabilePrestiti);
    }
}
