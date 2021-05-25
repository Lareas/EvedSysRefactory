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
import entities.Dadocadastrogeral;
import entities.Dadocadastrogeralministeriais;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import jpa_controler.DadocadastrogeralJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class AlunosTesteController implements Initializable {

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
    private TableView<Dadocadastrogeral> tvPesquisa;
    @FXML
    private TableColumn<Dadocadastrogeral, Integer> tc1;
    @FXML
    private TableColumn<Dadocadastrogeral, String> tc2;
    @FXML
    private TableColumn<Dadocadastrogeral, String> tc3;
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
    @FXML
    private JFXTextField edNomeProg;
    @FXML
    private JFXTextField edDescCateg;

    // --------------------
    // VARIÁVEIS LOCAIS
    // --------------------
    private ObservableList<Dadocadastrogeral> dados;
    private DadocadastrogeralJpaController jpaCon;
    
//    private ObservableList<Dadocadastrogeralministeriais> dadosM;
//    private Dado jpaM;
    
    
    Dadocadastrogeral reg_atual = new Dadocadastrogeral();
    private boolean inserindo, editando;
    @FXML
    private TableView<Dadocadastrogeralministeriais> tvMM;
    @FXML
    private TableColumn<Dadocadastrogeralministeriais, String> tcNom;
    
    @FXML
    private FontAwesomeIconView buAbreRec1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jpaCon = new DadocadastrogeralJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO

        inserindo = false;
        editando = false;
        desligaEdicao();
        initColumns();

        // Inicializa ComboBox do Índice de Pesquisa
        cbIndice.getItems().addAll("Código", "Aluno", "Endereço");
        cbIndice.getSelectionModel().selectFirst();
        cbCondicao.getItems().addAll("igual a", "diferente de", "maior ou igual a", "maior que", "menor ou igual a", "menor que", "começa com", "termina com", "contém");
        cbCondicao.getSelectionModel().selectFirst();

        // só para usuário com permissão
        buApaga.setVisible(true);
        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(1); //select by index starting with 0
    }
    
    @FXML
    private void clicouAbreMin(ActionEvent event) {
        jpaCon = new DadocadastrogeralJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO

        initMin();
    }
    
    public void initMin() {
        
//        jpaM = new 
        
//        private ObservableList<Dadocadastrogeralministeriais> dadosM;
//    private Dadocadastrogeralministeriais jpaM;
        tcNom.setCellValueFactory(new PropertyValueFactory<>("ministeriais"));
//        tvMM.setItems(getMeusMin());
        tvMM.refresh();
    }
    
    public ObservableList<Dadocadastrogeral> setCellValueFactory() {

        jpaCon = new DadocadastrogeralJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findDadocadastrogeralEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getDadocadastrogeralCount();
        if (rowcount > 1) {
            lblRegistros.setText(" " + rowcount + " registros ");
        } else {
            lblRegistros.setText(" " + rowcount + " registro ");
        }

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }

    }

    public void initColumns() {
        tc1.setCellValueFactory(new PropertyValueFactory<>("dadoCadastroGeralId"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("enderecoResidencial"));
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
    }

    public ObservableList<Dadocadastrogeral> getMeusDados() {

        jpaCon = new DadocadastrogeralJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findDadocadastrogeralEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getDadocadastrogeralCount();
        if (rowcount > 1) {
            lblRegistros.setText(" " + rowcount + " registros ");
        } else {
            lblRegistros.setText(" " + rowcount + " registro ");
        }

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }

    }

    public ObservableList<Dadocadastrogeral> getMinhaPesqDados(String pesq) {

        jpaCon = new DadocadastrogeralJpaController();
        dados = FXCollections.observableArrayList(jpaCon.getGeralalunoPesq(pesq));
        lblRegistros.setText("");

        Long rowcount = getQuantosNomes("Dadocadastrogeral", "categoriaAluno", pesq);

        if (rowcount > 1) {
            lblRegistros.setText(" " + rowcount + " registros ");
        } else {
            lblRegistros.setText(" " + rowcount + " registro ");
        }
        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }
    }

    public ObservableList<Dadocadastrogeral> getPesquisa() {
        jpaCon = new DadocadastrogeralJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findDadocadastrogeralEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getDadocadastrogeralCount();
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
            warn = warn + "O campo <Categoria> não pode ficar vazio.\n";
            cte++;
        }

        if (edDescCateg.getText().equals("")) {
            warn = warn + "O campo <Descrição> não pode ficar vazio.\n";
            cte++;
        }

        // se for inserção, verificar se Nome do Programa já existe
        if (cte > 0) {
            // há erros
            mostraMsg("Há erro(s) de preenchimento.", warn, 2);
        } else {

            if (verifPodeGravar(edNomeProg.getText()) == false) {
                mostraMsg("Não é possível gravar!", "Já existe outro registro com o mesmo campo <Categoria>.", 2);
            } else {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Confirma");
                alert.setContentText("Confirma a gravação dos dados?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {

                    if (editando == true) {
                        try {
                            preencheObjComEds(); // PREENCHE OBd COM Eds
                            System.out.println(reg_atual);
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
                                Dadocadastrogeral obj = new Dadocadastrogeral();
                                obj.setNome(edNomeProg.getText());
                                obj.setEnderecoResidencial(edDescCateg.getText());

                                jpaCon.create(obj);
                                reg_atual = obj;
                                txtCodPK.setText("Categoria nº " + reg_atual.getDadoCadastroGeralId());
                                

                                // ======================================================================================================
                                // AQUI EU PRECISO OBTER A PRIMARY KEY QUE ACABA DE SER GERADA, SENÃO DARÁ ERRO NA EDIÇÃO OU DELEÇÃO.
                                // A ALTERNATIVA É JOGAR O USUÁRIO NOVAMENTE PARA A TELA DE PESQUISA
                                // ======================================================================================================
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

//    private void clicouPesquisa(ActionEvent event) {
//        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
//        selectionModel.select(1); //select by index starting with 0
//    }
    
    
    
    
    @FXML
    private void clicouLimpa(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmação");
        alert.setContentText("Deseja limpar a pesquisa?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            tvPesquisa.setItems(getMeusDados());
            tvPesquisa.refresh(); /////////////////////////////////////////////////
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
        edDescCateg.setEditable(true);
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
        edDescCateg.setEditable(false);
    }
    
    

    public void abreRegSelected() {
        reg_atual = jpaCon.findDadocadastrogeral((Integer) 4912);
      //  reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();
        preencheEdsComObj();

        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(0); //select by index starting with 0

        // PRECISA MESMO DESSA LINHA?
        tvPesquisa.setItems(getMeusDados());
    }

    public void preencheEdsComObj() {
        txtCodPK.setText("Alluno nº " + reg_atual.getDadoCadastroGeralId());
        edNomeProg.setText(reg_atual.getNome());
        edDescCateg.setText(reg_atual.getEnderecoResidencial());
    }

    public void preencheObjComEds() {
        reg_atual.setNome(edNomeProg.getText());
        reg_atual.setEnderecoResidencial(edDescCateg.getText());
    }

    public void limpaCampos() {
        txtCodPK.setText("Categoria nº ");
        edNomeProg.setText("");
        edDescCateg.setText("");
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
        if (!edNomeProg.getText().equals("")) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirmação - apagar registro " + reg_atual.getDadoCadastroGeralId());
            alert.setContentText("Deseja realmente apagar esse registro?\nOs dados serão perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaCon.destroy(reg_atual.getDadoCadastroGeralId());

                    desligaEdicao();
                    limpaCampos();
                    initColumns();
                    tvPesquisa.setItems(getMeusDados());
                    mostraMsg("Dados apagados com sucesso", "", 3);
                    // PREENCHE OBd COM Eds
                } catch (Exception e) {
                    mostraMsg("Erro ao apagar registro", "", 2);
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
        jpaCon = new DadocadastrogeralJpaController();
        List lis = jpaCon.getGeralalunoPesqExac(pesq);

        Dadocadastrogeral prog;
        if (editando == true) {

            for (int i = 0; i < lis.size(); i++) {
                prog = (Dadocadastrogeral) lis.get(i);
                if (!prog.getDadoCadastroGeralId().equals(reg_atual.getDadoCadastroGeralId())) {
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
