package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void tearDown() {
        new File("cassaforte").delete();
    }
    
    /**
     * UTC 4.1.1 - Test Password – controllo della password – controllo passato con successo
     */
    @Test
    public void testVerificaPassword1() {
        assertTrue(password.verificaPassword("ciaoMondo"));
    }
    
    /**
     * UTC 4.1.2 - Test Password – controllo della password – controllo fallito a causa di password differenti
     */
    @Test
    public void testVerificaPassword2() {
        assertFalse(password.verificaPassword("biblioteca"));
    }

    /**
     * UTC 4.2 - Test Password – reimpostazione della password
     */
    @Test
    public void testImpostaPassword() {
        password.impostaPassword("biblioteca");
        assertTrue(password.verificaPassword("biblioteca"));
    }
    
    /**
     * UTC 4.3.1 - Test Password – controllare se sia presente una password – caso vero
     */
    @Test
    public void testEsistePassword_Caso1() {
        password.impostaPassword("ciaoMondo");
        assertTrue(password.esistePassword());
    }
    
    /**
     * UTC 4.3.2 - Test Password – controllare se sia presente una password – caso false
     */
    @Test
    public void testEsistePassword_Caso2() {
        assertFalse(password.esistePassword());
    }
    
}
