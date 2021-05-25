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
@Table(name = "es_matrisem")
public class EsMatrisem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodMatriSem")
    private Integer codMatriSem;
    @Basic(optional = false)
    @Column(name = "DadoCadastroProgramaId")
    private Integer dadoCadastroProgramaId;
    @Basic(optional = false)
    @Column(name = "DadoCadastroGeralId")
    private int dadoCadastroGeralId;
    @Basic(optional = false)
    @Column(name = "AnoLetivo")
    private short anoLetivo;
    @Basic(optional = false)
    @Column(name = "SemestreId")
    private Short semestreId;
    @Column(name = "DataMatri")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMatri;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TaxaMatri")
    private BigDecimal taxaMatri;
    @Column(name = "ValorCredito")
    private BigDecimal valorCredito;
    @Column(name = "NumCredsRE")
    private Short numCredsRE;
    @Column(name = "NumCredsO")
    private Short numCredsO;
    @Column(name = "PercDescCred")
    private BigDecimal percDescCred;
    @Column(name = "ValorDesconto")
    private BigDecimal valorDesconto;
    @Column(name = "TotalAPagar")
    private BigDecimal totalAPagar;
    @Column(name = "ValorSistema")
    private BigDecimal valorSistema;
    @Column(name = "CodUserAltorizaDesc")
    private Integer codUserAltorizaDesc;
    @Column(name = "ObservacaoPagto")
    private String observacaoPagto;
    @Column(name = "NumDisci")
    private Short numDisci;
    @Column(name = "NumFormaPagto")
    private Short numFormaPagto;
    
    @Column(name = "DataSecretaria1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSecretaria1;
    @Column(name = "DataSecretaria2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSecretaria2;
    @Column(name = "DataFinanceiro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinanceiro;
    @Column(name = "DataCoordenacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCoordenacao;
    
    @Column(name = "SituacaoMatri")
    private Short situacaoMatri;
    
    @Column(name = "CodGrade")
    private Integer codGrade;
    
    @Column(name = "ObsMatri")
    private String obsMatri;
    
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
    

    public EsMatrisem() {
    }

    public EsMatrisem(Integer codMatriSem) {
        this.codMatriSem = codMatriSem;
    }

    public EsMatrisem(Integer codMatriSem, Integer dadoCadastroProgramaId, int dadoCadastroGeralId, short anoLetivo, short semestreId) {
        this.codMatriSem = codMatriSem;
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
        this.dadoCadastroGeralId = dadoCadastroGeralId;
        this.anoLetivo = anoLetivo;
        this.semestreId = semestreId;
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
    
    

    public String getObsMatri() {
        return obsMatri;
    }

    public void setObsMatri(String obsMatri) {
        this.obsMatri = obsMatri;
    }

    
    public Integer getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public Short getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(Short semestreId) {
        this.semestreId = semestreId;
    }
    
    

    public Integer getCodMatriSem() {
        return codMatriSem;
    }

    public void setCodMatriSem(Integer codMatriSem) {
        this.codMatriSem = codMatriSem;
    }

    public int getDadoCadastroProgramaId() {
        return dadoCadastroProgramaId;
    }

    public void setDadoCadastroProgramaId(Integer dadoCadastroProgramaId) {
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
    }

    public int getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(int dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public short getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(short anoLetivo) {
        this.anoLetivo = anoLetivo;
    }


    public Date getDataMatri() {
        return dataMatri;
    }

    public void setDataMatri(Date dataMatri) {
        this.dataMatri = dataMatri;
    }

    public BigDecimal getTaxaMatri() {
        return taxaMatri;
    }

    public void setTaxaMatri(BigDecimal taxaMatri) {
        this.taxaMatri = taxaMatri;
    }

    public BigDecimal getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }

    public Short getNumCredsRE() {
        return numCredsRE;
    }

    public void setNumCredsRE(Short numCredsRE) {
        this.numCredsRE = numCredsRE;
    }

    public Short getNumCredsO() {
        return numCredsO;
    }

    public void setNumCredsO(Short numCredsO) {
        this.numCredsO = numCredsO;
    }

    public BigDecimal getPercDescCred() {
        return percDescCred;
    }

    public void setPercDescCred(BigDecimal percDescCred) {
        this.percDescCred = percDescCred;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public BigDecimal getValorSistema() {
        return valorSistema;
    }

    public void setValorSistema(BigDecimal valorSistema) {
        this.valorSistema = valorSistema;
    }

    public Integer getCodUserAltorizaDesc() {
        return codUserAltorizaDesc;
    }

    public void setCodUserAltorizaDesc(Integer codUserAltorizaDesc) {
        this.codUserAltorizaDesc = codUserAltorizaDesc;
    }

    public String getObservacaoPagto() {
        return observacaoPagto;
    }

    public void setObservacaoPagto(String observacaoPagto) {
        this.observacaoPagto = observacaoPagto;
    }

    public Date getDataSecretaria1() {
        return dataSecretaria1;
    }

    public void setDataSecretaria1(Date dataSecretaria1) {
        this.dataSecretaria1 = dataSecretaria1;
    }

    public Date getDataSecretaria2() {
        return dataSecretaria2;
    }

    public void setDataSecretaria2(Date dataSecretaria2) {
        this.dataSecretaria2 = dataSecretaria2;
    }

    public Short getSituacaoMatri() {
        return situacaoMatri;
    }

    public void setSituacaoMatri(Short situacaoMatri) {
        this.situacaoMatri = situacaoMatri;
    }

    
    public Date getDataFinanceiro() {
        return dataFinanceiro;
    }

    public void setDataFinanceiro(Date dataFinanceiro) {
        this.dataFinanceiro = dataFinanceiro;
    }

    public Date getDataCoordenacao() {
        return dataCoordenacao;
    }

    public void setDataCoordenacao(Date dataCoordenacao) {
        this.dataCoordenacao = dataCoordenacao;
    }
    
    

    public Short getNumDisci() {
        return numDisci;
    }

    public void setNumDisci(Short numDisci) {
        this.numDisci = numDisci;
    }

    public Short getNumFormaPagto() {
        return numFormaPagto;
    }

    public void setNumFormaPagto(Short numFormaPagto) {
        this.numFormaPagto = numFormaPagto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMatriSem != null ? codMatriSem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsMatrisem)) {
            return false;
        }
        EsMatrisem other = (EsMatrisem) object;
        if ((this.codMatriSem == null && other.codMatriSem != null) || (this.codMatriSem != null && !this.codMatriSem.equals(other.codMatriSem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsMatrisem[ codMatriSem=" + codMatriSem + " ]";
    }
    
}
