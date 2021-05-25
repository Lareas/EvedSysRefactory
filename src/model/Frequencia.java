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
@Table(name = "frequencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frequencia.findAll", query = "SELECT f FROM Frequencia f")
    , @NamedQuery(name = "Frequencia.findByFrequenciaId", query = "SELECT f FROM Frequencia f WHERE f.frequenciaId = :frequenciaId")
    , @NamedQuery(name = "Frequencia.findByFrequencia", query = "SELECT f FROM Frequencia f WHERE f.frequencia = :frequencia")})
public class Frequencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "FrequenciaId")
    private Short frequenciaId;
    @Basic(optional = false)
    @Column(name = "Frequencia")
    private String frequencia;
    
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

    public Frequencia() {
    }

    public Frequencia(Short frequenciaId) {
        this.frequenciaId = frequenciaId;
    }

    public Frequencia(Short frequenciaId, String frequencia) {
        this.frequenciaId = frequenciaId;
        this.frequencia = frequencia;
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
    
    

    public Short getFrequenciaId() {
        return frequenciaId;
    }

    public void setFrequenciaId(Short frequenciaId) {
        this.frequenciaId = frequenciaId;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (frequenciaId != null ? frequenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frequencia)) {
            return false;
        }
        Frequencia other = (Frequencia) object;
        if ((this.frequenciaId == null && other.frequenciaId != null) || (this.frequenciaId != null && !this.frequenciaId.equals(other.frequenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Frequencia[ frequenciaId=" + frequenciaId + " ]";
    }

}
