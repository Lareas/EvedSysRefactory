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
@Table(name = "es_equivalenciadet")
public class EsEquivalenciadet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodEquivalenciaDet")
    private Integer codEquivalenciaDet;
    @Column(name = "CodEquivalencia")
    private Integer codEquivalencia;
    @Column(name = "Instituicao")
    private String instituicao;
    @Column(name = "Disciplina")
    private String disciplina;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Carga")
    private BigDecimal carga;
    @Column(name = "Credito")
    private String credito;
    @Column(name = "Ano")
    private String ano;
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

    public EsEquivalenciadet() {
    }

    public EsEquivalenciadet(Integer codEquivalenciaDet) {
        this.codEquivalenciaDet = codEquivalenciaDet;
    }

    public Integer getCodEquivalenciaDet() {
        return codEquivalenciaDet;
    }

    public void setCodEquivalenciaDet(Integer codEquivalenciaDet) {
        this.codEquivalenciaDet = codEquivalenciaDet;
    }

    public Integer getCodEquivalencia() {
        return codEquivalencia;
    }

    public void setCodEquivalencia(Integer codEquivalencia) {
        this.codEquivalencia = codEquivalencia;
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

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
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
        hash += (codEquivalenciaDet != null ? codEquivalenciaDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsEquivalenciadet)) {
            return false;
        }
        EsEquivalenciadet other = (EsEquivalenciadet) object;
        if ((this.codEquivalenciaDet == null && other.codEquivalenciaDet != null) || (this.codEquivalenciaDet != null && !this.codEquivalenciaDet.equals(other.codEquivalenciaDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsEquivalenciadet[ codEquivalenciaDet=" + codEquivalenciaDet + " ]";
    }

}
