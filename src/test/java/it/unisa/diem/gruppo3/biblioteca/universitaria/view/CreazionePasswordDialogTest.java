package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class CreazionePasswordDialogTest {
    
    private CreazionePasswordDialog d;
    
    @BeforeEach
    public void setUp() {
        d = new CreazionePasswordDialog();
    }

    /**
     * UTC 10 - Test CreazionePasswordDialog – costruttore
     */
    @Test
    public void testCostruttore() {
        assertNotNull(d.getTxfPassword());
        assertNotNull(d.getTxfConfermaPassword());
    }
    
}
