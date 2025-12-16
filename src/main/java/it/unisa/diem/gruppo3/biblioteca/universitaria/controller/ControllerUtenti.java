package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelArchivio;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ConfermaAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ErroreAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.UtentiDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ViewBiblioteca;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * @brief
 * @author gruppo 3
 */
public class ControllerUtenti implements ControllerDato {
    /**
     * @brief Il model che astrae il concetto di archivio utenti
     */
    private ModelArchivio<Utente> modelUtenti;
    
    /**
     * @brief Il model che astrae il concetto di archivio prestiti
     */
    private ModelArchivio<Prestito> modelPrestiti;
    
    /**
     * @brief La view che astrae la finestra principale della pagine dove sono
     * presenti le tab degli archivi dei libri, degli utenti e dei prestiti
     */
    private ViewBiblioteca viewBiblioteca;
    
    
    /**
     * @brief Costruttore che imposta le reference ai valori passati per parametro
     * @param[in] modelUtenti       Il modello di archivio degli utenti a cui si rifà l'applicazione
     * @param[in] modelPrestiti     Il modello di archivio dei prestiti a cui si rifà l'applicazione
     * @param[in] viewBiblioteca    La vista principale dell'applicazione
     * @param[in] popUpDialog       La finestra di pop up legata alla tab dei libri
     */
    public ControllerUtenti(ModelArchivio<Utente> modelUtenti, ModelArchivio<Prestito> modelPrestiti, ViewBiblioteca viewBiblioteca) {
        this.modelUtenti = modelUtenti;
        this.modelPrestiti = modelPrestiti;
        this.viewBiblioteca = viewBiblioteca;
    }
    
    
     /**
     * @brief Contratto ereditato dall'interfaccia ControllerDato - Inizializza gli event handlers che riguardano gli utenti
     */    
    @Override 
    public void inizializzaEventHandlersSpecifici() {
        EventHandlersAggiungiUtente();
        EventHandlersModificaUtente();
        EventHandlersCancellaUtente();
        EventHandlersFiltri();
    }

    /**
     * @brief Inizializzaza gli event handlers per l'aggiunta di un utente
     */    
    private void EventHandlersAggiungiUtente() {
        
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
    }
        
    /**
     * @brief Inizializza gli event handlers per la modifica di un utente selezionato dalla tabella
     */    
    private void EventHandlersModificaUtente() {
        
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
    }
 
    /**
     * @brief Inizializzaza gli event handlers che si occupano della cancellazione di un utente
     */    
    private void EventHandlersCancellaUtente() {

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
                viewBiblioteca.getTabUtenti().getTabella().refresh();
            }
        });
    }
        
    /**
     * @brief Il metodo si occupa di inizializzare gli event handler per la ricerca costituita da
     * filtri sulla vista della tabella
     */    
    private void EventHandlersFiltri() {
        //Applica filtri
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
}
