/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static main.Login.gbAtuNotas;
import static main.Login.GBFORMATDATAHORA;
import static main.Login.gbUser;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Cadaludissit;
import entities.Cadastroalunodisciplina;
import entities.EsFotosalunos;
import entities.EsRegaltnotas;
import entities.Frequencia;
import funcoes.ComboBoxAutoComplete;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jpa_controler.CadaludissitJpaController;
import jpa_controler.CadastroalunodisciplinaJpaController;
import jpa_controler.EsFotosalunosJpaController;
import jpa_controler.FrequenciaJpaController;
import static funcoes.MyFunc.mostraMsg;
import static funcoes.MyFunc.isFloat;
import static funcoes.MyFunc.isShort;
import java.math.BigDecimal;
import java.util.Date;
import jpa_controler.EsRegaltnotasJpaController;

/**
 * FXML Controller class
 *
 * @author ticoa
 */
public class Atualiza_Notas implements Initializable {

    @FXML
    private JFXButton buConfirma;
    @FXML
    private JFXButton buCancela;
    @FXML
    private Label lblAluno;
    @FXML
    private Label lblCredito;
    @FXML
    private Label lblTurma;
    @FXML
    private Label lblLocalidade;
    @FXML
    private Label lblProfessor;
    @FXML
    private Label lblHorario;
    @FXML
    private Label lblTurno;
    @FXML
    private Label lblCargaH;
    @FXML
    private Label lblSemestre;
    @FXML
    private Label lblAnoLetivo;
    @FXML
    private Label lblDisciplina;
    @FXML
    private ComboBox<String> cbSituacao;
    @FXML
    private ComboBox<String> cbFrequencia;
    @FXML
    private TextField ttfMedia;
    @FXML
    private TextField ttfFaltas;
    @FXML
    private Label lblPrograma;
    @FXML
    private ImageView ivFotoAluno;
    @FXML
    private TextField lblAutent;

    private ObservableList<String> dadosSI;
    private CadaludissitJpaController jpaSI;

    private ObservableList<String> dadosFR;
    private FrequenciaJpaController jpaFR;

    private EsFotosalunosJpaController jpaFotos;
    private ObservableList<EsFotosalunos> dadosFoto;
    private EsFotosalunos reg_foto = new EsFotosalunos();

    private EsRegaltnotasJpaController jpaRegAlt;
    private ObservableList<EsRegaltnotas> dadpsRegAlt;

    private CadastroalunodisciplinaJpaController jpaCad;
    private Cadastroalunodisciplina reg_cad;
    @FXML
    private FontAwesomeIconView fvBoneco;
    @FXML
    private ComboBox<String> cbTipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //cbTipo.getItems().addAll("Regular", "Especial", "Ouvinte", "Grava????o");
        cbTipo.getItems().addAll("R", "E", "O", "G");
        cbSituacao.setItems(getNomeSituacao());
        cbFrequencia.setItems(getNomeFrequencia());

        new ComboBoxAutoComplete<String>(cbSituacao, "");
        new ComboBoxAutoComplete<String>(cbFrequencia, "");

        lblAluno.setText(gbAtuNotas.getNomeAluno());
        lblPrograma.setText(gbAtuNotas.getPrograma());
        lblCredito.setText(String.valueOf(gbAtuNotas.getCredito()));
        lblTurma.setText(gbAtuNotas.getTurma());
        lblLocalidade.setText(gbAtuNotas.getLocalidade());
        lblProfessor.setText(gbAtuNotas.getProfessor());
        lblHorario.setText(gbAtuNotas.getHorario());
        lblTurno.setText(gbAtuNotas.getTurno());
        lblCargaH.setText(String.valueOf(gbAtuNotas.getCargah()));
        lblSemestre.setText(String.valueOf(gbAtuNotas.getSemestre()));
        lblAnoLetivo.setText(String.valueOf(gbAtuNotas.getAnoLetivo()));
        lblDisciplina.setText(String.valueOf(gbAtuNotas.getDisciplina()));
        ttfMedia.setText(String.valueOf(gbAtuNotas.getMedia()));
        ttfFaltas.setText(String.valueOf(gbAtuNotas.getFaltas()));
        cbSituacao.getSelectionModel().select(gbAtuNotas.getSituacao());
        cbFrequencia.getSelectionModel().select(gbAtuNotas.getFrequencia());
        cbTipo.getSelectionModel().select(gbAtuNotas.getTipoRegOuv());

