package it.unisa.diem.gruppo3.biblioteca.universitaria.io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @file AppendableObjectOutputStream.java
 * @author gruppo 3
 * 
 * @brief Classe necessaria al fine di eseguire correttamente l'operazione di 
 * append quando si scrive sul file di cache
 * 
 * La necessità sorge da come opera ObjectOutputStream: questa classe, ogni
 * volta che viene usata per scrivere su di un file un oggetto serializzato 
 * scrive prima di tutto un header, che poi verrà letto da ObjectInputStream.
 * In una situazione normale avremo che scrivo un oggetto e leggo un oggetto;
 * o anche scrivo una collezione di oggetti tutta assieme e leggo una collezione
 * di oggetti tutta assieme. 
 * 
 * Con le funzioni salvaSuCache e caricaDaCache tuttavia non sono simmetriche
 * in quanto salvaSuCache può scrivere un solo oggetto alla volta mentre 
 * caricaDaCache legge l'intero file. Il problema sorge in quanto ogni volta che
 * viene scritto un elemento su file, viene inevitabilemente chiuso anche lo
 * stream di scrittura associato, cosicché alla prossima chiamata della funzione
 * salvaSuCache venga aperto un nuovo stream con ObjectOutputStream che,
 * inevitabilemente, aggiungerà all'inizio un suo header. Essendo poi che 
 * salvaSuCache è concepito per fare l'operazione di append ad ogni scrittura
 * ne viene che ogni oggetto che sarà presente nel file sarà preceduto da un
 * proprio header.
 * 
 * Quando poi si userà la funzione di lettura caricaDaCache e si utilizzerà
 * un unico ObjectInputFile, questo, dopo aver letto il primo elemento,
 * incontrerà l'header del secondo e dunque lancerà l'eccezione:
 * java.io.StreamCorruptedException: invalid type code: AC.
 * 
 * Per risolvere il problema, come suggerisce il thread di StackOverflow 
 * https://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream
 * si può estendere la classe ObjectOutputStream e modificare il metodo 
 * writeStreamHeader(). Questo metodo, come stesso la documentazione jdk 1.8
 * cita, "è dato in modo tale che le sottoclassi (di ObjectOutputStream) possono
 * aggiungere o anteporre i propri header allo stream". 
 * 
 * Va da sé che essendo creato per gestire il caso di aggiunta in un file (e 
 * non sovrascrittura) deve essere usato solo dalla seconda scrittura in poi.
 * Dunque va prima controllato che il file esista e abbia qualcosa scritto al
 * suo interno, ad esempio con
 * 
 *      File f = new File(pathname); 
 *      boolean appendFlag = (f.exists()) && (f.length() > 0);
 * 
 * Dopodiché gestire la scrittura in base al risultato di appendFlag o con 
 * ObjectOutputStream (false) o con AppendableObjectOutputStream (true).
 */
class AppendableObjectOutputStream extends ObjectOutputStream {

    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    
    /**
     * @brief metodo che provvede a levare l'header dall'inizio per proprio stream
     * @throws IOException 
     */
    @Override
    public void writeStreamHeader() throws IOException {
        reset();    
    }
}
