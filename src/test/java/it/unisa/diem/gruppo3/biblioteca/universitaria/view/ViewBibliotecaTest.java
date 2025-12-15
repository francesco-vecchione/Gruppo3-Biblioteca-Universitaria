package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 *
 * @author gruppo 3
 */
@ExtendWith(ApplicationExtension.class)
public class ViewBibliotecaTest {
    
    private ViewBiblioteca v;
    
    @Start
    private void start(Stage stage) {
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