        try {
            // carregar foto
            carregaFoto(gbAtuNotas.getDadoCadastroGeralId());
        } catch (IOException ex) {
            Logger.getLogger(Atualiza_Notas.class.getName()).log(Level.SEVERE, null, ex);
            mostraMsg("Erro 117 - N??o ?? poss??vel carregar a foto! ", "" + ex, 2);
            System.out.println("");
        }

        ttfMedia.requestFocus();

    }

    public void carregaFoto(int codAluno) throws FileNotFoundException, IOException {

        jpaFotos = new EsFotosalunosJpaController();
        reg_foto = obtemFoto(String.valueOf(codAluno));

        if (reg_foto.getCodFotoAluno() != null && reg_foto.getFotoAluno() != null) {
            fvBoneco.setVisible(false);
            String path = "C:/evedsys/temp/";
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }

            String nomeArq = path + "/photo" + codAluno + ".jpg";
            InputStream is = new ByteArrayInputStream(reg_foto.getFotoAluno());
            OutputStream os = new FileOutputStream(new File(nomeArq));

            byte[] content = new byte[1024];
            int size = 0;
            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }

            os.close();
            is.close();
            Image image = new Image("file:" + nomeArq, 100, 150, true, true);
            ivFotoAluno.setImage(image);
            ivFotoAluno.setPreserveRatio(true);

            // apaga arquivo tempor??rio da foto
            try {

                File f = new File(nomeArq);           //file to be delete  
                f.delete();
            } catch (Exception e) {
                System.out.println("erro ao tentar apagar foto tempor??ria " + e);
            }
        } else {
            fvBoneco.setVisible(true);
        }
    }

    public EsFotosalunos obtemFoto(String codAluno) {
        dadosFoto = FXCollections.observableArrayList(jpaFotos.getFotoAluno(codAluno));

        if (dadosFoto == null) {
            return null;
        } else {
            EsFotosalunos reg_achado = new EsFotosalunos();

            for (int i = 0; i < dadosFoto.size(); i++) {
                reg_achado = dadosFoto.get(i);
            }

            return reg_achado;
        }
    }

    public ObservableList<String> getNomeSituacao() {
        jpaSI = new CadaludissitJpaController();
        dadosSI = FXCollections.observableArrayList(jpaSI.getNomeSituacao());

        if (dadosSI == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosSI;
        }
    }

    public ObservableList<String> getNomeFrequencia() {
        jpaFR = new FrequenciaJpaController();
        dadosFR = FXCollections.observableArrayList(jpaFR.getNomeFrequencia());

        if (dadosFR == null) {
            return FXCollections.observableArrayList();
        } else {
            return dadosFR;
        }
    }

    @FXML
    private void clicouCancela(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Cancelar?");
        alert.setContentText("Se voc?? cancelar esse procedimento? Os dados n??o ser??o gravados.");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {

            // FECHA JANELA
            Stage stage = (Stage) buConfirma.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void clicouConfNota(ActionEvent event) {
        if (cbSituacao.getSelectionModel().getSelectedItem() == null) {
            mostraMsg("O campo <SITUA????O> n??o pode ficar vazio!", "", 2);
        } else {
            if (cbFrequencia.getSelectionModel().getSelectedItem() == null) {
                mostraMsg("O campo <FREQU??NCIA> n??o pode ficar vazio!", "", 2);
            } else {
                // CONSIST??NCIA DE M??DIA E FALTAS
                if (!isFloat(ttfMedia)) {
                    mostraMsg("O campo <M??DIA> n??o ?? num??rico", "", 2);
                } else {
                    if (!isShort(ttfFaltas)) {
                        mostraMsg("O campo <FALTAS> n??o ?? num??rico", "", 2);
                    } else {

                        // CONSISTE CAMPO TIPO
                        if (cbTipo.getSelectionModel().getSelectedItem() == null) {
                            mostraMsg("O campo <TIPO> n??o pode ficar vazio!", "", 2);
                        } else {
                            if (tipoOK()) { // verifica a consist??ncia de situa????o, frequ??ncia e tipo

                                // recuepra dados antigos
                                jpaCad = new CadastroalunodisciplinaJpaController();
                                //  reg_cad = new Cadastroalunodisciplina();
                                reg_cad = jpaCad.findCadastroalunodisciplina(gbAtuNotas.getCadastroAlunoDisciplinaId());
                                String frase = "Voc?? est?? alterando os dados deste aluno\n"
                                        + "\nde\n\n"
                                        + "m??dia atual: " + (reg_cad.getMedia() != null ? reg_cad.getMedia() : 0) + "\n"
                                        + "faltas atual: " + (reg_cad.getFalta() != null ? reg_cad.getFalta() : 0) + "\n"
                                        + "Situa????o atual: " + gbAtuNotas.getSituacao() + "\n"
                                        + "Frequ??ncia atual: " + gbAtuNotas.getFrequencia() + "\n"
                                        + "Tipo: " + gbAtuNotas.getTipoRegOuv() + "\n"
                                        + "\npara\n\n"
                                        + "m??dia nova: " + (ttfMedia.getText() != null ? ttfMedia.getText() : 0) + "\n"
                                        + "faltas nova: " + (ttfFaltas.getText() != null ? ttfFaltas.getText() : 0) + "\n"
                                        + "Situa????o nova: " + cbSituacao.getSelectionModel().getSelectedItem() + "\n"
                                        + "Frequ??ncia nova: " + cbFrequencia.getSelectionModel().getSelectedItem() + "\n"
                                        + "Tipo: " + cbTipo.getSelectionModel().getSelectedItem() + "\n";

                                // PODE GRAVAR!
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setHeaderText(frase);
                                alert.setContentText("Confirma a grava????o dos dados?");
                                Optional<ButtonType> option = alert.showAndWait();

                                if (option.get() == ButtonType.OK) {
                                    jpaSI = new CadaludissitJpaController();
                                    Cadaludissit sit = jpaSI.getObjeto(cbSituacao.getSelectionModel().getSelectedItem());
                                    jpaFR = new FrequenciaJpaController();
                                    Frequencia fre = jpaFR.getObjeto(cbFrequencia.getSelectionModel().getSelectedItem());

                                    reg_cad.setMedia(BigDecimal.valueOf(Float.valueOf(ttfMedia.getText())));
                                    reg_cad.setFalta(ttfFaltas.getText());
                                    reg_cad.setCadastroAlunoDisciplinaSituacaoId(sit.getCadsId());
                                    reg_cad.setFrequenciaId(fre.getFrequenciaId());
                                    reg_cad.setTipoRgOuv(cbTipo.getSelectionModel().getSelectedItem());

                                    try {
                                        jpaCad.edit(reg_cad);
                                        // COPIA ARQUIVO ATUAL SOBRE O EXISTENTE EM ESPHOTO
                                    } catch (Exception e) {
                                        mostraMsg("Erro 074a - Erro ao atualizar cadaludis ", "" + e, 2);
                                    }

                                    jpaRegAlt = new EsRegaltnotasJpaController();
                                    EsRegaltnotas reg_alt = new EsRegaltnotas();
                                    reg_alt.setCadastroAlunoDisciplinaId(gbAtuNotas.getCadastroAlunoDisciplinaId());
                                    reg_alt.setMedia(BigDecimal.valueOf(Float.valueOf(ttfMedia.getText())));
                                    reg_alt.setFalta(ttfFaltas.getText());
                                    reg_alt.setCadastroAlunoDisciplinaSituacaoId(sit.getCadsId());
                                    reg_alt.setFrequenciaId(fre.getFrequenciaId());
                                    reg_alt.setTipoRegOuv(cbTipo.getSelectionModel().getSelectedItem());

                                    reg_alt.setOldMedia(BigDecimal.valueOf(Float.valueOf(ttfMedia.getText())));
                                    reg_alt.setOldFalta(ttfFaltas.getText());
                                    reg_alt.setOldCadastroAlunoDisciplinaSituacaoId(sit.getCadsId());
                                    reg_alt.setOldFrequenciaId(fre.getFrequenciaId());
                                    reg_alt.setOldTipoRegOuv(gbAtuNotas.getTipoRegOuv());
                                    reg_alt.setDataInc(new Date());
                                    reg_alt.setCodUserInc(gbUser.getCoduser());

                                    try {
                                        jpaRegAlt = new EsRegaltnotasJpaController();
                                        jpaRegAlt.create(reg_alt);
                                        // COPIA ARQUIVO ATUAL SOBRE O EXISTENTE EM ESPHOTO
                                    } catch (Exception e) {
                                        mostraMsg("Erro 074b - Erro ao atualizar es_regaltnotas ", "" + e, 2);
                                    }

                                    Date agora = new Date();
                                    String fago = GBFORMATDATAHORA.format(agora);
                                    lblAutent.setText("Usu??rio: " + gbUser.getLogin() + "      |      Data: " + fago);

                                    alert.setHeaderText("Sucesso");
                                    alert.setContentText("Em " + fago + ", " + gbUser.getLogin() + " alterou os dados com sucesso.");
                                    option = alert.showAndWait();

                                    if (option.get() == ButtonType.OK) {

                                    }

                                    Stage stage = (Stage) buConfirma.getScene().getWindow();
                                    stage.close();

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Boolean tipoOK() {
        Boolean res = false;
        String situ = cbSituacao.getSelectionModel().getSelectedItem().toLowerCase();
        String freq = cbFrequencia.getSelectionModel().getSelectedItem().toLowerCase();
        Integer contaO = 0, contaG = 0;

        if (cbTipo.getSelectionModel().getSelectedItem().equals("O")) {
            ++contaO;
        }
        if (cbSituacao.getSelectionModel().getSelectedItem().toLowerCase().equals("ouvinte")) {
            ++contaO;
        }
        if (cbFrequencia.getSelectionModel().getSelectedItem().toLowerCase().equals("ouvinte")) {
            ++contaO;
        }

        if (cbTipo.getSelectionModel().getSelectedItem().equals("G")) {
            ++contaG;
        }
        if (cbSituacao.getSelectionModel().getSelectedItem().toLowerCase().equals("grava????o")) {
            ++contaG;
        }
        if (cbFrequencia.getSelectionModel().getSelectedItem().toLowerCase().equals("grava????o")) {
            ++contaG;
        }

        if (contaO > 0 && contaO < 3) {
            mostraMsg("Se um dos campos <SITUA????O>, <FREQU??NCIA> e <TIPO> for 'Ouvinte', os demais tamb??m tem que ser.", "", 2);
            return false;
        }

        if (contaG > 0 && contaG < 3) {
            mostraMsg("Se um dos campos <SITUA????O>, <FREQU??NCIA> e <TIPO> for 'Grava????o', os demais tamb??m tem que ser.", "", 2);
            return false;
        }

        return true;

//        if ((cbTipo.getSelectionModel().getSelectedItem().equals("O")) && (!situ.equals("ouvinte") || (!freq.equals("ouvinte")))) {
//            mostraMsg("Se campo <TIPO> for <O>, ent??o os campos <SITUA????O> e <FREQU??NCIA> t??m que ser igual a 'Ouvinte'!", "", 2);
//            return false;
//        }
//        if ((cbTipo.getSelectionModel().getSelectedItem().equals("G")) && (!situ.equals("grava????o") || (!freq.equals("grava????o")))) {
//            mostraMsg("Se campo <TIPO> for <G>, ent??o os campos <SITUA????O> e <FREQU??NCIA> t??m que ser igual a 'Grava????o'!", "", 2);
//            return false;
//        }
//        if ((!situ.equals("ouvinte") || (!freq.equals("ouvinte"))) && ((!cbTipo.getSelectionModel().getSelectedItem().equals("O")))) {
//            mostraMsg("Se os campos <SITUA????O> ou <FREQU??NCIA> forem iguais a 'Ouvinte', o campo <TIPO> tem que ser 'O'!", "", 2);
//            return false;
//        }
//        if ((!situ.equals("grava????o") || (!freq.equals("grava????o"))) && ((!cbTipo.getSelectionModel().getSelectedItem().equals("G")))) {
//            mostraMsg("Se os campos <SITUA????O> ou <FREQU??NCIA> forem iguais a 'Grava????o', o campo <TIPO> tem que ser 'G'!", "", 2);
//            return false;
//        }
//        if ((situ.equals("grava????o") ^ (freq.equals("grava????o")))) {
//            mostraMsg("Se um dos campos <SITUA????O> e <FREQU??NCIA> for 'Grava????o', o outro tamb??m tem que ser.", "", 2);
//            return false;
//        }
//        if ((situ.equals("ouvinte") ^ (freq.equals("ouvinte")))) {
//            mostraMsg("Se um dos campos <SITUA????O> e <FREQU??NCIA> for 'Ouvinte', o outro tamb??m tem que ser.", "", 2);
//            return false;
//        }
    }

    private void mudouTipo(Integer qual) {
        switch (qual) {
            case 1: // situacao
                if (cbSituacao.getSelectionModel().getSelectedItem().toLowerCase().equals("ouvinte")) {
                    cbFrequencia.setValue("Ouvinte");
                    cbTipo.setValue(("O"));
                } else {
                    if (cbSituacao.getSelectionModel().getSelectedItem().toLowerCase().equals("grava????o")) {
                        cbFrequencia.setValue("Grava????o");
                        cbTipo.setValue(("G"));
                    }
                }
                break;
            case 2: // frequencia
                if (cbFrequencia.getSelectionModel().getSelectedItem().toLowerCase().equals("ouvinte")) {
                    cbSituacao.setValue("ouvinte");
                    cbTipo.setValue(("O"));
                } else {
                    if (cbFrequencia.getSelectionModel().getSelectedItem().toLowerCase().equals("grava????o")) {
                        cbSituacao.setValue("Grava????o");
                        cbTipo.setValue(("G"));
                    }
                }
                break;
            case 3: // tipo
                if (cbTipo.getSelectionModel().getSelectedItem().equals("O")) {
                    cbSituacao.setValue("ouvinte");
                    cbFrequencia.setValue("Ouvinte");
                } else {
                    if (cbTipo.getSelectionModel().getSelectedItem().equals("G")) {
                        cbSituacao.setValue("Grava????o");
                        cbFrequencia.setValue("Grava????o");
                    }
                }

                break;
        }
    }

    @FXML
    private void mudouTipo1(ActionEvent event) {
        mudouTipo(1);
    }

    @FXML
    private void mudouTipo2(ActionEvent event) {
        mudouTipo(2);
    }

    @FXML
    private void mudouTipo3(ActionEvent event) {
        mudouTipo(3);
    }

}
