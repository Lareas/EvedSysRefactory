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
@Table(name = "tracking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tracking.findAll", query = "SELECT t FROM Tracking t")
    , @NamedQuery(name = "Tracking.findByCodtracking", query = "SELECT t FROM Tracking t WHERE t.codtracking = :codtracking")
    , @NamedQuery(name = "Tracking.findByCoduser", query = "SELECT t FROM Tracking t WHERE t.coduser = :coduser")
    , @NamedQuery(name = "Tracking.findByDatahora", query = "SELECT t FROM Tracking t WHERE t.datahora = :datahora")
    , @NamedQuery(name = "Tracking.findByTela", query = "SELECT t FROM Tracking t WHERE t.tela = :tela")
    , @NamedQuery(name = "Tracking.findByArquivo", query = "SELECT t FROM Tracking t WHERE t.arquivo = :arquivo")
    , @NamedQuery(name = "Tracking.findByAtividade", query = "SELECT t FROM Tracking t WHERE t.atividade = :atividade")
    , @NamedQuery(name = "Tracking.findByDescricao", query = "SELECT t FROM Tracking t WHERE t.descricao = :descricao")})
public class Tracking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codtracking")
    private Integer codtracking;
    @Column(name = "coduser")
    private Short coduser;
    @Column(name = "datahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;
    @Column(name = "tela")
    private Short tela;
    @Column(name = "arquivo")
    private String arquivo;
    @Column(name = "atividade")
    private String atividade;
    @Column(name = "descricao")
    private String descricao;
    
    public Tracking() {
    }

    public Tracking(Integer codtracking) {
        this.codtracking = codtracking;
    }

    public Integer getCodtracking() {
        return codtracking;
    }

    public void setCodtracking(Integer codtracking) {
        this.codtracking = codtracking;
    }

    public Short getCoduser() {
        return coduser;
    }

    public void setCoduser(Short coduser) {
        this.coduser = coduser;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public Short getTela() {
        return tela;
    }

    public void setTela(Short tela) {
        this.tela = tela;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
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
        hash += (codtracking != null ? codtracking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tracking)) {
            return false;
        }
        Tracking other = (Tracking) object;
        if ((this.codtracking == null && other.codtracking != null) || (this.codtracking != null && !this.codtracking.equals(other.codtracking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tracking[ codtracking=" + codtracking + " ]";
    }

}
