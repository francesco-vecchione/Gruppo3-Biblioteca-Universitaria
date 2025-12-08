package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import java.io.Serializable;

/**
 * @author gruppo 3
 */
public interface Dato extends Comparable<Object>, Serializable {
    
    boolean isValid();
    
    boolean isFilled();
}
