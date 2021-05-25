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
@Table(name = "modalidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modalidade.findAll", query = "SELECT m FROM Modalidade m")
    , @NamedQuery(name = "Modalidade.findByModalidadeId", query = "SELECT m FROM Modalidade m WHERE m.modalidadeId = :modalidadeId")
    , @NamedQuery(name = "Modalidade.findByModalidade", query = "SELECT m FROM Modalidade m WHERE m.modalidade = :modalidade")})
public class Modalidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ModalidadeId")
    private Integer modalidadeId;
    @Basic(optional = false)
    @Column(name = "Modalidade")
    private String modalidade;
    
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

    public Modalidade() {
    }

    public Modalidade(Integer modalidadeId) {
        this.modalidadeId = modalidadeId;
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
    
    

    public Modalidade(Integer modalidadeId, String modalidade) {
        this.modalidadeId = modalidadeId;
        this.modalidade = modalidade;
    }

    public Integer getModalidadeId() {
        return modalidadeId;
    }

    public void setModalidadeId(Integer modalidadeId) {
        this.modalidadeId = modalidadeId;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modalidadeId != null ? modalidadeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modalidade)) {
            return false;
        }
        Modalidade other = (Modalidade) object;
        if ((this.modalidadeId == null && other.modalidadeId != null) || (this.modalidadeId != null && !this.modalidadeId.equals(other.modalidadeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Modalidade[ modalidadeId=" + modalidadeId + " ]";
    }

}
