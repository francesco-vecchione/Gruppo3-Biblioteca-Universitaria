/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.gruppo3.biblioteca.universitaria.model;

import it.unisa.diem.gruppo3.biblioteca.universitaria.view.ErroreAlert;
import java.time.LocalDate;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author giosc
 */
public class ModelBiblioteca {
    
    private static final int MAX_PRESTITI_UTENTE = 3;
    
    private ModelArchivio<Libro> modelLibri;
    private ModelArchivio<Utente> modelUtenti;
    private ModelArchivio<Prestito> modelPrestiti;

    public ModelBiblioteca() {
        modelLibri = new ModelArchivio<>("files/archivioLibri");
        modelUtenti = new ModelArchivio<>("files/archivioUtenti");
        modelPrestiti = new ModelArchivio<>("files/archivioPrestiti");
        
        modelLibri.apriArchivio();
        modelUtenti.apriArchivio();
        modelPrestiti.apriArchivio();
    }
    
        /*  LIBRI   */
    public boolean aggiungiLibro(Libro libro) {
        return modelLibri.aggiungiElemento(libro);
    }
    
    public boolean modificaLibro(Libro target, Libro nuovo) {
        return modelLibri.modificaElemento(target, nuovo);
    }
    
    //return true -> cancellazione libro, return false -> azzera copie
    public boolean cancellaLibro(Libro libro) {
        if(modelPrestiti.getArchivioFiltrato().filtered(prestito -> {
                    return prestito.getIsbnPrestito().equals(libro.getIsbn()) && (prestito.getStatoPrestito() == StatoPrestito.ATTIVO);}).size() > 0) {
                    // Se nella lista dei filtrati tramite isbn c'è un prestito che combacia con l'isbn selezionato dalla tableview
                    // e che ha stato attivo, allora posso svolgere l'operazione di azzeramento copie senza levare il libro dall'archivio
                    libro.azzeraCopie();
                    return false;
        }
        return modelLibri.rimuoviElemento(libro);
    }
    
    public FilteredList<Libro> getLibriPrestabili() {
        return modelLibri.getArchivioFiltrato().filtered(libro -> libro.getNumeroCopieDisponibili() > 0);
    }
    
    public FilteredList<Libro> getArchivioLibri() {
        return modelLibri.getArchivioFiltrato();
    }
    
    public boolean chiudiModelLibri() {
        return modelLibri.chiudiArchivio();
    }
    
    
    
        /*  UTENTI  */
    public boolean aggiungUtente(Utente utente) {
        return modelUtenti.aggiungiElemento(utente);
    }
    
    public boolean modificaUtente(Utente target, Utente nuovo) {
        return modelUtenti.modificaElemento(target, nuovo);
    }
    
    public boolean cancellaUtente(Utente utente) {
        if(modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getMatricolaUtente().equals(utente.getMatricola())).size() > 0) {
            return false;
        }
        return modelUtenti.rimuoviElemento(utente);
    }
    
    public FilteredList<Utente> getUtentiPrestabili() {
        return modelUtenti.getArchivioFiltrato().filtered(utente -> 
            modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getMatricolaUtente().equals(utente.getMatricola()) &&
                    prestito.getStatoPrestito().equals(StatoPrestito.ATTIVO)).size() < MAX_PRESTITI_UTENTE);
    }
    
    public FilteredList<Utente> getArchivioUtenti() {
        return modelUtenti.getArchivioFiltrato();
    }
    
    public boolean chiudiModelUtenti() {
        return modelUtenti.chiudiArchivio();
    }
    
    
    
        /*  PRESTITI    */
    public boolean registraPrestito(Utente utente, Libro libro, LocalDate dataRestituzione) {
        if(libro.getNumeroCopieDisponibili() < 1) return false;
        if(modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getMatricolaUtente().equals(utente.getMatricola()) &&
                prestito.getStatoPrestito().equals(StatoPrestito.ATTIVO)).size() >= MAX_PRESTITI_UTENTE) return false;
        
        libro.prestaCopia();
        return modelPrestiti.aggiungiElemento(new Prestito(utente.getMatricola(), libro.getIsbn(), LocalDate.now(), dataRestituzione));
    }
    
    public void registraRestituzione(Prestito prestito) {
        prestito.registraRestituzione();
        Libro libroInPrestito = modelLibri.ricercaElemento(new Libro("", "", 0, prestito.getIsbnPrestito(), 0));
        libroInPrestito.restituisciCopia();
    }
    
    public FilteredList<Prestito> getPrestitiAttivi() {
        return modelPrestiti.getArchivioFiltrato().filtered(prestito -> prestito.getStatoPrestito().equals(StatoPrestito.ATTIVO));
    }
    
    public FilteredList<Prestito> getArchivioPrestiti() {
        return modelPrestiti.getArchivioFiltrato();
    }
    
    public boolean chiudiModelPrestiti() {
        return modelPrestiti.chiudiArchivio();
    }
}
