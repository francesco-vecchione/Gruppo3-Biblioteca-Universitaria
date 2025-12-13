package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gruppo 3
 * @brief Questa classe rappresenta un gestore della cache. Per cache s'intende un file dove vengono salvate le operazioni svolte sull'archivio della biblioteca durante l'esecuzione del programma. La cache viene creata all'inizio dell'esecuzione e cancellata alla fine; se il file esiste da esecuzioni precedenti significa che il programma non è stato chiuso correttamente.
 * @invariant
 * Il pathname del file di cache non è null.
 */
public class GestoreCache<T extends Dato> {

    /**
     * @brief Stringa che mantiene il percorso del file di cache.
     */
    private final String pathname;

    /**
     * @brief Costruttore che salva una copia del pathname passato in input.
     * @param[in] pathname  Il percorso del file di cache.
     * @pre
     * Il pathname non deve essere null.
     */
    public GestoreCache(String pathname) {
        this.pathname = pathname;
        
        // Crea un nuovo file se e solo se non ne esiste uno con questo nome
        try {
            new File(this.pathname).createNewFile();
        } catch(IOException e) {
            // Non gestito
        }
    }

    /**
     * @brief Salva su cache un aggiornamento qualsiasi dell'archivio; ritorna boolean per indicare se l'operazione ha avuto successo.
     * @param[in] cacheRecord   Il record da salvare sulla cache.
     * @return true se l'operazione ha avuto successo, false altrimenti.
     * @pre
     * Il record da salvare non deve essere null.
     */
    public boolean salvaSuCache(CacheRecord<T> cacheRecord) {
        
        File f = new File(pathname);
        boolean appendFlag = f.exists() && f.length() > 0;
        
        // Il costruttore usato per FileOutputStream permette di usare lo stream in 
        //      modalità append
        try (FileOutputStream fos = new FileOutputStream(pathname, true);
                ObjectOutputStream oos = appendFlag ?   new AppendableObjectOutputStream(fos) :
                                                        new ObjectOutputStream(fos);){
            oos.writeObject(cacheRecord);
        } catch (IOException e) {
            return false;
        }
        
        return true;
    }

    /**
     * @brief Recupera la lista dei record contenuti nella cache; se la lista è vuota significa che il file è stato creato ex novo.
     * @return La lista dei record presenti nella cache, null se incontra un errore irreversibile.
     */
    public List<CacheRecord<T>> caricaDaCache() {
        
        List<CacheRecord<T>> cacheRecords = new LinkedList<>();
        
        try (FileInputStream fis = new FileInputStream(pathname);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            while(true) {
                cacheRecords.add((CacheRecord<T>) ois.readObject());
            }
        } catch (EOFException e) {
            // Tutto bene, ha concluso la lettura
        } catch (Exception e) {
            return null;
        }
        
        return cacheRecords;
    }

    /**
     * @brief Elimina il file di cache associato all'oggetto corrente; ritorna boolean per indicare se l'operazione ha avuto successo.
     * @return true se l'eliminazione ha avuto successo, false altrimenti.
     */
    public boolean eliminaCache() {
        return new File(pathname).delete();
    }
}