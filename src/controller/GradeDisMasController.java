/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.CRITERIOLISTALU;
import static main.Login.aListasPre;
import static main.Login.gbAtuNotas;
import static main.Login.gbClicouInsGraDis;
import static main.Login.gbClicouEdiGraDis;
import static main.Login.gbCadDis_GridMas;
import static main.Login.gbDeOnde;
import static main.Login.gbDisci;
import static main.Login.GBFORMATDATAHORA;
import static main.Login.gbRegCad;
import static main.Login.graM;
import static main.Login.gbRegCadGer;
import static main.Login.gbListaCabec;
import static main.Login.gbListaInfo;
import static main.Login.gbUser;
import com.jfoenix.controls.JFXButton;
import static controller.MenuPrincipalController.stgGradeMaster;
import entities.Atu_Notas;
import entities.Cadastroalunodisciplina;
import entities.Cadastrodisciplina;
import entities.DisciProgAluno;
import entities.EsGrade;
import entities.GraDisAluPro;
import entities.Grade;
import entities.GradeTot;
import entities.ListaCabec;
import entities.ListaInfo;
import entities.Listagens;
import entities.TotAluPro;
import funcoes.DBConnector;
import funcoes.MyFunc;
import static funcoes.MyFunc.mostraMsg;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.CadastrodisciplinaJpaController;
import jpa_controler.DadocadastrogeralJpaController;
import jpa_controler.EsGradeJpaController;
import jpa_controler.ListaCabecJpaController;
import jpa_controler.ListagensJpaController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;
/**
 * FXML Controller class
 *
 * @author luiza
 */
public class GradeDisMasController implements Initializable {

    @FXML
    private TableView<EsGrade> tvGradeM;
    @FXML
    private TableColumn<EsGrade, Integer> tc2;
    @FXML
    private TableColumn<EsGrade, Integer> tc3;

    @FXML
    private AnchorPane apEsq;
    @FXML
    private AnchorPane apDir;
    @FXML
    private AnchorPane apHori;
    @FXML
    private TableView<Grade> tvGradeD1;
    @FXML
    private TableColumn<Grade, String> tcTipoDis1;
    @FXML
    private TableColumn<Grade, String> tcTurno1;
    @FXML
    private TableColumn<Grade, String> tcDatas1;
    @FXML
    private TableColumn<Grade, String> tcHorarios1;
    @FXML
    private TableColumn<Grade, String> tcDisciplinas1;
    @FXML
    private TableColumn<Grade, String> tcProf11;
    @FXML
    private TableColumn<Grade, String> tcProf21;
    @FXML
    private TableColumn<Grade, Integer> tcCred1;
    @FXML
    private TableColumn<Grade, Integer> tcTotAlu1;
    @FXML
    private TableColumn<Grade, Integer> tcReg1;
    @FXML
    private TableColumn<Grade, Integer> tcOuv1;
    @FXML
    private ComboBox<String> cbAluDis;
    @FXML
    private TableView<GraDisAluPro> tvAluDis;
    @FXML
    private TableColumn<GraDisAluPro, String> tcAluno;
    @FXML
    private TableColumn<GraDisAluPro, String> tcPrograma;
    @FXML
    private TableColumn<GraDisAluPro, Float> tcMEdia;
    @FXML
    private TableColumn<GraDisAluPro, Short> tcFalta;
    @FXML
    private TableColumn<GraDisAluPro, String> tcSituacao;
    @FXML
    private TableColumn<GraDisAluPro, String> tcFrequencia;
    @FXML
    private TableColumn<GraDisAluPro, Short> tcCadastroAlunoDisciplinaId;
    @FXML
    private TableColumn<GraDisAluPro, String> tcTipo;

    @FXML
    private TextField lblRegistrosDet1;

    @FXML
    private TextField lblAluDis;
    @FXML
    private PieChart pieAluPro;
    @FXML
    private TableView<TotAluPro> tvTotAlu;
    @FXML
    private TableColumn<TotAluPro, Integer> tcTotPro;

    @FXML
    private TextField lblAluDis1;
    @FXML
    private TableColumn<?, ?> tcTotAlu2;

    @FXML
    private SplitPane sp1;
    @FXML
    private SplitPane sp2;
    @FXML
    private SplitPane sp3;

    @FXML
    private SplitPane sp4;
    @FXML
    private LineChart<?, ?> linePainel;
    @FXML
    private PieChart piePainel;
    @FXML
    private Label lblRegulares;
    @FXML
    private Label lblOuvintes;
    @FXML
    private Label lblProgramas;
    @FXML
    private Label lblMedia;

    public Connection con = DBConnector.getConnection();
    private ObservableList<EsGrade> dadosGraM;
    private EsGradeJpaController jpaGraM;
    private ListaCabecJpaController jpaLC;
    private CadastroalunodisciplinaJpaController jpaPup;

    private ObservableList<Grade> dadosGraD;
    private ObservableList<GradeTot> dadosTot;
    private CadastrodisciplinaJpaController jpaCAD;

    ObservableList<GraDisAluPro> oblist;
    ObservableList<TotAluPro> obTot;

    EsGrade reg_atual = new EsGrade();
    Cadastrodisciplina reg_cad = new Cadastrodisciplina();
    String sqlProgramas;

    private String SQL_MASTER_TOTAIS
            = "SELECT pup.CadastroDisciplinaId, cad.CadastroDisciplinaId, count(*) as totAlunos, cad.codGrade    "
            + " FROM cadastroalunodisciplina pup  "
            + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId "
            + " LEFT JOIN cadastrodisciplina cd ON cad.CadastroDisciplinaId = cd.CadastroDisciplinaId  "
            + " LEFT JOIN es_grade gra ON cad.codGrade = gra.codGrade ";

    private ObservableList<String> dadosPG;
    private ObservableList dataAluPro;
    private ObservableList dataSit;
    private XYChart.Series serieNotas = new XYChart.Series();

    public final String SQLBASE
            = "SELECT dcg.nome, cur.curso, pup.media, pup.falta, cs.Descricao as situacao, fr.frequencia, pup.CadastroAlunoDisciplinaId, dcg.dadoCadastroGeralId, pup.TipoRegOuv  "
            + " FROM cadastroalunodisciplina pup "
            + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId "
            + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
            + " LEFT JOIN Curso cur ON dcp.CursoId = cur.cursoId   "
            + " LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id  "
            + " LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  "
            + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId  ";

