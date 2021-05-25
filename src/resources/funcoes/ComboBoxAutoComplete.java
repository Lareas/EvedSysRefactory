package funcoes;

import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Window;

/**
 *
 * Uses a combobox tooltip as the suggestion for auto complete and updates the combo box
 * itens accordingly <br />
 * It does not work with space, space and escape cause the combobox to hide and clean the
 * filter ... Send me a PR if you want it to work with all characters -> It should be a
 * custom controller - I KNOW!
 *
 * @author wsiqueir
 *
 * @param <T>
 */
public class ComboBoxAutoComplete<T> {

    private ComboBox<T> cmb;
    String filter = "";
    private ObservableList<T> originalItems;
    private double cX;
    private double cY;

    public ComboBoxAutoComplete(ComboBox<T> cmb, ObservableList<T> originalItems, double cX, double cY) {
        this.cmb = cmb;
        this.originalItems = originalItems;
        this.cX = cX;
        this.cY = cY;
    }

    public ComboBoxAutoComplete(ComboBox<T> cmb, double cX, double cY) {
        this.cmb = cmb;
        this.cX = cX;
        this.cY = cY;
    }

    public ComboBoxAutoComplete(ComboBox<T> cmb) {
        this.cmb = cmb;
        originalItems = FXCollections.observableArrayList(cmb.getItems());
        cmb.setTooltip(new Tooltip());
        cmb.setOnKeyPressed(this::handleOnKeyPressed);
        cmb.setOnHidden(this::handleOnHiding);
    }
    
    public ComboBoxAutoComplete(ComboBox<T> cmb, String nomeTip) {
        this.cmb = cmb;
        Tooltip toolTip = new Tooltip(nomeTip);
        toolTip.setFont(Font.font("Arial", 20));
        
        originalItems = FXCollections.observableArrayList(cmb.getItems());
        cmb.setTooltip(toolTip);
        cmb.setOnKeyPressed(this::handleOnKeyPressed);
        cmb.setOnHidden(this::handleOnHiding);
    }

    public void handleOnKeyPressed(KeyEvent e) {
        ObservableList<T> filteredList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            cmb.getItems().setAll(originalItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (filter.length() == 0) {
            filteredList = originalItems;
            cmb.getTooltip().hide();
        } else {
            Stream<T> itens = cmb.getItems().stream();
            String txtUsr = filter.toString().toLowerCase();
            itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add);
            cmb.getTooltip().setText(txtUsr);
            Window stage = cmb.getScene().getWindow();
//            double posX = stage.getX() + cmb.getBoundsInParent().getMinX();
//            double posY = stage.getY() + cmb.getBoundsInParent().getMinY();
//            double posX = stage.getX() + cmb.localToScene(cmb.getBoundsInLocal()).getMinX();
//            double posY = stage.getY() + cmb.localToScene(cmb.getBoundsInLocal()).getMinY();
//            double pX = cmb.getLayoutX();
//            double pY = cmb.getLayoutY();
//            cmb.getTooltip().show(stage, posX, posY);
//            cmb.getTooltip().show(stage, pX, pY);
            cmb.getTooltip().show(stage, 0,0);
            cmb.show();
        }
        cmb.getItems().setAll(filteredList);
    }

    public void handleOnHiding(Event e) {
        filter = "";
        cmb.getTooltip().hide();
        T s = cmb.getSelectionModel().getSelectedItem();
        cmb.getItems().setAll(originalItems);
        cmb.getSelectionModel().select(s);
    }

}

//Point2D p = label.localToScene(0.0, 0.0);
//label.getTooltip().show(label,
//        p.getX() + label.getScene().getX() + label.getScene().getWindow().getX(),
//        p.getY() + label.getScene().getY() + label.getScene().getWindow().getY());
