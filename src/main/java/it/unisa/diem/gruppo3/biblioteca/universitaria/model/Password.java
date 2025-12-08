package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import it.unisa.diem.gruppo3.biblioteca.universitaria.io.Cassaforte;

/**
 * @author gruppo 3
 */
public class Password implements ModelPassword {
    private Cassaforte cassaforte;
    
    public Password(String pathname) {
        
    }

    @Override
    public boolean verificaPassword(String passwordInChiaro) {
        return false;
    }

    @Override
    public boolean impostaPassword(String passwordInChiaro) {
        return false;
    }
}
