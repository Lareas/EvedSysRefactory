package controller;

import static main.Login.CRITERIOLISTALU;
import static main.Login.gbDisci;
import static main.Login.GBFORMATHORA;
import static main.Login.GBFORMATDATA;
import static main.Login.GBFORMATDATASQL;
import static main.Login.gbListaInfo;
import static main.Login.gbUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import entities.AlunosMatric;
import entities.ListaComp;
import entities.ListaDias;
import entities.ListaSQL;
import funcoes.DBConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import jpa_controler.ListaCabecJpaController;
import entities.AlunosLC;
import entities.InsFaltas;
import entities.ListaAula;
import entities.ListaChamada;
import entities.TotsChamada;
import static funcoes.MyFunc.getEntityManager;
import static funcoes.MyFunc.mostraMsg;
import static funcoes.MyFunc.weekDay;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import jpa_controler.ListaAulaJpaController;
import jpa_controler.ListaChamadaJpaController;
import static main.Login.NUMATRASOSIGUALUMAFALTA;

public class ListaPresencaController implements Initializable {

    @FXML
    private Label lblDis;
    @FXML
    private Label lblPro1;
    @FXML
    private Label lblPro2;
    @FXML
    private Label lblAno;
    @FXML
    private Label lblSem;
    @FXML
    private Label lblCre;
    @FXML
    private Label lblCar;
    @FXML
    private Label lblTur;
    @FXML
    private Label lblHor;
    @FXML
    private JFXCheckBox chkDom;
    @FXML
    private JFXCheckBox chkSeg;
    @FXML
    private JFXCheckBox chkTer;
    @FXML
    private JFXCheckBox chkQua;
    @FXML
    private JFXCheckBox chkQui;
    @FXML
    private JFXCheckBox chkSex;
    @FXML
    private JFXCheckBox chkSab;
    @FXML
    private TableView<ListaDias> tvAulas;
    @FXML
    private TableColumn<ListaDias, Integer> tcCodLista_Aula;
    @FXML
    private TableColumn<ListaDias, Integer> tcNumAula;
    @FXML
    private TableColumn<ListaDias, Date> tcData;
    @FXML
    private Pane top_pane1;
    @FXML
    private JFXDatePicker dpDataAbre;
    @FXML
    private JFXButton buConfirma1;
    @FXML
    private Label lblAula;
    @FXML
    private TableView<ListaComp> tvTeste;
    @FXML
    private TextField lblListaPre;
    @FXML
    private Label lblCadastrpDisciplinaId;
    @FXML
    private Label lblDataIni;
    @FXML
    private Label lblDataFim;
    @FXML
    private JFXButton buInsFal;
    @FXML
    private JFXButton buInsAtra;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private JFXButton buInsGra;
    @FXML
    private JFXButton buInsJus;
    @FXML
    private Label lblNumAula;
    @FXML
    private Label lblDiaSemana;
    @FXML
    private Label lblDiaMes;
    @FXML
    private Label lblTopoCal;
    @FXML
    private TableView<InsFaltas> tvChamada;
    @FXML
    private MenuItem cmRemoverDia;
    @FXML
    private JFXButton buSair;
    @FXML
    private AnchorPane apListaPre;

    private ListaCabecJpaController jpaCab;
    private ListaAulaJpaController jpaAul;
    public Connection con = DBConnector.getConnection();

    public ObservableList<ListaSQL> dadosAlu = FXCollections.observableArrayList();
    public ObservableList<ListaComp> dadosLComp = FXCollections.observableArrayList();
    public ObservableList<ListaDias> dadosDias = FXCollections.observableArrayList();
    public ObservableList<AlunosMatric> dadosAM = FXCollections.observableArrayList();
    public ObservableList<TotsChamada> dadosChamada = FXCollections.observableArrayList();

    private int numAulaAtual = 0;
    private int qualTipo = 0; // qualo tipo da chamada, falta, atraso, gravação, justif
    public static final Boolean E_DMIN = (gbListaInfo.getNomeDis().getNomeTipoDis().getTipodisc().equals("DMin"));
    final static Short MAXDIASAULAS = 17;

    ListaChamadaJpaController jpaCha;

    Stage stag = new Stage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jpaCha = new ListaChamadaJpaController();

        // preparando para ver se a janela já está aberta
        int codDiscAtual = gbListaInfo.getNomeDis().getCadastroDisciplinaId();
        int indAtualStage = -1;
        for (int idx = 0; idx < gbDisci.length; idx++) {
            if (codDiscAtual == gbDisci[idx]) {
                indAtualStage = idx;
                break;
            }
        }

        if (indAtualStage == -1) {
            mostraMsg("Problema", "NUnca deveria ser ZERO!", 3);
        }

//        try {
//            stag = (Stage) buConfirma.getScene().getWindow();
//            stag.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent e) {
//                    mostraMsg("Vai sair!", "", 3);
//                    System.exit(0);
//                }
//            });
//        } catch (Exception e) {
//            mostraMsg("Erro exceção: " + e, "", 3);
//        }
        // Stage stage = aListasPre[indAtualStage];
        //        aListasPre[0].setOnCloseRequest(new EventHandler<WindowEvent>() {
        //            @Override
        //            public void handle(WindowEvent e) {
        //                mostraMsg("Vai sair!", "", 3);
        //                System.exit(0);
        //            }
        //        });
        //
        //        stag = (Stage) buConfirma.getScene().getWindow();
        cmRemoverDia.setVisible(gbUser.getCodperfil() == 1 || gbUser.getCodperfil() == 2);

        desLigaEdicao();

