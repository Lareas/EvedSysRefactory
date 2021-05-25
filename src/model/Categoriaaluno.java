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
@Table(name = "categoriaaluno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriaaluno.findAll", query = "SELECT c FROM Categoriaaluno c")
    , @NamedQuery(name = "Categoriaaluno.findByCategoriaAlunoId", query = "SELECT c FROM Categoriaaluno c WHERE c.categoriaAlunoId = :categoriaAlunoId")
    , @NamedQuery(name = "Categoriaaluno.findByCategoriaAluno", query = "SELECT c FROM Categoriaaluno c WHERE c.categoriaAluno = :categoriaAluno")
    , @NamedQuery(name = "Categoriaaluno.findByDescricao", query = "SELECT c FROM Categoriaaluno c WHERE c.descricao = :descricao")})
public class Categoriaaluno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CategoriaAlunoId")
    private Short categoriaAlunoId;
    
    
    @Column(name = "CategoriaAluno")
    private String categoriaAluno;
    @Column(name = "Descricao")
    private String descricao;
    
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

    public Categoriaaluno() {
    }

    public Categoriaaluno(Short categoriaAlunoId) {
        this.categoriaAlunoId = categoriaAlunoId;
    }

    public Short getCategoriaAlunoId() {
        return categoriaAlunoId;
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
    

    public void setCategoriaAlunoId(Short categoriaAlunoId) {
        this.categoriaAlunoId = categoriaAlunoId;
    }

    public String getCategoriaAluno() {
        return categoriaAluno;
    }

    public void setCategoriaAluno(String categoriaAluno) {
        this.categoriaAluno = categoriaAluno;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoriaAlunoId != null ? categoriaAlunoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriaaluno)) {
            return false;
        }
        Categoriaaluno other = (Categoriaaluno) object;
        if ((this.categoriaAlunoId == null && other.categoriaAlunoId != null) || (this.categoriaAlunoId != null && !this.categoriaAlunoId.equals(other.categoriaAlunoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categoriaaluno[ categoriaAlunoId=" + categoriaAlunoId + " ]";
    }

}
