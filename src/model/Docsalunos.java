package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "docsalunos")
public class Docsalunos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddocaluno")
    private Integer coddocaluno;
    @Basic(optional = false)
    @Column(name = "DadoCadastroGeralId")
    private int dadoCadastroGeralId;
    @Basic(optional = false)
    @Column(name = "tipoDoc")
    private short tipoDoc;
    @Basic(optional = false)
    @Column(name = "labelDoc")
    private String labelDoc;
    
    @Basic(optional = false)
    @Column(name = "nomeArq")
    private String nomeArq;
    
    @Basic(optional = false)
    @Column(name = "nomeOri")
    private String nomeOri;
    
    @Column(name = "AnoLetivo")
    Short anoLetivo;

    @Column(name = "SemestreId")
    Short semestreId;
    
    @Basic(optional = false)
    @Column(name = "StatusDoc")
    private short statusDoc;
    
    
    @Column(name = "DataInc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInc;
    @Column(name = "DataAlt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlt;
    @Column(name = "CodUserInc")
    private Integer codUserInc;
    @Column(name = "CodUserAlt")
    private Integer codUserAlt;
    

    public Docsalunos() {
    }

    public Docsalunos(Integer coddocaluno) {
        this.coddocaluno = coddocaluno;
    }

    public Docsalunos(Integer coddocaluno, int dadoCadastroGeralId, short tipoDoc, String labelDoc, String nomeOri, String nomeArq, short statusDoc, Date dataInc, Date dataAlt, Integer codUserInc, Integer codUserAlt) {
        this.coddocaluno = coddocaluno;
        this.dadoCadastroGeralId = dadoCadastroGeralId;
        this.tipoDoc = tipoDoc;
        this.labelDoc = labelDoc;
        this.nomeOri = nomeOri;
        this.nomeArq = nomeArq;
        this.statusDoc = statusDoc;
        this.dataInc = dataInc;
        this.dataAlt = dataAlt;
        this.codUserInc = codUserInc;
        this.codUserAlt = codUserAlt;
    }

    public String getNomeArq() {
        return nomeArq;
    }

    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public String getNomeOri() {
        return nomeOri;
    }

    public void setNomeOri(String nomeOri) {
        this.nomeOri = nomeOri;
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

    public Integer getCodUserInc() {
        return codUserInc;
    }

    public void setCodUserInc(Integer codUserInc) {
        this.codUserInc = codUserInc;
    }

    public Integer getCodUserAlt() {
        return codUserAlt;
    }

    public void setCodUserAlt(Integer codUserAlt) {
        this.codUserAlt = codUserAlt;
    }

    

    public Integer getCoddocaluno() {
        return coddocaluno;
    }

    public void setCoddocaluno(Integer coddocaluno) {
        this.coddocaluno = coddocaluno;
    }

    public int getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(int dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public short getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(short tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getLabelDoc() {
        return labelDoc;
    }

    public void setLabelDoc(String labelDoc) {
        this.labelDoc = labelDoc;
    }

   

    public short getStatusDoc() {
        return statusDoc;
    }

    public void setStatusDoc(short statusDoc) {
        this.statusDoc = statusDoc;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddocaluno != null ? coddocaluno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docsalunos)) {
            return false;
        }
        Docsalunos other = (Docsalunos) object;
        if ((this.coddocaluno == null && other.coddocaluno != null) || (this.coddocaluno != null && !this.coddocaluno.equals(other.coddocaluno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Docsalunos[ coddocaluno=" + coddocaluno + " ]";
    }

}
