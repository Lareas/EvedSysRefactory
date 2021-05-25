/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author ticoa
 */
@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DisciplinaId")
    private Integer disciplinaId;
    @Basic(optional = false)
    @Column(name = "Disciplina")
    private String disciplina;
    @Basic(optional = false)
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "Credito")
    private Short credito;
    @Column(name = "CargaHoraria")
    private Float cargaHoraria;
//    @Column(name = "Especificidade")
//    private String especificidade;
    @Column(name = "Ementa")
    private String ementa;
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
    @JoinColumn(name = "CodEspecificidade", referencedColumnName = "especificidadeId", nullable = true)
    private Especificidade nomeEspecificidade;
    
    @Lob
    @Column(name = "ProgramaCurso")
    private byte[] programaCurso;
    @Column(name = "NomeArquivo")
    private String nomeArquivo;
    @Column(name = "PathArquivo")
    private String pathArquivo;

    public Disciplina() {
    }

    public Disciplina(Integer disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Disciplina(Integer disciplinaId, String disciplina, String descricao) {
        this.disciplinaId = disciplinaId;
        this.disciplina = disciplina;
        this.descricao = descricao;
    }

    public Integer getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Integer disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getCredito() {
        return credito;
    }

    public void setCredito(Short credito) {
        this.credito = credito;
    }

    public Float getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
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

    public Especificidade getNomeEspecificidade() {
        return nomeEspecificidade;
    }

    public void setNomeEspecificidade(Especificidade nomeEspecificidade) {
        this.nomeEspecificidade = nomeEspecificidade;
    }

    public byte[] getProgramaCurso() {
        return programaCurso;
    }

    public void setProgramaCurso(byte[] programaCurso) {
        this.programaCurso = programaCurso;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getPathArquivo() {
        return pathArquivo;
    }

    public void setPathArquivo(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disciplinaId != null ? disciplinaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.disciplinaId == null && other.disciplinaId != null) || (this.disciplinaId != null && !this.disciplinaId.equals(other.disciplinaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Disciplina[ disciplinaId=" + disciplinaId + " ]";
    }
    
}
