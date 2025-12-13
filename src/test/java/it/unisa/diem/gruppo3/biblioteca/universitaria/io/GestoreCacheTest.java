package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
/**
 * @author gruppo 3
 */


public class GestoreCacheTest {

    @AfterEach
    public void tearDown() {
       //Alla fine di ogni test viene eliminato il file di cache.
        new File("archivioLibriCache").delete();
        new File("archivioUtentiCache").delete();
        new File("archivioPrestitiCache").delete();
    }

   /**
     * UTC 8.1.1 – Test GestoreCache – costruttore per cache Libri  
     */
    @Test
    public void testCostruttoreCacheLibri() {
        String pathLibri = "archivioLibriCache";
        GestoreCache<Libro> gcl = new GestoreCache<>(pathLibri);
        
        assertTrue(new File(pathLibri).exists());
    }

    /**
     * UTC 8.1.2 – Test GestoreCache – costruttore per cache Utenti
     */
    @Test
    public void testCostruttoreCacheUtenti() {
        String pathUtenti = "archivioUtentiCache";
        GestoreCache<Utente> gcu = new GestoreCache<>(pathUtenti);
        
        assertTrue(new File(pathUtenti).exists());
    }

   /**
     * UTC 8.1.3 – Test GestoreCache – costruttore per cache Prestiti
     */
    @Test
    public void testCostruttoreCachePrestiti() {
        String pathPrestiti = "archivioPrestitiCache";
        GestoreCache<Prestito> gcp = new GestoreCache<>(pathPrestiti);
        
        assertTrue(new File(pathPrestiti).exists());
    }