        lblCadastrpDisciplinaId.setText("" + gbListaInfo.getNomeDis().getCadastroDisciplinaId());
        tcNumAula.setCellValueFactory(new PropertyValueFactory<>("numAula"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataAula"));

        tcData.setCellFactory(column -> {
            TableCell<ListaDias, Date> cell = new TableCell<ListaDias, Date>() {

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

        tvTeste.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
                TableHeaderRow header = (TableHeaderRow) tvTeste.lookup("TableHeaderRow");
                header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        header.setReordering(false);
                    }
                });
            }
        });

        tvAulas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                numAulaAtual = tvAulas.getSelectionModel().getSelectedItem().getNumAula();
                qualTipo = 5;
                inicCalend();
            }
        });

        // PREENCHE CABEÇALHO DA LISTA
        inicListaInfo();

        // MONTA LISTA COMPLETA
        montaLc();
        qualTipo = 0;
        inicCalend();

    }

    private void montaLc() {
        // PREENCHE LISTA COMPLETA
        dadosAM = getDadosAM();

        String retCAA = completaAlunosAtrasados(); // retorno de completaAlunosAtrasados
        if (retCAA == "A0") {
            mostraMsg("Não há alunos matriculados nesta disciplina!", "Não é possível gerar a Lista de Presença.", 4);
        } else {
            preencheListaCompleta(); // processou dadosDias
            if (dadosDias.size() < gbListaInfo.getNomeDis().getNumAulas()) {
                Date proxDataProv = calcProxDataProv();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(proxDataProv);
                if (proxDataProv != null) {
                    dpDataAbre.setValue(calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }
                qualTipo = 0;
                inicCalend(); // FORÇA O USUÁRIO SELECIONAR UMA DATA
            }
        }

        //  carregaAulas();
        //   tvTeste.getItems().clear();
        //   tvTeste.setEditable(true);
    }

    private String completaAlunosAtrasados() {
        // valores de retorno
        // "A0" = ALUNOS ZEDO
        // "D0" = DATA ZERO
        // "TC" = TE=UDO CERTO. TEM ALUNOS E DATAS
        // ---------------------------------------

        ObservableList<AlunosLC> dadosLC = FXCollections.observableArrayList();
        ObservableList<ListaAula> dadosDL = FXCollections.observableArrayList();
        ObservableList<ListaComp> dadosSN = FXCollections.observableArrayList();

        String messa = "";
        int ctNovo = 0;

        if (dadosAM.size() == 0) {
            return "A0"; // NÃO HÁ ALUNOS
        } else {

            dadosLC = getDadosLC();
            dadosDL = getDadosDL();

            // SE NÃO TIVER AULAS, PREENCHE APENAS NOMES E TIPOS DE ALUNOS
            if (dadosDL.size() == 0) {
                return "D0"; // NÃO HÁ DATAS ABERTAS
            }

            EntityManagerFactory emf;
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();

                // VERIFICA SE HÃ ALUNO NOVO QUE NÃO CONSTA NA LISTA
                for (int i = 0; i < dadosAM.size(); i++) {
                    AlunosMatric am = dadosAM.get(i);

                    Boolean temAluno = false;
                    for (int j = 0; j < dadosLC.size(); j++) {
                        AlunosLC lc = dadosLC.get(j);

                        if (am.getCodAM() == lc.getCodAluLC()) {
                            temAluno = true;
                            break;
                        }
                    }

                    // SE ENCONTROU ALUNO(S), INSERE-OS NA LISTA (CHAMADA, COLOCANDO "F"?)
                    if (!temAluno) {
                        ++ctNovo;
                        messa = messa + "\n" + am.getNomeAM();
                        insereNovoAluno(dadosDL, am.getCodAM());
                    }
                }

                em.getTransaction().commit();
                //   mostraMsg("Operação concluída com sucesso", "", 1);
            } finally {
                if (em != null) {
                    em.close();
                }
            }

//            if (ctNovo > 0) {
//
//                mostraMsg("Há " + ctNovo + " aluno(s) novo(s) acrescentado(s) na lista:", messa, ctNovo);
//            }
            return "TC"; // TUDO CERTO - TEM ALUNOS E DATAS
        }
    }

    private void preencheListaCompleta() {

        // SQLs
        // LISTA COMPLETA
        String sqlListaComp
                = " SELECT dcg.nome, dcg.dadoCadastroGeralId, pup.TipoRegOuv, cab.codLista, aul.numaula, aul.dataaula, cha.chamada "
                + " FROM cadastroalunodisciplina pup  "
                + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
                + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId "
                + " LEFT JOIN lista_cabec cab ON pup.CadastroDisciplinaId = cab.CadastroDisciplinaId "
                + " LEFT JOIN lista_aula aul ON cab.CodLista = aul.CodLista "
                + " LEFT JOIN lista_chamada cha ON aul.CodLista_Aula = cha.CodLista_Aula "
                + " WHERE pup.CadastroDisciplinaId = " + gbListaInfo.getNomeDis().getCadastroDisciplinaId()
                + " AND dcg.dadoCadastroGeralId = cha.CodAluno "
                + CRITERIOLISTALU + ", aul.numaula";

        // PREENCHE CABEÇALHO DA LISTA
        String sqlDias
                = " SELECT CodLista_Aula, NumAula, DataAula "
                + " FROM lista_aula "
                + " WHERE CodLista = " + gbListaInfo.getCodLista()
                + " ORDER BY NumAula; ";

        DateFormat diames = new SimpleDateFormat("dd/MM");

        // numau terá o número máximo de aulas dessa disciplina
        Short numau = 0;
        if (gbListaInfo.getNomeDis().getNomeTipoDis().getTipodisc().equals("DMin")) {
            numau = 8;
        } else {
            numau = gbListaInfo.getNomeDis().getNumAulas();
            if (numau == null || numau == 0) {
                numau = MAXDIASAULAS;
            }
        }

        // AJUSTA NÚMERO DE DIAS +2 (NOME E TIPO DO ALUNO) - pois será usado para definir número de colunas
        numau++;
        numau++;

        // VETOR DE TÍTULOS (DIAS)
        String[] aTit;

        aTit = new String[numau - 1];

        // VETOR DE COLUNAS
        TableColumn[] aCols;
        aCols = new TableColumn[MAXDIASAULAS+2];

        // COLUNAS NOME E TIPO
        TableColumn tcAlu = new TableColumn("Nome do Aluno");
        tcAlu.setMinWidth(300);
        tcAlu.setCellValueFactory(new PropertyValueFactory<ListaComp, String>("nomeAluno"));

        TableColumn tcTip = new TableColumn("Tipo");
        tcTip.setMinWidth(50);
        tcTip.setCellValueFactory(new PropertyValueFactory<ListaComp, String>("tipoAluno"));
        tcTip.setStyle("-fx-alignment: CENTER;");

        // PREPARA aTit - array do cabeçalho
        for (int i = 0; i < aTit.length; i++) {
            aTit[i] = String.valueOf(i + 1);
        }

        // PROCESSA LISTA COMPLETA''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        try {
            System.out.println(")))))))))))))))  sqlDias ((((((((((");
            System.out.println(sqlDias);
            System.out.println(")))))))))))))))))))))))))))))))))))\n");
            ResultSet rs = con.createStatement().executeQuery(sqlDias);
            int ctl = 0;

            dadosDias.clear();
            // CARREGA CABEÇALHO DE DIAS E DATAS, SE HOUVER
            while (rs.next()) {
                dadosDias.add(new ListaDias(rs.getInt("CodLista_Aula"), rs.getInt("NumAula"), rs.getDate("DataAula")));

                String dm = diames.format(rs.getDate("DataAula"));
                if (ctl < 9) {
                    aTit[ctl] = "    " + aTit[ctl] + "\n" + dm;
                } else {
                    aTit[ctl] = "  " + aTit[ctl] + "\n" + dm;
                }
                ++ctl;
            }

            for (int i = 2; i < numau; i++) {
                aCols[i] = new TableColumn(aTit[i - 2]);
                aCols[i].setMinWidth(40);
                aCols[i].setMaxWidth(40);
                aCols[i].setStyle("-fx-alignment: CENTER;");
                aCols[i].setSortable(false);
            }

            for (int i = numau; i < aCols.length; i++) {
                aCols[i] = new TableColumn();
                aCols[i].setVisible(false);
            }

            aCols[2].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia1"));
            aCols[3].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia2"));
            aCols[4].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia3"));
            aCols[5].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia4"));
            aCols[6].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia5"));
            aCols[7].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia6"));
            aCols[8].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia7"));
            aCols[9].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia8"));
            aCols[10].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia9"));
            aCols[11].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia10"));
            aCols[12].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia11"));
            aCols[13].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia12"));
            aCols[14].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia13"));
            aCols[15].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia14"));
            aCols[16].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia15"));
            aCols[17].setCellValueFactory(new PropertyValueFactory<ListaComp, String>("dia16"));

            // ADICIONA NOME E TIPO DO ALUNO NAS 2 PRIMEIRAS POSIÇÕES
            aCols[0] = tcAlu;
            aCols[0].setSortable(false);
            aCols[1] = tcTip;
            aCols[1].setSortable(false);

        } catch (SQLException ex) {
            Logger.getLogger(ListaPresencaController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018c - em initRegDis()", "" + ex, 2);
        }

        // AQUI O CABEÇALHO FOI CARREGADO COM SUCESSO
        tvAulas.setItems(null);
        tvAulas.setItems(dadosDias);
        tvAulas.refresh();

        tvTeste.getItems().clear();
        tvTeste.getColumns().clear();
        tvTeste.getColumns().addAll(aCols);

        int aluRE = 0;
        int aluO = 0;
        String spa = "    |    ";
        if (dadosDias.size() == 0) {
            // ESTA LISTA EXISTE, MAS NENHUMA DATA FOI ABERTA.
            // MOSTRAR APENAS NOME E TIPO NA LISTA
            for (int i = 0; i < dadosAM.size(); i++) {
                dadosLComp.add(new ListaComp(dadosAM.get(i).getNomeAM(), dadosAM.get(i).getTipoAluno()));
                if (dadosAM.get(i).getTipoAluno() != null && dadosAM.get(i).getTipoAluno().equals("O")) {
                    ++aluO;
                } else {
                    ++aluRE;
                }
            }

            lblListaPre.setText("Regulares/Especiais: " + aluRE + spa + "Ouvintes: " + aluO + spa + "Total: " + (aluRE + aluO));
        } else {

            try {
                System.out.println("++++  sqlListaComp +++++++++++++++++++++");
                System.out.println(sqlListaComp);
                System.out.println("++++++++++++++++++++++++++++++++++++++++");

                ResultSet rs = con.createStatement().executeQuery(sqlListaComp);

                dadosAlu.clear();
                while (rs.next()) {

                    dadosAlu.add(new ListaSQL(
                            rs.getString("dcg.nome"),
                            rs.getString("pup.TipoRegOuv"),
                            rs.getInt("dcg.dadoCadastroGeralId"),
                            rs.getInt("cab.codLista"),
                            rs.getInt("aul.numaula"),
                            rs.getDate("aul.dataaula"),
                            rs.getString("cha.chamada")));
                }

            } catch (SQLException ex) {
                Logger.getLogger(ListaPresencaController.class
                        .getName()).log(Level.SEVERE, null, ex);
                mostraMsg("Erro 018g - em preencheListaCompleta()", "" + ex, 2);
            }

            // PROCESSA dadosAlu
            int i = 0;

            while (i < dadosAlu.size()) {

                ListaComp lc = new ListaComp();
                lc.setNomeAluno(dadosAlu.get(i).getNomeAluno());
                lc.setTipoAluno(dadosAlu.get(i).getTipoAluno());

                if (dadosAlu.get(i).getTipoAluno() != null && lc.getTipoAluno().equals("O")) {
                    ++aluO;
                } else {
                    ++aluRE;
                }

                int x = 0;
                while (i < dadosAlu.size() && lc.getNomeAluno().equals(dadosAlu.get(i).getNomeAluno())) {

                    ++x;
                    switch (x) {
                        case 1:
                            lc.setDia1(dadosAlu.get(i).getChamada());
                            break;
                        case 2:
                            lc.setDia2(dadosAlu.get(i).getChamada());
                            break;
                        case 3:
                            lc.setDia3(dadosAlu.get(i).getChamada());
                            break;
                        case 4:
                            lc.setDia4(dadosAlu.get(i).getChamada());
                            break;
                        case 5:
                            lc.setDia5(dadosAlu.get(i).getChamada());
                            break;
                        case 6:
                            lc.setDia6(dadosAlu.get(i).getChamada());
                            break;
                        case 7:
                            lc.setDia7(dadosAlu.get(i).getChamada());
                            break;
                        case 8:
                            lc.setDia8(dadosAlu.get(i).getChamada());
                            break;
                        case 9:
                            lc.setDia9(dadosAlu.get(i).getChamada());
                            break;
                        case 10:
                            lc.setDia10(dadosAlu.get(i).getChamada());
                            break;
                        case 11:
                            lc.setDia11(dadosAlu.get(i).getChamada());
                            break;
                        case 12:
                            lc.setDia12(dadosAlu.get(i).getChamada());
                            break;
                        case 13:
                            lc.setDia13(dadosAlu.get(i).getChamada());
                            break;
                        case 14:
                            lc.setDia14(dadosAlu.get(i).getChamada());
                            break;
                        case 15:
                            lc.setDia15(dadosAlu.get(i).getChamada());
                            break;
                        case 16:
                            lc.setDia16(dadosAlu.get(i).getChamada());
                            break;
                        default:
                            break;
                    }
                    ++i;
                }
                dadosLComp.add(lc);

            }
            lblListaPre.setText("Regulares/Especiais: " + aluRE + spa + "Ouvintes: " + aluO + spa + "Total: " + (aluRE + aluO));
        }

        // lista completa
        //   carregaAulas();
        tvTeste.setItems(dadosLComp);
        tvTeste.refresh();
    }

    private void insereNovoAluno(ObservableList<ListaAula> dadosDL, Integer codNovoAluno) {

        for (int i = 0; i < dadosDL.size(); i++) {
            ListaAula dlp = dadosDL.get(i);

            ListaChamada reg_cham = new ListaChamada();
            reg_cham.setCodListaAula(dlp.getCodLau());
            reg_cham.setCodAluno(codNovoAluno);
            reg_cham.setChamada("?");
            jpaCha.create(reg_cham);
        }
    }

    private ObservableList<ListaAula> getDadosDL() {
        ObservableList<ListaAula> dadosDL = FXCollections.observableArrayList();
        ListaAulaJpaController jpaLP = new ListaAulaJpaController();

        dadosDL = FXCollections.observableArrayList(jpaLP.getAulasLC(gbListaInfo.getCodLista()));

        if (dadosDL == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosDL;
        }

    }

    private ObservableList<AlunosMatric> getDadosAM() {
        ObservableList<AlunosMatric> dadosAM = FXCollections.observableArrayList();
        dadosAM.clear();
        String sqlAlunosMatri
                = " SELECT dcg.dadoCadastroGeralId, dcg.nome, pup.TipoRegOuv "
                + " FROM cadastroalunodisciplina pup  "
                + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
                + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId "
                + "   WHERE pup.CadastroDisciplinaId = " + gbListaInfo.getNomeDis().getCadastroDisciplinaId()
                + CRITERIOLISTALU;
        try {
            System.out.println("****** getDadosAM sqlAlunosMatri");
            System.out.println(sqlAlunosMatri);
            System.out.println("****** getDadosAM sqlAlunosMatri\n ");
            ResultSet rs = con.createStatement().executeQuery(sqlAlunosMatri);

            while (rs.next()) {
                dadosAM.add(new AlunosMatric(
                        rs.getInt("dcg.dadoCadastroGeralId"),
                        rs.getString("dcg.nome"),
                        rs.getString("pup.TipoRegOuv")));
            }
            return dadosAM;

        } catch (SQLException ex) {
            Logger.getLogger(ListaPresencaController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018m - em getDadosAM()", "" + ex, 2);
            return null;
        }

    }

//    private ObservableList<ListaDias> getDias() {
//        ObservableList<ListaDias> dadosDias = FXCollections.observableArrayList();
//        dadosDias.clear();
//        String sqlDias
//                = " SELECT NumAula, DataAula "
//                + " FROM lista_aula "
//                + " WHERE CodLista = " + gbListaInfo.getCodLista()
//                + " ORDER BY NumAula; ";
//        try {
//            System.out.println(sqlDias);
//            ResultSet rs = con.createStatement().executeQuery(sqlDias);
//
//            while (rs.next()) {
//                dadosDias.add(new ListaDias(rs.getInt("NumAula"), rs.getDate("DataAula")));
//            }
//            return dadosDias;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ListaPresencaController.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            mostraMsg("Erro 018p - em getDadosLC()", "" + ex, 2);
//            return null;
//        }
//
//    }
    private ObservableList<AlunosLC> getDadosLC() {
        ObservableList<AlunosLC> dadosLC = FXCollections.observableArrayList();
        dadosLC.clear();
        String sqlAlunosLC
                = " SELECT dcg.dadoCadastroGeralId "
                + " FROM cadastroalunodisciplina pup   "
                + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
                + " LEFT JOIN dadocadastrogeral dcg ON dcp.DadoCadastroGeralId = dcg.DadoCadastroGeralId  "
                + " LEFT JOIN lista_cabec cab ON pup.CadastroDisciplinaId = cab.CadastroDisciplinaId  "
                + " LEFT JOIN lista_aula aul ON cab.CodLista = aul.CodLista  "
                + " LEFT JOIN lista_chamada cha ON aul.CodLista_Aula = cha.CodLista_Aula "
                + " WHERE pup.CadastroDisciplinaId = " + gbListaInfo.getNomeDis().getCadastroDisciplinaId()
                + " AND dcg.dadoCadastroGeralId = cha.CodAluno "
                + " AND ((pup.CadastroAlunoDisciplinaSituacaoId !=  4)  "
                + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 14)  "
                + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 15)   "
                + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 16) "
                + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 17) "
                + " OR (pup.CadastroAlunoDisciplinaSituacaoId IS NULL)) "
                + " ORDER BY pup.TipoRegOuv DESC, dcg.nome;";
        try {
            System.out.println("****** getDadosLC sqlAlunosLC ");
            System.out.println(sqlAlunosLC);
            System.out.println("****** getDadosLC sqlAlunosLC \n");
            ResultSet rs = con.createStatement().executeQuery(sqlAlunosLC);

            while (rs.next()) {
                dadosLC.add(new AlunosLC(rs.getInt("dcg.dadoCadastroGeralId")));
            }
            return dadosLC;

        } catch (SQLException ex) {
            Logger.getLogger(ListaPresencaController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018p - em getDadosLC()", "" + ex, 2);
            return null;
        }
    }

    @FXML
    private void clicouAbreData(ActionEvent event) {
        // prepara cálculo da próxima data
        Date proxDataProv = null;

        // preparar número de aulas máximas desta disciplina e considerar DMin, que tem 2 aulas por dia
        int xnumAulas = gbListaInfo.getNomeDis().getNumAulas();

        if (E_DMIN) {
            xnumAulas = xnumAulas * 2; // 4 dias, duas aulas por dia
        }

        if (dadosDias.size() == xnumAulas) { // ==gbListaInfo.getNomeDis().getNumAulas()) {
            mostraMsg("Não é possível abrir mais datas!", "Esta Lista de Presença já tem o número de aulas total", 4);
        } else {

            // CALCULA PRÓXIMA PROVÁVEL DATA
            if (dpDataAbre.getValue() == null) {
                proxDataProv = calcProxDataProv();
            } else {
                proxDataProv = Date.from(dpDataAbre.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            }

            // VERIFICA SE O DIA JÁ ESTÁ CADASTRADO
            if (temJaEsteDia(proxDataProv) && !E_DMIN) {
                mostraMsg("O dia " + GBFORMATDATA.format(proxDataProv) + " já está cadastrado!", "não é possível abrir uma aula com esta data.", 2);
            } else {

                if (dadosDias.size() == xnumAulas) {
                    mostraMsg("Não é possível abrir mais datas!", "Esta Lista de Presença já tem o número de aulas total", 4);
                } else {

                    // VERIFICA SE DATA É MENOR DO QUE A ÚLTIMA AULA
                    if (dadosDias.size() > 0) {
                        Date ultDiaAberto = dadosDias.get(dadosDias.size() - 1).getDataAula(); // pega a data da última aula
                        if (proxDataProv.compareTo(ultDiaAberto) < 0) {
                            mostraMsg("Não é possível abrir aula com esta data!", "A data " + GBFORMATDATA.format(proxDataProv) + " é anterior à da última aula aberta (" + GBFORMATDATA.format(ultDiaAberto) + ").", 3);
                        } else {

                            // VERIFICA SE DATA JÁ EXISTE
                            int jaTem = 0;
                            for (int i = 0; i < dadosDias.size(); i++) {
                                if (proxDataProv.equals(dadosDias.get(i).getDataAula())) {
                                    jaTem++;
                                }
                            }

                            // SE É DMIN, PODE TER ATÉ 2 DIAS
                            if (E_DMIN) {
                                if (jaTem == 2) {
                                    mostraMsg("Já há dois dias abertos com a mesma data " + GBFORMATDATA.format(proxDataProv) + " para essa disciplina do DMin.", "Por isso não é possível abrir outra aula com esta data", 3);
                                } else {
                                    podeGerarAula(proxDataProv);
                                }
                            } else {
                                if (jaTem > 0) {
                                    mostraMsg("A aula do dia " + GBFORMATDATA.format(proxDataProv) + " já está aberta!", "Por isso não é possível abrir aula com esta data", 3);
                                } else {
                                    podeGerarAula(proxDataProv);
                                }
                            }

                        }
                    } else {
                        podeGerarAula(proxDataProv);
                    }
                }
            }
        }
    }

    public List<ListaAula> getDataDia(Date dadaDia) {
        Calendar MyEnddate;
        MyEnddate = Calendar.getInstance();
        MyEnddate.add(Calendar.MONTH, 1);
        EntityManager emf = null;

        TypedQuery<ListaAula> query;
        String sQLdataDia = "SELECT s FROM ListaAula s WHERE s.dataAula = :datadia";
        query = emf.createQuery(sQLdataDia, ListaAula.class);
        query.setParameter("dataDia", dadaDia);

        System.out.println("@@@@@@@@@@@@ getDataDia sQLdataDia ");
        System.out.println(sQLdataDia);
        System.out.println("@@@@@@@@@@@@ getDataDia sQLdataDia \n");
        return query.getResultList();
    }

    private Boolean jaTemDiaCadastrado(Date dadaDia) {
//        jpaAul = new ListaAulaJpaController();
//        List<ListaAula> lis = jpaAul.getDataDia(dadaDia);
//        

        EntityManager emf = null;
        Collection<ListaAula> c = getDataDia(dadaDia);

        Boolean jaTem = false;
        for (Iterator i = c.iterator(); i.hasNext();) {
            ListaAula u = (ListaAula) i.next();
            if (u.getDataAula().equals(dadaDia)) {
                jaTem = true;
                break;
            }
        }

        return (jaTem);
    }

    private void podeGerarAula(Date proxDataProv) {
        numAulaAtual = dadosDias.size() + 1;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Abrir a aula nº " + numAulaAtual + ", data " + GBFORMATDATA.format(proxDataProv) + "?");
        alert.setContentText("Confirma a abertura deste dia na Lista de Presença?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            // ABRE A DATA

            // COMO ESTÁ ABRINDO UMA AULA, numAulaAtual É O QUE TINHA +1
            ListaAulaJpaController jpaLau = new ListaAulaJpaController();
            ListaAula lau = new ListaAula();

            lau.setCodLista(gbListaInfo.getCodLista());
            lau.setNumAula(numAulaAtual);
            lau.setDataAula(proxDataProv);
            lau.setDataInc(new Date());
            lau.setCodUserInc(gbUser.getCoduser());

            // GRAVA NOVA DATA EM LISTAAULA
            try {
                jpaLau.create(lau);
            } catch (Exception e) {
                mostraMsg("Erro 0331n - Erro ao inserir clicouAbreData", "" + e, 2);
            }

            try {
                // GRAVA CHAMADA EM BRANCO PARA ESTE DIA
                ListaChamadaJpaController jpaCha = new ListaChamadaJpaController();
                for (int i = 0; i < dadosAM.size(); i++) {
                    ListaChamada cha = new ListaChamada();
                    cha.setCodListaAula(lau.getCodLau());
                    cha.setCodAluno(dadosAM.get(i).getCodAM());
                    cha.setChamada(" ");
                    jpaCha.create(cha);
                }
            } catch (Exception e) {
                mostraMsg("Erro 0332n - Erro ao inserir chamada", "" + e, 2);
            }

            // atualiza a Lista Completa para que apareça o novo dia no cabeçalho da coluna
            preencheListaCompleta();

            // força a seleção da aula criada
            tvAulas.requestFocus();
            tvAulas.getSelectionModel().select(dadosDias.size() - 1);
//                tvAulas.getFocusModel().focus(0);
            //     inicCalend(5);
            // muda para esse painel
        }
    }

    private void inicCalend() {

        String cor = "";
        String top = "";
        switch (qualTipo) {
            case 0:
                cor = "#000000";
                top = "<-- SELECIONE UMA AULA";
                break;
            case 1:
                cor = "#C660D5";
                top = "INSERINDO FALTAS";
                break;
            case 2:
                cor = "#7A22B5";
                top = "INSERINDO ATRASOS";
                break;
            case 3:
                cor = "#0DAEEC";
                top = "INSERINDO GRAVAÇÃO";
                break;
            case 4:
                cor = "#2715FF";
                top = "INSERINDO JUSTIF.";
                break;
            case 5:
                cor = "#000000";
                top = "AULA SELECIONADA";
                break;
            default:
                break;
        }

        lblTopoCal.setStyle("-fx-background-color: " + cor + ";");
        lblDiaSemana.setStyle("-fx-text-fill: " + cor + ";");
        lblDiaMes.setStyle("-fx-background-color: " + cor + ";");
        lblAula.setStyle("-fx-text-fill: " + cor + ";");
        lblNumAula.setStyle("-fx-text-fill: " + cor + ";");
        lblTopoCal.setStyle("-fx-background-color: " + cor + ";");
        lblTopoCal.setText(top);

        if (qualTipo == 0) {
            numAulaAtual = 0;
            lblAula.setVisible(false);
            lblDiaMes.setVisible(false);
            lblDiaSemana.setVisible(false);
            lblNumAula.setVisible(false);
        } else {
            lblAula.setVisible(true);
            lblDiaMes.setVisible(true);
            lblDiaSemana.setVisible(true);
            lblNumAula.setVisible(true);

            if (tvAulas.getSelectionModel().getSelectedItem() == null) {
                // selecione a data!
                lblDiaMes.setText("");
                lblNumAula.setText("");
            } else {
                lblDiaMes.setText(" " + GBFORMATDATA.format((tvAulas.getSelectionModel().getSelectedItem().getDataAula())) + " ");
                lblNumAula.setText(" " + numAulaAtual + " ");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(tvAulas.getSelectionModel().getSelectedItem().getDataAula());
                lblDiaSemana.setText(weekDay(calendar));
            }
        }

//        if (dpDataAbre.getValue() != null) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(Date.from(dpDataAbre.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//            lblDiaSemana.setText(weekDay(calendar));
//        }
    }

    public ObservableList<InsFaltas> getAbreDataFaltas() {
        ObservableList<InsFaltas> dadosFal = FXCollections.observableArrayList();
        String sqlAbreDataFaltas
                = "SELECT cha.CodLista_Chamada, dcg.nome, pup.TipoRegOuv, cha.Chamada "
                + " FROM lista_chamada cha "
                + " LEFT JOIN dadocadastrogeral dcg ON cha.codaluno = dcg.DadoCadastroGeralId "
                + " LEFT JOIN lista_aula aul ON cha.CodLista_Aula = aul.CodLista_Aula "
                + " LEFT JOIN lista_cabec cab ON aul.CodLista = cab.CodLista "
                + " LEFT JOIN cadastroalunodisciplina pup ON cab.CadastroDisciplinaId = pup.CadastroDisciplinaId "
                + " LEFT JOIN dadocadastroprograma dcp ON pup.DadoCadastroProgramaId = dcp.DadoCadastroProgramaId  "
                + " WHERE aul.CodLista = " + gbListaInfo.getCodLista()
                + " AND ((pup.CadastroAlunoDisciplinaSituacaoId !=  4)  "
                + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 14)  "
                + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 15)   "
                + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 16) "
                + " AND (pup.CadastroAlunoDisciplinaSituacaoId != 17) "
                + " OR (pup.CadastroAlunoDisciplinaSituacaoId IS NULL)) "
                + " AND aul.NumAula = " + numAulaAtual
                + " AND dcp.DadoCadastroGeralId = cha.CodAluno  "
                + " ORDER BY pup.IndTipoRegOuv, dcg.nome ";

        try {
            System.out.println("%  getAbreDataFaltas sqlAbreDataFaltas   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println(sqlAbreDataFaltas);
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
            ResultSet rs = con.createStatement().executeQuery(sqlAbreDataFaltas);
            dadosFal.clear();
            while (rs.next()) {

                dadosFal.add(new InsFaltas(rs.getInt("cha.CodLista_Chamada"),
                        rs.getString("dcg.nome"),
                        rs.getString("pup.TipoRegOuv"),
                        rs.getString("cha.Chamada")
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ListaPresencaController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 034k - em getAbreDataFaltas()", "" + ex, 2);
        }
        return dadosFal;
    }

    private Date calcProxDataProv() {

        // se não houver aulas abertas, retorna o dia inicial
        if (dadosDias.size() == 0) {
            return gbListaInfo.getNomeDis().getDataini();
        } else {

            /*
               Tem aula aberta, calcula próximo dia com base no anterior e no tipo da disciplina
               *** criado campo intervalo com o intervalo de dias entre as aulas
             */
            // pega a data da última aula
            Calendar calendar = Calendar.getInstance();
            Date ultData = dadosDias.get(dadosDias.size() - 1).getDataAula();

            if (E_DMIN) {
                /*
                   No DMin há 2 aulas por dia. 
                   Por isso, é preciso verificar se a PENÚLTIMA AULA é igual a ÚLTIMA
                   Se for, a data é um dia a mais. Se não for, a data é igual à última
                 */
                if (dadosDias.size() == 0) { // não há dias abertos. Data = Dataini
                    calendar.setTime(gbListaInfo.getNomeDis().getDataini());
                    return calendar.getTime();
                } else {
                    if (dadosDias.size() % 2 == 0) { // Se o número de dias for par, data = última mais 1
                        Calendar temp = Calendar.getInstance();
                        temp.add(temp.DAY_OF_MONTH, 1);
                        return temp.getTime();
                    } else {
                        return ultData;
                    }
                }
            } else {
                // não é DMin. Próxima data = a última + intervalo
                calendar.setTime(ultData);
                calendar.add(Calendar.DATE, gbListaInfo.getNomeDis().getNomeTipoDis().getIntervalo());
                return calendar.getTime();
            }

        }
    }

    private void closeStage() {
        //    if (!username.getScene().equals(null)) {
        mostraMsg("Fechando tela!", "", 3);
        ((Stage) buConfirma1.getScene().getWindow()).close();
        //     }

    }

    private void inicListaInfo() {
        // SE LISTA DE PRESENÇA NÃO EXISTIA, JÁ FOI CRIADA POR GradeDisMasController
        // e está em gbListaInfo
        lblDis.setText(" " + gbListaInfo.getNomeDis().getDisciplina() + " ");
        lblAno.setText(" " + gbListaInfo.getNomeDis().getAnoLetivo() + " ");
        lblSem.setText(" " + gbListaInfo.getNomeDis().getSemestreId() + " ");
        lblCre.setText(" " + gbListaInfo.getNomeDis().getCredito() + " ");
        lblCar.setText(" " + gbListaInfo.getNomeDis().getCargaHoraria() + " ");
        lblPro1.setText(" " + gbListaInfo.getNomeDis().getNomeProfessor1().getNome() + " ");

        if (gbListaInfo.getNomeDis().getNomeProfessor2() != null) {
            lblPro2.setText(" " + gbListaInfo.getNomeDis().getNomeProfessor2().getNome() + " ");
        } else {
            lblPro2.setText("    ");
        }

        lblTur.setText(" " + gbListaInfo.getNomeDis().getNomeTurno().getTurno() + " ");
        lblHor.setText(" " + GBFORMATHORA.format(gbListaInfo.getNomeDis().getHorafim()) + " ");
        chkDom.selectedProperty().setValue((gbListaInfo.getNomeDis().getDomingo() != null) ? gbListaInfo.getNomeDis().getDomingo() == true : null);
        chkSeg.selectedProperty().setValue((gbListaInfo.getNomeDis().getSegunda() != null) ? gbListaInfo.getNomeDis().getSegunda() == true : null);
        chkTer.selectedProperty().setValue((gbListaInfo.getNomeDis().getTerca() != null) ? gbListaInfo.getNomeDis().getTerca() == true : null);
        chkQua.selectedProperty().setValue((gbListaInfo.getNomeDis().getQuarta() != null) ? gbListaInfo.getNomeDis().getQuarta() == true : null);
        chkQui.selectedProperty().setValue((gbListaInfo.getNomeDis().getQuinta() != null) ? gbListaInfo.getNomeDis().getQuinta() == true : null);
        chkSex.selectedProperty().setValue((gbListaInfo.getNomeDis().getSexta() != null) ? gbListaInfo.getNomeDis().getSexta() == true : null);
        chkSab.selectedProperty().setValue((gbListaInfo.getNomeDis().getSabado() != null) ? gbListaInfo.getNomeDis().getSabado() == true : null);
        lblDataIni.setText(" " + GBFORMATDATA.format(gbListaInfo.getNomeDis().getDataini()) + " ");
        lblDataFim.setText(" " + GBFORMATDATA.format(gbListaInfo.getNomeDis().getDatafim()) + " ");
    }

    private void ligaEdicao() {
        buInsAtra.setDisable(true);
        buInsFal.setDisable(true);
        buInsGra.setDisable(true);
        buInsJus.setDisable(true);
        buConfirma.setDisable(false);
        buCancela.setDisable(false);
        tvAulas.setDisable(true);
        tvTeste.setVisible(false);
        tvChamada.setVisible(true);
    }

    private void desLigaEdicao() {
        buInsAtra.setDisable(false);
        buInsFal.setDisable(false);
        buInsGra.setDisable(false);
        buInsJus.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        tvTeste.setVisible(true);
        tvChamada.setVisible(false);
        tvAulas.setDisable(false);
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
        String chamaX = "";
        switch (qualTipo) {
            case 1: // faltas
                chamaX = "F";
                break;
            case 2: // atrasos
                chamaX = "A";
                break;
            case 3: // gravação
                chamaX = "G";
                break;
            default: // justif
                chamaX = "J";
                break;
        }

        if (tvChamada.isVisible()) { // GRAVA FALTAS
            for (int i = 0; i < tvChamada.getItems().size(); i++) {
                InsFaltas fal = tvChamada.getItems().get(i);
                if (fal.getChkFalta().isSelected()) {
                    // ALTERAR REGISTRO, COLOCANDO "F" SE VAZIO OU " ", SE f
                    ListaChamada cha = jpaCha.findListaChamada(fal.getCodLista_Chamada());
                    cha.setChamada(fal.getChamaFal().equals(chamaX) ? " " : chamaX);

                    try {
                        jpaCha.edit(cha);
                    } catch (Exception e) {
                        mostraMsg("Erro 0334n - Erro ao gravar chamaX (" + chamaX + ")", "" + e, 2);
                    }
                }
            }

            // ATUALIZA NÚMERO DE FALTAS
            atuFaltas(gbListaInfo.getNomeDis().getCadastroDisciplinaId());
        }

        desLigaEdicao();
        preencheListaCompleta();

        tvAulas.requestFocus();
        tvAulas.getSelectionModel().select(numAulaAtual - 1);

        qualTipo = 5;
        inicCalend();
    }

    private void atuFaltas(Integer codDis) {

        ObservableList<TotsChamada> dadosChamada = FXCollections.observableArrayList();
        dadosChamada.clear();
        String sqlTotsChamada
                = " SELECT cha.codaluno, cha.chamada, count(*) as totcham "
                + " FROM lista_chamada cha "
                + " LEFT JOIN lista_aula aul ON cha.CodLista_Aula = aul.CodLista_Aula "
                + " LEFT JOIN lista_cabec cab ON aul.CodLista = cab.CodLista "
                + " WHERE cab.CadastroDisciplinaId = " + codDis
                + " GROUP BY cha.codaluno, cha.chamada "
                + " ORDER BY cha.codaluno, aul.numaula; ";

        try {
            System.out.println("****** sqlTotsChamada ");
            System.out.println(sqlTotsChamada);
            System.out.println("****** sqlTotsChamada\n ");
            ResultSet rs = con.createStatement().executeQuery(sqlTotsChamada);

            while (rs.next()) {
                dadosChamada.add(new TotsChamada(
                        rs.getInt("cha.codaluno"),
                        rs.getString("cha.chamada"),
                        rs.getInt("totcham")));
            }

            Integer codAluno = dadosChamada.get(0).getCodAluno();

            int i = 0;
            while (i < dadosChamada.size()) {
                Integer faltas = 0;
                Integer atrasos = 0;
                Integer totcham = 0;
                while (i < dadosChamada.size() && dadosChamada.get(i).getCodAluno().equals(codAluno)) {
                    if (dadosChamada.get(i).getChamada().equals("F")) {
                        faltas = dadosChamada.get(i).getTotChamada();
                    } else {
                        if (dadosChamada.get(i).getChamada().equals("A")) {
                            atrasos = (int) (dadosChamada.get(i).getTotChamada() / NUMATRASOSIGUALUMAFALTA);
                        }
                    }
                    i++;
                }

                if (i < dadosChamada.size()) {
                    totcham = faltas + atrasos;
                    System.out.println("Aluno: " + codAluno + "    faltas: " + faltas  + "    atrasos: " + atrasos + "   total: " + totcham);
                    codAluno = dadosChamada.get(i).getCodAluno();
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ListaPresencaController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018xe - em atuFaltas()", "" + ex, 2);
            //   return null;
        }

    }

    @FXML
    private void clicouCancela(ActionEvent event) {
        desLigaEdicao();
        qualTipo = 5;
        inicCalend();
    }

    private void invocaChamada() {
        String cabecCol = "";
        switch (qualTipo) {
            case 1: // faltas
                cabecCol = "FALTAS";
                break;
            case 2: // atrasos
                cabecCol = "ATRASOS";
                break;
            case 3: // gravação
                cabecCol = "GRAV.";
                break;
            default: // justif
                cabecCol = "JUSTIF.";
                break;
        }

        TableColumn tcAluTeste = new TableColumn("Aluno");
        TableColumn tcTipoTeste = new TableColumn("Tipo");

        TableColumn tcFalPK = new TableColumn("codLista_Chamada");
        TableColumn tcFalAlu = new TableColumn("Nome do Aluno");
        TableColumn tcFalTip = new TableColumn("Tipo Aluno");
        TableColumn tcFalCha = new TableColumn("Chamada");
        TableColumn tcFalFal = new TableColumn(cabecCol);

        tcFalAlu.setMinWidth(482);
        tcFalAlu.setMaxWidth(482);
        tcFalTip.setMinWidth(82);
        tcFalTip.setMaxWidth(82);
        tcFalCha.setMinWidth(66);
        tcFalCha.setMaxWidth(66);
        tcFalFal.setMinWidth(66);
        tcFalFal.setMaxWidth(66);

        tcFalPK.setVisible(false);
        tcFalAlu.setCellValueFactory(new PropertyValueFactory<>("nomeFalt"));
        tcFalTip.setCellValueFactory(new PropertyValueFactory<>("tipoFalt"));
        tcFalCha.setCellValueFactory(new PropertyValueFactory<>("chamaFal"));
        tcFalFal.setCellValueFactory(new PropertyValueFactory<>("chkFalta"));

        tcFalTip.setStyle("-fx-alignment: CENTER;");
        tcFalCha.setStyle("-fx-alignment: CENTER;");
        tcFalFal.setStyle("-fx-alignment: CENTER;");

        ligaEdicao();
        inicCalend();
        tvChamada.setVisible(true);
        tvTeste.setVisible(false);

        tvChamada.setEditable(true);
        tvChamada.getColumns().clear();
        tvChamada.getColumns().addAll(tcFalPK, tcFalAlu, tcFalTip, tcFalCha, tcFalFal);

        tvChamada.getItems().clear();
        tvChamada.setItems(getAbreDataFaltas());
        tvChamada.refresh();
    }

    @FXML
    private void clicouInsFaltas(ActionEvent event) {
        if (tvAulas.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Selecione uma data no painel 'Aulas Abertas' ou abra a primeira aula.", "", 3);
        } else {
            qualTipo = 1;
            invocaChamada();
        }
    }

    @FXML
    private void clicouInsAtrasos(ActionEvent event) {
        if (tvAulas.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Selecione uma data no painel 'Aulas Abertas' ou abra a primeira aula.", "", 3);
        } else {
            qualTipo = 2;
            invocaChamada();
        }
    }

    @FXML
    private void clicouInsGra(ActionEvent event) {
        if (tvAulas.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Selecione uma data no painel 'Aulas Abertas' ou abra a primeira aula.", "", 3);
        } else {
            qualTipo = 3;
            invocaChamada();
        }
    }

    @FXML
    private void clicouInsJus(ActionEvent event) {
        if (tvAulas.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Selecione uma data no painel 'Aulas Abertas' ou abra a primeira aula.", "", 3);
        } else {
            qualTipo = 4;
            invocaChamada();
        }
    }

    @FXML
    private void clicouRemoveDia(ActionEvent event) {
        if (tvAulas.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Remover dia da Lista de Presença");
            alert.setContentText("\nAtenção!\n\nDeseja realmente apagar da Lista de Presença\n\no dia " + GBFORMATDATA.format(tvAulas.getSelectionModel().getSelectedItem().getDataAula()) + ", aula nº "
                    + tvAulas.getSelectionModel().getSelectedItem().getNumAula() + "?\n\nEsse procedimento não pode ser revertido.");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {

                // resolvi não perguntar sobre RECALCULAR. SEMPRE RECALCULAR
                apagaDiaChamada(tvAulas.getSelectionModel().getSelectedItem().getCodLista_Aula(), true);

//                int inde = (int) tvAulas.getSelectionModel().selectedIndexProperty().getValue();
//                if (tvAulas.getSelectionModel().selectedIndexProperty().getValue() + 1 == tvAulas.itemsProperty().get().size()) {
//                    //    é o ultimo dia. não precisa recalcular
//                    apagaDiaChamada(tvAulas.getSelectionModel().getSelectedItem().getCodLista_Aula(), false);
//                } 
//                else {
//                    Alert recalc = new Alert(Alert.AlertType.CONFIRMATION);
//                    recalc.setHeaderText("Como o dia a ser apagado não é o último, você deve escolher se o sistema recalculará a numeração dos dias posteriores ou não.");
//                    recalc.setContentText("Recalcular [Sim], Não Recalcular[Não] ou Não Apagar o Dia [Cancelar]");
//                    recalc.getButtonTypes().clear();
//                    recalc.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//
//                    Optional<ButtonType> option2 = recalc.showAndWait();
//                    if (option2.get() == ButtonType.CANCEL) {
//                        mostraMsg("O dia selecionado NÂO foi apagado", "", 2);
//                    } else {
//                        if (option2.get() == ButtonType.YES) {
//                            apagaDiaChamada(tvAulas.getSelectionModel().getSelectedItem().getCodLista_Aula(), true);
//                        } else {
//                            apagaDiaChamada(tvAulas.getSelectionModel().getSelectedItem().getCodLista_Aula(), false);
//                        }
//                    }
//                }
            }
        }
    }

    private void apagaDiaChamada(Integer codListaAula, Boolean recalcula) {
        List<ListaChamada> dadosChamada = FXCollections.observableArrayList();
        jpaCha = new ListaChamadaJpaController();
        jpaAul = new ListaAulaJpaController();
        dadosChamada = jpaCha.pegaChamadaDia(codListaAula);

        int err = 0;
        int ctl = 0;
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // APAGA CHAMADAS EM LISTA_CHAMADA
            for (int i = 0; i < dadosChamada.size(); i++) {
                ListaChamada cha = dadosChamada.get(i);

                ListaChamada current = null;
                if (!em.contains(cha)) {
                    current = em.merge(cha);
                }
                em.remove(current);

                ++ctl;
            }
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }

        try {
            // APAGA DIA EM LISTA_AULA
            jpaAul.destroy(codListaAula);

        } catch (Exception e) {
            ++err;
            mostraMsg("Erro 098a - erro ao apagar dia da Lista_Aula - ", "" + e, 2);
        }

        try {
            // RECALCULA, SE NECESSÁRIO
            if (recalcula) {
                Integer j = tvAulas.getSelectionModel().selectedIndexProperty().getValue() + 1; // o selecionado era + 1. +2 deve pegar o próximo
                for (int i = j; i < tvAulas.itemsProperty().get().size(); i++) {
                    ListaAula lau = jpaAul.findListaAula(tvAulas.getItems().get(j).getCodLista_Aula());
                    lau.setNumAula(lau.getNumAula() - 1);
                    jpaAul.edit(lau);
                    ++j;
                }
            }
        } catch (Exception e) {
            ++err;
            mostraMsg("Erro 098b - erro ao recalcular dia da Lista_Aula - ", "" + e, 2);
        }

        // MONTA LISTA COMPLETA
        montaLc();
        qualTipo = 0;
        inicCalend();

        if (err == 0) {
            mostraMsg("Operação concluída com sucesso", "Foram apagados " + ctl + " registros", 3);
        }

    }

    private Boolean temJaEsteDia(Date aulaDia) {
        ObservableList<Integer> dadosLAD = FXCollections.observableArrayList();
        dadosLAD.clear();
        String strAulaDia = GBFORMATDATASQL.format(aulaDia);
        String sqlDiasAula
                = " SELECT CodLista_Aula FROM lista_aula "
                + " WHERE CodLista = " + gbListaInfo.getCodLista()
                + " AND DataAula = '" + strAulaDia + "'";
        try {
            System.out.println("--------- sqlDiasAula ------");
            System.out.println(sqlDiasAula);
            System.out.println("----------------------------\n");
            ResultSet rs = con.createStatement().executeQuery(sqlDiasAula);

            while (rs.next()) {
                dadosLAD.add(rs.getInt("CodLista_Aula"));
            }

            return (dadosLAD.size() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(ListaPresencaController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018gd - em getDiasAUla()", "" + ex, 2);
            return null;
        }

    }

    @FXML
    private void clicouSair(ActionEvent event) {
        Stage stage = (Stage) buConfirma.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void clicouDrag(MouseEvent event) {
        //  mostraMsg("Está tentando mover a janela", "", 3);
    }

}
