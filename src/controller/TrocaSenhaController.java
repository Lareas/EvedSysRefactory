/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbUser;
import static main.Login.gbSenhaOk;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import funcoes.MyFunc;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class TrocaSenhaController implements Initializable {

    @FXML
    private AnchorPane apTrocaSenha;
    @FXML
    private JFXPasswordField redigita;
    @FXML
    private JFXButton buConfima;
    @FXML
    private JFXPasswordField passwordAtual;
    @FXML
    private JFXPasswordField passwordNova;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
        if ((passwordAtual.getText().equals("")) || ((!passwordAtual.getText().equals(gbUser.getPassword())))) { // OU USER OU SENHA VAZIOS(username.getText().equals("") || password.getText().equals("")) {
            MyFunc.mostraMsg("Senha atual não confere", "", 2);
            passwordAtual.requestFocus();
        } else {
            if ((passwordNova.getText().equals(""))) {
                MyFunc.mostraMsg("Informe a nova senha", "", 1);
                passwordNova.requestFocus();
            } else {
                if (!passwordNova.getText().equals(redigita.getText())) {
                    MyFunc.mostraMsg("A senha nova e a redigitação não conferem", "", 2);
                } else {
                    if (passwordNova.getText().trim().length() < 5) {
                        MyFunc.mostraMsg("A senha deve ter pelo menos 5 caracteres.", "", 4);
                    } else {
                        gbSenhaOk = true;
                        gbUser.setPassword(passwordNova.getText());
                        Stage stage = (Stage) buConfima.getScene().getWindow();
                        stage.close();
                    }
                }

            }

        }
    }

    @FXML
    private void clicouCancela(ActionEvent event) {
        MyFunc.mostraMsg("A senha não foi alterada", "", 3);
        Stage stage = (Stage) buConfima.getScene().getWindow();
        stage.close();
    }

}
