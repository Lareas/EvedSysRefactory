package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTabPane;
import static controller.MenuPrincipalController.stgCurso;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Curso;
import entities.Especificidade;
import entities.Programa;
import funcoes.ComboBoxAutoComplete;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jpa_controler.CursoJpaController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import static funcoes.MyFunc.*;
import funcoes.TextFieldFormatter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import jpa_controler.EspecificidadeJpaController;
import jpa_controler.ProgramaJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class CursosController implements Initializable {

    @FXML
    private TextField txtCodPK;
    @FXML
    private TextField edNomeProg;
    @FXML
    private JFXButton buInsere;
    @FXML
    private JFXButton buEdita;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private JFXTabPane tabFX;
    @FXML
    private Tab tabPesquisa;
    @FXML
    private TableView<Curso> tvPesquisa;
    @FXML
    private TableColumn<Curso, Integer> tc1;
    @FXML
    private TableColumn<Curso, String> tcSigla;
    @FXML
    private TableColumn<Curso, String> tc2;
    @FXML
    private TableColumn<Curso, String> tc3;
    @FXML
    private TableColumn<Curso, Programa> tc4;
    @FXML
    private TableColumn<Curso, String> tc5;
    @FXML
    private TableColumn<Curso, String> tc6;
    @FXML
    private JFXComboBox<String> cbIndice;
    @FXML
    private JFXComboBox<String> cbCondicao;
    @FXML
    private JFXTextField txtPesq;
    @FXML
    private JFXButton buLimpa;
    @FXML
    private JFXButton buAbre;
    @FXML
    private FontAwesomeIconView buAbreRec;
    @FXML
    private TextField lblRegistros;
    @FXML
    private JFXButton buApaga;
    @FXML
    private TextField edTit;
    @FXML
    private ComboBox<String> cbProg;
    @FXML
    private TextField edGrau;
    @FXML
    private TextArea edDescricao;
    @FXML
    private Tab tabEdicao;
    @FXML
    private JFXButton buPesquisa;
    @FXML
    private JFXButton buInsere1;
    @FXML
    private TextField edDocFichaInsc;
    @FXML
    private TextField edFormMatri;
    @FXML
    private TextField edDocDiploma;
    @FXML
    private TextField edHistorico;
    @FXML
    private TextField edDocCarta1;
    @FXML
    private TextField edDocCarta2;
    @FXML
    private TextField edDocRG;
    @FXML
    private TextField edDocCPF;
    @FXML
    private TextField edDocFoto;
    @FXML
    private TextField edDocTimbrada;
    @FXML
    private TextField edDocTestemunho;
    @FXML
    private TextField edCurricMin;
    @FXML
    private TextField edDocDissertacaoTCC;
    @FXML
    private TextField edDocEsbocoProjMin;
    @FXML
    private TextField edDocExameDmin;
    @FXML
    private TextField edDocDeclarConhecIgreja;
    @FXML
    private TextField edNomeArquivo;
    @FXML
    private TextField edNomePasta;
    @FXML
    private JFXButton buPegaArq;
    @FXML
    private JFXButton buPegaPasta;
    @FXML
    private TextArea taFiles;
    @FXML
    private JFXButton buCopia;
    @FXML
    private TextField edSigla;

    @FXML
    private ComboBox<String> cbEspec;
    @FXML
    private TextField edDuracao;

    // --------------------
    // VARIÁVEIS LOCAIS
    // --------------------
    private ObservableList<Curso> dados;
    private ObservableList<String> dadosPro;
    private ObservableList<String> dadosEs;
    private CursoJpaController jpaCon;
    private EspecificidadeJpaController jpaEs;
    private ProgramaJpaController jpaCombo;

    Curso reg_atual = new Curso();
    private boolean inserindo, editando;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jpaCon = new CursoJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO
        jpaEs = new EspecificidadeJpaController();

        inserindo = false;
        editando = false;
        desligaEdicao();
        initColumns();

        // Inicializa ComboBox do Índice de Pesquisa
        cbIndice.getItems().addAll("Código", "Programa", "Descrição", "Nível", "Titulação", "Grau");
        cbIndice.getSelectionModel().selectFirst();
        cbCondicao.getItems().addAll("igual a", "diferente de", "maior ou igual a", "maior que", "menor ou igual a", "menor que", "começa com", "termina com", "contém");
        cbCondicao.getSelectionModel().selectFirst();

        // CARREGA COMBOBOX
        cbProg.setItems(getNomeProgramas());
        new ComboBoxAutoComplete<String>(cbProg, "");

        cbEspec.setItems(getNomeEspecs());
        new ComboBoxAutoComplete<String>(cbEspec, "");

        // só para usuário com permissão
        buApaga.setVisible(true);
        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(1); //select by index starting with 0

//        cbProg.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//           
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                if (buConfirma.isDisable()) {
//                    newValue = oldValue;
//                }
//            }
//        });
    }

    public void initColumns() {
        tc1.setCellValueFactory(new PropertyValueFactory<>("cursoId"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tcSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("programaEspec"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("titulacao"));
        tc6.setCellValueFactory(new PropertyValueFactory<>("grau"));
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
    }

    public ObservableList<Curso> getMeusDados() {

        dados = FXCollections.observableArrayList(jpaCon.getCursos());
        txtPesq.setText("");

        int rowcount = jpaCon.getCursoCount();
        if (rowcount > 1) {
            lblRegistros.setText(" " + rowcount + " registros");
        } else {
            lblRegistros.setText(" " + rowcount + " registro");
        }

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }
    }

    public ObservableList<String> getNomeProgramas() {

        jpaCombo = new ProgramaJpaController();
        dadosPro = FXCollections.observableArrayList(jpaCombo.getNomeDosProgramas());

        if (dadosPro == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosPro;
        }
    }

    public ObservableList<String> getNomeEspecs() {

        jpaEs = new EspecificidadeJpaController();
        dadosEs = FXCollections.observableArrayList(jpaEs.getNomeEsp());

        if (dadosEs == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosEs;
        }
    }

    public ObservableList<Curso> getPesquisa() {
        jpaCon = new CursoJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findCursoEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getCursoCount();
        if (rowcount > 1) {
            lblRegistros.setText(" " + rowcount + " registros");
        } else {
            lblRegistros.setText(" " + rowcount + " registro");
        }

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }
    }

    @FXML
    private void clicouInsere(ActionEvent event) {
        reg_atual = null;
        limpaCampos(); // para quem vai requestfocus;
        ligaEdicao();
        inserindo = true;
        edNomeProg.requestFocus();
    }

    @FXML
    private void clicouEdita(ActionEvent event) {
        if (!edNomeProg.getText().equals("")) {
            ligaEdicao();
            editando = true;
        }
    }

    @FXML
    private void clicouCancela(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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

    @FXML
    private void clicouConfirma(ActionEvent event) {
        int cte = 0;
        String warn = "";
        // CONSISTÊNCIA DOS CAMPOS
        if (edNomeProg.getText().equals("")) {
            warn = warn + "O campo <Nome do Programa> não pode ficar vazio.\n";
            cte++;
        }

        if (edDescricao.getText().equals("")) {
            warn = warn + "O campo <Descrição> não pode ficar vazio.\n";
            cte++;
        }

        //COMBO????????????????
        // se for inserção, verificar se Nome do Curso já existe
        if (cte > 0) {
            // há erros
            mostraMsg("Há erro(s) de preenchimento.", warn, 2);
        } else {

            if (verifPodeGravar(edNomeProg.getText()) == false) {
                mostraMsg("Não é possível gravar!", "Já existe outro registro com o mesmo <nome do programa>.", 2);
            } else {

                // PODE GRAVAR!
                Alert alert = new Alert(AlertType.CONFIRMATION);
//                alert.setHeaderText("cbProg.getSelectionModel().toString(): " + cbProg.getSelectionModel().toString() + "\n" +
//                                       "cbProg.getSelectionModel().getSelectedIndex(): " + cbProg.getSelectionModel().getSelectedIndex() + "\n" + 
//                                       "cbProg.getSelectionModel().getSelectedItem(): " + cbProg.getSelectionModel().getSelectedItem()+ "\n"); 

                alert.setHeaderText("Confirma");
                alert.setContentText("Confirma a gravação dos dados?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {

                    if (editando == true) {
                        try {

                            preencheObjComEds(); // PREENCHE OBd COM Eds
                            jpaCon.edit(reg_atual);
                            editando = false;

                            desligaEdicao();
                            initColumns();

                        } catch (Exception e) {
                            System.out.println(reg_atual);
                            System.out.println("\n============================");
                            System.out.println(e);
                            System.out.println("============================\n");
                            mostraMsg("Erro ao gravar a edição dos dados.", "" + e, 2);
                        }
                    } else {
                        if (inserindo == true) {
                            try {

                                // PREENCHE OBd COM Eds
                                Curso obj = new Curso();
                                obj.setCurso(edNomeProg.getText());
                                obj.setSigla(edSigla.getText());
                                obj.setTitulacao(edTit.getText());
                                obj.setGrau(edGrau.getText());
                                obj.setDescricao(edDescricao.getText());
                                obj.setDuracao(convInt(edDuracao.getText()));

                                obj.setDocFichaInsc(edDocFichaInsc.getText());
                                obj.setFormMatri(edFormMatri.getText());
                                obj.setDocDiploma(edDocDiploma.getText());
                                obj.setHistorico(edHistorico.getText());
                                obj.setDocCarta1(edDocCarta1.getText());
                                obj.setDocCarta2(edDocCarta2.getText());
                                obj.setDocRG(edDocRG.getText());
                                obj.setDocCPF(edDocCPF.getText());
                                obj.setDocFoto(edDocFoto.getText());
                                obj.setDocTimbrada(edDocTimbrada.getText());
                                obj.setDocTestemunho(edDocTestemunho.getText());
                                obj.setCurricMin(edCurricMin.getText());
                                obj.setDocDissertacaoTCC(edDocDissertacaoTCC.getText());
                                obj.setDocEsbocoProjMin(edDocEsbocoProjMin.getText());
                                obj.setDocExameDmin(edDocExameDmin.getText());
                                obj.setDocDeclarConhecIgreja(edDocDeclarConhecIgreja.getText());
                                obj.setNomeArq(edNomeArquivo.getText());
                                obj.setCaminhoArq(edNomePasta.getText());

                                Programa progSelec = new Programa();
                                ProgramaJpaController jpa_p = new ProgramaJpaController();
                                progSelec = jpa_p.getProgramasPesqUmExac(cbProg.getSelectionModel().getSelectedItem());
                                obj.setProgramaEspec(progSelec);

                                Especificidade especi = new Especificidade();
                                EspecificidadeJpaController jpa_e = new EspecificidadeJpaController();
                                especi = jpa_e.getObjeto(cbProg.getSelectionModel().getSelectedItem());
                                obj.setNomeEspecificidade(especi);

                                jpaCon.create(obj);
                                reg_atual = obj;
                                txtCodPK.setText("Programa nº " + reg_atual.getCursoId());
                                inserindo = false;

                                desligaEdicao();
                                initColumns();
                                // PREENCHE OBd COM Eds
                            } catch (Exception e) {
                                System.out.println(reg_atual);
                                System.out.println("\n============================");
                                System.out.println(e);
                                System.out.println("============================\n");
                                mostraMsg("Erro ao gravar novos dados.", "" + e, 2);
                            }
                        } else {
                            mostraMsg("Mensagem DN2", "Mensagem DN-YANot - 012", 2);
                        }
                    }

                }

            }

        }

    }

    private void clicouPesquisa(ActionEvent event) {
        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(1); //select by index starting with 0
    }

    @FXML
    private void clicouLimpa(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmação");
        alert.setContentText("Deseja limpar a pesquisa?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            tvPesquisa.setItems(getMeusDados());
            tvPesquisa.refresh();
        }
    }

    @FXML
    private void clicouAbre(ActionEvent event) {
        if (tvPesquisa.getSelectionModel().getSelectedItem() != null) {
            abreRegSelected();
        }
    }

    public void ligaEdicao() {

        // habilita botões para inserção e deleção
        buInsere.setDisable(true);
        buEdita.setDisable(true);
        buConfirma.setDisable(false);
        buCancela.setDisable(false);
        buApaga.setDisable(true);
        tabPesquisa.setDisable(true);
        buPegaArq.setDisable(false);

        edNomeProg.setEditable(true);
        edSigla.setEditable(true);
        edTit.setEditable(true);
        edGrau.setEditable(true);
        edDescricao.setEditable(true);
//        cbProg.setEditable(true);
        cbProg.setDisable(false);
        cbEspec.setDisable(false);

        edDocFichaInsc.setEditable(true);
        edFormMatri.setEditable(true);
        edDocDiploma.setEditable(true);
        edHistorico.setEditable(true);
        edDocCarta1.setEditable(true);
        edDocCarta2.setEditable(true);
        edDocRG.setEditable(true);
        edDocCPF.setEditable(true);
        edDocFoto.setEditable(true);
        edDocTimbrada.setEditable(true);
        edDocTestemunho.setEditable(true);
        edCurricMin.setEditable(true);
        edDocDissertacaoTCC.setEditable(true);
        edDocEsbocoProjMin.setEditable(true);
        edDocExameDmin.setEditable(true);
        edDocDeclarConhecIgreja.setEditable(true);
        edNomeArquivo.setEditable(true);
        edNomePasta.setEditable(true);
        edDuracao.setEditable(true);
    }

    public void desligaEdicao() {

        // habilita botões para inserção e deleção
        buInsere.setDisable(false);
        buEdita.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buApaga.setDisable(false);
        tabPesquisa.setDisable(false);
        buPegaArq.setDisable(true);

        edNomeProg.setEditable(false);
        edSigla.setEditable(false);
        edTit.setEditable(false);
        edGrau.setEditable(false);
        edDescricao.setEditable(false);
//        cbProg.setEditable(false);
        cbProg.setDisable(true);
        cbProg.setStyle("-fx-opacity: 1;");

        cbEspec.setDisable(true);
        cbEspec.setStyle("-fx-opacity: 1;");

        edDocFichaInsc.setEditable(false);
        edFormMatri.setEditable(false);
        edDocDiploma.setEditable(false);
        edHistorico.setEditable(false);
        edDocCarta1.setEditable(false);
        edDocCarta2.setEditable(false);
        edDocRG.setEditable(false);
        edDocCPF.setEditable(false);
        edDocFoto.setEditable(false);
        edDocTimbrada.setEditable(false);
        edDocTestemunho.setEditable(false);
        edCurricMin.setEditable(false);
        edDocDissertacaoTCC.setEditable(false);
        edDocEsbocoProjMin.setEditable(false);
        edDocExameDmin.setEditable(false);
        edDocDeclarConhecIgreja.setEditable(false);
        edNomeArquivo.setEditable(false);
        edNomePasta.setEditable(false);
        edDuracao.setEditable(false);
    }

    public void abreRegSelected() {
        reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();
        preencheEdsComObj();

        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(0); //select by index starting with 0
    }

    public void preencheEdsComObj() {
        txtCodPK.setText("Programa nº " + reg_atual.getCursoId());
        edNomeProg.setText(reg_atual.getCurso());
        edSigla.setText(reg_atual.getSigla());
        edTit.setText(reg_atual.getTitulacao());
        edGrau.setText(reg_atual.getGrau());
        edDescricao.setText(reg_atual.getDescricao());

        // COLOCAR NO COMBOBOX O VALOR SELECIONADO
        cbProg.getSelectionModel().select((reg_atual.getProgramaEspec() != null) ? reg_atual.getProgramaEspec().getPrograma() : null);
        cbEspec.getSelectionModel().select((reg_atual.getNomeEspecificidade() != null) ? reg_atual.getNomeEspecificidade().getEspecificidade() : null);

        edDocFichaInsc.setText(reg_atual.getDocFichaInsc());
        edFormMatri.setText(reg_atual.getFormMatri());
        edDocDiploma.setText(reg_atual.getDocDiploma());
        edHistorico.setText(reg_atual.getHistorico());
        edDocCarta1.setText(reg_atual.getDocCarta1());
        edDocCarta2.setText(reg_atual.getDocCarta2());
        edDocRG.setText(reg_atual.getDocRG());
        edDocCPF.setText(reg_atual.getDocCPF());
        edDocFoto.setText(reg_atual.getDocFoto());
        edDocTimbrada.setText(reg_atual.getDocTimbrada());
        edDocTestemunho.setText(reg_atual.getDocTestemunho());
        edCurricMin.setText(reg_atual.getCurricMin());
        edDocDissertacaoTCC.setText(reg_atual.getDocDissertacaoTCC());
        edDocEsbocoProjMin.setText(reg_atual.getDocEsbocoProjMin());
        edDocExameDmin.setText(reg_atual.getDocExameDmin());
        edDocDeclarConhecIgreja.setText(reg_atual.getDocDeclarConhecIgreja());
        edNomeArquivo.setText(reg_atual.getNomeArq());
        edNomePasta.setText(reg_atual.getCaminhoArq());
        edDuracao.setText(String.valueOf(reg_atual.getDuracao()));
    }

    public void preencheObjComEds() {
        reg_atual.setCurso(edNomeProg.getText());
        reg_atual.setSigla(edSigla.getText());
        reg_atual.setTitulacao(edTit.getText());
        reg_atual.setDescricao(edDescricao.getText());
        reg_atual.setGrau(edGrau.getText());

        // TUDO ISSO PARA JOGAR O PROGRAMA SELECIONADO NO COMBO EM REG_ATUAL 
        Programa progSelec = new Programa();
        ProgramaJpaController jpa_p = new ProgramaJpaController();
        progSelec = jpa_p.getProgramasPesqUmExac(cbProg.getSelectionModel().getSelectedItem());
        reg_atual.setProgramaEspec(progSelec);

        Especificidade especi = new Especificidade();
        EspecificidadeJpaController jpa_e = new EspecificidadeJpaController();
        especi = jpa_e.getObjeto(cbEspec.getSelectionModel().getSelectedItem());
        reg_atual.setNomeEspecificidade(especi);

        reg_atual.setDocFichaInsc(edDocFichaInsc.getText());
        reg_atual.setFormMatri(edFormMatri.getText());
        reg_atual.setDocDiploma(edDocDiploma.getText());
        reg_atual.setHistorico(edHistorico.getText());
        reg_atual.setDocCarta1(edDocCarta1.getText());
        reg_atual.setDocCarta2(edDocCarta2.getText());
        reg_atual.setDocRG(edDocRG.getText());
        reg_atual.setDocCPF(edDocCPF.getText());
        reg_atual.setDocFoto(edDocFoto.getText());
        reg_atual.setDocTimbrada(edDocTimbrada.getText());
        reg_atual.setDocTestemunho(edDocTestemunho.getText());
        reg_atual.setCurricMin(edCurricMin.getText());
        reg_atual.setDocDissertacaoTCC(edDocDissertacaoTCC.getText());
        reg_atual.setDocEsbocoProjMin(edDocEsbocoProjMin.getText());
        reg_atual.setDocExameDmin(edDocExameDmin.getText());
        reg_atual.setDocDeclarConhecIgreja(edDocDeclarConhecIgreja.getText());
        reg_atual.setNomeArq(edNomeArquivo.getText());
        reg_atual.setCaminhoArq(edNomePasta.getText());
        reg_atual.setDuracao(convInt(edDuracao.getText()));

    }

    public void limpaCampos() {
        txtCodPK.setText("Programa nº ");
        edTit.setText("");
        edGrau.setText("");
        edDescricao.setText("");
        cbProg.setValue(null);
        cbEspec.setValue(null);
        edNomeProg.setText("");
        edSigla.setText("");

        edDocFichaInsc.setText("");
        edFormMatri.setText("");
        edDocDiploma.setText("");
        edHistorico.setText("");
        edDocCarta1.setText("");
        edDocCarta2.setText("");
        edDocRG.setText("");
        edDocCPF.setText("");
        edDocFoto.setText("");
        edDocTimbrada.setText("");
        edDocTestemunho.setText("");
        edCurricMin.setText("");
        edDocDissertacaoTCC.setText("");
        edDocEsbocoProjMin.setText("");
        edDocExameDmin.setText("");
        edDocDeclarConhecIgreja.setText("");
        edNomeArquivo.setText("");
        edNomePasta.setText("");
        edDuracao.setText("");
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
        if (!edNomeProg.getText().equals("")) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirmação - apagar registro " + reg_atual.getCursoId());
            alert.setContentText("Deseja realmente apagar esse registro?\nOs dados serão perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaCon.destroy(reg_atual.getCursoId());

                    desligaEdicao();
                    limpaCampos();
                    initColumns();
                    mostraMsg("Dados apagados com sucesso", "", 3);
                    // PREENCHE OBd COM Eds

                } catch (Exception e) {
                    mostraMsg("Erro ao apagar registro", "" + e, 2);

                }
            }
        }
    }

    @FXML
    private void soltouComboCampos(MouseEvent event) {
//        txtIndex.setText(String.valueOf(cbIndice.getSelectionModel().getSelectedIndex()));
//        txtItem.setText(cbIndice.getSelectionModel().getSelectedItem());
//        txtValue.setText(cbIndice.getValue());
//        cbIndice.getSelectionModel().getSelectedIndex();
    }

    @FXML
    private void clicouMinhaPesq(ActionEvent event) {
        String conteudo = txtPesq.getText().trim();

        if (conteudo.length() < 1) {
            mostraMsg("Pesquisa", "Informe o conteúdo a pesquisar", 3);
            txtPesq.requestFocus();

        } else {
            tvPesquisa.setItems(getMinhaPesqDados(conteudo));
        }
    }

    public ObservableList<Curso> getMinhaPesqDados(String pesq) {

        jpaCon = new CursoJpaController();
        dados = FXCollections.observableArrayList(jpaCon.getCursosPesq(pesq));
//        txtPesq.setText("");
        lblRegistros.setText("");

        Long rowcount = getQuantosNomes("Curso", "curso", pesq);
        if (rowcount > 1) {
            lblRegistros.setText(" " + rowcount + " registros");
        } else {
            lblRegistros.setText(" " + rowcount + " registro");
        }

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }
    }

    @FXML
    private void clicouMinhaPesqEnter(KeyEvent event) {
        if (tabFX.getSelectionModel().getSelectedIndex() == 1) {
            String conteudo = txtPesq.getText().trim();
            if (conteudo.length() < 1) {
                mostraMsg("Pesquisa", "Informe o conteúdo a pesquisar", 3);
                txtPesq.requestFocus();
            } else {
                tvPesquisa.setItems(getMinhaPesqDados(conteudo));
            }
        }
    }

    private Boolean verifPodeGravar(String pesq) {
        Boolean podegravar = true;

        Long achados = 0L;
        jpaCon = new CursoJpaController();
        List lis = jpaCon.getCursosPesqExac(pesq);

        Curso prog;
        if (editando == true) {

            for (int i = 0; i < lis.size(); i++) {
                prog = (Curso) lis.get(i);
                if (!prog.getCursoId().equals(reg_atual.getCursoId())) {
                    podegravar = false;
                }
            }
        } else {
            if (inserindo == true) {
                if (lis.size() > 0) {
                    podegravar = false;
                }
            }
        }

        return podegravar;
    }

    @FXML
    private void clicouCombo(MouseEvent event) {

    }

    @FXML
    private void cliTTFFicha(KeyEvent event) {
        chamaTTF(edDocFichaInsc);
    }

    @FXML
    private void cliTTFForm(KeyEvent event) {
        chamaTTF(edFormMatri);
    }

    @FXML
    private void cliTTFDiplo(KeyEvent event) {
        chamaTTF(edDocDiploma);
    }

    @FXML
    private void cliTTFHist(KeyEvent event) {
        chamaTTF(edHistorico);
    }

    @FXML
    private void cliTTFCarta1(KeyEvent event) {
        chamaTTF(edDocCarta1);
    }

    @FXML
    private void cliTTFCarta2(KeyEvent event) {
        chamaTTF(edDocCarta2);
    }

    @FXML
    private void cliTTFRG(KeyEvent event) {
        chamaTTF(edDocRG);
    }

    @FXML
    private void cliTTFCPF(KeyEvent event) {
        chamaTTF(edDocCPF);
    }

    @FXML
    private void cliTTFFioto(KeyEvent event) {
        chamaTTF(edDocFoto);
    }

    @FXML
    private void cliTTFTimbra(KeyEvent event) {
        chamaTTF(edDocTimbrada);
    }

    @FXML
    private void cliTTFTeste(KeyEvent event) {
        chamaTTF(edDocTestemunho);
    }

    @FXML
    private void cliTTFCurr(KeyEvent event) {
        chamaTTF(edCurricMin);
    }

    @FXML
    private void cliTTFDiss(KeyEvent event) {
        chamaTTF(edDocDissertacaoTCC);
    }

    @FXML
    private void cliTTFEsb(KeyEvent event) {
        chamaTTF(edDocEsbocoProjMin);
    }

    @FXML
    private void cliTTFExame(KeyEvent event) {
        chamaTTF(edDocExameDmin);
    }

    @FXML
    private void cliTTFDeclaIg(KeyEvent event) {
        chamaTTF(edDocDeclarConhecIgreja);
    }

    private void chamaTTF(TextField edTTF) {
        if ((inserindo) || (editando)) {
            if (!edTTF.equals(null)) {
                if (edTTF.getText() != null) {
                    if (edTTF.getText().trim().length() > 0) {
                        if (!edTTF.getText().equals("")) {
                            TextFieldFormatter tff = new TextFieldFormatter();
                            tff.formatter(edTTF, "SN", "A");
                        }
                    }
                }
            }

        }
    }

    @FXML
    private void clicouPegaArq(ActionEvent event) throws IOException {
        //  FileChooser fileChooser = new FileChooser();
        //  fileChooser.setInitialDirectory(new File(edNomePasta.getText()));
        //  File selectedFile = fileChooser.showOpenDialog(stgCurso);
//        edNomeArquivo.setText(selectedFile.getName());
//        mostraMsg("Nome do Arquivo: " + selectedFile.getName()
//                + "\nNome da Pasta: " + selectedFile.getPath()
//                + "\ngetAbsolutePath: " + selectedFile.getAbsolutePath()
//                + "\ngetCanonicalPath: " + selectedFile.getCanonicalPath()
//                + "\ngetParent: " + selectedFile.getParent(), "", 0);

        File file1, file2;
        FileChooser fileChooser = new FileChooser();
        List<File> list = fileChooser.showOpenMultipleDialog(stgCurso);
        String txt = "Arquivos selecionados\n";
//            }
        for (int i = 0; i < list.size(); i++) {
            mostraMsg("Nome do Arquivo: " + list.get(i).getName()
                    + "\nNome da Pasta: " + list.get(i).getPath()
                    + "\ngetAbsolutePath: " + list.get(i).getAbsolutePath()
                    + "\ngetCanonicalPath: " + list.get(i).getCanonicalPath()
                    + "\ngetParent: " + list.get(i).getParent(), "", 0);
            txt = txt + list.get(i) + "\n";
        }
        taFiles.setText(txt);

    }

    @FXML
    private void clicouPegaPasta(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stgCurso);

        if (selectedDirectory == null) {
            //No Directory selected
        } else {
            edNomePasta.setText(selectedDirectory.getAbsolutePath());
            System.out.println(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void clicouCopia(ActionEvent event) {
        String secretCase = "\\\\192.168.0.19\\esphoto";

//        Path sourceDirectory = Paths.get("C:/temp5/ADILSON PRAGANA TESTE.xlsx");
//        Path targetDirectory = Paths.get("C:/temp8/ADILSON PRAGANA TESTE_00148.xlsx");
        String pastaAluno = "ADILSON PRAGANA_2616";

        String pastaAbs = secretCase + "\\" + pastaAluno;

        // se necessário, cria pasta
        File file = new File(pastaAbs);
        if (!file.exists()) {
            try {
                file.mkdir();
                System.out.println("pasta <" + pastaAbs + "> criada!");
            } catch (Exception e) {
                System.out.println("Não foi possível criar a pasta! --> " + e);
            }
        }

//        try {
//            Files.copy(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);
//            mostraMsg("Arquivo copiado com sucesso", "", 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(e);
//        }
    }

}
