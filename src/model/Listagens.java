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
@Table(name = "listagens")
public class Listagens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codListagem")
    private Integer codListagem;
    @Column(name = "jasperLocal")
    private String jasperLocal;
    @Column(name = "jasperServer")
    private String jasperServer;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "tipo")
    private Integer tipo;

    public Listagens() {
    }

    public Listagens(Integer codListagem) {
        this.codListagem = codListagem;
    }

    public Integer getCodListagem() {
        return codListagem;
    }

    public void setCodListagem(Integer codListagem) {
        this.codListagem = codListagem;
    }

    public String getJasperLocal() {
        return jasperLocal;
    }

    public void setJasperLocal(String jasperLocal) {
        this.jasperLocal = jasperLocal;
    }

    public String getJasperServer() {
        return jasperServer;
    }

    public void setJasperServer(String jasperServer) {
        this.jasperServer = jasperServer;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codListagem != null ? codListagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listagens)) {
            return false;
        }
        Listagens other = (Listagens) object;
        if ((this.codListagem == null && other.codListagem != null) || (this.codListagem != null && !this.codListagem.equals(other.codListagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Listagens[ codListagem=" + codListagem + " ]";
    }
    
}
