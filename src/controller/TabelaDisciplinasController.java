/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbRegDisc;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Cadastrodisciplina;
import entities.Disciplina;
import entities.Turma;
import entities.Turno;
import funcoes.ComboBoxAutoComplete;
import static funcoes.MyFunc.getContaIgual;
import static funcoes.MyFunc.mostraMsg;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import jpa_controler.CadastrodisciplinaJpaController;
import jpa_controler.DisciplinaJpaController;
import jpa_controler.EspecificidadeJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class TabelaDisciplinasController implements Initializable {

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
    private JFXTextField edNomeProg;
    @FXML
    private JFXTextField edCredito;
    @FXML
    private JFXTextField edCargaHoraria;
    @FXML
    private JFXTextArea edDescricao;
    @FXML
    private JFXTextArea edEmenta;
    @FXML
    private RadioButton rb1Sem;
    @FXML
    private RadioButton rb2Sem;
    @FXML
    private TableView<Cadastrodisciplina> tvPesquisa;
    @FXML
    private TableColumn<Cadastrodisciplina, String> tc1;
    @FXML
    private TableColumn<Cadastrodisciplina, Short> tc2;
    @FXML
    private TableColumn<Cadastrodisciplina, Short> tc3;
    @FXML
    private TableColumn<Cadastrodisciplina, Short> tc4;
    @FXML
    private TableColumn<Cadastrodisciplina, Short> tc5;
    @FXML
    private TableColumn<Cadastrodisciplina, Turno> tc6;
    @FXML
    private TableColumn<Cadastrodisciplina, Turma> tc7;
    @FXML
    private TableColumn<Cadastrodisciplina, String> tc8;
    @FXML
    private TableColumn<Cadastrodisciplina, Boolean> tc9;
    @FXML
    private TableColumn<Cadastrodisciplina, Boolean> tc10;
    @FXML
    private TableColumn<Cadastrodisciplina, Boolean> tc11;
    @FXML
    private TableColumn<Cadastrodisciplina, Boolean> tc12;
    @FXML
    private TableColumn<Cadastrodisciplina, Boolean> tc13;
    @FXML
    private TableColumn<Cadastrodisciplina, Boolean> tc14;
    @FXML
    private TableColumn<Cadastrodisciplina, Boolean> tc15;
    @FXML
    private TableColumn<Cadastrodisciplina, String> tc16;
    @FXML
    private Label lblTitDisc;
    @FXML
    private ComboBox<String> cbArea;
    @FXML
    private TextField lblRegistros;
    @FXML
    private Tab tabPesquisa;
    @FXML
    private ToggleGroup g1;
    @FXML
    private ComboBox<Integer> cbAnoFiltro;

    private ObservableList<Cadastrodisciplina> dados;
    private CadastrodisciplinaJpaController jpaCon;
    private DisciplinaJpaController jpaDis;

    // PARA cbArea
    private ObservableList<String> dadosEsp;
    private EspecificidadeJpaController jpaConEsp;

    // PARA cbAnoFiltro
    private ObservableList<Integer> dadosAno;
    private CadastrodisciplinaJpaController jpaAno;

    //   Cadastrodisciplina reg_atual = new Cadastrodisciplina();
    Disciplina reg_disc = new Disciplina();

    private EspecificidadeJpaController jpaAR;
    private boolean inserindo, editando;

    private int iSemestreHoje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa ComboBox do Índice de Pesquisa
        cbArea.setItems(getNomeEspecs());

        // CARREGA CAMPOS DA DISCIPLINA SELECIONADA
        new ComboBoxAutoComplete<String>(cbArea, "");
        reg_disc = gbRegDisc;
        if (gbRegDisc == null) {
            // ENTRA INSERINDO
            reg_disc = new Disciplina();
            limpaCampos(); // para quem vai requestfocus;
            ligaEdicao();
           // cbArea.setItems(getNomeEspecs());
            inserindo = true;
            edNomeProg.requestFocus();
        } else {
            lblTitDisc.setText("Disciplina: " + reg_disc.getDisciplina());

//            tc9.setCellFactory(tc -> new TableCell<Cadastrodisciplina, Boolean>() {
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item.booleanValue() ? "S" : "n");
//                }
//            });
//            tc10.setCellFactory(tc -> new TableCell<Cadastrodisciplina, Boolean>() {
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item.booleanValue() ? "S" : "n");
//                }
//            });
//            tc11.setCellFactory(tc -> new TableCell<Cadastrodisciplina, Boolean>() {
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item.booleanValue() ? "S" : "n");
//                }
//            });
//            tc12.setCellFactory(tc -> new TableCell<Cadastrodisciplina, Boolean>() {
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item.booleanValue() ? "S" : "n");
//                }
//            });
//            tc13.setCellFactory(tc -> new TableCell<Cadastrodisciplina, Boolean>() {
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item.booleanValue() ? "S" : "n");
//                }
//            });
//            tc14.setCellFactory(tc -> new TableCell<Cadastrodisciplina, Boolean>() {
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item.booleanValue() ? "S" : "n");
//                }
//            });
//            tc15.setCellFactory(tc -> new TableCell<Cadastrodisciplina, Boolean>() {
//                @Override
//                protected void updateItem(Boolean item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? null : item.booleanValue() ? "S" : "n");
//                }
//            });
            // CARREGA DADOS DA TELA ANTERIOR ATRAVÉS DA VARIÁVEL GLOBAL gbRegDisc
            preencheEdsComObj();

            // DETERMINA SEMESTRE PARA MARCAR PESQUISA
            Calendar cal = Calendar.getInstance();
            System.out.println(cal.getTime());

            int mesHoje = cal.get(Calendar.MONTH) + 1;
            int anoHoje = cal.get(Calendar.YEAR);
            if (mesHoje > 6) {
                rb2Sem.selectedProperty().setValue(true);
            } else {
                rb1Sem.selectedProperty().setValue(true);
            }

            jpaCon = new CadastrodisciplinaJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO

            inserindo = false;
            editando = false;
            desligaEdicao();
            initColumns();

            // só para usuário com permissão
            buApaga.setVisible(true);

        }
    }

    public void initColumns() {
        tc1.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("credito"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("anoLetivo"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("semestreId"));
        tc6.setCellValueFactory(new PropertyValueFactory<>("nomeDoTurno.turno"));
        tc7.setCellValueFactory(new PropertyValueFactory<>("nomeDaTurma"));
        tc8.setCellValueFactory(new PropertyValueFactory<>("localidade"));
        tc9.setCellValueFactory(new PropertyValueFactory<>("domingo"));
        tc10.setCellValueFactory(new PropertyValueFactory<>("segunda"));
        tc11.setCellValueFactory(new PropertyValueFactory<>("terca"));
        tc12.setCellValueFactory(new PropertyValueFactory<>("quarta"));
        tc13.setCellValueFactory(new PropertyValueFactory<>("quinta"));
        tc14.setCellValueFactory(new PropertyValueFactory<>("sexta"));
        tc15.setCellValueFactory(new PropertyValueFactory<>("sabado"));
        tc16.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        // MOSTRA CHECKBOX
//        tc9.setCellFactory(CheckBoxTableCell.forTableColumn(tc9));
//        tc10.setCellFactory(CheckBoxTableCell.forTableColumn(tc10));
//        tc11.setCellFactory(CheckBoxTableCell.forTableColumn(tc11));
//        tc12.setCellFactory(CheckBoxTableCell.forTableColumn(tc12));
//        tc13.setCellFactory(CheckBoxTableCell.forTableColumn(tc13));
//        tc14.setCellFactory(CheckBoxTableCell.forTableColumn(tc14));
//        tc15.setCellFactory(CheckBoxTableCell.forTableColumn(tc15));
        tvPesquisa.setItems(getMeusDados(reg_disc.getDisciplinaId(), ""));
        tvPesquisa.refresh();
    }

    public ObservableList<Cadastrodisciplina> getMeusDados(int codDisciplina, String filtro) {

        if (filtro != "") {
            filtro = " AND (" + filtro + ")";
        }

        //  jpaCon = new CadastrodisciplinaJpaController();
        dados = FXCollections.observableArrayList(jpaCon.getMeuCadDisPes(codDisciplina, filtro));
        lblRegistros.setText("Total de registros: " + tvPesquisa.itemsProperty().get().size()); //rs2.getString(1));

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }

    }

