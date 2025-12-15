package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @file CreazionePasswordDialog.java
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per adattarsi alle funzionalità 
 * di registrazione e di reimpostazione password
 */
public class CreazionePasswordDialog {
    /**
     * @brief Dialog
     */
    private Dialog<String> dialog;
    
    /**
     * @brief Bottone di Avanzamento
     */
    private Button btnOk;
    
    /**
     * @brief Bottone di Chiusura
     */
    private Button btnChiudi;
    
    /**
     * @brief Campo di testo per inserire la password
     */
    private TextField txfPassword;
    
    /**
     * @brief Campo di testo per reinserire la password
     */
    private TextField txfConfermaPassword;

    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per l'inserimento password
     */
    public CreazionePasswordDialog() {
        dialog = new Dialog<>();
        
        txfPassword = new PasswordField();
        txfPassword.setPromptText("Password");
        txfConfermaPassword = new PasswordField();
        txfConfermaPassword.setPromptText("Conferma Password");
        
        VBox form = new VBox();
        form.getChildren().addAll(txfPassword, txfConfermaPassword);
        dialog.getDialogPane().setContent(form);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        
        btnOk = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnChiudi = (Button)dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        
        
        dialog.setTitle("Registra Password");
        dialog.setHeaderText("Registra una Nuova Password");
    }

    /**
     * @brief Getter per il campo di testo per la password
     * @return Il campo di testo per la password
     */
    public TextField getTxfPassword() {
        return txfPassword;
    }

    /**
     * @brief Getter per il campo di testo per la conferma della password
     * @return Il campo di testo per la comferma della password
     */
    public TextField getTxfConfermaPassword() {
        return txfConfermaPassword;
    }
    
    /**
     * @brief Getter per il bottone Ok
     * @return Il bottone OK
     */
    public Button getBtnOk() {
        return btnOk;
    }
    
    /**
     * @brief Getter per il bottone Chiudi
     * @return Il bottone CHIUDI
     */
    public Button getBtnChiudi() {
        return btnChiudi;
    }
    
    /**
     * @brief Getter per la Dialog
     * @return La Dialog
     */
    public Dialog<String> getDialog() {
        return dialog;
    }
}
