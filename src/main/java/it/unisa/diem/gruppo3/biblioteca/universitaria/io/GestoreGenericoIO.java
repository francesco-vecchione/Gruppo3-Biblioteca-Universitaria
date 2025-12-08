package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.collections.ObservableList;

/**
 * @author gruppo 3
 */
public interface GestoreGenericoIO<T extends Dato> {
    
    boolean salvaArchivio(ObservableList<T> archivio);
    
    ObservableList<T> caricaArchivio();
    
    boolean salvaModificaArchivio(CacheRecord<T> cacheRecord);
}
