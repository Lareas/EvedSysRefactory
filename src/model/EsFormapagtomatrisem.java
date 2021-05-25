/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ticoa
 */
@Entity
@Table(name = "es_formapagtomatrisem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsFormapagtomatrisem.findAll", query = "SELECT e FROM EsFormapagtomatrisem e")
    , @NamedQuery(name = "EsFormapagtomatrisem.findByCodFormaPagtoMatriSem", query = "SELECT e FROM EsFormapagtomatrisem e WHERE e.codFormaPagtoMatriSem = :codFormaPagtoMatriSem")
    , @NamedQuery(name = "EsFormapagtomatrisem.findByCodMatriSem", query = "SELECT e FROM EsFormapagtomatrisem e WHERE e.codMatriSem = :codMatriSem")
    , @NamedQuery(name = "EsFormapagtomatrisem.findByTipoPagto", query = "SELECT e FROM EsFormapagtomatrisem e WHERE e.tipoPagto = :tipoPagto")
    , @NamedQuery(name = "EsFormapagtomatrisem.findByValorAPagar", query = "SELECT e FROM EsFormapagtomatrisem e WHERE e.valorAPagar = :valorAPagar")
    , @NamedQuery(name = "EsFormapagtomatrisem.findByNumParcelas", query = "SELECT e FROM EsFormapagtomatrisem e WHERE e.numParcelas = :numParcelas")})
public class EsFormapagtomatrisem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodFormaPagtoMatriSem")
    private Integer codFormaPagtoMatriSem;
    @Basic(optional = false)
    @Column(name = "CodMatriSem")
    private int codMatriSem;
    @Column(name = "TipoPagto")
    private Short tipoPagto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValorAPagar")
    private BigDecimal valorAPagar;
    @Column(name = "NumParcelas")
    private Short numParcelas;

    public EsFormapagtomatrisem() {
    }

    public EsFormapagtomatrisem(Integer codFormaPagtoMatriSem) {
        this.codFormaPagtoMatriSem = codFormaPagtoMatriSem;
    }

    public EsFormapagtomatrisem(Integer codFormaPagtoMatriSem, int codMatriSem) {
        this.codFormaPagtoMatriSem = codFormaPagtoMatriSem;
        this.codMatriSem = codMatriSem;
    }

    public Integer getCodFormaPagtoMatriSem() {
        return codFormaPagtoMatriSem;
    }

    public void setCodFormaPagtoMatriSem(Integer codFormaPagtoMatriSem) {
        this.codFormaPagtoMatriSem = codFormaPagtoMatriSem;
    }

    public int getCodMatriSem() {
        return codMatriSem;
    }

    public void setCodMatriSem(int codMatriSem) {
        this.codMatriSem = codMatriSem;
    }

    public Short getTipoPagto() {
        return tipoPagto;
    }

    public void setTipoPagto(Short tipoPagto) {
        this.tipoPagto = tipoPagto;
    }

    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public Short getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(Short numParcelas) {
        this.numParcelas = numParcelas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFormaPagtoMatriSem != null ? codFormaPagtoMatriSem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsFormapagtomatrisem)) {
            return false;
        }
        EsFormapagtomatrisem other = (EsFormapagtomatrisem) object;
        if ((this.codFormaPagtoMatriSem == null && other.codFormaPagtoMatriSem != null) || (this.codFormaPagtoMatriSem != null && !this.codFormaPagtoMatriSem.equals(other.codFormaPagtoMatriSem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsFormapagtomatrisem[ codFormaPagtoMatriSem=" + codFormaPagtoMatriSem + " ]";
    }
    
}
