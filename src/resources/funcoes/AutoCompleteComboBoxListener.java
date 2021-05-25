package funcoes;


import javafx.scene.input.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.scene.control.*;

public class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {
    private ComboBox<T> comboBox;
    private ObservableList<T> data;
    private boolean moveCaretToPos = false;
    private int caretPos;

    public AutoCompleteComboBoxListener(final ComboBox<T> comboBox) {
        this.comboBox = comboBox;
        data = comboBox.getItems();
    }

    @Override
    public void handle(KeyEvent event) {
      if (event.getCode() == KeyCode.DOWN){
        if(!comboBox.isShowing())comboBox.show();
        return;
      }

        if (event.getCode().isModifierKey()||event.getCode().isNavigationKey()||event.getCode().isFunctionKey()){
          return;
        }

        comboBox.hide();

        if (event.getCode() == KeyCode.ENTER||event.getCode() == KeyCode.TAB){
            return;
        }
        if (event.getCode() ==KeyCode.ESCAPE||event.getCode() ==KeyCode.DELETE){
          comboBox.getEditor().setText("");
          comboBox.setValue(null);
          comboBox.setItems(data);
          return;
        }
        if(event.getCode() == KeyCode.BACK_SPACE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        }



        ObservableList<T> list = FXCollections.observableArrayList();
        if(data!=null){
          for (int i=0; i<data.size(); i++) {
            if(data.get(i).toString().toLowerCase().contains(
                                                             AutoCompleteComboBoxListener.this.comboBox
                                                               .getEditor().getText().toLowerCase())) {
              list.add(data.get(i));
            }
          }
        }
        String t = comboBox.getEditor().getText();
        comboBox.setItems(list);
        comboBox.getEditor().setText(t);
        if(!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length());
        if(!list.isEmpty()) {
            comboBox.show();
        }
    }

    private void moveCaret(int textLength) {
        if(caretPos == -1) 
            comboBox.getEditor().positionCaret(textLength);
        else 
            comboBox.getEditor().positionCaret(caretPos);

        moveCaretToPos = false;
    }

}