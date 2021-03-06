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
import entities.EsGrade;
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
import jpa_controler.EsGradeJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class GradeMContoller implements Initializable {

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
    private TableView<EsGrade> tvPesquisa;
    @FXML
    private TableColumn<EsGrade, Integer> tc1;
    @FXML
    private TableColumn<EsGrade, String> tc2;
    @FXML
    private TableColumn<EsGrade, String> tc3;
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
    // VARI??VEIS LOCAIS
    // --------------------
    private ObservableList<EsGrade> dados;
    private EsGradeJpaController jpaCon;
    EsGrade reg_atual = new EsGrade();
    private boolean inserindo, editando;

    private TextField txtIndex;
    private TextField txtItem;
    private TextField txtValue;

    @FXML
    private JFXTextField edNomeProg;
    @FXML
    private JFXTextField edDescCateg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jpaCon = new EsGradeJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO

        inserindo = false;
        editando = false;
        desligaEdicao();
        initColumns();

        // Inicializa ComboBox do ??ndice de Pesquisa
        cbIndice.getItems().addAll("C??digo", "Ano letivo");
        cbIndice.getSelectionModel().selectFirst();
        cbCondicao.getItems().addAll("igual a", "diferente de", "maior ou igual a", "maior que", "menor ou igual a", "menor que", "come??a com", "termina com", "cont??m");
        cbCondicao.getSelectionModel().selectFirst();

        // s?? para usu??rio com permiss??o
        buApaga.setVisible(true);
        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(1); //select by index starting with 0
    }

    public void initColumns() {
        tc1.setCellValueFactory(new PropertyValueFactory<>("codGrade"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
    }

    public ObservableList<EsGrade> getMeusDados() {

        jpaCon = new EsGradeJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findEsGradeEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getEsGradeCount();
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

    public ObservableList<EsGrade> getMinhaPesqDados(String pesq) {

        jpaCon = new EsGradeJpaController();
        dados = FXCollections.observableArrayList(jpaCon.getEsGradePesq(pesq));
        lblRegistros.setText("");

        Long rowcount = getQuantosNomes("EsGrade", "codGrade", pesq);

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

    public ObservableList<EsGrade> getPesquisa() {
        jpaCon = new EsGradeJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findEsGradeEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getEsGradeCount();
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
        alert.setContentText("Se voc?? cancelar a edi????o, os dados n??o ser??o gravados.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            if (editando == true) {
                preencheEdsComObj();  // restaura nos campos os valores antes da edi????o.
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
        // CONSIST??NCIA DOS CAMPOS
        if (edNomeProg.getText().equals("")) {
            warn = warn + "O campo <Ano Letivo> n??o pode ficar vazio.\n";
            cte++;
        }

        if (edDescCateg.getText().equals("")) {
            warn = warn + "O campo <Semestre> n??o pode ficar vazio.\n";
            cte++;
        }

        // se for inser????o, verificar se Nome do Programa j?? existe
        if (cte > 0) {
            // h?? erros
            mostraMsg("H?? erro(s) de preenchimento.", warn, 2);
        } else {

            if (verifPodeGravar(edNomeProg.getText(), edDescCateg.getText()) == false) {
                mostraMsg("N??o ?? poss??vel gravar!", "J?? existe outro registro com o mesmos Ano Letivo e Semestre.", 2);
            } else {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Confirma");
                alert.setContentText("Confirma a grava????o dos dados?");
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
                            mostraMsg("Erro ao gravar a edi????o dos dados.", ""+e, 2);
                        }
                    } else {
                        if (inserindo == true) {
                            try {
                                // PREENCHE OBd COM Eds
                                EsGrade obj = new EsGrade();
                                obj.setAnoLetivo(Short.valueOf(edNomeProg.getText()));
                                obj.setSemestre(Short.valueOf(edDescCateg.getText()));

                                jpaCon.create(obj);
                                reg_atual = obj;
                                txtCodPK.setText("Grade n?? " + reg_atual.getCodGrade());
                                

                                // ======================================================================================================
                                // AQUI EU PRECISO OBTER A PRIMARY KEY QUE ACABA DE SER GERADA, SEN??O DAR?? ERRO NA EDI????O OU DELE????O.
                                // A ALTERNATIVA ?? JOGAR O USU??RIO NOVAMENTE PARA A TELA DE PESQUISA
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
        alert.setHeaderText("Confirma????o");
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

        // habilita bot??es para inser????o e dele????o
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

        // habilita bot??es para inser????o e dele????o
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
        reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();
        preencheEdsComObj();

        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(0); //select by index starting with 0

        // PRECISA MESMO DESSA LINHA?
        tvPesquisa.setItems(getMeusDados());
    }

    public void preencheEdsComObj() {
        txtCodPK.setText("Grade n?? " + reg_atual.getCodGrade());
        edNomeProg.setText(Short.toString(reg_atual.getAnoLetivo()));
        edDescCateg.setText(Short.toString(reg_atual.getSemestre()));
    }

    public void preencheObjComEds() {
        reg_atual.setAnoLetivo(Short.valueOf(edNomeProg.getText()));
        reg_atual.setSemestre(Short.valueOf(edDescCateg.getText()));
    }

    public void limpaCampos() {
        txtCodPK.setText("Grade n?? ");
        edNomeProg.setText("");
        edDescCateg.setText("");
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
        if (!edNomeProg.getText().equals("")) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Confirma????o - apagar registro " + reg_atual.getCodGrade());
            alert.setContentText("Deseja realmente apagar esse registro?\nOs dados ser??o perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
                    jpaCon.destroy(reg_atual.getCodGrade());

                    desligaEdicao();
                    limpaCampos();
                    initColumns();
                    tvPesquisa.setItems(getMeusDados());
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
            mostraMsg("Pesquisa", "Informe o conte??do a pesquisar", 3);
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
                mostraMsg("Pesquisa", "Informe o conte??do a pesquisar", 3);
                txtPesq.requestFocus();
            } else {
                tvPesquisa.setItems(getMinhaPesqDados(conteudo));
            }
        }

    }

    private Boolean verifPodeGravar(String ano, String semestre) {
        Boolean podegravar = true;

        Long achados = 0L;
        jpaCon = new EsGradeJpaController();
        List lis = jpaCon.getEsGradePesqExac(ano, semestre);

        EsGrade prog;
        if (editando == true) {

            for (int i = 0; i < lis.size(); i++) {
                prog = (EsGrade) lis.get(i);
                if (!prog.getCodGrade().equals(reg_atual.getCodGrade())) {
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
