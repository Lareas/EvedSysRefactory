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
@Table(name = "denominacao")
public class Denominacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DenominacaoId")
    private Short denominacaoId;
    @Basic(optional = false)
    @Column(name = "Denominacao")
    private String denominacao;
    @Column(name = "Ramificacao")
    private String ramificacao;
    
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

    public Denominacao() {
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
    

    public Denominacao(Short denominacaoId) {
        this.denominacaoId = denominacaoId;
    }

    public Denominacao(Short denominacaoId, String denominacao) {
        this.denominacaoId = denominacaoId;
        this.denominacao = denominacao;
    }

    public Short getDenominacaoId() {
        return denominacaoId;
    }

    public void setDenominacaoId(Short denominacaoId) {
        this.denominacaoId = denominacaoId;
    }

    public String getDenominacao() {
        return denominacao;
    }

    public void setDenominacao(String denominacao) {
        this.denominacao = denominacao;
    }

    public String getRamificacao() {
        return ramificacao;
    }

    public void setRamificacao(String ramificacao) {
        this.ramificacao = ramificacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (denominacaoId != null ? denominacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Denominacao)) {
            return false;
        }
        Denominacao other = (Denominacao) object;
        if ((this.denominacaoId == null && other.denominacaoId != null) || (this.denominacaoId != null && !this.denominacaoId.equals(other.denominacaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Denominacao[ denominacaoId=" + denominacaoId + " ]";
    }

}
