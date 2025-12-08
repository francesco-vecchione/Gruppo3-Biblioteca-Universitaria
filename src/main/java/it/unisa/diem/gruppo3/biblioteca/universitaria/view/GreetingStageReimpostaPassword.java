package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.TextField;

/**
 * @author gruppo 3
 */
public class GreetingStageReimpostaPassword extends GreetingStage {
    private TextField fieldPassword;
    private TextField fieldConfermaPassword;
    
    public GreetingStageReimpostaPassword() {
        
    }
    
    @Override
    public String getDatiInseriti() {
        return null;
    }
    
    public BooleanBinding passwordEqualBinding() {
        return null;
    }
}
