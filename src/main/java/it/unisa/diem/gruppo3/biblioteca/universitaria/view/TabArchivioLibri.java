package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.effect.InnerShadow;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.Cursor;


/**
 * @file TabArchivioLibri.java
 * @author gruppo 3
 * @brief Questa classe specializza TabArchivio per la gestione dell'archivio dei
 * libri
 */
public class TabArchivioLibri extends TabArchivio<Libro>{
    /**
     * @brief Bottone per la cancellazione di un elemento nella tabella
     */
    private final Button btnCancella;
    
    /**
     * @brief Costruttore che aggiunge al layout standard di una tab generica il 
     * bottone di cancellazione
     * @param[in] listaOsservabileLibri     Lista visualizzata nella TableView
     * @pre
     *      listaOsservabile passato in input non deve essere null.
     * @post
     *      L'istanza di una specializzazione di TabArchivio è creata con la lista specificata.   
     */
    public TabArchivioLibri(FilteredList<Libro> listaOsservabileLibri) {
        super(listaOsservabileLibri);
        
        btnCancella = new Button("Rimuovi Libri");
        getBtnAggiungi().setText("Inserisci Libro");
        getBtnModifica().setText("Modifica Dati Libro");
        
        TableColumn<Libro, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        
        TableColumn<Libro, String> titoloCol = new TableColumn<>("Titolo");
        titoloCol.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        
        TableColumn<Libro, String> autoriCol = new TableColumn<>("Autori");
        autoriCol.setCellValueFactory(new PropertyValueFactory<>("autori"));
        
        TableColumn<Libro, Integer> annoCol = new TableColumn<>("Anno di Pubblicazione");
        annoCol.setCellValueFactory(new PropertyValueFactory<>("annoDiPubblicazione"));
        
        TableColumn<Libro, Integer> copieCol = new TableColumn<>("Copie Disponibili");
        copieCol.setCellValueFactory(new PropertyValueFactory<>("numeroCopieDisponibili"));
        
        getTabella().getColumns().addAll(isbnCol, titoloCol, autoriCol, annoCol, copieCol);
        getTabella().setItems(listaOsservabileLibri);
        
        
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
        
        getTab().setText("Libri");
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
