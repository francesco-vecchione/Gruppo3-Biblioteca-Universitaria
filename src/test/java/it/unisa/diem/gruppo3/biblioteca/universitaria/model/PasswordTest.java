package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class PasswordTest {
    
    private Password password;
    
    @BeforeEach
    public void setUp() {
        password = new Password("cassaforte");
        password.impostaPassword("ciaoMondo");
    }

    /**
     * UTC 4.1.1 - Test of verificaPassword method, of class Password.
     */
    @Test
    public void testVerificaPassword1() {
        assertTrue(password.verificaPassword("ciaoMondo"));
    }
    
    /**
     * UTC 4.1.2 - Test of verificaPassword method, of class Password.
     */
    @Test
    public void testVerificaPassword2() {
        assertFalse(password.verificaPassword("biblioteca"));
    }

    /**
     * UTC 4.2 - Test of impostaPassword method, of class Password.
     */
    @Test
    public void testImpostaPassword() {
        password.impostaPassword("biblioteca");
        assertTrue(password.verificaPassword("biblioteca"));
    }
    
}
