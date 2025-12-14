package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;

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
    }
    
    /**
     * @brief Getter per il bottone per la cancellazione
     * @return Il bottone per la cancellazione
     */
    public Button getBtnCancella() {
        return btnCancella;
    }
}
