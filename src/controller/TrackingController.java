/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class TrackingController implements Initializable {

    @FXML
    private JFXButton buPesquisa;
    @FXML
    private JFXButton buLimpa;
    @FXML
    private TableView<?> tvPesquisa;
    @FXML
    private TableColumn<?, ?> tcUsu;
    @FXML
    private TableColumn<?, ?> tcDat;
    @FXML
    private TableColumn<?, ?> tcTel;
    @FXML
    private TableColumn<?, ?> tcAti;
    @FXML
    private TableColumn<?, ?> tcDes;
    @FXML
    private TextField lblRegistros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicouMinhaPesqEnter(KeyEvent event) {
    }

    @FXML
    private void clicouMinhaPesq(ActionEvent event) {
    }

    @FXML
    private void clicouLimpa(ActionEvent event) {
    }

    @FXML
    private void clicouAbre(ActionEvent event) {
    }

    @FXML
    private void soltouComboCampos(MouseEvent event) {
    }
    
}
