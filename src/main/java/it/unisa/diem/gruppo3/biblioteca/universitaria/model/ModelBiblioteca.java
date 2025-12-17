/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import java.time.LocalDate;
import javafx.collections.transformation.FilteredList;

/**
 * @file
 * @author gruppo 3
 * @brief Il model che astrae le funzionalità necessarie alla gestione della biblioteca
 * 
 * Al fine di fornire le uniche operazioni necessarie al controller ed evitare dunque 
 * che abbia accesso alle primitive fornite dai modelli dei dati, è stata creata la 
 * classe ModelBiblioteca. Le operazioni atomiche che fornisce sono di aggiunta e modifica
 * per libri ed utenti e prestiti. Poi per libri ed utenti è stata fornita inoltre l'operazione
 * di cancellazione. Inoltre altre operazioni fornite sono quelle di getter per gli archivi
 * e le operazioni di chiusura.
 */
public class ModelBiblioteca {
    /**
     * @brief Costante che definisce il numero di prestito massimo per utente
     */
    private static final int MAX_PRESTITI_UTENTE = 3;
    
    /**
     * @brief Il modello di archivio interagibile dei libri
     */
    private final ModelArchivio<Libro> modelLibri;

    /**
     * @brief Il modello di archivio interagibile degli utenti
     */
    private final ModelArchivio<Utente> modelUtenti;

    /**
     * @brief Il modello di archivio interagibile dei prestiti
     */
    private final ModelArchivio<Prestito> modelPrestiti;

    /**
     * @brief Costruttore senza parametri che permette di istanziare i modelli degli archivi
     * interagibili ed aprirli
     * @post
     * Gli archivi dei libri, degli utenti e dei prestiti sono aperti
     */
    public ModelBiblioteca() {
        modelLibri = new ModelArchivio<>("files/archivioLibri");
        modelUtenti = new ModelArchivio<>("files/archivioUtenti");
        modelPrestiti = new ModelArchivio<>("files/archivioPrestiti");
        
        modelLibri.apriArchivio();
        modelUtenti.apriArchivio();
        modelPrestiti.apriArchivio();
    }
    
        /*  LIBRI   */
    /**
     * @brief Operazione atomica di aggiunta nuovo libro all'archivio dei libri
     * @param[in] libro     Il libro nuovo da aggiungere all'archivio
     * @return Ritorna true se l'operazione di aggiunta è andata a buon fine, false altrimenti
     */
    public boolean aggiungiLibro(Libro libro) {
        return modelLibri.aggiungiElemento(libro);
    }
    
    /**
     * @brief Operazione atomica di modifica dati di un libro nell'archivio dei libri
     * @param[in] target    Il libro che si deve modificare
     * @param[in] nuovo     Il libro modificato che deve essere aggiunto
     * @return Ritorna true se l'operazione di modifica è andata a buon fine, false altrimenti
     */
    public boolean modificaLibro(Libro target, Libro nuovo) {
        return modelLibri.modificaElemento(target, nuovo);
    }
    
