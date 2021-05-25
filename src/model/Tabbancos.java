/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luiza
 */
@Entity
@Table(name = "tabbancos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tabbancos.findAll", query = "SELECT t FROM Tabbancos t")
    , @NamedQuery(name = "Tabbancos.findByChaveBanco", query = "SELECT t FROM Tabbancos t WHERE t.chaveBanco = :chaveBanco")
    , @NamedQuery(name = "Tabbancos.findByCodBanco", query = "SELECT t FROM Tabbancos t WHERE t.codBanco = :codBanco")
    , @NamedQuery(name = "Tabbancos.findByNomeBanco", query = "SELECT t FROM Tabbancos t WHERE t.nomeBanco = :nomeBanco")})
public class Tabbancos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ChaveBanco")
    private Integer chaveBanco;
    @Column(name = "CodBanco")
    private Integer codBanco;
    @Column(name = "NomeBanco")
    private String nomeBanco;

    public Tabbancos() {
    }

    public Tabbancos(Integer chaveBanco) {
        this.chaveBanco = chaveBanco;
    }

    public Integer getChaveBanco() {
        return chaveBanco;
    }

    public void setChaveBanco(Integer chaveBanco) {
        this.chaveBanco = chaveBanco;
    }

    public Integer getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(Integer codBanco) {
        this.codBanco = codBanco;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chaveBanco != null ? chaveBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabbancos)) {
            return false;
        }
        Tabbancos other = (Tabbancos) object;
        if ((this.chaveBanco == null && other.chaveBanco != null) || (this.chaveBanco != null && !this.chaveBanco.equals(other.chaveBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tabbancos[ chaveBanco=" + chaveBanco + " ]";
    }
    
}
