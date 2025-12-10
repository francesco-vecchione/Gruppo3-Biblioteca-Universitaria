package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

/**
 * @author gruppo 3
 * @brief Questa classe astrae la Tab che visualizza i diversi dati del programma
 * @invariant
 * Il tipo di operazione non deve essere null.
 */

public abstract class TabArchivio<T extends Dato> { 
    
    /**
 * @brief Tabella che visualizza i dati di tipo T
 */
    private TableView<T> tabella;
    
    /**
 * @brief  Etichetta che visualizza i filtri attivi
 */
    private Label lblFiltriAttivi;
    
        /**
 * @brief Bottone per aggiungere un Dato 
 */
    private Button btnAggiungi;
    
        /**
 * @brief Bottone per modificare il Dato selezionato nella TableView
 */
    private Button btnModifica;
    
        /**
 * @brief Bottone per cercare un Dato
 */
    private Button btnCerca;
    
        /**
 * @brief Bottone per eliminare i filtri di ricerca e resettare la TableView di default
 */
    private Button btnEliminaFiltri;
    
        /**
 * @brief Reference alla Tab
 */
    private Tab tab;
    
    /**
     * @brief Costruttore che imposta la FilteredList visualizza nella TableView
     * @param[in] listaOsservabile Lista visualizzata nella TableView
     * @pre
     * listaOsservabile passato in input non deve essere null.
     * @post
     * L'istanza di una specializzazione di TabArchivio è creata con la lista specificata.
     */
    public TabArchivio(FilteredList<T> listaOsservabile) { 
        
        
    }
/**
     * @brief imposta il testo visibile nella label lblFiltriAttivi
     * @param[in] testo Testo visualizzato in lblFiltriAttivi
     * @pre
     * testo non deve essere null
     * @post
     * lblFiltriAttivi mostra il testo specificato
     */
    
    public void setLblFiltriAttivi(String testo) {
        
    }
    
    /**
     * @brief restituisce la reference di btnAggiungi
     * @param[in] 
     * @preturn l'oggetto btnAggiungi
     */
    public Button getBtnAggiungi() {
        return btnAggiungi;
    }

    /**
     * @brief restituisce la reference di btnModifica
     * @param[in] 
     * @preturn l'oggetto btnModifica
     */
    public Button getBtnModifica() {
        return btnModifica;
    }

    /**
     * @brief restituisce la refernce di btnCerca
     * @param[in] 
     * @preturn l'oggetto btnCerca
     */
    public Button getBtnCerca() {
        return btnCerca;
    }

    /**
     * @brief restituisce la reference di btnEliminaFiltri
     * @param[in] 
     * @preturn l'oggetto btnEliminaFiltri
     */
    public Button getBtnEliminaFiltri() {
        return btnEliminaFiltri;
    }
    
    /**
     * @brief restituisce la reference di tab
     * @param[in] 
     * @preturn l'oggetto tab
     */
    public Tab getTab() {
        return tab;
    }
    
    /**
     * @brief restituisce la reference al dato selezionato di tipo T
     * @param[in] 
     * @preturn l'oggetto selezionato di tipo T
     */
    public T getSelectedItem() {
        return null;
    }
}
