package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "es_fotosalunos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsFotosalunos.findAll", query = "SELECT e FROM EsFotosalunos e")
    , @NamedQuery(name = "EsFotosalunos.findByCodFotoAluno", query = "SELECT e FROM EsFotosalunos e WHERE e.codFotoAluno = :codFotoAluno")
    , @NamedQuery(name = "EsFotosalunos.findByDadoCadastroGeralId", query = "SELECT e FROM EsFotosalunos e WHERE e.dadoCadastroGeralId = :dadoCadastroGeralId")})
public class EsFotosalunos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodFotoAluno")
    private Short codFotoAluno;
    @Column(name = "DadoCadastroGeralId")
    private Integer dadoCadastroGeralId;
    @Lob
    @Column(name = "FotoAluno")
    private byte[] fotoAluno;
    
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
    
    public EsFotosalunos() {
    }

    public Short getCodFotoAluno() {
        return codFotoAluno;
    }

    public void setCodFotoAluno(Short codFotoAluno) {
        this.codFotoAluno = codFotoAluno;
    }

    public Integer getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(Integer dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public byte[] getFotoAluno() {
        return fotoAluno;
    }

    public void setFotoAluno(byte[] fotoAluno) {
        this.fotoAluno = fotoAluno;
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
        hash += (codFotoAluno != null ? codFotoAluno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsFotosalunos)) {
            return false;
        }
        EsFotosalunos other = (EsFotosalunos) object;
        if ((this.codFotoAluno == null && other.codFotoAluno != null) || (this.codFotoAluno != null && !this.codFotoAluno.equals(other.codFotoAluno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsFotosalunos[ codFotoAluno=" + codFotoAluno + " ]";
    }

}
