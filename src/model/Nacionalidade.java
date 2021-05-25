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
@Table(name = "nacionalidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nacionalidade.findAll", query = "SELECT n FROM Nacionalidade n")
    , @NamedQuery(name = "Nacionalidade.findByNacionalidadeId", query = "SELECT n FROM Nacionalidade n WHERE n.nacionalidadeId = :nacionalidadeId")
    , @NamedQuery(name = "Nacionalidade.findByNacionalidade", query = "SELECT n FROM Nacionalidade n WHERE n.nacionalidade = :nacionalidade")})
public class Nacionalidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "NacionalidadeId")
    private Short nacionalidadeId;
    @Basic(optional = false)
    @Column(name = "Nacionalidade")
    private String nacionalidade;
    
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

    public Nacionalidade() {
    }

    public Nacionalidade(Short nacionalidadeId) {
        this.nacionalidadeId = nacionalidadeId;
    }

    public Nacionalidade(Short nacionalidadeId, String nacionalidade) {
        this.nacionalidadeId = nacionalidadeId;
        this.nacionalidade = nacionalidade;
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
    
    

    public Short getNacionalidadeId() {
        return nacionalidadeId;
    }

    public void setNacionalidadeId(Short nacionalidadeId) {
        this.nacionalidadeId = nacionalidadeId;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nacionalidadeId != null ? nacionalidadeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacionalidade)) {
            return false;
        }
        Nacionalidade other = (Nacionalidade) object;
        if ((this.nacionalidadeId == null && other.nacionalidadeId != null) || (this.nacionalidadeId != null && !this.nacionalidadeId.equals(other.nacionalidadeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Nacionalidade[ nacionalidadeId=" + nacionalidadeId + " ]";
    }

}
