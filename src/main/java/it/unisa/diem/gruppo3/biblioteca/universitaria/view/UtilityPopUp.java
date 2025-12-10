package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author gruppo 3
 */
public abstract class UtilityPopUp {
    private Label lblIntestazione;
    private Label lblMessaggioErrore;
    private Button btnAzione;
    private Button btnAnnulla;
    Stage stage;

    public UtilityPopUp() {
    }

    public Button getBtnAzione() {
        return btnAzione;
    }

    public Button getBtnAnnulla() {
        return btnAnnulla;
    }

    public Stage getStage() {
        return stage;
    }

    public void setLblIntestazione(Label lblIntestazione) {
        this.lblIntestazione = lblIntestazione;
    }

    public void setLblMessaggioErrore(Label lblMessaggioErrore) {
        this.lblMessaggioErrore = lblMessaggioErrore;
    }
}
