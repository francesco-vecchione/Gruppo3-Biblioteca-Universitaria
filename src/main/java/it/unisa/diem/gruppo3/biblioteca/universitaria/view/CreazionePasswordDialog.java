package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

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
        //impostazione del colore dello sfondo, del titolo
        dialog.getDialogPane().setStyle("-fx-background-color: #FFFDF5;");
        dialog.setTitle("Sign-Up");
        
        //creazione anchorpane, con dimensioni fisse della finestra, e colore sfondo
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 260);
        root.setStyle("-fx-background-color: #FFFDF5;");

        //text benvenuto
        Text title = new Text("Benvenuto!");
        title.setLayoutX(182.0);
        title.setLayoutY(64.0);
        title.setBoundsType(TextBoundsType.VISUAL);
        title.setFont(new Font("Avenir Next Bold", 42.0));

        //text descrizione
        Text description = new Text("Crea la tua password che ti servirà per accedere all'applicazione dal prossimo avvio");
        description.setLayoutX(152.0);
        description.setLayoutY(93.0);
        description.setWrappingWidth(296.21630859375);
        description.setTextAlignment(TextAlignment.CENTER);
        description.setFill(Color.web("#544b4b"));
        description.setFont(new Font("Kodchasan SemiBold Italic", 13.0));

        //textfield password
        txfPassword = new PasswordField();
        txfPassword.setLayoutX(160.0);
        txfPassword.setLayoutY(139.0);
        txfPassword.setPrefSize(280.0, 36.0);
        txfPassword.setPromptText("Password");
        txfPassword.setFont(new Font("Kodchasan Medium", 15.0));
        txfPassword.setStyle("-fx-background-radius: 20;");

        //textfield conferma password
        txfConfermaPassword = new PasswordField();
        txfConfermaPassword.setLayoutX(160.0);
        txfConfermaPassword.setLayoutY(195.0);
        txfConfermaPassword.setPrefSize(280.0, 36.0);
        txfConfermaPassword.setPromptText("Conferma Password");
        txfConfermaPassword.setFont(new Font("Kodchasan Medium", 15.0));
        txfConfermaPassword.setStyle("-fx-background-radius: 20;");

       

        //definizione dei bottoni della dialogpane con le loro etichette
        ButtonType typeChiudi = new ButtonType("Chiudi", ButtonData.CANCEL_CLOSE);
        ButtonType typeEntra = new ButtonType("Entra", ButtonData.OK_DONE);
        
        //aggiungiamo i bottoni alla dialogpane
        dialog.getDialogPane().getButtonTypes().addAll(typeChiudi, typeEntra);
        
        //chiamo i bottoni della dialogpane per poi poterli personalizzare
        btnChiudi = (Button) dialog.getDialogPane().lookupButton(typeChiudi);
        btnOk = (Button) dialog.getDialogPane().lookupButton(typeEntra);
        
        //effetto ombra di entrambi i bottoni
        InnerShadow buttonShadow = new InnerShadow();
        buttonShadow.setBlurType(BlurType.TWO_PASS_BOX);

        //stile bottone "chiudi"
        btnChiudi.setPrefSize(100.0, 36.0);
        btnChiudi.setStyle("-fx-background-color: #D64C3C; -fx-text-fill: black; -fx-background-radius:8;"); // Rosso
        btnChiudi.setFont(new Font("Kodchasan SemiBold Italic", 17.0));
        btnChiudi.setCursor(Cursor.HAND);
        btnChiudi.setOnMouseEntered(e -> btnChiudi.setEffect(buttonShadow));
        btnChiudi.setOnMouseExited(e -> btnChiudi.setEffect(null));
        btnChiudi.setTranslateX(-325); //sposta nell'angolo inferiore sinistro

        //stile bottone "entra"
        btnOk.setPrefSize(119.0, 36.0);
        btnOk.setStyle("-fx-background-color: #6AB565; -fx-text-fill: black; -fx-background-radius:8;"); // Verde
        btnOk.setFont(new Font("Kodchasan SemiBold Italic", 17.0));
        btnOk.setCursor(Cursor.HAND);
        btnOk.setOnMouseEntered(e -> btnOk.setEffect(buttonShadow));
        btnOk.setOnMouseExited(e -> btnOk.setEffect(null));
        btnOk.setTranslateX(0); //sposta il tasto nell'angolo inferiore destro

        //aggiungo la roba all'anchorpane
        root.getChildren().addAll(title, description, txfPassword, txfConfermaPassword);
        
        //aggiungo la anchorpane al dialogpane
        dialog.getDialogPane().setContent(root);
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
