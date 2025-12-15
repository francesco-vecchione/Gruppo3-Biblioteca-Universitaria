package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.TextField;

/**
 * @file CreazionePasswordDialog.java
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per adattarsi alle funzionalità 
 * di registrazione e di reimpostazione password
 */
public class CreazionePasswordDialog {
    /**
     * @brief Campo di testo per inserire la password
     */
    private TextField txfPassword;
    
    /**
     * @brief Campo di testo per reinserire la password
     */
    private TextField txfConfermaPassword;
    
    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per l'inserimento password
     */
    public CreazionePasswordDialog() {
    }

    /**
     * @brief Getter per il campo di testo per la password
     * @return Il campo di testo per la password
     */
    public TextField getTxfPassword() {
        return txfPassword;
    }

    /**
     * @brief Getter per il campo di testo per la conferma della password
     * @return Il campo di testo per la comferma della password
     */
    public TextField getTxfConfermaPassword() {
        return txfConfermaPassword;
    }
}
