package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;

import java.time.LocalDate;
import javafx.scene.paint.Color;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonBar;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
        
        
        //creazione della dialog con lo sfondo
        dialog = new Dialog<>();
        dialog.getDialogPane().setStyle("-fx-background-color: #FFFDF5;");
        dialog.setTitle("Registrazione nuovo prestito");

       
        
        //creazione anchorpane
        AnchorPane root = new AnchorPane();
        root.setPrefHeight(474.0);
        root.setPrefWidth(400.0);

        //text titolo
        Text txtTitolo = new Text("Inserisci i dati per registrare il prestito");
        txtTitolo.setLayoutX(22.0);
        txtTitolo.setLayoutY(80.0);
        txtTitolo.setWrappingWidth(354.55);
        txtTitolo.setTextAlignment(TextAlignment.CENTER);
        // Nota: Assicurati che il font sia installato nel sistema, altrimenti userà il default
        txtTitolo.setFont(Font.font("Avenir Next", FontWeight.BOLD, FontPosture.ITALIC, 25.0));

        //etichetta utente
        Text lblUtente = new Text("Utente");
        lblUtente.setFill(Color.web("#979696"));
        lblUtente.setLayoutX(59.0);
        lblUtente.setLayoutY(171.0);
        lblUtente.setWrappingWidth(280.0);
        lblUtente.setFont(Font.font("Kodchasan", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 20.0));

        //combobox utente
        cbUtenti = new ComboBox<>();
        cbUtenti.setPromptText("Seleziona Utente");
        cbUtenti.getItems().addAll(utenti);
        cbUtenti.setLayoutX(59.0);
        cbUtenti.setLayoutY(177.0);
        cbUtenti.setPrefHeight(36.0);
        cbUtenti.setPrefWidth(280.0);

        //etichetta libro
        Text lblLibro = new Text("Libro");
        lblLibro.setFill(Color.web("#979696"));
        lblLibro.setLayoutX(60.0);
        lblLibro.setLayoutY(247.0);
        lblLibro.setWrappingWidth(280.0);
        lblLibro.setFont(Font.font("Kodchasan", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 20.0));

        // combobox libro
        cbLibri = new ComboBox<>();
        cbLibri.setPromptText("Seleziona Libro");
        cbLibri.getItems().addAll(libri);
        cbLibri.setLayoutX(60.0);
        cbLibri.setLayoutY(254.0);
        cbLibri.setPrefHeight(36.0);
        cbLibri.setPrefWidth(280.0);

        //label data di restituzione
        Text lblData = new Text("Data di restituzione");
        lblData.setFill(Color.web("#979696"));
        lblData.setLayoutX(59.0);
        lblData.setLayoutY(332.0);
        lblData.setWrappingWidth(280.0);
        lblData.setFont(Font.font("Kodchasan", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 20.0));

        //datepicker
        datePicker = new DatePicker();
        datePicker.setLayoutX(59.0);
        datePicker.setLayoutY(338.0);
        datePicker.setPrefHeight(36.0);
        datePicker.setPrefWidth(280.0);
        
        
        root.getChildren().addAll(
            txtTitolo, 
            lblUtente, cbUtenti, 
            lblLibro, cbLibri, 
            lblData, datePicker 
        );

        //aggiungo la anchorpane al dialogpane
        dialog.getDialogPane().setContent(root);

        //definizione del bottone della dialogpane
        ButtonType typeEntra = new ButtonType("Conferma", ButtonBar.ButtonData.OK_DONE);
        
        //aggiungo il bottone alla dialog pane
        dialog.getDialogPane().getButtonTypes().addAll(typeEntra);

        
        //chiamo il bottone per personalizzarlo
        btnOk = (Button) dialog.getDialogPane().lookupButton(typeEntra);

        //effetto ombra del bottone
        InnerShadow buttonShadow = new InnerShadow();
        buttonShadow.setBlurType(BlurType.TWO_PASS_BOX);

        //stile bottone "entra"
        btnOk.setPrefSize(119.0, 36.0);
        btnOk.setStyle("-fx-background-color: #6AB565; -fx-text-fill: black; -fx-background-radius:8;"); // Verde
        btnOk.setFont(new Font("Kodchasan SemiBold Italic", 17.0));
        btnOk.setCursor(Cursor.HAND);
        btnOk.setOnMouseEntered(e -> btnOk.setEffect(buttonShadow));
        btnOk.setOnMouseExited(e -> btnOk.setEffect(null));
        btnOk.setTranslateX(-130); //sposta il tasto in basso al centro

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
