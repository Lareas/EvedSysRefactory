package controller;

import static main.Login.gbClicouEdiGraDis;
import static main.Login.gbClicouInsGraDis;
import static main.Login.gbCadDis_GridMas;
import static main.Login.gbSoProgsAtivos;
import static main.Login.GBFORMATHORA;
import static main.Login.gbRegCad;
import static main.Login.graM;
import static main.Login.gbCrudProg;
import static main.Login.gbUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import entities.Cadastrodisciplina;
import entities.CalendDis;
import entities.ChecaErros;
import entities.DisProgCateg;
import entities.Disciplina;
import entities.EsGradedisprg;
import entities.EsSalas;
import entities.GraDisAluPro;
import entities.TotAluPro;
import funcoes.ComboBoxAutoComplete2;
import funcoes.DBConnector;
//import static funcoes.MyFunc.checaCombo;
import static funcoes.MyFunc.checaTTF;
import static funcoes.MyFunc.mostraMsg;
import static funcoes.MyFunc.mostraMsgWait;
import static funcoes.MyFunc.verifTTFNum;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.CadastrodisciplinaJpaController;
import jpa_controler.DisciplinaJpaController;
import jpa_controler.EsCategdisJpaController;
import jpa_controler.EsGradeJpaController;
import jpa_controler.EsGradedisprgJpaController;
import jpa_controler.EsSalasJpaController;
import jpa_controler.EsTipodisJpaController;
import jpa_controler.EspecificidadeJpaController;
import jpa_controler.FuncionarioJpaController;
import jpa_controler.LocalidadeJpaController;
import jpa_controler.TurnoJpaController;

public class GradeDisDetController implements Initializable {

    @FXML
    private ComboBox<String> cbDisciplina;
    @FXML
    private ComboBox<String> cbProfessor1;
    @FXML
    private ComboBox<String> cbProfessor2;
    @FXML
    private ComboBox<String> cbTipoDis;
    @FXML
    private ComboBox<String> cbTurno;
    @FXML
    private ComboBox<String> cbArea;
    @FXML
    private ComboBox<String> cbLocalidade;
    @FXML
    private ComboBox<String> cbSala;
    @FXML
    private TableView<CalendDis> tvCalendDis;
    @FXML
    private TableColumn<CalendDis, Integer> tcAula;
    @FXML
    private TableColumn<CalendDis, Date> tcDataCal;
    @FXML
    private TableColumn<CalendDis, String> tcObs;
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
    private JFXCheckBox chkSex;
    @FXML
    private JFXCheckBox chkSab;
    @FXML
    private JFXDatePicker dpDataFim;
    @FXML
    private TextField edRotData;
    @FXML
    private TextField edRotHora;
    @FXML
    private TextField edCred;
    @FXML
    private TextField edCarga;
    @FXML
    private TextArea taObs;
    @FXML
    private TextField edMaxOuv;
    @FXML
    private TextField tfNomeDis;
    @FXML
    private TextField tfAno;
    @FXML
    private TextField tfSem;
    @FXML
    private JFXCheckBox chkQui;
    @FXML
    private TextField edMaxAlunos;
    @FXML
    private TextField edMaxReg;
    @FXML
    private JFXButton buRefDis;
    @FXML
    private JFXButton buRefPro1;
    @FXML
    private JFXButton buRefPro2;
    @FXML
    private TextArea taConsist;
    @FXML
    private TextField tfStatusDados;
    @FXML
    private TextField tfDataInc;
    @FXML
    private TextField tfDataAtu;
    @FXML
    private TableView<DisProgCateg> tvProgsDis;

    @FXML
    private JFXButton buProIns;
    @FXML
    private JFXButton buProApa;
    @FXML
    private Label lblLimSala;
    @FXML
    private TextField tfStatusDados1;
    @FXML
    private TextField edOrdem;
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
    private TextField lblAluDis;
    @FXML
    private ComboBox<String> cbAluDis;
    @FXML
    private TableView<TotAluPro> tvTotAlu;
    @FXML
    private TableColumn<TotAluPro, String> tcTotPro;
    @FXML
    private TableColumn<TotAluPro, Integer> tcTotAlu;
    @FXML
    private TextField lblAluDis1;
    @FXML
    private PieChart pieAluPro;

    @FXML
    private TextField edNomeDisciplina;
    @FXML
    private Label lblGrade;
    @FXML
    private Label lblCodDis;
    @FXML
    private TextField edAvisarCom;

    @FXML
    private TextField lblRegistrosPro;

    private ObservableList<String> dadosCAD;
    private ObservableList<String> dadosLO;
    private ObservableList<String> dadosAR;
    private ObservableList<String> dadosPR;
    private ObservableList<String> dadosTN;
    private ObservableList<String> dadosTD;
    private ObservableList<String> dadosSL;
    private ObservableList<String> dadosPG;
    private ObservableList<EsGradedisprg> dadosPro;
    private ObservableList<String> dadosCDI;
    private ObservableList<Cadastrodisciplina> dadosVerif;

    private LocalidadeJpaController jpaLO;
    private EspecificidadeJpaController jpaAR;
    private FuncionarioJpaController jpaPR;
    private TurnoJpaController jpaTN;
    private EsGradeJpaController jpaGR;

    private EsTipodisJpaController jpaTD;
    private EsSalasJpaController jpaSL;
    private EsGradedisprgJpaController jpaPro;

    ObservableList<GraDisAluPro> oblist;
    ObservableList<TotAluPro> obTot;

    private boolean inserindo, editando;
    public String sqlProgramas;
    public static Date d; // para converter String em data em preencheObj

    public Integer insercaoPendente = null;
    public Connection con = DBConnector.getConnection();

    private ObservableList data;

    public final String SQLBASE
            = "SELECT dcg.nome, cur.curso, pup.media, pup.falta, cs.Descricao as situacao, fr.frequencia, pup.CadastroAlunoDisciplinaId, dcp.DadoCadastroGeralId  "
            + "FROM cadastroalunodisciplina pup  "
            + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId  "
            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
            + "LEFT JOIN Curso cur ON dcp.CursoId = cur.cursoId  "
            + "LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id  "
            + "LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  "
            + "LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId ";

    public final String SQLTOTGRU
            = "SELECT cur.curso, cur.sigla, count(*) as totprograma "
            + "FROM cadastroalunodisciplina pup "
            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId "
            + "LEFT JOIN Curso cur ON dcp.CursoId = cur.cursoId "
            + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId ";

    Cadastrodisciplina reg_cad;
    private CadastrodisciplinaJpaController jpaCAD;
    private DisciplinaJpaController jpaCDI;

    private ComboBox<String> cbCategDis = new ComboBox<>();

    //TableColumn tcNomeProg = new TableColumn("Programas atendidos por esta disciplina");
    TableColumn<DisProgCateg, String> tcCategDis = new TableColumn<>("Categ. Disciplina");

    private ObservableList<String> dadosCat;
    private EsCategdisJpaController jpaCat;
    ObservableList<DisProgCateg> categlist;

