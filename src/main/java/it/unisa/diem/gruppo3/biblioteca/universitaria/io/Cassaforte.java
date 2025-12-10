package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

/**
 * @author gruppo 3
 * @brief Questa classe astrae il concetto di cassaforte dove è mantenuta la password. Si occupa di salvarla criptandola su file e di restituirla.
 * @invariant
 * Il pathname del file dove è salvata la password non è null.
 */
public class Cassaforte {

    /**
     * @brief Stringa che mantiene il percorso del file dove è salvata la password.
     */
    private String pathname;

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
        return false;
    }

    /**
     * @brief Recupera la password criptata dal file e la restituisce al chiamante.
     * @return L'intero rappresentante l'hash della password criptata, oppure un codice di errore.
     * @pre
     * Il file specificato da pathname deve esistere.
     */
    public int leggiPasswordCriptata() {
        return -1;
    }
}