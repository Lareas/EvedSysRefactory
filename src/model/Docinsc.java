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
@Table(name = "docinsc")
public class Docinsc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codDocInsc")
    private Short codDocInsc;
    @Column(name = "NomeDocInsc")
    private String nomeDocInsc;
    
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

    public Docinsc() {
    }

    public Docinsc(Short codDocInsc) {
        this.codDocInsc = codDocInsc;
    }

    public Short getCodDocInsc() {
        return codDocInsc;
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
    

    public void setCodDocInsc(Short codDocInsc) {
        this.codDocInsc = codDocInsc;
    }

    public String getNomeDocInsc() {
        return nomeDocInsc;
    }

    public void setNomeDocInsc(String nomeDocInsc) {
        this.nomeDocInsc = nomeDocInsc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDocInsc != null ? codDocInsc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docinsc)) {
            return false;
        }
        Docinsc other = (Docinsc) object;
        if ((this.codDocInsc == null && other.codDocInsc != null) || (this.codDocInsc != null && !this.codDocInsc.equals(other.codDocInsc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Docinsc[ codDocInsc=" + codDocInsc + " ]";
    }

}
