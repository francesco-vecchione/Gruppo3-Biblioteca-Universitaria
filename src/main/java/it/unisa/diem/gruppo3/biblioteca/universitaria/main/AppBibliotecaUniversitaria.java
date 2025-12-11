package it.unisa.diem.gruppo3.biblioteca.universitaria.main;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX AppBibliotecaUniversitaria
 */


/**
    * @brief Questa è la classe che lancia effettivamente l'applicazione,
    * si occupa di passare ad AppController lo stage principale che si trova nel metodo start
*/
public class AppBibliotecaUniversitaria extends Application {
    
    /**
    * @brief Istanzia AppController e gli passa lo stage principale
    * 
    * @param[in] Stage è lo stage principale dell'applicazione
    *
    * @post
    *   Il metodo passa ad AppController solo lo stage principale
    */
    @Override
    public void start(Stage stage) {
        
    }

    /**
    * @brief È il "metodo delega" di launch che lancia una singola applicazione
    * 
    * @param[in] args non viene mai usato effettivamente nel programma
    */
    public static void launchProxy(String[] args) {
        launch(args);
    }

}