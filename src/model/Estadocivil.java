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
@Table(name = "estadocivil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadocivil.findAll", query = "SELECT e FROM Estadocivil e")
    , @NamedQuery(name = "Estadocivil.findByEstadoCivilId", query = "SELECT e FROM Estadocivil e WHERE e.estadoCivilId = :estadoCivilId")
    , @NamedQuery(name = "Estadocivil.findByEstadoCivil", query = "SELECT e FROM Estadocivil e WHERE e.estadoCivil = :estadoCivil")})
public class Estadocivil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "EstadoCivilId")
    private Short estadoCivilId;
    @Basic(optional = false)
    @Column(name = "EstadoCivil")
    private String estadoCivil;
    
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

    public Estadocivil() {
    }

    public Estadocivil(Short estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
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

    
    public Estadocivil(Short estadoCivilId, String estadoCivil) {
        this.estadoCivilId = estadoCivilId;
        this.estadoCivil = estadoCivil;
    }

    public Short getEstadoCivilId() {
        return estadoCivilId;
    }

    public void setEstadoCivilId(Short estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoCivilId != null ? estadoCivilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadocivil)) {
            return false;
        }
        Estadocivil other = (Estadocivil) object;
        if ((this.estadoCivilId == null && other.estadoCivilId != null) || (this.estadoCivilId != null && !this.estadoCivilId.equals(other.estadoCivilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Estadocivil[ estadoCivilId=" + estadoCivilId + " ]";
    }

}
