/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ticoa
 */
@Entity
@Table(name = "lista_aula")
public class ListaAula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodLista_Aula")
    private Integer codLau;
    
    @Column(name = "CodLista")
    private Integer codLista;
    
//    @ManyToOne
//    @JoinColumn(name = "codLista", referencedColumnName = "codLista", nullable = true)
//    private ListaCabec nomeCodLista;
    
    @Column(name = "NumAula")
    private Integer numAula;
    @Column(name = "DataAula")
    @Temporal(TemporalType.DATE)
    private Date dataAula;
    @Column(name = "ObsAula")
    private String obsAula;
    
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
    
    

    public ListaAula() {
    }

    public Integer getCodLau() {
        return codLau;
    }

    public void setCodLau(Integer codLau) {
        this.codLau = codLau;
    }

    public Integer getCodLista() {
        return codLista;
    }

    public void setCodLista(Integer codLista) {
        this.codLista = codLista;
    }


    public Integer getNumAula() {
        return numAula;
    }

    public void setNumAula(Integer numAula) {
        this.numAula = numAula;
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }

    public String getObsAula() {
        return obsAula;
    }

    public void setObsAula(String obsAula) {
        this.obsAula = obsAula;
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

    
}
