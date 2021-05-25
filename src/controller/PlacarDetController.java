/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.ESVERSION;
import static main.Login.gbClicouEdiPro;
import static main.Login.gbClicouInsPro;
import static main.Login.gbDeOnde;
import static main.Login.GBFORMATDATAHORA;
import static main.Login.gbInsPro;
import static main.Login.gbPlacarInfo;
import static main.Login.gbRegCadGer;
import static main.Login.gbRegProAlu;
import static main.Login.gbUltSem;
import static main.Login.gbUser;
import com.jfoenix.controls.JFXButton;
import static controller.DadocadastrogeralSimplesController.stgDadosCad;
import entities.DisciProgAluno;
import entities.EsFotosalunos;
import entities.InserePro;
import entities.Listagens;
import entities.PlacarAluno;
import entities.PlacarInfo;
import entities.ProgramasAluno;
import funcoes.DBConnector;
import java.util.HashMap;
import funcoes.MyFunc;
import static funcoes.MyFunc.mostraMsg;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.DadocadastrogeralJpaController;
import jpa_controler.DadocadastroprogramaJpaController;
import jpa_controler.EsFotosalunosJpaController;
import jpa_controler.ListagensJpaController;
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

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class PlacarDetController implements Initializable {

    @FXML
    private TextField lblRegs2;
    @FXML
    private TableView<ProgramasAluno> tvProgramas;
    @FXML
    private TableColumn<ProgramasAluno, String> tc2;
    @FXML
    private TableColumn<ProgramasAluno, String> tc3;
    @FXML
    private TableColumn<ProgramasAluno, Integer> tc4;
    @FXML
    private TableColumn<ProgramasAluno, Integer> tc5;
    @FXML
    private ImageView ivFotoAluno;
    @FXML
    private Label lblNomeALuno;
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
    private TableColumn<DisciProgAluno, String> tcTipo;
    @FXML
    private SplitPane spPlacar;
    @FXML
    private TextField lblRegs21;

    @FXML
    private TableView<PlacarAluno> tvAllunos;
    @FXML
    private TableColumn<PlacarAluno, Integer> tcCod;
    @FXML
    private TableColumn<PlacarAluno, Integer> tcMat;
    @FXML
    private TableColumn<PlacarAluno, String> tcNome;
    @FXML
    private TableColumn<PlacarAluno, String> tcPro;
    @FXML
    private TableColumn<PlacarAluno, String> tcDisPlacar;
    @FXML
    private TableColumn<PlacarAluno, String> tcTip;

    @FXML
    private Label lblTitPlacar;
    @FXML
    private Pane pnListagem;
    @FXML
    private JFXButton buPastaRelat;
    @FXML
    private TextField tfPathRelats;
    @FXML
    private ComboBox<String> cbListagens;
    @FXML
    private Label lblQtd;
    @FXML
    private Label lblGrade;

    private ObservableList<PlacarAluno> dadosMat;

    public Connection con = DBConnector.getConnection();
    private EsFotosalunosJpaController jpaFotos;
    EsFotosalunos reg_foto = new EsFotosalunos();
    private ObservableList<EsFotosalunos> dadosFoto;
    private ObservableList<DisciProgAluno> dadosRD;

    private final String SQLB_PROGRAMAS = "SELECT dcp.DadoCadastroProgramaId, dcp.dadocadastrogeralId, pro.programa, dcp.cursoId, cur.curso, prs.DadoCadastroProgramaSituacao, dcp.anoLetivo, dcp.semestreId, tur.turno, dcp.DataCadastro "
            + "FROM programa pro "
            + "LEFT JOIN curso cur ON cur.ProgramaId = pro.ProgramaId "
            + "LEFT JOIN dadocadastroprograma dcp ON dcp.cursoId = cur.cursoId "
            + "LEFT JOIN dadocadastroprogramasituacao prs ON dcp.DadoCadastroProgramaSituacaoId = prs.DadoCadastroProgramaSituacaoId "
            + "LEFT JOIN turno tur on tur.turnoId = dcp.turnoId ";

    private final String SQLB_DISCIPLINAS
            = "SELECT pup.CadastroAlunoDisciplinaId, c.curso, cd.Disciplina, fu.nome, pup.media, pup.falta, cs.Descricao, fr.frequencia, cd.Credito, cd.CargaHoraria, cd.AnoLetivo, cd.SemestreId, pup.TipoRegOuv "
            + "FROM cadastroalunodisciplina pup "
            + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId "
            + "LEFT JOIN cadastrodisciplina cd ON pup.CadastroDisciplinaId = cd.CadastroDisciplinaId "
            + "LEFT JOIN funcionario fu ON fu.FuncionarioId = cd.FuncionarioId  "
            + "LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id "
            + "LEFT JOIN curso c ON dcp.CursoId = c.CursoId "
            + "LEFT JOIN frequencia fr ON pup.FrequenciaId = fr.FrequenciaId  ";

    private String sqlMatric
            = " SELECT pup.CadastroAlunoDisciplinaId, pup.DadoCadastroProgramaId, dcg.DadoCadastroGeralId, dcg.matricula, dcg.nome, cur.curso, cad.Disciplina, pup.TipoRegOuv "
            + " FROM cadastroalunodisciplina pup "
            + " LEFT JOIN dadocadastroprograma dcp ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId   "
            + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId   "
            + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId   "
            + " LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId   ";

    private String condicao = "";

    public String sqlProgramas;
    public String sqlDisciplinas;
    public String sqlRelat = " WHERE pup.CodGrade = $P(CODGRADE) "; // pega do WHERE para frente

    private int totCre;
    private int totCreAprov;
    ProgramasAluno pro_atual = new ProgramasAluno();
    private DadocadastroprogramaJpaController jpaPro;
    private DadocadastrogeralJpaController jpaCon;
    ObservableList<ProgramasAluno> prolist;

    public static Stage stgJanelaPlacar;
    public PlacarInfo pi;
    private ListagensJpaController jpaLis;
    ObservableList<String> dadosLis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbListagens.setItems(getListagens(2)); // 1 =  listagens lista de presença; 2 = placar
        cbListagens.getSelectionModel().selectFirst();

        pi = gbPlacarInfo;
        lblGrade.setText(pi.getSemestre());
        //lblQtd.setStyle("-fx-background-color :  #2196F3;");
        //lblQtd.setStyle("-fx-background-color : " + pi.getCorFundo());
        lblQtd.setBackground(new Background(new BackgroundFill(Color.valueOf(pi.getCorFundo()), new CornerRadii(4), Insets.EMPTY)));
        lblQtd.setStyle("-fx-text-fill : " + pi.getCorFonte() + ";");

        tfPathRelats.setText(gbUser.getPastaRelat());
        DirectoryChooser directoryChooser = new DirectoryChooser();

        buPastaRelat.setOnAction(e -> {
            directoryChooser.setInitialDirectory(new File(tfPathRelats.getText()));
            File selectedDirectory = directoryChooser.showDialog(stgJanelaPlacar);
            tfPathRelats.setText(selectedDirectory.getAbsolutePath());
        });

        jpaCon = new DadocadastrogeralJpaController();
        jpaPro = new DadocadastroprogramaJpaController();
        tvAllunos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                atuProgs();
                initColsPro();
                tvProgramas.requestFocus();
                tvProgramas.getSelectionModel().select(0);
                tvProgramas.getFocusModel().focus(0);
            }
        });

        tvProgramas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
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

        tcCod.setCellValueFactory(new PropertyValueFactory<>("codALuno"));
        tcMat.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcPro.setCellValueFactory(new PropertyValueFactory<>("programa"));
        tcDisPlacar.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        tcTip.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        aluMatri();
    }

    private void atuProgs() {
        limpaCampos();
        leFoto(Integer.valueOf(tvAllunos.getSelectionModel().getSelectedItem().getCodALuno()));
        lblNomeALuno.setText(tvAllunos.getSelectionModel().getSelectedItem().getNome());

    }

    private void limpaCampos() {
        ivFotoAluno.setImage(null);
        tvProgramas.setItems(null);
        tvRegDis.setItems(null);
    }

    @FXML
    private void clicouAbreCad(ActionEvent event) {
        AbreCadastro();
    }

    @FXML
    private void clicouAbrePro(ActionEvent event) {
        if (tvProgramas.getSelectionModel().getSelectedItem() != null) {
            AbrePrograma(2);
        }
    }

    public void AbreCadastro() {
        if (tvAllunos.getSelectionModel().getSelectedItem() != null) {

            //PASSA REGISTRO SELECIONADO PARA A VARIÁVEL GLOBAL rgRegDisc, que será usado na tela TabelaDadocadastrogerals
            gbRegCadGer = jpaCon.findDadocadastrogeral(tvAllunos.getSelectionModel().getSelectedItem().getCodALuno());
//            stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "/MeuCSS/cssDadosCad.css");
            stgDadosCad = chamaTelaStage("/view/DadosCadastrais.fxml", "Cadastro de Alunos", "");

        }

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

    private void fillPlacar() {
        //     String sql

    }

    private void aluMatri() {
//        tfNumber.setStyle("-fx-background-color: #5650CA;"); //The background of the cell in yellow
//        tfNumber.setStyle("-fx-text-fill: white;"); //The background of the cell in yellow
        dadosMat = FXCollections.observableArrayList();

        try {
            sqlRelat = sqlRelat.replace("$P(CODGRADE)", pi.getCodGrade().toString());

            if (pi.getTipo() == null) {
                sqlRelat = sqlRelat + " AND TipoRegOuv is NULL ";
            } else {
                if (!pi.getTipo().equals("")) { // É O TOTAL, não tem AND TipoRegOuv
                    String cond = " AND TipoRegOuv = \"$P(TIPOREG)\"";
                    cond = cond.replace("$P(TIPOREG)", pi.getTipo());
                    sqlRelat = sqlRelat + cond;
                }
            }
            
            sqlRelat = sqlRelat + " ORDER BY dcg.nome, cur.curso ";
            sqlMatric = sqlMatric + sqlRelat;
            //sqlMatric = sqlMatric.replace("$P(TIPOREG)", pi.getTipo());
            System.out.println("###>>>");
            System.out.println(sqlMatric);
            ResultSet rs = con.createStatement().executeQuery(sqlMatric);

            while (rs.next()) {
                dadosMat.add(new PlacarAluno(
                        rs.getInt("dcg.DadoCadastroGeralId"),
                        rs.getInt("dcg.matricula"),
                        rs.getString("dcg.nome"),
                        rs.getString("cur.curso"),
                        rs.getString("cad.Disciplina"),
                        rs.getString("pup.TipoRegOuv")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipalController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018j - em aluMatri()", "" + ex, 2);
        }
        tvAllunos.setItems(dadosMat);
        tvAllunos.refresh();
        lblRegs2.setText("Registros exibidos: " + tvAllunos.itemsProperty().get().size());
        lblQtd.setText("" + tvAllunos.itemsProperty().get().size());
        lblTitPlacar.setText(pi.getTitulo());
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
                } catch (IOException e) {
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

    public void initRegDis() {
        dadosRD = FXCollections.observableArrayList();
        if (tvProgramas.getSelectionModel().getSelectedItem() != null) {
            ProgramasAluno proAlu = tvProgramas.getSelectionModel().getSelectedItem();

            sqlDisciplinas = SQLB_DISCIPLINAS
                    + " WHERE dcp.DadoCadastroGeralId = " + tvAllunos.getSelectionModel().getSelectedItem().getCodALuno() + " "
                    + "  AND c.curso = '" + proAlu.getCurso() + "'"
                    + "  ORDER BY pup.DadoCadastroProgramaId, cd.AnoLetivo DESC, cd.SemestreId DESC";

            try {
                System.out.println(sqlDisciplinas);

                ResultSet rs = con.createStatement().executeQuery(sqlDisciplinas);
                totCre = 0;
                totCreAprov = 0;
                while (rs.next()) {
                    dadosRD.add(new DisciProgAluno(
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
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipoRegOuv"));

        tvRegDis.setItems(dadosRD);
        tvRegDis.refresh();
        String espacos = "     |     ";
        lblRegs21.setText("   Disciplinas: " + tvRegDis.itemsProperty().get().size() + espacos
                + "Créd. listados: " + totCre + espacos + "Créd. válidos: " + totCreAprov);
    }

    private void AbrePrograma(int modo) { // modo = 1 INS, modo = 2 = EDI
        if (modo == 2) {

            if (tvProgramas.getSelectionModel().getSelectedItem() != null) {
                gbClicouInsPro = false;
                gbClicouEdiPro = true;
                pro_atual = tvProgramas.getSelectionModel().getSelectedItem();
                int codProAlu = tvAllunos.getSelectionModel().getSelectedItem().getCodALuno();

                jpaPro = new DadocadastroprogramaJpaController();
                gbRegProAlu = jpaPro.findDadocadastroprograma(codProAlu);
            }
        } else {
            gbClicouInsPro = true;
            gbClicouEdiPro = false;
            gbInsPro = new InserePro();

            gbInsPro.setAnoLetivo(gbUltSem.getUltAno());
            gbInsPro.setSemestre(gbUltSem.getUltSem());

            gbInsPro.setCodAluno((short) tvAllunos.getSelectionModel().getSelectedItem().getCodALuno());
            gbInsPro.setNomeAluno(tvAllunos.getSelectionModel().getSelectedItem().getNome());
            //    gbInsPro.setFotoMaior(ivFotoAluno.getImage());

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

    public void initColsPro() {
        prolist = FXCollections.observableArrayList();
        if (tvAllunos.getSelectionModel().getSelectedItem() != null) {
            sqlProgramas = SQLB_PROGRAMAS
                    + "WHERE (dcp.DadoCadastroGeralId = " + tvAllunos.getSelectionModel().getSelectedItem().getCodALuno() + " )"
                    + "ORDER BY dcp.anoLetivo DESC";

            try {
                System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
                System.out.println(sqlProgramas);
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

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
                            rs.getDate("dataCadastro")));

                }

            } catch (SQLException ex) {
                Logger.getLogger(DadocadastrogeralController.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018 - em initColsPro()", "" + ex, 2);
            }
        }
        tc2.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("semestre"));

        tvProgramas.setItems(prolist);
        tvProgramas.refresh();
        // lblRegistrosPro.setText("Registros exibidos: " + tvProgramas.itemsProperty().get().size()); //rs2.getString(1));

    }

    @FXML
    private void clicouListagem(ActionEvent event) {
        pnListagem.setVisible(true);
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

    @FXML
    private void clicouImprimeListagem(ActionEvent event) throws JRException, IOException {
        geraLista(4); // TELA
    }

    @FXML
    private void clicouFechaPainelListagem(ActionEvent event) {
        pnListagem.setVisible(false);
    }

    @FXML
    private void clicouPresEsq(MouseEvent event) {
        spPlacar.setDividerPositions(0.3527);
    }

    @FXML
    private void clicouPresDir(MouseEvent event) {
        spPlacar.setDividerPositions(0.6153);
    }

    private void geraLista(int tipo) throws JRException, IOException {
        String nomeRepo;
        Listagens lisSele;
        File destFile = null;

        String sqlMatric2
                = " SELECT pup.CadastroAlunoDisciplinaId, pup.DadoCadastroProgramaId, dcg.DadoCadastroGeralId, dcg.matricula, dcg.nome, cur.curso, cad.Disciplina, pup.TipoRegOuv "
                + " FROM cadastroalunodisciplina pup "
                + " LEFT JOIN dadocadastroprograma dcp ON dcp.DadoCadastroProgramaId = pup.DadoCadastroProgramaId   "
                + " LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId   "
                + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId   "
                + " LEFT JOIN curso cur ON dcp.CursoId = cur.CursoId   ";

        if (cbListagens.getSelectionModel().getSelectedItem() != null) {
            jpaLis = new ListagensJpaController();
            lisSele = jpaLis.getObjeto(cbListagens.getSelectionModel().getSelectedItem());

            nomeRepo = (gbDeOnde == "Jpa_EvedSys_Server") ? lisSele.getJasperServer() : lisSele.getJasperLocal();

            try {

                InputStream jasperF = GradeDisMasController.class.getResourceAsStream("/reporters/" + nomeRepo + ".jasper");

                Map<String, Object> parametros = new HashMap<>();

                Date agora = new Date();
                String fago = GBFORMATDATAHORA.format(agora);

                condicao = condicao + " ORDER BY dcg.nome, cur.curso ";

                if (cbListagens.getSelectionModel().getSelectedItem().equals("Listagem alunos - cód., matr., aluno, programa, ano, semestre")) { // listagem placar
                    parametros.put("RESTANTE_COND", sqlRelat);
                    parametros.put("TITULO", pi.getSemestre() + " - Assentos - " + lblTitPlacar.getText() + " (" + lblQtd.getText() + " registros)");
                    parametros.put("DATAHORA", fago);
                } else {
                }

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperF, parametros, con);
                String nomeArq = "";
                ButtonType abrir = new ButtonType("abrir", ButtonBar.ButtonData.OK_DONE);
                ButtonType naoabrir = new ButtonType("não abrir", ButtonBar.ButtonData.CANCEL_CLOSE);
                String nomeArquivoOK = "";
                String nomeLimpo = limpaNome(lblTitPlacar.getText());

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

    public ObservableList<String> getListagens(Integer tipo) {
        jpaLis = new ListagensJpaController();
        dadosLis = FXCollections.observableArrayList(jpaLis.getDescricoes(tipo));

        if (dadosLis == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosLis;
        }
    }

    private Boolean abre_ou_nao_relat(String nomeDestFile, String tipoArq, ButtonType abrir, ButtonType naoabrir) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deseja abrir o arquivo " + nomeDestFile + " agora?", abrir, naoabrir);
        alert.setHeaderText("O arquivo " + tipoArq + " foi gerado com sucesso.");
        alert.setContentText("\nDeseja abrir o arquivo " + nomeDestFile + " agora?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.orElse(naoabrir) == abrir) {
            try {
                Desktop.getDesktop().open(new File(nomeDestFile));
            } catch (Exception e) {
                mostraMsg(nomeDestFile, "Erro 0283z ao abrir arquivo\n" + e, 2);
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
        //  nomeDestFile = nomeDestFile.replaceAll("|", "_");ISSO AQUI DÁ PAU
        nomeDestFile = nomeDestFile.replaceAll("<", "_");
        nomeDestFile = nomeDestFile.replaceAll(">", "_");
        nomeDestFile = nomeDestFile.replaceAll("“", "_");
        nomeDestFile = nomeDestFile.replaceAll("'", "_");
        return nomeDestFile;
    }
}
