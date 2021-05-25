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
@Table(name = "notifverfsao")
public class Notifverfsao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodNotifVerfsao")
    private Integer codNotifVerfsao;
    
    @Column(name = "Versao")
    private String versao;
    @Column(name = "DataVersao")
    @Temporal(TemporalType.DATE)
    private Date dataVersao;
    @Column(name = "TextoVersao")
    private String textoVersao;

    public Notifverfsao() {
    }

    public Notifverfsao(Integer codNotifVerfsao) {
        this.codNotifVerfsao = codNotifVerfsao;
    }

    public Integer getCodNotifVerfsao() {
        return codNotifVerfsao;
    }

    public void setCodNotifVerfsao(Integer codNotifVerfsao) {
        this.codNotifVerfsao = codNotifVerfsao;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Date getDataVersao() {
        return dataVersao;
    }

    public void setDataVersao(Date dataVersao) {
        this.dataVersao = dataVersao;
    }

    public String getTextoVersao() {
        return textoVersao;
    }

    public void setTextoVersao(String textoVersao) {
        this.textoVersao = textoVersao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codNotifVerfsao != null ? codNotifVerfsao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notifverfsao)) {
            return false;
        }
        Notifverfsao other = (Notifverfsao) object;
        if ((this.codNotifVerfsao == null && other.codNotifVerfsao != null) || (this.codNotifVerfsao != null && !this.codNotifVerfsao.equals(other.codNotifVerfsao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Notifverfsao[ codNotifVerfsao=" + codNotifVerfsao + " ]";
    }

}
