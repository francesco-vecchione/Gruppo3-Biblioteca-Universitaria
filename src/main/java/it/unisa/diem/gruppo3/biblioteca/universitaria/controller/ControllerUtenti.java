package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelArchivio;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelBiblioteca;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ConfermaAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ErroreAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.TabArchivioUtenti;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.UtentiDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ViewBiblioteca;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * @file
 * @author gruppo 3
 * @brief Classe che si occupa dell'inizializzazione degli event handlers per gli utenti
 */
public class ControllerUtenti implements ControllerDato {
    /**
     * @brief Il model che astrae le funzionalità necessarie alla gestione della biblioteca
     */
    private ModelBiblioteca modelBiblioteca;
    
    /**
     * @brief La view che astrae la finestra principale della pagine dove sono
     * presenti le tab degli archivi dei libri, degli utenti e dei prestiti
     */
    private TabArchivioUtenti tabArchivioUtenti;
    
    
    /**
     * @brief Costruttore che imposta le reference ai valori passati per parametro
     * @param[in] modelUtenti       Il modello di archivio degli utenti a cui si rifà l'applicazione
     * @param[in] modelPrestiti     Il modello di archivio dei prestiti a cui si rifà l'applicazione
     * @param[in] viewBiblioteca    La vista principale dell'applicazione
     * @param[in] popUpDialog       La finestra di pop up legata alla tab dei libri
     */
    public ControllerUtenti(ModelBiblioteca modelBiblioteca, TabArchivioUtenti tabArchivioUtenti) {
        this.modelBiblioteca = modelBiblioteca;
        this.tabArchivioUtenti = tabArchivioUtenti;
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
        
        tabArchivioUtenti.getBtnAggiungi().setOnAction(event -> {
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
                if (!modelBiblioteca.aggiungUtente(nuovoUtente)) {
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
        
        tabArchivioUtenti.getBtnModifica().setOnAction(event -> {
            Utente target = tabArchivioUtenti.getSelectedItem();
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
                if (!modelBiblioteca.modificaUtente(target, nuovoUtente)) {
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

        tabArchivioUtenti.getBtnCancella().setOnAction(event -> {
            Utente target = tabArchivioUtenti.getSelectedItem();
            if (target == null) {
                new ErroreAlert("Devi prima selezionare un Utente");
                return;
            }

            //Conferma
            ConfermaAlert alert = new ConfermaAlert("Vuoi davvero eliminare " + target.toString());
            if (alert.getEsito()) {
                if(!modelBiblioteca.cancellaUtente(target)) {
                    new ErroreAlert("Non è possibile eliminare un utente che ha un Prestito in atto");
                    return;
                }
                tabArchivioUtenti.getTabella().refresh();
            }
        });
    }
        
    /**
     * @brief Il metodo si occupa di inizializzare gli event handler per la ricerca costituita da
     * filtri sulla vista della tabella
     */    
    private void EventHandlersFiltri() {
        //Applica filtri
        tabArchivioUtenti.getBtnCerca().setOnAction(event -> {
            String testoInserito = tabArchivioUtenti.getTxfFiltroRicerca().getText();
            String[] filtri = testoInserito.split(" ");
            List<Predicate<Utente>> filtriPredicate = new ArrayList<>();
            for (String filtro : filtri) {
                filtriPredicate.add(utente -> utente.toString().toLowerCase().contains(filtro.toLowerCase()));
            }
            FilteredList<Utente> cercati = modelBiblioteca.getArchivioUtenti().filtered(
                    filtriPredicate.stream()
                            .reduce(Predicate::and)
                            .orElse(x -> true) //se non ci sonon filtri resituisce tutto
            );
            tabArchivioUtenti.getTabella().setItems(cercati);
        });

        //Elimina Filtri
        tabArchivioUtenti.getBtnEliminaFiltri().setOnAction(event -> {
            tabArchivioUtenti.getTxfFiltroRicerca().clear();
            tabArchivioUtenti.getTabella().setItems(modelBiblioteca.getArchivioUtenti());
        });
    }
}
