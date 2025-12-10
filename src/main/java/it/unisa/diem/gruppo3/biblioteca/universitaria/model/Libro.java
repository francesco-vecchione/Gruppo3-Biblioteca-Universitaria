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
    }

    /**
     * @brief Getter per il titolo.
     * @return Il titolo del libro.
     */
    public String getTitolo() {
        return null;
    }

    /**
     * @brief Getter per gli autori.
     * @return Gli autori del libro.
     */
    public String getAutori() {
        return null; 
    }

    /**
     * @brief Getter per l'anno di pubblicazione.
     * @return L'anno di pubblicazione.
     */
    public int getAnnoDiPubblicazione() {
        return -1; 
    }

    /**
     * @brief Getter per l'ISBN.
     * @return Il codice ISBN.
     */
    public String getIsbn() {
        return null; 
    }

    /**
     * @brief Getter per il numero di copie disponibili.
     * @return Il numero di copie.
     */
    public int getNumeroCopieDisponibili() {
        return -1;
    }

    /**
     * @brief Setter per il titolo.
     * @param[in] titolo    Il nuovo titolo.
     */
    public void setTitolo(String titolo) {
    }

    /**
     * @brief Setter per gli autori.
     * @param[in] autori    I nuovi autori.
     */
    public void setAutori(String autori) {
    }

    /**
     * @brief Setter per l'anno di pubblicazione.
     * @param[in] annoPubblicazione Il nuovo anno di pubblicazione.
     */
    public void setAnnoPubblicazione(int annoPubblicazione) {
    }

    /**
     * @brief Setter per l'ISBN.
     * @param[in] isbn  Il nuovo codice ISBN.
     */
    public void setIsbn(String isbn) {
    }

    /**
     * @brief Setter per il numero di copie disponibili.
     * @param[in] numeroCopieDisponibili    Il nuovo numero di copie.
     */
    public void setNumeroCopieDisponibili(int numeroCopieDisponibili) {
    }

    /**
     * @brief Getter per la property del titolo.
     * @return La StringProperty associata al titolo.
     */
    public StringProperty titoloProperty() {
        return null; 
    }

    /**
     * @brief Getter per la property degli autori.
     * @return La StringProperty associata agli autori.
     */
    public StringProperty autoriProperty() {
        return null; 
    }

    /**
     * @brief Getter per la property dell'anno di pubblicazione.
     * @return La IntegerProperty associata all'anno.
     */
    public IntegerProperty annoPubblicazioneProperty() {
        return null; 
    }

    /**
     * @brief Getter per la property dell'ISBN.
     * @return La StringProperty associata all'ISBN.
     */
    public StringProperty isbnProperty() {
        return null; 
    }

    /**
     * @brief Getter per la property del numero di copie disponibili.
     * @return La IntegerProperty associata al numero di copie.
     */
    public IntegerProperty numeroCopieDisponibiliProperty() {
        return null; 
    }

    /**
     * @brief Contratto fornito dall'interfaccia Dato. Un oggetto Libro è valido se ha un numero di copie maggiore di zero e l'isbn rispetta il formato standard.
     * @return true se l'istanza è valida, false altrimenti.
     */
    @Override
    public boolean isValid() {
        return false; 
    }

    /**
     * @brief Contratto fornito dalla classe Object. Due oggetti Libro sono uguali se hanno isbn uguale.
     * @param[in] o L'oggetto con cui confrontare l'istanza corrente.
     * @return true se gli oggetti sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        return false; 
    }

    /**
     * @brief Contratto fornito dall'interfaccia Comparable. Due oggetti Libro sono confrontati sulla base del titolo.
     * @param[in] o L'oggetto da confrontare.
     * @return Un intero negativo, zero o positivo a seconda dell'ordine alfabetico dei titoli.
     */
    @Override
    public int compareTo(Object o) {
        return -1; 
    }

    /**
     * @brief Contratto fornito dalla classe Object. Restituisce una rappresentazione in stringa dell'oggetto.
     * @return Una stringa contenente Titolo e ISBN.
     */
    @Override
    public String toString() {
        return null;
    }
}