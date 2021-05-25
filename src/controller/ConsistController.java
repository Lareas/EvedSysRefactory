/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.ChecaErros;
import entities.Dadocadastrogeral;
import static funcoes.MyFunc.isCPF;
import static funcoes.MyFunc.isEmailValid;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jpa_controler.DadocadastrogeralJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class ConsistController implements Initializable {

    @FXML
    private TableView<Dadocadastrogeral> tvPesquisa;
    @FXML
    private TableColumn<Dadocadastrogeral, Integer> tcCod;
    @FXML
    private TableColumn<Dadocadastrogeral, String> tcAluno;
    @FXML
    private TableColumn<Dadocadastrogeral, String> tcStatus;
    @FXML
    private TableColumn<Dadocadastrogeral, String> tcConsist;
    @FXML
    private TextField lblRegistros;

    private ObservableList<Dadocadastrogeral> dados;
    private DadocadastrogeralJpaController jpaCon;
    Dadocadastrogeral reg_atual = new Dadocadastrogeral();
    @FXML
    private Label lblRegs;
    @FXML
    private TextArea taConsist;
    @FXML
    private TextField edStatus;
    @FXML
    private TextField edAluno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        jpaCon = new DadocadastrogeralJpaController();
        initColumns();
    }

    public void initColumns() {
        tcCod.setCellValueFactory(new PropertyValueFactory<>("dadoCadastroGeralId"));
        tcAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("statusDados"));
        tcConsist.setCellValueFactory(new PropertyValueFactory<>("consist"));
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
        lblRegistros.setText("Registros exibidos: " + tvPesquisa.itemsProperty().get().size());
    }

    public ObservableList<Dadocadastrogeral> getMeusDados() {
        jpaCon = new DadocadastrogeralJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findDadocadastrogeralEntities());

        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }
    }

    private void checaTTF(String campo, int tipErr, String campodado, ChecaErros che) {
        // tipoErro-> 1 = ERRO, 2 = Warning
        if (campodado == null) {
            if (tipErr == 1) {
                che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ficar vazio!\n");
                che.setCte(che.getCte() + 1);
            } else {
                che.setWarn(che.getWarn() + "- O campo <" + campo + "> está vazio.\n");
                che.setCtw(che.getCtw() + 1);
            }
        } else {
            if (campodado.equals("")) {
                if (tipErr == 1) {
                    che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ficar vazio!\n");
                    che.setCte(che.getCte() + 1);
                } else {
                    che.setWarn(che.getWarn() + "- O campo <" + campo + "> está vazio.\n");
                    che.setCtw(che.getCtw() + 1);
                }
            }
        }
    }

    @FXML
    private void clicouVerifica(ActionEvent event) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Verificar?");
        alert.setContentText("Deseja realmente proceder com a verificação? Todos os registros serão checados.");
        Optional<ButtonType> option = alert.showAndWait();
        String dum;

        if (option.get() == ButtonType.OK) {
            ObservableList<Dadocadastrogeral> dados = getMeusDados();
            int gravados = 0;

            Dadocadastrogeral r = new Dadocadastrogeral();
            for (Dadocadastrogeral i : dados) {
                r = i;

                ChecaErros check = new ChecaErros();
                System.out.println("\nAluno: " + r.getNome());

                checaTTF("Nome", 1, r.getNome(), check);
                if (r.getSexoId() == null) {
                    check.setCte(check.getCte() + 1);
                    check.setWarn(check.getWarn() + "*** O campo <Sexo> não pode ficar vazio!\n");
                }

                checaTTF("CEP do Aluno", 2, r.getCEPResidencial(), check);
                checaTTF("Endereço do Aluno", 2, r.getEnderecoResidencial(), check);
                checaTTF("Cidade do Aluno", 2, r.getCidadeResidencial(), check);
                checaTTF("Bairro do Aluno", 2, r.getBairroResidencial(), check);
//                checaTTF("Estado do Aluno", 2, r.getNomeUFResid().getEstado(), check);

                String fres, fcom, fcel;// = "";
                fres = (r.getTelefoneResidencial() == null ? "" : r.getTelefoneResidencial());
                fcom = (r.getTelefoneComercial() == null ? "" : r.getTelefoneComercial());
                fcel = (r.getTelefoneCelular() == null ? "" : r.getTelefoneCelular());

                if ((fres.equals("")) && (fcom.equals("")) && (fcel.equals(""))) {
                    check.setCtw(check.getCtw() + 1);
                    check.setWarn(check.getWarn() + "- Pelo menos um telefone deve ser preenchido.\n");
                }

                dum = (r.getEmailPessoal() == null ? "" : r.getEmailPessoal());
                if (dum.equals("")) {
                    check.setWarn(check.getWarn() + "- O campo <E-mail> está vazio.\n");
                    check.setCtw(check.getCtw() + 1);
                } else {
                    if (!isEmailValid(dum)) {
                        check.setWarn(check.getWarn() + "*** O campo <E-mail> está incorreto!\n");
                        check.setCte(check.getCte() + 1);
                    }
                }

                checaTTF("Local do Nascimento", 2, r.getLocalNascimento(), check);
//                checaTTF("Estado do Local de Nascimento", 2, r.getNomeUFNasc().getEstado(), check);
//                checaTTF("Nacionalidade", 2, r.getNomeNacionalidade().getNacionalidade(), check);

                checaTTF("RG", 2, r.getRg(), check);

                dum = (r.getCpf() == null ? "" : r.getCpf());
                if (dum.equals("")) {
                    check.setWarn(check.getWarn() + "- O campo <CPF> está vazio.\n");
                    check.setCtw(check.getCtw() + 1);
                } else {
                    if (!isCPF(dum)) {
                        check.setWarn(check.getWarn() + "*** O campo <CPF> está incorreto!\n");
                        check.setCte(check.getCte() + 1);
                    }
                }

                checaTTF("Escolaridade", 2, String.valueOf(r.getEscolaGrauMaxima()), check);
                checaTTF("Igreja", 2, r.getNomeIgreja(), check);
                checaTTF("Pastor", 2, r.getPastorIgreja(), check);

//                checaTTF("Formação", 2, r.getNomeFormacao().getNomeFormacao(), check);
//                checaTTF("Profissão", 2, r.getNomeProfissao().getNomeProfissao(), check);
//                checaTTF("Denominação", 2, r.getNomeDenominacao().getDenominacao(), check);
                dum = (r.getNomeEstadoCivil() == null ? "" : r.getNomeEstadoCivil().getEstadoCivil());
                if (dum.equals("")) {
                    check.setCtw(check.getCtw() + 1);
                    check.setWarn(check.getWarn() + "- O campo <Estado CIvil do Aluno> não pode ficar vazio.\n");
                } else {
                    if ((dum == "Casado (a)")  && (r.getConjuge().equals(""))) {
                        check.setCtw(check.getCtw() + 1);
                        check.setWarn(check.getWarn() + "- O campo Se o aluno for CASADO, o campo <Cônjuge> não pode ficar vazio.\n");
                    }
                }

                if (check.getCte() + check.getCtw() > 0) {
                    r.setConsist("ATENÇÃO! \n" + "HÁ " + check.getCte() + " ERRO(S). \n"
                            + "HÁ " + check.getCtw() + " AVISO(S). \n\n" + check.getWarn());
//                    r.setStatusdados("DADOS INCOMPLETOS");
                } else {
//                    r.setStatusdados("DADOS COMPLETOS");
                }

                edAluno.setText(r.getNome());
//                edStatus.setText(r.getStatusdados());
                taConsist.setText(r.getConsist());

//                System.out.println("Status : " + r.getStatusdados());
                System.out.println("Consist: " + r.getConsist());
//                reg_atual = jpaCon.findDadocadastrogeral(r.getDadoCadastroGeralId());
//                reg_atual.setConsist(r.getConsist());
//                reg_atual.setStatusDados(r.getStatusDados());
//                jpaCon.edit(reg_atual);
                lblRegs.setText("recs: " + ++gravados);

            }
            System.out.println("\n\n ************* TOTAL DE REGISTROS: " + gravados);

        }
    }

}
