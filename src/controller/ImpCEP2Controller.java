/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbCEP;
import static main.Login.gbCepAluno;
import com.jfoenix.controls.JFXButton;
import entities.CepBairros;
import entities.CepCidades;
import entities.CepLogradouros;
import entities.CepTipoLog;
import static funcoes.MyFunc.mostraMsg;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jpa_controler.CepLogradourosJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class ImpCEP2Controller implements Initializable {

    @FXML
    private TextField lblRegistrosMin;
    @FXML
    private TableView<CepLogradouros> tvCEP;
    @FXML
    private TableColumn<CepLogradouros, String> tcNumCep;
    @FXML
    private TableColumn<CepLogradouros, String> tcNome;
    @FXML
    private TableColumn<CepLogradouros, CepCidades> tcCidade;
    @FXML
    private TableColumn<CepLogradouros, String> tcUF;
    @FXML
    private TableColumn<CepLogradouros, CepTipoLog> tcTipoLog;
    @FXML
    private TableColumn<CepLogradouros, String> tcComp;
    @FXML
    private TableColumn<CepLogradouros, CepBairros> tcBairro;
    @FXML
    private JFXButton buVoltar;
    @FXML
    private TextField edCEP;

    private CepLogradourosJpaController jpaLO;
    private ObservableList<CepLogradouros> dadosLO;
    
    private String sCEP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jpaLO = new CepLogradourosJpaController();

        edCEP.setText(gbCEP);
        sCEP = edCEP.getText().replaceAll("-", ""); //edCEP.getText().substring(0, 5) + edCEP.getText().substring(6, 9);
        InitColumns();
    }

    public void InitColumns() {
        tcNumCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tcTipoLog.setCellValueFactory(new PropertyValueFactory<>("nomeDoTipoLog"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcComp.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        tcBairro.setCellValueFactory(new PropertyValueFactory<>("nomeDoBairro"));
        tcCidade.setCellValueFactory(new PropertyValueFactory<>("nomeDaCidade"));
        tcUF.setCellValueFactory(new PropertyValueFactory<>("uf"));
        tvCEP.setItems(getMeusDados());
        tvCEP.refresh();
        lblRegistrosMin.setText("Registros exibidos: " + tvCEP.itemsProperty().get().size()); 
    }

    public ObservableList<CepLogradouros> getMeusDados() {
        jpaLO = new CepLogradourosJpaController();
        dadosLO = FXCollections.observableArrayList(jpaLO.getCEPs(sCEP));

        if (dadosLO == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosLO;
        }
    }

    @FXML
    private void clicouVolta(ActionEvent event) {
        if (tvCEP.getSelectionModel().getSelectedItem() != null) {
            gbCepAluno = tvCEP.getSelectionModel().getSelectedItem();
        } else {
            gbCepAluno = null;
        }
        
        //////////////////////// FECHA A TELA
        Stage stage = (Stage) buVoltar.getScene().getWindow();
        stage.close();
        
//        DadocadastrogeralController.stageCadastrais.show();

        // FECHA JANELA
    }

    @FXML
    private void clicouFiltra(ActionEvent event) {
        if (edCEP.getText().trim().length() < 5) {
            mostraMsg("Informe pelo menos 5 números do CEP", "", 1);
        } else {
            sCEP = edCEP.getText().replaceAll("-", ""); //edCEP.getText().substring(0, 5) + edCEP.getText().substring(6, 9);
            InitColumns();
        }
    }

//    public void inflateUI (CepLogradouros cepImportado) {
//        if (tvCEP.getSelectionModel().getSelectedItem() != null) {
//            
//        }
//    }
}
