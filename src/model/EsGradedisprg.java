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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "es_gradedisprg")
public class EsGradedisprg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codgradedisprg")
    private Integer codgradedisprg;

    @ManyToOne
    @JoinColumn(name = "CadastroDisciplinaId", referencedColumnName = "CadastroDisciplinaId", nullable = true)
    private Cadastrodisciplina nomeCadDisciplina;

    @ManyToOne
    @JoinColumn(name = "codprograma", referencedColumnName = "cursoId", nullable = true)
    private Curso nomePrograma;

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
    
    @ManyToOne
    @JoinColumn(name = "CategDis", referencedColumnName = "CodCategDis", nullable = true)
    private EsCategdis nomeCategDis;

    public EsGradedisprg() {
    }

    public EsGradedisprg(Integer codgradedisprg) {
        this.codgradedisprg = codgradedisprg;
    }

    public String getNomedoprograma() {
        return getNomePrograma().getCurso();
    }

    public Integer getCodgradedisprg() {
        return codgradedisprg;
    }

    public void setCodgradedisprg(Integer codgradedisprg) {
        this.codgradedisprg = codgradedisprg;
    }

    public Cadastrodisciplina getNomeCadDisciplina() {
        return nomeCadDisciplina;
    }

    public void setNomeCadDisciplina(Cadastrodisciplina nomeCadDisciplina) {
        this.nomeCadDisciplina = nomeCadDisciplina;
    }

    public Curso getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(Curso nomePrograma) {
        this.nomePrograma = nomePrograma;
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

    public EsCategdis getNomeCategDis() {
        return nomeCategDis;
    }

    public void setNomeCategDis(EsCategdis nomeCategDis) {
        this.nomeCategDis = nomeCategDis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codgradedisprg != null ? codgradedisprg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsGradedisprg)) {
            return false;
        }
        EsGradedisprg other = (EsGradedisprg) object;
        if ((this.codgradedisprg == null && other.codgradedisprg != null) || (this.codgradedisprg != null && !this.codgradedisprg.equals(other.codgradedisprg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsGradedisprg[ codgradedisprg=" + codgradedisprg + " ]";
    }

}