    @FXML
    private CheckBox chkAtivos;
    @FXML
    private TableColumn<DisProgCateg, String> tcProgAte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reg_cad = new Cadastrodisciplina();
        jpaCDI = new DisciplinaJpaController();

        buEdita.setVisible(false);
        buInsere.setVisible(false);

        //   buCopia.setVisible(gbUser.getLogin().equals("lfa"));
        tvProgsDis.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        AbreProgramas();
                    }
                }
            }
        });

        //  cbDisciplina.setTooltip(new Tooltip());
        cbDisciplina.setItems(getDisciplinas());
        cbProfessor1.setItems(getProfessores());
        cbProfessor2.setItems(getProfessores());
        cbTipoDis.setItems(getTipoDis());
        cbTurno.setItems(getTurnos());
        cbArea.setItems(getEspecificidades());
        cbLocalidade.setItems(getLocalidades());
        cbSala.setItems(getSalas());
        cbCategDis.setItems(getCategDis());

        new ComboBoxAutoComplete2<String>(cbDisciplina);
        new ComboBoxAutoComplete2<String>(cbProfessor1);

        new ComboBoxAutoComplete2<String>(cbProfessor2);
        new ComboBoxAutoComplete2<String>(cbTipoDis);
        new ComboBoxAutoComplete2<String>(cbTurno);
        new ComboBoxAutoComplete2<String>(cbArea);
        new ComboBoxAutoComplete2<String>(cbLocalidade);
        new ComboBoxAutoComplete2<String>(cbSala);

        // ESTÁ INSERINDO
        if (gbClicouInsGraDis) {

            gbClicouInsGraDis = false;
            reg_cad.setNomeGrade(graM);
            reg_cad.setAnoLetivo(reg_cad.getNomeGrade().getAnoLetivo());
            reg_cad.setSemestreId(reg_cad.getNomeGrade().getSemestre());

            gbRegCad = reg_cad;

            limpaCampos(); // para quem vai requestfocus;
            lblGrade.setText((reg_cad.getNomeGrade() != null) ? reg_cad.getNomeGrade().getCodGrade().toString() : null);
            tfAno.setText((reg_cad.getNomeGrade() != null) ? reg_cad.getNomeGrade().getAnoLetivo().toString() : null);
            tfSem.setText((reg_cad.getNomeGrade() != null) ? reg_cad.getNomeGrade().getSemestre().toString() : null);
            cbLocalidade.getSelectionModel().select("STSC - São Paulo"); // DEFAULT
            edMaxAlunos.setText("100");
            edMaxReg.setText("85");
            edMaxOuv.setText("15");
            edAvisarCom.setText("80");

            ligaEdicao();
            inserindo = true;
            cbDisciplina.requestFocus();
        } else { // ESTÁ EDITANDO
            if (gbClicouEdiGraDis) {
                gbClicouEdiGraDis = false;
                reg_cad = gbRegCad;

                // recupera 
                jpaCAD = new CadastrodisciplinaJpaController();

                reg_cad = jpaCAD.findCadastrodisciplina(gbCadDis_GridMas);

                inserindo = false;
                editando = true;
                initGridProgs();
                initColsCal();
                preencheEdsComObj();
                ligaEdicao();
                cbSala.setItems(getSalas());
                cbAluDis.setItems(getProgramasDistinct());
                cbAluDis.getSelectionModel().select("=> TODOS");
                inictColsGDAP();
                inictColsTot();
            }
        }
    }

    public ObservableList<String> getCategDis() {
        jpaCat = new EsCategdisJpaController();
        dadosCat = FXCollections.observableArrayList(jpaCat.getCategDis());

        if (dadosTD == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosTD;
        }
    }

    public ObservableList<String> getProgramasDistinct() {
        dadosPG = FXCollections.observableArrayList();

        try {
            String sqlProDis = SQLBASE + " WHERE cad.CodGrade = " + graM.getCodGrade()
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

    public void inictColsGDAP() {
        oblist = FXCollections.observableArrayList();
        String filtro = "";
        if (cbAluDis.getSelectionModel().getSelectedItem() != "=> TODOS") {
            filtro = " AND cur.curso = '" + cbAluDis.getSelectionModel().getSelectedItem() + "' ";
        }
        try {
            String sqlFinal = SQLBASE + " WHERE cad.codgrade = " + graM.getCodGrade()
                    + " AND cad.CadastroDisciplinaId = " + reg_cad.getCadastroDisciplinaId()
                    + filtro
                    + " ORDER BY cur.curso, dcg.nome";

            System.out.println(sqlFinal);

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
                        rs.getInt("dcp.DadoCadastroGeralId")));
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

        tcCadastroAlunoDisciplinaId.setCellValueFactory(new PropertyValueFactory<>("cadastroAlunoDisciplinaId"));

        //   TelaAlunosDAO dao = new TelaAlunosDAO();
        tvAluDis.setItems(oblist);
        tvAluDis.refresh();
        lblAluDis.setText("Registros exibidos: " + tvAluDis.itemsProperty().get().size()); //rs2.getString(1));
    }

    public void inictColsTot() {
        obTot = FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();
        //   data = null;

        try {
            String sqlTot = SQLTOTGRU + " WHERE cad.CodGrade = " + graM.getCodGrade()
                    + " AND cad.CadastroDisciplinaId = " + reg_cad.getCadastroDisciplinaId()
                    + " GROUP BY cur.curso "
                    + " ORDER BY cur.curso ";

            System.out.println(sqlTot);

            ResultSet rs = con.createStatement().executeQuery(sqlTot);

            while (rs.next()) {
                obTot.add(new TotAluPro(rs.getString(2), rs.getInt(3)));

                data.add(new PieChart.Data(rs.getString(2) + " (" + rs.getInt(3) + ")", rs.getInt(3)));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tcTotPro.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        tcTotAlu.setCellValueFactory(new PropertyValueFactory<>("totAluno"));

        //   TelaAlunosDAO dao = new TelaAlunosDAO();
        tvTotAlu.setItems(obTot);
        tvTotAlu.refresh();
        lblAluDis1.setText("Total de Alunos: " + tvAluDis.itemsProperty().get().size()); //rs2.getString(1));

        pieAluPro.getData().clear();
        pieAluPro.getData().addAll(data);

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

    public ObservableList<String> getDisciplinas() {

        dadosCDI = FXCollections.observableArrayList(jpaCDI.getNomeDasDisci());

        if (dadosCDI == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosCDI;
        }
    }

    public ObservableList<String> getTipoDis() {
        jpaTD = new EsTipodisJpaController();
        dadosTD = FXCollections.observableArrayList(jpaTD.getNomeTipoDis());

        if (dadosTD == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosTD;
        }
    }

    public ObservableList<String> getSalas() {
        jpaSL = new EsSalasJpaController();
        dadosSL = FXCollections.observableArrayList(jpaSL.getNomeSalas());

        if (dadosSL == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosSL;
        }
    }

    public ObservableList<String> getProfessores1() {
        jpaPR = new FuncionarioJpaController();
        dadosPR = FXCollections.observableArrayList(jpaPR.getNomeDosProfessores());

        if (dadosPR == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosPR;
        }
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

    public ObservableList<String> getEspecificidades() {
        jpaAR = new EspecificidadeJpaController();
        dadosAR = FXCollections.observableArrayList(jpaAR.getNomeDasEspecificidades());

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

//    public ObservableList<String> getTurmas() {
//        jpaTM = new TurmaJpaController();
//        dadosTM = FXCollections.observableArrayList(jpaTM.getNomeTurmas());
//
//        if (dadosTM == null) {
//            return FXCollections.observableArrayList();
//        } else {
//            return dadosTM;
//        }
//    }
    @FXML
    private void clicouInsere(ActionEvent event) {
        reg_cad = new Cadastrodisciplina();

        limpaCampos(); // para quem vai requestfocus;
        ligaEdicao();
        inserindo = true;
        cbDisciplina.requestFocus();
        cbLocalidade.getSelectionModel().select("STSC - São Paulo");
    }

    @FXML
    private void clicouEdita(ActionEvent event) {
        if (cbDisciplina.getSelectionModel().getSelectedItem() != null) {
            ligaEdicao();
            editando = true;
        }
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
                    limpaCampos();    // limpa campos
                    inserindo = false;
                } else {
                    mostraMsg("Mensagem DN", "Mensagem DN-YANSTBH - 018", 2);
                }
            }
            desligaEdicao();
        }
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
    }

    public void limpaCampos() {
        lblGrade.setText(null);
        tfAno.setText(null);
        tfSem.setText(null);
        lblCodDis.setText(null);
        tfNomeDis.setText(null);

        cbDisciplina.setValue(null);
        cbProfessor1.setValue(null);
        cbProfessor2.setValue(null);
        cbTipoDis.setValue(null);
        cbTurno.setValue(null);
        cbArea.setValue(null);
        cbLocalidade.setValue(null);
        cbSala.setValue(null);

        chkDom.selectedProperty().setValue(false);
        chkSeg.selectedProperty().setValue(false);
        chkTer.selectedProperty().setValue(false);
        chkQua.selectedProperty().setValue(false);
        chkQui.selectedProperty().setValue(false);
        chkSex.selectedProperty().setValue(false);
        chkSab.selectedProperty().setValue(false);

        dpDataIni.setValue(null);
        dpDataFim.setValue(null);

        edRotData.setText(null);
        edRotHora.setText(null);
        edHoraIni.setText(null);
        edHoraFim.setText(null);
        edNumAulas.setText(null);
        edCred.setText(null);
        edCarga.setText(null);
        edMaxAlunos.setText(null);
        edMaxReg.setText(null);
        edMaxOuv.setText(null);
        edAvisarCom.setText(null);
        taObs.setText(null);
        tvCalendDis.setItems(null);
    }

    public void preencheEdsComObj() {

        lblGrade.setText((reg_cad.getNomeGrade() != null) ? reg_cad.getNomeGrade().getCodGrade().toString() : null);
        tfAno.setText((reg_cad.getNomeGrade() != null) ? reg_cad.getNomeGrade().getAnoLetivo().toString() : null);
        tfSem.setText((reg_cad.getNomeGrade() != null) ? reg_cad.getNomeGrade().getSemestre().toString() : null);
        lblCodDis.setText((reg_cad.getNomeDisciplina() != null) ? reg_cad.getNomeDisciplina().getDisciplinaId().toString() : null);
        tfNomeDis.setText((reg_cad.getNomeDisciplina() != null) ? reg_cad.getNomeDisciplina().getDisciplina() : null);
        edNomeDisciplina.setText(reg_cad.getDescricao());

        cbDisciplina.getSelectionModel().select((reg_cad.getNomeDisciplina() != null) ? reg_cad.getNomeDisciplina().getDisciplina() : null);
        cbProfessor1.getSelectionModel().select((reg_cad.getNomeProfessor1() != null) ? reg_cad.getNomeProfessor1().getNome() : null);
        cbProfessor2.getSelectionModel().select((reg_cad.getNomeProfessor2() != null) ? reg_cad.getNomeProfessor2().getNome() : null);
        cbTipoDis.getSelectionModel().select((reg_cad.getNomeTipoDis() != null) ? reg_cad.getNomeTipoDis().getTipodisc() : null);
        cbTurno.getSelectionModel().select((reg_cad.getNomeTurno() != null) ? reg_cad.getNomeTurno().getTurno() : null);
        cbArea.getSelectionModel().select((reg_cad.getNomeEspecificidade() != null) ? reg_cad.getNomeEspecificidade().getEspecificidade() : null);
        cbLocalidade.getSelectionModel().select((reg_cad.getNomeLocalidade() != null) ? reg_cad.getNomeLocalidade().getLocalidade() : null);
        cbSala.getSelectionModel().select((reg_cad.getNomeSala() != null) ? reg_cad.getNomeSala().getNomesala() : null);

        chkDom.selectedProperty().setValue((reg_cad.getDomingo() != null) ? reg_cad.getDomingo() == true : null);
        chkSeg.selectedProperty().setValue((reg_cad.getSegunda() != null) ? reg_cad.getSegunda() == true : null);
        chkTer.selectedProperty().setValue((reg_cad.getTerca() != null) ? reg_cad.getTerca() == true : null);
        chkQua.selectedProperty().setValue((reg_cad.getQuarta() != null) ? reg_cad.getQuarta() == true : null);
        chkQui.selectedProperty().setValue((reg_cad.getQuinta() != null) ? reg_cad.getQuinta() == true : null);
        chkSex.selectedProperty().setValue((reg_cad.getSexta() != null) ? reg_cad.getSexta() == true : null);
        chkSab.selectedProperty().setValue((reg_cad.getSabado() != null) ? reg_cad.getSabado() == true : null);

        dpDataIni.setValue((reg_cad.getDataini() != null) ? reg_cad.getDataini().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        dpDataFim.setValue((reg_cad.getDatafim() != null) ? reg_cad.getDatafim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);

        edRotData.setText(reg_cad.getLabelData());
        edRotHora.setText(reg_cad.getLabelHora());

        edHoraIni.setText((reg_cad.getHoraini() != null) ? GBFORMATHORA.format(reg_cad.getHoraini()) : null);
        edHoraFim.setText((reg_cad.getHorafim() != null) ? GBFORMATHORA.format(reg_cad.getHorafim()) : null);

        edNumAulas.setText((reg_cad.getNumAulas() != null) ? String.valueOf(reg_cad.getNumAulas()) : "");
        edCred.setText((reg_cad.getCredito() != null) ? String.valueOf(reg_cad.getCredito()) : "");
        edCarga.setText((reg_cad.getCargaHoraria() != null) ? String.valueOf(reg_cad.getCargaHoraria()) : "");
        edMaxAlunos.setText((reg_cad.getLimitetotal() != null) ? String.valueOf(reg_cad.getLimitetotal()) : "");
        edMaxReg.setText((reg_cad.getLimiteregular() != null) ? String.valueOf(reg_cad.getLimiteregular()) : "");
        edMaxOuv.setText((reg_cad.getLimiteouvinte() != null) ? String.valueOf(reg_cad.getLimiteouvinte()) : "");
        edAvisarCom.setText((reg_cad.getAvisarCom() != null) ? String.valueOf(reg_cad.getAvisarCom()) : "");
        edOrdem.setText((reg_cad.getOrdemLista() != null) ? String.valueOf(reg_cad.getOrdemLista()) : "");
        taObs.setText((reg_cad.getObs() != null) ? String.valueOf(reg_cad.getObs()) : "");
        tvCalendDis.setItems(null);

        tfStatusDados.setText(reg_cad.getStatusMsg());
        tfStatusDados1.setText(reg_cad.getStatusMsg());

    }

    public void ligaEdicao() {
        // habilita botões para inserção e deleção
        buInsere.setDisable(true);
        buEdita.setDisable(true);
        buConfirma.setDisable(false);
        buCancela.setDisable(false);
        buApaga.setDisable(true);

        // combos
        cbDisciplina.setDisable(false);
        cbProfessor1.setDisable(false);
        cbProfessor2.setDisable(false);
        cbTipoDis.setDisable(false);
        cbTurno.setDisable(false);
        cbArea.setDisable(false);
        cbLocalidade.setDisable(false);
        cbSala.setDisable(false);

        buRefDis.setDisable(false);
        buRefPro1.setDisable(false);
        buRefPro2.setDisable(false);

        chkDom.setDisable(false);
        chkSeg.setDisable(false);
        chkTer.setDisable(false);
        chkQua.setDisable(false);
        chkQui.setDisable(false);
        chkSex.setDisable(false);
        chkSab.setDisable(false);

        dpDataIni.setEditable(true);
        dpDataFim.setEditable(true);

        // habilita campos
        edRotData.setEditable(true);
        edHoraIni.setEditable(true);
        edHoraFim.setEditable(true);
        edRotHora.setEditable(true);
        edNumAulas.setEditable(true);
        edCred.setEditable(true);
        edCarga.setEditable(true);
        edMaxAlunos.setEditable(true);
        edMaxReg.setEditable(true);
        edMaxOuv.setEditable(true);
        edAvisarCom.setEditable(true);
        taObs.setEditable(true);

        tvCalendDis.setDisable(false);
    }

    public void desligaEdicao() {
        // habilita botões para inserção e deleção
        buInsere.setDisable(false);
        buEdita.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buApaga.setDisable(false);

        // combos
        cbDisciplina.setDisable(true);
        cbProfessor1.setDisable(true);
        cbProfessor2.setDisable(true);
        cbTipoDis.setDisable(true);
        cbTurno.setDisable(true);
        cbArea.setDisable(true);
        cbLocalidade.setDisable(true);
        cbSala.setDisable(true);

        cbDisciplina.setStyle("-fx-opacity: 1;");
        cbProfessor1.setStyle("-fx-opacity: 1;");
        cbProfessor2.setStyle("-fx-opacity: 1;");
        cbTipoDis.setStyle("-fx-opacity: 1;");
        cbTurno.setStyle("-fx-opacity: 1;");
        cbArea.setStyle("-fx-opacity: 1;");
        cbLocalidade.setStyle("-fx-opacity: 1;");
        cbSala.setStyle("-fx-opacity: 1;");
        tvCalendDis.setStyle("-fx-opacity: 1;");

        buRefDis.setDisable(true);
        buRefPro1.setDisable(true);
        buRefPro2.setDisable(true);

        chkDom.setDisable(true);
        chkSeg.setDisable(true);
        chkTer.setDisable(true);
        chkQua.setDisable(true);
        chkQui.setDisable(true);
        chkSex.setDisable(true);
        chkSab.setDisable(true);

        dpDataIni.setEditable(false);
        dpDataFim.setEditable(false);

        // habilita campos
        edRotData.setEditable(false);
        edHoraIni.setEditable(false);
        edHoraFim.setEditable(false);
        edRotHora.setEditable(false);
        edNumAulas.setEditable(false);
        edCred.setEditable(false);
        edCarga.setEditable(false);
        edMaxAlunos.setEditable(false);
        edMaxReg.setEditable(false);
        edMaxOuv.setEditable(false);
        edAvisarCom.setEditable(false);
        taObs.setEditable(false);

        tvCalendDis.setDisable(true);
    }

//    private void clicouTFFAulas(KeyEvent event) {
//        chamaTTF2(edNumAulas, "0123456789", "##");
//    }
//
//    private void clicouTFFCreditos(KeyEvent event) {
//        chamaTTF2(edCred, "0123456789", "#");
//    }
//
//    private void clicouTFFCarga(KeyEvent event) {
//        chamaTTF2(edCarga, "0123456789", "##");
//    }
//
//    private void clicouTFFMaxAlu(KeyEvent event) {
//        chamaTTF2(edMaxAlunos, "0123456789", "###");
//    }
//
//    private void clicouTFFMaxReg(KeyEvent event) {
//        chamaTTF2(edMaxReg, "0123456789", "###");
//    }
//
//    private void clicouTFFMaxOuv(KeyEvent event) {
//        chamaTTF2(edMaxOuv, "0123456789", "##");
//    }
    @FXML
    private void clicouConfirma(ActionEvent event) {
        ChecaErros check = new ChecaErros();
        taConsist.setText("");

        if (cbDisciplina.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Disciplina> não pode ficar vazio.\n");
        }
        if (cbProfessor1.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Professor 1> não pode ficar vazio.\n");
        }
        if (cbTurno.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Turno> não pode ficar vazio.\n");
        }
        if (cbTipoDis.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Tipo da Disciplina> não pode ficar vazio.\n");
        }
        if (cbArea.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Área> não pode ficar vazio.\n");
        }
        if (cbLocalidade.getSelectionModel().getSelectedItem() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** O campo <Localidade> não pode ficar vazio.\n");
        }

//        checaCombo("Disciplina", 1, cbDisciplina, check);
//        checaCombo("Professor 1", 1, cbProfessor1, check);
//        checaCombo("Turno", 1, cbTurno, check);
//        checaCombo("Tipo da Disciplina", 1, cbTipoDis, check);
//        checaCombo("Área", 2, cbArea, check);
//        checaCombo("Localidade", 2, cbLocalidade, check);
        checaTTF("Rótulo Datas", 1, edRotData, check);
        checaTTF("Hora início", 1, edHoraIni, check);
        checaTTF("Hora FIm", 1, edHoraFim, check);
        checaTTF("Rótulo Horário", 1, edRotHora, check);

        checaTTF("Nº aulas", 1, edNumAulas, check);
        checaTTF("Créditos", 1, edCred, check);
        checaTTF("Carga Horária", 2, edCarga, check);
        checaTTF("Máx Alunos", 2, edMaxAlunos, check);
        checaTTF("Máx Reg", 2, edMaxReg, check);
        checaTTF("Máx Ouv.", 2, edMaxOuv, check);
        checaTTF("Avisar com", 2, edAvisarCom, check);

        verifTTFNum("Nº aulas", 1, edNumAulas, check, true, 1, 30);
        verifTTFNum("Créditos", 1, edCred, check, true, 0, 4);
        //   verifTTFNumFloat("Carga Horária", 1, edCarga, check, true, 1, 80);
        verifTTFNum("Ordem na Lista", 1, edOrdem, check, true, 1, 45);
        verifTTFNum("Máx Alunos", 1, edMaxAlunos, check, true, 1, 200);
        verifTTFNum("Máx. Reg.", 1, edMaxReg, check, true, 1, 200);
        verifTTFNum("Máx. Ouv", 1, edMaxOuv, check, true, 1, 40); // parametrizar (arqsys) futuramente
        verifTTFNum("Avisar com", 1, edAvisarCom, check, true, 1, 100); // parametrizar (arqsys) futuramente

        if (dpDataIni.getValue() == null) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** A Data Inicial deve ser preenchida.\n");
        }

        if (dpDataFim.getValue() == null) {
            check.setCtw(check.getCtw() + 1);
            check.setWarn(check.getWarn() + "- A Data Final deve ser preenchida.\n");
        }

        // checar horas hh:mm
//        if (!checaHora(edHoraIni.getText())) {
//            check.setCte(check.getCte() + 1);
//            check.setWarn(check.getWarn() + "- A Hora Inicial parece estar incorreta.\n");
//        }
//
//        if (!checaHora(edHoraFim.getText())) {
//            check.setCte(check.getCte() + 1);
//            check.setWarn(check.getWarn() + "- A Hora Final parece estar incorreta.\n");
//        }
        if (check.getCte() + check.getCtw() > 0) {
            taConsist.setText("ATENÇÃO! \n" + "HÁ " + check.getCte() + " ERRO(S). \n"
                    + "HÁ " + check.getCtw() + " AVISO(S). \n\n" + check.getWarn());
            tfStatusDados.setText("DADOS INCOMPLETOS");
            tfStatusDados.setStyle("-fx-background-color : #FBA588;");
            tfStatusDados1.setText("DADOS INCOMPLETOS");
            tfStatusDados1.setStyle("-fx-background-color : #FBA588;");
            mostraMsgWait("ATENÇÃO! \n" + "HÁ " + check.getCte() + " ERRO(S). \n"
                    + "HÁ " + check.getCtw() + " AVISO(S). \n\n", check.getWarn(), (check.getCte() > 0) ? 2 : 4);
//            reg_det.setStatusdados("DADOS INCOMPLETOS"); // DADOS INCOMPLETOS
        } else {
            tfStatusDados.setText("DADOS COMPLETOS");
            tfStatusDados.setStyle("-fx-background-color : #ABD0BC;");
            tfStatusDados1.setText("DADOS COMPLETOS");
            tfStatusDados1.setStyle("-fx-background-color : #ABD0BC;");
//            reg_det.setStatusdados("DADOS COMPLETOS"); // DADOS INCOMPLETOS
        }

        if (professoresIguais()) {
            check.setCte(check.getCte() + 1);
            check.setWarn(check.getWarn() + "*** Professor 1 e Professor 2 não podem er iguais.\n");
        }

        if (check.getCte() == 0) {
            // NÃO HAVENDO ERROS, PODE TENTAR GRAVAR, MESMO COM AVISOS

            if (!verifDescricao(edNomeDisciplina.getText())) {
                mostraMsg("Não é possível gravar!", "Já existe uma disciplina com essa descrição.", 2);
            } else {
                if (!verifPodeGravar(lblCodDis.getText())) {
                    mostraMsg("Não é possível gravar!", "A disciplina, com mesma data, horário e professor, já faz parte desta grade.", 2);
                } else {
                    // PODE GRAVAR!
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setHeaderText("Confirma");
                    alert.setContentText("Confirma a gravação dos dados?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get() == ButtonType.OK) {

                        if (editando == true) {
                            try {
                                preencheObjComEd(reg_cad, true);
                                gbRegCad = reg_cad;
                                gbRegCad.setDataAlt(new Date());
                                gbRegCad.setCodUserAlt(gbUser.getCoduser());
                                jpaCAD.edit(gbRegCad);
                                editando = false;

                                desligaEdicao();
                                initGridProgs();
                                insercaoPendente = null;
                                mostraMsg("Dados gravados com sucesso", "", 3);

                                // FECHA JANELA
                                Stage stage = (Stage) buConfirma.getScene().getWindow();
                                stage.close();

                            } catch (Exception e) {
                                System.out.println(reg_cad);
                                System.out.println("\n============================");
                                System.out.println(e);
                                System.out.println("============================\n");
                                mostraMsg("Erro 043 ao gravar a edição dos dados.", "> " + e, 2);
                            }
                        } else {
                            if (inserindo == true) {
                                try {

                                    Cadastrodisciplina obj = new Cadastrodisciplina();

                                    preencheObjComEd(obj, false);
                                    obj.setNomeGrade(graM);
                                    obj.setAnoLetivo(graM.getAnoLetivo());
                                    obj.setSemestreId(graM.getSemestre());
                                    obj.setDataInc(new Date());
                                    obj.setCodUserInc(gbUser.getCoduser());

                                    reg_cad = obj;
                                    jpaCAD.create(obj);
                                    reg_cad = obj;
                                    gbRegCad = reg_cad;

                                    lblGrade.setText(String.valueOf(reg_cad.getNomeGrade().getCodGrade()));
                                    inserindo = false;

                                    desligaEdicao();
                                    tvCalendDis.setItems(null);
                                    tvProgsDis.setItems(null);
                                    insercaoPendente = null;

                                    mostraMsg("Dados gravados com sucesso!", "", 3);
                                    // FECHA JANELA
                                    Stage stage = (Stage) buConfirma.getScene().getWindow();
                                    stage.close();

                                } catch (Exception e) {
                                    System.out.println(reg_cad);
                                    System.out.println("\n============================");
                                    System.out.println(e);
                                    System.out.println("============================\n");
                                    mostraMsg("Erro 014a ao gravar novos dados.", "> " + e, 2);
                                }
                            } else {
                                mostraMsg("Mensagem DN2a", "Mensagem DN-YANot - 012", 2);
                            }
                        }

                    }

                }
            }
        }

    }

    private void preencheObjComEd(Cadastrodisciplina x, Boolean preenchePK) {
        if (cbDisciplina.getSelectionModel().getSelectedItem() != null) {
            x.setDisciplina(cbDisciplina.getSelectionModel().getSelectedItem());
            if (preenchePK) {
                //x.setNomeGrade().(Integer.valueOf(lblGrade.getText()));
            }
        }

        x.setDescricao(edNomeDisciplina.getText());

//        if (lblGrade.getText() == null) {
//            mostraMsg("Erro 40 em GradeDisDetController", "Resultado inesperado", 2);
//        } else {
//            jpaGR = new EsGradeJpaController();
//            x.setNomeGrade(jpaGR.findEsGrade(Integer.valueOf(lblGrade.getText())));
//        }
        if (cbDisciplina.getSelectionModel().getSelectedItem() != null) {
            jpaCDI = new DisciplinaJpaController();
            x.setNomeDisciplina(jpaCDI.getObjeto(cbDisciplina.getSelectionModel().getSelectedItem()));
        }

        if (cbProfessor1.getSelectionModel().getSelectedItem() != null) {
            jpaPR = new FuncionarioJpaController();
            x.setNomeProfessor1(jpaPR.getObjeto(cbProfessor1.getSelectionModel().getSelectedItem()));
        }

        if (cbProfessor2.getSelectionModel().getSelectedItem() != null) {
            jpaPR = new FuncionarioJpaController();
            x.setNomeProfessor2(jpaPR.getObjeto(cbProfessor2.getSelectionModel().getSelectedItem()));
        }

        if (cbTipoDis.getSelectionModel().getSelectedItem() != null) {
            jpaTD = new EsTipodisJpaController();
            x.setNomeTipoDis(jpaTD.getObjeto(cbTipoDis.getSelectionModel().getSelectedItem()));
        }

        if (cbTurno.getSelectionModel().getSelectedItem() != null) {
            jpaTN = new TurnoJpaController();
            x.setNomeTurno(jpaTN.getObjeto(cbTurno.getSelectionModel().getSelectedItem()));
        }

        if (cbArea.getSelectionModel().getSelectedItem() != null) {
            jpaAR = new EspecificidadeJpaController();
            x.setNomeEspecificidade(jpaAR.getObjeto(cbArea.getSelectionModel().getSelectedItem()));
        }

        if (cbLocalidade.getSelectionModel().getSelectedItem() != null) {
            jpaLO = new LocalidadeJpaController();
            x.setNomeLocalidade(jpaLO.getObjeto(cbLocalidade.getSelectionModel().getSelectedItem()));
        }

        x.setDomingo((chkDom.isSelected()));
        x.setSegunda((chkSeg.isSelected()));
        x.setTerca((chkTer.isSelected()));
        x.setQuarta((chkQua.isSelected()));
        x.setQuinta((chkQui.isSelected()));
        x.setSexta((chkSex.isSelected()));
        x.setSabado((chkSab.isSelected()));

        if (dpDataIni.getValue() != null) {
            x.setDataini(Date.from(dpDataIni.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (dpDataFim.getValue() != null) {
            x.setDatafim(Date.from(dpDataFim.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        x.setLabelData(edRotData.getText());

        try {
            java.sql.Date hora = new java.sql.Date(GBFORMATHORA.parse(edHoraIni.getText()).getTime());
            x.setHoraini(hora);
        } catch (ParseException e) {
            // log the exception
            mostraMsg("Erro 042 - formato do campo <Hora inicial> incorreto", "" + e, 0);
        }

        try {
            java.sql.Date hora = new java.sql.Date(GBFORMATHORA.parse(edHoraFim.getText()).getTime());
            x.setHorafim(hora);
        } catch (ParseException e) {
            // log the exception
            mostraMsg("Erro 042 - formato do campo <Hora final> incorreto", "" + e, 0);
        }

        x.setLabelHora(edRotHora.getText());

        x.setNumAulas(valorShort(edNumAulas.getText()));
        x.setCredito(valorShort(edCred.getText()));
        x.setCargaHoraria(new BigDecimal(edCarga.getText()));
        x.setLimitetotal(valorShort(edMaxAlunos.getText()));
        x.setLimiteregular(valorShort(edMaxReg.getText()));
        x.setLimiteouvinte(valorShort(edMaxOuv.getText()));
        x.setAvisarCom(valorShort(edAvisarCom.getText()));
        x.setOrdemLista(valorShort(edOrdem.getText()));

        if (cbSala.getSelectionModel().getSelectedItem() != null) {
            jpaSL = new EsSalasJpaController();
            x.setNomeSala(jpaSL.getObjeto(cbSala.getSelectionModel().getSelectedItem()));
        }

        if (taObs.getText() != null && taObs.getText().trim() != "") {
            x.setObs(taObs.getText().getBytes());
        }
    }

    private Short valorShort(String campo) {
        Short xa = 0;
        if ((campo == null) || ((campo.trim().equals("")))) {
            return xa;
        } else {
            return Short.valueOf(campo.trim());
        }
    }

    public ObservableList<EsGradedisprg> getNomeProgramas() {
        jpaPro = new EsGradedisprgJpaController();
        dadosPro = FXCollections.observableArrayList(jpaPro.getListProgs(reg_cad.getCadastroDisciplinaId()));

        if (dadosPro == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosPro;
        }
    }

    public void initColsCal() {

    }

    @FXML
    private void clicouInserePro(ActionEvent event) {
        if (reg_cad.getNomeGrade() == null) {
            mostraMsg("Aviso", "Por favor, grave os dados da disciplina antes de prosseguir", 4);
        } else {
            gbSoProgsAtivos = chkAtivos.selectedProperty().getValue();
            chamaCrudPro(1);
        }
    }

    private void clicouEditaPro(ActionEvent event) {
        if (tvProgsDis.getSelectionModel().getSelectedItem() != null) {
            gbSoProgsAtivos = chkAtivos.selectedProperty().getValue();
            AbreProgramas();
        }
    }

    private void AbreProgramas() {
        //    gbNomeCrudPro = tvProgsDis.getSelectionModel().getSelectedItem().getMinisteriais();
        chamaCrudPro(2);
    }

    private void chamaCrudPro(int crud) {
        gbCrudProg = crud;
        gbRegCad = reg_cad;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CrudProgramas.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Programas");
            stage.setScene(scene);

            stage.showAndWait();
            initGridProgs();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 004a ao carregar CrudProgramas", "" + ex, 0);
        }
    }

    @FXML
    private void clicouApagaPro(ActionEvent event) {
        if (tvProgsDis.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            //  alert.setHeaderText("Remover o programa <" + tvProgsDis.getSelectionModel().getSelectedItem().+ "> desta disciplina ?");
            alert.setContentText("Deseja realmente apagar este registro?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaPro = new EsGradedisprgJpaController();

                    Integer qual = jpaPro.getCodgradedisprg(gbRegCad.getCadastroDisciplinaId(), tvProgsDis.getSelectionModel().getSelectedItem().getNmpro());
                    jpaPro.destroy(qual);

//                    desligaEdicao();
//                    limpaCampos();
                    initGridProgs();
                    mostraMsg("Dados apagados com sucesso", "", 3);
//                    // FECHA JANELA
//                    Stage stage = (Stage) buConfirma.getScene().getWindow();
//                    stage.close();
                } catch (Exception e) {
                    mostraMsg("Erro 045 ao apagar registro", "" + e, 2);
                }
            }
        }
    }

    @FXML
    private void mudouTipoDisciplina(ActionEvent event) { // configura semana e horários, de acordo com o tipo da disciplina
        fillDatasHoras();
    }

    @FXML
    private void mudouDisciplina(ActionEvent event) {
        tfNomeDis.setText(cbDisciplina.getValue());
        Disciplina dis_selec = new Disciplina();
        dis_selec = jpaCDI.getObjeto(cbDisciplina.getValue());
        edNomeDisciplina.setText(dis_selec.getDisciplina());

        if (dis_selec.getCredito() != null) {
            edCred.setText(String.valueOf(dis_selec.getCredito()));
        }

        if (dis_selec.getNomeEspecificidade() != null) {
            cbArea.getSelectionModel().select(dis_selec.getNomeEspecificidade().getEspecificidade());
            cbArea.getSelectionModel().select(dis_selec.getNomeEspecificidade().getEspecificidade());
        }

        if (dis_selec.getDisciplinaId() != null) {
            lblCodDis.setText(String.valueOf(dis_selec.getDisciplinaId()));
        }

    }

    private Short pegaCodigoDis(String nomeDis) {
        DisciplinaJpaController jpaCDI = new DisciplinaJpaController();
        return jpaCDI.getCodigoDisci(nomeDis);
    }

    @FXML
    private void mudouTurno(ActionEvent event) {
        fillDatasHoras();
    }

    @FXML
    private void mudouSala(ActionEvent event) {
        jpaSL = new EsSalasJpaController();
        EsSalas sl = jpaSL.getObjeto(cbSala.getValue());
        if (sl != null) {
            lblLimSala.setText("(" + sl.getLimitefisico() + " lugares)");
        } else {
            lblLimSala.setText("");
        }
    }

    private void fillDatasHoras() {

        if (cbTipoDis.getSelectionModel().getSelectedItem() != null) {

            String tipoDis = cbTipoDis.getValue();

            switch (tipoDis) {

                case "DMin":
                    chkDom.selectedProperty().setValue(false);
                    chkSeg.selectedProperty().setValue(true);
                    chkTer.selectedProperty().setValue(true);
                    chkQua.selectedProperty().setValue(true);
                    chkQui.selectedProperty().setValue(true);
                    chkSex.selectedProperty().setValue(false);
                    chkSab.selectedProperty().setValue(false);

                    edHoraIni.setText("08:00");
                    edHoraFim.setText("18:30");
                    edNumAulas.setText("4");
                    edCarga.setText("80");
                    edRotHora.setText("Segunda a Quinta");
                    // edCarga.setText("35");
                    cbSala.setValue("Sala 5");

                    jpaSL = new EsSalasJpaController();
                    EsSalas sl = jpaSL.getObjeto(cbSala.getValue());
                    if (sl != null) {
                        lblLimSala.setText("(" + sl.getLimitefisico() + " lugares)");
                    } else {
                        lblLimSala.setText("");
                    }
                    break;
                case "Regular":
                    fillHoras();
                    fillRotData();
                    edCarga.setText("80");
                    edNumAulas.setText("16");
                    edCred.setText("4");
                    break;
                case "Intensivo":
                    cbTurno.setValue("Noturno");
                    edHoraIni.setText("19:00");
                    edHoraFim.setText("22:30");
                    edRotHora.setText("19h às 22h30");
                    edCred.setText("2");
                    edCarga.setText("40");
                    edNumAulas.setText("5");
                    chkDom.selectedProperty().setValue(false);
                    chkSeg.selectedProperty().setValue(true);
                    chkTer.selectedProperty().setValue(true);
                    chkQua.selectedProperty().setValue(true);
                    chkQui.selectedProperty().setValue(true);
                    chkSex.selectedProperty().setValue(true);
                    chkSab.selectedProperty().setValue(false);
                    break;
                case "CBCM":
                    cbTurno.setValue("Noturno");
                    edHoraIni.setText("19:00");
                    edHoraFim.setText("22:30");
                    edRotHora.setText("19h às 22h30");
                    edCred.setText("0");
                    edCarga.setText("17");
                    edNumAulas.setText("5");
                    chkDom.selectedProperty().setValue(false);
                    chkSeg.selectedProperty().setValue(true);
                    chkTer.selectedProperty().setValue(true);
                    chkQua.selectedProperty().setValue(true);
                    chkQui.selectedProperty().setValue(true);
                    chkSex.selectedProperty().setValue(true);
                    chkSab.selectedProperty().setValue(false);
                    break;
                default:
                    break;

            }
        }
    }

    private void fillRotData() {
        edRotData.setText((chkDom.selectedProperty().getValue() == true) ? "Domingo" : "");
        edRotData.setText((chkSeg.selectedProperty().getValue() == true) ? "Segunda" : "");
        edRotData.setText((chkTer.selectedProperty().getValue() == true) ? "Terça" : "");
        edRotData.setText((chkQua.selectedProperty().getValue() == true) ? "Quarta" : "");
        edRotData.setText((chkQui.selectedProperty().getValue() == true) ? "Quinta" : "");
        edRotData.setText((chkSex.selectedProperty().getValue() == true) ? "Sexta" : "");
        edRotData.setText((chkSab.selectedProperty().getValue() == true) ? "Sábado" : "");
    }

    private void fillHoras() {
        if (cbTurno.getSelectionModel().getSelectedItem() != null) {
            if (cbTurno.getValue().equals("Diurno")) {
                edHoraIni.setText("08:00");
                edHoraFim.setText("11:30");
                edRotHora.setText("8h às 11h30");
            } else {
                if (cbTurno.getValue().equals("Noturno")) {
                    edHoraIni.setText("19:00");
                    edHoraFim.setText("22:30");
                    edRotHora.setText("19h às 22h30");
                }
            }
        }
    }

    private Boolean professoresIguais() {
        if (cbProfessor1.getValue() != null) {
            if (cbProfessor1.getValue().equals(cbProfessor2.getValue())) {
                return true;
                //mostraMsg("Professor repetido!", "Professor 1 e Professor 2 não podem er iguais", 2);
            }
        }
        return false;
    }

    private Boolean verifDescricao(String descricao) {
        Boolean podegravar = true;
        
        jpaCAD = new CadastrodisciplinaJpaController();
        List lis = jpaCAD.getJaTemDescri(descricao, Integer.valueOf(lblGrade.getText()));
        
        Cadastrodisciplina cadi;
        if (editando == true) {
            
            for (int i = 0; i < lis.size(); i++) {
                cadi = (Cadastrodisciplina) lis.get(i);
                if (!cadi.getCadastroDisciplinaId().equals(reg_cad.getCadastroDisciplinaId())) {
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

    private Boolean verifPodeGravar(String codDis) { //verifica se a disciplina já está cadastrada com mesmo dia, horário e grade

        if (codDis == null) {
            mostraMsg("codDis = null!", "Mensagem DN-YANot - 045", 2);
            return false;
        }

        if (lblGrade.getText() == null) {
            mostraMsg("tfCodGrade = null!", "Mensagem DN-YANot - 045B", 2);
            return false;
        }

        Boolean podegravar = true;

        jpaCAD = new CadastrodisciplinaJpaController();
        dadosVerif = FXCollections.observableArrayList(
                jpaCAD.getVerifJaExiste(Integer.valueOf(lblGrade.getText()), Integer.valueOf(codDis)));

        if (dadosVerif.isEmpty()) {
            return true;
        } else {

            Cadastrodisciplina cad_disg;
            for (int i = 0; i < dadosVerif.size(); i++) {
                cad_disg = (Cadastrodisciplina) dadosVerif.get(i);

                // se for a própria, pula
                if (cad_disg.getNomeGrade().equals(reg_cad.getNomeGrade())) {
                    continue;
                }

                // AQUI VAI ENTRAR UMA PORÇÃO DE IFs
                if ((cad_disg.getHoraini().equals(reg_cad.getHoraini())
                        && (cad_disg.getHorafim().equals(reg_cad.getHorafim())
                        && (cad_disg.getDataini().equals(reg_cad.getDataini())
                        && (cad_disg.getSegunda().equals(reg_cad.getSegunda())
                        && (cad_disg.getTerca().equals(reg_cad.getTerca())
                        && (cad_disg.getQuarta().equals(reg_cad.getQuarta())
                        && (cad_disg.getQuinta().equals(reg_cad.getQuinta())
                        && (cad_disg.getSexta().equals(reg_cad.getSexta())
                        && (cad_disg.getSabado().equals(reg_cad.getSabado())
                        && (cad_disg.getNomeProfessor1().getNome().equals(reg_cad.getNomeProfessor1().getNome())))))))))))) {
                    podegravar = false;
                }
            }
            return podegravar;
        }
    }

    @FXML
    private void cliiclouLimparProf2(ActionEvent event) {
        cbProfessor2.getSelectionModel().clearSelection();
    }

    @FXML
    private void clicouLimpaDisci(ActionEvent event) {
        //     cbDisciplina.getSelectionModel().clearSelection();
    }

    @FXML
    private void cliiclouLimparProf1(ActionEvent event) {
        cbProfessor1.getSelectionModel().clearSelection();
    }

    @FXML
    private void clicouLimpaTipoDis(ActionEvent event) {
        cbTipoDis.getSelectionModel().clearSelection();
    }

    @FXML
    private void clicouLimpaTurno(ActionEvent event) {
        cbTurno.getSelectionModel().clearSelection();
    }

    @FXML
    private void clicouLimpaArea(ActionEvent event) {
        cbArea.getSelectionModel().clearSelection();
    }

    @FXML
    private void clicouLimpaLocal(ActionEvent event) {
        cbLocalidade.getSelectionModel().clearSelection();
    }

    @FXML
    private void clicouLimpaSala(ActionEvent event) {
        cbSala.getSelectionModel().clearSelection();
    }

    @FXML
    private void clicouDom(MouseEvent event) {
        if (chkDom.isSelected()) {
            edRotData.setText("Domingo");
        }
    }

    @FXML
    private void clicouSeg(MouseEvent event) {
        if (chkSeg.isSelected()) {
            edRotData.setText("Segunda");
        }
    }

    @FXML
    private void clicouTer(MouseEvent event) {
        if (chkTer.isSelected()) {
            edRotData.setText("Terça");
        }
    }

    @FXML
    private void clicouQua(MouseEvent event) {
        if (chkQua.isSelected()) {
            edRotData.setText("Quarta");
        }
    }

    @FXML
    private void clicouQui(MouseEvent event) {
        if (chkQui.isSelected()) {
            edRotData.setText("Quinta");
        }
    }

    @FXML
    private void clicouSex(MouseEvent event) {
        if (chkSex.isSelected()) {
            edRotData.setText("Sexta");
        }
    }

    @FXML
    private void clioouSab(MouseEvent event) {
        if (chkSab.isSelected()) {
            edRotData.setText("Sábado");
        }
    }

    @FXML
    private void cbAluDisAction(ActionEvent event) {
        inictColsGDAP();
    }

    public void initGridProgs() {

        List<String> listCategDis = cbCategDis.getItems();
        String[] categs = new String[listCategDis.size()];
        categs = listCategDis.toArray(categs);

        tcCategDis.setCellValueFactory(cellData -> cellData.getValue().categProperty());
        tcCategDis.setCellFactory(ComboBoxTableCell.forTableColumn(categs));
        tcCategDis.setMinWidth(200);
        ComboBoxTableCell.forTableColumn(categs);

        tcProgAte.setCellValueFactory(new PropertyValueFactory<>("nompro"));
        tcProgAte.setMinWidth(300);

        //   tvProgsDis.getColumns().addAll(tcNomeProg, tcCategDis);
        //   tvProgsDis.getColumns().addAll(tcProgAte);
        //    tvProgsDis.setEditable(true);
        if (reg_cad.getCadastroDisciplinaId() > 0) {
            tvProgsDis.setItems(getProgDis());
        }
        lblRegistrosPro.setText("Programas Listados: " + tvProgsDis.itemsProperty().get().size());
    }

    public ObservableList<DisProgCateg> getProgDis() {
        categlist = FXCollections.observableArrayList();
        if (reg_cad.getCadastroDisciplinaId() > 0) {
            String sqlDisProg
                    = " SELECT cur.Curso, cat.CategDis "
                    + " FROM es_gradedisprg prg "
                    + " LEFT JOIN Curso cur ON prg.codprograma = cur.cursoId "
                    + " LEFT JOIN es_categdis cat ON prg.CategDis = cat.CodCategDis "
                    + " WHERE prg.CadastroDisciplinaId  = " + reg_cad.getCadastroDisciplinaId();
            try {
                System.out.println(sqlDisProg);
                ResultSet rs = con.createStatement().executeQuery(sqlDisProg);
                while (rs.next()) {

                    categlist.add(new DisProgCateg(rs.getString("cur.Curso"),
                            rs.getString("cat.CategDis")
                    ));
                }

            } catch (SQLException ex) {
                Logger.getLogger(GradeDisDetController.class.getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 034m - em getProgDis()", "" + ex, 2);
            }
        }
        return categlist;
    }

    @FXML
    private void mudouDataIni(ActionEvent event) {
        if (cbTipoDis.getSelectionModel().getSelectedItem() != null) {

            String tipoDis = cbTipoDis.getValue();
            Calendar calendar = Calendar.getInstance();

            switch (tipoDis) {
                case "DMin":
                    calendar.setTime((Date.from(dpDataIni.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                    calendar.add(Calendar.DATE, 3);
                    dpDataFim.setValue(calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    break;
                case "CBCM":
                    calendar.setTime((Date.from(dpDataIni.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                    calendar.add(Calendar.DATE, 4);
                    dpDataFim.setValue(calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    break;
                case "Intensivo":
                    calendar.setTime((Date.from(dpDataIni.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                    calendar.add(Calendar.DATE, 4);
                    dpDataFim.setValue(calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    break;
                case "Regular":
                    calendar.setTime((Date.from(dpDataIni.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                    calendar.add(Calendar.DATE, 7 * 16);
                    dpDataFim.setValue(calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    break;
            }

        }
    }

}