    public final String SQLTOTGRU
            = "SELECT cur.curso, cur.sigla, count(*) as totprograma  "
            + "FROM cadastroalunodisciplina pup  "
            + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId "
            + "LEFT JOIN cadastrodisciplina cd ON pup.CadastroDisciplinaId = cd.CadastroDisciplinaId  "
            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
            + "LEFT JOIN Curso cur ON dcp.CursoId = cur.cursoId  ";

    public final String SQLSIT
            = "SELECT cs.Sigla as situacao, fr.frequencia, COUNT(*) AS totsit  "
            + " FROM cadastroalunodisciplina pup  "
            + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId  "
            + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
            + " LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id  "
            + " LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  ";

    public final String SQLNOTAS
            = " SELECT dcg.nome, pup.media, pup.falta, cs.Descricao as situacao, fr.frequencia, pup.TipoRegOuv  "
            + "  FROM cadastroalunodisciplina pup  "
            + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId  "
            + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
            + " LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id  "
            + "  LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  "
            + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId  ";

    public final String SQLBASENOTAS
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
            + " LEFT JOIN turma tm ON cad.TurmaId = tm.TurmaId ";

    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private ComboBox<String> cbCategDis = new ComboBox<>();
    private DadocadastrogeralJpaController jpaCon;

    ObservableList<DisciProgAluno> disProList;

    @FXML
    private TableColumn<?, ?> tcCadDus;
    @FXML
    private ContextMenu cmAbreCadastro;

    public static Stage stgGridMas;
    @FXML
    private Label lblPlacar1;
    @FXML
    private Label lblPlacar2;
    @FXML
    private TextField tfFiltrar;
    @FXML
    private JFXButton buApagaDis;
    @FXML
    private Label lblCadastrpDisciplinaId;

    @FXML
    private Pane pnListagem;

    @FXML
    private TextField tfPathRelats;
    @FXML
    private JFXButton buPastaRelat;

    private ListagensJpaController jpaLis;
    ObservableList<String> dadosLis;

    // PARA GERENCIAR LISTAS ABERTAS
    public static final Integer MAXLISTASABERTAS = 15;

    Integer contaAbertos = 0;

    Map<Integer, String> listagens = new HashMap<>();
    @FXML
    private ComboBox<String> cbListagens;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // MAPA COM NOME DAS LISTAGENS
        cbListagens.setItems(getListagens(1)); // 1 =  listagens lista de presença; 2 = placar
        cbListagens.getSelectionModel().selectFirst();

        // INICIA ARRAY DE LISTAS
        tfPathRelats.setText(gbUser.getPastaRelat());
        DirectoryChooser directoryChooser = new DirectoryChooser();

        buPastaRelat.setOnAction(e -> {
            directoryChooser.setInitialDirectory(new File(tfPathRelats.getText()));
            File selectedDirectory = directoryChooser.showDialog(stgGradeMaster);
            tfPathRelats.setText(selectedDirectory.getAbsolutePath());
        });

        aListasPre = new Stage[MAXLISTASABERTAS];
        gbDisci = new Integer[MAXLISTASABERTAS];

        for (int i = 0; i < aListasPre.length; i++) {
            aListasPre[i] = null;
            gbDisci[i] = 0;
        }

        //     aListasPre[0].setOnCloseRequest(value);
        buApagaDis.setVisible(gbUser.getCodperfil() <= 2);

        jpaCAD = new CadastrodisciplinaJpaController();
        jpaPup = new CadastroalunodisciplinaJpaController();

        initColsM();
        //     tswVisao.setSelected(true);

        tvGradeM.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        AbreGradeM();
                    }
                }
            }
        });

        // atualiza grid disciplinas quando navega por GradeM
        tvGradeM.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tvAluDis.getItems().clear();
                tvAluDis.refresh();
                pieAluPro.getData().clear();
                cbAluDis.getItems().clear();
                tvTotAlu.getItems().clear();
                tvTotAlu.refresh();
                piePainel.getData().clear();
                initColsD();
                lblPlacar1.setText("Ano: " + tvGradeM.getSelectionModel().getSelectedItem().getAnoLetivo() + "   Semestre: " + tvGradeM.getSelectionModel().getSelectedItem().getSemestre());
                lblPlacar2.setText("");
            }
        });

        tvGradeD1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                inicGraM();

                //jpaCAD.getCadDisbyCodGrade(graM.getCodGrade());
                gbRegCad = jpaCAD.findCadastrodisciplina(tvGradeD1.getSelectionModel().selectedItemProperty().getValue().getCadastroDisciplinaId());
                reg_cad = gbRegCad;
                cbAluDis.setItems(getProgramasDistinct());
                cbAluDis.getSelectionModel().select("=> TODOS");
                inictColsGDAP();
                inictColsTot();
                inicPainel();
                lblPlacar2.setText("Disciplina: " + tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc());
                lblCadastrpDisciplinaId.setText("" + tvGradeD1.getSelectionModel().getSelectedItem().getCadastroDisciplinaId());
            }
        });

        tvGradeD1.requestFocus();
        tvGradeD1.getSelectionModel().select(0);
        tvGradeD1.getFocusModel().focus(0);

        /////////////////////////////////////////////////////
        //
        // TIRAR DEPOIS
        //###################################################
        /////////////////////////////////////////////////////
        // PARA ENTRAR EM TIAGO
