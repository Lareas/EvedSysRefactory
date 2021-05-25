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
@Table(name = "tabuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tabuser.findAll", query = "SELECT t FROM Tabuser t")
    , @NamedQuery(name = "Tabuser.findByCoduser", query = "SELECT t FROM Tabuser t WHERE t.coduser = :coduser")
    , @NamedQuery(name = "Tabuser.findByLogin", query = "SELECT t FROM Tabuser t WHERE t.login = :login")
    , @NamedQuery(name = "Tabuser.findByPassword", query = "SELECT t FROM Tabuser t WHERE t.password = :password")
    , @NamedQuery(name = "Tabuser.findByCodperfil", query = "SELECT t FROM Tabuser t WHERE t.codperfil = :codperfil")})
public class Tabuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coduser")
    private Integer coduser;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "codperfil")
    private Integer codperfil;

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

    @Column(name = "PastaIni")
    private String pastaIni;

    @Column(name = "SemestrePadrao")
    private Short semestrePadrao;

    @Column(name = "AnoPadrao")
    private Short anoPadrao;
    
    @Column(name = "PastaFotos")
    private String pastaFotos;
    
    @Column(name = "PastaRelat")
    private String pastaRelat;

    public Tabuser() {
    }

    public Tabuser(Integer coduser) {
        this.coduser = coduser;
    }

    public String getPastaIni() {
        return pastaIni;
    }

    public String getPastaRelat() {
        return pastaRelat;
    }

    public void setPastaRelat(String pastaRelat) {
        this.pastaRelat = pastaRelat;
    }
    
    public void setPastaIni(String pastaIni) {
        this.pastaIni = pastaIni;
    }

    public Short getSemestrePadrao() {
        return semestrePadrao;
    }

    public void setSemestrePadrao(Short semestrePadrao) {
        this.semestrePadrao = semestrePadrao;
    }

    public String getPastaFotos() {
        return pastaFotos;
    }

    public void setPastaFotos(String pastaFotos) {
        this.pastaFotos = pastaFotos;
    }

    public Short getAnoPadrao() {
        return anoPadrao;
    }

    public void setAnoPadrao(Short anoPadrao) {
        this.anoPadrao = anoPadrao;
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

    public Integer getCoduser() {
        return coduser;
    }

    public void setCoduser(Integer coduser) {
        this.coduser = coduser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCodperfil() {
        return codperfil;
    }

    public void setCodperfil(Integer codperfil) {
        this.codperfil = codperfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coduser != null ? coduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabuser)) {
            return false;
        }
        Tabuser other = (Tabuser) object;
        if ((this.coduser == null && other.coduser != null) || (this.coduser != null && !this.coduser.equals(other.coduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tabuser[ coduser=" + coduser + " ]";
    }

}
