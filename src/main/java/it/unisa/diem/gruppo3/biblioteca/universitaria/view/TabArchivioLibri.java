package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author gruppo 3
 * @breaf Questa classe specializza TabArchivio per la gestione dell'archivio dei
 * libri
 */
public class TabArchivioLibri extends TabArchivio<Libro>{
    /**
     * @brief Bottone per la cancellazione di un elemento nella tabella
     */
    private Button btnCancella;
    
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
