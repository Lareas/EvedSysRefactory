package controller;

//import Model.RecEspecie;
//import Model.RecReferencia;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entities.Especificidade;
import entities.Programa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public class RecRecController implements Initializable {

    @FXML
    private AnchorPane apRecRecPane;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXComboBox<Especificidade> cbInstituicao;
    @FXML
    private JFXTextField txtCNPJ;
    @FXML
    private JFXComboBox<Programa> cbEspecie;
    @FXML
    private JFXTextField txtValor;
    @FXML
    private TextField txtCodRecibo;
    @FXML
    private JFXComboBox<Especificidade> cbReferencia;
    @FXML
    private JFXDatePicker dpDataEmissao;
    @FXML
    private JFXTextField txtLocal;
    @FXML
    private JFXTextField txtTesouraria;

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
    private JFXButton buApagar;

    @FXML
    private ButtonBar bbButtonBar;
    
    public static boolean editando, inserindo;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        desligaEdicao();
    }

    @FXML
    void clicouInsere(ActionEvent event) {
        inserindo = true;
        ligaEdicao();

        // procedimento de inserção de registro
        // limpa campos
        txtNome.setText("");
        cbInstituicao.getSelectionModel().clearSelection();
        txtCNPJ.setText("");
        cbEspecie.getSelectionModel().clearSelection();
        txtValor.setText("");
        cbReferencia.getSelectionModel().clearSelection();
        dpDataEmissao.getEditor().clear();
        txtLocal.setText("");
        txtTesouraria.setText("");
        
        txtNome.requestFocus();
    }

    @FXML
    void clicouEdita(ActionEvent event) {
        editando = true;
        ligaEdicao();
    }

    @FXML
    void clicouConfirma(ActionEvent event) {
        System.out.println("Clicou *** CONFIRMA ///");
        desligaEdicao();
    }

    @FXML
    void clicouCancela(ActionEvent event) {
        System.out.println("Clicou XXXXX CANCELA XXXXX");
        desligaEdicao();
        inserindo = false;
        editando = false;
    }

    @FXML
    void clicouPesquisa(ActionEvent event) {
        System.out.println("Clicou ??? PESQUISA ???");
        // buPesquisa não altera status dos botões
    }

    public void ligaEdicao() {

        // habilita botões para inserção e deleção
        buInsere.setDisable(true);
        buEdita.setDisable(true);
        buConfirma.setDisable(false);
        buCancela.setDisable(false);
        buPesquisa.setDisable(true);
        
        txtNome.setEditable(true);
        cbInstituicao.setEditable(true);
        txtCNPJ.setEditable(true);
        cbEspecie.setEditable(true);
        txtValor.setEditable(true);
        cbReferencia.setEditable(true);
        dpDataEmissao.setEditable(true);
        txtLocal.setEditable(true);
        txtTesouraria.setEditable(true);

    }

    public void desligaEdicao() {
        
        // habilita botões para inserção e deleção
        buInsere.setDisable(false);
        buEdita.setDisable(false);
        buConfirma.setDisable(true);
        buCancela.setDisable(true);
        buPesquisa.setDisable(false);

        txtCodRecibo.setEditable(false);
        txtNome.setEditable(false);
        cbInstituicao.setEditable(false);
        txtCNPJ.setEditable(false);

        cbEspecie.setEditable(false);
        cbReferencia.setEditable(false);
        dpDataEmissao.setEditable(false);
        txtValor.setEditable(false);
        txtLocal.setEditable(false);
        txtTesouraria.setEditable(false);
    }

}
