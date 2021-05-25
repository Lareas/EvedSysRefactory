/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import static main.Login.gbDispensa;
import static main.Login.GBFORMATDATAHORA;
import static main.Login.gbUltSem;
import static main.Login.gbUser;
import entities.DispensaGrid;
import entities.GradeDispensa;
import funcoes.DBConnector;
import static funcoes.MyFunc.mostraMsg;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpa_controler.CadastrodisciplinaJpaController;
import jpa_controler.EsGradeJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class Dispensa implements Initializable {

    @FXML
    private ComboBox<String> cbDisciplina;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private JFXButton buRefDen;
    @FXML
    private Label lblTipo;
    @FXML
    private FontAwesomeIconView fvBoneco;
    @FXML
    private ImageView ivFotoAluno;
    @FXML
    private CheckBox chkDocsDigi;
    @FXML
    private Line lineRed;
    @FXML
    private Label lblAluno;
    @FXML
    private TextArea taObs;
    @FXML
    private TextField lblRegistrosPro;
    @FXML
    private TableView<DispensaGrid> tvDispensaDet;
    @FXML
    private JFXButton buMinApa;
    @FXML
    private ComboBox<String> cbGrade;
    @FXML
    private Label lblPrograma;

    private EsGradeJpaController jpaGra;
    private CadastrodisciplinaJpaController jpaCad;

    ObservableList<DispensaGrid> dislist;
    public Connection con = DBConnector.getConnection();

    public final Integer MAXDISCI = 80;
    public final Integer MAXGRADE = 100;
    Integer[] aCadis;
    Integer[] aGrade;
    String[] aConcatGrade;

    Integer iGrade = 0;

    private ObservableList<String> dadosGra;
    private ObservableList<String> dadosDis;
    Integer codGrade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // INICIALIZA VETORES DA DISCIPLINA
        aCadis = new Integer[MAXDISCI];
        aConcatGrade = new String[MAXGRADE];
        jpaGra = new EsGradeJpaController();
        jpaCad = new CadastrodisciplinaJpaController();

        for (int i = 0; i < aCadis.length; i++) {
            aCadis[i] = 0;
        }

        for (int i = 0; i < aConcatGrade.length; i++) {
            aConcatGrade[i] = " ";
        }

        // TODO
        lblAluno.setText("Aluno: " + gbDispensa.getNomeALuno());
        lblPrograma.setText("Programa: " + gbDispensa.getNomePrograma());
        if (gbDispensa.getModoDis() == 1) {
            lblRegistrosPro.setText("Inserido por: " + gbUser.getLogin() + "   |   " + "Data Ins.: " + GBFORMATDATAHORA.format(new Date()));
            tvDispensaDet.setItems(null);
            taObs.setText("");
            chkDocsDigi.setSelected(false);

            // PREENCHE COMBOS ANO E SEMESTRE DA MATRÍCULA SEMESTRAL
            preparaGrade();
            cbGrade.setItems(getGradeMS());

            // SELECIONA SEMESTRE ATUAL
            String semAtual = gbUltSem.getUltAno() + "." + gbUltSem.getUltSem();
            cbGrade.getSelectionModel().select(semAtual);
            preencheDis();

        } else {
            // edição - carrega dadls
            lblRegistrosPro.setText("Alterado por: " + gbUser.getLogin() + "   |   " + "Data Alt.: " + GBFORMATDATAHORA.format(new Date()));
            insGridDisp();

        }

    }

    public void preparaGrade() {

    }

    public ObservableList<GradeDispensa> getGradeDispensa() {

        jpaGra = new EsGradeJpaController();
        ObservableList<GradeDispensa> dadosGraDis = FXCollections.observableArrayList(jpaGra.getCodsGrades());

        if (dadosGraDis == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosGraDis;
        }
    }

    public ObservableList<String> getDisci() {
//        jpaCad = new EsGradeJpaController();
//        ObservableList<String> dadosDisci = FXCollections.observableArrayList(jpaCad.getNomeDasDisci());
//
//        if (dadosGra == null) {
//            return FXCollections.observableArrayList();
//        } else {
//            return dadosGra;
//        }
        return null;
    }

    private void insGridDisp() {

        dislist = FXCollections.observableArrayList();
        String sqlDispensaDet
                = "SELECT disp.CodDispensaDet, disp.CodDispensa, disp.Instituicao, "
                + " disp.Disciplina, disp.Carga, disp.Credito, disp.Ano, disp.Nota "
                + " FROM es_dispensadet disp "
                + " WHERE disp.CodDispensa = " + "1"
                + " ORDER BY disp.CodDispensaDet ";

        try {
            System.out.println(sqlDispensaDet);

            ResultSet rs = con.createStatement().executeQuery(sqlDispensaDet);
            while (rs.next()) {
                dislist.add(new DispensaGrid(
                        rs.getInt("disp.CodDispensaDet"),
                        rs.getString("disp.Instituicao"),
                        rs.getString("disp.Disciplina"),
                        rs.getFloat("disp.Carga"),
                        rs.getInt("disp.Credito"),
                        rs.getInt("disp.Ano"),
                        rs.getString("disp.Nota")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Dispensa.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018d - em insGridDisp()", "" + ex, 2);
        }

        // CALCULA NUMERO DE DISCIPLINAS E VALORES
        Integer numcre = 0;
        Float valCreRE = 0F, valCreO = 0F;

        tvDispensaDet.setItems(dislist);

        DispensaGrid reg = new DispensaGrid();
        for (int i = 0; i < dislist.size(); i++) {
            reg = dislist.get(i);
            //   if (reg.getCredito() != null) {
            numcre = numcre + reg.getCredito();
            //   }
        }

        String spa = "     ";
        int ndisp = tvDispensaDet.itemsProperty().get().size();
        lblRegistrosPro.setText("Nº Disciplinas: " + ndisp + spa + "Nº Créditos (RE): " + numcre + spa + "     Inserido por: " + gbUser.getLogin() + "   |   " + "Data Ins.: " + GBFORMATDATAHORA.format(new Date()));

        tvDispensaDet.refresh();
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {
    }

    @FXML
    private void clicouCancela(ActionEvent event) {
    }

    @FXML
    private void clicouRefreshDen(ActionEvent event) {
    }

    @FXML
    private void clicouInsereDisp(ActionEvent event) {
    }

    @FXML
    private void clicouEditaDisp(ActionEvent event) {
    }

    @FXML
    private void clicouApagaDisp(ActionEvent event) {
    }

    @FXML
    private void mudouGrade(ActionEvent event) {
        if (cbGrade.getSelectionModel().getSelectedItem() != null) {
            preencheDis();
        }
    }

    public void preencheDis() {
        // obtem código da grade selecionada
        codGrade = jpaGra.getCodGradePorConcat(cbGrade.getSelectionModel().getSelectedItem());
        dadosDis = FXCollections.observableArrayList(jpaCad.getNomeDasDisci(codGrade));
        cbDisciplina.setItems(dadosDis);
    }

    public ObservableList<String> getGradeMS() {
        dadosGra = FXCollections.observableArrayList(jpaGra.getGradeMS());

        if (dadosGra == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosGra;
        }
    }

}
