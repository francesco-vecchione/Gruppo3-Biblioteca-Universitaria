package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import javafx.beans.property.StringProperty;

/**
 * @author gruppo 3
 */

/**
    * @brief Astrae l'oggetto Utente
    * @invariant
    * Il campo matricola deve essere composto da sole 10 cifre
    * @invariant
    * Il campo email dee essere formato dal'iniziale del nome, seguita da 
    * un punto, dal cognome, da un numero e dal dominio "@studenti.unisa.it" 
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
    */
    private StringProperty matricola;
    
    /**
        * @brief nome dell'utente
    */
    private StringProperty email;
    
    /**
        * @brief Costruttore che si occupa di inizializzare gli attributi
        * 
        * @param[in] nome Il nome dell'utente
        * @param[in] cognome Il cognome dell'utente
        * @param[in] matricola La matricola dell'utente
        * @param[in] email L'email dell'utente
        * 
        * @post
        *   Viene creato un utente con gli attributi specificati dal bibliotecario
    */
    public Utente(String nome, String cognome, String matricola, String email) {
        
    }
    
    /**
        * @brief get di nome
        * 
        * @return Ritorna il nome dell'utente
    */
    public String getNome() {
        return null;
    }
    
    /**
        * @brief get di cognome
        * 
        * @return Ritorna il cognome dell'utente
    */
    public String getCognome() {
        return null;
    }
    
    /**
        * @brief get di matricola
        * 
        * @return Ritorna la matricola dell'utente
    */
    public String getMatricola() {
        return null;
    }
    
    /**
        * @brief get di email
        * 
        * @return Ritorna la mail dell'utente
    */
    public String getEmail() {
        return null;
    }
    
    /**
        * @brief set di nome
        * 
        * @param[in] nome Il nuovo valore di nome
        * 
        * @post Assegna all'utente il nome scritto dal bibliotecario
    */
    public void setNome(String nome) {
        
    }
    
    /**
        * @brief set di cognome
        * 
        * @param[in] cognome Il nuovo valore di cognome
        * 
        * @post Assegna all'utente il cognome scritto dal bibliotecario
    */
    public void setCognome(String cognome) {
        
    }
    
    /**
        * @brief set di matricola
        * 
        * @param[in] matricola Il nuovo valore della matricola
        * 
        * @post Assegna all'utente la matricola scritta dal bibliotecario
    */
    public void setMatricola(String matricola) {
        
    }
    
    /**
        * @brief set di email
        * 
        * @param[in] email Il nuovo valore di email 
        * 
        * @post Assegna all'utente la mail scritta dal bibliotecario
    */
    public void setEmail(String email) {
        
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
        * @return Ritorna true se i dati sono corretti, false altrimenti
    */
    @Override
    public boolean isValid() {
        return false;
    }

    /**
        * @brief Messo a disposizione da Object che verifica l'uguaglianza di due oggetti
        * Utente in base alla loro matricola
        * 
        * @param[in] o L'altro Utente con cui fare la comparazione
        *      
        * @return Ritorna true se i due oggetti hanno la stessa matricola, false in caso contrario
    */
    @Override
    public boolean equals(Object o) {
        return false;
    }
    
    /**
    * @brief Fornito da Comparable, permette di confrontare due oggetti utente sulla base
    * del loro nome e cognome
    * 
    * @param[in] o L'altro Utente con cui fare la comparazione
    * 
    * @return Ritorna un valore numerico:
    *   positivo se l'oggetto corrente è "maggiore" di o;
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
        * @return ritorna la stampa dell'oggetto Utente
    */
    @Override
    public String toString() {
        return null;
    }
}
