package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.time.LocalDate;
/**
 * @author gruppo 3
 */

public class GestoreIOTest {

    @AfterEach
    public void tearDown() {
        String[] files = {
            "archivioLibri", "archivioLibriCache",
            "archivioUtenti", "archivioUtentiCache",
            "archivioPrestiti", "archivioPrestitiCache"
        };
        for (String s : files) {
            File f = new File(s);
            if (f.exists()) f.delete();
        }
    }
    /**
     * UTC 9.1.1 –  Test GestoreIO – costruttore io Libri  
     */
    @Test
    public void testCostruttoreLibri() {
        String pathLibri = "archivioLibri";
        GestoreIO<Libro> giol = new GestoreIO<>(pathLibri);
        
        assertTrue(new File(pathLibri).exists());
    }
    /**
     * UTC 9.1.2 –  Test GestoreIO – costruttore Utenti 
     */
    @Test
    public void testCostruttoreUtenti() {
        String pathUtenti = "archivioUtenti";
        GestoreIO<Utente> giou = new GestoreIO<>(pathUtenti);
        
        assertTrue(new File(pathUtenti).exists());
    }
    /**
     * UTC 9.1.3 –  Test GestoreIO – costruttore io Prestiti 
     */
    @Test
    public void testCostruttorePrestiti() {
        String pathPrestiti = "archivioPrestiti";
        GestoreIO<Prestito> giop = new GestoreIO<>(pathPrestiti);
        
        assertTrue(new File(pathPrestiti).exists());
    }
    /**
     * UTC 9.2.1 –  Test GestoreIO – salvataggio dell’archivio per Libri  
     */
    @Test
    public void testSalvaArchivioLibri() {
        String pathLibri = "archivioLibri";
        GestoreIO<Libro> giol = new GestoreIO<>(pathLibri);
        ObservableList<Libro> archivio = FXCollections.observableArrayList();
        archivio.add(new Libro("Lolita", "Nobokov", 1955, "9788845912542", 2));
        archivio.add(new Libro("L’idiota", "Dostoevskij", 1868, "9788854175037", 3));
        archivio.add(new Libro("Padri e figli", "Turgenev", 1862, "9788883373893", 1));

        assertTrue(giol.salvaArchivio(archivio));
        assertFalse(new File(pathLibri + "Cache").exists());
    }
    /**
     * UTC 9.2.2 –  Test GestoreIO – salvataggio dell’archivio per Utenti
     */
    @Test
    public void testSalvaArchivioUtenti() {
        String pathUtenti = "archivioUtenti";
        GestoreIO<Utente> giou = new GestoreIO<>(pathUtenti);
        ObservableList<Utente> archivio = FXCollections.observableArrayList();
        archivio.add(new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it"));
        archivio.add(new Utente("Francesco", "Pisaturo", "0612709177", "f.pisaturo@studenti.unisa.it"));
        archivio.add(new Utente("Matteo", "Sirignano", "0612709812", "m.sirignano2@studenti.unisa.it"));

        assertTrue(giou.salvaArchivio(archivio));
        assertFalse(new File(pathUtenti + "Cache").exists());
    }
    /**
     * UTC 9.2.3 –  Test GestoreIO – salvataggio dell’archivio per Prestiti
     */
    @Test
    public void testSalvaArchivioPrestiti() {
        String pathPrestiti = "archivioPrestiti";
        GestoreIO<Prestito> giop = new GestoreIO<>(pathPrestiti);
        ObservableList<Prestito> archivio = FXCollections.observableArrayList();
        archivio.add(new Prestito("0612709177", "9788883373893", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 10)));
        archivio.add(new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 8)));
        archivio.add(new Prestito("0612709812", "9788854175037", LocalDate.of(2025, 12, 13), LocalDate.of(2026, 1, 10)));

        assertTrue(giop.salvaArchivio(archivio));
        assertFalse(new File(pathPrestiti + "Cache").exists());
    }
    /**
     * UTC 9.3.1.1 –  Test GestoreIO – caricamento archivio per libri quando l’archivio è vuoto e la cache è vuota  
     */
    @Test
    public void testCaricaArchivioLibriVuoto() {
        String pathLibri = "archivioLibri";
        GestoreIO<Libro> giol = new GestoreIO<>(pathLibri);
        
        assertTrue(giol.caricaArchivio().isEmpty());
    }
    /**
     * UTC 9.3.1.2 –  Test GestoreIO – caricamento archivio per libri quando l’archivio è non vuoto e la cache è vuota  i
     */
    @Test
    public void testCaricaArchivioLibriNonVuotoCacheVuota() {
        String pathLibri = "archivioLibri";
        GestoreIO<Libro> giol = new GestoreIO<>(pathLibri);
        ObservableList<Libro> archivio = FXCollections.observableArrayList();
        archivio.add(new Libro("Lolita", "Nobokov", 1955, "9788845912542", 2));
        archivio.add(new Libro("L’idiota", "Dostoevskij", 1868, "9788854175037", 3));
        archivio.add(new Libro("Padri e figli", "Turgenev", 1862, "9788883373893", 1));
        giol.salvaArchivio(archivio);

        ObservableList<Libro> retrieved = giol.caricaArchivio();

        for (int i = 0; i <= 2; i++) {
            assertEquals(archivio.get(i).getTitolo(), retrieved.get(i).getTitolo());
            assertEquals(archivio.get(i).getAutori(), retrieved.get(i).getAutori());
            assertEquals(archivio.get(i).getAnnoDiPubblicazione(), retrieved.get(i).getAnnoDiPubblicazione());
            assertEquals(archivio.get(i).getIsbn(), retrieved.get(i).getIsbn());
            assertEquals(archivio.get(i).getNumeroCopieDisponibili(), retrieved.get(i).getNumeroCopieDisponibili());
        }
    }
    /**
     * UTC 9.3.1.3 –  Test GestoreIO – caricamento archivio per libri quando l’archivio è non vuoto e la cache è non vuota  
     */
    @Test
    public void testCaricaArchivioLibriNonVuotoCacheNonVuota() {
        String pathLibri = "archivioLibri";
        GestoreIO<Libro> giol = new GestoreIO<>(pathLibri);
        Libro l1 = new Libro("Lolita", "Nobokov", 1955, "9788845912542", 2);
        Libro l2 = new Libro("L’idiota", "Dostoevskij", 1868, "9788854175037", 3);
        Libro l3 = new Libro("Guerra e pace", "Tolstoj", 1869, "9788883375873", 3);
        Libro l4 = new Libro("L’uomo invisibile", "Wells", 1895, "9788845912542", 1);
        
        ObservableList<Libro> archivio = FXCollections.observableArrayList();
        archivio.add(l1);
        archivio.add(l2);
        giol.salvaArchivio(archivio);

        giol.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.CANCELLAZIONE, l2, null));
        giol.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.AGGIUNTA, null, l3));
        giol.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.MODIFICA, l1, l4));

        ObservableList<Libro> retrieved = giol.caricaArchivio();
        int index = retrieved.indexOf(l4);

        assertFalse(retrieved.contains(l2));
        assertTrue(retrieved.contains(l3));
        assertEquals(l4.getTitolo(), retrieved.get(index).getTitolo());
        assertEquals(l4.getAutori(), retrieved.get(index).getAutori());
        assertEquals(l4.getAnnoDiPubblicazione(), retrieved.get(index).getAnnoDiPubblicazione());
        assertEquals(l4.getIsbn(), retrieved.get(index).getIsbn());
        assertEquals(l4.getNumeroCopieDisponibili(), retrieved.get(index).getNumeroCopieDisponibili());
    }
    /**
     * UTC 9.3.2.1 –  Test GestoreIO – caricamento archivio per utenti quando l’archivio è vuoto e la cache è vuota    
     */
    @Test
    public void testCaricaArchivioUtentiVuoto() {
        String pathUtenti = "archivioUtenti";
        GestoreIO<Utente> giou = new GestoreIO<>(pathUtenti);
        
        assertTrue(giou.caricaArchivio().isEmpty());
    }
    /**
     * UTC 9.3..2.2 –  Test GestoreIO – caricamento archivio per utenti quando l’archivio è non vuoto e la cache è vuota  
     */
    @Test
    public void testCaricaArchivioUtentiNonVuotoCacheVuota() {
        String pathUtenti = "archivioUtenti";
        GestoreIO<Utente> giou = new GestoreIO<>(pathUtenti);
        ObservableList<Utente> archivio = FXCollections.observableArrayList();
        archivio.add(new Utente("Francesco", "Pisaturo", "0612709311", "f.pisaturo1@studenti.unisa.it"));
        archivio.add(new Utente("Matteo", "Sirignano", "0612709365", "m.sirignano@studenti.unisa.it"));
        archivio.add(new Utente("Giovanni", "Scelzo", "0612709314", "g.scelzo44@studenti.unisa.it"));
        giou.salvaArchivio(archivio);

        ObservableList<Utente> retrieved = giou.caricaArchivio();

        for (int i = 0; i <= 2; i++) {
            assertEquals(archivio.get(i).getNome(), retrieved.get(i).getNome());
            assertEquals(archivio.get(i).getCognome(), retrieved.get(i).getCognome());
            assertEquals(archivio.get(i).getMatricola(), retrieved.get(i).getMatricola());
            assertEquals(archivio.get(i).getEmail(), retrieved.get(i).getEmail());
        }
    }
     /**
     * UTC 9.3.2.3 –  Test GestoreIO – caricamento archivio per utenti quando l’archivio è non vuoto e la cache è non vuota  
     */
    @Test
    public void testCaricaArchivioUtentiNonVuotoCacheNonVuota() {
        String pathUtenti = "archivioUtenti";
        GestoreIO<Utente> giou = new GestoreIO<>(pathUtenti);
        Utente u1 = new Utente("Francesco", "Pisaturo", "0612709311", "f.pisaturo1@studenti.unisa.it");
        Utente u2 = new Utente("Matteo", "Sirignano", "0612709365", "m.sirignano@studenti.unisa.it");
        Utente u3 = new Utente("Giovanni", "Scelzo", "0612709314", "g.scelzo44@studenti.unisa.it");
        Utente u4 = new Utente("Francesco", "Vecchione", "0612709314", "f.vecchione17@studenti.unisa.it");

        ObservableList<Utente> archivio = FXCollections.observableArrayList();
        archivio.add(u1);
        archivio.add(u3);
        giou.salvaArchivio(archivio);

        giou.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.CANCELLAZIONE, u1, null));
        giou.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.AGGIUNTA, null, u2));
        giou.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.MODIFICA, u3, u4));

        ObservableList<Utente> retrieved = giou.caricaArchivio();
        int index = retrieved.indexOf(u4);

        assertFalse(retrieved.contains(u1));
        assertTrue(retrieved.contains(u2));
        assertEquals(u4.getNome(), retrieved.get(index).getNome());
        assertEquals(u4.getCognome(), retrieved.get(index).getCognome());
        assertEquals(u4.getMatricola(), retrieved.get(index).getMatricola());
        assertEquals(u4.getEmail(), retrieved.get(index).getEmail());
    }
    /**
     * UTC 9.3.3.1 –  Test GestoreIO – caricamento archivio per prestiti quando l’archivio è vuoto e la cache è vuota  
     */
    @Test
    public void testCaricaArchivioPrestitiVuoto() {
        String pathPrestiti = "archivioPrestiti";
        GestoreIO<Prestito> giop = new GestoreIO<>(pathPrestiti);
        
        assertTrue(giop.caricaArchivio().isEmpty());
    }
    /**
     * UTC 9.3.3.2–  Test GestoreIO – caricamento archivio per prestiti quando l’archivio è non vuoto e la cache è vuota  
     */
    @Test
    public void testCaricaArchivioPrestitiNonVuotoCacheVuota() {
        String pathPrestiti = "archivioPrestiti";
        GestoreIO<Prestito> giop = new GestoreIO<>(pathPrestiti);
        ObservableList<Prestito> archivio = FXCollections.observableArrayList();
        archivio.add(new Prestito("0612709177", "9788883373893", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 10)));
        archivio.add(new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 8)));
        archivio.add(new Prestito("0612709812", "9788854175037", LocalDate.of(2025, 12, 13), LocalDate.of(2026, 1, 10)));
        giop.salvaArchivio(archivio);

        ObservableList<Prestito> retrieved = giop.caricaArchivio();

        for (int i = 0; i <= 2; i++) {
            assertEquals(archivio.get(i).getMatricolaUtente(), retrieved.get(i).getMatricolaUtente());
            assertEquals(archivio.get(i).getIsbnPrestito(), retrieved.get(i).getIsbnPrestito());
            assertEquals(archivio.get(i).getDataPrestito(), retrieved.get(i).getDataPrestito());
            assertEquals(archivio.get(i).getDataRestituzione(), retrieved.get(i).getDataRestituzione());
        }
    }
    /**
     * UTC 9.3.3.3 –  Test GestoreIO – caricamento archivio per prestiti quando l’archivio è non vuoto e la cache è non vuota  
     */
    @Test
    public void testCaricaArchivioPrestitiNonVuotoCacheNonVuota() {
        String pathPrestiti = "archivioPrestiti";
        GestoreIO<Prestito> giop = new GestoreIO<>(pathPrestiti);
        Prestito p1 = new Prestito("0612709177", "9788883373893", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 10));
        Prestito p2 = new Prestito("0612709314", "9788854175037", LocalDate.of(2025, 12, 12), LocalDate.of(2026, 1, 8));
        Prestito p3 = new Prestito("0612709812", "9788854175037", LocalDate.of(2025, 12, 13), LocalDate.of(2026, 1, 10));
        Prestito p4 = new Prestito("0612709812", "9788854175037", LocalDate.of(2025, 12, 13), LocalDate.of(2026, 1, 31));

        ObservableList<Prestito> archivio = FXCollections.observableArrayList();
        archivio.add(p3);
        archivio.add(p2);
        giop.salvaArchivio(archivio);

        giop.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.CANCELLAZIONE, p2, null));
        giop.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.AGGIUNTA, null, p1));
        giop.salvaModificaArchivio(new CacheRecord<>(TipoOperazione.MODIFICA, p3, p4));

        ObservableList<Prestito> retrieved = giop.caricaArchivio();
        int index = retrieved.indexOf(p4);

        assertFalse(retrieved.contains(p2));
        assertTrue(retrieved.contains(p1));
        assertEquals(p4.getMatricolaUtente(), retrieved.get(index).getMatricolaUtente());
        assertEquals(p4.getIsbnPrestito(), retrieved.get(index).getIsbnPrestito());
        assertEquals(p4.getDataPrestito(), retrieved.get(index).getDataPrestito());
        assertEquals(p4.getDataRestituzione(), retrieved.get(index).getDataRestituzione());
    }
}