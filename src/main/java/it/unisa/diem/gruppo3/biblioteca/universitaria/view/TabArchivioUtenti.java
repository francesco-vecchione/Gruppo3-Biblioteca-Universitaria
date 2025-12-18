package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

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

        boxBottoni.setPrefWidth(223.0);
        boxBottoni.setPrefHeight(420.0);
        boxBottoni.setSpacing(10.0);

        Font fontKodchasan = Font.font("Kodchasan", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 20.0);

        InnerShadow innerShadow = new InnerShadow();

        //personalizzazione bottone aggiungi
        getBtnAggiungi().setMnemonicParsing(false);
        getBtnAggiungi().setPrefWidth(240.0);
        getBtnAggiungi().setPrefHeight(100.0);
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
        getBtnModifica().setPrefWidth(240.0);
        getBtnModifica().setPrefHeight(100.0);
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
        btnCancella.setPrefWidth(240.0);
        btnCancella.setPrefHeight(100.0);
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
    }

    /**
     * @brief Getter per il bottone per la cancellazione
     * @return Il bottone per la cancellazione
     */
    public Button getBtnCancella() {
        return btnCancella;
    }
}
