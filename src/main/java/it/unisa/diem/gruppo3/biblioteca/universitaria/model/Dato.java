package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import java.io.Serializable;

/**
 * @author gruppo 3
 * @brief Questa interfaccia astrae il concetto di dato che la biblioteca deve gestire.
 * @invariant
 * L'implementazione di Dato deve essere serializzabile e comparabile.
 */
public interface Dato extends Comparable<Object>, Serializable {

    /**
     * @brief Metodo invocato su una particolare istanza per verificare se tale istanza è corretta secondo i principi della classe di appartenenza.
     * @return true se l'istanza è valida, false altrimenti.
     */
    boolean isValid();

    /**
     * @brief Metodo per verificare se tutti i campi obbligatori sono stati riempiti.
     * @return true se i campi sono pieni, false altrimenti.
     */
    boolean isFilled();
}