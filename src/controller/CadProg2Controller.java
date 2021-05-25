/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.ESVERSION;
import static main.Login.gbAtuNotas;
import static main.Login.gbClicouInsPro;
import static main.Login.gbRegCadGer;
import static main.Login.gbRegMatriSem;
import static main.Login.gbRegProAlu;
import static main.Login.gbDisProAlu;
import static main.Login.gbEspecial;
import static main.Login.gbInsPro;
import static main.Login.gbJanSituDis;
import static main.Login.gbUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Atu_Notas;
import entities.ChecaErros;
import entities.Curso;
import entities.Dadocadastroprograma;
import entities.DisMSgrid;
import entities.DisciProgAluno;
import entities.EsFotosalunos;
import entities.EsMatrisem;
import entities.MatriSemestre;
import funcoes.ComboBoxAutoComplete;
import funcoes.DBConnector;
import funcoes.MyFunc;
//import static funcoes.MyFunc.checaCombo;
import static funcoes.MyFunc.checaTTF;
import static funcoes.MyFunc.convInt;
import static funcoes.MyFunc.mostraMsg;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import jpa_controler.CursoJpaController;
import jpa_controler.DadocadastroprogramaJpaController;
import jpa_controler.EspecificidadeJpaController;
import jpa_controler.LocalidadeJpaController;
import java.util.Date;
import java.util.List;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.DadocadastroprogramasituacaoJpaController;
import jpa_controler.EsFotosalunosJpaController;
import jpa_controler.EsMatrisemJpaController;
import jpa_controler.FrequenciaJpaController;
import jpa_controler.TurnoJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class CadProg2Controller implements Initializable {

    @FXML
    private TextField tfTitulacao;
    @FXML
    private TextField edAnoLetivo;
    @FXML
    private JFXDatePicker dpDataCad;
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
    private TextArea taObs;
    @FXML
    private JFXButton buRefUFAluno;
    @FXML
    private JFXButton buRefUFAluno1;
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
    private JFXDatePicker dpDataVest;
    @FXML
    private TextField edMediaFinal;
    @FXML
    private RadioButton rb1Sem;
    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton rb2Sem;
    @FXML
    private TextArea taBiografia;
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
    private ComboBox<String> cbPrograma;
    @FXML
    private ComboBox<String> cbLocalidade;
    @FXML
    private ComboBox<String> cbTurno;
//    @FXML
//    private ComboBox<String> cbFrequencia;
    @FXML
    private TableView<DisciProgAluno> tvRegDis;
    @FXML
    private TableColumn<DisciProgAluno, String> tcDis;
    @FXML
    private TableColumn<DisciProgAluno, String> tcProf;
    @FXML
    private TableColumn<DisciProgAluno, Float> tcMed;
    @FXML
    private TableColumn<DisciProgAluno, Short> tcFal;
    @FXML
    private TableColumn<DisciProgAluno, String> tcSit; ///////////////////
    @FXML
    private TableColumn<DisciProgAluno, String> tcFreq;
    @FXML
    private TableColumn<DisciProgAluno, Short> tcCre;
    @FXML
    private TableColumn<DisciProgAluno, Short> tcCar;
    @FXML
    private TableColumn<DisciProgAluno, Short> tcAno;
    @FXML
    private TableColumn<DisciProgAluno, Short> tcSem;
    @FXML
    private ComboBox<Integer> cbAnoFiltro;
    @FXML
    private RadioButton rb1SemPro;
    @FXML
    private RadioButton rb2SemPro;
    @FXML
    private TextArea taConsist;
    @FXML
    private TextField tfStatusDados;
    @FXML
    private TextField tfDataInc;
    @FXML
    private TextField tfDataAtu;
    @FXML
    private TextField lblRegistroDisProAlu;
    @FXML
    private ComboBox<String> cbSitPro;
    @FXML
    private Label lblCodAluno;
    @FXML
    private ImageView ivFotoAluno;
    @FXML
    private TableColumn<DisciProgAluno, Integer> tcCAD;
    @FXML
    private TextField tfPrograma;
    @FXML
    private CheckBox chkBioReli;

    @FXML
    private FontAwesomeIconView fvBoneco1;

    @FXML
    private JFXButton buRecIni1;
    @FXML
    private TableView<MatriSemestre> tvMatriSemestre;
    @FXML
    private TableColumn<MatriSemestre, Date> tcDataMSem;
    @FXML
    private TableColumn<MatriSemestre, Short> tcAnoMSem;
    @FXML
    private TableColumn<MatriSemestre, Short> tcSemMSem;
    @FXML
    private TableColumn<MatriSemestre, Integer> tcCMS;
    @FXML
    private Pane top_pane1;
    @FXML
    private JFXButton buReciRec1;
    @FXML
    private JFXButton buReciPes1;
    @FXML
    private TableView<DisMSgrid> tvDisMSem;
    @FXML
    private TableColumn<DisMSgrid, String> tcTipoAluno1;
    @FXML
    private TableColumn<DisMSgrid, String> tcData1;
    @FXML
    private TableColumn<DisMSgrid, String> tcHorario1;
    @FXML
    private TableColumn<DisMSgrid, String> tcDisciplina1;
    @FXML
    private TableColumn<DisMSgrid, String> tcProfessor1;
    @FXML
    private TableColumn<DisMSgrid, Short> tcCred1;
    @FXML
    private JFXDatePicker dpDataMSem;

    @FXML
    private TextField edLeAno;
    @FXML
    private RadioButton rbLeSem1;
    @FXML
    private ToggleGroup tg7;
    @FXML
    private RadioButton rbLeSem2;
    @FXML
    private TextField edLeAno1;
    @FXML
    private RadioButton rbLeSem11;
    @FXML
    private ToggleGroup tg5;
    @FXML
    private RadioButton rbLeSem21;

    private boolean inserindo, editando;
    private ObservableList<String> dadosCS;
    private ObservableList<String> dadosLO;
    private ObservableList<String> dadosES;
    private ObservableList<String> dadosSIP;
    private ObservableList<String> dadosTU;
    private ObservableList<String> dadosFR;
    private ObservableList<MatriSemestre> dadosMS;
    private ObservableList<DisMSgrid> dadosDS;

    private EsMatrisemJpaController jpaMS;
    private CursoJpaController jpaCS;
    private LocalidadeJpaController jpaLO;
    private EspecificidadeJpaController jpaES;
    private DadocadastroprogramasituacaoJpaController jpaSIP;
    private FrequenciaJpaController jpaFR;
    private TurnoJpaController jpaTU;
    private CadastroalunodisciplinaJpaController jpaCon;
    private DadocadastroprogramaJpaController jpaDCP = new DadocadastroprogramaJpaController();
    private CursoJpaController jpaCr;

    DisciProgAluno reg_disci = new DisciProgAluno();
    Dadocadastroprograma reg_prog = new Dadocadastroprograma();
    EsMatrisem reg_ms;

    public static Stage stgJanSituDis;

    ObservableList<DisciProgAluno> disProList;

    public String sqlDisProAlu;

    public Connection con = DBConnector.getConnection();
    Curso curso = new Curso();

    public int semHoje, mesHoje, anoHoje;
    //   public Short insercaoPendente = null;
    public final String SQLBASE
            = "SELECT pup.CadastroAlunoDisciplinaId, cur.curso, cad.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao, "
            + "fr.frequencia, cad.Credito, cad.CargaHoraria, cad.AnoLetivo, cad.SemestreId  "
            + "FROM cadastroalunodisciplina pup  "
            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
            + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId  "
            + "LEFT JOIN funcionario fu ON fu.FuncionarioId = cad.FuncionarioId "
            + "LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id "
            + "LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId "
            + "LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId   ";
//            = "SELECT pup.CadastroAlunoDisciplinaId, cur.curso, cad.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao, fr.frequencia, cad.Credito, cad.CargaHoraria, cad.AnoLetivo, cad.SemestreId  "
//            + "FROM cadastroalunodisciplina pup  "
//            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
//            + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cd.CadastroDisciplinaId  "
//            + "LEFT JOIN funcionario fu ON fu.FuncionarioId = cad.FuncionarioId  "
//            + "LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id  "
//            + "LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId  "
//            + "LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId ";
//            = "SELECT pup.CadastroAlunoDisciplinaId, cur.curso, cd.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao, fr.frequencia, cad.Credito, cad.CargaHoraria, cad.AnoLetivo, cd.SemestreId  "
//            + "FROM cadastroalunodisciplina pup "
//            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId "
//            + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cd.CadastroDisciplinaId "
//            + "LEFT JOIN funcionario fu ON fu.FuncionarioId = cd.FuncionarioId  "
//            + "LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id "
//            + "LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId "
//            + "LEFT JOIN frequencia fr ON cad.FrequenciaId = fr.FrequenciaId  ";

    public final String SQLMATRISEM
            = "SELECT mse.CodMatriSem, mse.DataMatri, mse.AnoLetivo, mse.SemestreId "
            + " FROM es_matrisem mse";

    private int totCre;
    private int totCreAprov;

    private EsFotosalunosJpaController jpaFotos;
    EsFotosalunos reg_foto = new EsFotosalunos();
    private ObservableList<EsFotosalunos> dadosFoto;
    @FXML
    private ToggleGroup w2;
    @FXML
    private Line lineRed;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lineRed.setVisible(gbEspecial);
        desligaMatri();

        lblCodAluno.setText(String.valueOf(gbRegCadGer.getDadoCadastroGeralId()));

        tvMatriSemestre.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                inicJanelaMatriSem();
            }
        });

        tcDataMSem.setCellFactory(column -> {
            TableCell<MatriSemestre, Date> cell = new TableCell<MatriSemestre, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if ((empty) || (item == null)) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });

        tvRegDis.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        AbreAtualiza_Notas();
                    }
                }
            }
        });

