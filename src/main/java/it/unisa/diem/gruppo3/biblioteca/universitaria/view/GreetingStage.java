package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author gruppo 3
 */
public abstract class GreetingStage {
    private Button bottoneAzione;
    private Button bottoneChiudi;
    private Stage stage;
    
    public GreetingStage() {
        
    }
    
    public abstract String getDatiInseriti();
    
    public Button getBottoneAzione() {
        return null;
    }
    
    public Button getBottoneChiudi() {
        return null;
    }
    
    public Stage getStage() {
        return null;
    }
}
