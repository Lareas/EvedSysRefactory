/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Profissoes;
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
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import static funcoes.MyFunc.*;
import java.util.List;
import javafx.scene.control.Labeled;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableRow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import jpa_controler.ProfissoesJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class ProfissaoController implements Initializable {

    @FXML
    private TextField txtCodPK;
    @FXML
    private JFXButton buInsere;
    @FXML
    private JFXButton buEdita;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private JFXButton buPesquisa;

    @FXML
    private JFXTabPane tabFX;
    @FXML
    private Tab tabEdicao, tabPesquisa;

    @FXML
    private TableView<Profissoes> tvPesquisa;
    @FXML
    private TableColumn<Profissoes, Integer> tc1;
    @FXML
    private TableColumn<Profissoes, String> tc2;
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
    private ObservableList<Profissoes> dados;
    private ProfissoesJpaController jpaCon;
    Profissoes reg_atual = new Profissoes();
    private boolean inserindo, editando;

    private TextField txtIndex;
    private TextField txtItem;
    private TextField txtValue;
    @FXML
    private JFXTextField edNomeProg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jpaCon = new ProfissoesJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO

        inserindo = false;
        editando = false;
        desligaEdicao();
        initColumns();

        // Inicializa ComboBox do Índice de Pesquisa
        cbIndice.getItems().addAll("Código", "Profissão");
        cbIndice.getSelectionModel().selectFirst();
        cbCondicao.getItems().addAll("igual a", "diferente de", "maior ou igual a", "maior que", "menor ou igual a", "menor que", "começa com", "termina com", "contém");
        cbCondicao.getSelectionModel().selectFirst();

        // só para usuário com permissão
        buApaga.setVisible(true);
        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(1); //select by index starting with 0

        tvPesquisa.setRowFactory(row -> new TableRow<Profissoes>() {
            @Override
            public void updateItem(Profissoes item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else {
                    //Now 'item' has all the info of the Person in this row
                    if (item.getNomeProfissao().contains("er")) {
                        //We apply now the changes in all the cells of the row
                        for (int i = 0; i < getChildren().size(); i++) {
                            ((Labeled) getChildren().get(i)).setTextFill(Color.RED);
                            ((Labeled) getChildren().get(i)).setStyle("-fx-background-color: yellow");
                        }
                    } else {
                        if (getTableView().getSelectionModel().getSelectedItems().contains(item)) {
                            for (int i = 0; i < getChildren().size(); i++) {
                                ((Labeled) getChildren().get(i)).setTextFill(Color.WHITE);;
                            }
                        } else {
                            for (int i = 0; i < getChildren().size(); i++) {
                                ((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);;
                            }
                        }
                    }
                }
            }
        });

        tvPesquisa.setOnSort((event) -> {
            System.out.println("Sorting items");
        });
    }

    public void initColumns() {
        tc1.setCellValueFactory(new PropertyValueFactory<>("codProfissao"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("nomeProfissao"));
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
    }

    public ObservableList<Profissoes> getMeusDados() {

        jpaCon = new ProfissoesJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findProfissoesEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getProfissoesCount();
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

    public ObservableList<Profissoes> getMinhaPesqDados(String pesq) {

        jpaCon = new ProfissoesJpaController();
        dados = FXCollections.observableArrayList(jpaCon.getProfissoesPesq(pesq));
        lblRegistros.setText("");

        Long rowcount = getQuantosNomes("Profissoes", "nomeProfissao", pesq);

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

    public ObservableList<Profissoes> getPesquisa() {
        jpaCon = new ProfissoesJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findProfissoesEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getProfissoesCount();
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
            warn = warn + "O campo <Profissão> não pode ficar vazio.\n";
            cte++;
        }

        // se for inserção, verificar se Nome do Programa já existe
        if (cte > 0) {
            // há erros
            mostraMsg("Há erro(s) de preenchimento.", warn, 2);
        } else {
            if (verifPodeGravar(edNomeProg.getText()) == false) {
                mostraMsg("Não é possível gravar!", "Já existe outro registro com o mesmo campo <Profissão>.", 2);
            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION);
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
                                Profissoes obj = new Profissoes();
                                obj.setNomeProfissao(edNomeProg.getText());

                                jpaCon.create(obj);
                                reg_atual = obj;
                                txtCodPK.setText("Profissão nº " + reg_atual.getCodProfissao());
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

//        Long achados = getQuantosNomes("Profissoes", "estadoCivil", "(a)");
//        Alert alert = new Alert(AlertType.CONFIRMATION);
//        alert.setHeaderText("Confirmação");
//        alert.setContentText("Número de nomes com <a> " + achados);
//        Optional<ButtonType> option = alert.showAndWait();
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
    }

    public void abreRegSelected() {
        reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();
        preencheEdsComObj();

        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(0); //select by index starting with 0

        // PRECISA MESMO DESSA LINHA?
        tvPesquisa.setItems(getMeusDados());
    }

    public void preencheEdsComObj() {
        txtCodPK.setText("Profissão nº " + reg_atual.getCodProfissao());
        edNomeProg.setText(reg_atual.getNomeProfissao());
    }

    public void preencheObjComEds() {
        reg_atual.setNomeProfissao(edNomeProg.getText());
    }

    public void limpaCampos() {
        txtCodPK.setText("Profissão nº ");
        edNomeProg.setText("");
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
        if (!edNomeProg.getText().equals("")) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirmação - apagar registro " + reg_atual.getCodProfissao());
            alert.setContentText("Deseja realmente apagar esse registro?\nOs dados serão perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaCon.destroy(reg_atual.getCodProfissao());

                    desligaEdicao();
                    limpaCampos();
                    initColumns();
                    tvPesquisa.setItems(getMeusDados());
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
        jpaCon = new ProfissoesJpaController();
        List lis = jpaCon.getProfissoesPesqExac(pesq);

        Profissoes prog;
        if (editando == true) {

            for (int i = 0; i < lis.size(); i++) {
                prog = (Profissoes) lis.get(i);
                if (!prog.getCodProfissao().equals(reg_atual.getCodProfissao())) {
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

}