//    public ObservableList<Cadastrodisciplina> getMeusDadosFil(String filtro) {
//
//        //  jpaCon = new CadastrodisciplinaJpaController();
//        dados = FXCollections.observableArrayList(jpaCon.getMeuCadDisFil(filtro));
//
//        Long rowcount = contaFiltrados("Cadastrodisciplina", filtro);
//        if (rowcount > 1) {
//            lblRegistros.setText(" " + rowcount + " registros ");
//        } else {
//            lblRegistros.setText(" " + rowcount + " registro ");
//        }
//
//        if (dados == null) {
//            return FXCollections.observableArrayList();
//        } else {
//            return dados;
//        }
//
//    }
    public ObservableList<String> getNomeEspecs() {
        jpaConEsp = new EspecificidadeJpaController();
        dadosEsp = FXCollections.observableArrayList(jpaConEsp.getNomeDasEspecificidades());

        if (dadosEsp == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosEsp;
        }
    }

    @FXML
    private void clicouInsere(ActionEvent event) {
        reg_disc = null;
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
            warn = warn + "O campo <Nome do Curso> não pode ficar vazio.\n";
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
                alert.setHeaderText("Confirma");
                alert.setContentText("Confirma a gravação dos dados?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {

                    if (editando == true) {
                        try {

                            preencheObjComEds(); // PREENCHE OBd COM Eds
                            jpaDis.edit(reg_disc);
                            editando = false;

                            desligaEdicao();
                            initColumns();
                            mostraMsg("Dados gravados com sucesso.", "", 3);

                        } catch (Exception e) {
                            System.out.println(reg_disc);
                            System.out.println("\n============================");
                            System.out.println(e);
                            System.out.println("============================\n");
                            mostraMsg("Erro ao gravar a edição dos dados.", "" + e, 2);
                        }
                    } else {
                        if (inserindo == true) {
                            try {

                                // PREENCHE OBd COM Eds
                                Disciplina obj = new Disciplina();
                                preencheObjComEds();
                                obj = reg_disc;
                                lblTitDisc.setText("Disciplina: " + edNomeProg.getText());
                                jpaDis.create(obj);
                                reg_disc = obj;
                                inserindo = false;

                                desligaEdicao();
                                tvPesquisa.setItems(null);
                                tvPesquisa.refresh();
                                // PREENCHE OBd COM Eds
                                mostraMsg("Dados gravados com sucesso.", "", 3);
                            } catch (Exception e) {
                                System.out.println(reg_disc);
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
        // SingleSelectionModel<Tab> selectionModel = tabFX.getSelectionModel();
        //  selectionModel.select(1); //select by index starting with 0
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
        edCredito.setEditable(true);
        edCargaHoraria.setEditable(true);
        edDescricao.setEditable(true);
        edEmenta.setEditable(true);
        cbArea.setDisable(false);
    }

    public void desligaEdicao() {

        // habilita botões para inserção e deleção
        cbArea.setStyle("-fx-opacity: 1;");
        buInsere.setDisable(false);
        buEdita.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buApaga.setDisable(false);
        tabPesquisa.setDisable(false);

        edNomeProg.setEditable(false);
        edCredito.setEditable(false);
        edCargaHoraria.setEditable(false);
        edDescricao.setEditable(false);
        edEmenta.setEditable(false);
        cbArea.setDisable(true);
    }

    public void preencheEdsComObj() {
        lblTitDisc.setText("Disciplina: " + reg_disc.getDisciplina());
        edNomeProg.setText(reg_disc.getDisciplina());
        edCredito.setText(String.valueOf(reg_disc.getCredito()));
        edCargaHoraria.setText(String.valueOf(reg_disc.getCargaHoraria()));
        edDescricao.setText(reg_disc.getDescricao());
        edEmenta.setText(reg_disc.getEmenta());


        // COLOCAR NO COMBOBOX O VALOR SELECIONADO      
        cbArea.getSelectionModel().select((reg_disc.getNomeEspecificidade() != null) ? reg_disc.getNomeEspecificidade().getEspecificidade() : null);
        cbAnoFiltro.setItems(getAnoDistinct(reg_disc.getDisciplinaId()));
    }

    public void preencheObjComEds() {
        reg_disc.setDisciplina(edNomeProg.getText());
        reg_disc.setCredito(Short.valueOf(edCredito.getText()));
        reg_disc.setCargaHoraria(Float.valueOf(edCargaHoraria.getText()));
        reg_disc.setDescricao(edDescricao.getText());
        reg_disc.setEmenta(edEmenta.getText());
        

        if (cbArea.getSelectionModel().getSelectedItem() != null) {
            jpaAR = new EspecificidadeJpaController();
            reg_disc.setNomeEspecificidade(jpaAR.getObjeto(cbArea.getSelectionModel().getSelectedItem()));
        }
    }

    public void limpaCampos() {
        edNomeProg.setText("");
        edCredito.setText("");
        edCargaHoraria.setText("");
        edDescricao.setText("");
        edEmenta.setText("");
        cbArea.setValue(null);
    }

    public ObservableList<Integer> getAnoDistinct(int codDisciplina) {

        jpaAno = new CadastrodisciplinaJpaController();
        dadosAno = FXCollections.observableArrayList(jpaAno.getAnosDistinct(codDisciplina));

        if (dadosAno == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosAno;
        }

    }

    @FXML
    private void clicouApaga(ActionEvent event) {
//        if (gbUser.getPerfilNome().getCodPerfil() != 1) {
//            mostraMsg("Ação não permitida!", "Você não tem privilégios para apagar esse registro.", 2);
//        } else {
//
//            if (!edNomeProg.getText().equals("")) {
//                Alert alert = new Alert(AlertType.CONFIRMATION);
//                alert.setHeaderText("Confirmação - apagar este registro");
//                alert.setContentText("Deseja realmente apagar esse registro?\nOs dados serão perdidos!");
//                Optional<ButtonType> option = alert.showAndWait();
//
//                if (option.get() == ButtonType.OK) {
//                    try {
//                        jpaDis.destroy(reg_disc.getDisciplinaId());
//
//                        desligaEdicao();
//                        limpaCampos();
//                        initColumns();
//                        mostraMsg("Dados apagados com sucesso", "", 3);
//                        // PREENCHE OBd COM Eds
//
//                    } catch (Exception e) {
//                        System.out.println(e);
////                        mostraMsg("Erro ao apagar registro", (String) e, 2);
//
//                    }
//                }
//            }
//        }
    }

    private Boolean verifPodeGravar(String pesq) {
        Boolean podegravar = true;

        Long achados = 0L;
        jpaDis = new DisciplinaJpaController();
        List lis = jpaDis.getPesquisaPesqExac(pesq);

        Disciplina disc;
        if (editando == true) {

            for (int i = 0; i < lis.size(); i++) {
                disc = (Disciplina) lis.get(i);
                if (!disc.getDisciplinaId().equals(reg_disc.getDisciplinaId())) {
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
    private void clicouAbre(ActionEvent event) {
    }

    @FXML
    private void clicouFiltra(ActionEvent event) {

        //getMeuCadDisFil(fil);
        String filAno = "";
        String filSem = "";
        String filtro = "";

        if (cbAnoFiltro.getSelectionModel().getSelectedItem() != null) {
            filAno = "s.anoLetivo = '" + cbAnoFiltro.getSelectionModel().getSelectedItem() + "' ";
        }

        if (rb1Sem.isSelected() == true) {
            filSem = "s.semestreId = '1'";
        } else {
            if (rb2Sem.isSelected() == true) {
                filSem = "s.semestreId = '2'";
            }
        }

        if ((filAno != "") && (filSem != "")) {
            filtro = "( " + filAno + ") AND ( " + filSem + ") ";
        } else {
            if (filAno != "") {
                filtro = filAno;
            } else {
                if (filSem != "") {
                    filtro = filSem;
                }
            }
        }

        if (filtro == "") {
            mostraMsg("Informação", "Selecione o ANO e/ou o SEMESTRE a filtrar", 3);
        } else {
            tvPesquisa.setItems(getMeusDados(reg_disc.getDisciplinaId(), filtro));
            tvPesquisa.refresh();
        }
    }

    @FXML
    private void clicouLimpa(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmação");
        alert.setContentText("Deseja limpar o filtro?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            initColumns();
        }
    }

}
