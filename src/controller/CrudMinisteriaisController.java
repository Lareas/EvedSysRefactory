/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbNomeCrudMin;
import static main.Login.gbCrudMin;
import static main.Login.gbRegCadGer;
import com.jfoenix.controls.JFXButton;
import entities.Dadocadastrogeralministeriais;
import funcoes.ComboBoxAutoComplete;
import static funcoes.MyFunc.mostraMsg;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jpa_controler.DadocadastrogeralministeriaisJpaController;
import jpa_controler.MinisteriaisJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class CrudMinisteriaisController implements Initializable {

    @FXML
    private ComboBox<String> cbMinisterio;
    @FXML
    private Label lblIns;
    @FXML
    private Label lblEdi;

    private ObservableList<String> dadosMI;
    private MinisteriaisJpaController jpaMI;
    private DadocadastrogeralministeriaisJpaController jpaCon;

    @FXML
    private JFXButton buRefDen;
    Dadocadastrogeralministeriais reg_atual = new Dadocadastrogeralministeriais();
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbMinisterio.setItems(getNomeMinisterio());
        new ComboBoxAutoComplete<String>(cbMinisterio, "");

        if (gbCrudMin == 1) { // INSERE
            lblIns.setVisible(true);
            lblEdi.setVisible(false);
        } else if (gbCrudMin == 2) { // EDITA
            lblIns.setVisible(false);
            lblEdi.setVisible(true);
            cbMinisterio.getSelectionModel().select(gbNomeCrudMin);
        }
    }

    public ObservableList<String> getNomeMinisterio() {
        jpaMI = new MinisteriaisJpaController();
        dadosMI = FXCollections.observableArrayList(jpaMI.getNomeDosMinisteriosF());

        if (dadosMI == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosMI;
        }
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {

        if (cbMinisterio.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Preenche!", "", 2);
        } else {
            if (verifPodeGravar(gbRegCadGer.getDadoCadastroGeralId(), cbMinisterio.getSelectionModel().getSelectedItem()) == false) {
                mostraMsg("Não é possível gravar!", "O aluno já tem esse ministério.", 2);
            } else {
//
                jpaCon = new DadocadastrogeralministeriaisJpaController();
                Dadocadastrogeralministeriais obj = new Dadocadastrogeralministeriais();
                if (gbCrudMin == 1) { // está inserindo
                    try {

                        obj.setDadoCadastroGeralId(gbRegCadGer.getDadoCadastroGeralId());
                        obj.setMinisteriais(cbMinisterio.getSelectionModel().getSelectedItem());
                        jpaCon.create(obj);

                        // FECHA JANELA
                        gbCrudMin = 0;
                        Stage stage = (Stage) buConfirma.getScene().getWindow();
                        stage.close();

                    } catch (Exception e) {
                        System.out.println(obj);
                        System.out.println("\n============================");
                        System.out.println(e);
                        System.out.println("============================\n");
                        mostraMsg("Erro ao gravar a edição dos dados.", ""+e, 2);
                    }

                } else {
                    if (gbCrudMin == 2) { // edita
                        try {
                            Integer qual = jpaCon.getCodDocMin(gbRegCadGer.getDadoCadastroGeralId(), gbNomeCrudMin);
                            obj.setDadoCadastroGeralMinisteriaisId(qual);
                            obj.setDadoCadastroGeralId(gbRegCadGer.getDadoCadastroGeralId());
                            obj.setMinisteriais(cbMinisterio.getSelectionModel().getSelectedItem());

                            jpaCon.edit(obj);
                            reg_atual = null;
                            mostraMsg("Dados gravados com sucesso.", "", 1);

                            // FECHA JANELA
                            gbCrudMin = 0;
                            Stage stage = (Stage) buConfirma.getScene().getWindow();
                            stage.close();

                        } catch (Exception e) {
                            System.out.println(obj);
                            System.out.println("\n============================");
                            System.out.println(e);
                            System.out.println("============================\n");
                            mostraMsg("Erro ao gravar novos dados.", ""+e, 2);
                        }

                    }

                }

            }
        }
    }

    @FXML
    private void clicouCancela(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Cancelar?");
        alert.setContentText("Se você cancelar esse procedimento? Os dados não serão gravados.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            gbCrudMin = 0;

            // FECHA JANELA
            Stage stage = (Stage) buConfirma.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void clicouRefreshDen(ActionEvent event) {
    }

    private Boolean verifPodeGravar(Integer codAluno, String ministerio) {
        Boolean podegravar = true;

        DadocadastrogeralministeriaisJpaController jpaDCM = new DadocadastrogeralministeriaisJpaController();
        List lis = jpaDCM.getDadocadastrogeralministeriaisPesqExac(codAluno, ministerio);

        Dadocadastrogeralministeriais prog;
        if (gbCrudMin == 1) {

            for (int i = 0; i < lis.size(); i++) {
                prog = (Dadocadastrogeralministeriais) lis.get(i);

                if ((prog.getDadoCadastroGeralId() != reg_atual.getDadoCadastroGeralId()) && (prog.getMinisteriais() != reg_atual.getMinisteriais())) {
                    podegravar = false;
                }
            }
        } else {
            if (gbCrudMin == 2) {
                if (lis.size() > 0) {
                    podegravar = false;
                }
            }
        }

        return podegravar;
    }
}
