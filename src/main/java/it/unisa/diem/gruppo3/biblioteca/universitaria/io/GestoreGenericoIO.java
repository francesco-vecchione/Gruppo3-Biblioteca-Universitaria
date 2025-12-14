package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.collections.ObservableList;

/**
 * @file GestoreGenericoIO.java
 * @author gruppo 3
 * @brief Questa interfaccia astrae il concetto di gestore generico di IO. Fornisce i servizi necessari al modello di archivio per salvare le modifiche fatte all'archivio effettivo nella cache, caricare e salvare l'archivio in maniera trasparente rispetto a crash e chiusure inaspettate dell'applicazione.
 */
public interface GestoreGenericoIO<T extends Dato> {

    /**
     * @brief Salva in un file l'archivio passato come input; ritorna un valore booleano che indica se l'operazione sia andata a buon fine.
     * @param[in] archivio  Lista osservabile contenente i dati da salvare.
     * @return true se l'operazione è andata a buon fine, false altrimenti.
     */
    boolean salvaArchivio(ObservableList<T> archivio);

    /**
     * @brief Recupera l'archivio da file, gestendo eventuali chiusure non corrette della precedente esecuzione; ritorna un valore booleano che indica il successo dell'operazione.
     * @return La lista osservabile contenente i dati recuperati.
     */
    ObservableList<T> caricaArchivio();

    /**
     * @brief Salva sulla cache la modifica dell'archivio passata in input; ritorna un valore booleano che indica se l'operazione sia andata a buon fine.
     * @param[in] cacheRecord   Il record della cache che descrive la
     * modifica.
     * @return true se l'operazione è andata a buon fine, false altrimenti.
     */
    boolean salvaModificaArchivio(CacheRecord<T> cacheRecord);
}