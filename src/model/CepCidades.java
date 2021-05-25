package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "cep_cidades")
public class CepCidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Column(name = "UF")
    private String uf;
    @Basic(optional = false)
    @Column(name = "CEP_UNICO")
    private short cepUnico;
    @Column(name = "CEP")
    private String cep;
    @Basic(optional = false)
    @Column(name = "NOME_BUSCA")
    private String nomeBusca;

    public CepCidades() {
    }

    public CepCidades(Integer codigo) {
        this.codigo = codigo;
    }

    public CepCidades(Integer codigo, String nome, short cepUnico, String nomeBusca) {
        this.codigo = codigo;
        this.nome = nome;
        this.cepUnico = cepUnico;
        this.nomeBusca = nomeBusca;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public short getCepUnico() {
        return cepUnico;
    }

    public void setCepUnico(short cepUnico) {
        this.cepUnico = cepUnico;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CepCidades)) {
            return false;
        }
        CepCidades other = (CepCidades) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CepCidades[ codigo=" + codigo + " ]";
    }

}
