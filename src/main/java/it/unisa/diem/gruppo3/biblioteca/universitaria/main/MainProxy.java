package it.unisa.diem.gruppo3.biblioteca.universitaria.main;

/**
 * @file MainProxy.java
 * @author gruppo 3
    * @brief Questa classe rappresenta un "entry point" del programma, in quanto l'unico servizio
    * che offre è fare da "proxy" per lanciare il programma. Questo per via di un bug dell'IDE
    * che non permetteva di lanciare direttamente la classe che estende Application con una versione
    * di Java superiore a Java8 
    */
public class MainProxy {
    
    /**
    * @brief Lancia la classe AppBibliotecaUniversitaria 
    * @param[in] args non viene mai effettivamente usato
    */
    public static void main(String[] args) {
        AppBibliotecaUniversitaria.launchProxy(args);
    }
}
