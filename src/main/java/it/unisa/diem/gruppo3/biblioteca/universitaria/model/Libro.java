package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * @author gruppo 3
 */
public class Libro implements Dato {
    private StringProperty titolo;
    private StringProperty autori;
    private IntegerProperty annoDiPubblicazione;
    private StringProperty isbn;
    private IntegerProperty numeroCopieDisponibili;
    
    public Libro(String titolo, String autori, int annoDiPubblicazione, String isbn, int numeroCopieDisponibili) {
        
    }
    
    public String getTitolo() {
        return null;
    }
    
    public String getAutori() {
        return null;
    }
    
    public int getAnnoPubblicazione() {
        return -1;
    }
    
    public String getIsbn() {
        return null;
    }
    
    public int getNumeroCopieDisponibili() {
        return -1;
    }
    
    public void setTitolo(String titolo) {
        
    }
    
    public void setAutori(String autori) {
        
    }
    
    public void setAnnoPubblicazione(int annoPubblicazione) {
        
    }
    
    public void setIsbn(String isbn) {
        
    }
    
    public void setNumeroCopieDisponibili(int numeroCopieDisponibili) {
        
    }
    
    public StringProperty titoloProperty() {
        return null;
    }
    
    public StringProperty autoriProperty() {
        return null;
    }
    
    public IntegerProperty annoPubblicazioneProperty() {
        return null;
    }
    
    public StringProperty isbnProperty() {
        return null;
    }
    
    public IntegerProperty numeroCopieDisponibiliProperty() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isFilled() {
        return false;
    }
    
    @Override
    public boolean equals(Object o) {
        return false;
    }
    
    @Override
    public int compareTo(Object o) {
        return -1;
    }   
    
    @Override
    public String toString() {
        return null;
    }
}
