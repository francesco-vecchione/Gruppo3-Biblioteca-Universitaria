package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @file Cassaforte.java
 * @author gruppo 3
 * @brief Questa classe astrae il concetto di cassaforte dove è mantenuta la password. Si occupa di salvarla criptandola su file e di restituirla.
 * @invariant
 * Il pathname del file dove è salvata la password non è null.
 */
public class Cassaforte {

    /**
     * @brief Stringa che mantiene il percorso del file dove è salvata la password.
     */
    private final String pathname;

    /**
     * @brief Costruttore che salva una copia del pathname passato in input.
     * @param[in] pathname  Il percorso del file dove verrà salvata la
     * password.
     * @pre
     * Il pathname passato in input non deve essere null.
     * @post
     * L'istanza di Cassaforte è creata con il pathname specificato.
     */
    public Cassaforte(String pathname) {
        this.pathname = pathname;
    }

    
    /**
     * @brief Cripta la password attraverso la funzione di hashing 
     * @param[in] passwordInChiaro La password in chiaro che deve essere criptata
     * @return Ritorna la password criptata
     */
    public int criptaPassword(String passwordInChiaro) {
        return passwordInChiaro.hashCode();
    }
    
    
    /**
     * @brief Cripta la password e la salva su file.
     * @param[in] passwordInChiaro  La password in chiaro da criptare e
     * salvare.
     * @return true se l'operazione di salvataggio ha successo, false altrimenti.
     * @pre
     * La password in chiaro non deve essere null.
     */
    public boolean salvaPasswordCriptata(String passwordInChiaro) {
        Integer hash = criptaPassword(passwordInChiaro);
        
        // Il costruttore ad un solo parametro di FileOutputStream sovrascrive di base 
        //      il contenuto del file pathname
        try (FileOutputStream fos = new FileOutputStream(pathname);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(hash);
        } catch (IOException e) {
            return false;
        }
        
        return true;
    }

    /**
     * @brief Recupera la password criptata dal file e la restituisce al chiamante.
     * @return L'intero rappresentante l'hash della password criptata, oppure un codice di errore.
     * @pre
     * Il file specificato da pathname deve esistere.
     */
    public int leggiPasswordCriptata() {
        
        Integer hash = -1;
        
        try(FileInputStream fis = new FileInputStream(pathname);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis)) {
            hash = (Integer) ois.readObject();
        } catch (EOFException e) {
            // Tutto bene, ha letto la password
        } catch (Exception e) {
            return -1;
        }
        
        return hash;
    }
}