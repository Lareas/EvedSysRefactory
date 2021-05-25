/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Notifverfsao;
import static java.lang.String.format;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jpa_controler.NotifverfsaoJpaController;

/**
 * FXML Controller class
 *
 * @author luiza
 */
public class VersoesController implements Initializable {

    @FXML
    private TableView<Notifverfsao> tvPesquisa;
    @FXML
    private TableColumn<Notifverfsao, String> tcVersao;
    @FXML
    private TableColumn<Notifverfsao, Date> tcData;
    @FXML
    private TableColumn<Notifverfsao, String> tcDesc;
    @FXML
    private TextField edVersao;
    @FXML
    private TextField edData;
    @FXML
    private TextArea taDesc;
    
    private ObservableList<Notifverfsao> dados;
    private NotifverfsaoJpaController jpaCon;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         jpaCon = new NotifverfsaoJpaController(); 
         initColumns();
         
         tcData.setCellFactory(column -> {
            TableCell<Notifverfsao, Date> cell = new TableCell<Notifverfsao, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if ((empty) || (item == null)) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
         
        tvPesquisa.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                initRegs();
            }
        }); 
         
    }  
    
    public void initRegs() {
        
        edVersao.setText(tvPesquisa.getSelectionModel().getSelectedItem().getVersao());
        edData.setText(format.format(tvPesquisa.getSelectionModel().getSelectedItem().getDataVersao()));
        taDesc.setText(tvPesquisa.getSelectionModel().getSelectedItem().getTextoVersao());
        
    }
    
    public void initColumns() {
        tcVersao.setCellValueFactory(new PropertyValueFactory<>("versao"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("dataVersao"));
        tcDesc.setCellValueFactory(new PropertyValueFactory<>("textoVersao"));
        tvPesquisa.setItems(getMeusDados());
        tvPesquisa.refresh();
    }

    public ObservableList<Notifverfsao> getMeusDados() {

        jpaCon = new NotifverfsaoJpaController();
        dados = FXCollections.observableArrayList(jpaCon.findNotifverfsaoEntities());


        if (dados == null) {
            return FXCollections.observableArrayList();
        } else {
            return dados;
        }

    }
    
}
