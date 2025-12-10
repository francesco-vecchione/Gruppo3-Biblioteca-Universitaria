/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

/**
 *
 * @author gruppo 3
 * @brief Questa classe specializza UtilityPopUp per il caso di log in
 */
public class UtilityPopUpLogin extends UtilityPopUp {
    /**
     * @brief Campo di testo per l'inserimento della password
     */
    private TextField txfPassword;
    
    /**
     * @brief Link per procedere alla procedura di recupero della password
     */
    private Hyperlink linkPasswordDimenticata;

    /**
     * @brief Costruttore di default che aggiunge al layout base i campi di testo
     * specifici per la procedura di login
     */
    public UtilityPopUpLogin() {
    }

    /**
     * @brief Getter per il campo di testo per la password
     * @return Campo di testo per la password
     */
    public TextField getTxfPassword() {
        return txfPassword;
    }

    /**
     * @brief Getter per il link alla procedura di recupero password
     * @return Link per la procedura di recupero password 
     */
    public Hyperlink getLinkPasswordDimenticata() {
        return linkPasswordDimenticata;
    }
}
