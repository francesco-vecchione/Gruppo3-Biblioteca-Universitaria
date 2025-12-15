package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * @file UtilityPopUpPrestiti.java
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per il caso in cui la finestra
 * utilità venga generata dalla tab dell'archivio dei prestiti
 */
public class UtilityPopUpPrestiti {
    /**
     * @brief Campo di testo per la matricola dell'utente richiedente del prestito
     */
    private TextField txfMatricola;
    
    /**
     * @brief Campo di testo per l'ISBN del libro richiesto per il prestito
     */
    private TextField txfIsbn;
    
    /**
     * @brief Selettore per la data di prestito
     */
    private DatePicker dpkDataPrestito;
    
    /**
     * @brief Selettore per la data di restituzione
     */
    private DatePicker dpkDataRestituzione;
    
    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per l'inserimento o la modifica di un Prestito
     */
    public UtilityPopUpPrestiti() {
        
    }

    /**
     * @brief Getter per il campo di testo della matricola del richiedente prestito
     * @return Campo di testo per la matricola del richiedente prestito
     */
    public TextField getTxfMatricola() {
        return txfMatricola;
    }

    /**
     * @brief Getter per il campo di testo per l'ISBN del libro richiesto per il
     * prestito
     * @return Campo di testo per l'ISBN del libro richiesto
     */
    public TextField getTxfIsbn() {
        return txfIsbn;
    }

    /**
     * @brief Getter per il selettore della data di richiesta prestito
     * @return Selettore della data di richiesta prestito
     */
    public DatePicker getDpkDataPrestito() {
        return dpkDataPrestito;
    }

    /**
     * @brief Getter per il selettore della data di restituzione del prestito
     * @return Selettore della data di restituzione del prestito
     */
    public DatePicker getDpkDataRestituzione() {
        return dpkDataRestituzione;
    }
}
