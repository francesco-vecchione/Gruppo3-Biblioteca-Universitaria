package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author gruppo 3
 */

/**
    * @brief Astrae l'oggetto Utente
    * @invariant
    * Il campo matricola deve essere composto da sole 10 cifre
    * @invariant
    * Il campo email deve essere formato dal'iniziale del nome, seguita da 
    * un punto, dal cognome, opzionalmente un numero e dal dominio "@studenti.unisa.it" 
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
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
        this.matricola = new SimpleStringProperty(matricola);
        this.email = new SimpleStringProperty(email);
    }
    
    /**
        * @brief get di nome
        * 
        * @return Ritorna il nome dell'utente
    */
    public String getNome() {
        return nome.get();
    }
    
    /**
        * @brief get di cognome
        * 
        * @return Ritorna il cognome dell'utente
    */
    public String getCognome() {
        return cognome.get();
    }
    
    /**
        * @brief get di matricola
        * 
        * @return Ritorna la matricola dell'utente
    */
    public String getMatricola() {
        return matricola.get();
    }
    
    /**
        * @brief get di email
        * 
        * @return Ritorna la mail dell'utente
    */
    public String getEmail() {
        return email.get();
    }
    
    /**
        * @brief set di nome
        * 
        * @param[in] nome Il nuovo valore di nome
        * @pre Nome non deve essere null
        * @post Assegna all'utente il nome scritto dal bibliotecario
    */
    public void setNome(String nome) {
        this.nome.set(nome);
    }
    
    /**
        * @brief set di cognome
        * 
        * @param[in] cognome Il nuovo valore di cognome
        * @pre Cognome non deve essere null
        * @post Assegna all'utente il cognome scritto dal bibliotecario
    */
    public void setCognome(String cognome) {
        this.cognome.set(cognome);
    }
    
    /**
        * @brief set di matricola
        * 
        * @param[in] matricola Il nuovo valore della matricola
        * @pre Matricola non deve essere null
        * @post Assegna all'utente la matricola scritta dal bibliotecario
    */
    public void setMatricola(String matricola) {
        this.matricola.set(matricola);
    }
    
    /**
        * @brief set di email
        * 
        * @param[in] email Il nuovo valore di email 
        * @pre Email non deve essere null
        * @post Assegna all'utente la mail scritta dal bibliotecario
    */
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    /**
        * @brief getter della property nome
        * @return Il wrapper property per il nome
    */
    public StringProperty nomeProperty() {
        return nome;
    }
    
    /**
        * @brief getter della property cognome
        * @return Il wrapper property per il cognome
    */
    public StringProperty cognomeProperty() {
        return cognome;
    }
    
    /**
        * @brief getter della property matricola
        * @return Il wrapper property per la matricola
    */
    public StringProperty matricolaProperty() {
        return matricola;
    }
    
    /**
        * @brief getter della property email
        * @return Il wrapper property per l'email
    */
    public StringProperty emailProperty() {
        return email;
    }

    /**
        * @brief Messo a disposizione da Dato, e verifica che un oggetto Utente è valido
        * se e solo se la matricola e la mail rispettano il loro standard
        *  
        * @return Ritorna true se i dati sono corretti, false altrimenti
    */
    @Override
    public boolean isValid() {
        
        // replaceAll(" ", "") elimina tutti i caratteri spazio
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.getNome().toLowerCase().replaceAll(" ", "").charAt(0));
        buffer.append(".");
        buffer.append(this.getCognome().toLowerCase().replaceAll(" ", ""));
        buffer.append("(\\d*)");
        buffer.append("@studenti.unisa.it");
        
        return (this.getMatricola().matches("^\\d{10}$")) && (this.getEmail().matches(buffer.toString()));
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
        if(o == null) return false;
        if(this == o) return true;
        if(this.getClass() != o.getClass()) return false;
        
        Utente tmp = (Utente) o;
        return this.getMatricola().equals(tmp.getMatricola());
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
    * 
    * @pre
    * o non deve essere di tipo Utente e non deve essere null
    */
    @Override
    public int compareTo(Object o) {
        Utente tmp = (Utente) o; 
        
        return (this.getNome() + this.getCognome()).replaceAll(" ", "").compareToIgnoreCase((tmp.getNome() + tmp.getCognome()).replaceAll(" ", ""));
    }
    
    /**
        * @brief È fornito da Object
        * 
        * @return ritorna la stampa dell'oggetto Utente
    */
    @Override
    public String toString() {
        
        // Da fare
        return null;
    }
}
