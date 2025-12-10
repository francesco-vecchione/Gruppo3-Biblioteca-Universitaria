package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

/**
 * @author gruppo 3
 * @brief Quest'interfaccia rappresenta il modello di password accessibile al controller. Il suo compito è di fornire le uniche funzioni che il controller può utilizzare ovvero quella di verificare che la password sia corretta e quella di impostare la password.
 */
public interface ModelPassword {

    /**
     * @brief Verifica che la password inserita come argomento coincida con quella memorizzata in archivio.
     * @param[in] passwordInChiaro  La password in chiaro da verificare.
     * @return true se la password corrisponde, false altrimenti.
     */
    boolean verificaPassword(String passwordInChiaro);

    /**
     * @brief Salva l'hash della password all'interno dell'archivio.
     * @param[in] passwordInChiaro  La password in chiaro da impostare.
     * @return true se il salvataggio ha avuto successo, false altrimenti.
     */
    boolean impostaPassword(String passwordInChiaro);
}