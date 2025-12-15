package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @file LoginDialog.java
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per il caso di log in
 */
public class LoginDialog {
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
     * @brief Campo di testo per l'inserimento della password
     */
    private TextField txfPassword;
    
    /**
     * @brief Link per procedere alla procedura di recupero della password
     */
    private Hyperlink linkPasswordDimenticata;

    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per la procedura di login
     */
    public LoginDialog() {
        dialog = new Dialog<>();
        
        txfPassword = new PasswordField();
        txfPassword.setPromptText("Password");   
        linkPasswordDimenticata = new Hyperlink("Password Dimenticata");
        
        VBox form = new VBox();
        form.getChildren().addAll(txfPassword, linkPasswordDimenticata);
        dialog.getDialogPane().setContent(form);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        
        btnOk = (Button)dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnChiudi = (Button)dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
    
        dialog.setTitle("Login");
        dialog.setHeaderText("Benvenuto\nEffettua l'Accesso");
    }

    /**
     * @brief Getter per il campo di testo per la password
     * @return Campo di testo per la password
     */
    public TextField getTxfPassword() {
        return txfPassword;
    }

    /**
     * @brief Getter per il link alla procedura di recupero password
     * @return Link per la procedura di recupero password 
     */
    public Hyperlink getLinkPasswordDimenticata() {
        return linkPasswordDimenticata;
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
