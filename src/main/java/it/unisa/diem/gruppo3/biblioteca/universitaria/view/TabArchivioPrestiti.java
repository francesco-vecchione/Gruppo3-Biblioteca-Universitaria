package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import javafx.collections.transformation.FilteredList;

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
    }
}
