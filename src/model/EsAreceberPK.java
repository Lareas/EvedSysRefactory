/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ticoa
 */
@Embeddable
public class EsAreceberPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CodAReceber")
    private int codAReceber;
    @Basic(optional = false)
    @Column(name = "DadoCadastroGeralId")
    private int dadoCadastroGeralId;
    @Basic(optional = false)
    @Column(name = "CodMatriSem")
    private int codMatriSem;

    public EsAreceberPK() {
    }

    public EsAreceberPK(int codAReceber, int dadoCadastroGeralId, int codMatriSem) {
        this.codAReceber = codAReceber;
        this.dadoCadastroGeralId = dadoCadastroGeralId;
        this.codMatriSem = codMatriSem;
    }

    public int getCodAReceber() {
        return codAReceber;
    }

    public void setCodAReceber(int codAReceber) {
        this.codAReceber = codAReceber;
    }

    public int getDadoCadastroGeralId() {
        return dadoCadastroGeralId;
    }

    public void setDadoCadastroGeralId(int dadoCadastroGeralId) {
        this.dadoCadastroGeralId = dadoCadastroGeralId;
    }

    public int getCodMatriSem() {
        return codMatriSem;
    }

    public void setCodMatriSem(int codMatriSem) {
        this.codMatriSem = codMatriSem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codAReceber;
        hash += (int) dadoCadastroGeralId;
        hash += (int) codMatriSem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsAreceberPK)) {
            return false;
        }
        EsAreceberPK other = (EsAreceberPK) object;
        if (this.codAReceber != other.codAReceber) {
            return false;
        }
        if (this.dadoCadastroGeralId != other.dadoCadastroGeralId) {
            return false;
        }
        if (this.codMatriSem != other.codMatriSem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EsAreceberPK[ codAReceber=" + codAReceber + ", dadoCadastroGeralId=" + dadoCadastroGeralId + ", codMatriSem=" + codMatriSem + " ]";
    }
    
}
