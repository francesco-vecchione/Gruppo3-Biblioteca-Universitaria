package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.StatoPrestito;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.effect.InnerShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.Cursor;

/**
 * @file TabArchivioPrestiti.java
 * @author gruppo 3
 * @brief Questa classe specializza TabArchivio per la gestione dell'archivio
 * dei prestiti
 */
public class TabArchivioPrestiti extends TabArchivio<Prestito> {

    /**
     * @brief Costruttore che non aggiunge nulla al layout costruito da
     * TabArchvio
     * @param[in] listaOsservabilePrestiti Lista visualizzata nella TableView
     * @pre listaOsservabilePrestiti passato in input non deve essere null.
     * @post L'istanza di una specializzazione di TabArchivio è creata con la
     * lista specificata.
     */
    public TabArchivioPrestiti(FilteredList<Prestito> listaOsservabilePrestiti) {
        super(listaOsservabilePrestiti);

        getBtnAggiungi().setText("Registra Prestito");
        getBtnModifica().setText("Registra Restituzione");

        TableColumn<Prestito, String> matricolaCol = new TableColumn<>("Matricola Utente");
        matricolaCol.setCellValueFactory(new PropertyValueFactory<>("matricolaUtente"));

        TableColumn<Prestito, String> isbnCol = new TableColumn<>("ISBN Libro");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbnPrestito"));

        TableColumn<Prestito, LocalDate> dataPrestitoCol = new TableColumn<>("Data Inizio Prestito");
        dataPrestitoCol.setCellValueFactory(new PropertyValueFactory<>("dataPrestito"));

        TableColumn<Prestito, LocalDate> dataRestituzioneCol = new TableColumn<>("Data di Restituzione Concordata");
        dataRestituzioneCol.setCellValueFactory(new PropertyValueFactory<>("dataRestituzione"));
        
        TableColumn<Prestito, LocalDate> statoPrestitoCol = new TableColumn<>("Stato del Prestito");
        statoPrestitoCol.setCellValueFactory(new PropertyValueFactory<>("statoPrestito"));
        
        getTabella().setRowFactory(tv -> new TableRow<>() {
            // In base alla documentazione updateItem viene chiamato quando:
            // cambia l'item;
            // cambia la selezione;
            // la tabella si aggiorna (con ad esempio refresh())
            @Override
            protected void updateItem(Prestito item, boolean empty) {
                super.updateItem(item, empty);
                aggiornaStile();
            }
            
            private void aggiornaStile() {
                setStyle("");
                
                Prestito item = getItem();
                // Se non c'è alcun elemento, i campi sono vuoti o è selezionato, lo stile non
                // deve essere aggiornato
                if(item == null || isEmpty() || isSelected()) return;
                
                // Se il prestito è stato restituito, allora colora la riga di un verde chiaro
                if(item.getStatoPrestito() == StatoPrestito.RESTITUITO) {
                    setStyle("-fx-background-color: #C8E6C9;");
                    return;
                }
                
                // Se il prestito esiste e non è già stato restituito, allora imposta il colore
                // in base ai giorni rimasti alla scadenza
                LocalDate dataRestituzione = item.getDataRestituzione();
                if (dataRestituzione != null) {
                    long giorniAllaScadenza = ChronoUnit.DAYS.between(LocalDate.now(), dataRestituzione);
                    
                    if (giorniAllaScadenza <= 0) {
                        setStyle("-fx-background-color: #F08080;");
                    } else if (giorniAllaScadenza <= 3) {
                        setStyle("-fx-background-color: #FFECB3;");
                    }
                }
            }
        });

        getTabella().getColumns().addAll(matricolaCol, isbnCol, dataPrestitoCol, dataRestituzioneCol, statoPrestitoCol);
        getTabella().setItems(listaOsservabilePrestiti);

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
        boxBottoni.getChildren().addAll(getBtnAggiungi(), getBtnModifica());

        BorderPane root = new BorderPane();
        root.setCenter(getBoxCentro());
        root.setRight(boxBottoni);

        getTab().setText("Prestiti");
        getTab().setContent(root);
        getTab().setClosable(false);
    }
}
