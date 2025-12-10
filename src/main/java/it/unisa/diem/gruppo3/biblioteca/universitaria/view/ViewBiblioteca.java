package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.collections.transformation.FilteredList;
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
    private TabArchivio<Libro> tabLibri;
    
    /** 
     * @brief La tab della gestione dell'archivio degli utenti
     */
    private TabArchivio<Utente> tabUtenti;
    
    /**
     * @brief La tab della gestione dell'archivio dei prestiti
     */
    private TabArchivio<Prestito> tabPrestiti;
    
    /**
     * @brief Costruttore che istanzia gli attributi dell'oggetto ed imposta il 
     * layout generale della vista principale
     * @param[in] stage
     * @param[in] listaOsservabileLibri
     * @param[in] listaOsservabileUtenti
     * @param[in] listaOsservabilePrestiti 
     */
    public ViewBiblioteca(Stage stage, FilteredList<Libro> listaOsservabileLibri, FilteredList<Utente> listaOsservabileUtenti, FilteredList<Prestito> listaOsservabilePrestiti) {
        
    }
    
    /**
     * @brief Getter per lo stage della vista principale
     * @return Stage per la vista principale
     */
    public Stage getStage() {
        return null;
    }
    
    /**
     * @brief Getter per la classe tab per la gestione dei libri
     * @return Tab per la gestione dei libri
     */
    public TabArchivio<Libro> getTabLibri() {
        return null;
    }

    /**
     * @brief Getter per la classe tab per la gestione degli utenti
     * @return Tab per la gestione degli utenti
     */
    public TabArchivio<Utente> getTabUtenti() {
        return null;
    }

    /**
     * @brief Getter per la classe tab per la gestione dei prestiti
     * @return Tab per la gestione dei prestiti
     */    
    public TabArchivio<Prestito> getTabPrestiti() {
        return null;
    }
}