    /**
     * @brief Operazione atomica di cancellazione di un libro dall'archivio dei libri
     * 
     * Il metodo si limita ad azzerare il numero di copie del libro se è attualmente
     * in prestito, altrimenti elimina il record direttamente dall'archivio, eliminando anche i prestiti relativi al libro da eliminare
     * 
     * @param[in] libro     Il libro da cancellare
     * @return Ritorna true se è stato possibile cancellare il record del libro dall'archivio, false se il libro era in prestito
     */
    public boolean cancellaLibro(Libro libro) {
        if(modelPrestiti.getArchivioFiltrato().filtered(prestito -> {
                    return prestito.getIsbnPrestito().equals(libro.getIsbn()) && (prestito.getStatoPrestito() == StatoPrestito.ATTIVO);}).size() > 0) {
                    // Se nella lista dei filtrati tramite isbn c'è un prestito che combacia con l'isbn selezionato dalla tableview
                    // e che ha stato attivo, allora posso svolgere l'operazione di azzeramento copie senza levare il libro dall'archivio
                    libro.azzeraCopie();
                    return false;
        }
        //Elimina i prestiti restituiti relativi al libro da eliminare
        for(Prestito prestito : modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getIsbnPrestito().equals(libro.getIsbn()) && 
                prestito.getStatoPrestito() == StatoPrestito.RESTITUITO))
            modelPrestiti.rimuoviElemento(prestito);
        return modelLibri.rimuoviElemento(libro);
    }

    /**
     * @brief Getter per la lista dei soli libri che possono essere prestati, ossia che hanno un numero di copie maggiore di 0
     * @return La lista degli utenti che possono effettuare il prestito
     */    
    public FilteredList<Libro> getLibriPrestabili() {
        return modelLibri.getArchivioFiltrato().filtered(libro -> libro.getNumeroCopieDisponibili() > 0);
    }
    
    /**
     * @brief Getter per l'archivio dei libri
     * @return La lista filtrata che costituisce l'archivio dei libri
     */    
    public FilteredList<Libro> getArchivioLibri() {
        return modelLibri.getArchivioFiltrato();
    }

    /**
     * @brief Operazione atomica di chiusura dell'archivio dei libri
     * @return Ritorna true se l'operazione di chiusura è andata a buon fine, false altrimenti 
     */    
    public boolean chiudiModelLibri() {
        return modelLibri.chiudiArchivio();
    }
    
    
    
        /*  UTENTI  */
    /**
     * @brief Operazione atomica di aggiunta nuovo utente all'archivio degli utenti
     * @param[in] utente    L'utente nuovo da aggiungere all'archivio
     * @return Ritorna true se l'operazione di aggiunta è andata a buon fine, false altrimenti
     */
    public boolean aggiungUtente(Utente utente) {
        return modelUtenti.aggiungiElemento(utente);
    }
    
    /**
     * @brief Operazione atomica di modifica dei dati di un utente
     * @param[in] target    L'utente che deve essere modificato
     * @param[in] nuovo     L'utente con modificato
     * @return Ritorna true se l'operazione di modifica è andata a buon fine, false altrimenti
     */
    public boolean modificaUtente(Utente target, Utente nuovo) {
        return modelUtenti.modificaElemento(target, nuovo);
    }
    
    /**
     * @brief Operazione atomica di cancellazione di un utente dall'archivio degli utenti
     * 
     * Vengono eliminati anche i prestiti relativi all'utente da cancellare
     * @param[in] utente    L'utente da cancellare nell'archivio
     * @return Ritorna true se l'operazione di cancellazione è andata a buon fine, false altrimenti
     */
    public boolean cancellaUtente(Utente utente) {
        if(modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getMatricolaUtente().equals(utente.getMatricola())).size() > 0) {
            return false;
        }
        //Elimina i prestiti relativi all'utente da eliminare
        for(Prestito prestito : modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getMatricolaUtente().equals(utente.getMatricola()) && 
                prestito.getStatoPrestito() == StatoPrestito.RESTITUITO))
            modelPrestiti.rimuoviElemento(prestito);
        return modelUtenti.rimuoviElemento(utente);
    }
    
    /**
     * @brief Getter per la lista dei soli utenti che hanno un numero minore al massimo numero di prestiti pro capite consentiti 
     * @return La lista degli utenti che possono effettuare il prestito
     */
    public FilteredList<Utente> getUtentiPrestabili() {
        return modelUtenti.getArchivioFiltrato().filtered(utente -> 
            modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getMatricolaUtente().equals(utente.getMatricola()) &&
                    (prestito.getStatoPrestito() == StatoPrestito.ATTIVO)).size() < MAX_PRESTITI_UTENTE);
    }
    
    /**
     * @brief Getter per l'archivio degli utenti
     * @return La lista filtrata che costituisce l'archivio dei prestiti
     */
    public FilteredList<Utente> getArchivioUtenti() {
        return modelUtenti.getArchivioFiltrato();
    }
    
    /**
     * @brief Operazione atomica di chiusura dell'archivio degli utenti
     * @return Ritorna true se l'operazione di chiusura è andata a buon fine, false altrimenti 
     */
    public boolean chiudiModelUtenti() {
        return modelUtenti.chiudiArchivio();
    }
    
    
    
        /*  PRESTITI    */
    /**
     * @brief Operazione atomica di registrazione dei un nuovo prestito nell'archivio dei prestiti
     * @param[in] utente    L'utente che richiede il prestito
     * @param[in] libro     Il libro da prestare
     * @param[in] dataRestituzione  La data di restituzione entro cui ritornare il prestito
     * @return Ritorna true se l'operazione di registrazione è andata a buon fine, false altrimenti
     */
    public boolean registraPrestito(Utente utente, Libro libro, LocalDate dataRestituzione) {
        if(libro.getNumeroCopieDisponibili() < 1) return false;
        if(modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getMatricolaUtente().equals(utente.getMatricola()) &&
                prestito.getStatoPrestito().equals(StatoPrestito.ATTIVO)).size() >= MAX_PRESTITI_UTENTE) return false;
        
        libro.prestaCopia();
        modelLibri.modificaElemento(libro, libro);
        return modelPrestiti.aggiungiElemento(new Prestito(utente.getMatricola(), libro.getIsbn(), LocalDate.now(), dataRestituzione));
    }
    
    /**
     * @brief Operazione atomica di registrazione di restituzione del prestito
     * @param[in] prestito  Il prestito che viene restituito
     */
    public void registraRestituzione(Prestito prestito) {
        prestito.registraRestituzione();
        modelPrestiti.modificaElemento(prestito, prestito); // Salva operazione nella cache
        
        Libro libroInPrestito = modelLibri.ricercaElemento(new Libro("", "", 0, prestito.getIsbnPrestito(), 0));
        libroInPrestito.restituisciCopia();
        modelLibri.modificaElemento(libroInPrestito, libroInPrestito);  // Salva operazione nella cache
    }
    
    /**
     * @brief Getter per lista dei soli prestiti attivi
     * @return La lista filtrata dei soli prestiti attivi
     */
    public FilteredList<Prestito> getPrestitiAttivi() {
        return modelPrestiti.getArchivioFiltrato().filtered(prestito -> (prestito.getStatoPrestito() == StatoPrestito.ATTIVO));
    }
    
    /**
     * @brief Getter per l'archivio dei prestiti
     * @return La lista filtrata che costituisce l'archivio dei prestiti
     */
    public FilteredList<Prestito> getArchivioPrestiti() {
        return modelPrestiti.getArchivioFiltrato();
    }
    
    /**
     * @brief Operazione atomica di chiusura dell'archivio dei prestiti
     * @return Ritorna true se l'operazione di chiusura è andata a buon fine, false altrimenti
     */
    public boolean chiudiModelPrestiti() {
        return modelPrestiti.chiudiArchivio();
    }
}
