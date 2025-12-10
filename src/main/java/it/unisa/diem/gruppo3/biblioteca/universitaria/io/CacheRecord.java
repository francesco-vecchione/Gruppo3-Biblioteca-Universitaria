package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import java.io.Serializable;

/**
 * @author gruppo 3
 * @brief Questa classe rappresenta un record della cache.
 * @invariant
 * Il tipo di operazione non deve essere null.
 */
public class CacheRecord<T extends Dato> implements Serializable {

    /**
     * @brief Stringa che indica il tipo di operazione svolta.
     */
    private String tipoOperazione;

    /**
     * @brief Elemento cancellato o modificato (null in caso di aggiunta).
     */
    private T target;

    /**
     * @brief Elemento aggiunto o che modifica un elemento esistente (null in caso di cancellazione).
     */
    private T elem;

    /**
     * @brief Costruttore che inizializza gli attributi del record.
     * @param[in] tipoOperazione    Stringa che indica il tipo di operazione
     * svolta (es. "insert", "delete").
     * @param[in] target            Elemento cancellato o modificato; null in
     * caso di aggiunta.
     * @param[in] elem              Elemento aggiunto o che modifica un
     * elemento esistente; null in caso di
     * cancellazione.
     * @pre
     * Il tipo di operazione non deve essere null.
     */
    public CacheRecord(String tipoOperazione, T target, T elem) {
    }

    /**
     * @brief Restituisce il tipo di operazione.
     * @return La stringa rappresentante l'operazione.
     */
    public String getTipoOperazione() {
        return null;
    }

    /**
     * @brief Restituisce l'elemento target.
     * @return L'oggetto target (può essere null).
     */
    public T getTarget() {
        return null;
    }

    /**
     * @brief Restituisce l'elemento elem.
     * @return L'oggetto elem (può essere null).
     */
    public T getElem() {
        return null;
    }
}