package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.TextField;

/**
 * @file LibriDialog.java
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per il caso in cui la finestra 
 * di utilità venga generata dalla tab dell'archivio dei libri
 */
public class LibriDialog {
    /**
     * @brief Campo di testo per il testo del libro
     */
    private TextField txfTitolo;
    
    /**
     * @brief Campo di testo per la lista degli autori
     */
    private TextField txfAutori;
    
    /**
     * @brief Campo di testo per l'anno di pubblicazione
     */
    private TextField txfAnnoPubblicazione;
    
    /**
     * @brief Campo di testo per il codice ISBN
     */
    private TextField txfIsbn;
    
    /**
     * @brief Campo di testo per il numero di copie
     */
    private TextField txfNumCopie;
    
    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per l'inserimento o la modifica di un libro
     */
    public LibriDialog() {
        
    }

    /**
     * @brief Getter del campo di testo per il titolo
     * @return Campo di testo per il titolo
     */
    public TextField getTxfTitolo() {
        return txfTitolo;
    }

    /**
     * @brief Getter del campo di testo per la lista degli autori
     * @return Campo di testo per la lista degli autori
     */
    public TextField getTxfAutori() {
        return txfAutori;
    }

    /**
     * @brief Getter del campo di testo per l'anno di pubblicazione
     * @return Campo di testo per l'anno di pubblicazione
     */
    public TextField getTxfAnnoPubblicazione() {
        return txfAnnoPubblicazione;
    }

    /**
     * @brief Getter del campo di testo per l'ISBN
     * @return Campo di testo per l'ISBN
     */
    public TextField getTxfIsbn() {
        return txfIsbn;
    }

    /**
     * @brief Getter del campo di testo per il numero di copie
     * @return Campo di testo per il numero di copie
     */
    public TextField getTxfNumCopie() {
        return txfNumCopie;
    }
}
