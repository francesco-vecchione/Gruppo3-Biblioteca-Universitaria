package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gruppo 3
 */
public class ViewBibliotecaTest {
    
    private ViewBiblioteca v;
    
    @BeforeEach
    public void setUp() {   
        v = new ViewBiblioteca(new Stage(), null, null, null);
    }

    /**
     * UTC 18 - Test ViewBiblioteca – costruttore
     */
    @Test
    public void testCostruttore() {
        assertNotNull(v.getStage());
        assertNotNull(v.getTabLibri());
        assertNotNull(v.getTabUtenti());
        assertNotNull(v.getTabPrestiti());
    }
}
