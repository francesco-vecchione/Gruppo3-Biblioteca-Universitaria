package it.unisa.diem.gruppo3.biblioteca.universitaria.controller;

/**
 * @file
 * @brief Interfaccia che astrae il concetto di controller specifico per un tipo di dato
 * @author gruppo 3
 */
public interface ControllerDato {
    /**
     * @brief Si occupa di inizializzare gli event handlers per un particolare tipo di dato
     */
    void inizializzaEventHandlersSpecifici();
}
