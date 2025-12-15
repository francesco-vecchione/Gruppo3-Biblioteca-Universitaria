package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 *
 * @author gruppo 3
 */
@ExtendWith(ApplicationExtension.class)
public class UtentiDialogTest {
    
    private UtentiDialog d1;
    private UtentiDialog d2;

    @Start
    private void start(Stage stage) {
        d1 = new UtentiDialog();
        d2 = new UtentiDialog(new Utente("Francesco", "Pisaturo", "0612709311", "f.pisaturo1@studenti.unisa.it"));
    }    
    
    /**
     * UTC 13.1 - Test UtentiDialog – costruttore
     */
    @Test
    public void testCostruttore() {
        
        assertNotNull(d1.getTxfNome());
        assertNotNull(d1.getTxfCognome());
        assertNotNull(d1.getTxfMatricola());
        assertNotNull(d1.getTxfEmail());
        assertNotNull(d1.getDialog());
    }

    /**
     * UTC 13.2 - Test UtentiDialog – costruttore sovraccarico
     */
    @Test
    public void testCostruttoreSovraccarico() {
        assertEquals("Francesco", d2.getTxfNome().getText());
        assertEquals("Pisaturo", d2.getTxfCognome().getText());
        assertEquals("0612709311", d2.getTxfMatricola().getText());
        assertEquals("f.pisaturo1@studenti.unisa.it", d2.getTxfEmail().getText());
    }
    
}
