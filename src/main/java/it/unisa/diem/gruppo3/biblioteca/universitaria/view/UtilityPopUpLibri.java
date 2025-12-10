package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import javafx.scene.control.TextField;

/**
 * @author gruppo 3
 */
public class UtilityPopUpLibri extends UtilityPopUp{
    private TextField txfTitolo;
    private TextField txfAutori;
    private TextField txfAnnoPubblicazione;
    private TextField txfIsbn;
    private TextField txfNumCopie;
    
    public UtilityPopUpLibri() {
        
    }

    public TextField getTxfTitolo() {
        return txfTitolo;
    }

    public TextField getTxfAutori() {
        return txfAutori;
    }

    public TextField getTxfAnnoPubblicazione() {
        return txfAnnoPubblicazione;
    }

    public TextField getTxfIsbn() {
        return txfIsbn;
    }

    public TextField getTxfNumCopie() {
        return txfNumCopie;
    }
}
