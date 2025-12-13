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
     * UTC 3.1 - Test Prestito – costruttore
     */
    @Test
    public void testCostruttore() {
        assertEquals("0612709314", prestito.getMatricolaUtente());
        assertEquals("9788854175037", prestito.getIsbnPrestito());
        assertTrue(prestito.getDataPrestito().equals(LocalDate.of(2025,12,12)));
        assertTrue(prestito.getDataRestituzione().equals(LocalDate.of(2026, 1, 13)));
        assertEquals(StatoPrestito.ATTIVO, prestito.getStatoPrestito());
    }

    /**
     * UTC 3.2.1 - Test Prestito – controllo validità – validità riscontrata
     */
    @Test
    public void testIsValid1() {
        assertTrue(prestito.isValid());
    }
    
    /**
     * UTC 3.2.2 - Test Prestito – controllo validità – controllo fallito a causa di data restituzione precedente a data di prestito
     */
    @Test
    public void testIsValid2() {
        Prestito prestito1 = new Prestito("", "", LocalDate.of(2025, 12, 12), LocalDate.of(2025, 12, 10));
        assertFalse(prestito1.isValid());
    }

    /**
     * UTC 3.3.1 - Test Prestito – confronto tra due oggetti uguali – confronto positivo
     */
    @Test
    public void testEquals1() {
        Prestito prestito1 = new Prestito("0612709314", "9788854175037", null, null);
        assertTrue(prestito.equals(prestito1));
    }
    
    /**
     * UTC 3.3.2 - Test Prestito – confronto tra due oggetti uguali – confronto negativo data matricola diversa
     */
    @Test
    public void testEquals2() {
        Prestito prestito1 = new Prestito("0612709323", "9788854175037", null, null);
        assertFalse(prestito.equals(prestito1));
    }
    
    /**
     * UTC 3.3.3 - Test Prestito – confronto tra due oggetti uguali – confronto negativo dato isbn diverso
     */
    @Test
    public void testEquals3() {
        Prestito prestito1 = new Prestito("0612709314", "9788823145037", null, null);
        assertFalse(prestito.equals(prestito1));
    }

    /**
     * UTC 3.4.1 - Test Prestito – ordinamento cronologico sulla data di restituzione – prestito “maggiore” di prestito1
     */
    @Test
    public void testCompareTo1() {
        Prestito prestito1 = new Prestito("", "", null,  LocalDate.of(2026, 1, 10));
        assertTrue(prestito.compareTo(prestito1) > 0);
    }
    
    /**
     * UTC 3.4.2 - Test Prestito – ordinamento cronologico sulla data di restituzione – prestito “uguale” a prestito1
     */
    @Test
    public void testCompareTo2() {
        Prestito prestito1 = new Prestito("", "", null,  LocalDate.of(2026, 1, 13));
        assertTrue(prestito.compareTo(prestito1) == 0);
    }
    
    /**
     * UTC 3.4.3 - Test Prestito – ordinamento cronologico sulla data di restituzione – prestito “minore” di prestito1
     */
    @Test
    public void testCompareTo3() {
        Prestito prestito1 = new Prestito("", "", null,  LocalDate.of(2026, 1, 16));
        assertTrue(prestito.compareTo(prestito1) < 0);
    }

    /**
     * UTC 3.5 - Test Prestito – stampa della classe
     */
    @Test
    public void testToString() {
        assertNotNull(prestito.toString());
    }
    
}
