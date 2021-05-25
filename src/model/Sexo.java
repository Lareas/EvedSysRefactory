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
@Table(name = "sexo")
public class Sexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SexoId")
    private Short sexoId;
    @Basic(optional = false)
    @Column(name = "SexoCd")
    private String sexoCd;
    @Basic(optional = false)
    @Column(name = "Sexo")
    private String sexo;
    
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

    public Sexo() {
    }

    public Sexo(Short sexoId) {
        this.sexoId = sexoId;
    }

    public Sexo(Short sexoId, String sexoCd, String sexo) {
        this.sexoId = sexoId;
        this.sexoCd = sexoCd;
        this.sexo = sexo;
    }

    public Short getSexoId() {
        return sexoId;
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

    public void setSexoId(Short sexoId) {
        this.sexoId = sexoId;
    }

    public String getSexoCd() {
        return sexoCd;
    }

    public void setSexoCd(String sexoCd) {
        this.sexoCd = sexoCd;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sexoId != null ? sexoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.sexoId == null && other.sexoId != null) || (this.sexoId != null && !this.sexoId.equals(other.sexoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sexo[ sexoId=" + sexoId + " ]";
    }

}
