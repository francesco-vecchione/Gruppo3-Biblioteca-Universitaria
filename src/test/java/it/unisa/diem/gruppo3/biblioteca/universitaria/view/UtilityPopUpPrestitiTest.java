package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class UtilityPopUpPrestitiTest {
    
    private UtilityPopUpPrestiti u;
    
    @BeforeEach
    public void setUp() {
        u = new UtilityPopUpPrestiti();
    }

    /**
     * UTC 17 - Test UtilityPopUpPrestiti – costruttore
     */
    @Test
    public void testCostruttore() {
        assertNotNull(u.getTxfMatricola());
        assertNotNull(u.getTxfIsbn());
        assertNotNull(u.getDpkDataPrestito());
        assertNotNull(u.getDpkDataRestituzione());
    }
}
