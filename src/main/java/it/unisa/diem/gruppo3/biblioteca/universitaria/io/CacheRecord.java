package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import java.io.Serializable;

/**
 * @author gruppo 3
 */
public class CacheRecord<T extends Dato> implements Serializable {
    private String tipoOperazione;
    private T target;
    private T elem;
    
    public CacheRecord(String tipoOperazione, T target, T elem) {
        
    }
    
    public String getTipoOperazione() {
        return null;
    }
    
    public T getTarget() {
        return null;
    }
    
    public T getElem() {
        return null;
    }
}
