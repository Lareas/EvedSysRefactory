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
@Table(name = "es_salas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsSalas.findAll", query = "SELECT e FROM EsSalas e")
    , @NamedQuery(name = "EsSalas.findByCodessala", query = "SELECT e FROM EsSalas e WHERE e.codessala = :codessala")
    , @NamedQuery(name = "EsSalas.findByNomesala", query = "SELECT e FROM EsSalas e WHERE e.nomesala = :nomesala")
    , @NamedQuery(name = "EsSalas.findByLimitefisico", query = "SELECT e FROM EsSalas e WHERE e.limitefisico = :limitefisico")})
public class EsSalas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codessala")
    private Integer codessala;
    @Column(name = "nomesala")
    private String nomesala;
    @Column(name = "limitefisico")
    private Integer limitefisico;

    public EsSalas() {
    }

    public EsSalas(Integer codessala) {
        this.codessala = codessala;
    }

    public Integer getCodessala() {
        return codessala;
    }

    public void setCodessala(Integer codessala) {
        this.codessala = codessala;
    }

    public String getNomesala() {
        return nomesala;
    }

    public void setNomesala(String nomesala) {
        this.nomesala = nomesala;
    }

    public Integer getLimitefisico() {
        return limitefisico;
    }

    public void setLimitefisico(Integer limitefisico) {
        this.limitefisico = limitefisico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codessala != null ? codessala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsSalas)) {
            return false;
        }
        EsSalas other = (EsSalas) object;
        if ((this.codessala == null && other.codessala != null) || (this.codessala != null && !this.codessala.equals(other.codessala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsSalas[ codessala=" + codessala + " ]";
    }

}
