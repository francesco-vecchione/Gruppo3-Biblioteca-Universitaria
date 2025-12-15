package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
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
public class LibriDialogTest {
    
    private LibriDialog d1;
    private LibriDialog d2;

    @Start
    private void start(Stage stage) {
        d1 = new LibriDialog();
        d2 = new LibriDialog(new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 3));
    }
    
    /**
     * UTC 11.1 - Test LibriDialog – costruttore
     */
    @Test
    public void testCostruttore() {       
        assertNotNull(d1.getTxfTitolo());
        assertNotNull(d1.getTxfAutori());
        assertNotNull(d1.getTxfAnnoPubblicazione());
        assertNotNull(d1.getTxfIsbn());
        assertNotNull(d1.getTxfNumCopie());
        assertNotNull(d1.getDialog());
    }
    
    /**
     * UTC 11.2 - Test LibriDialog – costruttore sovraccarico
     */
    @Test
    public void testCostruttoreSovraccarico() {
        assertEquals("L'idiota", d2.getTxfTitolo().getText());
        assertEquals("Dostoevskij", d2.getTxfAutori().getText());
        assertEquals("2023", d2.getTxfAnnoPubblicazione().getText());
        assertEquals("9788854175037", d2.getTxfIsbn().getText());
        assertEquals("3", d2.getTxfNumCopie().getText());
    }
}
