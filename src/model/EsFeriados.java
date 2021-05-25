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
@Table(name = "es_feriados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsFeriados.findAll", query = "SELECT e FROM EsFeriados e")
    , @NamedQuery(name = "EsFeriados.findByCodesferiado", query = "SELECT e FROM EsFeriados e WHERE e.codesferiado = :codesferiado")
    , @NamedQuery(name = "EsFeriados.findByData", query = "SELECT e FROM EsFeriados e WHERE e.data = :data")
    , @NamedQuery(name = "EsFeriados.findByAno", query = "SELECT e FROM EsFeriados e WHERE e.ano = :ano")
    , @NamedQuery(name = "EsFeriados.findByFeriado", query = "SELECT e FROM EsFeriados e WHERE e.feriado = :feriado")})
public class EsFeriados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codesferiado")
    private Integer codesferiado;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "ano")
    private Short ano;
    @Column(name = "feriado")
    private String feriado;

    public EsFeriados() {
    }

    public EsFeriados(Integer codesferiado) {
        this.codesferiado = codesferiado;
    }

    public Integer getCodesferiado() {
        return codesferiado;
    }

    public void setCodesferiado(Integer codesferiado) {
        this.codesferiado = codesferiado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public String getFeriado() {
        return feriado;
    }

    public void setFeriado(String feriado) {
        this.feriado = feriado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codesferiado != null ? codesferiado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsFeriados)) {
            return false;
        }
        EsFeriados other = (EsFeriados) object;
        if ((this.codesferiado == null && other.codesferiado != null) || (this.codesferiado != null && !this.codesferiado.equals(other.codesferiado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsFeriados[ codesferiado=" + codesferiado + " ]";
    }

}
