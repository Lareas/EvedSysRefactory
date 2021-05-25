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
@Table(name = "cordapele")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cordapele.findAll", query = "SELECT c FROM Cordapele c")
    , @NamedQuery(name = "Cordapele.findByCodcordapele", query = "SELECT c FROM Cordapele c WHERE c.codcordapele = :codcordapele")
    , @NamedQuery(name = "Cordapele.findByCordapele", query = "SELECT c FROM Cordapele c WHERE c.cordapele = :cordapele")})
public class Cordapele implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codcordapele")
    private Integer codcordapele;
    @Column(name = "cordapele")
    private String cordapele;
    
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

    public Cordapele() {
    }

    public Cordapele(Integer codcordapele) {
        this.codcordapele = codcordapele;
    }

    public Integer getCodcordapele() {
        return codcordapele;
    }

    public void setCodcordapele(Integer codcordapele) {
        this.codcordapele = codcordapele;
    }

    public String getCordapele() {
        return cordapele;
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
    

    public void setCordapele(String cordapele) {
        this.cordapele = cordapele;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcordapele != null ? codcordapele.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cordapele)) {
            return false;
        }
        Cordapele other = (Cordapele) object;
        if ((this.codcordapele == null && other.codcordapele != null) || (this.codcordapele != null && !this.codcordapele.equals(other.codcordapele))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cordapele[ codcordapele=" + codcordapele + " ]";
    }

}
