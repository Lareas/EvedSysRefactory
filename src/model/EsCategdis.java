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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ticoa
 */
@Entity
@Table(name = "es_categdis")
@XmlRootElement
public class EsCategdis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodCategDis")
    private Integer codCategDis;
    @Column(name = "CategDis")
    private String categDis;

    public EsCategdis() {
    }

    public EsCategdis(Integer codCategDis) {
        this.codCategDis = codCategDis;
    }

    public Integer getCodCategDis() {
        return codCategDis;
    }

    public void setCodCategDis(Integer codCategDis) {
        this.codCategDis = codCategDis;
    }

    public String getCategDis() {
        return categDis;
    }

    public void setCategDis(String categDis) {
        this.categDis = categDis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCategDis != null ? codCategDis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsCategdis)) {
            return false;
        }
        EsCategdis other = (EsCategdis) object;
        if ((this.codCategDis == null && other.codCategDis != null) || (this.codCategDis != null && !this.codCategDis.equals(other.codCategDis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsCategdis[ codCategDis=" + codCategDis + " ]";
    }
    
}
