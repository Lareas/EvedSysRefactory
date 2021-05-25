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
@Table(name = "docsoficiais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docsoficiais.findAll", query = "SELECT d FROM Docsoficiais d")
    , @NamedQuery(name = "Docsoficiais.findByDocOficialId", query = "SELECT d FROM Docsoficiais d WHERE d.docOficialId = :docOficialId")
    , @NamedQuery(name = "Docsoficiais.findByNomeDocOficial", query = "SELECT d FROM Docsoficiais d WHERE d.nomeDocOficial = :nomeDocOficial")})
public class Docsoficiais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DocOficialId")
    private Short docOficialId;
    @Basic(optional = false)
    @Column(name = "NomeDocOficial")
    private String nomeDocOficial;
    
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

    public Docsoficiais() {
    }

    public Docsoficiais(Short docOficialId) {
        this.docOficialId = docOficialId;
    }

    public Docsoficiais(Short docOficialId, String nomeDocOficial) {
        this.docOficialId = docOficialId;
        this.nomeDocOficial = nomeDocOficial;
    }

    public Short getDocOficialId() {
        return docOficialId;
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

    
    public void setDocOficialId(Short docOficialId) {
        this.docOficialId = docOficialId;
    }

    public String getNomeDocOficial() {
        return nomeDocOficial;
    }

    public void setNomeDocOficial(String nomeDocOficial) {
        this.nomeDocOficial = nomeDocOficial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docOficialId != null ? docOficialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docsoficiais)) {
            return false;
        }
        Docsoficiais other = (Docsoficiais) object;
        if ((this.docOficialId == null && other.docOficialId != null) || (this.docOficialId != null && !this.docOficialId.equals(other.docOficialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Docsoficiais[ docOficialId=" + docOficialId + " ]";
    }

}
