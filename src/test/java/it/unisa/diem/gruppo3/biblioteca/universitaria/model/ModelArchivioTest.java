package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.junit.jupiter.api.BeforeAll;
/**
 * @author gruppo 3
 */

public class ModelArchivioTest {

    private ModelArchivio<Libro> model;
    private final String FILE_TEST = "archivioLibri";
    
    //private static ModelArchivio<Libro> stressModel;
    private ModelArchivio<Libro> stressModel;
    private final String STRESS_FILE_TEST = "testFiles/stressArchivioLibri"; 
    
    @BeforeAll
    public static void setUpClass() {
        /* codice commentato perché la sua funzione, ovvero di fare il parsing del file dove originariamente sono stati scritte le entry per i libri, è stata svolta con successo
        
        String stressTestFile = "testFiles/stressArchivioLibriRaw.txt";
        stressModel = new ModelArchivio<>("testFiles/stressArchivioLibri");
        stressModel.apriArchivio();
        
        try (FileInputStream fis = new FileInputStream(stressTestFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                DataInputStream dis = new DataInputStream(bis)) {
            while(true) {
                String[] toAdd = dis.readUTF().split(";");
                
                stressModel.aggiungiElemento(new Libro(toAdd[0], toAdd[1], Integer.parseInt(toAdd[2]), toAdd[3], Integer.parseInt(toAdd[4])));
            }
        } catch (EOFException e) {
            // fine lettura
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
    
    
    @BeforeEach
    public void setUp() {
        // Inizializzazione comune
        model = new ModelArchivio<>(FILE_TEST);
        model.apriArchivio();
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
        assertTrue(model.chiudiArchivio());
    }

    /**
     * UTC 5.3 - Test ModelArchivio – getter archivio filtrato
     */
    @Test
    public void testGetArchivioFiltrato() {   
        assertNotNull(model.getArchivioFiltrato());
    }

    /**
     * UTC 5.4 - Test ModelArchivio – aggiunta elemento
     */
    @Test
    public void testAggiungiElemento() {
        Libro l1 = new Libro("Test Book", "Test Author", 2024, "9781234567890", 1);
        
        assertTrue(model.aggiungiElemento(l1));
        assertTrue(model.getArchivioFiltrato().contains(l1));
    }

    /**
     * UTC 5.5 - Test ModelArchivio – rimozione elemento
     */
    @Test
    public void testRimuoviElemento() {
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
        Libro oldL = new Libro("Old Book", "Old Author", 2024, "9781234567890", 1);
        Libro newL = new Libro("New Book", "New Author", 2025, "9781234567890", 1);
        model.aggiungiElemento(oldL);
        
        assertTrue(model.modificaElemento(oldL, newL));
        
        int index = model.getArchivioFiltrato().indexOf(newL);
        assertEquals("New Book", model.getArchivioFiltrato().get(index).getTitolo());
    }
    
    
    /**
     * UTC 5.7.1 - Test ModelArchivio – stress test archivio libri con 150000 entry – chiusura archivio sotto i 500 millisecondi
     */
    @Test
    public void testStressChiudiArchivio() {
        stressModel = new ModelArchivio<>(STRESS_FILE_TEST);
        stressModel.apriArchivio();
        
        assertTimeout(Duration.ofMillis(500), () -> {
           stressModel.chiudiArchivio(); 
        });
    }
    
    /**
     * UTC 5.7.2 - Test ModelArchivio – stress test archivio libri con 150000 entry – apertura archivio sotto i 500 millisecondi
     */    
    @Test 
    public void testStressApriArchivio() {
        stressModel = new ModelArchivio<>(STRESS_FILE_TEST);
        
        assertTimeout(Duration.ofMillis(500), () -> {
           stressModel.apriArchivio();
        });
        
        new File(STRESS_FILE_TEST + "Cache").delete();
    }
}