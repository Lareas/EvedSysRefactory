/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.ESVERSION;
import static main.Login.gbRegCadGer;
import static main.Login.gbClicouInsere;
import static main.Login.gbUltSem;
import entities.TelaAluno;
import funcoes.ComboBoxAutoComplete;
import funcoes.DBConnector;
import funcoes.MyFunc;
import static funcoes.MyFunc.corrigeCaixaNomes;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.CursoJpaController;
import jpa_controler.DadocadastrogeralJpaController;
import jpa_controler.DadocadastroprogramasituacaoJpaController;
import jpa_controler.DenominacaoJpaController;
import jpa_controler.FormacaoJpaController;
import jpa_controler.MinisteriaisJpaController;
import jpa_controler.ProfissoesJpaController;
import jpa_controler.ProgramaJpaController;

//import com.itextpdf.text.Anchor;
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chapter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.List;
//import com.itextpdf.text.ListItem;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Section;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import jpa_controler.EsGradeJpaController;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class DadocadastrogeralSimplesController implements Initializable {

    @FXML
    private TableView<TelaAluno> tvPesquisa;
    @FXML
    private TableColumn<TelaAluno, String> tcAluno;
    @FXML
    private TableColumn<TelaAluno, String> tcPrograma;
    @FXML
    private TableColumn<TelaAluno, String> tcCurso;
    @FXML
    private TableColumn<TelaAluno, String> tcSituacao;
    @FXML
    private TableColumn<TelaAluno, Integer> tcAnoLetivo; // anoletivo
    @FXML
    private TableColumn<TelaAluno, Integer> tcSemestre; // semestre
    @FXML
    private TableColumn<TelaAluno, Date> tcDataCadastro; // datacadastro
    @FXML
    private TableColumn<TelaAluno, String> tcLocalidade; // localidade
    @FXML
    private TableColumn<TelaAluno, String> tcCelular; // telefonecelular
    @FXML
    private TableColumn<TelaAluno, String> tcEmailPessoal; // email pessoal
    @FXML
    private TextField edAluno;
    @FXML
    private ComboBox<String> cbProgramas;
    @FXML
    private ComboBox<String> cbCursos;
    @FXML
    private ComboBox<String> cbSituacoes;
//    private JFXCheckBox chk1Sem;
//    private JFXCheckBox chk2Sem;
//    private TextField edAnoLetivo;
    @FXML
    private TextField lblRegistros;

    @FXML
    private Label lblFiltrados;

    @FXML
    private TextField edMatri;
    @FXML
    private TextField edCodAlu;
    @FXML
    private TableColumn<?, ?> tcNumProg;
    @FXML
    private TableColumn<?, ?> tcMatri;
    @FXML
    private TableColumn<?, ?> tcCodigo;

    public Integer codGrade;

    // --------------------
    // VARIÁVEIS LOCAIS
    // --------------------
    TelaAluno reg_atual = new TelaAluno();
    //  private ObservableList<Dadocadastrogeral> dados;
    private DadocadastrogeralJpaController jpaCon;

    private ObservableList<String> dadosPro;
    private ProgramaJpaController jpaPro;

    private ObservableList<String> dadosCur;
    private CursoJpaController jpaCur;

    private ObservableList<String> dadosSit;
    private DadocadastroprogramasituacaoJpaController jpaSit;

    private ObservableList<String> dadosFor;
    private FormacaoJpaController jpaFor;

    private ObservableList<String> dadosProf;
    private ProfissoesJpaController jpaProf;

    private ObservableList<String> dadosMin;
    private MinisteriaisJpaController jpaMin;

    private ObservableList<String> dadosDen;
    private DenominacaoJpaController jpaDen;

    ObservableList<TelaAluno> oblist; // = FXCollections.observableArrayList();
    public Connection con = DBConnector.getConnection();
    public int semHoje, mesHoje, anoHoje;
    public String filtroAtivos;

    public static Stage stgDadosCad;
    private ObservableList<String> dadosGra;
    private EsGradeJpaController jpaGra;

    private static String FILE = "c:/EvedSys/FirstPdf.pdf";
