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
    */
    private /*final*/ String matricolaUtente;
    
    /**
        * @brief È l'ISBN del libro richiesto dall'utente
    */
    private /*final*/ String isbnPrestito;
    
    /**
        * @brief È la data in cui viene registrato il prestito
    */
    private /*final*/ LocalDate dataPrestito;
    
    /**
        * @brief È la data entro cui l'utente deve restituire il libro
    */
    private /*final*/ LocalDate dataRestituzione;
    
    /**
        * @brief È un booleano che indica lo stato del prestito
    */
    private ObjectProperty<StatoPrestito> statoPrestito;
    
    /**
        * @brief È il costruttore che si occupa di inizializzare gli attributi
        * 
        * @param[in] matricolaUtente Matricola dell'utente che effettua il prestito
        * @param[in] isbnPrestito Codice univoco che identifica il libro
        * @param[in] dataPrestito Data in cui viene registrato il prestito
        * @param[in] dataRestituzione Data in cui l'utente dovrà resituire il libro
        *  
        * @pre
        *   l'utente corrispondente alla matricola deve essere registrato nell'archivio 
        * @pre
        *   il libro corrispondente all'ISBN deve essere registrato nell'archivio
        * 
        * @post
        *   inizializza l'oggetto prestito con i dati inseriti dal bibliotecario
    */
    public Prestito(String matricolaUtente, String isbnPrestito, LocalDate dataPrestito, LocalDate dataRestituzione) {
        
    }
    
    /**
        * @brief Getter di matricolaUtente
        * 
        * @return Ritorna la MatricolaUtente dell'utente che ha registrato il prestito
    */
    public String getMatricolaUtente() {
        return null;
    }
    
    /**
        * @brief Getter di isbnPrestito
        * 
        * @return Ritorna l'ISBN del libro che è stato prestato
    */
    public String getIsbnPrestito() {
        return null;
    }
    
    /**
        * @brief Getter di dataPrestito
        * 
        * @return Ritorna la data in cui è stato registrato il prestito
    */
    public LocalDate getDataPrestito() {
        return null;
    }
    
    /**
        * @brief Getter di dataRestituzione
        * 
        * @return Ritorna la data in cui il libro deve essere restituito
    */
    public LocalDate getDataRestituzione() {
        return null;
    }
    
    /**
        * @brief Getter di statoPrestito
        * 
        * @return Ritorna lo stato del prestito in "Attivo", che indica che il libro
        * è ancora in prestito, o in "Restituito" che indica che il prestito è stato concluso
        * con la restituzione del libro
    */
    public StatoPrestito getStatoPrestito() {
        return null;
    }
    
    /**
        * @brief set di statoPrestito
        * 
        * @post
        *   Assegna al prestito creato uno stato, "Attivo" o "Restituito"
    */
    public void setStatoPrestito(StatoPrestito statoPrestito) {
        
    }
    
    /**
        * @brief È la property che ci fa vedere lo stato di un prestito
    */
    public ObjectProperty<StatoPrestito> statoPrestitoProperty() {
        return null;
    }

    /**
        * @brief È fornito dall'interfaccia Dato, e permette di registrare correttamente un prestito
        * solo se dataRestituzione è "maggiore" di dataPrestito
        * @return ritorna true se i dati rispettano lo standard 
    */
    @Override
    public boolean isValid() {
        return false;
    }

    /**
        * @brief Messo a disposizione da Object, e verifica che due oggetti Prestito sono uguali
        * se hanno la stessa matricolaUtente e stesso isbnPrestito
        * 
        * @param[in] o L'altro prestito con cui fare il confronto
        * 
        * @return ritorna true nel caso i due oggetti hanno la stessa matricola e lo stesso isbn, 
        * false altrimenti
    */
    @Override
    public boolean equals(Object o) {
        return false;
    }
    
    /**
        * @brief È fornito da Comparable, che permette di confrontare due oggetti 
        * Prestito sulla base della loro dataRestituzione, dalla data più piccola (vicina)
        * alla più grande (lontana)
        * 
        * @param[in] o L'altro prestito con cui fare il confronto
        *
        * @return Ritorna un valore numerico:
        *   positivo se l'oggetto corrente "maggiore" di o;
        *   0 se l'oggetto corrente è "uguale" ad o;
        *   negativo se l'oggetto corrente è "minore" di o
        */
    @Override
    public int compareTo(Object o) {
        return -1;
    }
    
    /**
        * @brief È fornito da Object
        * 
        * @return Ritorna la stampa dell'oggetto Prestito
    */
    @Override
    public String toString() {
        return null;
    }
}