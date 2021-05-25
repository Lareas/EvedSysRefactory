/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.validation.RequiredFieldValidator;
import controller.LoginController;
import entities.Atu_Notas;
import entities.Cadastroalunodisciplina;
import entities.Cadastrodisciplina;
import entities.CepLogradouros;
import entities.Dadocadastrogeral;
import entities.Dadocadastroprograma;
import entities.DataCrudMS;
import entities.DisciProgAluno;
import entities.Disciplina;
import entities.DispensaGB;
import entities.EquivalenciaGB;
import entities.EsGrade;
import entities.EsMatrisem;
import entities.FotoMaior;
import entities.Funcionario;
import entities.InserePro;
import entities.ListaCabec;
import entities.ListaInfo;
import entities.MatriSemCabec;
import entities.PlacarInfo;
import entities.RegCrudMS;
import entities.Siglaestado;
import entities.Tabuser;
import entities.UltSemestre;
import funcoes.MyFunc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.TabuserJpaController;

public class Login extends Application {

    // CONSTANTES
    //  java.net.URL url = ClassLoader.getSystemResource("com/xyz/resources/camera.png");
    public static final String ICONERROR = "src/resources/Icons/Error5.png";

    /////////////////////////////////////////////////
    public static final String ESVERSION = "2.8a";
    public static String secretCase = "\\\\192.168.0.19\\esphoto";
    /////////////////////////////////////////////////

    public static final SimpleDateFormat GBFORMATDATA = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat GBFORMATDATASQL = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat GBFORMATHORA = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat GBFORMATDATAHORA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final String CRITERIOLISTALU
            = " AND ((pup.CadastroAlunoDisciplinaSituacaoId != 4)  "
            + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 14) "
            + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 15) "
            + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 16) "
            + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 17) "
            + " OR (pup.CadastroAlunoDisciplinaSituacaoId IS NULL)) "
            + " ORDER BY pup.IndTipoRegOuv, pup.TipoRegOuv desc, dcg.nome";

    public static final Integer NUMATRASOSIGUALUMAFALTA = 4;
    
    /////////////////////////////////////////////////
    // VARIÁVEIS GLOBAIS
    /////////////////////////////////////////////////
    public static String gbDeOnde = "Jpa_EvedSys_Server"; // Jpa_EvedSys_Server = servidor; Jpa_EvedSys_Local = local
    public static Tabuser gbUser;
    public static int gbIdioma = 1; // 1 = "Port"; 2 = "Chi";
    public static boolean gbTranca = false;
    public static boolean gbSenhaOk = false;  // usado para trocar a senha (TROCASENHA)
    public static boolean gbTemNotif = false;
    public static boolean gbClicouInsere = false; // da tela DadoCadastroGeral para DadosCadastrais
    public static boolean gbClicouInsereProf = false; // da tela Professores
    public static boolean gbClicouInsGraDis = false; // da tela DadoCadastroGeral para DadosCadastrais
    public static boolean gbClicouEdiGraDis = false; // da tela DadoCadastroGeral para DadosCadastrais

    public static boolean gbClicouInsPro = false; // da tela DadosCadastrais para CadPro
    public static boolean gbClicouEdiPro = false; // da tela DadosCadastrais para CadPro
    public static Image gbFotoUser = null;
    public static boolean gbEdiUser = false;
    public static boolean gbInsUser = false;

    public static int gbCrudMin = 0; // para crud de ministérios a partir de DadosCadastrais (1 = INS, 2 = EDI, 3 = APA)
    public static int gbCrudProg = 0; // para crud de ministérios a partir de DadosCadastrais (1 = INS, 2 = EDI, 3 = APA)
    public static String gbNomeCrudMin = "";
    public static DataCrudMS gbDataCrudMS;
    public static RegCrudMS gbRegCrudMS;
    public static int gbTipoInsEdiMS;
    public static int gbCrudAmareloMS;
    public static int gbCrudEqui;
    public static String gbNomeCrudPro = "";
    public static String caminhoImagensDefaut = System.getProperty("user.home");

    public static RequiredFieldValidator validator = new RequiredFieldValidator();
    public static String[] myCondics = {"contém", "começa com", "termina com", "igual a", "diferente de", "maior ou igual a", "maior que", "menor ou igual a", "menor que"};
    public static Disciplina gbRegDisc; // = new Disciplina();
    public static Cadastroalunodisciplina gbDisProAlu; // para editar situação de disciplina do aluno em CadProg2

    public static int gbCadDis_GridMas = 0;
    public static Boolean gbSoProgsAtivos = true;
    public static Dadocadastrogeral gbRegCadGer;
    public static Funcionario gbFunCadGer;
    public static Cadastrodisciplina gbRegCad;
    public static Dadocadastroprograma gbRegProAlu;
    public static Dadocadastroprograma gbRegProg;
    public static CepLogradouros gbCepAluno;
    public static String gbCEP;
    public static DisciProgAluno gbJanSituDis;
    public static EsGrade graM;
    public static FotoMaior gbFotoMaior; // para mostrar a foto maior dentro do cadastro do aluno
    public static EsMatrisem gbRegMatriSem;
    public static MatriSemCabec gbRegMatriSemCabec;
    public static InserePro gbInsPro;
    public static ListaCabec gbListaCabec;
    public static Boolean gbEspecial;
    public static UltSemestre gbUltSem;
    public static PlacarInfo gbPlacarInfo;
    public static Atu_Notas gbAtuNotas; // para chamar Atualiza_nNotas de vários pontos do sistema  
    public static ListaInfo gbListaInfo;
    public static DispensaGB gbDispensa;
    public static EquivalenciaGB gbEquivalencia;
    public static Stage[] aListasPre;
    public static Integer[] gbDisci;

    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Date today = new Date();

        validator.setMessage("Este campo não pode ficar vazio");
        String path = "C:/eved_relatorios/";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        // JÁ ENTRA DIRETO SE FOR LOCAL
        String location = "C:\\___0all";
        File file = new File(location);
        if (false) { /////// PARA FORÇAR O LOGIN, SE NÃO FOR EU
            // if (file.isDirectory()) {
            gbDeOnde = "Jpa_EvedSys_Server";
            secretCase = "C:\\EvS\\ESPHOTO";
            TabuserJpaController usuCon = new TabuserJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO
            Tabuser obj_usu = usuCon.findUserPorLogin("lfa");
            gbUser = obj_usu;

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
                stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Eved Sys - Menu Principal - " + ESVERSION);
                stage.setScene(new Scene(parent));
                stage.show();
                // LibraryAssistantUtil.setStageIcon(stage);

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class
                        .getName()).log(Level.SEVERE, null, ex);
                MyFunc.mostraMsg("Erro 005 ao carregar o Menu Principal", "" + ex, 2);
            }

        } else {

            try {
                Image icn = new Image(new FileInputStream(ICONERROR));
                validator.setIcon(new ImageView(icn));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Siglaestado.class.getName()).log(Level.SEVERE, null, ex);
            }

            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml")); // carrega FXML
            Scene scene = new Scene(root); // coloca o FXML em uma cena
            stage.setTitle("Eved Sys - Login - " + ESVERSION);
            stage.setScene(scene); // coloca a cena em uma janela
            stage.show(); // abre a janela
            setStage(stage);
        }

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Login.stage = stage;
    }

}
