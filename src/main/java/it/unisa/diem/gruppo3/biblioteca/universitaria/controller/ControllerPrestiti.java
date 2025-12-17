package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelBiblioteca;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ConfermaAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ErroreAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.PrestitiDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.TabArchivioPrestiti;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * @file
 * @author gruppo 3
 * @brief Classe che si occupa dell'inizializzazione degli event handlers per i prestiti
 */
public class ControllerPrestiti implements ControllerDato {
    /**
     * @brief Il model che astrae le funzionalità necessarie alla gestione della biblioteca
     */
    private ModelBiblioteca modelBiblioteca;
    
    /**
     * @brief La view che astrae la tab per la gestione dei prestiti
     */
    private TabArchivioPrestiti tabArchivioPrestiti;
    
    /**
     * @brief Costruttore che imposta le reference ai valori passati per parametro
     * @param[in] modelBiblioteca           Il modello di biblioteca accessibile al controller
     * @param[in] tabArchivioPrestiti       La vista principale sulla tab della gestione dei prestiti
     */
    public ControllerPrestiti(ModelBiblioteca modelBiblioteca, TabArchivioPrestiti tabArchivioPrestiti) {
        this.modelBiblioteca = modelBiblioteca;
        this.tabArchivioPrestiti = tabArchivioPrestiti;
    }     
    
    /**
     * @brief Contratto ereditato dall'interfaccia ControllerDato - Inizializza gli event handlers che riguardano i prestiti
     */    
    @Override 
    public void inizializzaEventHandlersSpecifici() {
        EventHandlersRegistraPrestito();
        EventHandlersRegistraRestituzione();
        EventHandlersFiltri();
    }

    /**
     * @brief Inizializzaza gli event handlers per la registrazione di un prestito
     */
    private void EventHandlersRegistraPrestito() {

        tabArchivioPrestiti.getBtnAggiungi().setOnAction(event -> {
            FilteredList<Libro> libriPrestabili = modelBiblioteca.getLibriPrestabili();
            FilteredList<Utente> utentiPrestabili = modelBiblioteca.getUtentiPrestabili();

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
                LocalDate dataRestituzione = dialog.getDatePicker().getValue();

                if (libroSelezionato == null || utenteSelezionato == null || dataRestituzione == null) {
                    new ErroreAlert("Devi selezionare Libro, Utente e Data");
                    eventOk.consume();
                    return;
                }
                
                if (!modelBiblioteca.registraPrestito(utenteSelezionato, libroSelezionato, dataRestituzione)) {
                    if (new Prestito(null, null, null, dataRestituzione).isValid()) {
                        new ErroreAlert("Data restituzione invalida, scegliere una data successiva a quella odierna");
                    } else {
                        new ErroreAlert("L'utente non può richiedere lo stesso libro mentre già lo possiede");
                    }
                    eventOk.consume();
                    return;
                }
                tabArchivioPrestiti.getTabella().refresh();
            });

            dialog.getDialog().showAndWait();
        });
    }

    /**
     * @brief Inizializza gli event handlers per la registrazione di una restituzione di un prestito
     */
    private void EventHandlersRegistraRestituzione() {

        tabArchivioPrestiti.getBtnModifica().setOnAction(event -> {
            Prestito target = tabArchivioPrestiti.getSelectedItem();

            if (target == null) {
                new ErroreAlert("Devi prima selezionare un Prestito");
                return;
            }

            // Conferma
            ConfermaAlert alert = new ConfermaAlert("Vuoi Registrare la Restituzione del Prestito?");

            // Attendi la risposta dell'utente
            if (alert.getEsito()) { // getEsito() deve restituire true se confermato
                modelBiblioteca.registraRestituzione(target);
                tabArchivioPrestiti.getTabella().refresh();
            }
        });
    }

    /**
     * @brief Il metodo si occupa di inizializzare gli event handler per la ricerca costituita da
     * filtri sulla vista della tabella
     */  
    private void EventHandlersFiltri() {
        //Applica filtri
        tabArchivioPrestiti.getBtnCerca().setOnAction(event -> {
            String testoInserito = tabArchivioPrestiti.getTxfFiltroRicerca().getText();
            String[] filtri = testoInserito.split(" ");
            List<Predicate<Prestito>> filtriPredicate = new ArrayList<>();
            for (String filtro : filtri) {
                filtriPredicate.add(prestito -> prestito.toString().toLowerCase().contains(filtro.toLowerCase()));
            }
            FilteredList<Prestito> cercati = modelBiblioteca.getArchivioPrestiti().filtered(
                    filtriPredicate.stream()
                            .reduce(Predicate::and)
                            .orElse(x -> true) //se non ci sonon filtri resituisce tutto
            );
            tabArchivioPrestiti.getTabella().setItems(cercati);
        });

        //Elimina Filtri
        tabArchivioPrestiti.getBtnEliminaFiltri().setOnAction(event -> {
            tabArchivioPrestiti.getTxfFiltroRicerca().clear();
            tabArchivioPrestiti.getTabella().setItems(modelBiblioteca.getArchivioPrestiti());
        });
    }
}