//    private static Font catFont = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);
//    private static Font redFont = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.RED);
//    private static Font subFont = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
//    private static Font smallBold = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

    public String condicao = "";
    @FXML
    private ComboBox<String> cbGrade;

    public static final Boolean LIMPO = true;
    public static final Boolean FILTRAR = false;
    public String sqlLIMPO
            = "SELECT dcg.DadoCadastroGeralId, dcg.Matricula, dcg.nome, pro.programa as titulacao, count(*) as numprogs,   \n"
            + "   cur.curso as programa, sit.DadoCadastroProgramaSituacao, dcp.anoLetivo, dcp.semestreId, dcp.datacadastro,   \n"
            + "   dcp.localidade, dcg.telefonecelular, dcg.EmailPessoal   \n"
            + "   \n"
            + "   FROM dadocadastroprograma dcp \n"
            + "   LEFT JOIN curso cur ON dcp.cursoId = cur.cursoId \n"
            + "   LEFT JOIN programa pro ON cur.ProgramaId = pro.ProgramaId   \n"
            + "   LEFT JOIN dadocadastroprogramasituacao sit ON dcp.DadoCadastroProgramaSituacaoId = sit.DadoCadastroProgramaSituacaoId  \n"
            + "   RIGHT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId   \n";

    String sqlFILTRA = " SELECT dcg.DadoCadastroGeralId, dcg.Matricula, dcg.nome, pro.programa as titulacao, count(*) as numprogs,  \n"
            + "   cur.curso as programa, sit.DadoCadastroProgramaSituacao, dcp.anoLetivo, dcp.semestreId, dcp.datacadastro,  \n"
            + "   dcp.localidade, dcg.telefonecelular, dcg.EmailPessoal  \n"
            + "   FROM cadastroalunodisciplina pup  \n"
            + "   LEFT JOIN dadocadastroprograma dcp ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId  \n"
            + "   LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId  \n"
            + "   LEFT JOIN curso cur ON dcp.cursoId = cur.cursoId  \n"
            + "   LEFT JOIN programa pro ON cur.ProgramaId = pro.ProgramaId  \n"
            + "   LEFT JOIN dadocadastroprogramasituacao sit ON dcp.DadoCadastroProgramaSituacaoId = sit.DadoCadastroProgramaSituacaoId \n";

    String sqlGroupLimpo = "  GROUP BY dcg.DadoCadastroGeralId \n "
            + "ORDER BY dcg.DadoCadastroGeralId DESC ";

    String sqlGroupFiltro = " \nGROUP BY dcg.DadoCadastroGeralId, dcp.DadoCadastroProgramaId ";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jpaGra = new EsGradeJpaController();
        jpaPro = new ProgramaJpaController();
        jpaCon = new DadocadastrogeralJpaController();
        jpaSit = new DadocadastroprogramasituacaoJpaController();
        jpaCur = new CursoJpaController();

        cbGrade.setItems(getGradeMS());

        // SELECIONA SEMESTRE ATUAL
        String semAtual = gbUltSem.getUltAno() + "." + gbUltSem.getUltSem();
        cbGrade.getSelectionModel().select(semAtual);
        codGrade = jpaGra.getCodGradePorConcat(cbGrade.getSelectionModel().getSelectedItem());

        //  geraPDF();
        tcDataCadastro.setCellFactory(column -> {
            TableCell<TelaAluno, Date> cell = new TableCell<TelaAluno, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

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

        tvPesquisa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        AbreCadastro();
                    }
                }
            }
        });

//        edAnoLetivo.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d{0,4}")) {
//                    edAnoLetivo.setText(oldValue);
//                }
//            }
//        });
        // DETERMINA SEMESTRE PARA MARCAR PESQUISA
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());

        mesHoje = cal.get(Calendar.MONTH) + 1;
        anoHoje = cal.get(Calendar.YEAR);
        semHoje = 0;

        cbProgramas.setItems(getNomeProgramas());
        cbCursos.setItems(getNomeCursos());
        cbSituacoes.setItems(getNomeSituacoes());

