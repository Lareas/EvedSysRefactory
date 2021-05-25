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
@Table(name = "dadocadastrogeralministeriais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dadocadastrogeralministeriais.findAll", query = "SELECT d FROM Dadocadastrogeralministeriais d")
    , @NamedQuery(name = "Dadocadastrogeralministeriais.findByDadoCadastroGeralMinisteriaisId", query = "SELECT d FROM Dadocadastrogeralministeriais d WHERE d.dadoCadastroGeralMinisteriaisId = :dadoCadastroGeralMinisteriaisId")
    , @NamedQuery(name = "Dadocadastrogeralministeriais.findByDadoCadastroGeralId", query = "SELECT d FROM Dadocadastrogeralministeriais d WHERE d.dadoCadastroGeralId = :dadoCadastroGeralId")
    , @NamedQuery(name = "Dadocadastrogeralministeriais.findByMinisteriais", query = "SELECT d FROM Dadocadastrogeralministeriais d WHERE d.ministeriais = :ministeriais")})
public class Dadocadastrogeralministeriais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DadoCadastroGeralMinisteriaisId")
    private Integer dadoCadastroGeralMinisteriaisId;
    @Basic(optional = false)
    @Column(name = "DadoCadastroGeralId")
    private int dadoCadastroGeralId;
    @Column(name = "Ministeriais")
    private String ministeriais;

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
    
    
    public Dadocadastrogeralministeriais() {
    }

    public Integer getDadoCadastroGeralMinisteriaisId() {
        return dadoCadastroGeralMinisteriaisId;
    }

    public void setDadoCadastroGeralMinisteriaisId(Integer dadoCadastroGeralMinisteriaisId) {
        this.dadoCadastroGeralMinisteriaisId = dadoCadastroGeralMinisteriaisId;
    }

    public int getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
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

    
    public void setDadoCadastroGeralId(int dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public String getMinisteriais() {
        return ministeriais;
    }

    public void setMinisteriais(String ministeriais) {
        this.ministeriais = ministeriais;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dadoCadastroGeralMinisteriaisId != null ? dadoCadastroGeralMinisteriaisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dadocadastrogeralministeriais)) {
            return false;
        }
        Dadocadastrogeralministeriais other = (Dadocadastrogeralministeriais) object;
        if ((this.dadoCadastroGeralMinisteriaisId == null && other.dadoCadastroGeralMinisteriaisId != null) || (this.dadoCadastroGeralMinisteriaisId != null && !this.dadoCadastroGeralMinisteriaisId.equals(other.dadoCadastroGeralMinisteriaisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Dadocadastrogeralministeriais[ dadoCadastroGeralMinisteriaisId=" + dadoCadastroGeralMinisteriaisId + " ]";
    }

}
