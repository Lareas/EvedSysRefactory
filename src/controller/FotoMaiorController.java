/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbFotoMaior;
import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class FotoMaiorController implements Initializable {

    @FXML
    private ImageView ivFotoMaior;
    @FXML
    private Label lblNomeAluno;
    @FXML
    private JFXButton buFechar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblNomeAluno.setText(gbFotoMaior.getNomeAluno());
        ivFotoMaior.setImage(gbFotoMaior.getFotoMaior());
       // ivFotoMaior.setFitWidth(200);
        ivFotoMaior.setPreserveRatio(true);
        ivFotoMaior.setSmooth(true);
        ivFotoMaior.setCache(true);        
    }

    @FXML
    private void clicouFechar(ActionEvent event) {
        Stage stage = (Stage) buFechar.getScene().getWindow();
        stage.close();
    }

}
