package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;

/**
 * @author gruppo 3
 */
public class Prestito implements Dato {
    private /*final*/ String matricolaUtente;
    private /*final*/ String isbnPrestito;
    private /*final*/ LocalDate dataPrestito;
    private /*final*/ LocalDate dataRestituzione;
    private boolean inRitardo;
    private ObjectProperty<StatoPrestito> statoPrestito;
    
    public Prestito(String matricolaUtente, String isbn, LocalDate dataPrestito, LocalDate dataRestituzione) {
        
    }
    
    public String getMatricolaUtente() {
        return null;
    }
    
    public String getIsbnPrestito() {
        return null;
    }
    
    public LocalDate getDataPrestito() {
        return null;
    }
    
    public LocalDate getDataRestituzione() {
        return null;
    }
    
    public StatoPrestito getStatoPrestito() {
        return null;
    }
    
    public boolean isInRitardo() {
        return false;
    }
    
    public void setStatoPrestito(StatoPrestito statoPrestito) {
        
    }
    
    public void setInRitardo(boolean inRitardo) {
        
    }
    
    public ObjectProperty<StatoPrestito> StatoPrestitoProperty() {
        return null;
    }
    
    public BooleanProperty inRitardoProperty() {
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
