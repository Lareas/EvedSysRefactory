/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class TestaEditTVController implements Initializable {

    @FXML
    private TableView<?> tvTeste;
    @FXML
    private TableColumn<?, ?> tcNome;
    @FXML
    private TableColumn<?, ?> tcIdade;
    @FXML
    private TableColumn<?, ?> tcAltura;
    @FXML
    private TableColumn<?, ?> tcApelido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
