package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.AlunosNestaDisciplina;
import funcoes.ComboBoxAutoComplete;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jpa_controler.EspecificidadeJpaController;
import jpa_controler.FuncionarioJpaController;
import jpa_controler.LocalidadeJpaController;
import jpa_controler.TurmaJpaController;
import jpa_controler.TurnoJpaController;

public class DisciplinasSemestreController implements Initializable {

    @FXML
    private ComboBox<String> cbLocalidade;
    @FXML
    private ComboBox<String> cbArea;
    @FXML
    private ComboBox<String> cbProf1;
    @FXML
    private ComboBox<String> cbProf2;
    @FXML
    private ComboBox<String> cbTurno;
    @FXML
    private ComboBox<String> cbTurma;
    @FXML
    private TextField edAnoLetivo;
    @FXML
    private TextField tfNomePrograma1;
    @FXML
    private TableView<AlunosNestaDisciplina> tvRegDis;
    @FXML
    private TableColumn<AlunosNestaDisciplina, String> tcSit;
    @FXML
    private TableColumn<AlunosNestaDisciplina, String> tcFreq;
    @FXML
    private TableColumn<AlunosNestaDisciplina, String> tcAluno;
    @FXML
    private TableColumn<AlunosNestaDisciplina, Float> tcMedia;
    @FXML
    private TableColumn<AlunosNestaDisciplina, Short> tcFaltas;
    @FXML
    private TextArea taConsist;
    @FXML
    private TextField tfStatusDados;
    @FXML
    private TextField tfDataInc;
    @FXML
    private TextField tfDataAtu;
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
    private TextField tfCodCadProg;
    @FXML
    private TextField tfNomeAluno;
    @FXML
    private TextArea taDesc;
    @FXML
    private TextField edCredito;
    @FXML
    private TextField edCargaH;
    @FXML
    private RadioButton rb1SemDis;
    @FXML
    private RadioButton rb2SemDis;
    @FXML
    private JFXDatePicker dpDataIni;
    @FXML
    private TextField edHoraIni;
    @FXML
    private TextField edHoraFim;
    @FXML
    private TextField edNumAulas;
    @FXML
    private JFXCheckBox chkDom;
    @FXML
    private JFXCheckBox chkSeg;
    @FXML
    private JFXCheckBox chkTer;
    @FXML
    private JFXCheckBox chkQua;
    @FXML
    private JFXCheckBox ckQui;
    @FXML
    private JFXCheckBox chkSex;
    @FXML
    private JFXCheckBox chkSab;
    @FXML
    private TextField edLimReg;
    @FXML
    private TextField edLimOuv;
    @FXML
    private JFXCheckBox chkCom;
    @FXML
    private JFXCheckBox chkSup;
    @FXML
    private JFXCheckBox chkDis;
    @FXML
    private JFXButton buRefArea;
    @FXML
    private JFXButton buRefProf1;
    @FXML
    private JFXButton buRefProf2;
    @FXML
    private JFXButton buRefUFAluno111;
    @FXML
    private FontAwesomeIconView buLocalidade;
    @FXML
    private TextField tfNomePrograma12;
    @FXML
    private JFXButton buRefTurno;
    @FXML
    private JFXButton buRefTurma;
    @FXML
    private TextField lblAlunos;
    @FXML
    private JFXButton buCopia;
    
    private ObservableList<String> dadosLO;
    private ObservableList<String> dadosAR;
    private ObservableList<String> dadosPR;
    private ObservableList<String> dadosTN;
    private ObservableList<String> dadosTM;
    
    private LocalidadeJpaController jpaLO;
    private EspecificidadeJpaController jpaAR;
    private FuncionarioJpaController jpaPR;
    private TurnoJpaController jpaTN;
    private TurmaJpaController jpaTM;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbLocalidade.setItems(getLocalidades());
        cbArea.setItems(getAreas());
        cbProf1.setItems(getProfessores());
        cbProf2.setItems(getProfessores());
        cbTurno.setItems(getTurnos());
        cbTurma.setItems(getTurmas());
        
        new ComboBoxAutoComplete<String>(cbLocalidade, "");
        new ComboBoxAutoComplete<String>(cbArea, "");
        new ComboBoxAutoComplete<String>(cbProf1, "");
        new ComboBoxAutoComplete<String>(cbProf2, "");
        new ComboBoxAutoComplete<String>(cbTurno, "");
        new ComboBoxAutoComplete<String>(cbTurma, "");
    }
    
    public ObservableList<String> getLocalidades() {
        jpaLO = new LocalidadeJpaController();
        dadosLO = FXCollections.observableArrayList(jpaLO.getNomeDasLocalidades());

        if (dadosLO == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosLO;
        }
    }
    
    public ObservableList<String> getAreas() {
        jpaAR = new EspecificidadeJpaController();
        dadosAR = FXCollections.observableArrayList(jpaAR.getNomeEsp());

        if (dadosAR == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosAR;
        }
    }
    
    public ObservableList<String> getProfessores() {
        jpaPR = new FuncionarioJpaController();
        dadosPR = FXCollections.observableArrayList(jpaPR.getNomeDosProfessores());

        if (dadosPR == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosPR;
        }
    }
    
    public ObservableList<String> getTurnos() {
        jpaTN = new TurnoJpaController();
        dadosTN = FXCollections.observableArrayList(jpaTN.getNomeTurno());

        if (dadosTN == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosTN;
        }
    }
    
    public ObservableList<String> getTurmas() {
        jpaTM = new TurmaJpaController();
        dadosTM = FXCollections.observableArrayList(jpaTM.getNomeTurmas());

        if (dadosTM == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosTM;
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
    private void clicouCopia(ActionEvent event) {
    }

}
