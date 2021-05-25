package entities;

import java.util.Date;
import javafx.scene.control.ComboBox;

public class DocAluGrid {

    private int coddocaluno;
    private int codaluno;
    private ComboBox<String> tipoDoc;
    private String labelDoc;
    private Short statusDoc;
    private String nomeUser;
    private Date dataInc;
    private Date dataAlt;

    public DocAluGrid() {
    }

    public DocAluGrid(int coddocaluno, int codaluno, ComboBox<String> tipoDoc, String labelDoc, Short statusDoc, String nomeUser, Date dataInc, Date dataAlt) {
        this.coddocaluno = coddocaluno;
        this.codaluno = codaluno;
        this.tipoDoc = tipoDoc;
        this.labelDoc = labelDoc;
        this.statusDoc = statusDoc;
        this.nomeUser = nomeUser;
        this.dataInc = dataInc;
        this.dataAlt = dataAlt;
    }


    public int getCoddocaluno() {
        return coddocaluno;
    }

    public void setCoddocaluno(int coddocaluno) {
        this.coddocaluno = coddocaluno;
    }

    public int getCodaluno() {
        return codaluno;
    }

    public void setCodaluno(int codaluno) {
        this.codaluno = codaluno;
    }

    public ComboBox<String> getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(ComboBox<String> tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getLabelDoc() {
        return labelDoc;
    }

    public void setLabelDoc(String labelDoc) {
        this.labelDoc = labelDoc;
    }

    public Short getStatusDoc() {
        return statusDoc;
    }

    public void setStatusDoc(Short statusDoc) {
        this.statusDoc = statusDoc;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public Date getDataInc() {
        return dataInc;
    }

    public void setDataInc(Date dataInc) {
        this.dataInc = dataInc;
    }

    public Date getDataAlt() {
        return dataAlt;
    }

    public void setDataAlt(Date dataAlt) {
        this.dataAlt = dataAlt;
    }
    
    
}
