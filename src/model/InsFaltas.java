package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

public class InsFaltas {

    private int codLista_Chamada;
    private final SimpleStringProperty nomeFalt = new SimpleStringProperty();
    private final SimpleStringProperty tipoFalt = new SimpleStringProperty();
    private final SimpleStringProperty chamaFal = new SimpleStringProperty();
    private CheckBox chkFalta;
    
    public InsFaltas(int codLista_Chamada, String nomeFalt, String tipoFalt, String chamaFal) {
        this.codLista_Chamada = codLista_Chamada;
        setNomeFalt(nomeFalt);
        setTipoFalt(tipoFalt);
        setChamaFal(chamaFal);
        this.chkFalta = new CheckBox();
    }

    public int getCodLista_Chamada() {
        return codLista_Chamada;
    }

    public void setCodLista_Chamada(int codLista_Chamada) {
        this.codLista_Chamada = codLista_Chamada;
    }

    public CheckBox getChkFalta() {
        return chkFalta;
    }

    public void setChkFalta(CheckBox chkFalta) {
        this.chkFalta = chkFalta;
    }

    
    public final StringProperty nomeFaltProperty() {
        return this.nomeFalt;
    }

    public final String getNomeFalt() {
        return this.nomeFaltProperty().get();
    }

    public final void setNomeFalt(final String nomeFalt) {
        this.nomeFaltProperty().set(nomeFalt);
    }
    
    
    public final StringProperty tipoFaltProperty() {
        return this.tipoFalt;
    }

    public final String getTipoFalt() {
        return this.tipoFaltProperty().get();
    }

    public final void setTipoFalt(final String tipoFalt) {
        this.tipoFaltProperty().set(tipoFalt);
    }
    
    
    public final StringProperty chamaFalProperty() {
        return this.chamaFal;
    }

    public final String getChamaFal() {
        return this.chamaFalProperty().get();
    }

    public final void setChamaFal(final String chamaFal) {
        this.chamaFalProperty().set(chamaFal);
    }
    
    

}
