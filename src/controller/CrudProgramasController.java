/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbNomeCrudPro;
import static main.Login.gbCrudProg;
import static main.Login.gbRegCad;
import static main.Login.gbUser;
import static main.Login.gbSoProgsAtivos;
import com.jfoenix.controls.JFXButton;
import entities.EsGradedisprg;
import funcoes.ComboBoxAutoComplete;
import static funcoes.MyFunc.mostraMsg;
import java.net.URL;
import java.util.Date;
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
import jpa_controler.CursoJpaController;
import jpa_controler.EsGradedisprgJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class CrudProgramasController implements Initializable {

    @FXML
    private ComboBox<String> cbProgramas;
    @FXML
    private Label lblIns;
    @FXML
    private Label lblEdi;
    @FXML
    private JFXButton buRefDen;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;

    private ObservableList<String> dadosPro;
    private CursoJpaController jpaPro;
    private EsGradedisprgJpaController jpaCon;

    EsGradedisprg reg_atual = new EsGradedisprg();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbProgramas.setItems(getNomePrograma());
        new ComboBoxAutoComplete<String>(cbProgramas, "Programas");

        if (gbCrudProg == 1) { // INSERE
            lblIns.setVisible(true);
            lblEdi.setVisible(false);
        } else if (gbCrudProg == 2) { // EDITA
            lblIns.setVisible(false);
            lblEdi.setVisible(true);
            cbProgramas.getSelectionModel().select(gbNomeCrudPro);
        }
        
    }

    public ObservableList<String> getNomePrograma() {
        jpaPro = new CursoJpaController();
        if (gbSoProgsAtivos) {
            dadosPro = FXCollections.observableArrayList(jpaPro.getNomeDosCursosAtivos());
        } else {
            dadosPro = FXCollections.observableArrayList(jpaPro.getNomeDosCursos());
        }
        
        

        if (dadosPro == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosPro;
        }
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {

        if (cbProgramas.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Preenche!", "", 2);
        } else {
            if (verifPodeGravar(gbRegCad.getCadastroDisciplinaId(), cbProgramas.getSelectionModel().getSelectedItem()) == false) {
                mostraMsg("Esse programa já está cadastrado nessa disciplina.", "", 4);
            } else {
                jpaCon = new EsGradedisprgJpaController();
                EsGradedisprg obj = new EsGradedisprg();
                if (gbCrudProg == 1) { // está inserindo
                    try {

                        obj.setNomeCadDisciplina(gbRegCad);

                        jpaPro = new CursoJpaController();
                        obj.setNomePrograma(jpaPro.getObjeto(cbProgramas.getSelectionModel().getSelectedItem().toString()));
                        obj.setDataInc(new Date());
                        obj.setCodUserInc(gbUser.getCoduser());

                        jpaCon = new EsGradedisprgJpaController();
                        jpaCon.create(obj);

                        // FECHA JANELA
                        gbCrudProg = 0;
                        Stage stage = (Stage) buConfirma.getScene().getWindow();
                        stage.close();

                    } catch (Exception e) {
                        System.out.println(obj);
                        System.out.println("\n============================");
                        System.out.println(e);
                        System.out.println("============================\n");
                        mostraMsg("Erro ao gravar a edição dos dados.", "" + e, 2);
                    }

                } else {
                    if (gbCrudProg == 2) { // EDITA
                        try {
//                            jpaCon = new DadocadastrogeralministeriaisJpaController();
//                            Dadocadastrogeralministeriais obj = new Dadocadastrogeralministeriais();

                            if (cbProgramas.getSelectionModel().getSelectedItem() != null) {
                                jpaCon = new EsGradedisprgJpaController();
                                String progselec = cbProgramas.getSelectionModel().getSelectedItem().toString();
                                EsGradedisprg objDisProg = jpaCon.getCodObjeto(reg_atual.getNomeCadDisciplina().getCadastroDisciplinaId(), progselec);
                                obj.setCodgradedisprg(objDisProg.getCodgradedisprg());
                                obj.setNomeCadDisciplina(objDisProg.getNomeCadDisciplina());
                                obj.setNomePrograma(objDisProg.getNomePrograma());
                                obj.setDataAlt(new Date());
                                obj.setCodUserAlt(gbUser.getCoduser());

                                jpaCon.edit(obj);
                                reg_atual = null;
                                mostraMsg("Dados gravados com sucesso.", "", 1);
                            }

                            // FECHA JANELA
                            gbCrudProg = 0;
                            Stage stage = (Stage) buConfirma.getScene().getWindow();
                            stage.close();

                        } catch (Exception e) {
                            System.out.println(obj);
                            System.out.println("\n============================");
                            System.out.println(e);
                            System.out.println("============================\n");
                            mostraMsg("Erro ao gravar novos dados.", "" + e, 2);
                        }

                    }

                }
//                }

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
            gbCrudProg = 0;

            // FECHA JANELA
            Stage stage = (Stage) buConfirma.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void clicouRefreshDen(ActionEvent event) {
    }

    private Boolean verifPodeGravar(Integer cod_dis, String programa) {
        Boolean podegravar = true;

        EsGradedisprgJpaController jpaDCM = new EsGradedisprgJpaController();
        List lis = jpaDCM.getListExac(cod_dis, programa);

        EsGradedisprg prog;
        if (gbCrudProg == 1) {

            for (int i = 0; i < lis.size(); i++) {
                prog = (EsGradedisprg) lis.get(i);

                if ((prog.getCodgradedisprg() != reg_atual.getCodgradedisprg()) && (prog.getNomePrograma() != reg_atual.getNomePrograma())) {
                    podegravar = false;
                }
            }
        } else {
            if (gbCrudProg == 2) {
                if (lis.size() > 0) {
                    podegravar = false;
                }
            }
        }

        return podegravar;
    }
}
