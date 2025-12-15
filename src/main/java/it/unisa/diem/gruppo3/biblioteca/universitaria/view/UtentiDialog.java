package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @file UtentiDialog.java
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per il caso in cui la finestra 
 * utilità venga generata dalla tab dell'archivio degli utenti
 */
public class UtentiDialog {
    
    /**
     * @brief Finestra
     */
    private Dialog<Utente> dialog;
    /**
     * @brief Campo di testo per il nome dell'utente
     */
    private TextField txfNome;
    
    /**
     * @brief Campo di testo per il cognome dell'utente
     */
    private TextField txfCognome;
    
    /**
     * @brief Campo di testo per la matricola dell'utente
     */
    private TextField txfMatricola;
    
    /**
     * @brief Campo di testo per l'email dell'utente
     */
    private TextField txfEmail;
    
    /**
     * @brief Bottone di conferma operazione
     */
    private Button btnOk;
    
    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per l'inserimento o la modifica di un utente
     */
    public UtentiDialog() {
        dialog = new Dialog<>();
        dialog.setTitle("Inserisci");
        dialog.setHeaderText("Inserisci un Nuovo Utente");
        
        txfNome = new TextField();
        txfNome.setPromptText("Nome");
        txfCognome = new TextField();
        txfCognome.setPromptText("Cognome");
        txfMatricola = new TextField();
        txfMatricola.setPromptText("Matricola");
        txfEmail = new TextField();
        txfEmail.setPromptText("Email");
        
        VBox form = new VBox();
        form.getChildren().addAll(txfNome, txfCognome, txfMatricola, txfEmail);
        dialog.getDialogPane().setContent(form);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        btnOk = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        
        dialog.showAndWait();
    }
    
    public UtentiDialog(Utente target) {
        this();
        dialog.setTitle("Modifica");
        dialog.setHeaderText("Inserisci un Nuovo Utente");
        
        txfNome.setText(target.toString());
        txfCognome.setText(target.getCognome());
        txfMatricola.setText(target.getMatricola());
        txfEmail.setText(target.getEmail());
    }

    /**
     * @brief Getter per il campo di testo del nome
     * @return Campo di testo per il nome
     */
    public TextField getTxfNome() {
        return txfNome;
    }

    /**
     * @brief Getter per il campo di testo del cognome
     * @return Campo di testo per il cognome
     */
    public TextField getTxfCognome() {
        return txfCognome;
    }

    /**
     * @brief Getter per il campo di testo per la matricola
     * @return Campo di testo per la matricola
     */
    public TextField getTxfMatricola() {
        return txfMatricola;
    }

    /**
     * @brief Getter per il campo di testo per l'email
     * @return Campo di testo per l'email
     */
    public TextField getTxfEmail() {
        return txfEmail;
    }
    
    /**
     * @brief Getter per il bottone di conferma operazione
     * @return Reference al bottone di conferma operazione
     */
    public Button getBtnOk() {
        return btnOk;
    }
}
