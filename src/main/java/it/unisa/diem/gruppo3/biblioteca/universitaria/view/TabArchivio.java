package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.scene.layout.Priority;
import javafx.scene.effect.SepiaTone;
import javafx.scene.Cursor;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TabPane;

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
        
        tabella.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
     
        //personalizzaione texfield
        txfFiltroRicerca.setPrefWidth(319.0);
        txfFiltroRicerca.setPrefHeight(36.0);
        txfFiltroRicerca.setStyle("-fx-background-radius: 20;");

        //resize della tableview
        tabella.setPrefWidth(451.0);
        tabella.setPrefHeight(315.0);
        // Permettiamo alla tabella di espandersi se la finestra viene ingrandita
        VBox.setVgrow(tabella, Priority.ALWAYS); 

        //font per i tasti e per la tab
        Font fontBottoni = Font.font("Kodchasan", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 17.0);
        
        SepiaTone seppia= new SepiaTone(0.4);
        
        //bottone cerca
        btnCerca.setPrefWidth(89.0);
        btnCerca.setPrefHeight(36.0);
        btnCerca.setStyle("-fx-background-radius: 40; -fx-background-color: #5AC3F2;");
        btnCerca.setFont(fontBottoni);
        btnCerca.setCursor(Cursor.HAND);
        btnCerca.setEffect(null);
        btnCerca.setOnMouseEntered(e -> btnCerca.setEffect(seppia));
        btnCerca.setOnMouseExited(e -> btnCerca.setEffect(null));

        //bottone eliminafiltri
        btnEliminaFiltri.setPrefWidth(145.0);
        btnEliminaFiltri.setPrefHeight(36.0);
        btnEliminaFiltri.setStyle("-fx-background-radius: 40; -fx-background-color: #F2885A;");
        btnEliminaFiltri.setFont(fontBottoni);
        btnEliminaFiltri.setCursor(Cursor.HAND);
        btnEliminaFiltri.setEffect(null);
        btnEliminaFiltri.setOnMouseEntered(e -> btnEliminaFiltri.setEffect(seppia));
        btnEliminaFiltri.setOnMouseExited(e -> btnEliminaFiltri.setEffect(null));

        //creazione hboxcerca
        HBox boxCerca = new HBox();
        boxCerca.setStyle("-fx-background-color: #FFFDF5;");
        boxCerca.setSpacing(13.0); 
        boxCerca.setAlignment(Pos.CENTER_LEFT); // Allinea tutto a sinistra
        boxCerca.getChildren().addAll(txfFiltroRicerca, btnCerca, btnEliminaFiltri);
    
        //creazione della vbox
        boxCentro = new VBox();
        boxCentro.setStyle("-fx-background-color: #FFFDF5;");
        boxCentro.setSpacing(7.0); 
        boxCentro.setPadding(new Insets(33, 0, 0, 8));
 
        boxCentro.getChildren().addAll(boxCerca, tabella);
        //assegnazione alla tab
        tab.setContent(boxCentro);
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
