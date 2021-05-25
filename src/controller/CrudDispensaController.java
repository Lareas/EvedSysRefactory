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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class CrudDispensaController implements Initializable {

    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private Label lblTipo;
    @FXML
    private TextField ttfInstitu;
    @FXML
    private TextField ttfDisci;
    @FXML
    private TextField ttfCraga;
    @FXML
    private TextField ttfCredito;
    @FXML
    private TextField ttfAno;
    @FXML
    private TextField ttfNOta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void clicouConfirma(ActionEvent event) {
    }

    @FXML
    private void clicouCancela(ActionEvent event) {
    }

    
}
