package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import it.unisa.diem.gruppo3.biblioteca.universitaria.io.GestoreGenericoIO;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * @author gruppo 3
 */
public class ModelArchivio<T extends Dato>{
    private ObservableList<T> archivio;
    private FilteredList<T> archivioFiltrato;
    private GestoreGenericoIO<T> io;

    public ModelArchivio(String pathname) {
    
    }
    
    public boolean apriArchivio() {
        return false;
    }
    
    public boolean chiudiArchivio() {
        return false;
    }
    
    public FilteredList<T> getArchivioFiltrato() {
        return null;
    }
    
    public boolean aggiungiElemento(T elem) {
        return false;
    }
    
    public boolean rimuoviElemento(T target) {
        return false;
    }
    
    public boolean modificaElemento(T target, T elem) {
        return false;
    }
}
