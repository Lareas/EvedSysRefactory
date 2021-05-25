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
 * @author ticoa
 */
@Entity
@Table(name = "es_pagamentosdetalhes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsPagamentosdetalhes.findAll", query = "SELECT e FROM EsPagamentosdetalhes e")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByCodPagDet", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.codPagDet = :codPagDet")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByCodMatriSem", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.codMatriSem = :codMatriSem")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByNumParcela", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.numParcela = :numParcela")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByTotParcelas", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.totParcelas = :totParcelas")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByDataVencto", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.dataVencto = :dataVencto")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByValorAPagar", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.valorAPagar = :valorAPagar")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByTipoPagto", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.tipoPagto = :tipoPagto")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByNumBoleto", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.numBoleto = :numBoleto")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByDataPagto", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.dataPagto = :dataPagto")
    , @NamedQuery(name = "EsPagamentosdetalhes.findByValorPago", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.valorPago = :valorPago")
    , @NamedQuery(name = "EsPagamentosdetalhes.findBySituacaoPagto", query = "SELECT e FROM EsPagamentosdetalhes e WHERE e.situacaoPagto = :situacaoPagto")})
public class EsPagamentosdetalhes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodPagDet")
    private Integer codPagDet;
    @Column(name = "CodMatriSem")
    private Integer codMatriSem;
    @Column(name = "NumParcela")
    private Short numParcela;
    @Column(name = "TotParcelas")
    private Short totParcelas;
    @Column(name = "DataVencto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValorAPagar")
    private BigDecimal valorAPagar;
    @Column(name = "TipoPagto")
    private Short tipoPagto;
    @Column(name = "NumBoleto")
    private String numBoleto;
    @Column(name = "Data Pagto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagto;
    @Column(name = "ValorPago")
    private BigDecimal valorPago;
    @Column(name = "SituacaoPagto")
    private Short situacaoPagto;

    public EsPagamentosdetalhes() {
    }

    public EsPagamentosdetalhes(Integer codPagDet) {
        this.codPagDet = codPagDet;
    }

    public Integer getCodPagDet() {
        return codPagDet;
    }

    public void setCodPagDet(Integer codPagDet) {
        this.codPagDet = codPagDet;
    }

    public Integer getCodMatriSem() {
        return codMatriSem;
    }

    public void setCodMatriSem(Integer codMatriSem) {
        this.codMatriSem = codMatriSem;
    }

    public Short getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Short numParcela) {
        this.numParcela = numParcela;
    }

    public Short getTotParcelas() {
        return totParcelas;
    }

    public void setTotParcelas(Short totParcelas) {
        this.totParcelas = totParcelas;
    }

    public Date getDataVencto() {
        return dataVencto;
    }

    public void setDataVencto(Date dataVencto) {
        this.dataVencto = dataVencto;
    }

    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public Short getTipoPagto() {
        return tipoPagto;
    }

    public void setTipoPagto(Short tipoPagto) {
        this.tipoPagto = tipoPagto;
    }

    public String getNumBoleto() {
        return numBoleto;
    }

    public void setNumBoleto(String numBoleto) {
        this.numBoleto = numBoleto;
    }

    public Date getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(Date dataPagto) {
        this.dataPagto = dataPagto;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Short getSituacaoPagto() {
        return situacaoPagto;
    }

    public void setSituacaoPagto(Short situacaoPagto) {
        this.situacaoPagto = situacaoPagto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPagDet != null ? codPagDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsPagamentosdetalhes)) {
            return false;
        }
        EsPagamentosdetalhes other = (EsPagamentosdetalhes) object;
        if ((this.codPagDet == null && other.codPagDet != null) || (this.codPagDet != null && !this.codPagDet.equals(other.codPagDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsPagamentosdetalhes[ codPagDet=" + codPagDet + " ]";
    }
    
}
