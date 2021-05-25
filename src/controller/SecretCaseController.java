/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Cadastroalunodisciplina;
import entities.Cadastrodisciplina;
import entities.Chamadas;
import entities.ChamadasCalc;
import entities.DisOfe;
//import entities.EsGrade;
import entities.EsGrade3;
import entities.EsMatrisem;
import entities.GeraEsMatriSem;
import funcoes.DBConnector;
import static funcoes.MyFunc.getEntityManager;
import static funcoes.MyFunc.mostraMsg;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.CadastrodisciplinaJpaController;
import jpa_controler.EsGrade3JpaController;
import jpa_controler.EsMatrisemJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class SecretCaseController implements Initializable {

    @FXML
    private Button buEsconde;
    @FXML
    private Button buCria;
    @FXML
    private Button buExiste;
    @FXML
    private TextField txtArq;
    @FXML
    private Button buAbrePDF;
    @FXML
    private Button buGeraEs_matrisem;
    @FXML
    private TableView<GeraEsMatriSem> tvDisProAlu;
    @FXML
    private TableColumn<GeraEsMatriSem, Integer> tcDcp;
    @FXML
    private TableColumn<GeraEsMatriSem, Integer> tcDcg;
    @FXML
    private TableColumn<GeraEsMatriSem, Integer> tcAno;
    @FXML
    private TableColumn<GeraEsMatriSem, Integer> tcSem;
    @FXML
    private TableColumn<GeraEsMatriSem, Integer> tcCre;

    public Connection con = DBConnector.getConnection();
    private ObservableList<GeraEsMatriSem> dadosGr;
    private ObservableList<DisOfe> dadosDF;
    private ObservableList<DisOfe> dadosDF2;
    private ObservableList<Chamadas> dadosChamadas;
    private ObservableList<ChamadasCalc> dadosChamadasCalc;
    private EsMatrisemJpaController jpaCon;

    EsMatrisem regMatriSem;
    @FXML
    private TableColumn<?, ?> tcDis;
    @FXML
    private TableColumn<?, ?> tccodpup;
    @FXML
    private TableColumn<?, ?> tcCdoGrade;
    @FXML
    private TableColumn<?, ?> tcCadDis;
    @FXML
    private TableColumn<?, ?> tcMedia;
    @FXML
    private TableColumn<?, ?> tcFalta;
    @FXML
    private TableColumn<?, ?> tcAnoLetivo;
    @FXML
    private TableColumn<?, ?> tcSemestre;
    @FXML
    private TableColumn<?, ?> tcCodALuno;

    EsGrade3JpaController jpaGra3;
    CadastroalunodisciplinaJpaController jpaPup;
    int contador = 0;
    Cadastroalunodisciplina pup;

    @FXML
    private TableView<Chamadas> tvChamadas;
    @FXML
    private TableColumn<Chamadas, Integer> tcCodDisS;
    @FXML
    private TableColumn<Chamadas, Integer> tcCodAluS;
    @FXML
    private TableColumn<Chamadas, String> tcChamada;
    @FXML
    private TableColumn<Chamadas, Integer> tctotCham;

    private CadastrodisciplinaJpaController jpaCad;

    @FXML
    private TableView<ChamadasCalc> tvChamadasCalc;
    @FXML
    private TableColumn<ChamadasCalc, Integer> tcCodDisC;
    @FXML
    private TableColumn<ChamadasCalc, Integer> tcCodAluC;
    @FXML
    private TableColumn<ChamadasCalc, Boolean> tcVerificarC;
    @FXML
    private TableColumn<ChamadasCalc, Integer> tcTotFaltasC;
    @FXML
    private TableColumn<?, ?> tcNumAulas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void clicouEsconde(MouseEvent event) {
        Path p = Paths.get("\\\\\\\\192.168.0.19\\\\EvedSys\\\\sEcretCase");
        //  \\192.168.0.19\EvedSys\sEcretCase
        try {
            DosFileAttributes dos = Files.readAttributes(p, DosFileAttributes.class);
            Files.setAttribute(p, "dos:hidden", true);
            System.out.println("Pasta c:\\temp escondida com sucesso.");

            System.out.println(dos.isHidden());
        } catch (IOException x) {
            System.err.println("DOS file attributes not supported:" + x);
        }
    }

    @FXML
    private void clicouCriar(MouseEvent event) {
    }

    @FXML
    private void criaExiste(MouseEvent event) {
        //   textoteste.txt
        // test "/var/tmp" directory
        String arquivo = "c:/temp15/textoteste.txt";
        String pasta = "c:/temp15";
        File tmpDir = new File(arquivo);

        boolean exists = tmpDir.exists();
        if (exists) {
            System.out.println(arquivo);
        }

        if (tmpDir.isDirectory()) {
            System.out.println(arquivo + " é uma pasta");
        }

        // test to see if a file exists
        File file = new File(arquivo);
        exists = file.exists();
        if (file.exists() && file.isFile()) {
            System.out.println("O arquivo >>> " + arquivo + " <<< existe e é um arquivo");
        }
    }

    @FXML
    private void clicouAbrePDF(MouseEvent event) {
        String pasta = "\\\\\\\\192.168.0.19\\\\EvedSys\\\\sEcretCase\\";
        String arqPDF = "telas ca 4 - ago 19 - dados cadastrais.pdf";
//        HostServices services = SecretCaseController.getInstance().getApplication().getHostServices();
//        services.showDocument(path);
//        hs.showDocument("C:\\DOF.pdf");
        try {
            Desktop.getDesktop().open(new File(pasta + arqPDF));

        } catch (Exception e) {
            System.out.println("Erro " + e);
        }

    }

    @FXML
    private void clicou_buGeraEs_matrisem(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CONFIRMA?");
        alert.setContentText("Confirma a geração do arquivo es_matrisem?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            InicGrig();

        }

    }

    public void InicGrig() {
        dadosGr = FXCollections.observableArrayList();
        String sqlMatriSem
                = "SELECT dcp.DadoCadastroGeralId, pup.DadoCadastroProgramaId, cad.AnoLetivo, cad.SemestreId, SUM(cad.Credito) as Totcre, COUNT(*) as Totdis  "
                + "FROM cadastroalunodisciplina pup  "
                + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
                + "LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId  "
                + "LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id  "
                + "GROUP BY dcp.DadoCadastroGeralId, pup.DadoCadastroProgramaId, cad.AnoLetivo, cad.SemestreId  "
                + "ORDER BY dcp.DadoCadastroGeralId, pup.DadoCadastroProgramaId, cad.AnoLetivo, cad.SemestreId  ";
//                = "SELECT  dcp.DadoCadastroGeralId, pup.DadoCadastroProgramaId, cad.AnoLetivo, cad.SemestreId, SUM(cd.Credito) as Totcre, COUNT(*) as Totdis "
//                + "FROM cadastroalunodisciplina pup "
//                + "           LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId "
//                + "           LEFT JOIN cadastrodisciplina cad ON pup.CadastroDisciplinaId = cad.CadastroDisciplinaId "
//                + "           LEFT JOIN cadaludissit cs ON pup.CadastroAlunoDisciplinaSituacaoId = cs.cads_id "
//                + "GROUP BY dcp.DadoCadastroGeralId, cad.DadoCadastroProgramaId, cad.AnoLetivo, cad.SemestreId "
//                + "ORDER BY dcp.DadoCadastroGeralId, cad.DadoCadastroProgramaId, cad.AnoLetivo, cad.SemestreId ";

        try {
            System.out.println(sqlMatriSem);

            ResultSet rs = con.createStatement().executeQuery(sqlMatriSem);
            while (rs.next()) {
                dadosGr.add(new GeraEsMatriSem(
                        rs.getInt("DadoCadastroProgramaId"),
                        rs.getInt("DadoCadastroGeralId"),
                        rs.getInt("AnoLetivo"),
                        rs.getInt("SemestreId"),
                        rs.getInt("Totcre"),
                        rs.getInt("Totdis")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SecretCaseController.class.getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018d - em InicGrid()", "" + ex, 2);
        }

        tcDcp.setCellValueFactory(new PropertyValueFactory<>("dcp"));
        tcDcg.setCellValueFactory(new PropertyValueFactory<>("dcg"));
        tcAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tcSem.setCellValueFactory(new PropertyValueFactory<>("sem"));
        tcCre.setCellValueFactory(new PropertyValueFactory<>("Totcre"));
        tcDis.setCellValueFactory(new PropertyValueFactory<>("Totdis"));

        tvDisProAlu.setItems(dadosGr);
        tvDisProAlu.refresh();

        String spa = "     ";

        // VARRER TABLEVIEW
        // Lembrando que quando gravava 1 por 1, o procedimento para 10 mil registros demorou mais de 20 minutos
        // Do jeito abaixo, o procedimento não dura nem 15 segundos!
        //jpaCon = new EsMatrisemJpaController();
        EntityManagerFactory emf;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            int ctl = 0;

            for (GeraEsMatriSem ems : tvDisProAlu.getItems()) {
                GeraEsMatriSem reg = ems; // linha atual

                if (ems.getAno() != null) {
                    regMatriSem = new EsMatrisem();
                    regMatriSem.setDadoCadastroGeralId(ems.getDcg());
                    regMatriSem.setDadoCadastroProgramaId(ems.getDcp());
                    regMatriSem.setAnoLetivo(ems.getAno().shortValue());
                    regMatriSem.setSemestreId(ems.getSem().shortValue());
                    regMatriSem.setNumCredsRE(ems.getCre().shortValue());
                    regMatriSem.setNumDisci(ems.getDis().shortValue());

                    em.persist(regMatriSem);
                    ++ctl;
                    //    System.out.println("ctl: " + ++ctl);

                    // jpaCon.create(regMatriSem);
                }
            }
            em.getTransaction().commit();
            mostraMsg("Operação concluída com sucesso", "Foram gerados " + ctl + " registros", 1);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @FXML
    private void clicouGeraGrade(ActionEvent event) {
        dadosDF = FXCollections.observableArrayList();
        dadosDF2 = FXCollections.observableArrayList();
        String sqlDisOfe
                = "SELECT pup.CadastroAlunoDisciplinaId, pup.CadastroDisciplinaId, pup.CodGrade, dcp.AnoLetivo, dcp.SemestreId "
                + "FROM cadastroalunodisciplina pup "
                + "LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId "
                + "GROUP BY dcp.AnoLetivo, dcp.SemestreId "
                + "ORDER BY dcp.AnoLetivo, dcp.SemestreId ";

        String sqlFase2
                = "SELECT pup.CadastroAlunoDisciplinaId, pup.CodGrade, dcp.AnoLetivo, dcp.SemestreId "
                + " FROM cadastroalunodisciplina pup "
                + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId ";

        try {
            System.out.println(sqlDisOfe);

            ResultSet rs = con.createStatement().executeQuery(sqlDisOfe);
            while (rs.next()) {
                dadosDF.add(new DisOfe(
                        rs.getInt("pup.CadastroAlunoDisciplinaId"),
                        rs.getInt("CadastroDisciplinaId"),
                        rs.getInt("pup.CodGrade"),
                        rs.getInt("AnoLetivo"),
                        rs.getInt("SemestreId")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SecretCaseController.class.getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018e - em clicouGeraGrade()", "" + ex, 2);
        }

        try {
            System.out.println(sqlFase2);

            ResultSet rs = con.createStatement().executeQuery(sqlFase2);
            while (rs.next()) {
                dadosDF2.add(new DisOfe(
                        rs.getInt("pup.CadastroAlunoDisciplinaId"),
                        rs.getInt("pup.CodGrade"),
                        rs.getInt("dcp.AnoLetivo"),
                        rs.getInt("dcp.SemestreId")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SecretCaseController.class.getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018e - em clicouGeraGrade()", "" + ex, 2);
        }

        jpaGra3 = new EsGrade3JpaController();

        // FAASE 1 = GRAVA GRADES INEXISTENTES EM ES_GRADE
        dadosDF.forEach(i -> {
            DisOfe dofe = i;
            int ano = i.getAnoletivo();
            int sem = i.getSemestre();

            // verifica se grade existe. senão existir, cria
            List<EsGrade3> lis = jpaGra3.getGradeAnoSem(i.getAnoletivo(), i.getSemestre());
            int tam = lis.size();

            if (tam == 0) {
                // cadastra essa grade
                EsGrade3 gra = new EsGrade3();
                gra.setAnoLetivo((short) i.getAnoletivo());
                gra.setSemestre((short) i.getSemestre());
                jpaGra3.create(gra);
            }

        });
        mostraMsg("Geração da grade: fase 1 concluída", "", 2);

// NÃO ESTÁ FUNCIONANDO
//        jpaPup = new CadastroalunodisciplinaJpaController();
//        // FASE 2 - preencher cadastroalunodisciplina.CodGrade
//
//        EntityManagerFactory emf;
//        EntityManager em = null;
//        int erros = 0;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//
//            for (int i = 0; i < dadosDF2.size(); i++) {
//                DisOfe x = dadosDF2.get(i);
//
//                EsGrade gra = jpaGra.getCodGradeExac(x.getAnoletivo(), x.getSemestre());
//
//                pup = jpaPup.findCadastroalunodisciplina(x.getCadastroAlunoDisciplinaId());
//                if (pup == null) {
//                    mostraMsg("CodGrade não localizado!",
//                            "\nAno: " + x.getAnoletivo()
//                            + "CodGrade não localizado!"
//                            + "\nSemestre: " + x.getSemestre()
//                            + "\nCadastroAlunoDisciplinaId: " + x.getCadastroAlunoDisciplinaId(), 2);
//                    ++erros;
//                } else {
//                    pup.setCodGrade(gra.getCodGrade());
//                    em.persist(pup);
//                }
//                //  jpaPup.edit();
//                System.out.println(++contador);
//            }
//            em.getTransaction().commit();
//            //    mostraMsg("Operação concluída com sucesso", "Foram gerados " + ctl + " registros", 1);
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        mostraMsg("Preencher cadastroalunodisciplina.CodGrade - fase 2 concluída", "Erros: " + erros, 1);
    }

    @FXML
    private void clicouVarreChamada(ActionEvent event) {

        // VARRE CHAMADA
        dadosChamadas = FXCollections.observableArrayList();
        dadosChamadasCalc = FXCollections.observableArrayList();

        String sqlChama
                = "SELECT cab.CadastroDisciplinaId, cha.codaluno, cha.chamada, count(*) as totcham "
                + "FROM lista_chamada cha "
                + "LEFT JOIN lista_aula aul ON cha.CodLista_Aula = aul.CodLista_Aula "
                + "LEFT JOIN lista_cabec cab ON aul.CodLista = cab.CodLista "
                + "WHERE cab.CadastroDisciplinaId IS NOT NULL "
                + "AND (CHAMADA IN ('A','F','?')) "
                + "GROUP BY cab.CadastroDisciplinaId, cha.codaluno, cha.chamada "
                + "ORDER BY cab.CadastroDisciplinaId, cha.codaluno ";

        try {
            System.out.println(sqlChama);

            ResultSet rs = con.createStatement().executeQuery(sqlChama);
            while (rs.next()) {
                dadosChamadas.add(new Chamadas(
                        rs.getInt("cab.CadastroDisciplinaId"),
                        rs.getInt("cha.codaluno"),
                        rs.getString("cha.chamada"),
                        rs.getInt("totcham")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SecretCaseController.class.getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018s - em clicouAtualizaFaltas()", "" + ex, 2);
        }

        tcCodDisS.setCellValueFactory(new PropertyValueFactory<>("cadDis"));
        tcCodAluS.setCellValueFactory(new PropertyValueFactory<>("codAluno"));
        tcChamada.setCellValueFactory(new PropertyValueFactory<>("chamada"));
        tctotCham.setCellValueFactory(new PropertyValueFactory<>("totCham"));
        
        tcCodDisC.setCellValueFactory(new PropertyValueFactory<>("cadDis"));
        tcCodAluC.setCellValueFactory(new PropertyValueFactory<>("codAluno"));
        tcVerificarC.setCellValueFactory(new PropertyValueFactory<>("verificar"));
        tcTotFaltasC.setCellValueFactory(new PropertyValueFactory<>("totFaltas"));
        tcNumAulas.setCellValueFactory(new PropertyValueFactory<>("numAulas"));

        // PROCESSA FALTAS
        Integer coddis = dadosChamadas.get(0).getCadDis();
        Integer i = 0;

        jpaCad = new CadastrodisciplinaJpaController();

        while (i < dadosChamadas.size()) {
//            Chamadas chama = new Chamadas();
//            chama.setCadDis(dadosChamadas.get(i).getCadDis());
//            chama.setCodAluno(dadosChamadas.get(i).getCodAluno());
//            chama.setChamada(dadosChamadas.get(i).getChamada());
//            chama.setTotCham(dadosChamadas.get(i).getTotCham());

            // quebra por disciplina
            Cadastrodisciplina cadi = jpaCad.findCadastrodisciplina(coddis);
            while ((i < dadosChamadas.size() && coddis.equals(dadosChamadas.get(i).getCadDis()))) {

                // quebra por aluno dentro da mesma disciplina
                Integer codalu = dadosChamadas.get(i).getCodAluno();

                // zera contadores para este aluno
                Integer numfaltas = 0;
                Boolean verificar = false;
                while ((i < dadosChamadas.size() && (codalu.equals(dadosChamadas.get(i).getCodAluno())) && (coddis.equals(dadosChamadas.get(i).getCadDis())) )) {

                    switch (dadosChamadas.get(i).getChamada()) {
                        case "F":
                            numfaltas += dadosChamadas.get(i).getTotCham();
                            break;
                        case "A":
                            numfaltas += (int) dadosChamadas.get(i).getTotCham() / 4;
                            break;
                        case "?":
                            if (dadosChamadas.get(i).getTotCham() > cadi.getNumAulas() / 4) { // se interrogações for maior que 25%
                                verificar = true;
                            }
                            break;
                    }

                    ++i;
                    if (i.equals(dadosChamadas.size())) {
                        break;
                    }
                } // quebra aluno

                // mudou de aluno. Calcular Verificar e totFaltas. Lançar aluno em ChamadasCalc no aluno AGORA anterior (i-1)
                if (!verificar) {
                    verificar = (numfaltas > cadi.getNumAulas() / 4);
                }

                ChamadasCalc chamaCalc = new ChamadasCalc();
                chamaCalc.setCadDis(dadosChamadas.get(i - 1).getCadDis());
                chamaCalc.setCodAluno(dadosChamadas.get(i - 1).getCodAluno());
                chamaCalc.setVerificar(verificar);
                chamaCalc.setTotFaltas(numfaltas);
                chamaCalc.setNumAulas(cadi.getNumAulas());

                dadosChamadasCalc.add(chamaCalc);

            } // quebra disciplina

            if (i < dadosChamadas.size()) {
                coddis = dadosChamadas.get(i).getCadDis();
            }
        }

        tvChamadas.setItems(dadosChamadas);
        tvChamadas.refresh();

        tvChamadasCalc.setItems(dadosChamadasCalc);
        tvChamadasCalc.refresh();

    }

}
