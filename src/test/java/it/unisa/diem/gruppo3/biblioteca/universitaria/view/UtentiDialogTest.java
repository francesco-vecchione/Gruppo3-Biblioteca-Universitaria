package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Utente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class UtentiDialogTest {
    
    private UtentiDialog d;

    /**
     * UTC 13.1 - Test UtentiDialog – costruttore
     */
    @Test
    public void testCostruttore() {
        d = new UtentiDialog();
        
        assertNotNull(d.getTxfNome());
        assertNotNull(d.getTxfCognome());
        assertNotNull(d.getTxfMatricola());
        assertNotNull(d.getTxfEmail());
        assertNotNull(d.getDialog());
    }

    /**
     * UTC 13.2 - Test UtentiDialog – costruttore sovraccarico
     */
    @Test
    public void testCostruttoreSovraccarico() {
        d = new UtentiDialog(new Utente("Francesco", "Pisaturo", "0612709311", "f.pisaturo1@studenti.unisa.it"));
        
        assertEquals("Francesco", d.getTxfNome().getText());
        assertEquals("Pisaturo", d.getTxfCognome().getText());
        assertEquals("0612709311", d.getTxfMatricola().getText());
        assertEquals("f.pisaturo1@studenti.unisa.it", d.getTxfEmail().getText());
    }
    
}
