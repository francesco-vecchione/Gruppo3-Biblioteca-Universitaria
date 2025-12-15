package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.VBox;

/**
 * @file FiltroDialog.java
 * @author gruppo 3
 * @brief Dialog mono-pagina per filtrare prestiti
 */
public class PrestitiDialog {

    /**
     * @brief Dialog principale
     */
    private Dialog<Prestito> dialog;

    /**
     * @brief Bottone di conferma
     */
    private Button btnOk;

    /**
     * @brief Bottone di chiusura
     */
    private Button btnChiudi;

    /**
     * @brief ComboBox per la selezione dell'utente
     */
    private ComboBox<Utente> cbUtenti;

    /**
     * @brief ComboBox per la selezione del libro
     */
    private ComboBox<Libro> cbLibri;

    /**
     * @brief DatePicker per selezionare la data
     */
    private DatePicker datePicker;

    /**
     * @brief Costruttore di default
     */
    public PrestitiDialog(FilteredList<Libro> libri, FilteredList<Utente> utenti) {
        dialog = new Dialog<>();
        dialog.setTitle("Filtra Prestiti");
        dialog.setHeaderText("Seleziona Utente, Libro e Data");

        cbUtenti = new ComboBox<>();
        cbUtenti.getItems().addAll(utenti);
        cbUtenti.setPromptText("Seleziona Utente");

        cbLibri = new ComboBox<>();
        cbLibri.getItems().addAll(libri);
        cbLibri.setPromptText("Seleziona Libro");

        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        VBox form = new VBox(10);
        form.getChildren().addAll(cbUtenti, cbLibri, datePicker);
        dialog.getDialogPane().setContent(form);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);

        btnOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnChiudi = (Button) dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
    }

    /**
     * @brief Getter per ComboBox Utenti
     */
    public ComboBox<Utente> getCbUtenti() {
        return cbUtenti;
    }

    /**
     * @brief Getter per ComboBox Libri
     */
    public ComboBox<Libro> getCbLibri() {
        return cbLibri;
    }

    /**
     * @brief Getter per DatePicker
     */
    public DatePicker getDatePicker() {
        return datePicker;
    }

    /**
     * @brief Getter per bottone OK
     */
    public Button getBtnOk() {
        return btnOk;
    }

    /**
     * @brief Getter per bottone Chiudi
     */
    public Button getBtnChiudi() {
        return btnChiudi;
    }

    /**
     * @brief Getter per il Dialog
     */
    public Dialog<Prestito> getDialog() {
        return dialog;
    }
}
