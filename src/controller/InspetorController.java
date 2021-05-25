/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Atu_Notas;
import entities.Inspetor;
import funcoes.DBConnector;
import static funcoes.MyFunc.mostraMsg;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class InspetorController implements Initializable {

    @FXML
    private TableView<Inspetor> tvInspetor;
    @FXML
    private TableColumn<Inspetor, String> tc1;
    @FXML
    private TableColumn<Inspetor, String> tc2;
    @FXML
    private TableColumn<Inspetor, String> tc3;
    @FXML
    private TableColumn<Inspetor, String> tc4;
    @FXML
    private TableColumn<Inspetor, String> tc5;
    @FXML
    private TableColumn<Inspetor, String> tc6;
    @FXML
    private TableColumn<Inspetor, String> tc7;
    @FXML
    private TableColumn<Inspetor, String> tc8;
    @FXML
    private TableColumn<Inspetor, String> tc9;
    @FXML
    private TableColumn<Inspetor, String> tc10;
    @FXML
    private TableColumn<Inspetor, String> tc11;
    @FXML
    private TableColumn<Inspetor, String> tc12;
    @FXML
    private TableColumn<Inspetor, String> tc13;
    @FXML
    private TableColumn<Inspetor, String> tc14;
    @FXML
    private TableColumn<Inspetor, String> tc15;
    @FXML
    private TableColumn<Inspetor, String> tc16;
    @FXML
    private ComboBox<String> cbTabelas;
    @FXML
    private TextField ttfTotReg;

    private ObservableList<Inspetor> dadosInsp;
    private ObservableList<String> tabsInsp;
    public Connection con = DBConnector.getConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String sqlAllTables = "SHOW TABLES";
        try {
            System.out.println(sqlAllTables);
            tabsInsp = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery(sqlAllTables);
            while (rs.next()) {
                tabsInsp.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CadProg2Controller.class.getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 051a - em Inpetor()", "" + ex, 2);
        }
        cbTabelas.setItems(tabsInsp);

    }

    @FXML
    private void comboAction(ActionEvent event) {

        tc1.setVisible(false);
        tc2.setVisible(false);
        tc3.setVisible(false);
        tc4.setVisible(false);
        tc5.setVisible(false);
        tc6.setVisible(false);
        tc7.setVisible(false);
        tc8.setVisible(false);
        tc9.setVisible(false);
        tc10.setVisible(false);
        tc11.setVisible(false);
        tc12.setVisible(false);
        tc13.setVisible(false);
        tc14.setVisible(false);
        tc15.setVisible(false);
        tc16.setVisible(false);
        String sqlTabela = "SELECT * FROM " + cbTabelas.getValue();
        String sqlTotal = "SELECT COUNT(*) FROM " + cbTabelas.getValue();

        try {
            System.out.println(sqlTabela);
            dadosInsp = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery(sqlTabela);
            int numCols = rs.getMetaData().getColumnCount();
            if (numCols > 16) {
                numCols = 16;
            }

            while (rs.next()) {
                switch (numCols) {
                    case 1:
                        dadosInsp.add(new Inspetor(rs.getString(1)));
                        break;
                    case 2:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2)));
                        break;
                    case 3:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3)));
                        break;
                    case 4:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                        break;
                    case 5:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                        break;
                    case 6:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                        break;
                    case 7:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7)));
                        break;
                    case 8:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                        break;
                    case 9:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                        break;
                    case 10:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
                        break;
                    case 11:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
                        break;
                    case 12:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                                rs.getString(12)));
                        break;
                    case 13:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                                rs.getString(12), rs.getString(13)));
                        break;
                    case 14:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                                rs.getString(12), rs.getString(13), rs.getString(14)));
                        break;
                    case 15:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                                rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15)));
                        break;
                    default: //case 16:
                        dadosInsp.add(new Inspetor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                                rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16)));
                        break;

                }

            }

            switch (numCols) {
                case 1:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc1.setVisible(true);
                    break;
                case 2:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    break;
                case 3:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    break;
                case 4:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    break;
                case 5:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    break;
                case 6:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    break;
                case 7:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    break;
                case 8:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    break;
                case 9:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc9.setCellValueFactory(new PropertyValueFactory<>("campo9"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    tc9.setVisible(true);
                    break;
                case 10:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc9.setCellValueFactory(new PropertyValueFactory<>("campo9"));
                    tc10.setCellValueFactory(new PropertyValueFactory<>("campo10"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    tc9.setVisible(true);
                    tc10.setVisible(true);
                    break;
                case 11:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc9.setCellValueFactory(new PropertyValueFactory<>("campo9"));
                    tc10.setCellValueFactory(new PropertyValueFactory<>("campo10"));
                    tc11.setCellValueFactory(new PropertyValueFactory<>("campo11"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    tc9.setVisible(true);
                    tc10.setVisible(true);
                    tc11.setVisible(true);
                    break;
                case 12:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc9.setCellValueFactory(new PropertyValueFactory<>("campo9"));
                    tc10.setCellValueFactory(new PropertyValueFactory<>("campo10"));
                    tc11.setCellValueFactory(new PropertyValueFactory<>("campo11"));
                    tc12.setCellValueFactory(new PropertyValueFactory<>("campo12"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    tc9.setVisible(true);
                    tc10.setVisible(true);
                    tc11.setVisible(true);
                    tc12.setVisible(true);
                    break;
                case 13:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc9.setCellValueFactory(new PropertyValueFactory<>("campo9"));
                    tc10.setCellValueFactory(new PropertyValueFactory<>("campo10"));
                    tc11.setCellValueFactory(new PropertyValueFactory<>("campo11"));
                    tc12.setCellValueFactory(new PropertyValueFactory<>("campo12"));
                    tc13.setCellValueFactory(new PropertyValueFactory<>("campo13"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    tc9.setVisible(true);
                    tc10.setVisible(true);
                    tc11.setVisible(true);
                    tc12.setVisible(true);
                    tc13.setVisible(true);
                    break;
                case 14:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc9.setCellValueFactory(new PropertyValueFactory<>("campo9"));
                    tc10.setCellValueFactory(new PropertyValueFactory<>("campo10"));
                    tc11.setCellValueFactory(new PropertyValueFactory<>("campo11"));
                    tc12.setCellValueFactory(new PropertyValueFactory<>("campo12"));
                    tc13.setCellValueFactory(new PropertyValueFactory<>("campo13"));
                    tc14.setCellValueFactory(new PropertyValueFactory<>("campo14"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    tc9.setVisible(true);
                    tc10.setVisible(true);
                    tc11.setVisible(true);
                    tc12.setVisible(true);
                    tc13.setVisible(true);
                    tc14.setVisible(true);
                    break;
                case 15:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc9.setCellValueFactory(new PropertyValueFactory<>("campo9"));
                    tc10.setCellValueFactory(new PropertyValueFactory<>("campo10"));
                    tc11.setCellValueFactory(new PropertyValueFactory<>("campo11"));
                    tc12.setCellValueFactory(new PropertyValueFactory<>("campo12"));
                    tc13.setCellValueFactory(new PropertyValueFactory<>("campo13"));
                    tc14.setCellValueFactory(new PropertyValueFactory<>("campo14"));
                    tc15.setCellValueFactory(new PropertyValueFactory<>("campo15"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    tc9.setVisible(true);
                    tc10.setVisible(true);
                    tc11.setVisible(true);
                    tc12.setVisible(true);
                    tc13.setVisible(true);
                    tc14.setVisible(true);
                    tc15.setVisible(true);
                    break;
                default: //case 16:
                    tc1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
                    tc2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
                    tc3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
                    tc4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
                    tc5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
                    tc6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
                    tc7.setCellValueFactory(new PropertyValueFactory<>("campo7"));
                    tc8.setCellValueFactory(new PropertyValueFactory<>("campo8"));
                    tc9.setCellValueFactory(new PropertyValueFactory<>("campo9"));
                    tc10.setCellValueFactory(new PropertyValueFactory<>("campo10"));
                    tc11.setCellValueFactory(new PropertyValueFactory<>("campo11"));
                    tc12.setCellValueFactory(new PropertyValueFactory<>("campo12"));
                    tc13.setCellValueFactory(new PropertyValueFactory<>("campo13"));
                    tc14.setCellValueFactory(new PropertyValueFactory<>("campo14"));
                    tc15.setCellValueFactory(new PropertyValueFactory<>("campo15"));
                    tc16.setCellValueFactory(new PropertyValueFactory<>("campo16"));
                    tc1.setVisible(true);
                    tc2.setVisible(true);
                    tc3.setVisible(true);
                    tc4.setVisible(true);
                    tc5.setVisible(true);
                    tc6.setVisible(true);
                    tc7.setVisible(true);
                    tc8.setVisible(true);
                    tc9.setVisible(true);
                    tc10.setVisible(true);
                    tc11.setVisible(true);
                    tc12.setVisible(true);
                    tc13.setVisible(true);
                    tc14.setVisible(true);
                    tc15.setVisible(true);
                    tc16.setVisible(true);
                    break;

            }

        } catch (SQLException ex) {
            Logger.getLogger(CadProg2Controller.class.getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 051a - em Inpetor()", "" + ex, 2);
        }
            
        tvInspetor.setItems(dadosInsp);
        tvInspetor.refresh();
        ttfTotReg.setText(String.valueOf(tvInspetor.itemsProperty().get().size())); 
        System.out.println(cbTabelas.getValue());
    }

}
