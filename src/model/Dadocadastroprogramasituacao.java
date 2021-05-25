package entities;

import java.io.Serializable;
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

@Entity
@Table(name = "dadocadastroprogramasituacao")
public class Dadocadastroprogramasituacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DadoCadastroProgramaSituacaoId")
    private Integer dadoCadastroProgramaSituacaoId;

    @Basic(optional = false)
    @Column(name = "DadoCadastroProgramaSituacao")
    private String dadoCadastroProgramaSituacao;

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

    public Dadocadastroprogramasituacao() {
    }

    public Dadocadastroprogramasituacao(Integer dadoCadastroProgramaSituacaoId) {
        this.dadoCadastroProgramaSituacaoId = dadoCadastroProgramaSituacaoId;
    }

    public Dadocadastroprogramasituacao(Integer dadoCadastroProgramaSituacaoId, String dadoCadastroProgramaSituacao) {
        this.dadoCadastroProgramaSituacaoId = dadoCadastroProgramaSituacaoId;
        this.dadoCadastroProgramaSituacao = dadoCadastroProgramaSituacao;
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

    public Integer getDadoCadastroProgramaSituacaoId() {
        return dadoCadastroProgramaSituacaoId;
    }

    public void setDadoCadastroProgramaSituacaoId(Integer dadoCadastroProgramaSituacaoId) {
        this.dadoCadastroProgramaSituacaoId = dadoCadastroProgramaSituacaoId;
    }

    public String getDadoCadastroProgramaSituacao() {
        return dadoCadastroProgramaSituacao;
    }

    public void setDadoCadastroProgramaSituacao(String dadoCadastroProgramaSituacao) {
        this.dadoCadastroProgramaSituacao = dadoCadastroProgramaSituacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dadoCadastroProgramaSituacaoId != null ? dadoCadastroProgramaSituacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dadocadastroprogramasituacao)) {
            return false;
        }
        Dadocadastroprogramasituacao other = (Dadocadastroprogramasituacao) object;
        if ((this.dadoCadastroProgramaSituacaoId == null && other.dadoCadastroProgramaSituacaoId != null) || (this.dadoCadastroProgramaSituacaoId != null && !this.dadoCadastroProgramaSituacaoId.equals(other.dadoCadastroProgramaSituacaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dadocadastroprogramasituacao[ dadoCadastroProgramaSituacaoId=" + dadoCadastroProgramaSituacaoId + " ]";
    }

}
