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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luiza
 */
@Entity
@Table(name = "copi_matri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CopiMatri.findAll", query = "SELECT c FROM CopiMatri c")
    , @NamedQuery(name = "CopiMatri.findByCodMatriSem", query = "SELECT c FROM CopiMatri c WHERE c.codMatriSem = :codMatriSem")
    , @NamedQuery(name = "CopiMatri.findByDadoCadastroProgramaId", query = "SELECT c FROM CopiMatri c WHERE c.dadoCadastroProgramaId = :dadoCadastroProgramaId")
    , @NamedQuery(name = "CopiMatri.findByDadoCadastroGeralId", query = "SELECT c FROM CopiMatri c WHERE c.dadoCadastroGeralId = :dadoCadastroGeralId")
    , @NamedQuery(name = "CopiMatri.findByAnoLetivo", query = "SELECT c FROM CopiMatri c WHERE c.anoLetivo = :anoLetivo")
    , @NamedQuery(name = "CopiMatri.findBySemestreId", query = "SELECT c FROM CopiMatri c WHERE c.semestreId = :semestreId")
    , @NamedQuery(name = "CopiMatri.findByDataMatri", query = "SELECT c FROM CopiMatri c WHERE c.dataMatri = :dataMatri")
    , @NamedQuery(name = "CopiMatri.findByTaxaMatri", query = "SELECT c FROM CopiMatri c WHERE c.taxaMatri = :taxaMatri")
    , @NamedQuery(name = "CopiMatri.findByValorCredito", query = "SELECT c FROM CopiMatri c WHERE c.valorCredito = :valorCredito")
    , @NamedQuery(name = "CopiMatri.findByNumCreditos", query = "SELECT c FROM CopiMatri c WHERE c.numCreditos = :numCreditos")
    , @NamedQuery(name = "CopiMatri.findByValorDesconto", query = "SELECT c FROM CopiMatri c WHERE c.valorDesconto = :valorDesconto")
    , @NamedQuery(name = "CopiMatri.findByTotalAPagar", query = "SELECT c FROM CopiMatri c WHERE c.totalAPagar = :totalAPagar")
    , @NamedQuery(name = "CopiMatri.findByNumParcelas", query = "SELECT c FROM CopiMatri c WHERE c.numParcelas = :numParcelas")
    , @NamedQuery(name = "CopiMatri.findByValorSistema", query = "SELECT c FROM CopiMatri c WHERE c.valorSistema = :valorSistema")
    , @NamedQuery(name = "CopiMatri.findByCodUserAltorizaDesc", query = "SELECT c FROM CopiMatri c WHERE c.codUserAltorizaDesc = :codUserAltorizaDesc")
    , @NamedQuery(name = "CopiMatri.findByTipoPagto", query = "SELECT c FROM CopiMatri c WHERE c.tipoPagto = :tipoPagto")
    , @NamedQuery(name = "CopiMatri.findByObservacaoPagto", query = "SELECT c FROM CopiMatri c WHERE c.observacaoPagto = :observacaoPagto")
    , @NamedQuery(name = "CopiMatri.findByNumDisci", query = "SELECT c FROM CopiMatri c WHERE c.numDisci = :numDisci")
    , @NamedQuery(name = "CopiMatri.findByDataInc", query = "SELECT c FROM CopiMatri c WHERE c.dataInc = :dataInc")
    , @NamedQuery(name = "CopiMatri.findByDataAlt", query = "SELECT c FROM CopiMatri c WHERE c.dataAlt = :dataAlt")
    , @NamedQuery(name = "CopiMatri.findByCodUserInc", query = "SELECT c FROM CopiMatri c WHERE c.codUserInc = :codUserInc")
    , @NamedQuery(name = "CopiMatri.findByCodUserAlt", query = "SELECT c FROM CopiMatri c WHERE c.codUserAlt = :codUserAlt")
    , @NamedQuery(name = "CopiMatri.findByVale", query = "SELECT c FROM CopiMatri c WHERE c.vale = :vale")})
public class CopiMatri implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodMatriSem")
    private Integer codMatriSem;
    @Column(name = "DadoCadastroProgramaId")
    private Integer dadoCadastroProgramaId;
    @Column(name = "DadoCadastroGeralId")
    private Integer dadoCadastroGeralId;
    @Column(name = "AnoLetivo")
    private Short anoLetivo;
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
    @Column(name = "NumCreditos")
    private Short numCreditos;
    @Column(name = "ValorDesconto")
    private BigDecimal valorDesconto;
    @Column(name = "TotalAPagar")
    private BigDecimal totalAPagar;
    @Column(name = "NumParcelas")
    private Short numParcelas;
    @Column(name = "ValorSistema")
    private BigDecimal valorSistema;
    @Column(name = "CodUserAltorizaDesc")
    private Integer codUserAltorizaDesc;
    @Column(name = "TipoPagto")
    private Short tipoPagto;
    @Column(name = "ObservacaoPagto")
    private String observacaoPagto;
    @Column(name = "NumDisci")
    private Short numDisci;
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
    @Column(name = "vale")
    private Boolean vale;

    public CopiMatri() {
    }

    public CopiMatri(Integer codMatriSem) {
        this.codMatriSem = codMatriSem;
    }

    public Integer getCodMatriSem() {
        return codMatriSem;
    }

    public void setCodMatriSem(Integer codMatriSem) {
        this.codMatriSem = codMatriSem;
    }

    public Integer getDadoCadastroProgramaId() {
        return dadoCadastroProgramaId;
    }

    public void setDadoCadastroProgramaId(Integer dadoCadastroProgramaId) {
        this.dadoCadastroProgramaId = dadoCadastroProgramaId;
    }

    public Integer getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(Integer dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public Short getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Short anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Short getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(Short semestreId) {
        this.semestreId = semestreId;
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

    public Short getNumCreditos() {
        return numCreditos;
    }

    public void setNumCreditos(Short numCreditos) {
        this.numCreditos = numCreditos;
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

    public Short getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(Short numParcelas) {
        this.numParcelas = numParcelas;
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

    public Short getTipoPagto() {
        return tipoPagto;
    }

    public void setTipoPagto(Short tipoPagto) {
        this.tipoPagto = tipoPagto;
    }

    public String getObservacaoPagto() {
        return observacaoPagto;
    }

    public void setObservacaoPagto(String observacaoPagto) {
        this.observacaoPagto = observacaoPagto;
    }

    public Short getNumDisci() {
        return numDisci;
    }

    public void setNumDisci(Short numDisci) {
        this.numDisci = numDisci;
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

    public Boolean getVale() {
        return vale;
    }

    public void setVale(Boolean vale) {
        this.vale = vale;
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
        if (!(object instanceof CopiMatri)) {
            return false;
        }
        CopiMatri other = (CopiMatri) object;
        if ((this.codMatriSem == null && other.codMatriSem != null) || (this.codMatriSem != null && !this.codMatriSem.equals(other.codMatriSem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CopiMatri[ codMatriSem=" + codMatriSem + " ]";
    }
    
}
