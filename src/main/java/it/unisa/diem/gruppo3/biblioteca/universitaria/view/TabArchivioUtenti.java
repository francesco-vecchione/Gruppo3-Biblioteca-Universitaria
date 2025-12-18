package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TabPane;

/**
 * @file TabArchivioUtenti.java
 * @author gruppo 3
 * @brief Questa classe specializza TabArchivio per la gestione dell'archivio
 * degli utenti
 */
public class TabArchivioUtenti extends TabArchivio<Utente> {

    /**
     * @brief Bottone per la cancellazione di un elemento nella tabella
     */
    private final Button btnCancella;

    /**
     * @brief Costruttore che aggiunge al layout standard di una tab generica il
     * bottone di cancellazione
     * @param[in] listaOsservabileUtenti Lista visualizzata nella TableView
     * @pre listaOsservabileUtenti passato in input non deve essere null.
     * @post L'istanza di una specializzazione di TabArchivio è creata con la
     * lista specificata.
     */
    public TabArchivioUtenti(FilteredList<Utente> listaOsservabileUtenti) {
        super(listaOsservabileUtenti);
        btnCancella = new Button("Cancella Utente");
        getBtnAggiungi().setText("Registra Utente");
        getBtnModifica().setText("Modifica Dati Utente");

        TableColumn<Utente, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Utente, String> cognomeCol = new TableColumn<>("Cognome");
        cognomeCol.setCellValueFactory(new PropertyValueFactory<>("cognome"));

        TableColumn<Utente, String> emailCol = new TableColumn<>("email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Utente, String> matricolaCol = new TableColumn<>("Matricola");
        matricolaCol.setCellValueFactory(new PropertyValueFactory<>("matricola"));

        getTabella().getColumns().addAll(matricolaCol, nomeCol, cognomeCol, emailCol);
        getTabella().setItems(listaOsservabileUtenti);

        VBox boxBottoni = new VBox();
        boxBottoni.setStyle("-fx-background-color: #FFFDF5;");
        boxBottoni.setPrefWidth(223.0);
        boxBottoni.setPrefHeight(420.0);
        boxBottoni.setSpacing(10.0);
        boxBottoni.setAlignment(Pos.CENTER);

        Font fontKodchasan = Font.font("Kodchasan", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 15.0);

        InnerShadow innerShadow = new InnerShadow();

        //personalizzazione bottone aggiungi
        getBtnAggiungi().setMnemonicParsing(false);
        getBtnAggiungi().setPrefWidth(200.0);
        getBtnAggiungi().setPrefHeight(50.0);
        getBtnAggiungi().setStyle("-fx-background-color: #DE9A3A;");
        getBtnAggiungi().setTextAlignment(TextAlignment.CENTER);
        getBtnAggiungi().setWrapText(true);
        getBtnAggiungi().setFont(fontKodchasan);
        getBtnAggiungi().setCursor(Cursor.HAND); // Specifico solo per il primo bottone nel tuo FXML
        getBtnAggiungi().setEffect(innerShadow);
        getBtnAggiungi().setEffect(null);
        getBtnAggiungi().setOnMouseEntered(e -> getBtnAggiungi().setEffect(innerShadow));
        getBtnAggiungi().setOnMouseExited(e -> getBtnAggiungi().setEffect(null));

        //personalizzazione bottone modifica
        getBtnModifica().setMnemonicParsing(false);
        getBtnModifica().setPrefWidth(200.0);
        getBtnModifica().setPrefHeight(50.0);
        getBtnModifica().setStyle("-fx-background-color: #DE9A3A;");
        getBtnModifica().setTextAlignment(TextAlignment.CENTER);
        getBtnModifica().setWrapText(true);
        getBtnModifica().setFont(fontKodchasan);
        getBtnModifica().setCursor(Cursor.HAND);
        getBtnModifica().setEffect(null);
        getBtnModifica().setOnMouseEntered(e -> getBtnModifica().setEffect(innerShadow));
        getBtnModifica().setOnMouseExited(e -> getBtnModifica().setEffect(null));

        //personalizzazione bottone cancella
        btnCancella.setMnemonicParsing(false);
        btnCancella.setPrefWidth(200.0);
        btnCancella.setPrefHeight(50.0);
        btnCancella.setStyle("-fx-background-color: #DE733A;"); // Colore diverso come da FXML
        btnCancella.setTextAlignment(TextAlignment.CENTER);
        btnCancella.setWrapText(true);
        btnCancella.setFont(fontKodchasan);
        btnCancella.setCursor(Cursor.HAND);
        btnCancella.setEffect(null);
        btnCancella.setOnMouseEntered(e -> btnCancella.setEffect(innerShadow));
        btnCancella.setOnMouseExited(e -> btnCancella.setEffect(null));

        boxBottoni.getChildren().addAll(getBtnAggiungi(), getBtnModifica(), btnCancella);

        BorderPane root = new BorderPane();
        root.setCenter(getBoxCentro());
        root.setRight(boxBottoni);

        getTab().setText("Utenti");
        getTab().setContent(root);
        getTab().setClosable(false);
        
        
        
        
       
        
        //label tasto tab
        Label labelTab = new Label("Utenti");
        labelTab.setFont(Font.font("Kodchasan", FontWeight.SEMI_BOLD, 25));
        labelTab.setTextFill(Color.WHITE);
        getTab().setGraphic(labelTab);
        getTab().setText(""); // Nascondi testo di default
        labelTab.setPadding(new Insets(20, 30, 20, 30)); 

        //personalizzazione tasto tab
        getTab().setStyle("-fx-background-color: #8D4925; " +
                          "-fx-background-radius: 15 15 0 0; " +
                          "-fx-border-color: #4BA8D1; " +
                          "-fx-border-width: 0 0 1 0;");
        
        // Colori di esempio (Palette Midnight Library)
        String coloreAttivo = "-fx-background-color: #2C3E50; -fx-background-radius: 15 15 0 0; -fx-padding: 10 20 10 20;";
        String coloreInattivo = "-fx-background-color: #B5B5B5; -fx-background-radius: 15 15 0 0; -fx-padding: 10 20 10 20;";

        // Applichiamo lo stile iniziale (se è il primo tab sarà attivo)
        getTab().setStyle(getTab().isSelected() ? coloreAttivo : coloreInattivo);

        // Ascoltatore per il cambio di stato
        getTab().selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
        if (isNowSelected) {
                    // Stile quando il tab è SELEZIONATO
                    getTab().setStyle(coloreAttivo);
        if (getTab().getGraphic() instanceof Label label) {
                    label.setTextFill(Color.WHITE); // Testo chiaro
        }
        } else {
            // Stile quando il tab NON è selezionato
            getTab().setStyle(coloreInattivo);
            if (getTab().getGraphic() instanceof Label label) {
                label.setTextFill(Color.LIGHTGRAY); // Testo spento
            }
        }
    });
    }

    /**
     * @brief Getter per il bottone per la cancellazione
     * @return Il bottone per la cancellazione
     */
    public Button getBtnCancella() {
        return btnCancella;
    }
}
