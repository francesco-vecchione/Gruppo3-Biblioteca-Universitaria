package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pc
 */
public class UtenteTest {
    
    private Utente utente;
    
    @BeforeEach
    public void setUp() {
        utente = new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it");
    }
    
    /**
     * UTC 2.1 - Test of constructor, of class Utente.
     */
    @Test
    public void testUtente() {
        assertEquals("Francesco", utente.getNome());
        assertEquals("Vecchione", utente.getCognome());
        assertEquals("0612709314", utente.getMatricola());
        assertEquals("f.vecchione17@studenti.unisa.it", utente.getEmail());
    }

    /**
     * UTC 2.2.1 - Test of isValid method, of class Utente.
     */
    @Test
    public void testIsValid1() {
        assertTrue(utente.isValid());
    }
    
    /**
     * UTC 2.2.2 - Test of isValid method, of class Utente.
     */
    @Test
    public void testIsValid2() {
        Utente utente1 = new Utente("Francesco", "Vecchione", "06127AA314", "f.vecchione17@studenti.unisa.it");
        Utente utente2 = new Utente("Francesco", "Vecchione", "921826", "f.vecchione17@studenti.unisa.it");
        assertFalse(utente1.isValid());
        assertFalse(utente2.isValid());
    }
    
    /**
     * UTC 2.2.3 - Test of isValid method, of class Utente.
     */
    @Test
    public void testIsValid3() {
        Utente utente1 = new Utente("Francesco", "Vecchione", "0612709314", "fran.vecchione17@studenti.unisa.it");
        Utente utente2 = new Utente("Francesco", "Vecchione", "0612709314", "f.vecc17@studenti.unisa.it");
        Utente utente3 = new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17studenti.unisa.it");
        assertFalse(utente1.isValid());
        assertFalse(utente2.isValid());
        assertFalse(utente3.isValid());
    }

    /**
     * UTC 2.3 - Test of equals method, of class Utente.
     */
    @Test
    public void testEquals() {
        Utente utente1 = new Utente("", "", "0612709314", "");
        assertTrue(utente.equals(utente1));
    }

    /**
     * UTC 2.4.1 - Test of compareTo method, of class Utente.
     */
    @Test
    public void testCompareTo1() {
        Utente utente1 = new Utente("Daniel", "Vita", "", "");
        assertTrue(utente.compareTo(utente1) > 0);
    }
    
    /**
     * UTC 2.4.2 - Test of compareTo method, of class Utente.
     */
    @Test
    public void testCompareTo2() {
        Utente utente1 = new Utente("Francesco", "Vecchione", "", "");
        assertTrue(utente.compareTo(utente1) == 0);
    }
    
    /**
     * UTC 2.4.3 - Test of compareTo method, of class Utente.
     */
    @Test
    public void testCompareTo3() {
        Utente utente1 = new Utente("Marco", "Del Gaudio", "", "");
        assertTrue(utente.compareTo(utente1) < 0);
    }

    /**
     * UTC 2.5 - Test of toString method, of class Utente.
     */
    @Test
    public void testToString() {
        assertNotNull(utente.toString());
    }
    
}
