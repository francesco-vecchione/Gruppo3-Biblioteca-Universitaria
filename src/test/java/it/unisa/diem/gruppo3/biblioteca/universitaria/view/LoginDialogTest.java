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
public class LoginDialogTest {
    
    private LoginDialog d;
    
    @Start
    private void start(Stage stage) {
        d = new LoginDialog();
    }    
    
    /**
     * UTC 12 - Test LoginDialog – costruttore
     */
    @Test
    public void testGetTxfPassword() {
        assertNotNull(d.getTxfPassword());
        assertNotNull(d.getLinkPasswordDimenticata());
    }
    
}
