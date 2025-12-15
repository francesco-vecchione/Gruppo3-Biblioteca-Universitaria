package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
/**
 * @author gruppo 3
 */

public class CassaforteTest {

    private Cassaforte cf; 
    
    @BeforeEach
    public void setUp() {
        cf = new Cassaforte("cassaforte");
    }
    
    @AfterEach
    public void tearDown() {
        // Pulizia file dopo ogni test 
        new File("cassaforte").delete();
    }

    /**
     * UTC 7.1 - Test Cassaforte - funzione di codifica password
     */
    @Test
    public void testCriptaPassword() {
        String passwordInChiaro1 = "@ksfutlSTTn2&3s";
        String passwordInChiaro2 = "\'\'\"\"\'\'\'";

        assertEquals(passwordInChiaro1.hashCode(), cf.criptaPassword(passwordInChiaro1));
        assertEquals(passwordInChiaro2.hashCode(), cf.criptaPassword(passwordInChiaro2));
    }

    /**
     * UTC 7.2 - Test Cassaforte – metodo per salvare la password in un file – reimpostazione della password
     */
    @Test
    public void testSalvaPasswordCriptata() {
        String passwordInChiaro1 = "@ksfutlSTTn2&3s";
        String passwordInChiaro2 = "\'\'\"\"\'\'\'";

        assertTrue(cf.salvaPasswordCriptata(passwordInChiaro1));
        assertEquals(cf.criptaPassword(passwordInChiaro1), cf.leggiPasswordCriptata());
        
        assertTrue(cf.salvaPasswordCriptata(passwordInChiaro2));
        assertEquals(cf.criptaPassword(passwordInChiaro2), cf.leggiPasswordCriptata());
    }

    /**
     * UTC 7.3.1 - Test Cassaforte – metodo per leggere la password salvata – caso 1
     */
    @Test
    public void testLeggiPasswordCriptata_Caso1() {
        String passwordInChiaro1 = "@ksfutlSTTn2&3s";
        cf.salvaPasswordCriptata(passwordInChiaro1);

        assertEquals(cf.criptaPassword(passwordInChiaro1), cf.leggiPasswordCriptata());
    }

    /**
     * UTC 7.3.2- Test Cassaforte - metodo per leggere la password salvata – caso 2
     */
    @Test
    public void testLeggiPasswordCriptata_Caso2() {
        String passwordInChiaro2 = "\'\'\"\"\'\'\'";
        cf.salvaPasswordCriptata(passwordInChiaro2);

        assertEquals(cf.criptaPassword(passwordInChiaro2), cf.leggiPasswordCriptata());
    }
}