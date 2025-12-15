package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import it.unisa.diem.gruppo3.biblioteca.universitaria.io.CacheRecord;
import it.unisa.diem.gruppo3.biblioteca.universitaria.io.GestoreGenericoIO;
import it.unisa.diem.gruppo3.biblioteca.universitaria.io.GestoreIO;
import it.unisa.diem.gruppo3.biblioteca.universitaria.io.TipoOperazione;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * @file ModelArchivio.java
 * @author Gruppo 3
 * @brief Questa classe rappresenta il modello di archivio accessibile al controller. Le funzionalità possibili sono aprire e chiudere l'archivio, ottenere la lista osservabile (filtrata) dei dati, aggiungere, rimuovere e modificare un elemento dalla lista osservabile. La classe è generica per potersi adattare alla necessità di astrarre il concetto di archivio dei Libro, degli Utente e dei Prestito.
 * @invariant
 * La lista osservabile archivio non è mai null.
 * @invariant
 * La lista filtrata archivioFiltrato non è mai null.
 * @invariant
 * Il gestore di IO non è mai null.
 */
public class ModelArchivio<T extends Dato> {

    /**
     * @brief Lista osservabile che astrae l'archivio.
     */
    private ObservableList<T> archivio;

    /**
     * @brief Lista ordinata e filtrabile che viene visualizzata dalle tabelle.
     */
    private FilteredList<T> archivioFiltrato;

    /**
     * @brief Mantiene una reference al gestore generico IO per garantire i propri servizi.
     */
    private GestoreGenericoIO<T> io;

    /**
     * @brief Costruttore che istanzia GestoreIO e inizializza le strutture che astraggono l'archivio.
     * @param[in] pathname  Il percorso del file dell'archivio.
     * @pre
     * Il pathname non deve essere null.
     * @post
     * Le strutture dati (archivio, archivioFiltrato) e il gestore IO sono inizializzati.
     */
    public ModelArchivio(String pathname) {
        io = new GestoreIO<>(pathname);
    }

    /**
     * @brief Richiede a GestoreIO l'archivio salvato su file; ritorna un valore booleano che indica se il caricamento dei dati è avvenuto con successo. In caso di ritorno di false, è anche possibile che il caricamento dell'archivio sia avvenuto accedendo a record contenuti nella cache (chiusura non corretta nell'esecuzione precedente).
     */
    public void apriArchivio() {
        
        archivio = io.caricaArchivio();
        SortedList archivioOrdinato = new SortedList(archivio);
        archivioFiltrato = new FilteredList<>(archivioOrdinato);
        
    }

    /**
     * @brief Richiede a GestoreIO di salvare l'archivio in modo corretto l'archivio su file. Ritorna un valore boolean che indica se l'operazione è avvenuta con successo.
     * @return true se il salvataggio ha avuto successo, false altrimenti.
     */
    public boolean chiudiArchivio() { 
        
        return io.salvaArchivio(archivio);
    }

    /**
     * @brief Semplice metodo accessor per archivioFiltrato.
     * @return La lista filtrata (FilteredList) dei dati.
     */
    public FilteredList<T> getArchivioFiltrato() {
        return archivioFiltrato;
    }

    /**
     * @brief Metodo delega per l'operazione di aggiunta di un elemento all'archivio che richiede a GestoreIO l'aggiunta dell'operazione in cache. Ritorna un valore booleano che indica se l'operazione è avvenuta con successo.
     * @param[in] elem  L'elemento da aggiungere all'archivio.
     * @return true se l'aggiunta ha avuto successo, false altrimenti.
     * @pre
     * L'elemento da aggiungere non deve essere null.
     */
    public boolean aggiungiElemento(T elem) {
        
        if(!elem.isValid())
            return false;
        if(archivio.contains(elem))
            return false;
        
        if(archivio.add(elem))
            return io.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.AGGIUNTA, null, elem));
        
        return false;
    }

    /**
     * @brief Metodo delega per la rimozione di un elemento dall'archivio selezionato dalla tabella che richiede a GestoreIO l'aggiunta dell'operazione in cache. Ritorna un valore booleano che indica se l'operazione è avvenuta con successo.
     * @param[in] target    L'elemento da rimuovere dall'archivio.
     * @return true se la rimozione ha avuto successo, false altrimenti.
     * @pre
     * L'elemento target deve esistere nell'archivio.
     */
    public boolean rimuoviElemento(T target) {
        
        if(archivio.remove(target))
            return io.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.CANCELLAZIONE, target, null));
        
        return false;
    }

    /**
     * @brief Metodo per la modifica di un elemento selezionato dalla tabella (target) nell'archivio che richiede a GestoreIO l'aggiunta dell'operazione in cache. Ritorna un valore booleano che indica se l'operazione è avvenuta con successo.
     * @param[in] target    L'elemento originale da modificare.
     * @param[in] elem      Il nuovo elemento modificato.
     * @return true se la modifica ha avuto successo, false altrimenti.
     * @pre
     * Target ed elem non devono essere nulli.
     */
    public boolean modificaElemento(T target, T elem) {

        if(!elem.isValid())
            return false;
        
        int index = ricercaElemento(target);
        if(index < 0) return false;
        
        archivio.set(index, elem);
        return io.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.MODIFICA, target, elem));
    }
    
    /**
     * @brief Funzione per ricercare la posizione dell'elemento all'interno dell'archivio
     * @param[in] target elemento di di cui si vuole controllare la presenza nell'archivio
     * @return Indice dell'elemento nll'archivio, ritorna -1 se non è presente
     */
    public int ricercaElemento(T target) {
        return archivio.indexOf(target);
    }
}