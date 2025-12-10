/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

/**
 *
 * @author 39379
 */
public class UtilityPopUpLogin extends UtilityPopUp {
    private TextField txfPassword;
    private Hyperlink linkPasswordDimenticata;

    public UtilityPopUpLogin() {
    }

    public TextField getTxfPassword() {
        return txfPassword;
    }

    public Hyperlink getLinkPasswordDimenticata() {
        return linkPasswordDimenticata;
    }
}
