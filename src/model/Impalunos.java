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
@Table(name = "impalunos")
public class Impalunos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codimpressao")
    private Integer codimpressao;
    @Column(name = "jasperLocal")
    private String jasperLocal;
    @Column(name = "jasperServer")
    private String jasperServer;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "vale")
    private Boolean vale;

    public Impalunos() {
    }

    public Impalunos(Integer codimpressao) {
        this.codimpressao = codimpressao;
    }

    public Integer getCodimpressao() {
        return codimpressao;
    }

    public void setCodimpressao(Integer codimpressao) {
        this.codimpressao = codimpressao;
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

    public Boolean getVale() {
        return vale;
    }

    public void setVale(Boolean vale) {
        this.vale = vale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codimpressao != null ? codimpressao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impalunos)) {
            return false;
        }
        Impalunos other = (Impalunos) object;
        if ((this.codimpressao == null && other.codimpressao != null) || (this.codimpressao != null && !this.codimpressao.equals(other.codimpressao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Impalunos[ codimpressao=" + codimpressao + " ]";
    }
    
}
