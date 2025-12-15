package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelArchivio;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelPassword;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.CreazionePasswordDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.LibriDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.LoginDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.UtentiDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ViewBiblioteca;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 * @author gruppo 3
 */


/**
    * @brief Astrae il concetto di controller del pattern MVC, e l'unico servizio che offre in maniera
    * pubblica è quello di essere istanziata. Ha la responsabilità di passare i dati dalla view al model e viceversa
*/
public class AppController {
     
    /**
    * @brief Il model che astrae il concetto di archivio libri
    */
    private /*final*/ ModelArchivio<Libro> modelLibri;
    
    /**
    * @brief Il model che astrae il concetto di archivio utenti
    */
    private /*final*/ ModelArchivio<Utente> modelUtenti;
    
    /**
    * @brief Il model che astrae il concetto di archivio prestiti
    */
    private /*final*/ ModelArchivio<Prestito> modelPrestiti;
    
    /**
    * @brief Il model che astrae il contenitore della password
    */
    private /*final*/ ModelPassword modelPassword;
    
    /**
    * @brief La view che astrae la finestra principale della pagine dove sono presenti le tab
    * degli archivi dei libri, degli utenti e dei prestiti
    */
    private /*final*/ ViewBiblioteca viewBiblioteca;
    /**
    * @brief La view che astrae la finestra di pop up generica generabile dal premere uno dei
    * bottoni della tab dei libri
    */ 
    private /*final*/ LibriDialog popUpLibri;
    /**
    * @brief La view che astrae la finestra di pop up generica generabile dal premere uno dei
    * bottoni della tab degli utenti
    */ 
    private /*final*/ UtentiDialog popUpUtenti;
    /**
    * @brief La view che astrae la finestra di pop up generica generabile dal premere uno dei
    * bottoni della tab dei prestiti
    */ 
    private /*final*/ UtentiDialog popUpPrestiti;
    /**
    * @brief La view che astrae la finestra di log in visualizzata prima dell'apertura della
    * finestra principale
    */ 
    private /*final*/ LoginDialog stageLogIn;
    /**
    * @brief La view che astrae la finestra di sign up e la finestra di recupero password; le
    * due differenziano solo per il titolo della finestra e un'etichetta descrittiva
    */
    private /*final*/ CreazionePasswordDialog stageCreazionePassword;
    
    /**
    * @brief È il costruttore di AppController, che accetta una reference di tipo Stage come parametro
    * 
    * @param[in] Stage si occupa di istanziare gli attributi di AppController, di inizializzare
    *                  gli event handlers e i bindings
    */
    public AppController(Stage stage) {
        modelLibri = new ModelArchivio<>("libri");
        modelUtenti = new ModelArchivio<>("utenti");
        modelPrestiti = new ModelArchivio<>("prestiti");
        
        modelLibri.apriArchivio();
        modelUtenti.apriArchivio();
        modelPrestiti.apriArchivio();
        
        viewBiblioteca = new ViewBiblioteca(stage, modelLibri.getArchivioFiltrato(), modelUtenti.getArchivioFiltrato(), modelPrestiti.getArchivioFiltrato());
        
        inizializzaEventHandlers();
    }
    
    /**
    * @brief Inizializza gli event handlers
    */
    private void inizializzaEventHandlers() {
        inizializzaEventHandlersLibri();
        inizializzaEventHandlersPrestiti();
        inizializzaEventHandlersUtenti();
    }
    
    /**
    * @brief Inizializza gli event handlers che riguardano Libri
    */
    private void inizializzaEventHandlersLibri() {
        viewBiblioteca.getTabLibri().getBtnAggiungi().setOnAction(event -> new LibriDialog());
        viewBiblioteca.getTabLibri().getBtnModifica().setOnAction(event -> new LibriDialog());  //a cui passo l'oggetto
        viewBiblioteca.getTabLibri().getBtnCancella().setOnAction(event -> new LibriDialog());  //a cui passo l'oggetto
        viewBiblioteca.getTabLibri().getBtnCerca().setOnAction(event -> {
            
        });
        viewBiblioteca.getTabLibri().getBtnEliminaFiltri().setOnAction(event -> {
            viewBiblioteca.getTabLibri().getTxfFiltroRicerca().clear();
            //ripristino della tabella
        });
    }
    
    /**
    * @brief Inizializza gli event handlers che riguardano Utenti
    */
    private void inizializzaEventHandlersUtenti() {
        viewBiblioteca.getTabUtenti().getBtnAggiungi().setOnAction(event -> new UtentiDialog());
        viewBiblioteca.getTabUtenti().getBtnModifica().setOnAction(event -> new UtentiDialog());  //a cui passo l'oggetto
        viewBiblioteca.getTabUtenti().getBtnCancella().setOnAction(event -> new UtentiDialog());  //a cui passo l'oggetto
        viewBiblioteca.getTabUtenti().getBtnCerca().setOnAction(event -> {
            
        });
        viewBiblioteca.getTabUtenti().getBtnEliminaFiltri().setOnAction(event -> {
            viewBiblioteca.getTabUtenti().getTxfFiltroRicerca().clear();
            //ripristino della tabella
        });
    }
    
    /**
    * @brief Inizializza gli event handlers che riguardano Prestiti
    */
    private void inizializzaEventHandlersPrestiti() {
        //viewBiblioteca.getTabPrestiti().getBtnAggiungi().setOnAction(event -> new PrestitiDialog());
        //viewBiblioteca.getTabPrestiti().getBtnModifica().setOnAction(event -> new PrestitiDialog());  //a cui passo l'oggetto
        viewBiblioteca.getTabPrestiti().getBtnCerca().setOnAction(event -> {
            
        });
        viewBiblioteca.getTabPrestiti().getBtnEliminaFiltri().setOnAction(event -> {
            viewBiblioteca.getTabPrestiti().getTxfFiltroRicerca().clear();
            //ripristino della tabella
        });
    }
    
    /**
    * @brief Inizializza gli event handlers che riguardano Passwrod
    */
    private void inizializzaEventHandlersPassword() {
        
    }
    
    /**
    * @brief Inizializza i bindings
    */
    private void inizializzaBindings() {
        
    }
}
