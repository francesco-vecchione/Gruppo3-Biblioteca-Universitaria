package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelArchivio;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.StatoPrestito;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ConfermaAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ErroreAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.PrestitiDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ViewBiblioteca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * 
 * @author gruppo 3
 */
public class ControllerPrestiti implements ControllerDato {
    
    /**
     * @brief Il model che astrae il concetto di archivio prestiti
     */
    private ModelArchivio<Prestito> modelPrestiti;
    
    /**
     * @brief Il model che astrae il concetto di archivio libri
     */
    private ModelArchivio<Libro> modelLibri;

    /**
     * @brief Il model che astrae il concetto di archivio utenti
     */
    private ModelArchivio<Utente> modelUtenti;
    
    /**
     * @brief La view che astrae la finestra principale della pagine dove sono
     * presenti le tab degli archivi dei libri, degli utenti e dei prestiti
     */
    private ViewBiblioteca viewBiblioteca;
    
    /**
     * @brief Costruttore che imposta le reference ai valori passati per parametro
     * @param[in] modelPrestiti     Il modello di archivio dei prestiti a cui si rifà l'applicazione
     * @param[in] modelLibri        Il modello di archivio dei libri a cui si rifà l'applicazione
     * @param[in] modelUtenti       Il modello di archivio degli utenti a cui si rifa l'applicazione
     * @param[in] viewBiblioteca    La vista principale dell'applicazione
     */
    public ControllerPrestiti(ModelArchivio<Prestito> modelPrestiti, ModelArchivio<Libro> modelLibri, ModelArchivio<Utente> modelUtenti, ViewBiblioteca viewBiblioteca) {
        this.modelPrestiti = modelPrestiti;
        this.modelLibri = modelLibri;
        this.modelUtenti = modelUtenti;
        this.viewBiblioteca = viewBiblioteca;
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
                
                // Aggiorna copie disponibili
                Libro elem = libroSelezionato;
                elem.prestaCopia();
                modelLibri.modificaElemento(libroSelezionato, elem);
            });

            dialog.getDialog().showAndWait();
        });
    }

    /**
     * @brief Inizializza gli event handlers per la registrazione di una restituzione di un prestito
     */
    private void EventHandlersRegistraRestituzione() {

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
                
                // Aggiorna stato prestito
                Prestito elemPrestito = target;
                elemPrestito.registraRestituzione();
                modelPrestiti.modificaElemento(target, elemPrestito);
                
                // Aggiorna numero di copie libro
                Libro libroInPrestito = modelLibri.ricercaElemento(new Libro("", "", 0, elemPrestito.getIsbnPrestito(), 0));
                Libro elemLibro = libroInPrestito;
                elemLibro.restituisciCopia();
                modelLibri.modificaElemento(libroInPrestito, elemLibro);
            }
        });
    }

    /**
     * @brief Il metodo si occupa di inizializzare gli event handler per la ricerca costituita da
     * filtri sulla vista della tabella
     */  
    private void EventHandlersFiltri() {
        //Applica filtri
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
        viewBiblioteca.getTabPrestiti().getBtnEliminaFiltri().setOnAction(event -> {
            viewBiblioteca.getTabPrestiti().getTxfFiltroRicerca().clear();
            viewBiblioteca.getTabPrestiti().getTabella().setItems(modelPrestiti.getArchivioFiltrato());
        });
    }
}
