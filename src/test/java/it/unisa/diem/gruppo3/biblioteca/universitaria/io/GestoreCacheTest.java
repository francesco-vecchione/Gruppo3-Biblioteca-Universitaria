package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
/**
 * @author gruppo 3
 */

public class GestoreCacheTest {

    private final String CACHE_LIBRI = "cl.dat";
    private final String CACHE_UTENTI = "cu.dat";
    private final String CACHE_PRESTITI = "cp.dat";
    
    private GestoreCache<Libro> gcLibri;
    private GestoreCache<Utente> gcUtenti;
    private GestoreCache<Prestito> gcPrestiti;

    @BeforeEach
    public void setUp() {
        gcLibri = new GestoreCache<>(CACHE_LIBRI);
        gcUtenti = new GestoreCache<>(CACHE_UTENTI);
        gcPrestiti = new GestoreCache<>(CACHE_PRESTITI);
    }

    @AfterEach
    public void tearDown() {
        new File(CACHE_LIBRI).delete();
        new File(CACHE_UTENTI).delete();
        new File(CACHE_PRESTITI).delete();
    }

    // --- COSTRUTTORI (UTC 8.1) ---
    @Test public void testCostruttoreLibri() { assertNotNull(gcLibri); } // 8.1.1
    @Test public void testCostruttoreUtenti() { assertNotNull(gcUtenti); } // 8.1.2
    @Test public void testCostruttorePrestiti() { assertNotNull(gcPrestiti); } // 8.1.3

    // --- SALVA SU CACHE (UTC 8.2) ---
    @Test // 8.2.1
    public void testSalvaCacheLibri() {
        CacheRecord<Libro> cr = new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Libro("T", "A", 2024, "1", 1));
        assertTrue(gcLibri.salvaSuCache(cr));
        assertTrue(new File(CACHE_LIBRI).exists());
    }

    @Test // 8.2.2
    public void testSalvaCacheUtenti() {
        CacheRecord<Utente> cr = new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Utente("N", "C", "1", "e"));
        assertTrue(gcUtenti.salvaSuCache(cr));
    }

    @Test // 8.2.3
    public void testSalvaCachePrestiti() {
        CacheRecord<Prestito> cr = new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Prestito("1", "1", LocalDate.now(), LocalDate.now()));
        assertTrue(gcPrestiti.salvaSuCache(cr));
    }

    // --- CARICA DA CACHE (UTC 8.3) ---
    @Test // 8.3.1
    public void testCaricaCacheLibri() {
        gcLibri.salvaSuCache(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Libro("T", "A", 2024, "1", 1)));
        List<CacheRecord<Libro>> list = gcLibri.caricaDaCache();
        assertFalse(list.isEmpty());
    }

    @Test // 8.3.2
    public void testCaricaCacheUtenti() {
        gcUtenti.salvaSuCache(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Utente("N", "C", "1", "e")));
        assertFalse(gcUtenti.caricaDaCache().isEmpty());
    }

    @Test // 8.3.3
    public void testCaricaCachePrestiti() {
        gcPrestiti.salvaSuCache(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Prestito("1", "1", LocalDate.now(), LocalDate.now())));
        assertFalse(gcPrestiti.caricaDaCache().isEmpty());
    }

    // --- ELIMINA CACHE (UTC 8.4) ---
    @Test // 8.4.1
    public void testEliminaCacheLibri() {
        gcLibri.salvaSuCache(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Libro("T", "A", 2024, "1", 1)));
        assertTrue(gcLibri.eliminaCache());
        assertFalse(new File(CACHE_LIBRI).exists());
    }

    @Test // 8.4.2
    public void testEliminaCacheUtenti() {
        gcUtenti.salvaSuCache(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Utente("N", "C", "1", "e")));
        assertTrue(gcUtenti.eliminaCache());
    }

    @Test // 8.4.3
    public void testEliminaCachePrestiti() {
        gcPrestiti.salvaSuCache(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Prestito("1", "1", LocalDate.now(), LocalDate.now())));
        assertTrue(gcPrestiti.eliminaCache());
    }
}