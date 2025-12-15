package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
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
public class TabArchivioUtentiTest {
    
    private TabArchivioUtenti t;

    @Start
    private void start(Stage stage) {
        t = new TabArchivioUtenti(null);
    }

    /**
     * UTC 16 - Test TabArchivioUtenti – costruttore
     */
    @Test
    public void testCostruttore() {
        assertNotNull(t.getBtnAggiungi());
        assertNotNull(t.getBtnModifica());
        assertNotNull(t.getBtnCerca());
        assertNotNull(t.getBtnEliminaFiltri());
        assertNotNull(t.getTab());
        assertNull(t.getSelectedItem());
        assertNotNull(t.getTxfFiltroRicerca());
        assertNotNull(t.getTabella());
        assertNotNull(t.getBoxCentro());
        assertNotNull(t.getBtnCancella());
    }
    
}
