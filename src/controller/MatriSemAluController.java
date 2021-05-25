/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class MatriSemAluController implements Initializable {

    @FXML
    private TextField edAnoLetivo;
    @FXML
    private RadioButton rb1SemPro;
    @FXML
    private RadioButton rb2SemPro;
    @FXML
    private JFXDatePicker dpDataMatri;
    @FXML
    private ImageView ivFotoAluno;
    @FXML
    private TextField tfMatriAluno;
    @FXML
    private TextField tfNomeAluno;
    @FXML
    private TextField tfPrograma;
    @FXML
    private TextField edDescontoPorc;
    @FXML
    private TextField edCredComDesc;
    @FXML
    private TextField tfCodMatriSemestre;
    @FXML
    private TableView<?> tvDisMatri;
    @FXML
    private TableColumn<?, ?> tcRegOuv;
    @FXML
    private TableColumn<?, ?> tcTurno;
    @FXML
    private TableColumn<?, ?> tcTipoDis;
    @FXML
    private TableColumn<?, ?> tcDatas;
    @FXML
    private TableColumn<?, ?> tcHorarios;
    @FXML
    private TableColumn<?, ?> tcDisciplinas;
    @FXML
    private TableColumn<?, ?> tcProf1;
    @FXML
    private TableColumn<?, ?> tcCred;
    @FXML
    private TextField lblRegDis;
    @FXML
    private TextField edNumParc;
    @FXML
    private TextField edOutros;
    @FXML
    private TextField edPelaIgreja;
    @FXML
    private TextField tfTaxa;
    @FXML
    private TextField tfValCre;
    @FXML
    private TextField tfNumCre;
    @FXML
    private JFXButton buDisIns;
    @FXML
    private JFXButton buDisEdi;
    @FXML
    private JFXButton buDisCon;
    @FXML
    private JFXButton buDisCan;
    @FXML
    private JFXButton buDisApa;
    @FXML
    private ToggleGroup gPag;
    @FXML
    private CheckBox chkMonitoria;
    @FXML
    private CheckBox chkOutros;
    @FXML
    private CheckBox chkPelaIgreja;
    @FXML
    private JFXButton buEdita;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private JFXButton buApaga;
    @FXML
    private TextArea edMatriObs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicouInserePro(ActionEvent event) {
    }

    @FXML
    private void clicouEditaPro(ActionEvent event) {
    }

    @FXML
    private void clicouEdita(ActionEvent event) {
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
    }

    @FXML
    private void clicouCancela(ActionEvent event) {
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
    }
    
}
