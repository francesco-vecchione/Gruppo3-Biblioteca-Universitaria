package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import javafx.beans.property.StringProperty;

/**
 * @author gruppo 3
 */

/**
    * @brief Astrae l'oggetto Utente
*/
public class Utente implements Dato {
    
    /**
        * @brief nome dell'utente
    */
    private StringProperty nome;
    
    /**
        * @brief cognome dell'utente
    */
    private StringProperty cognome;
    
    /**
        * @brief matricola dell'utente
        * 
        * @invariant deve essere di un formato specifico
    */
    private StringProperty matricola;
    
    /**
        * @brief nome dell'utente
        * 
        * @invariant deve essere di un formato specifico
    */
    private StringProperty email;
    
    /**
        * @brief Costruttore che si occupa di inizializzare gli attributi
        * 
        * @param[out] nome Il nome dell'utente
        * @param[out] cognome Il cognome dell'utente
        * @param[out] matricola La matricola dell'utente
        * @param[out] email L'email dell'utente
        * 
        * @post
        *   viene creato un utente con gli attributi specificati dal bibliotecario
    */
    public Utente(String nome, String cognome, String matricola, String email) {
        
    }
    
    /**
        * @brief get di nome
        * 
        * @invariant
        *   deve esistere l'oggetto Utente
        * 
        * @return ritorna il nome dell'utente
    */
    public String getNome() {
        return null;
    }
    
    /**
        * @brief get di cognome
        * 
        * @invariant
        *   deve esistere l'oggetto Utente
        * 
        * @return ritorna il cognome dell'utente
    */
    public String getCognome() {
        return null;
    }
    
    /**
        * @brief get di matricola
        * 
        * @invariant
        *   deve esistere l'oggetto Utente
        * 
        * @return ritorna la matricola dell'utente
    */
    public String getMatricola() {
        return null;
    }
    
    /**
        * @brief get di email
        * 
        * @invariant
        *   deve esistere l'oggetto Utente
        * 
        * @return ritorna la mail dell'utente
    */
    public String getEmail() {
        return null;
    }
    
    /**
        * @brief set di nome
        * 
        * @invariant
        *   deve esistere l'oggetto Utente
        * 
        * @post assegna all'utente il nome scritto dal bibliotecario
    */
    public void setNome() {
        
    }
    
    /**
        * @brief set di cognome
        * 
        * @invariant
        *   deve esistere l'oggetto Utente
        * 
        * @post assegna all'utente il cognome scritto dal bibliotecario
    */
    public void setCognome() {
        
    }
    
    /**
        * @brief set di matricola
        * 
        * @invariant
        *   deve esistere l'oggetto Utente
        * 
        * @post assegna all'utente la matricola scritta dal bibliotecario
    */
    public void setMatricola() {
        
    }
    
    /**
        * @brief set di email
        * 
        * @invariant
        *   deve esistere l'oggetto Utente
        * 
        * @post assegna all'utente la mail scritta dal bibliotecario
    */
    public void setEmail() {
        
    }
    
    /**
        * @brief getter della property nome
    */
    public StringProperty nomeProperty() {
        return null;
    }
    
    /**
        * @brief getter della property cognome
    */
    public StringProperty cognomeProperty() {
        return null;
    }
    
    /**
        * @brief getter della property matricola
    */
    public StringProperty matricolaProperty() {
        return null;
    }
    
    /**
        * @brief getter della property email
    */
    public StringProperty emailProperty() {
        return null;
    }

    /**
        * @brief Messo a disposizione da Dato, e verifica che un oggetto Utente è valido
        * se e solo se la matricola e la mail rispettano il loro standard
        *  
        * @return ritorna true se i dati sono corretti, false altrimenti
    */
    @Override
    public boolean isValid() {
        return false;
    }
    
    /**
     * sCUSATE ANCORA, MA NON CI STA :-(
    */
    @Override
    public boolean isFilled() {
        return false;
    }

    /**
        * @brief Messo a disposizione da Object che verifica l'uguaglianza di due oggetti
        * Utente in base alla loro matricola
        * 
        * @invariant
        *   entrambi gli oggetti Utente devono esistere
        * 
        * @param[o] L'altro Utente con cui fare la comparazione
        * 
        * @return ritorna true se i due oggetti hanno la stessa matricola, false in caso contrario
    */
    @Override
    public boolean equals(Object o) {
        return false;
    }
    
    /**
    * @brief Fornito da Comparable, permette di confrontare due oggetti utente sulla base
    * del loro nome e cognome
    * 
    * @param[o] L'altro Utente con cui fare la comparazione
*/
    @Override
    public int compareTo(Object o) {
        return -1;
    }
    
    /**
        * @brief È fornito da Object
        * 
        * @return ritorna la stampa dell'oggetto Utente
    */
    @Override
    public String toString() {
        return null;
    }
}
