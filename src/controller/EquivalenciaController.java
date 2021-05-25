/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.GBFORMATDATAHORA;
import static main.Login.gbUltSem;
import static main.Login.gbUser;
import static main.Login.gbCrudEqui;
import static main.Login.gbEquivalencia;
import com.jfoenix.controls.JFXButton;
import entities.Cadastroalunodisciplina;
import entities.Cadastrodisciplina;
import entities.EquivalenciaGrid;
import entities.EsEquivalencia;
import entities.EsEquivalenciadet;
import entities.EsFotosalunos;
import entities.EsGrade;
import entities.GradeDispensa;
import funcoes.DBConnector;
import static funcoes.MyFunc.getEntityManager;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.CadastrodisciplinaJpaController;
import jpa_controler.EsEquivalenciaJpaController;
import jpa_controler.EsEquivalenciadetJpaController;
import jpa_controler.EsFotosalunosJpaController;
import jpa_controler.EsGradeJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class EquivalenciaController implements Initializable {

    @FXML
    private ComboBox<String> cbDisciplina;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private JFXButton buRefDen;
    @FXML
    private Label lblAluno;
    @FXML
    private Label lblTipo;
    @FXML
    private ImageView ivFotoAluno;
    private CheckBox chkDocsDigi;
    @FXML
    private TextArea taObs;
    @FXML
    private TextField lblRegistrosPro;
    @FXML
    private JFXButton buMinApa;
    @FXML
    private ComboBox<String> cbGrade;
    private Label lblPrograma;
    //  private ComboBox<String> cbSituDis;
    @FXML
    private TableView<EquivalenciaGrid> tvEquivalenciaDet;
    @FXML
    private TableColumn<EquivalenciaGrid, String> tcIns;
    @FXML
    private TableColumn<EquivalenciaGrid, String> tcDis;
    @FXML
    private TableColumn<EquivalenciaGrid, Float> tcCar;
    @FXML
    private TableColumn<EquivalenciaGrid, Integer> tcCre;
    @FXML
    private TableColumn<EquivalenciaGrid, Integer> tcAno;
    @FXML
    private TableColumn<EquivalenciaGrid, String> tcNot;
    @FXML
    private TableColumn<EquivalenciaGrid, Integer> tcPK;
    @FXML
    private TableColumn<EquivalenciaGrid, Integer> tcCodEq;
    @FXML
    private JFXButton buApagaEquivMaster;

    private EsGradeJpaController jpaGra;
    private CadastrodisciplinaJpaController jpaCad;
//    private CadaludissitJpaController jpaSit;
    private EsEquivalenciadetJpaController jpaEqDet;
    private CadastroalunodisciplinaJpaController jpaPup;
    private EsEquivalenciaJpaController jpaEqPai;

    ObservableList<EquivalenciaGrid> dislist;
    public Connection con = DBConnector.getConnection();

    public final Integer MAXDISCI = 80;
    public final Integer MAXGRADE = 100;
//    Integer[] aCadis;
//    Integer[] aGrade;
//    String[] aConcatGrade;

    private ObservableList<String> dadosGra;
    private ObservableList<String> dadosDis;
    //   private ObservableList<String> dadosSit;
    public static EquivalenciaGrid gbRegEqDet;
    public EsEquivalencia equi;

    public Integer codGrade;

    private EsFotosalunosJpaController jpaFotos;
    EsFotosalunos reg_foto = new EsFotosalunos();
    private ObservableList<EsFotosalunos> dadosFoto;
    @FXML
    private TableColumn<?, ?> tcDis1;
    @FXML
    private ComboBox<?> cbGrade1;
    @FXML
    private ComboBox<?> cbGrade2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcPK.setCellValueFactory(new PropertyValueFactory<EquivalenciaGrid, Integer>("codEquiDet"));
        tcCodEq.setCellValueFactory(new PropertyValueFactory<EquivalenciaGrid, Integer>("codEqui"));
        tcIns.setCellValueFactory(new PropertyValueFactory<EquivalenciaGrid, String>("nomeInstitu"));
        tcDis.setCellValueFactory(new PropertyValueFactory<EquivalenciaGrid, String>("nomeDisciplina"));
        tcCar.setCellValueFactory(new PropertyValueFactory<EquivalenciaGrid, Float>("carga"));
        tcCre.setCellValueFactory(new PropertyValueFactory<EquivalenciaGrid, Integer>("credito"));
        tcAno.setCellValueFactory(new PropertyValueFactory<EquivalenciaGrid, Integer>("ano"));
        tcNot.setCellValueFactory(new PropertyValueFactory<EquivalenciaGrid, String>("nota"));
        leFoto(Integer.valueOf(gbEquivalencia.getCodAluno()));

        gbRegEqDet = new EquivalenciaGrid();

        // INICIALIZA VETORES DA DISCIPLINA
//        aCadis = new Integer[MAXDISCI];
//        aConcatGrade = new String[MAXGRADE];
        jpaGra = new EsGradeJpaController();
        jpaCad = new CadastrodisciplinaJpaController();
        //    jpaSit = new CadaludissitJpaController();
        jpaEqPai = new EsEquivalenciaJpaController();
        jpaEqDet = new EsEquivalenciadetJpaController();
        jpaPup = new CadastroalunodisciplinaJpaController();

//        for (int i = 0; i < aCadis.length; i++) {
//            aCadis[i] = 0;
//        }
//
//        for (int i = 0; i < aConcatGrade.length; i++) {
//            aConcatGrade[i] = " ";
//        }
        // PREENCHE COMBOS ANO E SEMESTRE DA MATRÍCULA SEMESTRAL
        cbGrade.setItems(getGradeMS());
        //   cbSituDis.setItems(getSitu());

        lblAluno.setText("Aluno: " + gbEquivalencia.getNomeALuno());
        lblPrograma.setText("Programa: " + gbEquivalencia.getNomePrograma());

        if (gbEquivalencia.getModoDis() == 1) { // inserção
            lblRegistrosPro.setText("Inserido por: " + gbUser.getLogin() + "   |   " + "Data Ins.: " + GBFORMATDATAHORA.format(new Date()));
            tvEquivalenciaDet.setItems(null);
            taObs.setText("");
            chkDocsDigi.setSelected(false);

            // SELECIONA SEMESTRE ATUAL
            String semAtual = gbUltSem.getUltAno() + "." + gbUltSem.getUltSem();
            cbGrade.getSelectionModel().select(semAtual);
            preencheDis();

        } else {
            // edição - carrega dados
            // calcula máscara da grade
            EsGrade gra = jpaGra.findEsGrade(gbEquivalencia.getCodGrade());
            String sGra = gra.getAnoLetivo() + "." + gra.getSemestre();

            Cadastrodisciplina cad = jpaCad.findCadastrodisciplina(gbEquivalencia.getCodDisciplina());
            //  Cadaludissit sit = jpaSit.findCadaludissit(gbEquivalencia.getCodSituacao().shortValue());

            cbGrade.getSelectionModel().select((sGra != null) ? sGra : null);
            cbDisciplina.getSelectionModel().select((cad.getDescricao() != null) ? cad.getDescricao() : null);
            //   cbSituDis.getSelectionModel().select((sit.getDescricao() != null) ? sit.getDescricao() : null);
            taObs.setText(gbEquivalencia.getObservacoes());
            chkDocsDigi.setSelected((gbEquivalencia.getDocsDigi() != null) ? gbEquivalencia.getDocsDigi() : false);

            lblRegistrosPro.setText("Alterado por: " + gbUser.getLogin() + "   |   " + "Data Alt.: " + GBFORMATDATAHORA.format(new Date()));
            preencheDis();
            insGridEqui();
        }

    }

    public ObservableList<GradeDispensa> getGradeEquiv() {

        jpaGra = new EsGradeJpaController();
        ObservableList<GradeDispensa> dadosGraDis = FXCollections.observableArrayList(jpaGra.getCodsGrades());

        if (dadosGraDis == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosGraDis;
        }
    }

