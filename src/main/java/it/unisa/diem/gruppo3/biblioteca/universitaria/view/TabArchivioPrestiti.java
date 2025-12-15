package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import java.time.LocalDate;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author gruppo 3
 * @brief Questa classe specializza TabArchivio per la gestione dell'archivio dei
 * prestiti
 */
public class TabArchivioPrestiti extends TabArchivio<Prestito> { 
    /**
     * @brief Costruttore che non aggiunge nulla al layout costruito da TabArchvio
     * @param[in] listaOsservabilePrestiti     Lista visualizzata nella TableView
     * @pre
     *      listaOsservabilePrestiti passato in input non deve essere null.
     * @post
     *      L'istanza di una specializzazione di TabArchivio è creata con la lista specificata.   
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
        dataRestituzioneCol.setCellValueFactory(new PropertyValueFactory<>("dataRestituziona"));
        
        getTabella().getColumns().addAll(matricolaCol, isbnCol, dataPrestitoCol, dataRestituzioneCol);
        getTabella().setItems(listaOsservabilePrestiti);
        
        VBox boxBottoni = new VBox();
        boxBottoni.getChildren().addAll(getBtnAggiungi(), getBtnModifica());
        
        BorderPane root = new BorderPane();
        root.setCenter(getBoxCentro());
        root.setRight(boxBottoni);
        
        getTab().setText("Prestiti");
        getTab().setContent(root);
        getTab().setClosable(false);
    }
}
