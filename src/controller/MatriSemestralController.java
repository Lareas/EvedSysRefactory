/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbEspecial;
import static main.Login.gbUser;
import static main.Login.gbCrudAmareloMS;

import static main.Login.gbDataCrudMS;
import static main.Login.GBFORMATDATAHORA;
import static main.Login.gbRegCrudMS;
import static main.Login.gbRegMatriSemCabec;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.DataCrudMS;
import entities.DisMatriSemestre;
import entities.EsFotosalunos;
import entities.EsMatrisem;
import entities.RegCrudMS;
import funcoes.DBConnector;
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
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.EsFotosalunosJpaController;
import jpa_controler.EsMatrisemJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class MatriSemestralController implements Initializable {

    @FXML
    private Pane top_pane;
    @FXML
    private JFXButton buRecIni1;
    @FXML
    private JFXButton buRecIni;
    @FXML
    private JFXButton buReciRec;
    @FXML
    private JFXButton buReciPes;
    @FXML
    private JFXButton buRecImp;
    @FXML
    private JFXButton buUsers;
    @FXML
    private JFXButton buRecUser;
    @FXML
    private ScrollPane spMatriSem;
    @FXML
    private FontAwesomeIconView fvBoneco;
    @FXML
    private JFXDatePicker dpDataMatri;
    @FXML
    private Label lblAlu;
    @FXML
    private Label lblPro;
    @FXML
    private TableView<DisMatriSemestre> tvMatriDis;
    @FXML
    private TableColumn<DisMatriSemestre, String> tcTipoAluno;
    @FXML
    private TableColumn<DisMatriSemestre, Date> tcData;
    @FXML
    private TableColumn<DisMatriSemestre, String> tcHorario;
    @FXML
    private TableColumn<DisMatriSemestre, String> tcDisciplina;
    @FXML
    private TableColumn<DisMatriSemestre, String> tcProfessor;
    @FXML
    private TableColumn<DisMatriSemestre, Short> tcCred;
    @FXML
    private TableColumn<DisMatriSemestre, Short> tcTotAlu;
    @FXML
    private TableColumn<DisMatriSemestre, Short> tcTotDispRE;
    @FXML
    private TableColumn<DisMatriSemestre, Short> tcTotDisoO;
    @FXML
    private TableColumn<DisMatriSemestre, Integer> tcCADI;
    @FXML
    private Label lblCreRE;
    @FXML
    private Label lblCreO;
    @FXML
    private Label lblValCreO;
    @FXML
    private Label lblValCreRE;
    @FXML
    private Label lblTotCred;
    @FXML
    private TextArea taObsMatricula;
    @FXML
    private Label lblSemestre;
    @FXML
    private ImageView ivFotoAluno;
    @FXML
    private JFXButton buMinApa;
    @FXML
    private TextField lblRegDis;

    @FXML
    private Label lblCredRef;
    @FXML
    private Label lblTaxaRef;
    @FXML
    private Label lblDataAlt12;
    @FXML
    private CheckBox chkVistoVerde;
    @FXML
    private CheckBox chkVistoRoxo;
    @FXML
    private CheckBox chkVistoAmarelo;
    @FXML
    private CheckBox chkVistoVermelho;
    @FXML
    private Label lblVistoVerde;
    @FXML
    private Label lblVistoRoxo;
    @FXML
    private Label lblVistoAmarelo;
    @FXML
    private Label lblVistoVermelho;
    @FXML
    private AnchorPane apCabec;
    @FXML
    private Line lineRed;
    @FXML
    private JFXButton buFechaMS;

    ObservableList<DisMatriSemestre> dislist;

    public Connection con = DBConnector.getConnection();
    private CadastroalunodisciplinaJpaController jpaCad;

//    private final String SQLLOADDIS
//            = "SELECT pup.CadastroAlunoDisciplinaId, cad.CadastroDisciplinaId, cad.CodGrade, cad.disciplina, cad.labelData, cad.labelHora, fun.nome as prof1, fun2.nome as prof2, cad.Credito, turn.turno, tipo.tipodisc  "
//            + " FROM cadastroalunodisciplina pup "
//            + "               LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId  "
//            + "               LEFT JOIN disciplina dis ON cad.DisciplinaId = dis.DisciplinaId  "
//            + "               LEFT JOIN funcionario fun ON cad.FuncionarioId = fun.FuncionarioId "
//            + "               LEFT JOIN funcionario fun2 ON cad.Professor2 = fun2.FuncionarioId  "
//            + "               LEFT JOIN turno turn ON cad.TurnoId = turn.TurnoId  "
//            + "               LEFT JOIN es_tipodis tipo ON cad.TIpoDis = tipo.codtipodis ";
    private EsFotosalunosJpaController jpaFotos;
    EsFotosalunos reg_foto = new EsFotosalunos();
    private ObservableList<EsFotosalunos> dadosFoto;

    @FXML
    private void clicouTipoAluno(TableColumn.CellEditEvent edittedCell) {
        gbRegCrudMS = new RegCrudMS();
        DisMatriSemestre selec = tvMatriDis.getSelectionModel().getSelectedItem();
        String upper = edittedCell.getNewValue().toString().toUpperCase();
        selec.setTipoRegOuv(upper);
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        lineRed.setVisible(gbEspecial);

        // INIBE VISTOS DE ACORDO COM O CALENDÁRIO
        switch (gbUser.getCoduser()) {
            case 6: // CLAUDIA
                chkVistoVerde.setDisable(true);
                chkVistoRoxo.setDisable(false);
                chkVistoAmarelo.setDisable(true);
                chkVistoVermelho.setDisable(true);
                break;
            case 7: // LEILA
                chkVistoVerde.setDisable(false);
                chkVistoRoxo.setDisable(true);
                chkVistoAmarelo.setDisable(true);
                chkVistoVermelho.setDisable(true);
                break;
            case 8: // ELIANE
                chkVistoVerde.setDisable(true);
                chkVistoRoxo.setDisable(true);
                chkVistoAmarelo.setDisable(true);
                chkVistoVermelho.setDisable(false);
                break;
            case 10: // ÉRIKA
                chkVistoVerde.setDisable(true);
                chkVistoRoxo.setDisable(true);
                chkVistoAmarelo.setDisable(false);
                chkVistoVermelho.setDisable(true);
                break;
            case 2: // lfa
                chkVistoVerde.setDisable(false);
                chkVistoRoxo.setDisable(false);
                chkVistoAmarelo.setDisable(false);
                chkVistoVermelho.setDisable(false);
                break;
            default:
                chkVistoVerde.setDisable(true);
                chkVistoRoxo.setDisable(true);
                chkVistoAmarelo.setDisable(true);
                chkVistoVermelho.setDisable(true);
        }

        tcCADI.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, Integer>("cadastroAlunoDisciplinaId"));
        tcTipoAluno.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, String>("tipoRegOuv"));
        tcData.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, Date>("labelData"));
        tcHorario.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, String>("labelHorario"));
        tcDisciplina.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, String>("descricao"));
        tcProfessor.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, String>("professor1"));
        tcCred.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, Short>("credito"));
        tcTotAlu.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, Short>("totAlu"));
        tcTotDispRE.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, Short>("disRegEsp"));
        tcTotDisoO.setCellValueFactory(new PropertyValueFactory<DisMatriSemestre, Short>("dispOuv"));
        leFoto(Integer.valueOf(gbRegMatriSemCabec.getCodAluno()));

        inicSemestre();
        InicColsMatri();

        if (gbEspecial) {
            apCabec.setStyle("-fx-background-color : #FDBCA6;");
        } else {
            apCabec.setStyle("-fx-background-color : #F0F0F0;");
        }

    }

    private void inicSemestre() {
        dpDataMatri.setValue((gbRegMatriSemCabec.getDataMatricula() != null) ? gbRegMatriSemCabec.getDataMatricula().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        lblAlu.setText("Aluno: " + gbRegMatriSemCabec.getNomeAluno());
        lblPro.setText("Programa: " + gbRegMatriSemCabec.getNomePrograma());
        lblSemestre.setText("Ano: " + gbRegMatriSemCabec.getAnoLetivo() + "  -  " + gbRegMatriSemCabec.getSemestre() + "º semestre");

        if (gbRegMatriSemCabec.getDataSecretaria1() != null) {
            lblVistoVerde.setText("" + gbRegMatriSemCabec.getDataSecretaria1());
        } else {
            lblVistoVerde.setText("");
        }

        if (gbRegMatriSemCabec.getDataSecretaria2() != null) {
            lblVistoRoxo.setText("" + gbRegMatriSemCabec.getDataSecretaria2());
        } else {
            lblVistoRoxo.setText("");
        }

        if (gbRegMatriSemCabec.getDataFinanceiro() != null) {
            lblVistoAmarelo.setText("" + gbRegMatriSemCabec.getDataFinanceiro());
        } else {
            lblVistoAmarelo.setText("");
        }

        if (gbRegMatriSemCabec.getDataCoordenacao() != null) {
            lblVistoVermelho.setText("" + gbRegMatriSemCabec.getDataCoordenacao());
        } else {
            lblVistoVermelho.setText("");
        }

        taObsMatricula.setText(gbRegMatriSemCabec.getObsMatri());
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

    @FXML
    private void clicouIniciarMatri(ActionEvent event) {
    }

    @FXML
    private void clicouAlteraMatri(ActionEvent event) {
    }

    @FXML
    private void clicouSemestre(ActionEvent event) {
    }

    @FXML
    private void clicouDisciplinas(ActionEvent event) {
    }

    @FXML
    private void ClicouPagamento(ActionEvent event) {
    }

    @FXML
    private void clicouImprimir(ActionEvent event) {
    }

    @FXML
    private void clicouRelatorios(ActionEvent event) {
    }

    @FXML
    private void clicouTFFdnasc(KeyEvent event) {
    }

    @FXML
    private void clicouInsereMDis(ActionEvent event) {
        gbCrudAmareloMS = 1; // EDITA DISCIPLINA
        chamaCrudMatriDis();
    }

    @FXML
    private void clicouEditaMDis(ActionEvent event) {
        if (tvMatriDis.getSelectionModel().getSelectedItem() != null) {
            gbRegCrudMS = new RegCrudMS();
            gbRegCrudMS.setCadastroAlunoDisciplina(tvMatriDis.getSelectionModel().getSelectedItem().getCadastroAlunoDisciplinaId());
            gbRegCrudMS.setNomeDisc(tvMatriDis.getSelectionModel().getSelectedItem().getDescricao());
            gbCrudAmareloMS = 2; // EDITA DISCIPLINA
            chamaCrudMatriDis();
        }
    }

    @FXML
    private void clicouApagaMDis(ActionEvent event) {
        if (tvMatriDis.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Apagar a disciplina <" + tvMatriDis.getSelectionModel().getSelectedItem().getDescricao() + ">?");
            alert.setContentText("Deseja realmente apagar este registro?\nOs dados serão perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaCad = new CadastroalunodisciplinaJpaController();
                    jpaCad.destroy(tvMatriDis.getSelectionModel().getSelectedItem().getCadastroAlunoDisciplinaId());
                    InicColsMatri();
                    mostraMsg("Dados apagados com sucesso", "", 3);
                    // PREENCHE OBd COM Eds
                } catch (Exception e) {
                    mostraMsg("Erro ao apagar registro", "" + e, 2);
                }
            }
        }
    }

    private void chamaCrudMatriDis() {
        if (gbRegCrudMS == null) {
            gbRegCrudMS = new RegCrudMS();
        }

        // carrega variável global para CrusMS
        gbDataCrudMS = new DataCrudMS();
        gbDataCrudMS.setNomeMatriSem(gbRegMatriSemCabec.getNomeMatriSem());
        if (gbCrudAmareloMS == 2) { // EDITA DISCIPLINA == 2) {
            gbDataCrudMS.setNomeMatriSem(gbRegMatriSemCabec.getNomeMatriSem());
            gbRegCrudMS.setCadastroAlunoDisciplina(tvMatriDis.getSelectionModel().getSelectedItem().getCadastroAlunoDisciplinaId());
            gbRegCrudMS.setCms(tvMatriDis.getSelectionModel().getSelectedItem().getCodmatrisem());
            gbRegCrudMS.setTipoReg(tvMatriDis.getSelectionModel().getSelectedItem().getTipoRegOuv());
            gbRegCrudMS.setNomeDisc(tvMatriDis.getSelectionModel().getSelectedItem().getDescricao());
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CrudMatriDis.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Disciplinas desta matrícula");
            stage.setScene(scene);

            stage.showAndWait();
            InicColsMatri();

        } catch (IOException ex) {
            Logger.getLogger(MatriSemestralController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 004 ao carregar CrudMin", "" + ex, 0);
        }
    }

    private void InicColsMatri() {

        dislist = FXCollections.observableArrayList();
        String sqlDisLis
                = "SELECT pup.CadastroAlunoDisciplinaId, pup.DadoCadastroProgramaId, pup.CodMatriSem, pup.CodGrade, pup.TipoRegOuv, cad.labelData, cad.labelHora, dis.Disciplina, fun.nome, cad.credito, cad.descricao "
                + "FROM cadastroalunodisciplina pup "
                + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId  = cad.CadastroDisciplinaId  "
                + "LEFT JOIN funcionario fun ON cad.FuncionarioId = fun.FuncionarioId "
                + "LEFT JOIN disciplina dis ON cad.DisciplinaId = dis.DisciplinaId   "
                + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
                + " WHERE dcp.DadoCadastroGeralId = " + gbRegMatriSemCabec.getCodAluno()
                + " AND pup.DadoCadastroProgramaId = " + gbRegMatriSemCabec.getCodPrograma()
                + " AND pup.codgrade = " + gbRegMatriSemCabec.getCodGrade();
//        = "SELECT pup.CadastroAlunoDisciplinaId, pup.DadoCadastroProgramaId, pup.CodMatriSem, pup.CodGrade, pup.TipoRegOuv, cad.labelData, cad.labelHora, dis.Disciplina, fun.nome, cad.credito "
//                + "FROM cadastroalunodisciplina pup "
//                + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId  = cad.CadastroDisciplinaId  "
//                + "LEFT JOIN funcionario fun ON cad.FuncionarioId = fun.FuncionarioId "
//                + "LEFT JOIN disciplina dis ON cad.DisciplinaId = dis.DisciplinaId   "
//                + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
//                + " WHERE dcp.DadoCadastroGeralId = " + gbRegMatriSemCabec.getCodAluno()
//                + " AND pup.DadoCadastroProgramaId = " + gbRegMatriSemCabec.getCodPrograma()
//                + " AND pup.codgrade = " + gbRegMatriSemCabec.getCodGrade();

        try {
            System.out.println(sqlDisLis);

            ResultSet rs = con.createStatement().executeQuery(sqlDisLis);
            while (rs.next()) {
                dislist.add(new DisMatriSemestre(
                        rs.getInt("pup.CadastroAlunoDisciplinaId"),
                        rs.getInt("pup.DadoCadastroProgramaId"),
                        rs.getInt("pup.CodMatriSem"),
                        rs.getInt("pup.CodGrade"),
                        rs.getString("pup.TipoRegOuv"),
                        rs.getString("cad.labelData"),
                        rs.getString("cad.labelHora"),
                        rs.getString("dis.Disciplina"),
                        rs.getString("fun.nome"),
                        rs.getShort("cad.credito")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MatriSemestralController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018 - em initColsPro()", "" + ex, 2);
        }

        // CALCULA NUMERO DE DISCIPLINAS E VALORES
        Integer nreg = 0, nesp = 0, nouv = 0, ndis = 0, creReg = 0, creOuv = 0, ngra = 0;
        Float valCreRE = 0F, valCreO = 0F;

        tvMatriDis.setItems(dislist);

        DisMatriSemestre reg = new DisMatriSemestre();
        for (int i = 0; i < dislist.size(); i++) {
            reg = dislist.get(i);
            if (reg.getTipoRegOuv() == null) {
                // tenta preencher
                if (gbEspecial) {
                    ++nesp;
                    creReg = creReg + reg.getCredito();
                } else {
                    if (gbRegMatriSemCabec.getNomePrograma().contains("uvinte")) {
                        ++nouv;
                        creOuv = creOuv + reg.getCredito();
                    } else {
                        ++nreg;
                        creReg = creReg + reg.getCredito();
                    }
                }
            } else {
                if (reg.getTipoRegOuv().equals("R")) {
                    ++nreg;
                    creReg = creReg + reg.getCredito();
                } else {
                    if (reg.getTipoRegOuv().equals("E")) {
                        ++nesp;
                        creReg = creReg + reg.getCredito();
                    } else {
                        if (reg.getTipoRegOuv().equals("O")) {
                            ++nouv;
                            creOuv = creOuv + reg.getCredito();
                        } else {
                            if (reg.getTipoRegOuv().equals("G")) {
                                ++ngra;
                                //  creOuv = creOuv + reg.getCredito();
                            }
                        }
                    }
                }
            }

            String spa = "     ";
            ndis = tvMatriDis.itemsProperty().get().size();
            //  valCre
            lblRegDis.setText("Nº Disciplinas: " + ndis + spa + "(R): " + nreg + spa + "(E): " + nesp + spa + "(O): " + nouv + spa
                    + "(G): " + ngra + spa + "     Nº Créditos (RE): " + creReg + spa + "     Nº Créditos (O): " + creOuv);

            tvMatriDis.refresh();
            //  lblRegistrosMin.setText("Programas Listados: " + ); //rs2.getString(1));
        }
    }

    @FXML
    private void clicouSec1(ActionEvent event
    ) {
        if (chkVistoVerde.isSelected()) {
            lblVistoVerde.setText(GBFORMATDATAHORA.format(new Date()));
        }
    }

    @FXML
    private void clicouSec2(ActionEvent event
    ) {
        if (chkVistoRoxo.isSelected()) {
            lblVistoRoxo.setText(GBFORMATDATAHORA.format(new Date()));
        }
    }

    @FXML
    private void clicouFin(ActionEvent event
    ) {
        if (chkVistoAmarelo.isSelected()) {
            lblVistoAmarelo.setText(GBFORMATDATAHORA.format(new Date()));
        }
    }

    @FXML
    private void clicouCoo(ActionEvent event
    ) {
        if (chkVistoVermelho.isSelected()) {
            lblVistoVermelho.setText(GBFORMATDATAHORA.format(new Date()));
        }
    }

    @FXML
    private void clicouFechaMS(ActionEvent event
    ) {
        // RECUPERA REGISTRO MATRISEM PARA ATUALIZAR VISTOS (E, FUTURAMENTE, FINANCEIRO)
        EsMatrisemJpaController jpaMS = new EsMatrisemJpaController();
        EsMatrisem esmatrisem = jpaMS.findEsMatrisem(gbRegMatriSemCabec.getNomeMatriSem().getCodMatriSem());

        if (chkVistoVerde.isSelected()) {
            try {
                esmatrisem.setDataSecretaria1(GBFORMATDATAHORA.parse(lblVistoVerde.getText()));
                System.out.println(esmatrisem.getDataSecretaria1());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (chkVistoRoxo.isSelected()) {
            try {
                esmatrisem.setDataSecretaria2(GBFORMATDATAHORA.parse(lblVistoRoxo.getText()));
                System.out.println(esmatrisem.getDataSecretaria2());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (chkVistoAmarelo.isSelected()) {
            try {
                esmatrisem.setDataFinanceiro(GBFORMATDATAHORA.parse(lblVistoAmarelo.getText()));
                System.out.println(esmatrisem.getDataFinanceiro());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (chkVistoVermelho.isSelected()) {
            try {
                esmatrisem.setDataCoordenacao(GBFORMATDATAHORA.parse(lblVistoVermelho.getText()));
                System.out.println(esmatrisem.getDataCoordenacao());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        try {
            esmatrisem.setDataAlt(new Date());
            esmatrisem.setCodUserAlt(gbUser.getCoduser());
            jpaMS.edit(esmatrisem); //*** GRAVA EDIÇÃO
        } catch (Exception e) {
            System.out.println(esmatrisem);
            System.out.println("\n============================");
            System.out.println(e);
            System.out.println("============================\n");
            mostraMsg("Erro 014k ao gravar novos dados.", "> " + e, 2);
        }
        Stage stage = (Stage) buFechaMS.getScene().getWindow();

        // GRAVA VISTOS EM MATRISEM
        stage.close();

    }
}
