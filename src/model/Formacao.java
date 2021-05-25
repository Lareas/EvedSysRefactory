package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "formacao")
public class Formacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodFormacao")
    private Short codFormacao;
    @Basic(optional = false)
    @Column(name = "NomeFormacao")
    private String nomeFormacao;
    
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

    public Formacao() {
    }

    public Formacao(Short codFormacao) {
        this.codFormacao = codFormacao;
    }

    public Formacao(Short codFormacao, String nomeFormacao) {
        this.codFormacao = codFormacao;
        this.nomeFormacao = nomeFormacao;
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

    
    public Short getCodFormacao() {
        return codFormacao;
    }

    public void setCodFormacao(Short codFormacao) {
        this.codFormacao = codFormacao;
    }

    public String getNomeFormacao() {
        return nomeFormacao;
    }

    public void setNomeFormacao(String nomeFormacao) {
        this.nomeFormacao = nomeFormacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFormacao != null ? codFormacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formacao)) {
            return false;
        }
        Formacao other = (Formacao) object;
        if ((this.codFormacao == null && other.codFormacao != null) || (this.codFormacao != null && !this.codFormacao.equals(other.codFormacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Formacao[ codFormacao=" + codFormacao + " ]";
    }

    
}
