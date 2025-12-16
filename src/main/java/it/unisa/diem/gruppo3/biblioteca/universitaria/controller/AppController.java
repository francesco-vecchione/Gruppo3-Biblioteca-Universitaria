package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelArchivio;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelPassword;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Password;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.CreazionePasswordDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ErroreAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.LoginDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ViewBiblioteca;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @file AppController.java
 * @author gruppo 3
 * @brief Astrae il concetto di controller del pattern MVC, e l'unico servizio
 * che offre in maniera pubblica è quello di essere istanziata. Ha la
 * responsabilità di passare i dati dalla view al model e viceversa
 */
public class AppController {

    /**
     * @brief Il model che astrae il concetto di archivio libri
     */
    private ModelArchivio<Libro> modelLibri;

    /**
     * @brief Il model che astrae il concetto di archivio utenti
     */
    private ModelArchivio<Utente> modelUtenti;

    /**
     * @brief Il model che astrae il concetto di archivio prestiti
     */
    private ModelArchivio<Prestito> modelPrestiti;

    /**
     * @brief Il model che astrae il contenitore della password
     */
    private ModelPassword modelPassword;

    /**
     * @brief La view che astrae la finestra principale della pagine dove sono
     * presenti le tab degli archivi dei libri, degli utenti e dei prestiti
     */
    private ViewBiblioteca viewBiblioteca;

    /**
     * @brief La view che astrae la finestra di log in visualizzata prima
     * dell'apertura della finestra principale
     */
    private LoginDialog stageLogIn;

    /**
     * @brief La view che astrae la finestra di sign up e la finestra di
     * recupero password; le due differenziano solo per il titolo della finestra
     * e un'etichetta descrittiva
     */
    private CreazionePasswordDialog stageCreazionePassword;

    /**
     * @brief Controller specifico per la gestione degli eventi legati ai libri
     */
    private ControllerDato controllerLibri;
    
    /**
     * @brief Controller specifico per la gestione degli eventi legati agli utenti
     */
    private ControllerDato controllerUtenti;
    
    /**
     * @brief Controller specifico per la gestione degli eventi legati ai prestiti
     */
    private ControllerDato controllerPrestiti;
    
    
    /**
     * @brief È il costruttore di AppController, che accetta una reference di
     * tipo Stage come parametro
     *
     * @param[in] Stage si occupa di istanziare gli attributi di AppController,
     * di inizializzare gli event handlers e i bindings
     */
    public AppController(Stage stage) {
        modelPassword = new Password("files/cassaforte");
        
        boolean accessoRiuscito = false;
        if (!modelPassword.esistePassword()) {
            accessoRiuscito = creazionePassword();
        } else {
            accessoRiuscito = login();
        }

        if (accessoRiuscito) {
            // E' inutile aprire gli archivi se non si fa l'accesso
            
            modelLibri = new ModelArchivio<>("files/archivioLibri");
            modelUtenti = new ModelArchivio<>("files/archivioUtenti");
            modelPrestiti = new ModelArchivio<>("files/archivioPrestiti");
            
            modelLibri.apriArchivio();
            modelUtenti.apriArchivio();
            modelPrestiti.apriArchivio();
            
            viewBiblioteca = new ViewBiblioteca(stage, modelLibri.getArchivioFiltrato(), modelUtenti.getArchivioFiltrato(), modelPrestiti.getArchivioFiltrato());

            controllerLibri = new ControllerLibri(modelLibri, modelPrestiti, viewBiblioteca);
            controllerUtenti = new ControllerUtenti(modelUtenti, modelPrestiti, viewBiblioteca);
            controllerPrestiti = new ControllerPrestiti(modelPrestiti, modelLibri, modelUtenti, viewBiblioteca);

            inizializzaEventHandlers();
            viewBiblioteca.getStage().show();
        }
    }

