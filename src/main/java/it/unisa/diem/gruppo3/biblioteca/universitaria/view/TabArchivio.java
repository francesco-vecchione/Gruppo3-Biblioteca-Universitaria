package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @file TabArchivio.java
 * @author gruppo 3
 * @brief Questa classe astrae la Tab che visualizza i diversi dati del programma
 */

public abstract class TabArchivio<T extends Dato> { 
    
    /**
 * @brief Tabella che visualizza i dati di tipo T
 */
    private final TableView<T> tabella;
    
    /**
 * @brief  Campo per l'inserimento dei filtri di ricerca
 */
    private final TextField txfFiltroRicerca;
    
        /**
 * @brief Bottone per aggiungere un Dato 
 */
    private final Button btnAggiungi;
    
        /**
 * @brief Bottone per modificare il Dato selezionato nella TableView
 */
    private final Button btnModifica;
    
        /**
 * @brief Bottone per cercare un Dato
 */
    private final Button btnCerca;
    
        /**
 * @brief Bottone per eliminare i filtri di ricerca e resettare la TableView di default
 */
    private final Button btnEliminaFiltri;
    
        /**
 * @brief Reference alla Tab
 */
    private final Tab tab;
    
        /**
 * @brief Reference al box centrale
 * 
 */
    private final VBox boxCentro;
    
    /**
     * @brief Costruttore che imposta la FilteredList visualizza nella TableView
     * @param[in] listaOsservabile Lista visualizzata nella TableView
     * @post
     * Costruttore che imposta il layout generico della tab istanziando le 
     * componenti
     */
    public TabArchivio(FilteredList<T> listaOsservabile) { 
        tabella = new TableView<>();
        btnAggiungi = new Button();
        btnModifica = new Button();
        btnCerca = new Button("Cerca");
        btnEliminaFiltri = new Button("Elimina Filtri");
        txfFiltroRicerca = new TextField();
        txfFiltroRicerca.setPromptText("Cerca");
        
        tab = new Tab();
        
        HBox boxCerca = new HBox();
        boxCerca.getChildren().addAll(txfFiltroRicerca, btnCerca, btnEliminaFiltri);
        
        boxCentro = new VBox();
        boxCentro.getChildren().addAll(boxCerca, tabella);
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
        return tabella.getSelectionModel().getSelectedItem();
    }
    
    /**
     * @brief restituisce il TextField che fa da filtro di ricerca
     * @param[in] 
     * @preturn l'oggetto txfFiltroRicerca
     */
    public TextField getTxfFiltroRicerca() {
        return txfFiltroRicerca;
    }
    
    /**
     * @brief restituisce la tabella contenente i dati visualizzati
     * @param[in] 
     * @preturn l'oggetto TableView<T>
     */
    public TableView<T> getTabella() {
        return tabella;
    }
    
    /**
     * @brief restituisce il box contenente la tabella e i componenti di ricerca
     * @param[in] 
     * @preturn l'oggetto VBox
     */
    public VBox getBoxCentro() {
        return boxCentro;
    }
}
