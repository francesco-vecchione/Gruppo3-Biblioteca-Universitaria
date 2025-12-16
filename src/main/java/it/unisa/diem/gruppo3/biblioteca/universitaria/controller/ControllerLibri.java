package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import it.unisa.diem.gruppo3.biblioteca.universitaria.model.ModelBiblioteca;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ConfermaAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ErroreAlert;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.LibriDialog;
import it.unisa.diem.gruppo3.biblioteca.universitaria.view.TabArchivioLibri;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * @file
 * @author gruppo 3
 * @brief Classe che si occupa dell'inizializzazione degli event handlers per i libri
 */
public class ControllerLibri implements ControllerDato {
    /**
     * @brief Il model che astrae le funzionalità necessarie alla gestione della biblioteca
     */
    private ModelBiblioteca modelBiblioteca;
    
    /**
     * @brief La view che astrae la finestra principale della pagine dove sono
     * presenti le tab degli archivi dei libri, degli utenti e dei prestiti
     */
    private TabArchivioLibri tabArchivioLibri;
    
    /**
     * @brief Costruttore che imposta le reference ai valori passati per parametro
     * @param[in] modelLibri        Il modello di archivio dei libri a cui si rifà l'applicazione
     * @param[in] modelPrestiti     Il modello di archivio dei prestiti a cui si rifà l'applicazione
     * @param[in] viewBiblioteca    La vista principale dell'applicazione
     */
    public ControllerLibri(ModelBiblioteca modelBiblioteca, TabArchivioLibri tabArchivioLibri) {
        this.modelBiblioteca = modelBiblioteca;
        this.tabArchivioLibri = tabArchivioLibri;
    }
    
     /**
     * @brief Contratto ereditato dall'interfaccia ControllerDato - Inizializza gli event handlers che riguardano i libri
     */
    @Override
    public void inizializzaEventHandlersSpecifici() {
        EventHandlersAggiungiLibro();
        EventHandlersModificaLibro();
        EventHandlersCancellaLibro();
        EventHandlersFiltri();
    }
    
    /**
     * @brief Inizializzaza gli event handlers per l'aggiunta di un libro
     */
    private void EventHandlersAggiungiLibro() {

        tabArchivioLibri.getBtnAggiungi().setOnAction(event -> {
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
                
                if (!modelBiblioteca.aggiungiLibro(nuovoLibro)) {
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
    }
    
    /**
     * @brief Inizializza gli event handlers per la modifica di un libro selezionato dalla tabella
     */
    private void EventHandlersModificaLibro() {
        
        tabArchivioLibri.getBtnModifica().setOnAction(event -> {
            Libro target = tabArchivioLibri.getSelectedItem();
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
                if (!modelBiblioteca.modificaLibro(target, nuovoLibro)) {
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
    }
    
    /**
     * @brief Inizializzaza gli event handlers che si occupano della cancellazione di un libro
     */
    private void EventHandlersCancellaLibro() {
        tabArchivioLibri.getBtnCancella().setOnAction(event -> {
            Libro target = tabArchivioLibri.getSelectedItem();
            if (target == null) {
                new ErroreAlert("Devi prima selezionare un Libro");
                event.consume();
            }

            //Conferma
            ConfermaAlert alert = new ConfermaAlert("Vuoi davvero eliminare " + target.toString());
            if (alert.getEsito()) {
                if(!modelBiblioteca.cancellaLibro(target)) {
                    new ErroreAlert("Ho cancellato le copie disponibili in quanto una copia è ancora in presito");
                }
                tabArchivioLibri.getTabella().refresh();
            }
        });
    }
    
    /**
     * @brief Il metodo si occupa di inizializzare gli event handler per la ricerca costituita da
     * filtri sulla vista della tabella
     */
    private void EventHandlersFiltri() {
        //Applica filtri
        tabArchivioLibri.getBtnCerca().setOnAction(event -> {
            String testoInserito = tabArchivioLibri.getTxfFiltroRicerca().getText();
            String[] filtri = testoInserito.split(" ");
            List<Predicate<Libro>> filtriPredicate = new ArrayList<>();
            for (String filtro : filtri) {
                filtriPredicate.add(libro -> libro.toString().toLowerCase().contains(filtro.toLowerCase()));
            }
            FilteredList<Libro> cercati = modelBiblioteca.getArchivioLibri().filtered(
                    filtriPredicate.stream()
                            .reduce(Predicate::and)
                            .orElse(x -> true) //se non ci sonon filtri resituisce tutto
            );
            tabArchivioLibri.getTabella().setItems(cercati);
        });

        //Elimina Filtri
        tabArchivioLibri.getBtnEliminaFiltri().setOnAction(event -> {
            tabArchivioLibri.getTxfFiltroRicerca().clear();
            tabArchivioLibri.getTabella().setItems(modelBiblioteca.getArchivioLibri());
        });
    }
}
