package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "especificidade")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Especificidade.findAll", query = "SELECT e FROM Especificidade e")
//    , @NamedQuery(name = "Especificidade.findByEspecificidadeId", query = "SELECT e FROM Especificidade e WHERE e.especificidadeId = :especificidadeId")
//    , @NamedQuery(name = "Especificidade.findByEspecificidade", query = "SELECT e FROM Especificidade e WHERE e.especificidade = :especificidade")
//    , @NamedQuery(name = "Especificidade.fichaNormal", query = "select "
//            + " e.especificidadeId, e.especificidade, e.descricao, p.programa " //, t.nomeTime "
//            + " from Especificidade e join e.programaEspec p ") //join j.timeFut t "
//        
//        
//    , @NamedQuery(name = "Especificidade.fichaFinal", query = "select "
//            + " NEW entities.FichaEspecificidade (e.especificidadeId, e.especificidade, e.descricao, p.programa) " //, t.nomeTime "
//            + " from Especificidade e join e.programaEspec p ") //join j.timeFut t "    
//})

public class Especificidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "EspecificidadeId")
    private Short especificidadeId;
    @Basic(optional = false)
    @Column(name = "Especificidade")
    private String especificidade;
    
    @Lob
    @Column(name = "Descricao")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "ProgramaId", referencedColumnName = "ProgramaId", nullable = false)
    private Programa programaEspec;
    
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

    //  @CollectionTable
    public Especificidade() {
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

    
    public Especificidade(Short especificidadeId) {
        this.especificidadeId = especificidadeId;
    }

    public Especificidade(Short especificidadeId, String especificidade) {
        this.especificidadeId = especificidadeId;
        this.especificidade = especificidade;
    }

    public Short getEspecificidadeId() {
        return especificidadeId;
    }

    public void setEspecificidadeId(Short especificidadeId) {
        this.especificidadeId = especificidadeId;
    }

    public String getEspecificidade() {
        return especificidade;
    }

    public void setEspecificidade(String especificidade) {
        this.especificidade = especificidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Programa getProgramaEspec() {
        return programaEspec;
    }

    public void setProgramaEspec(Programa programaEspec) {
        this.programaEspec = programaEspec;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (especificidadeId != null ? especificidadeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especificidade)) {
            return false;
        }
        Especificidade other = (Especificidade) object;
        if ((this.especificidadeId == null && other.especificidadeId != null) || (this.especificidadeId != null && !this.especificidadeId.equals(other.especificidadeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Especificidade[ especificidadeId=" + especificidadeId + " ]";
    }

}
