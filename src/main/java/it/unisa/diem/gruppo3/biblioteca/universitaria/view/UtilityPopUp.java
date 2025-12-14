package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @file UtilityPopUp.java
 * @author gruppo 3
 * @brief La classe astrae una generica ginestra di pop up dotata di etichette
 * di intestazione e di errore, con bottoni di azione e di annullamento dell'operazione
 */
public abstract class UtilityPopUp {
    /**
     * @brief Etichetta di intestazione configurabile in base all'implementazione
     * concreta della classe
     */
    private Label lblIntestazione;
    
    /**
     * @brief Etichetta di notifica di eventuali errori durante l'interazione
     * del gestore con il pop up
     */
    private Label lblMessaggioErrore;
    
    /**
     * @brief Bottone per la conferma delle operazioni nella finestra
     */
    private Button btnAzione;
    
    /**
     * @brief Bottone per l'annullamento delle operazioni nella finestra
     */
    private Button btnAnnulla;
    
    /**
     * @brief Stage della finestra
     */
    private Stage stage;

    /**
     * @brief Costruttore di default di UtilityPopUp per impostare il layout del
     * pop up
     */
    public UtilityPopUp() {
    }

    /**
     * @brief Getter per il bottone di conferma
     * @return Bottone di conferma
     */
    public Button getBtnAzione() {
        return btnAzione;
    }

    /**
     * @brief Getter per il bottone di annullamento
     * @return Bottone di annullamento
     */
    public Button getBtnAnnulla() {
        return btnAnnulla;
    }

    /**
     * @brief Getter per lo stage
     * @return Stage del pop up
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @brief Setter per l'etichetta di intestazione
     * @param[in] lblIntestazione       Nuova etichetta di intestazione
     */
    public void setLblIntestazione(Label lblIntestazione) {
        this.lblIntestazione = lblIntestazione;
    }

    /**
     * @brief Setter per l'etichetta di errore
     * @param[in] lblMessaggioErrore    Nuova etichetta di errore
     */
    public void setLblMessaggioErrore(Label lblMessaggioErrore) {
        this.lblMessaggioErrore = lblMessaggioErrore;
    }
}
