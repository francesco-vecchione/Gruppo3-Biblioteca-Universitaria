package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

/**
 * @author gruppo 3
 */

/**
        * @brief Si occupa di astrarre l'oggetto Prestito e ne fornisce le sue
        * caratteristiche
*/
public class Prestito implements Dato {
    
    /**
        * @brief È la matricola dell'utente che ha richiesto il prestito
        * 
        * @invariant
        *   deve essere di un formato specifico
        * @pre
        *   l'utente corrispondente alla matricola deve essere registrato nell'archivio
    */
    private /*final*/ String matricolaUtente;
    
    /**
        * @brief È l'ISBN del libro richiesto dall'utente
        * 
        * @invariant
        *   deve essere di un formato specifico
        * 
        * @pre
        *   il libro corrispondente all'ISBN deve essere registrato nell'archivio
    */
    private /*final*/ String isbnPrestito;
    
    /**
        * @brief È la data in cui viene registrato il prestito
    */
    private /*final*/ LocalDate dataPrestito;
    
    /**
        * @brief È la data entro cui l'utente deve restituire il libro
        * 
        * @invariant
        *   deve essere di un tot superiore a dataPrestito
    */
    private /*final*/ LocalDate dataRestituzione;
    
    /**
        * @brief È un booleano che indica che l'utente è in ritardo nella restituzione
        * del libro
        * 
        * @invariant
        *   dataRestituzione deve essere almeno uguale alla data odierna
    */
    private boolean inRitardo;
    
    /**
        * @brief È un booleano che indica lo stato del prestito
        * 
        * @post
        *   indica se il prestito è "Attivo" o "Restituito"
    */
    private ObjectProperty<StatoPrestito> statoPrestito;
    
    /**
        * @brief È il costruttore che si occupa di inizializzare gli attributi
        * 
        * @invariant
        *   i parametri inseriti dal bibliotecario devono essere tutti validi
        * 
        * @param[out] matricolaUtente Matricola dell'utente che effettua il prestito
        * @param[out] isbn Codice univoco che identifica il libro
        * @param[out] dataPrestito Data in cui viene registrato il prestito
        * @param[out] dataRestituzione Data in cui l'utente dovrà resituire il libro
        * 
        * @post
        *   inizializza l'oggetto prestito con i dati inseriti dal bibliotecario
    */
    public Prestito(String matricolaUtente, String isbn, LocalDate dataPrestito, LocalDate dataRestituzione) {
        
    }
    
    /**
        * @brief get di matricolaUtente
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
        * 
        * @return ritorna la MatricolaUtente dell'utente che ha registrato il prestito
    */
    public String getMatricolaUtente() {
        return null;
    }
    
    /**
        * @brief get di isbnPrestito
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
        * 
        * @return ritorna l'ISBN del libro che è stato prestato
    */
    public String getIsbnPrestito() {
        return null;
    }
    
    /**
        * @brief get di dataPrestito
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
        * 
        * @return ritorna la data in cui è stato registrato il prestito
    */
    public LocalDate getDataPrestito() {
        return null;
    }
    
    /**
        * @brief get di dataRestituzione
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
        * 
        * @return ritorna la data in cui il libro deve essere restituito
    */
    public LocalDate getDataRestituzione() {
        return null;
    }
    
    /**
        * @brief get di statoPrestito
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
        * 
        * @return ritorna lo stato del prestito in "Attivo", che indica che il libro
        * è ancora in prestito, o in "Restituito" che indica che il prestito è stato concluso
        * con la restituzione del libro
    */
    public StatoPrestito getStatoPrestito() {
        return null;
    }
    
    /**
        * @brief Verifica che la data di restituzione del prestito non sia ancora arrivata
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
        * 
        * @return ritorna true se la data di restituzione coincide con quella odierna, false in caso
        * contrario
    */
    public boolean isInRitardo() {
        return false;
    }
    
    /**
        * @brief set di statoPrestito
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
        * 
        * @post
        *   assegna al prestito creato uno stato, "Attivo" o "Restituito"
    */
    public void setStatoPrestito(StatoPrestito statoPrestito) {
        
    }
    
    /**
        * @brief set di inRitardo
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
        * 
        * @post
        *   indica che l'utente non ha restituito il libro nel tempo debito
    */
    public void setInRitardo(boolean inRitardo) {
        
    }
    
    /**
        * @brief È la property che ci fa vedere lo stato di un prestito
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
    */
    public ObjectProperty<StatoPrestito> StatoPrestitoProperty() {
        return null;
    }
    
    /**
        * @brief È la property che ci fa vedere se un prestito è in ritardo o meno
        * 
        * @invariant
        *   deve esistere l'oggetto Prestito
    */
    public BooleanProperty inRitardoProperty() {
        return null;
    }

    /**
        * @brief È fornito dall'interfaccia Dato, e permette di registrare correttamente un prestito
        * solo se matricolaUtente e isbnPrestito rispettano i loro standard
        * 
        * @pre
        *   l'utente e il libro da inserire devono essere presenti negll'archivio
        * 
        * @post
        *   permette la registrazione o meno del prestito
    */
    @Override
    public boolean isValid() {
        return false;
    }

    /**
     * SCUSATE RAGAZZI, MA NON STA NELLA DOCUMENTAZIONE :-( 
    */
    @Override
    public boolean isFilled() {
        return false;
    }

    /**
        * @brief Messo a disposizione da Object, e verifica che due oggetti Prestito sono uguali
        * se hanno la stessa matricolaUtente e stesso isbnPrestito
        * 
        * @invariant
        *   l'altro prestito deve essere già presente nell'archivio
        * 
        * @param[in] o L'altro prestito con cui fare il confronto
        * 
        * @return ritorna true in caso positivo, false altrimenti
    */
    @Override
    public boolean equals(Object o) {
        return false;
    }
    
    /**
        * @brief È fornito da Comparable<T>, che permette di confrontare due oggetti 
        * Prestito sulla base della loro dataRestituzione
        * 
        * @param[in] o L'altro prestito con cui fare il confronto
        * 
        * @post
        *   permette il confronto tra due prestiti
    */
    @Override
    public int compareTo(Object o) {
        return -1;
    }
    
    /**
        * @brief È fornito da Object
        * 
        * @return ritorna la stampa dell'oggetto Prestito
    */
    @Override
    public String toString() {
        return null;
    }
}