    /**
     * @brief Esegue le istruzioni necessarie ad implementare la finestra di
     * Registra Password
     */
    private boolean creazionePassword() {
        AtomicBoolean accessoRiuscito = new AtomicBoolean(false);
        CreazionePasswordDialog dialog = new CreazionePasswordDialog();
        Button btnOk = dialog.getBtnOk();
        Button btnChiudi = dialog.getBtnChiudi();

        btnOk.disableProperty().bind(dialog.getTxfPassword().textProperty().isEmpty()
                .or(dialog.getTxfConfermaPassword().textProperty().isEmpty()));

        //CHIUDI
        btnChiudi.addEventFilter(ActionEvent.ACTION, event -> Platform.exit());

        //OK
        btnOk.addEventFilter(ActionEvent.ACTION, event -> {
            String password = dialog.getTxfPassword().getText();
            String conferma = dialog.getTxfConfermaPassword().getText();

            if (!password.equals(conferma)) {
                new ErroreAlert("Le password non coincidono");
                event.consume();
                return;
            }

            if (!modelPassword.impostaPassword(password)) {
                new ErroreAlert("Errore nel salvataggio della password");
                event.consume();
                return;
            }
            accessoRiuscito.set(true);
        });

        dialog.getDialog().showAndWait();
        return accessoRiuscito.get();
    }

    /**
     * @brief Esegue le istruzioni necessarie ad implementare la finestra di
     * Login
     */
    private boolean login() {
        AtomicBoolean accessoRiuscito = new AtomicBoolean(false);
        LoginDialog dialog = new LoginDialog();
        Button btnOk = dialog.getBtnOk();
        Button btnChiudi = dialog.getBtnChiudi();

        btnOk.disableProperty().bind(dialog.getTxfPassword().textProperty().isEmpty());

        //CHIUDI
        btnChiudi.addEventFilter(ActionEvent.ACTION, event -> Platform.exit());

        //OK
        btnOk.addEventFilter(ActionEvent.ACTION, event -> {
            String password = dialog.getTxfPassword().getText();

            if (!modelPassword.verificaPassword(password)) {
                new ErroreAlert("La password non è esatta");
                event.consume();
                return;
            }

            dialog.getDialog().close();
            accessoRiuscito.set(true);
        });

        //Password Dimenticata
        dialog.getLinkPasswordDimenticata().addEventFilter(ActionEvent.ACTION, event -> {
            creazionePassword();
        });

        dialog.getDialog().showAndWait();
        return accessoRiuscito.get();
    }

    /**
     * @brief Inizializza gli event handlers
     */
    private void inizializzaEventHandlers() {

        // Sulla main view, una volta premuto la x, l'archivio viene salvato automaticamente
        viewBiblioteca.getStage().setOnCloseRequest(event -> {
            
            boolean chiusuraCorrettaLibri = modelLibri.chiudiArchivio();
            boolean chiusuraCorrettaUtenti = modelUtenti.chiudiArchivio();
            boolean chiusuraCorrettaPrestiti = modelPrestiti.chiudiArchivio();

            Alert chiusura = new Alert(Alert.AlertType.NONE);
            if (!chiusuraCorrettaLibri || !chiusuraCorrettaUtenti || !chiusuraCorrettaPrestiti) {
                // Nel caso vada storto qualcosa nella chiusura, lo si notifica all'utente prima di chiudere l'applicazione
                chiusura.setAlertType(Alert.AlertType.WARNING);
                chiusura.setTitle("Attenzione");
                chiusura.setHeaderText("Attenzione!");

                StringBuffer buff = new StringBuffer();
                buff.append("Salvataggio non riuscito per i seguenti archivi:\n");
                if (!chiusuraCorrettaLibri) {
                    buff.append("\tArchivio Libri\n");
                }
                if (!chiusuraCorrettaUtenti) {
                    buff.append("\tArchivio Utenti\n");
                }
                if (!chiusuraCorrettaPrestiti) {
                    buff.append("\tArchivio Prestiti\n");
                }
                buff.append("\nAl prossimo accesso i dati verranno aggiornati con quelli contenuti negli archivi cache.");

                chiusura.setContentText(buff.toString());
            } else {
                // Nel caso vada tutto bene, lo si notifica comunque all'utente
                chiusura.setAlertType(Alert.AlertType.INFORMATION);
                chiusura.setTitle("Informazione");
                chiusura.setHeaderText("Successo!");
                chiusura.setContentText("Archivi chiusi e salvati con successo");
            }
            chiusura.showAndWait();
        });

        controllerLibri.inizializzaEventHandlersSpecifici();
        controllerUtenti.inizializzaEventHandlersSpecifici();
        controllerPrestiti.inizializzaEventHandlersSpecifici();
    }

}