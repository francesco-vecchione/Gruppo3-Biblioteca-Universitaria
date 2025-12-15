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
public class TabArchivioPrestitiTest {
    
    private TabArchivioPrestiti t;
    
    @Start
    private void start(Stage stage) {
        t = new TabArchivioPrestiti(null);
    }
    
    /**
     * UTC 15 - Test TabArchivioPrestiti – costruttore
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
    }
    
}
