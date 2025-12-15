package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

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
    private final String nome;
    
    /**
        * @brief cognome dell'utente
    */
    private final String cognome;
    
    /**
        * @brief matricola dell'utente
    */
    private final String matricola;
    
    /**
        * @brief nome dell'utente
    */
    private final String email;
    
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
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.email = email;
    }
    
    /**
        * @brief get di nome
        * 
        * @return Ritorna il nome dell'utente
    */
    public String getNome() {
        return nome;
    }
    
    /**
        * @brief get di cognome
        * 
        * @return Ritorna il cognome dell'utente
    */
    public String getCognome() {
        return cognome;
    }
    
    /**
        * @brief get di matricola
        * 
        * @return Ritorna la matricola dell'utente
    */
    public String getMatricola() {
        return matricola;
    }
    
    /**
        * @brief get di email
        * 
        * @return Ritorna la mail dell'utente
    */
    public String getEmail() {
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
        return matricola + ": " + nome + " " + cognome + " - " + email;
    }
}
