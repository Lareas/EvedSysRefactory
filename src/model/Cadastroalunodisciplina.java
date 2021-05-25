package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cadastroalunodisciplina")

public class Cadastroalunodisciplina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CadastroAlunoDisciplinaId")
    private Integer cadastroAlunoDisciplinaId;
    
    @Column(name = "DadoCadastroGeralId")
    private Integer dadoCadastroGeralId;

//    @Column(name = "CadastroDisciplinaId")
//    private Short cadastroDisciplinaId;
    @Column(name = "DadoCadastroProgramaId")
    private Integer dadoCadastroProgramaId;

    @ManyToOne
    @JoinColumn(name = "CadastroDisciplinaId", referencedColumnName = "cadastroDisciplinaId", nullable = true)
    private Cadastrodisciplina nomeCadDisciplina;

//    @ManyToOne
//    @JoinColumn(name = "DadoCadastroProgramaId", referencedColumnName = "dadoCadastroProgramaId", nullable = true)
//    private Dadocadastroprograma nomePrograma;
// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Media")
    private BigDecimal media;
    @Column(name = "Falta")
    private String falta;
    @Column(name = "CadastroAlunoDisciplinaSituacaoId")
    private Short cadastroAlunoDisciplinaSituacaoId;
    @Column(name = "FrequenciaId")
    private Short frequenciaId;
    @Column(name = "Supervisionada")
    private Boolean supervisionada;

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

    // CAMPOS NOVOS - EVEDSYS
//    @Column(name = "CodMatriSem")
//    private Integer codMatriSem;
    @Column(name = "TipoRegOuv")
    private String tipoRgOuv;

    @Column(name = "CodGrade")
    private Integer codGrade;
    
    @Column(name = "IndTipoRegOuv")
    private Integer indTipoRegOuv;

    @ManyToOne
    @JoinColumn(name = "CodMatriSem", referencedColumnName = "codMatriSem", nullable = true)
    private EsMatrisem nomeMatriSem;
    
    @Column(name = "CodEquivalencia")
    private Integer codEquivalencia;

    public Integer getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(Integer dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }
    
    public Integer getIndTipoRegOuv() {
        return indTipoRegOuv;
    }

    public void setIndTipoRegOuv(Integer indTipoRegOuv) {
        this.indTipoRegOuv = indTipoRegOuv;
    }
    
    public Cadastroalunodisciplina() {
    }

    public Cadastroalunodisciplina(Integer cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public Integer getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public Integer getCodEquivalencia() {
        return codEquivalencia;
    }

    public void setCodEquivalencia(Integer codEquivalencia) {
        this.codEquivalencia = codEquivalencia;
    }

    
    public Integer getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public void setCadastroAlunoDisciplinaId(Integer cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public Date getDataInc() {
        return dataInc;
    }

    public Integer getDadoCadastroProgramaId() {
        return dadoCadastroProgramaId;
    }

    public void setDadoCadastroProgramaId(Integer dadoCadastroProgramaId) {
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
    }

    
    public void setDataInc(Date dataInc) {
        this.dataInc = dataInc;
    }

    public EsMatrisem getNomeMatriSem() {
        return nomeMatriSem;
    }

    public void setNomeMatriSem(EsMatrisem nomeMatriSem) {
        this.nomeMatriSem = nomeMatriSem;
    }


    public String getTipoRgOuv() {
        return tipoRgOuv;
    }

    public void setTipoRgOuv(String tipoRgOuv) {
        this.tipoRgOuv = tipoRgOuv;
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

    public Cadastrodisciplina getNomeCadDisciplina() {
        return nomeCadDisciplina;
    }

    public void setNomeCadDisciplina(Cadastrodisciplina nomeCadDisciplina) {
        this.nomeCadDisciplina = nomeCadDisciplina;
    }


//    public Dadocadastroprograma getNomePrograma() {
//        return nomePrograma;
//    }
//
//    public void setNomePrograma(Dadocadastroprograma nomePrograma) {
//        this.nomePrograma = nomePrograma;
//    }
    public BigDecimal getMedia() {
        return media;
    }

    public void setMedia(BigDecimal media) {
        this.media = media;
    }

    public String getFalta() {
        return falta;
    }

    public void setFalta(String falta) {
        this.falta = falta;
    }

    public Short getCadastroAlunoDisciplinaSituacaoId() {
        return cadastroAlunoDisciplinaSituacaoId;
    }

    public void setCadastroAlunoDisciplinaSituacaoId(Short cadastroAlunoDisciplinaSituacaoId) {
        this.cadastroAlunoDisciplinaSituacaoId = cadastroAlunoDisciplinaSituacaoId;
    }

    public Short getFrequenciaId() {
        return frequenciaId;
    }

    public void setFrequenciaId(Short frequenciaId) {
        this.frequenciaId = frequenciaId;
    }

    public Boolean getSupervisionada() {
        return supervisionada;
    }

    public void setSupervisionada(Boolean supervisionada) {
        this.supervisionada = supervisionada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadastroAlunoDisciplinaId != null ? cadastroAlunoDisciplinaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cadastroalunodisciplina)) {
            return false;
        }
        Cadastroalunodisciplina other = (Cadastroalunodisciplina) object;
        if ((this.cadastroAlunoDisciplinaId == null && other.cadastroAlunoDisciplinaId != null) || (this.cadastroAlunoDisciplinaId != null && !this.cadastroAlunoDisciplinaId.equals(other.cadastroAlunoDisciplinaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cadastroalunodisciplina[ cadastroAlunoDisciplinaId=" + cadastroAlunoDisciplinaId + " ]";
    }

}
