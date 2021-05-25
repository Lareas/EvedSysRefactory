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
@Table(name = "perfilusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfilusers.findAll", query = "SELECT p FROM Perfilusers p")
    , @NamedQuery(name = "Perfilusers.findByCodPerfil", query = "SELECT p FROM Perfilusers p WHERE p.codPerfil = :codPerfil")
    , @NamedQuery(name = "Perfilusers.findByPerfil", query = "SELECT p FROM Perfilusers p WHERE p.perfil = :perfil")})
public class Perfilusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodPerfil")
    private Short codPerfil;
    @Column(name = "Perfil")
    private String perfil;
    
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

    public Perfilusers() {
    }

    public Perfilusers(Short codPerfil) {
        this.codPerfil = codPerfil;
    }

    public Short getCodPerfil() {
        return codPerfil;
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
    
    

    public void setCodPerfil(Short codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPerfil != null ? codPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfilusers)) {
            return false;
        }
        Perfilusers other = (Perfilusers) object;
        if ((this.codPerfil == null && other.codPerfil != null) || (this.codPerfil != null && !this.codPerfil.equals(other.codPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Perfilusers[ codPerfil=" + codPerfil + " ]";
    }

}
