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
@Table(name = "es_equivalencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsEquivalencia.findAll", query = "SELECT e FROM EsEquivalencia e")
    , @NamedQuery(name = "EsEquivalencia.findByCodEquivalencia", query = "SELECT e FROM EsEquivalencia e WHERE e.codEquivalencia = :codEquivalencia")
    , @NamedQuery(name = "EsEquivalencia.findByCodAluno", query = "SELECT e FROM EsEquivalencia e WHERE e.codAluno = :codAluno")
    , @NamedQuery(name = "EsEquivalencia.findByCodPrograma", query = "SELECT e FROM EsEquivalencia e WHERE e.codPrograma = :codPrograma")
    , @NamedQuery(name = "EsEquivalencia.findByCodDisciplina", query = "SELECT e FROM EsEquivalencia e WHERE e.codDisciplina = :codDisciplina")
    , @NamedQuery(name = "EsEquivalencia.findByObservacoes", query = "SELECT e FROM EsEquivalencia e WHERE e.observacoes = :observacoes")
    , @NamedQuery(name = "EsEquivalencia.findByDocsDigi", query = "SELECT e FROM EsEquivalencia e WHERE e.docsDigi = :docsDigi")
    , @NamedQuery(name = "EsEquivalencia.findByDataInc", query = "SELECT e FROM EsEquivalencia e WHERE e.dataInc = :dataInc")
    , @NamedQuery(name = "EsEquivalencia.findByDataAlt", query = "SELECT e FROM EsEquivalencia e WHERE e.dataAlt = :dataAlt")
    , @NamedQuery(name = "EsEquivalencia.findByCodUserInc", query = "SELECT e FROM EsEquivalencia e WHERE e.codUserInc = :codUserInc")
    , @NamedQuery(name = "EsEquivalencia.findByCodUserAlt", query = "SELECT e FROM EsEquivalencia e WHERE e.codUserAlt = :codUserAlt")})
public class EsEquivalencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodEquivalencia")
    private Integer codEquivalencia;
    @Column(name = "CodAluno")
    private Integer codAluno;
    @Column(name = "CodPrograma")
    private Integer codPrograma;
    @Column(name = "CodDisciplina")
    private Integer codDisciplina;
    @Column(name = "Observacoes")
    private String observacoes;
    @Column(name = "DocsDigi")
    private Boolean docsDigi;
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
    
    @Column(name = "CodGrade")
    private Integer codGrade;

    @Column(name = "CodSituacao")
    private Integer codSituacao;
    
    @Column(name = "CodPup")
    private Integer codPup;
    
    
    public EsEquivalencia() {
    }

    public EsEquivalencia(Integer codEquivalencia) {
        this.codEquivalencia = codEquivalencia;
    }

    public Integer getCodEquivalencia() {
        return codEquivalencia;
    }

    public Integer getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public Integer getCodSituacao() {
        return codSituacao;
    }

    public void setCodSituacao(Integer codSituacao) {
        this.codSituacao = codSituacao;
    }

    public Integer getCodPup() {
        return codPup;
    }

    public void setCodPup(Integer codPup) {
        this.codPup = codPup;
    }
    
    public void setCodEquivalencia(Integer codEquivalencia) {
        this.codEquivalencia = codEquivalencia;
    }

    public Integer getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Integer codAluno) {
        this.codAluno = codAluno;
    }

    public Integer getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(Integer codPrograma) {
        this.codPrograma = codPrograma;
    }

    public Integer getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(Integer codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getDocsDigi() {
        return docsDigi;
    }

    public void setDocsDigi(Boolean docsDigi) {
        this.docsDigi = docsDigi;
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
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEquivalencia != null ? codEquivalencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsEquivalencia)) {
            return false;
        }
        EsEquivalencia other = (EsEquivalencia) object;
        if ((this.codEquivalencia == null && other.codEquivalencia != null) || (this.codEquivalencia != null && !this.codEquivalencia.equals(other.codEquivalencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsEquivalencia[ codEquivalencia=" + codEquivalencia + " ]";
    }

}
