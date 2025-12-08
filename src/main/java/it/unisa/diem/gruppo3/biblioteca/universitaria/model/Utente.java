package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import javafx.beans.property.StringProperty;

/**
 * @author gruppo 3
 */
public class Utente implements Dato {
    private StringProperty nome;
    private StringProperty cognome;
    private StringProperty matricola;
    private StringProperty email;
    
    public Utente(String nome, String cognome, String matricola, String email) {
        
    }
    
    public String getNome() {
        return null;
    }
    
    public String getCogome() {
        return null;
    }
    
    public String getMatricola() {
        return null;
    }
    
    public String getEmail() {
        return null;
    }
    
    public void setNome() {
        
    }
    
    public void setCogome() {
        
    }
    
    public void setMatricola() {
        
    }
    
    public void setEmail() {
        
    }
    
    public StringProperty nomeProperty() {
        return null;
    }
    
    public StringProperty cognomeProperty() {
        return null;
    }
    
    public StringProperty matricolaProperty() {
        return null;
    }
    
    public StringProperty emailProperty() {
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
