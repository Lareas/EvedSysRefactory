package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "arqsys")
public class Arqsys implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Cod")
    private Short cod;

    @Column(name = "CodSequencial")
    private Short codSequencial;

    @Column(name = "UltAno")
    private Short ultAno;

    @Column(name = "UltSemestre")
    private Short ultSemestre;

    @Column(name = "CodGrade")
    private Integer codGrade;

    public Arqsys() {
    }
    
     public Arqsys(Short ultAno, Short ultSemestre, Integer codGrade) {
        this.ultAno = ultAno;
        this.ultSemestre = ultSemestre;
        this.codGrade = codGrade;
    }


    public Arqsys(Short cod) {
        this.cod = cod;
    }

   
    public Integer getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public Short getCod() {
        return cod;
    }

    public void setCod(Short cod) {
        this.cod = cod;
    }

    public Short getCodSequencial() {
        return codSequencial;
    }

    public void setCodSequencial(Short codSequencial) {
        this.codSequencial = codSequencial;
    }

    public Short getUltAno() {
        return ultAno;
    }

    public void setUltAno(Short ultAno) {
        this.ultAno = ultAno;
    }

    public Short getUltSemestre() {
        return ultSemestre;
    }

    public void setUltSemestre(Short ultSemestre) {
        this.ultSemestre = ultSemestre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arqsys)) {
            return false;
        }
        Arqsys other = (Arqsys) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Arqsys[ cod=" + cod + " ]";
    }

}
