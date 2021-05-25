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
@Table(name = "lista_cabec")
public class ListaCabec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodLista")
    private Integer codLista;
    
    
//    @Column(name = "CadastroDisciplinaId")
//    private Integer cadastroDisciplinaId;
    
    @ManyToOne
    @JoinColumn(name = "CadastroDisciplinaId", referencedColumnName = "cadastroDisciplinaId", nullable = true)
    private Cadastrodisciplina nomeCadDisId;
    
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

    public ListaCabec() {
    }

    public ListaCabec(Integer codLista) {
        this.codLista = codLista;
    }

    public Integer getCodLista() {
        return codLista;
    }

    public void setCodLista(Integer codLista) {
        this.codLista = codLista;
    }

    public Cadastrodisciplina getNomeCadDisId() {
        return nomeCadDisId;
    }

    public void setNomeCadDisId(Cadastrodisciplina nomeCadDisId) {
        this.nomeCadDisId = nomeCadDisId;
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
        hash += (codLista != null ? codLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaCabec)) {
            return false;
        }
        ListaCabec other = (ListaCabec) object;
        if ((this.codLista == null && other.codLista != null) || (this.codLista != null && !this.codLista.equals(other.codLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ListaCabec[ codLista=" + codLista + " ]";
    }
    
}
