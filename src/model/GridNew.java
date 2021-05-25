package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;


public class GridNew {
private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private CheckBox checkbox;    
    
     GridNew(String fName, String lName, String email) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
        this.checkbox = new CheckBox();         
        
    }
}