//    public ObservableList<String> getDisci() {
////        jpaCad = new EsGradeJpaController();
////        ObservableList<String> dadosDisci = FXCollections.observableArrayList(jpaCad.getNomeDasDisci());
////
////        if (dadosGra == null) {
////            return FXCollections.observableArrayList();
////        } else {
////            return dadosGra;
////        }
//        return null;
//    }
    private void insGridEqui() {

        dislist = FXCollections.observableArrayList();
        String sqlDispensaDet
                = "SELECT eq.CodEquivalenciaDet, eq.CodEquivalencia, eq.Instituicao, "
                + " eq.Disciplina, eq.Carga, eq.Credito, eq.Ano, eq.Nota "
                + " FROM es_equivalenciadet eq "
                + " WHERE eq.CodEquivalencia = " + gbEquivalencia.getCodEquivalencia()
                + " ORDER BY eq.CodEquivalenciaDet ";

        try {
            System.out.println(sqlDispensaDet);

            ResultSet rs = con.createStatement().executeQuery(sqlDispensaDet);
            while (rs.next()) {
                dislist.add(new EquivalenciaGrid(
                        rs.getInt("CodEquivalenciaDet"),
                        rs.getInt("CodEquivalencia"),
                        rs.getString("Instituicao"),
                        rs.getString("Disciplina"),
                        rs.getFloat("Carga"),
                        rs.getString("Credito"),
                        rs.getString("Ano"),
                        rs.getString("Nota")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Dispensa.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018g - em insGridEqui()", "" + ex, 2);
        }

        // CALCULA NUMERO DE DISCIPLINAS E VALORES
       // Integer numcre = 0;

        tvEquivalenciaDet.setItems(dislist);

        EquivalenciaGrid reg = new EquivalenciaGrid();
        for (int i = 0; i < dislist.size(); i++) {
            reg = dislist.get(i);
            //   if (reg.getCredito() != null) {
           // numcre = numcre + reg.getCredito();
            //   }
        }

        String spa = "     ";
        int ndisp = tvEquivalenciaDet.itemsProperty().get().size();
        lblRegistrosPro.setText("Nº Disciplinas: " + ndisp + spa +      "      Inserido por: " + gbUser.getLogin() + "   |   " + "Data Ins.: " + GBFORMATDATAHORA.format(new Date()));

        tvEquivalenciaDet.setItems(dislist);
        tvEquivalenciaDet.refresh();
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
        if (cbGrade.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Selecione o campo <Grade>", "", 2);
        } else {
            if (cbDisciplina.getSelectionModel().getSelectedItem() == null) {
                mostraMsg("Selecione o campo <Disciplina>", "", 2);
            } else {
//                if (cbSituDis.getSelectionModel().getSelectedItem() == null) {
//                    mostraMsg("Selecione o campo <Situação>", "", 2);
//                } else {
                // já tem registro inicial. é so atualizar
                EsEquivalenciaJpaController jpaEqui = new EsEquivalenciaJpaController();
                EsEquivalencia equi = jpaEqui.findEsEquivalencia(gbEquivalencia.getCodEquivalencia());
                //   Cadastrodisciplina cadi = jpaCad.findCadastrodisciplina(gbEquivalencia.getCodDisciplina());
                Cadastrodisciplina cadi = jpaCad.getObjetoCodGradeByDescricao(codGrade, cbDisciplina.getSelectionModel().getSelectedItem());
                //     Cadaludissit sit = jpaSit.getObjeto(cbSituDis.getSelectionModel().getSelectedItem());

                Cadastroalunodisciplina pup = new Cadastroalunodisciplina();
                CadastroalunodisciplinaJpaController jpaPup = new CadastroalunodisciplinaJpaController();

                // GERA/ALTERA REGISTRO EM pup
                if (equi.getCodPup() == null) {
                    pup.setNomeCadDisciplina(cadi);
                    pup.setDadoCadastroProgramaId(gbEquivalencia.getCodPrograma());
                    //  pup.setCadastroAlunoDisciplinaSituacaoId(sit.getCadsId());
                    Short dispExt = 14;
                    pup.setCadastroAlunoDisciplinaSituacaoId(dispExt); // Dispensa (externa)
                    pup.setCodGrade(codGrade);
                    pup.setDataInc(new Date());
                    pup.setCodUserInc(gbUser.getCoduser());
                    pup.setCodEquivalencia(gbEquivalencia.getCodEquivalencia());

                    try {
                        jpaPup.create(pup);
                    } catch (Exception e) {
                        mostraMsg("Erro 014equi2 ao gravar Dispensna (externa).", "> " + e, 2);
                    }

                } else {
                    // pup já existe. Atualizar
                    pup = jpaPup.findCadastroalunodisciplina(equi.getCodPup());
                    pup.setCodGrade(codGrade);
                    pup.setNomeCadDisciplina(cadi);
                    // pup.setCadastroAlunoDisciplinaSituacaoId(sit.getCadsId());
                    pup.setDataAlt(new Date());
                    pup.setCodUserAlt(gbUser.getCoduser());

                    try {
                        jpaPup.edit(pup);
                    } catch (Exception e) {
                        mostraMsg("Erro 014equi3 ao gravar Dispensna (externa).", "> " + e, 2);
                    }

                }

                // preenche campos que o usuário alterou (grade, disciplina, situação, observacao, docsdigi)
                equi.setCodGrade(codGrade);
                equi.setCodDisciplina(cadi.getCadastroDisciplinaId());
                //    equi.setCodSituacao(sit.getCadsId().intValue());
                equi.setDocsDigi(chkDocsDigi.isSelected());
                equi.setObservacoes(taObs.getText());
                if (equi.getCodPup() == null) {
                    equi.setCodPup(pup.getCadastroAlunoDisciplinaId());
                }

                try {
                    jpaEqui.edit(equi);
                } catch (Exception e) {
                    mostraMsg("Erro 014equi ao gravar Dispensna (externa).", "> " + e, 2);
                }
                Stage stage = (Stage) buConfirma.getScene().getWindow();
                stage.close();
                //  }
            }
        }
    }

    private void preencheEquiMaster(EsEquivalencia equi) {

    }

    @FXML
    private void clicouCancela(ActionEvent event) {
    }

    @FXML
    private void clicouRefreshDen(ActionEvent event) {
    }

    @FXML
    private void mudouGrade(ActionEvent event) {
        if (cbGrade.getSelectionModel().getSelectedItem() != null) {
            preencheDis();
        }
    }

    public void preencheDis() {
        // obtem código da grade selecionada
        codGrade = jpaGra.getCodGradePorConcat(cbGrade.getSelectionModel().getSelectedItem());
        dadosDis = FXCollections.observableArrayList(jpaCad.getNomeDasDisci(codGrade));
        cbDisciplina.setItems(dadosDis);
    }

    public ObservableList<String> getGradeMS() {
        dadosGra = FXCollections.observableArrayList(jpaGra.getGradeMS());

        if (dadosGra == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosGra;
        }
    }

//    public ObservableList<String> getSitu() {
//        dadosSit = FXCollections.observableArrayList(jpaSit.getEquivs());
//
//        if (dadosSit == null) {
//            return FXCollections.observableArrayList();
//        } else {
//            return dadosSit;
//        }
//    }
    @FXML
    private void clicouInsereEqui(ActionEvent event) {
        gbCrudEqui = 1; // INSERE DISCIPLINA
        chamaCrudEqui();
    }

    @FXML
    private void clicouEditaEqui(ActionEvent event) {
        if (tvEquivalenciaDet.getSelectionModel().getSelectedItem() != null) {
            gbRegEqDet = new EquivalenciaGrid();
            gbRegEqDet.setCodEquiDet(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getCodEquiDet());
            gbRegEqDet.setCodEqui(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getCodEqui());
            gbRegEqDet.setNomeInstitu(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getNomeInstitu());
            gbRegEqDet.setNomeDisciplina(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getNomeDisciplina());
            gbRegEqDet.setCarga(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getCarga());
            gbRegEqDet.setCredito(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getCredito());
            gbRegEqDet.setAno(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getAno());
            gbRegEqDet.setNota(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getNota());
            gbCrudEqui = 2; // EDITA DISCIPLINA
            chamaCrudEqui();
        }
    }

    @FXML
    private void clicouApagaEqui(ActionEvent event) {
        if (tvEquivalenciaDet.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Apagar a disciplina <" + tvEquivalenciaDet.getSelectionModel().getSelectedItem().getNomeDisciplina() + "> desta Dispensna (externa)");
            alert.setContentText("Deseja realmente apagar este registro?\nOs dados serão perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaEqDet.destroy(tvEquivalenciaDet.getSelectionModel().getSelectedItem().getCodEquiDet());
                    insGridEqui();
                    mostraMsg("Dados apagados com sucesso", "", 3);
                    Stage stage = (Stage) buConfirma.getScene().getWindow();
                    stage.close();
                } catch (Exception e) {
                    mostraMsg("Erro ao apagar registro", "" + e, 2);
                }
            }
        }
    }

    private void chamaCrudEqui() {
        if (gbRegEqDet == null) {
            gbRegEqDet = new EquivalenciaGrid();
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CrudEquiDet.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Dados da Dispensna (externa)");
            stage.setScene(scene);

            stage.showAndWait();
            insGridEqui();

        } catch (IOException ex) {
            Logger.getLogger(EquivalenciaController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 004e ao carregar chamaCrudEqui", "" + ex, 0);
        }
    }

    @FXML
    private void clicouApagaEquiMaster(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Deseja realmente apagar esta Dispensna (externa) de Disciplina?");
        alert.setContentText("Esses dados serão perdidos.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            List<EsEquivalenciadet> dadosFilhosEq = jpaEqDet.pegaFilhoeEqui(gbEquivalencia.getCodEquivalencia());
            EntityManager em = null;

            EsEquivalencia equi = jpaEqPai.findEsEquivalencia(gbEquivalencia.getCodEquivalencia());
            if (equi.getCodPup() != null) {
                // APAGA PUP
                try {
                    jpaPup.destroy(equi.getCodPup());
                } catch (Exception e) {
                    mostraMsg("Erro 098eqp - erro ao apagar dados Pup - ", "" + e, 2);
                }
            }

            // APAGA DET
            try {
                em = getEntityManager();
                em.getTransaction().begin();

                for (int i = 0; i < dadosFilhosEq.size(); i++) {
                    EsEquivalenciadet fil = dadosFilhosEq.get(i);

                    EsEquivalenciadet current = null;
                    if (!em.contains(fil)) {
                        current = em.merge(fil);
                    }
                    em.remove(current);

                }
                em.getTransaction().commit();

            } finally {
                if (em != null) {
                    em.close();
                }
            }

            // APAGA EQUIVALENCIA MASTER
            try {

                jpaEqPai.destroy(gbEquivalencia.getCodEquivalencia());

            } catch (Exception e) {
                mostraMsg("Erro 098eq - erro ao apagar dadosFilhosEq - ", "" + e, 2);
            }
            mostraMsg("Dados apagados com sucesso,", "", 0);
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
}
