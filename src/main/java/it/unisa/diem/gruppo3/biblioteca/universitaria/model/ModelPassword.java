package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

/**
 * @author gruppo 3
 */
public interface ModelPassword {

    boolean verificaPassword(String passwordInChiaro);
    
    boolean impostaPassword(String passwordInChiaro);
}
