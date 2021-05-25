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
@Table(name = "ministeriais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ministeriais.findAll", query = "SELECT m FROM Ministeriais m")
    , @NamedQuery(name = "Ministeriais.findByMinisteriaisId", query = "SELECT m FROM Ministeriais m WHERE m.ministeriaisId = :ministeriaisId")
    , @NamedQuery(name = "Ministeriais.findByMinisteriais", query = "SELECT m FROM Ministeriais m WHERE m.ministeriais = :ministeriais")})
public class Ministeriais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MinisteriaisId")
    private Short ministeriaisId;
    @Basic(optional = false)
    @Column(name = "Ministeriais")
    private String ministeriais;
    
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

    public Ministeriais() {
    }

    public Ministeriais(Short ministeriaisId) {
        this.ministeriaisId = ministeriaisId;
    }

    public Ministeriais(Short ministeriaisId, String ministeriais) {
        this.ministeriaisId = ministeriaisId;
        this.ministeriais = ministeriais;
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
    
    

    public Short getMinisteriaisId() {
        return ministeriaisId;
    }

    public void setMinisteriaisId(Short ministeriaisId) {
        this.ministeriaisId = ministeriaisId;
    }

    public String getMinisteriais() {
        return ministeriais;
    }

    public void setMinisteriais(String ministeriais) {
        this.ministeriais = ministeriais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ministeriaisId != null ? ministeriaisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ministeriais)) {
            return false;
        }
        Ministeriais other = (Ministeriais) object;
        if ((this.ministeriaisId == null && other.ministeriaisId != null) || (this.ministeriaisId != null && !this.ministeriaisId.equals(other.ministeriaisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ministeriais[ ministeriaisId=" + ministeriaisId + " ]";
    }

}
