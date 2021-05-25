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


@Entity
@Table(name = "es_dispensadet")
public class EsDispensadet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodDispensaDet")
    private Integer codDispensaDet;
    @Column(name = "Instituicao")
    private String instituicao;
    @Column(name = "Disciplina")
    private String disciplina;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Carga")
    private BigDecimal carga;
    @Column(name = "Credito")
    private Integer credito;
    @Column(name = "Ano")
    private Integer ano;
    @Column(name = "Nota")
    private String nota;
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

    public EsDispensadet() {
    }

    public EsDispensadet(Integer codDispensaDet) {
        this.codDispensaDet = codDispensaDet;
    }

    public Integer getCodDispensaDet() {
        return codDispensaDet;
    }

    public void setCodDispensaDet(Integer codDispensaDet) {
        this.codDispensaDet = codDispensaDet;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public BigDecimal getCarga() {
        return carga;
    }

    public void setCarga(BigDecimal carga) {
        this.carga = carga;
    }

    public Integer getCredito() {
        return credito;
    }

    public void setCredito(Integer credito) {
        this.credito = credito;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
        hash += (codDispensaDet != null ? codDispensaDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsDispensadet)) {
            return false;
        }
        EsDispensadet other = (EsDispensadet) object;
        if ((this.codDispensaDet == null && other.codDispensaDet != null) || (this.codDispensaDet != null && !this.codDispensaDet.equals(other.codDispensaDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsDispensadet[ codDispensaDet=" + codDispensaDet + " ]";
    }

}
