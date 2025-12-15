package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.TextField;

/**
 * @file UtentiDialog.java
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per il caso in cui la finestra 
 * utilità venga generata dalla tab dell'archivio degli utenti
 */
public class UtentiDialog {
    /**
     * @brief Campo di testo per il nome dell'utente
     */
    private TextField txfNome;
    
    /**
     * @brief Campo di testo per il cognome dell'utente
     */
    private TextField txfCognome;
    
    /**
     * @brief Campo di testo per la matricola dell'utente
     */
    private TextField txfMatricola;
    
    /**
     * @brief Campo di testo per l'email dell'utente
     */
    private TextField txfEmail;
    
    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per l'inserimento o la modifica di un utente
     */
    public UtentiDialog() {
        
    }

    /**
     * @brief Getter per il campo di testo del nome
     * @return Campo di testo per il nome
     */
    public TextField getTxfNome() {
        return txfNome;
    }

    /**
     * @brief Getter per il campo di testo del cognome
     * @return Campo di testo per il cognome
     */
    public TextField getTxfCognome() {
        return txfCognome;
    }

    /**
     * @brief Getter per il campo di testo per la matricola
     * @return Campo di testo per la matricola
     */
    public TextField getTxfMatricola() {
        return txfMatricola;
    }

    /**
     * @brief Getter per il campo di testo per l'email
     * @return Campo di testo per l'email
     */
    public TextField getTxfEmail() {
        return txfEmail;
    }
}
