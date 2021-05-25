/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "es_areceber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsAreceber.findAll", query = "SELECT e FROM EsAreceber e")
    , @NamedQuery(name = "EsAreceber.findByCodAReceber", query = "SELECT e FROM EsAreceber e WHERE e.esAreceberPK.codAReceber = :codAReceber")
    , @NamedQuery(name = "EsAreceber.findByDadoCadastroGeralId", query = "SELECT e FROM EsAreceber e WHERE e.esAreceberPK.dadoCadastroGeralId = :dadoCadastroGeralId")
    , @NamedQuery(name = "EsAreceber.findByCodMatriSem", query = "SELECT e FROM EsAreceber e WHERE e.esAreceberPK.codMatriSem = :codMatriSem")
    , @NamedQuery(name = "EsAreceber.findByDataVencto", query = "SELECT e FROM EsAreceber e WHERE e.dataVencto = :dataVencto")
    , @NamedQuery(name = "EsAreceber.findByValorAPagar", query = "SELECT e FROM EsAreceber e WHERE e.valorAPagar = :valorAPagar")
    , @NamedQuery(name = "EsAreceber.findByDataPagto", query = "SELECT e FROM EsAreceber e WHERE e.dataPagto = :dataPagto")
    , @NamedQuery(name = "EsAreceber.findByValorPago", query = "SELECT e FROM EsAreceber e WHERE e.valorPago = :valorPago")
    , @NamedQuery(name = "EsAreceber.findByMulta", query = "SELECT e FROM EsAreceber e WHERE e.multa = :multa")
    , @NamedQuery(name = "EsAreceber.findByDocumento", query = "SELECT e FROM EsAreceber e WHERE e.documento = :documento")
    , @NamedQuery(name = "EsAreceber.findByCodtransacao", query = "SELECT e FROM EsAreceber e WHERE e.codtransacao = :codtransacao")})
public class EsAreceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EsAreceberPK esAreceberPK;
    @Column(name = "DataVencto")
    @Temporal(TemporalType.DATE)
    private Date dataVencto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValorAPagar")
    private BigDecimal valorAPagar;
    @Column(name = "DataPagto")
    @Temporal(TemporalType.DATE)
    private Date dataPagto;
    @Column(name = "ValorPago")
    private BigDecimal valorPago;
    @Column(name = "Multa")
    private Long multa;
    @Column(name = "Documento")
    private String documento;
    @Column(name = "Codtransacao")
    private String codtransacao;

    public EsAreceber() {
    }

    public EsAreceber(EsAreceberPK esAreceberPK) {
        this.esAreceberPK = esAreceberPK;
    }

    public EsAreceber(int codAReceber, int dadoCadastroGeralId, int codMatriSem) {
        this.esAreceberPK = new EsAreceberPK(codAReceber, dadoCadastroGeralId, codMatriSem);
    }

    public EsAreceberPK getEsAreceberPK() {
        return esAreceberPK;
    }

    public void setEsAreceberPK(EsAreceberPK esAreceberPK) {
        this.esAreceberPK = esAreceberPK;
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

    public Long getMulta() {
        return multa;
    }

    public void setMulta(Long multa) {
        this.multa = multa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCodtransacao() {
        return codtransacao;
    }

    public void setCodtransacao(String codtransacao) {
        this.codtransacao = codtransacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esAreceberPK != null ? esAreceberPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsAreceber)) {
            return false;
        }
        EsAreceber other = (EsAreceber) object;
        if ((this.esAreceberPK == null && other.esAreceberPK != null) || (this.esAreceberPK != null && !this.esAreceberPK.equals(other.esAreceberPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsAreceber[ esAreceberPK=" + esAreceberPK + " ]";
    }
    
}
