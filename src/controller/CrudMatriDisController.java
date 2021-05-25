/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbCrudAmareloMS;
import static main.Login.gbDataCrudMS;
import static main.Login.gbEspecial;
import static main.Login.gbRegCadGer;
import static main.Login.gbRegCrudMS;
import static main.Login.gbRegMatriSemCabec;
import static main.Login.gbUser;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import entities.Cadastroalunodisciplina;
import entities.Cadastrodisciplina;
import entities.Curso;
import entities.GradeMS;
import funcoes.ComboBoxAutoComplete;
import funcoes.DBConnector;
import static funcoes.MyFunc.mostraMsg;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Line;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.CadastrodisciplinaJpaController;
import jpa_controler.CursoJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class CrudMatriDisController implements Initializable {

    @FXML
    private ComboBox<String> cbDisciplina;
    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private Label lblIns;
    @FXML
    private Label lblEdi;
    @FXML
    private JFXButton buRefDen;
    @FXML
    private RadioButton rbReg;
    @FXML
    private RadioButton rbOuv;
    @FXML
    private RadioButton rbEsp;
    @FXML
    private ToggleGroup tg1;
    @FXML
    private Label lblTipo;

    private ObservableList<String> dadosMatriSem;
    private CadastrodisciplinaJpaController jpaCad;
    private CursoJpaController jpaCur;
    private CadastroalunodisciplinaJpaController jpaPup;
    Cadastroalunodisciplina reg_atual = new Cadastroalunodisciplina();

    private ObservableList<GradeMS> dadosMS;
    public Connection con = DBConnector.getConnection();
    @FXML
    private Line lineRed;
    @FXML
    private RadioButton rbGra;
    @FXML
    private ToggleGroup tg11;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        lineRed.setVisible(gbEspecial);

        cbDisciplina.setItems(getNomeDis());
        new ComboBoxAutoComplete<String>(cbDisciplina, "");

        if (gbCrudAmareloMS == 1) { // INSERE
            lblIns.setVisible(true);
            lblEdi.setVisible(false);
        } else if (gbCrudAmareloMS == 2) { // EDITA

            // carrega reg_atual com registro atual selecionado no grid de disciplinas
            jpaPup = new CadastroalunodisciplinaJpaController();
            reg_atual = jpaPup.findCadastroalunodisciplina(gbRegCrudMS.getCadastroAlunoDisciplina());

            lblIns.setVisible(false);
            lblEdi.setVisible(true);

            cbDisciplina.getSelectionModel().select(gbRegCrudMS.getNomeDisc());
            if (gbRegCrudMS.getTipoReg() == "R") {
                rbReg.setSelected(true);
            } else {
                if (gbRegCrudMS.getTipoReg() == "E") {
                    rbEsp.setSelected(true);
                } else {
                    if (gbRegCrudMS.getTipoReg() == "O") {
                        rbOuv.setSelected(true);
                    } else {
                        if (gbRegCrudMS.getTipoReg() == "G") {
                            rbGra.setSelected(true);
                        }
                    }
                }
            }
        }
    }

    public ObservableList<String> getNomeDis() {
        jpaCad = new CadastrodisciplinaJpaController();
        dadosMatriSem = FXCollections.observableArrayList(jpaCad.getNomeDasDisci(gbRegMatriSemCabec.getCodGrade()));

        if (dadosMatriSem == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosMatriSem;
        }
    }

    @FXML
    private void clicouConfirma(ActionEvent event) {

        if (cbDisciplina.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("Selecione a <Disciplina>.", "", 2);
        } else {
            if ((!rbReg.isSelected()) && (!rbOuv.isSelected()) && (!rbEsp.isSelected()) && (!rbGra.isSelected())) {
                mostraMsg("Selecione o <Tipo de Aluno>.", "", 2);
            } else {
                if (gbEspecial && rbReg.isSelected()) {
                    mostraMsg("Se aluno é ESPECIAL, o <Tipo do Aluno> não pode ser REGULAR.", "", 2);
                } else {
                    if (!gbEspecial && rbEsp.isSelected()) {
                        mostraMsg("Se o aluno NÃO for ESPECIALL, o <Tipo do Aluno> não pode ser ESPECIAL.", "", 2);
                    } else {

                        Cadastroalunodisciplina obj = new Cadastroalunodisciplina();

                        if (gbCrudAmareloMS == 1) { // está inserindo
                            if (verifPodeGravar(cbDisciplina.getSelectionModel().getSelectedItem(), 0) == false) {
                                mostraMsg("Essa disciplina já está nesta matrícula.", "Não é possível gravar!", 2);
                            } else {
                                try {

                                    gbRegCrudMS.setNomeDisc(cbDisciplina.getSelectionModel().getSelectedItem());
                                    jpaCad = new CadastrodisciplinaJpaController();
                                    jpaCur = new CursoJpaController();

                                    Cadastrodisciplina cadi = new Cadastrodisciplina();
                                    cadi = jpaCad.getObjetoCodGradeByDescricao(gbRegMatriSemCabec.getCodGrade(), cbDisciplina.getSelectionModel().getSelectedItem());

                                    Curso cur = new Curso();
                                    cur = jpaCur.getObjeto(gbRegMatriSemCabec.getNomePrograma());

                                    obj.setNomeCadDisciplina(cadi);
                                    obj.setDadoCadastroGeralId(gbRegMatriSemCabec.getCodAluno());
                                    obj.setDadoCadastroProgramaId(gbRegMatriSemCabec.getCodPrograma());
                                    obj.setCadastroAlunoDisciplinaSituacaoId(gbDataCrudMS.getCadastroAlunoDisciplinaSituacaoId());

                                    obj.setNomeMatriSem(gbDataCrudMS.getNomeMatriSem());

                                    String tipoAlu = "";
                                    if (rbReg.isSelected()) {
                                        tipoAlu = "R";
                                        Short cursando = 9;
                                        obj.setCadastroAlunoDisciplinaSituacaoId(cursando); // ***** CURSANDO *****
                                    } else {
                                        if (rbEsp.isSelected()) {
                                            tipoAlu = "E";
                                            Short cursando = 9;
                                            obj.setCadastroAlunoDisciplinaSituacaoId(cursando); // ***** CURSANDO *****
                                        } else {
                                            if (rbOuv.isSelected()) {
                                                tipoAlu = "O";
                                                Short ouvinte = 4;
                                                obj.setFrequenciaId(ouvinte); // ***** OUVINTE *****
                                                ouvinte = 6;
                                                obj.setCadastroAlunoDisciplinaSituacaoId(ouvinte); // ***** OUVINTE *****
                                            } else {
                                                if (rbGra.isSelected()) {
                                                    tipoAlu = "G";
                                                    Short gravacao = 6;
                                                    obj.setFrequenciaId(gravacao); // ***** GRAVAÇÃO *****
                                                    Short situacao = 18;
                                                    obj.setCadastroAlunoDisciplinaSituacaoId(situacao); // ***** GRAVAÇÃO *****
                                                }
                                            }
                                        }
                                    }

                                    obj.setTipoRgOuv(tipoAlu);
                                    if (tipoAlu.equals("R") || tipoAlu.equals("E")) {
                                        obj.setIndTipoRegOuv(1);
                                    } else {
                                        if (tipoAlu.equals("O")) {
                                            obj.setIndTipoRegOuv(2);
                                        } else {
                                            obj.setIndTipoRegOuv(3); // GRAVAÇÃO
                                        }
                                    }
                                    obj.setCodGrade(gbRegMatriSemCabec.getCodGrade());
                                    obj.setDataInc(new Date());
                                    obj.setCodUserInc(gbUser.getCoduser());

                                    jpaPup = new CadastroalunodisciplinaJpaController();
                                    jpaPup.create(obj);

                                    // FECHA JANELA
                                    gbCrudAmareloMS = 0;
                                    Stage stage = (Stage) buConfirma.getScene().getWindow();
                                    stage.close();

                                } catch (Exception e) {
                                    System.out.println(obj);
                                    System.out.println("\n============================");
                                    System.out.println(e);
                                    System.out.println("============================\n");
                                    mostraMsg("Erro ao gravar a edição dos dados.", "" + e, 2);
                                }

                            }
                        } else {
                            if (gbCrudAmareloMS == 2) { // edita
                                if (verifPodeGravar(cbDisciplina.getSelectionModel().getSelectedItem(), gbRegCrudMS.getCadastroAlunoDisciplina()) == false) {
                                    mostraMsg("Essa disciplina já está nesta matrícula.", "Não é possível gravar!", 2);
                                } else {
                                    try {

                                        // RECUPERA CÓDIGO CADI DA DISCIPLINA SELECIONADA
                                        gbRegCrudMS.setNomeDisc(cbDisciplina.getSelectionModel().getSelectedItem());
                                        jpaCad = new CadastrodisciplinaJpaController();

                                        obj = reg_atual; // FINALMENTE!

                                        Cadastrodisciplina cadi = new Cadastrodisciplina();
                                        cadi = jpaCad.getObjetoCodGradeByDescricao(gbRegMatriSemCabec.getCodGrade(), cbDisciplina.getSelectionModel().getSelectedItem());

                                        String tipoAlu;
                                        if (rbReg.isSelected()) {
                                            tipoAlu = "R";
                                            Short cursando = 9;
                                            obj.setCadastroAlunoDisciplinaSituacaoId(cursando); // ***** CURSANDO *****
                                        } else {
                                            if (rbEsp.isSelected()) {
                                                tipoAlu = "E";
                                                Short cursando = 9;
                                                obj.setCadastroAlunoDisciplinaSituacaoId(cursando); // ***** CURSANDO *****
                                            } else {
                                                if (rbOuv.isSelected()) {
                                                    tipoAlu = "O";
                                                    Short ouvinte = 4;
                                                    obj.setFrequenciaId(ouvinte); // ***** OUVINTE *****
                                                    ouvinte = 6;
                                                    obj.setCadastroAlunoDisciplinaSituacaoId(ouvinte); // ***** OUVINTE *****
                                                } else { // gravação
                                                    tipoAlu = "G";
                                                    Short gravacao = 6;
                                                    obj.setFrequenciaId(gravacao); // ***** gravacao *****
                                                    gravacao = 18;
                                                    obj.setCadastroAlunoDisciplinaSituacaoId(gravacao); // ***** OUVINTE ***** 
                                                }
                                            }
                                        }

                                        obj.setNomeCadDisciplina(cadi); // disciplina
                                        obj.setTipoRgOuv(tipoAlu);      // tipo do aluno

                                        if (tipoAlu.equals("R") || tipoAlu.equals("E")) {
                                            obj.setIndTipoRegOuv(1);
                                        } else {
                                            if (tipoAlu.equals("O")) {
                                                obj.setIndTipoRegOuv(2);
                                            } else {
                                                obj.setIndTipoRegOuv(3); // GRAVAÇÃO
                                            }
                                        }

                                        obj.setDataAlt(new Date());
                                        obj.setCodUserAlt(gbUser.getCoduser());

                                        jpaPup = new CadastroalunodisciplinaJpaController();
                                        jpaPup.edit(obj);
                                        mostraMsg("Dados gravados com sucesso.", "", 1);

                                        // FECHA JANELA
                                        gbCrudAmareloMS = 0;
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
                        }
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
        alert.setContentText("Se você cancelar esse procedimento? Os dados não serão gravados.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            gbCrudAmareloMS = 0;

            // FECHA JANELA
            Stage stage = (Stage) buConfirma.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void clicouRefreshDen(ActionEvent event
    ) {
        cbDisciplina.setItems(getNomeDis());
    }

    private Boolean verifPodeGravar(String nomeDaDis, int codCadi) {
        Boolean podegravar = true;

        String sqlAluDis
                = "SELECT s.CadastroAlunoDisciplinaId, cad.Disciplina, s.DadoCadastroProgramaId, s.codgrade, mat.DadoCadastroGeralId, S.TIPOREGOUV "
                + " FROM  Cadastroalunodisciplina s  "
                + " LEFT JOIN es_matrisem mat ON s.DadoCadastroProgramaId = mat.DadoCadastroProgramaId "
                + " LEFT JOIN cadastrodisciplina cad ON s.CadastroDisciplinaId = cad.CadastroDisciplinaId "
                + "                  WHERE s.codGrade = " + gbRegMatriSemCabec.getCodGrade()
                + "                  AND mat.dadoCadastroGeralId = " + gbRegCadGer.getDadoCadastroGeralId()
                + "                  AND cad.Disciplina = '" + nomeDaDis + "'"
                + " GROUP BY s.CadastroAlunoDisciplinaId    ";
        //   + " GROUP BY s.codgrade, mat.DadoCadastroGeralId, s.CadastroDisciplinaId ";

        dadosMS = FXCollections.observableArrayList();
        try {
            System.out.println(sqlAluDis);

            ResultSet rs = con.createStatement().executeQuery(sqlAluDis);
            while (rs.next()) {
                dadosMS.add(new GradeMS(rs.getInt("s.CadastroAlunoDisciplinaId"),
                        rs.getString("cad.Disciplina")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudMatriDisController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 018e - em CurdMS.verifPodeGravar()", "" + ex, 2);
        }

        GradeMS gra;
        if (gbCrudAmareloMS == 1) {
            if (dadosMS.size() > 0) {
                podegravar = false;
            }
        } else { // É EDIÇÃO - se encontrar 1, verificar se cadi é o mesmo
            if (gbCrudAmareloMS == 2) {
                if (dadosMS.size() > 0) {
                    if (dadosMS.get(0).getCadastroAlunoDisciplinaId() != codCadi) {
                        podegravar = false;
                    }
                }
            }
        }

        return podegravar;
    }

    @FXML
    private void mudouTIpo(ActionEvent event) {
        if (rbReg.isSelected()) {
            lblTipo.setStyle("-fx-text-fill : #1565c6;");
            lblTipo.setText("Regular");
        } else {
            if (rbEsp.isSelected()) {
                lblTipo.setStyle("-fx-text-fill : #d00e0e;");
                lblTipo.setText("Especial");
            } else {
                if (rbOuv.isSelected()) {
                    lblTipo.setStyle("-fx-text-fill : #ba7518;");
                    lblTipo.setText("Ouvinte");
                } else {
                    lblTipo.setStyle("-fx-text-fill : #1da11d;");
                    lblTipo.setText("Gravação");
                }
            }
        }
    }

}
