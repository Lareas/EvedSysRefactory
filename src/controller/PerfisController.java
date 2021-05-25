/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import static a000_main.Login.gbRegProg;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import entities.AluProCurDis;
import entities.Dadocadastroprograma;
import funcoes.ComboBoxAutoComplete;
import funcoes.DBConnector;
import java.net.URL;
import java.sql.Connection;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import jpa_controler.CadaludissitJpaController;
import jpa_controler.CursoJpaController;
import jpa_controler.EspecificidadeJpaController;
import jpa_controler.LocalidadeJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class PerfisController implements Initializable {

    @FXML
    private ComboBox<String> cbCurso;
    @FXML
    private TextField edAnoLetivo;
    @FXML
    private JFXDatePicker dpDataCad;
    @FXML
    private TextField tfNomePrograma;
    private ComboBox<String> cbEspec;
    @FXML
    private ComboBox<String> cbLocalidade;
    @FXML
    private ComboBox<Integer> cbSemestre;
    @FXML
    private ComboBox<String> cbDuracao;
    @FXML
    private TextField tfNomePrograma1;
    @FXML
    private JFXDatePicker dpCursando;
    @FXML
    private JFXDatePicker dpReativado;
    @FXML
    private JFXDatePicker dpTrancamento;
    @FXML
    private JFXDatePicker dpDesist;
    @FXML
    private JFXDatePicker dpAband;
    @FXML
    private JFXDatePicker dpColacao;
    @FXML
    private ComboBox<String> cbSituacao;
    @FXML
    private TextArea taObs;
    @FXML
    private TextField tfNomePrograma11;
    @FXML
    private CheckBox chkFoto;
    @FXML
    private CheckBox chkHist2;
    @FXML
    private CheckBox chkHist3;
    @FXML
    private CheckBox chkFotRG;
    @FXML
    private CheckBox chkFotCpf;
    @FXML
    private CheckBox chkReq;
    @FXML
    private CheckBox chkQuest;
    @FXML
    private CheckBox chkCarta;
    @FXML
    private TextField tfNomePrograma111;
    @FXML
    private Label dpDataVest;
    @FXML
    private TextField edMediaFinal;
    @FXML
    private JFXDatePicker dpNasc2;
    @FXML
    private TextField lblRegistrosMin;
    @FXML
    private TableView<AluProCurDis> tvRegDis;
    @FXML
    private TableColumn<AluProCurDis, String> tcDis;
    @FXML
    private TableColumn<AluProCurDis, String> tcProf;
    @FXML
    private TableColumn<AluProCurDis, Float> tcMed;
    @FXML
    private TableColumn<AluProCurDis, Integer> tcFal;
    @FXML
    private TableColumn<AluProCurDis, String> tcSit;
    @FXML
    private TableColumn<AluProCurDis, String> tcFreq;
    @FXML
    private TableColumn<AluProCurDis, Integer> tcCre;
    @FXML
    private TableColumn<AluProCurDis, Integer> tcCar;
    @FXML
    private TableColumn<AluProCurDis, Integer> tcAno;
    @FXML
    private TableColumn<AluProCurDis, Integer> tcSem;
    @FXML
    private TableColumn<AluProCurDis, String> tcSup;
    @FXML
    private ComboBox<Integer> cbAnoFiltro;
    @FXML
    private RadioButton rb1Sem;
    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton rb2Sem;
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

    private boolean inserindo, editando;
    private ObservableList<String> dadosCS;
    private ObservableList<String> dadosLO;
    private ObservableList<String> dadosES;
    private ObservableList<String> dadosSI;

    private CursoJpaController jpaCS;
    private LocalidadeJpaController jpaLO;
    private EspecificidadeJpaController jpaES;
    private CadaludissitJpaController jpaSI;

    Dadocadastroprograma reg_pro = new Dadocadastroprograma();
    ObservableList<AluProCurDis> prolist;

    public String sqlProgramas
            = "SELECT   g.nome, cadi.disciplina, fu.nome, aludi.media, aludi.falta, alusit.descricao, fre.frequencia, cadi.credito, cadi.cargahoraria, cadi.anoletivo, cadi.semestreid, aludi.supervisionada, p.DadoCadastroGeralId , p.DadoCadastroProgramaId,  aludi.CadastroDisciplinaId, aludi.CadastroAlunoDisciplinaSituacaoId "
            + " FROM  dadocadastroprograma p, dadocadastrogeral g, cadastroalunodisciplina aludi, cadastrodisciplina cadi, funcionario fu, cadaludissit alusit, frequencia fre "
            + " WHERE "
            + " (p.DadoCadastroGeralId = g.DadoCadastroGeralId) "
            + " and (p.DadoCadastroProgramaId = aludi.DadoCadastroProgramaId) "
            + " and (aludi.CadastroDisciplinaId = cadi.CadastroDisciplinaId) "
            + " and (cadi.funcionarioid = fu.funcionarioid) "
            + " and (alusit.cads_id = aludi.CadastroAlunoDisciplinaSituacaoId) "
            + " and (aludi.FrequenciaId = fre.FrequenciaId) "
            + " and (p.DadoCadastroGeralId = 3158) "
            + " order by p.DadoCadastroProgramaId, cadi.anoletivo, cadi.semestreid, cadi.disciplina ";

    public Connection con = DBConnector.getConnection();
    @FXML
    private JFXButton buRefUFAluno;
    @FXML
    private JFXButton buRefUFAluno1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        reg_pro = gbRegProg;

        jpaCS = new CursoJpaController();
        jpaLO = new LocalidadeJpaController();
        jpaES = new EspecificidadeJpaController();
        jpaSI = new CadaludissitJpaController();
        
        cbCurso.setItems(getCurso());
        cbLocalidade.setItems(getLocalidade());
        cbEspec.setItems(getEspec());
        cbSituacao.setItems(getSituacao());
        cbDuracao.setItems(getDuracao());
        
        new ComboBoxAutoComplete<String>(cbCurso, "");
        new ComboBoxAutoComplete<String>(cbLocalidade, "");
        new ComboBoxAutoComplete<String>(cbEspec, "");
        new ComboBoxAutoComplete<String>(cbSituacao, "");
        new ComboBoxAutoComplete<String>(cbDuracao, "");
        
        
        ObservableList<Integer> itSem = FXCollections.observableArrayList(1,2);
        cbSemestre.setItems(itSem);

        inserindo = false;
        editando = false;
        desligaEdicao();
//        initColsMin();
//        initColsPro();
//
//        preencheEdsComObj();
//
//        // CARREGA COMBOBOXES
//        cbUFAluno.setItems(getNomeUF());
//        cbEstadoCivil.setItems(getNomeEstadoCivil());
//        cbUFNatu.setItems(getNomeUF());
//        cbMedioUF.setItems(getNomeUF());
//        cbSuperiorUF.setItems(getNomeUF());
//        cbDenominacao.setItems(getNomeDenominacao());
//        cbDocsOficiais.setItems(getNomeDocsOficiais());
//        cbFormacao.setItems(getNomeFormacao());
//        cbProfissao.setItems(getNomeProfissao());
//        //  cbUFIgreja.setItems(getNomeUF());
//        cbNacionalidade.setItems(getNomeNacionalidades());

        buApaga.setVisible(true);
    }
    
     public void preencheEdsComObj() {
        // combos
        cbCurso.getSelectionModel().select((reg_pro.getNomeCurso()!= null) ? reg_pro.getNomeCurso().getCurso() : null);
        cbLocalidade.getSelectionModel().select((reg_pro.getLocalidade()!= null) ? reg_pro.getLocalidade(): null);
        cbEspec.getSelectionModel().select((reg_pro.getEspecificidade()!= null) ? reg_pro.getEspecificidade() : null);
        cbSituacao.getSelectionModel().select((reg_pro.getNomeSituacao()!= null) ? reg_pro.getNomeSituacao().getDadoCadastroProgramaSituacao() : null);
        cbSemestre.getSelectionModel().select((reg_pro.getSemestreId() != null) ? reg_pro.getSemestreId() : null);
        cbDuracao.getSelectionModel().select((reg_pro.getNomeCurso() != null) ? reg_pro.getNomeCurso().getCurso() : null);

        dpDataCad.setValue((reg_pro.getDataCadastro() != null) ? reg_pro.getDataCadastro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        dpCursando.setValue((reg_pro.getCursando()!= null) ? reg_pro.getCursando().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        dpReativado.setValue((reg_pro.getReativado()!= null) ? reg_pro.getReativado().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        dpTrancamento.setValue((reg_pro.getMatriculaTrancada()!= null) ? reg_pro.getMatriculaTrancada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        dpDesist.setValue((reg_pro.getDesistencia()!= null) ? reg_pro.getDesistencia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        dpAband.setValue((reg_pro.getAbandono()!= null) ? reg_pro.getAbandono().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        dpColacao.setValue((reg_pro.getColacaoGrau()!= null) ? reg_pro.getColacaoGrau().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        
        chkFoto.setSelected((reg_pro.getFoto3X4() != null) ? reg_pro.getFoto3X4() : null);
//        chkHist2.setSelected((reg_pro.get() != null) ? reg_pro.getFoto3X4() : null);
//        chkHist3.setSelected((reg_pro.getFoto3X4() != null) ? reg_pro.getFoto3X4() : null);
//        chkFotRG.setSelected((reg_pro.getFoto3X4() != null) ? reg_pro.getFoto3X4() : null);
//        chkFotCpf.setSelected((reg_pro.getFoto3X4() != null) ? reg_pro.getFoto3X4() : null);
//        chkReq.setSelected((reg_pro.getFoto3X4() != null) ? reg_pro.getFoto3X4() : null);
//        chkQuest.setSelected((reg_pro.getFoto3X4() != null) ? reg_pro.getFoto3X4() : null);
//        chkCarta.setSelected((reg_pro.getFoto3X4() != null) ? reg_pro.getFoto3X4() : null);
        

        edMediaFinal.setEditable(false);
        dpNasc2.setDisable(true);
     }
     
    public ObservableList<String> getCurso() {
        jpaCS = new CursoJpaController();
        dadosCS = FXCollections.observableArrayList(jpaCS.getNomeDosCursos());

        if (dadosCS == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosCS;
        }
    }
    
    public ObservableList<String> getLocalidade() {
        jpaLO = new LocalidadeJpaController();
        dadosLO = FXCollections.observableArrayList(jpaLO.getNomeLocalidades());

        if (dadosLO == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosLO;
        }
    }
    
    public ObservableList<String> getEspec() {
        jpaES = new EspecificidadeJpaController();
        dadosES = FXCollections.observableArrayList(jpaES.getNomeEsp());

        if (dadosES == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosES;
        }
    }
    
    public ObservableList<String> getSituacao() {
        jpaSI = new CadaludissitJpaController();
        dadosSI = FXCollections.observableArrayList(jpaSI.getNomeSituacao());

        if (dadosSI == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosSI;
        }
    }
    
    
    
    public ObservableList<String> getDuracao() {
        jpaCS = new CursoJpaController();
        dadosCS = FXCollections.observableArrayList(jpaCS.getNomeDosCursos());

        if (dadosCS == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosCS;
        }
    }
    
    
    public void desligaEdicao() {

        // combos
        cbCurso.setStyle("-fx-opacity: 1;");
        cbLocalidade.setStyle("-fx-opacity: 1;");
        cbEspec.setStyle("-fx-opacity: 1;");
        cbSituacao.setStyle("-fx-opacity: 1;");
        cbCurso.setDisable(true);
        cbLocalidade.setDisable(true);
        cbEspec.setDisable(true);
        cbSituacao.setDisable(true);
        cbSemestre.setDisable(true);
        cbDuracao.setDisable(true);

        // habilita botões para inserção e deleção
        buInsere.setDisable(false);
        buEdita.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buApaga.setDisable(false);

        dpDataCad.setEditable(false);
        dpCursando.setEditable(false);
        dpReativado.setEditable(false);
        dpTrancamento.setEditable(false);
        dpDesist.setEditable(false);
        dpAband.setEditable(false);
        dpColacao.setEditable(false);
        chkFoto.setDisable(true);
        chkHist2.setDisable(true);
        chkHist3.setDisable(true);
        chkFotRG.setDisable(true);
        chkFotCpf.setDisable(true);
        chkReq.setDisable(true);
        chkQuest.setDisable(true);
        chkCarta.setDisable(true);

        edMediaFinal.setEditable(false);
        dpNasc2.setDisable(true);
    }
    public void LigaEdicao() {

        // combos
        cbCurso.setDisable(false);
        cbLocalidade.setDisable(false);
        cbEspec.setDisable(false);
        cbSituacao.setDisable(false);
        cbSemestre.setDisable(false);
        cbDuracao.setDisable(false);

        // habilita botões para inserção e deleção
        buInsere.setDisable(true);
        buEdita.setDisable(true);
        buConfirma.setDisable(false);
        buCancela.setDisable(false);
        buApaga.setDisable(true);

        dpDataCad.setEditable(true);
        dpCursando.setEditable(true);
        dpReativado.setEditable(true);
        dpTrancamento.setEditable(true);
        dpDesist.setEditable(true);
        dpAband.setEditable(true);
        dpColacao.setEditable(true);
        
        chkFoto.setDisable(false);
        chkHist2.setDisable(false);
        chkHist3.setDisable(false);
        chkFotRG.setDisable(false);
        chkFotCpf.setDisable(false);
        chkReq.setDisable(false);
        chkQuest.setDisable(false);
        chkCarta.setDisable(false);

        edMediaFinal.setEditable(true);
        dpNasc2.setDisable(false);
    }
    
    public void limpaCampos() {
        cbCurso.setValue(null);
        cbLocalidade.setValue(null);
        cbEspec.setValue(null);
        cbSituacao.setValue(null);
        cbSemestre.setValue(null);
        cbDuracao.setValue(null);

        dpDataCad.setValue(null);
        dpCursando.setValue(null);
        dpReativado.setValue(null);
        dpTrancamento.setValue(null);
        dpDesist.setValue(null);
        dpAband.setValue(null);
        dpColacao.setValue(null);
        
        chkFoto.setIndeterminate(true);
        chkHist2.setIndeterminate(true);
        chkHist3.setIndeterminate(true);
        chkFotRG.setIndeterminate(true);
        chkFotCpf.setDisable(false);
        chkReq.setIndeterminate(true);
        chkQuest.setIndeterminate(true);
        chkCarta.setIndeterminate(true);

        edMediaFinal.setText("");
        dpNasc2.setValue(null);
    }

    @FXML
    private void clicouFiltra(ActionEvent event) {
    }

    @FXML
    private void clicouLimpa(ActionEvent event) {
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
    private void clicouRefresUFResid(ActionEvent event) {
    }

}
