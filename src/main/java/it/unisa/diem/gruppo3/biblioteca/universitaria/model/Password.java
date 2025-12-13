package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import it.unisa.diem.gruppo3.biblioteca.universitaria.io.Cassaforte;

/**
 * @author gruppo 3
 */

/**
    * @brief Ha lo scopo di implementare l'interfaccia ModelPassword, per questo
    * ne eredita le responsabilità
*/
public class Password implements ModelPassword {
    
    /**
        * @brief È la reference alla classe I/O che si occupa di memorizzare la password
    */
    private final Cassaforte cassaforte;
    
    /**
        * @brief È il costruttore di Password, e si occupa di istanziare Cassaforte
        * ed assegnarla al proprio parametro
        * 
        * @param[in] pathname La stringa che contiene il percorso del file dove si trova la password
    */
    public Password(String pathname) {
        cassaforte = new Cassaforte(pathname);
    }

    /**
        * @brief Viene fornito da ModelPassword, che si occupa di verificare che la password
        * inserita come argomento coincida con quella memorizzata nella cassaforte
        * 
        * @param[in] passwordInChiaro La password inserita dall'utente che verrà conforntata con quella
        *            memorizzata nella cassaforte
        * 
        * @return ritorna vero o falso a seconda della correttezza della password
        * 
        * @pre
        *   Il parametro deve essere non vuoto
    */
    @Override
    public boolean verificaPassword(String passwordInChiaro) {
        return cassaforte.leggiPasswordCriptata(passwordInChiaro) == cassaforte.criptaPassword(passwordInChiaro);
    }

    /**
        * @brief Viene fornito da ModelPassword, che si occupa di salvare l'hash della password
        * all'interno dell'archivio
        * 
        * @param[in] passwordInChiaro La nuova password che verrà memorizzata in archivio e che
        *            servirà ad accedere al programma
        * 
        * @return ritorna un booleano che simboleggia la riuscita dell'operazione
        * 
        * @post
        *   la password viene impostata
    */
    @Override
    public boolean impostaPassword(String passwordInChiaro) {
        return cassaforte.salvaPasswordCriptata(passwordInChiaro);
    }
}
