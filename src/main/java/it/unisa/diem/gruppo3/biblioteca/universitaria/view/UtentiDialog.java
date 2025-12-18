package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

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
        
        
        //creazione della dialog con lo sfondo
        dialog = new Dialog<>();
        dialog.getDialogPane().setStyle("-fx-background-color: #FFFDF5;");
        dialog.setTitle("Registrazione nuovo utente");

        //creazione della anchorpane
        AnchorPane root = new AnchorPane();
        root.setPrefSize(400,50);
        root.setStyle("-fx-background-color: #FFFDF5;");

        //Text titolo (CHE NON CAMBIA DA MODIFICA A INSERIMENTO)
        Text headerText = new Text("Inserisci i dati dell'utente");
        headerText.setBoundsType(TextBoundsType.VISUAL);
        headerText.setLayoutX(20.0);
        headerText.setLayoutY(50.0);
        headerText.setWrappingWidth(360.0);
        headerText.setTextAlignment(TextAlignment.CENTER);
        headerText.setFont(Font.font("Avenir Next", FontWeight.BOLD, FontPosture.ITALIC, 25));

        //creazione dei textfield con la spaziatura fissa
        double startY = 90.0;
        double gap = 55.0;

        txfNome= createCustomTextField("Nome", 60.0, startY);
        txfCognome= createCustomTextField("Cognome", 60.0, startY + gap);
        txfMatricola= createCustomTextField("Matricola", 60.0, startY + gap * 2);
        txfEmail= createCustomTextField("E-Mail", 60.0, startY + gap * 3);


        
        //aggiungo tutto alla anchorpane
        root.getChildren().addAll(
            headerText, 
            txfNome,
            txfCognome, 
            txfMatricola, 
            txfEmail
        );

        //aggiungo la anchorpane al dialogpane
        dialog.getDialogPane().setContent(root);

        //definizione del bottone della dialogpane
        ButtonType typeEntra = new ButtonType("Conferma", ButtonBar.ButtonData.OK_DONE);
        
        //aggiungo il bottone alla dialog pane
        dialog.getDialogPane().getButtonTypes().addAll(typeEntra);

        
        //chiamo il bottone per personalizzarlo
        btnOk = (Button) dialog.getDialogPane().lookupButton(typeEntra);

        //effetto ombra del bottone
        InnerShadow buttonShadow = new InnerShadow();
        buttonShadow.setBlurType(BlurType.TWO_PASS_BOX);

        //stile bottone "entra"
        btnOk.setPrefSize(119.0, 36.0);
        btnOk.setStyle("-fx-background-color: #6AB565; -fx-text-fill: black; -fx-background-radius:8;"); // Verde
        btnOk.setFont(new Font("Kodchasan SemiBold Italic", 17.0));
        btnOk.setCursor(Cursor.HAND);
        btnOk.setOnMouseEntered(e -> btnOk.setEffect(buttonShadow));
        btnOk.setOnMouseExited(e -> btnOk.setEffect(null));
        btnOk.setTranslateX(-130); //sposta il tasto in basso al centro  
    }
    private TextField createCustomTextField(String prompt, double layoutX, double layoutY) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setLayoutX(layoutX);
        tf.setLayoutY(layoutY);
        tf.setPrefSize(280.0, 36.0);
        tf.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-radius: 10;"); 
        tf.setFont(Font.font("Kodchasan", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 16));
        tf.setCursor(Cursor.TEXT);
        return tf;
    }
    
    public UtentiDialog(Utente target) {
        this();
        
        dialog.setTitle("Modifica");
        
        txfNome.setText(target.getNome());
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
    
    /**
     * @brief Getter per la finestra
     * @return Reference alla finestra
     */
    public Dialog<Utente> getDialog() {
        return dialog;
    }
}
