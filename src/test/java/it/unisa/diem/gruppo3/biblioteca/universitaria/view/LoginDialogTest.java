package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class LoginDialogTest {
    
    private LoginDialog d;
    
    @BeforeEach
    public void setUp() {
        d = new LoginDialog();
    }
    
    /**
     * UTC 12 - Test LoginDialog – costruttore
     */
    @Test
    public void testGetTxfPassword() {
        assertNotNull(d.getTxfPassword());
        assertNotNull(d.getLinkPasswordDimenticata());
    }
    
}
