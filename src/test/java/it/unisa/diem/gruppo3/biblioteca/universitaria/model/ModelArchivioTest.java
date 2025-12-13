package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
/**
 * @author gruppo 3
 */

public class ModelArchivioTest {

    private ModelArchivio<Libro> model;
    private final String FILE_TEST = "archivioLibri";

    @BeforeEach
    public void setUp() {
        // Inizializzazione comune
        model = new ModelArchivio<>(FILE_TEST);
    }

    @AfterEach
    public void tearDown() {
        // Pulizia file dopo ogni test
        new File(FILE_TEST).delete();
        new File(FILE_TEST + "Cache").delete();
    }

    /**
     * UTC 5.1 - Test ModelArchivio – apertura archivio
     */
    @Test
    public void testApriArchivio() {
        assertNotNull(model.getArchivioFiltrato());
    }

    /**
     * UTC 5.2 - Test ModelArchivio – chiusura archivio
     */
    @Test
    public void testChiudiArchivio() {
        model.apriArchivio();
        
        assertTrue(model.chiudiArchivio());
    }

    /**
     * UTC 5.3 - Test ModelArchivio – getter archivio filtrato
     */
    @Test
    public void testGetArchivioFiltrato() {
        model.apriArchivio();
        
        assertNotNull(model.getArchivioFiltrato());
    }

    /**
     * UTC 5.4 - Test ModelArchivio – aggiunta elemento
     */
    @Test
    public void testAggiungiElemento() {
        model.apriArchivio();
        Libro l1 = new Libro("Test Book", "Test Author", 2024, "9781234567890", 1);
        
        assertTrue(model.aggiungiElemento(l1));
        assertTrue(model.getArchivioFiltrato().contains(l1));
    }

    /**
     * UTC 5.5 - Test ModelArchivio – rimozione elemento
     */
    @Test
    public void testRimuoviElemento() {
        model.apriArchivio();
        Libro l1 = new Libro("Test Book", "Test Author", 2024, "9781234567890", 1);
        model.aggiungiElemento(l1);
        
        assertTrue(model.rimuoviElemento(l1));
        assertFalse(model.getArchivioFiltrato().contains(l1));
    }

    /**
     * UTC 5.6 - Test ModelArchivio – modifica elemento
     */
    @Test
    public void testModificaElemento() {
        model.apriArchivio();
        Libro oldL = new Libro("Old Book", "Old Author", 2024, "9781234567890", 1);
        Libro newL = new Libro("New Book", "New Author", 2025, "9781234567890", 1);
        model.aggiungiElemento(oldL);
        
        assertTrue(model.modificaElemento(oldL, newL));
        
        int index = model.getArchivioFiltrato().indexOf(newL);
        assertEquals("New Book", model.getArchivioFiltrato().get(index).getTitolo());
    }
}