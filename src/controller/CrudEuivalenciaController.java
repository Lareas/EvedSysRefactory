/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbCrudEqui;
import static main.Login.gbEquivalencia;
import static main.Login.gbUser;
import com.jfoenix.controls.JFXButton;
import static controller.EquivalenciaController.gbRegEqDet;
import entities.EsEquivalenciadet;
import static funcoes.MyFunc.isFloat;
import static funcoes.MyFunc.isInteger;
import static funcoes.MyFunc.mostraMsg;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jpa_controler.EsEquivalenciadetJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class CrudEuivalenciaController implements Initializable {

    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private Label lblTipo;
    @FXML
    private TextField ttfInstitu;
    @FXML
    private TextField ttfDisci;
    @FXML
    private TextField ttfCarga;
    @FXML
    private TextField ttfCredito;
    @FXML
    private TextField ttfAno;
    @FXML
    private TextField ttfNOta;

    private EsEquivalenciadetJpaController jpaEdDet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ttfInstitu.setText(gbRegEqDet.getNomeInstitu());
        if (gbCrudEqui == 2) {
            ttfDisci.setText(gbRegEqDet.getNomeDisciplina());
            ttfCarga.setText(String.valueOf(gbRegEqDet.getCarga()));
            ttfCredito.setText(String.valueOf(gbRegEqDet.getCredito()));
            ttfAno.setText(String.valueOf(gbRegEqDet.getAno()));
            ttfNOta.setText(gbRegEqDet.getNota());
        }
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
        if (ttfInstitu.getText() == null) {
            mostraMsg("Preencha o campo <Instituição>", "", 4);
        } else {
            if (ttfDisci.getText() == null) {
                mostraMsg("Preencha o campo <Disciplina>", "", 4);
            } else {
                if (ttfCarga.getText() == null) {
                    mostraMsg("Preencha o campo <Carga Horária>", "", 4);
                } else {
//                    if (ttfCredito.getText() == null) {
//                        mostraMsg("Preencha o campo <Créditos>", "", 4);
//                    } else {
//                        if (ttfAno.getText() == null) {
//                            mostraMsg("Preencha o campo <Ano>", "", 4);
//                        } else {
                            if (ttfNOta.getText() == null) {
                                mostraMsg("Preencha o campo <Nota>", "", 4);
                            } else {
                                if (!isFloat(ttfCarga)) {
                                    mostraMsg("O campo <Carga Horária> não é numérico", "", 2);
                                } else {
//                                    if (!isInteger(ttfCredito)) {
//                                        mostraMsg("O campo <Crédito> não é inteiro", "", 2);
//                                    } else {
//                                        if (!isInteger(ttfAno)) {
//                                            mostraMsg("O campo <Ano> não é inteiro", "", 2);
//                                        } else {

                                            // alimenta gbRegEqDet
                                            gbRegEqDet.setNomeInstitu(ttfInstitu.getText());
                                            gbRegEqDet.setNomeDisciplina(ttfDisci.getText());
                                            gbRegEqDet.setCarga(Float.valueOf(ttfCarga.getText()));
                                            gbRegEqDet.setCredito(ttfCredito.getText());
                                            gbRegEqDet.setAno(ttfAno.getText());
                                            gbRegEqDet.setNota(ttfNOta.getText());

                                            try {
                                                EsEquivalenciadet det = new EsEquivalenciadet();
                                                det.setCodEquivalencia(gbEquivalencia.getCodEquivalencia());
                                                det.setInstituicao(gbRegEqDet.getNomeInstitu());
                                                det.setDisciplina(gbRegEqDet.getNomeDisciplina());
                                                det.setCarga(BigDecimal.valueOf(gbRegEqDet.getCarga()));
                                                det.setCredito(gbRegEqDet.getCredito());
                                                det.setAno(gbRegEqDet.getAno());
                                                det.setNota(gbRegEqDet.getNota());

                                                jpaEdDet = new EsEquivalenciadetJpaController();

                                                if (gbCrudEqui == 1) {
                                                    det.setDataInc(new Date());
                                                    det.setCodUserInc(gbUser.getCoduser());
                                                    jpaEdDet.create(det);
                                                } else {
                                                    det.setCodEquivalenciaDet(gbRegEqDet.getCodEquiDet());
                                                    det.setDataAlt(new Date());
                                                    det.setCodUserAlt(gbUser.getCoduser());
                                                    jpaEdDet.edit(det);
                                                }
                                                Stage stage = (Stage) buConfirma.getScene().getWindow();
                                                stage.close();

                                            } catch (Exception e) {
                                                mostraMsg("Erro 014eq ao gravar novos dados.", "> " + e, 2);
                                            }
                                     //   }
                                  //  }
                                }
                            }
                       // }
                  //  }
                }

            }
        }
    }

    @FXML
    private void clicouCancela(ActionEvent event
    ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Cancelar?");
        alert.setContentText("Se você cancelar esse procedimento? Os dados não serão gravados.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            gbCrudEqui = 0;

            // FECHA JANELA
            Stage stage = (Stage) buConfirma.getScene().getWindow();
            stage.close();
        }
    }

}
