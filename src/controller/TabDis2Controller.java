/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.myCondics;
import static main.Login.gbRegDisc;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Disciplina;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import static funcoes.MyFunc.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.TabDis2JpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class TabDis2Controller implements Initializable {

    @FXML
    private TableView<Disciplina> tvPesquisa;

    @FXML
    private TableColumn<Disciplina, Integer> tc1;

    @FXML
    private TableColumn<Disciplina, String> tc2;

    @FXML
    private TableColumn<Disciplina, String> tc3;

    @FXML
    private TableColumn<Disciplina, String> tc4;

    @FXML
    private TableColumn<Disciplina, String> tc5;

    @FXML
    private TableColumn<Disciplina, String> tc6;

    @FXML
    private TableColumn<Disciplina, String> tc7;

    @FXML
    private TextField lblRegistros;

    // --------------------
    // VARIÁVEIS LOCAIS
    // --------------------
    private ObservableList<Disciplina> dados;
    private TabDis2JpaController jpaCon;

    Disciplina reg_atual = new Disciplina();

    private final String[] aCampos = {"disciplinaId", "disciplina", "especificidade", "credito", "cargaHoraria", "descricao", "ementa"};
    private final String[] myCampos = {"Código", "Disciplina", "Especificidade", "Crédito", "Carga Horária", "Descrição", "Ementa"};
    private final String[] aTipo = {"I", "S", "S", "I", "I", "S", "S"};

    @FXML
    private ToggleGroup group1;
    @FXML
    private JFXComboBox<String> cbIndice2;
    @FXML
    private JFXComboBox<String> cbCondicao2;
    @FXML
    private JFXTextField txtPesq2;
    @FXML
    private RadioButton rbE;
    @FXML
    private RadioButton rbOu;
    @FXML
    private JFXComboBox<String> cbIndice1;
    @FXML
    private JFXComboBox<String> cbCondicao1;
    @FXML
    private JFXTextField txtPesq1;
    @FXML
    private TextField resPesq;
    @FXML
    private ProgressIndicator piFiltrados;
    @FXML
    private Label lblFiltrados;
    @FXML
    private JFXButton buInsere;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jpaCon = new TabDis2JpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO
        lblFiltrados.setAlignment(Pos.CENTER);
        lblFiltrados.setText("");
        piFiltrados.setProgress(0);
        initColumns();

        // Inicializa ComboBox do Índice de Pesquisa
        cbIndice1.getItems().addAll(myCampos);
        cbIndice1.getSelectionModel().select("Disciplina");
        cbIndice2.getItems().addAll(myCampos);
        cbIndice2.getSelectionModel().selectFirst();

        cbCondicao1.getItems().addAll(myCondics);
        cbCondicao1.getSelectionModel().selectFirst();
        cbCondicao2.getItems().addAll(myCondics);
        cbCondicao2.getSelectionModel().selectFirst();

        tvPesquisa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        abrePrograma();
                    }
                }
            }
        });
    }

    private void abrePrograma() {
        if (tvPesquisa.getSelectionModel().getSelectedItem() != null) {
            reg_atual = tvPesquisa.getSelectionModel().getSelectedItem();

            // PASSA REGISTRO SELECIONADO PARA A VARIÁVEL GLOBAL rgRegDisc, que será usado na tela TabelaDisciplinas
            gbRegDisc = reg_atual; //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

            tvPesquisa.setItems(getMeusDados());

            // ABRE CADASTRODISCIPLINA (FILHO) DO REGISTRO SELECIONADO (DISCIPLINA-PAI)
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/TabelaDisciplinas.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setTitle("Disciplina");
                stage.setScene(new Scene(parent));
                stage.show();

                // LibraryAssistantUtil.setStageIcon(stage);
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("=====================");
                System.out.println(ex);
                System.out.println("=====================");

            }
        }
    }

    @FXML
    private void clicouAbre(ActionEvent event) {
        abrePrograma();
    }

    public void initColumns() {
//        if (gbUser.getPerfilNome().getCodPerfil() != 1) {
//            tc1.setVisible(false);
//            resPesq.setVisible(false);
//        } else {
//            tc1.setCellValueFactory(new PropertyValueFactory<>("disciplinaId"));
//        }
        tc2.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("credito"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("especificidade"));
        tc6.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tc7.setCellValueFactory(new PropertyValueFactory<>("ementa"));
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
    }

    public ObservableList<Disciplina> getMeusDados() {

        jpaCon = new TabDis2JpaController();
        dados = FXCollections.observableArrayList(jpaCon.findDisciplinaEntities());
        txtPesq1.setText("");

        int rowcount = jpaCon.getDisciplinaCount();
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

    public ObservableList<Disciplina> getMinhaPesqDados(String pesq) {

        jpaCon = new TabDis2JpaController();
        String entidade = "Disciplina";
        Long lTodos = contaTodos(entidade);
        Long lFiltrados = contaFiltrados(entidade, " WHERE " + pesq);
        double percent;
        percent = ((100 * lFiltrados) / lTodos);
        piFiltrados.setProgress(percent / 100);
        lblFiltrados.setText(lFiltrados + " de " + lTodos);

        dados = FXCollections.observableArrayList(jpaCon.getDisciplinaPesq(" WHERE " + pesq));
        lblRegistros.setText(lFiltrados + " registro(s) filtrados do total de " + lTodos + " registros - (" + percent + "%)");

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }
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
    private void clicouMinhaPesq(ActionEvent event) {
        // ANALIZA CONSISTÊNCIA DA PESQUISA
        int cte = 0;
        String warn = "";
        String resp = ""; // montar string da pesquisa
        String resp2 = "";
        String respU = "";

        // CONSISTÊNCIA DOS CAMPOS
        if (cbCondicao1.getSelectionModel().getSelectedItem().trim().length() < 1) {
            warn = warn + "O campo <condição 1> não pode ficar vazio.\n";
            cte++;
        }

        if (txtPesq1.getText().trim().length() < 1) {
            warn = warn + "O campo <conteúdo a pesquisar 1> não pode ficar vazio.\n";
            cte++;
        }

        if ((rbE.isSelected()) || (rbOu.isSelected())) {

            // CONSISTÊNCIA DOS CAMPOS
            if (cbIndice2.getSelectionModel().getSelectedItem().trim().length() < 1) {
                warn = warn + "O campo <campo a pesquisar 2> não pode ficar vazio.\n";
                cte++;
            }

            if (cbCondicao2.getSelectionModel().getSelectedItem().trim().length() < 1) {
                warn = warn + "O campo <condição 2> não pode ficar vazio.\n";
                cte++;
            }

            if (txtPesq2.getText().trim().length() < 1) {
                warn = warn + "O campo <conteúdo a pesquisar 2> não pode ficar vazio.\n";
                cte++;
            }

        }

        // VERIFICA SE HÁ CAMPOS E CONDIÇÕES INCOMPATÍVEIS
        // HÁ ERROS DE PREENCHIMENTO?
        if (cte > 0) {
            // há erros
            mostraMsg("Há erro(s) de preenchimento.", warn, 2);
        } else {
            resp = montaCondic(aCampos[cbIndice1.getSelectionModel().getSelectedIndex()],
                    cbCondicao1.getSelectionModel().getSelectedIndex(), txtPesq1.getText(), aTipo[cbIndice1.getSelectionModel().getSelectedIndex()]);
            if ((rbE.isSelected()) || (rbOu.isSelected())) {

                if ((rbE.isSelected())) {
                    resp = "(" + resp + ") AND (";
                } else {
                    resp = "(" + resp + ") OR (";
                }

                resp2 = montaCondic(aCampos[cbIndice2.getSelectionModel().getSelectedIndex()],
                        cbCondicao2.getSelectionModel().getSelectedIndex(), txtPesq2.getText(), aTipo[cbIndice2.getSelectionModel().getSelectedIndex()]);
                resp = resp + resp2 + ")";
            }

            resPesq.setText("Pesquisa: " + resp);//aCampos[cbIndice1.getSelectionModel().getSelectedIndex()]

            tvPesquisa.setItems(getMinhaPesqDados(resp));
//        }
        }
    }

    @FXML
    private void clicouMinhaPesqEnter(KeyEvent event) {
    }

    @FXML
    private void clicouInsere(ActionEvent event) {
        // ABRE CADASTRODISCIPLINA (FILHO) DO REGISTRO SELECIONADO (DISCIPLINA-PAI)
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/TabelaDisciplinas.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Disciplina");
            stage.setScene(new Scene(parent));
            stage.show();

            // LibraryAssistantUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("=====================");
            System.out.println(ex);
            System.out.println("=====================");

        }
    }

}
