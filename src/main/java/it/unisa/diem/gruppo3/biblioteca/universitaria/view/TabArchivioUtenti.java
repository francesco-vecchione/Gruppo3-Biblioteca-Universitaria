package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;

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
    private Button btnCancella;
    
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
    }
    
    /**
     * @brief Getter per il bottone per la cancellazione
     * @return Il bottone per la cancellazione
     */
    public Button getBtnCancella() {
        return btnCancella;
    }
}
