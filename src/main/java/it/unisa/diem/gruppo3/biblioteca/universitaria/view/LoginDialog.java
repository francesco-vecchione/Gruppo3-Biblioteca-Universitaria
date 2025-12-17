package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.control.ButtonBar.ButtonData;






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
        
        //creazione dell'oggetto dialog, col titolo
        dialog = new Dialog<>();
        dialog.setTitle("Login");

        //impostazione del colore dello sfondo
        dialog.getDialogPane().setStyle("-fx-background-color: #FFFDF5;");

        //creazione anchorpane che conterrà tutto tranne i bottoni
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 220); 
        root.setStyle("-fx-background-color: #FFFDF5;");
        
        //Text bentornato
        Text title = new Text("Bentornato!");
        title.setLayoutX(179.0);
        title.setLayoutY(82.0);
        title.setBoundsType(TextBoundsType.VISUAL);
        title.setFont(new Font("Avenir Next Bold", 42.0));
        
        //text descrizione
        Text subtitle = new Text("Effettua il Login inserendo la password qui sotto");
        subtitle.setLayoutX(153.0);
        subtitle.setLayoutY(110.0);
        subtitle.setWrappingWidth(296.216);
        subtitle.setTextAlignment(TextAlignment.CENTER);
        subtitle.setFill(Color.web("#544b4b"));
        subtitle.setFont(new Font("Kodchasan SemiBold Italic", 13.0));

        //textField Password
        txfPassword = new PasswordField();
        txfPassword.setLayoutX(161.0);
        txfPassword.setLayoutY(151.0);
        txfPassword.setPrefSize(280.0, 36.0);
        txfPassword.setPromptText("Password");
        txfPassword.setFont(new Font("Kodchasan Medium", 15.0));
        txfPassword.setStyle("-fx-background-radius: 20;");

        //link password dimenticata
        linkPasswordDimenticata = new Hyperlink("Password dimenticata?");
        linkPasswordDimenticata.setLayoutX(220.0);
        linkPasswordDimenticata.setLayoutY(185.0);
        linkPasswordDimenticata.setFont(new Font("Kodchasan Medium", 13.0));
        linkPasswordDimenticata.setUnderline(true);
        linkPasswordDimenticata.setStyle("-fx-text-fill: #6f6a6a; -fx-border-color: transparent; -fx-padding: 4 0 4 0;");
        linkPasswordDimenticata.setCursor(Cursor.HAND);

        //aggiungiamo tutto alla anchorpane
        root.getChildren().addAll(title, subtitle, txfPassword, linkPasswordDimenticata);
        
        //inseriamo l'anchorpane nella dialogpane
        dialog.getDialogPane().setContent(root);

        
        
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
