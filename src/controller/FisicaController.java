/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.GBFORMATDATA;
import static main.Login.gbUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Dadocadastrogeral;
import entities.Fisica;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import jpa_controler.DadocadastrogeralJpaController;
import jpa_controler.FisicaJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class FisicaController implements Initializable {

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
    private TableView<Fisica> tvPesquisa;
    @FXML
    private TableColumn<Fisica, Integer> tc1;
    @FXML
    private TableColumn<Fisica, String> tc2;
    @FXML
    private TableColumn<Fisica, Date> tc3;
    @FXML
    private TableColumn<Fisica, String> tc4;
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
    private ObservableList<Fisica> dados;
    private FisicaJpaController jpaCon;
    private ObservableList<Dadocadastrogeral> dadosEV;
    private DadocadastrogeralJpaController jpaEV;

    Fisica reg_atual = new Fisica();
    private boolean inserindo, editando;

    private TextField txtIndex;
    private TextField txtItem;
    private TextField txtValue;

    @FXML
    private JFXTextField edNomeProg;
    @FXML
    private JFXTextField edDescCateg;
    @FXML
    private JFXTextField edDataNasc;
    @FXML
    private TableView<Dadocadastrogeral> tcAlunosEv;
    @FXML
    private TableColumn<Dadocadastrogeral, Integer> tcCodE;
    @FXML
    private TableColumn<Dadocadastrogeral, String> tcAlunoE;
    @FXML
    private TableColumn<Dadocadastrogeral, Date> tcDataE;
    @FXML
    private TableColumn<Dadocadastrogeral, String> tcCpfE;
    @FXML
    private JFXButton buImPDataNasc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Locale localeBR = new Locale("pt", "BR");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", localeBR);

        tc3.setCellFactory(column -> {
            TableCell<Fisica, Date> cell = new TableCell<Fisica, Date>() {

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

        tcDataE.setCellFactory(column -> {
            TableCell<Dadocadastrogeral, Date> cell = new TableCell<Dadocadastrogeral, Date>() {

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

        jpaCon = new FisicaJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO

        inserindo = false;
        editando = false;
        desligaEdicao();
        initColumns();

        // Inicializa ComboBox do Índice de Pesquisa
        cbIndice.getItems().addAll("Código", "Física");
        cbIndice.getSelectionModel().selectFirst();
        cbCondicao.getItems().addAll("igual a", "diferente de", "maior ou igual a", "maior que", "menor ou igual a", "menor que", "começa com", "termina com", "contém");
        cbCondicao.getSelectionModel().selectFirst();

        // só para usuário com permissão
        buApaga.setVisible(true);
        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(1); //select by index starting with 0
    }

    public void initColumns() {
        tc1.setCellValueFactory(new PropertyValueFactory<>("codext"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("datanasc"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        tcCodE.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcAlunoE.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcDataE.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tcCpfE.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tvPesquisa.setItems(getMeusDados());
        tcAlunosEv.setItems(getMeusDadosEv());
        tvPesquisa.refresh();
    }

    public ObservableList<Fisica> getMeusDados() {

        jpaCon = new FisicaJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findFisicaEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getFisicaCount();
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

    public ObservableList<Dadocadastrogeral> getMeusDadosEv() {

        jpaEV = new DadocadastrogeralJpaController();
        dadosEV = FXCollections.observableArrayList(jpaEV.findDadocadastrogeralEntities());
        txtPesq.setText("");

        if (dadosEV == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosEV;
        }

    }

    public ObservableList<Fisica> getMinhaPesqDados(String pesq) {

        jpaCon = new FisicaJpaController();
        dados = FXCollections.observableArrayList(jpaCon.getFisicaPesq(pesq));
        lblRegistros.setText("");

        Long rowcount = getQuantosNomes("Fisica", "categoriaAluno", pesq);

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

    public ObservableList<Fisica> getPesquisa() {
        jpaCon = new FisicaJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findFisicaEntities());
        txtPesq.setText("");

        int rowcount = jpaCon.getFisicaCount();
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
        reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();
        preencheEdsComObj();

        SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        selectionModel.select(0); //select by index starting with 0

        // PRECISA MESMO DESSA LINHA?
        tvPesquisa.setItems(getMeusDados());
    }

    public void preencheEdsComObj() {
        txtCodPK.setText("Física nº " + reg_atual.getCodigo());
        edNomeProg.setText(reg_atual.getNome());
        edDataNasc.setText(String.valueOf(reg_atual.getDatanasc()));
    }

    public void preencheObjComEds() {
//      //  reg_atual.setCodigo(edNomeProg.getText());
//        reg_atual.setNome(edDescCateg.getText());
//        reg_atual.setDatanasc((Date) edDataNasc.getText());
    }

    public void limpaCampos() {
        txtCodPK.setText("Física nº ");
        edNomeProg.setText("");
        edDescCateg.setText("");
    }

    @FXML
    private void clicouApaga(ActionEvent event) {
//        if (!edNomeProg.getText().equals("")) {
//            Alert alert = new Alert(AlertType.CONFIRMATION);
//            alert.setHeaderText("Confirmação - apagar registro " + reg_atual.getCodigo());
//            alert.setContentText("Deseja realmente apagar esse registro?\nOs dados serão perdidos!");
//            Optional<ButtonType> option = alert.showAndWait();
//
//            if (option.get() == ButtonType.OK) {
//                try {
//                   jpaCon.destroy(reg_atual.getCodigo());
//
//                    desligaEdicao();
//                    limpaCampos();
//                    initColumns();
//                    tvPesquisa.setItems(getMeusDados());
//                    mostraMsg("Dados apagados com sucesso", "", 3);
//                    // PREENCHE OBd COM Eds
//                } catch (Exception e) {
//                    mostraMsg("Erro ao apagar registro", "", 2);
//                }
//            }
//        }
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

//    private Boolean verifPodeGravar(String pesq) {
//        Boolean podegravar = true;
//
//        Long achados = 0L;
//        jpaCon = new FisicaJpaController();
//        List lis = jpaCon.getFisicaPesqExac(pesq);
//
//        Fisica prog;
//        if (editando == true) {
//
//            for (int i = 0; i < lis.size(); i++) {
//                prog = (Fisica) lis.get(i);
//                if (!prog.getCategoriaAlunoId().equals(reg_atual.getCategoriaAlunoId())) {
//                    podegravar = false;
//                }
//            }
//        } else {
//            if (inserindo == true) {
//                if (lis.size() > 0) {
//                    podegravar = false;
//                }
//            }
//        }
//
//        return podegravar;
//    }
    public void procuraPeloCpf(Dadocadastrogeral i, List<String> lNaoTem, List<String> lPerfect, List<String> lQuase, List<String> lSemDN, List<String> lDupli) {
        Long temCpf = jpaCon.getContaObjetoViaCpf(i.getCpf());
        if (temCpf == 0) {// NÃO TEM MESMO
            lNaoTem.add(i.getMatricula() + " Aluno: " + i.getNome() + " CPF: " + i.getCpf());
        } else {
            if (temCpf == 1) {
                Fisica s = jpaCon.getObjetoViaCodCpf(i.getCpf());

                if (s != null) {
                    if (s.getDatanasc() != null) { // data nasc existe. verificar se é match perfeito
                        if (i.getNome().equals(s.getNome())) {
                            lPerfect.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc() + " CPF: " + i.getCpf());
                            //  ++cPerfect;
                        } else {
                            lQuase.add(s.getCodext() + " Aluno1: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                            //  ++cQuase;
                        }

                    }

                } else {
                    lSemDN.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                    //   ++cSemDN;
                }
            } else { // tem > 1 - há mais de uma ocorrência deste cpf
                List<Fisica> lDuptemp = jpaCon.getListCpf(i.getCpf());
                for (Fisica d : lDuptemp) {
                    lDupli.add(d.getCodext() + " Aluno: " + d.getNome() + " Dnasc: " + d.getDatanasc() + " CPF: " + d.getCpf());
                    //     ++cDupli;
                }

            }
        }
    }

    @FXML
    private void clicouImPDataNasc(ActionEvent event) {

        if (gbUser.getLogin().equals("lfa")) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String ini = "Início: " + formatter.format(date);
            System.out.println(ini);  

            long start = System.currentTimeMillis();
            List<String> lNaoTem = new ArrayList<>();
            List<String> lPerfect = new ArrayList<>();
            List<String> lQuase = new ArrayList<>();
            List<String> lDupli = new ArrayList<>();
            List<String> lSemDN = new ArrayList<>();
            List<String> lComAspa = new ArrayList<>();

            Long temMatri = 0L;
            Long temNome = 0L;
            Long temCpf = 0L;

            // int cNaotem = 0, cPerfect = 0, cQuase = 0, cDupli = 0, cSemDN = 0, cConta = 0;
            int cConta = 0;
            int tamanho = tcAlunosEv.itemsProperty().get().size();
            jpaCon = new FisicaJpaController();

            // VARRE Dadocadastrogeral e tenta recuperar data de nascimento em SOPHIA
            for (Dadocadastrogeral i : tcAlunosEv.getItems()) {
                // O loop jogará em i cada linha
                if (i.getDataNascimento() == null) { // data nasc vazia, tenta recuperar de Sophia

                    temMatri = jpaCon.getContaObjetoViaCodext(i.getMatricula()); // quantas ocorrências há deste código de marícula?
                    if (temMatri == 0) { // não achou

                        // antes de desistir, tenta localizar pelo nome
//                    if (i.getNome().indexOf("'") != -1) {
//                        lComAspa.add(i.getMatricula() + " Aluno: " + i.getNome());
//                        // troca character?                        
//                    } else {
                        temNome = jpaCon.getContaObjetoViaNome(i.getNome());
                        if (temNome == 0) {

                            // =========================== não achou, tenta localizar pelo cpf
                            temCpf = jpaCon.getContaObjetoViaCpf(i.getCpf());
                            if (temCpf == 0) {// NÃO TEM MESMO
                                lNaoTem.add(i.getMatricula() + " Aluno: " + i.getNome() + " CPF: " + i.getCpf());
                            } else {
                                if (temCpf == 1) {
                                    Fisica s = jpaCon.getObjetoViaCodCpf(i.getCpf());

                                    if (s != null) {
                                        if (s.getDatanasc() != null) { // data nasc existe. verificar se é match perfeito
                                            if (i.getNome().equals(s.getNome())) {
                                                lPerfect.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc() + " CPF: " + i.getCpf());
                                            } else {
                                                lQuase.add(s.getCodext() + " Aluno_CA: " + i.getNome() + " Dnasc: " + s.getDatanasc() + " nome diferente de Aluno_Sophia: " + s.getNome() );
                                            }

                                        } else { // tem nome e cpf, mas não tem data
                                            lSemDN.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc() + " CPF: " + s.getCpf());
                                        }

                                    } else {
                                        lSemDN.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                                    }
                                } else { // tem > 1 - há mais de uma ocorrência deste cpf
                                    List<Fisica> lDuptemp = jpaCon.getListCpf(i.getCpf());
                                    for (Fisica d : lDuptemp) {
                                        lDupli.add(d.getCodext() + " Aluno: " + d.getNome() + " Dnasc: " + d.getDatanasc() + " CPF: " + d.getCpf());
                                    }

                                }
                            } // fim CPF ========================================

                        } else {
                            if (temNome == 1) {
//                                if (i.getNome().indexOf("'") != -1) {
//                                    lComAspa.add(i.getMatricula() + " Aluno: " + i.getNome());
//                                    // troca character?            
//                                } else {

                                Fisica s = jpaCon.getObjetoViaNome(i.getNome());

                                if (s != null) {
                                    if (s.getDatanasc() != null) { // data nasc existe. verificar se é match perfeito
                                        if (i.getNome().equals(s.getNome())) {
                                            lPerfect.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                                        } else {
                                            lQuase.add(s.getCodext() + " Aluno.CA3: " + i.getNome() + " Aluno.Sophia: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                                        }

                                    } else { // tem nome, mas não tem data
                                        lSemDN.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                                    }

                                } else {
                                    lSemDN.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                                }
                                //  }
                            } else { // tem > 1 - há mais de uma ocorrência deste nome
                                List<Fisica> lDuptemp = jpaCon.getListNome(i.getNome());
                                for (Fisica d : lDuptemp) {
                                    lDupli.add(d.getCodext() + " Aluno: " + d.getNome() + " Dnasc: " + d.getDatanasc() + " CPF: " + d.getCpf());
                                }

                            }
                        }
                        //  }
                    } else {

                        if (temMatri == 1) { // achou apenas um códugo de matrícula deste aluno, o ideal
                            Fisica s = jpaCon.getObjetoViaCodext(i.getMatricula());

                            if (s != null) {
                                if (s.getDatanasc() != null) { // data nasc existe. verificar se é match perfeito
                                    if (i.getNome().equals(s.getNome())) {
                                        lPerfect.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                                    } else {
                                        lQuase.add(s.getCodext() + " Aluno.CA4: " + i.getNome() + " Aluno.Sophia: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                                    }
                                } else { // tem matrícula, mas não tem data
                                    lSemDN.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                                }

                            } else { // não tem matrícula
                                lSemDN.add(s.getCodext() + " Aluno: " + s.getNome() + " Dnasc: " + s.getDatanasc());
                            }

                        } else { // tem > 1 - há mais de uma ocorrência deste código de matrícula
                            List<Fisica> lDuptemp = jpaCon.getListCodExt(i.getMatricula());
                            for (Fisica d : lDuptemp) {
                                lDupli.add(d.getCodext() + " Aluno: " + d.getNome() + " Dnasc: " + d.getDatanasc() + " CPF: " + d.getCpf());
                            }

                        }
                    }
                }
                //  System.out.println(++cConta);
            }

            

            impRes(lNaoTem, "Alunos cujas datas de nascimento não foram localizadas no Sophia");
            impRes(lPerfect, "Alunos cujas datas de nascimento localizadas no Sophia");
            impRes(lQuase, "Alunos cujas datas de nascimento localizadas no Sophia, mas com nomes diferentes");
            impRes(lDupli, "Alunos com código de Matrícula com mais de uma ocorrência no Sophia");
            impRes(lSemDN, "Alunos sem data de nascimento no Sophia");
            impRes(lComAspa, "Alunos cujos nomes contém ASPAS ' ");

            long finish = System.currentTimeMillis();
            float sec = (finish - start) / 1000F;
            
            date = new Date();
            
            System.out.println("\n\n***** Tempo de processamento:");
            System.out.println(ini);  
            System.out.println("Fim: " + formatter.format(date));  
            System.out.println("Tempo gasto: " + sec + " segundos");

        }
    }

    public void impRes(List<String> array, String titulo) {
        // imprime resultados
        System.out.println("=======================================================================");
        System.out.println("*-> " + array.size() + " " + titulo);
        System.out.println("=======================================================================\n");

        for (int b = 0; b < array.size(); b++) {
            System.out.println(array.get(b));
        }
        System.out.println("=======================================================================\n");
    }

    public final static void clearConsole() {

        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");

            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Tratar Exceptions
        }
    }

}
