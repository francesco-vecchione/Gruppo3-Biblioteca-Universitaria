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
public class UtilityPopUpPrestitiTest {
    
    private UtilityPopUpPrestiti u;
 
    @Start
    private void start(Stage stage) {
        u = new UtilityPopUpPrestiti();
    }

    /**
     * UTC 17 - Test UtilityPopUpPrestiti – costruttore
     */
    @Test
    public void testCostruttore() {
        assertNotNull(u.getTxfMatricola());
        assertNotNull(u.getTxfIsbn());
        assertNotNull(u.getDpkDataPrestito());
        assertNotNull(u.getDpkDataRestituzione());
    }
}
