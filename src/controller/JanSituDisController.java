/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbJanSituDis;
import static main.Login.gbRegCadGer;
import com.jfoenix.controls.JFXButton;
import entities.ChecaErros;
import funcoes.ComboBoxAutoComplete;
import funcoes.MyFunc;
import static funcoes.MyFunc.checaTTF;
import static funcoes.MyFunc.mostraMsgWait;
import funcoes.TextFieldFormatter;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jpa_controler.CadaludissitJpaController;
import jpa_controler.FrequenciaJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class JanSituDisController implements Initializable {

    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private Label lblIns;
    @FXML
    private TextField tfNomeAluno;
    @FXML
    private TextField tfNomeDisciplina;
    @FXML
    private TextField tfMedia;
    @FXML
    private TextField tfFaltas;
    @FXML
    private TextField tfSituacao;
    @FXML
    private TextField tfFrequencia;
    @FXML
    private TextField edMedia;
    @FXML
    private TextField edFaltas;
    @FXML
    private ComboBox<String> cbSituacao;
    @FXML
    private ComboBox<String> cbFrequencia;
    @FXML
    private TextField tfNomeProfessor;

    private CadaludissitJpaController jpaSID;
    private FrequenciaJpaController jpaFR;

    private ObservableList<String> dadosSID;
    private ObservableList<String> dadosFR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbSituacao.setItems(getSituacao());
        cbFrequencia.setItems(getFrequencia());
        
        tfNomeAluno.setText(gbRegCadGer.getNome());
        tfNomeDisciplina.setText(gbJanSituDis.getDisciplina());
        tfNomeProfessor.setText(gbJanSituDis.getProfessor());
        tfMedia.setText(String.valueOf(gbJanSituDis.getMedia()));
        tfFaltas.setText(String.valueOf(gbJanSituDis.getFaltas()));
        tfSituacao.setText(gbJanSituDis.getSituaacao());
        tfFrequencia.setText(gbJanSituDis.getFrequencia());

        edMedia.setText(String.valueOf(gbJanSituDis.getMedia()));
        edFaltas.setText(String.valueOf(gbJanSituDis.getFaltas()));
        cbSituacao.getSelectionModel().select((gbJanSituDis.getSituaacao()!= null) ? gbJanSituDis.getSituaacao(): null);
        cbFrequencia.getSelectionModel().select((gbJanSituDis.getFrequencia()!= null) ? gbJanSituDis.getFrequencia(): null);
        
        new ComboBoxAutoComplete<String>(cbSituacao, "");
        new ComboBoxAutoComplete<String>(cbFrequencia, "");
    }

    public ObservableList<String> getSituacao() {
        jpaSID = new CadaludissitJpaController();
        dadosSID = FXCollections.observableArrayList(jpaSID.getNomeSituacao());

        if (dadosSID == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosSID;
        }
    }

    public ObservableList<String> getFrequencia() {
        jpaFR = new FrequenciaJpaController();
        dadosFR = FXCollections.observableArrayList(jpaFR.getNomeFrequencia());

        if (dadosFR == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosFR;
        }
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
        ChecaErros check = new ChecaErros();
        checaTTF("Média", 1, edMedia, check);
        checaTTF("Faltas", 1, edFaltas, check);

        // testa valor da média
        try {
            double dbl = Double.parseDouble(edMedia.getText());
            if ((dbl < 0) || (dbl > 10)) {
                MyFunc.mostraMsg("Erro no valor da Média", "Cheque o valor", 2);
            }
        } catch (NumberFormatException ex) {
            MyFunc.mostraMsg("Erro no valor da Média", "Verifique o valor", 2);
        }

        // testa valor das faltas
        try {
            int faltas = Integer.parseInt(edFaltas.getText());
            if ((faltas < 0) || (faltas > 16)) {
                MyFunc.mostraMsg("Erro no valor da Média", "Cheque o valor", 2);
            }
        } catch (NumberFormatException ex) {
            MyFunc.mostraMsg("Erro no valor das faltas", "Verifique o valor", 2);
        }

        if (check.getCte() + check.getCtw() > 0) {
            mostraMsgWait("ATENÇÃO! \n" + "HÁ " + check.getCte() + " ERRO(S). \n"
                    + "HÁ " + check.getCtw() + " AVISO(S). \n\n", check.getWarn(), (check.getCte() > 0) ? 2 : 4);
        } else {
            String resp = "";

            if (check.getCte() == 0) {
                int numAlt = 0;
                if (!tfMedia.getText().equals(edMedia.getText())) {
                    ++numAlt;
                    resp = resp + "Média: de " + tfMedia.getText() + "   para " + edMedia.getText() + "\n";
                }
                if (!tfFaltas.getText().equals(edFaltas.getText())) {
                    ++numAlt;
                    resp = resp + "Faltas: de " + tfFaltas.getText() + "   para " + edFaltas.getText() + "\n";
                }
                if (!tfSituacao.getText().equals(cbSituacao.getSelectionModel().getSelectedItem())) {
                    ++numAlt;
                    resp = resp + "Situação: de " + tfSituacao.getText() + "   para " + cbSituacao.getSelectionModel().getSelectedItem() + "\n";
                }
                if (!tfFrequencia.getText().equals(cbFrequencia.getSelectionModel().getSelectedItem())) {
                    ++numAlt;
                    resp = resp + "Frequência: de " + tfFrequencia.getText() + "   para " + cbFrequencia.getSelectionModel().getSelectedItem() + "\n";
                }

                if (numAlt == 0) {
                    MyFunc.mostraMsgWait("Nenhuma alteração foi feita.", "", 3);
                    Stage stage = (Stage) buConfirma.getScene().getWindow();
                    stage.close();
                } else {
                    // PODE GRAVAR!
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Há " + numAlt + " alterações a serem gravadas\n" + resp);
                    alert.setContentText("Confirma a gravação dos dados?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get() == ButtonType.OK) {
                        MyFunc.mostraMsg("Gravei.", "", 1);
                    } else {
                        MyFunc.mostraMsg("Não gravei.", "", 1);
                    }
                }
            }

        }
    }

    @FXML
    private void clicouCancela(ActionEvent event
    ) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Cancelar?");
        alert.setContentText("Se você cancelar esse procedimento?\nOs dados não serão alterados.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {

            // FECHA JANELA
            Stage stage = (Stage) buConfirma.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void clicouTTFMedia(KeyEvent event
    ) {
        //    chamaTTF2(edMedia, "0123456789.", "###");
    }

    @FXML
    private void clicouTTFFaltas(KeyEvent event
    ) {
        chamaTTF2(edFaltas, "0123456789", "##");
    }

    private void chamaTTF2(TextField edTTF, String validos, String mascara) {
        if (!edTTF.equals(null)) {
            if (edTTF.getText() != null) {
                if (edTTF.getText().trim().length() > 0) {
                    if (!edTTF.getText().equals("")) {
                        TextFieldFormatter tff = new TextFieldFormatter();
                        tff.formatter(edTTF, validos, mascara);
                    }
                }
            }
        }

    }

}
