package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
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
    private String pathname;

    /**
     * @brief Costruttore che salva una copia del pathname passato in input.
     * @param[in] pathname  Il percorso del file di cache.
     * @pre
     * Il pathname non deve essere null.
     */
    public GestoreCache(String pathname) {
    }

    /**
     * @brief Salva su cache un aggiornamento qualsiasi dell'archivio; ritorna boolean per indicare se l'operazione ha avuto successo.
     * @param[in] cacheRecord   Il record da salvare sulla cache.
     * @return true se l'operazione ha avuto successo, false altrimenti.
     * @pre
     * Il record da salvare non deve essere null.
     */
    public boolean salvaSuCache(CacheRecord<T> cacheRecord) {
        return false;
    }

    /**
     * @brief Recupera la lista dei record contenuti nella cache; se la lista è vuota significa che il file è stato creato ex novo.
     * @return La lista dei record presenti nella cache.
     * @post
     * La lista restituita non è mai null.
     */
    public List<CacheRecord<T>> caricaDaCache() {
        return null;
    }

    /**
     * @brief Elimina il file di cache associato all'oggetto corrente; ritorna boolean per indicare se l'operazione ha avuto successo.
     * @return true se l'eliminazione ha avuto successo, false altrimenti.
     */
    public boolean eliminaCache() {
        return false;
    }
}