    /**
     * UTC 8.2.1 – Test GestoreCache – salva un cache record per archivio Libri  
     */
    @Test
    public void testSalvaSuCacheLibri_() {
        String pathLibri = "archivioLibriCache";
        GestoreCache<Libro> gcl = new GestoreCache<>(pathLibri);

        CacheRecord<Libro> crl1 = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 1));
        CacheRecord<Libro> crl2 = new CacheRecord<>(TipoOperazione.MODIFICA, new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 1), new Libro("L'idiota", "Francesco Vecchione", 2004, "9788854175037", 5));
        CacheRecord<Libro> crl3 = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 1), null);

        assertTrue(gcl.salvaSuCache(crl1));
        assertTrue(gcl.salvaSuCache(crl2));
        assertTrue(gcl.salvaSuCache(crl3));
    }

    /**
     * UTC 8.2.2 – Test GestoreCache – salva un cache record per archivio Utenti
     */
    @Test
    public void testSalvaSuCacheUtenti() {
        String pathUtenti = "archivioUtentiCache";
        GestoreCache<Utente> gcu = new GestoreCache<>(pathUtenti);

        CacheRecord<Utente> cru1 = new CacheRecord<>(TipoOperazione.MODIFICA, new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it"), new Utente("Maciu", "Piciu", "0612709314", "m.piciu@studenti.unisa.it"));
        CacheRecord<Utente> cru2 = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it"));
        CacheRecord<Utente> cru3 = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it"), null);

        assertTrue(gcu.salvaSuCache(cru1));
        assertTrue(gcu.salvaSuCache(cru2));
        assertTrue(gcu.salvaSuCache(cru3));
    }

    /**
     * UTC 8.2.3 – Test GestoreCache –  salva un cache record per archivio Prestti    
     */
    @Test
    public void testSalvaSuCachePrestiti() {
        String pathPrestiti = "archivioPrestitiCache";
        GestoreCache<Prestito> gcp = new GestoreCache<>(pathPrestiti);

        CacheRecord<Prestito> crp1 = new CacheRecord<>(TipoOperazione.MODIFICA, new Prestito("0612701283", "9788854175222", LocalDate.of(2025, 12, 30), LocalDate.of(2026, 2, 13)), new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 13)));
        CacheRecord<Prestito> crp2 = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, new Prestito("0612701283", "9788854175222", LocalDate.of(2025, 12, 30), LocalDate.of(2026, 2, 13)), null);
        CacheRecord<Prestito> crp3 = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 13)));

        assertTrue(gcp.salvaSuCache(crp1));
        assertTrue(gcp.salvaSuCache(crp2));
        assertTrue(gcp.salvaSuCache(crp3));
    }

    /**
     * UTC 8.3.1 – Test GestoreCache –  carica da cache una lista di record per Libri   
     */
    @Test
    public void testCaricaDaCacheLibri() {
        String pathLibri = "archivioLibriCache";
        GestoreCache<Libro> gcl = new GestoreCache<>(pathLibri);

        CacheRecord<Libro> crl1 = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 1));
        CacheRecord<Libro> crl2 = new CacheRecord<>(TipoOperazione.MODIFICA, new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 1), new Libro("L'idiota", "Francesco Vecchione", 2004, "9788854175037", 5));
        CacheRecord<Libro> crl3 = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, new Libro("L'idiota", "Dostoevskij", 2023, "9788854175037", 1), null);

        gcl.salvaSuCache(crl1);
        gcl.salvaSuCache(crl2);
        gcl.salvaSuCache(crl3);

        List<CacheRecord<Libro>> test = new LinkedList<>();
        test.add(crl1);
        test.add(crl2);
        test.add(crl3);

        List<CacheRecord<Libro>> loaded = gcl.caricaDaCache();

        for (int i = 0; i < loaded.size(); i++) {
            assertEquals(test.get(i).getTipoOperazione(), loaded.get(i).getTipoOperazione());
            assertEquals(test.get(i).getTarget(), loaded.get(i).getTarget());
            assertEquals(test.get(i).getElem(), loaded.get(i).getElem());
        }
    }

    /**
     * UTC 8.3.2 – Test GestoreCache –  carica da cache una lista di record per Utenti 
     */
    @Test
    public void testCaricaDaCacheUtenti() {
        String pathUtenti = "archivioUtentiCache";
        GestoreCache<Utente> gcu = new GestoreCache<>(pathUtenti);

        CacheRecord<Utente> cru1 = new CacheRecord<>(TipoOperazione.MODIFICA, new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it"), new Utente("Maciu", "Piciu", "0612709314", "m.piciu@studenti.unisa.it"));
        CacheRecord<Utente> cru2 = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it"));
        CacheRecord<Utente> cru3 = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it"), null);

        gcu.salvaSuCache(cru1);
        gcu.salvaSuCache(cru2);
        gcu.salvaSuCache(cru3);

        List<CacheRecord<Utente>> test = new LinkedList<>();
        test.add(cru1);
        test.add(cru2);
        test.add(cru3);

        List<CacheRecord<Utente>> loaded = gcu.caricaDaCache();

        for (int i = 0; i < loaded.size(); i++) {
            assertEquals(test.get(i).getTipoOperazione(), loaded.get(i).getTipoOperazione());
            assertEquals(test.get(i).getTarget(), loaded.get(i).getTarget());
            assertEquals(test.get(i).getElem(), loaded.get(i).getElem());
        }
    }

    /**
     * UTC 8.3.3 – Test GestoreCache –  carica da cache una lista di record per Prestiti  
     */
    @Test
    public void testCaricaDaCachePrestiti() {
        String pathPrestiti = "archivioPrestitiCache";
        GestoreCache<Prestito> gcp = new GestoreCache<>(pathPrestiti);

        CacheRecord<Prestito> crp1 = new CacheRecord<>(TipoOperazione.MODIFICA, new Prestito("0612701283", "9788854175222", LocalDate.of(2025, 12, 30), LocalDate.of(2026, 2, 13)), new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 13)));
        CacheRecord<Prestito> crp2 = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, new Prestito("0612701283", "9788854175222", LocalDate.of(2025, 12, 30), LocalDate.of(2026, 2, 13)), null);
        CacheRecord<Prestito> crp3 = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 13)));

        gcp.salvaSuCache(crp1);
        gcp.salvaSuCache(crp2);
        gcp.salvaSuCache(crp3);

        List<CacheRecord<Prestito>> test = new LinkedList<>();
        test.add(crp1);
        test.add(crp2);
        test.add(crp3);

        List<CacheRecord<Prestito>> loaded = gcp.caricaDaCache();

        for (int i = 0; i < loaded.size(); i++) {
            assertEquals(test.get(i).getTipoOperazione(), loaded.get(i).getTipoOperazione());
            assertEquals(test.get(i).getTarget(), loaded.get(i).getTarget());
            assertEquals(test.get(i).getElem(), loaded.get(i).getElem());
        }
    }

    /**
     * UTC 8.4.1 – Test GestoreCache –  eliminazione file di cache Libri     
     */
    @Test
    public void testEliminaCacheLibri() {
        String pathLibri = "archivioLibriCache";
        GestoreCache<Libro> gcl = new GestoreCache<>(pathLibri);
        
        assertTrue(gcl.eliminaCache());
        assertFalse(new File(pathLibri).exists());
    }

    /**
     * UTC 8.4.2 – Test GestoreCache –  eliminazione file di cache Utenti   
     */
    @Test
    public void testEliminaCacheUtenti() {
        String pathUtenti = "archivioUtentiCache";
        GestoreCache<Utente> gcu = new GestoreCache<>(pathUtenti);
        
        assertTrue(gcu.eliminaCache());
        assertFalse(new File(pathUtenti).exists());
    }

    /**
     * UTC 8.4.3 – Test GestoreCache –  eliminazione file di cache Prestiti  
     */
    @Test
    public void testEliminaCachePrestiti() {
        String pathPrestiti = "archivioPrestitiCache";
        GestoreCache<Prestito> gcp = new GestoreCache<>(pathPrestiti);
        
        assertTrue(gcp.eliminaCache());
        assertFalse(new File(pathPrestiti).exists());
    }
}