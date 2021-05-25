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
@Table(name = "turno")
public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TurnoId")
    private Short turnoId;
    @Basic(optional = false)
    @Column(name = "Turno")
    private String turno;
    
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
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turnoNome")
//    private Collection<Cadastrodisciplina> cadDisCollection;
    
//    @OneToMany(mappedBy = "turnoNome")
//    Collection<Cadastrodisciplina> cadastrosDis = new ArrayList<Cadastrodisciplina>();

    public Turno() {
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

    
    public Turno(Short turnoId) {
        this.turnoId = turnoId;
    }

    public Short getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Short turnoId) {
        this.turnoId = turnoId;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }



//    public Collection<Cadastrodisciplina> getCadDisCollection() {
//        return cadDisCollection;
//    }
//
//    public void setCadDisCollection(Collection<Cadastrodisciplina> cadDisCollection) {
//        this.cadDisCollection = cadDisCollection;
//    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turnoId != null ? turnoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.turnoId == null && other.turnoId != null) || (this.turnoId != null && !this.turnoId.equals(other.turnoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Turno[ turnoId=" + turnoId + " ]";
    }

}
