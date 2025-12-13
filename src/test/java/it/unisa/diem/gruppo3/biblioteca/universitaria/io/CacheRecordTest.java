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
   
    /**
     *  UTC 6.1.1 – Test CacheRecord – costruttore Libro – operazione di aggiunta  
     */
     @Test
    public void testCostruttoreLibroAggiunta() {
        CacheRecord<Libro> cr = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, libroTest);
        
        assertEquals(TipoOperazione.AGGIUNTA, cr.getTipoOperazione());
        assertNull(cr.getTarget());
        assertEquals("L'idiota", cr.getElem().getTitolo());
        assertEquals("Dostoevskij", cr.getElem().getAutori());
        assertEquals(2023, cr.getElem().getAnnoDiPubblicazione());
        assertEquals("9788854175037", cr.getElem().getIsbn());
        assertEquals(1, cr.getElem().getNumeroCopieDisponibili());
    }
    /**
     * UTC 6.1.2 – Test CacheRecord – costruttore Libro – operazione di modifica  
     */
    @Test
    public void testCostruttoreLibroModifica() {
        Libro libroNuovo = new Libro("L'idiota", "Francesco Vecchione", 2004, "9788854175037", 5);
        CacheRecord<Libro> cr = new CacheRecord<>(TipoOperazione.MODIFICA, libroTest, libroNuovo);
        
        assertEquals(TipoOperazione.MODIFICA, cr.getTipoOperazione());
        assertEquals("L'idiota", cr.getTarget().getTitolo());
        assertEquals("Dostoevskij", cr.getTarget().getAutori());
        assertEquals(2023, cr.getTarget().getAnnoDiPubblicazione());
        assertEquals("9788854175037", cr.getTarget().getIsbn());
        assertEquals(1, cr.getTarget().getNumeroCopieDisponibili());
        assertEquals("L'idiota", cr.getElem().getTitolo());
        assertEquals("Francesco Vecchione", cr.getElem().getAutori());
        assertEquals(2004, cr.getElem().getAnnoDiPubblicazione());
        assertEquals("9788854175037", cr.getElem().getIsbn());
        assertEquals(5, cr.getElem().getNumeroCopieDisponibili());
    }
    /**
     * UTC 6.1.3 – Test CacheRecord – costruttore Libro – operazione di Cancellazione  
     */
    @Test
    public void testCostruttoreLibroCancellazione() {
        CacheRecord<Libro> cr = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, libroTest, null);
        
        assertEquals(TipoOperazione.CANCELLAZIONE, cr.getTipoOperazione());
        assertEquals("L'idiota", cr.getTarget().getTitolo());
        assertEquals("Dostoevskij", cr.getTarget().getAutori());
        assertEquals(2023, cr.getTarget().getAnnoDiPubblicazione());
        assertEquals("9788854175037", cr.getTarget().getIsbn());
        assertEquals(1, cr.getTarget().getNumeroCopieDisponibili());
        assertNull(cr.getElem());
    }
    /**
     * UTC 6.2.1 – Test CacheRecord – costruttore Utente – operazione di aggiunta  
     */
    @Test
    public void testCostruttoreUtenteAggiunta() {
        CacheRecord<Utente> cr = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, utenteTest);
        
        assertEquals(TipoOperazione.AGGIUNTA, cr.getTipoOperazione());
        assertNull(cr.getTarget());
        assertEquals("Francesco", cr.getElem().getNome());
        assertEquals("Vecchione", cr.getElem().getCognome());
        assertEquals("0612709314", cr.getElem().getMatricola());
        assertEquals("f.vecchione17@studenti.unisa.it", cr.getElem().getEmail());
    }
    /**
     * UTC 6.2.2 – Test CacheRecord – costruttore Utente – operazione di modifica  
     */
    @Test
    public void testCostruttoreUtenteModifica() {
        Utente utenteNuovo = new Utente("Maciu", "Piciu", "0612709314", "m.piciu@studenti.unisa.it");
        CacheRecord<Utente> cr = new CacheRecord<>(TipoOperazione.MODIFICA, utenteTest, utenteNuovo);
        
        assertEquals(TipoOperazione.MODIFICA, cr.getTipoOperazione());
        assertEquals("Francesco", cr.getTarget().getNome());
        assertEquals("Vecchione", cr.getTarget().getCognome());
        assertEquals("0612709314", cr.getTarget().getMatricola());
        assertEquals("f.vecchione17@studenti.unisa.it", cr.getTarget().getEmail());
        assertEquals("Maciu", cr.getElem().getNome());
        assertEquals("Piciu", cr.getElem().getCognome());
        assertEquals("0612709314", cr.getElem().getMatricola());
        assertEquals("m.piciu@studenti.unisa.it", cr.getElem().getEmail());
    }
    /**
     * UTC 6.2.3 – Test CacheRecord – costruttore Utente – operazione di cancellazione  
     */
    @Test
    public void testCostruttoreUtenteCancellazione() {
        CacheRecord<Utente> cr = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, utenteTest, null);
        
        assertEquals(TipoOperazione.CANCELLAZIONE, cr.getTipoOperazione());
        assertEquals("Francesco", cr.getTarget().getNome());
        assertEquals("Vecchione", cr.getTarget().getCognome());
        assertEquals("0612709314", cr.getTarget().getMatricola());
        assertEquals("f.vecchione17@studenti.unisa.it", cr.getTarget().getEmail());
    }
    /**
     * UTC 6.3.1 – Test CacheRecord – costruttore Prestito – operazione di aggiunta  
     */
    @Test
    public void testCostruttorePrestitoAggiunta() {
        CacheRecord<Prestito> cr = new CacheRecord<>(TipoOperazione.AGGIUNTA, null, prestitoTest);
        
        assertEquals(TipoOperazione.AGGIUNTA, cr.getTipoOperazione());
        assertNull(cr.getTarget());
        assertEquals("0612709314", cr.getElem().getMatricolaUtente());
        assertEquals("9788854175037", cr.getElem().getIsbnPrestito());
        assertEquals(LocalDate.of(2025, 12, 12), cr.getElem().getDataPrestito());
        assertEquals(LocalDate.of(2026, 1, 13), cr.getElem().getDataRestituzione());
    }
    /**
     * UTC 6.3.2 – Test CacheRecord – costruttore Prestito – operazione di modifica  
     */
     
    @Test
    public void testCostruttorePrestitoModifica() {
        Prestito prestitoNuovo = new Prestito("0612701283", "9788854175222", LocalDate.of(2025, 12, 30), LocalDate.of(2026, 2, 13));
        CacheRecord<Prestito> cr = new CacheRecord<>(TipoOperazione.MODIFICA, prestitoNuovo, prestitoTest);
        
        assertEquals(TipoOperazione.MODIFICA, cr.getTipoOperazione());
    }
    /**
     * UTC 6.3.3 Test CacheRecord – costruttore Prestito – operazione di cancellazione
     */
    @Test
    public void testCostruttorePrestitoCancellazione() {
        Prestito prestitoDaCancellare = new Prestito("0612701283", "9788854175222", LocalDate.of(2025, 12, 30), LocalDate.of(2026, 2, 13));
        CacheRecord<Prestito> cr = new CacheRecord<>(TipoOperazione.CANCELLAZIONE, prestitoDaCancellare, null);
        
        assertEquals(TipoOperazione.CANCELLAZIONE, cr.getTipoOperazione());
        assertEquals("0612701283", cr.getTarget().getMatricolaUtente());
        assertEquals("9788854175222", cr.getTarget().getIsbnPrestito());
        assertEquals(LocalDate.of(2025, 12, 30), cr.getTarget().getDataPrestito());
        assertEquals(LocalDate.of(2026, 2, 13), cr.getTarget().getDataRestituzione());
        assertNull(cr.getElem());
    }
}