//        if (!gbUser.getLogin().equals("lfa")) {
////            edAnoLetivo.setText(String.valueOf(anoHoje));
////
////            if (mesHoje > 6) {
////                chk2Sem.selectedProperty().setValue(true);
////                semHoje = 2;
////            } else {
////                chk1Sem.selectedProperty().setValue(true);
////                semHoje = 1;
////            }
//
//            filtroAtivos = " AND dcp.anoLetivo = " + anoHoje + " AND dcp.semestreId = " + semHoje + " AND dcp.DadoCadastroProgramaSituacaoId = 2";
//            cbSituacoes.getSelectionModel().select("Ativo");
//            condicao = filtroAtivos;
//            initColumns(false);
//
//        } else {
        cbProgramas.setItems(getNomeProgramas());
        cbCursos.setItems(getNomeCursos());
        cbSituacoes.setItems(getNomeSituacoes());

        filtrar();
//        condicao = filtroAtivos;
//        initColumns(true);
        //  }

        new ComboBoxAutoComplete<String>(cbProgramas, "");
        new ComboBoxAutoComplete<String>(cbCursos, "");
        new ComboBoxAutoComplete<String>(cbSituacoes, "");
    }

    public ObservableList<String> getGradeMS() {
        dadosGra = FXCollections.observableArrayList(jpaGra.getGradeMS());
        dadosGra.add(0, "[TODOS]");

        if (dadosGra == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosGra;
        }
    }

    @FXML
    private void clicouAbre(ActionEvent event) {
        AbreCadastro();
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
            stage.setTitle(titulo + " - " + ESVERSION);
            stage.setScene(scene);
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

    public void AbreCadastro() {
        if (tvPesquisa.getSelectionModel().getSelectedItem() != null) {
            reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();

            //PASSA REGISTRO SELECIONADO PARA A VARIÁVEL GLOBAL rgRegDisc, que será usado na tela TabelaDadocadastrogerals
            gbRegCadGer = jpaCon.findDadocadastrogeral(reg_atual.getCodAluno());
//            stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "/MeuCSS/cssDadosCad.css");
            stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "");

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

    public void initColumns(Boolean limpa) {
        oblist = FXCollections.observableArrayList();

        String sqlFinal = "";
        if ((limpa) || (cbGrade.getSelectionModel().getSelectedItem() == "[TODOS]")) {
            limpa = false;
            sqlFinal = sqlLIMPO;
            if (condicao.trim().length() > 0) {
                String con = condicao.substring(4, condicao.trim().length() + 1);
                sqlFinal = sqlFinal + " \nWHERE " + con + " \n" + sqlGroupLimpo;
            } else {
                sqlFinal = sqlFinal + " \n" + sqlGroupLimpo;
            }
        } else {
            sqlFinal = sqlFILTRA;
            if (condicao.trim().length() > 0) {
                String con = condicao.substring(4, condicao.trim().length() + 1);
                sqlFinal = sqlFinal + " \nWHERE " + con + " \n" + sqlGroupFiltro;
            } else {
                sqlFinal = sqlFinal + " \n" + sqlGroupFiltro;
            }
        }

        try {

            // ENVIA SQL PARA CLIPBOARD
            ClipboardContent content = new ClipboardContent();
            content.putString(sqlFinal);
            //content.putHtml("<b>Bold</b> text");
            Clipboard.getSystemClipboard().setContent(content);

            System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println(sqlFinal);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@\n");

            ResultSet rs = con.createStatement().executeQuery(sqlFinal);

            while (rs.next()) {
                oblist.add(new TelaAluno(
                        rs.getString("Matricula"),
                        rs.getInt("DadoCadastroGeralId"),
                        rs.getString("nome"),
                        rs.getString("titulacao"),
                        rs.getString("programa"),
                        rs.getShort("numprogs"),
                        rs.getString("DadoCadastroProgramaSituacao"),
                        rs.getShort("anoLetivo"),
                        rs.getShort("semestreId"),
                        rs.getDate("datacadastro"),
                        rs.getString("Localidade"),
                        rs.getString("telefoneCelular"),
                        rs.getString("emailPessoal")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralSimplesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tcMatri.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codAluno"));
        tcAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        tcPrograma.setCellValueFactory(new PropertyValueFactory<>("titulacao"));
        tcCurso.setCellValueFactory(new PropertyValueFactory<>("programa"));
        tcNumProg.setCellValueFactory(new PropertyValueFactory<>("numprogs"));
        tcSituacao.setCellValueFactory(new PropertyValueFactory<>("situacaoNome"));
        tcAnoLetivo.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tcSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        tcDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        tcLocalidade.setCellValueFactory(new PropertyValueFactory<>("Localidade"));
        tcCelular.setCellValueFactory(new PropertyValueFactory<>("telefoneCelular"));
        tcEmailPessoal.setCellValueFactory(new PropertyValueFactory<>("emailPessoal"));

        //   TelaAlunosDAO dao = new TelaAlunosDAO();
        tvPesquisa.setItems(oblist);
        tvPesquisa.refresh();
        String spa = "    ";
        int totAlunos = jpaCon.getDadocadastrogeralCount();
        lblRegistros.setText("Registros exibidos: " + tvPesquisa.itemsProperty().get().size() + spa + "|" + spa + "Total de Alunos: " + totAlunos); //rs2.getString(1));
    }

    @FXML
    private void clicouFiltra(ActionEvent event) {
        filtrar();
    }

    @FXML
    private void clicouLimpa(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmação");
        alert.setContentText("Deseja limpar a pesquisa?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {

            // LIMPA TODAS AS CONDIÇÕES
            condicao = "";
            cbGrade.getSelectionModel().select("[TODOS]");
            codGrade = 0;

            initColumns(LIMPO);

            // limpar campos e seleção dos combos
            edAluno.setText("");
//            edAnoLetivo.setText("");
//
//            chk1Sem.selectedProperty().setValue(false);
//            chk2Sem.selectedProperty().setValue(false);

            cbProgramas.getSelectionModel().clearSelection();
            cbSituacoes.getSelectionModel().clearSelection();
            cbCursos.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void clicouFiltraAt(ActionEvent event) {

        /* O botão FILTRA ATIVOS filtra os seguintes critérios:
        Ano ATUAL
        Semestre ATUAL
        dcp.DadoCadastroProgramaSituacaoId = 2 --> ATIVOS
         */
        condicao = filtroAtivos;
        initColumns(FILTRAR);

        // limpar campos e seleção dos combos
        edAluno.setText("");
//        edAnoLetivo.setText(String.valueOf(anoHoje));
//
//        if (semHoje == 1) {
//            chk1Sem.selectedProperty().setValue(true);
//            chk2Sem.selectedProperty().setValue(false);
//        } else {
//            chk1Sem.selectedProperty().setValue(false);
//            chk2Sem.selectedProperty().setValue(true);
//        }

        cbProgramas.getSelectionModel().clearSelection();
        cbSituacoes.getSelectionModel().select("Ativo");
        cbCursos.getSelectionModel().clearSelection();
    }

    public void filtrar() {
        String pesq = "";

        if (edCodAlu.getText().trim().length() > 0) {
            pesq = pesq + " AND dcg.DadoCadastroGeralId LIKE '%" + edCodAlu.getText() + "%' \n";
        }

        if (edMatri.getText().trim().length() > 0) {
            pesq = pesq + " AND dcg.Matricula LIKE '%" + edMatri.getText() + "%' \n";
        }

        if (edAluno.getText().trim().length() > 0) {
            pesq = pesq + " AND dcg.nome LIKE '%" + edAluno.getText() + "%' \n";
        }

        if ((cbProgramas.getSelectionModel().getSelectedItem() != null) && (cbProgramas.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND pro.programa = '" + cbProgramas.getSelectionModel().getSelectedItem() + "' \n";
        }

        if ((cbCursos.getSelectionModel().getSelectedItem() != null) && (cbCursos.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND cur.curso = '" + cbCursos.getSelectionModel().getSelectedItem() + "' \n";
        }

        if ((cbSituacoes.getSelectionModel().getSelectedItem() != null) && (cbSituacoes.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND prs.DadoCadastroProgramaSituacao = '" + cbSituacoes.getSelectionModel().getSelectedItem() + "' \n";
        }

        if ((cbGrade.getSelectionModel().getSelectedItem() != null) && (cbGrade.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND pup.codgrade = " + codGrade + "\n";
        }

        condicao = pesq;
        initColumns(FILTRAR);
    }

    public ObservableList<String> getNomeProgramas() {

        dadosPro = FXCollections.observableArrayList(jpaPro.getNomeDosProgramas());
        dadosPro.add(0, "[TODOS]");

        if (dadosPro == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosPro;
        }
    }

    public ObservableList<String> getNomeSituacoes() {

        dadosSit = FXCollections.observableArrayList(jpaSit.getNomeDasSituacoes());
        dadosSit.add(0, "[TODOS]");

        if (dadosSit == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosSit;
        }
    }

    public ObservableList<String> getNomeCursos() {

        dadosCur = FXCollections.observableArrayList(jpaCur.getNomeDosCursos());
        dadosCur.add(0, "[TODOS]");

        if (dadosCur == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosCur;
        }
    }

    @FXML
    private void clicouInsere(ActionEvent event) {
        gbClicouInsere = true;
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/DadosCadastrais.fxml"));
            Stage stageCadastrais = new Stage(StageStyle.DECORATED);
            stageCadastrais.initModality(Modality.WINDOW_MODAL);
            stageCadastrais.setTitle("Dados Cadastrais do Aluno" + ESVERSION);
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

    @FXML
    private void clicouTvPesquisa(MouseEvent event) {

    }

    @FXML
    private void clicouEnter(KeyEvent event) {
//        edAluno.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });

//        edMatri.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });
//        
//        edCodAlu.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });
//        
//        edAnoLetivo.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });
//        
//        chk1Sem.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });
//        
//        chk2Sem.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });
//        
//        cbCursos.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });
//        
//        cbProgramas.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });
//        
//        cbSituacoes.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent ke) {
//                if (ke.getCode().equals(KeyCode.ENTER)) {
//                    filtrar();
//                }
//            }
//        });
    }

    @FXML
    private void onActionClicar(ActionEvent event) {
        filtrar();
    }

    @FXML
    private void clicouMatri(KeyEvent event) {
        edAluno.setText("");
        edCodAlu.setText("");
    }

    @FXML
    private void clicouCod(KeyEvent event) {
        edAluno.setText("");
        edMatri.setText("");
    }

    @FXML
    private void clicouExpPDF(ActionEvent event) {
        geraPDF();
    }

    private void geraPDF() {
        try {
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(FILE));
//            document.open();
//            addMetaData(document);
//            addTitlePage(document);
//            addContent(document);
//            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static void addMetaData(Document document) {
//        // PROPRIEDADES DO DOCUMENTO
//        document.addTitle("EvedSys - Listagem de Pesquisa");
//        document.addSubject("Listagem de Pesquisa");
//        document.addKeywords("EvedSysy, PDF, Pesquisa");
//        document.addAuthor("EvedSys - STSC");
//        document.addCreator("Sistema EvedSysy - STSC");
//    }
//    private static void addTitlePage(Document document) throws DocumentException {
////        Paragraph preface = new Paragraph();
////        // We add one empty line
////        addEmptyLine(preface, 1);
////        // Lets write a big header
////        preface.add(new Paragraph("Listagem de Pesquisa", catFont));
////
////        addEmptyLine(preface, 1);
////        // Will create: Report generated by: _name, _date
////        preface.add(new Paragraph("Gerado por: " + gbUser.getLogin() + ", " + new Date(), smallBold));
////        addEmptyLine(preface, 1);
////        document.add(preface);
//
//    }
//    private static void addContent(Document document) throws DocumentException {
//        Anchor anchor = new Anchor("First Chapter", catFont);
//        anchor.setName("First Chapter");
//
//        // Second parameter is the number of the chapter
//        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
//
//        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
//        Section subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("Hello"));
//
////        subPara = new Paragraph("Subcategory 2", subFont);
////        subCatPart = catPart.addSection(subPara);
////        subCatPart.add(new Paragraph("Paragraph 1"));
////        subCatPart.add(new Paragraph("Paragraph 2"));
////        subCatPart.add(new Paragraph("Paragraph 3"));
//        // add a list
//        //   createList(subCatPart);
//        Paragraph paragraph = new Paragraph();
//        addEmptyLine(paragraph, 1);
//        subCatPart.add(paragraph);
//
//        // add a table
//        createTable(subCatPart);
//
//        // now add all this to the document
//        document.add(catPart);
//
////        // Next section
////        anchor = new Anchor("Second Chapter", catFont);
////        anchor.setName("Second Chapter");
////
////        // Second parameter is the number of the chapter
////        catPart = new Chapter(new Paragraph(anchor), 1);
////
////        subPara = new Paragraph("Subcategory", subFont);
////        subCatPart = catPart.addSection(subPara);
////        subCatPart.add(new Paragraph("This is a very important message"));
//        // now add all this to the document
//        //  document.add(catPart);
//    }
//    private static void createTable(Section subCatPart)
//            throws BadElementException {
//        PdfPTable table = new PdfPTable(3);
//
//        // t.setBorderColor(BaseColor.GRAY);
//        // t.setPadding(4);
//        // t.setSpacing(4);
//        // t.setBorderWidth(1);
//        PdfPCell c1 = new PdfPCell(new Phrase("Aluno"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Programa"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("E-mail"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Fone"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Profissão"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        table.setHeaderRows(1);
//
//        table.addCell("1.0");
//        table.addCell("1.1");
//        table.addCell("1.2");
//        table.addCell("1.3");
//        table.addCell("1.4");
//        table.addCell("2.1");
//        table.addCell("2.2");
//        table.addCell("2.3");
//        table.addCell("d");
//        table.addCell("e");
//
//        subCatPart.add(table);
//
//    }
//    private static void createList(Section subCatPart) {
//        List list = new List(true, false, 10);
//        list.add(new ListItem("First point"));
//        list.add(new ListItem("Second point"));
//        list.add(new ListItem("Third point"));
//        subCatPart.add(list);
//    }
//    private static void addEmptyLine(Paragraph paragraph, int number) {
//        for (int i = 0; i < number; i++) {
//            paragraph.add(new Paragraph(" "));
//        }
//    }
    private void clicouExpExcel(ActionEvent event) throws FileNotFoundException {
        oblist = FXCollections.observableArrayList();

        String sql = "SELECT dcg.DadoCadastroGeralId, dcg.Matricula, dcg.nome, pro.programa as titulacao, count(*) as numprogs, cur.curso as programa,  prs.DadoCadastroProgramaSituacao, "
                + " dcp.anoLetivo, dcp.semestreId, dcp.datacadastro, dcp.localidade, dcg.telefonecelular, dcg.EmailPessoal "
                + " FROM dadocadastrogeral ger "
                + "  LEFT JOIN dadocadastroprograma dcp ON dcg.dadocadastrogeralid = dcp.DadoCadastroGeralId "
                + "  LEFT JOIN curso cur ON dcp.cursoId = cur.cursoId "
                + "  LEFT JOIN programa pro ON cur.ProgramaId = pro.ProgramaId "
                + "  LEFT JOIN dadocadastroprogramasituacao prs ON dcp.DadoCadastroProgramaSituacaoId = prs.DadoCadastroProgramaSituacaoId ";

        String sql2 = "  \nGROUP BY dcg.DadoCadastroGeralId ";

        String sqlFinal = "";
        if (condicao.trim().length() > 0) {
            String con = condicao.substring(4, condicao.trim().length() + 1);
            sqlFinal = sql + " \nWHERE " + con + " " + sql2;
        } else {
            sqlFinal = sql + " " + sql2;
        }

        String excelFilePath = "Reviews-export.xlsx";

        System.out.println("\n\n\n>>>> " + sqlFinal + " <<<\n\n\n");
        System.out.println("condicao = " + condicao);
        System.out.println("sql2 = " + sql2);

        try {

            ResultSet rs = con.createStatement().executeQuery(sqlFinal);

//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet sheet = workbook.createSheet("Reviews");
//
//            writeHeaderLine(sheet);
//            writeDataLines(rs, workbook, sheet);
//
//            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//            workbook.write(outputStream);
//            workbook.close();
            while (rs.next()) {
                oblist.add(new TelaAluno(
                        rs.getString("Matricula"),
                        rs.getInt("DadoCadastroGeralId"),
                        rs.getString("nome"),
                        rs.getString("titulacao"),
                        rs.getString("programa"),
                        rs.getShort("numprogs"),
                        rs.getString("DadoCadastroProgramaSituacao"),
                        //                        rs.getString("nomeformacao"),
                        //                        rs.getString("nomeprofissao"),
                        //                        rs.getString("Ministeriais"),
                        //                        rs.getString("Denominacao"),
                        rs.getShort("anoLetivo"),
                        rs.getShort("semestreId"),
                        rs.getDate("datacadastro"),
                        rs.getString("Localidade"),
                        rs.getString("telefoneCelular"),
                        rs.getString("emailPessoal")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralSimplesController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//
//            
//
//        } catch (IOException e) {
//            System.out.println("File IO error:");
//            e.printStackTrace();
//        }
    }

//    private void writeHeaderLine(XSSFSheet sheet) {
//
//        Row headerRow = sheet.createRow(0);
//
//        Cell headerCell = headerRow.createCell(0);
//        headerCell.setCellValue("Aluno");
//
//        headerCell = headerRow.createCell(1);
//        headerCell.setCellValue("Curso");
//
//        headerCell = headerRow.createCell(2);
//        headerCell.setCellValue("Disciplina");
//
//        headerCell = headerRow.createCell(3);
//        headerCell.setCellValue("Idade");
//
//        headerCell = headerRow.createCell(4);
//        headerCell.setCellValue("Profissão");
//
//        headerCell = headerRow.createCell(5);
//        headerCell.setCellValue("Ministério");
//
//    }
//    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
//            XSSFSheet sheet) throws SQLException {
//        int rowCount = 1;
//
//        while (result.next()) {
//            String courseName = result.getString("course_name");
//            String studentName = result.getString("student_name");
//            float rating = result.getFloat("rating");
//            Timestamp timestamp = result.getTimestamp("timestamp");
//            String comment = result.getString("comment");
//
//            Row row = sheet.createRow(rowCount++);
//
//            int columnCount = 0;
//            Cell cell = row.createCell(columnCount++);
//            cell.setCellValue(courseName);
//
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(studentName);
//
//            cell = row.createCell(columnCount++);
//
//            CellStyle cellStyle = workbook.createCellStyle();
//            CreationHelper creationHelper = workbook.getCreationHelper();
//            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//            cell.setCellStyle(cellStyle);
//
//            cell.setCellValue(timestamp);
//
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(rating);
//
//            cell = row.createCell(columnCount);
//            cell.setCellValue(comment);
//
//        }
//    }
    @FXML
    private void mudouGrade(ActionEvent event) {
        if ((cbGrade.getSelectionModel().getSelectedItem() != null) && (cbGrade.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            codGrade = jpaGra.getCodGradePorConcat(cbGrade.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void clicouCCN(ActionEvent event) {
        for (TelaAluno a : this.tvPesquisa.getItems()) {
            String nome = a.getNomeAluno();
            System.out.println("++ " + nome);
            System.out.println(">> " + corrigeCaixaNomes(nome));
            System.out.println("");

        }
    }
}
