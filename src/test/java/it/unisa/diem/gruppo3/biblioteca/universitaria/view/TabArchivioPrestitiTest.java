package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class TabArchivioPrestitiTest {
    
    private TabArchivioPrestiti t;
    
    @BeforeEach
    public void setUp() {
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
        assertNotNull(t.getSelectedItem());
        assertNotNull(t.getTxfFiltroRicerca());
        assertNotNull(t.getTabella());
        assertNotNull(t.getBoxCentro());
    }
    
}