//        tvDisMatri.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//          //      initRegDis();
//            }
//        });
        // SERÁ QUE DÁ PARA ABRIR PARA EDIÇÃO COM DOIS CLIQUES E INSERIR COM UM CLIQUE SÓ?
//        tvDisMatri.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
//                    if (mouseEvent.getClickCount() == 2) {
//                        //    AbrePrograma(2);
//                    }
//                }
//            }
//        });
        jpaCS = new CursoJpaController();
        jpaLO = new LocalidadeJpaController();
        jpaES = new EspecificidadeJpaController();
        jpaSIP = new DadocadastroprogramasituacaoJpaController();
        jpaTU = new TurnoJpaController();
        jpaMS = new EsMatrisemJpaController();
        jpaCr = new CursoJpaController();

        cbPrograma.setItems(getCurso());
        cbLocalidade.setItems(getLocalidade());
        //    cbEspec.setItems(getEspec());
        cbSitPro.setItems(getSituacaoPro());
        //  cbFrequencia.setItems(getFrequencia());
        cbTurno.setItems(getTurno());
        new ComboBoxAutoComplete<String>(cbPrograma, "Programas");
        LigaEdicao();

        cbPrograma.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (cbPrograma.getSelectionModel().selectedItemProperty() != null) {
                    tfPrograma.setText(cbPrograma.getSelectionModel().getSelectedItem());
                    //   lblEspec.setText(SQLBASE);
                }
