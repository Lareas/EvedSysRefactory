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
@Table(name = "es_grade3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsGrade3.findAll", query = "SELECT e FROM EsGrade3 e")
    , @NamedQuery(name = "EsGrade3.findByCodGrade", query = "SELECT e FROM EsGrade3 e WHERE e.codGrade = :codGrade")
    , @NamedQuery(name = "EsGrade3.findByAnoLetivo", query = "SELECT e FROM EsGrade3 e WHERE e.anoLetivo = :anoLetivo")
    , @NamedQuery(name = "EsGrade3.findBySemestre", query = "SELECT e FROM EsGrade3 e WHERE e.semestre = :semestre")})
public class EsGrade3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codGrade")
    private Integer codGrade;
    @Column(name = "AnoLetivo")
    private Short anoLetivo;
    @Column(name = "Semestre")
    private Short semestre;

    public EsGrade3() {
    }

    public EsGrade3(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public Integer getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public Short getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Short anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Short getSemestre() {
        return semestre;
    }

    public void setSemestre(Short semestre) {
        this.semestre = semestre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codGrade != null ? codGrade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsGrade3)) {
            return false;
        }
        EsGrade3 other = (EsGrade3) object;
        if ((this.codGrade == null && other.codGrade != null) || (this.codGrade != null && !this.codGrade.equals(other.codGrade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsGrade3[ codGrade=" + codGrade + " ]";
    }

}
