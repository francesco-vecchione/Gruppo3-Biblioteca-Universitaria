package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class PrestitoTest {

    private Prestito prestito;

    @BeforeEach
    public void setUp() {
        prestito = new Prestito("0612709314", "9788854175037", LocalDate.of(2025,12,12), LocalDate.of(2026, 1, 13));
    }

    /**
     * UTC 3.1 - Test of constructor, of class Prestito.
     */
    @Test
    public void testUtente() {
        assertEquals("0612709314", prestito.getMatricolaUtente());
        assertEquals("9788854175037", prestito.getIsbnPrestito());
        assertTrue(prestito.getDataPrestito().equals(LocalDate.of(2025,12,12)));
        assertTrue(prestito.getDataRestituzione().equals(LocalDate.of(2026, 1, 13)));
        assertEquals(StatoPrestito.ATTIVO, prestito.getStatoPrestito());
    }

    /**
     * UTC 3.2.1 - Test of isValid method, of class Prestito.
     */
    @Test
    public void testIsValid1() {
        assertTrue(prestito.isValid());
    }
    
    /**
     * UTC 3.2.2 - Test of isValid method, of class Prestito.
     */
    @Test
    public void testIsValid2() {
        Prestito prestito1 = new Prestito("", "", LocalDate.of(2025, 12, 12), LocalDate.of(2025, 12, 10));
        assertFalse(prestito1.isValid());
    }

    /**
     * UTC 3.3.1 - Test of equals method, of class Prestito.
     */
    @Test
    public void testEquals1() {
        Prestito prestito1 = new Prestito("0612709314", "9788854175037", null, null);
        assertTrue(prestito.equals(prestito1));
    }
    
    /**
     * UTC 3.3.2 - Test of equals method, of class Prestito.
     */
    @Test
    public void testEquals2() {
        Prestito prestito1 = new Prestito("0612709323", "9788854175037", null, null);
        assertFalse(prestito.equals(prestito1));
    }
    
    /**
     * UTC 3.3.3 - Test of equals method, of class Prestito.
     */
    @Test
    public void testEquals3() {
        Prestito prestito1 = new Prestito("0612709314", "9788823145037", null, null);
        assertFalse(prestito.equals(prestito1));
    }

    /**
     * UTC 3.4.1 - Test of compareTo method, of class Prestito.
     */
    @Test
    public void testCompareTo1() {
        Prestito prestito1 = new Prestito("", "", null,  LocalDate.of(2026, 1, 10));
        assertTrue(prestito.compareTo(prestito1) > 0);
    }
    
    /**
     * UTC 3.4.2 - Test of compareTo method, of class Prestito.
     */
    @Test
    public void testCompareTo2() {
        Prestito prestito1 = new Prestito("", "", null,  LocalDate.of(2026, 1, 13));
        assertTrue(prestito.compareTo(prestito1) == 0);
    }
    
    /**
     * UTC 3.4.3 - Test of compareTo method, of class Prestito.
     */
    @Test
    public void testCompareTo3() {
        Prestito prestito1 = new Prestito("", "", null,  LocalDate.of(2026, 1, 16));
        assertTrue(prestito.compareTo(prestito1) < 0);
    }

    /**
     * UTC 3.5 - Test of toString method, of class Prestito.
     */
    @Test
    public void testToString() {
        assertNotNull(prestito.toString());
    }
    
}