//        if (gbUser.getCoduser() == 2) {
//            tvGradeM.requestFocus();
//            tvGradeM.getSelectionModel().select(2);
//
//            tvGradeD1.requestFocus();
//            tvGradeD1.getSelectionModel().select(0);
//            tvGradeD1.getFocusModel().focus(0);
//            chamaLista();
//        }
    }

    public ObservableList<String> getListagens(Integer tipo) {
        jpaLis = new ListagensJpaController();
        dadosLis = FXCollections.observableArrayList(jpaLis.getDescricoes(tipo));

        if (dadosLis == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosLis;
        }
    }

    public void inictColsTot() {
        obTot = FXCollections.observableArrayList();
        dataAluPro = FXCollections.observableArrayList();
        int numProgs = 0;

        try {
            String sqlTot = SQLTOTGRU + " WHERE cad.codgrade = " + graM.getCodGrade()
                    + " AND cad.CadastroDisciplinaId = " + reg_cad.getCadastroDisciplinaId()
                    + " GROUP BY cur.curso "
                    + " ORDER BY cur.curso ";

            System.out.println(sqlTot);

            ResultSet rs = con.createStatement().executeQuery(sqlTot);

            while (rs.next()) {
                obTot.add(new TotAluPro(rs.getString(2), rs.getInt(3)));
                dataAluPro.add(new PieChart.Data(rs.getString(2) + " (" + rs.getInt(3) + ")", rs.getInt(3)));
                ++numProgs;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralController.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblProgramas.setText(String.valueOf(numProgs));
        tcTotPro.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        tcTotAlu2.setCellValueFactory(new PropertyValueFactory<>("totAluno"));

        //   TelaAlunosDAO dao = new TelaAlunosDAO();
        tvTotAlu.setItems(obTot);
        tvTotAlu.refresh();
        lblAluDis1.setText("Total de Alunos: " + tvAluDis.itemsProperty().get().size()); //rs2.getString(1));

        pieAluPro.getData().clear();
        pieAluPro.getData().addAll(dataAluPro);

//        // ALIMENTAR PIE CHART
//        ObservableList<PieChart.Data> pieData = obTot;
//        pieAluPro = new PieChart(pieData);
//        System.out.println(dadosPie);
//        for (Iterator<PieDados> iterator = dadosPie.iterator(); iterator.hasNext();) {
//            PieDados next = iterator.next();
//            System.out.println(next.getTitulo() +  ",  " + next.getValor());
//            
//        }
//      //  dados2.add()
    }

    public void inicPainel() {
        dataSit = FXCollections.observableArrayList();
        serieNotas = new XYChart.Series();
        serieNotas.setName("Notas da classe");
        int numAlunos = 0;
        int ouv = 0;
        int reg = 0;
        Float somaNotas = 0F;
        Float media = 0F;

        // CALCULA pie SITUAÇÃO
        try {
            String sqlSitu = SQLSIT + " WHERE cad.codgrade = " + graM.getCodGrade()
                    + " AND cad.CadastroDisciplinaId  = " + reg_cad.getCadastroDisciplinaId()
                    + " GROUP BY cs.Descricao "
                    + " ORDER BY cs.Descricao ";

            System.out.println(sqlSitu);

            ResultSet rs = con.createStatement().executeQuery(sqlSitu);

            while (rs.next()) {
                dataSit.add(new PieChart.Data(rs.getString(1) + " (" + rs.getInt(3) + ")", rs.getInt(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // CALCULA line NOTAS
        try {
            String sqlNotas = SQLNOTAS + " WHERE cad.codgrade = " + graM.getCodGrade()
                    + " AND cad.CadastroDisciplinaId  = " + reg_cad.getCadastroDisciplinaId()
                    + " ORDER BY pup.media desc, dcg.nome ";

            System.out.println(sqlNotas);

            ResultSet rs = con.createStatement().executeQuery(sqlNotas);

            while (rs.next()) {
                if (rs.getString(5) == null || !rs.getString(5).equals("Ouvinte")) {
                    ++reg;
                    somaNotas = somaNotas + rs.getFloat(2);
                } else {
                    ++ouv;
                }
                ++numAlunos;
                //  String x = rs.getString(1).substring(0, 1);
                Float y = rs.getFloat(2);
                serieNotas.getData().add(new XYChart.Data(String.valueOf(reg), y));
                ++numAlunos;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralController.class.getName()).log(Level.SEVERE, null, ex);
        }

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        media = somaNotas / reg;
        lblMedia.setText(df.format(media));
        lblOuvintes.setText(String.valueOf(ouv));
        lblRegulares.setText(String.valueOf(reg));
        piePainel.getData().clear();
        piePainel.getData().addAll(dataSit);

//        xAxis = new NumberAxis(0, 10, 0.5);
//        yAxis = new NumberAxis(0, NumAlunos, 1);
        linePainel.getData().clear();
//        linePainel = new LineChart(xAxis, yAxis);
        linePainel.getData().addAll(serieNotas);

//        for (int i = 0; i < serieNotas.getData().size(); i++) {
//            System.out.println(i + 1 + " " + serieNotas.getData().get(i));
//        }
    }

    public void inictColsGDAP() {
        oblist = FXCollections.observableArrayList();
        String filtro = "";
        if (cbAluDis.getSelectionModel().getSelectedItem() != "=> TODOS") {
            filtro = " AND cur.curso = '" + cbAluDis.getSelectionModel().getSelectedItem() + "' ";
        }
        try {
            String sqlFinal = SQLBASE + " WHERE cad.codgrade = " + graM.getCodGrade()
                    + " AND pup.CadastroDisciplinaId = " + reg_cad.getCadastroDisciplinaId()
                    //   + " AND (cs.Descricao != 'Trancado' OR cs.Descricao IS NULL) "
                    + filtro
                    + CRITERIOLISTALU;

            System.out.println("iii INI - ALUNOS DA DISCIPLINA - TVALUDIS");
            System.out.println(sqlFinal);
            System.out.println("eee fim - ALUNOS DA DISCIPLINA - TVALUDIS");

            ResultSet rs = con.createStatement().executeQuery(sqlFinal);

            while (rs.next()) {
                oblist.add(new GraDisAluPro(
                        rs.getString("cur.curso"),
                        rs.getString("nome"),
                        rs.getFloat("media"),
                        rs.getShort("falta"),
                        rs.getString("situacao"),
                        rs.getString("frequencia"),
                        rs.getShort("CadastroAlunoDisciplinaId"),
                        rs.getInt("dcg.dadoCadastroGeralId"),
                        rs.getString("pup.TipoRegOuv")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tcAluno.setCellValueFactory(new PropertyValueFactory<>("aluno"));
        tcPrograma.setCellValueFactory(new PropertyValueFactory<>("programa"));
        tcMEdia.setCellValueFactory(new PropertyValueFactory<>("media"));
        tcFalta.setCellValueFactory(new PropertyValueFactory<>("falta"));
        tcSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        tcFrequencia.setCellValueFactory(new PropertyValueFactory<>("frequencia"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        tcCadastroAlunoDisciplinaId.setCellValueFactory(new PropertyValueFactory<>("cadastroAlunoDisciplinaId"));

        //   TelaAlunosDAO dao = new TelaAlunosDAO();
        tvAluDis.setItems(oblist);
        tvAluDis.refresh();
        lblAluDis.setText("Registros exibidos: " + tvAluDis.itemsProperty().get().size()); //rs2.getString(1));
    }

    private void AbreGradeM() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/GradeM.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setTitle("Grades dos Semestres");
            stage.setScene(scene);

            stage.showAndWait();
            initColsM();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 104 ao carregar GradeM", "" + ex, 0);
        }
    }

    private void AbreGradeD() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/GradeDisDet.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setTitle("Disciplinas do Semestre");
            stage.setScene(scene);

            stage.showAndWait();
            initColsD();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 105 ao carregar GradeD", "" + ex, 0);
        }
    }

    public void initColsM() {
        //tc1.setCellValueFactory(new PropertyValueFactory<>("codGrade"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        tvGradeM.setItems(getMeusDadosM());
        tvGradeM.refresh();
    }

    public void initColsD() {

        dadosGraD = FXCollections.observableArrayList();
        dadosTot = FXCollections.observableArrayList();
        if (tvGradeM.getSelectionModel().getSelectedItem() != null) {
            reg_atual = tvGradeM.getSelectionModel().getSelectedItem();

            if (reg_atual.getCodGrade() > 0) {
                sqlProgramas
                        = "SELECT cad.CadastroDisciplinaId, cad.CodGrade, cad.Descricao, cad.labelData, cad.labelHora, fun.nome as prof1, fun2.nome as prof2, cad.Credito, turn.turno, tipo.tipodisc  "
                        + "FROM cadastrodisciplina cad "
                        + "LEFT JOIN disciplina dis ON cad.DisciplinaId = dis.DisciplinaId  "
                        + "LEFT JOIN funcionario fun ON cad.FuncionarioId = fun.FuncionarioId "
                        + "LEFT JOIN funcionario fun2 ON cad.Professor2 = fun2.FuncionarioId  "
                        + "LEFT JOIN turno turn ON cad.TurnoId = turn.TurnoId  "
                        + "LEFT JOIN es_tipodis tipo ON cad.TIpoDis = tipo.codtipodis "
                        + " WHERE (cad.codgrade = " + reg_atual.getCodGrade() + " ) "
                        + " ORDER BY cad.ordemLista "; //+ " ORDER BY det.turno, det.ordemLista ";
                try {
                    System.out.println(sqlProgramas);

                    ResultSet rs = con.createStatement().executeQuery(sqlProgramas);
                    while (rs.next()) {
                        dadosGraD.add(new Grade(
                                rs.getInt("cad.CadastroDisciplinaId"),
                                rs.getInt("codgrade"),
                                rs.getString("turno"),
                                rs.getString("tipodisc"),
                                rs.getString("Descricao"),
                                rs.getString("prof1"),
                                rs.getString("prof2"),
                                rs.getString("labelData"),
                                rs.getString("labelHora"),
                                rs.getInt("Credito"),
                                0)); // zera total de alunos para preencher depois
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DadocadastrogeralController.class
                            .getName()).log(Level.SEVERE, null, ex);
                    mostraMsg("Erro 018 - em initColsD()", "" + ex, 2);
                }

                try {
                    String sqlTotais = SQL_MASTER_TOTAIS
                            + " WHERE cad.codGrade = " + reg_atual.getCodGrade()
                            + " GROUP BY cad.CadastroDisciplinaId "
                            + " ORDER BY cad.ordemLista ";
                    System.out.println("===============================================");
                    System.out.println(sqlTotais);
                    System.out.println("===============================================");

                    ResultSet rs2 = con.createStatement().executeQuery(sqlTotais);
                    while (rs2.next()) {
                        dadosTot.add(new GradeTot(
                                rs2.getInt("cad.CadastroDisciplinaId"),
                                rs2.getInt("cad.codGrade"),
                                rs2.getInt("totAlunos"))); // zera total de alunos para preencher depois
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DadocadastrogeralController.class.getName()).log(Level.SEVERE, null, ex);
                    mostraMsg("Erro 018c - em initColsD()", "" + ex, 2);
                }
            }
        }

        tcCadDus.setCellValueFactory(new PropertyValueFactory<>("cadastroDisciplinaId"));
        tcTipoDis1.setCellValueFactory(new PropertyValueFactory<>("tipoDis"));
        tcTurno1.setCellValueFactory(new PropertyValueFactory<>("turno"));
        tcDatas1.setCellValueFactory(new PropertyValueFactory<>("labelData"));
        tcHorarios1.setCellValueFactory(new PropertyValueFactory<>("labelHora"));
        tcDisciplinas1.setCellValueFactory(new PropertyValueFactory<>("nomeDisc"));
        tcProf11.setCellValueFactory(new PropertyValueFactory<>("professor1"));
        tcProf21.setCellValueFactory(new PropertyValueFactory<>("professor2"));
        tcCred1.setCellValueFactory(new PropertyValueFactory<>("credito"));
        tcTotAlu1.setCellValueFactory(new PropertyValueFactory<>("totAlu"));
        tcReg1.setCellValueFactory(new PropertyValueFactory<>("aluReg"));
        tcOuv1.setCellValueFactory(new PropertyValueFactory<>("aluOuv"));

        dadosTot.forEach(i -> {
            int cadiT = i.getCadastroDisciplinaId();
            for (int j = 0; j < dadosGraD.size(); j++) {
                int cadiG = dadosGraD.get(j).getCadastroDisciplinaId();
                if (cadiT == cadiG) {
                    dadosGraD.get(j).setTotAlu(i.getTotAlunos());
                    break;
                }

            }
        });

        tvGradeD1.setItems(dadosGraD);
        tvGradeD1.refresh();

        lblRegistrosDet1.setText(
                "Registros exibidos: " + tvGradeD1.itemsProperty().get().size()); //rs2.getString(1));
    }

    public ObservableList<EsGrade> getMeusDadosM() {

        jpaGraM = new EsGradeJpaController();
        dadosGraM = FXCollections.observableArrayList(jpaGraM.getGradesOrdenadas(sqlProgramas));
        //  txtPesq.setText("");

        if (dadosGraM == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosGraM;
        }

    }

    public ObservableList<Grade> getMeusDadosD() {

//        jpaGraD = new getMeusDadosD();
//        dadosGraD = FXCollections.observableArrayList(jpaGraD.findEsGradeEntities());
//        //  txtPesq.setText("");
//
//        if (dadosGraD == null) {
//            return FXCollections.observableArrayList();
//        } else {
//            return dadosGraD;
//        }
        return null;
    }

    @FXML
    private void cbAluDisAction(ActionEvent event) {
        inictColsGDAP();
    }

    // ======================
    // GUARDAR PARA O FUTURO
    // ======================
//    private void clicouSwitch(MouseEvent event) {
//        if (tswVisao.isSelected()) {
//            lblVisao.setText("visão horizontal");
//            apHori.setVisible(true);
//            apVert.setVisible(false);
//        } else {
//            lblVisao.setText("visão vertical");
//            apHori.setVisible(false);
//            apVert.setVisible(true);
//        }
//
//    }
    public ObservableList<String> getProgramasDistinct() {
        dadosPG = FXCollections.observableArrayList();

        try {
            String sqlProDis = SQLBASE + " WHERE cad.codgrade = " + graM.getCodGrade()
                    + " AND cad.CadastroDisciplinaId = " + reg_cad.getCadastroDisciplinaId()
                    + " GROUP BY cur.curso "
                    + " ORDER BY cur.curso ";

            System.out.println(sqlProDis);

            ResultSet rs = con.createStatement().executeQuery(sqlProDis);
            dadosPG.add("=> TODOS");

            while (rs.next()) {
                dadosPG.add(rs.getString("cur.curso"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro sql " + ex);
        }

        return dadosPG;
    }

    private void inicGraM() {
        graM = new EsGrade();
        graM.setCodGrade(tvGradeM.getSelectionModel().selectedItemProperty().getValue().getCodGrade());
        graM.setAnoLetivo(tvGradeM.getSelectionModel().selectedItemProperty().getValue().getAnoLetivo());
        graM.setSemestre(tvGradeM.getSelectionModel().selectedItemProperty().getValue().getSemestre());
    }

    @FXML
    private void clicouIndDis(ActionEvent event) {

        // vai criar registro em branco e gerar PK para netos (es_gradedisprg.
        // graM é objeto com codgrade
        if (tvGradeM.getSelectionModel().selectedItemProperty().getValue() != null) {
            inicGraM();
            gbRegCad = new Cadastrodisciplina();
            gbRegCad.setNomeGrade(graM);
            gbClicouInsGraDis = true;
            gbClicouEdiGraDis = false;
            gbCadDis_GridMas = 0;
            AbreGradeD();
        }
    }

    @FXML
    private void clicouEdiDis(ActionEvent event) {
        if (tvGradeD1.getSelectionModel().selectedItemProperty().getValue() != null) { //.getNomeDisc(
            inicGraM();
            // carrega em gbRegCad o registro selecionado
            jpaCAD = new CadastrodisciplinaJpaController();
            gbCadDis_GridMas = tvGradeD1.getSelectionModel().selectedItemProperty().getValue().getCadastroDisciplinaId();
            gbRegCad = jpaCAD.findCadastrodisciplina(gbCadDis_GridMas);
            gbClicouInsGraDis = false;
            gbClicouEdiGraDis = true;
            AbreGradeD();
        }
    }

    @FXML
    private void clicouEdNotas(ActionEvent event) {
        if (tvAluDis.getSelectionModel().getSelectedItem() != null) {
            disProList = FXCollections.observableArrayList();
            String sqlAtuNotas = SQLBASENOTAS
                    + " WHERE pup.CadastroAlunoDisciplinaId = " + tvAluDis.getSelectionModel().getSelectedItem().getCadastroAlunoDisciplinaId();
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
                Logger.getLogger(CadProg2Controller.class.getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018k - em initColsPro()", "" + ex, 2);
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
                inictColsGDAP();
                //     inictColsTot();
                inicPainel();

            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 004df ao carregar AtuNotas", "" + ex, 0);
            }
        }
    }

    @FXML
    private void clicouOrgJanelas(ActionEvent event) {
        sp1.setDividerPositions(0.0802);
        sp2.setDividerPositions(0.5009);
        sp3.setDividerPositions(0.6924);
        sp4.setDividerPositions(0.3604, 0.7119);
    }

    @FXML
    private void abreCadastro(ActionEvent event) {
        if (tvAluDis.getSelectionModel().getSelectedItem() != null) {

            //PASSA REGISTRO SELECIONADO PARA A VARIÁVEL GLOBAL rgRegDisc, que será usado na tela TabelaDadocadastrogerals
            jpaCon = new DadocadastrogeralJpaController();
            gbRegCadGer = jpaCon.findDadocadastrogeral((tvAluDis.getSelectionModel().getSelectedItem().getDadoCadastroGeralId()));
//            stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml" "Cadastro de Alunos", "/MeuCSS/cssDadosCad.css");
            stgGridMas = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "");

//            try {
//                Parent parent = FXMLLoader.load(getClass().getResource("/view/DadosCadastrais.fxml"));
//                Stage stageCadastrais = new Stage(StageStyle.DECORATED);
//                stageCadastrais.initModality(Modality.WINDOW_MODAL);
//                stageCadastrais.setTitle("Dadocadastrogeral");
//                stageCadastrais.setScene(new Scene(parent));
//                stageCadastrais.show();
//
//                // LibraryAssistantUtil.setStageIcon(stage);
//            } catch (IOException ex) {
//                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("=====================");
//                System.out.println(ex);
//                System.out.println("=====================");
//            }
        }
    }

    public Stage chamaTelaStageUnd(String nomeTela, String titulo, String arqCSS) {
        Stage stg = null;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(nomeTela));
            Scene scene = new Scene(parent);
            if (arqCSS.trim() != "") {
                scene.getStylesheets().add(arqCSS);
            }
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle(titulo);
            stage.setScene(scene);
            stg = stage;
            stage.show();

            // LibraryAssistantUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            MyFunc.mostraMsg("Erro 003g ao carregar item de menu", "" + ex, 2);

        }
        return stg;
    }

    public Stage chamaTelaStage(String nomeTela, String titulo, String arqCSS) {
        Stage stg = null;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(nomeTela));
            Scene scene = new Scene(parent);
            if (arqCSS.trim() != "") {
                scene.getStylesheets().add(arqCSS);
            }
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle(titulo);
            stage.setScene(scene);
            stg = stage;
            stage.show();

            // LibraryAssistantUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            MyFunc.mostraMsg("Erro 003g ao carregar item de menu", "" + ex, 2);

        }
        return stg;
    }

    private void chamaLista() {

        // CARREGA VARIÁVEL GLOBAL PARA LISTA DE PRESENÇA
        if (tvGradeD1.getSelectionModel().getSelectedItem() != null) {

            if (tvAluDis.itemsProperty().get().size() == 0) {
                mostraMsg("Não há alunos matriculados nesta disciplina!", "Não é possível gerar a Lista de Presença.", 2);
            } else {

                int cod_cadi = tvGradeD1.getSelectionModel().getSelectedItem().getCadastroDisciplinaId();

                // VERIFICA SE A TELA DA LISTA DA DISCIPLINA JÁ ESTÁ ABERTA. SE ESTIVER, MUDA PARA ELA
                int jaAberta = -1;
                for (int i = 0; i < gbDisci.length; i++) {
                    if (gbDisci[i] == cod_cadi) {
                        jaAberta = i;
                        break;
                    }
                }

                if (jaAberta > -1) {
                    // stage já está aberto. Mudar para ele.
                    aListasPre[jaAberta].show();
                    aListasPre[jaAberta].requestFocus();

                } else {

                    jpaLC = new ListaCabecJpaController();

                    // CARREGA VARIÁVEL GLOBAL PARA LISTA DE PRESENÇA
                    gbListaInfo = new ListaInfo();

                    // stage não está aberto, Abrir.
                    if (contaAbertos == MAXLISTASABERTAS) {
                        mostraMsg("Limite atingido de Listas de Presença abertas simultaneamente.", "Feche uma das listas abertas para abrir outra", 2);
                    } else {

                        List<ListaCabec> lis = jpaLC.getCadDis((cod_cadi));

                        if (lis.isEmpty()) {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText("A Lista de Presença de " + tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc() + " não existe e pode ser criada.");
                            alert.setContentText("\nAtenção!\n\nTodo aluno que for inserido na disciplina DEPOIS que a Lista for criada,\nreceberá uma '?' nas aulas anteriores.\n\n"
                                    + "Confirma a criação desta Lista de Presença?");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get() == ButtonType.OK) {
                                // A LISTA DE PRESENÇA DESTA DISCIPLINA NÃO EXISTE. CRIÁ-LA
                                Cadastrodisciplina reg_cadi = jpaCAD.findCadastrodisciplina(cod_cadi);
                                gbListaInfo.setNomeDis(reg_cadi);

                                ListaCabec reg_cab = new ListaCabec();
                                reg_cab.setNomeCadDisId(reg_cadi);
                                reg_cab.setDataInc(new Date());
                                reg_cab.setCodUserInc(gbUser.getCoduser());

                                jpaLC.create(reg_cab);
                                gbListaCabec = reg_cab;
                                gbListaInfo.setCodLista(reg_cab.getCodLista());
                                gbListaInfo.setNomeDis(jpaCAD.findCadastrodisciplina(cod_cadi));
                                ++contaAbertos;
                                gbDisci[contaAbertos - 1] = cod_cadi;
                                aListasPre[contaAbertos - 1] = chamaTelaStage("/view/ListaPresenca.fxml", "Lista de Presença - " + tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc(), "");
                            }

                        } else {
                            Cadastrodisciplina reg_cadi = jpaCAD.findCadastrodisciplina(cod_cadi);
                            gbListaInfo.setNomeDis(reg_cadi);

                            gbListaInfo.setCodLista(lis.get(0).getCodLista());
                            //   gbListaInfo.setNomeDis(jpaCAD.findCadastrodisciplina(cod_cadi));
                            ++contaAbertos;
                            gbDisci[contaAbertos - 1] = cod_cadi;
                            aListasPre[contaAbertos - 1] = chamaTelaStage("/view/ListaPresenca.fxml", "Lista de Presença - " + tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc(), "");
                        }
                    }

                }

            }
        }
    }

    @FXML
    private void clicouMinhaPesqEnter(KeyEvent event) {
    }

    @FXML
    private void clicouFiltrar(ActionEvent event) {
    }

    @FXML
    private void clicouApagDis(ActionEvent event) {
        /* PAGA PODER EXCLUIR UMA DISCIPLINA DA GRADE, QUE SERÁ APAGADO REGISTRO EM
        CADASTRODISCIPLINA, É NECESSÁRIO:
        - TER AUTORIZAÇÃO
        - NÃO TER ALUNOS CAADSTRADOS NESTA DISCIPLINA (CADASTROALUNODISCIPLINA = 0)
        - VERIFICAR LISTAS DE PRESENÇA
         */
        if (tvGradeD1.getSelectionModel().getSelectedItem() != null) {

            // VERIFICA SE HÁ ALUNOS CADASTRADOS NESTA DISCIPLINA
            List<Cadastroalunodisciplina> listPup = jpaPup.getContaAlunosPup(tvGradeD1.getSelectionModel().getSelectedItem().getCadastroDisciplinaId());
            if (listPup.size() > 0) {
                mostraMsg("Não é possível excluir essa disciplina", "Há " + listPup.size() + " aluno(s) cadastrado(s) nela.", 3);
            } else {
                // VERIFICA SE HÁ LISTA DE PRESENÇA. SE HOUVER, NÃO PODE EXCLUIR
                jpaLC = new ListaCabecJpaController();
                List<ListaCabec> lisLC = jpaLC.getCadDis(tvGradeD1.getSelectionModel().getSelectedItem().getCadastroDisciplinaId());
                if (lisLC.size() > 0) {
                    mostraMsg("Não é possível excluir essa disciplina", "Há " + lisLC.size() + " lista(s) de presença vinculada(s) a ela.", 3);
                } else {
                    // PODE EXCLUIR A DISCIPLINA. PEDE CONFIRMAÇÃO
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Confirma");
                    alert.setContentText("Confirma a gravação dos dados?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get() == ButtonType.OK) {
                        try {
                            jpaCAD = new CadastrodisciplinaJpaController();
                            jpaCAD.destroy(tvGradeD1.getSelectionModel().getSelectedItem().getCadastroDisciplinaId());
                            initColsD();
                            mostraMsg("A disciplina foi apagada com sucesso.", "", 3);

                        } catch (Exception e) {
                            System.out.println(reg_atual);
                            System.out.println("\n============================");
                            System.out.println(e);
                            System.out.println("============================\n");
                            mostraMsg("Erro 053a ao apagar disciplina.", "> " + e, 2);
                        }
                    }
                }
            }

        }
    }

    @FXML
    private void clicouLista(ActionEvent event) {
        chamaLista();
//        String ms = "Listas Abertas";
//        for (int i = 0; i < aDisci.length; i++) {
//            ms = ms + "\n" + i + " - disciplina: " + aDisci[i];
//        }
//        mostraMsg(ms, "", 3);

    }

    @FXML
    private void clicouExpExcel(ActionEvent event) {
//        File file = new File("c:\\temp");
//        try {
//            boolean dirCreated = file.mkdir();
//        } catch (DirectoryIteratorException x) {
//            // IOException can never be thrown by the iteration.
//            // In this snippet, it can only be thrown by newDirectoryStream.
//            System.err.println("Não foi possível criar o diretório c:\\temp!" + "\n" + x);
//        }
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_mm_yyyy__HHmmss");
//        LocalDateTime now = LocalDateTime.now();
//        String xnow = "" + now;
//        String nomeArq = "listagem-" + tvGradeM.getSelectionModel().getSelectedItem().getAnoLetivo() + "."
//                + tvGradeM.getSelectionModel().getSelectedItem().getSemestre() + "-" + tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc() + " - " + xnow + ".xlsx";
//        nomeArq = nomeArq.replaceAll(":", " ");
//        String excelFilePath = "c:/temp/" + nomeArq;
//
//        try {
//            String sqlExcel
//                    = "SELECT dcg.Nome, cur.curso, cad.Descricao, dcg.DataNascimento, pro.NomeProfissao, dcm.Ministeriais "
//                    + " FROM cadastroalunodisciplina pup "
//                    + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId "
//                    + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
//                    + " LEFT JOIN Curso cur ON dcp.CursoId = cur.cursoId  "
//                    + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId "
//                    + " LEFT JOIN profissoes pro ON dcg.codProfissao = pro.CodProfissao "
//                    + " LEFT JOIN dadocadastrogeralministeriais dcm ON dcg.DadoCadastroGeralId = dcm.DadoCadastroGeralId "
//                    + " WHERE cad.codgrade = " + tvGradeD1.getSelectionModel().getSelectedItem().getCodGrade()
//                    + " AND pup.CadastroDisciplinaId = " + tvGradeD1.getSelectionModel().getSelectedItem().getCadastroDisciplinaId()
//                    + " ORDER BY cur.curso, dcg.nome ";
//
//            System.out.println(sqlExcel);
//
//            ResultSet rs = con.createStatement().executeQuery(sqlExcel);
//
//            XSSFWorkbook workbook = new XSSFWorkbook();
//
//            XSSFSheet sheet = workbook.createSheet("Listagem");
//            sheet.setColumnWidth(0, 1500);
//            sheet.setColumnWidth(1, 11000);
//            sheet.setColumnWidth(2, 11000);
//            sheet.setColumnWidth(3, 6500);
//            sheet.setColumnWidth(4, 8000);
//            sheet.setColumnWidth(5, 30000);
//
//            writeHeaderLine(sheet);
//            writeDataLines(rs, workbook, sheet);
//
//            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//            workbook.write(outputStream);
//            workbook.close();
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setHeaderText("A exportação para o Excel feita com sucesso.\nArquivo:\n\n   c:\\temp\\" + nomeArq);
//            alert.setContentText("Deseja abrir o arquivo?");
//            Optional<ButtonType> option = alert.showAndWait();
//
//            if (option.get() == ButtonType.OK) {
//                try {
//                    Desktop.getDesktop().open(new File(excelFilePath));
//                } catch (Exception e) {
//                    mostraMsg(excelFilePath, "Erro 028u ao abrir arquivo\n" + e, 2);
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Erro no Banco de Dados: " + e);
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("File IO error: " + e);
//            e.printStackTrace();
//        }
//
//    }
//
//    private void writeHeaderLine(XSSFSheet sheet) {
//
//        Row cabec = sheet.createRow(0);
//        Cell cabecCell = cabec.createCell(0);
//        cabecCell.setCellValue("Semestre: " + tvGradeM.getSelectionModel().getSelectedItem().getAnoLetivo() + "."
//                + tvGradeM.getSelectionModel().getSelectedItem().getSemestre());
//
//        Row cabec2 = sheet.createRow(1);
//        Cell cabecCell2 = cabec2.createCell(0);
//        cabecCell2.setCellValue("Disciplina: " + tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc());
//
//        Row cabec3 = sheet.createRow(2);
//        Cell cabecCell3 = cabec3.createCell(0);
//        String prof = tvGradeD1.getSelectionModel().getSelectedItem().getProfessor1();
//        if (tvGradeD1.getSelectionModel().getSelectedItem().getProfessor2() != null
//                && tvGradeD1.getSelectionModel().getSelectedItem().getProfessor2().trim().length() > 0) {
//            prof = prof + " e " + tvGradeD1.getSelectionModel().getSelectedItem().getProfessor2();
//        }
//        cabecCell3.setCellValue("Professor(es): " + prof);
//
//        Row headerRow = sheet.createRow(5);
//
//        Cell headerCell = headerRow.createCell(0);
//        headerCell.setCellValue("#");
//
//        headerCell = headerRow.createCell(1);
//        headerCell.setCellValue("Aluno");
//
//        headerCell = headerRow.createCell(2);
//        headerCell.setCellValue("Curso");
//
//        headerCell = headerRow.createCell(3);
//        headerCell.setCellValue("Idade");
//
//        headerCell = headerRow.createCell(4);
//        headerCell.setCellValue("Profissão");
//
//        headerCell = headerRow.createCell(5);
//        headerCell.setCellValue("Ministério");

    }

//    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
//            XSSFSheet sheet) throws SQLException {
//        int rowCount = 6;
//        int numAluno = 0;
//        String minis = "";
//
//        result.next();
//        while (!result.isLast()) { //isAfterLast
//
//            //     while (true) {
//            String rs_nome = result.getString("dcg.Nome");
//            String rs_curso = result.getString("cur.curso");
//            String rs_pro = result.getString("pro.NomeProfissao");
//            String idade = "";
//
//            if (result.getDate("dcg.DataNascimento") != null) {
//                idade = calcularIdade(result.getDate("dcg.DataNascimento"));
//                idade = idade.substring(0, 2) + " anos e " + idade.substring(3, 5) + " mês(es)";
//            }
//
//            minis = "";
//            int contaMin = 0;
//
//            while ((rs_nome.equals(result.getString("dcg.Nome"))) && (!result.isLast())) {
//                if (result.getString("dcm.Ministeriais") != null) {
//                    ++contaMin;
//                    if (contaMin > 1) {
//                        minis = minis + ", " + result.getString("dcm.Ministeriais");
//                    } else {
//                        minis = result.getString("dcm.Ministeriais");
//                    }
//                }
//                result.next();
//            }
//
//            if (result.isLast() && rs_nome.equals(result.getString("dcg.Nome"))) {
//                if (result.getString("dcm.Ministeriais") != null) {
//                    ++contaMin;
//                    if (contaMin > 1) {
//                        minis = minis + ", " + result.getString("dcm.Ministeriais");
//                    } else {
//                        minis = result.getString("dcm.Ministeriais");
//                    }
//                }
//            }
//
//            Row row = sheet.createRow(rowCount++);
//            int columnCount = 0;
//            Cell cell = row.createCell(columnCount++);
//
//            cell.setCellValue(++numAluno);
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(rs_nome);
//
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(rs_curso);
//
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(idade);
//
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(rs_pro);
//
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(minis);
//
//            //  cell = row.createCell(columnCount++);
////            if (result.isLast()) {
////                rs_nome = result.getString("dcg.Nome");
////                rs_curso = result.getString("cur.curso");
////
////                idade = "";
////                if (result.getDate("dcg.DataNascimento") != null) {
////                    idade = calcularIdade(result.getDate("dcg.DataNascimento"));
////
////                    idade = idade.substring(0, 2) + " anos e " + idade.substring(3, 5) + " mês(es)";
////                }
////
////                rs_pro = result.getString("pro.NomeProfissao");
////
////                minis = result.getString("dcm.Ministeriais");
////
////                row = sheet.createRow(rowCount++);
////                columnCount = 0;
////                Cell cell0 = row.createCell(columnCount++);
////                cell0.setCellValue(++numAluno);
////
////                cell0 = row.createCell(columnCount++);
////                cell0.setCellValue(rs_nome);
////
////                cell0 = row.createCell(columnCount++);
////                cell0.setCellValue(rs_curso);
////
////                cell0 = row.createCell(columnCount++);
////                cell0.setCellValue(idade);
////
////                cell0 = row.createCell(columnCount++);
////                cell0.setCellValue(rs_pro);
////
////                cell0 = row.createCell(columnCount++);
////                cell0.setCellValue(minis);
////
////                //      cell0 = row.createCell(columnCount++);
////                break;
////            }
//        }
//    }
    public void xlsx(JasperPrint jasperPrint) throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/UnicodeReport.jrprint");

        //   JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);
        File destFile = new File("/temp2/" + jasperPrint.getName() + ".xlsx");

        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();

        configuration.setOnePagePerSheet(true);
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        System.err.println("XLSX creation time : " + (System.currentTimeMillis() - start));
    }

    public void xlsx_ori() throws JRException {
        long start = System.currentTimeMillis();
        File sourceFile = new File("build/reports/UnicodeReport.jrprint");

        JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

        File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xlsx");

        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();

        configuration.setOnePagePerSheet(true);
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        System.err.println("XLSX creation time : " + (System.currentTimeMillis() - start));
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

    private String limpaNome(String nomeDestFile) {
        nomeDestFile = nomeDestFile.replaceAll(":", "_");
      //  nomeDestFile = nomeDestFile.replaceAll("|", "_");ISSO AQUI DÁ PAU
        nomeDestFile = nomeDestFile.replaceAll("<", "_");
        nomeDestFile = nomeDestFile.replaceAll(">", "_");
        nomeDestFile = nomeDestFile.replaceAll("“", "_");
        nomeDestFile = nomeDestFile.replaceAll("'", "_");
        return nomeDestFile;
    }

    private void geraLista(int tipo) throws JRException, IOException {
        String nomeRepo;
        Listagens lisSele;
        File destFile = null;

        if (cbListagens.getSelectionModel().getSelectedItem() != null) {
            jpaLis = new ListagensJpaController();
            lisSele = jpaLis.getObjeto(cbListagens.getSelectionModel().getSelectedItem());

            nomeRepo = (gbDeOnde == "Jpa_EvedSys_Server") ? lisSele.getJasperServer() : lisSele.getJasperLocal();

            try {

                InputStream jasperF = GradeDisMasController.class.getResourceAsStream("/reporters/" + nomeRepo + ".jasper");

                Map<String, Object> parametros = new HashMap<>();

                Date agora = new Date();
                String fago = GBFORMATDATAHORA.format(agora);
                String professores = tvGradeD1.getSelectionModel().getSelectedItem().getProfessor1();
                if (tvGradeD1.getSelectionModel().getSelectedItem().getProfessor2() != null) {
                    professores += " e " + tvGradeD1.getSelectionModel().getSelectedItem().getProfessor2();
                }

                if (cbListagens.getSelectionModel().getSelectedIndex() == 2) { // PAPELETA
                    parametros.put("CODDIS", tvGradeD1.getSelectionModel().getSelectedItem().getCadastroDisciplinaId());
                    parametros.put("DISCIPLINA", tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc());
                    parametros.put("PROFESSOR", professores);
                    parametros.put("CREDITOS", tvGradeD1.getSelectionModel().getSelectedItem().getCredito());
                    parametros.put("CARGAHORARIA", gbRegCad.getCargaHoraria());
                    parametros.put("ANOLET", gbRegCad.getAnoLetivo());
                    parametros.put("SEMESTRE", gbRegCad.getSemestreId());
                    parametros.put("TURNO", tvGradeD1.getSelectionModel().getSelectedItem().getTurno());
                    parametros.put("DATAHORA", fago);
                } else {
                    parametros.put("DISCIPLINA", tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc());
                    parametros.put("PROFESSOR", professores);
                    parametros.put("DATADIS", tvGradeD1.getSelectionModel().getSelectedItem().getLabelData()
                            + " - " + tvGradeM.getSelectionModel().getSelectedItem().getAnoLetivo() + "." + tvGradeM.getSelectionModel().getSelectedItem().getSemestre());
                    parametros.put("CODDIS", tvGradeD1.getSelectionModel().getSelectedItem().getCadastroDisciplinaId());
                    parametros.put("DATAHORA", fago);
                    parametros.put("CODGRA", tvGradeD1.getSelectionModel().getSelectedItem().getCodGrade());
                    parametros.put("CODGRADE", tvGradeD1.getSelectionModel().getSelectedItem().getCodGrade());
                }

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperF, parametros, con);
                String nomeArq = "";
                ButtonType abrir = new ButtonType("abrir", ButtonBar.ButtonData.OK_DONE);
                ButtonType naoabrir = new ButtonType("não abrir", ButtonBar.ButtonData.CANCEL_CLOSE);
                String nomeArquivoOK = "";
                String nomeLimpo = limpaNome(tvGradeD1.getSelectionModel().getSelectedItem().getNomeDisc());

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

    private Boolean abre_ou_nao_relat(String nomeDestFile, String tipoArq, ButtonType abrir, ButtonType naoabrir) {
        Alert alert = new Alert(AlertType.INFORMATION, "Deseja abrir o arquivo " + nomeDestFile + " agora?", abrir, naoabrir);
        alert.setHeaderText("O arquivo " + tipoArq + " foi gerado com sucesso.");
        alert.setContentText("\nDeseja abrir o arquivo " + nomeDestFile + " agora?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.orElse(naoabrir) == abrir) {
            try {
                Desktop.getDesktop().open(new File(nomeDestFile));
            } catch (Exception e) {
                mostraMsg(nomeDestFile, "Erro 0283u ao abrir arquivo\n" + e, 2);
            }
            return false;
        }
        return true;
    }

    @FXML
    private void clicouFechaPainelListagem(ActionEvent event) {
        pnListagem.setVisible(false);
    }

    @FXML
    private void clicouListagem(ActionEvent event) throws FileNotFoundException {
        if (tvGradeD1.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Antes de gerar listagens, selecione uma disciplina.", "", 3);
        } else {
            pnListagem.setVisible(true);
        }
    }

}
