package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Libro;
import javafx.scene.control.TextField;

/**
 * @author gruppo 3
 */
public class UtilityPopUpLibri extends UtilityPopUp<Libro>{
    private TextField fieldTitolo;
    private TextField fieldAutori;
    private TextField fieldAnnoDiPubblicazione;
    private TextField fieldIsbn;
    private TextField fieldNumeroCopieDisponibili;
    
    public UtilityPopUpLibri() {
        
    }
    
    public UtilityPopUpLibri(Libro placeHolder) {
        
    }
    
    @Override
    public Libro getDatiInseriti() {
        return null;
    }
}
