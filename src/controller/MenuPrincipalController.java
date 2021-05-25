/*--
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.ESVERSION;
import static main.Login.gbUser;
import static main.Login.gbSenhaOk;
import static main.Login.gbDeOnde;
import static main.Login.gbRegCadGer;
import static main.Login.gbUltSem;
import static main.Login.gbPlacarInfo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import static controller.DadocadastrogeralSimplesController.stgDadosCad;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import de.jensd.fx.glyphs.octicons.OctIconView;
import de.jensd.fx.glyphs.weathericons.WeatherIconView;
import entities.Arqsys;
import entities.PlacarInfo;
import entities.Tabuser;
import entities.UltSemestre;
import funcoes.DBConnector;
import funcoes.MyFunc;
import static funcoes.MyFunc.mostraMsg;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.ArqsysJpaController;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.DadocadastrogeralJpaController;
import jpa_controler.TabuserJpaController;
//import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private FontAwesomeIconView faiSino;
    @FXML
    private FontAwesomeIconView faiHome;
    @FXML
    private ImageView ivUser;
    @FXML
    private Label lblUser;
    @FXML
    private FontAwesomeIconView faiCadeado;
    @FXML
    private Label txtSTSC;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private MenuItem mnTabProg;
    @FXML
    private MenuItem mnTabDisc;
    @FXML
    private FontAwesomeIconView fa1;
    @FXML
    private MaterialDesignIconView md1;
    @FXML
    private MaterialIconView mi1;
    @FXML
    private OctIconView oi1;
    @FXML
    private WeatherIconView wi1;
    @FXML
    private TextField tf1;
    @FXML
    private MenuItem mnSair1;
    @FXML
    private MenuItem mnAbout1;
    @FXML
    private Menu mnTabBas;
    @FXML
    private Menu mnTabMas;
    @FXML
    private Menu mnCad;
    @FXML
    private Menu mnConf;
    @FXML
    private Menu mnSobre;

    @FXML
    private Label lblVersao;
    //private Label lblArquivoJPA;
    @FXML
    private Label lblHoje;
    @FXML
    private AnchorPane apBarraSup;
    @FXML
    private MenuBar mnMenuBar;
    @FXML
    private MenuItem mnConsist;
    @FXML
    private Label lblJPA;
    @FXML
    private MenuItem mnSecretCase;
    @FXML
    private MenuItem mnDocInsc;

    @FXML
    private MenuItem mnOutrosDocs;
    @FXML
    private Menu mnAca;
    @FXML
    private MenuItem mnGrade;
    @FXML
    private MenuItem mnTipoDis;
    @FXML
    private MenuItem mnDataNascSophia;
    @FXML
    private MenuItem mnInspetor;
//    private Label lblUltAnoSem;
//
//    private Label lblMatri;
//    private Label lblNovos;
    @FXML
    private AnchorPane apPlacar1;
    @FXML
    private Label lblUltAnoSem1;
    @FXML
    private Label lblUltAnoSem11;
    @FXML
    private Label lblUltAnoSem111;
    @FXML
    private Label lblUltAnoSem1111;

    public Connection con = DBConnector.getConnection();
    public static Stage stgCurso;
    public static Stage stgUsers;
    private ArqsysJpaController jpaCon;
    TabuserJpaController jpaUser;
    Tabuser reg_atual = new Tabuser();

    CadastroalunodisciplinaJpaController jpaPup;

    private DadocadastrogeralJpaController jpaCon2;

    public static Connection con2 = DBConnector.getConnection();
    public static Stage stgGradeMaster;

    private String SQLMATRI
            = " SELECT dcg.nome, cur.curso, dcp.anoletivo, dcp.semestreid "
            + " FROM dadocadastroprograma dcp "
            + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId "
            + " LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId ";
//    + " WHERE dcp.anoletivo = 2020 AND dcp.semestreid = 2;
    @FXML
    private MenuItem mnAbout11;
    @FXML
    private Label lblUltAnoSem12;
    @FXML
    private Label lblUltAnoSem11111;
    @FXML
    private JFXButton buSem1Sem;
    @FXML
    private JFXButton buSem1Reg;
    @FXML
    private JFXButton buSem1Esp;
    @FXML
    private JFXButton buSem1Ouv;
    @FXML
    private JFXButton buSem1Gra;
    @FXML
    private JFXButton buSem1Tot;
    @FXML
    private JFXButton buSem2Sem;
    @FXML
    private JFXButton buSem2Reg;
    @FXML
    private JFXButton buSem2Esp;
    @FXML
    private JFXButton buSem2Ouv;
    @FXML
    private JFXButton buSem2Gra;
    @FXML
    private JFXButton buSem2Tot;
    @FXML
    private JFXButton buSem3Sem;
    @FXML
    private JFXButton buSem3Reg;
    @FXML
    private JFXButton buSem3Esp;
    @FXML
    private JFXButton buSem3Ouv;
    @FXML
    private JFXButton buSem3Gra;
    @FXML
    private JFXButton buSem3Tot;
    @FXML
    private Label lblUltAnoSem111111;
    @FXML
    private JFXButton buSem1Mat;
    @FXML
    private JFXButton buSem2Mat;
    @FXML
    private JFXButton buSem3Mat;
    @FXML
    private AnchorPane apCalculando;
    @FXML
    private Label lblACOuv1;
    @FXML
    private JFXButton buAtuAssentos;
    @FXML
    private Label lblUltAnoSem11112;
    @FXML
    private JFXButton buSem1NaoId;
    @FXML
    private JFXButton buSem2NaoId;
    @FXML
    private JFXButton buSem3NaoId;
    @FXML
    private AnchorPane pnEsconde1;
    @FXML
    private AnchorPane pnEsconde2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jpaPup = new CadastroalunodisciplinaJpaController();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        escondePlacar();

/////////////////////////////################################################################
// NÃO APAGUE PORQUE UM DIA VOCÊ PODE PRECISAR DISSO!
//        tvPlacarAssentos.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent click) {
//                if (click.getClickCount() == 2) {
//                    @SuppressWarnings("rawtypes")
//                    TablePosition pos = tvPlacarAssentos.getSelectionModel().getSelectedCells().get(0);
//                    int row = pos.getRow();
//                    int col = pos.getColumn();
//                    @SuppressWarnings("rawtypes")
//                    TableColumn column = pos.getTableColumn();
//                    String val = column.getCellData(row).toString();
//                    System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);
//                    mostraMsg("Selected Value, " + val + ", Column: " + col + ", Row: " + row, "", 3);
//
//                }
//            }
//
//        });
        carregaUltSemestre();

//        if (gbUser.getCoduser() == 2) {
//            calcAssentos();
//        }
//        lblArquivoJPA.setText(""); //lblArquivoJPA.setText(gbDeOnde);
        lblVersao.setText(ESVERSION);
        if (gbUser.getLogin().equals("lfa")) {
            lblJPA.setText(gbDeOnde);
        }
        lblHoje.setText(dateFormat.format(date));
        if (gbDeOnde == "Jpa_EvedSys_Server") {
            lblVersao.setText(lblVersao.getText() + "-S");
        } else {
            if (gbDeOnde == "Jpa_EvedSys_Local") {
                lblVersao.setText(lblVersao.getText() + "-L");
                apBarraSup.setStyle("-fx-background-color : #2E6D59");
            } else {
                lblVersao.setText(lblVersao.getText() + "-C");
                apBarraSup.setStyle("-fx-background-color : #282828");
                mnMenuBar.setStyle("-fx-background-color : #282828");
                mnTabBas.setStyle("-fx-background-color : #282828");
                mnConsist.setStyle("-fx-background-color : #282828");
            }
        }

        if ((gbUser.getCoduser() != 1) && (gbUser.getCoduser() != 2)) {
            mnConf.setVisible(false);
        }

        lblUser.setText(gbUser.getLogin());

        if (gbUser.getCoduser() == 2) {
//            chamaTela("/view/GradeDisMas.fxml", "Grades de Disciplinas do Semestre");

//            chamaTela("/view/DadocadastrogeralSimples.fxml", "Cadastro de Alunos");
//            File file = new File("C:/EvedSys/FirstPdf.pdf");
//
//            if (file.delete()) {
//                System.out.println("File deleted successfully");
//            } else {
//                System.out.println("Failed to delete the file");
//            }
            //   chamaTela("/view/GradeDisMas.fxml", "Grades de Disciplinas do Semestre");
            // ================================
            // PARA ENTRAR AUTOMATICAMENTE NA TELA DO ALUNO
            // CLARO, TIRAR DEPOIS
            // ===============================
//            Short alu = 2573;
//            jpaCon2 = new DadocadastrogeralJpaController();
//            gbRegCadGer = jpaCon2.findDadocadastrogeral(alu);
//            stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "/MeuCSS/cssDadosCad.css");
//            stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "");
        }
    }

    public void calcAssentos() {
        apCalculando.setVisible(true);

        // limpa placar
        buSem1Sem.setText("2020.2");
        buSem1Reg.setText("");
        buSem1Esp.setText("");
        buSem1Ouv.setText("");
        buSem1Gra.setText("");
        buSem1Tot.setText("");
        buSem2Sem.setText("2021.1");
        buSem2Reg.setText("");
        buSem2Esp.setText("");
        buSem2Ouv.setText("");
        buSem2Gra.setText("");
        buSem2Tot.setText("");
        buSem3Sem.setText("2021.2");
        buSem3Reg.setText("");
        buSem3Esp.setText("");
        buSem3Ouv.setText("");
        buSem3Gra.setText("");
        buSem3Tot.setText("");
        buSem1Mat.setText("");
        buSem2Mat.setText("");
        buSem3Mat.setText("");
        buSem1NaoId.setText("");
        buSem2NaoId.setText("");
        buSem3NaoId.setText("");

        // calcula semestre atual
        SemPlacar sem1 = new SemPlacar();
        SemPlacar sem2 = new SemPlacar();
        SemPlacar sem3 = new SemPlacar();
        sem1 = calculaSem(1);
        sem2 = calculaSem(2);
        sem3 = calculaSem(3);

        buSem1Reg.setText(sem1.getReg().toString());
        buSem1Esp.setText(sem1.getEsp().toString());
        buSem1Ouv.setText(sem1.getOuv().toString());
        buSem1Gra.setText(sem1.getGra().toString());
        buSem1NaoId.setText(sem1.getNaoid().toString());
        Integer totSem1 = sem1.getReg() + sem1.getEsp() + sem1.getOuv() + sem1.getGra() + sem1.getNaoid();
        buSem1Tot.setText(totSem1.toString());
        buSem1Mat.setText(sem1.getMatr().toString());

        buSem2Reg.setText(sem2.getReg().toString());
        buSem2Esp.setText(sem2.getEsp().toString());
        buSem2Ouv.setText(sem2.getOuv().toString());
        buSem2Gra.setText(sem2.getGra().toString());
        buSem2NaoId.setText(sem2.getNaoid().toString());
        Integer totSem2 = sem2.getReg() + sem2.getEsp() + sem2.getOuv() + sem2.getGra() + sem2.getNaoid();
        buSem2Tot.setText(totSem2.toString());
        buSem2Mat.setText(sem2.getMatr().toString());

        buSem3Reg.setText(sem3.getReg().toString());
        buSem3Esp.setText(sem3.getEsp().toString());
        buSem3Ouv.setText(sem3.getOuv().toString());
        buSem3Gra.setText(sem3.getGra().toString());
        buSem3NaoId.setText(sem3.getNaoid().toString());
        Integer totSem3 = sem3.getReg() + sem3.getEsp() + sem3.getOuv() + sem3.getGra() + sem3.getNaoid();
        buSem3Tot.setText(totSem3.toString());
        buSem3Mat.setText(sem3.getMatr().toString());

//        lblACReg.setTextAlignment(TextAlignment.CENTER);
//
//        lblACEsp.setTextAlignment(TextAlignment.CENTER);
//
//        lblACOuv.setTextAlignment(TextAlignment.CENTER);
//
//        lblACTot.setTextAlignment(TextAlignment.CENTER);
//        lblACReg.setText(calACR());
//        lblACEsp.setText(calACE());
//        lblACOuv.setText(calACO());
//        lblACTot.setText(calACT());
        apCalculando.setVisible(false);
   //     lblUltAnoSem.requestFocus();

    }

    private SemPlacar calculaSem(int semesCalcular) {
        SemPlacar sem = new SemPlacar();
        sem.setSemestre(calQualSemestre(gbUltSem.getUltAno(), gbUltSem.getUltSem(), semesCalcular));
        sem.setReg(calculaSemestre(gbUltSem.getCodGrade() + semesCalcular - 1, "R"));
        sem.setEsp(calculaSemestre(gbUltSem.getCodGrade() + semesCalcular - 1, "E"));
        sem.setOuv(calculaSemestre(gbUltSem.getCodGrade() + semesCalcular - 1, "O"));
        sem.setGra(calculaSemestre(gbUltSem.getCodGrade() + semesCalcular - 1, "G"));
        sem.setNaoid(calculaSemestre(gbUltSem.getCodGrade() + semesCalcular - 1, null));
        sem.setMatr(calcMatric(gbUltSem.getCodGrade() + semesCalcular - 1));

        return sem;
    }

    private Integer calcMatric(Integer codGrade) {
        String sql = "SELECT dcg.DadoCadastroGeralId "
                + " FROM cadastroalunodisciplina pup "
                + " LEFT JOIN dadocadastroprograma dcp ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId "
                + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId "
                + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId "
                + " WHERE cad.codgrade = $P(CODGRADE) "
                + " GROUP BY dcg.DadoCadastroGeralId; ";
        sql = sql.replace("$P(CODGRADE)", codGrade.toString());

        try {
            System.out.println("////////////");
            System.out.println(sql);
            System.out.println("////////////");
            ResultSet rs = con.createStatement().executeQuery(sql);
            return getRowCount(rs);

        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018k - em calcMatric()", "" + ex, 2);
            return -1;
        }
    }

    private Integer calculaSemestre(Integer codGrade, String tipo) {
        String sqlACR;
        if (tipo == null) {
            sqlACR = "SELECT count(*) as totR FROM cadastroalunodisciplina WHERE CodGrade = $P(CODGRADE) AND TipoRegOuv is NULL";
        } else {
            sqlACR = "SELECT count(*) as totR FROM cadastroalunodisciplina WHERE CodGrade = $P(CODGRADE) AND TipoRegOuv = \"$P(TIPOREG)\"";
        }

        String sql;
        sql = sqlACR.replace("$P(CODGRADE)", codGrade.toString());

        if (tipo != null) {
            sql = sql.replace("$P(TIPOREG)", tipo);
        }

        try {
            System.out.println("////////////");
            System.out.println(sql);
            System.out.println("////////////");
            ResultSet rs = con.createStatement().executeQuery(sql);
            int total = 0;
            while (rs.next()) {
                total = rs.getInt("totR");
            }
            System.out.println(":::::: valor calculado = " + total);
            return total;

        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018j - em calACR()", "" + ex, 2);
            return -1;
        }
    }

    private String calQualSemestre(int ano, int sem, int qual) {
        if (qual > 1) {
            if (sem == 2) {
                ++ano;
                sem = 1;
            } else {
                sem = 2;
            }
        }
        return ano + "." + sem;
    }

//    private String calACR() {
//        String sqlACR = "SELECT count(*) as totR FROM cadastroalunodisciplina WHERE CodGrade = $P(CODGRADE) AND TipoRegOuv = \"$P(TIPOREG)\"";
//
//        String sql = sqlACR.replace("$P(CODGRADE)", "60");
//        sql = sql.replace("$P(TIPOREG)", "R");
//
//        try {
//            System.out.println("////////////");
//            System.out.println(sql);
//            System.out.println("////////////");
//            ResultSet rs = con.createStatement().executeQuery(sql);
//            int total = 0;
//            while (rs.next()) {
//                total = rs.getInt("totR");
//            }
//            System.out.println(":::::: valor calACR = " + total);
//            return String.valueOf(total);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuPrincipalController.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            mostraMsg("Erro 018j - em calACR()", "" + ex, 2);
//            return "";
//        }
////return "";
//
//    }
//
//    private String calACE() {
//        String sqlACR
//                = "SELECT count(*) as totR \n"
//                + "FROM cadastroalunodisciplina\n"
//                + "WHERE CodGrade = " + gbUltSem.getCodGrade() + " AND TipoRegOuv = 'E'";
//        try {
//            System.out.println("////////////");
//            System.out.println(sqlACR);
//            System.out.println("////////////");
//            ResultSet rs = con.createStatement().executeQuery(sqlACR);
//            int total = 0;
//            while (rs.next()) {
//                total = rs.getInt("totR");
//            }
//            return String.valueOf(total);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuPrincipalController.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            mostraMsg("Erro 018j - em calACR()", "" + ex, 2);
//            return "";
//        }
//
//    }
//
//    private String calACO() {
//        String sqlACR
//                = "SELECT count(*) as totR \n"
//                + "FROM cadastroalunodisciplina\n"
//                + "WHERE CodGrade = " + gbUltSem.getCodGrade() + " AND TipoRegOuv = 'O'";
//        try {
//            System.out.println("////////////");
//            System.out.println(sqlACR);
//            System.out.println("////////////");
//            ResultSet rs = con.createStatement().executeQuery(sqlACR);
//            int total = 0;
//            while (rs.next()) {
//                total = rs.getInt("totR");
//            }
//            return String.valueOf(total);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuPrincipalController.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            mostraMsg("Erro 018j - em calACR()", "" + ex, 2);
//            return "";
//        }
//
//    }
//
//    private String calACT() {
//        String sqlACR
//                = "SELECT count(*) as totR \n"
//                + "FROM cadastroalunodisciplina\n"
//                + "WHERE CodGrade = " + gbUltSem.getCodGrade();
//        try {
//            System.out.println("////////////");
//            System.out.println(sqlACR);
//            System.out.println("////////////");
//            ResultSet rs = con.createStatement().executeQuery(sqlACR);
//            int total = 0;
//            while (rs.next()) {
//                total = rs.getInt("totR");
//            }
//            return String.valueOf(total);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuPrincipalController.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            mostraMsg("Erro 018j - em calACR()", "" + ex, 2);
//            return "";
//        }
//
//    }
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
            MyFunc.mostraMsg("Erro 003 ao carregar item de menu", "" + ex, 2);

        }
    }

    public void chamaTelaComCSS(String nomeTela, String titulo, String arqCSS) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(nomeTela));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle(titulo + " - " + ESVERSION);
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(arqCSS);
            stage.setScene(scene);
            stage.show();

            // LibraryAssistantUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            MyFunc.mostraMsg("Erro 008 ao carregar o CSS", "" + ex, 2);

        }
    }

    private void carregaUltSemestre() {

        jpaCon = new ArqsysJpaController();
        Arqsys asys = new Arqsys();

        Short um = 1;
        asys = jpaCon.findArqsys(um);
        UltSemestre ult = new UltSemestre();
        ult.setUltAno(asys.getUltAno());
        ult.setUltSem(asys.getUltSemestre());
        //lblUltAnoSem.setText("Ano: " + ult.getUltAno() + "  -  " + ult.getUltSem() + "º semestre");
        gbUltSem = new UltSemestre(asys.getUltAno(), asys.getUltSemestre(), asys.getCodGrade());

    }

    @FXML
    private void clicouPro(ActionEvent event) {
        chamaTela("/view/Programas.fxml", "Tabela de Programas");
    }

    private void clicouARec(ActionEvent event) {
        // chamaTela("/view/Turma.fxml", "...");
    }

    @FXML
    private void clicouAbout(ActionEvent event) {
        // chamaTela("/view/Turma.fxml", "...");
    }

    @FXML
    private void clicouMin(ActionEvent event) {
//        chamaTelaComCSS("/view/Ministeriais.fxml", "Tabela de Ministeriais","/MeuCSS/CssMinisteriais.css");
        chamaTela("/view/Ministeriais.fxml", "Tabela de Ministeriais");
    }

    @FXML
    private void clicouCat(ActionEvent event) {
        chamaTela("/view/Categorias.fxml", "Tabela de Categorias");
    }

    @FXML
    private void clicouSit(ActionEvent event) {
        chamaTela("/view/SituacaoPrograma.fxml", "Tabela de Situação do Aluno no Programa");
    }

    @FXML
    private void clicouFre(ActionEvent event) {
        chamaTela("/view/Frequencia.fxml", "Tabela de Frequencia");
    }

    @FXML
    private void clicouLoc(ActionEvent event) {
        chamaTela("/view/Localidade.fxml", "Tabela de Localidades");
    }

    @FXML
    private void clicouTip(ActionEvent event) {
        chamaTela("/view/TipoTrabalho.fxml", "Tabela de Tipos de Trabalho");
    }

    @FXML
    private void clicouNac(ActionEvent event) {
        chamaTela("/view/Nacionalidade.fxml", "Tabela de Nacionalidades");
    }

    @FXML
    private void clicouEstCivil(ActionEvent event) {
        chamaTela("/view/EstadoCivil.fxml", "Tabela de Estado Civil");
    }

    @FXML
    private void clicouSexo(ActionEvent event) {
        chamaTela("/view/Sexo.fxml", "Tabela de Gêneros");
    }

    @FXML
    private void clicouUF(ActionEvent event) {
        chamaTela("/view/Siglaestado.fxml", "Tabela de Siglas UF");
    }

    @FXML
    private void clicouTurma(ActionEvent event) {
        chamaTela("/view/Turma.fxml", "Tabela de Turmas");
    }

    @FXML
    private void clicouTurno(ActionEvent event) {
        chamaTela("/view/Turno.fxml", "Tabela de Turnos");
    }

    @FXML
    private void clicouTabDis(ActionEvent event) {
        chamaTela("/view/TabDis2.fxml", "Disciplinas");
    }

    @FXML
    private void clicouDen(ActionEvent event) {
        chamaTela("/view/Denominacao.fxml", "Tabela de Denominações");
    }

    @FXML
    private void clicouAlunosSimples(ActionEvent event) {
        chamaTela("/view/DadocadastrogeralSimples.fxml", "Cadastro de Alunos");
//        stgDadosCad = chamaTelaStage("/view/DadocadastrogeralSimples.fxml", "Cadastro de Alunos");
    }

    @FXML
    private void clicouTracking(ActionEvent event) {
        chamaTela("/view/Tracking.fxml", "Rastreamento de Atividades");
    }

    @FXML
    private void clicouConsistAlunos(ActionEvent event) {
        chamaTela("/view/Consist.fxml", "Consistência dos dados dos alunos");
    }

    @FXML
    private void clicouSecretCase(ActionEvent event) {
        chamaTela("/view/SecretCase.fxml", "Teste Secret Case");
    }

    @FXML
    private void clicouDocInsc(ActionEvent event) {
        chamaTela("/view/DocInsc.fxml", "Documentos do Aluno");
        //   chamaTela("/view/DocInsc.fxml", "Tipos de Documentos para Inscrição");
    }

    @FXML
    private void clicouSitDis(ActionEvent event) {
        chamaTela("/view/SituDis.fxml", "Tabela de Situação do ALuno na Disciplina");
    }

    @FXML
    private void clicouSair(ActionEvent event) {
        if (!gbUser.getLogin().equals("lfa")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Deseja realmente sair do sistema?");
            alert.setContentText("Os dados não gravados serão perdidos.");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                System.exit(0);
            }
        } else {
            System.exit(0);
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

    @FXML
    private void clicouCadCur(ActionEvent event) throws IOException {
        stgCurso = chamaTelaStage("/view/Cursos.fxml", "Cadastro dos Cursos");
    }

    @FXML
    private void clicouEsp(ActionEvent event) {
        chamaTela("/view/Espec.fxml", "Tabela de Especificidades");
    }

    @FXML
    private void clicouAlunos(ActionEvent event) {
        chamaTela("/view/Dadocadastrogeral.fxml", "Cadastro de Alunos");
    }

    private void clicouAlunosTeste(ActionEvent event) {
        chamaTela("/view/AlunosTeste.fxml", "Cadastro de Alunos");
    }

    @FXML
    private void clicouFormacoes(ActionEvent event) {
        chamaTela("/view/Formacao.fxml", "Tabela de Formações");
    }

    @FXML
    private void clicouProfissoes(ActionEvent event) {
        chamaTela("/view/Profissao.fxml", "Tabela de Profissões");
    }

    @FXML
    private void clicouTipoDoc(ActionEvent event) {
        chamaTela("/view/OutrosDocs.fxml", "Tabela de Tipos de Documento");
    }

    @FXML
    private void clicouUsers(ActionEvent event) {
        // chamaTela("/view/Users000.fxml", "Cadastro de Usuários");
        stgUsers = chamaTelaStage("/view/Users000.fxml", "Cadastro de Usuários");
    }

    @FXML
    private void clicuPerfil(ActionEvent event) {
        //  chamaTela("/view/Users.fxml", "Perfis de Usuários");
    }

    @FXML
    private void clicouTrocaSenha(MouseEvent event) throws Exception {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/view/TrocaSenha.fxml"));
            gbSenhaOk = false;

            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("TROCAR A SENHA");
            stage.setScene(scene);

            stage.showAndWait();

            if (gbSenhaOk == true) {
                jpaUser = new TabuserJpaController();
                reg_atual.setCoduser(gbUser.getCoduser());
                reg_atual.setLogin(gbUser.getLogin());
                reg_atual.setPassword(gbUser.getPassword());
//                reg_atual.setNomePerfil(gbUser.getNomePerfil());
                jpaUser.edit(reg_atual);
                MyFunc.mostraMsg("Senha alterada com sucesso", "", 3);

            }

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("=====================");
            MyFunc.mostraMsg("Erro 009 ao carregar o Menu Principal", "" + ex, 2);
            System.out.println("=====================");
        }

        // SE TROUXE CEP SELECIONADO, ALIMENTA CAMPOS
    }

    @FXML
    private void clicouGrade(ActionEvent event) {
        stgGradeMaster = chamaTelaStage("/view/GradeDisMas.fxml", "Grades de Disciplinas do Semestre", "");
        //chamaTela("/view/GradeDisMas.fxml", "Grades de Disciplinas do Semestre");
    }

    @FXML
    private void clicouTipoDis(ActionEvent event) {
        chamaTela("/view/teste.fxml", "teste");
    }

    @FXML
    private void clicouDataNascSophia(ActionEvent event) {
        chamaTela("/view/Fisica.fxml", "Data de Nascimento (Sophia)");
    }

    @FXML
    private void clicouInspetor(ActionEvent event) {
        chamaTela("/view/Inspetor.fxml", "Inspetor");
    }

    private void clicouTsi(ActionEvent event) {
        Integer alu = 39;
        jpaCon2 = new DadocadastrogeralJpaController();
        gbRegCadGer = jpaCon2.findDadocadastrogeral(alu);
//            stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "/MeuCSS/cssDadosCad.css");
        stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "");

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

    private void clicouMostraMatric(ActionEvent event) {
//        gbPlacarInfo = new PlacarInfo("Alunos Matriculados neste semestre", Integer.valueOf(lblMatri.getText()), 1);
//        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getNomeTela());
    }

    private void clicouMostraRematric(ActionEvent event) {
//        gbPlacarInfo = new PlacarInfo("Alunos Rematriculados neste semestre", Integer.valueOf(lblMatri.getText()), 2);
//        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getNomeTela());
    }

    private void clicouMostraNovos(ActionEvent event) {
//        gbPlacarInfo = new PlacarInfo("Alunos Novos neste semestre", Integer.valueOf(lblMatri.getText()), 3);
//        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getNomeTela());
    }

    private void clicouMostraNaoRematri(ActionEvent event) {
//        gbPlacarInfo = new PlacarInfo("Alunos Não Rematriculados neste semestre", Integer.valueOf(lblMatri.getText()), 4);
//        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getNomeTela());
    }

    @FXML
    private void mudaIcon(ActionEvent event) {
        // FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.);
//        Icons525View icon = new FontAwesomeIconView(Icons525.ACCESS);
//        Icons525Factory.
//MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.);
        fa1.glyphNameProperty().setValue(tf1.getText());
        md1.glyphNameProperty().setValue(tf1.getText());
        mi1.glyphNameProperty().setValue(tf1.getText());
        oi1.glyphNameProperty().setValue(tf1.getText());
        wi1.glyphNameProperty().setValue(tf1.getText());
    }

    private void placar() {
        //   if (gbUser.getCoduser() == 2) {
     //   lblMatri.setText(aluMatri());
    //    lblNovos.setText(aluNovos());
//            lblRematric.setText(aluRematri());
//            lblNaoRematric.setText(aluNaoRematri());
//        }

    }

    private String aluMatri() {
        String sqlMatric
                = " SELECT dcp.DadoCadastroGeralId "
                + " FROM cadastroalunodisciplina pup "
                + " LEFT JOIN dadocadastroprograma dcp ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId "
                + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId "
                + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId "
                + " WHERE pup.codgrade = " + gbUltSem.getCodGrade()
                + " GROUP BY dcp.DadoCadastroGeralId";

        try {
            System.out.println("////////////");
            System.out.println(sqlMatric);
            System.out.println("////////////");
            ResultSet rs = con.createStatement().executeQuery(sqlMatric);
            return String.valueOf(getRowCount(rs));

        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018j - em aluMatri()", "" + ex, 2);
            return "";
        }

    }

    private String aluNovos() {
        String salNovos
                = " SELECT dcp.dadocadastrogeralId "
                + " FROM dadocadastroprograma dcp  "
                + " LEFT JOIN cadastroalunodisciplina pup ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId "
                + " WHERE pup.codgrade = " + gbUltSem.getCodGrade()
                + " AND dcp.dadocadastrogeralId NOT IN "
                + " (SELECT dcp.dadocadastrogeralId "
                + " FROM dadocadastroprograma dcp "
                + " LEFT JOIN cadastroalunodisciplina pup ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId "
                + " WHERE pup.codgrade < " + gbUltSem.getCodGrade() + ") "
                + " GROUP BY dcp.dadocadastrogeralId; ";
        try {
            System.out.println(salNovos);
            ResultSet rs = con.createStatement().executeQuery(salNovos);
            return String.valueOf(getRowCount(rs));

        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018j - em aluNovos()", "" + ex, 2);
            return "";
        }

    }

    private String aluRematri() {

        String sqlRematri = " SELECT dcp.dadocadastrogeralId "
                + " FROM dadocadastroprograma dcp "
                + " LEFT JOIN cadastroalunodisciplina pup ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId "
                + " WHERE pup.codgrade = " + gbUltSem.getCodGrade()
                + " AND dcp.dadocadastrogeralId IN "
                + " (SELECT dcp.dadocadastrogeralId "
                + " FROM dadocadastroprograma dcp "
                + " LEFT JOIN cadastroalunodisciplina pup ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId "
                + " WHERE pup.codgrade < " + gbUltSem.getCodGrade() + ") "
                + " GROUP BY dcp.dadocadastrogeralId ";

        try {
            System.out.println(sqlRematri);
            ResultSet rs = con.createStatement().executeQuery(sqlRematri);
            return String.valueOf(getRowCount(rs));

        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018j - em AluRematri()", "" + ex, 2);
            return "";
        }

    }

    private String aluNaoRematri() {

        int anterior = gbUltSem.getCodGrade() - 1;
        String sqlRNaoematri = " SELECT dcp.dadocadastrogeralId "
                + " FROM dadocadastroprograma dcp "
                + " LEFT JOIN cadastroalunodisciplina pup ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId "
                + " WHERE pup.codgrade <> " + gbUltSem.getCodGrade()
                + " AND dcp.dadocadastrogeralId IN "
                + " (SELECT dcp.dadocadastrogeralId "
                + " FROM dadocadastroprograma dcp "
                + " LEFT JOIN cadastroalunodisciplina pup ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId "
                + " WHERE pup.codgrade = " + anterior + " AND dcp.DadoCadastroProgramaSituacaoId <> 5) "
                + " GROUP BY dcp.dadocadastrogeralId ";

        try {
            System.out.println(sqlRNaoematri);
            ResultSet rs = con.createStatement().executeQuery(sqlRNaoematri);
            return String.valueOf(getRowCount(rs));

        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018j - em AluRematri()", "" + ex, 2);
            return "";
        }

    }

    private int getRowCount(ResultSet resultSet) {
        if (resultSet == null) {
            return 0;
        }

        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        }

        return 0;
    }

    @FXML
    private void clicouVersoes(ActionEvent event) {
        chamaTela("/view/Versoes.fxml", "Notificações de Versões");
    }

    @FXML
    private void clicouCadProf(ActionEvent event) {
        chamaTela("/view/Professores.fxml", "Cadastro dos Professores");
    }

    private void clicouPlacar(ActionEvent event) {
        chamaTela("/view/Assentos.fxml", "Assentos");
    }

    @FXML
    private void clicouAtuAssentos(ActionEvent event) {
        mostraPlacar();
        calcAssentos();
    }

    @FXML
    private void clicouSem1Reg(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade(), buSem1Sem.getText(), "Alunos Regulares", "R", "#2196F3", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem1Esp(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade(), buSem1Sem.getText(), "Alunos Especiais", "E", "#FF6E40", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem1Ouv(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade(), buSem1Sem.getText(), "Alunos Ouvintes", "O", "#FFC107", "#000000");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem1Gra(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade(), buSem1Sem.getText(), "Alunos Gravação", "G", "#009688", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem1Tot(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade(), buSem1Sem.getText(), "Total de Alunos", "", "#7C4DFF", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem1Mat(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade(), buSem1Sem.getText(), "Alunos Matriculados", "", "#A1A1A1", "#FFFFFF");
        chamaTela("/view/PlacarMatri.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem2Reg(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 1, buSem2Sem.getText(), "Alunos Regulares", "R", "#2196F3", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem2Esp(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 1, buSem2Sem.getText(), "Alunos Especiais", "E", "#FF6E40", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem2Ouv(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 1, buSem2Sem.getText(), "Alunos Ouvintes", "O", "#FFC107", "#000000");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem2Gra(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 1, buSem2Sem.getText(), "Alunos Gravação", "G", "#009688", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem2Tot(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 1, buSem2Sem.getText(), "Total de Alunos", "", "#7C4DFF", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem2Mat(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 1, buSem2Sem.getText(), "Alunos Matriculados", "", "#A1A1A1", "#FFFFFF");
        chamaTela("/view/PlacarMatri.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem3Reg(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 2, buSem3Sem.getText(), "Alunos Regulares", "R", "#2196F3", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem3Esp(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 2, buSem3Sem.getText(), "Alunos Especiais", "E", "#FF6E40", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem3Ouv(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 2, buSem3Sem.getText(), "Alunos Ouvintes", "O", "#FFC107", "#000000");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem3Gra(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 2, buSem3Sem.getText(), "Alunos Gravação", "G", "#009688", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem3Tot(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 2, buSem3Sem.getText(), "Total de Alunos", "", "#7C4DFF", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem3Mat(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade()+2, buSem3Sem.getText(), "Alunos Matriculados", "", "#A1A1A1", "#FFFFFF");
        chamaTela("/view/PlacarMatri.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem1NaoId(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade(), buSem1Sem.getText(), "Alunos com Tipo não identificado", null, "#8D5501", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem2NaoId(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 1, buSem2Sem.getText(), "Alunos com Tipo não identificado", null, "#8D5501", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    @FXML
    private void clicouSem3NaoId(ActionEvent event) {
        gbPlacarInfo = new PlacarInfo(gbUltSem.getCodGrade() + 2, buSem3Sem.getText(), "Alunos com Tipo não identificado", null, "#8D5501", "#FFFFFF");
        chamaTela("/view/PlacarDet.fxml", gbPlacarInfo.getTitulo());
    }

    public class Linhas {

        private String titulo;
        private int sem1;
        private int sem2;
        private int sem3;
        private int sem4;
        private int sem5;

        public Linhas(String titulo, int sem1, int sem2, int sem3, int sem4, int sem5) {
            this.titulo = titulo;
            this.sem1 = sem1;
            this.sem2 = sem2;
            this.sem3 = sem3;
            this.sem4 = sem4;
            this.sem5 = sem5;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public int getSem1() {
            return sem1;
        }

        public void setSem1(int sem1) {
            this.sem1 = sem1;
        }

        public int getSem2() {
            return sem2;
        }

        public void setSem2(int sem2) {
            this.sem2 = sem2;
        }

        public int getSem3() {
            return sem3;
        }

        public void setSem3(int sem3) {
            this.sem3 = sem3;
        }

        public int getSem4() {
            return sem4;
        }

        public void setSem4(int sem4) {
            this.sem4 = sem4;
        }

        public int getSem5() {
            return sem5;
        }

        public void setSem5(int sem5) {
            this.sem5 = sem5;
        }

    }

    public class Assentos {

        private Integer reg;
        private Integer esp;
        private Integer ouv;
        private Integer gra;
        private Integer tot;

        public Assentos(Integer reg, Integer esp, Integer ouv, Integer gra, Integer tot) {
            this.reg = reg;
            this.esp = esp;
            this.ouv = ouv;
            this.gra = gra;
            this.tot = tot;
        }

        public Integer getReg() {
            return reg;
        }

        public void setReg(Integer reg) {
            this.reg = reg;
        }

        public Integer getEsp() {
            return esp;
        }

        public void setEsp(Integer esp) {
            this.esp = esp;
        }

        public Integer getOuv() {
            return ouv;
        }

        public void setOuv(Integer ouv) {
            this.ouv = ouv;
        }

        public Integer getGra() {
            return gra;
        }

        public void setGra(Integer gra) {
            this.gra = gra;
        }

        public Integer getTot() {
            return tot;
        }

        public void setTot(Integer tot) {
            this.tot = this.reg + this.esp + this.ouv + this.gra;
        }

    }

    private class SemPlacar {

        private String semestre;
        private Integer reg;
        private Integer esp;
        private Integer ouv;
        private Integer gra;
        private Integer naoid;
        private Integer matr;

        public SemPlacar() {
        }

        public String getSemestre() {
            return semestre;
        }

        public void setSemestre(String semestre) {
            this.semestre = semestre;
        }

        public Integer getReg() {
            return reg;
        }

        public void setReg(Integer reg) {
            this.reg = reg;
        }

        public Integer getEsp() {
            return esp;
        }

        public void setEsp(Integer esp) {
            this.esp = esp;
        }

        public Integer getOuv() {
            return ouv;
        }

        public void setOuv(Integer ouv) {
            this.ouv = ouv;
        }

        public Integer getGra() {
            return gra;
        }

        public void setGra(Integer gra) {
            this.gra = gra;
        }

        public Integer getNaoid() {
            return naoid;
        }

        public void setNaoid(Integer naoid) {
            this.naoid = naoid;
        }

        public Integer getMatr() {
            return matr;
        }

        public void setMatr(Integer matr) {
            this.matr = matr;
        }

    }

    private void escondePlacar() {
        pnEsconde1.setVisible(true);
        pnEsconde2.setVisible(true);
    }

    private void mostraPlacar() {
        pnEsconde1.setVisible(false);
        pnEsconde2.setVisible(false);
    }
}
