package entities;

import java.io.Serializable;
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
@Table(name = "es_dispensa")
public class EsDispensa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodDispensa")
    private Integer codDispensa;
    @Column(name = "CodAluno")
    private Integer codAluno;
    @Column(name = "CodPrograma")
    private Integer codPrograma;
    @Column(name = "CodDisciplina")
    private Integer codDisciplina;
    @Column(name = "Observacoes")
    private String observacoes;
    @Column(name = "DocsDigi")
    private Boolean docsDigi;
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

    public EsDispensa() {
    }

    public EsDispensa(Integer codDispensa) {
        this.codDispensa = codDispensa;
    }

    public Integer getCodDispensa() {
        return codDispensa;
    }

    public void setCodDispensa(Integer codDispensa) {
        this.codDispensa = codDispensa;
    }

    public Integer getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Integer codAluno) {
        this.codAluno = codAluno;
    }

    public Integer getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(Integer codPrograma) {
        this.codPrograma = codPrograma;
    }

    public Integer getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(Integer codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getDocsDigi() {
        return docsDigi;
    }

    public void setDocsDigi(Boolean docsDigi) {
        this.docsDigi = docsDigi;
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
        hash += (codDispensa != null ? codDispensa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsDispensa)) {
            return false;
        }
        EsDispensa other = (EsDispensa) object;
        if ((this.codDispensa == null && other.codDispensa != null) || (this.codDispensa != null && !this.codDispensa.equals(other.codDispensa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsDispensa[ codDispensa=" + codDispensa + " ]";
    }

}
