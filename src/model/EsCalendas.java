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
@Table(name = "es_calendas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsCalendas.findAll", query = "SELECT e FROM EsCalendas e")
    , @NamedQuery(name = "EsCalendas.findByCodescalendas", query = "SELECT e FROM EsCalendas e WHERE e.codescalendas = :codescalendas")
    , @NamedQuery(name = "EsCalendas.findByCodGrade", query = "SELECT e FROM EsCalendas e WHERE e.codGrade = :codGrade")
    , @NamedQuery(name = "EsCalendas.findByData", query = "SELECT e FROM EsCalendas e WHERE e.data = :data")
    , @NamedQuery(name = "EsCalendas.findByHoraIni", query = "SELECT e FROM EsCalendas e WHERE e.horaIni = :horaIni")
    , @NamedQuery(name = "EsCalendas.findByHoraFil", query = "SELECT e FROM EsCalendas e WHERE e.horaFil = :horaFil")
    , @NamedQuery(name = "EsCalendas.findByCodDisc", query = "SELECT e FROM EsCalendas e WHERE e.codDisc = :codDisc")
    , @NamedQuery(name = "EsCalendas.findByCodfer", query = "SELECT e FROM EsCalendas e WHERE e.codfer = :codfer")
    , @NamedQuery(name = "EsCalendas.findByCodSala", query = "SELECT e FROM EsCalendas e WHERE e.codSala = :codSala")
    , @NamedQuery(name = "EsCalendas.findByLabelCalend", query = "SELECT e FROM EsCalendas e WHERE e.labelCalend = :labelCalend")
    , @NamedQuery(name = "EsCalendas.findByCorLabel", query = "SELECT e FROM EsCalendas e WHERE e.corLabel = :corLabel")})
public class EsCalendas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codescalendas")
    private Integer codescalendas;
    @Column(name = "CodGrade")
    private Integer codGrade;
    @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "HoraIni")
    @Temporal(TemporalType.TIME)
    private Date horaIni;
    @Column(name = "HoraFil")
    @Temporal(TemporalType.TIME)
    private Date horaFil;
    @Column(name = "CodDisc")
    private Integer codDisc;
    @Column(name = "Codfer")
    private Integer codfer;
    @Column(name = "CodSala")
    private Integer codSala;
    @Column(name = "LabelCalend")
    private String labelCalend;
    @Column(name = "corLabel")
    private String corLabel;

    public EsCalendas() {
    }

    public EsCalendas(Integer codescalendas) {
        this.codescalendas = codescalendas;
    }

    public Integer getCodescalendas() {
        return codescalendas;
    }

    public void setCodescalendas(Integer codescalendas) {
        this.codescalendas = codescalendas;
    }

    public Integer getCodGrade() {
        return codGrade;
    }

    public void setCodGrade(Integer codGrade) {
        this.codGrade = codGrade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(Date horaIni) {
        this.horaIni = horaIni;
    }

    public Date getHoraFil() {
        return horaFil;
    }

    public void setHoraFil(Date horaFil) {
        this.horaFil = horaFil;
    }

    public Integer getCodDisc() {
        return codDisc;
    }

    public void setCodDisc(Integer codDisc) {
        this.codDisc = codDisc;
    }

    public Integer getCodfer() {
        return codfer;
    }

    public void setCodfer(Integer codfer) {
        this.codfer = codfer;
    }

    public Integer getCodSala() {
        return codSala;
    }

    public void setCodSala(Integer codSala) {
        this.codSala = codSala;
    }

    public String getLabelCalend() {
        return labelCalend;
    }

    public void setLabelCalend(String labelCalend) {
        this.labelCalend = labelCalend;
    }

    public String getCorLabel() {
        return corLabel;
    }

    public void setCorLabel(String corLabel) {
        this.corLabel = corLabel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codescalendas != null ? codescalendas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsCalendas)) {
            return false;
        }
        EsCalendas other = (EsCalendas) object;
        if ((this.codescalendas == null && other.codescalendas != null) || (this.codescalendas != null && !this.codescalendas.equals(other.codescalendas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsCalendas[ codescalendas=" + codescalendas + " ]";
    }

}
