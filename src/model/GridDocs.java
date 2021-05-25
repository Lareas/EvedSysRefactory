package entities;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

public class GridDocs {

    private int codDocAluno;
    private int tipoDoc;
    private final SimpleStringProperty tipod = new SimpleStringProperty();
    private final SimpleStringProperty labelDoc = new SimpleStringProperty();
    private final SimpleStringProperty nomeOri = new SimpleStringProperty();
    private final SimpleStringProperty nomeArq = new SimpleStringProperty();
    private Short anoLetivo;
    private Short semestreId;
    private int userInc;
    private Date dataInc;
    private int userAlt;
    private Date dataAlt;
    private Short status;
    private final SimpleStringProperty userIncNome = new SimpleStringProperty();
    private final SimpleStringProperty userAltNome = new SimpleStringProperty();
    private CheckBox removeDoc;

    public GridDocs(int codDocAluno, int tipoDoc, String tipod, String labelDoc, String nomeOri, String nomeArq, Short anoLetivo, Short semestreId, Date dataInc, int userInc, Date dataAlt, int userAlt, String userIncNome, String userAltNome, Short status) {
        this.codDocAluno = codDocAluno;
        this.tipoDoc = tipoDoc;
        setTipod(tipod);
        setLabelDoc(labelDoc);
        setNomeOri(nomeOri);
        setNomeArq(nomeArq);
        this.anoLetivo = anoLetivo;
        this.semestreId = semestreId;
        this.dataInc = dataInc;
        this.userInc = userInc;
        this.dataAlt = dataAlt;
        this.userAlt = userAlt;
        setUserIncNome(userIncNome);
        setUserAltNome(userAltNome);
        this.status = status;
        this.removeDoc = new CheckBox();
    }

    public GridDocs() {
    }

    public Short getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Short anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Short getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(Short semestreId) {
        this.semestreId = semestreId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public int getCodDocAluno() {
        return codDocAluno;
    }

    public void setCodDocAluno(int codDocAluno) {
        this.codDocAluno = codDocAluno;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
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

    public final StringProperty tipodProperty() {
        return this.tipod;
    }

    public final String getTipod() {
        return this.tipodProperty().get();
    }

    public final void setTipod(final String tipod) {
        this.tipodProperty().set(tipod);
    }

    public final StringProperty labelDocProperty() {
        return this.labelDoc;
    }

    public final String getLabelDoc() {
        return this.labelDocProperty().get();
    }

    public final void setLabelDoc(final String labelDoc) {
        this.labelDocProperty().set(labelDoc);
    }
    
    
    
    public final StringProperty userIncNomeProperty() {
        return this.userIncNome;
    }

    public final String getUserIncNome() {
        return this.userIncNomeProperty().get();
    }

    public final void setUserIncNome(final String userIncNome) {
        this.userIncNomeProperty().set(userIncNome);
    }
    
    
    public final StringProperty userAltNomeProperty() {
        return this.userAltNome;
    }

    public final String getUserAltNome() {
        return this.userAltNomeProperty().get();
    }

    public final void setUserAltNome(final String userAltNome) {
        this.userAltNomeProperty().set(userAltNome);
    }
    
    
    public final StringProperty nomeArqProperty() {
        return this.nomeArq;
    }

    public final String getNomeArq() {
        return this.nomeArqProperty().get();
    }

    public final void setNomeArq(final String nomeArq) {
        this.nomeArqProperty().set(nomeArq);
    }
    
    public final StringProperty nomeOriProperty() {
        return this.nomeOri;
    }

    public final String getNomeOri() {
        return this.nomeOriProperty().get();
    }

    public final void setNomeOri(final String nomeOri) {
        this.nomeOriProperty().set(nomeOri);
    }

    public int getUserInc() {
        return userInc;
    }

    public void setUserInc(int userInc) {
        this.userInc = userInc;
    }

    public int getUserAlt() {
        return userAlt;
    }

    public void setUserAlt(int userAlt) {
        this.userAlt = userAlt;
    }


    public CheckBox getRemoveDoc() {
        return removeDoc;
    }

    public void setRemoveDoc(CheckBox removeDoc) {
        this.removeDoc = removeDoc;
    }

}
