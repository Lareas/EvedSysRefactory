package controller;

import static main.Login.ESVERSION;
import static main.Login.gbAtuNotas;
import static main.Login.gbClicouInsPro;
import static main.Login.gbClicouEdiPro;
import static main.Login.gbCepAluno;
import static main.Login.gbCrudMin;
import static main.Login.gbNomeCrudMin;
import static main.Login.gbRegCadGer;
import static main.Login.gbClicouInsere;
import static main.Login.gbDeOnde;
import static main.Login.gbRegProAlu;
import static main.Login.gbUser;
import static main.Login.gbFotoMaior;
import static main.Login.secretCase;
import static main.Login.gbInsPro;
import static main.Login.gbRegMatriSemCabec;
import static main.Login.gbUltSem;
import static main.Login.gbEspecial;
import static main.Login.GBFORMATDATA;
import static main.Login.gbTipoInsEdiMS;
import static main.Login.gbDispensa;
import static main.Login.gbEquivalencia;
import static main.Login.GBFORMATDATAHORA;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import static controller.DadocadastrogeralSimplesController.stgDadosCad;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Atu_Notas;
import entities.CepLogradouros;
import entities.ChecaErros;
import entities.Dadocadastrogeral;
import entities.Dadocadastrogeralministeriais;
import entities.DisciProgAluno;
import entities.DispensaGB;
import entities.Docsalunos;
import entities.EquivalenciaGB;
import entities.EsEquivalencia;
import entities.EsFotosalunos;
import entities.EsGrade;
import entities.EsMatrisem;
import entities.FotoMaior;
import entities.GridDocs;
import entities.Impalunos;
import entities.InserePro;
import entities.MatriSemCabec;
import entities.MatriSemestre;
import entities.Presenca;
import entities.ProgramasAluno;
import entities.Tabuser;
import funcoes.ComboBoxAutoComplete;
import funcoes.DBConnector;
import funcoes.MyFunc;
import static funcoes.MyFunc.mostraMsg;
import static funcoes.MyFunc.isCPF;
//import static funcoes.MyFunc.checaCombo;
import static funcoes.MyFunc.checaTTF;
//import static funcoes.MyFunc.convShort;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.DadocadastrogeralJpaController;
import jpa_controler.DadocadastrogeralministeriaisJpaController;
import jpa_controler.DenominacaoJpaController;
import jpa_controler.DocsoficiaisJpaController;
import jpa_controler.EstadocivilJpaController;
import jpa_controler.FormacaoJpaController;
import jpa_controler.NacionalidadeJpaController;
import jpa_controler.ProfissoesJpaController;
import jpa_controler.SiglaestadoJpaController;
import static funcoes.MyFunc.*;
import funcoes.TextFieldFormatter;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.CepLogradourosJpaController;
import jpa_controler.CordapeleJpaController;
import jpa_controler.DadocadastroprogramaJpaController;
import jpa_controler.DocinscJpaController;
import jpa_controler.DocsalunosJpaController;
import jpa_controler.EsEquivalenciaJpaController;
import jpa_controler.EsFotosalunosJpaController;
import jpa_controler.EsGradeJpaController;
import jpa_controler.EsMatrisemJpaController;
import jpa_controler.ImpalunosJpaController;
import jpa_controler.TabuserJpaController;
import jpa_controler.exceptions.NonexistentEntityException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class DadosCadastraisController implements Initializable {

    @FXML
    private TextField edCepAluno;
    @FXML
    private JFXButton buImpCepAlu;
    @FXML
    private TextField edEnderecoAluno;
    @FXML
    private TextField edBairroAluno;
    @FXML
    private TextField edCidadeAluno;
    @FXML
    private ComboBox<String> cbUFAluno;
    @FXML
    private TextField edFoneRes;
    @FXML
    private TextField edFoneCel;
    @FXML
    private TextField edFoneCom;
    @FXML
    private TextField edEmailAluno;
    @FXML
    private ComboBox<String> cbCorPele;
    @FXML
    private ComboBox<String> cbEstadoCivil;
    @FXML
    private TextField edConjuge;
    @FXML
    private TextField edDependentes;
    @FXML
    private TextField edNaturalidade;
    @FXML
    private JFXDatePicker dpNasc;
    @FXML
    private ComboBox<String> cbUFNatu;
    @FXML
    private ComboBox<String> cbNacionalidade;
    @FXML
    private JFXButton buRefUFAluno;
    @FXML
    private JFXButton buRefEst;
    @FXML
    private JFXButton buRefUFNat;
    @FXML
    private JFXButton buRefNac;
    @FXML
    private JFXButton buSP1;
    @FXML
    private TextField edRG;
    @FXML
    private TextField edCPF;
    @FXML
    private ComboBox<String> cbDocsOficiais;
    @FXML
    private TextField edNumDocOficial;
    @FXML
    private ComboBox<String> cbFormacao;
    @FXML
    private ComboBox<String> cbProfissao;
    @FXML
    private TextField edCompProfissao;
    @FXML
    private ComboBox<String> cbProfissao2;
    @FXML
    private TextField edCompProfissao2;
    @FXML
    private JFXButton buRefDoc;
    @FXML
    private JFXButton buRefFor;
    @FXML
    private JFXButton buRefPro;
    @FXML
    private JFXButton buRefPro2;
    @FXML
    private TextField edEscolaridade;
    @FXML
    private TextField edMedioInst;
    @FXML
    private TextField edMedioCid;
    @FXML
    private ComboBox<String> cbMedioUF;
    @FXML
    private TextField edCursoEscola;
    @FXML
    private TextField edMedioAno;
    @FXML
    private TextField edSuperiorInst;
    @FXML
    private TextField edSuperiorCid;
    @FXML
    private ComboBox<String> cbSuperiorUF;
    @FXML
    private TextField edCursoPos;
    @FXML
    private TextField edSuperiorAno;
    @FXML
    private JFXButton buRefUFMed;
    @FXML
    private JFXButton buRefUFSup;
    @FXML
    private JFXButton buSP2;
    @FXML
    private JFXButton buSP3;
    @FXML
    private ComboBox<String> cbDenominacao;
    @FXML
    private TextField edIgreja;
    @FXML
    private TextField edFoneIgreja;
    @FXML
    private TextField edEmailIgreja;
    @FXML
    private TextField edPastorIgreja;
    @FXML
    private JFXButton buMinIns;
    @FXML
    private JFXButton buMinEdi;
    @FXML
    private JFXButton buMinApa;
    @FXML
    private TextField lblRegistrosMin;
    @FXML
    private TableView<Dadocadastrogeralministeriais> tvMinitry;
    @FXML
    private TableColumn<Dadocadastrogeralministeriais, String> tcMinistries;
    @FXML
    private JFXButton buRefDen;
    @FXML
    private TableView<ProgramasAluno> tvProgramas;
    @FXML
    private TableColumn<ProgramasAluno, String> tc1;
    @FXML
    private TableColumn<ProgramasAluno, String> tc2;
    @FXML
    private TableColumn<ProgramasAluno, String> tc3;
    @FXML
    private TableColumn<ProgramasAluno, Integer> tc4;
    @FXML
    private TableColumn<ProgramasAluno, Integer> tc5;
    @FXML
    private TableColumn<ProgramasAluno, String> tc6;
    @FXML
    private TableColumn<ProgramasAluno, Date> tc7;

    @FXML
    private TableView<ProgramasAluno> tvProgsReport;
    @FXML
    private TableColumn<ProgramasAluno, String> tc11;
    @FXML
    private TableColumn<ProgramasAluno, String> tc21;

    @FXML
    private TextField lblRegistrosPro;
    @FXML
    private JFXButton buProIns;
    @FXML
    private JFXButton buProEdi;
    @FXML
    private TextArea taConsist;
    @FXML
    private TextField tfStatusDados;
    @FXML
    private TextField tfDataInc;
    @FXML
    private TextField tfDataAtu;
    @FXML
    private TextArea taObservacoes;
    @FXML
    private TableView<GridDocs> tvNewDocs;
    @FXML
    private TextField lblRegistrosDoc;
    @FXML
    private JFXButton buPegaDoc;
    @FXML
    private JFXButton buConfDoc;
    @FXML
    private JFXButton buRemoveDoc;
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
    private TextField edNome;
    @FXML
    private JFXRadioButton rbSexF;
    @FXML
    private ToggleGroup gp1;
    @FXML
    private JFXRadioButton rbSexM;
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
    private TableColumn<DisciProgAluno, String> tcSit;
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
    private TableColumn<DisciProgAluno, Integer> tcCadastroAlunoDisciplinaId;
    @FXML
    private TableColumn<DisciProgAluno, String> tcTip;
    @FXML
    private JFXButton buVisualiza;
    @FXML
    private JFXButton buMarcaTodos;
    @FXML
    private JFXButton buDesmarcaTodos;
    @FXML
    private RadioButton rb1Sem;
    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton rb2Sem;
    @FXML
    private TextField edAnoPadrao;

    @FXML
    private ImageView ivFotoAluno;
    @FXML
    private TextField lblRegs2;
    @FXML
    private TextField edMatri;
    @FXML
    private Label lblCodAluno;

    @FXML
    private FontAwesomeIconView fvBoneco;

    @FXML
    private Pane top_pane1;
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
    private Pane top_pane11;
    @FXML
    private JFXButton buRefUFAluno1;
    @FXML
    private TabPane tbALuno;
    @FXML
    private Tab tabDadosPessoais;
    @FXML
    private JFXCheckBox chkEspecial;
    @FXML
    private AnchorPane paneCabec;
    @FXML
    private Line lineRed;
    @FXML
    private Line lineYellow;
    @FXML
    private TextArea taObsSup;
    @FXML
    private TextArea taObsEsp;
    @FXML
    private ComboBox<String> cbGrade;
    @FXML
    private JFXButton buProEdi1;
    @FXML
    private JFXButton buProEdi11;
    @FXML
    private JFXButton buProEdi111;
    @FXML
    private JFXButton buProEdi1111;
    @FXML
    private TableColumn<?, ?> tcEqui;
    @FXML
    private JFXButton buProEdi11111;
    @FXML
    private JFXButton buProEdi1112;
    @FXML
    private Label lblIdade;
    @FXML
    private AnchorPane pnGrav;
    @FXML
    private Label lblGrav;
    @FXML
    private SplitPane spPresenca;
    @FXML
    private TableView<Presenca> tvPresenca;
    @FXML
    private TableColumn<Presenca, Integer> tcCodChamada;
    @FXML
    private TableColumn<Presenca, Integer> tcNumAula;
    @FXML
    private TableColumn<Presenca, Date> tcDataAula;
    @FXML
    private TableColumn<Presenca, String> tcChamadaAula;
    @FXML
    private TableColumn<Presenca, String> tcObsAula;
    @FXML
    private Label lblFreq;
//    private TextField edLeAno;
//    private RadioButton rbLeSem1;
//    private RadioButton rbLeSem2;

    private ComboBox<String> cbTipoDoc = new ComboBox<>();
    ObservableList<GridDocs> newdocs;

    private List<File> listDocs;

    private boolean inserindo, editando;
    private ObservableList<String> dadosUF;
    private ObservableList<String> dadosEC;
    private ObservableList<String> dadosDE;
    private ObservableList<String> dadosDC;
    private ObservableList<String> dadosFO;
    private ObservableList<String> dadosPR;
    private ObservableList<String> dadosNA;
    private ObservableList<String> dadosCP;
    private ObservableList<String> dadosTD;
    private ObservableList<DisciProgAluno> dadosRD;
    private ObservableList<MatriSemestre> dadosMS;
    private ObservableList<String> dadosGra;

    private ObservableList<Dadocadastrogeralministeriais> dadosMI;
    private ObservableList<EsFotosalunos> dadosFoto;

    private DadocadastrogeralJpaController jpaCon;
    private DadocadastrogeralministeriaisJpaController jpaDCM;
    private SiglaestadoJpaController jpaUF;
    private EstadocivilJpaController jpaEC;
    private DenominacaoJpaController jpaDE;
    private DocsoficiaisJpaController jpaDC;
    private FormacaoJpaController jpaFO;
    private ProfissoesJpaController jpaPR;
    private NacionalidadeJpaController jpaNA;
    private DadocadastrogeralministeriaisJpaController jpaMI;
    private SiglaestadoJpaController jpaEX;
    private CordapeleJpaController jpaCP;
    private DocinscJpaController jpaTD;
    private DadocadastroprogramaJpaController jpaPro;
    private EsFotosalunosJpaController jpaFotos;
    private EsMatrisemJpaController jpaMS;
    private EsGradeJpaController jpaGra;
    private EsEquivalenciaJpaController jpaEqui;
    private CadastroalunodisciplinaJpaController jpaPup;

    Dadocadastrogeral reg_atual = new Dadocadastrogeral();
    ProgramasAluno pro_atual = new ProgramasAluno();

    ObservableList<ProgramasAluno> prolist;
    ObservableList<ProgramasAluno> prolistRep;
    ObservableList<GridDocs> doclist;
    //public Short insercaoPendente = null;
    public String sqlProgramas;
    public String sqlDisciplinas;
    public String sqlDocsAluno;
    public Connection con = DBConnector.getConnection();

    ObservableList<GridDocs> list = FXCollections.observableArrayList();

    TableColumn tcCoddocaluno = new TableColumn("coddocaluno");
    TableColumn tcRemoveDoc = new TableColumn("Rem");
    TableColumn tcLabelDoc = new TableColumn("Documento");
    TableColumn tcNomeArq = new TableColumn("nome Arq");
    TableColumn tcNomeOri = new TableColumn("nome Ori");
    TableColumn tcAnoLetivo = new TableColumn("Ano");
    TableColumn tcSemestreId = new TableColumn("Sem");
    TableColumn tcStatus = new TableColumn("Status");
    TableColumn tcUserInc = new TableColumn("User Inc");
    TableColumn tcUserAlt = new TableColumn("User Alt");
    TableColumn tcDataInc = new TableColumn("Data Inc");

    TableColumn tcDataAlt = new TableColumn("Data Alt");
    TableColumn<GridDocs, String> tcTipo = new TableColumn<>("Tipo do Documento");

    String sTipoDocPadrao = "<tipo documento>            ";

    final Short DA_NOVO = 1;
    final Short DA_ALTERADO = 2; // NOVO E JÁ SALVO. SÓ PODE SER ATUALIZADO, NÃO PODE SER APAGADO
    final Short DA_SALVO = 3;

    private int totCre;
    private int totCreAprov;
    EsFotosalunos reg_foto = new EsFotosalunos();
    EsMatrisem ems;

    ObservableList<Presenca> obPres;

    private final String SQLB_PROGRAMAS = "SELECT dcp.DadoCadastroProgramaId, dcp.dadocadastrogeralId, pro.programa, dcp.cursoId, cur.curso, prs.DadoCadastroProgramaSituacao, dcp.anoLetivo, dcp.semestreId, tur.turno, dcp.DataCadastro, titulacao "
            + "FROM programa pro "
            + "LEFT JOIN curso cur ON cur.ProgramaId = pro.ProgramaId "
            + "LEFT JOIN dadocadastroprograma dcp ON dcp.cursoId = cur.cursoId "
            + "LEFT JOIN dadocadastroprogramasituacao prs ON dcp.DadoCadastroProgramaSituacaoId = prs.DadoCadastroProgramaSituacaoId "
            + "LEFT JOIN turno tur on tur.turnoId = dcp.turnoId ";

    private final String SQLB_DISCIPLINAS
            = "SELECT pup.CadastroAlunoDisciplinaId, c.curso, cd.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao, fr.frequencia, "
            + " cd.Credito, cd.CargaHoraria, cd.AnoLetivo, cd.SemestreId, pup.codEquivalencia, pup.CadastroDisciplinaId, pup.TipoRegOuv "
            + "FROM cadastroalunodisciplina pup "
            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId "
            + "LEFT JOIN cadastrodisciplina cd ON pup.CadastroDisciplinaId = cd.CadastroDisciplinaId "
            + "LEFT JOIN funcionario fu ON fu.FuncionarioId = cd.FuncionarioId  "
            + "LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id "
            + "LEFT JOIN curso c ON dcp.CursoId = c.CursoId "
            + "LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  ";

//    private final String SQLB_MATRISEM
//            = "SELECT pup.CadastroAlunoDisciplinaId, c.curso, cd.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao, fr.frequencia, cd.Credito, cd.CargaHoraria, cd.AnoLetivo, cd.SemestreId "
//            + "FROM cadastroalunodisciplina pup "
//            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId "
//            + "LEFT JOIN cadastrodisciplina cd ON pup.CadastroDisciplinaId = cd.CadastroDisciplinaId "
//            + "LEFT JOIN funcionario fu ON fu.FuncionarioId = cd.FuncionarioId  "
//            + "LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id "
//            + "LEFT JOIN curso c ON dcp.CursoId = c.CursoId "
//            + "LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  ";
//    
    public final String SQLMATRISEM
            = "SELECT mse.CodMatriSem, mse.DataMatri, mse.AnoLetivo, mse.SemestreId "
            + " FROM es_matrisem mse";

    int modoMS = 0;
    @FXML
    private TableColumn<?, ?> tcCodCadDis;
    @FXML
    private TextField lblPresencaRodape;
    @FXML
    private Pane pnListagem;
    @FXML
    private JFXButton buPastaRelat;
    @FXML
    private TextField tfPathRelats;
    @FXML
    private ComboBox<String> cbImpAlunos;

    private ImpalunosJpaController jpaImp;
    ObservableList<String> dadosImp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblIdade.setText("");
        lineYellow.setVisible(false);
        lineRed.setVisible(false);

        // MAPA COM NOME DAS LISTAGENS
        cbImpAlunos.setItems(getImpAlunos());
        cbImpAlunos.getSelectionModel().select("Relatório de Notas");

//        tvMatriSemestre.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                inicJanelaMatriSem();
//            }
//        });
        //mostraMsg("ponto 1", "", 0);
        tc7.setCellFactory(column -> {
            TableCell<ProgramasAluno, Date> cell = new TableCell<ProgramasAluno, Date>() {

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if ((empty) || (item == null)) {
                        setText(null);
                    } else {
                        setText(GBFORMATDATA.format(item));
                    }
                }
            };

            return cell;
        });

        tcDataInc.setCellFactory(column -> {
            TableCell<GridDocs, Date> cell = new TableCell<GridDocs, Date>() {

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if ((empty) || (item == null)) {
                        setText(null);
                    } else {
                        setText(GBFORMATDATAHORA.format(item));
                    }
                }
            };

            return cell;
        });

        tcDataAlt.setCellFactory(column -> {
            TableCell<GridDocs, Date> cell = new TableCell<GridDocs, Date>() {

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if ((empty) || (item == null)) {
                        setText(null);
                    } else {
                        setText(GBFORMATDATAHORA.format(item));
                    }
                }
            };

            return cell;
        });

        tcDataMSem.setCellFactory(column -> {
            TableCell<MatriSemestre, Date> cell = new TableCell<MatriSemestre, Date>() {

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if ((empty) || (item == null)) {
                        setText(null);
                    } else {
                        setText(GBFORMATDATA.format(item));
                    }
                }
            };

            return cell;
        });

        // CARREGA ANO E SEMESTRE PADRÃO
        edAnoPadrao.setText("" + gbUltSem.getUltAno());

        if (gbUltSem.getUltSem() == 1) {
            rb1Sem.setSelected(true);
        } else {
            rb2Sem.setSelected(true);
        }

        buVisualiza.setTooltip(new Tooltip("Tenta visualizar o documento"));
        buMarcaTodos.setTooltip(new Tooltip("Marca todos as linhas"));
        buDesmarcaTodos.setTooltip(new Tooltip("Desmarca todas as linhas"));

        //   buCopia.setVisible(gbUser.getLogin().equals("lfa"));
        tvMinitry.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        AbreMinisterio();
                    }
                }
            }
        });

        tcDataAula.setCellFactory(column -> {
            TableCell<Presenca, Date> cell = new TableCell<Presenca, Date>() {

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if ((empty) || (item == null)) {
                        setText(null);
                    } else {
                        setText(GBFORMATDATA.format(item));
                    }
                }
            };

            return cell;
        });

        tvRegDis.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                lblFreq.setText(tvRegDis.getSelectionModel().getSelectedItem().getDisciplina());
            }
        });

        tvProgramas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                iniMatriSem();
                initRegDis();
            }
        });

        tvProgramas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        AbrePrograma(2);
                    }
                }
            }
        });

        jpaCon = new DadocadastrogeralJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO
        jpaMI = new DadocadastrogeralministeriaisJpaController();
        jpaMS = new EsMatrisemJpaController();
        jpaGra = new EsGradeJpaController();

        // CARREGA COMBOBOXES
        //   cbTipoDoc = new ComboBox();
        cbTipoDoc.setItems(getTipoDoc());
        cbUFAluno.setItems(getNomeUF());
        cbEstadoCivil.setItems(getNomeEstadoCivil());
        cbUFNatu.setItems(getNomeUF());
        cbMedioUF.setItems(getNomeUF());
        cbSuperiorUF.setItems(getNomeUF());
        cbDenominacao.setItems(getNomeDenominacao());
        cbDocsOficiais.setItems(getNomeDocsOficiais());
        cbFormacao.setItems(getNomeFormacao());
        cbProfissao.setItems(getNomeProfissao());
        cbProfissao2.setItems(getNomeProfissao());
        cbNacionalidade.setItems(getNomeNacionalidades());
        cbCorPele.setItems(getCordaPele());

        // PREENCHE COMBOS ANO E SEMESTRE DA MATRÍCULA SEMESTRAL
        cbGrade.setItems(getGradeMS());
        // SELECIONA SEMESTRE ATUAL
        String semAtual = gbUltSem.getUltAno() + "." + gbUltSem.getUltSem();
        cbGrade.getSelectionModel().select(semAtual);

        new ComboBoxAutoComplete<String>(cbTipoDoc, "Tipo de Docs");
        new ComboBoxAutoComplete<String>(cbUFAluno, "UF");
        new ComboBoxAutoComplete<String>(cbEstadoCivil, "Estado Civil");
        new ComboBoxAutoComplete<String>(cbUFNatu, "UF");
        new ComboBoxAutoComplete<String>(cbMedioUF, "UF");
        new ComboBoxAutoComplete<String>(cbSuperiorUF, "UF");
        new ComboBoxAutoComplete<String>(cbDenominacao, "Denominação");
        new ComboBoxAutoComplete<String>(cbDocsOficiais, "Docs. Oficiais");
        new ComboBoxAutoComplete<String>(cbFormacao, "Formação");
        new ComboBoxAutoComplete<String>(cbProfissao, "Profissão");
        new ComboBoxAutoComplete<String>(cbProfissao2, "Profissão");
        new ComboBoxAutoComplete<String>(cbNacionalidade, "Nacionalidade");
        new ComboBoxAutoComplete<String>(cbCorPele, "Cor da Pele");

        edFoneRes.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if (!newPropertyValue) {
                        edFoneRes.setText(foneMascarado(edFoneRes.getText()));
                    }
                }
                );

        edFoneCel.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if (!newPropertyValue) {
                        edFoneCel.setText(foneMascarado(edFoneCel.getText()));
                    }
                }
                );

        edFoneCom.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if (!newPropertyValue) {
                        edFoneCom.setText(foneMascarado(edFoneCom.getText()));
                    }
                }
                );

        edFoneIgreja.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if (!newPropertyValue) {
                        edFoneIgreja.setText(foneMascarado(edFoneIgreja.getText()));
                    }
                }
                );

        edEnderecoAluno.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edEnderecoAluno.setText(corrigeCaixaNomes(edEnderecoAluno.getText()));
                    }
                }
                );

        edBairroAluno.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edBairroAluno.setText(corrigeCaixaNomes(edBairroAluno.getText()));
                    }
                }
                );

        edCidadeAluno.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edCidadeAluno.setText(corrigeCaixaNomes(edCidadeAluno.getText()));
                    }
                }
                );

        edEmailAluno.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edEmailAluno.setText(edEmailAluno.getText().toLowerCase());
                    }
                }
                );

        edEmailIgreja.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edEmailIgreja.setText(edEmailIgreja.getText().toLowerCase());
                    }
                }
                );

        edConjuge.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edConjuge.setText(corrigeCaixaNomes(edConjuge.getText()));
                    }
                }
                );

        edNaturalidade.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edNaturalidade.setText(corrigeCaixaNomes(edNaturalidade.getText()));
                    }
                }
                );

        edCompProfissao.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edCompProfissao.setText(corrigeCaixaNomes(edCompProfissao.getText()));
                    }
                }
                );

        edCompProfissao2.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edCompProfissao2.setText(corrigeCaixaNomes(edCompProfissao2.getText()));
                    }
                }
                );

        edMedioInst.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edMedioInst.setText(corrigeCaixaNomes(edMedioInst.getText()));
                    }
                }
                );

        edMedioCid.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edMedioCid.setText(corrigeCaixaNomes(edMedioCid.getText()));
                    }
                }
                );

        edCursoEscola.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edCursoEscola.setText(corrigeCaixaNomes(edCursoEscola.getText()));
                    }
                }
                );

        edSuperiorInst.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edSuperiorInst.setText(corrigeCaixaNomes(edSuperiorInst.getText()));
                    }
                }
                );

        edSuperiorCid.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edSuperiorCid.setText(corrigeCaixaNomes(edSuperiorCid.getText()));
                    }
                }
                );

        edCursoPos.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edCursoPos.setText(corrigeCaixaNomes(edCursoPos.getText()));
                    }
                }
                );

        edIgreja.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edIgreja.setText(corrigeCaixaNomes(edIgreja.getText()));
                    }
                }
                );

        edPastorIgreja.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        edPastorIgreja.setText(corrigeCaixaNomes(edPastorIgreja.getText()));
                    }
                }
                );

        edNome.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        // verifica se nome já existe
                        if ((edNome.getText() == null) || (edNome.equals(null)) || (edNome.getText().trim().length() == 0)) {
                            Short um = 0;
                        } else {
                            edNome.setText(corrigeCaixaNomes(edNome.getText()));
                            if (reg_atual.getDadoCadastroGeralId() != null) {
                                Integer codAchado = verifNome(edNome.getText(), reg_atual.getDadoCadastroGeralId());
                                if (!codAchado.equals(reg_atual.getDadoCadastroGeralId())) {
                                    mostraMsg("Atenção!", "O(a) aluno(a) <" + edNome.getText() + "> já está cadatrado(a)!", 0);
                                }
                            }
                        }
                    }
                }
                );

        edCPF.focusedProperty()
                .addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
                    if ((!newPropertyValue) && (inserindo || editando)) {
                        // verifica se nome já existe
                        String nomeAchado = verifCpf(edCPF.getText(), edNome.getText());
                        if ((edCPF.getText() == null) || (edCPF.equals(null)) || (edCPF.getText().trim().length() == 0)) {
                            Short um = 0;
                        } else {
                            if (!nomeAchado.equals(edNome.getText()) && (!nomeAchado.equals(edCPF.getText()))) {
                                mostraMsg("Atenção!", "O CPF <" + edCPF.getText() + "> já está cadatrado!\n Aluno(a) " + nomeAchado, 0);
                            }
                        }
                    }
                }
                );

        buApaga.setVisible(
                true);
        if (gbClicouInsere) {
            lblCodAluno.setText("");
            lineRed.setVisible(false);
            chkEspecial.setSelected(false);

            // GERA UM REGISTRO COM PRÓXIMO CÓDIGO (PK AI) E NOME PROVISÓRIO
            gbClicouInsere = false;
            reg_atual = new Dadocadastrogeral();
            gbRegCadGer = reg_atual;

            limpaCampos(); // para quem vai requestfocus;
            ligaEdicao();
            inserindo = true;
            edNome.requestFocus();
        } else {
            reg_atual = gbRegCadGer;
            if (reg_atual != null) {

                if (reg_atual.getEspecial() != null) {
                    if (reg_atual.getEspecial()) {
                        lineRed.setVisible(true);
                        chkEspecial.setSelected(true);
                    } else {
                        lineRed.setVisible(false);
                        chkEspecial.setSelected(false);
                    }
                }

                inserindo = false;
                editando = false;
                desligaEdicao();
                initColsMin();
                initColsPro();
                preencheEdsComObj();
                initGridDocs();

            }
        }

        tvProgramas.requestFocus();

        tvProgramas.getSelectionModel()
                .select(0);
        tvProgramas.getFocusModel()
                .focus(0);
        // MUDAR COR DE tvRegDis (dispensa)

        tcDis.setSortable(
                false);
        tcProf.setSortable(
                false);
        tcMed.setSortable(
                false);
        tcFal.setSortable(
                false);
        tcSit.setSortable(
                false);
        tcFreq.setSortable(
                false);
        tcCre.setSortable(
                false);
        tcCar.setSortable(
                false);
        tcAno.setSortable(
                false);
        tcSem.setSortable(
                false);

        tcSit.setCellFactory(column
                -> {
            return new TableCell<DisciProgAluno, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        DisciProgAluno auxPerson = getTableView().getItems().get(getIndex());
                        /*
                        if (auxPerson.getSituaacao().indexOf("Gravação") != -1) {
                            setTextFill(Color.WHITE); //The text in red
                            setStyle("-fx-background-color: #1da11d");
                        } else {
                            if (auxPerson.getSituaacao().indexOf("ispensado") != -1) {
                                setTextFill(Color.WHITE); //The text in red
                                setStyle("-fx-background-color: #32550F");
                            } else {
                                if (auxPerson.getSituaacao().indexOf("Dispensa (externa)") != -1) {
                                    setTextFill(Color.WHITE); //The text in red
                                    setStyle("-fx-background-color: #E16B00");
                                } else {
                                    if (auxPerson.getSituaacao().indexOf("Dispensa (interna)") != -1) {
                                        setTextFill(Color.WHITE); //The text in red
                                        setStyle("-fx-background-color: #32550F");
                                    } else {
                                        if (auxPerson.getSituaacao().indexOf("Equiv") != -1) {
                                            setTextFill(Color.WHITE); //The text in red
                                            setStyle("-fx-background-color: #572364");
                                        } else {
                                            if (auxPerson.getSituaacao().indexOf("Reprov") != -1) {
                                                setTextFill(Color.WHITE); //The text in red
                                                setStyle("-fx-background-color: #B71A09");
                                            } else {
                                                //Here I see if the row of this cell is selected or not
                                                if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                                    setTextFill(Color.WHITE);
                                                } else {
                                                    setTextFill(Color.BLACK);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
*/
                    }
                }
            };

        }
        );
        inicPnGrav();
