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
@Table(name = "localidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localidade.findAll", query = "SELECT l FROM Localidade l")
    , @NamedQuery(name = "Localidade.findByLocalidadeId", query = "SELECT l FROM Localidade l WHERE l.localidadeId = :localidadeId")
    , @NamedQuery(name = "Localidade.findByLocalidade", query = "SELECT l FROM Localidade l WHERE l.localidade = :localidade")})
public class Localidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LocalidadeId")
    private Short localidadeId;
    @Basic(optional = false)
    @Column(name = "Localidade")
    private String localidade;
    
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

    public Localidade() {
    }

    public Localidade(Short localidadeId) {
        this.localidadeId = localidadeId;
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
    

    public Localidade(Short localidadeId, String localidade) {
        this.localidadeId = localidadeId;
        this.localidade = localidade;
    }

    public Short getLocalidadeId() {
        return localidadeId;
    }

    public void setLocalidadeId(Short localidadeId) {
        this.localidadeId = localidadeId;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localidadeId != null ? localidadeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidade)) {
            return false;
        }
        Localidade other = (Localidade) object;
        if ((this.localidadeId == null && other.localidadeId != null) || (this.localidadeId != null && !this.localidadeId.equals(other.localidadeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Localidade[ localidadeId=" + localidadeId + " ]";
    }

}
