package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author gruppo 3
 */
public abstract class UtilityPopUp<T extends Dato> {
    private Label targhettaErrori;
    private Button bottoneAzione;
    private Button bottoneAnnulla;
    private Stage stage;
    
    public UtilityPopUp() {
        
    }
    
    public Label getTarghettaErrori() {
        return null;
    }
    
    public Button getBottoneAzione() {
        return null;
    }
    
    public Button getBottoneAnnulla() {
        return null;
    }
    
    public Stage getStage() {
        return null;
    }
    
    public abstract T getDatiInseriti();
}
