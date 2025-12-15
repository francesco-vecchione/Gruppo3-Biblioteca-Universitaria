package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

/**
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per il caso in cui la finestra 
 * di utilità venga generata dalla tab dell'archivio dei libri
 */
public class LibriDialog {
    /**
     * @brief Finestra
     */
    private Dialog<Libro> dialog;
    
    /**
     * @brief Campo di testo per il testo del libro
     */
    private TextField txfTitolo;
    
    /**
     * @brief Campo di testo per la lista degli autori
     */
    private TextField txfAutori;
    
    /**
     * @brief Campo di testo per l'anno di pubblicazione
     */
    private TextField txfAnnoPubblicazione;
    
    /**
     * @brief Campo di testo per il codice ISBN
     */
    private TextField txfIsbn;
    
    /**
     * @brief Campo di testo per il numero di copie
     */
    private TextField txfNumCopie;
    
    /**
     * @brief Libro Inserito
     */
    private Libro nuovoLibro;
    
    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per l'inserimento o la modifica di un libro
     */
    public LibriDialog() {
        dialog = new Dialog<>();
        dialog.setTitle("Inserisci");
        dialog.setHeaderText("Inserisci un Nuovo Libro");
        
        txfIsbn = new TextField();
        txfIsbn.setPromptText("ISBN");
        txfTitolo = new TextField();
        txfTitolo.setPromptText("Titolo");
        txfAutori = new TextField();
        txfAutori.setPromptText("Autori");
        txfAnnoPubblicazione = new TextField();
        txfAnnoPubblicazione.setPromptText("Anno");
        txfNumCopie = new TextField();
        txfNumCopie.setPromptText("Numero Copie");
        
        VBox form = new VBox();
        form.getChildren().addAll(txfIsbn, txfTitolo, txfAutori, txfAnnoPubblicazione, txfNumCopie);
        dialog.getDialogPane().setContent(form);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        Button btnOk = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnOk.disableProperty().bind(
            txfIsbn.textProperty().isEmpty()
            .or(txfTitolo.textProperty().isEmpty())
            .or(txfAutori.textProperty().isEmpty())
            .or(txfAnnoPubblicazione.textProperty().isEmpty())
            .or(txfNumCopie.textProperty().isEmpty()));
        
        btnOk.addEventFilter(ActionEvent.ACTION, event -> {
            try{
                Integer.parseInt(txfAnnoPubblicazione.getText());
                Integer.parseInt(txfNumCopie.getText());
            }
            catch(NumberFormatException e) {
                new ErroreAlert("Il formato dei dati che hai inserito non è corretto");
                event.consume();
                return;
            }
            nuovoLibro = new Libro(txfTitolo.getText(), txfAutori.getText(), Integer.parseInt(txfAnnoPubblicazione.getText()), txfIsbn.getText(), Integer.parseInt(txfNumCopie.getText()));
            if(!nuovoLibro.isValid()) {
                new ErroreAlert("Il formato dell'ISBN che hai inserito non è valido");
                event.consume();
            }
        });
        
        dialog.showAndWait();
    }
    
    public LibriDialog(Libro target) {
        dialog = new Dialog<>();
        dialog.setTitle("Inserisci");
        dialog.setHeaderText("Inserisci un Nuovo Libro");
        
        txfIsbn = new TextField(target.getIsbn());
        txfIsbn.setPromptText("ISBN");
        txfTitolo = new TextField(target.getTitolo());
        txfTitolo.setPromptText("Titolo");
        txfAutori = new TextField(target.getAutori());
        txfAutori.setPromptText("Autori");
        txfAnnoPubblicazione = new TextField(String.valueOf(target.getAnnoDiPubblicazione()));
        txfAnnoPubblicazione.setPromptText("Anno");
        txfNumCopie = new TextField(String.valueOf(target.getNumeroCopieDisponibili()));
        txfNumCopie.setPromptText("Numero Copie");
        
        VBox form = new VBox();
        form.getChildren().addAll(txfIsbn, txfTitolo, txfAutori, txfAnnoPubblicazione, txfNumCopie);
        dialog.getDialogPane().setContent(form);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        Button btnOk = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnOk.disableProperty().bind(
            txfIsbn.textProperty().isEmpty()
            .or(txfTitolo.textProperty().isEmpty())
            .or(txfAutori.textProperty().isEmpty())
            .or(txfAnnoPubblicazione.textProperty().isEmpty())
            .or(txfNumCopie.textProperty().isEmpty()));
        
        btnOk.addEventFilter(ActionEvent.ACTION, event -> {
            try{
                Integer.parseInt(txfAnnoPubblicazione.getText());
                Integer.parseInt(txfNumCopie.getText());
            }
            catch(NumberFormatException e) {
                new ErroreAlert("Il formato dei dati che hai inserito non è corretto");
                event.consume();
                return;
            }
            nuovoLibro = new Libro(txfTitolo.getText(), txfAutori.getText(), Integer.parseInt(txfAnnoPubblicazione.getText()), txfIsbn.getText(), Integer.parseInt(txfNumCopie.getText()));
            if(!nuovoLibro.isValid()) {
                new ErroreAlert("Il formato dell'ISBN che hai inserito non è valido");
                event.consume();
            }
        });
        
        dialog.showAndWait();
    }

    /**
     * @brief Getter del campo di testo per il titolo
     * @return Campo di testo per il titolo
     */
    public TextField getTxfTitolo() {
        return txfTitolo;
    }

    /**
     * @brief Getter del campo di testo per la lista degli autori
     * @return Campo di testo per la lista degli autori
     */
    public TextField getTxfAutori() {
        return txfAutori;
    }

    /**
     * @brief Getter del campo di testo per l'anno di pubblicazione
     * @return Campo di testo per l'anno di pubblicazione
     */
    public TextField getTxfAnnoPubblicazione() {
        return txfAnnoPubblicazione;
    }

    /**
     * @brief Getter del campo di testo per l'ISBN
     * @return Campo di testo per l'ISBN
     */
    public TextField getTxfIsbn() {
        return txfIsbn;
    }

    /**
     * @brief Getter del campo di testo per il numero di copie
     * @return Campo di testo per il numero di copie
     */
    public TextField getTxfNumCopie() {
        return txfNumCopie;
    }
    
    /**
     * @brief Getter del libro inserito
     * @return oggetto di tipo Libro
     */
    public Libro getLibro() {
        return nuovoLibro;
    }
}