//                if (cbPrograma.getSelectionModel().selectedItemProperty() != null) {
//                    curso = jpaCr.getObjeto(cbPrograma.getSelectionModel().getSelectedItem());
//                    tfPrograma.setText(curso.getCurso());
//                    tfTitulacao.setText(curso.getTitulacao());
////                    if (verifProExiste(cbPrograma.getSelectionModel().getSelectedItem())) {
////                        mostraMsg("O aluno já está matriculado neste programa.", "Não é possivel inserir outra matrícula master para o mesmo programa em aberto.", 0);
////                    }
//                }
            }
        });

        reg_prog = gbRegProAlu;
        buApaga.setVisible(true);
        if (gbClicouInsPro) { // está inserindo
            // GERA UM REGISTRO COM PRÓXIMO CÓDIGO (PK AI) E NOME PROVISÓRIO
            gbClicouInsPro = false;
            //   geraProProvis();
            inserindo = true;
            editando = false;
            limpaCampos();
            preencheBaseIns();
            // preencheEdsComObj();
            cbPrograma.requestFocus();
        } else { // está editando

            reg_prog = gbRegProAlu;
            String filtro = calcFiltro();
            if (!filtro.equals("")) {
                filtro = " AND (" + filtro + ")";
            }
            sqlDisProAlu = SQLBASE + " WHERE (pup.DadoCadastroProgramaId = " + reg_prog.getDadoCadastroProgramaId()
                    + " AND pup.DadoCadastroProgramaId = " + gbRegCadGer.getDadoCadastroGeralId() + filtro
                    + " ORDER BY pup.DadoCadastroProgramaId, cad.AnoLetivo, cad.SemestreId ";

            iniMatriSem();

            inserindo = false;
            editando = true;

//            reg_prog = new Dadocadastroprograma();
//            reg_prog = jpaDCP.findDadocadastroprograma(gbRegProAlu.getDadoCadastroProgramaId());
            cbAnoFiltro.setItems((ObservableList<Integer>) getAnoDistinct());

            Calendar cal = Calendar.getInstance();
            System.out.println(cal.getTime());

            mesHoje = cal.get(Calendar.MONTH) + 1;
            anoHoje = cal.get(Calendar.YEAR);
            semHoje = 0;
            edAnoLetivo.setText(String.valueOf(anoHoje));

            if (mesHoje > 6) {
                rb2Sem.selectedProperty().setValue(true);
                semHoje = 2;
            } else {
                rb1Sem.selectedProperty().setValue(true);
                semHoje = 1;
            }
            cbAnoFiltro.getSelectionModel().selectLast();
            inserindo = false;
            editando = true;

            preencheEdsComObj();
            LigaEdicao();
        }
    }

    private Boolean verifProExiste(String programa) {
        List<Dadocadastroprograma> lis = jpaDCP.getCursoExato(programa, Integer.valueOf(tfCodCadProg.getText()));

        if (lis.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void inicJanelaMatriSem() {
        // reg_ms = new EsMatrisem();

        if (tvMatriSemestre.getSelectionModel().getSelectedItem() != null) {
            gbRegMatriSem = jpaMS.findEsMatrisem(tvMatriSemestre.getSelectionModel().getSelectedItem().getCms());
            dpDataMSem.setValue((gbRegMatriSem.getDataMatri() != null) ? gbRegMatriSem.getDataMatri().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);

            // MELHORAR - VERRIFICAR APÓS TIVERMOS DADOS MAIS CONCRETOS
            if (gbRegMatriSem.getSemestreId() == 1) {
                rbLeSem1.setSelected(true);
            } else {
                rbLeSem2.setSelected(true);
            }
            edLeAno.setText(String.valueOf(tvMatriSemestre.getSelectionModel().getSelectedItem().getAnoLetivo()));

            inicDisMSem();
        }

    }

    private void iniMatriSem() {
        dadosMS = FXCollections.observableArrayList();
        if (tfPrograma.getText() != null && tfPrograma.getText() != ""
                && gbRegProAlu.getDadoCadastroProgramaId() > 0) {

            String sqlMatriS = SQLMATRISEM
                    + " WHERE mse.DadoCadastroGeralId = " + lblCodAluno.getText() + " "
                    + "  AND mse.DadoCadastroProgramaId = " + gbRegProAlu.getDadoCadastroProgramaId() + " "
                    + "  ORDER BY mse.AnoLetivo desc, mse.SemestreId ";

            try {
                System.out.println(sqlMatriS);

                ResultSet rs = con.createStatement().executeQuery(sqlMatriS);
                while (rs.next()) {
                    dadosMS.add(new MatriSemestre(
                            rs.getShort("mse.CodMatriSem"),
                            rs.getDate("mse.DataMatri"),
                            rs.getShort("mse.AnoLetivo"),
                            rs.getShort("mse.SemestreId")));

                }

            } catch (SQLException ex) {
                Logger.getLogger(CadProg2Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018e - em iniMatriSem()", "" + ex, 2);
            }
        }

        tcCMS.setCellValueFactory(new PropertyValueFactory<>("cms"));
        tcDataMSem.setCellValueFactory(new PropertyValueFactory<>("dataMatri"));
        tcAnoMSem.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tcSemMSem.setCellValueFactory(new PropertyValueFactory<>("semestre"));

        tvMatriSemestre.setItems(dadosMS);
        tvMatriSemestre.refresh();
    }

    private void inicDisMSem() {
        dadosDS = FXCollections.observableArrayList();
        if (tvMatriSemestre.getSelectionModel().getSelectedItem() != null) {

            String sqlLoadGradeMS
                    = "SELECT cad.codGrade, cad.labelData, cad.labelHora, cad.DisciplinaId, dis.Disciplina, fun.nome, cad.credito  "
                    + " FROM cadastrodisciplina cad  "
                    + " LEFT JOIN es_gradedisprg prg ON cad.CadastroDisciplinaId = prg.CadastroDisciplinaId  "
                    + " LEFT JOIN es_grade gra ON cad.codGrade = gra.codGrade  "
                    + " LEFT JOIN funcionario fun ON cad.FuncionarioId = fun.FuncionarioId  "
                    + " LEFT JOIN disciplina dis ON cad.DisciplinaId = dis.DisciplinaId  "
                    + " LEFT JOIN dadocadastroprograma dcp ON prg.codprograma = dcp.DadoCadastroProgramaId  "
                    + " LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId  "
                    //                    = "SELECT det.codGradeDet, det.codGrade, det.labelData, det.labelHora, det.coddis, dis.Disciplina, fun.nome, det.credito "
                    //                    + " FROM es_gradedet det "
                    //                    + " LEFT JOIN es_gradedisprg prg ON det.codgradedet = prg.codgradedet "
                    //                    + " LEFT JOIN es_grade gra ON det.codGrade = gra.codGrade "
                    //                    + " LEFT JOIN funcionario fun ON det.codprof1 = fun.FuncionarioId "
                    //                    + " LEFT JOIN disciplina dis ON det.coddis = dis.DisciplinaId "
                    //                    + " LEFT JOIN dadocadastroprograma dcp ON prg.codprograma = dcp.DadoCadastroProgramaId "
                    //                    + " LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId "

                    + " WHERE prg.codprograma = " + gbRegProAlu.getDadoCadastroProgramaId()
                    + "   AND gra.AnoLetivo = " + tvMatriSemestre.getSelectionModel().getSelectedItem().getAnoLetivo()
                    + "   AND gra.semestre =  " + tvMatriSemestre.getSelectionModel().getSelectedItem().getSemestre()
                    + " ORDER BY cad.OrdemLista";

            try {
                System.out.println(sqlLoadGradeMS);

                ResultSet rs = con.createStatement().executeQuery(sqlLoadGradeMS);
                while (rs.next()) {
                    dadosDS.add(new DisMSgrid(
                            rs.getInt("cad.codGrade"),
                            rs.getString("cad.labelData"),
                            rs.getString("cad.labelHora"),
                            rs.getInt("cad.DisciplinaId"),
                            rs.getString("dis.Disciplina"),
                            rs.getString("fun.nome"),
                            rs.getShort("cad.credito")));

                }

            } catch (SQLException ex) {
                Logger.getLogger(CadProg2Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018f - em inicDisMSem()", "" + ex, 2);
            }
        }

        tcTipoAluno1.setCellValueFactory(new PropertyValueFactory<>("tipoRegOuv"));
        tcData1.setCellValueFactory(new PropertyValueFactory<>("labelData"));
        tcHorario1.setCellValueFactory(new PropertyValueFactory<>("labelHora"));
        tcDisciplina1.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        tcProfessor1.setCellValueFactory(new PropertyValueFactory<>("professor"));
        tcCred1.setCellValueFactory(new PropertyValueFactory<>("credito"));

        tvDisMSem.setItems(dadosDS);
        tvDisMSem.refresh();
    }

    private void desligaMatri() {
//        edmsAno.setEditable(false);
//        rb1Sem1Matri.setDisable(true);
//        rb1Sem2Matri.setDisable(true);
//        edTaxaM.setEditable(false);
//        edDescPerc.setEditable(false);
//        edDescAutorizado.setEditable(false);
//        edTotAPagar.setEditable(false);
//        rbVista.setDisable(true);
//        rbParc.setDisable(true);
//        edNumParc.setEditable(false);
//        ckDin.setDisable(true);
//        ckCar.setDisable(true);
//        ckBol.setDisable(true);
//        ckMon.setDisable(true);
//        ckIgre.setDisable(true);
//        taObsMatricula.setEditable(false);
    }

    private void ligaMatri() {
//        edmsAno.setEditable(true);
//        rb1Sem1Matri.setDisable(false);
//        rb1Sem2Matri.setDisable(false);
//        edTaxaM.setEditable(true);
//        edDescPerc.setEditable(true);
//        edDescAutorizado.setEditable(true);
//        edTotAPagar.setEditable(true);
//        rbVista.setDisable(false);
//        rbParc.setDisable(false);
//        edNumParc.setEditable(true);
//        ckDin.setDisable(false);
//        ckCar.setDisable(false);
//        ckBol.setDisable(false);
//        ckMon.setDisable(true);
//        ckIgre.setDisable(false);
//        taObsMatricula.setEditable(true);
    }

    private void AbreAtualiza_Notas() {

        if (tvRegDis.getSelectionModel().getSelectedItem() != null) {

            disProList = FXCollections.observableArrayList();
            if (reg_prog.getDadoCadastroProgramaId() > 0) {
                String sqlEssePrograma
                        = "SELECT pup.CadastroAlunoDisciplinaId, cg.nome as nomeAluno, cur.curso, cad.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao,  "
                        + " fr.frequencia, cad.Credito, cad.CargaHoraria, cad.AnoLetivo, cad.SemestreId , tn.Turno, tm.turma, cad.Horario, cad.Localidade "
                        + " FROM cadastroalunodisciplina pup "
                        + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId "
                        + " LEFT JOIN dadocadastrogeral cg ON dcp.DadoCadastroGeralId = cg.DadoCadastroGeralId "
                        + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId "
                        + " LEFT JOIN funcionario fu ON fu.FuncionarioId = cad.FuncionarioId "
                        + " LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id "
                        + " LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId "
                        + " LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId "
                        + " LEFT JOIN turno tn ON cad.TurnoId = tn.TurnoId "
                        + " LEFT JOIN turma tm ON cad.TurmaId = tm.TurmaId	 "
                        + " WHERE pup.CadastroAlunoDisciplinaId = " + tvRegDis.getSelectionModel().getSelectedItem().getCadastroAlunoDisciplinaId();
//                        = "SELECT cad.CadastroAlunoDisciplinaId, cg.nome as nomeAluno, c.curso, cd.Disciplina, fu.nome, cad.media, cad.falta, cs.Descricao, "
//                        + "         fr.frequencia, cd.Credito, cd.CargaHoraria, cd.AnoLetivo, cd.SemestreId , tn.Turno, tm.turma, cd.Horario, cd.Localidade\n"
//                        + "            FROM cadastroalunodisciplina cad \n"
//                        + "            LEFT JOIN dadocadastroprograma dcp ON cad.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId \n"
//                        + "            LEFT JOIN dadocadastrogeral cg ON dcp.DadoCadastroGeralId = cg.DadoCadastroGeralId\n"
//                        + "            LEFT JOIN cadastrodisciplina cd ON cad.CadastroDisciplinaId = cd.CadastroDisciplinaId \n"
//                        + "            LEFT JOIN funcionario fu ON fu.FuncionarioId = cd.FuncionarioId  \n"
//                        + "            LEFT JOIN cadaludissit cs ON cad.CadastroAlunoDisciplinaSituacaoId = cs.cads_id \n"
//                        + "            LEFT JOIN curso c ON dcp.CursoId = c.CursoId \n"
//                        + "            LEFT JOIN frequencia fr ON cad.FrequenciaId = fr.FrequenciaId \n"
//                        + "            LEFT JOIN turno tn ON cd.TurnoId = tn.TurnoId\n"
//                        + "            LEFT JOIN turma tm ON cd.TurmaId = tm.TurmaId	"
//                        + "            WHERE cad.CadastroAlunoDisciplinaId = " + tvRegDis.getSelectionModel().getSelectedItem().getCadastroAlunoDisciplinaId();
                try {
                    System.out.println(sqlEssePrograma);

                    ResultSet rs = con.createStatement().executeQuery(sqlEssePrograma);
                    while (rs.next()) {
                        gbAtuNotas = new Atu_Notas();
                        gbAtuNotas.setCadastroAlunoDisciplinaId(rs.getInt("CadastroAlunoDisciplinaId"));
                        gbAtuNotas.setNomeAluno(rs.getString("nomeAluno"));
                        gbAtuNotas.setPrograma(rs.getString("curso"));
                        gbAtuNotas.setDisciplina(rs.getString("Disciplina"));
                        gbAtuNotas.setProfessor(rs.getString("nome"));
                        gbAtuNotas.setMedia(rs.getFloat("media"));
                        gbAtuNotas.setFaltas(rs.getShort("falta"));
                        gbAtuNotas.setSituacao(rs.getString("Descricao"));
                        gbAtuNotas.setFrequencia(rs.getString("frequencia"));
                        gbAtuNotas.setCredito(rs.getShort("Credito"));
                        gbAtuNotas.setCargah(rs.getShort("CargaHoraria"));
                        gbAtuNotas.setAnoLetivo(rs.getShort("AnoLetivo"));
                        gbAtuNotas.setSemestre(rs.getShort("SemestreId"));
                        gbAtuNotas.setTurno(rs.getString("Turno"));
                        gbAtuNotas.setTurma(rs.getString("turma"));
                        gbAtuNotas.setHorario(rs.getString("Horario"));
                        gbAtuNotas.setLocalidade(rs.getString("Localidade"));

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(CadProg2Controller.class
                            .getName()).log(Level.SEVERE, null, ex);
                    mostraMsg("Erro 018 - em initColsPro()", "" + ex, 2);
                }
            }

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Atualiza_Notas.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("Ministérios");
                stage.setScene(scene);

                stage.showAndWait();

            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 004d ao carregar AtuNotas", "" + ex, 0);
            }
        }
    }

    public void AbreJanSituDis() {
        if (tvRegDis.getSelectionModel().getSelectedItem() != null) {
            reg_disci = tvRegDis.getSelectionModel().getSelectedItem();
            jpaCon = new CadastroalunodisciplinaJpaController();

            //PASSA REGISTRO SELECIONADO PARA A VARIÁVEL GLOBAL rgRegDisc, que será usado na tela TabelaDadocadastrogerals
            gbJanSituDis = reg_disci;
            gbDisProAlu = jpaCon.findCadastroalunodisciplina(reg_disci.getCadastroAlunoDisciplinaId());
            stgJanSituDis = chamaTelaStage("/view/JanSituDis.fxml", "Atualizar Situação do Aluno nesta Disciplina");

        }
    }

    public Stage chamaTelaStage(String nomeTela, String titulo) {
        Stage stg = null;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(nomeTela));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle(titulo);
            stage.setScene(new Scene(parent));
            stg = stage;
            stage.show();

            // LibraryAssistantUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            MyFunc.mostraMsg("Erro 003 ao carregar item de menu", "" + ex, 2);

        }
        return stg;
    }

    public List<Integer> getAnoDistinct() {
        ObservableList<Integer> anoDistinc;
        anoDistinc = FXCollections.observableArrayList();

        if (reg_prog.getDadoCadastroProgramaId() > 0) {
            String sqlAnoDistinct = SQLBASE + "   WHERE (pup.DadoCadastroProgramaId = " + reg_prog.getDadoCadastroProgramaId() + " ) group BY cad.AnoLetivo order by cad.AnoLetivo";
            try {
                System.out.println(sqlAnoDistinct);

                ResultSet rs = con.createStatement().executeQuery(sqlAnoDistinct);
                while (rs.next()) {
                    anoDistinc.add(rs.getInt("AnoLetivo"));

                }

            } catch (SQLException ex) {
                Logger.getLogger(CadProg2Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 048 - em getAnoDistinct()", "" + ex, 2);
                return null;
            }
        }
        return anoDistinc;
    }

    public void initColsDisPro(String filtro) {
        if (!filtro.equals("")) {
            filtro = " AND (" + filtro + ")";
        }

        disProList = FXCollections.observableArrayList();
        if (reg_prog.getDadoCadastroProgramaId() > 0) {
            sqlDisProAlu = SQLBASE + "   WHERE (pup.DadoCadastroProgramaId = " + reg_prog.getDadoCadastroProgramaId() + " ) " + filtro;
            try {
                System.out.println(sqlDisProAlu);

                ResultSet rs = con.createStatement().executeQuery(sqlDisProAlu);
                totCre = 0;
                totCreAprov = 0;
                while (rs.next()) {
                    disProList.add(new DisciProgAluno(
                            rs.getInt("CadastroAlunoDisciplinaId"),
                            rs.getString("curso"),
                            rs.getString("Disciplina"),
                            rs.getString("nome"),
                            rs.getFloat("media"),
                            rs.getShort("falta"),
                            rs.getString("Descricao"),
                            rs.getString("frequencia"),
                            rs.getShort("Credito"),
                            rs.getShort("CargaHoraria"),
                            rs.getShort("AnoLetivo"),
                            rs.getShort("SemestreId")));
                    totCre = totCre + rs.getShort("Credito");
                    if ((rs.getString("Descricao") != null) && (rs.getString("Descricao").equals("Aprovado"))) {
                        totCreAprov = totCreAprov + rs.getShort("Credito");

                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(CadProg2Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018 - em initColsPro()", "" + ex, 2);
            }
        }

        tcCAD.setCellValueFactory(new PropertyValueFactory<>("cadastroAlunoDisciplinaId"));
        tcDis.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        tcProf.setCellValueFactory(new PropertyValueFactory<>("professor"));
        tcMed.setCellValueFactory(new PropertyValueFactory<>("media"));
        tcFal.setCellValueFactory(new PropertyValueFactory<>("faltas"));
        tcSit.setCellValueFactory(new PropertyValueFactory<>("situaacao"));
        tcFreq.setCellValueFactory(new PropertyValueFactory<>("frequencia"));
        tcCre.setCellValueFactory(new PropertyValueFactory<>("credito"));
        tcCar.setCellValueFactory(new PropertyValueFactory<>("cargah"));
        tcAno.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tcSem.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        //        tvRegDis.setEditable(true);
        tvRegDis.setItems(disProList);
        tvRegDis.refresh();
        String espacos = "     |     ";
        lblRegistroDisProAlu.setText("Disciplinas listadas: " + tvRegDis.itemsProperty().get().size() + espacos
                + "Créditos listados: " + totCre + espacos + "Créditos válidos: " + totCreAprov);
    }

    public void preencheBaseIns() {

        tfCodCadProg.setText(String.valueOf(gbInsPro.getCodAluno()));
        tfNomeAluno.setText(gbInsPro.getNomeAluno());
        edAnoLetivo.setText((String.valueOf(gbInsPro.getAnoLetivo())));
        cbLocalidade.getSelectionModel().select("STSC - São Paulo");

        leFoto(Integer.valueOf(gbInsPro.getCodAluno()));
        if ((gbInsPro.getSemestre() == 1)) {
            rb1SemPro.setSelected(true);
        } else {
            rb2SemPro.setSelected(true);
        }

        // VALORES DEFAULT 
        dpDataCad.setValue(LocalDate.now());
        cbSitPro.getSelectionModel().select("Cadastrando");
    }

    public void preencheEdsComObj() {

        if (reg_prog == null) {
            limpaCampos();
        } else {
            if ((reg_prog.getSemestreId() == 1)) {
                rb1SemPro.setSelected(true); //pergunta //isSelected
            } else {
                if (reg_prog.getSemestreId() == 2) {
                    rb2SemPro.setSelected(true);
                }
            }

            tfCodCadProg.setText((reg_prog.getDadoCadastroProgramaId() != null) ? String.valueOf(reg_prog.getDadoCadastroProgramaId()) : null);
            tfNomeAluno.setText(gbRegCadGer.getNome());
            cbPrograma.getSelectionModel().select((reg_prog.getNomeCurso() != null) ? reg_prog.getNomeCurso().getCurso() : null);
            tfTitulacao.setText((reg_prog.getNomeCurso() != null) ? reg_prog.getNomeCurso().getTitulacao() : null);

            cbLocalidade.getSelectionModel().select((reg_prog.getLocalidade() != null) ? reg_prog.getLocalidade() : null);
            //     cbEspec.getSelectionModel().select((reg_prog.getEspecificidade() != null) ? reg_prog.getEspecificidade() : null);

            edAnoLetivo.setText((reg_prog.getAnoLetivo() != null) ? String.valueOf(reg_prog.getAnoLetivo()) : null);
            //    edDuracao.setText((reg_prog.getd() != null) ? String.valueOf(reg_prog.getModalidadeId()) : null);
            dpDataCad.setValue((reg_prog.getDataCadastro() != null) ? reg_prog.getDataCadastro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            cbTurno.getSelectionModel().select((reg_prog.getNomeTurno() != null) ? reg_prog.getNomeTurno().getTurno() : null);

            dpCursando.setValue((reg_prog.getCursando() != null) ? reg_prog.getCursando().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            dpReativado.setValue((reg_prog.getReativado() != null) ? reg_prog.getReativado().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            dpTrancamento.setValue((reg_prog.getMatriculaTrancada() != null) ? reg_prog.getMatriculaTrancada().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            dpDesist.setValue((reg_prog.getDesistencia() != null) ? reg_prog.getDesistencia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            dpAband.setValue((reg_prog.getAbandono() != null) ? reg_prog.getAbandono().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            dpColacao.setValue((reg_prog.getColacaoGrau() != null) ? reg_prog.getColacaoGrau().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            cbSitPro.getSelectionModel().select((reg_prog.getNomeSituacao() != null) ? reg_prog.getNomeSituacao().getDadoCadastroProgramaSituacao() : null);
            taObs.setText((reg_prog.getAtualSituacaoObservacoes() != null) ? reg_prog.getAtualSituacaoObservacoes() : null);

//            if (reg_prog.getFoto3X4() == null || reg_prog.getFoto3X4() == false) {
//                chkFoto.setSelected(false);
//            } else {
//                chkFoto.setSelected(false);
//            }
            chkFoto.setSelected((reg_prog.getFoto3X4() != null) ? reg_prog.getFoto3X4() : false);
            chkHist2.setSelected((reg_prog.getHistorico2grau() != null) ? reg_prog.getHistorico2grau() : false);
            chkHist3.setSelected((reg_prog.getHistorico3grau() != null) ? reg_prog.getHistorico3grau() : false);
            chkFotRG.setSelected((reg_prog.getFotocopiaRG() != null) ? reg_prog.getFotocopiaRG() : false);
            chkFotCpf.setSelected((reg_prog.getFotocopiaCPFoCIC() != null) ? reg_prog.getFotocopiaCPFoCIC() : false);
            chkReq.setSelected((reg_prog.getRequerimentoMatricula() != null) ? reg_prog.getRequerimentoMatricula() : false);
            chkQuest.setSelected((reg_prog.getQuestionario() != null) ? reg_prog.getQuestionario() : false);
            chkCarta.setSelected((reg_prog.getCartaRecomendacao() != null) ? reg_prog.getCartaRecomendacao() : false);
            chkBioReli.setSelected((reg_prog.getBiografiaReligiosa() != null) ? reg_prog.getBiografiaReligiosa() : false);

            edMediaFinal.setEditable(false);
            //  dpNasc2.setDisable(true);
            taBiografia.setText((reg_prog.getBiografia() != null) ? reg_prog.getBiografia() : null);
            leFoto(Integer.valueOf(reg_prog.getDadoCadastroGeralId()));
        }
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

//    public ObservableList<String> getEspec() {
//        jpaES = new EspecificidadeJpaController();
//        dadosES = FXCollections.observableArrayList(jpaES.getNomeEsp());
//
//        if (dadosES == null) {
//            return FXCollections.observableArrayList();
//        } else {
//            return dadosES;
//        }
//    }
    public ObservableList<String> getSituacaoPro() {
        jpaSIP = new DadocadastroprogramasituacaoJpaController();
        dadosSIP = FXCollections.observableArrayList(jpaSIP.getNomeDasSituacoes());

        if (dadosSIP == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosSIP;
        }
    }

    public ObservableList<String> getFrequencia() {
        jpaFR = new FrequenciaJpaController();
        dadosFR = FXCollections.observableArrayList(jpaFR.getNomeFrequencia());

        if (dadosFR == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosFR;
        }
    }

    public ObservableList<String> getTurno() {
        jpaTU = new TurnoJpaController();
        dadosTU = FXCollections.observableArrayList(jpaTU.getNomeTurno());

        if (dadosTU == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosTU;
        }
    }

    public void desligaEdicao() {

        // combos
        cbPrograma.setStyle("-fx-opacity: 1;");
        cbLocalidade.setStyle("-fx-opacity: 1;");
        //    cbEspec.setStyle("-fx-opacity: 1;");
        cbSitPro.setStyle("-fx-opacity: 1;");
        cbTurno.setStyle("-fx-opacity: 1;");
        cbPrograma.setDisable(true);
        cbLocalidade.setDisable(true);
        //     cbEspec.setDisable(true);
        cbSitPro.setDisable(true);
        cbTurno.setDisable(true);
        rb1SemPro.setDisable(true);
        rb2SemPro.setDisable(true);

        // habilita botões para inserção e deleção
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
        chkBioReli.setDisable(true);

        edMediaFinal.setEditable(false);
//        dpNasc2.setDisable(true);
    }

    public void LigaEdicao() {

        // combos
        cbPrograma.setDisable(false);
        cbLocalidade.setDisable(false);
        //    cbEspec.setDisable(false);
        cbTurno.setDisable(false);
        cbSitPro.setDisable(false);
        rb1SemPro.setDisable(false);
        rb2SemPro.setDisable(false);

        // habilita botões para inserção e deleção
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
        chkBioReli.setDisable(false);

        edMediaFinal.setEditable(true);
//        dpNasc2.setDisable(false);
    }

    public void limpaCampos() {
        tfCodCadProg.setText("");
        tfNomeAluno.setText("");
        tfTitulacao.setText("");
        tfPrograma.setText("");
        cbPrograma.setValue(null);
        cbTurno.setValue(null);
        cbLocalidade.setValue(null);
        //       cbEspec.setValue(null);
        cbSitPro.setValue(null);
        rb1SemPro.setSelected(false); //pergunta //isSelected
        rb2SemPro.setSelected(false);

        dpDataCad.setValue(null);
        dpCursando.setValue(null);
        dpReativado.setValue(null);
        dpTrancamento.setValue(null);
        dpDesist.setValue(null);
        dpAband.setValue(null);
        dpColacao.setValue(null);

        chkFoto.setSelected(false);
        chkFoto.setSelected(false);
        chkHist2.setSelected(false);
        chkHist3.setSelected(false);
        chkFotRG.setSelected(false);
        chkFotCpf.setSelected(false);
        chkReq.setSelected(false);
        chkQuest.setSelected(false);
        chkCarta.setSelected(false);
        chkBioReli.setSelected(false);

        edMediaFinal.setText("");
//        dpNasc2.setValue(null);
    }

    @FXML
    private void clicouEdita(ActionEvent event) {
        if (cbPrograma.getSelectionModel().selectedItemProperty() != null) {
            LigaEdicao();
            editando = true;
        }
    }

//    public void geraProProvis() {
//        // INSERE REGISTRO PROVISÓRIO PARA PEGAR CÓDIGO PARA FILHO (DISCIPLINAS)
//        Curso cursox = new Curso();
//        cursox.setCursoId(0);
//        Dadocadastroprogramasituacao sit = new Dadocadastroprogramasituacao();
//        Integer i = 0;
//        sit.setDadoCadastroProgramaSituacaoId(i);
//        reg_prog = new Dadocadastroprograma();
//        reg_prog.setDadoCadastroGeralId(gbRegCadGer.getDadoCadastroGeralId());
////        reg_prog.setLocalidade("");
////        reg_prog.setAnoLetivo(i);
////        reg_prog.setCursoNome(curso);
////        reg_prog.setSituacaoNome(sit);
////        reg_prog.setDataCadastro(new Date());
//
//        try {
//            jpaDCP.create(reg_prog);
//
//        } catch (Exception ex) {
//            Logger.getLogger(CadProg2Controller.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            MyFunc.mostraMsg("Erro 025 ao gravar reg provisório programa", "" + ex, 0);
//        }
//
//        insercaoPendente = reg_prog.getDadoCadastroGeralId(); // CÓDIGO DO REGISTRO PROVISÓRIO
//    }
    private void clicouInsere(ActionEvent event) {

        //   geraProProvis();
        limpaCampos(); // para quem vai requestfocus;
        LigaEdicao();
        inserindo = true;
        cbPrograma.requestFocus();
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
        ChecaErros check = new ChecaErros();
        taConsist.setText("");

        if (cbPrograma.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Programa> não pode ficar vazio.\n");
        }
        if (cbLocalidade.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Localidade> não pode ficar vazio.\n");
        }
        if (cbSitPro.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Situação do Aluno no Programa> não pode ficar vazio.\n");
        }

//        checaCombo("Programa", 1, cbPrograma, check);
//        checaCombo("Localidade", 1, cbLocalidade, check);
        //  checaCombo("Especialização", 2, cbEspec, check);
        checaTTF("Ano Letivo", 1, edAnoLetivo, check);

        if (dpDataCad.getValue() != null) {
            Date today = new Date();
            dpDataCad.setValue(today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }

//        checaCombo("Situação do Aluno no Programa", 1, cbSitPro, check);
        if (check.getCte() == 0) {
            // NÃO HAVENDO ERROS, PODE TENTAR GRAVAR, MESMO COM AVISOS

            if (JaEstaNestePrograma(cbPrograma.getSelectionModel().getSelectedItem()) == false) {
                mostraMsg("Não é possível gravar!", "O aluno já está cadastrado nesse programa e ainda não se formou no mesmo.", 2);
            } else {
                // PODE GRAVAR!
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Confirma");
                alert.setContentText("Confirma a gravação dos dados?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {

                    if (editando == true) {
                        try {

                            fillObj(reg_prog, true);
                            reg_prog.setDataAlt(new Date());
                            reg_prog.setCodUserAlt(gbUser.getCoduser());
                            jpaDCP = new DadocadastroprogramaJpaController();
                            jpaDCP.edit(reg_prog);
                            gbRegProAlu = reg_prog;

//                            reg_disci.setAtualizacao(new Date()); // ESTÁ EDITANDO
//                            gbRegCadGer = reg_disci;
//                            jpaCon.edit(reg_disci);
                            editando = false;

                            desligaEdicao();
                            //     insercaoPendente = null;
                            mostraMsg("Dados gravados com sucesso.", "", 3);

                        } catch (Exception e) {
                            mostraMsg("Erro 026m ao gravar a edição dos dados.", "> " + e, 2);
                        }
                    } else {
                        if (inserindo == true) {
                            try {

                                // GRAVA PAI - DADOCADASTROPROGRAMA
                                Dadocadastroprograma obj = new Dadocadastroprograma();

                                fillObj(obj, false);
                                obj.setDadoCadastroGeralId(gbInsPro.getCodAluno());
                                obj.setDataInc(new Date());
                                obj.setCodUserInc(gbUser.getCoduser());

                                jpaDCP = new DadocadastroprogramaJpaController();
                                jpaDCP.create(obj);
                                reg_prog = obj;
                                gbRegProAlu = reg_prog;

                                //    tfCodCadGer.setText(String.valueOf(reg_disci.getDadoCadastroGeralId()));
                                inserindo = false;

                                desligaEdicao();
                                //                      insercaoPendente = null;

                                mostraMsg("Dados gravados com sucesso.", "", 1);
                            } catch (Exception e) {
                                System.out.println(reg_disci);
                                System.out.println("\n============================");
                                System.out.println(e);
                                System.out.println("============================\n");
                                mostraMsg("Erro 014s ao gravar novos dados.", "> " + e, 2);
                            }
                        } else {
                            mostraMsg("Mensagem DN2", "Mensagem DN-YANot - 012", 2);
                        }
                    }

                }

            }
        }
    }

    private void fillObj(Dadocadastroprograma x, Boolean preenchePK) {
//        if (preenchePK) {
//            x.setDadoCadastroProgramaId(Short.parseShort(tfCodCadProg.getText()));
//        }

        if (rb1SemPro.isSelected()) {
            Integer sx = 1;
            x.setSemestreId(sx);
        } else {
            Integer sx = 2;
            x.setSemestreId(sx);
        }

        if (cbPrograma.getSelectionModel().getSelectedItem() != null) {
            jpaCr = new CursoJpaController();
            x.setNomeCurso(jpaCr.getObjeto(cbPrograma.getSelectionModel().getSelectedItem()));
        }

        if (cbLocalidade.getSelectionModel().getSelectedItem() != null) {
            jpaLO = new LocalidadeJpaController();
            x.setLocalidade(jpaLO.getObjeto(cbLocalidade.getSelectionModel().getSelectedItem()).getLocalidade());
        }

//        if (cbEspec.getSelectionModel().getSelectedItem() != null) {
        //      jpaES = new EspecificidadeJpaController();
        //        x.setEspecificidade(jpaES.getObjeto(cbEspec.getSelectionModel().getSelectedItem()).getEspecificidade());
        //    }
        if (cbSitPro.getSelectionModel().getSelectedItem() != null) {
            jpaSIP = new DadocadastroprogramasituacaoJpaController();
            x.setNomeSituacao(jpaSIP.getObjeto(cbSitPro.getSelectionModel().getSelectedItem()));
        }

        if (cbTurno.getSelectionModel().getSelectedItem() != null) {
            jpaTU = new TurnoJpaController();
            x.setNomeTurno(jpaTU.getObjeto(cbTurno.getSelectionModel().getSelectedItem()));
        }

        x.setAnoLetivo(convInt(edAnoLetivo.getText()));
        // DURAÇÃO???

        if (dpDataCad.getValue() != null) {
            x.setDataCadastro(Date.from(dpDataCad.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (dpCursando.getValue() != null) {
            x.setCursando(Date.from(dpCursando.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (dpReativado.getValue() != null) {
            x.setReativado(Date.from(dpReativado.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (dpTrancamento.getValue() != null) {
            x.setMatriculaTrancada(Date.from(dpTrancamento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (dpDesist.getValue() != null) {
            x.setDesistencia(Date.from(dpDesist.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (dpAband.getValue() != null) {
            x.setAbandono(Date.from(dpAband.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (dpColacao.getValue() != null) {
            x.setColacaoGrau(Date.from(dpColacao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        x.setAtualSituacaoObservacoes(taObs.getText());

//        if (chkFoto.isSelected()) {
//            x.setFoto3X4(true);
//        }
//        if (chkFoto.isSelected()) {
//            x.setFoto3X4(true);
//        } else {
//            x.setFoto3X4(false);
//        }
        x.setFoto3X4(chkFoto.isSelected());
        x.setHistorico2grau(chkHist2.isSelected());
        x.setHistorico3grau(chkHist3.isSelected());
        x.setFotocopiaRG(chkFotRG.isSelected());
        x.setFotocopiaCPFoCIC(chkFotCpf.isSelected());
        x.setRequerimentoMatricula(chkReq.isSelected());
        x.setQuestionario(chkQuest.isSelected());
        x.setCartaRecomendacao(chkCarta.isSelected());
        x.setBiografiaReligiosa(chkBioReli.isSelected());
        
//        if (chkHist2.isSelected()) {
//            x.setHistorico2grau(true);
//        }
//
//        if (chkHist3.isSelected()) {
//            x.setHistorico3grau(true);
//        }
//
//        if (chkFotRG.isSelected()) {
//            x.setFotocopiaRG(true);
//        }
//
//        if (chkFotCpf.isSelected()) {
//            x.setFotocopiaCPFoCIC(true);
//        }
//
//        if (chkReq.isSelected()) {
//            x.setRequerimentoMatricula(true);
//        }
//
//        if (chkQuest.isSelected()) {
//            x.setQuestionario(true);
//        }
//
//        if (chkCarta.isSelected()) {
//            x.setCartaRecomendacao(true);
//        }
//
//        if (chkBioReli.isSelected()) {
//            x.setBiografiaReligiosa(true);
//        }

        if (dpDataVest.getValue() != null) {
            x.setDataVestibularInicio(Date.from(dpDataVest.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        x.setBiografia(taBiografia.getText());

        //  x.setn(edNome.getText());
    }

    private Boolean JaEstaNestePrograma(String pesq) {
        Boolean pode = true;

        jpaDCP = new DadocadastroprogramaJpaController();
        List lis = jpaDCP.getProgramasAluno(pesq, gbRegCadGer.getDadoCadastroGeralId());

        // VERIFICA SE O ALUNO JÁ ESTÁ CADASTRADO NO PROGRAMA E ESTÁ ATIVO
        Dadocadastroprograma prog;
        if (inserindo) {
            for (int i = 0; i < lis.size(); i++) {
                prog = (Dadocadastroprograma) lis.get(i);
                Integer a = prog.getDadoCadastroGeralId();
                Integer b = reg_prog.getDadoCadastroGeralId();
                if ((a == b) && !prog.getNomeSituacao().getDadoCadastroProgramaSituacao().equals("Formado")) {
                    pode = false;
                    break;
                }
            }
        } else { // EDITANDO
            for (int i = 0; i < lis.size(); i++) {
                prog = (Dadocadastroprograma) lis.get(i);
                Integer a = prog.getDadoCadastroGeralId();
                Integer b = reg_prog.getDadoCadastroGeralId();
                Integer c = prog.getDadoCadastroProgramaId();
                Integer d = reg_prog.getDadoCadastroProgramaId();

                if ((a == b) && (c != d) && (!prog.getNomeSituacao().getDadoCadastroProgramaSituacao().equals("Formado"))) {
                    pode = false;
                    break;
                }
            }
        }

        return pode;
    }

    @FXML
    private void clicouCancela(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Cancelar?");
        alert.setContentText("Se você cancelar a edição, os dados não serão gravados.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            if (editando == true) {
                preencheEdsComObj();  // restaura nos campos os valores antes da edição.
                editando = false;
            } else {
                if (inserindo == true) {

                    // APAGA REGISTRO RESERVADO
//                    if (insercaoPendente != null) {
//                        insercaoPendente = null;
////                        tvMinitry.setItems(null);
//                    }
                    limpaCampos();    // limpa campos
                    inserindo = false;
                } else {
                    mostraMsg("Mensagem DNY", "Mensagem DN-YANSTBH - 011", 2);
                }
            }
            desligaEdicao();
        }
    }

    @FXML
    private void clicouApaga(ActionEvent event) {

    }

    private String calcFiltro() {
        String filAno = "";
        String filSem = "";
        String filtro = "";

        if (cbAnoFiltro.getSelectionModel().getSelectedItem() != null) {
            filAno = "cd.AnoLetivo = '" + cbAnoFiltro.getSelectionModel().getSelectedItem() + "' ";
        }

        if (rb1Sem.isSelected() == true) {
            filSem = "cd.SemestreId = '1'";
        } else {
            if (rb2Sem.isSelected() == true) {
                filSem = "cd.SemestreId = '2'";
            }
        }

        if ((filAno != "") && (filSem != "")) {
            filtro = "( " + filAno + ") AND ( " + filSem + ") ";
        } else {
            if (filAno != "") {
                filtro = filAno;
            } else {
                if (filSem != "") {
                    filtro = filSem;
                }
            }
        }

        return filtro;
    }

    @FXML
    private void clicouFiltra(ActionEvent event) {
        String filtro = calcFiltro();

        if (filtro.equals("")) {
            mostraMsg("Informação", "Selecione o ANO e/ou o SEMESTRE a filtrar", 3);
        } else {
            initColsDisPro(filtro);
        }
    }

    @FXML
    private void clicouLimpa(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmação");
        alert.setContentText("Deseja limpar o filtro?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            initColsDisPro("");
        }
    }

    private void leFoto(Integer codAluno) {
        // CARREGA FOTO PARA IMAGEVIEW        
        String nomeArq = "photo" + codAluno + ".jpg";

        File file = new File("C:\\EvedSys\\temp");
        boolean dirCreated = file.mkdir();

        jpaFotos = new EsFotosalunosJpaController();
        reg_foto = obtemFoto(String.valueOf(codAluno)); //jpaFotos.getObjeto(String.valueOf(codAluno));
        if (reg_foto.getCodFotoAluno() != null) {

            if (reg_foto.getFotoAluno() != null) {
                try {
                    InputStream is = new ByteArrayInputStream(reg_foto.getFotoAluno());
                    OutputStream os = new FileOutputStream(new File(nomeArq));

                    byte[] content = new byte[1024];
                    int size = 0;
                    while ((size = is.read(content)) != -1) {
                        os.write(content, 0, size);
                    }

                    os.close();
                    is.close();
                    Image image = new Image("file:" + nomeArq, 100, 150, true, true);
                    ivFotoAluno.setImage(image);
                    ivFotoAluno.setPreserveRatio(true);
                } catch (Exception e) {
                    mostraMsg("Erro ao mostrar Foto", "" + e, 2);
                }
            }

            // apaga arquivo temporário da foto
            try {

                File f = new File(nomeArq);           //file to be delete  
                if (f.delete()) //returns Boolean value  
                {
                    //   System.out.println("foto temporária apagada com sucesso.");   //getting and printing the file name  
                } else {
                    System.out.println("erro ao tentar apagar foto temporária.");   //getting and printing the file name  
                }
            } catch (Exception e) {
                System.out.println("erro ao tentar apagar foto temporária " + e);
            }
        }

    }

    public EsFotosalunos obtemFoto(String codAluno) {
        dadosFoto = FXCollections.observableArrayList(jpaFotos.getFotoAluno(codAluno));

        if (dadosFoto == null) {
            return null;
        } else {
            EsFotosalunos reg_achado = new EsFotosalunos();

            for (int i = 0; i < dadosFoto.size(); i++) {
                reg_achado = dadosFoto.get(i);
            }

            return reg_achado;
        }
    }

    private void clicouInserePro(ActionEvent event) {
        chamaTela("/view/ScrollPa.fxml", "Teste Scroll");
    }

    public void chamaTela(String nomeTela, String titulo) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(nomeTela));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle(titulo + " - " + ESVERSION);
            stage.setScene(new Scene(parent));
            stage.show();

            // LibraryAssistantUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            MyFunc.mostraMsg("Erro 0014d ao carregar ScrollPa", "" + ex, 2);

        }
    }

    @FXML
    private void clicouSemestre(ActionEvent event) {
        //spMatriSem.setVvalue(0.0);
    }

    @FXML
    private void clicouDisciplinas(ActionEvent event) {
        //   spMatriSem.setVvalue(0.25);
    }

    @FXML
    private void ClicouPagamento(ActionEvent event) {
        //    spMatriSem.setVvalue(1);
    }

    @FXML
    public void clicouTipoAluno(TableColumn.CellEditEvent edittedCell) {
//        MatriDis selec = tvMatriDis.getSelectionModel().getSelectedItem();
//        selec.setTipoAluno(edittedCell.getNewValue().toString());
    }

}
