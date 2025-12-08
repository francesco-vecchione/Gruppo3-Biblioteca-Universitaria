package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import java.util.List;

/**
 * @author gruppo 3
 */
public class GestoreCache<T extends Dato> {
    private String pathname;

    public GestoreCache(String pathname) {
    
    }
    
    public boolean salvaSuCache(CacheRecord<T> cacheRecord) {
        return false;
    }
    
    public List<CacheRecord<T>> carcaDaCache() {
        return null;
    }
    
    public boolean eliminaCache() {
        return false;
    }
}
