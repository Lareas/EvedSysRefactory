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
@Table(name = "siglaestado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Siglaestado.findAll", query = "SELECT s FROM Siglaestado s")
    , @NamedQuery(name = "Siglaestado.findBySiglaEstadoId", query = "SELECT s FROM Siglaestado s WHERE s.siglaEstadoId = :siglaEstadoId")
    , @NamedQuery(name = "Siglaestado.findBySiglaEstado", query = "SELECT s FROM Siglaestado s WHERE s.siglaEstado = :siglaEstado")
    , @NamedQuery(name = "Siglaestado.findByEstado", query = "SELECT s FROM Siglaestado s WHERE s.estado = :estado")})
public class Siglaestado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SiglaEstadoId")
    private Short siglaEstadoId;
    
    @Basic(optional = false)
    @Column(name = "SiglaEstado")
    private String siglaEstado;
    
    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;
    
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

    public Siglaestado() {
    }

    public Siglaestado(Short siglaEstadoId) {
        this.siglaEstadoId = siglaEstadoId;
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

    
    public Siglaestado(Short siglaEstadoId, String siglaEstado, String estado) {
        this.siglaEstadoId = siglaEstadoId;
        this.siglaEstado = siglaEstado;
        this.estado = estado;
    }

    public Short getSiglaEstadoId() {
        return siglaEstadoId;
    }

    public void setSiglaEstadoId(Short siglaEstadoId) {
        this.siglaEstadoId = siglaEstadoId;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siglaEstadoId != null ? siglaEstadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Siglaestado)) {
            return false;
        }
        Siglaestado other = (Siglaestado) object;
        if ((this.siglaEstadoId == null && other.siglaEstadoId != null) || (this.siglaEstadoId != null && !this.siglaEstadoId.equals(other.siglaEstadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Siglaestado[ siglaEstadoId=" + siglaEstadoId + " ]";
    }

}
