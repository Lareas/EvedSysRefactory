/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ticoa
 */
@Entity
@Table(name = "es_regaltnotas")
public class EsRegaltnotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodRegAltNotas")
    private Integer codRegAltNotas;
    @Basic(optional = false)
    @Column(name = "CadastroAlunoDisciplinaId")
    private int cadastroAlunoDisciplinaId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Media")
    private BigDecimal media;
    @Column(name = "Falta")
    private String falta;
    @Column(name = "CadastroAlunoDisciplinaSituacaoId")
    private Short cadastroAlunoDisciplinaSituacaoId;
    @Column(name = "FrequenciaId")
    private Short frequenciaId;
    @Column(name = "TipoRegOuv")
    private String tipoRegOuv;
    @Column(name = "oldMedia")
    private BigDecimal oldMedia;
    @Column(name = "oldFalta")
    private String oldFalta;
    @Column(name = "oldCadastroAlunoDisciplinaSituacaoId")
    private Short oldCadastroAlunoDisciplinaSituacaoId;
    @Column(name = "oldFrequenciaId")
    private Short oldFrequenciaId;
    @Column(name = "oldTipoRegOuv")
    private String oldTipoRegOuv;
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

    public EsRegaltnotas() {
    }

    public EsRegaltnotas(Integer codRegAltNotas) {
        this.codRegAltNotas = codRegAltNotas;
    }

    public EsRegaltnotas(Integer codRegAltNotas, int cadastroAlunoDisciplinaId) {
        this.codRegAltNotas = codRegAltNotas;
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

    public Integer getCodRegAltNotas() {
        return codRegAltNotas;
    }

    public void setCodRegAltNotas(Integer codRegAltNotas) {
        this.codRegAltNotas = codRegAltNotas;
    }

    public int getCadastroAlunoDisciplinaId() {
        return cadastroAlunoDisciplinaId;
    }

    public void setCadastroAlunoDisciplinaId(int cadastroAlunoDisciplinaId) {
        this.cadastroAlunoDisciplinaId = cadastroAlunoDisciplinaId;
    }

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

    public String getTipoRegOuv() {
        return tipoRegOuv;
    }

    public void setTipoRegOuv(String tipoRegOuv) {
        this.tipoRegOuv = tipoRegOuv;
    }

    public BigDecimal getOldMedia() {
        return oldMedia;
    }

    public void setOldMedia(BigDecimal oldMedia) {
        this.oldMedia = oldMedia;
    }

    public String getOldFalta() {
        return oldFalta;
    }

    public void setOldFalta(String oldFalta) {
        this.oldFalta = oldFalta;
    }

    public Short getOldCadastroAlunoDisciplinaSituacaoId() {
        return oldCadastroAlunoDisciplinaSituacaoId;
    }

    public void setOldCadastroAlunoDisciplinaSituacaoId(Short oldCadastroAlunoDisciplinaSituacaoId) {
        this.oldCadastroAlunoDisciplinaSituacaoId = oldCadastroAlunoDisciplinaSituacaoId;
    }

    public Short getOldFrequenciaId() {
        return oldFrequenciaId;
    }

    public void setOldFrequenciaId(Short oldFrequenciaId) {
        this.oldFrequenciaId = oldFrequenciaId;
    }

    public String getOldTipoRegOuv() {
        return oldTipoRegOuv;
    }

    public void setOldTipoRegOuv(String oldTipoRegOuv) {
        this.oldTipoRegOuv = oldTipoRegOuv;
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
        hash += (codRegAltNotas != null ? codRegAltNotas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsRegaltnotas)) {
            return false;
        }
        EsRegaltnotas other = (EsRegaltnotas) object;
        if ((this.codRegAltNotas == null && other.codRegAltNotas != null) || (this.codRegAltNotas != null && !this.codRegAltNotas.equals(other.codRegAltNotas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsRegaltnotas[ codRegAltNotas=" + codRegAltNotas + " ]";
    }

}
