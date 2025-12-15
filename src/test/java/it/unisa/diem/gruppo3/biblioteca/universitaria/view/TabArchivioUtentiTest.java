package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pc
 */
public class TabArchivioUtentiTest {
    
    private TabArchivioUtenti t;
    
    @BeforeEach
    public void setUp() {
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
        assertNotNull(t.getSelectedItem());
        assertNotNull(t.getTxfFiltroRicerca());
        assertNotNull(t.getTabella());
        assertNotNull(t.getBoxCentro());
        assertNotNull(t.getBtnCancella());
    }
    
}
