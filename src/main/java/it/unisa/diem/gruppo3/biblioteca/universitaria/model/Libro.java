package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

/**
 * @file Libro.java
 * @author gruppo 3
 * @brief Questa classe astrae l'oggetto libro.
 * @invariant
 * Il numero di copie disponibili deve essere maggiore di zero.
 * @invariant
 * L'ISBN deve rispettare il formato standard (13 cifre, prefisso 978 o 979).
 */
public class Libro implements Dato {

    /**
     * @brief Titolo del libro.
     */
    private final String titolo;

    /**
     * @brief Lista di uno o più autori separati da una virgola.
     */
    private final String autori;

    /**
     * @brief Anno di pubblicazione del libro.
     */
    private final int annoDiPubblicazione;

    /**
     * @brief Codice identificativo univoco del libro.
     */
    private final String isbn;

    /**
     * @brief Numero di copie disponibili del libro.
     */
    private int numeroCopieDisponibili;

    /**
     * @brief Costruttore che si occupa di inizializzare gli attributi.
     * @param[in] titolo                    Titolo del libro.
     * @param[in] autori                    Autori del libro.
     * @param[in] annoDiPubblicazione       Anno di pubblicazione.
     * @param[in] isbn                      Codice ISBN.
     * @param[in] numeroCopieDisponibili    Numero di copie disponibili.
     * @post
     * L'istanza è creata e tutti gli attributi sono inizializzati.
     */
    public Libro(String titolo, String autori, int annoDiPubblicazione, String isbn, int numeroCopieDisponibili) {
        this.titolo = titolo;
        this.autori = autori;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.isbn = isbn;
        this.numeroCopieDisponibili = numeroCopieDisponibili;
    }

    /**
     * @brief Getter per il titolo.
     * @return Il titolo del libro.
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @brief Getter per gli autori.
     * @return Gli autori del libro.
     */
    public String getAutori() {
        return autori; 
    }

    /**
     * @brief Getter per l'anno di pubblicazione.
     * @return L'anno di pubblicazione.
     */
    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione; 
    }

    /**
     * @brief Getter per l'ISBN.
     * @return Il codice ISBN.
     */
    public String getIsbn() {
        return isbn; 
    }

    /**
     * @brief Getter per il numero di copie disponibili.
     * @return Il numero di copie.
     */
    public int getNumeroCopieDisponibili() {
        return numeroCopieDisponibili;
    }
    
    /**
     * @brief Metodo per decrementare la disponibilità del libro
     */
    public void prestaCopia() {
        numeroCopieDisponibili--;
    }
    
    /**
     * @brief Metodo per decrementare la disponibilità del libro
     */
    public void restituisciCopia() {
        numeroCopieDisponibili++;
    }
    
    /**
     * @brief Metodo azzerare le copie disponibili
     */
    public void azzeraCopie() {
        numeroCopieDisponibili = 0;
    }
    
    
    /**
     * @brief Contratto fornito dall'interfaccia Dato. Un oggetto Libro è valido 
     * se ha un numero di copie maggiore di zero e l'isbn rispetta il formato standard.
     * @return true se l'istanza è valida, false altrimenti.
     */
    @Override
    public boolean isValid() {
        return (getNumeroCopieDisponibili() > 0) && (getIsbn().matches("^(978|979)\\d{10}$")); 
    }

    /**
     * @brief Contratto fornito dalla classe Object. Due oggetti Libro sono uguali se hanno isbn uguale.
     * @param[in] o L'oggetto con cui confrontare l'istanza corrente.
     * @return true se gli oggetti sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(this == o) return true;
        if(this.getClass() != o.getClass()) return false;
        
        Libro tmp = (Libro) o; 
        return this.getIsbn().equals(tmp.getIsbn()); 
    }

    /**
     * @brief Contratto fornito dall'interfaccia Comparable. Due oggetti Libro sono confrontati sulla base del titolo.
     * @param[in] o L'oggetto da confrontare.
     * @return Un intero negativo, zero o positivo a seconda dell'ordine alfabetico dei titoli.
     * @pre 
     * o deve essere di tipo Libro e non deve essere null
     */
    @Override
    public int compareTo(Object o) {
        Libro tmp = (Libro) o;
        
        // replaceAll(" ", "") elimina gli spazi tra le parole, o meglio tutti i caratteri spazio
        return this.getTitolo().replaceAll(" ", "").compareToIgnoreCase(tmp.getTitolo().replaceAll(" ", "")); 
    }

    /**
     * @brief Contratto fornito dalla classe Object. Restituisce una rappresentazione in stringa dell'oggetto.
     * @return Una stringa contenente Titolo e ISBN.
     */
    @Override
    public String toString() {        
        return isbn + ":" + titolo + " - " + autori + ", " + annoDiPubblicazione;
    }
}