package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
/**
 * @author gruppo 3
 */

public class CacheRecordTest {

    private Libro libroTest;
    private Utente utenteTest;
    private Prestito prestitoTest;

    @BeforeEach
    public void setUp() {
        // Inizializzo gli oggetti base prima di ogni test
        libroTest = new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 1);
        utenteTest = new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it");
        prestitoTest = new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 13));
    }

    // --- UTC 6.1: Test su LIBRO ---

    @Test
    public void testCostruttoreLibroAggiunta() { // UTC 6.1.1
        CacheRecord<Libro> record = new CacheRecord<>(StatoOperazione.AGGIUNTA, null, libroTest);
        
        assertEquals(StatoOperazione.AGGIUNTA, record.getTipoOperazione());
        assertNull(record.getTarget());
        assertEquals("L'idiota", record.getElem().getTitolo());
    }

    @Test
    public void testCostruttoreLibroModifica() { // UTC 6.1.2
        Libro libroNuovo = new Libro("L'idiota", "Nuovo Autore", 2024, "9788854175037", 5);
        CacheRecord<Libro> record = new CacheRecord<>(StatoOperazione.MODIFICA, libroTest, libroNuovo);
        
        assertEquals(StatoOperazione.MODIFICA, record.getTipoOperazione());
        assertEquals("Dostoevskij", record.getTarget().getAutori());
        assertEquals("Nuovo Autore", record.getElem().getAutori());
    }

    @Test
    public void testCostruttoreLibroCancellazione() { // UTC 6.1.3
        CacheRecord<Libro> record = new CacheRecord<>(StatoOperazione.CANCELLAZIONE, libroTest, null);
        
        assertEquals(StatoOperazione.CANCELLAZIONE, record.getTipoOperazione());
        assertEquals("9788854175037", record.getTarget().getIsbn());
        assertNull(record.getElem());
    }

    // --- UTC 6.2: Test su UTENTE ---

    @Test
    public void testCostruttoreUtenteAggiunta() { // UTC 6.2.1
        CacheRecord<Utente> record = new CacheRecord<>(StatoOperazione.AGGIUNTA, null, utenteTest);
        
        assertEquals(StatoOperazione.AGGIUNTA, record.getTipoOperazione());
        assertEquals("0612709314", record.getElem().getMatricola());
    }

    @Test
    public void testCostruttoreUtenteModifica() { // UTC 6.2.2
        Utente utenteNuovo = new Utente("Mario", "Rossi", "0612709314", "m.rossi@studenti.unisa.it");
        CacheRecord<Utente> record = new CacheRecord<>(StatoOperazione.MODIFICA, utenteTest, utenteNuovo);
        
        assertEquals(StatoOperazione.MODIFICA, record.getTipoOperazione());
        assertEquals("Vecchione", record.getTarget().getCognome());
        assertEquals("Rossi", record.getElem().getCognome());
    }

    @Test
    public void testCostruttoreUtenteCancellazione() { // UTC 6.2.3
        CacheRecord<Utente> record = new CacheRecord<>(StatoOperazione.CANCELLAZIONE, utenteTest, null);
        
        assertEquals(StatoOperazione.CANCELLAZIONE, record.getTipoOperazione());
        assertEquals("0612709314", record.getTarget().getMatricola());
    }

    // --- UTC 6.3: Test su PRESTITO ---

    @Test
    public void testCostruttorePrestitoAggiunta() { // UTC 6.3.1
        CacheRecord<Prestito> record = new CacheRecord<>(StatoOperazione.AGGIUNTA, null, prestitoTest);
        
        assertEquals(StatoOperazione.AGGIUNTA, record.getTipoOperazione());
        assertEquals("0612709314", record.getElem().getMatricolaUtente());
    }

    @Test
    public void testCostruttorePrestitoModifica() { // UTC 6.3.2
        Prestito prestitoNuovo = new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 2, 1));
        CacheRecord<Prestito> record = new CacheRecord<>(StatoOperazione.MODIFICA, prestitoTest, prestitoNuovo);
        
        assertEquals(StatoOperazione.MODIFICA, record.getTipoOperazione());
        assertEquals(LocalDate.of(2026, 1, 13), record.getTarget().getDataRestituzione());
        assertEquals(LocalDate.of(2026, 2, 1), record.getElem().getDataRestituzione());
    }

    @Test
    public void testCostruttorePrestitoCancellazione() { // UTC 6.3.3
        CacheRecord<Prestito> record = new CacheRecord<>(StatoOperazione.CANCELLAZIONE, prestitoTest, null);
        
        assertEquals(StatoOperazione.CANCELLAZIONE, record.getTipoOperazione());
        assertEquals("0612709314", record.getTarget().getMatricolaUtente());
    }
}