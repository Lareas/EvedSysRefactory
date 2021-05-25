/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Cadastrodisciplina;
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
import jpa_controler.CadastrodisciplinaJpaController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import static funcoes.MyFunc.*;
import static java.lang.Integer.parseInt;
import static java.lang.Short.parseShort;
import java.math.BigDecimal;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class CadDisciplinaController implements Initializable {

    @FXML
    private TextField txtCodPK;
    @FXML
    private JFXTextField edNomeProg;
    private JFXTextField edCoord;
    private JFXTextField edCoordTit;
    private JFXTextField edEmail;
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
    private TableView<Cadastrodisciplina> tvPesquisa;
    @FXML
    private TableColumn<Cadastrodisciplina, Integer> tc1;
    @FXML
    private TableColumn<Cadastrodisciplina, Integer> tc2;
    @FXML
    private TableColumn<Cadastrodisciplina, String> tc3;
    @FXML
    private TableColumn<Cadastrodisciplina, String> tc4;
    @FXML
    private TableColumn<Cadastrodisciplina, String> tc5;
    @FXML
    private TableColumn<Cadastrodisciplina, Integer> tc6;
    @FXML
    private TableColumn<Cadastrodisciplina, Integer> tc7;
    @FXML
    private TableColumn<Cadastrodisciplina, Integer> tc8;
    @FXML
    private TableColumn<Cadastrodisciplina, Integer> tc9;
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

    // --------------------
    // VARIÁVEIS LOCAIS
    // --------------------
    private ObservableList<Cadastrodisciplina> dados;
    private CadastrodisciplinaJpaController jpaCon;
    Cadastrodisciplina reg_atual = new Cadastrodisciplina();
    private boolean inserindo, editando;

    private TextField txtIndex;
    private TextField txtItem;
    private TextField txtValue;
    @FXML
    private JFXTextField edIdDis;
    @FXML
    private JFXTextField edCre;
    @FXML
    private JFXTextField edCar;
    @FXML
    private JFXTextField edAno;
    @FXML
    private JFXTextField edSem;
    @FXML
    private JFXTextField edHor;
    @FXML
    private JFXCheckBox chkDom;
    @FXML
    private JFXCheckBox chkSeg;
    @FXML
    private JFXCheckBox chkTer;
    @FXML
    private JFXCheckBox chkQua;
    @FXML
    private JFXCheckBox ckQui;
    @FXML
    private JFXCheckBox chkSex;
    @FXML
    private JFXCheckBox chkSab;
    @FXML
    private JFXTextField edLoc;
    @FXML
    private JFXTextField edTurma;
    @FXML
    private JFXTextField edTurno;
    @FXML
    private JFXTextField edEspec;
    @FXML
    private JFXCheckBox chkCom;
    @FXML
    private JFXCheckBox chkSup;
    @FXML
    private JFXCheckBox chkDis;
    @FXML
    private JFXTextArea edDescricao;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jpaCon = new CadastrodisciplinaJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO

        inserindo = false;
        editando = false;
        desligaEdicao();
        initColumns();

        // Inicializa ComboBox do Índice de Pesquisa
        cbIndice.getItems().addAll("Código", "Cadastrodisciplina", "Coordenador", "Coord. Titulação", "E-mail");
        cbIndice.getSelectionModel().selectFirst();
        cbCondicao.getItems().addAll("igual a", "diferente de", "maior ou igual a", "maior que", "menor ou igual a", "menor que", "começa com", "termina com", "contém");
        cbCondicao.getSelectionModel().selectFirst();

        // só para usuário com permissão
        buApaga.setVisible(true);
        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(1); //select by index starting with 0
    }

    public void initColumns() {
        tc1.setCellValueFactory(new PropertyValueFactory<>("cadastroDisciplinaId"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("disciplinaId"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("especificidade"));
        tc6.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tc7.setCellValueFactory(new PropertyValueFactory<>("semestreId"));
        tc8.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        tc9.setCellValueFactory(new PropertyValueFactory<>("horario"));
        
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
    }

    public ObservableList<Cadastrodisciplina> getMeusDados() {

        jpaCon = new CadastrodisciplinaJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findCadastrodisciplinaEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getCadastrodisciplinaCount();
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

    public ObservableList<Cadastrodisciplina> getMinhaPesqDados(String pesq) {

        jpaCon = new CadastrodisciplinaJpaController();
        dados = FXCollections.observableArrayList(jpaCon.getCadastrodisciplinaPesq(pesq));
        //  txtPesq.setText("");
        lblRegistros.setText("");

//        Long rowcount = getQuantosNomes("Cadastrodisciplina", "programa", pesq);
//        if (rowcount > 1) {
//            lblRegistros.setText(" " + rowcount + " registros");
//        } else {
//            lblRegistros.setText(" " + rowcount + " registro");
//        }

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }
    }

    public ObservableList<Cadastrodisciplina> getPesquisa() {
        jpaCon = new CadastrodisciplinaJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findCadastrodisciplinaEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getCadastrodisciplinaCount();
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
            warn = warn + "O campo <Nome do Cadastrodisciplina> não pode ficar vazio.\n";
            cte++;
        }

        if (edCoord.getText().equals("")) {
            warn = warn + "O campo <Coordenador(a)> não pode ficar vazio.\n";
            cte++;
        }

        if (edCoordTit.getText().equals("")) {
            warn = warn + "O campo <Coordenador(a) Titulação> não pode ficar vazio.\n";
            cte++;
        }

        if (edEmail.getText().equals("")) {
            warn = warn + "O campo <E-mail> não pode ficar vazio.\n";
            cte++;
        } else {
            // verifica consistência do email
            if (!emailConfere(edEmail.getText())) {
                warn = warn + "O email <" + edEmail.getText() + " parece não ser válido.\n";
                cte++;
            }
        }

        // se for inserção, verificar se Nome do Cadastrodisciplina já existe
        if (cte > 0) {
            // há erros
            mostraMsg("Há erro(s) de preenchimento.", warn, 2);
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
                            Cadastrodisciplina obj = new Cadastrodisciplina();
                            
                 /////           obj.setd((Integer) parseInt(edIdDis.getText()));
                            obj.setDisciplina(edNomeProg.getText());
                            obj.setCredito((Short) parseShort(edIdDis.getText()));
                            obj.setCargaHoraria(new BigDecimal(edCar.getText()));
                            obj.setAnoLetivo((Short) parseShort(edAno.getText()));
                            obj.setSemestreId((Short) parseShort(edSem.getText()));
                            
                            
//                            obj.setCoordenador(edCoord.getText());
//                            obj.setCoordenadorTitulacao(edCoordTit.getText());
//                            obj.setEmail(edEmail.getText());

                            jpaCon.create(obj);
                            reg_atual = obj;
//                            txtCodPK.setText("Cadastrodisciplina nº " + reg_atual.getCadastrodisciplinaId());
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
        edCoord.setEditable(true);
        edCoordTit.setEditable(true);
        edEmail.setEditable(true);
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
        edCoord.setEditable(false);
        edCoordTit.setEditable(false);
        edEmail.setEditable(false);
    }

    public void abreRegSelected() {
        reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();
        preencheEdsComObj();

        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(0); //select by index starting with 0

    }

    public void preencheEdsComObj() {
//        txtCodPK.setText("Disciplina nº " + reg_atual.getCadastrodisciplinaId());
//        
//        
//        
//        
//        edNomeProg.setText(reg_atual.getCadastrodisciplina());
//        edCoord.setText(reg_atual.getCoordenador());
//        edCoordTit.setText(reg_atual.getCoordenadorTitulacao());
//        edEmail.setText(reg_atual.getEmail());
    }

    public void preencheObjComEds() {

//        reg_atual.setCadastrodisciplina(edNomeProg.getText());
//        reg_atual.setCoordenador(edCoord.getText());
//        reg_atual.setCoordenadorTitulacao(edCoordTit.getText());
//        reg_atual.setEmail(edEmail.getText());
    }

    public void limpaCampos() {
        txtCodPK.setText("");
        edNomeProg.setText("");
        edCoord.setText("");
        edCoordTit.setText("");
        edEmail.setText("");
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
        if (!edNomeProg.getText().equals("")) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
//            alert.setHeaderText("Confirmação - apagar registro " + reg_atual.getCadastrodisciplinaId());
            alert.setContentText("Deseja realmente apagar esse registro?\nOs dados serão perdidos!");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                try {
//                    jpaCon.destroy(reg_atual.getCadastrodisciplinaId());

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
}
