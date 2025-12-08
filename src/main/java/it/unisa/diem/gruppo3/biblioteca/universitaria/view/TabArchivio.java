package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Dato;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

/**
 * @author gruppo 3
 */
public abstract class TabArchivio<T extends Dato> {
    private TableView<T> tabella;
    private Label targettaFiltriAttivi;
    private Button bottoneAggiungi;
    private Button bottoneModifica;
    private Button bottoneCerca;
    private Button bottoneEliminaFiltri;
    private Tab tab;
    
    public TabArchivio(FilteredList<T> listaOsservabile) {
        
    }
    
    public void setTargettaFiltriAttivi(String filtriAttivi) {
        
    }
    
    public Button getBottoneAggiungi() {
        return null;
    }
    
    public Button getBottoneModifica() {
        return null;    
    }
    
    public Button getBottoneCerca() {
        return null;    
    }
    
    public Button getBottoneEliminaFiltri() {
        return null;    
    }
    
    public Tab getTab() {
        return null;    
    }
    
    public T getSelectedItem() {
        return null;
    }
}
