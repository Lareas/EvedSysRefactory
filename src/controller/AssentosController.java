/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class AssentosController implements Initializable {

    @FXML
    private TableView<Linhas> tvPlacarAssentos;
    @FXML
    private TableColumn<Linhas, String> tcTitulos;
    @FXML
    private TableColumn<Linhas, Integer> tcSem1;
    @FXML
    private TableColumn<Linhas, Integer> tcSem2;
    @FXML
    private TableColumn<Linhas, Integer> tcSem3;
    @FXML
    private TableColumn<Linhas, Integer> tcSem4;
    @FXML
    private TableColumn<Linhas, Integer> tcSem5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tvPlacarAssentos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    @SuppressWarnings("rawtypes")
                    TablePosition pos = tvPlacarAssentos.getSelectionModel().getSelectedCells().get(0);
                    int row = pos.getRow();
                    int col = pos.getColumn();
                    @SuppressWarnings("rawtypes")
                    TableColumn column = pos.getTableColumn();
                    String val = column.getCellData(row).toString();
                    System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);

                }
            }

        });
        inicPlacar();
    }

    private void inicPlacar() {
        ObservableList<Linhas> dados = FXCollections.observableArrayList();

        Linhas lin = new Linhas("Regulares", 743, 0, 0, 0, 0);
        dados.add(lin);
        lin = new Linhas("Especiais", 20, 0, 0, 0, 0);
        dados.add(lin);
        lin = new Linhas("Ouvintes", 210, 0, 0, 0, 0);
        dados.add(lin);
        lin = new Linhas("Gravação", 5, 0, 0, 0, 0);
        dados.add(lin);
        lin = new Linhas("Total", 980, 0, 0, 0, 0);
        dados.add(lin);

        tcTitulos.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcSem1.setCellValueFactory(new PropertyValueFactory<>("sem1"));
        tcSem2.setCellValueFactory(new PropertyValueFactory<>("sem2"));
        tcSem3.setCellValueFactory(new PropertyValueFactory<>("sem3"));
        tcSem4.setCellValueFactory(new PropertyValueFactory<>("sem4"));
        tcSem5.setCellValueFactory(new PropertyValueFactory<>("sem5"));
        tcSem1.setStyle("-fx-alignment: CENTER;");
        tcSem2.setStyle("-fx-alignment: CENTER;");
        tcSem3.setStyle("-fx-alignment: CENTER;");
        tcSem4.setStyle("-fx-alignment: CENTER;");
        tcSem5.setStyle("-fx-alignment: CENTER;");

        tvPlacarAssentos.setItems(dados);
        tvPlacarAssentos.refresh();

    }

    @FXML
    private void clicouVer(ActionEvent event) {

    }

    public class Linhas {

        private String titulo;
        private int sem1;
        private int sem2;
        private int sem3;
        private int sem4;
        private int sem5;

        public Linhas(String titulo, int sem1, int sem2, int sem3, int sem4, int sem5) {
            this.titulo = titulo;
            this.sem1 = sem1;
            this.sem2 = sem2;
            this.sem3 = sem3;
            this.sem4 = sem4;
            this.sem5 = sem5;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public int getSem1() {
            return sem1;
        }

        public void setSem1(int sem1) {
            this.sem1 = sem1;
        }

        public int getSem2() {
            return sem2;
        }

        public void setSem2(int sem2) {
            this.sem2 = sem2;
        }

        public int getSem3() {
            return sem3;
        }

        public void setSem3(int sem3) {
            this.sem3 = sem3;
        }

        public int getSem4() {
            return sem4;
        }

        public void setSem4(int sem4) {
            this.sem4 = sem4;
        }

        public int getSem5() {
            return sem5;
        }

        public void setSem5(int sem5) {
            this.sem5 = sem5;
        }

    }

}
