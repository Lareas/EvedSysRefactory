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
@Table(name = "tipotrabalho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipotrabalho.findAll", query = "SELECT t FROM Tipotrabalho t")
    , @NamedQuery(name = "Tipotrabalho.findByTipoTrabalhoId", query = "SELECT t FROM Tipotrabalho t WHERE t.tipoTrabalhoId = :tipoTrabalhoId")
    , @NamedQuery(name = "Tipotrabalho.findByTipoTrabalho", query = "SELECT t FROM Tipotrabalho t WHERE t.tipoTrabalho = :tipoTrabalho")})
public class Tipotrabalho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "TipoTrabalhoId")
    private Short tipoTrabalhoId;
    @Basic(optional = false)
    @Column(name = "TipoTrabalho")
    private String tipoTrabalho;

    public Tipotrabalho() {
    }

    public Tipotrabalho(Short tipoTrabalhoId) {
        this.tipoTrabalhoId = tipoTrabalhoId;
    }

    public Tipotrabalho(Short tipoTrabalhoId, String tipoTrabalho) {
        this.tipoTrabalhoId = tipoTrabalhoId;
        this.tipoTrabalho = tipoTrabalho;
    }

    public Short getTipoTrabalhoId() {
        return tipoTrabalhoId;
    }

    public void setTipoTrabalhoId(Short tipoTrabalhoId) {
        this.tipoTrabalhoId = tipoTrabalhoId;
    }

    public String getTipoTrabalho() {
        return tipoTrabalho;
    }

    public void setTipoTrabalho(String tipoTrabalho) {
        this.tipoTrabalho = tipoTrabalho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoTrabalhoId != null ? tipoTrabalhoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipotrabalho)) {
            return false;
        }
        Tipotrabalho other = (Tipotrabalho) object;
        if ((this.tipoTrabalhoId == null && other.tipoTrabalhoId != null) || (this.tipoTrabalhoId != null && !this.tipoTrabalhoId.equals(other.tipoTrabalhoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tipotrabalho[ tipoTrabalhoId=" + tipoTrabalhoId + " ]";
    }

}
