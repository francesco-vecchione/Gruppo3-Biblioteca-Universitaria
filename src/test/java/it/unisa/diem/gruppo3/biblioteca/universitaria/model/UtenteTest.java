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
     * UTC 2.2 - Test of setNome method, of class Utente.
     */
    @Test
    public void testSetNome() {
        utente.setNome("Giovanni");
        assertEquals("Giovanni", utente.getNome());
    }

    /**
     * UTC 2.3 - Test of setCognome method, of class Utente.
     */
    @Test
    public void testSetCognome() {
        utente.setCognome("Battista");
        assertEquals("Battista", utente.getCognome());
    }

    /**
     * UTC 2.4 - Test of setMatricola method, of class Utente.
     */
    @Test
    public void testSetMatricola() {
        utente.setMatricola("0612709561");
        assertEquals("0612709561", utente.getMatricola());
    }

    /**
     * UTC 2.5 - Test of setEmail method, of class Utente.
     */
    @Test
    public void testSetEmail() {
        utente.setEmail("g.battista@studenti.unisa.it");
        assertEquals("g.battista@studenti.unisa.it", utente.getEmail());
    }

    /**
     * UTC 2.6 - Test of nomeProperty method, of class Utente.
     */
    @Test
    public void testNomeProperty() {
        assertNotNull(utente.nomeProperty());
    }

    /**
     * UTC 2.7 - Test of cognomeProperty method, of class Utente.
     */
    @Test
    public void testCognomeProperty() {
        assertNotNull(utente.cognomeProperty());
    }

    /**
     * UTC 2.8 - Test of matricolaProperty method, of class Utente.
     */
    @Test
    public void testMatricolaProperty() {
        assertNotNull(utente.matricolaProperty());
    }

    /**
     * UTC 2.9 - Test of emailProperty method, of class Utente.
     */
    @Test
    public void testEmailProperty() {
        assertNotNull(utente.emailProperty());
    }

    /**
     * UTC 2.10.1 - Test of isValid method, of class Utente.
     */
    @Test
    public void testIsValid1() {
        assertTrue(utente.isValid());
    }
    
    /**
     * UTC 2.10.2 - Test of isValid method, of class Utente.
     */
    @Test
    public void testIsValid2() {
        Utente utente1 = new Utente("Francesco", "Vecchione", "06127AA314", "f.vecchione17@studenti.unisa.it");
        Utente utente2 = new Utente("Francesco", "Vecchione", "921826", "f.vecchione17@studenti.unisa.it");
        assertFalse(utente1.isValid());
        assertFalse(utente2.isValid());
    }
    
    /**
     * UTC 2.10.3 - Test of isValid method, of class Utente.
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
     * UTC 2.11 - Test of equals method, of class Utente.
     */
    @Test
    public void testEquals() {
        Utente utente1 = new Utente("", "", "0612709314", "");
        assertTrue(utente.equals(utente1));
    }

    /**
     * UTC 2.12.1 - Test of compareTo method, of class Utente.
     */
    @Test
    public void testCompareTo1() {
        Utente utente1 = new Utente("Daniel", "Vita", "", "");
        assertTrue(utente.compareTo(utente1) > 0);
    }
    
    /**
     * UTC 2.12.2 - Test of compareTo method, of class Utente.
     */
    @Test
    public void testCompareTo2() {
        Utente utente1 = new Utente("Francesco", "Vecchione", "", "");
        assertTrue(utente.compareTo(utente1) == 0);
    }
    
    /**
     * UTC 2.12.3 - Test of compareTo method, of class Utente.
     */
    @Test
    public void testCompareTo3() {
        Utente utente1 = new Utente("Marco", "Del Gaudio", "", "");
        assertTrue(utente.compareTo(utente1) < 0);
    }

    /**
     * UTC 2.13 - Test of toString method, of class Utente.
     */
    @Test
    public void testToString() {
        assertNotNull(utente.toString());
    }
    
}
