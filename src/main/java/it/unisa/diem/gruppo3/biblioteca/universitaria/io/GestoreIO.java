package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @file GestoreIO.java
 * @author gruppo 3
 * @brief Questa classe concretizza l'interfaccia GestoreGenericoIO.
 * @invariant
 * Il pathname del file di archivio non è mai null.
 * @invariant
 * Il riferimento al gestore della cache non è mai null dopo l'inizializzazione.
 */
public class GestoreIO<T extends Dato> implements GestoreGenericoIO<T> {

    /**
     * @brief Stringa che mantiene il percorso del file di archivio.
     */
    private final String pathname;

    /**
     * @brief Reference ad un oggetto di tipo GestoreCache per utilizzare i relativi servizi.
     */
    private final GestoreCache<T> cache;

    /**
     * @brief Costruttore che istanzia l'oggetto GestoreCache e memorizza il pathname dell'archivio.
     * @param[in] pathname  Il percorso del file di archivio su cui operare.
     * @pre
     * Il pathname non deve essere null.
     * @post
     * L'istanza è creata e il gestore della cache è inizializzato.
     */
    public GestoreIO(String pathname) {
        this.pathname = pathname;
        
        cache = new GestoreCache<>(this.pathname + "Cache");
        
        // Crea un nuovo file se e solo se non ne esiste uno con questo nome
        try {
            new File(this.pathname).createNewFile();
        } catch (IOException e) {
            // Non gestito
        }
    }

    /**
     * @brief Implementazione del metodo per salvare l'archivio su file (Vedi Contratto GestoreGenericoIO).
     * @param[in] archivio  Lista osservabile da salvare.
     * @return true se il salvataggio riesce, false altrimenti.
     * @pre
     * L'archivio non è null.
     */
    @Override
    public boolean salvaArchivio(ObservableList<T> archivio) {
        
        // Riscrive il file archivio
        try (FileOutputStream fos = new FileOutputStream(pathname);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            
            for(T elem : archivio) {
                oos.writeObject(elem);
            } 
        } catch (IOException e) {
            return false;
        }
        
        cache.eliminaCache();
        
        return true;
    }

    /**
     * @brief Implementazione del metodo per caricare l'archivio da file (Vedi Contratto GestoreGenericoIO).
     * @return La lista dei dati caricati.
     * @post
     * Viene restituita una lista valida (eventualmente vuota).
     */
    @Override
    public ObservableList<T> caricaArchivio() {
        
        ObservableList<T> archivio = FXCollections.observableArrayList();
        
        try (FileInputStream fis = new FileInputStream(pathname);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis)) {
            
            while(true) {
                archivio.add((T) ois.readObject());
            }
            
        } catch (EOFException e) {
            // Tutto bene, ha concluso la lettura
        } catch (Exception e) {
            // Errore in fase di lettura
        }
        
        List<CacheRecord<T>> archivioCache = cache.caricaDaCache();
        if(archivioCache != null) {
            for(CacheRecord<T> cr : archivioCache) {
                switch (cr.getTipoOperazione()) {
                    case AGGIUNTA:
                        if(cr.getElem() != null) archivio.add(cr.getElem());
                        break;
                    case MODIFICA:
                        if(cr.getTarget() != null && cr.getElem() != null) {
                            int index = archivio.indexOf(cr.getTarget());
                            if(index >= 0) archivio.set(index, cr.getElem());
                        }
                        break;
                    case CANCELLAZIONE:
                        if(cr.getTarget() != null) archivio.remove(cr.getTarget());
                        break;
                    default:
                        // Clausola di default aggiunta in quanto tipoOperazione
                        // potrebbe essere null poiché il costruttore di CacheRecord
                        // non controlla se sia null 
                        break;
                }
            }
        }
        
        return archivio;
    }

    /**
     * @brief Implementazione del metodo per salvare una modifica nella cache (Vedi Contratto GestoreGenericoIO).
     * @param[in] cacheRecord   Il record della modifica.
     * @return true se la scrittura in cache riesce, false altrimenti.
     * @pre
     * cacheRecord non è null.
     */
    @Override
    public boolean salvaModificaArchivio(CacheRecord<T> cacheRecord) {
        return cache.salvaSuCache(cacheRecord);
    }
}