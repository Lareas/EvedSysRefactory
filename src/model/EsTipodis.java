package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "es_tipodis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsTipodis.findAll", query = "SELECT e FROM EsTipodis e")
    , @NamedQuery(name = "EsTipodis.findByCodtipodis", query = "SELECT e FROM EsTipodis e WHERE e.codtipodis = :codtipodis")
    , @NamedQuery(name = "EsTipodis.findByTipodisc", query = "SELECT e FROM EsTipodis e WHERE e.tipodisc = :tipodisc")})
public class EsTipodis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codtipodis")
    private Integer codtipodis;
    @Column(name = "tipodisc")
    private String tipodisc;
    @Column(name = "intervalo")
    private Integer intervalo;
    

    public EsTipodis() {
    }

    public EsTipodis(Integer codtipodis) {
        this.codtipodis = codtipodis;
    }

    public Integer getCodtipodis() {
        return codtipodis;
    }

    public void setCodtipodis(Integer codtipodis) {
        this.codtipodis = codtipodis;
    }

    public String getTipodisc() {
        return tipodisc;
    }

    public void setTipodisc(String tipodisc) {
        this.tipodisc = tipodisc;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtipodis != null ? codtipodis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsTipodis)) {
            return false;
        }
        EsTipodis other = (EsTipodis) object;
        if ((this.codtipodis == null && other.codtipodis != null) || (this.codtipodis != null && !this.codtipodis.equals(other.codtipodis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsTipodis[ codtipodis=" + codtipodis + " ]";
    }

}
