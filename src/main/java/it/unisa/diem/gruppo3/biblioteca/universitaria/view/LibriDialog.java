package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * @file LibriDialog.java
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
     * @brief Bottone di conferma operazione
     */
    private Button btnOk;
    
    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per l'inserimento o la modifica di un libro
     */
    public LibriDialog() {
        //creazione della dialog con lo sfondo
        dialog = new Dialog<>();
        dialog.getDialogPane().setStyle("-fx-background-color: #FFFDF5;");

        //creazione della anchorpane
        AnchorPane root = new AnchorPane();
        root.setPrefSize(400,50);
        root.setStyle("-fx-background-color: #FFFDF5;");

        //Text titolo (CHE NON CAMBIA DA MODIFICA A INSERIMENTO)
        Text headerText = new Text("Inserisci i dati del libro");
        headerText.setBoundsType(TextBoundsType.VISUAL);
        headerText.setLayoutX(20.0);
        headerText.setLayoutY(50.0);
        headerText.setWrappingWidth(360.0);
        headerText.setTextAlignment(TextAlignment.CENTER);
        headerText.setFont(Font.font("Avenir Next", FontWeight.BOLD, FontPosture.ITALIC, 25));

        //creazione dei textfield con la spaziatura fissa
        double startY = 90.0;
        double gap = 55.0;

        txfTitolo = createCustomTextField("Titolo", 60.0, startY);
        txfAutori = createCustomTextField("Autori", 60.0, startY + gap);
        txfIsbn = createCustomTextField("ISBN", 60.0, startY + gap * 2);
        txfAnnoPubblicazione = createCustomTextField("Anno Pubblicazione", 60.0, startY + gap * 3);
        txfNumCopie = createCustomTextField("Numero Copie", 60.0, startY + gap * 4);


        
        //aggiungo tutto alla anchorpane
        root.getChildren().addAll(
            headerText, 
            txfTitolo,
            txfAutori, 
            txfIsbn, 
            txfAnnoPubblicazione, 
            txfNumCopie
        );

        //aggiungo la anchorpane al dialogpane
        dialog.getDialogPane().setContent(root);

        //definizione del bottone della dialogpane
        
        ButtonType typeEntra = new ButtonType("Conferma", ButtonData.OK_DONE);
        
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
    
    
    
    
    
    
    public LibriDialog(Libro target) {
        this();
        
        dialog.setTitle("Modifica");

        txfIsbn.setText(target.getIsbn());
        txfTitolo.setText(target.getTitolo());
        txfAutori.setText(target.getAutori());
        txfAnnoPubblicazione.setText(((Integer)target.getAnnoDiPubblicazione()).toString());
        txfNumCopie.setText(((Integer)target.getNumeroCopieDisponibili()).toString());
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
    public Dialog<Libro> getDialog() {
        return dialog;
    }
}
