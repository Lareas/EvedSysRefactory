/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.ESVERSION;
import com.jfoenix.controls.JFXCheckBox;
import static main.Login.gbRegCadGer;
import static main.Login.gbUser;
import entities.TelaAluno;
import funcoes.ComboBoxAutoComplete;
import funcoes.DBConnector;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class DadocadastrogeralController implements Initializable {

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
    private TableColumn<TelaAluno, String> tcFormacao;
    @FXML
    private TableColumn<TelaAluno, String> tcProfissao;
    @FXML
    private TableColumn<TelaAluno, String> tcMinisterio;
    @FXML
    private TableColumn<TelaAluno, String> tcDenominacao;
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
    @FXML
    private ComboBox<String> cbFormacoes;
    @FXML
    private ComboBox<String> cbProfissoes;
    @FXML
    private ComboBox<String> cbMinisterios;
    @FXML
    private ComboBox<String> cbDenominacoes;
    @FXML
    private JFXCheckBox chk1Sem;
    @FXML
    private JFXCheckBox chk2Sem;
    @FXML
    private TextField edAnoLetivo;
    @FXML
    private TextField lblRegistros;

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
    @FXML
    private Label lblFiltrados;
    @FXML
    private TextField edMatri;
    @FXML
    private TextField edCodAlu;
    @FXML
    private TableColumn<?, ?> tcMatri;
    @FXML
    private TableColumn<?, ?> tcCodigo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        jpaCon = new DadocadastrogeralJpaController();

        edAnoLetivo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}")) {
                    edAnoLetivo.setText(oldValue);
                }
            }
        });

        // DETERMINA SEMESTRE PARA MARCAR PESQUISA
        if (!gbUser.getLogin().equals("lfa")) {

            // DETERMINA SEMESTRE PARA MARCAR PESQUISA
            Calendar cal = Calendar.getInstance();
            System.out.println(cal.getTime());

            mesHoje = cal.get(Calendar.MONTH) + 1;
            anoHoje = cal.get(Calendar.YEAR);
            semHoje = 0;
            edAnoLetivo.setText(String.valueOf(anoHoje));

            if (mesHoje > 6) {
                chk2Sem.selectedProperty().setValue(true);
                semHoje = 2;
            } else {
                chk1Sem.selectedProperty().setValue(true);
                semHoje = 1;
            }

            cbProgramas.setItems(getNomeProgramas());
            cbCursos.setItems(getNomeCursos());
            cbSituacoes.setItems(getNomeSituacoes());
            cbSituacoes.getSelectionModel().select("Ativo");
            cbFormacoes.setItems(getNomeFormacoes());
            cbProfissoes.setItems(getNomeProfissoes());
            cbMinisterios.setItems(getNomeMinisterios());
            cbDenominacoes.setItems(getNomeDenominacoes());

            filtroAtivos = " AND dcp.anoLetivo = " + anoHoje + " AND dcp.semestreId = " + semHoje + " AND dcp.DadoCadastroProgramaSituacaoId = 2";
            initColumns(filtroAtivos);

        } else {
            cbProgramas.setItems(getNomeProgramas());
            cbCursos.setItems(getNomeCursos());
            cbSituacoes.setItems(getNomeSituacoes());
            cbFormacoes.setItems(getNomeFormacoes());
            cbProfissoes.setItems(getNomeProfissoes());
            cbMinisterios.setItems(getNomeMinisterios());
            cbDenominacoes.setItems(getNomeDenominacoes());
            initColumns("");
        }

        new ComboBoxAutoComplete<String>(cbProgramas, "");
        new ComboBoxAutoComplete<String>(cbCursos, "");
        new ComboBoxAutoComplete<String>(cbSituacoes, "");
        new ComboBoxAutoComplete<String>(cbFormacoes, "");
        new ComboBoxAutoComplete<String>(cbProfissoes, "");
        new ComboBoxAutoComplete<String>(cbMinisterios, "");
        new ComboBoxAutoComplete<String>(cbDenominacoes, "");

    }

    @FXML
    private void clicouAbre(ActionEvent event) {
        AbreCadastro();
    }

    public void AbreCadastro() {
        if (tvPesquisa.getSelectionModel().getSelectedItem() != null) {
            reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();

            //PASSA REGISTRO SELECIONADO PARA A VARIÁVEL GLOBAL rgRegDisc, que será usado na tela TabelaDadocadastrogerals
            gbRegCadGer = jpaCon.findDadocadastrogeral( reg_atual.getCodAluno());

            // ABRE CADASTRODISCIPLINA (FILHO) DO REGISTRO SELECIONADO (DISCIPLINA-PAI)
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/DadosCadastrais.fxml"));
                Stage stageCadastrais = new Stage(StageStyle.DECORATED);
                stageCadastrais.initModality(Modality.WINDOW_MODAL);
                stageCadastrais.setTitle("Dadocadastrogeral " + ESVERSION);;
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
    }

    public void initColumns(String condicao) {
        oblist = FXCollections.observableArrayList();

        String sql = "SELECT ger.DadoCadastroGeralId, ger.Matricula, ger.nome, pro.programa as titulacao, count(*) as numprogs, cur.curso as programa, prs.DadoCadastroProgramaSituacao, "
                + " fm.nomeformacao, pf.nomeprofissao, dcm.Ministeriais, dn.Denominacao,  "
                + " dcp.anoLetivo, dcp.semestreId, dcp.datacadastro, dcp.localidade, ger.telefonecelular, ger.EmailPessoal "
                + " FROM dadocadastrogeral ger "
                + "  LEFT JOIN dadocadastroprograma dcp ON ger.dadocadastrogeralid = dcp.DadoCadastroGeralId "
                + "  LEFT JOIN curso cur ON dcp.cursoId = cur.cursoId "
                + "  LEFT JOIN programa pro ON cur.ProgramaId = pro.ProgramaId "
                + "  LEFT JOIN dadocadastroprogramasituacao prs ON dcp.DadoCadastroProgramaSituacaoId = prs.DadoCadastroProgramaSituacaoId "
                + "  LEFT JOIN formacao fm ON ger.codformacao = fm.codformacao "
                + "  LEFT JOIN profissoes pf ON ger.codprofissao = pf.codprofissao "
                + "  LEFT JOIN Denominacao dn ON ger.DenominacaoId = dn.DenominacaoId "
                + "  LEFT JOIN dadocadastrogeralministeriais dcm on (ger.DadoCadastroGeralId = dcm.DadoCadastroGeralId) ";

        String sql2 = "  GROUP BY ger.DadoCadastroGeralId ";

        String sqlFinal = "";
        if (condicao.trim().length() > 0) {
            String con = condicao.substring(4, condicao.trim().length() + 1);
            sqlFinal = sql + " WHERE " + con + " " + sql2;
        } else {
            sqlFinal = sql + " " + sql2;
        }

        try {
//            System.out.println("\n\n\n>>>> " + sqlFinal + " <<<\n\n\n");
//            System.out.println("condicao = " + condicao);
//            System.out.println("sql2 = " + sql2);
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
                        rs.getString("nomeformacao"),
                        rs.getString("nomeprofissao"),
                        rs.getString("Ministeriais"),
                        rs.getString("Denominacao"),
                        rs.getShort("anoLetivo"),
                        rs.getShort("semestreId"),
                        rs.getDate("datacadastro"),
                        rs.getString("Localidade"),
                        rs.getString("telefoneCelular"),
                        rs.getString("emailPessoal")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tcMatri.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codAluno"));
        tcAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        tcPrograma.setCellValueFactory(new PropertyValueFactory<>("programaNome"));
        tcCurso.setCellValueFactory(new PropertyValueFactory<>("cursoNome"));
        tcCurso.setCellValueFactory(new PropertyValueFactory<>("numprogs"));
        tcSituacao.setCellValueFactory(new PropertyValueFactory<>("situacaoNome"));
        tcFormacao.setCellValueFactory(new PropertyValueFactory<>("formacaoNome"));
        tcProfissao.setCellValueFactory(new PropertyValueFactory<>("profissaoNome"));
        tcMinisterio.setCellValueFactory(new PropertyValueFactory<>("ministerioNome"));
        tcDenominacao.setCellValueFactory(new PropertyValueFactory<>("denominacaoNome"));
        tcAnoLetivo.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tcSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        tcDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        tcLocalidade.setCellValueFactory(new PropertyValueFactory<>("Localidade"));
        tcCelular.setCellValueFactory(new PropertyValueFactory<>("telefoneCelular"));
        tcEmailPessoal.setCellValueFactory(new PropertyValueFactory<>("emailPessoal"));

        //   TelaAlunosDAO dao = new TelaAlunosDAO();
        tvPesquisa.setItems(oblist);
        tvPesquisa.refresh();
        lblRegistros.setText("Registros exibidos: " + tvPesquisa.itemsProperty().get().size()); //rs2.getString(1));
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

            initColumns("");

            // limpar campos e seleção dos combos
            edAluno.setText("");
            edAnoLetivo.setText("");

            chk1Sem.selectedProperty().setValue(false);
            chk2Sem.selectedProperty().setValue(false);

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
        initColumns(filtroAtivos);

        // limpar campos e seleção dos combos
        edAluno.setText("");
        edAnoLetivo.setText(String.valueOf(anoHoje));

        if (semHoje == 1) {
            chk1Sem.selectedProperty().setValue(true);
            chk2Sem.selectedProperty().setValue(false);
        } else {
            chk1Sem.selectedProperty().setValue(false);
            chk2Sem.selectedProperty().setValue(true);
        }

        cbProgramas.getSelectionModel().clearSelection();
        cbSituacoes.getSelectionModel().select("Ativo");
        cbCursos.getSelectionModel().clearSelection();
    }

    public void filtrar() {
        String pesq = "";

        if (edCodAlu.getText().trim().length() > 0) {
            pesq = pesq + " AND ger.DadoCadastroGeralId LIKE '%" + edCodAlu.getText() + "%' ";
        }

        if (edMatri.getText().trim().length() > 0) {
            pesq = pesq + " AND ger.Matricula LIKE '%" + edMatri.getText() + "%' ";
        }

        if (edAluno.getText().trim().length() > 0) {
            pesq = pesq + " AND ger.nome LIKE '%" + edAluno.getText() + "%' ";
        }

        if ((cbProgramas.getSelectionModel().getSelectedItem() != null) && (cbProgramas.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND pro.programa = '" + cbProgramas.getSelectionModel().getSelectedItem() + "' ";
        }

        if ((cbCursos.getSelectionModel().getSelectedItem() != null) && (cbCursos.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND cur.curso = '" + cbCursos.getSelectionModel().getSelectedItem() + "' ";
        }

        if ((cbSituacoes.getSelectionModel().getSelectedItem() != null) && (cbSituacoes.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND prs.DadoCadastroProgramaSituacao = '" + cbSituacoes.getSelectionModel().getSelectedItem() + "' ";
        }

        if ((cbFormacoes.getSelectionModel().getSelectedItem() != null) && (cbFormacoes.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND fm.nomeformacao = '" + cbFormacoes.getSelectionModel().getSelectedItem() + "' ";
        }

        if ((cbProfissoes.getSelectionModel().getSelectedItem() != null) && (cbProfissoes.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND pf.nomeprofissao = '" + cbProfissoes.getSelectionModel().getSelectedItem() + "' ";
        }

        if ((cbMinisterios.getSelectionModel().getSelectedItem() != null) && (cbMinisterios.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND dcm.Ministeriais = '" + cbMinisterios.getSelectionModel().getSelectedItem() + "' ";
        }

        if ((cbDenominacoes.getSelectionModel().getSelectedItem() != null) && (cbDenominacoes.getSelectionModel().getSelectedItem() != "[TODOS]")) {
            pesq = pesq + " AND dn.Denominacao = '" + cbDenominacoes.getSelectionModel().getSelectedItem() + "' ";
        }

        if (edAnoLetivo.getText().trim().length() > 0) {
            pesq = pesq + " AND dcp.anoLetivo = " + edAnoLetivo.getText() + " ";
        }

        if ((chk1Sem.selectedProperty().getValue() == true) && (chk2Sem.selectedProperty().getValue() == true)) {
            pesq = pesq + " AND (dcp.semestreId = 1 OR dcp.semestreId = 2) ";
        } else if (chk1Sem.selectedProperty().getValue() == true) {
            pesq = pesq + " AND dcp.semestreId = 1 ";
        } else if (chk2Sem.selectedProperty().getValue() == true) {
            pesq = pesq + " AND dcp.semestreId = 2 ";
        }

        initColumns(pesq);
    }
    
    public ObservableList<String> getNomeProgramas() {
        jpaPro = new ProgramaJpaController();
        dadosPro = FXCollections.observableArrayList(jpaPro.getNomeDosProgramas());
        dadosPro.add(0, "[TODOS]");

        if (dadosPro == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosPro;
        }
    }

    public ObservableList<String> getNomeSituacoes() {
        jpaSit = new DadocadastroprogramasituacaoJpaController();
        dadosSit = FXCollections.observableArrayList(jpaSit.getNomeDasSituacoes());
        dadosSit.add(0, "[TODOS]");

        if (dadosSit == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosSit;
        }
    }

    public ObservableList<String> getNomeCursos() {
        jpaCur = new CursoJpaController();
        dadosCur = FXCollections.observableArrayList(jpaCur.getNomeDosCursos());
        dadosCur.add(0, "[TODOS]");

        if (dadosCur == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosCur;
        }
    }

    public ObservableList<String> getNomeFormacoes() {
        jpaFor = new FormacaoJpaController();
        dadosFor = FXCollections.observableArrayList(jpaFor.getNomeDasFormacoes());
        dadosFor.add(0, "[TODOS]");

        if (dadosFor == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosFor;
        }
    }

    public ObservableList<String> getNomeProfissoes() {
        jpaProf = new ProfissoesJpaController();
        dadosProf = FXCollections.observableArrayList(jpaProf.getNomeDasProfissoes());
        dadosProf.add(0, "[TODOS]");

        if (dadosProf == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosProf;
        }
    }

    public ObservableList<String> getNomeMinisterios() {
        jpaMin = new MinisteriaisJpaController();
        dadosMin = FXCollections.observableArrayList(jpaMin.getNomeDosMinisteriosF());
        dadosMin.add(0, "[TODOS]");

        if (dadosMin == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosMin;
        }
    }

    public ObservableList<String> getNomeDenominacoes() {
        jpaDen = new DenominacaoJpaController();
        dadosDen = FXCollections.observableArrayList(jpaDen.getNomeDasDeniminacoes());
        dadosDen.add(0, "[TODOS]");

        if (dadosDen == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosDen;
        }
    }

    @FXML
    private void clicouTvPesquisa(MouseEvent event) {
    }

    @FXML
    private void onActionClicar(ActionEvent event) {
        filtrar();
    }
}
