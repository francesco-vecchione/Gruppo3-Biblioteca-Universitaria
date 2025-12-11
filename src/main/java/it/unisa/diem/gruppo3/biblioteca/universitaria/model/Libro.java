package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
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
    private StringProperty titolo;

    /**
     * @brief Lista di uno o più autori separati da una virgola.
     */
    private StringProperty autori;

    /**
     * @brief Anno di pubblicazione del libro.
     */
    private IntegerProperty annoDiPubblicazione;

    /**
     * @brief Codice identificativo univoco del libro.
     */
    private StringProperty isbn;

    /**
     * @brief Numero di copie disponibili del libro.
     */
    private IntegerProperty numeroCopieDisponibili;

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
        this.titolo = new SimpleStringProperty(titolo);
        this.autori = new SimpleStringProperty(autori);
        this.annoDiPubblicazione = new SimpleIntegerProperty(annoDiPubblicazione);
        this.isbn = new SimpleStringProperty(isbn);
        this.numeroCopieDisponibili = new SimpleIntegerProperty(numeroCopieDisponibili);
    }

    /**
     * @brief Getter per il titolo.
     * @return Il titolo del libro.
     */
    public String getTitolo() {
        return titolo.get();
    }

    /**
     * @brief Getter per gli autori.
     * @return Gli autori del libro.
     */
    public String getAutori() {
        return autori.get(); 
    }

    /**
     * @brief Getter per l'anno di pubblicazione.
     * @return L'anno di pubblicazione.
     */
    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione.get(); 
    }

    /**
     * @brief Getter per l'ISBN.
     * @return Il codice ISBN.
     */
    public String getIsbn() {
        return isbn.get(); 
    }

    /**
     * @brief Getter per il numero di copie disponibili.
     * @return Il numero di copie.
     */
    public int getNumeroCopieDisponibili() {
        return numeroCopieDisponibili.get();
    }

    /**
     * @brief Setter per il titolo.
     * @param[in] titolo    Il nuovo titolo.
     */
    public void setTitolo(String titolo) {
        this.titolo.set(titolo);
    }

    /**
     * @brief Setter per gli autori.
     * @param[in] autori    I nuovi autori.
     */
    public void setAutori(String autori) {
        this.autori.set(autori);
    }

    /**
     * @brief Setter per l'anno di pubblicazione.
     * @param[in] annoPubblicazione Il nuovo anno di pubblicazione.
     */
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoDiPubblicazione.set(annoPubblicazione);
    }

    /**
     * @brief Setter per l'ISBN.
     * @param[in] isbn  Il nuovo codice ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    /**
     * @brief Setter per il numero di copie disponibili.
     * @param[in] numeroCopieDisponibili    Il nuovo numero di copie.
     */
    public void setNumeroCopieDisponibili(int numeroCopieDisponibili) {
        this.numeroCopieDisponibili.set(numeroCopieDisponibili);
    }

    /**
     * @brief Getter per la property del titolo.
     * @return La StringProperty associata al titolo.
     */
    public StringProperty titoloProperty() {
        return titolo; 
    }

    /**
     * @brief Getter per la property degli autori.
     * @return La StringProperty associata agli autori.
     */
    public StringProperty autoriProperty() {
        return autori; 
    }

    /**
     * @brief Getter per la property dell'anno di pubblicazione.
     * @return La IntegerProperty associata all'anno.
     */
    public IntegerProperty annoPubblicazioneProperty() {
        return annoDiPubblicazione; 
    }

    /**
     * @brief Getter per la property dell'ISBN.
     * @return La StringProperty associata all'ISBN.
     */
    public StringProperty isbnProperty() {
        return isbn; 
    }

    /**
     * @brief Getter per la property del numero di copie disponibili.
     * @return La IntegerProperty associata al numero di copie.
     */
    public IntegerProperty numeroCopieDisponibiliProperty() {
        return numeroCopieDisponibili; 
    }

    /**
     * @brief Contratto fornito dall'interfaccia Dato. Un oggetto Libro è valido 
     * se ha un numero di copie maggiore di zero e l'isbn rispetta il formato standard.
     * @return true se l'istanza è valida, false altrimenti.
     */
    @Override
    public boolean isValid() {
        return (numeroCopieDisponibili.get() > 0) && (isbn.get().matches("^(978|979)\\d{10}$")); 
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
        
        // Da finire
        return null;
    }
}