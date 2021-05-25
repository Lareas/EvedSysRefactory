package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "defesadissertacaosituacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Defesadissertacaosituacao.findAll", query = "SELECT d FROM Defesadissertacaosituacao d"),
    @NamedQuery(name = "Defesadissertacaosituacao.findByDefesaDissertacaoSituacaoId", query = "SELECT d FROM Defesadissertacaosituacao d WHERE d.defesaDissertacaoSituacaoId = :defesaDissertacaoSituacaoId"),
    @NamedQuery(name = "Defesadissertacaosituacao.findByDefesaDissertacaoSituacao", query = "SELECT d FROM Defesadissertacaosituacao d WHERE d.defesaDissertacaoSituacao = :defesaDissertacaoSituacao")})
public class Defesadissertacaosituacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DefesaDissertacaoSituacaoId")
    private Short defesaDissertacaoSituacaoId;
    @Basic(optional = false)
    @Column(name = "DefesaDissertacaoSituacao")
    private String defesaDissertacaoSituacao;
    
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

    public Defesadissertacaosituacao() {
    }

    public Defesadissertacaosituacao(Short defesaDissertacaoSituacaoId) {
        this.defesaDissertacaoSituacaoId = defesaDissertacaoSituacaoId;
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
    

    public Defesadissertacaosituacao(Short defesaDissertacaoSituacaoId, String defesaDissertacaoSituacao) {
        this.defesaDissertacaoSituacaoId = defesaDissertacaoSituacaoId;
        this.defesaDissertacaoSituacao = defesaDissertacaoSituacao;
    }

    public Short getDefesaDissertacaoSituacaoId() {
        return defesaDissertacaoSituacaoId;
    }

    public void setDefesaDissertacaoSituacaoId(Short defesaDissertacaoSituacaoId) {
        this.defesaDissertacaoSituacaoId = defesaDissertacaoSituacaoId;
    }

    public String getDefesaDissertacaoSituacao() {
        return defesaDissertacaoSituacao;
    }

    public void setDefesaDissertacaoSituacao(String defesaDissertacaoSituacao) {
        this.defesaDissertacaoSituacao = defesaDissertacaoSituacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (defesaDissertacaoSituacaoId != null ? defesaDissertacaoSituacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Defesadissertacaosituacao)) {
            return false;
        }
        Defesadissertacaosituacao other = (Defesadissertacaosituacao) object;
        if ((this.defesaDissertacaoSituacaoId == null && other.defesaDissertacaoSituacaoId != null) || (this.defesaDissertacaoSituacaoId != null && !this.defesaDissertacaoSituacaoId.equals(other.defesaDissertacaoSituacaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Defesadissertacaosituacao[ defesaDissertacaoSituacaoId=" + defesaDissertacaoSituacaoId + " ]";
    }

}
