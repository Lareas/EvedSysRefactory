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
@Table(name = "profissoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profissoes.findAll", query = "SELECT p FROM Profissoes p")
    , @NamedQuery(name = "Profissoes.findByCodProfissao", query = "SELECT p FROM Profissoes p WHERE p.codProfissao = :codProfissao")
    , @NamedQuery(name = "Profissoes.findByNomeProfissao", query = "SELECT p FROM Profissoes p WHERE p.nomeProfissao = :nomeProfissao")})
public class Profissoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodProfissao")
    private Short codProfissao;
    @Basic(optional = false)
    @Column(name = "NomeProfissao")
    private String nomeProfissao;
    
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

    public Profissoes() {
    }

    public Profissoes(Short codProfissao) {
        this.codProfissao = codProfissao;
    }

    public Profissoes(Short codProfissao, String nomeProfissao) {
        this.codProfissao = codProfissao;
        this.nomeProfissao = nomeProfissao;
    }

    public Short getCodProfissao() {
        return codProfissao;
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
    
    

    public void setCodProfissao(Short codProfissao) {
        this.codProfissao = codProfissao;
    }

    public String getNomeProfissao() {
        return nomeProfissao;
    }

    public void setNomeProfissao(String nomeProfissao) {
        this.nomeProfissao = nomeProfissao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codProfissao != null ? codProfissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profissoes)) {
            return false;
        }
        Profissoes other = (Profissoes) object;
        if ((this.codProfissao == null && other.codProfissao != null) || (this.codProfissao != null && !this.codProfissao.equals(other.codProfissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Profissoes[ codProfissao=" + codProfissao + " ]";
    }

}
