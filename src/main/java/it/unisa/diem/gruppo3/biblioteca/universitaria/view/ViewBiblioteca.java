package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.collections.transformation.FilteredList;
import javafx.stage.Stage;

/**
 * @author gruppo 3
 */
public class ViewBiblioteca {
    private Stage stage;
    private TabArchivio<Libro> tabLibri;
    private TabArchivio<Utente> tabUtenti;
    private TabArchivio<Prestito> tabPrestiti;
    
    public ViewBiblioteca(Stage stage, FilteredList<Libro> listaOsservabileLibri, FilteredList<Utente> listaOsservabileUtenti, FilteredList<Prestito> listaOsservabilePrestiti) {
        
    }
    
    public Stage getStage() {
        return null;
    }
    
    public TabArchivio<Libro> getTabLibri() {
        return null;
    }
    
    public TabArchivio<Utente> getTabUtenti() {
        return null;
    }
    
    public TabArchivio<Prestito> getTabPrestiti() {
        return null;
    }
}
