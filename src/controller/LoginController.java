package controller;

import static main.Login.secretCase;
import static main.Login.ESVERSION;
import static main.Login.gbUser;
import static main.Login.gbDeOnde;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entities.Tabuser;
import funcoes.MyFunc;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jpa_controler.TabuserJpaController;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private VBox vbQualBD;
    @FXML
    private JFXRadioButton rb1;
    @FXML
    private ToggleGroup gp1;
    @FXML
    private JFXRadioButton rb2;
    @FXML
    private JFXRadioButton rb3;
    @FXML
    private Label lblVersion;

//  Monitoramento monitor = new Monitoramento(gbTemNotif, gbNomeUser, gbTranca, gbIdioma); // inicia váriaveis globais
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblVersion.setText(ESVERSION);
        vbQualBD.setVisible(false);
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        tentaLogar();
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    private void closeStage() {
    //    if (!username.getScene().equals(null)) {
            ((Stage) username.getScene().getWindow()).close();
   //     }
        
    }

    private void loginInvalido() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Login Inválido");
        alert.setContentText("Informe o usuário e a senha");
        alert.show();
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Principal.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Eved Sys - Menu Principal - " + ESVERSION);
            stage.setScene(new Scene(parent));
            stage.show();
            // LibraryAssistantUtil.setStageIcon(stage);
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                public void handle(WindowEvent we) {
//                    System.out.println("Stage is closing");
//                }
//            });

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class
                    .getName()).log(Level.SEVERE, null, ex);
            MyFunc.mostraMsg("Erro 005 ao carregar o Menu Principal", "" + ex, 2);
        }
    }

    @FXML
    private void clicouEnter(KeyEvent event) {

    }

    private void tentaLogar() {
        //   JOptionPane.showMessageDialog(null, "usu = " + usu +  "  sen = " + sen);
        // se um dos dois campos estiver vazio...
        if ((username.getText().equals("")) || (password.getText().equals(""))) { // OU USER OU SENHA VAZIOS(username.getText().equals("") || password.getText().equals("")) {
            loginInvalido();
        } else {

            // verifica usuário e senha
            TabuserJpaController usuCon = new TabuserJpaController(); // COLOQUEI UM CONSTRUCTOR VAZIO
            Tabuser obj_usu = usuCon.findUserPorLogin(username.getText());

            if (obj_usu != null) {

                if (!password.getText().equals(obj_usu.getPassword())) { // (!password.getText().equals(verificaSenha))   {

                    // JOptionPane.showMessageDialog(null, "333 sen = " + sen +  "  verificaSenha = " + verificaSenha);
                    // Senha não confere
                    loginInvalido();

                } else {

                    // LOGIN OK
                    closeStage();
                    gbUser = obj_usu;

                    // CHAMA PRINCIPAL
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Eved Sys - Menu Principal - " + ESVERSION);
                        stage.setScene(new Scene(parent));
                        stage.show();
                        // LibraryAssistantUtil.setStageIcon(stage);

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class
                                .getName()).log(Level.SEVERE, null, ex);
                        MyFunc.mostraMsg("Erro 005 ao carregar o Menu Principal", "" + ex, 2);
                    }

//                    Stage dialogAPPLICATION_MODAL = new Stage();
//                    dialogAPPLICATION_MODAL.initModality(Modality.APPLICATION_MODAL);
//
//                    Scene sceneAPPLICATION_MODAL = new Scene(VBoxBuilder.create()
//                            .children(
//                                    new Text("APPLICATION_MODAL dialog"),
//                                    new Text("Defines a modal window that blocks events "
//                                            + "from being delivered to any other application window."))
//                            .alignment(Pos.CENTER)
//                            .padding(new Insets(10))
//                            .build());
//
//                    dialogAPPLICATION_MODAL.setTitle("APPLICATION_MODAL dialog");
//                    dialogAPPLICATION_MODAL.setScene(sceneAPPLICATION_MODAL);
//                    dialogAPPLICATION_MODAL.show();
//
//
//                    Principal p = new Principal();
//
//                    // loadMain();
//                    try {
//                        p.start(new Stage());
//
//                    } catch (Exception ex) {
//                        Logger.getLogger(LoginController.class
//                                .getName()).log(Level.SEVERE, null, ex);
//                    }
                }
            } else {
                loginInvalido();
            }

        }

    }

    @FXML
    private void clicouEnterr(KeyEvent event) {
        username.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    tentaLogar();
                }
            }
        });
    }

    @FXML
    private void clicouSecretM(MouseEvent event) {
        // AQUI ACESSA MENU PARA ESCOLHER BD 
        if (event.isControlDown() && (event.isAltDown())) {
            vbQualBD.setVisible(true);
            username.setText("lfa");
            password.setText("man@");
        }
    }

    @FXML
    private void clicouSecretV(MouseEvent event) {
//        // AQUI ACESSA BD Jpa_EvedSys_Local
//        if (event.isControlDown() && (event.isAltDown())) {
//            username.setText("lfa");
//            password.setText("man@");
//            gbDeOnde = "Jpa_EvedSys_Local";
//            tentaLogar();
//        }
    }

    @FXML
    private void clicouEntradaSecreta(ActionEvent event) {
        if (rb1.isSelected()) {
            gbDeOnde = "Jpa_EvedSys_Server"; // LOCAL 
        } else {
            if (rb2.isSelected()) {
                gbDeOnde = "Jpa_EvedSys_Local"; // LOCAL 
            } else { //rb3 isSelected
                gbDeOnde = "Jpa_EvedSys_Loc_Copia04_CA"; // LOCAL  
            }
        }
        tentaLogar();
    }

    @FXML
    private void clicouSecretD(MouseEvent event) {
        // AQUI ACESSA BD Jpa_EvedSys_Local
        if (event.isControlDown() && (event.isAltDown())) {
            if (username.getText().equals("")) {
                username.setText("lfa");
                password.setText("man@");
            }
            gbDeOnde = "Jpa_EvedSys_Loc_Copia04_CA";
            secretCase = "C:\\EvS\\ESPHOTO";
            tentaLogar();
        }
    }

    @FXML
    private void clicouSecretE(MouseEvent event
    ) {
        // AQUI ACESSA BD Jpa_EvedSys_Server
        if (event.isControlDown() && (event.isAltDown())) {
            username.setText("lfa");
            password.setText("man@");
            gbDeOnde = "Jpa_EvedSys_Server";
            tentaLogar();
        }
    }
}
