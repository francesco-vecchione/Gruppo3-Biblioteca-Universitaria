package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelArchivio;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelPassword;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Password;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.StatoPrestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ConfermaAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.CreazionePasswordDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ErroreAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.LibriDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.LoginDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.PrestitiDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.UtentiDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ViewBiblioteca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
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
    private /*final*/ ModelArchivio<Utente> modelUtenti;

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
     * @brief La view che astrae la finestra di pop up generica generabile dal
     * premere uno dei bottoni della tab dei libri
     */
    private LibriDialog popUpLibri;

    /**
     * @brief La view che astrae la finestra di pop up generica generabile dal
     * premere uno dei bottoni della tab degli utenti
     */
    private UtentiDialog popUpUtenti;

    /**
     * @brief La view che astrae la finestra di pop up generica generabile dal
     * premere uno dei bottoni della tab dei prestiti
     */
    private UtentiDialog popUpPrestiti;

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
     * @brief È il costruttore di AppController, che accetta una reference di
     * tipo Stage come parametro
     *
     * @param[in] Stage si occupa di istanziare gli attributi di AppController,
     * di inizializzare gli event handlers e i bindings
     */
    public AppController(Stage stage) {
        modelPassword = new Password("cassaforte");

        boolean accessoRiuscito = false;
        if (!modelPassword.esistePassword()) {
            accessoRiuscito = creazionePassword();
        } else {
            accessoRiuscito = login();
        }

        if (accessoRiuscito) {
            modelLibri = new ModelArchivio<>("archivioLibri");
            modelUtenti = new ModelArchivio<>("archivioUtenti");
            modelPrestiti = new ModelArchivio<>("archivioPrestiti");

            modelLibri.apriArchivio();
            modelUtenti.apriArchivio();
            modelPrestiti.apriArchivio();

            viewBiblioteca = new ViewBiblioteca(stage, modelLibri.getArchivioFiltrato(), modelUtenti.getArchivioFiltrato(), modelPrestiti.getArchivioFiltrato());
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

        inizializzaEventHandlersPrestiti();
        inizializzaEventHandlersLibri();
        inizializzaEventHandlersUtenti();
    }

    /**
     * @brief Inizializza gli event handlers che riguardano Libri
     */
    private void inizializzaEventHandlersLibri() {
        //Aggiunta Libro
        viewBiblioteca.getTabLibri().getBtnAggiungi().setOnAction(event -> {
            LibriDialog dialog = new LibriDialog();

            //Tasto OK : bindings
            Button btnOk = dialog.getBtnOk();
            btnOk.disableProperty().bind(
                    dialog.getTxfIsbn().textProperty().isEmpty()
                            .or(dialog.getTxfTitolo().textProperty().isEmpty())
                            .or(dialog.getTxfAutori().textProperty().isEmpty())
                            .or(dialog.getTxfAnnoPubblicazione().textProperty().isEmpty())
                            .or(dialog.getTxfNumCopie().textProperty().isEmpty()));

            btnOk.addEventFilter(ActionEvent.ACTION, eventOk -> {
                //Raccolta Elementi per Inserimento
                try {
                    Integer.parseInt(dialog.getTxfAnnoPubblicazione().getText());
                    Integer.parseInt(dialog.getTxfNumCopie().getText());
                } catch (NumberFormatException e) {
                    new ErroreAlert("Il formato dei dati che hai inserito non è corretto");
                    eventOk.consume();
                    return;
                }
                Libro nuovoLibro = new Libro(dialog.getTxfTitolo().getText(), dialog.getTxfAutori().getText(), Integer.parseInt(dialog.getTxfAnnoPubblicazione().getText()), dialog.getTxfIsbn().getText(), Integer.parseInt(dialog.getTxfNumCopie().getText()));
                if (!modelLibri.aggiungiElemento(nuovoLibro)) {
                    if (!nuovoLibro.isValid()) {
                        new ErroreAlert("Il formato dell'ISBN non è corretto");
                    } else {
                        new ErroreAlert("Il libro è già presente in archivio");
                    }
                    eventOk.consume();
                    return;
                }
            });

            dialog.getDialog().showAndWait();
        });

        //Modifica Libro
        viewBiblioteca.getTabLibri().getBtnModifica().setOnAction(event -> {
            Libro target = viewBiblioteca.getTabLibri().getSelectedItem();
            if (target == null) {
                new ErroreAlert("Devi prima selezionare un Libro");
                return;
            }
            LibriDialog dialog = new LibriDialog(target);

            //Tasto OK : bindings
            Button btnOk = dialog.getBtnOk();
            btnOk.disableProperty().bind(
                    dialog.getTxfIsbn().textProperty().isEmpty()
                            .or(dialog.getTxfTitolo().textProperty().isEmpty())
                            .or(dialog.getTxfAutori().textProperty().isEmpty())
                            .or(dialog.getTxfAnnoPubblicazione().textProperty().isEmpty())
                            .or(dialog.getTxfNumCopie().textProperty().isEmpty()));

            btnOk.addEventFilter(ActionEvent.ACTION, eventOk -> {
                //Raccolta Dati per la Modifica
                try {
                    Integer.parseInt(dialog.getTxfAnnoPubblicazione().getText());
                    Integer.parseInt(dialog.getTxfNumCopie().getText());
                } catch (NumberFormatException e) {
                    new ErroreAlert("Il formato dei dati che hai inserito non è corretto");
                    eventOk.consume();
                    return;
                }
                Libro nuovoLibro = new Libro(dialog.getTxfTitolo().getText(), dialog.getTxfAutori().getText(), Integer.parseInt(dialog.getTxfAnnoPubblicazione().getText()), dialog.getTxfIsbn().getText(), Integer.parseInt(dialog.getTxfNumCopie().getText()));
                if (!modelLibri.modificaElemento(target, nuovoLibro)) {
                    if (!nuovoLibro.isValid()) {
                        new ErroreAlert("Il formato dell'ISBN non è corretto");
                    } else {
                        new ErroreAlert("Il libro è già presente in archivio");
                    }
                    eventOk.consume();
                    return;
                }
            });
            dialog.getDialog().showAndWait();
        });

        //Cancellazione
        viewBiblioteca.getTabLibri().getBtnCancella().setOnAction(event -> {
            Libro target = viewBiblioteca.getTabLibri().getSelectedItem();
            if (target == null) {
                new ErroreAlert("Devi prima selezionare un Libro");
            }

            //Conferma
            ConfermaAlert alert = new ConfermaAlert("Vuoi davvero eliminare " + target.toString());
            if (alert.getEsito()) {
                if(modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getIsbnPrestito().equals(target.getIsbn())).size() > 0) {
                    target.azzeraCopie();
                    new ErroreAlert("Ho cancellato le copie disponibili in quanto una copia è ancora in presito");
                }
                else {
                    modelLibri.rimuoviElemento(target);
                }
            }
        });

        //Ricerca
        viewBiblioteca.getTabPrestiti().getBtnCerca().setOnAction(event -> {
            String testoInserito = viewBiblioteca.getTabPrestiti().getTxfFiltroRicerca().getText();
            String[] filtri = testoInserito.split(" ");
            List<Predicate<Prestito>> filtriPredicate = new ArrayList<>();
            for (String filtro : filtri) {
                filtriPredicate.add(prestito -> prestito.toString().toLowerCase().contains(filtro.toLowerCase()));
            }
            FilteredList<Prestito> cercati = modelPrestiti.getArchivioFiltrato().filtered(
                    filtriPredicate.stream()
                            .reduce(Predicate::and)
                            .orElse(x -> true) //se non ci sonon filtri resituisce tutto
            );
            viewBiblioteca.getTabPrestiti().getTabella().setItems(cercati);
        });

        //Elimina Filtri
        viewBiblioteca.getTabLibri().getBtnEliminaFiltri().setOnAction(event -> {
            viewBiblioteca.getTabLibri().getTxfFiltroRicerca().clear();
            viewBiblioteca.getTabLibri().getTabella().setItems(modelLibri.getArchivioFiltrato());
        });
    }

    /**
     * @brief Inizializza gli event handlers che riguardano Utenti
     */
    private void inizializzaEventHandlersUtenti() {
        //Aggiunta Utente
        viewBiblioteca.getTabUtenti().getBtnAggiungi().setOnAction(event -> {
            UtentiDialog dialog = new UtentiDialog();

            //Tasto OK : bindings
            Button btnOk = dialog.getBtnOk();
            btnOk.disableProperty().bind(
                    dialog.getTxfMatricola().textProperty().isEmpty()
                            .or(dialog.getTxfNome().textProperty().isEmpty())
                            .or(dialog.getTxfCognome().textProperty().isEmpty())
                            .or(dialog.getTxfEmail().textProperty().isEmpty()));

            btnOk.addEventFilter(ActionEvent.ACTION, eventOk -> {
                Utente nuovoUtente = new Utente(dialog.getTxfNome().getText(), dialog.getTxfCognome().getText(), dialog.getTxfMatricola().getText(), dialog.getTxfEmail().getText());
                if (!modelUtenti.aggiungiElemento(nuovoUtente)) {
                    if (!nuovoUtente.isValid()) {
                        new ErroreAlert("Il formato della Matricola o dell'Email non è corretto");
                    } else {
                        new ErroreAlert("Utente già presente in archivio");
                    }
                    eventOk.consume();
                    return;
                }
            });

            dialog.getDialog().showAndWait();
        });

        //Modifica Utente
        viewBiblioteca.getTabUtenti().getBtnModifica().setOnAction(event -> {
            Utente target = viewBiblioteca.getTabUtenti().getSelectedItem();
            if (target == null) {
                new ErroreAlert("Devi prima selezionare un Utente");
                return;
            }
            UtentiDialog dialog = new UtentiDialog(target);

            //Tasto OK : bindings
            Button btnOk = dialog.getBtnOk();
            btnOk.disableProperty().bind(
                    dialog.getTxfMatricola().textProperty().isEmpty()
                            .or(dialog.getTxfNome().textProperty().isEmpty())
                            .or(dialog.getTxfCognome().textProperty().isEmpty())
                            .or(dialog.getTxfEmail().textProperty().isEmpty()));

            btnOk.addEventFilter(ActionEvent.ACTION, eventOk -> {
                //Raccolta Dati per la Modifica
                Utente nuovoUtente = new Utente(dialog.getTxfNome().getText(), dialog.getTxfCognome().getText(), dialog.getTxfMatricola().getText(), dialog.getTxfEmail().getText());
                if (!modelUtenti.modificaElemento(target, nuovoUtente)) {
                    if (!nuovoUtente.isValid()) {
                        new ErroreAlert("Il formato della Matricola o dell'Email non è corretto");
                    } else {
                        new ErroreAlert("Utente già presente in archivio");
                    }
                    eventOk.consume();
                    return;
                }
            });
            dialog.getDialog().showAndWait();
        });

        //Cancellazione
        viewBiblioteca.getTabUtenti().getBtnCancella().setOnAction(event -> {
            Utente target = viewBiblioteca.getTabUtenti().getSelectedItem();
            if (target == null) {
                new ErroreAlert("Devi prima selezionare un Utente");
                return;
            }

            //Conferma
            ConfermaAlert alert = new ConfermaAlert("Vuoi davvero eliminare " + target.toString());
            if (alert.getEsito()) {
                if(modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getMatricolaUtente().equals(target.getMatricola())).size() > 0) {
                    new ErroreAlert("Non è possibile eliminare un utente che ha un Prestito in atto");
                    return;
                }
                modelUtenti.rimuoviElemento(target);
            }
        });

        //Ricerca
        viewBiblioteca.getTabUtenti().getBtnCerca().setOnAction(event -> {
            String testoInserito = viewBiblioteca.getTabUtenti().getTxfFiltroRicerca().getText();
            String[] filtri = testoInserito.split(" ");
            List<Predicate<Utente>> filtriPredicate = new ArrayList<>();
            for (String filtro : filtri) {
                filtriPredicate.add(utente -> utente.toString().toLowerCase().contains(filtro.toLowerCase()));
            }
            FilteredList<Utente> cercati = modelUtenti.getArchivioFiltrato().filtered(
                    filtriPredicate.stream()
                            .reduce(Predicate::and)
                            .orElse(x -> true) //se non ci sonon filtri resituisce tutto
            );
            viewBiblioteca.getTabUtenti().getTabella().setItems(cercati);
        });

        //Elimina Filtri
        viewBiblioteca.getTabUtenti().getBtnEliminaFiltri().setOnAction(event -> {
            viewBiblioteca.getTabUtenti().getTxfFiltroRicerca().clear();
            viewBiblioteca.getTabUtenti().getTabella().setItems(modelUtenti.getArchivioFiltrato());
        });
    }

    /**
     * @brief Inizializza gli event handlers che riguardano Prestiti
     */
    private void inizializzaEventHandlersPrestiti() {
        //Registrazione Prestito
        viewBiblioteca.getTabPrestiti().getBtnAggiungi().setOnAction(event -> {
            FilteredList<Libro> libriPrestabili = modelLibri.getArchivioFiltrato().filtered(libro->
                    libro.getNumeroCopieDisponibili() > 0);
            FilteredList<Utente> utentiPrestabili= modelUtenti.getArchivioFiltrato().filtered(utente->
                    modelPrestiti.getArchivioFiltrato().stream()
                            .filter(prestito
                                    -> prestito.getStatoPrestito().equals(StatoPrestito.ATTIVO)
                            && prestito.getMatricolaUtente().equals(utente.getMatricola())
                            )
                            .count() < 3
                    );

            PrestitiDialog dialog = new PrestitiDialog(libriPrestabili, utentiPrestabili);

            // Tasto OK : bindings
            Button btnOk = dialog.getBtnOk();
            btnOk.disableProperty().bind(
                    dialog.getCbLibri().valueProperty().isNull()
                            .or(dialog.getCbUtenti().valueProperty().isNull())
                            .or(dialog.getDatePicker().valueProperty().isNull())
            );

            btnOk.addEventFilter(ActionEvent.ACTION, eventOk -> {
                // Raccolta dati dalla dialog
                Libro libroSelezionato = dialog.getCbLibri().getValue();
                Utente utenteSelezionato = dialog.getCbUtenti().getValue();
                LocalDate dataPrestito = dialog.getDatePicker().getValue();

                if (libroSelezionato == null || utenteSelezionato == null || dataPrestito == null) {
                    new ErroreAlert("Devi selezionare Libro, Utente e Data");
                    eventOk.consume();
                    return;
                }

                Prestito nuovoPrestito = new Prestito(utenteSelezionato.getMatricola(), libroSelezionato.getIsbn(), LocalDate.now(), dataPrestito);

                if (!modelPrestiti.aggiungiElemento(nuovoPrestito)) {
                    if (!nuovoPrestito.isValid()) {
                        new ErroreAlert("Data restituzione invalida, scegliere una data successiva a quella odierna");
                    } else {
                        new ErroreAlert("L'utente non può richiedere lo stesso libro mentre già lo possiede");
                    }
                    eventOk.consume();
                    return;
                }
                
                libroSelezionato.prestaCopia();
            });

            dialog.getDialog().showAndWait();
        });

        // Registra Restituzione
        viewBiblioteca.getTabPrestiti().getBtnModifica().setOnAction(event -> {
            Prestito target = viewBiblioteca.getTabPrestiti().getSelectedItem();

            if (target == null) {
                new ErroreAlert("Devi prima selezionare un Prestito");
                return;
            }

            // Conferma
            ConfermaAlert alert = new ConfermaAlert("Vuoi Registrare la Restituzione del Prestito?");

            // Attendi la risposta dell'utente
            if (alert.getEsito()) { // getEsito() deve restituire true se confermato
                target.registraRestituzione();
                FilteredList<Libro> libroSelezionato = modelLibri.getArchivioFiltrato().filtered(libro -> libro.getIsbn().equals(target.getIsbnPrestito()));
                libroSelezionato.get(0).restituisciCopia();
            }
        });FilteredList<Libro> libriPrestabili = modelLibri.getArchivioFiltrato().filtered(libro->
                    libro.getNumeroCopieDisponibili() > 0);

        //Ricerca
        viewBiblioteca.getTabPrestiti().getBtnCerca().setOnAction(event -> {
            String testoInserito = viewBiblioteca.getTabLibri().getTxfFiltroRicerca().getText();
            String[] filtri = testoInserito.split(" ");
            List<Predicate<Libro>> filtriPredicate = new ArrayList<>();
            for (String filtro : filtri) {
                filtriPredicate.add(libro -> libro.toString().toLowerCase().contains(filtro.toLowerCase()));
            }
            FilteredList<Libro> cercati = modelLibri.getArchivioFiltrato().filtered(
                    filtriPredicate.stream()
                            .reduce(Predicate::and)
                            .orElse(x -> true) //se non ci sonon filtri resituisce tutto
            );
            viewBiblioteca.getTabLibri().getTabella().setItems(cercati);
        });

        //Elimina Filtri
        viewBiblioteca.getTabPrestiti().getBtnEliminaFiltri().setOnAction(event -> {
            viewBiblioteca.getTabPrestiti().getTxfFiltroRicerca().clear();
            viewBiblioteca.getTabPrestiti().getTabella().setItems(modelPrestiti.getArchivioFiltrato());
        });
    }
}
