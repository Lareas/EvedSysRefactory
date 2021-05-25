/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.EsGrade;
import entities.Grade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.EsGradeJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class GradeController implements Initializable {

    @FXML
    private JFXTabPane tabFX;
    @FXML
    private Tab tabEdicao;
    @FXML
    private TextField txtCodPK;
    @FXML
    private JFXButton buInsere;
    @FXML
    private JFXButton buEdita;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private JFXButton buApaga;
    @FXML
    private Tab tabPesquisa;
    @FXML
    private JFXButton buPesquisa;
    @FXML
    private JFXButton buLimpa;
    @FXML
    private JFXButton buAbre;
    @FXML
    private FontAwesomeIconView buAbreRec;
    private TableView<EsGrade> tvPesquisa;
    @FXML
    private TableColumn<EsGrade, Integer> tc1;
    @FXML
    private TableColumn<EsGrade, Integer> tc2;
    @FXML
    private TableColumn<EsGrade, Integer> tc3;
    @FXML
    private JFXComboBox<EsGrade> cbIndice;
    @FXML
    private JFXComboBox<EsGrade> cbCondicao;
    @FXML
    private JFXTextField txtPesq;
    @FXML
    private TextField lblRegistros;
    @FXML
    private TableView<Grade> tvGrade;
    @FXML
    private TextField edAnoLet;
    @FXML
    private Label edSemestre;
    @FXML
    private TableView<Grade> tvGradeDet;
    @FXML
    private TableColumn<Grade, String> tcDatas;
    @FXML
    private TableColumn<Grade, String> tcHorarios;
    @FXML
    private TableColumn<Grade, String> tcDisciplinas;
    @FXML
    private TableColumn<Grade, String> tcProf1;
    @FXML
    private TableColumn<Grade, String> tcProf2;
    @FXML
    private TableColumn<Grade, Integer> tcCred;
    
    private boolean inserindo, editando;
    private EsGradeJpaController jpaGraM;
    
    private ObservableList<String> dadosGraM;
    private ObservableList<String> dadosGraD;
    Grade reg_atual = new Grade();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tvGradeDet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        AbreDisGrade();
                    }
                }
            }
        });
        
        jpaGraM = new EsGradeJpaController();
        inserindo = false;
        editando = false;
//        desligaEdicao();
//        initColumns();
    }    
    
    public void AbreDisGrade() {
        if (tvPesquisa.getSelectionModel().getSelectedItem().equals(null)) {
            // aqui vai abrir a janela das Disciplinas INSERINDO
        } else {
            reg_atual = tvGradeDet.getSelectionModel().getSelectedItem();

            //PASSA REGISTRO SELECIONADO PARA A VARIÁVEL GLOBAL rgRegDisc, que será usado na tela TabelaDadocadastrogerals
//            gbGradeDet = jpaGra.findEsGradedet( reg_atual.getCodGradeDet());

            // ABRE CADASTRODISCIPLINA (FILHO) DO REGISTRO SELECIONADO (DISCIPLINA-PAI)
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/GradeDetDis.fxml"));
                Stage stageCadastrais = new Stage(StageStyle.DECORATED);
                stageCadastrais.initModality(Modality.WINDOW_MODAL);
                stageCadastrais.setTitle("Dadocadastrogeral");
                stageCadastrais.setScene(new Scene(parent));
                stageCadastrais.show();

                // LibraryAssistantUtil.setStageIcon(stage);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("=====================");
                System.out.println(ex);
                System.out.println("=====================");
            }
        }
    }

    @FXML
    private void clicouInsere(ActionEvent event) {
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
