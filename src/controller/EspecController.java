package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import jpa_controler.EspecificidadeJpaController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import static funcoes.MyFunc.*;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import jpa_controler.ProgramaJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class EspecController implements Initializable {

    @FXML
    private TextField txtCodPK;
    @FXML
    private JFXTextField edNomeProg;
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
    private TableView<Especificidade> tvPesquisa;
    @FXML
    private TableColumn<Especificidade, Integer> tc1;
    @FXML
    private TableColumn<Especificidade, String> tc2;
    @FXML
    private TableColumn<Especificidade, String> tc3;
    @FXML
    private TableColumn<Especificidade, Programa> tc4;
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
    private StackPane spStackPane;
    @FXML
    private JFXButton buApaga;

    // --------------------
    // VARIÁVEIS LOCAIS
    // --------------------
    private ObservableList<Especificidade> dados;
    private ObservableList<String> dadosPro;
    private EspecificidadeJpaController jpaCon;
    private ProgramaJpaController jpaCombo;

    Especificidade reg_atual = new Especificidade();
    private boolean inserindo, editando;

    private TextField txtIndex;
    private TextField txtItem;
    private TextField txtValue;
    @FXML
    private ComboBox<String> cbProg;
    @FXML
    private JFXTextArea edDescricao;
    @FXML
    private Tab tabEdicao;
    @FXML
    private JFXButton buPesquisa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jpaCon = new EspecificidadeJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO

        inserindo = false;
        editando = false;
        desligaEdicao();
        initColumns();

        // Inicializa ComboBox do Índice de Pesquisa
        cbIndice.getItems().addAll("Código", "Especificidade", "Descrição", "Programa", "Titulação", "Grau");
        cbIndice.getSelectionModel().selectFirst();
        cbCondicao.getItems().addAll("igual a", "diferente de", "maior ou igual a", "maior que", "menor ou igual a", "menor que", "começa com", "termina com", "contém");
        cbCondicao.getSelectionModel().selectFirst();

        // CARREGA COMBOBOX
        cbProg.setItems(getNomeProgramas());
        new ComboBoxAutoComplete<String>(cbProg, "");

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
        tc1.setCellValueFactory(new PropertyValueFactory<>("especificidadeId"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("especificidade"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("programaEspec"));
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
    }

    public ObservableList<Especificidade> getMeusDados() {

        dados = FXCollections.observableArrayList(jpaCon.getEspecificidades());
        txtPesq.setText("");

        int rowcount = jpaCon.getEspecificidadeCount();
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

    public ObservableList<Especificidade> getPesquisa() {
        jpaCon = new EspecificidadeJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findEspecificidadeEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getEspecificidadeCount();
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
            warn = warn + "O campo <Nome do Especificidade> não pode ficar vazio.\n";
            cte++;
        }

        if (edDescricao.getText().equals("")) {
            warn = warn + "O campo <Descrição> não pode ficar vazio.\n";
            cte++;
        }

        //COMBO????????????????
        // se for inserção, verificar se Nome do Especificidade já existe
        if (cte > 0) {
            // há erros
            mostraMsg("Há erro(s) de preenchimento.", warn, 2);
        } else {

            if (verifPodeGravar(edNomeProg.getText()) == false) {
                mostraMsg("Não é possível gravar!", "Já existe outro registro com o mesmo <nome de especificidade>.", 2);
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
                            mostraMsg("Erro ao gravar a edição dos dados.", ""+e, 2);
                        }
                    } else {
                        if (inserindo == true) {
                            try {

                                // PREENCHE OBd COM Eds
                                Especificidade obj = new Especificidade();
                                obj.setEspecificidade(edNomeProg.getText());
                                obj.setDescricao(edDescricao.getText());

                                Programa progSelec = new Programa();
                                ProgramaJpaController jpa_p = new ProgramaJpaController();
                                progSelec = jpa_p.getProgramasPesqUmExac(cbProg.getSelectionModel().getSelectedItem());
                                obj.setProgramaEspec(progSelec);
                                
                                jpaCon.create(obj);
                                reg_atual = obj;
                                txtCodPK.setText("Especificidade nº " + reg_atual.getEspecificidadeId());
                                inserindo = false;

                                desligaEdicao();
                                initColumns();
                                // PREENCHE OBd COM Eds
                            } catch (Exception e) {
                                System.out.println(reg_atual);
                                System.out.println("\n============================");
                                System.out.println(e);
                                System.out.println("============================\n");
                                mostraMsg("Erro ao gravar novos dados.", ""+e, 2);
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

        edNomeProg.setEditable(true);
        edDescricao.setEditable(true);
        cbProg.setDisable(false);
    }

    public void desligaEdicao() {

        // habilita botões para inserção e deleção
        buInsere.setDisable(false);
        buEdita.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buApaga.setDisable(false);
        tabPesquisa.setDisable(false);

        edNomeProg.setEditable(false);
        edDescricao.setEditable(false);
        cbProg.setDisable(true);
        cbProg.setStyle("-fx-opacity: 1;");
    }

    public void abreRegSelected() {
        reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();
        preencheEdsComObj();

        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(0); //select by index starting with 0
    }

    public void preencheEdsComObj() {
        txtCodPK.setText("Especificidade nº " + reg_atual.getEspecificidadeId());
        edNomeProg.setText(reg_atual.getEspecificidade());
        edDescricao.setText(reg_atual.getDescricao());

        // COLOCAR NO COMBOBOX O VALOR SELECIONADO
        cbProg.getSelectionModel().select(reg_atual.getProgramaEspec().getPrograma());

    }

    public void preencheObjComEds() {
        reg_atual.setEspecificidade(edNomeProg.getText());
        reg_atual.setDescricao(edDescricao.getText());

        // TUDO ISSO PARA JOGAR O PROGRAMA SELECIONADO NO COMBO EM REG_ATUAL 
        Programa progSelec = new Programa();
        ProgramaJpaController jpa_p = new ProgramaJpaController();
        progSelec = jpa_p.getProgramasPesqUmExac(cbProg.getSelectionModel().getSelectedItem());
        reg_atual.setProgramaEspec(progSelec);

    }

    public void limpaCampos() {
        txtCodPK.setText("Especificidade nº ");
        edDescricao.setText("");
        cbProg.setValue(null);
        edNomeProg.setText("");
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
        if (!edNomeProg.getText().equals("")) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirmação - apagar registro " + reg_atual.getEspecificidadeId());
            alert.setContentText("Deseja realmente apagar esse registro?\nOs dados serão perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaCon.destroy(reg_atual.getEspecificidadeId());

                    desligaEdicao();
                    limpaCampos();
                    initColumns();
                    mostraMsg("Dados apagados com sucesso", "", 3);
                    // PREENCHE OBd COM Eds

                } catch (Exception e) {
                    mostraMsg("Erro ao apagar registro", ""+e, 2);

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

    
    public ObservableList<Especificidade> getMinhaPesqDados(String pesq) {

        jpaCon = new EspecificidadeJpaController();
        dados = FXCollections.observableArrayList(jpaCon.getEspecificidadesPesq(pesq));
//        txtPesq.setText("");
        lblRegistros.setText("");

        Long rowcount = getQuantosNomes("Especificidade", "especificidade", pesq);
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
        jpaCon = new EspecificidadeJpaController();
        List lis = jpaCon.getEspecificidadesPesqExac(pesq);

        Especificidade prog;
        if (editando == true) {

            for (int i = 0; i < lis.size(); i++) {
                prog = (Especificidade) lis.get(i);
                if (!prog.getEspecificidadeId().equals(reg_atual.getEspecificidadeId())) {
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

}
