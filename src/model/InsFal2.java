package entities;

import javafx.scene.control.CheckBox;

public class InsFal2 {

    private int codLista_Chamada;
    private String nomeFalt;
    private String tipoFalt;
    private String chamaFal;
    private CheckBox chkFalta;

    public InsFal2(int codLista_Chamada, String nomeFalt, String tipoFalt, String chamaFal) {
        this.codLista_Chamada = codLista_Chamada;
        this.nomeFalt = nomeFalt;
        this.tipoFalt = tipoFalt;
        this.chamaFal = chamaFal;
        this.chkFalta = chkFalta;
    }

    public int getCodLista_Chamada() {
        return codLista_Chamada;
    }

    public void setCodLista_Chamada(int codLista_Chamada) {
        this.codLista_Chamada = codLista_Chamada;
    }

    public String getNomeFalt() {
        return nomeFalt;
    }

    public void setNomeFalt(String nomeFalt) {
        this.nomeFalt = nomeFalt;
    }

    public String getTipoFalt() {
        return tipoFalt;
    }

    public void setTipoFalt(String tipoFalt) {
        this.tipoFalt = tipoFalt;
    }

    public String getChamaFal() {
        return chamaFal;
    }

    public void setChamaFal(String chamaFal) {
        this.chamaFal = chamaFal;
    }

    public CheckBox getChkFalta() {
        return chkFalta;
    }

    public void setChkFalta(CheckBox chkFalta) {
        this.chkFalta = chkFalta;
    }

}
