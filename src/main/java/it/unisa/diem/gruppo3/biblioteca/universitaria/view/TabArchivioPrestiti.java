package it.unisa.diem.gruppo3.biblioteca.universitaria.view;

import it.unisa.diem.gruppo3.biblioteca.universitaria.model.Prestito;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @file TabArchivioPrestiti.java
 * @author gruppo 3
 * @brief Questa classe specializza TabArchivio per la gestione dell'archivio
 * dei prestiti
 */
public class TabArchivioPrestiti extends TabArchivio<Prestito> {

    /**
     * @brief Costruttore che non aggiunge nulla al layout costruito da
     * TabArchvio
     * @param[in] listaOsservabilePrestiti Lista visualizzata nella TableView
     * @pre listaOsservabilePrestiti passato in input non deve essere null.
     * @post L'istanza di una specializzazione di TabArchivio è creata con la
     * lista specificata.
     */
    public TabArchivioPrestiti(FilteredList<Prestito> listaOsservabilePrestiti) {
        super(listaOsservabilePrestiti);

        getBtnAggiungi().setText("Registra Prestito");
        getBtnModifica().setText("Registra Restituzione");

        TableColumn<Prestito, String> matricolaCol = new TableColumn<>("Matricola Utente");
        matricolaCol.setCellValueFactory(new PropertyValueFactory<>("matricolaUtente"));

        TableColumn<Prestito, String> isbnCol = new TableColumn<>("ISBN Libro");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbnPrestito"));

        TableColumn<Prestito, LocalDate> dataPrestitoCol = new TableColumn<>("Data Inizio Prestito");
        dataPrestitoCol.setCellValueFactory(new PropertyValueFactory<>("dataPrestito"));

        TableColumn<Prestito, LocalDate> dataRestituzioneCol = new TableColumn<>("Data di Restituzione Concordata");
        dataRestituzioneCol.setCellValueFactory(new PropertyValueFactory<>("dataRestituzione"));

        getTabella().setRowFactory(tv -> new TableRow<Prestito>() {
            @Override
            protected void updateItem(Prestito item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("");
                if (!empty && item != null) {
                    if (!isSelected()) {
                        LocalDate dataRestituzione = item.getDataRestituzione();
                        if (dataRestituzione != null) {
                            long giorniRimanenti = ChronoUnit.DAYS.between(LocalDate.now(), dataRestituzione);
                            if (giorniRimanenti < 0) {
                                setStyle("-fx-background-color: #FFA07A;");
                            } else if (giorniRimanenti <= 3) {
                                setStyle("-fx-background-color: #FFECB3;");
                            }
                        }
                    }
                    selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                        if (isNowSelected) {
                            setStyle("");
                        } else {
                            LocalDate dataRestituzione = getItem().getDataRestituzione();
                            if (dataRestituzione != null) {
                                long giorniRimanenti = ChronoUnit.DAYS.between(LocalDate.now(), dataRestituzione);
                                if (giorniRimanenti < 0) {
                                    setStyle("-fx-background-color: #FFA07A;");
                                } else if (giorniRimanenti <= 3) {
                                    setStyle("-fx-background-color: #FFECB3;");
                                }
                            }
                        }
                    });
                }
            }
        });

        getTabella().getColumns().addAll(matricolaCol, isbnCol, dataPrestitoCol, dataRestituzioneCol);
        getTabella().setItems(listaOsservabilePrestiti);

        VBox boxBottoni = new VBox();
        boxBottoni.getChildren().addAll(getBtnAggiungi(), getBtnModifica());

        BorderPane root = new BorderPane();
        root.setCenter(getBoxCentro());
        root.setRight(boxBottoni);

        getTab().setText("Prestiti");
        getTab().setContent(root);
        getTab().setClosable(false);
    }
}
