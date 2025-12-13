package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

/**
 * @author gruppo 3
 */
public class CassaforteTest {

    private Cassaforte cassaforte;
    private final String FILE_TEST = "test_cassaforte.dat";

    @BeforeEach
    public void setUp() {
        cassaforte = new Cassaforte(FILE_TEST);
    }

    @AfterEach
    public void tearDown() {
        File f = new File(FILE_TEST);
        if (f.exists()) f.delete();
    }

    // --- UTC 7.1 ---
    @Test
    public void testCostruttore() {
        assertNotNull(cassaforte);
    }

    // --- UTC 7.2 ---
    @Test
    public void testScriviFile() {
        boolean result = cassaforte.scriviFile("Test Data");
        assertTrue(result);
        assertTrue(new File(FILE_TEST).exists());
    }

    // --- UTC 7.3.1 (Lettura) ---
    
    /**
     * UTC 7.3.1 - Case 1: Il file esiste e contiene dati.
     */
    @Test
    public void testLeggiFile_Caso1() {
        // Prepara: Scrivo dati
        cassaforte.scriviFile("Dati Presenti");
        
        // Esegui: Leggo
        Object result = cassaforte.leggiFile();
        
        // Verifica
        assertEquals("Dati Presenti", result);
    }

    /**
     * UTC 7.3.1 - Case 2: Il file non esiste (o è vuoto).
     */
    @Test
    public void testLeggiFile_Caso2() {
        // Prepara: Assicuro che il file NON esista (tearDown lo cancella, ma per sicurezza)
        new File(FILE_TEST).delete();
        
        // Esegui: Leggo
        Object result = cassaforte.leggiFile();
        
        // Verifica: Dovrebbe ritornare null (o gestire l'errore a seconda dell'implementazione)
        assertNull(result);
    }
}