//        tvRegDis.setRowFactory(row -> new TableRow<DisciProgAluno>() {
//            @Override
//            public void updateItem(DisciProgAluno item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (item == null || empty) {
//                    setStyle("");
//                } else {
//                    //Now 'item' has all the info of the Person in this row
//                    if (item.getSituaacao().contains("Dispensado")) {
//                        //We apply now the changes in all the cells of the row
//                        for (int i = 0; i < getChildren().size(); i++) {
//                            ((Labeled) getChildren().get(i)).setTextFill(Color.RED);
//                            ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: yellow");
//                        }
//                    } else {
//                        if (getTableView().getSelectionModel().getSelectedItems().contains(item)) {
//                            for (int i = 0; i < getChildren().size(); i++) {
//                                ((Labeled) getChildren().get(i)).setTextFill(Color.WHITE);;
//                            }
//                        } else {
//                            for (int i = 0; i < getChildren().size(); i++) {
//                                ((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);;
//                            }
//                        }
//                    }
//                }
//            }
//        });
    }

    public void preencheEdsComObj() {
        lblCodAluno.setText(reg_atual.getDadoCadastroGeralId().toString());
        edNome.setText(reg_atual.getNome());

        if (reg_atual.getEspecial() != null) {
            if (reg_atual.getEspecial()) {
                lineRed.setVisible(true);
                chkEspecial.setSelected(true);
            } else {
                lineRed.setVisible(false);
                chkEspecial.setSelected(false);
            }
        }

        if (reg_atual.getSexoId() == 1) {
            rbSexF.setSelected(true);
        } else {
            rbSexM.setSelected(true);
        }

        edMatri.setText(reg_atual.getMatricula());

        cbUFAluno.getSelectionModel().select((reg_atual.getNomeUFResid() != null) ? reg_atual.getNomeUFResid().getEstado() : null);
        cbEstadoCivil.getSelectionModel().select((reg_atual.getNomeEstadoCivil() != null) ? reg_atual.getNomeEstadoCivil().getEstadoCivil() : null);
        cbUFNatu.getSelectionModel().select((reg_atual.getNomeUFNasc() != null) ? reg_atual.getNomeUFNasc().getEstado() : null);
        cbNacionalidade.getSelectionModel().select((reg_atual.getNomeNacionalidade() != null) ? reg_atual.getNomeNacionalidade().getNacionalidade() : null);
        cbMedioUF.getSelectionModel().select((reg_atual.getNomeUFEscola() != null) ? reg_atual.getNomeUFEscola().getEstado() : null);
        cbDocsOficiais.getSelectionModel().select((reg_atual.getNomeDocOficial() != null) ? reg_atual.getNomeDocOficial().getNomeDocOficial() : null);
        cbSuperiorUF.getSelectionModel().select((reg_atual.getNomeUFPos() != null) ? reg_atual.getNomeUFPos().getEstado() : null);
        cbCorPele.getSelectionModel().select((reg_atual.getNomeCorDaPele() != null) ? reg_atual.getNomeCorDaPele().getCordapele() : null);

        edCepAluno.setText((reg_atual.getCEPResidencial() != null) ? reg_atual.getCEPResidencial() : null);
        edEnderecoAluno.setText((reg_atual.getEnderecoResidencial() != null) ? reg_atual.getEnderecoResidencial() : null);
        edBairroAluno.setText((reg_atual.getBairroResidencial() != null) ? reg_atual.getBairroResidencial() : null);
        edCidadeAluno.setText((reg_atual.getCidadeResidencial() != null) ? reg_atual.getCidadeResidencial() : null);

        edFoneRes.setText((reg_atual.getTelefoneResidencial() != null) ? reg_atual.getTelefoneResidencial() : null);
        edFoneCel.setText((reg_atual.getTelefoneCelular() != null) ? reg_atual.getTelefoneCelular() : null);
        edFoneCom.setText((reg_atual.getTelefoneComercial() != null) ? reg_atual.getTelefoneComercial() : null);
        edEmailAluno.setText((reg_atual.getEmailPessoal() != null) ? reg_atual.getEmailPessoal() : null);

        edConjuge.setText((reg_atual.getConjuge() != null) ? reg_atual.getConjuge() : null);
        edDependentes.setText((reg_atual.getnDependentes() != null) ? reg_atual.getnDependentes().toString() : null);
        edNaturalidade.setText((reg_atual.getLocalNascimento() != null) ? reg_atual.getLocalNascimento() : null);
        dpNasc.setValue((reg_atual.getDataNascimento() != null) ? reg_atual.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);

        if (dpNasc.getValue() != null) {
            String idade = calcularIdade(Date.from(dpNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            idade = idade.substring(0, 2) + " anos e " + idade.substring(3, 5) + " mês(es)";
            lblIdade.setText(idade);
        }

        edRG.setText((reg_atual.getRg() != null) ? reg_atual.getRg() : null);
        edCPF.setText((reg_atual.getCpf() != null) ? reg_atual.getCpf() : null);
        edNumDocOficial.setText((reg_atual.getNumDocOficial() != null) ? reg_atual.getNumDocOficial() : null);
        cbFormacao.getSelectionModel().select((reg_atual.getNomeFormacao() != null) ? reg_atual.getNomeFormacao().getNomeFormacao() : null);
        cbProfissao.getSelectionModel().select((reg_atual.getNomeProfissao() != null) ? reg_atual.getNomeProfissao().getNomeProfissao() : null);
        cbProfissao2.getSelectionModel().select((reg_atual.getNomeProfissao2() != null) ? reg_atual.getNomeProfissao2().getNomeProfissao() : null);
        edCompProfissao.setText((reg_atual.getCompProfissao() != null) ? reg_atual.getCompProfissao() : null);
        edCompProfissao2.setText((reg_atual.getCompProfissao2() != null) ? reg_atual.getCompProfissao2() : null);

        edEscolaridade.setText((reg_atual.getEscolaGrauMaxima() != null) ? String.valueOf(reg_atual.getEscolaGrauMaxima()) : null);
        edMedioInst.setText((reg_atual.getEscola() != null) ? reg_atual.getEscola() : null);
        edMedioCid.setText((reg_atual.getCidadeEscola() != null) ? reg_atual.getCidadeEscola() : null);
        edMedioAno.setText((reg_atual.getAnoConclusaoEscola() != null) ? reg_atual.getAnoConclusaoEscola().toString() : null);
        edCursoEscola.setText((reg_atual.getCursoEscola() != null) ? reg_atual.getCursoEscola().toString() : null);

        edSuperiorInst.setText((reg_atual.getEscolaPosGrad() != null) ? reg_atual.getEscolaPosGrad() : null);
        edSuperiorCid.setText((reg_atual.getCidadePosGrad() != null) ? reg_atual.getCidadePosGrad() : null);
        edSuperiorAno.setText((reg_atual.getAnoConclusaoPosGrad() != null) ? reg_atual.getAnoConclusaoPosGrad() : null);
        edCursoPos.setText((reg_atual.getCursoPosGrad() != null) ? reg_atual.getCursoPosGrad().toString() : null);

        cbDenominacao.getSelectionModel().select((reg_atual.getNomeDenominacao() != null) ? reg_atual.getNomeDenominacao().getDenominacao() : null);
        edIgreja.setText((reg_atual.getNomeIgreja() != null) ? reg_atual.getNomeIgreja() : null);
        edFoneIgreja.setText((reg_atual.getTelefoneIgreja() != null) ? reg_atual.getTelefoneIgreja() : null);
        edEmailIgreja.setText((reg_atual.getEmailIgreja() != null) ? reg_atual.getEmailIgreja() : null);
        edPastorIgreja.setText((reg_atual.getPastorIgreja() != null) ? reg_atual.getPastorIgreja() : null);

        taConsist.setText(reg_atual.getConsist());
        tfStatusDados.setText(reg_atual.getSituacaodados());

        if (reg_atual.getDataInclusao() != null) {
            tfDataInc.setText(GBFORMATDATAHORA.format(reg_atual.getDataInclusao()));
        } else {
            tfDataInc.setText("");
        }

        if (reg_atual.getAtualizacao() != null) {
            tfDataAtu.setText(GBFORMATDATAHORA.format(reg_atual.getAtualizacao()));
        } else {
            tfDataAtu.setText("");
        }

        leFoto(Integer.valueOf(reg_atual.getDadoCadastroGeralId()));
        taObservacoes.setText((reg_atual.getObsGeral() != null) ? reg_atual.getObsGeral().toString() : null);
        taObsSup.setText((reg_atual.getObsEnsinoMed() != null) ? reg_atual.getObsEnsinoMed().toString() : null);
        taObsEsp.setText((reg_atual.getObsEnsinoSup() != null) ? reg_atual.getObsEnsinoSup().toString() : null);

    }

//    public void initPainelMatriSem() {
//        dadosMS = FXCollections.observableArrayList();
//        if (tvProgramas.getSelectionModel().getSelectedItem() != null) {
//            if (!lblCodAluno.getText().trim().equals("")) {
//                ProgramasAluno proAlu = tvProgramas.getSelectionModel().getSelectedItem();
//                String sqlPainelMS = "SELECT CodMatriSem, AnoLetivo, SemestreId FROM es_matrisem "
//                        + " WHERE DadoCadastroGeralId = " + lblCodAluno.getText() + " "
//                        + " AND DadoCadastroProgramaId = " + tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId()
//                        + " ORDER BY AnoLetivo, SemestreId";
//                try {
//                    System.out.println(sqlPainelMS);
//                    ResultSet rs = con.createStatement().executeQuery(sqlPainelMS);
//                    while (rs.next()) {
//                        dadosMS.add(new PainelMatriSem(
//                                rs.getInt("codMatriSem"),
//                                rs.getShort("AnoLetivo"),
//                                rs.getShort("SemestreId")));
//
//                    }
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(DadocadastrogeralController.class
//                            .getName()).log(Level.SEVERE, null, ex);
//                    mostraMsg("Erro 018d - em initPainelMatriSem()", "" + ex, 2);
//                }
//            }
//        }
//
//        tcAnoMS.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
//        tcSemMS.setCellValueFactory(new PropertyValueFactory<>("semestre"));
//
//        tvMatriSem.setItems(dadosMS);
//        tvMatriSem.refresh();
//       }
    public void initRegDis() {
        dadosRD = FXCollections.observableArrayList();
        if (tvProgramas.getSelectionModel().getSelectedItem() != null) {
            if (!lblCodAluno.getText().trim().equals("")) {
                ProgramasAluno proAlu = tvProgramas.getSelectionModel().getSelectedItem();

                sqlDisciplinas = SQLB_DISCIPLINAS
                        + " WHERE dcp.DadoCadastroGeralId = " + lblCodAluno.getText() + " "
                        + "  AND c.curso = '" + proAlu.getCurso() + "'"
                        + "  ORDER BY pup.DadoCadastroProgramaId, cd.AnoLetivo DESC, cd.SemestreId DESC";

                try {
                    System.out.println("***********************");
                    System.out.println(sqlDisciplinas);
                    System.out.println("***********************");

                    ResultSet rs = con.createStatement().executeQuery(sqlDisciplinas);
                    totCre = 0;
                    totCreAprov = 0;
                    while (rs.next()) {
                        dadosRD.add(new DisciProgAluno(
                                rs.getInt("pup.CadastroAlunoDisciplinaId"),
                                rs.getString("Disciplina"),
                                rs.getString("nome"),
                                rs.getFloat("media"),
                                rs.getShort("falta"),
                                rs.getString("Descricao"),
                                rs.getString("frequencia"),
                                rs.getShort("Credito"),
                                rs.getShort("CargaHoraria"),
                                rs.getShort("AnoLetivo"),
                                rs.getShort("SemestreId"),
                                rs.getInt("codEquivalencia"),
                                rs.getInt("pup.CadastroDisciplinaId"),
                                rs.getString("pup.TipoRegOuv")));

                        totCre = totCre + rs.getShort("Credito");
                        if ((rs.getString("Descricao") != null) && (rs.getString("Descricao").equals("Aprovado"))) {
                            totCreAprov = totCreAprov + rs.getShort("Credito");

                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DadocadastrogeralController.class
                            .getName()).log(Level.SEVERE, null, ex);
                    mostraMsg("Erro 018c - em initRegDis()", "" + ex, 2);
                }
            }
        }

        tcCadastroAlunoDisciplinaId.setCellValueFactory(new PropertyValueFactory<>("cadastroAlunoDisciplinaId"));
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
        tcEqui.setCellValueFactory(new PropertyValueFactory<>("codEqui"));
        tcTip.setCellValueFactory(new PropertyValueFactory<>("tipoRegOuv"));

        tvRegDis.setItems(dadosRD);
        tvRegDis.refresh();
        String espacos = "     |     ";
        lblRegs2.setText("Disciplinas listadas: " + tvRegDis.itemsProperty().get().size() + espacos
                + "Créditos listados: " + totCre + espacos + "Créditos válidos: " + totCreAprov);
    }

    @FXML
    private void clicouInsere(ActionEvent event) throws Exception {
        reg_atual = new Dadocadastrogeral();
        lblCodAluno.setText("");
        gbClicouInsere = false;
        gbRegCadGer = reg_atual;

        limpaCampos(); // para quem vai requestfocus;
        ligaEdicao();
        inserindo = true;
        edNome.requestFocus();
        SingleSelectionModel<Tab> selectionModel = tbALuno.getSelectionModel();
        selectionModel.select(1); //select by index starting with 0
        taConsist.setText("");
        tvProgramas.setItems(null);
        tvMatriSemestre.setItems(null);
        tvRegDis.setItems(null);
        tvNewDocs.setItems(null);
        lblRegistrosPro.setText("");
        lblRegs2.setText("");
        ivFotoAluno.setImage(null);

    }

    @FXML
    private void clicouApaga(ActionEvent event) {
//        if (!edNome.getText().equals("")) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("Confirmação - apagar registro " + reg_atual.getDadoCadastroGeralId());
//            alert.setContentText("Deseja realmente apagar esse registro?\nOs dados serão perdidos!");
//            Optional<ButtonType> option = alert.showAndWait();
//
//            if (option.get() == ButtonType.OK) {
//                try {
//                    jpaCon.destroy(reg_atual.getDadoCadastroGeralId());
//
//                    desligaEdicao();
//                    limpaCampos();
//                    initColsMin();
//                    initColsPro();
//                    mostraMsg("Dados apagados com sucesso", "", 3);
//                    // PREENCHE OBd COM Eds
//
//                } catch (Exception e) {
//                    mostraMsg("Erro ao apagar registro", "", 2);
//
//                }
//            }
//        }
    }

    @FXML
    private void clicouEdita(ActionEvent event) {
        if (!edNome.getText().equals("")) {
            ligaEdicao();
            editando = true;
        }
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
        ChecaErros check = new ChecaErros();
        taConsist.setText("");

        checaTTF("Nome", 1, edNome, check);
        if ((!rbSexF.isSelected()) && (!rbSexM.isSelected())) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Sexo> tem que ser selecionado!\n");
        } else {
            if (reg_atual.getDadoCadastroGeralId() != null) {
                Integer codAchado = verifNome(edNome.getText(), reg_atual.getDadoCadastroGeralId());
                if (!codAchado.equals(reg_atual.getDadoCadastroGeralId())) {
                    check.setCte(check.getCte() + 1);
                    check.setWarn(check.getWarn() + "*** O(a) aluno(a) <" + edNome.getText() + "> já está cadastrado(a)!\n");
                }
            }
        }

        if ((edMatri.equals(null)) || (edMatri.getText() == null)) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Matrícula (Biblioteca> está vazio!\n");
        } else {
            //        checaTTF("Matrícula (Bilioteca)", 2, edMatrii, check);
            String nomeAchado = verifMatric(edMatri.getText(), edNome.getText());
            if (!nomeAchado.equals(edNome.getText())) {
                check.setCte(check.getCte() + 1);
                check.setWarn(check.getWarn() + "*** A matrícula <" + edMatri.getText() + "> já pertence a " + nomeAchado + "!\n");
            }
        }

        String b0 = edCepAluno.getText();
        if (b0 == null || (b0.trim().length() == 0)) {
            check.setWarn(check.getWarn() + "- O campo <CEP> está vazio.\n");
            check.setCtw(check.getCtw() + 1);

        } else {
            if (edCepAluno.getText().trim().length() < 9) {
                check.setWarn(check.getWarn() + "*** O campo <CEP> está incorreto!\n");
                check.setCte(check.getCte() + 1);
            }

        }
        checaTTF("Endereço do Aluno", 2, edEnderecoAluno, check);
        checaTTF("Cidade do Aluno", 2, edCidadeAluno, check);
        checaTTF("Bairro do Aluno", 2, edBairroAluno, check);
        //  checaCombo("Estado do Aluno", 2, cbUFAluno, check);
        if (cbUFAluno.getSelectionModel().getSelectedItem() == null) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Estado do Aluno> não pode ficar vazio.\n");
        }

        String fres, fcom, fcel;

        fres = "";
        String b1 = edFoneRes.getText();

        if (b1 == null || (b1.trim().length() == 0)) {
            fres = "";
        } else {
            if (edFoneRes.getText().equals("")) {
                fres = "";
            } else {
                fres = edFoneRes.getText();
            }
        }

        fcel = "";
        String b2 = edFoneCel.getText();

        if (b2 == null || (b2.trim().length() == 0)) {
            fcel = "";
        } else {
            if (edFoneCel.getText().equals("")) {
                fcel = "";
            } else {
                fcel = edFoneCel.getText();
            }
        }

        fcom = "";
        String b3 = edFoneCom.getText();

        if (b3 == null || (b3.trim().length() == 0)) {
            fcom = "";
        } else {
            if (edFoneCom.getText().equals("")) {
                fcom = "";
            } else {
                fcom = edFoneCom.getText();
            }
        }

        if ((fres.equals("")) && (fcom.equals("")) && (fcel.equals(""))) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- Pelo menos um telefone deve ser preenchido.\n");
        } else {
            if ((fres.trim().length() > 0) && (fres.trim().length() < 13)) {
                check.setCtw(check.getCtw() + 1);
                check.setWarn(check.getWarn() + "- O telefone residencial parece estar errado.\n");
            }
            if ((fcel.trim().length() > 0) && (fcel.trim().length() < 13)) {
                check.setCtw(check.getCtw() + 1);
                check.setWarn(check.getWarn() + "- O telefone celular parece estar errado.\n");
            }
            if ((fcom.trim().length() > 0) && (fcom.trim().length() < 13)) {
                check.setCtw(check.getCtw() + 1);
                check.setWarn(check.getWarn() + "- O telefone comercial parece estar errado.\n");
            }
        }

        if (!isEmailValid(edEmailAluno.getText())) {
            check.setWarn(check.getWarn() + "- O campo <E-mail> está incorreto ou vazio!\n");
            check.setCtw(check.getCtw() + 1);
        } else {
            if (edEmailAluno.getText().equals("")) {
                check.setWarn(check.getWarn() + "- O campo <E-mail> está vazio.\n");
                check.setCtw(check.getCtw() + 1);
            }
        }

        checaTTF("Local do Nascimento", 2, edNaturalidade, check);
//        checaCombo("Estado do Local de Nascimento", 2, cbUFNatu, check);
//        checaCombo("Nacionalidade", 2, cbNacionalidade, check);
//        checaCombo("Cor da Pele", 2, cbCorPele, check);
        if (cbUFNatu.getSelectionModel().getSelectedItem() == null) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Estado do Local de Nascimento> não pode ficar vazio.\n");
        }
        if (cbNacionalidade.getSelectionModel().getSelectedItem() == null) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Nacionalidade> não pode ficar vazio.\n");
        }
        if (cbCorPele.getSelectionModel().getSelectedItem() == null) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Cor da Pele> não pode ficar vazio.\n");
        }

        checaTTF("RG", 2, edRG, check);
        if ((edCPF.getText() == null) || (edCPF.equals(null)) || (edCPF.getText().trim().length() == 0)) {
            check.setWarn(check.getWarn() + "- O campo <CPF> está vazio.\n");
            check.setCtw(check.getCtw() + 1);

        } else {
            if (!isCPF(edCPF.getText())) {
                check.setWarn(check.getWarn() + "*** O campo <CPF> está incorreto!\n");
                check.setCte(check.getCte() + 1);
            }

        }
        checaTTF("Escolaridade", 2, edEscolaridade, check);
        checaTTF("Igreja", 2, edIgreja, check);
        checaTTF("Pastor", 2, edPastorIgreja, check);

        b0 = edFoneIgreja.getText();
        if (b0 == null || (b0.trim().length() == 0)) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O telefone da igreja parece estar errado.\n");
        } else {
            if ((edFoneIgreja.getText().trim().length() > 0) && (edFoneIgreja.getText().trim().length() < 13)) {
                check.setCtw(check.getCtw() + 1);
                check.setWarn(check.getWarn() + "- O telefone da igreja parece estar errado.\n");
            }
        }

        if (!isEmailValid(edEmailIgreja.getText())) {
            check.setWarn(check.getWarn() + "- O campo <E-mail da igreja> está incorreto ou vazio!\n");
            check.setCtw(check.getCtw() + 1);
        } else {
            if (edEmailIgreja.getText().equals("")) {
                check.setWarn(check.getWarn() + "- O campo <E-mail da igreja> está vazio.\n");
                check.setCtw(check.getCtw() + 1);
            }
        }

