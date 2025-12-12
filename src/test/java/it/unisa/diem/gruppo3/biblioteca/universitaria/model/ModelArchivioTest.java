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
    private Libro libroTest;
    private final String FILE_TEST = "test_archivio.dat";

    @BeforeEach
    public void setUp() {
        // Inizializzazione comune
        model = new ModelArchivio<>(FILE_TEST);
        model.apriArchivio();
        libroTest = new Libro("Test Book", "Test Author", 2024, "9781234567890", 1);
    }

    @AfterEach
    public void tearDown() {
        // Pulizia file dopo ogni test
        File f = new File(FILE_TEST);
        if(f.exists()) {
            f.delete();
        }
    }

    /**
     * UTC 5.1 - Test of costruttore and apriArchivio method, of class ModelArchivio.
     */
    @Test
    public void testCostruttoreEApriArchivio() {
        assertNotNull(model.getArchivioFiltrato());
    }

    /**
     * UTC 5.2 - Test of chiudiArchivio method, of class ModelArchivio.
     */
    @Test
    public void testChiudiArchivio() {
        boolean result = model.chiudiArchivio();
        assertTrue(result);
    }

    /**
     * UTC 5.3 - Test of getArchivioFiltrato method, of class ModelArchivio.
     */
    @Test
    public void testGetArchivioFiltrato() {
        assertNotNull(model.getArchivioFiltrato());
    }

    /**
     * UTC 5.4 - Test of aggiungiElemento method, of class ModelArchivio.
     */
    @Test
    public void testAggiungiElemento() {
        boolean result = model.aggiungiElemento(libroTest);
        assertTrue(result);
        assertTrue(model.getArchivioFiltrato().contains(libroTest));
    }

    /**
     * UTC 5.5 - Test of rimuoviElemento method, of class ModelArchivio.
     */
    @Test
    public void testRimuoviElemento() {
        model.aggiungiElemento(libroTest);
        boolean result = model.rimuoviElemento(libroTest);
        assertTrue(result);
        assertFalse(model.getArchivioFiltrato().contains(libroTest));
    }

    /**
     * UTC 5.6 - Test of modificaElemento method, of class ModelArchivio.
     */
    @Test
    public void testModificaElemento() {
        model.aggiungiElemento(libroTest);
        Libro libroModificato = new Libro("Titolo Nuovo", "Autore", 2025, "9781234567890", 1);
        
        boolean result = model.modificaElemento(libroTest, libroModificato);
        
        assertTrue(result);
        assertFalse(model.getArchivioFiltrato().contains(libroTest));
        assertTrue(model.getArchivioFiltrato().contains(libroModificato));
    }
}