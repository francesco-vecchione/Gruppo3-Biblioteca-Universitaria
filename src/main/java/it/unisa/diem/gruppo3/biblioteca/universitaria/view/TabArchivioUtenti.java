package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @file TabArchivioUtenti.java
 * @author gruppo 3
 * @brief Questa classe specializza TabArchivio per la gestione dell'archivio degli
 * utenti
 */
public class TabArchivioUtenti extends TabArchivio<Utente>{
    /**
     * @brief Bottone per la cancellazione di un elemento nella tabella
     */    
    private final Button btnCancella;
    
    /**
     * @brief Costruttore che aggiunge al layout standard di una tab generica il 
     * bottone di cancellazione
     * @param[in] listaOsservabileUtenti     Lista visualizzata nella TableView
     * @pre
     *      listaOsservabileUtenti passato in input non deve essere null.
     * @post
     *      L'istanza di una specializzazione di TabArchivio è creata con la lista specificata.   
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
