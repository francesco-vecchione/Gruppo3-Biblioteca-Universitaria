package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.time.LocalDate;

/**
 * @author gruppo 3
 */
public class GestoreIOTest {

    private final String DB_LIBRI = "dbL.dat";
    private final String DB_UTENTI = "dbU.dat";
    private final String DB_PRESTITI = "dbP.dat";
    
    private GestoreIO<Libro> gioLibri;
    private GestoreIO<Utente> gioUtenti;
    private GestoreIO<Prestito> gioPrestiti;

    @BeforeEach
    public void setUp() {
        gioLibri = new GestoreIO<>(DB_LIBRI);
        gioUtenti = new GestoreIO<>(DB_UTENTI);
        gioPrestiti = new GestoreIO<>(DB_PRESTITI);
    }

    @AfterEach
    public void tearDown() {
        new File(DB_LIBRI).delete(); new File(DB_LIBRI + "Cache").delete();
        new File(DB_UTENTI).delete(); new File(DB_UTENTI + "Cache").delete();
        new File(DB_PRESTITI).delete(); new File(DB_PRESTITI + "Cache").delete();
    }

    // --- COSTRUTTORI (UTC 9.1) ---
    @Test public void testCostruttoreLibri() { assertTrue(new File(DB_LIBRI).exists()); } // 9.1.1
    @Test public void testCostruttoreUtenti() { assertTrue(new File(DB_UTENTI).exists()); } // 9.1.2
    @Test public void testCostruttorePrestiti() { assertTrue(new File(DB_PRESTITI).exists()); } // 9.1.3

    // --- SALVA ARCHIVIO (UTC 9.2) ---
    @Test // 9.2.1
    public void testSalvaArchivioLibri() {
        ObservableList<Libro> list = FXCollections.observableArrayList(new Libro("T", "A", 2024, "1", 1));
        assertTrue(gioLibri.salvaArchivio(list));
    }
    @Test // 9.2.2
    public void testSalvaArchivioUtenti() {
        ObservableList<Utente> list = FXCollections.observableArrayList(new Utente("N", "C", "1", "e"));
        assertTrue(gioUtenti.salvaArchivio(list));
    }
    @Test // 9.2.3
    public void testSalvaArchivioPrestiti() {
        ObservableList<Prestito> list = FXCollections.observableArrayList(new Prestito("1", "1", LocalDate.now(), LocalDate.now()));
        assertTrue(gioPrestiti.salvaArchivio(list));
    }

    // --- CARICA ARCHIVIO (UTC 9.3) ---

    // 1. Caso Vuoto
    @Test public void testCaricaLibriVuoto() { assertTrue(gioLibri.caricaArchivio().isEmpty()); } // 9.3.1.1
    @Test public void testCaricaUtentiVuoto() { assertTrue(gioUtenti.caricaArchivio().isEmpty()); } // 9.3.2.1
    @Test public void testCaricaPrestitiVuoto() { assertTrue(gioPrestiti.caricaArchivio().isEmpty()); } // 9.3.3.1

    // 2. Caso Base (Dati presenti, Cache vuota)
    @Test // 9.3.1.2
    public void testCaricaLibriBase() {
        ObservableList<Libro> list = FXCollections.observableArrayList(new Libro("T", "A", 2024, "1", 1));
        gioLibri.salvaArchivio(list);
        assertEquals(1, gioLibri.caricaArchivio().size());
    }
    @Test // 9.3.2.2
    public void testCaricaUtentiBase() {
        ObservableList<Utente> list = FXCollections.observableArrayList(new Utente("N", "C", "1", "e"));
        gioUtenti.salvaArchivio(list);
        assertEquals(1, gioUtenti.caricaArchivio().size());
    }
    @Test // 9.3.3.2
    public void testCaricaPrestitiBase() {
        ObservableList<Prestito> list = FXCollections.observableArrayList(new Prestito("1", "1", LocalDate.now(), LocalDate.now()));
        gioPrestiti.salvaArchivio(list);
        assertEquals(1, gioPrestiti.caricaArchivio().size());
    }

    // 3. Caso Merge (Dati presenti + Cache)
    @Test // 9.3.1.3
    public void testCaricaLibriMerge() {
        gioLibri.salvaModificaArchivio(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Libro("T", "A", 2024, "1", 1)));
        assertEquals(1, gioLibri.caricaArchivio().size());
    }
    @Test // 9.3.2.3
    public void testCaricaUtentiMerge() {
        gioUtenti.salvaModificaArchivio(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Utente("N", "C", "1", "e")));
        assertEquals(1, gioUtenti.caricaArchivio().size());
    }
    @Test // 9.3.3.3
    public void testCaricaPrestitiMerge() {
        gioPrestiti.salvaModificaArchivio(new CacheRecord<>(StatoOperazione.AGGIUNTA, null, new Prestito("1", "1", LocalDate.now(), LocalDate.now())));
        assertEquals(1, gioPrestiti.caricaArchivio().size());
    }
}