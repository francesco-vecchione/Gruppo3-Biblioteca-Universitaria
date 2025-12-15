package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author gruppo 3
 * @brief Questa classe atrae la vista principale dell'applicazione, contenente 
 * le tre tab per i corrispondenti archivi: libri utenti e prestiti
 */
public class ViewBiblioteca {
    /**
     * @brief Lo stage della vista principale
     */
    private Stage stage;
    
    /**
     * @brief La tab della gestione dell'archivio dei libri
     */
    private TabArchivioLibri tabLibri;
    
    /** 
     * @brief La tab della gestione dell'archivio degli utenti
     */
    private TabArchivioUtenti tabUtenti;
    
    /**
     * @brief La tab della gestione dell'archivio dei prestiti
     */
    private TabArchivioPrestiti tabPrestiti;
    
    /**
     * @brief Costruttore che istanzia gli attributi dell'oggetto ed imposta il 
     * layout generale della vista principale
     * @param[in] stage
     * @param[in] listaOsservabileLibri
     * @param[in] listaOsservabileUtenti
     * @param[in] listaOsservabilePrestiti 
     */
    public ViewBiblioteca(Stage stage, FilteredList<Libro> listaOsservabileLibri, FilteredList<Utente> listaOsservabileUtenti, FilteredList<Prestito> listaOsservabilePrestiti) {
        this.stage = stage;
        
        TabPane tabPane = new TabPane();
        tabPrestiti = new TabArchivioPrestiti(listaOsservabilePrestiti);
        tabUtenti = new TabArchivioUtenti(listaOsservabileUtenti);
        tabLibri = new TabArchivioLibri(listaOsservabileLibri);
        tabPane.getTabs().addAll(tabPrestiti.getTab(), tabUtenti.getTab(), tabLibri.getTab());
        
        VBox root = new VBox();
        root.getChildren().add(tabPane);
        
        Scene scene = new Scene(root, 900, 400);
        this.stage.setTitle("Gestione Biblioteca Universitaria.");
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    /**
     * @brief Getter per lo stage della vista principale
     * @return Stage per la vista principale
     */
    public Stage getStage() {
        return stage;
    }
    
    /**
     * @brief Getter per la classe tab per la gestione dei libri
     * @return Tab per la gestione dei libri
     */
    public TabArchivioLibri getTabLibri() {
        return tabLibri;
    }

    /**
     * @brief Getter per la classe tab per la gestione degli utenti
     * @return Tab per la gestione degli utenti
     */
    public TabArchivioUtenti getTabUtenti() {
        return tabUtenti;
    }

    /**
     * @brief Getter per la classe tab per la gestione dei prestiti
     * @return Tab per la gestione dei prestiti
     */    
    public TabArchivioPrestiti getTabPrestiti() {
        return tabPrestiti;
    }
}
