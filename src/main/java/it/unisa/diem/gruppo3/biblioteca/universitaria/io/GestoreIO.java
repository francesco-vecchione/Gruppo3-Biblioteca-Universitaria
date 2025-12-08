package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.collections.ObservableList;

/**
 * @author gruppo 3
 */
public class GestoreIO<T extends Dato> implements GestoreGenericoIO<T>{
    private String pathname;
    private GestoreCache<T> cache;

    public GestoreIO(String pathname) {
   
    }

    @Override
    public boolean salvaArchivio(ObservableList<T> archivio) {
        return false;
    }

    @Override
    public ObservableList<T> caricaArchivio() {
        return null;
    }

    @Override
    public boolean salvaModificaArchivio(CacheRecord<T> cacheRecord) {
        return false;
    }
    
    
}