//        checaCombo("Formação", 2, cbFormacao, check);
//        checaCombo("Profissão", 2, cbProfissao, check);
//        // checaCombo("Profissão 2", 2, cbProfissao2, check);
//        checaCombo("Denominação", 2, cbDenominacao, check);
        if (cbFormacao.getSelectionModel().getSelectedItem() == null) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Formação> não pode ficar vazio.\n");
        }
        if (cbProfissao.getSelectionModel().getSelectedItem() == null) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Profissão> não pode ficar vazio.\n");
        }
        if (cbDenominacao.getSelectionModel().getSelectedItem() == null) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Denominação> não pode ficar vazio.\n");
        }

        if ((cbEstadoCivil.getSelectionModel().toString().equals("") || (cbEstadoCivil.getSelectionModel().getSelectedItem() == null))) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- O campo <Estado Civil do Aluno> não pode ficar vazio.\n");
        } else {
            b0 = edConjuge.getText();
            if ((cbEstadoCivil.getSelectionModel().getSelectedItem().equals("Casado (a)"))
                    && ((b0 == null || (b0.trim().length() == 0)))) {
                check.setCtw(check.getCtw() + 1);
                check.setWarn(check.getWarn() + "- Se o aluno for CASADO, o campo <Cônjuge> não pode ficar vazio.\n");
            }
        }

        if (check.getCte() + check.getCtw() > 0) {
            taConsist.setText("ATENÇÃO! \n" + "HÁ " + check.getCte() + " ERRO(S). \n"
                    + "HÁ " + check.getCtw() + " AVISO(S). \n\n" + check.getWarn());
            tfStatusDados.setText("DADOS INCOMPLETOS");
            tfStatusDados.setStyle("-fx-background-color : #FBA588;");
            mostraMsgWait("ATENÇÃO! \n" + "HÁ " + check.getCte() + " ERRO(S). \n"
                    + "HÁ " + check.getCtw() + " AVISO(S). \n\n", check.getWarn(), (check.getCte() > 0) ? 2 : 4);
//            reg_atual.setStatusdados("DADOS INCOMPLETOS"); // DADOS INCOMPLETOS
        } else {
            tfStatusDados.setText("DADOS COMPLETOS");
            tfStatusDados.setStyle("-fx-background-color : #ABD0BC;");
//            reg_atual.setStatusdados("DADOS COMPLETOS"); // DADOS INCOMPLETOS
        }

        if (check.getCte() == 0) {
            // NÃO HAVENDO ERROS, PODE TENTAR GRAVAR, MESMO COM AVISOS

            if (verifPodeGravar(edNome.getText()) == false) {
                mostraMsg("Não é possível gravar!", "Já existe outro registro com o mesmo <nome de aluno>.", 2);
            } else {
                // PODE GRAVAR!
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Confirma");
                alert.setContentText("Confirma a gravação dos dados?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {

                    if (editando == true) {
                        try {

                            fillObj(reg_atual, true);

                            reg_atual.setAtualizacao(new Date()); // ESTÁ EDITANDO
                            reg_atual.setDataAlt(new Date());
                            reg_atual.setCodUserAlt(gbUser.getCoduser());
                            gbRegCadGer = reg_atual;
                            jpaCon.edit(reg_atual); //*** GRAVA EDIÇÃO
                            editando = false;

                            desligaEdicao();
                            initColsMin();
                            initColsPro();
//                            insercaoPendente = null;
                            mostraMsg("Dados gravados com sucesso.", "", 3);

                        } catch (Exception e) {
                            System.out.println(reg_atual);
                            System.out.println("\n============================");
                            System.out.println(e);
                            System.out.println("============================\n");
                            mostraMsg("Erro ao gravar a edição dos dados.", "> " + e, 2);
                        }
                    } else {
                        if (inserindo == true) {
                            try {

                                Dadocadastrogeral obj = new Dadocadastrogeral();

                                fillObj(obj, false);
                                obj.setDataInclusao(new Date());
                                obj.setDataInc(new Date());
                                obj.setCodUserInc(gbUser.getCoduser());

                                jpaCon.create(obj); //*** GRAVA EDIÇÃO
                                reg_atual = obj;
                                gbRegCadGer = reg_atual;

                                lblCodAluno.setText(String.valueOf(reg_atual.getDadoCadastroGeralId()));
                                inserindo = false;

                                desligaEdicao();
                                tvProgramas.setItems(null);
                                tvMinitry.setItems(null);
//                                insercaoPendente = null;

                                mostraMsg("Dados gravados com sucesso.", "", 1);
                            } catch (Exception e) {
                                System.out.println(reg_atual);
                                System.out.println("\n============================");
                                System.out.println(e);
                                System.out.println("============================\n");
                                mostraMsg("Erro 014 ao gravar novos dados.", "> " + e, 2);
                            }
                        } else {
                            mostraMsg("Mensagem DN2", "Mensagem DN-YANot - 012", 2);
                        }
                    }

                }

            }
        }

    }

    private void fillObj(Dadocadastrogeral x, Boolean preenchePK) {
        if (preenchePK) {
            x.setDadoCadastroGeralId(Integer.parseInt(lblCodAluno.getText()));
        }

        x.setNome(edNome.getText());
        if (rbSexF.isSelected()) {
            Short sx = 1;
            x.setSexoId(sx);
        } else {
            Short sx = 2;
            x.setSexoId(sx);
        }

        x.setEspecial(chkEspecial.isSelected());

        x.setMatricula((edMatri.getText()));

        if (cbUFAluno.getSelectionModel().getSelectedItem() != null) {
            jpaUF = new SiglaestadoJpaController();
            x.setNomeUFResid(jpaUF.getObjeto(cbUFAluno.getSelectionModel().getSelectedItem()));
        }

        if (cbCorPele.getSelectionModel().getSelectedItem() != null) {
            jpaCP = new CordapeleJpaController();
            x.setNomeCorDaPele(jpaCP.getObjeto(cbCorPele.getSelectionModel().getSelectedItem()));
        }

        if (cbEstadoCivil.getSelectionModel().getSelectedItem() != null) {
            jpaEC = new EstadocivilJpaController();
            x.setNomeEstadoCivil(jpaEC.getObjeto(cbEstadoCivil.getSelectionModel().getSelectedItem()));
        }

        if (cbUFNatu.getSelectionModel().getSelectedItem() != null) {
            jpaUF = new SiglaestadoJpaController();
            x.setNomeUFNasc(jpaUF.getObjeto(cbUFNatu.getSelectionModel().getSelectedItem()));
        }

        if (cbNacionalidade.getSelectionModel().getSelectedItem() != null) {
            jpaNA = new NacionalidadeJpaController();
            x.setNomeNacionalidade(jpaNA.getObjeto(cbNacionalidade.getSelectionModel().getSelectedItem()));
        }

        if (cbDocsOficiais.getSelectionModel().getSelectedItem() != null) {
            jpaDC = new DocsoficiaisJpaController();
            x.setNomeDocOficial(jpaDC.getObjeto(cbDocsOficiais.getSelectionModel().getSelectedItem()));
        }

        if (cbMedioUF.getSelectionModel().getSelectedItem() != null) {
            jpaUF = new SiglaestadoJpaController();
            x.setNomeUFEscola(jpaUF.getObjeto(cbMedioUF.getSelectionModel().getSelectedItem()));
        }

        if (cbFormacao.getSelectionModel().getSelectedItem() != null) {
            jpaFO = new FormacaoJpaController();
            x.setNomeFormacao(jpaFO.getObjeto(cbFormacao.getSelectionModel().getSelectedItem()));
        }

        if (cbProfissao.getSelectionModel().getSelectedItem() != null) {
            jpaPR = new ProfissoesJpaController();
            x.setNomeProfissao(jpaPR.getObjeto(cbProfissao.getSelectionModel().getSelectedItem()));
        }

        if (cbProfissao2.getSelectionModel().getSelectedItem() != null) {
            jpaPR = new ProfissoesJpaController();
            x.setNomeProfissao2(jpaPR.getObjeto(cbProfissao2.getSelectionModel().getSelectedItem()));
        }

        if (cbSuperiorUF.getSelectionModel().getSelectedItem() != null) {
            jpaUF = new SiglaestadoJpaController();
            x.setNomeUFPos(jpaUF.getObjeto(cbSuperiorUF.getSelectionModel().getSelectedItem()));
        }

        if (cbDenominacao.getSelectionModel().getSelectedItem() != null) {
            jpaDE = new DenominacaoJpaController();
            x.setNomeDenominacao(jpaDE.getObjeto(cbDenominacao.getSelectionModel().getSelectedItem()));
        }

        x.setCEPResidencial(edCepAluno.getText());
        x.setEnderecoResidencial(edEnderecoAluno.getText());
        x.setBairroResidencial(edBairroAluno.getText());
        x.setCidadeResidencial(edCidadeAluno.getText());

        x.setTelefoneResidencial(edFoneRes.getText());
        x.setTelefoneCelular(edFoneCel.getText());
        x.setTelefoneComercial(edFoneCom.getText());
        x.setEmailPessoal(edEmailAluno.getText());
        x.setConjuge(edConjuge.getText());

        if ((edDependentes.getText() == null) || (edDependentes.getText().trim() == "") || (edDependentes.getText().trim() == "0")) {
            edDependentes.setText("0");
            x.setnDependentes(0);
        } else {
            x.setnDependentes(convInt(edDependentes.getText().trim()));
        }

        x.setLocalNascimento(edNaturalidade.getText());
        if (dpNasc.getValue() != null) {
            x.setDataNascimento(Date.from(dpNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        x.setRg(edRG.getText());
        x.setCpf(edCPF.getText());
        x.setNumDocOficial(edNumDocOficial.getText());
        x.setCompProfissao(edCompProfissao.getText());
        x.setCompProfissao2(edCompProfissao2.getText());

        // x.setEscolaGrauMaxima(edEscolaridade.getText().charAt(0));
        x.setEscolaGrauMaxima(edEscolaridade.getText());
        x.setEscola(edMedioInst.getText());
        x.setCidadeEscola(edMedioCid.getText());
        x.setCursoEscola(edCursoEscola.getText());
        x.setCursoPosGrad(edCursoPos.getText());

        if ((edMedioAno.getText() == null) || (edMedioAno.getText().trim() == "") || (edMedioAno.getText().trim() == "0")) {
            edMedioAno.setText("0");
            x.setAnoConclusaoEscola(0);
        } else {
            x.setAnoConclusaoEscola(convInt(edMedioAno.getText().trim()));
        }

        x.setEscolaPosGrad(edSuperiorInst.getText());
        x.setCidadePosGrad(edSuperiorCid.getText());
        x.setAnoConclusaoPosGrad(edSuperiorAno.getText());

        x.setNomeIgreja(edIgreja.getText());
        x.setTelefoneIgreja(edFoneIgreja.getText());
        x.setEmailIgreja(edEmailIgreja.getText());
        x.setPastorIgreja(edPastorIgreja.getText());

        x.setConsist(taConsist.getText());
        x.setSituacaodados(tfStatusDados.getText());

        if (inserindo) {
            x.setDataInclusao(new Date());
            tfDataInc.setText(GBFORMATDATAHORA.format(x.getDataInclusao()));
        } else { // atualizando
            x.setAtualizacao(new Date());
            tfDataAtu.setText(GBFORMATDATAHORA.format(x.getAtualizacao()));
        }
        x.setObsGeral(taObservacoes.getText());
        x.setObsEnsinoMed(taObsSup.getText());
        x.setObsEnsinoSup(taObsEsp.getText());
    }

    @FXML
    private void clicouCancela(ActionEvent event) throws NonexistentEntityException {
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
////                        String sqlApagaTemp = "DELETE FROM dadocadastrogeralministeriais  WHERE dadoCadastroGeralId = '" + insercaoPendente + "'";
////
////                        try {
////                            System.out.println(sqlApagaTemp);
////                            PreparedStatement stmt = conn.prepareStatement(sqlApagaTemp);
////                            mostraMsg("Ministérios do código " +insercaoPendente + " apagados.", "", 2);
////                        } catch (SQLException ex) {
////                            Logger.getLogger(DadocadastrogeralController.class
////                                    .getName()).log(Level.SEVERE, null, ex);
////                            mostraMsg("Erro 018 - em Apaga Temps()", "" + ex, 2);
////                        }
////
//////                        int apagados = jpaMI.apagaTemporario(insercaoPendente);
//////                        mostraMsg("foram apagados", apagados + " registros.", 1);
//                        insercaoPendente = null;
//                        tvMinitry.setItems(null);
//                    }
                    limpaCampos();    // limpa campos
                    inserindo = false;
                } else {
                    mostraMsg("Mensagem DN", "Mensagem DN-YANSTBH - 011", 2);
                }
            }
            desligaEdicao();
        }
    }

    @FXML
    private void clicouInsereMin(ActionEvent event) {
        if (reg_atual.getDadoCadastroGeralId() == null) {
            mostraMsg("Aviso", "Por favor, grave os dados do aluno antes de prosseguir", 4);
        } else {
            chamaCrudMin(1);
        }
    }

    private void chamaCrudMin(int crud) {
        gbCrudMin = crud;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CrudMinisteriais.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Ministérios");
            stage.setScene(scene);

            stage.showAndWait();
            initColsMin();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 004 ao carregar CrudMin", "" + ex, 0);
        }
    }

    @FXML
    private void clicouEditaMin(ActionEvent event) {
        if (tvMinitry.getSelectionModel().getSelectedItem() != null) {
            gbNomeCrudMin = tvMinitry.getSelectionModel().getSelectedItem().getMinisteriais();
            AbreMinisterio();
        }
    }

    private void AbreMinisterio() {
        chamaCrudMin(2);
    }

    private void AbrePrograma(int modo) { // modo = 1 INS, modo = 2 = EDI
        if (modo == 2) {

            if (tvProgramas.getSelectionModel().getSelectedItem() != null) {
                gbClicouInsPro = false;
                gbClicouEdiPro = true;
                pro_atual = tvProgramas.getSelectionModel().getSelectedItem();
                int codProAlu = tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId();

                jpaPro = new DadocadastroprogramaJpaController();
                gbRegProAlu = jpaPro.findDadocadastroprograma((Integer) codProAlu);
            }
        } else {
            gbClicouInsPro = true;
            gbClicouEdiPro = false;
            gbInsPro = new InserePro();

            gbInsPro.setAnoLetivo(gbUltSem.getUltAno());
            gbInsPro.setSemestre(gbUltSem.getUltSem());
            gbInsPro.setCodAluno(reg_atual.getDadoCadastroGeralId());
            gbInsPro.setNomeAluno(reg_atual.getNome());
            //    gbInsPro.setFotoMaior(ivFotoAluno.getImage());

        }

        if (reg_atual.getEspecial() != null) {
            gbEspecial = (reg_atual.getEspecial());
        } else {
            gbEspecial = false;
        }
        chamaTela("/view/CadProg2.fxml", "Cadastro do Programa");
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
            MyFunc.mostraMsg("Erro 0014 ao carregar CadProg2", "" + ex, 2);

        }
    }

    @FXML
    private void clicouApagaMin(ActionEvent event) {
//        if (tvMinitry.getSelectionModel().getSelectedItem() != null) {
//            gbNomeCrudMin = tvMinitry.getSelectionModel().getSelectedItem().getMinisteriais();
//            chamaCrudMin(3);
//        }
        if (tvMinitry.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Apagar o ministério <" + tvMinitry.getSelectionModel().getSelectedItem().getMinisteriais() + ">?");
            alert.setContentText("Deseja realmente apagar este registro?\nOs dados serão perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaDCM = new DadocadastrogeralministeriaisJpaController();
                    Integer qual = jpaDCM.getCodDocMin(gbRegCadGer.getDadoCadastroGeralId(), tvMinitry.getSelectionModel().getSelectedItem().getMinisteriais());
                    jpaDCM.destroy(qual);

//                    desligaEdicao();
//                    limpaCampos();
                    initColsMin();
                    mostraMsg("Dados apagados com sucesso", "", 3);
                    // PREENCHE OBd COM Eds
                } catch (Exception e) {
                    mostraMsg("Erro ao apagar registro", "" + e, 2);
                }
            }
        }
    }

    public ObservableList<String> getGradeMS() {
        dadosGra = FXCollections.observableArrayList(jpaGra.getGradeMS());

        if (dadosGra == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosGra;
        }
    }

    public ObservableList<String> getTipoDoc() {
        jpaTD = new DocinscJpaController();
        dadosTD = FXCollections.observableArrayList(jpaTD.getTipoDoc());

        if (dadosTD == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosTD;
        }
    }

    public ObservableList<String> getNomeUF() {
        jpaUF = new SiglaestadoJpaController();
        dadosUF = FXCollections.observableArrayList(jpaUF.getNomeDasUF());

        if (dadosUF == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosUF;
        }
    }

    public ObservableList<String> getNomeEstadoCivil() {
        jpaEC = new EstadocivilJpaController();
        dadosEC = FXCollections.observableArrayList(jpaEC.getNomeDosEstadosCivis());

        if (dadosEC == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosEC;
        }
    }

    public ObservableList<String> getNomeDenominacao() {
        jpaDE = new DenominacaoJpaController();
        dadosDE = FXCollections.observableArrayList(jpaDE.getNomeDasDeniminacoes());

        if (dadosDE == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosDE;
        }
    }

    public ObservableList<String> getNomeDocsOficiais() {
        jpaDC = new DocsoficiaisJpaController();
        dadosDC = FXCollections.observableArrayList(jpaDC.getNomeDosDocsOficiais());

        if (dadosDC == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosDC;
        }
    }

    public ObservableList<String> getNomeFormacao() {
        jpaFO = new FormacaoJpaController();
        dadosFO = FXCollections.observableArrayList(jpaFO.getNomeDasFormacoes());

        if (dadosFO == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosFO;
        }
    }

    public ObservableList<String> getNomeProfissao() {
        jpaPR = new ProfissoesJpaController();
        dadosPR = FXCollections.observableArrayList(jpaPR.getNomeDasProfissoes());

        if (dadosPR == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosPR;
        }
    }

    public ObservableList<String> getNomeNacionalidades() {
        jpaNA = new NacionalidadeJpaController();
        dadosNA = FXCollections.observableArrayList(jpaNA.getNomeDasNacionalidades());

        if (dadosNA == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosNA;
        }
    }

    public ObservableList<String> getCordaPele() {
        jpaCP = new CordapeleJpaController();
        dadosCP = FXCollections.observableArrayList(jpaCP.getNomeDaCordapele());

        if (dadosCP == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosCP;
        }
    }

    public ObservableList<Dadocadastrogeralministeriais> getNomeMinisterios() {
        jpaMI = new DadocadastrogeralministeriaisJpaController();
        dadosMI = FXCollections.observableArrayList(jpaMI.get00MinNomes(gbRegCadGer.getDadoCadastroGeralId()));

        if (dadosMI == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosMI;
        }
    }

    public void initColsMin() {
        tcMinistries.setCellValueFactory(new PropertyValueFactory<>("ministeriais"));
        tvMinitry.setItems(getNomeMinisterios());
        tvMinitry.refresh();
        lblRegistrosMin.setText("Programas Listados: " + tvMinitry.itemsProperty().get().size()); //rs2.getString(1));
    }

    public void initColsPro() {
        prolist = FXCollections.observableArrayList();
        // prolistRep = FXCollections.observableArrayList();
        if (gbRegCadGer.getDadoCadastroGeralId() > 0) {
            sqlProgramas = SQLB_PROGRAMAS
                    + " WHERE (dcp.DadoCadastroGeralId = " + gbRegCadGer.getDadoCadastroGeralId() + " )"
                    + " ORDER BY dcp.anoLetivo DESC";

            try {
                System.out.println("///////");
                System.out.println(sqlProgramas);
                System.out.println("///////");

                ResultSet rs = con.createStatement().executeQuery(sqlProgramas);
                while (rs.next()) {
                    prolist.add(new ProgramasAluno(rs.getInt("DadoCadastroProgramaId"),
                            rs.getInt("dadocadastrogeralId"),
                            rs.getString("programa"),
                            rs.getShort("dcp.cursoId"),
                            rs.getString("curso"),
                            rs.getString("DadoCadastroProgramaSituacao"),
                            rs.getInt("anoLetivo"),
                            rs.getInt("semestreId"),
                            rs.getString("turno"),
                            rs.getDate("dataCadastro"),
                            rs.getString("titulacao")));

                }

            } catch (SQLException ex) {
                Logger.getLogger(DadocadastrogeralController.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018 - em initColsPro()", "" + ex, 2);
            }
        }

        tc1.setCellValueFactory(new PropertyValueFactory<>("programa"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        tc6.setCellValueFactory(new PropertyValueFactory<>("turno"));
        tc7.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));

        tc11.setCellValueFactory(new PropertyValueFactory<>("titulacao"));
        tc21.setCellValueFactory(new PropertyValueFactory<>("curso"));

        tvProgramas.setItems(prolist);
        tvProgramas.refresh();
        tvProgsReport.setItems(prolist);
        tvProgsReport.refresh();
        tvProgsReport.getSelectionModel().selectFirst();
        lblRegistrosPro.setText("Registros exibidos: " + tvProgramas.itemsProperty().get().size()); //rs2.getString(1));

    }

    public void ligaEdicao() {
        buRefUFAluno.setDisable(false);
        buRefEst.setDisable(false);
        buRefNac.setDisable(false);
        buRefUFNat.setDisable(false);

        buRefDoc.setDisable(false);
        buRefFor.setDisable(false);
        buRefPro.setDisable(false);
        buRefUFMed.setDisable(false);
        buRefUFSup.setDisable(false);

        buRefDen.setDisable(false);

        buSP1.setDisable(false);
        buSP2.setDisable(false);
        buSP3.setDisable(false);

        // combos
        cbUFAluno.setDisable(false);
        cbEstadoCivil.setDisable(false);
        cbUFNatu.setDisable(false);
        cbNacionalidade.setDisable(false);
        cbMedioUF.setDisable(false);
        cbSuperiorUF.setDisable(false);
        cbDocsOficiais.setDisable(false);
        cbFormacao.setDisable(false);
        cbProfissao.setDisable(false);
        cbProfissao2.setDisable(false);
        cbDenominacao.setDisable(false);
        cbCorPele.setDisable(false);

        // habilita botões para inserção e deleção
        buInsere.setDisable(true);
        buEdita.setDisable(true);
        buConfirma.setDisable(false);
        buCancela.setDisable(false);
        buApaga.setDisable(true);

//        buMinIns.setDisable(false);
//        buMinEdi.setDisable(false);
//        buMinApa.setDisable(false);
        // HABILITA CAMPOS
        edNome.setEditable(true);
        rbSexF.setDisable(false);
        rbSexM.setDisable(false);
        edMatri.setEditable(true);
        edCepAluno.setEditable(true);
        buImpCepAlu.setDisable(false);
        edEnderecoAluno.setEditable(true);
        edBairroAluno.setEditable(true);
        edCidadeAluno.setEditable(true);
        edFoneRes.setEditable(true);
        edFoneCel.setEditable(true);
        edFoneCom.setEditable(true);
        edEmailAluno.setEditable(true);

        edConjuge.setEditable(true);
        edDependentes.setEditable(true);
        edNaturalidade.setEditable(true);
        dpNasc.setEditable(true);

        edRG.setEditable(true);
        edCPF.setEditable(true);
        edNumDocOficial.setEditable(true);
        edCompProfissao.setEditable(true);
        edCompProfissao2.setEditable(true);

        edEscolaridade.setEditable(true);
        edMedioInst.setEditable(true);
        edMedioCid.setEditable(true);
        edMedioAno.setEditable(true);
        edCursoEscola.setEditable(true);

        edSuperiorInst.setEditable(true);
        edSuperiorCid.setEditable(true);
        edSuperiorAno.setEditable(true);
        edCursoPos.setEditable(true);

        edIgreja.setEditable(true);
        edFoneIgreja.setEditable(true);
        edEmailIgreja.setEditable(true);
        edPastorIgreja.setEditable(true);
        chkEspecial.setDisable(false);
        taObservacoes.setEditable(true);
        taObsSup.setEditable(true);
        taObsEsp.setEditable(true);
    }

    public void desligaEdicao() {
        buRefUFAluno.setDisable(true);
        buRefEst.setDisable(true);
        buRefNac.setDisable(true);
        buRefUFNat.setDisable(true);

        buRefDoc.setDisable(true);
        buRefFor.setDisable(true);
        buRefPro.setDisable(true);
        buRefUFMed.setDisable(true);
        buRefUFSup.setDisable(true);

        buRefDen.setDisable(true);

        buSP1.setDisable(true);
        buSP2.setDisable(true);
        buSP3.setDisable(true);

        // combos
        cbUFAluno.setDisable(true);
        cbEstadoCivil.setDisable(true);
        cbUFNatu.setDisable(true);
        cbNacionalidade.setDisable(true);
        cbMedioUF.setDisable(true);
        cbSuperiorUF.setDisable(true);
        cbDocsOficiais.setDisable(true);
        cbFormacao.setDisable(true);
        cbProfissao.setDisable(true);
        cbProfissao2.setDisable(true);
        cbDenominacao.setDisable(true);
        cbCorPele.setDisable(true);

        // combos
        cbUFAluno.setStyle("-fx-opacity: 1;");
        cbEstadoCivil.setStyle("-fx-opacity: 1;");
        cbUFNatu.setStyle("-fx-opacity: 1;");
        cbMedioUF.setStyle("-fx-opacity: 1;");
        cbSuperiorUF.setStyle("-fx-opacity: 1;");
        cbDenominacao.setStyle("-fx-opacity: 1;");
        cbDocsOficiais.setStyle("-fx-opacity: 1;");
        cbFormacao.setStyle("-fx-opacity: 1;");
        cbProfissao.setStyle("-fx-opacity: 1;");
        cbProfissao2.setStyle("-fx-opacity: 1;");
        cbNacionalidade.setStyle("-fx-opacity: 1;");
        cbCorPele.setStyle("-fx-opacity: 1;");

        // habilita botões para inserção e deleção
        buInsere.setDisable(false);
        buEdita.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buApaga.setDisable(false);

//        buMinIns.setDisable(true);
//        buMinEdi.setDisable(true);
//        buMinApa.setDisable(true);
        // HABILITA CAMPOS
        edNome.setEditable(false);
        rbSexF.setDisable(true);
        rbSexM.setDisable(true);
        edMatri.setEditable(false);
        edCepAluno.setEditable(false);
        buImpCepAlu.setDisable(true);
        edEnderecoAluno.setEditable(false);
        edBairroAluno.setEditable(false);
        edCidadeAluno.setEditable(false);
        edFoneRes.setEditable(false);
        edFoneCel.setEditable(false);
        edFoneCom.setEditable(false);
        edEmailAluno.setEditable(false);

        edConjuge.setEditable(false);
        edDependentes.setEditable(false);
        edNaturalidade.setEditable(false);
        dpNasc.setEditable(false);

        edRG.setEditable(false);
        edCPF.setEditable(false);
        edNumDocOficial.setEditable(false);
        edCompProfissao.setEditable(false);
        edCompProfissao2.setEditable(false);

        edEscolaridade.setEditable(false);
        edMedioInst.setEditable(false);
        edMedioCid.setEditable(false);
        edMedioAno.setEditable(false);
        edCursoEscola.setEditable(false);

        edSuperiorInst.setEditable(false);
        edSuperiorCid.setEditable(false);
        edSuperiorAno.setEditable(false);
        edCursoPos.setEditable(false);

        edIgreja.setEditable(false);
        edFoneIgreja.setEditable(false);
        edEmailIgreja.setEditable(false);
        edPastorIgreja.setEditable(false);
        chkEspecial.setDisable(true);
        taObservacoes.setEditable(false);
        taObsSup.setEditable(false);
        taObsEsp.setEditable(false);
    }

    public void limpaCampos() {
        lblCodAluno.setText(null);
        edNome.setText("");
        rbSexF.setSelected(false);
        rbSexM.setSelected(false);
        edMatri.setText(null);

        lblRegistrosMin.setText(null);
        lblRegistrosPro.setText(null);
        cbUFAluno.setValue(null);
        cbEstadoCivil.setValue(null);
        cbUFNatu.setValue(null);
        cbMedioUF.setValue(null);
        cbSuperiorUF.setValue(null);
        cbDenominacao.setValue(null);
        cbDocsOficiais.setValue(null);
        cbFormacao.setValue(null);
        cbProfissao.setValue(null);
        cbProfissao2.setValue(null);
        cbNacionalidade.setValue(null);
        cbCorPele.setValue(null);

        edCepAluno.setText("");
        edEnderecoAluno.setText("");
        edBairroAluno.setText("");
        edCidadeAluno.setText("");
        edFoneRes.setText("");
        edFoneCel.setText("");
        edFoneCom.setText("");
        edEmailAluno.setText("");

        edConjuge.setText("");
        edDependentes.setText("");
        edNaturalidade.setText("");
        dpNasc.setValue(null);

        edRG.setText("");
        edCPF.setText("");
        edNumDocOficial.setText("");
        edCompProfissao.setText("");
        edCompProfissao2.setText("");

        edEscolaridade.setText("");
        edMedioInst.setText("");
        edMedioCid.setText("");
        edMedioAno.setText("");
        edCursoEscola.setText("");

        edSuperiorInst.setText("");
        edSuperiorCid.setText("");
        edSuperiorAno.setText("");
        edCursoPos.setText("");

        edIgreja.setText("");
        edFoneIgreja.setText("");
        edEmailIgreja.setText("");
        edPastorIgreja.setText("");
        tvProgramas.setItems(null);
        tvMinitry.setItems(null);

        tfStatusDados.setText(null);
        tfDataInc.setText(null);
        tfDataAtu.setText(null);
        chkEspecial.setDisable(false);
        chkEspecial.setSelected(false);
        taObservacoes.setText("");
        taObsSup.setText("");
        taObsEsp.setText("");
    }

    private Boolean verifPodeGravar(String pesq) {
        Boolean podegravar = true;

        jpaCon = new DadocadastrogeralJpaController();
        List lis = jpaCon.getGeralalunoPesqExac(pesq);

        Dadocadastrogeral prog;
        if (editando == true) {

            for (int i = 0; i < lis.size(); i++) {
                prog = (Dadocadastrogeral) lis.get(i);
                if (!prog.getDadoCadastroGeralId().equals(reg_atual.getDadoCadastroGeralId())) {
                    podegravar = false;
                }
            }
        } else {
            if (inserindo == true) {
                if (lis.size() > 0) {
                    podegravar = false;
                }
            }
        }

        return podegravar;
    }

    private String verifMatric(String pesq, String meuNome) {

        String nomeAchado = meuNome;
        jpaCon = new DadocadastrogeralJpaController();
        List lis = jpaCon.getMatriqExac(pesq);

        Dadocadastrogeral prog;

        for (int i = 0; i < lis.size(); i++) {
            prog = (Dadocadastrogeral) lis.get(i);
            if (!prog.getNome().equals(meuNome)) {
                nomeAchado = prog.getNome();
            }
        }
        return nomeAchado;
    }

    private Integer verifNome(String meuNome, Integer meuCod) {

        Integer codAchado = meuCod;
        jpaCon = new DadocadastrogeralJpaController();
        List lis = jpaCon.getGeralalunoPesqExac(meuNome);

        Dadocadastrogeral prog;

        for (int i = 0; i < lis.size(); i++) {
            prog = (Dadocadastrogeral) lis.get(i);
            if (!prog.getDadoCadastroGeralId().equals(meuCod)) {
                codAchado = prog.getDadoCadastroGeralId();
            }
        }
        return codAchado;
    }

    private String verifCpf(String pesq, String meuNome) {

        String nomeAchado = pesq;
        jpaCon = new DadocadastrogeralJpaController();
        List lis = jpaCon.getCpfExac(pesq);

        Dadocadastrogeral prog;

        for (int i = 0; i < lis.size(); i++) {
            prog = (Dadocadastrogeral) lis.get(i);
            if (!prog.getNome().equals(meuNome)) {
                nomeAchado = prog.getNome();
            }
        }
        return nomeAchado;
    }

    private void refreshCombo(ComboBox meuCombo, int qualMetodo) {
        // salva valor atual para recolocá-lo após o refresh
        String valAtual = (String) meuCombo.getSelectionModel().getSelectedItem();

        switch (qualMetodo) {
            case 1: // UF
                meuCombo.setItems(getNomeUF());
                break;
            case 2: // ESTADO CIVIL
                meuCombo.setItems(getNomeEstadoCivil());
                break;
            case 3: // DENOMINACAO
                meuCombo.setItems(getNomeDenominacao());
                break;
            case 4: // DOCS OFICIAIS
                meuCombo.setItems(getNomeDocsOficiais());
                break;
            case 5: // FORMAÇÃO
                meuCombo.setItems(getNomeFormacao());
                break;
            case 6: //NACIONALIDADES
                meuCombo.setItems(getNomeNacionalidades());
                break;
            case 7: //PROFISSÃO
                meuCombo.setItems(getNomeProfissao());
                break;
        }
        meuCombo.getSelectionModel().select(valAtual);

    }

//    private void usaMetodo(ComboBox meuCombo, String nomeMetodo) throws NoSuchMethodException {
//        String valAtual = (String) meuCombo.getSelectionModel().getSelectedItem();
//        Method metodo  = DadosCadastraisController.class.getMethod(nomeMetodo);//, int.class, double.class);
//        ObservableList<String> obl;
//                
//
//        try {
//            obl = (ObservableList<String>) metodo.invoke(null);
//            meuCombo.setItems(obl);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//        meuCombo.getSelectionModel().select(valAtual);
//    }
    @FXML
    private void clicouRefresUFResid(ActionEvent event) throws NoSuchMethodException, NoSuchMethodException {
        refreshCombo(cbUFAluno, 1);
    }

    @FXML
    private void clicouRefreshUFNat(ActionEvent event) {
        refreshCombo(cbUFNatu, 1);
    }

    @FXML
    private void clicouRefreshUFMed(ActionEvent event) {
        refreshCombo(cbMedioUF, 1);
    }

    @FXML
    private void clicouRefreshSup(ActionEvent event) {
        refreshCombo(cbSuperiorUF, 1);
    }

    @FXML
    private void clicouRefreshfEst(ActionEvent event) {
        refreshCombo(cbEstadoCivil, 2);
    }

    @FXML
    private void clicouRefreshDen(ActionEvent event) throws NoSuchMethodException, NoSuchMethodException {
        refreshCombo(cbDenominacao, 3);
    }

    @FXML
    private void clicouRefreshDoc(ActionEvent event) {
        refreshCombo(cbDocsOficiais, 4);
    }

    @FXML
    private void clicouRefreshFor(ActionEvent event) {
        refreshCombo(cbFormacao, 5);
    }

    @FXML
    private void clicouRefreshfNac(ActionEvent event) {
        refreshCombo(cbNacionalidade, 6);
    }

    @FXML
    private void clicouRefreshPro(ActionEvent event) {
        refreshCombo(cbProfissao, 7);
    }

    @FXML
    private void clicouImpCEP(ActionEvent event) {

        if (edCepAluno.getText().trim().length() == 9) {
            String soCEP = edCepAluno.getText().replaceAll("-", "");
            gbCepAluno = new CepLogradouros();
            CepLogradourosJpaController jpaCep = new CepLogradourosJpaController();

            Long qtdCep = jpaCep.qtdCEP(soCEP);

            if (qtdCep > 0) {
                gbCepAluno = jpaCep.getDadosCEP(soCEP);
                if (gbCepAluno != null) {
                    jpaEX = new SiglaestadoJpaController();
                    cbUFAluno.getSelectionModel().select(jpaEX.getUFPesqExtensaoUF(gbCepAluno.getUf()));
                    edCepAluno.setText(gbCepAluno.getCep().substring(0, 5) + "-" + gbCepAluno.getCep().substring(5, 8));
                    edEnderecoAluno.setText(gbCepAluno.getNomeDoTipoLog() + " " + gbCepAluno.getNome() + " ");
                    edBairroAluno.setText(gbCepAluno.getNomeDoBairro());
                    edCidadeAluno.setText(gbCepAluno.getNomeDaCidade());
                    edEnderecoAluno.requestFocus();
                }
            } else {
                mostraMsg("CEP não localizado.", edCepAluno.getText(), 2);
            }
        }
    }

    @FXML
    private void clicouSP1(ActionEvent event) {
        edNaturalidade.setText("São Paulo");
        cbUFNatu.getSelectionModel().select("São Paulo");
    }

    @FXML
    private void clicouSP2(ActionEvent event) {
        edMedioCid.setText("São Paulo");
        cbMedioUF.getSelectionModel().select("São Paulo");
    }

    @FXML
    private void clicouSP3(ActionEvent event) {
        edSuperiorCid.setText("São Paulo");
        cbSuperiorUF.getSelectionModel().select("São Paulo");
    }

    private void chamaTTF2(TextField edTTF, String validos, String mascara) {
        if ((inserindo) || (editando)) {
            if (!edTTF.equals(null)) {
                if (edTTF.getText() != null) {
                    if (edTTF.getText().trim().length() > 0) {
                        if (!edTTF.getText().equals("")) {
                            TextFieldFormatter tff = new TextFieldFormatter();
                            tff.formatter(edTTF, validos, mascara);
                        }
                    }
                }
            }

        }
    }

    @FXML
    private void clicouTTFAnoPadrao(KeyEvent event) {
        chamaTTF2(edAnoPadrao, "0123456789", "####");
    }

    @FXML
    private void clicouTFFcpf(KeyEvent event) {
        chamaTTF2(edCPF, "0123456789", "###.###.###-##");
    }

    @FXML
    private void clicouTFFcep(KeyEvent event) {
        chamaTTF2(edCepAluno, "0123456789", "#####-###");
    }

    @FXML
    private void clicouTFFfoneres(KeyEvent event) {
        chamaTTF2(edFoneRes, "0123456789", "(##)#########");
    }

    @FXML
    private void clicouTFFfonecel(KeyEvent event) {
        chamaTTF2(edFoneCel, "0123456789", "(##)#########");
    }

    @FXML
    private void clicouTFFfonecom(KeyEvent event) {
        chamaTTF2(edFoneCom, "0123456789", "(##)#########");
    }

    @FXML
    private void clicouTFFdepen(KeyEvent event) {
        chamaTTF2(edDependentes, "0123456789", "##");
    }

    @FXML
    private void clicouTFFdnasc(KeyEvent event
    ) {
//        if (((inserindo) || (editando)) && (!edFoneCom.equals(null)) && (edFoneCom.getText().trim().length() > 0)){
//        TextFieldFormatter tff = new TextFieldFormatter();
//        tff.formatter((TextField)dpNasc,"0123456789", "##/##/##");
//        }
    }

    @FXML
    private void clicouTFFescolaridade(KeyEvent event) {
        chamaTTF2(edEscolaridade, "0123", "#");
    }

    @FXML
    private void clicouTFFanoconcescola(KeyEvent event) {
        chamaTTF2(edMedioAno, "0123456789", "####");
    }

    @FXML
    private void clicouTFFanoconcpos(KeyEvent event) {
        chamaTTF2(edSuperiorAno, "0123456789", "####");
    }

    @FXML
    private void clicouTFFfoneigreja(KeyEvent event) {
        chamaTTF2(edFoneIgreja, "0123456789", "(##)#########");
    }

    private String foneMascarado(String fone) {
        if (fone != null) {
            if (fone.trim().length() == 12) {
                String x = fone.trim();
                String y = x.substring(0, 8) + "-" + x.substring(8, 12);
                fone = y;
            } else {
                if (fone.trim().length() == 13) {
                    String x = fone.trim();
                    String y = x.substring(0, 9) + "-" + x.substring(9, 13);
                    fone = y;
                }
            }
        }
        return fone;
    }

    @FXML
    private void clicouInserePro(ActionEvent event) {
        if (reg_atual.getDadoCadastroGeralId() == null) {
            mostraMsg("Aviso", "Por favor, grave os dados do aluno antes de prosseguir", 4);
        } else {
            AbrePrograma(1);
        }
    }

    @FXML
    private void clicouEditaPro(ActionEvent event) {
        if (tvProgramas.getSelectionModel().getSelectedItem() != null) {
            AbrePrograma(2);
        }

    }

    @FXML
    private void clicouRefreshPro2(ActionEvent event) {
        refreshCombo(cbProfissao2, 7);
    }

    @FXML
    private void clicouPegaArqs(ActionEvent event) {
        if (reg_atual.getDadoCadastroGeralId() == null) {
            mostraMsg("Aviso", "Por favor, grave os dados do aluno antes de prosseguir", 4);
        } else {
            // monta array de docs
            List<String> listDoc = cbTipoDoc.getItems();
            String[] docs = new String[listDoc.size()];
            docs = listDoc.toArray(docs);

            try {

                // preenche tvNewDocs
                FileChooser fileChooser = new FileChooser(); //
                File fileIni = new File(gbUser.getPastaIni());
                fileChooser.setInitialDirectory(fileIni);
                listDocs = fileChooser.showOpenMultipleDialog(stgDadosCad);
                String ultimoArq = "";
                for (int i = 0; i < listDocs.size(); i++) {
                    if (ultimoArq.equals(listDocs.get(i).getName())) {
                        continue; // para pular arquivos atalho, que fazem um arquivo duplicar na lista
                    }

                    GridDocs grido = new GridDocs();
                    grido.setLabelDoc(listDocs.get(i).getName());
                    grido.setNomeOri(listDocs.get(i).getAbsolutePath());
                    grido.setNomeArq("");
                    grido.setRemoveDoc(new CheckBox());
                    grido.setTipod(sTipoDocPadrao);
                    grido.setDataInc(new Date());
                    grido.setUserInc(gbUser.getCoduser());
                    grido.setUserIncNome(gbUser.getLogin());
                    grido.setStatus(DA_NOVO);
                    grido.setAnoLetivo(Short.valueOf(edAnoPadrao.getText()));
                    Short semPad = 0;
                    if (rb1Sem.isSelected()) {
                        semPad = 1;
                    } else {
                        semPad = 2;
                    }
                    grido.setSemestreId(semPad);

                    ultimoArq = listDocs.get(i).getName();

                    // VERIFICA SE ARQUIVO JÁ EXISTE E PEDE CONFIRMAÇÃO
                    Boolean jaExiste = false;
                    GridDocs gd = new GridDocs();
                    int valJ = 0;
                    for (int j = 0; j < tvNewDocs.getItems().size(); j++) {
                        gd = tvNewDocs.getItems().get(j);

                        if (gd.getLabelDoc().equals(grido.getLabelDoc())) {
                            jaExiste = true;
                            valJ = j;
                            break;
                        }
                    }

                    //VERIFICA SE O ARQUIVO JÁ EXISTE                
                    if (!jaExiste) {
                        tvNewDocs.getItems().add(grido);
                    } else {

                        // JÁ EXISTE
                        jaExiste = false;

                        // SE FOR NOVO, NÃO DEVERIA EXISTIR, PORTANTO É ERRO!
                        // SE ESTÁ SUBSTITUINDO UM NOVO (QUE AINDA NÃO FOI GRAVADO)
                        if (gd.getStatus() == DA_NOVO) { // SE FOR NOVO
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setHeaderText("O arquivo : " + grido.getLabelDoc() + " já foi selecionado!");
                            alert.setContentText("Deseja substituí-lo?");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get() == ButtonType.OK) {
                                GridDocs newgd = new GridDocs();
                                newgd.setLabelDoc(listDocs.get(i).getName());
                                newgd.setNomeOri(listDocs.get(i).getAbsolutePath());
                                newgd.setRemoveDoc(new CheckBox());
                                newgd.setTipod(sTipoDocPadrao);
                                newgd.setDataInc(gd.getDataInc());
                                newgd.setUserInc(gd.getUserInc());
                                newgd.setUserIncNome(gbUser.getLogin());
                                newgd.setNomeArq("");
                                newgd.setStatus(DA_NOVO);
                                newgd.setAnoLetivo(Short.valueOf(edAnoPadrao.getText()));
                                semPad = 0;
                                if (rb1Sem.isSelected()) {
                                    semPad = 1;
                                } else {
                                    semPad = 2;
                                }
                                newgd.setSemestreId(semPad);

                                // substitui arquivo selecionado
                                tvNewDocs.getItems().set(valJ, newgd); // ATUALIZA AQUI O tvNewDocs
                            }

                        } else {
                            // DA_SALVO! - JÁ EXISTE, ESTÁ SOBREPONDO UM ARQUIVO JÁ GRAVADO
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setHeaderText("O arquivo : " + grido.getLabelDoc() + " já está gravado!");
                            alert.setContentText("Deseja realmente substituí-lo?");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get() == ButtonType.OK) {
                                GridDocs newgd = new GridDocs();
                                newgd.setLabelDoc(listDocs.get(i).getName());
                                newgd.setNomeOri(listDocs.get(i).getAbsolutePath());
                                newgd.setRemoveDoc(new CheckBox());
                                newgd.setTipod(sTipoDocPadrao);
                                newgd.setDataInc(gd.getDataInc());
                                newgd.setUserInc(gd.getUserInc());
                                newgd.setUserIncNome(gd.getUserIncNome());
                                newgd.setDataAlt(new Date());
                                newgd.setUserAlt(gbUser.getCoduser());
                                newgd.setUserAltNome(gbUser.getLogin());
                                newgd.setNomeArq(gd.getNomeArq());
                                newgd.setStatus(DA_ALTERADO);

                                newgd.setAnoLetivo(Short.valueOf(edAnoPadrao.getText()));
                                semPad = 0;
                                if (rb1Sem.isSelected()) {
                                    semPad = 1;
                                } else {
                                    semPad = 2;
                                }
                                newgd.setSemestreId(semPad);

                                // substitui arquivo selecionado
                                tvNewDocs.getItems().set(valJ, newgd); // ATUALIZA AQUI O tvNewDocs
                            }
                        }
                    }
                    //  tvNewDocs.refresh();
                }

            } catch (Exception e) {
                System.out.println("Problemas ao pegar arquivos: " + e);
            }
            lblRegistrosDoc.setText("Registros exibidos: " + tvNewDocs.getItems().size());

        }
    }

    public void initGridDocs() {
        List<String> listDoc = cbTipoDoc.getItems();
        String[] docs = new String[listDoc.size()];
        docs = listDoc.toArray(docs);

        tcTipo.setCellValueFactory(cellData -> cellData.getValue().tipodProperty());
        tcTipo.setCellFactory(ComboBoxTableCell.forTableColumn(docs));
        tcTipo.setMinWidth(200);
        ComboBoxTableCell.forTableColumn(docs);

        tcCoddocaluno.setVisible(false);

        tcRemoveDoc.setCellValueFactory(new PropertyValueFactory<>("removeDoc"));
        tcRemoveDoc.setMinWidth(40);
        tcRemoveDoc.setMaxWidth(40);
        tcLabelDoc.setCellValueFactory(new PropertyValueFactory<>("labelDoc"));
        tcLabelDoc.setMinWidth(300);
        tcNomeArq.setCellValueFactory(new PropertyValueFactory<>("nomeArq"));
        tcNomeArq.setMinWidth(480);
        tcNomeArq.setVisible(false);
        tcNomeOri.setCellValueFactory(new PropertyValueFactory<>("nomeOri"));
        tcNomeOri.setMinWidth(480);
        tcNomeOri.setVisible(false);

        tcAnoLetivo.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tcAnoLetivo.setMinWidth(45);
        tcAnoLetivo.setMaxWidth(45);
        //  tcAnoLetivo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tcSemestreId.setCellValueFactory(new PropertyValueFactory<>("semestreId"));
        tcSemestreId.setMinWidth(35);
        tcSemestreId.setMaxWidth(35);
        //  tcSemestreId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcStatus.setMinWidth(50);

        tcUserInc.setCellValueFactory(new PropertyValueFactory<>("userIncNome"));
        tcUserInc.setMinWidth(50);
        tcDataInc.setCellValueFactory(new PropertyValueFactory<>("dataInc"));
        tcDataInc.setMinWidth(125);

        tcUserAlt.setCellValueFactory(new PropertyValueFactory<>("userAltNome"));
        tcUserAlt.setMinWidth(50);
        tcDataAlt.setCellValueFactory(new PropertyValueFactory<>("dataAlt"));
        tcDataAlt.setMinWidth(125);

        tvNewDocs.getColumns().addAll(tcRemoveDoc, tcTipo, tcLabelDoc, tcAnoLetivo, tcSemestreId, tcStatus, tcUserInc, tcDataInc, tcUserAlt, tcDataAlt, tcNomeArq, tcNomeOri, tcCoddocaluno);
        tvNewDocs.setEditable(true);
        if (reg_atual.getDadoCadastroGeralId() != null) {
            tvNewDocs.setItems(getDocsAluno());
        }
        // tvNewDocs.getItems().size();
        lblRegistrosDoc.setText("Registros exibidos: " + tvNewDocs.getItems().size());
    }

    public ObservableList<GridDocs> getDocsAluno() {
        doclist = FXCollections.observableArrayList();
        if (gbRegCadGer.getDadoCadastroGeralId() > 0) {
            sqlDocsAluno
                    = "select dal.coddocaluno, dal.tipoDoc, dal.labelDoc, dal.nomeori, dal.nomearq, dal.anoLetivo, dal.semestreId, dal.DataInc, dal.CodUserInc, dal.DataAlt, dal.CodUserAlt, din.nomedocinsc, dal.statusDoc "
                    + " from docsalunos dal "
                    + "  LEFT JOIN docinsc din ON dal.tipoDoc = din.coddocinsc "
                    + "  WHERE dal.DadoCadastroGeralId = " + gbRegCadGer.getDadoCadastroGeralId();
            try {
                System.out.println(sqlDocsAluno);
                ResultSet rs = con.createStatement().executeQuery(sqlDocsAluno);
                while (rs.next()) {

                    doclist.add(new GridDocs(rs.getInt("coddocaluno"),
                            rs.getInt("tipoDoc"),
                            rs.getString("nomedocinsc"),
                            rs.getString("labelDoc"),
                            rs.getString("nomeori"),
                            rs.getString("nomearq"),
                            rs.getShort("anoLetivo"),
                            rs.getShort("semestreId"),
                            rs.getTimestamp("DataInc"),
                            rs.getInt("CodUserInc"),
                            rs.getTimestamp("DataAlt"), //getdate
                            rs.getInt("CodUserAlt"),
                            pegaNomeUser(rs.getInt("CodUserInc")),
                            pegaNomeUser(rs.getInt("CodUserAlt")),
                            rs.getShort("statusDoc")
                    ));

                }

            } catch (SQLException ex) {
                Logger.getLogger(DadocadastrogeralController.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 034 - em getDocsAluno()", "" + ex, 2);
            }
        }
        return doclist;
    }

    private String pegaNomeUser(int codUser) {
        TabuserJpaController jpaUs = new TabuserJpaController();
        Tabuser tu = new Tabuser();
        tu = jpaUs.findTabuser(codUser);
        if (tu == null) {
            return null;
        } else {
            return tu.getLogin();
        }
    }

    @FXML
    private void clicouLista(ActionEvent event) {
        String aa = null;
        GridDocs gd = new GridDocs();

        for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
            gd = tvNewDocs.getItems().get(i);
            Boolean resu = (gd.getRemoveDoc().isSelected() ? true : false);
            aa = aa + "\nRemove: " + resu + " Tipo Doc: " + gd.getTipod() + "     Label: " + gd.getLabelDoc() + "     nome ori: "
                    + gd.getNomeOri() + "     nome arq: " + gd.getNomeArq();
        }
        System.out.println(aa);

    }

    @FXML
    private void clicouVer(ActionEvent event) {
        if (tvNewDocs.getSelectionModel().getSelectedItem() != null) {
            GridDocs gd = new GridDocs();
            gd = tvNewDocs.getSelectionModel().getSelectedItem();
            String pasta = "";

            if (gd.getStatus() == DA_SALVO) {
                pasta = gd.getNomeArq();
            } else {
                // o arquivo ou é novo ou foi alterado. Abrir a partir do local de origam
                pasta = gd.getNomeOri();
            }
            try {
                Desktop.getDesktop().open(new File(pasta));
            } catch (Exception e) {
                mostraMsg(pasta, "Erro 028 ao abrir arquivo\n" + e, 2);
            }
        }
    }

    @FXML
    private void clicouRemoveDoc(ActionEvent event) {
        int ctl = 0;
        GridDocs gd = new GridDocs();

        for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
            gd = tvNewDocs.getItems().get(i);
            if (gd.getRemoveDoc().isSelected()) {
                ++ctl;
            }
        }

        if (ctl > 0) {
            String frase = null;
            if (ctl == 1) {
                frase = "Há 1 linha marcada para remoção.";
            } else {
                frase = "Há " + ctl + " linhas marcadas para remoção.";
            }
            // PODE GRAVAR!
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(frase);
            alert.setContentText("Confirma a remoção desta(s) linha(s)?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                int fim = 0;
                int naoPode = 0;
                for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
                    gd = tvNewDocs.getItems().get(i);
                    if (gd.getRemoveDoc().isSelected()) {
                        if ((gd.getNomeArq() != null) && (gd.getNomeArq().trim().length() > 0)) {
                            ++naoPode;
                        } else {
                            tvNewDocs.getItems().remove(i);
                            i--;
                        }
                    }
                }

                if (naoPode > 0) {
                    mostraMsg("Documentos já gravados não podem ser removidos.", "Se for realmente necessário removê-lo(s), por favor, contate a área de TI.", 0);
                }
            }
        }
    }

    @FXML
    private void clicouMarca(ActionEvent event) {
        if (tvNewDocs.getItems().size() > 0) {
            // PODE GRAVAR!
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirma");
            alert.setContentText("Deseja marcar todas as linhas?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                GridDocs gd = new GridDocs();

                for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
                    gd = tvNewDocs.getItems().get(i);
                    CheckBox cb = new CheckBox();
                    cb.setSelected(true);
                    tvNewDocs.getItems().get(i).setRemoveDoc(cb);
                }
                tvNewDocs.refresh();
            }
        }
    }

    @FXML
    private void clicouDesmarca(ActionEvent event) {
        if (tvNewDocs.getItems().size() > 0) {
            // PODE GRAVAR!
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirma");
            alert.setContentText("Deseja desmarcar todas as linhas?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                GridDocs gd = new GridDocs();

                for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
                    gd = tvNewDocs.getItems().get(i);
                    CheckBox cb = new CheckBox();
                    cb.setSelected(false);
                    tvNewDocs.getItems().get(i).setRemoveDoc(cb);
                }
                tvNewDocs.refresh();
            }
        }
    }

    @FXML
    private void clicouConfirmaDoc(ActionEvent event) {
        ChecaErros chkDig = new ChecaErros();
        taConsist.setText("");
        int aGravar = 0;

        GridDocs gd = new GridDocs();

        Boolean haMarcados = false;
        for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
            if (tvNewDocs.getItems().get(i).getRemoveDoc().isSelected()) {
                haMarcados = true;
                break;
            }
        }

        Boolean removerMarcados = false;
        if (haMarcados) {
            // HÁ MARCADOS,PERGUNTA SE PODE REMOVÊ-LOS
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Há documentos MARCADOS para REMOVER");
            alert.setContentText("Deseja REMOVER os documentos MARCADOS ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                int fim = 0;
                for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
                    gd = tvNewDocs.getItems().get(i);
                    if (gd.getRemoveDoc().isSelected()) {
                        tvNewDocs.getItems().remove(i);
                        i--;
                    }
                }
            }
        }

        // VERIFICA SE HÁ ERROS NOS DOCUMENTOS A GRAVAR
        for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
            gd = tvNewDocs.getItems().get(i);

            // SE TIPO DO DOC NÃO ESTIVER PREENCHIDO, REGISTRA ERRO PARA DEPOIS AVISAR
            if ((gd.getTipod() == null) || (gd.getTipod().equals(null)) || (gd.getTipod().trim().length() == 0) || (gd.getTipod().equals(sTipoDocPadrao))) {
                chkDig.setCte(chkDig.getCte() + 1);
                chkDig.setWarn(chkDig.getWarn() + "*** É preciso preencher o <Tipo de Documento> de " + gd.getLabelDoc() + "\n");
            }

            if (gd.getSemestreId() > 2) {
                chkDig.setCte(chkDig.getCte() + 1);
                chkDig.setWarn(chkDig.getWarn() + "*** O campo <Semestre> não pode ser maior que 2 -> " + gd.getLabelDoc() + "\n");
            }

            if (gd.getRemoveDoc().isSelected()) {
                chkDig.setCte(chkDig.getCte() + 1);
                chkDig.setWarn(chkDig.getWarn() + "*** O campo <Remove> marcado! " + gd.getLabelDoc() + "\n");
            }
        }

        if (chkDig.getCte() > 0) {
            //  taConsist.setText("ATENÇÃO! \n" + "HÁ " + chkDig.getCte() + " ERRO(S). \n");
            mostraMsgWait("ATENÇÃO! \n" + "HÁ " + chkDig.getCte() + " ERRO(S). \n", chkDig.getWarn(), 2);
        } else {

            // SEM ERROS, PODE GRAVAR
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirma");
            alert.setContentText("Confirma a gravação destes documentos digitalizados?");
            Optional<ButtonType> option = alert.showAndWait();
            int err = 0;

            if (option.get() == ButtonType.OK) {
                String pastaAluno = resolvePastaAluno(); //obtém ou cria a pasta
                if (pastaAluno != null) {

                    // PASTA OK!
                    // VERIFICA SE ESTE DOCUMENTO JÁ ESTÁ GRAVADO. 
                    // SE ESTIVER, A PASTA SERÁ A MESMA, AINDA QUE O NOME DO ALUNO TENHA SIDO ALTERADO
                    // loop para gravar digitalizados na pasta e na tabela docalunos
                    for (int i = 0; i < tvNewDocs.getItems().size(); i++) {
                        gd = tvNewDocs.getItems().get(i);
                        DocsalunosJpaController jpaDA = new DocsalunosJpaController();
                        Docsalunos dal = new Docsalunos();
                        DocinscJpaController jpDocIns = new DocinscJpaController();
                        Short di = jpDocIns.getNumTipoDoc(gd.getTipod());
                        dal.setDadoCadastroGeralId(reg_atual.getDadoCadastroGeralId());
                        dal.setTipoDoc(di);
                        dal.setLabelDoc(gd.getLabelDoc());
                        dal.setNomeOri(gd.getNomeOri());
                        dal.setNomeArq(pastaAluno + "\\" + gd.getLabelDoc());
                        dal.setAnoLetivo(gd.getAnoLetivo());
                        dal.setSemestreId(gd.getSemestreId());

                        if (gd.getCodDocAluno() == 0) { // NÃO EXISTE - INSERIR

                            dal.setStatusDoc(DA_SALVO);
                            dal.setDataInc(new Date());
                            dal.setCodUserInc(gbUser.getCoduser());

                            try {
                                jpaDA.create(dal);

                                // COPIA ARQUIVO PARA ESPHOTO
                                copiaArq(dal.getNomeOri(), dal.getNomeArq());

                                // GRAVA ARQUIVO E, ESPHOTO
                            } catch (Exception e) {
                                ++err;
                                mostraMsg("Erro 033n - Erro ao inserir arquivo digitalizado", "" + e, 2);
                            }

                        } else {

                            dal.setStatusDoc(DA_SALVO);
                            dal.setDataInc(new Date());
                            dal.setCodUserInc(gbUser.getCoduser());

                            Docsalunos exist = jpaDA.findDocsalunos(gd.getCodDocAluno());

                            // SE O ATUAL ESTIVER DIFERENTE DO EXISTENTE, ATUALIZA
                            if (dal.getTipoDoc() != exist.getTipoDoc() || dal.getLabelDoc() != exist.getLabelDoc()
                                    || dal.getNomeOri() != exist.getNomeOri() || dal.getNomeArq() != exist.getNomeArq()) {

                                // É DIFERENTE. ATUALIZA
                                exist.setTipoDoc(dal.getTipoDoc());
                                exist.setLabelDoc(exist.getLabelDoc());
                                exist.setNomeOri(dal.getNomeOri());
                                exist.setNomeArq(dal.getNomeArq());
                                exist.setDataAlt(new Date());
                                exist.setCodUserAlt(gbUser.getCoduser());
                                exist.setStatusDoc(DA_SALVO);

                                try {
                                    jpaDA.edit(exist);
                                    // COPIA ARQUIVO ATUAL SOBRE O EXISTENTE EM ESPHOTO
                                    copiaArq(dal.getNomeOri(), dal.getNomeArq());
                                } catch (Exception e) {
                                    mostraMsg("Erro 032 - Erro ao atualizar arquivo digitalizado " + dal.getLabelDoc() + ".", "" + e, 2);
                                }
                            } else {
                                mostraMsg("Erro 037 - Erro ao localizar arquivo digitalizado " + dal.getLabelDoc() + ".", "", 2);
                            }
                        }
                    }
                    tvNewDocs.setItems(getDocsAluno());
                    tvNewDocs.getItems().size();
                    lblRegistrosDoc.setText("Registros exibidos: " + tvNewDocs.getItems().size());

                    int grav = tvNewDocs.getItems().size() - err;
                    String mes = "";
                    if (grav > 1) {
                        mes = "Foram gravados/atualizados " + grav + " arquivos com sucesso";
                        mostraMsg("Gravação", mes, 0);
                    } else {
                        if (grav == 1) {
                            mes = "Foi gravado/atualizado 1 arquivo com sucesso";
                            mostraMsg("Gravação", mes, 0);
                        }
                    }
                    if (err > 0) {
                        mostraMsg("Erro na gravação", "Houve " + err + " erro(s) de gravação.", 2);
                    }
                }
            }
        }

    }

    private String resolvePastaAluno() {
        String pa = "";
        GridDocs gd2 = new GridDocs();
        String aspa = "\\";
        Boolean pastaExiste = false;
        File file;

        for (int i = 0; i < tvNewDocs.getItems().size(); i++) {

            gd2 = tvNewDocs.getItems().get(i);
            if (((gd2.getNomeArq() != null)) && (gd2.getNomeArq().trim().length() != 0)) {
                file = new File(gd2.getNomeArq());
                if (file.exists()) {
                    pastaExiste = true;
                    int pos = gd2.getNomeArq().lastIndexOf(aspa);
                    pa = gd2.getNomeArq().substring(0, pos);
                    break;
                }
            }
        }

        if (!pastaExiste) {
            if ((edNome.getText().equals(null)) || (edNome.getText().trim().length() == 0)) {
                mostraMsg("Não é possível gravar os arquivos com o campo <nome do aluno> vazio!", "Preencha o campo <nome do aluno>", 2);
                return null;
            } else {
                try {
                    pa = secretCase + "/" + reg_atual.getNome() + "_" + reg_atual.getDadoCadastroGeralId();
                    file = new File(pa);
                    file.mkdir();
                    return pa;
                } catch (Exception e) {
                    mostraMsg("Erro 030 - Não foi possível criar a pasta! --> ", "" + e, 2);
                }
                return null;
            }

        } else {
            return pa;
        }
    }

    private Boolean copiaArq(String de, String para) {
        Path sourceDirectory = Paths.get(de);
        Path targetDirectory = Paths.get(para);

        try {
            Files.copy(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            mostraMsg("Erro 033 ao copiar arquivo --> de " + de + "\npara: " + para, "\n"
                    + "Origem: " + sourceDirectory + "\n"
                    + "Destino: " + targetDirectory + "\n"
                    + e, 2);
            return false;
        }
    }

    @FXML
    private void ClicouInsFoto(ActionEvent event) throws IOException {

        // abre Dialog para pegar arquivo da foto
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.jpeg", "*.png", "*.bmp"));

        File fileIni = new File(gbUser.getPastaFotos());
        fileChooser.setInitialDirectory(fileIni);
        File fotoSelec = fileChooser.showOpenDialog(stgDadosCad);

        // SE SELECIONOU UMA FOTO, PROCESSA
        if (fotoSelec != null) {

            byte[] imageData = new byte[(int) fotoSelec.length()];

            try {
                FileInputStream fileInputStream = new FileInputStream(fotoSelec);
                fileInputStream.read(imageData);
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // INSERE A FOTO NO BD
            jpaFotos = new EsFotosalunosJpaController();
            reg_foto.setDadoCadastroGeralId(Integer.valueOf(reg_atual.getDadoCadastroGeralId()));
            reg_foto.setFotoAluno(imageData);
            reg_foto.setDataInc(new Date());
            reg_foto.setCodUserInc(gbUser.getCoduser());

            Boolean ins_alt = false;
            // SE NÃO EXISTE, GRAVA, SE EXISTE, ALTERA
            try {
                EsFotosalunos reg_tem = new EsFotosalunos();
                reg_tem = obtemFoto(String.valueOf(reg_atual.getDadoCadastroGeralId()));
                if (reg_tem.getCodFotoAluno() != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Confirmação de alteração de foto do aluno.");
                    alert.setContentText("Este aluno já tem uma foto! Confirma a atualização?");
                    Optional<ButtonType> option = alert.showAndWait();
                    reg_foto.setCodFotoAluno(reg_tem.getCodFotoAluno());
                    reg_foto.setDataAlt(new Date());
                    reg_foto.setCodUserAlt(gbUser.getCoduser());
                    if (option.get() == ButtonType.OK) {
                        jpaFotos.edit(reg_foto); // até aqui rodou 
                        ins_alt = true;
                        mostraMsg("Foto atualizada com sucesso.", "", 0);
                    }

                } else {
                    ins_alt = true;
                    jpaFotos.create(reg_foto); // até aqui rodou 
                }
            } catch (Exception e) {
                mostraMsg("Erro 51a ao atualizar foto " + e, "", 0);
            }

            // ■■■■■■■■■> precisa pergutnar se já existe, se confirma etc ■■■■■■■■■■■
            // CARREGA FOTO PARA IMAGEVIEW
            if (ins_alt) {
                //
                File file = new File("C:\\EvedSys\\temp");
                boolean dirCreated = file.mkdir();

                String nomeArq = file + "\\photo" + reg_foto.getDadoCadastroGeralId() + ".jpg";
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

                // apaga arquivo temporário da foto
                try {

                    File f = new File(nomeArq);           //file to be delete  
                    if (!f.delete()) //returns Boolean value  
                    {
                        // System.out.println("foto temporária apagada com sucesso.");   //getting and printing the file name  
                    } else {
                        System.out.println("erro ao tentar apagar foto temporária.");   //getting and printing the file name  
                    }
                } catch (Exception e) {
                    System.out.println("erro ao tentar apagar foto temporária " + e);
                }
            }
        }

    }

    private void leFoto(Integer codAluno) {
        // CARREGA FOTO PARA IMAGEVIEW        
        String nomeArq = "photo" + codAluno + ".jpg";

        File file = new File("C:/EvedSys/temp");
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
//            try {
//                
//                File f = new File(nomeArq);           //file to be delete  
//                if (f.delete()) //returns Boolean value  
//                {
//                    //   System.out.println("foto temporária apagada com sucesso.");   //getting and printing the file name  
//                } else {
//                    System.out.println("erro ao tentar apagar foto temporária.");   //getting and printing the file name  
//                }
//            } catch (Exception e) {
//                System.out.println("erro ao tentar apagar foto temporária " + e);
//            }
        }

    }

    @FXML
    private void clicouVeFoto(ActionEvent event) {
        gbFotoMaior = new FotoMaior(reg_atual.getNome(), ivFotoAluno.getImage());

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FotoMaior.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);

            stage.showAndWait();
            initColsMin();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 004x ao carregar FotoMaior", "" + ex, 0);
        }
    }

    @FXML
    private void clicouApagaFoto(ActionEvent event) throws Exception {
        if (reg_foto.getCodFotoAluno() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Exclusão de foto do aluno.");
            alert.setContentText("Deseja realmente remover a foto deste aluno?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                reg_foto.setFotoAluno(null);
                jpaFotos.edit(reg_foto); // até aqui rodou 
                ivFotoAluno.setImage(null);
                mostraMsg("Foto excluída com sucesso.", "", 0);
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

    private void clicouButton(ActionEvent event) {
        try {

            // preenche tvNewDocs
            FileChooser fileChooser = new FileChooser(); //
            File fileIni = new File(gbUser.getPastaIni());
            fileChooser.setInitialDirectory(fileIni);
            listDocs = fileChooser.showOpenMultipleDialog(stgDadosCad);
            String ultimoArq = "";
        } catch (Exception e) {
            System.out.println("Problemas ao pegar arquivos: " + e);
        }

    }

    private void iniMatriSem() {
        dadosMS = FXCollections.observableArrayList();
        if (tvProgramas.getSelectionModel().getSelectedItem() != null) {

            String sqlMatriS = SQLMATRISEM
                    + " WHERE mse.DadoCadastroGeralId = " + lblCodAluno.getText() + " "
                    + "  AND mse.DadoCadastroProgramaId = " + tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId() + " "
                    + "  ORDER BY mse.AnoLetivo desc, mse.SemestreId desc";

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

    private void clicouAbreMatriSem(ActionEvent event) {

//        // VERIFICA SE MATRICULA SEMESTRAL JÁ EXISTE NA GRADE
//        // SE JÁ EXISTE, EDITA, SENÃO, CRIA
//        Integer leAno = 0;
//        if (isInteger(edLeAno)) {
//            leAno = Integer.valueOf(edLeAno.getText());
//        }
//
//        // edSuperiorInst.setText((reg_atual.getEscolaPosGrad() != null) ? reg_atual.getEscolaPosGrad() : null);
//        Integer leSem = (rbLeSem1.isSelected() ? 1 : 2);
//
//        Boolean temMatriem = false;
//        for (int i = 0; i < dadosMS.size(); i++) {
//            if (leAno == dadosMS.get(i).getAnoLetivo() && leSem == dadosMS.get(i).getSemestre()) {
//                temMatriem = true;
//                break;
//            }
//        }
//
//        if (!temMatriem) {
//            // TENTOU ABRIR MATRÍULA COM SEMESTRE NÃO CADASTRADO PARA ESTE ALUNO
//            if ((leAno > Integer.valueOf(gbUltSem.getUltAno()))
//                    || (leAno == Integer.valueOf(gbUltSem.getUltAno()) && leSem > Integer.valueOf(gbUltSem.getUltSem()))) {
//                mostraMsg("Não é possível criar a matrícula!", "O semestre selecionado ainda não foi aberto (Ano: " + leAno + ", " + leSem + "º semestre).", 2);
//            } else {
//
//                if ((leAno < Integer.valueOf(gbUltSem.getUltAno()))
//                        || (leAno == Integer.valueOf(gbUltSem.getUltAno()) && leSem < Integer.valueOf(gbUltSem.getUltSem()))) {
//                    // leAno e leSem já estão certos
//                } else {
//                    // ASSUMIR ÚLTIMO SEMESTRE ABERTO
//                    leAno = Integer.valueOf(gbUltSem.getUltAno());
//                    leSem = Integer.valueOf(gbUltSem.getUltSem());
//                }
//
//                // PODE GRAVAR!
//                Alert alert = new Alert(AlertType.CONFIRMATION);
//                alert.setHeaderText("Confirma criar a matrícula?\n");
//                alert.setContentText("Ano: " + leAno + ", " + leSem + "º semestre\n\n"
//                        + "Aluno: " + reg_atual.getNome() + "\n\n" + "Programa: " + tvProgramas.getSelectionModel().getSelectedItem().getCurso());
//                Optional<ButtonType> option = alert.showAndWait();
//
//                if (option.get() == ButtonType.OK) {
//                    // ABRA MATRÍCULA PARA INSERÇÃO --modoMatri = true; (default
//                    gbRegMatriSemCabec = new MatriSemCabec();
//                    gbRegMatriSemCabec.setModoMatri(true);
//                    gbRegMatriSemCabec.setAnoLetivo(leAno);
//                    gbRegMatriSemCabec.setSemestre(leSem);
//                    gbRegMatriSemCabec.setNomeAluno(reg_atual.getNome());
//                    gbRegMatriSemCabec.setNomePrograma(tvProgramas.getSelectionModel().getSelectedItem().getCurso());
//                    gbRegMatriSemCabec.setDataMatricula(new Date());
//
//                    gbRegMatriSemCabec.setCodAluno(reg_atual.getDadoCadastroGeralId());
//                    gbRegMatriSemCabec.setCodPrograma(tvProgramas.getSelectionModel().getSelectedItem().getCursoId());
//                    Short dez = 10;
//                    gbRegMatriSemCabec.setSituacaoMatri(dez); // PENDENTE
//                    abreMatriSem();
//                }
//            }
//        } else {
//            // SEMESTRE EXUSTE EM MATRISEM - EDITAR
//            int lSem = rbLeSem1.isSelected() ? 1 : 2;
//            Alert alert = new Alert(AlertType.CONFIRMATION);
//            alert.setHeaderText("Confirma editar a matrícula?\n");
//            alert.setContentText("Ano: " + edLeAno.getText() + ", " + lSem + "º semestre\n\n"
//                    + "Aluno: " + reg_atual.getNome() + "\n\n" + "Programa: " + tvProgramas.getSelectionModel().getSelectedItem().getCurso());
//            Optional<ButtonType> option = alert.showAndWait();
//
//            if (option.get() == ButtonType.OK) {
//                gbRegMatriSemCabec = new MatriSemCabec();
//                gbRegMatriSemCabec.setModoMatri(false);
//                EsMatrisem ems = jpaMS.findEsMatrisem(tvMatriSemestre.getSelectionModel().getSelectedItem().getCms());
//                gbRegMatriSemCabec.setCodMatriSem(ems.getCodMatriSem());
//                gbRegMatriSemCabec.setAnoLetivo(ems.getAnoLetivo());
//                gbRegMatriSemCabec.setSemestre(ems.getSemestreId());
//                gbRegMatriSemCabec.setCodAluno(ems.getDadoCadastroGeralId());
//                gbRegMatriSemCabec.setCodPrograma(ems.getDadoCadastroProgramaId());
//                gbRegMatriSemCabec.setNomeAluno(reg_atual.getNome());
//                gbRegMatriSemCabec.setNomePrograma(tvProgramas.getSelectionModel().getSelectedItem().getCurso());
//                gbRegMatriSemCabec.setDataMatricula(ems.getDataMatri());
//                gbRegMatriSemCabec.setDataSecretaria1(ems.getDataSecretaria1());
//                gbRegMatriSemCabec.setDataSecretaria2(ems.getDataSecretaria2());
//                gbRegMatriSemCabec.setDataFinanceiro(ems.getDataFinanceiro());
//                gbRegMatriSemCabec.setDataCoordenacao(ems.getDataCoordenacao());
//                abreMatriSem();
//            }
//        }
    }

    private void inicPnGrav() {
        if (reg_atual.getDadoCadastroGeralId() != null) {
            jpaPup = new CadastroalunodisciplinaJpaController();
            Long numGrav = jpaPup.contaGravacoes(reg_atual.getDadoCadastroGeralId());
            if (numGrav == 1) {
                lblGrav.setText("O aluno adquiriu 1 gravação");
            } else {
                if (numGrav > 1) {
                    lblGrav.setText("O aluno adquiriu " + numGrav + " gravações");
                }
            }
            pnGrav.setVisible(numGrav > 0);
        }
    }

    @FXML
    private void clicourefreshTvProgramas(ActionEvent event) {
        inicPnGrav();
        initColsPro();
        initRegDis();
        iniMatriSem();
    }

    @FXML
    private void clicouAbreMS(ActionEvent event) {
        if (tvProgramas.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Selecione o programa desta matrícula semestral ou insira um programa, se necessário.", "", 2);
        } else {
            // SÓ DEIXA INSERIR SE SITUAÇÃO DO PROGRAMA FOR
            // ATIVO OU 
            String situ = tvProgramas.getSelectionModel().getSelectedItem().getSituacao();
            if (situ.equals("Ativo") || situ.equals("Cadastrando") || situ.equals("Pendente")) {
                chamaCrudMS();
            } else {
                mostraMsg("Não é possível efetuar a matrícula para este aluno!", "Só é permitido inserir matrícula se <Situação> for ATIVO, CADASTRANDO ou PENDENTE.", 2);
            }
        }

    }

    private EsMatrisem temMatrisem(Short leAno, Short leSem, int codPrograma) {

        List lis = jpaMS.getListaMS(leAno, leSem, codPrograma);
        if (lis.size() > 0) {
            return (EsMatrisem) lis.get(0);
        } else {
            return null;
        }
    }

    private void chamaCrudMS() {
        Short leAno = Short.valueOf(cbGrade.getSelectionModel().getSelectedItem().substring(0, 4));
        Short leSem = leSem = Short.valueOf(cbGrade.getSelectionModel().getSelectedItem().substring(5, 6));

        if (reg_atual.getEspecial() != null) {
            gbEspecial = (reg_atual.getEspecial());
        } else {
            gbEspecial = false;
        }

        // SE NÃO EXISTE, INSERE, SE EXISTE, EDITA
        EsMatrisem emsP = temMatrisem(leAno, leSem, tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId());
        if (emsP == null) {
            gbTipoInsEdiMS = 1;
        } else {
            gbTipoInsEdiMS = 2;
        }
        //gbTipoInsEdiMS = temMatrisem(leAno, leSem, tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId()); // 1 = não tem, insere; 2 = tem, edita

        if (gbTipoInsEdiMS == 1) { // INSERINDO 

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirma criar a matrícula?\n");
            alert.setContentText("Ano: " + leAno + ", " + leSem + "º semestre\n\n"
                    + "Aluno: " + reg_atual.getNome() + "\n\n" + "Programa: " + tvProgramas.getSelectionModel().getSelectedItem().getCurso());
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                // ABRA MATRÍCULA PARA INSERÇÃO --modoMatri = true; (default)
                gbRegMatriSemCabec = new MatriSemCabec();
                gbRegMatriSemCabec.setModoMatri(true);
                gbRegMatriSemCabec.setAnoLetivo(leAno);
                gbRegMatriSemCabec.setSemestre(leSem);
                gbRegMatriSemCabec.setCodGrade(gbUltSem.getCodGrade());
                gbRegMatriSemCabec.setNomeAluno(reg_atual.getNome());
                gbRegMatriSemCabec.setNomePrograma(tvProgramas.getSelectionModel().getSelectedItem().getCurso());
                gbRegMatriSemCabec.setDataMatricula(new Date());

                gbRegMatriSemCabec.setCodAluno(reg_atual.getDadoCadastroGeralId());
                if (reg_atual.getEspecial() != null) {
                    gbEspecial = (reg_atual.getEspecial());
                } else {
                    gbEspecial = false;
                }
                gbRegMatriSemCabec.setCodPrograma(tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId());
                Short dez = 10;
                gbRegMatriSemCabec.setSituacaoMatri(dez); // PENDENTE

                // grava registro para gerar codmatrisem
                ems = new EsMatrisem();
                ems.setDadoCadastroProgramaId(tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId());
                ems.setDadoCadastroGeralId(reg_atual.getDadoCadastroGeralId());
                ems.setAnoLetivo(leAno);
                ems.setSemestreId(leSem);
                ems.setDataMatri(new Date());

                EsGrade gra = jpaGra.getCodGradeExac(leAno, leSem);
                //  gbRegMatriSemCabec.setCodGrade(gra.getCodGrade());

                ems.setCodGrade(gra.getCodGrade());
                ems.setDataInc(new Date());
                ems.setCodUserInc(gbUser.getCoduser());

                jpaMS.create(ems);
                gbRegMatriSemCabec.setNomeMatriSem(ems);
                gbRegMatriSemCabec.setCodGrade(ems.getCodGrade());
                fill_gbRegMatriSemCabec();
                loadCrudMS();
            }
        } else {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirma editar a matrícula?\n");
            alert.setContentText("Ano: " + leAno + ", " + leSem + "º semestre\n\n"
                    + "Aluno: " + reg_atual.getNome() + "\n\n" + "Programa: " + tvProgramas.getSelectionModel().getSelectedItem().getCurso());
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                gbRegMatriSemCabec = new MatriSemCabec();
                gbRegMatriSemCabec.setModoMatri(false);
                ems = emsP;
                //ems = jpaMS.findEsMatrisem(tvMatriSemestre.getSelectionModel().getSelectedItem().getCms());

                EsGrade gra = jpaGra.getCodGradeExac(leAno, leSem);
                gbRegMatriSemCabec.setCodGrade(gra.getCodGrade());

                fill_gbRegMatriSemCabec();
                loadCrudMS();

            }

        }

    }

    private void fill_gbRegMatriSemCabec() {
        gbRegMatriSemCabec.setNomeMatriSem(ems);
        gbRegMatriSemCabec.setAnoLetivo(ems.getAnoLetivo());
        gbRegMatriSemCabec.setSemestre(ems.getSemestreId());
        gbRegMatriSemCabec.setCodAluno(ems.getDadoCadastroGeralId());
        gbRegMatriSemCabec.setCodPrograma(ems.getDadoCadastroProgramaId());
        gbRegMatriSemCabec.setNomeAluno(reg_atual.getNome());
        gbRegMatriSemCabec.setNomePrograma(tvProgramas.getSelectionModel().getSelectedItem().getCurso());
        gbRegMatriSemCabec.setDataMatricula(ems.getDataMatri());
        gbRegMatriSemCabec.setDataSecretaria1(ems.getDataSecretaria1());
        gbRegMatriSemCabec.setDataSecretaria2(ems.getDataSecretaria2());
        gbRegMatriSemCabec.setDataFinanceiro(ems.getDataFinanceiro());
        gbRegMatriSemCabec.setDataCoordenacao(ems.getDataCoordenacao());
        gbRegMatriSemCabec.setObsMatri(ems.getObsMatri());
    }

    private void loadCrudMS() {
        //   gbCodGrade = 2; // DEPOIS CONSERTAMOS
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MatriSemestral.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Matrícula Semestra de: " + edNome.getText());
            stage.setResizable(true);
            stage.setScene(scene);

            stage.showAndWait();
            initColsMin();

        } catch (IOException ex) {
            Logger.getLogger(DadosCadastraisController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 004y ao carregar chamaCrudMS", "" + ex, 0);
        }
    }

    @FXML
    private void clicouSex(MouseEvent event) {
    }

    @FXML
    private void clicouEspecial(ActionEvent event) {
        checaEspecial();
    }

    private void checaEspecial() {
        if (chkEspecial.isSelected()) {
            lineRed.setVisible(true);
        } else {
            lineRed.setVisible(false);
        }
    }
//
//    private void clibouBut(ActionEvent event) {
//
////        String tes = edDependentes.getText();
////        int b = Integer.valueOf(tes);
////        
////        Short a = Short.parseShort(b);
////        System.out.println(a);
//        String string_to_Short = "1234";
//
//        short shrt = Short.parseShort(string_to_Short);
//
//        System.out.println("Type cast string to short :" + shrt);
//
//        String tes = edDependentes.getText().trim();
//        System.out.println(tes);
//
//        short shrt2 = Short.parseShort(tes);
//
//        System.out.println(" ***********************ype cast string to short :" + shrt2);
//
//    }

    @FXML
    private void clicouEdNotas(ActionEvent event) {
        if (tvRegDis.getSelectionModel().getSelectedItem() != null) {
            ObservableList<DisciProgAluno> disProList = FXCollections.observableArrayList();
            int cadi = tvRegDis.getSelectionModel().getSelectedItem().getCadastroAlunoDisciplinaId();
            String sqlAtuNotas
                    = "  SELECT pup.CadastroAlunoDisciplinaId, cg.DadoCadastroGeralId, cg.nome as nomeAluno, c.curso, cad.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao,  "
                    + " fr.frequencia, cad.Credito, cad.CargaHoraria, cad.AnoLetivo, cad.SemestreId , tn.Turno, tm.turma, cad.Horario, cad.Localidade, pup.tipoRegOuv  "
                    + " FROM cadastroalunodisciplina pup  "
                    + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
                    + " LEFT JOIN dadocadastrogeral cg ON dcp.DadoCadastroGeralId = cg.DadoCadastroGeralId  "
                    + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId  "
                    + " LEFT JOIN funcionario fu ON fu.FuncionarioId = cad.FuncionarioId  "
                    + " LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id  "
                    + " LEFT JOIN curso c ON dcp.CursoId = c.CursoId  "
                    + " LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  "
                    + " LEFT JOIN turno tn ON cad.TurnoId = tn.TurnoId   "
                    + " LEFT JOIN turma tm ON cad.TurmaId = tm.TurmaId "
                    + " WHERE pup.CadastroAlunoDisciplinaId = " + cadi;

            try {
                System.out.println(sqlAtuNotas);

                ResultSet rs = con.createStatement().executeQuery(sqlAtuNotas);
                while (rs.next()) {
                    gbAtuNotas = new Atu_Notas();
                    gbAtuNotas.setCadastroAlunoDisciplinaId(rs.getInt("CadastroAlunoDisciplinaId"));
                    gbAtuNotas.setDadoCadastroGeralId(rs.getInt("cg.DadoCadastroGeralId"));
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
                    gbAtuNotas.setTipoRegOuv((rs.getString("tipoRegOuv")));

                }

            } catch (SQLException ex) {
                Logger.getLogger(CadProg2Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018 - em initColsPro()", "" + ex, 2);
            }

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Atualiza_Notas.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("Situação do Aluno na Disciplina");
                stage.setScene(scene);

                stage.showAndWait();
                initRegDis();

            } catch (IOException ex) {
                Logger.getLogger(DadosCadastraisController.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 004df ao carregar AtuNotas", "" + ex, 0);
            }
        }
    }

    private void clicouInsDisp(ActionEvent event) {
        if (tvProgramas.getSelectionModel().getSelectedItem() != null) {

//            String situ = tvProgramas.getSelectionModel().getSelectedItem().getSituacao();
//            if (situ.equals("Formado") || situ.equals("Cadastrando") || situ.equals("Pendente")) {
//                chamaCrudMS();
//            } else {
//                mostraMsg("Não é possível efetuar a matrícula para este aluno!", "Só é permitido inserir matrícula se <Situação> for ATIVO, CADASTRANDO ou PENDENTE.", 2);
//            }
            // CARREGA VARIAVEI GLOBAL DE DISPENSA
            if (gbRegCadGer.getDadoCadastroGeralId() == null) {
                mostraMsg("Grave os dados aluno antes de prsseguir", "", 2);
            } else {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Confirma inserir Dispensa de Disciplina");
                alert.setContentText("para este aluno no programa " + tvProgramas.getSelectionModel().getSelectedItem().getCurso() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {
                    gbDispensa = new DispensaGB();
                    gbDispensa.setModoDis(1);
                    gbDispensa.setCodAluno(gbRegCadGer.getDadoCadastroGeralId());
                    gbDispensa.setCodPrograma(tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId());
                    gbDispensa.setNomeALuno(edNome.getText());
                    gbDispensa.setNomePrograma(tvProgramas.getSelectionModel().getSelectedItem().getCurso());
                    chamaTela("/view/Dispensa.fxml", "Dispensa de Disciplina");
                }
            }

        }
    }

    private void clicouEditaDisp(ActionEvent event) {
        if (tvRegDis.getSelectionModel().getSelectedItem() != null) {
            ObservableList<DisciProgAluno> disProList = FXCollections.observableArrayList();
            int cadi = tvRegDis.getSelectionModel().getSelectedItem().getCadastroAlunoDisciplinaId();
            String sqlAtuNotas
                    = "  SELECT pup.CadastroAlunoDisciplinaId, cg.DadoCadastroGeralId, cg.nome as nomeAluno, c.curso, cad.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao,  "
                    + " fr.frequencia, cad.Credito, cad.CargaHoraria, cad.AnoLetivo, cad.SemestreId , tn.Turno, tm.turma, cad.Horario, cad.Localidade, pup.tipoRegOuv  "
                    + " FROM cadastroalunodisciplina pup  "
                    + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
                    + " LEFT JOIN dadocadastrogeral cg ON dcp.DadoCadastroGeralId = cg.DadoCadastroGeralId  "
                    + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId  "
                    + " LEFT JOIN funcionario fu ON fu.FuncionarioId = cad.FuncionarioId  "
                    + " LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id  "
                    + " LEFT JOIN curso c ON dcp.CursoId = c.CursoId  "
                    + " LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  "
                    + " LEFT JOIN turno tn ON cad.TurnoId = tn.TurnoId   "
                    + " LEFT JOIN turma tm ON cad.TurmaId = tm.TurmaId "
                    + " WHERE pup.CadastroAlunoDisciplinaId = " + cadi;

            try {
                System.out.println(sqlAtuNotas);

                ResultSet rs = con.createStatement().executeQuery(sqlAtuNotas);
                while (rs.next()) {
                    gbAtuNotas = new Atu_Notas();
                    gbAtuNotas.setCadastroAlunoDisciplinaId(rs.getInt("CadastroAlunoDisciplinaId"));
                    gbAtuNotas.setDadoCadastroGeralId(rs.getInt("cg.DadoCadastroGeralId"));
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
                    gbAtuNotas.setTipoRegOuv((rs.getString("tipoRegOuv")));

                }

            } catch (SQLException ex) {
                Logger.getLogger(CadProg2Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018 - em initColsPro()", "" + ex, 2);
            }

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Atualiza_Notas.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setTitle("Situação do Aluno na Disciplina");
                stage.setScene(scene);

                stage.showAndWait();
                initRegDis();

            } catch (IOException ex) {
                Logger.getLogger(DadosCadastraisController.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 004df ao carregar AtuNotas", "" + ex, 0);
            }
        }
    }

    @FXML
    private void clicouInsDispExt(ActionEvent event) {
        if (tvProgramas.getSelectionModel().getSelectedItem() != null) {

            // CARREGA VARIAVEI GLOBAL DE EQUIVALENCIA
            if (gbRegCadGer.getDadoCadastroGeralId() == null) {
                mostraMsg("Grave os dados aluno antes de prsseguir", "", 2);
            } else {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Confirma inserir Dispensa (externa) de Disciplina");
                alert.setContentText("para este aluno no programa " + tvProgramas.getSelectionModel().getSelectedItem().getCurso() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {
                    try {
                        gbEquivalencia = new EquivalenciaGB();
                        gbEquivalencia.setModoDis(1);
                        gbEquivalencia.setCodAluno(gbRegCadGer.getDadoCadastroGeralId());
                        gbEquivalencia.setCodPrograma(tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId());
                        gbEquivalencia.setNomeALuno(edNome.getText());
                        gbEquivalencia.setNomePrograma(tvProgramas.getSelectionModel().getSelectedItem().getCurso());

                        EsEquivalencia equi = new EsEquivalencia();
                        equi.setCodAluno(reg_atual.getDadoCadastroGeralId().intValue());
                        equi.setCodPrograma(tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId());
                        equi.setDataInc(new Date());
                        equi.setCodUserInc(gbUser.getCoduser());
                        jpaEqui = new EsEquivalenciaJpaController();

                        jpaEqui.create(equi);
                        gbEquivalencia.setCodEquivalencia(equi.getCodEquivalencia());
                        chamaTela("/view/Equivalencia.fxml", "Dispensa (externa) de Disciplina");

                    } catch (Exception e) {
                        mostraMsg("Erro 014eq ao gravar novos dados.", "> " + e, 2);
                    }
                }
            }

        }
    }

    @FXML
    private void clicouEdiDispExterna(ActionEvent event) {
        if (tvRegDis.getSelectionModel().getSelectedItem() != null) {

            // CARREGA VARIAVEI GLOBAL DE EQUIVALENCIA
            if (gbRegCadGer.getDadoCadastroGeralId() == null) {
                mostraMsg("Grave os dados aluno antes de prsseguir", "", 2);
            } else {
                if (tvRegDis.getSelectionModel().getSelectedItem().getSituaacao().indexOf("Dispensa (externa)") == -1) {
                    mostraMsg("Essa não é uma disciplina com dispensa (externa)!", "", 2);
                } else {

                    // carrega dasdos eduivalencia
                    jpaEqui = new EsEquivalenciaJpaController();
                    EsEquivalencia eqEdi = jpaEqui.findEsEquivalencia(tvRegDis.getSelectionModel().getSelectedItem().getCodEqui());
                    gbEquivalencia = new EquivalenciaGB();
                    gbEquivalencia.setModoDis(2);
                    gbEquivalencia.setCodEquivalencia(tvRegDis.getSelectionModel().getSelectedItem().getCodEqui());
                    gbEquivalencia.setCodAluno(gbRegCadGer.getDadoCadastroGeralId());
                    gbEquivalencia.setCodPrograma(tvProgramas.getSelectionModel().getSelectedItem().getCodCadProId());
                    gbEquivalencia.setNomeALuno(edNome.getText());
                    gbEquivalencia.setNomePrograma(tvProgramas.getSelectionModel().getSelectedItem().getCurso());
                    gbEquivalencia.setCodGrade(eqEdi.getCodGrade());
                    gbEquivalencia.setCodDisciplina(eqEdi.getCodDisciplina());
                    gbEquivalencia.setCodSituacao(eqEdi.getCodSituacao());
                    gbEquivalencia.setObservacoes(eqEdi.getObservacoes());
                    gbEquivalencia.setDocsDigi(eqEdi.getDocsDigi());
                    gbEquivalencia.setCodPup(eqEdi.getCodPup());

                    chamaTela("/view/Equivalencia.fxml", "Dispensa (externa) de Disciplina");

                }

            }
        }
    }

    @FXML
    private void clicouEdiEquiv(ActionEvent event) {
        mostraMsg("Módulo em deselvolvimento", "", 3);
    }

    @FXML
    private void clicouEdiDispInterna(ActionEvent event) {
        mostraMsg("Módulo em deselvolvimento", "", 3);
    }

    @FXML
    private void clicouDispInterna(ActionEvent event) {
        mostraMsg("Módulo em deselvolvimento", "", 3);
    }

    @FXML
    private void clicouInsEqui(ActionEvent event) {
        mostraMsg("Módulo em deselvolvimento", "", 3);
    }

    @FXML
    private void mudouDataNasc(ActionEvent event) {
        String idade = "";
        if (dpNasc.getValue() != null) {
            idade = calcularIdade(Date.from(dpNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            idade = idade.substring(0, 2) + " anos e " + idade.substring(3, 5) + " mês(es)";
            lblIdade.setText(idade);
        }
    }

    public ObservableList<String> getImpAlunos() {
        jpaImp = new ImpalunosJpaController();
        dadosImp = FXCollections.observableArrayList(jpaImp.getDescricoes());

        if (dadosImp == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosImp;
        }
    }

    @FXML
    private void clicouVerLista(ActionEvent event) {

    }

    @FXML
    private void clicouEditaFreq(ActionEvent event) {
    }

    @FXML
    private void clicouPresEsq(MouseEvent event) {
        spPresenca.setDividerPositions(0.9875);
    }

    @FXML
    private void clicouPresDir(MouseEvent event) {

        spPresenca.setDividerPositions(0.1733);
    }

    @FXML
    private void clicouVerPresenca(ActionEvent event) {
        if (tvRegDis.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Primero selecione uma disciplina no painel da esquerda.", "", 3);
        } else {
            int numpre = 0, numfal = 0, numatr = 0, numgra = 0, numjus = 0;
            String preSql = "SELECT cha.CodLista_Chamada, aul.numaula, aul.dataaula, cha.chamada, cha.obsalunoaula "
                    + " FROM lista_chamada cha "
                    + " LEFT JOIN lista_aula aul ON cha.CodLista_Aula = aul.CodLista_Aula "
                    + " LEFT JOIN lista_cabec cab ON aul.CodLista = cab.CodLista "
                    + " WHERE cha.codaluno = " + gbRegCadGer.getDadoCadastroGeralId()
                    + " AND cab.CadastroDisciplinaId = " + tvRegDis.getSelectionModel().getSelectedItem().getCodCadDis()
                    + " ORDER BY aul.numaula ";

            obPres = FXCollections.observableArrayList();

            try {

                System.out.println("//--- PRESENCA ");
                System.out.println(preSql);
                System.out.println("//-----------------------");

                ResultSet rs = con.createStatement().executeQuery(preSql);

                while (rs.next()) {
                    obPres.add(new Presenca(
                            rs.getInt("cha.CodLista_Chamada"),
                            rs.getInt("aul.numaula"),
                            rs.getDate("aul.dataaula"),
                            rs.getString("cha.chamada"),
                            rs.getString("cha.obsalunoaula")));

                    if (rs.getString("cha.chamada").equals("F")) {
                        ++numfal;
                    } else if (rs.getString("cha.chamada").equals("A")) {
                        ++numatr;
                    } else if (rs.getString("cha.chamada").equals("G")) {
                        ++numgra;
                    } else if (rs.getString("cha.chamada").equals("J")) {
                        ++numjus;
                    } else {
                        ++numpre;

                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(DadocadastrogeralController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            tcNumAula.setCellValueFactory(new PropertyValueFactory<>("numAula"));
            tcDataAula.setCellValueFactory(new PropertyValueFactory<>("dataAula"));
            tcChamadaAula.setCellValueFactory(new PropertyValueFactory<>("chamada"));
            tcObsAula.setCellValueFactory(new PropertyValueFactory<>("obsChamada"));
            tcCodChamada.setCellValueFactory(new PropertyValueFactory<>("codChamada"));

            tcNumAula.setStyle("-fx-alignment: CENTER;");
            tcDataAula.setStyle("-fx-alignment: CENTER;");
            tcChamadaAula.setStyle("-fx-alignment: CENTER;");

            tvPresenca.setItems(obPres);
            tvPresenca.refresh();
            String spa = "   |   ";
            lblPresencaRodape.setText("Aulas: " + tvPresenca.itemsProperty().get().size() + spa + "P: " + numpre + spa + "F: " + numfal
                    + spa + "A: " + numatr + spa + "G: " + numgra + spa + "J: " + numjus);

        }
    }

    @FXML
    private void clicouImpDOC(ActionEvent event) throws JRException, IOException {
        geraLista(1); // WORD
    }

    @FXML
    private void clicouImpXLS(ActionEvent event) throws JRException, IOException {
        geraLista(2); // EXCEL
    }

    @FXML
    private void clicouImpPDF(ActionEvent event) throws JRException, IOException {
        geraLista(3); // PDF
    }
//

    @FXML
    private void clicouImprimeListagem(ActionEvent event) throws JRException, IOException {
        geraLista(4); // TELA
    }

    private void geraLista(int tipo) throws JRException, IOException {
        String nomeRepo;
        Impalunos lisSele;
        File destFile = null;

        if (cbImpAlunos.getSelectionModel().getSelectedItem() != null) {
            jpaImp = new ImpalunosJpaController();
            lisSele = jpaImp.getObjeto(cbImpAlunos.getSelectionModel().getSelectedItem());

            nomeRepo = (gbDeOnde == "Jpa_EvedSys_Server") ? lisSele.getJasperServer() : lisSele.getJasperLocal();

            try {

                Date agora = new Date();
                String fago = GBFORMATDATAHORA.format(agora);
                InputStream jasperF = GradeDisMasController.class.getResourceAsStream("/reporters/" + nomeRepo + ".jasper");
                InputStream imagem = DadosCadastraisController.class.getResourceAsStream("/imagens/LOGOSTSC.jpg");
                //mostraMsg(nomeRepo, "", 3);

                Map<String, Object> parametros = new HashMap<>();

                switch (cbImpAlunos.getSelectionModel().getSelectedIndex()) {
                    case 0: // RELATÓRIO DE NOTAS
                        parametros.put("ALUNO", reg_atual.getNome());
                        parametros.put("MATRICULA", reg_atual.getMatricula());
                        parametros.put("PROGRAMA", tvProgsReport.getSelectionModel().getSelectedItem().getCurso());
                        parametros.put("CURSO", tvProgsReport.getSelectionModel().getSelectedItem().getTitulacao());
                        parametros.put("CODALUNO", reg_atual.getDadoCadastroGeralId());
                        parametros.put("IMG_LOGO", imagem);
                        parametros.put("DATAHORA", fago);
                        break;
                    case 1: // HISTÓRICO ACADÊMICO
                        String dnas;
                        dnas = (reg_atual.getDataNascimento() != null) ? GBFORMATDATA.format(reg_atual.getDataNascimento()) : "";

                        parametros.put("CODPROGRAMA", tvProgsReport.getSelectionModel().getSelectedItem().getCodCadProId());
                        parametros.put("ALUNO", reg_atual.getNome());
                        parametros.put("LOCALNASC", reg_atual.getLocalNascimento());
                        parametros.put("UFNASC", reg_atual.getNomeUFNasc().getSiglaEstado());
                        parametros.put("DATANASC", dnas);
                        parametros.put("NACIONALIDADE", reg_atual.getNomeNacionalidade().getNacionalidade());
                        parametros.put("RG", reg_atual.getRg());
                        parametros.put("CPF", reg_atual.getCpf());

                        parametros.put("NIVEL", reg_atual.getEscolaGrauMaxima());
                        String curso = "";
                        String escola = "";
                        String conclusao = "";
                        String cidade = "";
                        String uf = "";
                        if (reg_atual.getEscolaPosGrad() != null) {
                            curso = reg_atual.getCursoPosGrad();
                            escola = reg_atual.getEscolaPosGrad();
                            conclusao = reg_atual.getAnoConclusaoPosGrad();
                            cidade = reg_atual.getCidadePosGrad();
                            uf = reg_atual.getNomeUFPos().getSiglaEstado();
                        } else {
                            curso = reg_atual.getCursoEscola();
                            escola = reg_atual.getEscola();
                            conclusao = reg_atual.getAnoConclusaoEscola().toString();
                            cidade = reg_atual.getCidadeEscola();
                            uf = reg_atual.getNomeUFEscola().getSiglaEstado();
                        }

                        // reg_atual.getes
                        parametros.put("CURSO", curso);
                        parametros.put("ESCOLA", escola);
                        parametros.put("CIDADE", cidade);
                        parametros.put("UF", uf);
                        parametros.put("CONCLUSAO", conclusao);

                        parametros.put("PROGRAMA", tvProgsReport.getSelectionModel().getSelectedItem().getCurso());

                        parametros.put("REITOR", "Ziel Machado");
                        parametros.put("CARGOREITOR", "Vice Reitor");

                        parametros.put("IMG_LOGO", imagem);
                        parametros.put("DATAHORA", fago);
                        break;
                }

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperF, parametros, con);
                JasperViewer.viewReport(jasperPrint, false);

                String nomeArq = "";
                ButtonType abrir = new ButtonType("abrir", ButtonBar.ButtonData.OK_DONE);
                ButtonType naoabrir = new ButtonType("não abrir", ButtonBar.ButtonData.CANCEL_CLOSE);
                String nomeArquivoOK = "";
                String nomeLimpo = limpaNome(tvProgsReport.getSelectionModel().getSelectedItem().getPrograma());

                // aqui o jasperPrint está pronto. Onde imprimir/exportar?
                switch (tipo) {
                    case 1: // WORD
                        nomeArq = tfPathRelats.getText() + "\\" + nomeRepo + "-" + nomeLimpo;
                        nomeArquivoOK = getNovoNomeArq(nomeArq, ".docx");
                        destFile = new File(nomeArquivoOK);

                        JRDocxExporter exporterDocx = new JRDocxExporter();
                        exporterDocx.setExporterInput(new SimpleExporterInput(jasperPrint));
                        exporterDocx.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
                        exporterDocx.exportReport();

                        abre_ou_nao_relat(nomeArquivoOK, "Word", abrir, naoabrir);
                        break;
                    case 2: // EXCEL

                        nomeArq = tfPathRelats.getText() + "\\" + nomeRepo + "-" + nomeLimpo;
                        nomeArquivoOK = getNovoNomeArq(nomeArq, ".xlsx");
                        destFile = new File(nomeArquivoOK);

                        JRXlsxExporter exporter = new JRXlsxExporter();
                        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));

                        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                        configuration.setOnePagePerSheet(false);
                        exporter.setConfiguration(configuration);
                        exporter.exportReport();

                        abre_ou_nao_relat(nomeArquivoOK, "Excel", abrir, naoabrir);

                        break;

                    case 3: // PDF

                        nomeArq = tfPathRelats.getText() + "\\" + nomeRepo + "-" + nomeLimpo;
                        nomeArquivoOK = getNovoNomeArq(nomeArq, ".pdf");
                        destFile = new File(nomeArquivoOK);

                        JRPdfExporter exporterPdf = new JRPdfExporter();

                        exporterPdf.setExporterInput(new SimpleExporterInput(jasperPrint));
                        exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
                        exporterPdf.exportReport();

                        abre_ou_nao_relat(nomeArquivoOK, "PDF", abrir, naoabrir);

                        break;
                    case 4: // TELA, IMPRESSORA
                        JasperViewer.viewReport(jasperPrint, false);
                        break;
                }

            } catch (JRException e) {
                PrintStream printer = new PrintStream("C:/eved_relatorios/listagens.log");
                e.printStackTrace(printer);
            }

        }
    }

    private Boolean abre_ou_nao_relat(String nomeDestFile, String tipoArq, ButtonType abrir, ButtonType naoabrir) {
        Alert alert = new Alert(AlertType.INFORMATION, "Deseja abrir o arquivo " + nomeDestFile + " agora?", abrir, naoabrir);
        alert.setHeaderText("O arquivo " + tipoArq + " foi gerado com sucesso.");
        alert.setContentText("\nDeseja abrir o arquivo " + nomeDestFile + " agora?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.orElse(naoabrir) == abrir) {
            try {
                Desktop.getDesktop().open(new File(nomeDestFile));
            } catch (Exception e) {
                mostraMsg(nomeDestFile, "Erro 0283x ao abrir arquivo\n" + e, 2);
            }
            return false;
        }
        return true;
    }

    private String getNovoNomeArq(String nomeArq, String extArq) {
        String nomeDestFile = nomeArq + extArq;

        File tempArq = new File(nomeDestFile);

        int contador = 0;
        while (tempArq.exists()) {
            ++contador;
            nomeDestFile = nomeArq + "_" + contador + extArq;
            tempArq = new File(nomeDestFile);
        }

        return nomeDestFile;
    }

    private String limpaNome(String nomeDestFile) {
        nomeDestFile = nomeDestFile.replaceAll(":", "_");
        nomeDestFile = nomeDestFile.replaceAll("<", "_");
        nomeDestFile = nomeDestFile.replaceAll(">", "_");
        nomeDestFile = nomeDestFile.replaceAll("“", "_");
        nomeDestFile = nomeDestFile.replaceAll("'", "_");
        return nomeDestFile;
    }

}
