package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelBiblioteca;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelPassword;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Password;
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
     * @brief Il model che astrae le funzionalità necessarie alla gestione della biblioteca
     */
    private ModelBiblioteca modelBiblioteca;

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
        modelPassword = new Password("Files/cassaforte");
        
        boolean accessoRiuscito = false;
        if (!modelPassword.esistePassword()) {
            accessoRiuscito = creazionePassword();
        } else {
            accessoRiuscito = login();
        }

        if (accessoRiuscito) {
            modelBiblioteca = new ModelBiblioteca();
            
            viewBiblioteca = new ViewBiblioteca(stage, modelBiblioteca.getArchivioLibri(), modelBiblioteca.getArchivioUtenti(), modelBiblioteca.getArchivioPrestiti());

            controllerLibri = new ControllerLibri(modelBiblioteca, viewBiblioteca.getTabLibri());
            controllerUtenti = new ControllerUtenti(modelBiblioteca, viewBiblioteca.getTabUtenti());
            controllerPrestiti = new ControllerPrestiti(modelBiblioteca, viewBiblioteca.getTabPrestiti());

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
            
            boolean chiusuraCorrettaLibri = modelBiblioteca.chiudiModelLibri();
            boolean chiusuraCorrettaUtenti = modelBiblioteca.chiudiModelUtenti();
            boolean chiusuraCorrettaPrestiti = modelBiblioteca.chiudiModelPrestiti();

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