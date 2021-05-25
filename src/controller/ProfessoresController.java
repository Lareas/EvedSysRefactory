/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.ClassesProf;
import entities.DisciProf;
import entities.Funcionario;
import entities.GraDisAluPro;
import entities.ProfessoresGrid;
import entities.Tabbancos;
import funcoes.DBConnector;
import static funcoes.MyFunc.calcularIdade;
import static funcoes.MyFunc.convInt;
import static funcoes.MyFunc.mostraMsg;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.CordapeleJpaController;
import jpa_controler.DadocadastrogeralministeriaisJpaController;
import jpa_controler.DadocadastroprogramaJpaController;
import jpa_controler.DenominacaoJpaController;
import jpa_controler.DocinscJpaController;
import jpa_controler.DocsoficiaisJpaController;
import jpa_controler.EsEquivalenciaJpaController;
import jpa_controler.EsFotosalunosJpaController;
import jpa_controler.EsGradeJpaController;
import jpa_controler.EsMatrisemJpaController;
import jpa_controler.EstadocivilJpaController;
import jpa_controler.FormacaoJpaController;
import jpa_controler.FuncionarioJpaController;
import jpa_controler.NacionalidadeJpaController;
import jpa_controler.ProfissoesJpaController;
import jpa_controler.SiglaestadoJpaController;
import jpa_controler.TabbancosJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class ProfessoresController implements Initializable {

    @FXML
    private TabPane tbALuno;
    @FXML
    private TableView<Funcionario> tvProfs;
    @FXML
    private TableColumn<Funcionario, Integer> tcCodProf;
    @FXML
    private TableColumn<Funcionario, String> tcNomeProf;
    @FXML
    private TableColumn<Funcionario, String> tcEspecProf;
    @FXML
    private Tab tabDadosPessoais;
    @FXML
    private TextField edCepAluno;
    @FXML
    private JFXButton buImpCepAlu;
    @FXML
    private TextField edEnderecoAluno;
    @FXML
    private TextField edBairroAluno;
    @FXML
    private TextField edCidadeAluno;
    @FXML
    private ComboBox<String> cbUFAluno;
    @FXML
    private TextField edFoneRes;
    @FXML
    private TextField edFoneCel;
    @FXML
    private TextField edFoneCom;
    @FXML
    private TextField edEmailAluno;
    @FXML
    private ComboBox<String> cbCorPele;
    @FXML
    private ComboBox<String> cbEstadoCivil;
    @FXML
    private TextField edConjuge;
    @FXML
    private TextField edDependentes;
    @FXML
    private TextField edNaturalidade;
    @FXML
    private JFXDatePicker dpNasc;
    @FXML
    private ComboBox<String> cbUFNatu;
    @FXML
    private ComboBox<String> cbNacionalidade;
    @FXML
    private JFXButton buRefUFAluno;
    @FXML
    private JFXButton buRefEst;
    @FXML
    private JFXButton buRefUFNat;
    @FXML
    private JFXButton buRefNac;
    @FXML
    private JFXButton buSP1;
    @FXML
    private Label lblIdade;
    @FXML
    private Tab tabDadosPessoais1;
    @FXML
    private ComboBox<String> cbBancos;
    @FXML
    private TextField edAgencia;
    @FXML
    private TextField edConta;
    @FXML
    private TextField edCPF;
    @FXML
    private TextField edFacebook;
    @FXML
    private TextField edTwitter;
    @FXML
    private TextField edInstagram;
    @FXML
    private TextArea taObservacoes;
    @FXML
    private TableView<DisciProf> tvDisci;
    @FXML
    private TextField lblAluDis11;
    @FXML
    private TableView<ClassesProf> tvClasses;
    @FXML
    private TextField lblAluDis1;
    @FXML
    private ContextMenu cmAbreCadastro;
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
    private TableColumn<GraDisAluPro, String> tcTipo;
    @FXML
    private TextField lblAluDis;
    @FXML
    private AnchorPane paneCabec;
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
    private TextField edNome;
    @FXML
    private JFXRadioButton rbSexF;
    @FXML
    private ToggleGroup gp1;
    @FXML
    private JFXRadioButton rbSexM;
    @FXML
    private FontAwesomeIconView fvBoneco;
    @FXML
    private ImageView ivFotoAluno;
    @FXML
    private Label lblCodAluno;
    @FXML
    private TextField tfFiltrar;
    @FXML
    private TextField lblRegsProfs;
    @FXML
    private TextField edEpec;
    @FXML
    private CheckBox chkAtivo;

    private ObservableList<Funcionario> dadosProf;
    private FuncionarioJpaController jpaProf;
    public Connection con = DBConnector.getConnection();

    private SiglaestadoJpaController jpaUF;
    private EstadocivilJpaController jpaEC;
    private CordapeleJpaController jpaCP;
    private NacionalidadeJpaController jpaNA;
    private TabbancosJpaController jpaBA;

    private String sqlDisci
            = " SELECT cad.Descricao "
            + " FROM cadastrodisciplina cad "
            + " WHERE cad.FuncionarioId = $P{CODPROF}"
            + " GROUP BY cad.Descricao "
            + " ORDER BY cad.Descricao ";

    private String sqlProfessores
            = " SELECT fun.FuncionarioId, fun.Nome, fun.Especificidade "
            + " FROM funcionario fun "
            + " WHERE fun.professor = 1 "
            + " ORDER BY fun.Nome ";

    private String sqlClasses = "";

    private boolean inserindo, editando;

    Funcionario reg_atual = new Funcionario();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jpaProf = new FuncionarioJpaController();

        inserindo = false;
        editando = false;
        desligaEdicao();
        initColumns();

        // só para usuário com permissão
        buApaga.setVisible(true);
//        

        iniGridProfs();
    }

    public void ligaEdicao() {

        // habilita botões para inserção e deleção
        buConfirma.setDisable(false);
        buCancela.setDisable(false);
        buApaga.setDisable(true);

        buRefUFAluno.setDisable(false);
        buRefEst.setDisable(false);
        buRefNac.setDisable(false);
        buRefUFNat.setDisable(false);
        buSP1.setDisable(false);

        // combos
        cbUFAluno.setDisable(false);
        cbEstadoCivil.setDisable(false);
        cbUFNatu.setDisable(false);
        cbNacionalidade.setDisable(false);
        cbCorPele.setDisable(false);

        // habilita botões para inserção e deleção
        buInsere.setDisable(true);
        buEdita.setDisable(true);
        buConfirma.setDisable(false);
        buCancela.setDisable(false);
        buApaga.setDisable(true);

        // HABILITA CAMPOS
        edNome.setEditable(true);
        rbSexF.setDisable(false);
        rbSexM.setDisable(false);
        chkAtivo.setDisable(false);
        edCepAluno.setEditable(true);
        buImpCepAlu.setDisable(false);
        edEnderecoAluno.setEditable(true);
        edBairroAluno.setEditable(true);
        edCidadeAluno.setEditable(true);
        edFoneRes.setEditable(true);
        edFoneCel.setEditable(true);
        edFoneCom.setEditable(true);
        edEmailAluno.setEditable(true);

        edConjuge.setEditable(true);
        edDependentes.setEditable(true);
        edNaturalidade.setEditable(true);
        dpNasc.setEditable(true);

        edCPF.setEditable(true);

        cbBancos.setDisable(false);
        edAgencia.setEditable(true);
        edConta.setEditable(true);
        edCPF.setEditable(true);

        edFacebook.setEditable(true);
        edTwitter.setEditable(true);
        edInstagram.setEditable(true);

        taObservacoes.setEditable(true);

    }

    public void desligaEdicao() {

        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buApaga.setDisable(false);

        buRefUFAluno.setDisable(true);
        buRefEst.setDisable(true);
        buRefNac.setDisable(true);
        buRefUFNat.setDisable(true);
        buSP1.setDisable(true);

        // combos
        cbUFAluno.setDisable(true);
        cbEstadoCivil.setDisable(true);
        cbUFNatu.setDisable(true);
        cbNacionalidade.setDisable(true);
        cbCorPele.setDisable(true);

        // habilita botões para inserção e deleção
        buInsere.setDisable(false);
        buEdita.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buApaga.setDisable(false);

        // HABILITA CAMPOS
        edNome.setEditable(false);
        rbSexF.setDisable(true);
        rbSexM.setDisable(true);
        chkAtivo.setDisable(true);
        edCepAluno.setEditable(false);
        buImpCepAlu.setDisable(true);
        edEnderecoAluno.setEditable(false);
        edBairroAluno.setEditable(false);
        edCidadeAluno.setEditable(false);
        edFoneRes.setEditable(false);
        edFoneCel.setEditable(false);
        edFoneCom.setEditable(false);
        edEmailAluno.setEditable(false);

        edConjuge.setEditable(false);
        edDependentes.setEditable(false);
        edNaturalidade.setEditable(false);
        dpNasc.setEditable(false);

        edCPF.setEditable(false);

        cbBancos.setDisable(true);
        edAgencia.setEditable(false);
        edConta.setEditable(false);
        edCPF.setEditable(false);

        edFacebook.setEditable(false);
        edTwitter.setEditable(false);
        edInstagram.setEditable(false);

        taObservacoes.setEditable(false);
    }

    private void inicGridProfs() {
//        getProfGrid();
//        tvProfs.setItems(dadosProf);
//        tvProfs.refresh();
//        for (int i = 0; i < dadosProf.size(); i++) {
//            Funcionario get = dadosProf.get(i);
//            System.out.println(get.getf() "--" + get.getNome()+ " // " + get.getEspecificidade());
//            
//        }

    }

    public void iniGridProfs() {
        dadosProf = FXCollections.observableArrayList();
        try {

            System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println(sqlProfessores);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@\n");

            ResultSet rs = con.createStatement().executeQuery(sqlProfessores);

            while (rs.next()) {
                dadosProf.add(new Funcionario(
                        rs.getInt("fun.FuncionarioId"),
                        rs.getString("fun.Nome"),
                        rs.getString("fun.Especificidade")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DadocadastrogeralSimplesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tvProfs.setItems(dadosProf);
        tvProfs.refresh();
        String spa = "    ";
        lblRegsProfs.setText("Registros exibidos: " + tvProfs.itemsProperty().get().size());
    }

    @FXML
    private void clicouTFFcep(KeyEvent event) {
    }

    @FXML
    private void clicouImpCEP(ActionEvent event) {
    }

    @FXML
    private void clicouTFFfoneres(KeyEvent event) {
    }

    @FXML
    private void clicouTFFfonecel(KeyEvent event) {
    }

    @FXML
    private void clicouTFFfonecom(KeyEvent event) {
    }

    @FXML
    private void clicouTFFdepen(KeyEvent event) {
    }

    @FXML
    private void mudouDataNasc(ActionEvent event) {
    }

    @FXML
    private void clicouTFFdnasc(KeyEvent event) {
    }

    @FXML
    private void clicouRefresUFResid(ActionEvent event) {
    }

    @FXML
    private void clicouRefreshfEst(ActionEvent event) {
    }

    @FXML
    private void clicouRefreshUFNat(ActionEvent event) {
    }

    @FXML
    private void clicouRefreshfNac(ActionEvent event) {
    }

    @FXML
    private void clicouSP1(ActionEvent event) {
    }

    @FXML
    private void clicouTFFcpf(KeyEvent event) {
    }

    @FXML
    private void abreCadastro(ActionEvent event) {
    }

    @FXML
    private void clicouEdita(ActionEvent event) {
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
    }

    @FXML
    private void ClicouInsFoto(ActionEvent event) {
    }

    @FXML
    private void clicouVeFoto(ActionEvent event) {
    }

    @FXML
    private void clicouApagaFoto(ActionEvent event) {
    }

    @FXML
    private void clicouMinhaPesqEnter(KeyEvent event) {
    }

    @FXML
    private void clicouFiltrar(ActionEvent event) {
        if (tfFiltrar.getText().trim().length() < 1) {
            mostraMsg("Pesquisa", "Informe o conteúdo a pesquisar", 3);
            tfFiltrar.requestFocus();
        } else {
            tvProfs.setItems(getMinhaPesqDados(tfFiltrar.getText().trim()));
        }
    }

    @FXML
    private void clicouLimpaFiltro(ActionEvent event) {
    }

    public void initColumns() {
        tcCodProf.setCellValueFactory(new PropertyValueFactory<>("codProf"));
        tcNomeProf.setCellValueFactory(new PropertyValueFactory<>("nomeProf"));
        tcEspecProf.setCellValueFactory(new PropertyValueFactory<>("especProf"));
        tvProfs.setItems(getMeusDados());
        tvProfs.refresh();
    }

    public ObservableList<Funcionario> getMeusDados() {

        jpaProf = new FuncionarioJpaController();
        dadosProf = FXCollections.observableArrayList(jpaProf.findFuncionarioEntities());
        tfFiltrar.setText("");

        lblRegsProfs.setText("Registros exibidos: " + tvProfs.itemsProperty().get().size());

        if (dadosProf == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosProf;
        }

    }

    public ObservableList<Funcionario> getMinhaPesqDados(String pesq) {

        jpaProf = new FuncionarioJpaController();
        dadosProf = FXCollections.observableArrayList(jpaProf.getProfsPesq(pesq));
        lblRegsProfs.setText("");

        lblRegsProfs.setText("Registros exibidos: " + tvProfs.itemsProperty().get().size());

        if (dadosProf == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosProf;
        }
    }

    @FXML
    private void clicouInsere(ActionEvent event) {
        reg_atual = null;
        limpaCampos(); // para quem vai requestfocus;
        ligaEdicao();
        inserindo = true;
        edNome.requestFocus();
    }

    public void limpaCampos() {
        edNome.setText("");
        edEpec.setText("");
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

                    limpaCampos();    // limpa campos
                    inserindo = false;
                } else {
                    mostraMsg("Mensagem DN", "Mensagem DN-YANSTBH - 011", 2);
                }
            }
            desligaEdicao();
        }
    }

    private void fillObj(Funcionario x, Boolean preenchePK) {
        if (preenchePK) {
            x.setFuncionarioId(Integer.parseInt(lblCodAluno.getText()));
        }

        if (cbUFAluno.getSelectionModel().getSelectedItem() != null) {
            jpaUF = new SiglaestadoJpaController();
            x.setNomeUFResid(jpaUF.getObjeto(cbUFAluno.getSelectionModel().getSelectedItem()));
        }

        if (cbCorPele.getSelectionModel().getSelectedItem() != null) {
            jpaCP = new CordapeleJpaController();
            x.setNomeCorDaPele(jpaCP.getObjeto(cbCorPele.getSelectionModel().getSelectedItem()));
        }

        if (cbEstadoCivil.getSelectionModel().getSelectedItem() != null) {
            jpaEC = new EstadocivilJpaController();
            x.setNomeEstadoCivil(jpaEC.getObjeto(cbEstadoCivil.getSelectionModel().getSelectedItem()));
        }

        if (cbUFNatu.getSelectionModel().getSelectedItem() != null) {
            jpaUF = new SiglaestadoJpaController();
            x.setNomeUFNasc(jpaUF.getObjeto(cbUFNatu.getSelectionModel().getSelectedItem()));
        }

        if (cbNacionalidade.getSelectionModel().getSelectedItem() != null) {
            jpaNA = new NacionalidadeJpaController();
            x.setNomeNacionalidade(jpaNA.getObjeto(cbNacionalidade.getSelectionModel().getSelectedItem()));
        }

        if (cbBancos.getSelectionModel().getSelectedItem() != null) {
            jpaBA = new TabbancosJpaController();
            x.setNomeBanco(jpaBA.getObjeto(cbBancos.getSelectionModel().getSelectedItem()));
        }

        x.setNome(edNome.getText());
        x.setEspecificidade(edEpec.getText());

        if (rbSexF.isSelected()) {
            Short sx = 1;
            x.setSexoId(sx);
        } else {
            Short sx = 2;
            x.setSexoId(sx);
        }

        if (chkAtivo.isSelected()) {
            x.setAtivo(true);
        } else {
            x.setAtivo(false);
        }

        x.setCEPResidencial(edCepAluno.getText());
        x.setEndereco(edEnderecoAluno.getText());
        x.setBairroResidencial(edBairroAluno.getText());
        x.setCidadeResidencial(edCidadeAluno.getText());

        x.setTelefone(edFoneRes.getText());
        x.setCelular(edFoneCel.getText());
        x.setEmail(edEmailAluno.getText());
        x.setConjuge(edConjuge.getText());

        if ((edDependentes.getText() == null) || (edDependentes.getText().trim() == "") || (edDependentes.getText().trim() == "0")) {
            edDependentes.setText("0");
            x.setnDependentes(0);
        } else {
            x.setnDependentes(convInt(edDependentes.getText().trim()));
        }

        x.setLocalNascimento(edNaturalidade.getText());
        if (dpNasc.getValue() != null) {
            x.setDataNascimento(Date.from(dpNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        x.setAgencia(edAgencia.getText());
        x.setContaCorrente(edConta.getText());
        x.setCpf(edCPF.getText());
        x.setFacebook(edFacebook.getText());
        x.setTwitter(edTwitter.getText());
        x.setInstagram(edInstagram.getText());

        x.setObservacoes(taObservacoes.getText());

    }

    public void preencheEdsComObj() {
        lblCodAluno.setText(reg_atual.getFuncionarioId().toString());
        edNome.setText(reg_atual.getNome());

        if (reg_atual.getSexoId() == 1) {
            rbSexF.setSelected(true);
        } else {
            rbSexM.setSelected(true);
        }

        if (reg_atual.getAtivo() != null) {
            chkAtivo.setSelected(true);
        } else {
            chkAtivo.setSelected(false);
        }

        // combos
        cbUFAluno.setDisable(true);
        cbEstadoCivil.setDisable(true);
        cbUFNatu.setDisable(true);
        cbNacionalidade.setDisable(true);
        cbCorPele.setDisable(true);
        
        cbUFAluno.getSelectionModel().select((reg_atual.getNomeUFResid() != null) ? reg_atual.getNomeUFResid().getEstado() : null);
        cbEstadoCivil.getSelectionModel().select((reg_atual.getNomeEstadoCivil() != null) ? reg_atual.getNomeEstadoCivil().getEstadoCivil() : null);
        cbUFNatu.getSelectionModel().select((reg_atual.getNomeUFNasc() != null) ? reg_atual.getNomeUFNasc().getEstado() : null);
        cbNacionalidade.getSelectionModel().select((reg_atual.getNomeNacionalidade() != null) ? reg_atual.getNomeNacionalidade().getNacionalidade() : null);
        cbCorPele.getSelectionModel().select((reg_atual.getNomeCorDaPele() != null) ? reg_atual.getNomeCorDaPele().getCordapele() : null);
        cbBancos.getSelectionModel().select((reg_atual.getNomeBanco() != null) ? reg_atual.getNomeBanco().getNomeBanco(): null);
        
        edCepAluno.setText((reg_atual.getCEPResidencial() != null) ? reg_atual.getCEPResidencial() : null);
        edEnderecoAluno.setText((reg_atual.getEndereco() != null) ? reg_atual.getEndereco() : null);
        edBairroAluno.setText((reg_atual.getBairroResidencial() != null) ? reg_atual.getBairroResidencial() : null);
        edCidadeAluno.setText((reg_atual.getCidadeResidencial() != null) ? reg_atual.getCidadeResidencial() : null);

        edFoneRes.setText((reg_atual.getTelefone() != null) ? reg_atual.getTelefone() : null);
        edFoneCel.setText((reg_atual.getCelular() != null) ? reg_atual.getCelular() : null);
        edEmailAluno.setText((reg_atual.getEmail() != null) ? reg_atual.getEmail() : null);

        edConjuge.setText((reg_atual.getConjuge() != null) ? reg_atual.getConjuge() : null);
        edDependentes.setText((reg_atual.getnDependentes() != null) ? reg_atual.getnDependentes().toString() : null);
        edNaturalidade.setText((reg_atual.getLocalNascimento() != null) ? reg_atual.getLocalNascimento() : null);
        dpNasc.setValue((reg_atual.getDataNascimento() != null) ? reg_atual.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);

        if (dpNasc.getValue() != null) {
            String idade = calcularIdade(Date.from(dpNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            idade = idade.substring(0, 2) + " anos e " + idade.substring(3, 5) + " mês(es)";
            lblIdade.setText(idade);
        }

        edCPF.setText((reg_atual.getCpf() != null) ? reg_atual.getCpf() : null);

    }

}
