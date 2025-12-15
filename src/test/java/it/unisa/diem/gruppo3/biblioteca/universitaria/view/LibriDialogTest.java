package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class LibriDialogTest {
    
    private LibriDialog d;

    /**
     * UTC 11.1 - Test LibriDialog – costruttore
     */
    @Test
    public void testCostruttore() {
        d = new LibriDialog();
        
        assertNotNull(d.getTxfTitolo());
        assertNotNull(d.getTxfAutori());
        assertNotNull(d.getTxfAnnoPubblicazione());
        assertNotNull(d.getTxfIsbn());
        assertNotNull(d.getTxfNumCopie());
        assertNotNull(d.getDialog());
    }
    
    /**
     * UTC 11.2 - Test LibriDialog – costruttore sovraccarico
     */
    @Test
    public void testCostruttoreSovraccarico() {
        d = new LibriDialog(new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 3));
    
        assertEquals("L'idiota", d.getTxfTitolo().getText());
        assertEquals("Dostoevskij", d.getTxfAutori().getText());
        assertEquals("2023", d.getTxfAnnoPubblicazione().getText());
        assertEquals("9788854175037", d.getTxfIsbn());
        assertEquals("3", d.getTxfNumCopie().getText());
    }
}
