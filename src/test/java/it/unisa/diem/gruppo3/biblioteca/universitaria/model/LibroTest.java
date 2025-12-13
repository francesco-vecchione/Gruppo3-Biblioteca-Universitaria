package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gruppo 3
 */
public class LibroTest {
    
    private Libro libro;
    
    @BeforeEach
    public void setUp() {
        libro = new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 1);
    }

    /**
     * UTC 1.1 - Test of constructor, of class Libro.
     */
    @org.junit.jupiter.api.Test
    public void testLibro() {
        assertEquals("L'idiota", libro.getTitolo());
        assertEquals("Dostoevskij", libro.getAutori());
        assertEquals(2023, libro.getAnnoDiPubblicazione());
        assertEquals("9788854175037", libro.getIsbn());
        assertEquals(1, libro.getNumeroCopieDisponibili());
    }
    
    /**
     * UTC 1.2 - Test of setTitolo method, of class Libro.
     */
    @Test
    public void testSetTitolo() {
        libro.setTitolo("Delitto e castigo");
        assertEquals("Delitto e castigo", libro.getTitolo());
    }

    /**
     * UTC 1.3 - Test of setAutori method, of class Libro.
     */
    @Test
    public void testSetAutori() {
        libro.setAutori("Turgenev");
        assertEquals("Turgenev", libro.getAutori());
    }

    /**
     * UTC 1.4 - Test of setAnnoPubblicazione method, of class Libro.
     */
    @Test
    public void testSetAnnoDiPubblicazione() {
        libro.setAnnoDiPubblicazione(2015);
        assertEquals(2015, libro.getAnnoDiPubblicazione());
    }

    /**
     * UTC 1.5 - Test of setIsbn method, of class Libro.
     */
    @Test
    public void testSetIsbn() {
        libro.setIsbn("9788855250342");
        assertEquals("9788855250342", libro.getIsbn());
    }

    /**
     * UTC 1.6 - Test of setNumeroCopieDisponibili method, of class Libro.
     */
    @Test
    public void testSetNumeroCopieDisponibili() {
        libro.setNumeroCopieDisponibili(3);
        assertEquals(3, libro.getNumeroCopieDisponibili());
    }

    /**
     * UTC 1.7 - Test of titoloProperty method, of class Libro.
     */
    @Test
    public void testTitoloProperty() {
        assertNotNull(libro.titoloProperty());
    }

    /**
     * UTC 1.8 - Test of autoriProperty method, of class Libro.
     */
    @Test
    public void testAutoriProperty() {
        assertNotNull(libro.autoriProperty());
    }

    /**
     * UTC 1.9 - Test of annoPubblicazioneProperty method, of class Libro.
     */
    @Test
    public void testAnnoPubblicazioneProperty() {
        assertNotNull(libro.annoPubblicazioneProperty());

    }

    /**
     * UTC 1.10 - Test of isbnProperty method, of class Libro.
     */
    @Test
    public void testIsbnProperty() {
        assertNotNull(libro.isbnProperty());

    }

    /**
     * UTC 1.11 - Test of numeroCopieDisponibiliProperty method, of class Libro.
     */
    @Test
    public void testNumeroCopieDisponibiliProperty() {
        assertNotNull(libro.numeroCopieDisponibiliProperty());

    }

    /**
     * UTC 1.12.1 - Test of isValid method, of class Libro.
     */
    @Test
    public void testIsValid1() {
        assertTrue(libro.isValid());
    }
    
    /**
     * UTC 1.12.2 - Test of isValid method, of class Libro.
     */
    @Test
    public void testIsValid2() {
        Libro libro1 = new Libro("L’idiota", "Dostoevskij", 2023, "9268854175037", 1);
        Libro libro2 = new Libro("L’idiota", "Dostoevskij", 2023, "9788854", 1);
        assertFalse(libro1.isValid());
        assertFalse(libro2.isValid());
    }
    
    /**
     * UTC 1.12.3 - Test of isValid method, of class Libro.
     */
    @Test
    public void testIsValid3() {
        Libro libro1 = new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 0);
        assertFalse(libro1.isValid());
    }
    
    /**
     * UTC 1.13 - Test of equals method, of class Libro.
     */
    @Test
    public void testEquals() {
        Libro libro1 = new Libro("", "", 0, "9788854175037", 0);
        assertTrue(libro.equals(libro1));
    }

    /**
     * UTC 1.14.1 - Test of compareTo method, of class Libro.
     */
    @Test
    public void testCompareTo1() {
        Libro libro1 = new Libro("Il libro rosso", "", 0, "", 0);
        assertTrue(libro.compareTo(libro1) > 0);
    }

    /**
     * UTC 1.14.2 - Test of compareTo method, of class Libro.
     */
    @Test
    public void testCompareTo2() {
        Libro libro1 = new Libro("L'idiota", "", 0, "", 0);
        assertTrue(libro.compareTo(libro1) == 0);
    }
    
    /**
     * UTC 1.14.3 - Test of compareTo method, of class Libro.
     */
    @Test
    public void testCompareTo3() {
        Libro libro1 = new Libro("L'uomo invisibile", "", 0, "", 0);
        assertTrue(libro.compareTo(libro1) < 0);
    }
    
    /**
     * UTC 1.15 Test of toString method, of class Libro.
     */
    @Test
    public void testToString() {
        assertNotNull(libro.toString());
    }
    
}
